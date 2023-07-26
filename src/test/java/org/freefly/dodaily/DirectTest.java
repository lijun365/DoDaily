package org.freefly.dodaily;

import org.freefly.dodaily.common.ISDate;

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
        stackTraceElementTest();
    }

    static void ISDateTest() {
        // getInstance + getDateAsString
        String dateStr = ISDate.getInstance().getDateAsString();
        System.out.println(dateStr);

        // parseDateFromString
        System.out.println(ISDate.getInstance().parseDateFromString(dateStr));
    }

    static void stackTraceElementTest(){
        StackTraceElement[] stcs=Thread.currentThread().getStackTrace();
        for (StackTraceElement stc : stcs) {
            System.out.println(stc);
        }
    }
}
