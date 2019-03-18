package com.test.dao;


import com.test.pojo.CustomerExt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerExtDao extends JpaRepository<CustomerExt,Long> {
}
