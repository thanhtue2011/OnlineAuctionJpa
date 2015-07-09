package com.asiantech.onlineauction.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.asiantech.onlineauction.entity.Item;

public interface ItemRepositoryCustom{
	PageImpl<Item> findByTitleContains(String title, Pageable pageable);

	
}
