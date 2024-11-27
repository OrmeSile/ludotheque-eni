package dev.orme.ludotheque.data.service;

import dev.orme.ludotheque.RoleTypeDTO;
import dev.orme.ludotheque.data.POJO.RoleType;
import org.springframework.stereotype.Service;

@Service
public class RoleTypeService implements DtoConvertable<RoleType, RoleTypeDTO> {

    @Override
    public RoleTypeDTO toDto(RoleType roleType) {
        return switch (roleType){
            case ROLE_ADMIN -> RoleTypeDTO.ROLE_ADMIN;
            case ROLE_CLERK -> RoleTypeDTO.ROLE_CLERK;
            case ROLE_USER -> RoleTypeDTO.ROLE_USER;
        };
    }

    @Override
    public RoleType fromDto(RoleTypeDTO roleTypeDTO) {
        return switch(roleTypeDTO){
            case ROLE_ADMIN -> RoleType.ROLE_ADMIN;
            case ROLE_CLERK -> RoleType.ROLE_CLERK;
            case ROLE_USER -> RoleType.ROLE_USER;
        };
    }
}
