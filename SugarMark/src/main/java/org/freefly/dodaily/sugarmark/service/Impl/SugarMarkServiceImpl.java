package org.freefly.dodaily.sugarmark.service.Impl;

import org.freefly.dodaily.sugarmark.common.SugarMarkPage;
import org.freefly.dodaily.common.entity.SugarMark;
import org.freefly.dodaily.sugarmark.mapper.SugarMarkMapper;
import org.freefly.dodaily.sugarmark.service.ISugarMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SugarMarkServiceImpl implements ISugarMarkService {

    @Autowired
    private SugarMarkMapper sugarMarkMapper;

    @Override
    public List<SugarMark> findResultByPage(SugarMark vo, SugarMarkPage page, int userId) {
        List<SugarMark> list = sugarMarkMapper.findResultByPage(vo, page, userId);
        return list;
    }

    @Override
    public int getTotalNumber(SugarMark vo, int userId) {
        return sugarMarkMapper.getTotalNumber(vo, userId);
    }

    @Override
    public int createSugarMark(List<SugarMark> voList, int userId) {
        return sugarMarkMapper.createSugarMark(voList, userId);
    }

    @Override
    public int updateSugarMark(SugarMark vo) {
        return sugarMarkMapper.updateSugarMark(vo);
    }

    @Override
    public int deleteSugarMark(List<Integer> ids) {
        return sugarMarkMapper.deleteSugarMark(ids);
    }
}
