package com.santatecla.G1.user;

public class Tabs {
	private String url;
	private boolean active;
	private String name;
	private long id;

	public Tabs(String url, String name, boolean active, long id) {
		this.url = url;
		this.active = active;
		this.name = name;
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void activeTab() {
		this.active = true;
	}

	public void inactiveTab() {
		this.active = false;
	}

	public String getName() {
		return this.name;
	}

	public String getUrl() {
		return this.url;
	}
	
	public long getId() {
		return this.id;
	}


}
