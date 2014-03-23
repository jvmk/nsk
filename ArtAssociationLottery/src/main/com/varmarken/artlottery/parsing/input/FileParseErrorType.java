package com.varmarken.artlottery.parsing.input;

/**
 * Enum used to indicate any specific type of error that may occur when parsing
 * a lottery file.
 * 
 * @author Janus Varmarken
 * 
 */
public enum FileParseErrorType {
	KEY_VAL_PAIR_NOT_FOUND, INPUT_PATTERN_MISMATCH, NOT_A_NON_NEGATIVE_INT, PLAYER_ID_DUPLICATE, IO_ERROR, FILE_NOT_FOUND
}
