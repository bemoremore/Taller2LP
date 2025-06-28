package ast;

import visitor.Visitor;

public class ExpressionStmtNode extends StatementNode {
    private ExpressionNode expression; // null para statement vacío

    public ExpressionStmtNode(ExpressionNode expression) {
        super();
        this.expression = expression;
    }

    public ExpressionNode getExpression() { return expression; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
