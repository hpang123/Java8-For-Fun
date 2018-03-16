package practice8;

import java.math.*;
import java.util.*;

public class Numbers {
	public static void main(String[] args) {
		System.out.printf("Integer SIZE: %d BYTES: %d\n", Integer.SIZE,
				Integer.BYTES);
		System.out.printf("Long SIZE: %d BYTES: %d\n", Long.SIZE, Long.BYTES);
		System.out.printf("Double SIZE: %d BYTES: %d\n", Double.SIZE,
				Double.BYTES);
		System.out
				.printf("Float SIZE: %d BYTES: %d\n", Float.SIZE, Float.BYTES);
		System.out
				.printf("Short SIZE: %d BYTES: %d\n", Short.SIZE, Short.BYTES);
		System.out.printf("Byte SIZE: %d BYTES: %d\n", Byte.SIZE, Byte.BYTES);
		System.out.printf("Character SIZE: %d BYTES: %d\n", Character.SIZE,
				Character.BYTES);

		System.out.println(Double.hashCode(0.25));
		Random generator = new Random();

		System.out.println(generator.ints().limit(100).reduce(Integer::sum));
		System.out.println(generator.ints(0,100).limit(100).reduce(Integer::min));
		System.out.println(generator.ints(0,100).limit(100).reduce((x,y) -> Integer.min(x,y)));
		
		System.out.println(Integer.sum(10, 1));

		System.out.println(generator.ints().limit(100)
				.mapToObj(i -> i % 2 == 0).reduce(Boolean::logicalXor));
		
		System.out.println(Boolean.logicalAnd(true, true)); //true
		System.out.println(Boolean.logicalAnd(true, false)); //false
		System.out.println(Boolean.logicalOr(true, false)); //true
		System.out.println(Boolean.logicalXor(true, true)); //false
		System.out.println(Boolean.logicalXor(false, false)); //false
		System.out.println(Boolean.logicalXor(true, false)); //true

		int maxValue = Integer.MAX_VALUE;
		int nextValue = maxValue + 1;
		System.out.printf("maxValue: %d, nextValue: %d, compared: %d\n",
				maxValue, nextValue,
				Integer.compareUnsigned(maxValue, nextValue));
		System.out.printf("nextValue / 65536 as unsigned: %d\n",
				Integer.divideUnsigned(nextValue, 65536));
		byte b = -127;
		System.out.println(Byte.toUnsignedInt(b));

		System.out.println(Double.isFinite(1.0 / 0.0));
		System.out.println(Double.isFinite(Math.sqrt(-1.0)));

		b = new BigInteger("120").byteValueExact();
		System.out.println("byteValueExact(120):" +b);
		
		try {
			b = new BigInteger("129").byteValueExact();
		} catch (ArithmeticException ex) {
			ex.printStackTrace();
		}
	}
}
