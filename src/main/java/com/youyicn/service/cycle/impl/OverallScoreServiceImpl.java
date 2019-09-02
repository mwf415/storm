package com.youyicn.service.cycle.impl;

import com.youyicn.dao.cycle.CycleScoreModelMapper;
import com.youyicn.entity.cycle.CycleScoreModel;
import com.youyicn.service.cycle.IOverallScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title IOverallScoreServiceImpl
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author wangtan
 * @Version 1.0.0
 * @Create 2018/11/27 11:27
 */
@Service
public class OverallScoreServiceImpl implements IOverallScoreService {

    @Autowired
    private CycleScoreModelMapper cycleScoreModelMapper;

    /**
     * @param []
     * @return java.util.List<com.youyicn.dao.entities.CycleScoreModel>
     * @title
     * @description 查询所有评分模板
     * @author wangtan
     * @createDate 2018/11/27 11:30
     */
    @Override
    public List<CycleScoreModel> findAllScoreModelList() {
        List<CycleScoreModel> allScoreModelList = cycleScoreModelMapper.findAllScoreModelList();
        return allScoreModelList;
    }

    /**
     * @param []
     * @return void
     * @title
     * @description 添加评分模板
     * @author wangtan
     * @createDate 2018/11/27 11:30
     */
    @Override
    public void addScoreModel(CycleScoreModel scoreModel) {
        cycleScoreModelMapper.addScoreModel(scoreModel);
    }

    /**
     * @param [modelId]
     * @return void
     * @title
     * @description 通过模板Id删除评分模板
     * @author wangtan
     * @createDate 2018/11/27 11:30
     */
    @Override
    public void deleteScoreModelById(Integer modelId) {
        cycleScoreModelMapper.deleteScoreModelById(modelId);
    }

    /**
     * @param [modelId]
     * @return com.youyicn.dao.entities.CycleScoreModel
     * @title
     * @description //通过模板Id查询评分模板详情
     * @author wangtan
     * @createDate 2018/11/27 11:29
     */
    @Override
    public CycleScoreModel findScoreModelById(Integer modelId) {
        if(modelId == null)
            return null;
        return cycleScoreModelMapper.findScoreModelById(modelId);
    }

    /**
     * @param []
     * @return void
     * @title
     * @description 通过模板Id修改评分模板
     * @author wangtan
     * @createDate 2018/11/27 11:29
     */
    @Override
    public void editScoreModel(CycleScoreModel scoreModel) {
        cycleScoreModelMapper.editScoreModel(scoreModel);
    }
}
