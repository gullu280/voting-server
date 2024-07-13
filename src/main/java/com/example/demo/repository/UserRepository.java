package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;




public interface UserRepository extends JpaRepository<User,Long> {

//	public User getByuserName(String userName);
	
	@Query("SELECT u FROM User u where u.UserName=:n")
	public User findByUserName(@Param("n") String UserName);
	}
