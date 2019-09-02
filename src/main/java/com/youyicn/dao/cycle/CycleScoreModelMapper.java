package com.youyicn.dao.cycle;

import com.youyicn.entity.cycle.CycleScoreModel;

import java.util.List;

/**
 * <p>
 *  360综合评分模板
 * </p>
 *
 * @author kaifa
 * @since 2018-11-27
 */
public interface CycleScoreModelMapper{
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
