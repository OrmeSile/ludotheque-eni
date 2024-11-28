package dev.orme.ludotheque.data.service;

import dev.orme.ludotheque.RoleDTO;
import dev.orme.ludotheque.UserDTO;
import dev.orme.ludotheque.data.entity.User;
import dev.orme.ludotheque.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements DtoConvertable<User, UserDTO> {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public UserDTO getUserById(String id){
        var user = userRepository.findById(id).orElse(null);
        if(user == null) return null;
        return toDto(user);
    }

    public ArrayList<UserDTO> getAllUsers(){
        var users = new ArrayList<UserDTO>();
        userRepository.findAll().iterator().forEachRemaining(user -> users.add(this.toDto(user)));
        return users;
    }

    public User saveUser(UserDTO userDTO) {
        User user = fromDto(userDTO);
        return userRepository.save(user);
    }

    public User fromDto(UserDTO userDTO) {
        return new User(UUID.fromString(userDTO.getId()), userDTO.getUsername(), userDTO.getPassword());
    }

    public UserDTO toDto(User user) {
        UserDTO.Builder builder = UserDTO.newBuilder();
        builder.setId(user.getId().toString());
        builder.setUsername(user.getUsername());
        builder.setPassword(user.getPassword());
        if(user.getRoles() != null && !user.getRoles().isEmpty()) {
            builder.addAllRoles(user.getRoles().stream().map(roleService::toDto).collect(Collectors.toList()));
        }
        return builder.build();
    }
}
