package com.qqfall.cloud.instance.dao.api;

import java.util.List;

import com.qqfall.cloud.instance.dao.entity.InstanceEntity;

public interface InstanceDaoCustom {
	
	public List<InstanceEntity> getAllInstance(String name) ;

}
