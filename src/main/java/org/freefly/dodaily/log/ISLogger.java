package org.freefly.dodaily.log;

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
    private LogLevel logLevel= LogLevel.Info;
    private LogDestinction logDestinction=LogDestinction.Console;

    private ISLogger(){}
    public void setLogLevel(LogLevel logLevel){
        this.logLevel=logLevel;
    }
    public void setLogDestinction(LogDestinction des){
        this.logDestinction=des;
    }

    public static ISLogger getInstance(){
        if(isLogger==null){
            isLogger=new ISLogger();
        }
        return isLogger;
    }

    public void logDebug(String context, StackTraceElement ste){
        if(LogLevel.Debug.isGte(logLevel)){
            if(LogDestinction.Console==logDestinction){
                logToConsole(context, ste);
            }
            else if(LogDestinction.File==logDestinction){
                logToFile(context, ste);
            }
        }
    }
    public void logInfo(LogDestinction des, String context, StackTraceElement ste){
        if(LogLevel.Info.isGte(logLevel)){
            if(LogDestinction.Console==logDestinction){
                logToConsole(context, ste);
            }
            else if(LogDestinction.File==logDestinction){
                logToFile(context, ste);
            }
        }
    }
    public void logAbnormal(LogDestinction des, String context, StackTraceElement ste){
        if(LogLevel.Abnormal.isGte(logLevel)){
            if(LogDestinction.Console==logDestinction){
                logToConsole(context, ste);
            }
            else if(LogDestinction.File==logDestinction){
                logToFile(context, ste);
            }
        }
    }
    public void logError(LogDestinction des, String context, StackTraceElement ste){
        if(LogLevel.Error.isGte(logLevel)){
            if(LogDestinction.Console==logDestinction){
                logToConsole(context, ste);
            }
            else if(LogDestinction.File==logDestinction){
                logToFile(context, ste);
            }
        }
    }

    private void logToConsole(String context, StackTraceElement ste){

    }
    public void logToFile(String context, StackTraceElement ste){}
}
