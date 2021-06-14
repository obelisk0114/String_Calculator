package noPostfix;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Calculator1 {
	private StringTokenizer tokenizer;
	private String token;

	public Calculator1(String line) {
		tokenizer = new StringTokenizer(line, "+-*/^()=", true);
		token = tokenizer.nextToken();
	}

	public double Evaluate() {
		return Expression();
	}

	private double Parentheses() {
		double priorResult = 0;

		token = tokenizer.nextToken();
		priorResult = Expression();
		return priorResult;
	}

	private double Primary() {
		double result;

		if (token.equals("(")) {
			result = Parentheses();
		} 
		else if (token.equals("")) {
			token = tokenizer.nextToken();
			result = Expression();
		} 
		else {
			result = Double.valueOf(token).doubleValue();
		}

		if (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken();
		}
		return result;
	}

	private double Power() {
		double nextValue;
		double result;

		ArrayList<Double> number = new ArrayList<Double>(3);
		result = Primary();
		number.add(result);

		while (token.equals("^")) {
			token = tokenizer.nextToken();
			nextValue = Primary();
			number.add(nextValue);
			if (!token.equals("^")) {
				while (number.size() != 1) {
					int last = number.size() - 1;
					result = Math.pow(number.remove(last - 1), number.remove(last - 1));
					number.add(result);
				}
				result = number.get(0);
			}
		}
		return result;
	}

	private double Term() {
		double nextValue;
		double result;

		result = Power();

		while (token.equals("*") || token.equals("/")) {
			if (token.equals("*")) {
				token = tokenizer.nextToken();
				nextValue = Power();
				result *= nextValue;
			} 
			else if (token.equals("/")) {
				token = tokenizer.nextToken();
				nextValue = Power();
				result /= nextValue;
			}
		}

		return result;
	}

	private double Expression() {
		double nextValue;
		double result;

		result = Term();

		while (token.equals("+") || token.equals("-")) {
			if (token.equals("+")) {
				token = tokenizer.nextToken();
				nextValue = Term();
				result += nextValue;
			} 
			else if (token.equals("-")) {
				token = tokenizer.nextToken();
				nextValue = Term();
				result -= nextValue;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String line;

		String choice = "y";
		while (choice.equalsIgnoreCase("y")) {
			System.out.print("Enter an expression: ");
			line = input.nextLine();

			if (line.length() == 0) {
				System.out.println("Invalid input.");
				System.out.println("Try again? (Y/N): ");
				choice = input.nextLine();
				if (choice.equalsIgnoreCase("n")) {
					System.out.println("Bye.");
					input.close();
					System.exit(0);
				} else
					continue;
			}

			Calculator1 expn = new Calculator1(line);
			System.out.println("Result is " + expn.Evaluate());
		}
	}
}
