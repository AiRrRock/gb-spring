package ru.gb.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.spring.entities.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
