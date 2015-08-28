package com.ebiz.mmt.service;

import java.util.List;
import java.util.Map;

import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.domain.KonkaOrderMessageProcess;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-15 14:04:59
 */
public interface KonkaOrderMessageProcessService {

	Long createKonkaOrderMessageProcess(KonkaOrderMessageProcess t);

	int modifyKonkaOrderMessageProcess(KonkaOrderMessageProcess t);

	int removeKonkaOrderMessageProcess(KonkaOrderMessageProcess t);

	KonkaOrderMessageProcess getKonkaOrderMessageProcess(KonkaOrderMessageProcess t);

	List<KonkaOrderMessageProcess> getKonkaOrderMessageProcessList(KonkaOrderMessageProcess t);

	Long getKonkaOrderMessageProcessCount(KonkaOrderMessageProcess t);

	List<KonkaOrderMessageProcess> getKonkaOrderMessageProcessPaginatedList(KonkaOrderMessageProcess t);

    List<Map<String, Object>> getAll_BCB_List(KonkaOrderMessageProcess t);
}