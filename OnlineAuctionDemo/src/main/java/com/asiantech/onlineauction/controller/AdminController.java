package com.asiantech.onlineauction.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asiantech.onlineauction.entity.Account;
import com.asiantech.onlineauction.entity.Category;
import com.asiantech.onlineauction.service.AccountService;
import com.asiantech.onlineauction.service.BidService;
import com.asiantech.onlineauction.service.CategoryService;
import com.asiantech.onlineauction.service.ItemService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
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
	
	@RequestMapping("")
	public String getIndex(){
		return "admin/adminindex";
	}
	@RequestMapping(value = "/adminaccount")
	public ModelAndView getAdminAccount(ModelAndView model,BindingResult bidding){
		 model.setViewName("admin/adminaccount");
	    	List<Account> listaccount = accountService.findAll();
	    	model.addObject("listaccount",listaccount);
		 
		return model;
	}
   
	@RequestMapping(value = "/block",method = RequestMethod.POST)
	public String getListBlockAccount(ModelAndView model,
			@RequestParam(value="id") Integer id,BindingResult bidding){
		if(bidding.hasErrors()){
			return null;
		}
		Account acc=accountService.findById(id);
		if(acc.isEnable()==true){
		   acc.setEnable(false);
		}
		else{
		acc.setEnable(true);}
		accountService.update(acc);
		return "redirect:/admin/adminaccount";
	}
	@RequestMapping(value = "/admincategory")
	public ModelAndView getAdminCategory(ModelAndView model,
			@PageableDefault(value=3,sort = {"title"},direction= Direction.ASC) Pageable pageable){
		Page<Category> listcategory=categoryService.findAll(pageable);
		model.addObject("listcategory",listcategory.getContent());
		
		int total=listcategory.getTotalPages();
		model.addObject("total",total);
		
		model.addObject("limit",listcategory.getSize());
		model.setViewName("admin/admincategory");
	    model.addObject("msg", accountService.getMsg());
	    accountService.setMsg("");
	     
		 return model;
		
	}
	@RequestMapping(value = "/adminaddcategory")
	public ModelAndView getAdminAddCategory(ModelAndView model){
		 model.setViewName("admin/adminaddcategory");
	    
		 return model;
		
	}
	@RequestMapping(value = "/editcategory",method = RequestMethod.POST)
	public ModelAndView getPostEditCategory(ModelAndView model,@RequestParam(value="id") Integer id){
		model.setViewName("admin/admineditcategory");
		 Category category = categoryService.findById(id);
	     model.addObject("category",category);
	     
		 return model;
		
	}
	@RequestMapping(value = "/saveeditcategory",method = RequestMethod.POST)
	public String getEditCategory(ModelAndView model,@Valid @ModelAttribute("category") Category category,
			BindingResult bidding){
		System.out.println("id"+category.getId_category());
	if(bidding.hasErrors()){
		accountService.setMsg("Cap nhat khong thanh cong!");
		return "redirect:/admin/admincategory";
	}
	categoryService.update(category);
	accountService.setMsg("Cap nhat thanh cong! "+category.getId_category());
    return "redirect:/admin/admincategory";
   }
	@RequestMapping(value = "/saveaddcategory",method = RequestMethod.POST)
	public String getAddCategory(ModelAndView model,@Valid Category category,
			BindingResult bidding){
	System.out.println(category.getDescription());
	System.out.println("id add"+category.getId_category());
	if(bidding.hasErrors()){
		accountService.setMsg("Them moi khong thanh cong!");
		return "redirect:/admin/admincategory";
	}

	categoryService.create(category);
	accountService.setMsg("Them moi thanh cong! ");
    return "redirect:/admin/admincategory";
   }
	
}
