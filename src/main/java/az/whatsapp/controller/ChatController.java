package az.whatsapp.controller;

import az.whatsapp.dto.request.GroupChatRequest;
import az.whatsapp.dto.request.SingleChatRequest;
import az.whatsapp.dto.response.ApiResponse;
import az.whatsapp.dto.response.ChatResponse;
import az.whatsapp.model.User;
import az.whatsapp.service.ChatService;
import az.whatsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("/single")
    public ResponseEntity<ChatResponse> createChat(@RequestBody SingleChatRequest singleChatRequest, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findProfile(jwt);
        ChatResponse chat = chatService.createChat(reqUser, singleChatRequest.getUserId());
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }

    @PostMapping("/group")
    public ResponseEntity<ChatResponse> createGroup(@RequestBody GroupChatRequest groupChatRequest, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findProfile(jwt);
        ChatResponse groupChat = chatService.createGroup(groupChatRequest, reqUser);
        return new ResponseEntity<>(groupChat, HttpStatus.CREATED);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<ChatResponse> findChatById(@PathVariable int chatId, @RequestHeader("Authorization") String jwt) {
        ChatResponse chat = chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ChatResponse>> findAllChatByUserId(@RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findProfile(jwt);
        List<ChatResponse> chats = chatService.findAllChatByUserId(reqUser.getId());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<ChatResponse> addUserToGroup(@PathVariable Integer chatId, @PathVariable Integer userId, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findProfile(jwt);
        ChatResponse chat = chatService.addUserToGroup(userId, chatId, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PutMapping("/{chatId}/remove/{userId}")
    public ResponseEntity<ChatResponse> removeUserFromGroup(@PathVariable Integer chatId, @PathVariable Integer userId, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findProfile(jwt);
        ChatResponse chat = chatService.removeUserFromGroup(chatId, userId, reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @PutMapping("/delete/{chatId}")
    public ResponseEntity<ApiResponse> deleteChat(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findProfile(jwt);
        chatService.removeChat(chatId, reqUser.getId());

        ApiResponse response = new ApiResponse("Chat is deleted successfully", true);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.NO_CONTENT);
    }


}
