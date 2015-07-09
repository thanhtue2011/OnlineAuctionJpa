package com.asiantech.onlineauction.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asiantech.onlineauction.entity.Rating;

public interface RatingRepository  extends JpaRepository<Rating, Integer>{
	
	@Query("select sum(u.point)/count(*) from Rating u where u.account.id_account = :id")
	int getRatingAverage(@Param("id") int id);
	

}
