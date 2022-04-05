package org.freefly.dodaily.sugarmark.common;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class ToolSets {

    private static String salt = "sugarmark_";

    public static String passwordEncodeAndDecode(String password){
        return DigestUtils.md5DigestAsHex((salt+password).getBytes(StandardCharsets.UTF_8));
    }
}
