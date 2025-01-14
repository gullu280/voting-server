package com.example.demo.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
@Service
public class MyUserDetailsService implements  UserDetailsService{
 @Autowired
 private UserRepository userRepository;


 	public UserDetails loadUserByUsername(String username) {
		User user=userRepository.findByUserName(username);
		 if(user==null) {
			throw new UsernameNotFoundException("user not found");
		 }  
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());	
		}
	
}
