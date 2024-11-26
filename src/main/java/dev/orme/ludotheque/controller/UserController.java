package dev.orme.ludotheque.controller;

import dev.orme.ludotheque.data.dto.inbound.UserDTO;
import dev.orme.ludotheque.data.repository.UserRepository;
import dev.orme.ludotheque.data.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import dev.orme.ludotheque.data.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }
    @GetMapping("/")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public User addUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @PostMapping(value="test", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public String test(){
        return "test";
    }

}
