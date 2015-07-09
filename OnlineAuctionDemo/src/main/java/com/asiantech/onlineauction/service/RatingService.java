package com.asiantech.onlineauction.service;

import java.util.List;

import com.asiantech.onlineauction.entity.Rating;

public interface RatingService {
	
	public static String NAME = "ratingService";
	public List<Rating> findAll();
	public Rating findById(int id);
	public Rating create(Rating bid);
	public Rating delete(int id);
	public Rating update(Rating bid);
	int getRatingAverage(int id);

}
