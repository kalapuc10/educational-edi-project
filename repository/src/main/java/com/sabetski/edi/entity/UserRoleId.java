package com.sabetski.edi.entity;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleId implements Serializable {
    private User user;
    private Role role;
}
