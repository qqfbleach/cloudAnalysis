package com.qqfall.cloud.instance.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqfall.cloud.instance.controller.InstanceDto;
import com.qqfall.cloud.instance.dao.api.InstanceDao;
import com.qqfall.cloud.instance.dao.entity.InstanceEntity;
import com.qqfall.cloud.instance.service.api.InstanceService;
import com.qqfall.cloud.instance.service.common.ServiceUtil;

@Service
public class InstanceServiceImpl implements InstanceService {
	public static Logger logger = Logger.getLogger(InstanceServiceImpl.class);

	@Autowired
	private InstanceDao instanceDao;

	@Override
	public List<InstanceDto> getAll() {
		
		List<InstanceEntity> entities = instanceDao.getAllInstance("55");

		logger.info("entities:{}"+entities);

		return ServiceUtil.INSTANCE.entitiesToDtos(entities);
	}

	@Override
	public InstanceDto addInstance(InstanceDto dto) {
		InstanceEntity entityin = ServiceUtil.INSTANCE.dtoToEntity(dto);
		InstanceEntity entity = instanceDao.insert(entityin);
		return ServiceUtil.INSTANCE.entityToDto(entity);
	}

}
