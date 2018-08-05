package com.qqfall.cloud.instance.service.api;

import com.qqfall.cloud.instance.controller.dto.InstanceActionDto;
import com.qqfall.cloud.instance.controller.dto.InstanceDto;
import java.util.List;

public interface InstanceService {

  public List<InstanceDto> getAll();

  public InstanceDto addInstance(InstanceDto dto);

  public InstanceDto updateInstance(InstanceDto dto);

  public InstanceDto updateInstanceByNodeId(InstanceDto dto, String nodeId);

  public InstanceDto updateInstanceAction(InstanceActionDto instanceActionDto);

  public void deleteInstanceId(String instanceId);
}
