package com.youyicn.service.cycle;

import com.youyicn.entity.cycle.CycleScoreModel;

import java.util.List;

/**
 * @Title 360互评服务
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author wangtan
 * @Version 1.0.0
 * @Create 2018/11/27 8:44
 */

public interface IOverallScoreService {
    //查询所有评分模板
    List<CycleScoreModel> findAllScoreModelList();

    //添加评分模板
    void addScoreModel(CycleScoreModel scoreModel);

    //通过模板Id删除评分模板
    void deleteScoreModelById(Integer modelId);

    //通过模板Id查询评分模板详情
    CycleScoreModel findScoreModelById(Integer modelId);

    //通过模板Id修改评分模板
    void editScoreModel(CycleScoreModel scoreModel);
}
