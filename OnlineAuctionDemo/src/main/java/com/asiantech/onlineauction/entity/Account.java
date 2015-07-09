package com.asiantech.onlineauction.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id_account;
	@NotNull
	@Size(min = 2, max = 15, message = "Username must be between 2 and 15 characters long.")
	private String username;
	@NotNull
	@Size(min = 4, max = 50, message = "fullname must be between 4 and 50 characters long.")
	private String fullname;
	
	private String password;
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Invalid email address.")
	private String email;
	
	@NotNull
	@Size(min = 3, max = 100, message = "address must be between 3 and 100 characters long.")
	private String address;
	
	private boolean enable=true;
	
	private String rol_name;
	
	private float rating;
	
	public Account(){}
	
	public Account(int id_account, String username, String fullname,
			String password, String email, String address, boolean enable,
			String rol_name, float rating) {
		super();
		this.id_account = id_account;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.address = address;
		this.enable = enable;
		this.rol_name = rol_name;
		this.rating = rating;
	}



	public boolean isEnable() {
		return enable;
	}



	public void setEnable(boolean enable) {
		this.enable = enable;
	}



	public String getRol_name() {
		return rol_name;
	}

	public void setRol_name(String rol_name) {
		this.rol_name = rol_name;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getId_account() {
		return id_account;
	}
	public void setId_account(int id_account) {
		this.id_account = id_account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
