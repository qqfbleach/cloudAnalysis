package com.qqfall.cloud.instance.dao.api;

import com.qqfall.cloud.instance.dao.entity.InstanceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface InstanceDao extends MongoRepository<InstanceEntity, Long>,InstanceDaoCustom{

	public InstanceEntity findById(String id);
	
	public InstanceEntity findByHostNodeId(String nodeId);
}
