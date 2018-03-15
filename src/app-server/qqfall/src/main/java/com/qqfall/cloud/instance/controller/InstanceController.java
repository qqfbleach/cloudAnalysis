package com.qqfall.cloud.instance.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qqfall.cloud.instance.service.api.InstanceService;



@RestController
@RequestMapping(path ="/cloud/v1/instance")
public class InstanceController {
	public static Logger logger = Logger.getLogger(InstanceController.class);

	@Autowired
	InstanceService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<InstanceDto> getAll(@RequestParam(value="name", defaultValue="World") String name,Pageable p) {
		InstanceDto dto = new InstanceDto();
		dto.setDescription("212");
		dto.setName("2r");
//		ResponseEntity<InstanceDto>(HttpStatus.NO_CONTENT);
		
		logger.info("getAll");
        return service.getAll();
    }
	
	@RequestMapping(method=RequestMethod.POST)
	public InstanceDto createInstance(@RequestBody InstanceDto dto) {
		
        return service.addInstance(dto);
    }
	
	
}
