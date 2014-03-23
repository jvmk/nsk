package com.varmarken.artlottery.parsing.input;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.varmarken.artlottery.model.Player;

public class FileParserTest {

	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();

	@Test
	public void testParseFile_ValidFile() throws IOException {
		// Number of rows (i.e. players) in the created test file.
		int playerCount = 5;
		File file = tmp.newFile("valid_instance.txt");
		// Write valid lines.
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			// Write valid lines.
			for (int i = 0; i < playerCount; i++) {
				// Each player has her (ID * 2) number of tickets.
				bw.write(getValidLine(i, i * 2));
				bw.newLine();
			}
		}
		// Parse written file.
		FileParseResult result = FileParser.parseFile(file);
		// Successful parse as expected?
		assertTrue(result instanceof FileParseSuccess);
		FileParseSuccess success = (FileParseSuccess) result;
		Map<Integer, Player> resultData = success.getResult();
		// Check that the proper number of player objects were created.
		assertTrue(resultData.keySet().size() == playerCount);
		assertTrue(resultData.values().size() == playerCount);
		for (Integer i : resultData.keySet()) {
			// Check map integrity
			assertTrue(resultData.get(i).getId() == i);
			// Check that ticket number is passed correctly.
			assertTrue(resultData.get(i).getTickets().size() == i * 2);
		}
	}

	@Test
	public void testParseFile_PatternMismatch() throws IOException {
		File file = tmp.newFile("invalid_instance_pattern_mismatch.txt");
		int errorLineNumber = 3;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			// Write some valid lines.
			for (int i = 1; i < errorLineNumber; i++) {
				bw.write(getValidLine(i, i * 10));
				bw.newLine();
			}
			// Write the error line.
			bw.write(getValidLine(errorLineNumber, errorLineNumber * 10)
					+ ";name=John;");
			bw.newLine();
			// Write another valid line.
			bw.write(getValidLine(errorLineNumber + 1,
					(errorLineNumber + 1) * 10));
			bw.newLine();
		}
		FileParseResult result = FileParser.parseFile(file);
		// Unsuccessful parse as expected?
		assertTrue(result instanceof FileParseError);
		FileParseError error = (FileParseError) result;
		// Correct error type?
		assertTrue(error.getErrorType() == FileParseErrorType.INPUT_PATTERN_MISMATCH);
		// Correct error line number?
		assertTrue(error.getErrorLineNumber() == errorLineNumber);
	}

	@Test
	public void testParseFile_KeyValPairNotFound() throws IOException {
		File file = tmp.newFile("invalid_instance_key_val_pair_not_found.txt");
		int errorLineNumber = 3;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			// Write some valid lines.
			for (int i = 1; i < errorLineNumber; i++) {
				bw.write(getValidLine(i, i + 2));
				bw.newLine();
			}
			// Write the error line.
			bw.write("id=" + errorLineNumber + ";tckets=5;");
			bw.newLine();
			// Write another valid line.
			bw.write(getValidLine(errorLineNumber + 1, errorLineNumber + 1 + 2));
			bw.newLine();
		}
		FileParseResult result = FileParser.parseFile(file);
		// Unsuccessful parse as expected?
		assertTrue(result instanceof FileParseError);
		FileParseError error = (FileParseError) result;
		// Correct error type?
		assertTrue(error.getErrorType() == FileParseErrorType.KEY_VAL_PAIR_NOT_FOUND);
		// Correct error line number?
		assertTrue(error.getErrorLineNumber() == errorLineNumber);
	}

	@Test
	public void testParseFile_NegativeId() throws IOException {
		File file = tmp.newFile("invalid_instance_negative_id.txt");
		int errorLineNumber = 3;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			// Write some valid lines.
			for (int i = 1; i < errorLineNumber; i++) {
				bw.write(getValidLine(i, i + 2));
				bw.newLine();
			}
			// Write the error line
			bw.write(getValidLine(-errorLineNumber, errorLineNumber + 2));
			bw.newLine();
			// Add another valid line
			bw.write(getValidLine(errorLineNumber + 1, errorLineNumber + 1 + 2));
			bw.newLine();
		}
		FileParseResult result = FileParser.parseFile(file);
		// Unsuccessful parse as expected?
		assertTrue(result instanceof FileParseError);
		FileParseError error = (FileParseError) result;
		// Correct error type?
		assertTrue(error.getErrorType() == FileParseErrorType.NOT_A_NON_NEGATIVE_INT);
		// Correct error line number?
		assertTrue(error.getErrorLineNumber() == errorLineNumber);
	}
	
	@Test
	public void testParseFile_NegativeTickets() throws IOException {
		File file = tmp.newFile("invalid_instance_negative_tickets.txt");
		int errorLineNumber = 3;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (int i = 1; i < errorLineNumber; i++) {
				bw.write(getValidLine(i, i + 2));
				bw.newLine();
			}
			// Write the error line
			bw.write(getValidLine(errorLineNumber, -1));
			bw.newLine();
			// Add another valid line
			bw.write(getValidLine(0, 0));
			bw.newLine();
		}
		FileParseResult result = FileParser.parseFile(file);
		// Unsuccessful parse as expected?
		assertTrue(result instanceof FileParseError);
		FileParseError error = (FileParseError) result;
		// Correct error type?
		assertTrue(error.getErrorType() == FileParseErrorType.NOT_A_NON_NEGATIVE_INT);
		// Correct error line number?
		assertTrue(error.getErrorLineNumber() == errorLineNumber);
	}

	@Test
	public void testParseFile_DuplicatePlayerIds() throws IOException {
		File file = tmp.newFile("invalid_instance_duplicate_player_ids.txt");
		int errorLineNumber = 3;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (int i = 1; i < errorLineNumber; i++) {
				bw.write(getValidLine(i, 0));
				bw.newLine();
			}
			// Write the error line (duplicate ID).
			bw.write(getValidLine(errorLineNumber - 1, 42));
			bw.newLine();
			// Add another valid line.
			bw.write(getValidLine(0, 0));
			bw.newLine();
		}
		FileParseResult result = FileParser.parseFile(file);
		// Unsuccessful parse as expected?
		assertTrue(result instanceof FileParseError);
		FileParseError error = (FileParseError) result;
		// Correct error type?
		assertTrue(error.getErrorType() == FileParseErrorType.PLAYER_ID_DUPLICATE);
		// Correct error line number?
		assertTrue(error.getErrorLineNumber() == errorLineNumber);
	}
	
	/**
	 * Utility method to produce a line that corresponds to the pattern defined
	 * by {@link FileParser}.
	 * 
	 * @param playerId
	 *            ID of the player.
	 * @param playerTickets
	 *            Number of tickets held by the player.
	 * @return A line representing the player with the given ID and the given
	 *         number of tickets (in correspondance with the pattern defined by
	 *         {@link FileParser})
	 */
	private static String getValidLine(int playerId, int playerTickets) {
		return String.format("%s=%d;%s=%d;", FileParser.KEYWORD_ID, playerId,
				FileParser.KEYWORD_TICKET, playerTickets);
	}
}
