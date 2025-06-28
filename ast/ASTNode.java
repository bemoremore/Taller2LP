package ast;

import visitor.Visitor;

public abstract class ASTNode {
    protected int lineNumber;
    protected int columnNumber;

    public ASTNode(int line, int column) {
        this.lineNumber = line;
        this.columnNumber = column;
    }

    public ASTNode() {
        this.lineNumber = 0;
        this.columnNumber = 0;
    }

    public abstract void accept(Visitor visitor);

    public int getLineNumber() { return lineNumber; }
    public int getColumnNumber() { return columnNumber; }
}
