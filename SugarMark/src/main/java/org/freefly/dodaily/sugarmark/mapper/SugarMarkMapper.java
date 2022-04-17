package org.freefly.dodaily.sugarmark.mapper;

import org.apache.ibatis.annotations.Param;
import org.freefly.dodaily.sugarmark.common.SugarMarkPage;
import org.freefly.dodaily.common.entity.SugarMark;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SugarMarkMapper {

    /**
     * @param vo
     * @param page
     */
    public List<SugarMark> findResultByPage(@Param("vo") SugarMark vo, @Param("page") SugarMarkPage page, @Param("userId") int userId);

    /**
     * This get total number is not for all datas, it is just for the all datas follow vo
     *
     * @param vo
     * @return int
     */
    public int getTotalNumber(@Param("vo") SugarMark vo, @Param("userId") int userId);

    /**
     * @param voList
     * @return
     */
    public int createSugarMark(@Param("list") List<SugarMark> voList,@Param("userId") int userId);

    /**
     * @param vo
     * @return
     */
    public int updateSugarMark(@Param("vo") SugarMark vo);

    /**
     * @param ids
     * @return
     */
    public int deleteSugarMark(List<Integer> ids);
}
