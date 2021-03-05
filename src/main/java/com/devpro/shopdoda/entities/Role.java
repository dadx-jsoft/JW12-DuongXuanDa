package com.devpro.shopdoda.entities;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the tbl_roles database table.
 * 
 */
@Entity
@Table(name = "tbl_roles")
public class Role extends BaseEntity {

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String name;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<User> users;

	private void addUser(User u) {
		users.add(u);
		u.setRoles((List<Role>) this);
	}

	private void removeUser(User u) {
		users.remove(u);
		u.setRoles(null);
	}

	public Role() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}