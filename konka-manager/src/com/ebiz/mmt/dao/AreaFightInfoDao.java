package com.ebiz.mmt.dao;

import java.util.HashMap;
import java.util.List;

import com.ebiz.mmt.domain.AreaFightInfo;
import com.ebiz.ssi.dao.EntityDao;


public interface AreaFightInfoDao extends EntityDao<AreaFightInfo> {
	Long selectTotalCount(AreaFightInfo t);
	
	Long selectDetailCount(AreaFightInfo t);

	List<HashMap> selectAreaFightListForMap(AreaFightInfo t);
	
	List<HashMap> selectAreaFightDetailList(AreaFightInfo t);
	
	void autoCountAreaInfo() throws Exception;
}
