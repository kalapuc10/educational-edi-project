package com.sabetski.edi.entity;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserRoleId implements Serializable {
    private User user;
    private Role role;
}
