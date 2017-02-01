package postfix;

import java.util.StringTokenizer;
import java.util.LinkedList;

public class Calculator {
	private StringTokenizer tokenizer;  
    private String token;
    private LinkedList<String> preserve;
    private String postfix;
    
    //private LinkedList<Double> calculateStack;
    
	public Calculator(String line) {
		tokenizer = new StringTokenizer(line , "+-*/^() =" , true);
        token = tokenizer.nextToken();
        preserve = new LinkedList<String>();
        postfix = "";
        
        //calculateStack = new LinkedList<Double>();
	}
	
	void convertPostfix() {
		if (token.equals("+") || token.equals("-")) {
			if (preserve.isEmpty()) {
				preserve.add(token);
			}
			else if (preserve.getLast().equals("(")) {
				preserve.add(token);
			}
			else {
				while (!preserve.isEmpty() && !preserve.getLast().equals("(")) {					
					postfix += preserve.removeLast();
					postfix += " ";
				}
				preserve.add(token);
			}
		}
		else if (token.equals("*") || token.equals("/")) {
			if (preserve.isEmpty()) {
				preserve.add(token);
			}
			else if (preserve.getLast().equals("+") || preserve.getLast().equals("-")){
				preserve.add(token);
			}
			else if (preserve.getLast().equals("(")) {
				preserve.add(token);
			}
			else {
				while ((preserve.getLast().equals("*") || preserve.getLast().equals("/")
						|| preserve.getLast().equals("^")) 
						&& !preserve.getLast().equals("(")) {
					postfix += preserve.removeLast();
					postfix += " ";
				}
				preserve.add(token);
			}
		}
		else if (token.equals("^")) {
			if (preserve.isEmpty()) {
				preserve.add(token);
			}
			else if (preserve.getLast().equals("(")){
				preserve.add(token);
			}
			else {
				preserve.add(token);
			}
		}
		else if (token.equals("(")) {
			preserve.add(token);
		}
		else if (token.equals(")")) {
			while(!preserve.getLast().equals("(")) {
				postfix += preserve.removeLast();
				postfix += " ";
			}
			preserve.removeLast();
		}
		else if (token.equals("=")) {
			clearPreserve();
			getPostfix();
			return;
		}
		else if (token.equals("") || token.equals(" ")) {
			;
		}
		else {
			postfix += token;	
			postfix += " ";
		}
		
		token = tokenizer.nextToken();
		convertPostfix();
	}
	
	void clearPreserve() {
		while (!preserve.isEmpty()) {
			postfix += preserve.removeLast();
			postfix += " ";
		}
	}
	
	String getPostfix() {
		return postfix;
	}
	
	double getResult(String postfix_notation) {
		LinkedList<Double> calculateStack = new LinkedList<Double>();
		StringTokenizer postfix_split = new StringTokenizer(postfix_notation, " +-*/^" , true);
        String postfix_token = postfix_split.nextToken();
        
        while (postfix_split.hasMoreTokens()) {
        	if (postfix_token.equals("+")) {
        		double top = calculateStack.removeLast() + calculateStack.removeLast();
        		calculateStack.add(top);
        	}
        	else if (postfix_token.equals("-")) {
        		double subtrahend = calculateStack.removeLast();
        		double minuend = calculateStack.removeLast();
        		double top = minuend - subtrahend;
        		calculateStack.add(top);
        	}
        	else if (postfix_token.equals("*")) {
        		double top = calculateStack.removeLast() * calculateStack.removeLast();
        		calculateStack.add(top);
        	}
        	else if (postfix_token.equals("/")) {
        		double divisor = calculateStack.removeLast();
        		double dividend = calculateStack.removeLast();
        		double top = dividend / divisor;
        		calculateStack.add(top);
        	}
        	else if (postfix_token.equals("^")) {
        		double power = calculateStack.removeLast();
        		double base = calculateStack.removeLast();
        		double top = Math.pow(base, power);
        		calculateStack.add(top);
        	}
        	else if (postfix_token.equals(" ")) {
        		;
        	}
        	else {
        		double top = Double.valueOf(postfix_token).doubleValue();
        		calculateStack.add(top);
        	}
        	postfix_token = postfix_split.nextToken();
        }
        
        return calculateStack.getFirst();
	}

}
