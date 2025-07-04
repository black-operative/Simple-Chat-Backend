package com.black_opeartive.schat.payload;

public class MessageRequest {
    private String content;
    private String sender;
    private String roomId;

    public String getContent() { return content; }
    public String getSender()  { return sender; }
    public String getRoomId()  { return roomId; }

    public void setContent (String content) { this.content = content; }
    public void setSender  (String sender)  { this.sender = sender; }
    public void setRoomId  (String roomId)  { this.roomId = roomId; }

    public MessageRequest() {}

    public MessageRequest(String content, String sender, String roomId) {
        this.content = content;
        this.sender = sender;
        this.roomId = roomId;
    }
}
