package tests.parser.assignment2a;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.AST;
import compiler.Compiler;

public class ParserOutputTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    AST.NodeCounter = 0;
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  private static final List<String> EXPECTED_OUTPUT_ITERATOR_X = List.of(
      "  1: program { int i",
      "  2:   iter |- 1 ~ 5 {",
      "  3:     if(i == 0) then {",
      "  4:       i = write(i)",
      "  5:     }",
      "  6:   }",
      "  7: }",
      "",
      "0:    Program                          ",
      "1:      Block                          ",
      "4:        Declaration                  ",
      "2:          IntType                    ",
      "3:          Identifier: i              ",
      "5:        Iteration                    ",
      "6:          Range                      ",
      "7:            Int: 1                   ",
      "8:            Int: 5                   ",
      "9:          Block                      ",
      "10:           If                       ",
      "12:             RelOp: ==              ",
      "11:               Identifier: i        ",
      "13:               Int: 0               ",
      "14:             Block                  ",
      "15:               Assignment           ",
      "16:                 Identifier: i      ",
      "18:                 Call               ",
      "17:                   Identifier: write",
      "19:                   ActualArguments  ",
      "20:                     Identifier: i  ");

  @Test
  public void testParserOutput() throws IOException {
    File temp = File.createTempFile("atestfileforme", null);
    String path = temp.getAbsolutePath().toString();

    FileWriter writer = new FileWriter(temp);

    writer.write(
        String.join(
            System.lineSeparator(),
            List.of(
                "program { int i",
                "  iter |- 1 ~ 5 {",
                "    if(i == 0) then {",
                "      i = write(i)",
                "    }",
                "  }",
                "}")));
    writer.close();

    Compiler.main(new String[] { path });

    List<String> output = Arrays.asList(outputStreamCaptor.toString().split(System.lineSeparator()));

    for (int lineIndex = 0; lineIndex < EXPECTED_OUTPUT_ITERATOR_X.size(); lineIndex++) {
      assertEquals(EXPECTED_OUTPUT_ITERATOR_X.get(lineIndex), output.get(lineIndex));
    }
  }

  private static final List<String> EXPECTED_OUTPUT_SIMPLE_X = List.of(
      "  1: program { int i int j",
      "  2:    i = i + j + 7",
      "  3:    j = write(i)",
      "  4: }",
      "",
      "0:    Program                          ",
      "1:      Block                          ",
      "4:        Declaration                  ",
      "2:          IntType                    ",
      "3:          Identifier: i              ",
      "7:        Declaration                  ",
      "5:          IntType                    ",
      "6:          Identifier: j              ",
      "8:        Assignment                   ",
      "9:          Identifier: i              ",
      "13:         AddOp: +                   ",
      "11:           AddOp: +                 ",
      "10:             Identifier: i          ",
      "12:             Identifier: j          ",
      "14:           Int: 7                   ",
      "15:       Assignment                   ",
      "16:         Identifier: j              ",
      "18:         Call                       ",
      "17:           Identifier: write        ",
      "19:           ActualArguments          ",
      "20:             Identifier: i          ");

  @Test
  public void testParserOutputSimpleX() throws IOException {
    File temp = File.createTempFile("atestfileforme", null);
    String path = temp.getAbsolutePath().toString();

    FileWriter writer = new FileWriter(temp);

    writer.write(
        String.join(
            System.lineSeparator(),
            List.of(
                "program { int i int j",
                "   i = i + j + 7",
                "   j = write(i)",
                "}")));
    writer.close();

    Compiler.main(new String[] { path });

    List<String> output = Arrays.asList(outputStreamCaptor.toString().split(System.lineSeparator()));

    for (int lineIndex = 0; lineIndex < EXPECTED_OUTPUT_SIMPLE_X.size(); lineIndex++) {
      assertEquals(EXPECTED_OUTPUT_SIMPLE_X.get(lineIndex), output.get(lineIndex));
    }
  }
}
