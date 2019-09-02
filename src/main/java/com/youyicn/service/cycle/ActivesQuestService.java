package com.youyicn.service.cycle;

import java.util.List;

import com.youyicn.entity.cycle.ActivesQuest;


public interface ActivesQuestService {
	/**
	 * 查询
	 */
	public List<ActivesQuest> getActivesQuestByActivesId(Integer activesId); 

	/**
	 * 添加问题
	 */
	public void addActivesQuest(ActivesQuest questCon);
	/**
	 * 根据Id删除删除问题
	 */
	public void delActivesQuest(ActivesQuest questId);
	/**
	 * 根据查房编号删除 问题
	 */
	public void delActivesQuestByActivesId(Integer activesId);
	
	/**根据Id获得问题
	 */
	public ActivesQuest getActivesQuestById(Integer questId);
	
	public void updateActivesQuest(ActivesQuest quest);
	
	
}
