package com.Backend.Spring.DTO.Request.User;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginRequest{
    String username;
    String password;
}
