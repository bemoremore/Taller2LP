package ast;

import visitor.Visitor;

public class VarNode extends ExpressionNode {
    private String identifier;
    private ExpressionNode indexExpression; // null si no es array

    public VarNode(String identifier, ExpressionNode indexExpression) {
        super();
        this.identifier = identifier;
        this.indexExpression = indexExpression;
    }

    public VarNode(String identifier) {
        this(identifier, null);
    }

    public String getIdentifier() { return identifier; }
    public ExpressionNode getIndexExpression() { return indexExpression; }
    public boolean isArray() { return indexExpression != null; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
