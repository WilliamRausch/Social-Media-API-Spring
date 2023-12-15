package com.example.controller;


import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.http.ResponseEntity;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
   @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;

    //message

    @PostMapping("/register")
    public  ResponseEntity<Account> register( @RequestBody Account acc) {
       
        Account account = new Account(acc.getUsername(), acc.getPassword());
        System.out.println("TEST" + account);
        Account test = accountService.createAccount(account);
        if(acc.getUsername() == null ||  acc.getPassword().length() <= 4){
test = null;
        }
      
     if(test != null){
  return ResponseEntity.status(200)
     .body(test);
     }else{
        return ResponseEntity.status(409).body(test);
     }
    }
    @PostMapping("/login")
    public  ResponseEntity<Account> login( @RequestBody Account acc ) {
       
        Account account = new Account(acc.getUsername(), acc.getPassword());
       
       Account test = accountService.login(account);
     if(test != null){
  return ResponseEntity.status(200)
     .body(test);
     }else{
        return ResponseEntity.status(401).body(test);
     }
    }
  
    @GetMapping ("accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Integer account_id) {

        List<Message> ans = messageService.getMessagesforUser(3);
        System.out.println("TESTing" + account_id);
  ans = messageService.getMessagesforUser(account_id);

  return ResponseEntity.status(200)
  .body(ans);
    }
    
    //message create
    @PostMapping(value = "/messages")
    public  ResponseEntity<Message> messageCreate( @RequestBody Message mess) {
       
     Message test = null;
        System.out.println("TEST" );
        if(mess.getMessage_text().length() < 255){
      test = messageService.messageCreate(mess);
        }else{
             test = null;
        }
     if(test != null && test.getMessage_text().length() < 255 && test.getMessage_text().length() > 0){
  return ResponseEntity.status(200)
     .body(test);
     }else{
        test = null;
        return ResponseEntity.status(400).body(test);
     }
    }
    
    //message all get
    @GetMapping(value = "/messages")
    public ResponseEntity<List<Message>> getMessagesAll() {

        List<Message> ans = messageService.getMessagesforUser(3);
        //System.out.println("TESTing" + account_id);
  ans = messageService.getMessages();

  return ResponseEntity.status(200)
  .body(ans);
    }
    //get one message
    @GetMapping(value = "messages/{message_id}")
    public ResponseEntity<Message> getMessage(@PathVariable Integer message_id) {

        System.out.println("TESTing" );
 Message ans = messageService.findMessageByMessage_id(message_id);

  return ResponseEntity.status(200)
  .body(ans);
    }
    //delete message
    @DeleteMapping(value = "messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer message_id) {

        System.out.println("TESTing" );
 Message ans = messageService.deleteMessageByMessage_id(message_id);
int count = 1;
if(ans != null){
  return ResponseEntity.status(200)
  .body(1);
}else{
    return ResponseEntity.status(200)
    .body(null);
}
}
    
    //update message
    @PatchMapping (value = "messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer message_id,  @RequestBody Message text) {
Message ans = null;
        if(text.getMessage_text().length() < 255 && text.getMessage_text().length() > 0){
  ans = messageService.updateMessageByMessage_id(message_id, text);
        }
 System.out.println("TESTing" + ResponseEntity.status(200).body(ans));

  if(ans != null){
    return ResponseEntity.status(200)
    .body(1);
  }else{
      return ResponseEntity.status(400)
      .body(null);
  }
    }
   

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        return ex.getParameterName() + " is missing in the query parameters and is required.";
    }
}
