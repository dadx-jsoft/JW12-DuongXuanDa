package com.eoptech.shopdoda.cotroller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eoptech.shopdoda.entities.Role;
import com.eoptech.shopdoda.repositories.RoleRepo;

@Controller
public class RoleAdminController {

	@Autowired
	private RoleRepo roleRepo;
	
	@RequestMapping(value = { "admin/roles" }, method = RequestMethod.GET)
	public String roles(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("roles", roleRepo.findAll());
		
		return "back-end/user/roles";
	}
	
	@RequestMapping(value = { "/admin/roles/add" }, method = RequestMethod.GET)
	public String addRole(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		
		model.addAttribute("roleEdit", new Role());
		
		return "back-end/user/save_role";
	}
	@RequestMapping(value = { "/admin/roles/edit/{id}" }, method = RequestMethod.GET)
	public String editRole(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("id") Integer roleID) throws Exception {
		
		Role roleEdit = roleRepo.findById(roleID).get();
		
		model.addAttribute("roleEdit", roleEdit);
		
		return "back-end/save_roles";
	}
	
	@RequestMapping(value = { "/admin/roles/save" }, method = RequestMethod.POST)
	public String saveRole(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("roleEdit") Role roleEdit) throws Exception {
		
		roleEdit.setCreatedDate(new Date());
		
		roleRepo.save(roleEdit);

		return "redirect:/admin/roles";
	}
	
	@RequestMapping(value = { "/admin/roles/delete/{id}" }, method = RequestMethod.GET)
	public String deleteRole(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("id") Integer roleId) throws Exception {
		Role deletedRole = roleRepo.findById(roleId).get();
		deletedRole.setStatus(false);
		roleRepo.save(deletedRole);
		
		return "redirect:/admin/roles";
	}
	
}
