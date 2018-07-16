package com.jaxi.repository;

import com.jaxi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
     User findByEmail(String email);

     User findByBoostId(String boostId);
}
