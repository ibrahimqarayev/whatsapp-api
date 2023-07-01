package az.whatsapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String email;
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
}
