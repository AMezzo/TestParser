package tests.parser.regression;

import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;
import org.junit.jupiter.api.Test;

import ast.AST;
import ast.trees.BlockTree;
import ast.trees.ProgramTree;
import lexer.Lexception;
import parser.Parser;
import tests.helpers.ast.PseudoProgram;
import tests.helpers.visitor.TestVisitor;
import visitor.TreeVisitor;

public class ProgramTest {
  @Test
  public void testProgram() throws Lexception, Exception {
    final Parser parser = new Parser(
        PseudoProgram.lexerFromPseudoProgram("program { }"));

    AST ast = parser.execute();
    TreeVisitor visitor = new TestVisitor(
        List.of(new ProgramTree(), new BlockTree()));

    Object result = ast.accept(visitor);

    assertNull(result);
  }
}
