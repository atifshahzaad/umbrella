package com.ou.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ou.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID>{

	@Query("SELECT new com.ou.model.Country(c.id) FROM Country c WHERE c.id=:id")
	Optional<Country> findByIdForReference(@Param("id") UUID id);
}
