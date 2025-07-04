package com.black_opeartive.schat.models;

import java.time.LocalDateTime;

public class Message {
    private String        sender;
    private String        content;
    private LocalDateTime timeStamp;

    public LocalDateTime getTimeStamp() { return timeStamp; }
    public String getContent()          { return content; }
    public String getSender()           { return sender; }

    public void setContent   (String content)          { this.content = content;}
    public void setSender    (String sender)           { this.sender = sender; }
    public void setTimeStamp (LocalDateTime timeStamp) { this.timeStamp = timeStamp; }

    public Message() {}

    public Message(String sender, String content) {
        this.sender    = sender;
        this.content   = content;
        this.timeStamp = LocalDateTime.now();
    }

    public Message(String sender, String content, LocalDateTime timeStamp) {
        this.sender    = sender;
        this.content   = content;
        this.timeStamp = timeStamp;
    }

}
