package com.geekbrains.webapp.controllers;

import com.geekbrains.webapp.dtos.ProductDto;
import com.geekbrains.webapp.dtos.UserDto;
import com.geekbrains.webapp.exceptions.DataValidationException;
import com.geekbrains.webapp.exceptions.ResourceNotFoundException;
import com.geekbrains.webapp.model.Category;
import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.model.Role;
import com.geekbrains.webapp.model.User;
import com.geekbrains.webapp.services.RoleService;
import com.geekbrains.webapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.LocalDateType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @PostMapping
    public UserDto save(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        LocalDateTime date = LocalDateTime.now();
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRole = roleService.findByRoleName("ROLE_USER");
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            roles.add(role);
        }
        user.setRoles(roles);
        userService.save(user);
        user.setPassword(null);
        return new UserDto(user);
    }
}
