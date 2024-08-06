package com.employee_management.entity;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="user_details", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 
	@Column(name="fname")
	private String firstName;
	
	@Column(name="lname")
	private String lastName;
	
	private String email;
	
	private String password;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManyToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(name="user_roles",
	joinColumns=@JoinColumn(
			       name="user_id", referencedColumnName="user_id"
			     ),
	       inverseJoinColumns= @JoinColumn(
	    	      name="role_id", referencedColumnName="role_id"	   
	    	    )
			)
	private Collection<Role> roles;
	
	

	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
}
