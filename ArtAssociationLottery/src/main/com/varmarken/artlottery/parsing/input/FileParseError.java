package com.varmarken.artlottery.parsing.input;

/**
 * An instance of this class contains information about a parsing error that has
 * occurred when parsing a lottery file.
 * 
 * @author Janus Varmarken
 * 
 */
public class FileParseError extends FileParseResult {

	/**
	 * The type of this error.
	 */
	private final FileParseErrorType errorType;

	/**
	 * The line number where the error was found.
	 */
	private final int errorLineNumber;

	public FileParseError(FileParseErrorType errorType, int errorLineNumber) {
		this.errorType = errorType;
		this.errorLineNumber = errorLineNumber;
	}

	/**
	 * Get the type of this error.
	 * 
	 * @return The type of this error.
	 */
	public FileParseErrorType getErrorType() {
		return this.errorType;
	}

	/**
	 * Get the line number where the error occurred.
	 * 
	 * @return The line number where the error occurred.
	 */
	public int getErrorLineNumber() {
		return errorLineNumber;
	}
}
