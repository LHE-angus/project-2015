package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JxcFifoDataCheck;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-04-14 18:29:54
 */
public interface JxcFifoDataCheckService {

	Long createJxcFifoDataCheck(JxcFifoDataCheck t);

	int modifyJxcFifoDataCheck(JxcFifoDataCheck t);

	int removeJxcFifoDataCheck(JxcFifoDataCheck t);

	JxcFifoDataCheck getJxcFifoDataCheck(JxcFifoDataCheck t);

	List<JxcFifoDataCheck> getJxcFifoDataCheckList(JxcFifoDataCheck t);

	Long getJxcFifoDataCheckCount(JxcFifoDataCheck t);

	List<JxcFifoDataCheck> getJxcFifoDataCheckPaginatedList(JxcFifoDataCheck t);

	List<JxcFifoDataCheck> getJxcFifoDataCheckForStockList(
			JxcFifoDataCheck jxcFifoDataCheck);

//	处理地采的数据到核对表
	int AutoRunUpdateFifoCheckByChannelDataImport();
	
//	处理集采的数据到核对表
	int AutoRunUpdateFifoCheckByZlesDataImport();

}