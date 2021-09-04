package com.geekbrains.webapp.services;

import com.geekbrains.webapp.model.Role;
import com.geekbrains.webapp.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByRoleName(String roleName) {
        return roleRepository.findByName(roleName);
    }

}