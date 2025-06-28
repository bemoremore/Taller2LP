package ast;

import visitor.Visitor;

public class ReturnNode extends StatementNode {
    private ExpressionNode expression; // null para return vacío

    public ReturnNode(ExpressionNode expression) {
        super();
        this.expression = expression;
    }

    public ExpressionNode getExpression() { return expression; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
