package com.black_opeartive.schat.controllers;

import com.black_opeartive.schat.models.Message;
import com.black_opeartive.schat.models.Room;

import com.black_opeartive.schat.repositories.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomRepository roomRepository;

    public RoomController(
            RoomRepository roomRepository
    ) {
        this.roomRepository = roomRepository;
    }

    // Create a Room
    @PostMapping
    public ResponseEntity<?> createRoom(
            @RequestBody String roomId
    ) {
        if (roomRepository.findByRoomId(roomId) == null) {
            // Room doesn't exist, create it
            Room room = new Room();
            room.setRoomId(roomId);
            roomRepository.save(room);

            return ResponseEntity.status(HttpStatus.CREATED).body(room);
        } else {
            // Room exists
            return ResponseEntity.badRequest().body(
                    "Room already exists"
            );
        }
    }

    // Get / Join a Room
    @GetMapping("/{roomId}")
    public  ResponseEntity<?> joinRoom(
            @PathVariable String roomId
    ) {
        Room room = roomRepository.findByRoomId(roomId);

        if (room == null) {
            return ResponseEntity.badRequest().body("Room Not Found.");
        }

        return ResponseEntity.ok(room);
    }

    // Get Messages of a Room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId
    ) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Message> messageList = room.getMessageList();
        return ResponseEntity.ok(messageList);
    }
}
