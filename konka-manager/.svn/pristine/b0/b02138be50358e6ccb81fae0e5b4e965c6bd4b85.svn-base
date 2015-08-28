package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderMessageProcessDao;
import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.domain.KonkaOrderMessageProcess;
import com.ebiz.mmt.service.KonkaOrderMessageProcessService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-15 14:04:59
 */
@Service
public class KonkaOrderMessageProcessServiceImpl implements KonkaOrderMessageProcessService {

	@Resource
	private KonkaOrderMessageProcessDao konkaOrderMessageProcessDao;
	

	public Long createKonkaOrderMessageProcess(KonkaOrderMessageProcess t) {
		return this.konkaOrderMessageProcessDao.insertEntity(t);
	}

	public KonkaOrderMessageProcess getKonkaOrderMessageProcess(KonkaOrderMessageProcess t) {
		return this.konkaOrderMessageProcessDao.selectEntity(t);
	}

	public Long getKonkaOrderMessageProcessCount(KonkaOrderMessageProcess t) {
		return this.konkaOrderMessageProcessDao.selectEntityCount(t);
	}

	public List<KonkaOrderMessageProcess> getKonkaOrderMessageProcessList(KonkaOrderMessageProcess t) {
		return this.konkaOrderMessageProcessDao.selectEntityList(t);
	}

	public int modifyKonkaOrderMessageProcess(KonkaOrderMessageProcess t) {
		return this.konkaOrderMessageProcessDao.updateEntity(t);
	}

	public int removeKonkaOrderMessageProcess(KonkaOrderMessageProcess t) {
		return this.konkaOrderMessageProcessDao.deleteEntity(t);
	}

	public List<KonkaOrderMessageProcess> getKonkaOrderMessageProcessPaginatedList(KonkaOrderMessageProcess t) {
		return this.konkaOrderMessageProcessDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<Map<String, Object>> getAll_BCB_List(KonkaOrderMessageProcess t) {
		return this.konkaOrderMessageProcessDao.selectAll_BCB_List(t);
	}

}
