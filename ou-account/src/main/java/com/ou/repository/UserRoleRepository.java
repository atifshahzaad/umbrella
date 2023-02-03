package com.ou.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ou.dto.UserSearchResultDTO;
import com.ou.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

	@Query("SELECT new com.ou.dto.UserSearchResultDTO(u.id, CONCAT(u.firstName, ' ', u.middleName, ' ', u.lastName) AS name, CAST(STRING_AGG(r.name, ' ') AS string) AS role) "
			+ "FROM UserRole ur JOIN ur.user u JOIN ur.role r "
			+ "WHERE LOWER(u.firstName) LIKE %:name% OR LOWER(u.middleName) LIKE %:name% OR LOWER(u.lastName) LIKE %:name% GROUP BY u.id, u.firstName, u.middleName, u.lastName")
	List<UserSearchResultDTO> searchUserByName(@Param("name") String name);

}
