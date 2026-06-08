package com.Backend.Spring.Service;

import com.Backend.Spring.DTO.Response.UResponse;
import com.Backend.Spring.Mapper.UserMapper;
import com.Backend.Spring.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UResponse> getAllActiveUsers(){

        return userRepository.findByStatus("ACTIVE").stream()
                .map(userMapper :: toDTO)
                .collect(Collectors.toList());

    }

}
