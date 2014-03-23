package com.varmarken.artlottery.model;

/**
 * A ticket in the lottery.
 * 
 * @author varmarken
 * 
 */
public class LotteryTicket {

	/**
	 * The player that owns this {@code LotteryTicket}.
	 */
	private final Player ticketOwner;

	/**
	 * Create a new {@code LotteryTicket}.
	 * 
	 * @param ticketOwner
	 *            The {@link Player} that holds/owns this new
	 *            {@code LotteryTicket}
	 */
	public LotteryTicket(Player ticketOwner) {
		this.ticketOwner = ticketOwner;
	}

	/**
	 * Get the {@link Player} who owns this {@code LotteryTicket}.
	 * 
	 * @return The {@link Player} that owns this {@code LotteryTicket}.
	 */
	public Player getTicketOwner() {
		return this.ticketOwner;
	}
}