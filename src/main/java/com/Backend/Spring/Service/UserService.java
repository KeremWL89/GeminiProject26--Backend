package com.Backend.Spring.Service;

import com.Backend.Spring.DTO.Request.User.UCreateRequest;
import com.Backend.Spring.DTO.Response.UResponse;
import com.Backend.Spring.Mapper.UserMapper;
import com.Backend.Spring.Model.Entity.UserModel;
import com.Backend.Spring.Model.Entity.UserPrincipal;
import com.Backend.Spring.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserMapper userMapper;

    // this is for encryption   , encrypt it 10 times
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Autowired
    private final UserRepository userRepository;

    public List<UResponse> getAllActiveUsers(){

        return userRepository.findByStatus("ACTIVE").stream()
                .map(userMapper :: toDTO)
                .collect(Collectors.toList());

    }

    @Transactional
    public UResponse createUser(UCreateRequest request){

        UserModel savedUser =  userMapper.toModel(request);

        savedUser.setStatus("ACTIVE");

        UserModel newUser = userRepository.save(savedUser);

        return userMapper.toDTO(newUser);
    }


    public UserModel register(UCreateRequest userRequest){

        UserModel savedUser= userMapper.toModel(userRequest);

        // encrypt the userpassword , set it on the model
        savedUser.setPassword_hash(encoder.encode(userRequest.password_hash()));
        savedUser.setStatus("ACTIVE");

        return userRepository.save(savedUser);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userRepository.findByUsername(username);

        if(user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user); // user principle

    }
}

