package ast;

import visitor.Visitor;
import java.util.List;

public class CompoundStmtNode extends StatementNode {
    private List<VarDeclarationNode> localDeclarations;
    private List<StatementNode> statements;

    public CompoundStmtNode(List<VarDeclarationNode> localDeclarations,
                            List<StatementNode> statements) {
        super();
        this.localDeclarations = localDeclarations;
        this.statements = statements;
    }

    public List<VarDeclarationNode> getLocalDeclarations() { return localDeclarations; }
    public List<StatementNode> getStatements() { return statements; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
