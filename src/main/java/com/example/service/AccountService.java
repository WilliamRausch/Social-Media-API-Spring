package com.example.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

  
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        
        this.accountRepository = accountRepository;
    }


    public Account createAccount(Account account){
        System.out.println("TESTINGGGG");
        Optional<Account> dupAccount = accountRepository.findByUsernameAndPassword(account.getUsername(),account.getPassword());
        if(dupAccount.isPresent()){
            return null;
        }else{
        return accountRepository.save(account);
        }
    }
    public Account login(Account account){
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword(account.getUsername(),account.getPassword());
        if(optionalAccount.isPresent()){
            return optionalAccount.get();
        }else{
            return null;
        }
        
      
    }
    
}
