import java.util.Stack;

public class BalancedParens{
	public static boolean isMatchingPair(char c1, char c2) {
		if(c1 == '(' && c2 == ')')
			return true;
		else if(c1 == '{' && c2 == '}')
			return true;
		else if(c1 == '[' && c2 == ']')
			return true;
		else 
			return false;
	}

	public static boolean isBalanced(char[] exp) {
		Stack<Character> s = new Stack<Character>();
		for(int i=0; i<exp.length; i++){
			if(exp[i] == '(' || exp[i] == '{' || exp[i] == '[')
				s.push(exp[i]);
			else if(exp[i] == ')' || exp[i] == '}' || exp[i] == ']') {
				if(s.isEmpty())
					return false;
				else if(!isMatchingPair(s.pop(), exp[i]))
					return false;
			}

		}
		if(s.isEmpty())
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		char[] exp = {'{','(',')','}','[',']','('}; 
		if(isBalanced(exp))
			System.out.println("Balanced");
		else
			System.out.println("Not balanced");
	}
}