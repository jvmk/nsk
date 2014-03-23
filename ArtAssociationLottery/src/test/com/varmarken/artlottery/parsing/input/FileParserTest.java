package com.varmarken.artlottery.parsing.input;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.varmarken.artlottery.model.Player;

public class FileParserTest {

	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testParseFile_ValidFile() throws IOException {
		// Number of rows (i.e. players) in the created test file.
		int playerCount = 5;
		File file = tmp.newFile("valid_instance.txt");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (int i = 0; i < playerCount; i++) {
				// Each player has her (ID * 2) number of tickets.
				bw.write(String.format("%s=%d;%s=%d;", FileParser.KEYWORD_ID, i,
						FileParser.KEYWORD_TICKET, i * 2));
				bw.newLine();
			}
		}
		FileParseResult result = FileParser.parseFile(file);
		// Successful parse as expected?
		assertTrue(result instanceof FileParseSuccess);
		FileParseSuccess success = (FileParseSuccess) result;
		Map<Integer, Player> resultData = success.getResult();
		// Check that the proper number of player objects were created.
		assertTrue(resultData.keySet().size() == playerCount);
		assertTrue(resultData.values().size() == playerCount);
		for(Integer i : resultData.keySet()) {
			// Check map integrity
			assertTrue(resultData.get(i).getId() == i);
			// Check that ticket number is passed correctly.
			assertTrue(resultData.get(i).getTickets().size() == i * 2);
		}
	}
}
