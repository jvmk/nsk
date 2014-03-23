package com.varmarken.artlottery.parsing;

/**
 * Exception that is thrown if a parsed {@link String} does not match an
 * expected pattern.
 * 
 * @author Janus Varmarken
 * 
 */
public class PatternMismatchException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new {@link PatternMismatchException} with the specified
	 * detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public PatternMismatchException(String message) {
		super(message);
	}

}
