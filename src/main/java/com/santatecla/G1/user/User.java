package com.santatecla.G1.user;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String passwordHash;
	
	private ArrayList<Tabs> tabs = new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	public User() {}

	public User( String name, String password, String... roles) {
		this.name = name;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}
	
	public void addTab(Tabs tab) {
		this.tabs.add(tab);
	}

	public void removeTab(Tabs tab) {
		this.tabs.remove(tab);
	}

	public void deleteTabByName(String name) {
		tabs.removeIf(t -> t.getName().equalsIgnoreCase(name));
	}

	public void inactiveAllTabs() {
		for (int i = 0; i < tabs.size(); i++) {
			this.tabs.get(i).inactiveTab();
		}
	}

	public List<Tabs> getTabs() {
		return this.tabs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	

}
	

