package com.asiantech.onlineauction.service.imple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiantech.onlineauction.entity.Item;
import com.asiantech.onlineauction.entity.Item.BidStatus;
import com.asiantech.onlineauction.repository.ItemRepository;
import com.asiantech.onlineauction.service.ItemService;

@Service(ItemService.NAME)
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Resource
	private ItemRepository itemRepository;

	@Override
	@Transactional
	public Item create(Item item) {
		return itemRepository.save(item);
	}

	@Override
	@Transactional
	public Item delete(int id) {
		Item deletedItem = itemRepository.findOne(id);
		itemRepository.delete(deletedItem);
		return deletedItem;
	}

	@Override
	@Transactional
	public List<Item> findAll() {
		
		return itemRepository.findAll();
	}

	@Override
	@Transactional
	public Item update(Item item) {
		Item updatedItem = itemRepository.findOne(item.getId());
		updatedItem.setTitle(item.getTitle());
		updatedItem.setImage(item.getImage());
		updatedItem.setDescription(item.getDescription());
		updatedItem.setBidStartdate(item.getBidStartdate());
		updatedItem.setBidEnddate(item.getBidEnddate());
		updatedItem.setBidMinimum(item.getBidMinimum());
		updatedItem.setStatusbidSell(item.getStatusbidSell());
		updatedItem.setCategory(item.getCategory());
		updatedItem.setAccount(item.getAccount());
		updatedItem.setBidCurrent(item.getBidCurrent());
		updatedItem.setBidIncremenent(item.getBidIncremenent());
		updatedItem.setBidStatus(item.getBidStatus());
		return updatedItem;
	}

	@Override
	@Transactional
	public Item findById(int id) {
		return itemRepository.findOne(id);
	}

	@Override
	@Transactional
	public List<Item> getListAccountItem(int id) {
		return itemRepository.getIdAccount(id);
	}

	@Override
	public List<Item> getListAll() {
		return itemRepository.getListAll();
	}

	@Override
	public Page<Item> getListAllCategory(Pageable pageable,String title) {
		if(title==null)
			return null;
		return itemRepository.getListAllCategory(pageable,title);
	}

	@Override
	public Page<Item> findAll(Pageable pageable, String title) {
		 String likeExpression = "%" + title + "%";
		return itemRepository.findAll(pageable,likeExpression);
	}

	@Override
	public List<Item> getStatusSucess(){
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date datecurrent= new Date();
		String current= dt.format(datecurrent).toString();
		List<Item> listitem= itemRepository.findAll();
	    for(Item items: listitem){
	    	String bidendate=dt.format(items.getBidEnddate()).toString();
	    	if(current.equals(bidendate))
	    		    items.setBidStatus(BidStatus.I);
	    	Item updatelistitem = itemRepository.findOne(items.getId());
	    	updatelistitem.setBidStatus(items.getBidStatus());
	    }
	    return listitem;
	}

	@Override
	public Page<Item> findAllChildrenCategory(Pageable pageable,
			String catetitle, String title) {
		if(catetitle==null && title==null)
		    return null;
		String likeExpression = "%" + title + "%";
		return itemRepository.findAllChildrenCategory(pageable, catetitle, likeExpression);
	}



}
