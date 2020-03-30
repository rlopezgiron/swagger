package com.test.swagger.service;

import java.util.List;

import com.test.swagger.model.TestItem;

public interface TestService {
	
	List<TestItem> findAll();

}
