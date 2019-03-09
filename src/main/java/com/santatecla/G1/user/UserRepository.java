package com.santatecla.G1.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{

	User findByName(String name);
	
	User findByNameIgnoreCase(String name);
}
