package com.qqfall.cloud.instance.controller;

import com.qqfall.cloud.core.common.DataResponse;
import com.qqfall.cloud.instance.controller.dto.InstanceActionDto;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qqfall.cloud.instance.controller.dto.InstanceDto;
import com.qqfall.cloud.instance.service.api.InstanceService;

@RestController
@RequestMapping(path = "/cloud/v1/instance")
public class InstanceController {

  public static Logger log = LoggerFactory.getLogger(InstanceController.class);

  @Autowired
  InstanceService service;

  @RequestMapping(method = RequestMethod.GET)
  public DataResponse<InstanceDto> getAll(
      @RequestParam(value = "name", defaultValue = "World") String name,
      Pageable p) {
    log.info("begin to get all");
    List<InstanceDto> instanceDtos = service.getAll();
    DataResponse dataResponse = new DataResponse();
    dataResponse.setData(instanceDtos);
    return dataResponse;
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public DataResponse<InstanceDto> createInstance(@RequestBody InstanceDto dto) {
    log.info("begin to create instace:{}", dto);
    InstanceDto result = service.addInstance(dto);
    DataResponse<InstanceDto> dataResponse = new DataResponse();
    dataResponse.setData(result);
    return dataResponse;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public DataResponse<InstanceDto> deleteInstanceById(@PathVariable("id") String id) {
    log.info("begin to delete instace id:{}", id);
    service.deleteInstanceId(id);
    DataResponse<InstanceDto> dataResponse = new DataResponse();
    dataResponse.setData(null);
    return dataResponse;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public DataResponse<InstanceDto> updateInstance(@RequestBody InstanceDto dto,
      @PathVariable("id") String id) {
    log.info("begin to create instace:{},id:{}", dto, id);
    dto.setId(id);
    InstanceDto result = service.updateInstance(dto);
    DataResponse<InstanceDto> dataResponse = new DataResponse();
    dataResponse.setData(result);
    return dataResponse;
  }

  @RequestMapping(value = "/node/{nodeId}", method = RequestMethod.PUT)
  public DataResponse<InstanceDto> updateInstanceByNodeId(@RequestBody InstanceDto dto,
      @PathVariable("nodeId") String nodeId) {
    log.info("begin to updateInstanceByNodeId instace:{},id:{}", dto, nodeId);
    InstanceDto result = service.updateInstanceByNodeId(dto, nodeId);
    DataResponse<InstanceDto> dataResponse = new DataResponse();
    dataResponse.setData(result);
    return dataResponse;
  }

  @RequestMapping(value = "/action", method = RequestMethod.POST)
  public InstanceActionDto updateInstanceAction(@RequestBody InstanceActionDto instanceActionDto) {
    service.updateInstanceAction(instanceActionDto);
    instanceActionDto.setResult("sucess");
    return instanceActionDto;
  }

}
