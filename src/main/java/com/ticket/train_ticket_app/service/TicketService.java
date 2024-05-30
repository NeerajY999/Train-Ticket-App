package com.ticket.train_ticket_app.service;

import com.ticket.train_ticket_app.model.Ticket;
import com.ticket.train_ticket_app.model.User;
import com.ticket.train_ticket_app.repository.TicketRepository;
import com.ticket.train_ticket_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;
    public Ticket purchaseTicket(User user){
        userRepository.save(user);
        String seat=allocateSeat();
        user.setSeat(seat);
        Ticket ticket=new Ticket(null,"London","France",20.0,user);
        return ticketRepository.save(ticket);
    }
    public Optional<Ticket> getReceipt(String email){
        return Optional.ofNullable(ticketRepository.findByUserEmail(email));
    }
    public List<User> getUsersBySection(String section){
        return userRepository.findAll().stream()
                .filter(user -> user.getSeat().startsWith(section))
                .collect(Collectors.toList());
    }
    public boolean removeUser(String email){
        Ticket ticket=ticketRepository.findByUserEmail(email);
        if(ticket!=null){
            ticketRepository.delete(ticket);
            return true;
        }
        return false;
    }
    public boolean modifyUserSeat(String email,String newSeat){
        User user=userRepository.findByEmail(email);
        if(user!=null){
            user.setSeat(newSeat);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    private String allocateSeat() {
        long count=ticketRepository.count();
        return (count%2==0?"A":"B")+(count/2+1);
    }


}
