package com.varmarken.artlottery.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	Player zeroTickets;
	Player oneTicket;
	Player manyTickets;
	
	private static final int zero = 0;
	private static final int one = 1;
	private static final int many = 4200;
	
	@Before
	public void setUp() {
		zeroTickets = new Player(41, zero);
		oneTicket = new Player(42, one);
		manyTickets = new Player(43, many);
	}
	
	@Test
	public void testTicketIntegrity() {
		assertTrue(zeroTickets.getTickets().size() == zero);
		assertTrue(oneTicket.getTickets().size() == one);
		assertTrue(manyTickets.getTickets().size() == many);
		for(LotteryTicket ticket : zeroTickets.getTickets()) {
			assertTrue(ticket.getTicketOwner().getId() == zeroTickets.getId());
		}
		for(LotteryTicket ticket : oneTicket.getTickets()) {
			assertTrue(ticket.getTicketOwner().getId() == oneTicket.getId());
		}
		for(LotteryTicket ticket : manyTickets.getTickets()) {
			assertTrue(ticket.getTicketOwner().getId() == manyTickets.getId());
		}
	}	
}
