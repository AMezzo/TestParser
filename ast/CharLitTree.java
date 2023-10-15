package ast.trees;

import ast.AST;
import lexer.daos.Token;
import visitor.TreeVisitor;

public class CharLitTree extends AST {
    private Token token;

    public CharLitTree(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "CharLiteral: " + token.getLexeme();
    }
}