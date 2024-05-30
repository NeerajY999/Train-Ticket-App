package com.ticket.train_ticket_app.service;


import com.ticket.train_ticket_app.model.Ticket;
import com.ticket.train_ticket_app.model.User;
import com.ticket.train_ticket_app.repository.TicketRepository;
import com.ticket.train_ticket_app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TicketService.class)
class TicketServiceTest {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        ticketRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testPurchaseTicket() {
        User user = new User(null,"John", "Doe", "john.doe@example.com", null);
        Ticket ticket=ticketService.purchaseTicket(user);
        assertNotNull(ticket);
        assertNotNull(ticket.getId());
    }

    @Test
    void testGetReceipt() {
        User user = new User(null,"John", "Doe", "john.doe@example.com", null);
        ticketService.purchaseTicket(user);
        assertTrue(ticketService.getReceipt("john.doe@example.com").isPresent());
    }

    @Test
    void testRemoveUser() {
        User user = new User(null,"John", "Doe", "john.doe@example.com", null);
        ticketService.purchaseTicket(user);
        assertTrue(ticketService.removeUser("john.doe@example.com"));
        assertFalse(ticketService.getReceipt("john.doe@example.com").isPresent());
    }

    @Test
    void testModifyUserSeat() {
        User user = new User(null,"John", "Doe", "john.doe@example.com", null);
        ticketService.purchaseTicket(user);
        assertTrue(ticketService.modifyUserSeat("john.doe@example.com", "B1"));
        assertEquals("B1", userRepository.findByEmail("john.doe@example.com").getSeat());
    }
}
