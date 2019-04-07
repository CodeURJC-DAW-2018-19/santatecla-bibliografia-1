package com.santatecla.G1.user;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/login")
	public ResponseEntity<User> logIn() {
		
		if (!userComponent.isLoggedUser()) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			User loggedUser = userComponent.getLoggedUser();
			log.info("Logged as " + loggedUser.getName());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}

	@RequestMapping("/logout")
	public ResponseEntity<Boolean> logOut(HttpSession session) {

		if (!userComponent.isLoggedUser()) {
			log.info("No user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			session.invalidate();
			log.info("Logged out");
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
