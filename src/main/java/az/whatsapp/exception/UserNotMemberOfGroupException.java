package az.whatsapp.exception;

public class UserNotMemberOfGroupException extends RuntimeException{
    public UserNotMemberOfGroupException(String message) {
        super(message);
    }
}
