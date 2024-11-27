package dev.orme.ludotheque.controller;

import dev.orme.ludotheque.UserDTO;
import dev.orme.ludotheque.data.entity.User;
import dev.orme.ludotheque.data.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_PROTOBUF_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_PROTOBUF_VALUE, produces = MediaType.APPLICATION_PROTOBUF_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public User addUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_PROTOBUF_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(value = "test", consumes = MediaType.APPLICATION_PROTOBUF_VALUE, produces = MediaType.APPLICATION_PROTOBUF_VALUE)
    public String test() {
        return "test";
    }

}
