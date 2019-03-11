package com.santatecla.G1.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.user.User.BasicView;

@RestController
@RequestMapping("/api")
public class UserRestController {
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private UserRepository userRepository;
	
	@JsonView(User.BasicView.class)
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
	
	@JsonView(User.BasicView.class)
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<User> signup(@RequestBody User user) {
		if (userRepository.findByNameIgnoreCase(user.getName()) == null) {	
			String name = user.getName();
			String password = user.getPasswordHash();
			User newUser = new User(name, password, "ROLE_USER");
			userRepository.save(newUser);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	} 

	
	
}
