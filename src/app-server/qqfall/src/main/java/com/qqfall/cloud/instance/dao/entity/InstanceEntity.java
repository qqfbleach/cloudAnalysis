package com.qqfall.cloud.instance.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "instance")
@Data
public class InstanceEntity {

	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	private String userId;
	
	private String taskId;
	
	private String taskResult;
	
	private String dataSetId;
	
	private HostEntity host;

}
