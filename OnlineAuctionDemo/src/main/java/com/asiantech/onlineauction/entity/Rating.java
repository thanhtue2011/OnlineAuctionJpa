package com.asiantech.onlineauction.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rating")
public class Rating {
	
	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private Account account;
	
	private int point;
	
	@ManyToOne
	private Account account_rating;

	public Rating(){}
	
	
	public Rating(int id, Account account, int point, Account account_rating) {
		super();
		this.id = id;
		this.account = account;
		this.point = point;
		this.account_rating = account_rating;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Account getAccount_rating() {
		return account_rating;
	}

	public void setAccount_rating(Account account_rating) {
		this.account_rating = account_rating;
	}
	
	
	
	
	
	

}
