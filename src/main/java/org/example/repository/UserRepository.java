package org.example.repository;

import org.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository


public interface UserRepository extends JpaRepository<UUID,Integer> {
    List<User> findAll();
    Optional<User> findByNameIgnoreCase(String name);
}