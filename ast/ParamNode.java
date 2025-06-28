package ast;

import visitor.Visitor;

public class ParamNode extends ASTNode {
    private TypeSpecifierNode type;
    private String identifier;
    private boolean isArray;

    public ParamNode(TypeSpecifierNode type, String identifier, boolean isArray) {
        super();
        this.type = type;
        this.identifier = identifier;
        this.isArray = isArray;
    }

    public TypeSpecifierNode getType() { return type; }
    public String getIdentifier() { return identifier; }
    public boolean isArray() { return isArray; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
