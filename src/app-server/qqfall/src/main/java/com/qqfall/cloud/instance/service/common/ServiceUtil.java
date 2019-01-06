package com.qqfall.cloud.instance.service.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.qqfall.cloud.instance.controller.dto.HostDto;
import com.qqfall.cloud.instance.controller.dto.InstanceDto;
import com.qqfall.cloud.instance.dao.entity.HostEntity;
import com.qqfall.cloud.instance.dao.entity.InstanceEntity;

public enum ServiceUtil {
	INSTANCE;

	public InstanceEntity dtoToEntity(InstanceDto dto) {
		if (null == dto) {
			return null;
		}
		InstanceEntity entity = new InstanceEntity();
		entity.setId(dto.getId());
		entity.setDescription(dto.getDescription());
		entity.setName(dto.getName());
		entity.setDataSetId(dto.getDataSetId());
		entity.setHost(hostDtoTohostEntity(dto.getHost()));
		return entity;
	}

	public InstanceEntity updateInstanceEntityByDto(InstanceEntity instanceEntity, InstanceDto dto) {
		if (null == dto) {
			return instanceEntity;
		}
		if (null == instanceEntity) {
			return null;
		}
		if (null != dto.getId()) {
			instanceEntity.setId(dto.getId());
		}
		if (null != dto.getDescription()) {
			instanceEntity.setDescription(dto.getDescription());
		}
		if (null != dto.getName()) {
			instanceEntity.setName(dto.getName());
		}
		if (null != dto.getDataSetId()) {
			instanceEntity.setDataSetId(dto.getDataSetId());
		}
		if (null != dto.getHost()) {
			instanceEntity.setHost(updateHostEntityByDto(instanceEntity.getHost(), dto.getHost()));
		}

		return instanceEntity;
	}

	private HostEntity updateHostEntityByDto(HostEntity hostEntity, HostDto hostDto) {
		if (null == hostDto) {
			return hostEntity;
		}

		if (null == hostEntity) {
			hostEntity = new HostEntity();
		}
		if (0 != hostDto.getCore()) {
			hostEntity.setCore(hostDto.getCore());
		}
		if (0 != hostDto.getMemory()) {
			hostEntity.setMemory(hostDto.getMemory());
		}
		if (0 != hostDto.getStatus()) {
			hostEntity.setStatus(hostDto.getStatus());
		}
		if (0 != hostDto.getTraffic()) {
			hostEntity.setTraffic(hostDto.getTraffic());
		}
		if (null != hostDto.getErrCode()) {
			hostEntity.setErrCode(hostDto.getErrCode());
		}
		if (null != hostDto.getErrMsg()) {
			hostEntity.setErrMsg(hostDto.getErrMsg());
		}
		if (null != hostDto.getProcess()) {
			hostEntity.setProcess(hostDto.getProcess());
		}
		if (null != hostDto.getNodeIp()) {
			hostEntity.setNodeIp(hostDto.getNodeIp());
		}
		if (null != hostDto.getNodeId()) {
			hostEntity.setNodeId(hostDto.getNodeId());
		}
		return hostEntity;
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
		if (null == entity) {
			return null;
		}
		InstanceDto dto = new InstanceDto();
		dto.setDescription(entity.getDescription());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDataSetId(entity.getDataSetId());
		dto.setHost(hostEntityTohostDto(entity.getHost()));
		dto.setUserId(entity.getUserId());
		return dto;
	}

	public List<InstanceDto> entitiesToDtos(List<InstanceEntity> entities) {
		List<InstanceDto> dtos = new ArrayList<>();
		if (!CollectionUtils.isEmpty(entities)) {
			for (InstanceEntity entity : entities) {
				dtos.add(entityToDto(entity));
			}
		}
		return dtos;

	}

	private HostEntity hostDtoTohostEntity(HostDto hostDto) {
		if (null == hostDto) {
			return null;
		}
		HostEntity hostEntity = new HostEntity();
		hostEntity.setCore(hostDto.getCore());
		hostEntity.setErrCode(hostDto.getErrCode());
		hostEntity.setErrMsg(hostDto.getErrMsg());
		hostEntity.setMemory(hostDto.getMemory());
		hostEntity.setProcess(hostDto.getProcess());
		hostEntity.setStatus(hostDto.getStatus());
		hostEntity.setTraffic(hostDto.getTraffic());
		return hostEntity;
	}

	private HostDto hostEntityTohostDto(HostEntity hostEntity) {
		if (null == hostEntity) {
			return null;
		}
		HostDto hostDto = new HostDto();
		hostDto.setCore(hostEntity.getCore());
		hostDto.setErrCode(hostEntity.getErrCode());
		hostDto.setErrMsg(hostEntity.getErrMsg());
		hostDto.setMemory(hostEntity.getMemory());
		hostDto.setProcess(hostEntity.getProcess());
		hostDto.setStatus(hostEntity.getStatus());
		hostDto.setTraffic(hostEntity.getTraffic());
		hostDto.setNodeIp(hostEntity.getNodeIp());
		hostDto.setNodeId(hostEntity.getNodeId());
		return hostDto;
	}

}
