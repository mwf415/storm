package com.youyicn.service.cycle;

import com.youyicn.entity.cycle.CycleScore;

import java.util.List;

/**
 * @Title
 * @Description 360评分管理服务
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 *
 * @Author wangtan
 * @Version 1.0.0
 * @Create 2018/12/14 10:58
 */
public interface IScoreService {

    //添加评分模板
    void addScore(CycleScore cycleScore);

    //查询所有评分模板
    List<CycleScore> findAllScoreList();

    //通过模板Id删除评分模板
    void deleteScoreById(Integer scoreId);

    //通过模板Id查询评分模板详情
    CycleScore findScoreById(Integer scoreId);

    //通过模板Id修改评分模板
    void editScore(CycleScore cycleScore);

    //通过被评分人和记录所属科室、基地查询评分记录
    CycleScore findScoreByCondition(String targetId, Integer roomId, Integer baseId);
}
