package com.Backend.Spring.Repository;

import com.Backend.Spring.Model.Entity.AssetsModel;
import com.Backend.Spring.Model.Enums.AssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssetsRepository extends JpaRepository<AssetsModel, UUID> {


    List<AssetsModel> findByStatus(AssetStatus status);
}
