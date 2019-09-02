package com.youyicn.service.cycle;

import java.util.List;
import java.util.Map;

import com.youyicn.entity.cycle.CyleNote;

public interface CyleNoteService {
	
	public List<CyleNote> getCyleNoteByArrTurnID(Integer arrTurnId); //
	
	
	public void add(CyleNote cyleNote);
	

}
