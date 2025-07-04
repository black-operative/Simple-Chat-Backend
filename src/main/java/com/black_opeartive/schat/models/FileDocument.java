package com.black_opeartive.schat.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "files")
public class FileDocument {
    @Id
    private String id;

    private String        originalName;
    private String        storedName;
    private String        url;
    private String        contentType;
    private long          size;
    private LocalDateTime uploadedAt;
    private String        uploadedBy;
    private String        roomId;

    public String        getId()            { return id; }
    public String        getOriginalName()  { return originalName; }
    public String        getStoredName()    { return storedName; }
    public String        getUrl()           { return url; }
    public String        getContentType()   { return contentType; }
    public long          getSize()          { return this.size; }
    public LocalDateTime getUploadedAt()    { return uploadedAt; }
    public String        getUploadedBy()    { return uploadedBy; }
    public String        getRoomId()        { return roomId; }

    public void setId          (String id)                { this.id = id; }
    public void setOriginalName(String originalName)      { this.originalName = originalName; }
    public void setStoredName  (String storedName)        { this.storedName = storedName; }
    public void setUrl         (String url)               { this.url = url; }
    public void setContentType (String contentType)       { this.contentType = contentType; }
    public void setSize        (long size)                { this.size = size; }
    public void setUploadedAt  (LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
    public void setUploadedBy  (String uploadedBy)        { this.uploadedBy = uploadedBy; }
    public void setRoomId      (String roomId)            { this.roomId = roomId; }
}
