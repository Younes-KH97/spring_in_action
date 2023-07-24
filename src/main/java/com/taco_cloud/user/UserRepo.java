package com.taco_cloud.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserApp, Long> {
    UserApp findByUsername(String username);
}
