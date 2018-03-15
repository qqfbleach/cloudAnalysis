package com.qqfall.cloud.instance.service.api;

import java.util.List;

import com.qqfall.cloud.instance.controller.InstanceDto;

public interface InstanceService {

	public List<InstanceDto> getAll();

	public InstanceDto addInstance(InstanceDto dto);

}
