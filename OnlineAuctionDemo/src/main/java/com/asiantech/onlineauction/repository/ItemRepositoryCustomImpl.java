package com.asiantech.onlineauction.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.asiantech.onlineauction.entity.Item;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

	@PersistenceContext
	EntityManager em;

	@Override
	public PageImpl<Item> findByTitleContains(String title, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<Item> getListAccountItem(int id) {
		Query q1 = em.createQuery("SELECT i FROM Item i WHERE i.account.id_account = :?");
		 q1.setParameter("?",id);
		List<Item> list= (List<Item>)q1.getSingleResult();
		return list;
	}*/

}
