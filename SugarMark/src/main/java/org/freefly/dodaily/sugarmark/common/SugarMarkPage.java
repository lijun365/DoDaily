package org.freefly.dodaily.sugarmark.common;

import lombok.Data;

@Data
public class SugarMarkPage {
    private Integer total;
    private Integer curPage;
    private Integer pageSize;
    private Integer startPos;

    public SugarMarkPage(Integer curPage, Integer pageSize){
        this.curPage = curPage;
        this.pageSize = pageSize;
        startPos = curPage * pageSize - pageSize;
    }

}
