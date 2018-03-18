package com.qqfall.cloud.instance.controller.dto;

import lombok.Data;

@Data
public class InstanceDto {
	
	private String id;
	
	private String name;
	
	private String description;
	
	private String dataSetId;

	private HostDto host;
	
	private String userId;

}
