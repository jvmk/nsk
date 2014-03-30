package com.varmarken.artlottery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.varmarken.artlottery.model.Player;
import com.varmarken.artlottery.parsing.PatternMismatchException;
import com.varmarken.artlottery.parsing.input.InputParser;

public class Launcher {

	/**
	 * Delimiter used to separate key-value pairs in input file.
	 */
	private static final String DELIMITER = ";";

	/**
	 * Keyword in input file indicating that the player id follows.
	 */
	private static final String KEYWORD_ID = "id";

	/**
	 * Keyword in input file indicating that the player's ticket count follows.
	 */
	private static final String KEYWORD_TICKET = "tickets";

	// private static final String DELIMITER_SPACE = " +";

	private static final int EXPECTED_PARTS = 2;
/*
	public static void main(String[] args) throws IOException {
		// TODO fix file path arg
		args = new String[] { "/Users/varmarken/Desktop/testinput" };
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line = null;
			int lineCount = 0;
			while ((line = br.readLine()) != null) {
				System.out.println("read line is: " + line);
				// We read another line, increment line counter
				lineCount++;
				String[] items = line.split(DELIMITER);
				if (items.length != EXPECTED_PARTS) {
					// There were either too many or too few key-value pairs at
					// this line
					// TODO inform user of bad input
					System.out.println("Malformed input at line: " + lineCount);
					// closeFile(br);
					return;
				}
				int playerId;
				int playerTickets;
				try {
					playerId = InputParser
							.getValue(items[0], KEYWORD_ID, false);
					playerTickets = InputParser.getValue(items[1],
							KEYWORD_TICKET, false);
				} catch (NumberFormatException e) {
					System.out.println("Malformed input at line: " + lineCount);
					System.out
							.println("Check that a valid non negative number was provided in all key-value pairs.");
					return;
				} catch (PatternMismatchException e) {
					System.out.println("Malformed input at line: " + lineCount);
					System.out.println("Hint: " + e.getMessage());
					return;
				}
				Player player = new Player(playerId, playerTickets);
				System.out.println(player);
			}
		}
	}
	*/
}
