package com.varmarken.artlottery.parsing.input;

import java.util.Map;
import java.util.Objects;

import com.varmarken.artlottery.model.Player;

/**
 * An instance of this class holds the result of successfully parsing a lottery
 * file.
 * 
 * @author Janus Varmarken
 * 
 */
public class FileParseSuccess extends FileParseResult {

	/**
	 * The parsed data. A map from player's ID to the corresponding player
	 * object.
	 */
	private final Map<Integer, Player> resultData;

	public FileParseSuccess(Map<Integer, Player> resultData) {
		this.resultData = Objects.requireNonNull(resultData);
	}

	public Map<Integer, Player> getResult() {
		return this.resultData;
	}

}
