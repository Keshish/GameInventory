package com.javaGameLibrary.GameInventory.repository.abstraction;

import com.javaGameLibrary.GameInventory.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
