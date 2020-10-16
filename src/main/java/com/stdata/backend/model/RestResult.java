package com.stdata.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RestResult {

    private static final String SUCCESS_STATUS = "ok";

    private String status;

    private Object data;

    public RestResult() {
        //no-args constructor.
    }

    public RestResult(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_STATUS.equals(this.status);
    }

    public static RestResult success() {
        return new RestResult(SUCCESS_STATUS, null);
    }

    public static RestResult success(Object data) {
        return new RestResult(SUCCESS_STATUS, data);
    }

    public static RestResult fail(String reason) {
        return new RestResult(reason, null);
    }
}
