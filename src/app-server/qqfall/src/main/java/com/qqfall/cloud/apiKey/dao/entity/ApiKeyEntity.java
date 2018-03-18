package com.qqfall.cloud.apiKey.dao.entity;

import lombok.Data;

@Data
public class ApiKeyEntity {

	private String id;
	
	private String userId;
	
	private String secretKey;
	
	private HostType type;
}
