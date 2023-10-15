package visitor;

import ast.*;
import ast.trees.*;

public class PrintVisitor extends TreeVisitor {

  private int indentation = 2;
  private final int INDENT_BY = 2;

  public void print (String nodeDescription, AST node) {
    String nodeNumber = String.format("%d:", node.getNodeNumber());
    System.out.println(String.format("%-3s %-35.35s",
        nodeNumber,
        String.format("%" + indentation + "s%s", "", nodeDescription)));

    indentation += INDENT_BY;
    visitChildren(node);
    indentation -= INDENT_BY;
  }

  @Override
  public Object visit(ProgramTree node) {
    print("Program", node);
    return null;
  }

  @Override
  public Object visit(BlockTree node) {
    print("Block", node);
    return null;
  }

  @Override
  public Object visit(DeclarationTree node) {
    print("Declaration", node);
    return null;
  }

  @Override
  public Object visit(FunctionDeclarationTree node) {
    print("FunctionDeclaration", node);
    return null;
  }

  @Override
  public Object visit(FormalsTree node) {
    print("Formals", node);
    return null;
  }

  @Override
  public Object visit(IntTypeTree node) {
    print("IntType", node);
    return null;
  }

  @Override
  public Object visit(BoolTypeTree node) {
    print("BoolType", node);
    return null;
  }

  @Override
  public Object visit(IfTree node) {
    print("If", node);
    return null;
  }

  @Override
  public Object visit(WhileTree node) {
    print("While", node);
    return null;
  }

  @Override
  public Object visit(ReturnTree node) {
    print("Return", node);
    return null;
  }

  @Override
  public Object visit(AssignmentTree node) {
    print("Assignment", node);
    return null;
  }

  @Override
  public Object visit(CallTree node) {
    print("Call", node);
    return null;
  }

  @Override
  public Object visit(ActualArgumentsTree node) {
    print("ActualArguments", node);
    return null;
  }

  @Override
  public Object visit(RelOpTree node) {
    print(String.format("RelOp: %s", ((ISymbolTree) node).getSymbol().getLexeme()), node);
    return null;
  }

  @Override
  public Object visit(AddOpTree node) {
    print(String.format("AddOp: %s", ((ISymbolTree) node).getSymbol().getLexeme()), node);
    return null;
  }

  @Override
  public Object visit(MultOpTree node) {
    print(String.format("MultOp: %s", ((ISymbolTree) node).getSymbol().getLexeme()), node);
    return null;
  }

  @Override
  public Object visit(IntTree node) {
    print(String.format("Int: %s", ((ISymbolTree) node).getSymbol().getLexeme()), node);
    return null;
  }

  @Override
  public Object visit(IdentifierTree node) {
    print(String.format("Identifier: %s", ((ISymbolTree) node).getSymbol().getLexeme()), node);
    return null;
  }

}