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
public class UserRole {
    @EmbeddedId
    UserRoleId id;

    @ManyToOne(cascade = CascadeType.REFRESH,  fetch = FetchType.LAZY)
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH,  fetch = FetchType.LAZY)
    @MapsId("role_id")
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole (User user, Role role) {
        this.id = new UserRoleId(user.getId(), role.getId());
        this.user = user;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole" + "(Id =  " + id + ")\n"  + "User=" + user + "Role=" + role;
    }
}