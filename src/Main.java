import java.util.Scanner;

public class Main {
	  public static void main(String[] args) {
	    	while (true) {
	            Scanner scan = new Scanner(System.in);
	            String input = scan.nextLine();
	            char[] mid = input.toCharArray();
	            Calculator calculator=new Calculator(input);
	            calculator.mid2Post();
	            //System.out.println(calculator.NBLList);
	            System.out.println(calculator.calcute()); 
			}
	    	
	    }
}
