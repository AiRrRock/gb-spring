package ru.gb.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.spring.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
