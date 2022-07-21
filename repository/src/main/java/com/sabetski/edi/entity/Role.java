package com.sabetski.edi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "main", name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main.role_id_serial")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true, length = 20)
    private String code;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<UserRole> userRoles;

    public Role(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Role{" + "roleId=" + id + ", code=" + code + "}\n";
    }
}