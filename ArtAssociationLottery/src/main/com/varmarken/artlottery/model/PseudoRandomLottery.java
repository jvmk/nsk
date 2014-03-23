package com.varmarken.artlottery.model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An implementation of {@link ITicketPicker} that uses pseudo random number
 * generation to pick the winning {@link LotteryTicket}.
 * 
 * @author varmarken
 * 
 */
public class PseudoRandomLottery implements ITicketPicker {

	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation uses the {@link ThreadLocalRandom} <b>pseudo</b>
	 * random number generator to produce the winner.
	 * </p>
	 */
	@Override
	public LotteryTicket pickWinner(List<LotteryTicket> tickets) {
		int winningIndex = ThreadLocalRandom.current().nextInt(tickets.size());
		return tickets.get(winningIndex);
	}
}
