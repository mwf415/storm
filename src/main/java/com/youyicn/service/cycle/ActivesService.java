package com.youyicn.service.cycle;

import java.util.List;
import java.util.Map;

import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.ActivesSingle;
import com.youyicn.entity.cycle.GroupByEntity;

public interface ActivesService {
	public Actives getById(Integer id);
	public List <Actives> getByCon(Actives actives);
	public void add (Actives actives);
	public void delById(Integer id);
	public void updateIsInById(Integer id);//确认参加
	public List <GroupByEntity> selectCount (Actives actives);
	public Actives getById(int parseInt);
	public List<Actives> getActivesByForm(Actives actives); 
	
	/**
	 * 获取首页的数据，因为其他地方有关联调用，所以使用这个方法
	 * @param status
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Actives> getIndexResult (String startTime,String endTime );
	public void updateFileNum(Integer id,Integer fileNum);//更新文件数
	
	//根据查找某一段时间用户的活动数
	public List<ActivesSingle> getByLoginStartTimeEndTime(Integer status ,Map <String,Object>map);
	public Integer getByLoginStartTimeEndTimeCount(Integer status ,Map <String,Object>map);
	

}
