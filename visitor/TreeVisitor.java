package visitor;

import ast.AST;
import ast.trees.*;

public abstract class TreeVisitor {

  public void visitChildren(AST node) {
    for (AST child: node.getChildren()) {
      child.accept(this);
    }
  }

  public abstract Object visit(ProgramTree node);

  public abstract Object visit(BlockTree node);

  public abstract Object visit(DeclarationTree node);

  public abstract Object visit(FunctionDeclarationTree node);

  public abstract Object visit(FormalsTree node);

  public abstract Object visit(IntTypeTree node);

  public abstract Object visit(BoolTypeTree node);

  public abstract Object visit(IfTree node);

  public abstract Object visit(WhileTree node);

  public abstract Object visit(ReturnTree node);

  public abstract Object visit(AssignmentTree node);

  public abstract Object visit(CallTree node);

  public abstract Object visit(ActualArgumentsTree node);

  public abstract Object visit(RelOpTree node);

  public abstract Object visit(AddOpTree node);

  public abstract Object visit(MultOpTree node);

  public abstract Object visit(IntTree node);

  public abstract Object visit(IdentifierTree node);

  public abstract Object visit(BinaryType node);

  public abstract Object visit(CharType node);

  public abstract Object visit(BinaryLit node);

  public abstract Object visit(CharLit node);

  public abstract Object visit(Iter node);

  public abstract Object visit(Range node);

}