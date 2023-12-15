package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;



import org.springframework.beans.factory.annotation.Autowired;


@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessagesforUser(int id){
        System.out.println("ABOUT TO CREATE");
        Optional<List<Message>> optionalMess = messageRepository.findMessagesByposted_by(id);
        if(optionalMess.isPresent()){
            return optionalMess.get();
        }else{
            return null;
        }
    }
        public List<Message> getMessages(){
            System.out.println("ABOUT TO CREATE");
            Optional<List<Message>> optionalMess = messageRepository.findMessages();
            if(optionalMess.isPresent()){
                return optionalMess.get();
            }else{
                return null;
            }

    }
    public Message findMessageByMessage_id(int id){
        System.out.println("ABOUT TO CREATE");
        Optional<Message> optionalMess = messageRepository.findMessageByMessage_Id(id);
        if(optionalMess.isPresent()){
            return optionalMess.get();
        }else{
            return null;
        }
    }
    public Message updateMessageByMessage_id(int id, Message text){
       
        Optional<Message> optionalMess = messageRepository.findMessageByMessage_Id(id);
       
        if(optionalMess.isPresent()){
            Message up = messageRepository.findMessageByMessage_Id2(id);
            up.setMessage_text(text.getMessage_text());
            messageRepository.save(up);
            System.out.println("ABOUT TO CREATE" + up);
            return up;
        }else{
            return null;
        }
    }
    public Message deleteMessageByMessage_id(int id){
        System.out.println("ABOUT TO CREATE");
        Optional<Message> optionalMess = messageRepository.findMessageByMessage_Id(id);
        
        if(optionalMess.isPresent()){
            messageRepository.deleteById(id);
            return optionalMess.get();
        }else{
            return null;
        }
    }
    public Message messageCreate(Message message){
        System.out.println("TESTINGGGG hi" + message);
        Optional<Message> dupAccount = messageRepository.findMessageByposted_by(message.getPosted_by());
        if(dupAccount.isPresent()){
            return messageRepository.save(message);
        }else{
        return null;
        }
    }
    
    
}
