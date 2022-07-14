package com.sabetski.edi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "main", name = "user_role")
@IdClass(UserRoleId.class)
public class UserRole {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Override
    public String toString() {
        return "UserRole\n" + "User=" + user + "Role=" + role;
    }
}