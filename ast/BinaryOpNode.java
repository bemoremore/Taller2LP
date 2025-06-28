package ast;

import visitor.Visitor;

public class BinaryOpNode extends ExpressionNode {
    public enum Operator {
        PLUS, MINUS, MULTIPLY, DIVIDE,
        LT, LE, GT, GE, EQ, NE,
        ASSIGN
    }

    private ExpressionNode left;
    private Operator operator;
    private ExpressionNode right;

    public BinaryOpNode(ExpressionNode left, Operator operator, ExpressionNode right) {
        super();
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public ExpressionNode getLeft() { return left; }
    public Operator getOperator() { return operator; }
    public ExpressionNode getRight() { return right; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
