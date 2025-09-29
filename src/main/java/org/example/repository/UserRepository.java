package org.example.repository;

import org.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<User, UUID> {
=======
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAll();
>>>>>>> parent of e24e6b5 (Added all controller classes and updated models, services, and repositories)
    Optional<User> findByNameIgnoreCase(String name);
}
