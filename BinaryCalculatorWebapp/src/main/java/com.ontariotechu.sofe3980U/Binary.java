package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	 */
	public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code

		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}

	}
	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue()
	{
		return this.number;
	}
	/**
	 * Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;

	}

	public static Binary OR(Binary num1, Binary num2) {
		// Determine the maximum length of the binary strings
		int maxLength = Math.max(num1.number.length(), num2.number.length());

		// Ensure both binary strings are of equal length by padding with leading zeros if necessary
		String paddedNum1 = String.format("%" + maxLength + "s", num1.number).replace(' ', '0');
		String paddedNum2 = String.format("%" + maxLength + "s", num2.number).replace(' ', '0');

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < maxLength; i++) {
			char digit1 = paddedNum1.charAt(i);
			char digit2 = paddedNum2.charAt(i);
			if (digit1 == '1' || digit2 == '1') {
				result.append('1');
			} else {
				result.append('0');
			}
		}
		return new Binary(result.toString());
	}

	public static Binary AND(Binary num1, Binary num2) {
		// Determine the maximum length of the binary strings
		int maxLength = Math.max(num1.number.length(), num2.number.length());

		// Ensure both binary strings are of equal length by padding with leading zeros if necessary
		StringBuilder paddedNum1 = new StringBuilder(num1.number);
		StringBuilder paddedNum2 = new StringBuilder(num2.number);
		while (paddedNum1.length() < maxLength) {
			paddedNum1.insert(0, '0');
		}
		while (paddedNum2.length() < maxLength) {
			paddedNum2.insert(0, '0');
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < maxLength; i++) {
			char digit1 = paddedNum1.charAt(i);
			char digit2 = paddedNum2.charAt(i);
			if (digit1 == '1' && digit2 == '1') {
				result.append('1');
			} else {
				result.append('0');
			}
		}
		return new Binary(result.toString());
	}

	public static Binary multiply(Binary num1, Binary num2) {
		Binary result = new Binary("0"); // Initialize result to 0
		Binary multiplier = new Binary(num1.number); // Use num1 as the multiplier

		// Iterate through each bit of num2
		for (int i = num2.number.length() - 1; i >= 0; i--) {
			if (num2.number.charAt(i) == '1') {
				// If the current bit of num2 is 1, add the multiplier to the result shifted appropriately
				result = add(result, multiplier);
			}
			// Shift the multiplier left by 1 bit
			multiplier.number = multiplier.number + "0";
		}
		return result;
	}
}
