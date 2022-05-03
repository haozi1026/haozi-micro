package com.haozi.common.exception.biz;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/19 2:14 下午
 */
public class AccessDeniedException extends BizException {

    Type type;

    public AccessDeniedException(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String msg = "";
        switch (this.type) {

            case NOT_LOGIN:
                msg = "未登录用户禁止访问";
                break;
            case OVERTIME:
                msg = "登录超时";
                break;
            case UNAUTHORIZED:
                msg = "没有权限访问该地址";
                break;
        }
        return msg;
    }

    public static enum Type {

        /**
         * 未登录
         */
        NOT_LOGIN,
        /**
         * 未授权
         */
        UNAUTHORIZED,
        /**
         * 超时
         */
        OVERTIME
    }
}
