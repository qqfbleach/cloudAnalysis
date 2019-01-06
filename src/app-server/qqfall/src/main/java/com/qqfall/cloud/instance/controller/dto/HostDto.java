package com.qqfall.cloud.instance.controller.dto;

import lombok.Data;

@Data
public class HostDto {
	
	private int core;

	private int memory;

	private int traffic;
	
	private String process;

	private int status;
	
	private String nodeId;
	
	private String nodeIp;

	private String errCode;

	private String errMsg;
}
