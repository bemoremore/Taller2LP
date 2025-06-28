package ast;

import visitor.Visitor;

public class IfNode extends StatementNode {
    private ExpressionNode condition;
    private StatementNode thenStatement;
    private StatementNode elseStatement; // null si no hay else

    public IfNode(ExpressionNode condition, StatementNode thenStatement, StatementNode elseStatement) {
        super();
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public IfNode(ExpressionNode condition, StatementNode thenStatement) {
        this(condition, thenStatement, null);
    }

    public ExpressionNode getCondition() { return condition; }
    public StatementNode getThenStatement() { return thenStatement; }
    public StatementNode getElseStatement() { return elseStatement; }
    public boolean hasElse() { return elseStatement != null; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}