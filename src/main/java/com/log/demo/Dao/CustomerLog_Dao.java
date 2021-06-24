package com.log.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.demo.model.CustomerLog_Model;

public interface CustomerLog_Dao extends JpaRepository<CustomerLog_Model, String> {
	List<CustomerLog_Model> findByDate(String date);
}
