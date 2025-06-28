package ast;

import visitor.Visitor;
import java.util.List;

public class FunDeclarationNode extends DeclarationNode {
    private List<ParamNode> params;
    private CompoundStmtNode body;

    public FunDeclarationNode(TypeSpecifierNode type, String identifier,
                              List<ParamNode> params, CompoundStmtNode body) {
        super(type, identifier);
        this.params = params;
        this.body = body;
    }

    public List<ParamNode> getParams() { return params; }
    public CompoundStmtNode getBody() { return body; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}