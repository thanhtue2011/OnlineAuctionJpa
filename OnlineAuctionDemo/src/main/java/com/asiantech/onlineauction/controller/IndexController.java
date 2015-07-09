package com.asiantech.onlineauction.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.asiantech.onlineauction.entity.Account;
import com.asiantech.onlineauction.entity.Bid;
import com.asiantech.onlineauction.entity.Category;
import com.asiantech.onlineauction.entity.Item;
import com.asiantech.onlineauction.entity.Item.BidStatus;
import com.asiantech.onlineauction.entity.Rating;
import com.asiantech.onlineauction.service.AccountService;
import com.asiantech.onlineauction.service.BidService;
import com.asiantech.onlineauction.service.CategoryService;
import com.asiantech.onlineauction.service.ItemService;
import com.asiantech.onlineauction.service.RatingService;

@Controller
public class IndexController {
	
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
    
    @Autowired
	@Qualifier(RatingService.NAME)
	private RatingService ratingService;
    
    @RequestMapping(value={"","/","/index","/index/"})
	public ModelAndView getIndex(ModelAndView model){
		return getPageIndex(model);
		
	}
    
    @RequestMapping(value = "/{user}/rating/{id}/{stars}")
    public String getRating(ModelAndView model,@PathVariable("user") String user,
    		@PathVariable("id") Integer id,@PathVariable("stars") Integer stars){
     if(id==null)
    	 return null;
     Item item = itemService.findById(id);
     Account account = accountService.findById(item.getAccount().getId_account());
     
     Rating rate = new Rating();
     
     Account userpoint= accountService.getAccount(user);
     rate.setAccount_rating(userpoint);
     rate.setAccount(account);
     rate.setPoint(stars);
     ratingService.create(rate);
     
     account.setRating(ratingService.getRatingAverage(account.getId_account()));
     accountService.update(account);
 	 
 	return "redirect:/chitiet?iditem="+id;
 }
    
	private ModelAndView getPageIndex(ModelAndView model) {
		model.setViewName("index");
		
		List<Category> listcategory = categoryService.findAll();
		model.addObject("listcategory", listcategory);
		
		List<Bid> listbid = bidService.getUserAccount();
		model.addObject("listbid",listbid);
		
		List<Item> listitem = itemService.getListAll();
		model.addObject("listitem",listitem);
				
		getUserSession(model);
		
		return model;
	}
	private String SessionUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

   @RequestMapping(value = "/login")
   public ModelAndView getLogin(ModelAndView model){
	 getUserSession(model);
	 model.setViewName("login");
	return model;
}

	//Ban hang truc tiep
	@RequestMapping(value = "/sellredirect")
	public ModelAndView getSellRedirect(ModelAndView model,HttpServletRequest request){
 	    getUserSession(model);
 	  
		model.setViewName("muahang");
		
		List<Category> listcategory = categoryService.findAll();
		model.addObject("listcategory", listcategory);
		
		Date current= new Date();
		model.addObject("current",current);
		
		List<Item> listitem = itemService.findAll();
		model.addObject("listitem",listitem);
				
		if(SessionUser()=="anonymousUser")
			return model;
	    getUserSession(model);
	    
		return model;
	
	}
	@RequestMapping(value = "/savesellredirect", method = RequestMethod.POST)
	public String getSaveMuahang(HttpServletRequest request,ModelAndView model,@Valid Bid bid, BindingResult bidding,@RequestParam("id") Integer id,@RequestParam("bidminimum") Long bidminimum,@RequestParam("bidincremenent") Long bidincremenent){
			 long bidCurrent=0;
	    	if(SessionUser()=="anonymousUser"){
	    		accountService.setMsg("Ban can dang nhap de dau gia!");
				return "redirect:/login";
				}
	    	bidCurrent=bidminimum;
	    	Item item = itemService.findById(id);
	    	item.setBidCurrent(bidCurrent);
	    	item.setBidStatus(BidStatus.I);
	    	itemService.update(item);
	    	Account account= accountService.getAccount(request.getSession().getAttribute("saccount").toString());
	    	bid.setAccount(account);
	    	bid.setBidCurrent(bidCurrent);
	    	bid.setItem(item);
	    	bidService.create(bid);
		return "redirect:/sellredirect";
	}
	
	@RequestMapping(value = "/chitietsell")
	public ModelAndView getChiTietSell(HttpServletRequest request,ModelAndView model){
 	    getUserSession(model);
 	  
		int id = Integer.parseInt(request.getParameter("iditem"));
		Item item = itemService.findById(id);
		model.addObject("item",item);
				
		model.setViewName("chitietsell");
		if(SessionUser()=="anonymousUser")
			return model;
		return model;
	}
	

	//Dang ky tai khoan
	@RequestMapping(value = "/register")
	public ModelAndView getRegister(ModelAndView model){
 	    getUserSession(model);
 		model.setViewName("register");
		return model;
	}
	@RequestMapping(value = "/chitiet")
	public ModelAndView getChiTiet(HttpServletRequest request,ModelAndView model){
 	    getUserSession(model);
 	     	  
		int id = Integer.parseInt(request.getParameter("iditem"));
		Item item = itemService.findById(id);
		model.addObject("item",item);
		
		List<Bid> listbid = bidService.getUserAccountBid(id);
		model.addObject("listbid",listbid);
		
		model.setViewName("chitiet");
		
		if(SessionUser()=="anonymousUser")
			return model;
		return model;
	}
	@RequestMapping(value = "/banhangdaugia")
public ModelAndView getBanhangDaugia(ModelAndView model){
		model.setViewName("banhangdaugia");
 	    getUserSession(model);
 	    
		List<Category> listcategory = categoryService.findAll();
		model.addObject("listcategory", listcategory);
		
		Item item = new Item();
	    model.addObject("item", item);
        return model;
		
	}
	@RequestMapping(value = "/demnguoc")
	public String getDemnguoc(){
		return "demnguoc";
	}
	@RequestMapping(value = "/registersucess", method = RequestMethod.POST)
	public String saveAccount(@Valid Account account,BindingResult bidding,ModelAndView model) {
		if(bidding.hasErrors()){
			return null;
		}
	     accountService.create(account);
	     accountService.setMsg("Dang ky thanh cong vui long dang nhap de tiep tuc!");
	     return "redirect:/login";
	    	
	 }
	/*@RequestMapping(value = "/check_login", method = RequestMethod.POST)
	public String Login(@RequestParam(value="username",required=false) String user,@RequestParam(value="password",required=false) String pass,HttpServletRequest request,ModelAndView model) {
	       if(accountService.getLogin(user, pass)==true){
	    	     Account account= accountService.getAccount(user);
	             model.addObject("account", account.getUsername());
	             System.out.println("username "+account.getUsername());
	             return "redirect:/index";
	          }
	       model.setViewName("login");
	       accountService.setMsg("Login not sucess!");
           model.addObject("msg", accountService.getMsg());
	       return "redirect:/login";
	     
	}*/
	    @RequestMapping(value="/upload")
	    public String getUpload(){
			return "upload";
		}
	    @RequestMapping(value = "/banhang", method = RequestMethod.POST)
		public String getBanhang(ModelMap model,@Valid @ModelAttribute("item") Item item,BindingResult bidding,
				@RequestParam(value="file") MultipartFile file,HttpServletRequest request){
	    	String name="";
	    	if(SessionUser()=="anonymousUser"){
	    		accountService.setMsg("Ban can dang nhap de ban hang!");
			    return "redirect:/login";
	    	}
	        Account account= accountService.getAccount(SessionUser());
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                name="/home/thanhtue/"+file.getOriginalFilename();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(name)));
	                stream.write(bytes);
	                item.setImage(file.getOriginalFilename());
	                item.setAccount(account);
	                itemService.create(item);
	                stream.close();
	            	
	            } catch (Exception e) {
	            	e.printStackTrace();	            }
	        } else {
	        	System.out.println("loi");
	            return null;
	            
	        }
	        return "redirect:/index";
			
		}
	    //bid dau gia
	    @RequestMapping(value="/bidauction",method = RequestMethod.POST)
	    public String getBid(HttpServletRequest request,ModelAndView model,
	    		@Valid Bid bid, BindingResult bidding,@RequestParam("id") Integer id,
	    		@RequestParam("bidminimum") Long bidminimum,@RequestParam("bidincremenent") Long bidincremenent){
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
	    	
	    	return "redirect:/index";
	    }
	    //Account cap nhat hang ban hoac dau gia
	    @RequestMapping(value="/capnhathang")
	    public ModelAndView getCapnhathang(ModelAndView model,HttpServletRequest request){
			   	    	   
	    	   Account account= accountService.getAccount(SessionUser());
	    	   getUserSession(model);
	    	   
	    	   model.setViewName("capnhathang");
	    	   Date datecurrent=new Date();
	    	   model.addObject("datecurrent",datecurrent);
	    	   
	           List<Item> listitem = itemService.getListAccountItem(account.getId_account());
	           if(listitem==null){
	              return	listItemFail(model);
	             }
			   return listItemSuccess(model, listitem);
	    }
		private ModelAndView listItemSuccess(ModelAndView model, List<Item> listitem) {
			model.addObject("listitem",listitem);
			model.addObject("msg", accountService.getMsg());
			accountService.setMsg("");
			return model;
		}
		private ModelAndView listItemFail(ModelAndView model) {
			accountService.setMsg("Ban chua tao danh sach ban hang!");
			model.addObject("msg", accountService.getMsg());
			return model;
		}
	    @RequestMapping(value="/edit",method = RequestMethod.POST)
	    public ModelAndView getEditBid(ModelAndView model,HttpServletRequest request,@RequestParam("id") Integer id){
	    	
	    	List<Category> listcategory = categoryService.findAll();
			model.addObject("listcategory", listcategory);
			
	        Item item = itemService.findById(id);
			model.addObject("item",item);
			
	    	model.setViewName("edit");
	    	return model;
	    }
	    @RequestMapping(value="/saveedit",method = RequestMethod.POST)
	    public String getSaveEdit(HttpServletRequest request,@Valid @ModelAttribute("item") Item item,
	    		@RequestParam(value="filename") String filename,@RequestParam(value="file") MultipartFile file,
	    		BindingResult bidding){
	                Account account= accountService.getAccount(SessionUser());	   			 
	                String name="";
	                if(item.getBidCurrent()!=0){
	                	
						if(!file.isEmpty()){
						try {
						byte[] bytes = file.getBytes();
						name="/home/thanhtue/"+file.getOriginalFilename();
		                BufferedOutputStream stream =
		                        new BufferedOutputStream(new FileOutputStream(new File(name)));
		                stream.write(bytes);
						item.setImage(file.getOriginalFilename());
		                stream.close();
		                }
		                
					     catch (IOException e) {
						    e.printStackTrace();
					     }
						}
						else{
							item.setImage(filename);
						
						}
	                item.setAccount(account);
	                itemService.update(item);
		    		accountService.setMsg("Cap nhat thanh cong Id: " +item.getId());
	                }
		    		accountService.setMsg("Hang dang dau gia khong the cap nhat id: " +item.getId());
		    		return "redirect:/capnhathang";
	        }
	    //method delete
	    @RequestMapping(value="/delete",method=RequestMethod.POST)
	    public String getDetete(@Valid Item item,BindingResult bidding, @RequestParam("id") Integer id){
	    	if(item.getBidCurrent()!=0){ 
	    	  itemService.delete(id);
	    	  accountService.setMsg("Xoa thanh cong Item"+id+" !");
	    	 }
    		 accountService.setMsg("Hang dang dau gia khong the xoa id: " +item.getId());
	    	 return "redirect:/capnhathang";
	    }
	    //cap nhat thong tin khach hang
	    @RequestMapping(value="/thongtin")
	    public ModelAndView getThongtin(ModelAndView model, HttpServletRequest request,BindingResult bidding){
	    	 Account saccount= accountService.getAccount(SessionUser());
	    	 getUserSession(model);
	    	 			 
			 Account account = accountService.findById(saccount.getId_account());
			 
			 model.addObject("account",account);
			 model.setViewName("thongtin");
			 
			 model.addObject("msg", accountService.getMsg());
 		    accountService.setMsg("");
	    	return model;
	    }
	    @RequestMapping(value="/savethongtin",method=RequestMethod.POST)
	    public String getSaveThongtin(@Valid @ModelAttribute("account") Account account,BindingResult bidding){
	    	accountService.update(account);
	    	accountService.setMsg("Cap nhat thanh cong!");
	    	return "redirect:/thongtin";
	    }
	    //doi mat khau
	    @RequestMapping(value="/doimatkhau")
	    public ModelAndView getDoiMatKhau(ModelAndView model, HttpServletRequest request,BindingResult bidding){	    	
	    	Account saccount= accountService.getAccount(SessionUser());
	    	 getUserSession(model);
			 Account account = accountService.findById(saccount.getId_account());
			 model.addObject("account",account);
			 
			 model.setViewName("doimatkhau");
	    	return model;
	    }
		private ModelAndView getUserSession(ModelAndView model) {
			model.addObject("user",SessionUser());
			return model;
		}
	    
	    @RequestMapping(value="/savedoimatkhau",method=RequestMethod.POST)
	    public String getSaveDoiMatKhau(@Valid @ModelAttribute("account") Account account,BindingResult bidding){
	    	accountService.update(account);
	    	System.out.println("password "+ account.getPassword());
	    	accountService.setMsg("Thay doi mat khau thanh cong!");
	    	return "redirect:/thongtin";
	    }
	    
	    @RequestMapping(value="/search")
	    public ModelAndView getSearch(ModelAndView model, @RequestParam(value="keyword", required=false) String title,
				@PageableDefault(value=1,sort = {"title"},direction= Direction.ASC) Pageable pageable){
	    	 getUserSession(model);
	    	 	    	
			Page<Item> listitem=itemService.findAll(pageable,title);
			model.addObject("listitem",listitem.getContent());
			
			int total=listitem.getTotalPages();
			model.addObject("total",total);
			
			model.addObject("keyword",title);
			
			model.setViewName("search");
			model.addObject("msg", accountService.getMsg());
		    accountService.setMsg("Danh sach tim kiem voi ten "+title);
	 	    return model;
	 }
	    
	    //top win
	    @RequestMapping(value = "/topwin")
	    public ModelAndView getTopWin(ModelAndView model){
	 	 getUserSession(model);
	 	 model.setViewName("topwin");
	 	 List<Bid> listbidwin = bidService.getTopWin();
	 	 model.addObject("topwin",listbidwin);
	 	
	 	return model;
	 }

}
