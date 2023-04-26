package com.eoptech.shopdoda.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_contact")
public class Contact extends BaseEntity {
	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "request_type", nullable = false)
	private String requestType;

	@Column(name = "message", nullable = false)
	private String message;

}
