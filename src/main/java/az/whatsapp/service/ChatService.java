package az.whatsapp.service;

import az.whatsapp.dto.request.GroupChatRequest;
import az.whatsapp.dto.response.ChatResponse;
import az.whatsapp.exception.AccessDeniedException;
import az.whatsapp.exception.AdminPermissionRequiredException;
import az.whatsapp.exception.ResourceNotFoundException;
import az.whatsapp.exception.UserNotMemberOfGroupException;
import az.whatsapp.model.Chat;
import az.whatsapp.model.User;
import az.whatsapp.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;
    private final DtoConversionService conversionService;

    public ChatService(ChatRepository chatRepository, UserService userService, DtoConversionService conversionService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
        this.conversionService = conversionService;
    }

    public ChatResponse createChat(User reqUser, int userId) {
        User user = userService.findById(userId);

        Chat isChatExist = chatRepository.findSingleChatByUserIds(user, reqUser);
        if (isChatExist != null) {
            return conversionService.toChatResponse(isChatExist);
        }

        Chat chat = new Chat();
        chat.setCreatedBy(reqUser);
        chat.getUsers().add(user);
        chat.getUsers().add(reqUser);
        chat.setGroup(false);
        return conversionService.toChatResponse(save(chat));
    }

    public List<ChatResponse> findAllChatByUserId(int userId) {
        User user = userService.findById(userId);
        List<Chat> chats = chatRepository.findChatByUserId(user.getId());
        return conversionService.toChatResponseList(chats);
    }

    public ChatResponse createGroup(GroupChatRequest req, User reqUser) {
        Chat groupChat = new Chat();
        groupChat.setGroup(true);
        groupChat.setChatName(req.getChatName());
        groupChat.setChatImage(req.getChatImage());
        groupChat.setCreatedBy(reqUser);
        groupChat.getAdmins().add(reqUser);

        for (Integer userId : req.getUserIds()) {
            User user = userService.findById(userId);
            groupChat.getUsers().add(user);
        }
        return conversionService.toChatResponse(save(groupChat));
    }

    public ChatResponse findChatById(int id) {
        return conversionService.toChatResponse(findById(id));
    }

    public ChatResponse addUserToGroup(int userId, int chatId, User reqUser) {
        User user = userService.findById(userId);
        Chat chat = findById(chatId);

        if (chat.getUsers().contains(reqUser)) {
            chat.getUsers().add(user);
            return conversionService.toChatResponse(save(chat));
        } else {
            throw new AdminPermissionRequiredException("You are not admin");
        }
    }

    public ChatResponse renameGroup(int chatId, String groupName, User reqUser) {
        Chat chat = findById(chatId);

        if (chat.getUsers().contains(reqUser)) {
            chat.setChatName(groupName);
            return conversionService.toChatResponse(save(chat));
        }
        throw new UserNotMemberOfGroupException("You are not member of this group");
    }


    public ChatResponse removeUserFromGroup(int chatId, int userId, User reqUser) {
        Chat chat = findById(chatId);
        User user = userService.findById(userId);

        if (chat.getAdmins().contains(reqUser)) {
            chat.getUsers().remove(user);
            return conversionService.toChatResponse(save(chat));
        } else if (chat.getUsers().contains(reqUser)) {
            if (user.getId().equals(reqUser.getId())) {
                chat.getUsers().remove(user);
            }
        }
        throw new AccessDeniedException("You can't remove another user");
    }

    public void removeChat(int chatId, int userId) {
        ChatResponse chat = findChatById(chatId);
        List<ChatResponse> chats = findAllChatByUserId(userId);

        if (chats.contains(chat)) {
            chatRepository.deleteById(chatId);
        }
    }

    protected Chat findById(int id) {
        return chatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chat not found with id: " + id));
    }

    private Chat save(Chat chat) {
        return chatRepository.save(chat);
    }

}
