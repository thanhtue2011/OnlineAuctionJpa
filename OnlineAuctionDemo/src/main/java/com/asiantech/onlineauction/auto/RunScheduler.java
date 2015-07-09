package com.asiantech.onlineauction.auto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asiantech.onlineauction.entity.Bid;
import com.asiantech.onlineauction.service.BidService;
import com.asiantech.onlineauction.service.ItemService;
import com.asiantech.onlineauction.service.MailService;

@Component
public class RunScheduler {
	
	@Autowired
   	private ItemService itemService;
	
	@Autowired
   	private BidService bidService;
	
	 @Autowired
	 private MailService mailService;
	
	public void run(){
		//getStatusBid();
		System.out.println(new Date());
		//getSendMail();
	}
	public void getStatusBid(){
		itemService.getStatusSucess();
	}
	
	public void getSendMail(){
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date datecurrent= new Date();
		String current= dt.format(datecurrent).toString();
		List<Bid> listbid= bidService.getAccountBidSucess();
		for(Bid bids: listbid){
			String bidendate=dt.format(bids.getItem().getBidEnddate()).toString();
	    	if(current.equals(bidendate)){
	    		String from=bids.getItem().getAccount().getEmail();
	    		String to =bids.getAccount().getEmail();
	    		String subject= "Thong bao dau gia thanh cong mat hang "+bids.getItem().getTitle()+"co id "+bids.getItem().getId();
	    		String body= "Mot lan nua xin chuc mung ban da dau gia thanh cong! "
	    				+"Gia dau gia :"+bids.getItem().getBidCurrent()
	    				+ " Xin vui long lien he dia chi de nhan hang "
	    				+ bids.getItem().getAccount().getAddress()
	    				+" Xin chan thanh cam on!";
	    		System.out.println(subject);
	    		System.out.println(body);
	    		System.out.println("from"+bids.getItem().getAccount().getEmail());
	    		System.out.println("to" +bids.getAccount().getEmail());
	    		mailService.sendMail(from,to,subject,body);
	    	}
	    }
	}

}
