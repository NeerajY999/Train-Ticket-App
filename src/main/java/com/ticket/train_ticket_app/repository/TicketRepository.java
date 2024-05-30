package com.ticket.train_ticket_app.repository;

import com.ticket.train_ticket_app.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Ticket findByUserEmail(String email);
}
