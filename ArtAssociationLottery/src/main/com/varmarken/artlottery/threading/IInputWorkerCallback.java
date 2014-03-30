package com.varmarken.artlottery.threading;

import java.util.concurrent.ExecutionException;

import com.varmarken.artlottery.parsing.input.FileParseResult;

/**
 * Base callback interface used by an {@link InputWorker} to deliver
 * notifications to interested clients.
 * 
 * @author Janus Varmarken
 * 
 */
public interface IInputWorkerCallback {
	/**
	 * Invoked when the {@link InputWorker} has finished its background task.
	 * 
	 * @param result
	 *            The result of parsing the file or {@code null} if an
	 *            {@link ExecutionException} occurred during the execution of
	 *            the task.
	 */
	public void onDone(FileParseResult result);
}
