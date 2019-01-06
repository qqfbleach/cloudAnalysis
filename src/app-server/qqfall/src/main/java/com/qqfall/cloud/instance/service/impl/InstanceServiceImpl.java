package com.qqfall.cloud.instance.service.impl;

import com.qqfall.cloud.instance.controller.dto.InstanceActionDto;
import com.qqfall.cloud.instance.controller.dto.InstanceDto;
import com.qqfall.cloud.instance.dao.api.InstanceDao;
import com.qqfall.cloud.instance.dao.entity.InstanceEntity;
import com.qqfall.cloud.instance.service.api.InstanceService;
import com.qqfall.cloud.instance.service.common.ServiceUtil;
import com.qqfall.cloud.instance.service.thirdPartApi.DigitalOcean.DigitalOceanApi;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InstanceServiceImpl implements InstanceService {

  public static Logger logger = LoggerFactory.getLogger(InstanceServiceImpl.class);

  @Autowired
  private InstanceDao instanceDao;

  @Autowired
  private DigitalOceanApi digitalOceanApi;

  @Override
  public List<InstanceDto> getAll() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    logger.info("user:{} log in.details:{}", auth.getPrincipal(), auth.getDetails());
    List<InstanceEntity> entities = instanceDao.findAll();

    logger.info("entities:{}", entities);

    return ServiceUtil.INSTANCE.entitiesToDtos(entities);
  }

  @Override
  public InstanceDto addInstance(InstanceDto dto) {
    InstanceEntity entityin = ServiceUtil.INSTANCE.dtoToEntity(dto);
    InstanceEntity result = instanceDao.insert(entityin);
    return ServiceUtil.INSTANCE.entityToDto(result);
  }

  @Override
  public InstanceDto updateInstance(InstanceDto dto) {
    InstanceEntity findResult = instanceDao.findById(dto.getId());

    InstanceEntity entityin = ServiceUtil.INSTANCE.updateInstanceEntityByDto(findResult, dto);

    InstanceEntity saveResult = instanceDao.save(entityin);
    return ServiceUtil.INSTANCE.entityToDto(saveResult);
  }

  @Override
  public InstanceDto updateInstanceByNodeId(InstanceDto dto, String nodeId) {
    InstanceEntity findResult = instanceDao.findByHostNodeId(nodeId);
    InstanceEntity entityin = ServiceUtil.INSTANCE.updateInstanceEntityByDto(findResult, dto);

    InstanceEntity saveResult = instanceDao.save(entityin);
    return ServiceUtil.INSTANCE.entityToDto(saveResult);
  }

  @Override
  public InstanceDto updateInstanceAction(InstanceActionDto instanceActionDto) {
    logger.info("updateInstanceAction begin");
    digitalOceanApi.createDroplet();
    return null;
  }

  @Override
  public void deleteInstanceId(String instanceId) {
    InstanceEntity entity = instanceDao.findById(instanceId);
    instanceDao.delete(entity);
  }

}
