package postfix;

import java.util.Scanner;

public class Calculate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter an expression: ");
        String line = keyboard.nextLine();
        Calculator calculator = new Calculator(line); 
        calculator.convertPostfix();
        System.out.println("Postfix notation : " + calculator.getPostfix());
        System.out.println("Answer : " + calculator.getResult(calculator.getPostfix()));
        
        keyboard.close();

	}

}
