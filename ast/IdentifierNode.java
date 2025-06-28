package ast;

import visitor.Visitor;

public class IdentifierNode extends ExpressionNode {
    private String name;

    public IdentifierNode(String name) {
        super();
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
