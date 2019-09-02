package com.youyicn.dao.cycle;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youyicn.entity.cycle.CyleNote;
import com.youyicn.entity.cycle.CyleNoteExample;

public interface CyleNoteMapper {
    int countByExample(CyleNoteExample example);

    int deleteByExample(CyleNoteExample example);

    int insert(CyleNote record);

    int insertSelective(CyleNote record);

    List<CyleNote> selectByExample(CyleNoteExample example);

    int updateByExampleSelective(@Param("record") CyleNote record, @Param("example") CyleNoteExample example);

    int updateByExample(@Param("record") CyleNote record, @Param("example") CyleNoteExample example);
}