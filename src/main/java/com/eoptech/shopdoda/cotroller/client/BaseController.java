package com.eoptech.shopdoda.cotroller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.eoptech.shopdoda.entities.Category;
import com.eoptech.shopdoda.services.CategoriesService;

public class BaseController {

	@Autowired
	private CategoriesService categoriesService;
	
	protected String buildMenu() {
		StringBuilder menu = new StringBuilder();
		
		List<Category> categories = categoriesService.getAllParents();
		for(Category c : categories) {
			if(c.getChilds() != null && !c.getChilds().isEmpty()) {
				menu.append("<li class=\"nav-item dropdown\"><a class=\"nav-link dropdown-toggle\" "
						+ "role ='button' data-toggle='dropdown'aria-haspopup='true' "
						+ "aria-expanded='false' href=\"/category/"+ c.getSeo() + "\">"+c.getName()+"</a>");
				recursiveMenu(menu, c.getChilds());
				menu.append("</li>");
			}else {
				menu.append("<li class=\"nav-item\"><a class=\"nav-link\" href=\"/category/"+ c.getSeo() + "\">"+c.getName()+"</a></li>");
			}
		}
		
		return menu.toString();
	}
	private void recursiveMenu(StringBuilder menu, List<Category> childs) {
		for(Category c : childs) {
			if(c.getChilds() != null && !c.getChilds().isEmpty()) {
				// add child but still haven't done
				recursiveMenu(menu, c.getChilds());
				menu.append("</li>");
			} else {
				menu.append("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>");
				menu.append("<a class='dropdown-item' href=\'/category/"+c.getSeo()+"\'>"+c.getName()+"</a>");
				menu.append("</div>");
			}
		}
		
	}
	
	protected String buildMenu1() {
		StringBuilder menu = new StringBuilder();
		
		List<Category> categories = categoriesService.getAllParents();
		for(Category c : categories) {
			menu.append("<li><a href=\"/category/"+ c.getSeo() + "\">"+c.getName()+"</a>");
			if(c.getChilds() != null && !c.getChilds().isEmpty()) {
				recursiveMenu1(menu, c.getChilds());
			}
			menu.append("</li>");
		}
		
		return menu.toString();
	}

	private void recursiveMenu1(StringBuilder menu, List<Category> childs) {
		menu.append("<ul>");
		for(Category c : childs) {
			menu.append("<li><a href=\"/category/"+ c.getSeo() + "\">"+c.getName()+"</a>");
			if(c.getChilds() != null && !c.getChilds().isEmpty()) {
				recursiveMenu1(menu, c.getChilds());
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
