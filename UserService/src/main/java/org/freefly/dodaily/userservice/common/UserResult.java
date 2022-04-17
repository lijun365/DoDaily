package org.freefly.dodaily.userservice.common;

import lombok.Data;

@Data
public class UserResult {

    /**
     * For resultStatue
     * 0 user is null
     * 200 success
     */
    private int resultStatue;
    private String resultDesc;

    private UserResult(int resultStatue, String resultDesc) {
        this.resultStatue = resultStatue;
        this.resultDesc = resultDesc;
    }

    public static UserResult success(int resultStatue, String resultDesc) {
        return new UserResult(resultStatue, resultDesc);
    }

    public static UserResult fail(int resultStatue, String resultDesc) {
        return new UserResult(resultStatue, resultDesc);
    }
}
