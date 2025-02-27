package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BinaryAPIController {

	@GetMapping("/add")
	public String addString(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
                       @RequestParam(name="operand2", required=false, defaultValue="") String operand2) {
		if (operand1.isEmpty() && operand2.isEmpty()) {
			return "0"; // Handle case where both operands are empty
		} else if (operand2.isEmpty()) {
			return "Error"; // Handle case where the second operand is missing
		}
		try {
			Binary number1 = new Binary(operand1);
			Binary number2 = new Binary(operand2);
			return Binary.add(number1, number2).getValue();
		} catch (IllegalArgumentException e) {
			return "Error"; // Handle case where either operand is invalid
		}
		// http://localhost:8080/add?operand1=111&operand2=1010
	}

	@GetMapping("/multiply")
	public String multiplyString(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
								 @RequestParam(name="operand2", required=false, defaultValue="") String operand2) {
		if (operand1.isEmpty() && operand2.isEmpty()) {
			return "0"; // Handle case where both operands are empty
		} else if (operand2.isEmpty()) {
			return "Error"; // Handle case where the second operand is missing
		}

		try {
			Binary number1 = new Binary(operand1);
			Binary number2 = new Binary(operand2);
			return Binary.multiply(number1, number2).getValue();
		} catch (IllegalArgumentException e) {
			return "Error"; // Handle case where either operand is invalid
		}
	}

	@GetMapping("/and")
	public String andString(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
							@RequestParam(name="operand2", required=false, defaultValue="") String operand2) {
		if (operand1.isEmpty() && operand2.isEmpty()) {
			return "0"; // Handle case where both operands are empty
		} else if (operand2.isEmpty()) {
			return "Error"; // Handle case where the second operand is missing
		}

		try {
			Binary number1 = new Binary(operand1);
			Binary number2 = new Binary(operand2);
			return Binary.AND(number1, number2).getValue();
		} catch (IllegalArgumentException e) {
			return "Error"; // Handle case where either operand is invalid
		}
	}

	@GetMapping("/or")
	public String orString(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
						   @RequestParam(name="operand2", required=false, defaultValue="") String operand2) {
		if (operand1.isEmpty() && operand2.isEmpty()) {
			return "0"; // Handle case where both operands are empty
		} else if (operand2.isEmpty()) {
			return "Error"; // Handle case where the second operand is missing
		}

		try {
			Binary number1 = new Binary(operand1);
			Binary number2 = new Binary(operand2);
			return Binary.OR(number1, number2).getValue();
		} catch (IllegalArgumentException e) {
			return "Error"; // Handle case where either operand is invalid
		}
	}


	@GetMapping("/add_json")
	public BinaryAPIResult addJSON(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
                       @RequestParam(name="operand2", required=false, defaultValue="") String operand2) {
		// Perform basic validation for the operands
		if (!isValidBinaryString(operand1) || !isValidBinaryString(operand2)) {
			return new BinaryAPIResult(new Binary(""), "Error", new Binary(""), new Binary(""));
		}
		try {
			Binary number1 = new Binary(operand1);
			Binary number2 = new Binary(operand2);
			return new BinaryAPIResult(number1, "add", number2, Binary.add(number1, number2));
		} catch (IllegalArgumentException e) {
			return new BinaryAPIResult(new Binary(operand1), "Error", new Binary(operand2), new Binary(""));
		}
		// http://localhost:8080/add?operand1=111&operand2=1010
	}
	// Helper method to check if a string is a valid binary string
	private boolean isValidBinaryString(String str) {
		for (char c : str.toCharArray()) {
			if (c != '0' && c != '1') {
				return false;
			}
		}
		return true;
	}
}
