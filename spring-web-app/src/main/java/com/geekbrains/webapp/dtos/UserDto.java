package com.geekbrains.webapp.dtos;

import com.geekbrains.webapp.model.Role;
import com.geekbrains.webapp.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private Collection<Role> roles;
    private String password;

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.roles = user.getRoles();
        this.email = user.getEmail();
    }
}
