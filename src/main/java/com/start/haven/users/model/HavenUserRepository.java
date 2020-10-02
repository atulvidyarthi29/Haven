package com.start.haven.users.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HavenUserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByUsername(String username);
}
