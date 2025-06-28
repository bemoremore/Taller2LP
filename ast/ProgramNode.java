package ast;

import visitor.Visitor;
import java.util.List;

public class ProgramNode extends ASTNode {
    private List<DeclarationNode> declarations;

    public ProgramNode(List<DeclarationNode> declarations) {
        super();
        this.declarations = declarations;
    }

    public List<DeclarationNode> getDeclarations() {
        return declarations;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
