package org.example.repository;

import org.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAll();
    Optional<User> findByNameIgnoreCase(String name);
}
