package com.youyicn.dao.cycle;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.ActivesSingle;
import com.youyicn.entity.cycle.GroupByEntity;
import com.youyicn.model.ActiveStat;
import com.youyicn.model.RoomStat;

@Service(value="activesMapper")
public interface ActivesMapper {
	public Actives getById(Integer idd);
	public List <Actives> getByCon(Actives actives);
	public void add (Actives actives);
	public void delById(Integer id);
	public void updateIsInById(Integer id);//确认参加
	public List <GroupByEntity> selectCount(Actives actives);
	
	/**
	 * 首页的调用，因为有关联调用，所以从新创建了一个方法
	 */
	public List<Actives> getIndexResult ( @Param(value="searchStart") String searchStart,@Param(value="searchEnd") String searchEnd );
	
	public List<Actives> getActivesByForm(Actives actives);  //是为了月度统计使用
	/**
	 * @Description: 统计科室
	 * @param s 开始时间
	 * @param e 结束时间
	 * @return
	 * List<RoomStat>
	 */
	List<RoomStat> getRoomStat(@Param("startTime") Timestamp s, @Param("endTime") Timestamp e);
	/**
	 * @Description: 统计活动
	 * @param s 开始时间
	 * @param e 结束时间
	 * @return
	 * List<RoomStat>
	 */
	List<ActiveStat> getActiveStat(@Param("startTime") Timestamp s, @Param("endTime") Timestamp e);

	/**
	 * 更新文件数
	 * @param id
	 * @param fileNum
	 */
	public void updateFileNum(@Param(value="id")Integer id,@Param(value="fileNum")Integer fileNum);
	
	
	public List<ActivesSingle> getByLoginStartTimeEndTime(Map<String, Object> map);
	public Integer getByLoginStartTimeEndTimeCount(Map<String, Object> map);

}
