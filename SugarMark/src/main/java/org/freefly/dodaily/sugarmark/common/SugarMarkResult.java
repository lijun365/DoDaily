package org.freefly.dodaily.sugarmark.common;

import lombok.Data;
import org.freefly.dodaily.common.entity.SugarMark;

import java.util.List;

@Data
public class SugarMarkResult {
    private Integer resultStatus;
    private String resultDesc;
    private List<SugarMark> resultList;
    private SugarMarkPage sugarMarkPage;

    private SugarMarkResult(Integer resultStatus, String desc, List<SugarMark> list) {
        this.resultStatus = resultStatus;
        this.resultDesc = desc;
        if (list != null) {
            this.resultList = list;
        } else {
            this.resultList = null;
        }
    }

    public static SugarMarkResult getSuccess(List<SugarMark> resultList) {
        return new SugarMarkResult(200, "OK", resultList);
    }

    public static SugarMarkResult getSuccess(String desc, List<SugarMark> resultList) {
        return new SugarMarkResult(200, desc, resultList);
    }

    public static SugarMarkResult getFail() {
        return new SugarMarkResult(404,"Unknown ERROR",null);

    }

    public static SugarMarkResult getFail(String desc) {
        return new SugarMarkResult(404,desc,null);
    }
}
