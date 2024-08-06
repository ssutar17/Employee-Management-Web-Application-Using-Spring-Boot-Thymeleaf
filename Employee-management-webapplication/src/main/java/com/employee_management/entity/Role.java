package com.employee_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Role")
public class Role {

	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;

	/*
	 * @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 * 
	 * @JoinTable( name = "users_roles", joinColumns = @JoinColumn( name =
	 * "role_id", referencedColumnName = "role_id"), inverseJoinColumns
	 * = @JoinColumn( name = "user_id", referencedColumnName = "user_id")) private
	 * User user;
	 */
	
	public Role(String name) {
		super();
		this.name = name;
	}

	public Role() {
		
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
