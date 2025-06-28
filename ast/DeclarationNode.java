package ast;

import visitor.Visitor;

public abstract class DeclarationNode extends ASTNode {
    protected String identifier;
    protected TypeSpecifierNode type;

    public DeclarationNode(TypeSpecifierNode type, String identifier) {
        super();
        this.type = type;
        this.identifier = identifier;
    }

    public String getIdentifier() { return identifier; }
    public TypeSpecifierNode getType() { return type; }
}
