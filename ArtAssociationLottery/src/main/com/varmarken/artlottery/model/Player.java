package com.varmarken.artlottery.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	/**
	 * The player's ID.
	 */
	private final int id;

	/**
	 * This player's tickets in the lottery.
	 */
	private List<LotteryTicket> tickets = new ArrayList<LotteryTicket>();

	/**
	 * Create a new {@code Player}.
	 * 
	 * @param id
	 *            The player's ID.
	 * @param lotteryTickets
	 *            The number of {@link LotteryTicket}s that this player owns.
	 */
	public Player(int id, int lotteryTickets) {
		this.id = id;
		this.createTickets(lotteryTickets);
	}

	/**
	 * Get the ID of this {@code Player}.
	 * 
	 * @return The ID of this {@code Player}.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Get this {@code Player}'s {@link LotteryTicket}s. The result is wrapped
	 * in a new list to protect the integrity of this {@code Player}'s internal
	 * list of {@link LotteryTicket}s. The {@link LotteryTicket}s contained in
	 * the returned list are NOT copies. These can be safely leaked as they are
	 * immutable objects.
	 * 
	 * @return A list containing this {@code Player}'s {@link LotteryTicket}s.
	 */
	public List<LotteryTicket> getTickets() {
		return new ArrayList<>(tickets);
	}

	/**
	 * Creates this player's {@link LotteryTicket}s.
	 * 
	 * @param lotteryTickets
	 *            Number of tickets to be produced.
	 */
	private void createTickets(int lotteryTickets) {
		for (int i = 0; i < lotteryTickets; i++) {
			tickets.add(new LotteryTicket(this));
		}
	}

	/**
	 * Prints this player's ID and this player's number of tickets.
	 */
	@Override
	public String toString() {
		return String.format(
				"Player with ID=%d has a total of %d tickets in the lottery.",
				this.getId(), this.tickets.size());
	}
}
