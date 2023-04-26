package com.eoptech.shopdoda.entities;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The persistent class for the tbl_roles database table.
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_roles")
public class Role extends BaseEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String name;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<User> users;

	public void addUser(User u) {
		users.add(u);
		u.getRoles().add(this);
	}

	public void removeUser(User u) {
		users.remove(u);
		u.setRoles(null);
	}

	public Role() {
	}

	public Role(String description, String name, List<User> users) {
		super();
		this.description = description;
		this.name = name;
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

}