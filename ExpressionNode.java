
public class ExpressionNode {
    public ExpressionNode left;
    public ExpressionNode right;
    public String value;

    public ExpressionNode(String value)  {
        this.value = value;
    }
    public ExpressionNode(String left, String right, String value) {
        this.left = new ExpressionNode(left);
        this.right = new ExpressionNode(right);
        this.value = value;

    }

    public ExpressionNode(ExpressionNode left, ExpressionNode right, String value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
    public ExpressionNode(ExpressionNode left, String right, String value) {
        this.left = left;
        this.right =  new ExpressionNode(right);
        this.value = value;
    }

}
