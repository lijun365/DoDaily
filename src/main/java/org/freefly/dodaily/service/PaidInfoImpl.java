package org.freefly.dodaily.service;

import org.freefly.dodaily.common.SyncIdInfo;
import org.freefly.dodaily.dao.PaidInfoIF;
import org.freefly.dodaily.entity.PaidInfo;
import org.freefly.dodaily.entity.PaidInfoSea;
import org.freefly.dodaily.log.ISLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaidInfoImpl {

    private int baseId = -10;
    @Autowired
    private PaidInfoIF piif;
    @Autowired
    private SyncIdInfo syncIdInfo;

    public PaidInfoImpl(){
        baseId=syncIdInfo.syncPaidInfo();
    }
    public int insert(PaidInfo pi) {
//        int cond=1;
//        while(baseId<0 && cond <=3){
//            baseId=syncIdInfo.syncPaidInfo();
//            ++cond;
//        }
        if(baseId<0){
            ISLogger.getInstance().logError("Generate Id Failed!",Thread.currentThread().getStackTrace()[1]);
            return -1;
        }

        ++baseId;
        pi.setId(baseId);
        Date ct=new Date();
        pi.setCreateTime(ct);
        pi.setUpdateTime(ct);

        return piif.insert(pi);
    }

    public int insertList(PaidInfo[] pis) {
        return 0;
    }

    public PaidInfo[] getList(PaidInfoSea pis) {
        return piif.getList(pis);
    }

    public int update(PaidInfo pi) {
        return 0;
    }

    public int delete(PaidInfo pi) {
        return 0;
    }
}
