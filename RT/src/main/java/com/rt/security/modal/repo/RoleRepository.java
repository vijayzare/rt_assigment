package com.rt.security.modal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rt.security.modal.entity.Role;
import com.rt.security.modal.enums.RoleNumber;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	

   Role findByRoleNumber(RoleNumber  roleNumber);

	
	

}
