package com.qqfall.cloud.core.exception;

public class ValidatorException extends BaseException {

    private static final long serialVersionUID = -9017848254303278970L;

    public ValidatorException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);

    }

}
