package com.asiantech.onlineauction.service;

import java.util.List;

import com.asiantech.onlineauction.entity.Account;

public interface AccountService {
	public static String NAME = "accountService";
    public void setMsg(String msg);
    public String getMsg();
	public List<Account> getLimitAccount(int limit,int offset);
	public long getTotalAll();
	public Account create(Account account);
	public Account delete(int id);
	public List<Account> findAll();
	public Account update(Account account);
	public Account findById(int id);
	public Account findByUsername(String username,String pass);
	public Account getAccount(String user);
	public Account getUsernameBidAccount(int iditem);
	public  boolean getCheckUsernameAccount(String user);
	public boolean getLogin(String user,String pass);


}