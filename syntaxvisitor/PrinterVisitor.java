package syntaxvisitor;

import ast.*;
import visitor.Visitor;

public class PrinterVisitor implements Visitor {

    private int indent = 0;

    private void printIndent() {
        System.out.print("  ".repeat(indent));
    }

    @Override
    public void visit(ProgramNode node) {
        printIndent();
        System.out.println("Program:");
        indent++;
        for (DeclarationNode decl : node.getDeclarations()) {
            decl.accept(this);
        }
        indent--;
    }

    @Override
    public void visit(FunDeclarationNode node) {
        printIndent();
        System.out.println("Function: " + node.getIdentifier());
        indent++;
        for (ParamNode param : node.getParams()) {
            param.accept(this);
        }
        node.getBody().accept(this);
        indent--;
    }

    @Override
    public void visit(TypeSpecifierNode node) {

    }

    @Override
    public void visit(VarDeclarationNode node) {
        printIndent();
        System.out.println("Variable: " + node.getIdentifier() +
                (node.isArray() ? " [array]" : ""));
    }

    @Override
    public void visit(ParamNode node) {
        printIndent();
        System.out.println("Param: " + node.getIdentifier() +
                (node.isArray() ? " [array]" : ""));
    }

    @Override
    public void visit(CompoundStmtNode node) {
        printIndent();
        System.out.println("Compound Statement:");
        indent++;
        for (VarDeclarationNode var : node.getLocalDeclarations()) {
            var.accept(this);
        }
        for (StatementNode stmt : node.getStatements()) {
            stmt.accept(this);
        }
        indent--;
    }

    @Override
    public void visit(ExpressionStmtNode node) {
        printIndent();
        System.out.println("Expression Statement:");
        indent++;
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
        } else {
            printIndent(); System.out.println("Empty Expression");
        }
        indent--;
    }

    @Override
    public void visit(IfNode node) {
        printIndent();
        System.out.println("If:");
        indent++;
        node.getCondition().accept(this);
        node.getThenStatement().accept(this);
        if (node.getElseStatement() != null) {
            printIndent(); System.out.println("Else:");
            node.getElseStatement().accept(this);
        }
        indent--;
    }

    @Override
    public void visit(WhileNode node) {
        printIndent();
        System.out.println("While:");
        indent++;
        node.getCondition().accept(this);
        node.getBody().accept(this);
        indent--;
    }

    @Override
    public void visit(DoWhileNode node) {
        printIndent();
        System.out.println("Do-While:");
        indent++;
        node.getBody().accept(this);
        node.getCondition().accept(this);
        indent--;
    }

    @Override
    public void visit(ReturnNode node) {
        printIndent();
        System.out.println("Return:");
        indent++;
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
        } else {
            printIndent(); System.out.println("void");
        }
        indent--;
    }

    @Override
    public void visit(BinaryOpNode node) {
        printIndent();
        System.out.println("Binary Operation: " + node.getOperator());
        indent++;
        node.getLeft().accept(this);
        node.getRight().accept(this);
        indent--;
    }

    @Override
    public void visit(CallNode node) {
        printIndent();
        System.out.println("Function Call: " + node.getFunctionName());
        indent++;
        for (ExpressionNode arg : node.getArguments()) {
            arg.accept(this);
        }
        indent--;
    }

    @Override
    public void visit(NumberNode node) {
        printIndent();
        System.out.println("Number: " + node.getValue());
    }

    @Override
    public void visit(IdentifierNode node) {
        printIndent();

    }

    @Override
    public void visit(VarNode node) {
        printIndent();
        if (node.getIndexExpression() != null) {
            System.out.println("Array Access: " + node.getIdentifier());
            indent++;
            node.getIndexExpression().accept(this);
            indent--;
        } else {
            System.out.println("Variable Reference: " + node.getIdentifier());
        }
    }
}
