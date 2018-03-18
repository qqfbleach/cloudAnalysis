package com.qqfall.cloud.instance.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<InstanceDto> getAll(@RequestParam(value = "name", defaultValue = "World") String name, Pageable p) {
		log.info("begin to get all");
		return service.getAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public InstanceDto createInstance(@RequestBody InstanceDto dto) {
		log.info("begin to create instace:{}", dto);
		return service.addInstance(dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public InstanceDto updateInstance(@RequestBody InstanceDto dto, @PathVariable("id") String id) {
		log.info("begin to create instace:{},id:{}", dto, id);
		dto.setId(id);
		return service.updateInstance(dto);
	}

	@RequestMapping(value = "/node/{nodeId}", method = RequestMethod.PUT)
	public InstanceDto updateInstanceByNodeId(@RequestBody InstanceDto dto, @PathVariable("nodeId") String nodeId) {
		log.info("begin to updateInstanceByNodeId instace:{},id:{}", dto, nodeId);

		return service.updateInstanceByNodeId(dto, nodeId);
	}

}
