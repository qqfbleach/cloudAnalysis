package com.qqfall.cloud.instance.dao.api;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qqfall.cloud.instance.dao.entity.InstanceEntity;


public interface InstanceDao extends MongoRepository<InstanceEntity, Long>,InstanceDaoCustom{

	public InstanceEntity findById(String id);
	
	public InstanceEntity findByHostNodeId(String nodeId);
}
