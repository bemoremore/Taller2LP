package visitor;

import ast.*;

public interface Visitor {
    void visit(ProgramNode node);
    void visit(VarDeclarationNode node);
    void visit(FunDeclarationNode node);
    void visit(TypeSpecifierNode node);
    void visit(ParamNode node);
    void visit(CompoundStmtNode node);
    void visit(ExpressionStmtNode node);
    void visit(IfNode node);
    void visit(WhileNode node);
    void visit(DoWhileNode node);
    void visit(ReturnNode node);
    void visit(BinaryOpNode node);
    void visit(VarNode node);
    void visit(CallNode node);
    void visit(NumberNode node);
    void visit(IdentifierNode node);
}