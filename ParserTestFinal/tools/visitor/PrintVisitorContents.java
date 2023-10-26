package tools.visitor;

import static tools.ToolHelpers.*;

import config.VisitorConfiguration;

public class PrintVisitorContents {
  public static String HEADER = String.join("",
      String.format("package %s;", VisitorConfiguration.VISITOR_PACKAGE),
      getNewLines(2),
      "import ast.*;",
      getNewLines(1),
      "import ast.trees.*;",
      getNewLines(2),
      String.format(
          "public class %s extends %s {",
          VisitorConfiguration.PRINT_VISITOR_CLASS,
          VisitorConfiguration.VISITOR_BASE_CLASS),
      endAndIndent(2, 1),
      "private int indentation = 2;",
      endAndIndent(1, 1),
      "private final int INDENT_BY = 2;",
      endAndIndent(2, 1),
      "public void print (String nodeDescription, AST node) {",
      endAndIndent(1, 2),
      "String nodeNumber = String.format(\"%d:\", node.getNodeNumber());",
      endAndIndent(1, 2),
      "System.out.println(String.format(\"%-3s %-35.35s\",",
      endAndIndent(1, 4),
      "nodeNumber,",
      endAndIndent(1, 4),
      "String.format(\"%\" + indentation + \"s%s\", \"\", nodeDescription)));",
      endAndIndent(2, 2),
      "indentation += INDENT_BY;",
      endAndIndent(1, 2),
      "visitChildren(node);",
      endAndIndent(1, 2),
      "indentation -= INDENT_BY;",
      endAndIndent(1, 1),
      "}",
      getNewLines(2));

  public static String fileContents(String className, boolean isSymbolTree) {
    return String.join(
        "",
        getIndentation(1),
        "@Override",
        getNewLines(1),
        visitFunction(className, isSymbolTree));
  }

  private static String visitFunction(String className, boolean isSymbolTree) {
    StringBuffer buffer = new StringBuffer();

    buffer.append(String.join(
        "",
        getIndentation(1),
        String.format("public Object visit(%sTree node) {", className),
        endAndIndent(1, 2)));

    if (isSymbolTree) {
      buffer.append(String.format("print(String.format(\"%s: ", className));
      buffer.append("%s\", ((ISymbolTree) node).getSymbol().getLexeme()), node);");
    } else {
      buffer.append(String.format("print(\"%s\", node);", className));
    }

    buffer.append(String.join("",
        endAndIndent(1, 2),
        "return null;",
        endAndIndent(1, 1),
        "}",
        getNewLines(2)));

    return buffer.toString();
  }
}
