package com.asiantech.onlineauction.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.asiantech.onlineauction.entity.Item;

public interface ItemService {
	public static String NAME = "itemService";
	public Item create(Item item);
	public Item delete(int id);
	public List<Item> findAll();
	public Item update(Item item);
	public Item findById(int id);
	public List<Item> getListAccountItem(int id);
	public List<Item> getListAll();
	Page<Item> getListAllCategory(Pageable pageable,String title);
	Page<Item> findAll(Pageable pageable,String title);
	public List<Item> getStatusSucess();
	Page<Item> findAllChildrenCategory(Pageable pageable,String catetitle,String title);
	

}
