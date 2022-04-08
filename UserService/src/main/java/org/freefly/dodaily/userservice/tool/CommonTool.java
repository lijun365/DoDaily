package org.freefly.dodaily.userservice.tool;

import org.freefly.dodaily.userservice.entity.User;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class CommonTool {

    private static String MD5_SECRET="DODAILY_MD5_";

    public static boolean userCheck(User user){
        if(user == null){
            return false;
        }
        if(user.getName() ==null || user.getNickname()==null||user.getPassword()==null){
            return false;
        }

        return true;
    }

    public static String md5Encrypt(String password){
        return DigestUtils.md5DigestAsHex((MD5_SECRET+password).getBytes(StandardCharsets.UTF_8));
    }
}
