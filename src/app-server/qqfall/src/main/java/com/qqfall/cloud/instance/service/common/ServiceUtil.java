package com.qqfall.cloud.instance.service.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.qqfall.cloud.instance.controller.InstanceDto;
import com.qqfall.cloud.instance.dao.entity.InstanceEntity;

public enum ServiceUtil {
	INSTANCE;

	public InstanceEntity dtoToEntity(InstanceDto dto) {
		InstanceEntity entity = new InstanceEntity();
		if (null != dto) {
			entity.setDescription(dto.getDescription());
			entity.setName(dto.getName());
		}
		return entity;
	}

	public List<InstanceEntity> dtosToEntities(List<InstanceDto> dtos) {
		List<InstanceEntity> entities = new ArrayList<>();
		if (!CollectionUtils.isEmpty(dtos)) {
			for (InstanceDto dto : dtos) {
				entities.add(dtoToEntity(dto));
			}
		}
		return entities;
	}

	public InstanceDto entityToDto(InstanceEntity entity) {
		InstanceDto dto = new InstanceDto();
		if (null != entity) {
			dto.setDescription(entity.getDescription());
			dto.setId(entity.getId());
			dto.setName(entity.getName());
		}
		return dto;
	}

	public List<InstanceDto> entitiesToDtos(List<InstanceEntity> entities) {
		List<InstanceDto> dtos = new ArrayList<>();
		if (!CollectionUtils.isEmpty(entities)) {
			for (InstanceEntity entity : entities) {
				InstanceDto dto = new InstanceDto();
				dto.setDescription(entity.getDescription());
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dtos.add(dto);
			}
		}
		return dtos;

	}

}
