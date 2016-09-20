import java.util.Stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {  // Dijkstra双栈算术表达式求值算法
	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
	
		while (!StdIn.isEmpty()) {
		
			String s = StdIn.readString();
		
			if (s.equals("(")) {
			} else if (s.equals("+")) {
				ops.push(s);
			} else if (s.equals("-")) {
				ops.push(s);
			} else if (s.equals("*")) {
				ops.push(s);
			} else if (s.equals("/")) {
				ops.push(s);
			} else if (s.equals("%")) {
				ops.push(s);
			} else if (s.equals(")")) {
				
				String op = ops.pop();
				double val = vals.pop();

				if (op.equals("+")) {
					val = vals.pop() + val;
				} else if (op.equals("-")) {
					val = vals.pop() - val;
				} else if (op.equals("*")) {
					val = vals.pop() * val;
				} else if (op.equals("/")) {
					val = vals.pop() / val;
				} else if (op.equals("%")) {
					val = vals.pop() % val;
				}
				
				vals.push(val);

			} else {
				vals.push(Double.parseDouble(s));
			}
		}
		
		StdOut.println(vals.pop());
	}
}