package com.example.workwave.controllers;
import com.example.workwave.entities.ChatMessage;
import com.example.workwave.entities.ChatNotification;
import com.example.workwave.services.ChatMessageService;
import com.example.workwave.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class ChatController {

    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;


    //{
    //    "id": "1",
    //    "chatId": "1",
    //    "senderId": "2",
    //    "recipientId": "1",
    //    "senderName": "John",
    //    "recipientName": "Jane",
    //    "content": "Hello, Jane!",
    //    "timestamp": "2022-04-12",
    //    "status": "RECEIVED"
    //}
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        Optional<String> chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());
        System.out.println(chatMessage);
        System.out.println(chatId);

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable String id) {
        chatMessageService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        String content = requestBody.get("content");
        ChatMessage chatMessage = chatMessageService.findById(id);
        chatMessage.setContent(content);
        chatMessageService.save(chatMessage);
        return ResponseEntity.ok(chatMessage);
    }
    @PostMapping("/chat/add")
    public ResponseEntity<?> addMessage( @RequestBody ChatMessage requestBody) {
        Optional<String> chatId = chatRoomService
                .getChatId(requestBody.getSenderId(), requestBody.getRecipientId(), true);
        requestBody.setChatId(chatId.get());
        chatMessageService.save(requestBody);

        return ResponseEntity.ok(requestBody);
    }
}