package com.ou.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ou.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID>{

	@Query("SELECT new com.ou.model.Company(c.id) FROM Company c WHERE c.id=:id")
	Optional<Company> findCompanyByIdForReference(@Param("id") UUID id);
}
