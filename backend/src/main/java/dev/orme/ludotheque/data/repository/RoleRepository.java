package dev.orme.ludotheque.data.repository;

import dev.orme.ludotheque.data.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
}
