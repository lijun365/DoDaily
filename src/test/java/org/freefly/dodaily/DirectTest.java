package org.freefly.dodaily;

import org.freefly.dodaily.common.ISDate;
import org.freefly.dodaily.log.ISLogger;
import org.freefly.dodaily.log.LogDestinction;

/**
 * test what I want to test
 *
 * @author freefly365
 * @date 2023-07-25
 */
public class DirectTest {

    public static void main(String[] args) {
        // 1.ISDateTest
        //ISDateTest();

        // 2
        //stackTraceElementTest();

        // 3
        ISLoggerTest();
    }

    static void ISDateTest() {
        // getInstance + getDateAsString
        String dateStr = ISDate.getInstance().getDateAsString();
        System.out.println(dateStr);

        // parseDateFromString
        System.out.println(ISDate.getInstance().parseDateFromString(dateStr));
    }

    static void stackTraceElementTest() {
        StackTraceElement[] stcs = Thread.currentThread().getStackTrace();
        for (StackTraceElement stc : stcs) {
            System.out.println(stc);
        }
    }

    static void ISLoggerTest() {
        StackTraceElement ste=Thread.currentThread().getStackTrace()[1];
        ISLogger.getInstance().logDebug("Testing ISLoggerTest.debug",ste);
        ISLogger.getInstance().logInfo("Testing ISLoggerTest.info",ste);
        ISLogger.getInstance().logAbnormal("Testing ISLoggerTest.abnormal",ste);
        ISLogger.getInstance().logError("Testing ISLoggerTest.error",ste);
    }
}
