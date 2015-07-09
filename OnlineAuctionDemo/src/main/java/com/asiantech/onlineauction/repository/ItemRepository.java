package com.asiantech.onlineauction.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asiantech.onlineauction.repository.ItemRepositoryCustom;
import com.asiantech.onlineauction.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>, ItemRepositoryCustom {
	  @Query("select u from Item u where u.account.id_account = :id")
	  List<Item> getIdAccount(@Param("id") int id);
	  
	  @Query("select u from Item u where u.bidEnddate > NOW()")
	  List<Item> getListAll();
	  
	  @Query("select u from Item u where u.category.title = :title")
	  Page<Item> getListAllCategory(Pageable pageable,@Param("title") String title);

	  @Query("select u from Item u where u.title LIKE :title ")
	  Page<Item> findAll(Pageable pageable,@Param("title") String title);
	  
	  @Query("select u from Item u where u.category.title = :catetitle and u.title LIKE :title")
	  Page<Item> findAllChildrenCategory(Pageable pageable,@Param("catetitle") String catetitle,@Param("title") String title);
	  
	  
	  
	  

}
