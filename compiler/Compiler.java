package compiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import ast.trees.ProgramTree;
import lexer.ILexer;
import lexer.Lexception;
import lexer.Lexer;
import parser.Parser;
import parser.SyntaxErrorException;
import visitor.PrintVisitor;

public class Compiler {

  private String sourceFile;

  public Compiler(String sourceFile) {
    this.sourceFile = sourceFile;
  }

  public void compileProgram() {
    try {
      ILexer lexer = new Lexer(sourceFile);
      Parser parser = new Parser(lexer);

      ProgramTree ast = (ProgramTree) parser.execute();

      try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
        String line;
        int lineNumber = 1;
        while ((line = br.readLine()) != null) {
          System.out.printf("%3d: %s%n", lineNumber, line);
          lineNumber++;
        }
      } catch (IOException e) {
        System.err.println("Error reading the source file.");
        System.exit(1);
      }

      System.out.println();

      PrintVisitor printVisitor = new PrintVisitor();
      printVisitor.visit(ast);

    } catch (Lexception e) {
      e.printStackTrace();
    } catch (SyntaxErrorException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("usage: java compiler.Compiler <file>");
      System.exit(1);
    }

    Compiler compiler = new Compiler(args[0]);
    compiler.compileProgram();
  }
}
