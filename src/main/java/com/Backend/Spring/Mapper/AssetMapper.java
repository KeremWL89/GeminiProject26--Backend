package com.Backend.Spring.Mapper;


import ch.qos.logback.core.model.ComponentModel;
import com.Backend.Spring.DTO.Request.Assets.ACreateRequest;
import com.Backend.Spring.DTO.Response.Assets.AssetResponse;
import com.Backend.Spring.Model.Entity.AssetsModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssetMapper {

    // DB --> FE
    AssetResponse toDTO(AssetsModel aModel);

    //toModel
    AssetsModel AssetCreate(ACreateRequest aCreateRequest);

}
