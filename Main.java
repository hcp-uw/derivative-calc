
public class Main {

    public static void main(String[] args) {
	    DerivativeCalculator calc = new DerivativeCalculator('x');
	    //System.out.println(calc.makeTree("((3 * x) + 1)"));
	    //calc.TestDerivative("((((x ^ 2) * 2) + 1) ^ 5)");
		System.out.print(calc.toString(calc.TestDerivative("(((2 * (x ^ 2)) + 1) ^ 5)")));
		//System.out.print(calc.toString(calc.TestDerivative("(2 * x)")));
    }
}
