package com.kmilewsk.github.Repository;

import com.kmilewsk.github.Model.LoginRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRequestRepository extends JpaRepository <LoginRequest, Long> {
    Optional<LoginRequest> findByLogin(String login);
}
