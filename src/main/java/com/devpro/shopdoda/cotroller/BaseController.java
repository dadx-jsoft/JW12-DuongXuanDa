package com.devpro.shopdoda.cotroller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.devpro.shopdoda.entities.Category;
import com.devpro.shopdoda.repositories.CategoriesRepo;
import com.devpro.shopdoda.services.CategoriesService;

public class BaseController {

	@Autowired
	private CategoriesService categoriesService;
	
	protected String buildMenu() {
		StringBuilder menu = new StringBuilder();
		
		List<Category> categories = categoriesService.getAllParents();
		for(Category c : categories) {
//			menu.append("<li><a href=\"\">" + c.getName() + "</a>");
			menu.append("<li><a href=\"" + "category/"+ c.getSeo() + "\">"+c.getName()+"</a>");
			if(c.getChilds() != null && !c.getChilds().isEmpty()) {
				recursiveMenu(menu, c.getChilds());
			}
			menu.append("</li>");
		}
		
		return menu.toString();
	}
	//"<li><a href=\"category/" + c.getName() + "\"></a>"
	
	private void recursiveMenu(StringBuilder menu, List<Category> childs) {
		menu.append("<ul>");
		for(Category c : childs) {
//			menu.append("<li><a href=\"\">" + c.getName() + "</a>");
			menu.append("<li><a href=\"" + "category/"+ c.getSeo() + "\">"+c.getName()+"</a>");
			if(c.getChilds() != null && !c.getChilds().isEmpty()) {
				recursiveMenu(menu, c.getChilds());
				menu.append("</li>");
			} else {
				menu.append("</li>");
			}
		}
		menu.append("</ul>");
	}
	
	@ModelAttribute("menu")
	public String getCategories() {
		return this.buildMenu();
	}
	
	
}
