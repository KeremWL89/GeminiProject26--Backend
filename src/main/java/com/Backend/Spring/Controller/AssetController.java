package com.Backend.Spring.Controller;

import com.Backend.Spring.DTO.Response.Assets.AssetResponse;
import com.Backend.Spring.Service.AssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Asset")
@RequiredArgsConstructor
public class AssetController {

    private final AssetsService assetService;

    @GetMapping("/GetAllActiveAssets")
    public ResponseEntity<List<AssetResponse>> getAllActiveAssets(){
        List<AssetResponse> data = assetService.getAllActiveAssets();

        if(data.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }


}
