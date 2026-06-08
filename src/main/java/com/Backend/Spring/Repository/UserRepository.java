package com.Backend.Spring.Repository;

import com.Backend.Spring.Model.Entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel , UUID> {

    //sql queries here.

    // kısaca "select * from Users where status= ?"
    List<UserModel> findByStatus(String status);

}
