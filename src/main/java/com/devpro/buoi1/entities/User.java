package com.devpro.buoi1.entities;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the tbl_users database table.
 * 
 */
@Entity
@Table(name = "tbl_users")
public class User extends BaseEntity {

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String username;

	// bi-directional many-to-many association to Role
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_users_roles", 
			joinColumns = {	@JoinColumn(name = "user_id", nullable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false) })
	private List<Role> roles;

	private void addRole(Role r) {
		roles.add(r);
		r.setUsers((List<User>) this);
	}

	private void removeRole(Role r) {
		roles.remove(r);
		r.setUsers(null);
	}
	
	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}