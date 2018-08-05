package com.qqfall.cloud.core.common;

import lombok.Data;

@Data
public class ExceptionResponse {
	private String errorCode;
    private String errorMessage;
}
