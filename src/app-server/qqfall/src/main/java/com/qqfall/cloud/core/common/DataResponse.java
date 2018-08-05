package com.qqfall.cloud.core.common;

import lombok.Data;

@Data
public class DataResponse<T> {
  private String errMessage;
  private String errCode;
  private T data;
}
