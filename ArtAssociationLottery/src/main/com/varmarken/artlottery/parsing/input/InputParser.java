package com.varmarken.artlottery.parsing.input;

import com.varmarken.artlottery.parsing.PatternMismatchException;

public class InputParser {

	private static final String EQUAL_SIGN = "=";

	/**
	 * Constructor is private as this class only offers utility methods and as
	 * such does not have any state to maintain.
	 */
	private InputParser() {

	}

	/**
	 * Parses an input string searching for an integer value for a specified
	 * key. The input string is expected to use the following pattern:
	 * <i>key=value</i>. This method is whitespace and case insensitive as both
	 * {@code input} and {@code key} are trimmed and converted to lower case.
	 * 
	 * @param input
	 *            The input string containing the key-value pair.
	 * @param key
	 *            The key to search for.
	 * @param includeNegatives
	 *            Whether to consider negative values as legal values.
	 * @return The integer value corresponding to the given key.
	 * @throws PatternMismatchException
	 *             Thrown if the {@code input} does not match the key-value
	 *             pattern defined above.
	 * @throws NumberFormatException
	 *             Thrown if the value found in the {@code input} does not parse
	 *             to a valid {@code int} or if {@code includeNegatives} is
	 *             {@code false} and the value is parsed to a negative number.
	 */
	public static int getValue(String input, String key,
			boolean includeNegatives) {
		// remove excessive whitespace and ignore case
		input = input.trim().toLowerCase();
		key = key.trim().toLowerCase();
		// split to key-value pair
		String keyval[] = input.split(EQUAL_SIGN);
		if (keyval.length != 2) {
			// input did not match key=value pattern
			throw new PatternMismatchException(
					"String did not match expected key-value string pattern");
		}
		// remove any white space
		keyval[0] = keyval[0].trim();
		keyval[1] = keyval[1].trim();
		if (!keyval[0].equals(key)) {
			// input key did not match expected key
			throw new PatternMismatchException(
					"Invalid key in key=value pattern.");
		}
		int value = Integer.parseInt(keyval[1]);
		if (!includeNegatives && value < 0) {
			throw new NumberFormatException(
					"Parsing resulted in a negative value.");
		}
		return value;
	}
}
