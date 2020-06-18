package com.thoughtworks.capacity.gtb.mvc.errorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    USER_EXISTS(400, "用户已存在"),
    USER_NOT_EXIST_ERROR(400, "用户名或密码错误");

    private int code;
    private String msg;
}
