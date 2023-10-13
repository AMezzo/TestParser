package ast.trees;

import ast.AST;
import visitor.TreeVisitor;

public class IterTree extends AST {

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "IterStatement";
    }
}
