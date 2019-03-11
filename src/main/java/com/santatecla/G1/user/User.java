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
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.santatecla.G1.author.Author.BasicView;


@Entity
public class User {
	
	public interface BasicView{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(BasicView.class)
	private Long id;
	
	@JsonView(BasicView.class)
	private String name;
	
	private String passwordHash;
	
	private ArrayList<Tabs> tabs = new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JsonView(BasicView.class)
	private List<String> roles;
	
	public User() {}

	public User( String name, String password, String... roles) {
		this.name = name;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}
	
	public void addTab(Tabs tab) {
		
		for (int i=0; i<tabs.size(); i++) {
			System.out.println("TABS ANTERIORES " + tabs.get(i).getName());
		}
		this.tabs.add(tab);
	}

	public void removeTab(Tabs tab) {
		this.tabs.remove(tab);
	}

	public void deleteTabById(long id) {
		tabs.removeIf(t -> (t.getId() == id));
	}

	public void inactiveAllTabs() {
		for (int i = 0; i < tabs.size(); i++) {
			this.tabs.get(i).inactiveTab();
		}
	}
	
	public void activeTab(Tabs tab) {
		for (int i = 0; i < tabs.size(); i++) {
			if (this.tabs.get(i).getId() == tab.getId()) {
				this.tabs.get(i).activeTab();
			}
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
	

