package org.freefly.dodaily.userservice.tool;

import org.freefly.dodaily.userservice.entity.User;

public class CommonTool {

    public static boolean userCheck(User user){
        if(user == null){
            return false;
        }
        if(user.getName() ==null || user.getNickname()==null||user.getPassword()==null){
            return false;
        }

        return true;
    }
}
