package ast.trees;

import ast.AST;
import visitor.TreeVisitor;

public class CharTypeTree extends AST {

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "CharType";
    }
}