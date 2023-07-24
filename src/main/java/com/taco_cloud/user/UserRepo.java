package com.taco_cloud.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUsername(String username);
}
