package com.example.repository;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

public interface MessageRepository  extends JpaRepository<Message, Integer>{
    @Query("FROM Message mess where mess.posted_by = :posted_by")
    Optional<List<Message>> findMessagesByposted_by(Integer posted_by);

    @Query("FROM Message mess where mess.posted_by = :posted_by")
    Optional<Message> findMessageByposted_by(Integer posted_by);

    @Query("FROM Message")
    Optional<List<Message>> findMessages();

    @Query("FROM Message mess where mess.message_id = :message_id")
    Optional<Message> findMessageByMessage_Id(Integer message_id);

    @Query("FROM Message mess where mess.message_id = :message_id")
    Message findMessageByMessage_Id2(Integer message_id);
    //Optional<Message> findByAccount(Integer posted_by);
}
