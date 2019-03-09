package com.santatecla.G1.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/login")
	public ResponseEntity<User> logIn() {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			User loggedUser = userComponent.getLoggedUser();
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/logout")
	public ResponseEntity<Boolean> logOut(HttpSession session) {
		if (!userComponent.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			session.invalidate();
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}
	
	
}
