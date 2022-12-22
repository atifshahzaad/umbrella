package com.ou.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ou.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID>{

}
