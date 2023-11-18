package az.whatsapp.dto.request;

public class SingleChatRequest {
    private int userId;

    public SingleChatRequest() {
    }

    public SingleChatRequest(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
