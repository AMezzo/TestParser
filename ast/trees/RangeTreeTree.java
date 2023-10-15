package ast.trees;

import ast.AST;
import visitor.TreeVisitor;

import ast.ISymbolTree;
import lexer.SymbolTable;
import lexer.daos.*;

/**
 * This file is automatically generated!
 * Do not manually update! (Use the ToolRunner to regenerate.)
 **/
public class RangeTreeTree extends AST implements ISymbolTree {
  private Symbol symbol;

  @Override
  public Object accept(TreeVisitor visitor) {
    return visitor.visit(this);
  }

  public RangeTreeTree(Token token) {
    this.symbol = SymbolTable.recordSymbol(token.getLexeme(), TokenKind.BogusToken);
  }

  public Symbol getSymbol() {
    return this.symbol;
  }
}