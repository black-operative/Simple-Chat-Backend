package com.black_opeartive.schat.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.ArrayList;

@Document(collection = "rooms")
public class Room {
    @Id
    private String id;
    private String roomId;
    private List<Message> messageList = new ArrayList<>();

    public void setId         (String id)                 { this.id = id; }
    public void setRoomId     (String roomId)             { this.roomId = roomId; }
    public void setMessageList(List<Message> messageList) { this.messageList = messageList; }

    public String        getId()          { return id; }
    public String        getRoomId()      { return roomId; }
    public List<Message> getMessageList() { return messageList; }

    public Room() {}

    public Room(String id, String roomId, ArrayList<Message> messages) {
        this.id = id;
        this.roomId = roomId;
        this.messageList = messages;
    }
}