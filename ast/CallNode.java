package ast;

import visitor.Visitor;
import java.util.List;

public class CallNode extends ExpressionNode {
    private String functionName;
    private List<ExpressionNode> arguments;

    public CallNode(String functionName, List<ExpressionNode> arguments) {
        super();
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() { return functionName; }
    public List<ExpressionNode> getArguments() { return arguments; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
