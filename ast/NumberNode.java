package ast;

import visitor.Visitor;

public class NumberNode extends ExpressionNode {
    private int value;

    public NumberNode(int value) {
        super();
        this.value = value;
    }

    public int getValue() { return value; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
