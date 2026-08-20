package com.Backend.Spring.DTO.Request.Assets;

import com.Backend.Spring.Model.Enums.AssetType;

public record ACreateRequest(
        String assetName,
        AssetType AssetType,
        String ipAddress,
        String macAddress,
        String location
) {
}
