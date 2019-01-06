package com.qqfall.cloud.instance.dao.entity;

import lombok.Data;

@Data
public class HostEntity {
	
	private int core;

	private int memory;

	private int traffic;

	private String taskId;

	private String taskResult;

	private String nodeIp;

	private String nodeId;

	private String process;

	private int status;

	private String errCode;

	private String errMsg;

}
