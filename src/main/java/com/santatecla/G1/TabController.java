package com.santatecla.G1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.santatecla.G1.user.Tabs;
import com.santatecla.G1.user.UserComponent;


public class TabController {
	
	@Autowired
	private UserComponent userComponent;

	private void userTabs(Model model, String url, String name, boolean active) {
		Tabs tab = new Tabs(url, name, active);

		if (!sameTab(tab)) {
			updateActiveTabs(active);
			if (this.userComponent.isLoggedUser()) {
				this.userComponent.getLoggedUser().addTab(tab);
			}
		}
		modelTabs(model);
	}

	public void modelTabs(Model model) {
		try {
			if (!this.userComponent.getLoggedUser().getTabs().isEmpty())
				model.addAttribute("tabs", this.userComponent.getLoggedUser().getTabs());
		} catch (Exception e) {

		}
	}

	public void deleteTab(String name) {
		this.userComponent.getLoggedUser().deleteTabByName(name);
		for (int i = 0; i < this.userComponent.getLoggedUser().getTabs().size(); i++) {
			System.out.println(this.userComponent.getLoggedUser().getTabs().get(i).getName());
		}

	}

	public void updateActiveTabs(boolean active) {
		if (active == true) {
			this.userComponent.getLoggedUser().inactiveAllTabs();
		}
	}

	public boolean sameTab(Tabs tab) {
		for (int i = 0; i < this.userComponent.getLoggedUser().getTabs().size(); i++) {
			if (this.userComponent.getLoggedUser().getTabs().get(i).getName().equalsIgnoreCase(tab.getName())
					&& this.userComponent.getLoggedUser().getTabs().get(i).getUrl().equalsIgnoreCase(tab.getUrl())) {
				return true;
			}
		}
		return false;
	}
	
}
