package dev.orme.ludotheque.data.dto.inbound;

import dev.orme.ludotheque.data.POJO.Role;
import jakarta.annotation.Nullable;

import java.util.List;

public record UserDTO(String username, String password, @Nullable String id, List<Role> roles) {}