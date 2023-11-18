package az.whatsapp.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(UserException.class)
//    public ResponseEntity<ErrorDetail> userExceptionHandler(UserException ex, WebRequest req) {
//        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
//        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MessageException.class)
//    public ResponseEntity<ErrorDetail> messageExceptionHandler(MessageException ex, WebRequest req) {
//        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
//        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ChatException.class)
//    public ResponseEntity<ErrorDetail> chatExceptionHandler(ChatException ex, WebRequest req) {
//        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
//        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ErrorDetail> badCredentialsExceptionHandler(BadCredentialsException ex, WebRequest req) {
//        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
//        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetail> otherException(Exception ex, WebRequest req) {
//        ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), req.getDescription(false), LocalDateTime.now());
//        return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);
//    }

}
