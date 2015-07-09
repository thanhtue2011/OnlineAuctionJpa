package com.asiantech.onlineauction.service;

import java.util.List;

import com.asiantech.onlineauction.entity.Bid;

public interface BidService {
	public static String NAME = "bidService";
	public List<Bid> findAll();
	public Bid findById(int id);
	public Bid create(Bid bid);
	public Bid delete(int id);
	public Bid update(Bid bid);
	public List<Bid> getUserAccount();
	public List<Bid> getUserAccountSameIdtem();
	public List<Integer> getCountBid();
	List<Bid> getUserAccountBid(int id);
	List<Bid> getAccountBidSucess();
	List<Bid> getTopWin();


}
