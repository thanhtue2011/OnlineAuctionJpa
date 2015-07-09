package com.asiantech.onlineauction.service;

public interface MailService {
	public static String NAME = "mailService";
	public void sendMail(String from, String to, String subject, String body);

}
