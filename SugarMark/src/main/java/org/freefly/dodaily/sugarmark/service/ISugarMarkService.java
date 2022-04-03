package org.freefly.dodaily.sugarmark.service;

import org.apache.ibatis.annotations.Param;
import org.freefly.dodaily.sugarmark.common.SugarMarkPage;
import org.freefly.dodaily.sugarmark.entity.SugarMark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISugarMarkService {

    /**
     *
     * @param vo
     * @param page
     */
    public List<SugarMark> findResultByPage(@Param("vo")SugarMark vo, @Param("page") SugarMarkPage page);

    /**
     * This get total number is not for all datas, it is just for the all datas follow vo
     * @param vo
     * @return int
     */
    public int getTotalNumber(@Param("vo")SugarMark vo);

    /**
     *
     * @param voList
     * @return
     */
    public int createSugarMark(List<SugarMark> voList);

    /**
     *
     * @param vo
     * @return
     */
    public int updateSugarMark(SugarMark vo);

    /**
     *
     * @param ids
     * @return
     */
    public int deleteSugarMark(List<Integer> ids);
}