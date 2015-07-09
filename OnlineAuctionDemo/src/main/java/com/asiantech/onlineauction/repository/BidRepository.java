package com.asiantech.onlineauction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asiantech.onlineauction.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{
	@Query("select u from Bid u")
	List<Bid> getUserAccount();
	@Query("select u.item.id, u.account.username,u.bidCurrent from Bid u")
	List<Bid> getUserAccountSameIdtem();
	@Query("select count(u) as count from Bid u group by u.item.id")
	List<Integer> getCountBid();
	@Query("select u from Bid u where u.item.id = :id")
	List<Bid> getUserAccountBid(@Param("id") int id);
	@Query("select u from Bid u where u.item.bidCurrent = u.bidCurrent")
	List<Bid> getAccountBidSucess();
	@Query("select u from Bid u where u.item.bidCurrent = u.bidCurrent and u.item.bidStatus = 'I'")
	List<Bid> getTopWin();

}
