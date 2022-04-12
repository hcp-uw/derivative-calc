
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.lang.Character;

public class DerivativeCalculator {
    private final char[] operators = {'+', '*', '-', '/', '^'};

    private char var;
    public DerivativeCalculator(char var) {
        this.var = var;
    }
    //  worry about input later

    // this is a testing function, ignore
    public ExpressionNode TestDerivative(String s) {
        ExpressionNode root = makeTree(s);
        root = derive(root);
        return root;
    }
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


    private ExpressionNode powerRule(ExpressionNode root) {
        ExpressionNode firstNode = new ExpressionNode("*");
        ExpressionNode exponent = new ExpressionNode(root.right.value);

        root.right = new ExpressionNode("1", root.right.value, "1");
        ExpressionNode baseCopy = copyTree(root.left);
        firstNode.left = root;
        firstNode.right = new ExpressionNode(derive(baseCopy), exponent, "*");
        // right side, - 1, copy right side, make its parent a subtract, right child is 1
        // left side, multiply by old right side
        // also need to multiply by derivative of old left side
        return firstNode;
    }

    private ExpressionNode derive(ExpressionNode root) {
        if (root == null) {
            return null;
        }
        if (root.value.equals(var +"")) {
            root.value = "1";
        }
        else if (root.value.equals("+") || root.value.equals("-")){
            root.left = derive(root.left);
            root.right = derive(root.right);

        }

        else if (root.value.equals("/")){

        }
        else if (root.value.equals("*")){
            root = productRule(root);
        }
        else if (root.value.equals("^")){
            root = powerRule(root);
        }
        else {
            root.value = "0";
            
        }
        /* first print data of node */
        // System.out.print(root.value + " ");

        /* then recur on left subtree */
        //derive(root.left);

        /* now recur on right subtree */
        //derive(root.right);
        return root;
    }
    private ExpressionNode productRule(ExpressionNode root) {
        root.value = "+";
        ExpressionNode rootLeftHolder = copyTree(root.left);
        root.left = new ExpressionNode(root.left, derive(root.right), "*");
        root.right = new ExpressionNode(root.right, derive(rootLeftHolder), "*");
        return root;
    }

    private ExpressionNode quotientRule(ExpressionNode root) {

        // left side is like product rule but -

        // right side is original right side squared
        return root;
    }
    private boolean containsOperator(char str) {
        for(char operator: operators) {
            if(str == operator) {
                return true;
            }
        }
        return false;
    }

    private ExpressionNode copyTree(ExpressionNode root) {
        if (root != null) {
            ExpressionNode newNode = new ExpressionNode(root.value);
            newNode.left = copyTree(root.left);
            newNode.right = copyTree(root.right);
            return newNode;
        }
        return root;
    }

    private ExpressionNode simplify(ExpressionNode root) {
        String originalValue = root.value;
        if (root.value.equals("+")) {
            if (root.left.value.equals("0")) {
                root.value = root.right.value;
            }
            else if (root.right.value.equals("0")) {
                root.value = root.left.value;
            }
        }
        else if (root.value.equals("*")) {
            if (root.left.value.equals("0") || root.right.value.equals("0")) {
                root.value = "0";
            }
            else if (root.right.value.equals("1")) {
                root.value = root.left.value;
            }
            else if (root.left.value.equals("1")) {
                root.value = root.right.value;
            }
        }
        else if (root.value.equals("^")) {
            if (root.right.value.equals("1")) {
                root.value = root.left.value;
            }
            else if (root.right.value.equals("0") || root.left.value.equals("1")) {
                root.value = "1";
            }

        }
        else if (root.value.equals("/")) {
            if (root.right.value.equals("1")) {
                root.value = root.left.value;
            }
            else if (root.left.value.equals("0")) {
                root.value =  "0";
            }
            else if (root.left.value.equals(root.right.value)) {
                root.value = "1";
            }
        }
        else if (root.value.equals("-")) {
            if (root.left.value.equals(root.right.value)) {
                root.value = "0";
            }
            else if (root.right.equals("0")) {
                root.value = root.left.value;
            }
        }
        // Need a check for if both left and right are numbers, then perform operation
        if (!originalValue.equals(root.value)) {
            root.left = null;
            root.right = null;
        }
        return root;
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

        public ExpressionNode(ExpressionNode left, ExpressionNode right, String value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

    }

}