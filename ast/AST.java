package ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import visitor.TreeVisitor;

public abstract class AST {
  public static int NodeCounter = 0;

  protected List<AST> children;
  private int nodeNumber;

  public AST() {
    this.children = new ArrayList<>();
    this.nodeNumber = AST.NodeCounter++;
  }

  public AST addChild(AST child) {
    this.children.add(child);

    return this;
  }

  public AST getChild(int index) {
    if (index < 0 || index >= this.children.size()) {
      return null;
    }

    return this.children.get(index);
  }

  public int getChildCount() {
    return this.children.size();
  }

  public List<AST> getChildren() {
    return this.children;
  }

  public int getNodeNumber() {
    return this.nodeNumber;
  }

  public String displayString() {
    return displayString(0);
  }

  public abstract Object accept(TreeVisitor visitor);

  private String displayString(int level) {
    StringBuilder buffer = new StringBuilder();

    for (int index = 0; index < level * 2; index++) {
      buffer.append(" ");
    }

    buffer.append(
        String.format("%2d: %s\n", this.getNodeNumber(), this.toString()));

    Iterator<AST> iterator = this.children.iterator();
    while (iterator.hasNext()) {
      buffer.append(iterator.next().displayString(level + 1));
    }

    return buffer.toString();
  }
}
