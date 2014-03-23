package com.varmarken.artlottery.model;

import java.util.List;

/**
 * <p>
 * Interface for picking a lottery winner. This allows for dependency injection
 * as implementations may individually chose e.g. to use pseudo random winner
 * selection or true random winner selection (for example using RANDOM.ORG)
 * </p>
 * 
 * @author varmarken
 * 
 */
public interface ITicketPicker {
	/**
	 * Pick a winner of the lottery from the supplied tickets.
	 * 
	 * @param tickets
	 *            The tickets in the lottery from which a winning ticket is to
	 *            be selected.
	 * @return The winning ticket or {@code null} if {@code tickets} is empty.
	 */
	LotteryTicket pickWinner(List<LotteryTicket> tickets);
}
