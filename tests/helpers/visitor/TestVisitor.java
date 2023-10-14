package tests.helpers.visitor;

import ast.*;
import ast.trees.*;
import java.util.List;
import visitor.TreeVisitor;

public class TestVisitor extends TreeVisitor {
  private List<AST> expected;
  private int index;

  public TestVisitor(final List<AST> expected) {
    this.expected = expected;
    this.index = 0;
  }

  public Object test(AST t) {
    try {
      expected.get(index).getClass().cast(t);

      index++;
      return testChildren(t);
    } catch (ClassCastException exception) {
      return String.format(
          "Expected [%s] but got [%s]",
          expected.get(index).getClass().getSimpleName(),
          t.getClass().getSimpleName());
    }
  }

  private Object test(AST t, String expectedSymbol, String actualSymbol) {
    try {
      expected.get(index).getClass().cast(t);

      if (!expectedSymbol.equals(actualSymbol)) {
        throw new Exception(
            String.format(
                "Expected [%s] but got [%s]",
                expectedSymbol,
                actualSymbol));
      }

      index++;
      return testChildren(t);
    } catch (ClassCastException exception) {
      return String.format(
          "Expected [%s] but got [%s]",
          expected.get(index).getClass().getSimpleName(),
          t.getClass().getSimpleName());
    } catch (Exception exception) {
      return exception.getMessage();
    }
  }

  private Object testChildren(AST t) {
    for (AST child : t.getChildren()) {
      Object result = child.accept(this);

      if (result != null) {
        return result;
      }
    }

    return null;
  }

  @Override
  public Object visit(ProgramTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(BlockTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(DeclarationTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(FunctionDeclarationTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(FormalsTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(IntTypeTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(BoolTypeTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(IfTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(WhileTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(ReturnTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(AssignmentTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(CallTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(ActualArgumentsTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(RelOpTree tree) {
    String actualSymbol = ((ISymbolTree) tree).getSymbol().toString();
    String expectedSymbol = ((ISymbolTree) expected.get(index)).getSymbol().toString();

    return test(tree, expectedSymbol, actualSymbol);
  }

  @Override
  public Object visit(AddOpTree tree) {
    String actualSymbol = ((ISymbolTree) tree).getSymbol().toString();
    String expectedSymbol = ((ISymbolTree) expected.get(index)).getSymbol().toString();

    return test(tree, expectedSymbol, actualSymbol);
  }

  @Override
  public Object visit(MultOpTree tree) {
    String actualSymbol = ((ISymbolTree) tree).getSymbol().toString();
    String expectedSymbol = ((ISymbolTree) expected.get(index)).getSymbol().toString();

    return test(tree, expectedSymbol, actualSymbol);
  }

  @Override
  public Object visit(IntTree tree) {
    String actualSymbol = ((ISymbolTree) tree).getSymbol().toString();
    String expectedSymbol = ((ISymbolTree) expected.get(index)).getSymbol().toString();

    return test(tree, expectedSymbol, actualSymbol);
  }

  @Override
  public Object visit(IdentifierTree tree) {
    String actualSymbol = ((ISymbolTree) tree).getSymbol().toString();
    String expectedSymbol = ((ISymbolTree) expected.get(index)).getSymbol().toString();

    return test(tree, expectedSymbol, actualSymbol);
  }

  @Override
  public Object visit(BinaryTypeTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(BinaryLitTree tree) {
    String actualSymbol = ((ISymbolTree) tree).getSymbol().toString();
    String expectedSymbol = ((ISymbolTree) expected.get(index)).getSymbol().toString();

    return test(tree, expectedSymbol, actualSymbol);
  }

  @Override
  public Object visit(CharTypeTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(CharLitTree tree) {
    String actualSymbol = ((ISymbolTree) tree).getSymbol().toString();
    String expectedSymbol = ((ISymbolTree) expected.get(index)).getSymbol().toString();

    return test(tree, expectedSymbol, actualSymbol);
  }

  @Override
  public Object visit(IterationTree tree) {
    return test(tree);
  }

  @Override
  public Object visit(RangeTree tree) {
    return test(tree);
  }

}