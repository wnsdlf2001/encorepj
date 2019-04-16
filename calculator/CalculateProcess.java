import java.io.*;
import java.math.*;
import java.util.*;


public class CalculateProcess {

	public BigDecimal add(String num1, String num2){
		BigDecimal bnum1 = new BigDecimal(num1);
		BigDecimal bnum2 = new BigDecimal(num2);
		return (bnum1.add(bnum2));
	}
	public BigDecimal subtract(String num1, String num2){
		BigDecimal bnum1 = new BigDecimal(num1);
		BigDecimal bnum2 = new BigDecimal(num2);
		return (bnum1.subtract(bnum2));
	}
	public BigDecimal multiply(String num1, String num2){
		BigDecimal bnum1 = new BigDecimal(num1);
		BigDecimal bnum2 = new BigDecimal(num2);
		return (bnum1.multiply(bnum2));
	}
	public BigDecimal division(String num1, String num2){
		BigDecimal bnum1 = new BigDecimal(num1);
		BigDecimal bnum2 = new BigDecimal(num2);
		return (bnum1.divide(bnum2,30, BigDecimal.ROUND_UP));
	}
	public BigDecimal modular(String num1, String num2){
		BigDecimal bnum1 = new BigDecimal(num1);
		BigDecimal bnum2 = new BigDecimal(num2);
		return (bnum1.remainder(bnum2));
	}
	public BigDecimal root(String num1){
		BigDecimal bnum1 = new BigDecimal(num1);
		return sqrt(bnum1, 10); //루트 math 써야할듯 
	}
	public BigDecimal square(String num1){
		BigDecimal bnum1 = new BigDecimal(num1);
		return (bnum1.pow(2));
	}
	public BigDecimal convert(String num1){
		BigDecimal bnum1 = new BigDecimal(num1);
		return (bnum1.negate());
	}
	public BigDecimal fac(String num1) {
		BigDecimal bnum1 = new BigDecimal(num1);
		return fact(bnum1, bnum1);
	}
	
	public BigDecimal sqrt(BigDecimal x, int scale){
		// Check that x >= 0.
		if (x.signum() < 0) {
			throw new IllegalArgumentException("x < 0");
		}
 
		// n = x*(10^(2*scale))
		BigInteger n = x.movePointRight(scale << 1).toBigInteger();
 
		// The first approximation is the upper half of n.
		int bits = (n.bitLength() + 1) >> 1;
		BigInteger ix = n.shiftRight(bits);
		BigInteger ixPrev;
 
		// Loop until the approximations converge
		// (two successive approximations are equal after rounding).
		do {
			ixPrev = ix;
 
			// x = (x + n/x)/2
			ix = ix.add(n.divide(ix)).shiftRight(1);
 
			Thread.yield();
		} while (ix.compareTo(ixPrev) != 0);
 
		return new BigDecimal(ix, scale);
	}
	
    static BigDecimal fact(BigDecimal n, BigDecimal acc) {
        if (n.equals(BigDecimal.ONE)) {
            return acc;
        }
        BigDecimal lessOne = n.subtract(BigDecimal.ONE);
        return fact(lessOne, acc.multiply(lessOne));
    }
}

			
