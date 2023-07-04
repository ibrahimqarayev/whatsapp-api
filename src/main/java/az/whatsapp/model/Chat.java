package az.whatsapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "chat_name")
    private String chatName;

    @Column(name = "chat_image")
    private String chatImage;

    @ManyToMany
    private Set<User> admins = new HashSet<>();

    @Column(name = "is_group")
    private boolean isGroup;

    @JoinColumn(name = "created_by")
    @ManyToOne
    private User createdBy;

    @ManyToMany
    private Set<User> users = new HashSet<>();

    @OneToMany
    private List<Message> messages = new ArrayList<>();

    public Chat() {
    }

    public Chat(Integer id, String chatName, String chatImage, Set<User> admins, boolean isGroup, User createdBy, Set<User> users, List<Message> messages) {
        this.id = id;
        this.chatName = chatName;
        this.chatImage = chatImage;
        this.admins = admins;
        this.isGroup = isGroup;
        this.createdBy = createdBy;
        this.users = users;
        this.messages = messages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<User> admins) {
        this.admins = admins;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
