package com.asiantech.onlineauction.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
/*import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.asiantech.onlineauction.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	   @Autowired
	   @Qualifier(MailService.NAME)
	   private MailService mailService;
	     
	   @RequestMapping(value="/sendmail")
	   public ModelAndView getPageMail(ModelAndView model){
		   model.setViewName("sendmail");
		   
		   return model;
	   }
	   
	    @RequestMapping(value="/sendmail",method = RequestMethod.POST)
	    public String SendEmail(HttpServletRequest request) {
	        // takes input from e-mail form
	    	String fromAddress = request.getParameter("from");
	        String recipientAddress = request.getParameter("to");
	        String subject = request.getParameter("subject");
	        String message = request.getParameter("message");
	         
	       mailService.sendMail(fromAddress,recipientAddress, subject,message);	         
	        // forwards to the view named "Result"
	        return "resultmail";
	    }

}
