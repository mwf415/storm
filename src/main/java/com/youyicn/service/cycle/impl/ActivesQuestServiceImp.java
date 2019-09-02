package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.ActivesQuestMapper;
import com.youyicn.entity.cycle.ActivesQuest;
import com.youyicn.service.cycle.ActivesQuestService;
@Service("questService")
public class ActivesQuestServiceImp implements ActivesQuestService{
	@Resource
	public ActivesQuestMapper activesQuestMapper;
	@Override
	public List<ActivesQuest> getActivesQuestByActivesId(Integer activesId) {
		List<ActivesQuest> questList=activesQuestMapper.getActivesQuestByActivesId(activesId);
		return questList;
	}

	@Override
	public void addActivesQuest(ActivesQuest quest) {
		activesQuestMapper.addActivesQuest(quest);
		
	}

	@Override
	public void delActivesQuest(ActivesQuest questId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delActivesQuestByActivesId(Integer activesId) {
		activesQuestMapper.delActivesQuestByActivesId(activesId);
		
	}
	
	@Override
	public void updateActivesQuest(ActivesQuest quest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ActivesQuest getActivesQuestById(Integer questId) {
		ActivesQuest quest= activesQuestMapper.getActivesQuestById(questId);
		return quest;
	}

}
