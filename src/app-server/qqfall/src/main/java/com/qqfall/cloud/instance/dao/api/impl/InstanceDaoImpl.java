package com.qqfall.cloud.instance.dao.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.qqfall.cloud.instance.dao.api.InstanceDaoCustom;
import com.qqfall.cloud.instance.dao.entity.InstanceEntity;

public class InstanceDaoImpl implements InstanceDaoCustom{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<InstanceEntity> getAllInstance(String name) {
		
		return mongoTemplate.findAll(InstanceEntity.class);
	}
	
	
}
