package com.luismatos.twitterclonebackend.useraccount.repository;

import com.luismatos.twitterclonebackend.useraccount.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
