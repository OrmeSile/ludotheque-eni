package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.UserDTO;
import dev.orme.ludotheque.entities.User;

import java.util.UUID;

public class UserConverter implements DtoConverter<User, UserDTO> {

    @Override
    public UserDTO toDto(User user) {
        UserDTO.Builder builder = UserDTO.newBuilder();
        builder.setId(user.getId().toString());
        builder.setUsername(user.getUsername());
        return builder.build();
    }

    @Override
    public User fromDto(UserDTO userDTO) {
        return new User(UUID.fromString(userDTO.getId()), userDTO.getUsername());
    }
}
