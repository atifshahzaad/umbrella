package com.ou.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ou.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID>{

}
