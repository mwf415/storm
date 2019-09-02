package com.youyicn.dao.cycle;

import com.youyicn.entity.cycle.CycleScore;
import com.youyicn.entity.cycle.CycleScoreModel;

import java.util.List;

/**
 * <p>
 *  360综合评分
 * </p>
 *
 * @author kaifa
 * @since 2018-11-27
 */
public interface CycleScoreMapper {

    //查询所有评分模板
    List<CycleScore> findAllScoreList();

    //添加评分模板
    void addScore(CycleScore score);

    //通过模板Id删除评分模板
    void deleteScoreById(Integer scoreId);

    //通过模板Id查询评分模板详情
    CycleScoreModel findScoreById(Integer scoreId);

    //通过模板Id修改评分模板
    void editScore(CycleScore score);
}
