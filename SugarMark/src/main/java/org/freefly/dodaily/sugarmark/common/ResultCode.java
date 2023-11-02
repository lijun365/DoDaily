package org.freefly.dodaily.sugarmark.common;

import org.freefly.dodaily.common.constant.BaseInt;

public class ResultCode {

    private static int baseInsert = BaseInt.baseSugarMarkInsert;
    private static int baseUpdate = BaseInt.baseSugarMarkUpdate;
    private static int baseDelete = BaseInt.baseSugarMarkDelete;

    // For insert
    public static final int INSERT_NULL = 0;
    public static final int INSERT_NOTALL = 100;
    public static final int INSERT_OK = 200;
    public static final int INSERT_NOTSUIT = 300;
    public static final int INSERT_FAIL = 400;
    public static final int S_INSERT_NULL = baseInsert + INSERT_NULL;
    public static final int S_INSERT_NOTALL = baseInsert + INSERT_NOTALL;
    public static final int S_INSERT_OK = baseInsert + INSERT_OK;
    public static final int S_INSERT_NOTSUIT = baseInsert + INSERT_NOTSUIT;
    public static final int S_INSERT_FAIL = baseInsert + INSERT_FAIL;

    // For update
    public static final int UPDATE_NULL = 0;
    public static final int UPDATE_OK = 200;
    public static final int UPDATE_FAIL = 400;
    public static final int S_UPDATE_NULL = baseUpdate + UPDATE_NULL;
    public static final int S_UPDATE_OK = baseUpdate + UPDATE_OK;
    public static final int S_UPDATE_FAIL = baseUpdate + UPDATE_FAIL;

    // For delete
    public static final int DELETE_NULL = 0;
    public static final int DELETE_NOTALL = 100;
    public static final int DELETE_OK = 200;
    public static final int DELETE_FAIL = 400;
    public static final int S_DELETE_NULL = baseDelete + DELETE_NULL;
    public static final int S_DELETE_NOTALL = baseDelete + DELETE_NOTALL;
    public static final int S_DELETE_OK = baseDelete + DELETE_OK;
    public static final int S_DELETE_FAIL = baseDelete + DELETE_FAIL;

}
