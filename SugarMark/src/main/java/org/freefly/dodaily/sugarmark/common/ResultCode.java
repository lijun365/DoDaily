package org.freefly.dodaily.sugarmark.common;

public class ResultCode {

    // Service Sign for MQ
    private static final String name = "SUGARMARK_";

    // For insert
    public static final int INSERT_NULL = 0;
    public static final int INSERT_NOTALL = 100;
    public static final int INSERT_OK = 200;
    public static final int INSERT_NOTSUIT = 300;
    public static final int INSERT_FAIL = 400;
    public static final String S_INSERT_NULL = name+"INSERT_NULL";
    public static final String S_INSERT_NOTALL = name+"INSERT_NOTALL";
    public static final String S_INSERT_OK = name+"INSERT_OK";
    public static final String S_INSERT_NOTSUIT = name + "INSERT_NOTSUIT";
    public static final String S_INSERT_FAIL = name+"INSERT_FAIL";

    // For update
    public static final int UPDATE_NULL = 0;
    public static final int UPDATE_OK = 200;
    public static final int UPDATE_FAIL = 400;
    public static final String S_UPDATE_NULL = name+"UPDATE_NULL";
    public static final String S_UPDATE_OK = name+"UPDATE_OK";
    public static final String S_UPDATE_FAIL = name+"UPDATE_FAIL";

    // For delete
    public static final int DELETE_NULL = 0;
    public static final int DELETE_NOTALL = 100;
    public static final int DELETE_OK = 200;
    public static final int DELETE_FAIL = 400;
    public static final String S_DELETE_NULL = name+"DELETE_NULL";
    public static final String S_DELETE_NOTALL = name+"DELETE_NOTALL";
    public static final String S_DELETE_OK = name+"DELETE_OK";
    public static final String S_DELETE_FAIL = name+"DELETE_FAIL";

}
