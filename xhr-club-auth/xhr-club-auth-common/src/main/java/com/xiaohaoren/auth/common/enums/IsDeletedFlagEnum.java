package com.xiaohaoren.auth.common.enums;

import lombok.Getter;

@Getter
public enum IsDeletedFlagEnum {
    NOT_DELETED(0, "未删除"),
    DELETED(1, "已删除");
    private int code;
    private String message;
    IsDeletedFlagEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public static IsDeletedFlagEnum getByCode(int code) {
        for (IsDeletedFlagEnum value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
