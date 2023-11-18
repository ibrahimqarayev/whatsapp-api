package az.whatsapp.exception;

public class AdminPermissionRequiredException extends RuntimeException{
    public AdminPermissionRequiredException(String message) {
        super(message);
    }
}
