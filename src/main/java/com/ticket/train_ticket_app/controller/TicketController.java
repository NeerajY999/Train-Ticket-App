package com.ticket.train_ticket_app.controller;

import com.ticket.train_ticket_app.model.Ticket;
import com.ticket.train_ticket_app.model.User;
import com.ticket.train_ticket_app.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {



        @Autowired
        private TicketService ticketService;

        @PostMapping("/purchase")
        public Ticket purchaseTicket(@RequestBody User user) {
            return ticketService.purchaseTicket(user);
        }

        @GetMapping("/receipt")
        public Optional<Ticket> getReceipt(@RequestParam String email) {
            return ticketService.getReceipt(email);
        }

        @GetMapping("/users")
        public List<User> getUsersBySection(@RequestParam String section) {
            return ticketService.getUsersBySection(section);
        }

        @DeleteMapping("/remove")
        public boolean removeUser(@RequestParam String email) {
            return ticketService.removeUser(email);
        }

        @PutMapping("/modify")
        public boolean modifyUserSeat(@RequestParam String email, @RequestParam String newSeat) {
            return ticketService.modifyUserSeat(email, newSeat);
        }

}
