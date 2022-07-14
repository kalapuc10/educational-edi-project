package com.sabetski.edi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "main", name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main.user_id_serial")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 256)
    private String name;

    @Column(name = "login", nullable = false, unique = true, length = 50)
    private String login;

    @Column(name = "email", nullable = false, unique = true, length = 135)
    private String email;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + id + ", name=" + name + ", login=" + login + ", email=" + email + "}\n";
    }
}