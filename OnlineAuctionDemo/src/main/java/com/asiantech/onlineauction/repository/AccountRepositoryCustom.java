package com.asiantech.onlineauction.repository;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.asiantech.onlineauction.entity.Account;

public interface AccountRepositoryCustom {
	PageImpl<Account> findByFirstNameAndLastNameContains(String firstName, String lastName, Pageable pageable);
	public List<Account> getLimitAccount(int limit,int offset);
	public long getTotalAll();
	public boolean getCheckUsernameAccount(String user);
	public Account getAccount(String user);
	public int getIdAccount(String user);
	public boolean getLogin(String user,String pass);
}
