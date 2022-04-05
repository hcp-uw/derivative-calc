import java.util.HashMap;
import java.util.Map;
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
    public ExpressionNode makeTree(String s) {
        s = s.replaceAll(" ", "");
        Stack<ExpressionNode> stN = new Stack<ExpressionNode>();
        Stack<String> stC = new Stack<>();

        ExpressionNode t, t1, t2;

        int[] p = new int[123];
        p['+'] = p['-'] = 1;
        p['/'] = p['*'] = 2;
        p['^'] = 3;
        p[')'] = 0;
        Map<String, Integer> opPriority = new HashMap<>();

        opPriority.put("+", 1);
        opPriority.put("-", 1);
        opPriority.put("/", 2);
        opPriority.put("*", 2);
        opPriority.put("^", 3);
        String checkNum = "";
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '(') {

                // Push '(' in char stack
                stC.add("" + s.charAt(i));
            }

            // Push the operands in node stack
            else if (Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i)))
            {
                if (!Character.isDigit(s.charAt(i+1))) {
                    checkNum += s.charAt(i) ;
                    t = new ExpressionNode(checkNum);
                    stN.add(t);
                    checkNum = "";
                }
                else {
                    checkNum +=  s.charAt(i);
                }

            }
            else if (p[s.charAt(i)] > 0)
            {

                // If an operator with lower or
                // same associativity appears
                while (
                        opPriority.containsKey(stC.peek()) && !stC.isEmpty() && !stC.peek().equals("(")
                                && ((s.charAt(i) != '^' && opPriority.get(stC.peek()) >= p[s.charAt(i)])
                                || (s.charAt(i) == '^'
                                && opPriority.get(stC.peek()) > p[s.charAt(i)])))
                {

                    // Get and remove the top element
                    // from the character stack
                    t = new ExpressionNode(stC.peek());
                    stC.pop();

                    // Get and remove the top element
                    // from the node stack
                    t1 = stN.peek();
                    stN.pop();

                    // Get and remove the currently top
                    // element from the node stack
                    t2 = stN.peek();
                    stN.pop();

                    // Update the tree
                    t.left = t2;
                    t.right = t1;

                    // Push the node to the node stack
                    stN.add(t);
                }

                // Push s[i] to char stack
                stC.push(s.charAt(i) + "");
            }
            else if (s.charAt(i) == ')') {
                while (!stC.isEmpty() && !stC.peek().equals("("))
                {
                    t = new ExpressionNode(stC.peek());
                    stC.pop();
                    t1 = stN.peek();
                    stN.pop();
                    t2 = stN.peek();
                    stN.pop();
                    t.left = t2;
                    t.right = t1;
                    stN.add(t);
                }
                stC.pop();
            }
        }
        t = stN.peek();
        return t;
    }

    // this recurses through the tree
    // should look through the code
    /*
    private ExpressionNode derive(ExpressionNode root) {
        if (root != null) {
            if (containsOperator(root.value.charAt(0))) {
                    
            }
        }
    }

     */


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
