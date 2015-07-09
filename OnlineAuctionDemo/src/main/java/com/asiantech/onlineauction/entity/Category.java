package com.asiantech.onlineauction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue
	@Column(name = "id_category", unique = true)
	private int id_category;
	@NotNull
	@Size(min = 1, max = 100, message = "Title must be between 1 and 50 characters long.")
	private String title;
	@NotNull
	@Size(min = 10, max = 4000, message = "Description must be between 10 and 1000 characters long.")
	private String description;
	public Category(){}
	public Category(int id_category, String title, String description) {
		super();
		this.id_category = id_category;
		this.title = title;
		this.description = description;
	}
	public int getId_category() {
		return id_category;
	}
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
