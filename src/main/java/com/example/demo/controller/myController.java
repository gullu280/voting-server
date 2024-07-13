package com.example.demo.controller;


import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.AuthRequest;
import com.example.demo.auth.AuthResponse;
import com.example.demo.auth.JwtUtill;
import com.example.demo.auth.MyUserDetailsService;
import com.example.demo.services.myservices;

@RestController
public class myController {
	
	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtUtill jwtUtill;
	    @Autowired
	    private MyUserDetailsService myUserDetailsService;
	    @Autowired
	    private myservices myservices;

	    @PostMapping("auth/login")
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
	    	System.out.println(authRequest.getUsername() + authRequest.getPassword());
	        try {
	            authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	            );
	        } catch (BadCredentialsException e) {
	            throw new Exception("Incorrect username or password", e);
	        }

	        final UserDetails userDetails = myUserDetailsService
	            .loadUserByUsername(authRequest.getUsername());

	        final String jwt = jwtUtill.generateToken(userDetails.getUsername());

	        return ResponseEntity.ok(new AuthResponse(jwt));
	    }
	    @PostMapping("auth/registers")
	    public ResponseEntity<String> registerUser(@RequestBody AuthRequest authRequest) {
	     	System.out.println(authRequest.getUsername() + authRequest.getPassword());
	        try {
	            myservices.register(authRequest);
	            return ResponseEntity.ok("User registered successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
	        }
	    }

@GetMapping("/")	
public String name() {
	return "hello";
}	
@GetMapping("/home")	
public String home() {
	return "NewFile";
}	
}
