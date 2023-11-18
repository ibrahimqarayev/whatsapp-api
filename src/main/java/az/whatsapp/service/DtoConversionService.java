package az.whatsapp.service;

import az.whatsapp.dto.response.ChatResponse;
import az.whatsapp.dto.response.MessageResponse;
import az.whatsapp.dto.response.UserResponse;
import az.whatsapp.model.Chat;
import az.whatsapp.model.Message;
import az.whatsapp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoConversionService {

    // UserResponse se
    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        return response;
    }

    public List<UserResponse> toUserResponseList(List<User> users) {
        return users.stream().map(this::toUserResponse).collect(Collectors.toList());
    }

    // MessageResponse
    public MessageResponse toMessageResponse(Message message) {
        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setContent(message.getContent());
        response.setTimestamp(message.getTimestamp());
        response.setUserId(message.getUser().getId());
        response.setChatId(message.getChat().getId());
        return response;
    }

    public List<MessageResponse> toMessageResponseList(List<Message> messages) {
        return messages.stream().map(this::toMessageResponse).collect(Collectors.toList());
    }

    // ChatResponse
    public ChatResponse toChatResponse(Chat chat) {
        ChatResponse response = new ChatResponse();
        response.setId(chat.getId());
        response.setChatName(chat.getChatName());
        response.setChatImage(chat.getChatImage());
        response.setAdminIds(chat.getAdmins().stream().map(User::getId).collect(Collectors.toSet()));
        response.setGroup(chat.isGroup());
        response.setCreatedByUserId(chat.getCreatedBy().getId());
        response.setUserIds(chat.getUsers().stream().map(User::getId).collect(Collectors.toSet()));
        response.setMessages(toMessageResponseList(chat.getMessages()));
        return response;
    }

    public List<ChatResponse> toChatResponseList(List<Chat> chats) {
        return chats.stream().map(this::toChatResponse).collect(Collectors.toList());
    }
}
