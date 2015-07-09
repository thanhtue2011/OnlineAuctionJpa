package com.asiantech.onlineauction.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.asiantech.onlineauction.entity.Category;

public interface CategoryService {
	public static String NAME = "categoryService";
	public List<Category> findAll();
	public Category findById(int id);
	public Category update(Category category);
	public Category create(Category category);
	public Category delete(int id);
	public int getTotal();
	Page<Category> findAll(Pageable pageable);

}
