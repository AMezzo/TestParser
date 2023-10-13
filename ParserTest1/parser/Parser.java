package parser;

import java.util.EnumSet;

import ast.AST;
import ast.trees.*;
import lexer.ILexer;
import lexer.Lexception;
import lexer.Lexer;
import lexer.daos.Token;
import lexer.daos.TokenKind;

public class Parser {
    private Token currentToken;
    private ILexer lexer;

    private EnumSet<TokenKind> relationalOperators = EnumSet.of(
        TokenKind.Equal,
        TokenKind.NotEqual,
        TokenKind.Less,
        TokenKind.LessEqual,
        TokenKind.GreaterEqual  // Aggiunto
    );
    private EnumSet<TokenKind> additionOperators = EnumSet.of(
        TokenKind.Plus,
        TokenKind.Minus,
        TokenKind.Or,
        TokenKind.BoolOr,  // Aggiunto
        TokenKind.BoolXor, // Aggiunto
        TokenKind.BoolAnd  // Aggiunto
    );
    private EnumSet<TokenKind> multiplicationOperators = EnumSet.of(
        TokenKind.Multiply,
        TokenKind.Divide,
        TokenKind.And
    );

    public Parser(String sourceProgramPath) throws Lexception {
        this(new Lexer(sourceProgramPath));
    }

    public Parser(ILexer lexer) throws Lexception {
        this.lexer = lexer;
        scan();
    }

    private void expect(TokenKind expected) throws SyntaxErrorException, Lexception {
        if (this.currentToken.getTokenKind() == expected) {
            scan();
        } else {
            error(currentToken.getTokenKind(), expected);
        }
    }

    private void error(TokenKind actual, TokenKind... expected) throws SyntaxErrorException {
        throw new SyntaxErrorException(actual, expected);
    }

    private void scan() throws Lexception {
        this.currentToken = lexer.nextToken();
    }

    private boolean match(TokenKind... kinds) {
        for (TokenKind tokenKind : kinds) {
            if (this.currentToken.getTokenKind() == tokenKind) {
                return true;
            }
        }
        return false;
    }

    public AST execute() throws SyntaxErrorException, Lexception {
        return program();
    }

    private AST program() throws SyntaxErrorException, Lexception {
        AST node = new ProgramTree();
        expect(TokenKind.Program);
        node.addChild(block());
        return node;
    }

    private AST block() throws SyntaxErrorException, Lexception {
        AST node = new BlockTree();
        expect(TokenKind.LeftBrace);

        while (startingDeclaration()) {
            node.addChild(declaration());
        }

        while (startingStatement()) {
            node.addChild(statement());
        }

        expect(TokenKind.RightBrace);
        return node;
    }

    private boolean startingDeclaration() {
        return match(
            TokenKind.IntType,
            TokenKind.BooleanType,
            TokenKind.BinaryType,  // Aggiunto
            TokenKind.CharType      // Aggiunto
        );
    }

    private boolean startingStatement() {
        return match(
            TokenKind.If,
            TokenKind.While,
            TokenKind.Return,
            TokenKind.LeftBrace,
            TokenKind.Identifier
        );
    }

    private AST declaration() throws SyntaxErrorException, Lexception {
        AST type = type();
        AST name = name();

        if (match(TokenKind.LeftParen)) {
            AST function = new FunctionDeclarationTree().addChild(type).addChild(name);
            expect(TokenKind.LeftParen);
            function.addChild(formals());
            expect(TokenKind.RightParen);
            return function.addChild(block());
        } else {
            return new DeclarationTree().addChild(type).addChild(name);
        }
    }

    private AST type() throws Lexception, SyntaxErrorException {
        AST node = null;

        if (match(TokenKind.IntType)) {
            node = new IntTypeTree();
        } else if (match(TokenKind.BooleanType)) {
            node = new BoolTypeTree();
        } else if (match(TokenKind.BinaryType)) {  // Aggiunto
            node = new BinaryTypeTree();  // Assumendo che tu crei questa classe AST
        } else if (match(TokenKind.CharType)) {    // Aggiunto
            node = new CharTypeTree();    // Assumendo che tu crei questa classe AST
        } else {
            error(currentToken.getTokenKind(), TokenKind.IntType, TokenKind.BooleanType, TokenKind.BinaryType, TokenKind.CharType);  // Modificato
        }

        scan();
        return node;
    }

    private AST name() throws Lexception, SyntaxErrorException {
        AST node = null;

        if (match(TokenKind.Identifier)) {
            node = new IdentifierTree(currentToken);
            expect(TokenKind.Identifier);
        }

        return node;
    }

    private AST formals() throws SyntaxErrorException, Lexception {
        AST formals = new FormalsTree();

        if (match(TokenKind.RightParen)) {
            return formals;
        } else {
            do {
                formals.addChild(declaration());

                if (match(TokenKind.Comma)) {
                    expect(TokenKind.Comma);
                }
            } while (!match(TokenKind.RightParen));

            return formals;
        }
    }

    private AST statement() throws SyntaxErrorException, Lexception {
        switch (currentToken.getTokenKind()) {
            case If:
                return ifStatement();
            case While:
                return whileStatement();
            case Return:
                return returnStatement();
            case LeftBrace:
                return block();
            case Identifier:
                return assignStatement();
            default:
                error(
                    currentToken.getTokenKind(),
                    TokenKind.If,
                    TokenKind.While,
                    TokenKind.Return,
                    TokenKind.LeftBrace,
                    TokenKind.Identifier
                );
                return null;
        }
    }

    private AST ifStatement() throws SyntaxErrorException, Lexception {
        AST node = new IfTree();
        expect(TokenKind.If);
        node.addChild(expression());
        expect(TokenKind.Then);
        node.addChild(block());
        
        if (match(TokenKind.Else)) {  // Modificato per rendere 'else' opzionale
            expect(TokenKind.Else);
            node.addChild(block());
        }

        return node;
    }

    private AST whileStatement() throws SyntaxErrorException, Lexception {
        AST node = new WhileTree();
        expect(TokenKind.While);
        node.addChild(expression()).addChild(block());
        return node;
    }

    private AST returnStatement() throws SyntaxErrorException, Lexception {
        AST node = new ReturnTree();
        expect(TokenKind.Return);
        node.addChild(expression());
        return node;
    }

    private AST assignStatement() throws SyntaxErrorException, Lexception {
        AST node = new AssignmentTree();
        node.addChild(name());
        expect(TokenKind.Assign);
        node.addChild(expression());
        return node;
    }

    private AST expression() throws Lexception, SyntaxErrorException {
        AST tree, child = simpleExpression();
        tree = getRelopTree();
        if (tree == null) {
            return child;
        }
        tree.addChild(child);
        tree.addChild(simpleExpression());
        return tree;
    }

    private AST getRelopTree() throws Lexception {
        if (relationalOperators.contains(currentToken.getTokenKind())) {
            AST tree = new RelOpTree(currentToken);
            scan();
            return tree;
        } else {
            return null;
        }
    }

    private AST simpleExpression() throws Lexception, SyntaxErrorException {
        AST tree, child = term();
        tree = getAddopTree();
        while (tree != null) {
            tree.addChild(child);
            tree.addChild(term());
            child = tree;
            tree = getAddopTree();
        }
        return child;
    }

    private AST getAddopTree() throws Lexception {
        if (additionOperators.contains(currentToken.getTokenKind())) {
            AST tree = new AddOpTree(currentToken);
            scan();
            return tree;
        } else {
            return null;
        }
    }

    private AST term() throws Lexception, SyntaxErrorException {
        AST tree, child = factor();
        tree = getMulopTree();
        while (tree != null) {
            tree.addChild(child);
            tree.addChild(factor());
            child = tree;
            tree = getMulopTree();
        }
        return child;
    }

    private AST getMulopTree() throws Lexception {
        if (multiplicationOperators.contains(currentToken.getTokenKind())) {
            AST tree = new MulOpTree(currentToken);
            scan();
            return tree;
        } else {
            return null;
        }
    }

    private AST factor() throws Lexception, SyntaxErrorException {
        AST node = null;

        if (match(TokenKind.Identifier)) {
            node = name();
        } else if (match(TokenKind.IntLit)) {
            node = new IntLitTree(currentToken);
            scan();
        } else if (match(TokenKind.True, TokenKind.False)) {
            node = new BoolLitTree(currentToken);
            scan();
        } else if (match(TokenKind.BinaryLit)) {  // Aggiunto
            node = new BinaryLitTree(currentToken);  // Assumendo che tu crei questa classe AST
            scan();
        } else if (match(TokenKind.CharLit)) {    // Aggiunto
            node = new CharLitTree(currentToken);    // Assumendo che tu crei questa classe AST
            scan();
        } else if (match(TokenKind.LeftParen)) {
            scan();
            node = expression();
            expect(TokenKind.RightParen);
        } else {
            error(
                currentToken.getTokenKind(),
                TokenKind.Identifier,
                TokenKind.IntLit,
                TokenKind.True,
                TokenKind.False,
                TokenKind.BinaryLit,  // Aggiunto
                TokenKind.CharLit,    // Aggiunto
                TokenKind.LeftParen
            );
        }

        return node;
    }

    private AST iterStatement() throws SyntaxErrorException, Lexception {
        AST node = new IterTree();  // Assumendo che tu crei questa classe AST

        expect(TokenKind.Iterate);
        expect(TokenKind.Pipette);  // Assumendo che Pipette sia il token '|-'
        node.addChild(expression());  // Inizio intervallo
        expect(TokenKind.Tilde);  // Assumendo che Tilde sia il token '~'
        node.addChild(expression());  // Fine intervallo
        node.addChild(block());  // Blocco di codice da iterare

        return node;
    }
}
