package lexer;

import java.util.Map;
import java.util.HashMap;
import lexer.daos.TokenKind;
import lexer.daos.Symbol;

/**
 * This file is automatically generated!
 * Do not manually update! (Use the ToolRunner to regenerate.)
 **/
public class SymbolTable {
  private static Map<String, Symbol> symbols;

  static {
    symbols = new HashMap<>();

    symbols.put("program", new Symbol("program", TokenKind.Program));
    symbols.put("int", new Symbol("int", TokenKind.IntType));
    symbols.put("boolean", new Symbol("boolean", TokenKind.BooleanType));
    symbols.put("if", new Symbol("if", TokenKind.If));
    symbols.put("then", new Symbol("then", TokenKind.Then));
    symbols.put("else", new Symbol("else", TokenKind.Else));
    symbols.put("while", new Symbol("while", TokenKind.While));
    symbols.put("function", new Symbol("function", TokenKind.Function));
    symbols.put("return", new Symbol("return", TokenKind.Return));
    symbols.put("<id>", new Symbol("<id>", TokenKind.Identifier));
    symbols.put("<int>", new Symbol("<int>", TokenKind.IntLit));
    symbols.put("{", new Symbol("{", TokenKind.LeftBrace));
    symbols.put("}", new Symbol("}", TokenKind.RightBrace));
    symbols.put("(", new Symbol("(", TokenKind.LeftParen));
    symbols.put(")", new Symbol(")", TokenKind.RightParen));
    symbols.put(",", new Symbol(",", TokenKind.Comma));
    symbols.put("=", new Symbol("=", TokenKind.Assign));
    symbols.put("==", new Symbol("==", TokenKind.Equal));
    symbols.put("!=", new Symbol("!=", TokenKind.NotEqual));
    symbols.put("<", new Symbol("<", TokenKind.Less));
    symbols.put("<=", new Symbol("<=", TokenKind.LessEqual));
    symbols.put("+", new Symbol("+", TokenKind.Plus));
    symbols.put("-", new Symbol("-", TokenKind.Minus));
    symbols.put("|", new Symbol("|", TokenKind.Or));
    symbols.put("&", new Symbol("&", TokenKind.And));
    symbols.put("*", new Symbol("*", TokenKind.Multiply));
    symbols.put("/", new Symbol("/", TokenKind.Divide));
    symbols.put("//", new Symbol("//", TokenKind.Comment));
    symbols.put(">", new Symbol(">", TokenKind.Greater));
    symbols.put(">=", new Symbol(">=", TokenKind.GreaterEqual));
    symbols.put("binary", new Symbol("binary", TokenKind.BinaryType));
    symbols.put("<binary>", new Symbol("<binary>", TokenKind.BinaryLit));
    symbols.put("char", new Symbol("char", TokenKind.CharType));
    symbols.put("<char>", new Symbol("<char>", TokenKind.CharLit));
    symbols.put("and", new Symbol("and", TokenKind.BoolAnd));
    symbols.put("or", new Symbol("or", TokenKind.BoolOr));
    symbols.put("xor", new Symbol("xor", TokenKind.BoolXor));
    symbols.put("iter", new Symbol("iter", TokenKind.Iterate));
    symbols.put("|-", new Symbol("|-", TokenKind.Pipette));
    symbols.put("~", new Symbol("~", TokenKind.Tilde));
  }

  public static Symbol recordSymbol(String lexeme, TokenKind kind) {
    Symbol s = symbols.get(lexeme);

    if (s == null) {
      if (kind == TokenKind.BogusToken) {
        // bogus string so don't enter into symbols
        return null;
      }

      s = new Symbol(lexeme, kind);
      symbols.put(lexeme, s);
    }

    return s;
  }
}
