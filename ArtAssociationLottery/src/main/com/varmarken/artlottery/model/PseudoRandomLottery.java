package com.varmarken.artlottery.model;

import java.util.List;
import java.util.Objects;
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
	 * 
	 * @throws NullPointerException
	 *             if {@code tickets} is {@code null}.
	 */
	@Override
	public LotteryTicket pickWinner(List<LotteryTicket> tickets) {
		Objects.requireNonNull(tickets);
		if(tickets.size() <= 0) {
			return null;
		}
		int winningIndex = ThreadLocalRandom.current().nextInt(tickets.size());
		return tickets.get(winningIndex);
	}
}
