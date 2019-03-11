package com.santatecla.G1.author;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long>{

	Author findByName(String name);
	Author findById(long id);
	Author findByNameIgnoreCase(String name);
	List<Author> findByNameContaining(String name);
	
}
