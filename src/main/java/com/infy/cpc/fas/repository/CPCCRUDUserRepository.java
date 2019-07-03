package com.infy.cpc.fas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infy.cpc.fas.entity.Address;
import com.infy.cpc.fas.entity.User;

@Repository
public interface CPCCRUDUserRepository extends JpaRepository<User, Integer> {
}
