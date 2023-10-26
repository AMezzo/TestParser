package tests.parser.assignment2a;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ast.AST;
import ast.trees.ProgramTree;
import lexer.Lexception;
import parser.Parser;
import tests.helpers.ast.PseudoProgram;
import tests.helpers.visitor.TestVisitor;
import visitor.TreeVisitor;

public class RelationalOperatorTest {

  @ParameterizedTest
  @MethodSource("provideTokens")
  public void testOperator(String relationalOperator) throws Lexception, Exception {
    final Parser parser = new Parser(
        PseudoProgram.lexerForRelationalOperator(relationalOperator));
    AST ast = parser.execute();

    assertEquals(ProgramTree.class, ast.getClass());

    TreeVisitor visitor = new TestVisitor(
        PseudoProgram.expectedAstForRelationalOperator(relationalOperator));
    Object result = visitor.visit((ProgramTree) ast);

    assertNull(result);
  }

  private static Stream<Arguments> provideTokens() {
    return Stream.of(
        Arguments.of(">"),
        Arguments.of(">="));
  }
}
