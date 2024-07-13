package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.auth.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
@Service
public class myservices {
	@Autowired
	 private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public void register(AuthRequest authRequest) {
		
	 User user=userRepository.findByUserName(authRequest.getUsername());
	 System.out.println(user);
	 if(user!=null) {
		 throw new UsernameNotFoundException("user already exist");
 	 }
		 User newUser = new User();
	        newUser.setUserName(authRequest.getUsername());
	      
	        
	        
	        newUser.setPassword(passwordEncoder.encode(authRequest.getPassword()));// Encode password
	     

	  

	        userRepository.save(newUser);
	
		
	}


}
