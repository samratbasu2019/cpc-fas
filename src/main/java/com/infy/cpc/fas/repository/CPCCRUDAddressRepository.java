package com.infy.cpc.fas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.cpc.fas.entity.Address;
import com.infy.cpc.fas.entity.User;

@Repository
public interface CPCCRUDAddressRepository extends JpaRepository<Address, Integer>{

}
