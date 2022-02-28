package com.block.constant;

import lombok.Data;

@Data
public class ResponseResult <T> implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private Boolean success;
    private String msg;
    private String desc;
    private T data;
}
