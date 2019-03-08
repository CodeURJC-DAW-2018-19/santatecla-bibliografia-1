package com.santatecla.G1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.santatecla.G1.user.Tabs;
import com.santatecla.G1.user.UserComponent;

@Controller
public class TabController {
	
	@Autowired
	private UserComponent userComponent;

	public void userTabs(Model model, String url, String name, boolean active, long id) {
		Tabs tab = new Tabs(url, name, active, id);

		if (!sameTab(tab)) {
			updateActiveTabs(active);
			if (this.userComponent.isLoggedUser()) {
				this.userComponent.getLoggedUser().addTab(tab);
			}
		}
		else {
			changeActiveTabs(tab);
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

	public void deleteTab(long id) {
		this.userComponent.getLoggedUser().deleteTabById(id);
		for (int i = 0; i < this.userComponent.getLoggedUser().getTabs().size(); i++) {
			System.out.println(this.userComponent.getLoggedUser().getTabs().get(i).getName());
		}

	}
	
	public void changeActiveTabs(Tabs tab) {	
		this.userComponent.getLoggedUser().inactiveAllTabs();
		this.userComponent.getLoggedUser().activeTab(tab);	
	}

	public void updateActiveTabs(boolean active) {
		if ((active == true)&(this.userComponent.getLoggedUser()!=null)) {
			this.userComponent.getLoggedUser().inactiveAllTabs();
		}
	}

	public boolean sameTab(Tabs tab) {
		if(this.userComponent.getLoggedUser()!=null) {
		for (int i = 0; i < this.userComponent.getLoggedUser().getTabs().size(); i++) {
			if (this.userComponent.getLoggedUser().getTabs().get(i).getName().equalsIgnoreCase(tab.getName())
					&& this.userComponent.getLoggedUser().getTabs().get(i).getUrl().equalsIgnoreCase(tab.getUrl())) {
				return true;
			}
		}
	}
		return false;
	}
	
	@GetMapping("/delete/{id}")
	private String closeTabs(Model model, @PathVariable long id) {
		
		deleteTab(id);
		return "redirect:/";
	}
	
}
