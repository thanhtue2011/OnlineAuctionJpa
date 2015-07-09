package com.asiantech.onlineauction.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.asiantech.onlineauction.repository.AccountRepositoryCustom;
import com.asiantech.onlineauction.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>, AccountRepositoryCustom{

}