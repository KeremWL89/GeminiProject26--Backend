package com.Backend.Spring.Repository;

import com.Backend.Spring.Model.Entity.AssetsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetsRepository extends JpaRepository<AssetsModel, UUID> {


}
