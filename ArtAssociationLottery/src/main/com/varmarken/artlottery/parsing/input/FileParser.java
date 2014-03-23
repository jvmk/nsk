package com.varmarken.artlottery.parsing.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.varmarken.artlottery.model.Player;
import com.varmarken.artlottery.parsing.PatternMismatchException;

public class FileParser {

	/**
	 * Delimiter used to separate key-value pairs in input file.
	 */
	public static final String DELIMITER = ";";

	private static final int EXPECTED_PARTS = 2;

	/**
	 * Keyword in input file indicating that the player id follows.
	 */
	public static final String KEYWORD_ID = "id";

	/**
	 * Keyword in input file indicating that the player's ticket count follows.
	 */
	public static final String KEYWORD_TICKET = "tickets";

	public static FileParseResult parseFile(File file) {
		// Used to store a successful result.
		HashMap<Integer, Player> players = new HashMap<>();
		// Used to report any errors that may occur while parsing.
		FileParseError error = null;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = null;
			int lineCount = 0;
			while ((line = br.readLine()) != null) {
				// System.out.println("read line is: " + line);
				// We read another line, increment line counter
				lineCount++;
				String[] items = line.split(DELIMITER);
				if (items.length != EXPECTED_PARTS) {
					/*
					 * There were either too many or too few key-value pairs at
					 * this line.
					 */
					error = new FileParseError(
							FileParseErrorType.INPUT_PATTERN_MISMATCH,
							lineCount);
					return error;
				}
				int playerId;
				int playerTickets;
				try {
					playerId = InputParser
							.getValue(items[0], KEYWORD_ID, false);
					playerTickets = InputParser.getValue(items[1],
							KEYWORD_TICKET, false);
				} catch (NumberFormatException e) {
					// Could not pass value to non negative int
					error = new FileParseError(
							FileParseErrorType.NOT_A_NON_NEGATIVE_INT,
							lineCount);
					return error;
				} catch (PatternMismatchException e) {
					// Could not find key=value pattern.
					// Or given key not found in pattern.
					error = new FileParseError(
							FileParseErrorType.KEY_VAL_PAIR_NOT_FOUND,
							lineCount);
					return error;
				}
				Player player = new Player(playerId, playerTickets);
				if (players.containsKey(player.getId())) {
					// Player appeared twice in file.
					// This is most likely a typo, so fail.
					error = new FileParseError(
							FileParseErrorType.PLAYER_ID_DUPLICATE, lineCount);
					return error;
				}
				players.put(player.getId(), player);
			}
			// Successful parse, wrap result.
			return new FileParseSuccess(players);
		} catch (FileNotFoundException e) {
			error = new FileParseError(FileParseErrorType.FILE_NOT_FOUND, 0);
		} catch (IOException e) {
			error = new FileParseError(FileParseErrorType.IO_ERROR, 0);
		}
		return error;
	}
}
