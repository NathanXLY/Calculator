import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;


public class Calculator {
	
	private ArrayList<String> originalList = new ArrayList<>();
	private Stack<String> symbolStack=new Stack<>();
	private ArrayList<String> NBLList=new ArrayList<>();
	private Stack<BigDecimal> calculateStack=new Stack<>();
	
	public Calculator(String input) {
		StringTokenizer stringTokenizer = new StringTokenizer(input, "+-*/()",true);
        while(stringTokenizer.hasMoreTokens()){
        	originalList .add(stringTokenizer.nextToken());
        }
        //System.out.println(originalList);
	}
	
	public void mid2Post(){
		for(String str:originalList){
			if(isNum(str)){
				NBLList.add(str);
			}else if(isSymbol(str)){
				pushStackLogic(str);
			}else {
				System.out.print("·Ç·¨×Ö·û");
			}
		}
		while(!symbolStack.empty()){
			NBLList.add(symbolStack.pop());
		}
	}
	
	public BigDecimal calcute(){
		for(String str:NBLList){
			if(isNum(str)){
				calculateStack.push(new BigDecimal(str));
			}else {
				calculateStack.push(calcuteLogic(str));
			}
		}
		return calculateStack.pop();
	}
	
	public BigDecimal calcuteLogic(String str){
		BigDecimal aBigDecimal=calculateStack.pop();
		BigDecimal bBigDecimal=calculateStack.pop();
		switch (str) {
		case "+":
			return aBigDecimal.add(bBigDecimal);
		case "-":
			return bBigDecimal.subtract(aBigDecimal);
			
		case "*":
			return aBigDecimal.multiply(bBigDecimal);
			
		case "/":
			return bBigDecimal.divide(aBigDecimal,4,BigDecimal.ROUND_HALF_UP);
			
		default:
			return new BigDecimal(0);
		}
	}
	
	public void pushStackLogic(String str){
		if(symbolStack.empty()){
			symbolStack.push(str);
			return;
		}
		if("(".equals(str)){
			symbolStack.push(str);
			return;
		}
		if(")".equals(str)){
			String topSymbol=null;
			while(!"(".equals(topSymbol=symbolStack.pop())){
				NBLList.add(topSymbol);
			}
			return;
		}
		if ("(".equals(symbolStack.peek())) {
			symbolStack.push(str);
			return;
		}else {
			if(getPriority(str)>getPriority(symbolStack.peek())){
				symbolStack.push(str);
				return;
			}else {
				NBLList.add(symbolStack.pop());
				pushStackLogic(str);
				return;
			}
		}
	}

	public boolean isNum(String str){
		return str.matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])");
	}
	
	public boolean isSymbol(String str){
		return str.matches("[\\+\\-\\*\\/\\(\\)]");
	}
	
	public int getPriority(String str){
		switch (str) {
		case "(":
			return 3;
		case "*":
		case "/":
			return 2;
		case "+":
		case "-":
			return 1;
		case ")":
			return 0;

		default:
			return -1;
		}
		
	}

}
