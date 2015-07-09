package com.asiantech.onlineauction.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="bid")
public class Bid {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
    private Item item;
	
	@ManyToOne
	private Account account;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	private Date bidCurrentdate=new Date();
	
	@NotNull
	private long bidCurrent;
	
	private String statusBid;

	public Bid(){}
	
	public Bid(int id, Item item, Account account, Date bidCurrentdate,
			long bidCurrent, String statusBid) {
		super();
		this.id = id;
		this.item = item;
		this.account = account;
		this.bidCurrentdate = bidCurrentdate;
		this.bidCurrent = bidCurrent;
		this.statusBid = statusBid;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	public Date getBidCurrentdate() {
		return bidCurrentdate;
	}


	public void setBidCurrentdate(Date bidCurrentdate) {
		this.bidCurrentdate = bidCurrentdate;
	}


	public long getBidCurrent() {
		return bidCurrent;
	}


	public void setBidCurrent(long bidCurrent) {
		this.bidCurrent = bidCurrent;
	}


	public String getStatusBid() {
		return statusBid;
	}


	public void setStatusBid(String statusBid) {
		this.statusBid = statusBid;
	}
	
	
	
}
