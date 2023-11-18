package az.whatsapp.service;

import az.whatsapp.dto.request.SendMessageRequest;
import az.whatsapp.dto.response.MessageResponse;
import az.whatsapp.exception.AccessDeniedException;
import az.whatsapp.exception.ResourceNotFoundException;
import az.whatsapp.model.Chat;
import az.whatsapp.model.Message;
import az.whatsapp.model.User;
import az.whatsapp.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ChatService chatService;
    private final DtoConversionService conversionService;

    public MessageService(MessageRepository messageRepository, UserService userService, ChatService chatService, DtoConversionService conversionService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.chatService = chatService;
        this.conversionService = conversionService;
    }

    @Transactional
    public MessageResponse sendMessage(SendMessageRequest sendMessageRequest) {
        User user = userService.findById(sendMessageRequest.getUserId());
        Chat chat = chatService.findById(sendMessageRequest.getChatId());
        Message message = new Message();
        message.setUser(user);
        message.setChat(chat);
        message.setContent(sendMessageRequest.getContent());
        message.setTimestamp(LocalDateTime.now());
        return conversionService.toMessageResponse(save(message));
    }

    public List<MessageResponse> getChatMessages(int chatId, User reqUser) {
        Chat chat = chatService.findById(chatId);

        if (!chat.getUsers().contains(reqUser)) {
            throw new AccessDeniedException("You are not related to this chat " + chat.getId());
        }
        List<Message> messages = messageRepository.findByChatId(chat.getId());
        return conversionService.toMessageResponseList(messages);
    }

    public MessageResponse findMessageById(int messageId) {
        return conversionService.toMessageResponse(findById(messageId));
    }

    @Transactional
    public void deleteMessage(int messageId, User reqUser) {
        Message message = findById(messageId);

        if (message.getUser().getId().equals(reqUser.getId())) {
            messageRepository.deleteById(messageId);
        }
        throw new AccessDeniedException("You can't delete another user's message " + reqUser.getFullName());
    }

    private Message findById(int messageId) {
        return messageRepository.findById(messageId).orElseThrow(() -> new ResourceNotFoundException("Message not found with id : " + messageId));
    }

    @Transactional
    private Message save(Message message) {
        return messageRepository.save(message);
    }

}
