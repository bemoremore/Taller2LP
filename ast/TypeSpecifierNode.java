package ast;

import visitor.Visitor;

public class TypeSpecifierNode extends ASTNode {
    public enum Type {
        INT, VOID
    }

    private Type type;

    public TypeSpecifierNode(Type type) {
        super();
        this.type = type;
    }

    public Type getType() { return type; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
