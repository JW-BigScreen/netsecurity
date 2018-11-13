package com.uestc.netsecurity.commons.exceptions;

import com.alibaba.druid.wall.violation.ErrorCode;

public class InvalidReqException extends Exception {

    private final String userErrMsg;
    private final ErrorCode errorCode;

    public InvalidReqException(ErrorCode errorCode, String sysErrMsg, String userErrMsg) {
        super(sysErrMsg);
        this.userErrMsg = userErrMsg;
        this.errorCode = errorCode;
    }

    public InvalidReqException(ErrorCode errorCode, String sysErrMsg, String userErrMsg, Throwable cause) {
        super(sysErrMsg, cause);
        this.userErrMsg = userErrMsg;
        this.errorCode = errorCode;
    }

    public InvalidReqException(ErrorCode errorCode, String sysErrMsg) {
        super(sysErrMsg);
        this.userErrMsg = null;
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getUserErrMsg() {
        return userErrMsg;
    }

    public String getSysErrMsg() {
        return getMessage();
    }

}
