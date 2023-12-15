package com.example.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository <Account, Integer>{
    Optional<Account> findByUsernameAndPassword(String name, String password);
}
