package com.asiantech.onlineauction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asiantech.onlineauction.entity.Account;
import com.asiantech.onlineauction.entity.Bid;
import com.asiantech.onlineauction.entity.Item;
import com.asiantech.onlineauction.service.AccountService;
import com.asiantech.onlineauction.service.BidService;
import com.asiantech.onlineauction.service.CategoryService;
import com.asiantech.onlineauction.service.ItemService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	@Qualifier(AccountService.NAME)
	private AccountService accountService;
    
    @Autowired
   	@Qualifier(ItemService.NAME)
   	private ItemService itemService;
    
    @Autowired
	@Qualifier(CategoryService.NAME)
	private CategoryService categoryService;
    
    @Autowired
   	@Qualifier(BidService.NAME)
   	private BidService bidService;
	
	@RequestMapping(value = "/{category}")
	public ModelAndView getIndexCategory(@PathVariable("category") String category,ModelAndView model,
			@PageableDefault(value=1,sort = {"bidStartdate"},direction= Direction.ASC) Pageable pageable){
		Page<Item> listitem= itemService.getListAllCategory(pageable,category);
		model.addObject("listitem",listitem.getContent());
		
		int total=listitem.getTotalPages();
		model.addObject("total",total);
		
		List<Bid> listbid = bidService.getUserAccount();
		model.addObject("listbid",listbid);
		
		model.setViewName("itembycategory");
		model.addObject("category",category);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("user",auth.getName());

		return model;
	}
	
	@RequestMapping(value = "/{category}/search")
	public ModelAndView getSearchCategory(@PathVariable("category") String category,
			ModelAndView model,@RequestParam(value="keyword", required=false) String title,
			@PageableDefault(value=1,sort = {"title"},direction= Direction.ASC) Pageable pageable){
		Page<Item> listitem= itemService.findAllChildrenCategory(pageable,category, title);
		model.addObject("listitem",listitem.getContent());
		
		int total = listitem.getTotalPages();
		model.addObject("total",total);
		
		List<Bid> listbid = bidService.getUserAccount();
		model.addObject("listbid",listbid);
		
		model.setViewName("searchchildren");
		model.addObject("category",category);
		
		model.addObject("keyword",title);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("user",auth.getName());

		return model;
	}
	
	//bid dau gia
    @RequestMapping(value="/{category}/bidauction",method = RequestMethod.POST)
    public String getBid(HttpServletRequest request,ModelAndView model,
    		@Valid Bid bid, BindingResult bidding,@RequestParam("id") Integer id,
    		@RequestParam("bidminimum") Long bidminimum,@RequestParam("bidincremenent") Long bidincremenent,
    		@PathVariable("category") String category){
    	long bidCurrent=0;
    	if(SessionUser()=="anonymousUser"){
    		accountService.setMsg("Ban can dang nhap de dau gia!");
			return "redirect:/login";
			}
    	bidCurrent=bidminimum+bidincremenent;
    	Item item = itemService.findById(id);
    		    	
    	item.setBidCurrent(bidCurrent);
    	itemService.update(item);
    	
    	Account account= accountService.getAccount(SessionUser());
    	bid.setAccount(account);
    	bid.setBidCurrent(bidCurrent);
    	bid.setItem(item);
    	bidService.create(bid);
    	
    	return "redirect:/category/"+category;
    }
    
    private String SessionUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
   

}
