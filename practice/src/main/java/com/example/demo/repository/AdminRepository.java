
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	@Query("SELECT COUNT(a) FROM Admin a WHERE a.email = :email AND a.password = :password")
	int countByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
