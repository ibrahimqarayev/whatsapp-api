package az.whatsapp.controller;

import az.whatsapp.dto.request.SendMessageRequest;
import az.whatsapp.dto.response.ApiResponse;
import az.whatsapp.dto.response.MessageResponse;
import az.whatsapp.model.User;
import az.whatsapp.service.MessageService;
import az.whatsapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody SendMessageRequest sendMessageRequest, @RequestHeader("Authorization") String jwt) {
        User user = userService.findProfile(jwt);
        sendMessageRequest.setUserId(user.getId());
        MessageResponse message = messageService.sendMessage(sendMessageRequest);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageResponse>> getChatMessages(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) {
        User user = userService.findProfile(jwt);
        List<MessageResponse> messages = messageService.getChatMessages(chatId, user);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessage(@PathVariable Integer messageId, @RequestHeader("Authorization") String jwt) {
        User user = userService.findProfile(jwt);
        messageService.deleteMessage(messageId, user);
        ApiResponse response = new ApiResponse("Message deleted successfully", true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NO_CONTENT);
    }


}
