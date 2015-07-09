package com.asiantech.onlineauction.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="item")
public class Item {
	public enum BidStatus{A,I}
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
    private Category category;
	
	@NotNull
	@Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters long.")
	private String title;
	
	private String image;
	
	@NotNull
	@Size(min = 10, max = 4000, message = "Description must be between 10 and 4000 characters long.")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private BidStatus bidStatus=BidStatus.A;
	
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	private Date bidStartdate;
	
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	private Date bidEnddate;
	
	private long bidIncremenent=10000;
	
	@NotNull
	private long bidMinimum;
	
	private long bidCurrent;
	
	@NotNull
	private String statusbidSell;
	
	@ManyToOne
    private Account account;
	public Item(){}
	
	public Item(int id, Category category, String title, String image,
			String description, BidStatus bidStatus, Date bidStartdate,
			Date bidEnddate, long bidIncremenent, long bidMinimum,
			long bidCurrent, String statusbidSell,
			Account account) {
		super();
		this.id = id;
		this.category = category;
		this.title = title;
		this.image = image;
		this.description = description;
		this.bidStatus = bidStatus;
		this.bidStartdate = bidStartdate;
		this.bidEnddate = bidEnddate;
		this.bidIncremenent = bidIncremenent;
		this.bidMinimum = bidMinimum;
		this.bidCurrent = bidCurrent;
		this.statusbidSell = statusbidSell;
		this.account = account;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BidStatus getBidStatus() {
		return bidStatus;
	}
	public void setBidStatus(BidStatus bidStatus) {
		this.bidStatus = bidStatus;
	}
	public Date getBidStartdate() {
		return bidStartdate;
	}
	public void setBidStartdate(Date bidStartdate) {
		this.bidStartdate = bidStartdate;
	}
	public Date getBidEnddate() {
		return bidEnddate;
	}
	public void setBidEnddate(Date bidEnddate) {
		this.bidEnddate = bidEnddate;
	}
	public long getBidIncremenent() {
		return bidIncremenent;
	}
	public void setBidIncremenent(long bidIncremenent) {
		this.bidIncremenent = bidIncremenent;
	}
	public long getBidMinimum() {
		return bidMinimum;
	}
	public void setBidMinimum(long bidMinimum) {
		this.bidMinimum = bidMinimum;
	}
	public long getBidCurrent() {
		return bidCurrent;
	}
	public void setBidCurrent(long bidCurrent) {
		this.bidCurrent = bidCurrent;
	}
	public String getStatusbidSell() {
		return statusbidSell;
	}
	public void setStatusbidSell(String statusbidSell) {
		this.statusbidSell = statusbidSell;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	

}
