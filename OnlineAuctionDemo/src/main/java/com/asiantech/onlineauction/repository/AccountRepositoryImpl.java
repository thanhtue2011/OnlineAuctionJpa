package com.asiantech.onlineauction.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.asiantech.onlineauction.entity.Account;

public class AccountRepositoryImpl implements AccountRepositoryCustom{

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public PageImpl<Account> findByFirstNameAndLastNameContains(
			String firstName, String lastName, Pageable pageable) {
		StringBuffer query = new StringBuffer(String.format(" From Account a WHERE lastName LIKE %s", "%"+lastName+"%"));
		if(StringUtils.isNotBlank(firstName)){
			query.append(String.format(" AND a.fistName = '%s'", firstName));
		}
		Sort sort = pageable.getSort();
		List<Order> oders = IteratorUtils.toList(sort.iterator());
		if(CollectionUtils.isNotEmpty(oders)){
			query.append(" ODERBY");
			for(Order oder : oders){
				query.append(String.format(" a.%s %s,", oder.getProperty(), oder.getDirection()));
			}
		}
		StringBuffer queryAccounts = new StringBuffer("Select a ");
		StringBuffer queryCountItems = new StringBuffer("Select COUNT(a) ");
		
		 Query  accounts = em.createQuery(queryAccounts.append(query.toString()).toString(),Account.class);
		 Query toTalItem = em.createQuery(queryCountItems.append(query.toString()).toString(),Long.class);
		 
		 List<Account> lstAccount = accounts.setFirstResult(pageable.getPageNumber())
		 .setMaxResults(pageable.getPageSize())
		 .getResultList();
		 
		 Long total = (long) toTalItem.getFirstResult();
		 
		 PageImpl<Account> pageAcount = new PageImpl<Account>(lstAccount, pageable, total);
		return pageAcount;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getLimitAccount(int limit,int offset){
		return em.createQuery(
			    "SELECT c FROM Account c")
			    .setFirstResult(offset)
			    .setMaxResults(limit)
			    .getResultList();
		
		
	}
	@Override
	public boolean getCheckUsernameAccount(String user){   
		    Query q1 = em.createQuery("SELECT c FROM Account c WHERE c.username = :username");
		    q1.setParameter("username",user);
		try {
			    @SuppressWarnings("unused")
				Account acount = (Account)q1.getSingleResult();
			    return true;
			  }
			 catch (NoResultException e) {
			    return false;
			  }
	}
	@Override
	public boolean getLogin(String user,String pass){   
		    Query q1 = em.createQuery("SELECT c FROM Account c WHERE c.username = :username and c.password = :pass");
		    q1.setParameter("username",user);
		    q1.setParameter("password",pass);
		try {
			    @SuppressWarnings("unused")
				Account acount = (Account)q1.getSingleResult();
			    return true;
			  }
			 catch (NoResultException e) {
			    return false;
			  }
	}
	public long getTotalAll(){
		 Query q = em.createQuery("SELECT COUNT(c) FROM Account c");
		 long count = (long)q.getSingleResult();
		 return count;
	}
	public Account getAccount(String user){
		 Query q1 = em.createQuery("SELECT c FROM Account c WHERE c.username = :username");
		 q1.setParameter("username",user);
		
		  Account acount = (Account)q1.getSingleResult();
		return acount;
	}
	@Override
	public int getIdAccount(String user) {
		Query q = em.createQuery("SELECT c.id_account FROM Account c WHERE c.username = :?");
		 q.setParameter("?",user);
		 int id = (int)q.getSingleResult();
		return id;
	}
	
}
