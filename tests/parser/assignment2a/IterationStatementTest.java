package tests.parser.assignment2a;

import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import ast.AST;
import ast.trees.AssignmentTree;
import ast.trees.BlockTree;
import ast.trees.IdentifierTree;
import ast.trees.IterationTree;
import ast.trees.ProgramTree;
import ast.trees.RangeTree;
import parser.Parser;
import tests.helpers.ast.PseudoProgram;
import tests.helpers.ast.PseudoProgramAsts;
import tests.helpers.ast.PseudoProgramTokens;
import tests.helpers.visitor.TestVisitor;
import visitor.TreeVisitor;

public class IterationStatementTest {

  private static final String ITERATION_PROGRAM = String.join(
      System.lineSeparator(),
      List.of(
          "program {",
          "  iter |- <int> ~ <int> {",
          "    <id> = <int>",
          "  }",
          "}"));
  private static final List<AST> EXPECTED_ASTS = List.of(
      new ProgramTree(),
      new BlockTree(),
      new IterationTree(),
      new RangeTree(),
      PseudoProgramAsts.get("<int>"),
      PseudoProgramAsts.get("<int>"),
      new BlockTree(),
      new AssignmentTree(),
      new IdentifierTree(PseudoProgramTokens.getTestToken("<id>")),
      PseudoProgramAsts.get("<int>"));

  @Test
  public void testIterationStatement() throws Exception {
    final Parser parser = new Parser(PseudoProgram.lexerFromPseudoProgram(ITERATION_PROGRAM));
    AST ast = parser.execute();

    TreeVisitor visitor = new TestVisitor(EXPECTED_ASTS);
    Object result = ast.accept(visitor);

    assertNull(result);
  }
}
