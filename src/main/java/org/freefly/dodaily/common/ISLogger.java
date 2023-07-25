package org.freefly.dodaily.common;

/**
 * Tool for all
 * @usage ISLogger.getInstance().*
 *
 * this tool is to record the logs.
 * it should be a singleton.
 *
 * @author freefly365
 * @date 2023-07-25
 * */
public class ISLogger {

    private static ISLogger isLogger=null;

    private ISLogger(){}

    public static ISLogger getInstance(){
        if(isLogger==null){
            isLogger=new ISLogger();
        }
        return isLogger;
    }
}
