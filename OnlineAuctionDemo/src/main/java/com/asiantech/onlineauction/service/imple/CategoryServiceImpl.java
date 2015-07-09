package com.asiantech.onlineauction.service.imple;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiantech.onlineauction.entity.Category;
import com.asiantech.onlineauction.repository.CategoryRepository;
import com.asiantech.onlineauction.service.CategoryService;

@Service(CategoryService.NAME)
@Transactional
public class CategoryServiceImpl implements CategoryService {
    
	@Resource
	private CategoryRepository categoryRepository;
	
	@Transactional
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	@Transactional
	@Override
	public Category findById(int id) {
		return categoryRepository.findOne(id);
	}
	@Override
	@Transactional
	public Category update(Category category) {
		if(category!=null){
		Category updatecategory=categoryRepository.findOne(category.getId_category());
		updatecategory.setTitle(category.getTitle());
		updatecategory.setDescription(category.getDescription());
		return updatecategory;
		}
		return null;
	}
	@Override
	@Transactional
	public Category create(Category category) {
		if(category!=null)
		  return categoryRepository.save(category);
		return null;
	}
	@Override
	@Transactional
	public Category delete(int id) {
		Category category=categoryRepository.findOne(id);
		categoryRepository.delete(category);
		return category;
	}
	@Override
	public int getTotal() {
		return categoryRepository.getTotal();
	}
	@Override
	@Transactional
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

}
