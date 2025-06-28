package ast;

import visitor.Visitor;

public class WhileNode extends StatementNode {
    private ExpressionNode condition;
    private StatementNode body;

    public WhileNode(ExpressionNode condition, StatementNode body) {
        super();
        this.condition = condition;
        this.body = body;
    }

    public ExpressionNode getCondition() { return condition; }
    public StatementNode getBody() { return body; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}