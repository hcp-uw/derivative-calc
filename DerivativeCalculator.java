import java.util.Stack;
import java.lang.Character;

public class DerivativeCalculator {
    private final char[] operators = {'+', '*', '-', '/', '^'};

    public DerivativeCalculator() {
    }
    //  worry about input later

    // input Formatting: ex. (x + 1)
    // this sets up the tree
    //
    public ExpressionNode makeTree(String input) {
        input = input.replaceAll(" ", "");
        Stack<ExpressionNode> stack = new Stack<ExpressionNode>();
        ExpressionNode temp;
        for(int i = 0; i < input.length(); i++) {
            if (!containsOperator(charAt(i)) && !Character.isDigit(input.charAt(i))) {
                return "Wrong equation";
            } else {
                if (!containsOperator(input.charAt(i))) {
                    temp = new ExpressionNode(input.charAt(i)+ "");
                    stack.push(temp);
                }
                else { // if is an operator
                    temp = new ExpressionNode(input.charAt(i) + "");
                    temp.right = stack.pop();
                    temp.left = stack.pop();
                    stack.push(temp);
                }
            }
        }
        temp = stack.pop();
        temp = derive(temp);
        return stack;
    }

    // this recurses through the tree
    // should look through the code

    private ExpressionNode derive(ExpressionNode root) {
        if (root != null) {
            if (containsOperator(root.value.charAt(0))) {
                    
            }
        }
    }


    private String powerRule(String input) {
        
        return "";

    }

    private String productRule(String input) {
        return "";
    }

    private String quotientRule(String input) {
        return "";
    }
    private boolean containsOperator(char str) {
        for(char operator: operators) {
            if(str == operator) {
                return true;
            }
        }
        return false;
    }
    // binary expression tree
    //
    private class ExpressionNode {
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
    }

}
