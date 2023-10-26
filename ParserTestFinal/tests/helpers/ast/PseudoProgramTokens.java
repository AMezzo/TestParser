package tests.helpers.ast;

import java.util.HashMap;
import java.util.Map;

import lexer.SymbolTable;
import lexer.daos.Token;
import lexer.daos.TokenKind;

import static lexer.daos.TokenKind.*;

public class PseudoProgramTokens {

  private static Map<String, TokenKind> pseudoTokenMappings = Map.ofEntries(
      // Reserved words
      Map.entry("program", Program),
      Map.entry("if", If),
      Map.entry("then", Then),
      Map.entry("else", Else),
      Map.entry("while", While),
      Map.entry("function", Function),
      Map.entry("return", Return),
      // Separators
      Map.entry("{", LeftBrace),
      Map.entry("}", RightBrace),
      Map.entry("(", LeftParen),
      Map.entry(")", RightParen),
      Map.entry(",", Comma),

      // Operators
      Map.entry("=", Assign),
      Map.entry("+", Plus),
      Map.entry("-", Minus),
      Map.entry("|", Or),
      Map.entry("&", And),
      Map.entry("*", Multiply),
      Map.entry("/", Divide),

      // Relational Operators
      Map.entry("==", Equal),
      Map.entry("!=", NotEqual),
      Map.entry("<", Less),
      Map.entry("<=", LessEqual),

      // Types
      Map.entry("int", IntType),
      Map.entry("boolean", BooleanType));

  private static Map<String, TokenKind> newTokenMappings = Map.ofEntries(
      // New operators
      Map.entry(">", Greater),
      Map.entry(">=", GreaterEqual),
      Map.entry("or", BoolOr),
      Map.entry("xor", BoolXor),
      Map.entry("and", BoolAnd),

      // New types
      Map.entry("binary", BinaryType),
      Map.entry("char", CharType),

      // New statement tokens
      Map.entry("iter", Iterate),
      Map.entry("|-", Pipette),
      Map.entry("~", Tilde));

  private static Map<String, Token> pseudoTokens = new HashMap<>();
  static {
    pseudoTokenMappings.entrySet().forEach(entry -> {
      pseudoTokens.put(entry.getKey(),
          new Token(SymbolTable.recordSymbol(entry.getKey(), entry.getValue()), 0, 0));
    });

    // Special identifier token
    pseudoTokens.put("<id>", new Token(SymbolTable.recordSymbol("x", Identifier), 0, 0));

    // Special literal tokens
    pseudoTokens.put("<int>", new Token(SymbolTable.recordSymbol("42", IntLit), 0, 0));
    pseudoTokens.put("<boolean>", new Token(SymbolTable.recordSymbol("42", IntLit), 0, 0));

    // This semester's assignment tokens
    newTokenMappings.entrySet().forEach(entry -> {
      pseudoTokens.put(entry.getKey(),
          new Token(SymbolTable.recordSymbol(entry.getKey(), entry.getValue()), 0, 0));
    });
    pseudoTokens.put("<binary>", new Token(SymbolTable.recordSymbol("011b", BinaryLit), 0, 0));
    pseudoTokens.put("<char>", new Token(SymbolTable.recordSymbol("'a'", CharLit), 0, 0));
  }

  public static Token getTestToken(String token) {
    return pseudoTokens.get(token);
  }
}
