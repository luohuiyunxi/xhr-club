package com.xiaohaoren.auth.common.enums;


import lombok.Data;
import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    NOT_FOUND(404, "未找到"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_SUPPORTED(415, "不支持"),
    NOT_ACCEPTABLE(406, "不可接受"),
    REQUEST_TIMEOUT(408, "请求超时"),
    SERVER_ERROR(500, "服务器错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用");

    private int code;
    private String message;
    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public static ResultCodeEnum getByCode(int code) {
        for (ResultCodeEnum value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
