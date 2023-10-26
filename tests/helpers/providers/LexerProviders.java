package tests.helpers.providers;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import lexer.daos.Symbol;
import lexer.daos.Token;
import lexer.daos.TokenKind;

public class LexerProviders {

  public static Stream<Arguments> provideBuiltInKeywords() {
    return Stream.of(
        Arguments.of(" program  ", TokenKind.Program, 1, 7),
        Arguments.of(" int  ", TokenKind.IntType, 1, 3),
        Arguments.of(" boolean  ", TokenKind.BooleanType, 1, 7),
        Arguments.of(" if  ", TokenKind.If, 1, 2),
        Arguments.of(" then  ", TokenKind.Then, 1, 4),
        Arguments.of(" else  ", TokenKind.Else, 1, 4),
        Arguments.of(" while  ", TokenKind.While, 1, 5),
        Arguments.of(" function  ", TokenKind.Function, 1, 8),
        Arguments.of(" return  ", TokenKind.Return, 1, 6));
  }

  public static Stream<Arguments> provideBuiltInOperators() {
    return Stream.of(
        Arguments.of(" + ", TokenKind.Plus, 1, 1),
        Arguments.of(" - ", TokenKind.Minus, 1, 1),
        Arguments.of(" / ", TokenKind.Divide, 1, 1),
        Arguments.of(" * ", TokenKind.Multiply, 1, 1),
        Arguments.of(" & ", TokenKind.And, 1, 1),
        Arguments.of(" | ", TokenKind.Or, 1, 1),
        Arguments.of(" = ", TokenKind.Assign, 1, 1),
        Arguments.of(" == ", TokenKind.Equal, 1, 2),
        Arguments.of(" != ", TokenKind.NotEqual, 1, 2),
        Arguments.of(" < ", TokenKind.Less, 1, 1),
        Arguments.of(" <= ", TokenKind.LessEqual, 1, 2));
  }

  public static Stream<Arguments> provideBuiltInSeparators() {
    return Stream.of(
        Arguments.of(" , ", TokenKind.Comma, 1, 1),
        Arguments.of(" ( ", TokenKind.LeftParen, 1, 1),
        Arguments.of(" ) ", TokenKind.RightParen, 1, 1),
        Arguments.of(" { ", TokenKind.LeftBrace, 1, 1),
        Arguments.of(" } ", TokenKind.RightBrace, 1, 1));
  }

  public static Stream<Arguments> provideIdentifierExamples() {
    return Stream.of(
        Arguments.of(" _underscore ", TokenKind.Identifier, 1, 11),
        Arguments.of(" i ", TokenKind.Identifier, 1, 1),
        Arguments.of(" ab ", TokenKind.Identifier, 1, 2),
        Arguments.of(" abalamahalamatandra ", TokenKind.Identifier, 1, 19));
  }

  public static Stream<Arguments> provideIntegerExamples() {
    return Stream.of(
        Arguments.of(" 012345 ", TokenKind.IntLit, 1, 6),
        Arguments.of(" 123 ", TokenKind.IntLit, 1, 3));
  }

  public static Stream<Arguments> provideAssignmentOneKeywords() {
    return Stream.of(
        Arguments.of(" binary  ", TokenKind.BinaryType, 1, 6),
        Arguments.of(" char  ", TokenKind.CharType, 1, 4),
        Arguments.of(" and  ", TokenKind.BoolAnd, 1, 3),
        Arguments.of(" or  ", TokenKind.BoolOr, 1, 2),
        Arguments.of(" xor  ", TokenKind.BoolXor, 1, 3),
        Arguments.of(" iter  ", TokenKind.Iterate, 1, 4));
  }

  public static Stream<Arguments> provideAssignmentOneOperators() {
    return Stream.of(
        Arguments.of(" > ", TokenKind.Greater, 1, 1),
        Arguments.of(" >= ", TokenKind.GreaterEqual, 1, 2));
  }

  public static Stream<Arguments> provideAssignmentOneSeparators() {
    return Stream.of(
        Arguments.of(" |- ", TokenKind.Pipette, 1, 2),
        Arguments.of(" ~ ", TokenKind.Tilde, 1, 1));
  }

  public static Stream<Arguments> provideAssignmentOneBinaryLiterals() {
    return Stream.of(
        Arguments.of(" 0b ", TokenKind.BinaryLit, 1, 2),
        Arguments.of(" 1B ", TokenKind.BinaryLit, 1, 2),
        Arguments.of(" 01b ", TokenKind.BinaryLit, 1, 3),
        Arguments.of(" 10B ", TokenKind.BinaryLit, 1, 3),
        Arguments.of(" 00001b ", TokenKind.BinaryLit, 1, 6),
        Arguments.of(" 10101B ", TokenKind.BinaryLit, 1, 6));
  }

  public static Stream<Arguments> provideAssignmentOneFailingBinaryLiterals() {
    return Stream.of(
        Arguments.of(
            " 00001 ",
            List.of(
                new Token(new Symbol("00001", TokenKind.IntLit), 1, 5))),
        Arguments.of(
            " 010103B ",
            List.of(
                new Token(new Symbol("010103", TokenKind.IntLit), 1, 6),
                new Token(new Symbol("B", TokenKind.Identifier), 7, 7))),
        Arguments.of(
            " 130100b ",
            List.of(
                new Token(new Symbol("130100", TokenKind.IntLit), 1, 6),
                new Token(new Symbol("b", TokenKind.Identifier), 7, 7))));
  }

  public static Stream<Arguments> provideAssignmentOneCharLiterals() {
    return Stream.of(
        Arguments.of(" 'a' ", TokenKind.CharLit, 1, 3),
        Arguments.of(" '7' ", TokenKind.CharLit, 1, 3),
        Arguments.of(" 'Z' ", TokenKind.CharLit, 1, 3));
  }
}
