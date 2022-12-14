package com.asra.developer.repository;

import com.asra.developer.common.enums.ERole;
import com.asra.developer.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(ERole name);

	@Query("SELECT r.name FROM Account a LEFT JOIN a.roles r WHERE a.id = ?1")
	List<String> findRoleByAccountId(long accountId);
}
