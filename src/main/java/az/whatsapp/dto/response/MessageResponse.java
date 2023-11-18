package az.whatsapp.dto.response;

import java.time.LocalDateTime;

public class MessageResponse {
    private int id;
    private String content;
    private LocalDateTime timestamp;
    private int userId;
    private int chatId;

    public MessageResponse() {
    }

    public MessageResponse(int id, String content, LocalDateTime timestamp, int userId, int chatId) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.userId = userId;
        this.chatId = chatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
}
