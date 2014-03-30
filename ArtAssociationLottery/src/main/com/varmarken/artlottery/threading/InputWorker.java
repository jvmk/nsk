package com.varmarken.artlottery.threading;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import com.varmarken.artlottery.parsing.input.FileParseResult;
import com.varmarken.artlottery.parsing.input.FileParser;

/**
 * Class used to read a file containing lottery data without blocking the EDT.
 * 
 * @author Janus Varmarken
 * 
 */
public class InputWorker<T extends IInputWorkerCallback> extends SwingWorker<FileParseResult, Void> {

	private final File fileToRead;
	
	private final T callback;

	public InputWorker(File fileToRead, T callback) {
		this.fileToRead = Objects.requireNonNull(fileToRead);
		this.callback = callback;
	}

	@Override
	protected FileParseResult doInBackground() throws Exception {
		return FileParser.parseFile(this.fileToRead);
	}
	
	@Override
	protected void done() {
		super.done();
		if(callback != null) {
			try {
				callback.onDone(this.get());
			} catch (InterruptedException | ExecutionException e) {
				callback.onDone(null);
			}
		}
	}
}
