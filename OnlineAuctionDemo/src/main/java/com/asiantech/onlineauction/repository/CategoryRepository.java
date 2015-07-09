package com.asiantech.onlineauction.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asiantech.onlineauction.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>,CategoryRepositoryCustom {
	  @Query("select count(u) from Category u")
	  int getTotal();
	  Page<Category> findByTitle(String title, Pageable pageable);
	  Page<Category> findAll(Pageable pageable);

}
