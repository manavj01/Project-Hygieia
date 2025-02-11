package com.hygieia.Project.Hygieia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "Full name is mandatory")
    private String fullName;

//    public User(Long id, String email, String password, String fullName) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.fullName = fullName;
//    }
//
//    public User() {
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email) {
//        this.email = email;
//    }
//
//    public void setPassword(@NotBlank(message = "Password is mandatory") String password) {
//        this.password = password;
//    }
//
//    public @NotBlank(message = "Full name is mandatory") String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(@NotBlank(message = "Full name is mandatory") String fullName) {
//        this.fullName = fullName;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", fullName='" + fullName + '\'' +
//                '}';
//    }
}