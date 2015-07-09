package com.asiantech.onlineauction.service.imple;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiantech.onlineauction.entity.Bid;
import com.asiantech.onlineauction.repository.BidRepository;
import com.asiantech.onlineauction.service.BidService;
@Service(BidService.NAME)
@Transactional
public class BidServiceImpl implements BidService {
    
	@Resource
	private BidRepository bidRepository;
	
	@Override
	@Transactional
	public List<Bid> findAll() {
		return bidRepository.findAll();
	}

	@Override
	@Transactional
	public Bid findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Bid create(Bid bid) {
		bid.setId(0);
		return bidRepository.save(bid);
	}

	@Override
	@Transactional
	public Bid delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Bid update(Bid bid) {
		Bid bidupdate = bidRepository.findOne(bid.getId()) ;
		bidupdate.setAccount(bid.getAccount());
		bidupdate.setItem(bid.getItem());
		bidupdate.setBidCurrent(bid.getBidCurrent());
		bidupdate.setBidCurrentdate(bid.getBidCurrentdate());
		bidupdate.setStatusBid(bid.getStatusBid());
		return bidupdate;
	}

	@Override
	@Transactional
	public List<Bid> getUserAccount() {
		return bidRepository.getUserAccount();
	}

	@Override
	@Transactional
	public List<Bid> getUserAccountSameIdtem() {
		return bidRepository.getUserAccountSameIdtem();
	}

	@Override
	public List<Integer> getCountBid() {
		return bidRepository.getCountBid();
	}

	@Override
	public List<Bid> getUserAccountBid(int id) {
		return bidRepository.getUserAccountBid(id);
	}

	@Override
	@Transactional
	public List<Bid> getAccountBidSucess(){
		return bidRepository.getAccountBidSucess();
			
	}

	@Override
	public List<Bid> getTopWin() {
		return bidRepository.getTopWin();
	}

}
