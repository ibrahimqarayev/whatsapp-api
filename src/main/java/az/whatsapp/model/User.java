package az.whatsapp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    @Column(name = "profile_picture")
    private String profilePicture;
    private String password;

    public User() {
    }

    public User(Integer id, String fullName, String email, String profilePicture, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.profilePicture = profilePicture;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(fullName, user.fullName)
                && Objects.equals(email, user.email)
                && Objects.equals(profilePicture, user.profilePicture)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, profilePicture, password);
    }
}
