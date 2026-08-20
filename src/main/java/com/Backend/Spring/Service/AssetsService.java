package com.Backend.Spring.Service;

import com.Backend.Spring.DTO.Response.Assets.AssetResponse;
import com.Backend.Spring.Mapper.AssetMapper;
import com.Backend.Spring.Model.Enums.AssetStatus;
import com.Backend.Spring.Model.Enums.AssetType;
import com.Backend.Spring.Repository.AssetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetsService {

    @Autowired
    private final AssetsRepository assetsRepository;

    @Autowired
    private final AssetMapper assetMapper;

    public List<AssetResponse> getAllActiveAssets() {

        return assetsRepository.findByStatus(AssetStatus.ACTIVE).stream()
                .map(assetMapper :: toDTO)
                .collect(Collectors.toList());

    }
}
