package az.whatsapp.request;

public class UserUpdateRequest {

    private String fullName;
    private String profilePicture;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String fullName, String profilePicture) {
        this.fullName = fullName;
        this.profilePicture = profilePicture;
    }
}
