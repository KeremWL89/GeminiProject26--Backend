package com.Backend.Spring.DTO.Response;

import java.util.UUID;

//dont return password , return uuid / status instead
public record UResponse(
        UUID id,
        String username,
        String email,
        String role,
        String status
) { }

