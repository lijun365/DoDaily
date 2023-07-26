package org.freefly.dodaily.log;

public enum LogLevel {
    Debug(1), Info(3),Abnormal(5),Error(10);

    private int value;
    LogLevel(int val){
        this.value=val;
    }

    public boolean isGte(LogLevel v){
        return this.value >= v.value;
    }
}
