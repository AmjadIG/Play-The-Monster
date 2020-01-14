package comlayer;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		Deserializer d = new Deserializer();
		String str = "#login/rayan,BfrrayanmdpjvER=dsfsdf";
		System.out.println(d.extractCommand(str));
		System.out.println(Arrays.toString(d.extractParams(str)));
		System.out.println(d.extractResult(str));
	}
}
