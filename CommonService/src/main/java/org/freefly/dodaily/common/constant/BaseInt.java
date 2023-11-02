package org.freefly.dodaily.common.constant;

public class BaseInt {

    // for UserService, the base int is 10000
    private static int baseUserService = 10000;
        /**
         * Signal base
         * <p>
         * 1000 for insert
         * 2000 for update
         * 3000 for delete
         */
        public static int baseUserServiceInsert = baseUserService + 1000;
        public static int baseUserServiceUpdate = baseUserService + 2000;
        public static int baseUserServiceDelete = baseUserService + 3000;

    // for SugarMark, the base int is 20000
    private static int baseSugarMark = 20000;
        /**
         * Signal base
         * <p>
         * 1000 for insert
         * 2000 for update
         * 3000 for delete
         */
        public static int baseSugarMarkInsert = baseSugarMark + 1000;
        public static int baseSugarMarkUpdate = baseSugarMark + 2000;
        public static int baseSugarMarkDelete = baseSugarMark + 3000;
}
