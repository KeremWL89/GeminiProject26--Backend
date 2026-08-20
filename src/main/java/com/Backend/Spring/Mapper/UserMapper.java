package com.Backend.Spring.Mapper;


import com.Backend.Spring.DTO.Request.User.UCreateRequest;
import com.Backend.Spring.DTO.Response.User.UResponse;
import com.Backend.Spring.Model.Entity.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

//bu interface i service sınıflarına @RequiredArgsConstructor ile enjekte edebiliriz.
//11. satır sayesinde.
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    // DB --> FE
   UResponse toDTO(UserModel uModel);

   // FE --> DB
   UserModel toModel(UCreateRequest uCreateRequest);

}
