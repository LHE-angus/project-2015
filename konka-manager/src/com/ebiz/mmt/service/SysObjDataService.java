package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.SysObjData;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-01-16 11:06:13
 */
public interface SysObjDataService {

	Long createSysObjData(SysObjData t);

	int modifySysObjData(SysObjData t);

	int removeSysObjData(SysObjData t);

	SysObjData getSysObjData(SysObjData t);

	List<SysObjData> getSysObjDataList(SysObjData t);

	Long getSysObjDataCount(SysObjData t);

	List<SysObjData> getSysObjDataPaginatedList(SysObjData t);

}