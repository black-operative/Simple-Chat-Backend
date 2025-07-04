package com.black_opeartive.schat.controllers;

import com.black_opeartive.schat.models.Message;
import com.black_opeartive.schat.models.Room;
import com.black_opeartive.schat.payload.MessageRequest;
import com.black_opeartive.schat.repositories.RoomRepository;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin("http://localhost:5173")
public class ChatController {
    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @MessageMapping("/send-message/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable String          roomId,
            @RequestBody MessageRequest request
    ) {
        Room room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message(
                request.getSender(),
                request.getContent(),
                LocalDateTime.now()
        );

        if (room != null) {
            room.getMessageList().add(message);
            roomRepository.save(room);
        } else {
            throw new RuntimeException("Room doesn't exist");
        }

        return message;
    }
}
