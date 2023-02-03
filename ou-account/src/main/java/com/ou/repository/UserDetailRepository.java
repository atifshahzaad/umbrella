package com.ou.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ou.dto.UserDetailDTO;
import com.ou.model.Company;
import com.ou.model.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, UUID>{


	@Query("SELECT new com.ou.dto.UserDetailDTO(u.id, u.firstName, u.middleName, u.lastName, u.email, u.picture,"
			+ "u.regCompleted, c.id) FROM UserDetail ud INNER JOIN ud.user u INNER JOIN ud.company c WHERE u.id=:id")
	Optional<UserDetailDTO> get(@Param("id") UUID id);
	
	@Query("SELECT new com.ou.model.Company(c.id) FROM UserDetail ud INNER JOIN ud.company c WHERE ud.user.id=:id")
	Optional<Company> getUserCompany(@Param("id") UUID id);
}
