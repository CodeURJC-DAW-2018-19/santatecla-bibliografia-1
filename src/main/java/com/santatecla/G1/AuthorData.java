package com.santatecla.G1;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AuthorData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String urlImage;
	private Date bornDate, deathDate;
	private String bornPlace;
	private String urlMap;
	
	//Constructor to Spring
	protected AuthorData() {}
	
	
}
