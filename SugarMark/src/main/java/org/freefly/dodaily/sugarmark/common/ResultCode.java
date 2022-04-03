package org.freefly.dodaily.sugarmark.common;

public enum ResultCode {

    INSERT_NULL(0), INSERT_NOTALL(100), INSERT_OK(200), INSERT_FAIL(400),
    UPDATE_NULL(0), UPDATE_OK(200), UPDATE_FAIL(400),
    DELETE_NULL(0), DELETE_NOTALL(100), DELETE_OK(200), DELETE_FAIL(400);

    ResultCode(int i) {
    }
}
