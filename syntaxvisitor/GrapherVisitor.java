package syntaxvisitor;

import visitor.Visitor;
import ast.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GrapherVisitor implements Visitor {
    private StringBuilder dotCode;
    private int nodeCounter;
    private String currentNodeId;

    public GrapherVisitor() {
        this.dotCode = new StringBuilder();
        this.nodeCounter = 0;
        initializeDotGraph();
    }

    private void initializeDotGraph() {
        dotCode.append("digraph AST {\n");
        dotCode.append("    node [shape=box, style=filled, fillcolor=lightblue];\n");
        dotCode.append("    rankdir=TB;\n\n");
    }

    private String generateNodeId() {
        return "node" + (++nodeCounter);
    }

    private void addNode(String nodeId, String label) {
        dotCode.append("    ").append(nodeId).append(" [label=\"").append(label).append("\"];\n");
    }

    private void addEdge(String fromNode, String toNode) {
        dotCode.append("    ").append(fromNode).append(" -> ").append(toNode).append(";\n");
    }

    public void saveToFile(String filename) throws IOException {
        dotCode.append("}\n");
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(dotCode.toString());
        }
    }

    public String getDotCode() {
        return dotCode.toString() + "}\n";
    }

    @Override
    public void visit(ProgramNode node) {
        String nodeId = generateNodeId();
        currentNodeId = nodeId;
        addNode(nodeId, "Program");

        for (DeclarationNode declaration : node.getDeclarations()) {
            String childId = generateNodeId();
            addEdge(nodeId, childId);
            String savedCurrentId = currentNodeId;
            currentNodeId = childId;
            declaration.accept(this);
            currentNodeId = savedCurrentId;
        }
    }

    @Override
    public void visit(VarDeclarationNode node) {
        String label = "VarDeclaration\\n" + node.getIdentifier();
        if (node.isArray()) {
            label += "[]";
        }
        addNode(currentNodeId, label);

        // Agregar tipo
        String typeId = generateNodeId();
        addEdge(currentNodeId, typeId);
        String savedCurrentId = currentNodeId;
        currentNodeId = typeId;
        node.getType().accept(this);
        currentNodeId = savedCurrentId;

        // Agregar tamaño del array si existe
        if (node.isArray()) {
            String sizeId = generateNodeId();
            addEdge(currentNodeId, sizeId);
            currentNodeId = sizeId;
            node.getArraySize().accept(this);
            currentNodeId = savedCurrentId;
        }
    }

    @Override
    public void visit(FunDeclarationNode node) {
        String label = "FunDeclaration\\n" + node.getIdentifier();
        addNode(currentNodeId, label);

        // Agregar tipo de retorno
        String typeId = generateNodeId();
        addEdge(currentNodeId, typeId);
        String savedCurrentId = currentNodeId;
        currentNodeId = typeId;
        node.getType().accept(this);
        currentNodeId = savedCurrentId;

        // Agregar parámetros
        if (!node.getParams().isEmpty()) {
            String paramsId = generateNodeId();
            addNode(paramsId, "Parameters");
            addEdge(currentNodeId, paramsId);

            for (ParamNode param : node.getParams()) {
                String paramId = generateNodeId();
                addEdge(paramsId, paramId);
                currentNodeId = paramId;
                param.accept(this);
            }
            currentNodeId = savedCurrentId;
        }

        // Agregar cuerpo
        String bodyId = generateNodeId();
        addEdge(currentNodeId, bodyId);
        currentNodeId = bodyId;
        node.getBody().accept(this);
        currentNodeId = savedCurrentId;
    }

    @Override
    public void visit(TypeSpecifierNode node) {
        String type = node.getType() == TypeSpecifierNode.Type.INT ? "int" : "void";
        addNode(currentNodeId, "Type: " + type);
    }

    @Override
    public void visit(ParamNode node) {
        String label = "Param\\n" + node.getIdentifier();
        if (node.isArray()) {
            label += "[]";
        }
        addNode(currentNodeId, label);

        // Agregar tipo
        String typeId = generateNodeId();
        addEdge(currentNodeId, typeId);
        String savedCurrentId = currentNodeId;
        currentNodeId = typeId;
        node.getType().accept(this);
        currentNodeId = savedCurrentId;
    }

    @Override
    public void visit(CompoundStmtNode node) {
        addNode(currentNodeId, "Compound Statement");
        String savedCurrentId = currentNodeId;

        // Agregar declaraciones locales
        if (!node.getLocalDeclarations().isEmpty()) {
            String declId = generateNodeId();
            addNode(declId, "Local Declarations");
            addEdge(currentNodeId, declId);

            for (VarDeclarationNode decl : node.getLocalDeclarations()) {
                String declNodeId = generateNodeId();
                addEdge(declId, declNodeId);
                currentNodeId = declNodeId;
                decl.accept(this);
            }
        }

        // Agregar statements
        if (!node.getStatements().isEmpty()) {
            String stmtId = generateNodeId();
            addNode(stmtId, "Statements");
            addEdge(savedCurrentId, stmtId);

            for (StatementNode stmt : node.getStatements()) {
                String stmtNodeId = generateNodeId();
                addEdge(stmtId, stmtNodeId);
                currentNodeId = stmtNodeId;
                stmt.accept(this);
            }
        }

        currentNodeId = savedCurrentId;
    }

    @Override
    public void visit(ExpressionStmtNode node) {
        addNode(currentNodeId, "Expression Statement");

        if (node.getExpression() != null) {
            String exprId = generateNodeId();
            addEdge(currentNodeId, exprId);
            String savedCurrentId = currentNodeId;
            currentNodeId = exprId;
            node.getExpression().accept(this);
            currentNodeId = savedCurrentId;
        }
    }

    @Override
    public void visit(IfNode node) {
        addNode(currentNodeId, "If Statement");
        String savedCurrentId = currentNodeId;

        // Condición
        String condId = generateNodeId();
        addNode(condId, "Condition");
        addEdge(currentNodeId, condId);
        String condExprId = generateNodeId();
        addEdge(condId, condExprId);
        currentNodeId = condExprId;
        node.getCondition().accept(this);

        // Then statement
        String thenId = generateNodeId();
        addNode(thenId, "Then");
        addEdge(savedCurrentId, thenId);
        String thenStmtId = generateNodeId();
        addEdge(thenId, thenStmtId);
        currentNodeId = thenStmtId;
        node.getThenStatement().accept(this);

        // Else statement (si existe)
        if (node.hasElse()) {
            String elseId = generateNodeId();
            addNode(elseId, "Else");
            addEdge(savedCurrentId, elseId);
            String elseStmtId = generateNodeId();
            addEdge(elseId, elseStmtId);
            currentNodeId = elseStmtId;
            node.getElseStatement().accept(this);
        }

        currentNodeId = savedCurrentId;
    }

    @Override
    public void visit(WhileNode node) {
        addNode(currentNodeId, "While Statement");
        String savedCurrentId = currentNodeId;

        // Condición
        String condId = generateNodeId();
        addNode(condId, "Condition");
        addEdge(currentNodeId, condId);
        String condExprId = generateNodeId();
        addEdge(condId, condExprId);
        currentNodeId = condExprId;
        node.getCondition().accept(this);

        // Cuerpo
        String bodyId = generateNodeId();
        addNode(bodyId, "Body");
        addEdge(savedCurrentId, bodyId);
        String bodyStmtId = generateNodeId();
        addEdge(bodyId, bodyStmtId);
        currentNodeId = bodyStmtId;
        node.getBody().accept(this);

        currentNodeId = savedCurrentId;
    }

    @Override
    public void visit(DoWhileNode node) {
        addNode(currentNodeId, "Do-While Statement");
        String savedCurrentId = currentNodeId;

        // Cuerpo
        String bodyId = generateNodeId();
        addNode(bodyId, "Body");
        addEdge(currentNodeId, bodyId);
        String bodyStmtId = generateNodeId();
        addEdge(bodyId, bodyStmtId);
        currentNodeId = bodyStmtId;
        node.getBody().accept(this);

        // Condición
        String condId = generateNodeId();
        addNode(condId, "Condition");
        addEdge(savedCurrentId, condId);
        String condExprId = generateNodeId();
        addEdge(condId, condExprId);
        currentNodeId = condExprId;
        node.getCondition().accept(this);

        currentNodeId = savedCurrentId;
    }

    @Override
    public void visit(ReturnNode node) {
        addNode(currentNodeId, "Return Statement");

        if (node.getExpression() != null) {
            String exprId = generateNodeId();
            addEdge(currentNodeId, exprId);
            String savedCurrentId = currentNodeId;
            currentNodeId = exprId;
            node.getExpression().accept(this);
            currentNodeId = savedCurrentId;
        }
    }

    @Override
    public void visit(BinaryOpNode node) {
        String operator = getOperatorString(node.getOperator());
        addNode(currentNodeId, "BinaryOp\\n" + operator);
        String savedCurrentId = currentNodeId;

        // Hijo izquierdo
        String leftId = generateNodeId();
        addEdge(currentNodeId, leftId);
        currentNodeId = leftId;
        node.getLeft().accept(this);

        // Hijo derecho
        String rightId = generateNodeId();
        addEdge(savedCurrentId, rightId);
        currentNodeId = rightId;
        node.getRight().accept(this);

        currentNodeId = savedCurrentId;
    }

    private String getOperatorString(BinaryOpNode.Operator op) {
        switch (op) {
            case PLUS: return "+";
            case MINUS: return "-";
            case MULTIPLY: return "*";
            case DIVIDE: return "/";
            case LT: return "<";
            case LE: return "<=";
            case GT: return ">";
            case GE: return ">=";
            case EQ: return "==";
            case NE: return "<>";
            case ASSIGN: return "=";
            default: return "?";
        }
    }

    @Override
    public void visit(VarNode node) {
        String label = "Var\\n" + node.getIdentifier();
        addNode(currentNodeId, label);

        if (node.isArray()) {
            String indexId = generateNodeId();
            addNode(indexId, "Index");
            addEdge(currentNodeId, indexId);
            String indexExprId = generateNodeId();
            addEdge(indexId, indexExprId);
            String savedCurrentId = currentNodeId;
            currentNodeId = indexExprId;
            node.getIndexExpression().accept(this);
            currentNodeId = savedCurrentId;
        }
    }

    @Override
    public void visit(CallNode node) {
        String label = "Call\\n" + node.getFunctionName();
        addNode(currentNodeId, label);
        String savedCurrentId = currentNodeId;

        if (!node.getArguments().isEmpty()) {
            String argsId = generateNodeId();
            addNode(argsId, "Arguments");
            addEdge(currentNodeId, argsId);

            for (ExpressionNode arg : node.getArguments()) {
                String argId = generateNodeId();
                addEdge(argsId, argId);
                currentNodeId = argId;
                arg.accept(this);
            }
        }

        currentNodeId = savedCurrentId;
    }

    @Override
    public void visit(NumberNode node) {
        addNode(currentNodeId, "Number\\n" + node.getValue());
    }

    @Override
    public void visit(IdentifierNode node) {
        addNode(currentNodeId, "ID\\n" + node.getName());
    }
}
