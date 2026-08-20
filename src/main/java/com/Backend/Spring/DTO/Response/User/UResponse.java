package com.Backend.Spring.DTO.Response.User;

import java.util.UUID;

//dont return password , return uuid / status instead
public record UResponse(
        UUID id,
        String username,
        String email,
        String role,
        String status
) { }

