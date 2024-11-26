package dev.orme.ludotheque.data.service;

import dev.orme.ludotheque.data.dto.inbound.UserDTO;
import dev.orme.ludotheque.data.entity.User;
import dev.orme.ludotheque.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO toDto(User user){
        return new UserDTO(user.getUsername(), user.getPassword(), user.getId(), user.getRoles());
    }

    public User fromDto(UserDTO dto){
        var user = new User(dto.username(), dto.password(), dto.id(), dto.roles());
        user.getRoles().forEach(role -> role.setUser(user));
        return user;
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(UserDTO userDTO) {
        User user = fromDto(userDTO);
        return userRepository.save(user);
    }
}
