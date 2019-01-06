package com.qqfall.cloud.instance.controller.dto;

import lombok.Data;

@Data
public class InstanceActionDto {

  private String instanceId;

  private InstanceAction action;

  private String result;
}
