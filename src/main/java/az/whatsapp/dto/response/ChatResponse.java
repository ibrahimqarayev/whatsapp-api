package az.whatsapp.dto.response;

import java.util.List;
import java.util.Set;

public class ChatResponse {
    private int id;
    private String chatName;
    private String chatImage;
    private boolean isGroup;
    private int createdByUserId;
    private Set<Integer> adminIds;
    private Set<Integer> userIds;
    private List<MessageResponse> messages;

    public ChatResponse() {
    }

    public ChatResponse(int id, String chatName, String chatImage, boolean isGroup, int createdByUserId, Set<Integer> adminIds, Set<Integer> userIds, List<MessageResponse> messages) {
        this.id = id;
        this.chatName = chatName;
        this.chatImage = chatImage;
        this.isGroup = isGroup;
        this.createdByUserId = createdByUserId;
        this.adminIds = adminIds;
        this.userIds = userIds;
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatImage() {
        return chatImage;
    }

    public void setChatImage(String chatImage) {
        this.chatImage = chatImage;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public int getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(int createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Set<Integer> getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(Set<Integer> adminIds) {
        this.adminIds = adminIds;
    }

    public Set<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }

    public List<MessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageResponse> messages) {
        this.messages = messages;
    }
}
