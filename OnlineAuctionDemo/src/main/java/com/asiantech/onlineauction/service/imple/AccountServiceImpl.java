package com.asiantech.onlineauction.service.imple;
import java.util.List;

import javax.annotation.Resource;

import  com.asiantech.onlineauction.repository.AccountRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiantech.onlineauction.entity.Account;
import com.asiantech.onlineauction.service.AccountService;
@Service(AccountService.NAME)
@Transactional
public class AccountServiceImpl implements AccountService {
	private String msg;
	 public AccountServiceImpl(){
		 msg="";
	 }
	@Resource
	private AccountRepository accountRepository;
	

	@Override
	@Transactional
	public Account create(Account account){
		return accountRepository.save(account);
	}
	@Override
	public Account delete(int id){	
    Account deletedAccount = accountRepository.findOne(id);
		accountRepository.delete(deletedAccount);
		return deletedAccount;
	}
	@Override
	@Transactional
	public List<Account> findAll(){
		return accountRepository.findAll();
	}
	
	@Override
	@Transactional
	public Account findById(int id){
		return accountRepository.findOne(id);
	}
	@Override
	@Transactional
	public void setMsg(String msg) {
		this.msg=msg;
	} 
	@Override
	@Transactional
	public String getMsg() {
		return msg;
	}
	@Override
	public List<Account> getLimitAccount(int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getTotalAll() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	@Transactional
	public Account update(Account account){
		if(account==null){
			return null;
		}
		Account updatedAccount = accountRepository.findOne(account.getId_account());
		updatedAccount.setUsername(account.getUsername());
		updatedAccount.setFullname(account.getFullname());
		updatedAccount.setAddress(account.getAddress());
		updatedAccount.setEmail(account.getEmail());
		updatedAccount.setPassword(account.getPassword());
		updatedAccount.setEnable(account.isEnable());
		updatedAccount.setRating(account.getRating());
		 
		return updatedAccount;
		
	}
	@Override
	public Account findByUsername(String username, String pass) {
		//Account ac=AccountRepository.
		return null;
	}
	@Override
	@Transactional
	public Account getAccount(String user) {
		return accountRepository.getAccount(user);
	}
	@Override
	@Transactional
	public Account getUsernameBidAccount(int iditem) {
		return  null;
	}
	@Override
	@Transactional
	public boolean getCheckUsernameAccount(String user) {
		if(user==null)
			return false;
		return accountRepository.getCheckUsernameAccount(user);
	}
	@Override
	@Transactional
	public boolean getLogin(String user, String pass) {
		return accountRepository.getLogin(user, pass);
	}
	
}
