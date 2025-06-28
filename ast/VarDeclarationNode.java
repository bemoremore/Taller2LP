package ast;

import visitor.Visitor;

public class VarDeclarationNode extends DeclarationNode {
    private ExpressionNode arraySize; // null si no es array

    public VarDeclarationNode(TypeSpecifierNode type, String identifier, ExpressionNode arraySize) {
        super(type, identifier);
        this.arraySize = arraySize;
    }

    public VarDeclarationNode(TypeSpecifierNode type, String identifier) {
        this(type, identifier, null);
    }

    public ExpressionNode getArraySize() { return arraySize; }
    public boolean isArray() { return arraySize != null; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}