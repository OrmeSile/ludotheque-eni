package dev.orme.ludotheque.data.service;

import dev.orme.ludotheque.RoleDTO;
import dev.orme.ludotheque.data.entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements DtoConvertable<Role, RoleDTO> {

    RoleTypeService roleTypeService;

    public RoleService(RoleTypeService roleTypeService) {
        this.roleTypeService = roleTypeService;
    }

    public Role fromDto(RoleDTO roleDTO) {
        return new Role(roleTypeService.fromDto(roleDTO.getRoletype()));
    }

    public RoleDTO toDto(Role role) {
        return RoleDTO.newBuilder().setId(role.getId().toString()).setRoletype(roleTypeService.toDto(role.getName())).build();
    }
}
