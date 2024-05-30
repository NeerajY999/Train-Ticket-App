package com.ticket.train_ticket_app.repository;

import com.ticket.train_ticket_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
