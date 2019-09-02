package com.youyicn.dao.cycle;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youyicn.entity.cycle.ActivesQuest;

public interface ActivesQuestMapper {
	/**
	 * 查询
	 */
	public List<ActivesQuest> getActivesQuestByActivesId(@Param(value="activesId")Integer activesId); 
	/**
	 * 添加问题
	 */
	public void addActivesQuest(ActivesQuest aaa);
	/**
	 * 删除问题
	 */
	public void delActivesQuest(ActivesQuest questId);
	/**
	 * 根据查房编号删除问题
	 */
	public void delActivesQuestByActivesId(@Param(value="activesId") Integer activesId);
	
	/**
	 * 更新查房老师提出的的问题
	 */
	public void updateActivesQuest(ActivesQuest quest);
	
	/**根据Id获得问题
	 */
	public ActivesQuest getActivesQuestById(Integer questId);
}
