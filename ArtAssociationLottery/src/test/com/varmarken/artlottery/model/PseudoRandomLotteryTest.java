package com.varmarken.artlottery.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PseudoRandomLotteryTest {

	private ITicketPicker picker;

	private Player playerOne;
	
	@Before
	public void setUp() {
		this.picker = new PseudoRandomLottery();
	}

	@Test(expected=NullPointerException.class)
	public void testNullNotAllowed() {
		this.picker.pickWinner(null);
	}
	
	@Test
	public void testNoWinner() {
		assertNull(this.picker.pickWinner(new ArrayList<LotteryTicket>()));
	}

	@Test
	public void testOnePlayerOneTicket() {
		int plyId = 1;
		int numOfTickets = 1;
		playerOne = new Player(plyId, numOfTickets);
		assertTrue(playerOne.getTickets().size() == numOfTickets);
		LotteryTicket winningTicket = this.picker.pickWinner(playerOne.getTickets());
		// Check winner
		assertNotNull(winningTicket);
		assertTrue(winningTicket.getTicketOwner() == playerOne);
		assertTrue(winningTicket.getTicketOwner().getId() == playerOne.getId());
		assertTrue(playerOne.getTickets().contains(winningTicket));
	}

	@Test
	public void testOnePlayerManyTickets() {
		int plyId = 1;
		int numOfTickets = 100;
		playerOne = new Player(plyId, numOfTickets);
		assertTrue(playerOne.getTickets().size() == numOfTickets);
		LotteryTicket winningTicket = this.picker.pickWinner(playerOne.getTickets());
		
		// Check winner
		assertNotNull(winningTicket);
		assertTrue(winningTicket.getTicketOwner() == playerOne);
		assertTrue(winningTicket.getTicketOwner().getId() == playerOne.getId());
		assertTrue(playerOne.getTickets().contains(winningTicket));
		
		// Now let's verify that only one ticket was picked as the winning ticket.
		int winCount = 0;
		for(LotteryTicket ticket : playerOne.getTickets()) {
			if(ticket == winningTicket) {
				winCount++;
			}
		}
		assertTrue(winCount == 1);
	}
	
}
