package com.asiantech.onlineauction.service.imple;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiantech.onlineauction.entity.Rating;
import com.asiantech.onlineauction.repository.RatingRepository;
import com.asiantech.onlineauction.service.RatingService;
@Service(RatingService.NAME)
@Transactional
public class RatingServiceImpl implements RatingService {
    
	@Resource
	private RatingRepository ratingRepository;
	
	@Override
	public List<Rating> findAll() {
		
		return ratingRepository.findAll();
	}

	@Override
	public Rating findById(int id) {
		if(id==0)
		    return null;
		else
			if(id<0)
				return null;
		    return ratingRepository.findOne(id);
	}

	@Override
	public Rating create(Rating bid) {
		if(bid==null)
		   return null;
		return ratingRepository.save(bid);
	}

	@Override
	public Rating delete(int id) {
		return null;
	}

	@Override
	public Rating update(Rating bid) {
		return null;
	}

	@Override
	public int getRatingAverage(int id) {
	   if(id<=0)
		   return 0;
	   return ratingRepository.getRatingAverage(id);
	}

}
