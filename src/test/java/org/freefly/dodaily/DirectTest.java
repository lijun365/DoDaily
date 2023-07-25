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
        ISDateTest();
    }

    static void ISDateTest() {
        // getInstance + getDateAsString
        String dateStr = ISDate.getInstance().getDateAsString();
        System.out.println(dateStr);

        // parseDateFromString
        System.out.println(ISDate.getInstance().parseDateFromString(dateStr));
    }
}
