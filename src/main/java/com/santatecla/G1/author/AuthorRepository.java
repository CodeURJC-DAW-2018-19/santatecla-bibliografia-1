package com.santatecla.G1.author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long>{

	Author findByName(String name);
	Author findById(long id);
	Author findByNameIgnoreCase(String name);
	
}
