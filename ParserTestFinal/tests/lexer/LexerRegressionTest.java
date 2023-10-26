package tests.lexer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lexer.Lexception;
import lexer.Lexer;
import lexer.daos.Token;
import lexer.daos.TokenKind;
import lexer.readers.IReader;
import tests.helpers.lexer.TestReader;

public class LexerRegressionTest {

  @Test
  public void testEndOfFile() throws Exception {
    IReader testReader = new TestReader(List.of("   \n", " "));

    try (Lexer lexer = new Lexer(testReader);) {
      Token token = lexer.nextToken();

      assertEquals(TokenKind.EOF, token.getTokenKind());
      assertEquals(1, token.getLeftPosition());
      assertEquals(1, token.getRightPosition());
    }
  }

  @Test
  public void testWhitespaceIsIgnored() throws Exception {
    IReader testReader = new TestReader(List.of("\r\n", "\t   "));

    try (Lexer lexer = new Lexer(testReader);) {
      Token token = lexer.nextToken();

      assertEquals(TokenKind.EOF, token.getTokenKind());
      assertEquals(4, token.getLeftPosition());
      assertEquals(4, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideBuiltInKeywords")
  public void testBuiltInKeywords(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideBuiltInOperators")
  public void testBuiltInOperators(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideBuiltInSeparators")
  public void testBuiltInSeparators(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @Test
  public void testComments() throws Exception {
    // Comments are a special case since the token gets "ignored" by the lexer
    try (Lexer lexer = new Lexer(new TestReader(List.of(" // ")))) {
      Token token = lexer.nextToken();
      // We expect EOF in this case because we only have a comment, which should
      // be ignored by the lexer. Lexer would normally reset start position
      // when next token encountered, but here we use string's length
      // (column 4 is where we encounter EOF)

      assertEquals("\0", token.getLexeme());
      assertEquals(TokenKind.EOF, token.getTokenKind());
      assertEquals(4, token.getLeftPosition());
      assertEquals(4, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideIdentifierExamples")
  public void testIdentifiers(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideIntegerExamples")
  public void testIntegers(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideAssignmentOneKeywords")
  public void testAssignmentOneKeywords(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideAssignmentOneOperators")
  public void testAssignmentOneOperators(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideAssignmentOneSeparators")
  public void testAssignmentOneSeparators(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideAssignmentOneBinaryLiterals")
  public void testBinaryLiterals(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideAssignmentOneFailingBinaryLiterals")
  public void testFailingBinaryLiterals(String source, List<Token> expectedTokens)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Iterator<Token> iterator = expectedTokens.iterator();

      while (iterator.hasNext()) {
        Token expectedToken = iterator.next();
        Token token = lexer.nextToken();

        assertEquals(expectedToken.getLexeme(), token.getLexeme());
        assertEquals(expectedToken.getTokenKind(), token.getTokenKind());
        assertEquals(expectedToken.getLeftPosition(), token.getLeftPosition());
        assertEquals(expectedToken.getRightPosition(), token.getRightPosition());
      }
    }
  }

  @ParameterizedTest
  @MethodSource("tests.helpers.providers.LexerProviders#provideAssignmentOneCharLiterals")
  public void testCharLiterals(String source, TokenKind kind, int expectedStart, int expectedEnd)
      throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of(source)))) {
      Token token = lexer.nextToken();

      assertEquals(source.trim(), token.getLexeme());
      assertEquals(kind, token.getTokenKind());
      assertEquals(expectedStart, token.getLeftPosition());
      assertEquals(expectedEnd, token.getRightPosition());
    }
  }

  @Test
  public void testFailingCharLiteralOne() throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of("'a")))) {
      Exception exception = assertThrows(Lexception.class, () -> {
        lexer.nextToken();
      });

      String expectedMessage = "Invalid character encountered: \0 (code: 0) on line 1, column 2";

      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  @Test
  public void testFailingCharLiteralTwo() throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of("a' (")))) {
      Token token = lexer.nextToken();

      assertEquals("a", token.getLexeme());
      assertEquals(TokenKind.Identifier, token.getTokenKind());
      assertEquals(0, token.getLeftPosition());
      assertEquals(0, token.getRightPosition());

      Exception exception = assertThrows(Lexception.class, () -> {
        lexer.nextToken();
      });

      String expectedMessage = "Invalid character encountered:   (code: 32) on line 1, column 2";

      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  @Test
  public void testFailingCharLiteralThree() throws Exception {
    try (Lexer lexer = new Lexer(new TestReader(List.of("'aa'")))) {
      Exception exception = assertThrows(Lexception.class, () -> {
        lexer.nextToken();
      });

      String expectedMessage = "Invalid character encountered: a (code: 97) on line 1, column 2";

      assertEquals(expectedMessage, exception.getMessage());
    }
  }
}
