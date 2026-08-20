package com.Backend.Spring.DTO.Response.Assets;

import com.Backend.Spring.Model.Enums.AssetStatus;
import com.Backend.Spring.Model.Enums.AssetType;

import java.time.LocalDateTime;
import java.util.UUID;

public record AssetResponse(
        UUID id,
        String asset_name,
        AssetType asset_type,
        String ip_address,
        String mac_address,
        AssetStatus status,
        String location,
        UUID assigned_user_id,
        String assigned_username,
        LocalDateTime updatedAt
) {
}
