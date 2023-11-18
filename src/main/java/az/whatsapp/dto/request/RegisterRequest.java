package az.whatsapp.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String email;
    private String profilePicture;
    @NotBlank
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String fullName, String email, String profilePicture, String password) {
        this.fullName = fullName;
        this.email = email;
        this.profilePicture = profilePicture;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
