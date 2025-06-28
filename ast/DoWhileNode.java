package ast;

import visitor.Visitor;

public class DoWhileNode extends StatementNode {
    private StatementNode body;
    private ExpressionNode condition;

    public DoWhileNode(StatementNode body, ExpressionNode condition) {
        super();
        this.body = body;
        this.condition = condition;
    }

    public StatementNode getBody() { return body; }
    public ExpressionNode getCondition() { return condition; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
