package com.example.teamproject.repositories;

import com.example.teamproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findFirstByUsername(String username);

    Users findByUsernameAndActive(String username,boolean active);
}
