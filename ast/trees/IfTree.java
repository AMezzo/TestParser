package ast.trees;

import ast.AST;
import visitor.TreeVisitor;

/**
 * This file is automatically generated!
 * Do not manually update! (Use the ToolRunner to regenerate.)
 **/
public class IfTree extends AST {
  @Override
  public Object accept(TreeVisitor visitor) {
    return visitor.visit(this);
  }
}