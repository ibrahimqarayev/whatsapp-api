package az.whatsapp.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UserUpdateRequest {

    @NotBlank
    private String fullName;
    private String profilePicture;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String fullName, String profilePicture) {
        this.fullName = fullName;
        this.profilePicture = profilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
