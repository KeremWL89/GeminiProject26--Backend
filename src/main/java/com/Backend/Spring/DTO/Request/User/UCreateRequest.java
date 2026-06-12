package com.Backend.Spring.DTO.Request.User;

// creating a dto
public record UCreateRequest(
    String username,
    String email,
    String password_hash,
    String role
) { }
