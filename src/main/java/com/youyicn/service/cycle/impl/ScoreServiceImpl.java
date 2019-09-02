package com.youyicn.service.cycle.impl;

import com.youyicn.dao.cycle.CycleScoreMapper;
import com.youyicn.entity.cycle.CycleScore;
import com.youyicn.service.cycle.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title ScoreServiceImpl
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author wangtan
 * @Version 1.0.0
 * @Create 2018/12/14 11:04
 */
@Service
public class ScoreServiceImpl implements IScoreService {

    @Autowired
    private CycleScoreMapper cycleScoreMapper;
    
    /** 
     *@title  
     *@description 添加评分记录
     *@param  [cycleScore]
     *@return  void
     *@author  wangtan
     *@createDate  2018/12/14 11:05
    */
    @Override
    public void addScore(CycleScore cycleScore) {
        cycleScoreMapper.addScore(cycleScore);
    }

    /** 
     *@title  
     *@description
     *@param  []
     *@return  java.util.List<com.youyicn.entity.cycle.CycleScore>
     *@author  wangtan
     *@createDate  2018/12/14 11:06
    */
    @Override
    public List<CycleScore> findAllScoreList() {
        return null;
    }

    /** 
     *@title  
     *@description 通过评分模板Id删除评分记录
     *@param  [scoreId]
     *@return  void
     *@author  wangtan
     *@createDate  2018/12/14 11:07
    */
    @Override
    public void deleteScoreById(Integer scoreId) {

    }

    /** 
     *@title  
     *@description 通过评分记录Id查询评分记录
     *@param  [scoreId]
     *@return  com.youyicn.entity.cycle.CycleScore
     *@author  wangtan
     *@createDate  2018/12/14 11:07
    */
    @Override
    public CycleScore findScoreById(Integer scoreId) {
        return null;
    }

    /**
     *@title
     *@description 修改评分记录
     *@param  [cycleScore]
     *@return  void
     *@author  wangtan
     *@createDate  2018/12/14 11:07
    */
    @Override
    public void editScore(CycleScore cycleScore) {

    }

    /**
     *@title
     *@description 通过被评分人和记录所属科室、基地查询评分记录
     *@param  [cycleScore]
     *@return  void
     *@author  wangtan
     *@createDate  2018/12/14 11:07
     */
    @Override
    public CycleScore findScoreByCondition(String targetId, Integer roomId, Integer baseId) {
        return null;
    }


}
