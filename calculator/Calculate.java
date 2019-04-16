import java.io.*;
import java.math.BigDecimal;
import java.util.*;


public class Calculate {
	CalculateProcess cp = new CalculateProcess();
	public BigDecimal calculate(String op, String num1, String num2){
		switch(op){
		case"+": 
			return cp.add(num1, num2);
		case"-":
			return cp.subtract(num1, num2);
		case"*":
			return cp.multiply(num1, num2);
		case"/":
			return cp.division(num1, num2);
		case"%": 
			return cp.modular(num1, num2);
		default:
			return null;
		}
	}
	public BigDecimal calculate(String op, String num1){
		switch(op){
		case"rt": 
			return cp.root(num1);
		case"sq": 
			return cp.square(num1);
		case"cv": 
			return cp.convert(num1);
		case"fac":
			return cp.fac(num1);
		default:
			return null;
		}
	}
}