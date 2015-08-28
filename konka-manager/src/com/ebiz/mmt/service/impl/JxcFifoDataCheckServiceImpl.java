package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcFifoDataCheckDao;
import com.ebiz.mmt.domain.JxcFifoDataCheck;
import com.ebiz.mmt.service.JxcFifoDataCheckService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-04-14 18:29:54
 */
@Service
public class JxcFifoDataCheckServiceImpl implements JxcFifoDataCheckService {

	@Resource
	private JxcFifoDataCheckDao jxcFifoDataCheckDao;
	

	public Long createJxcFifoDataCheck(JxcFifoDataCheck t) {
		return this.jxcFifoDataCheckDao.insertEntity(t);
	}

	public JxcFifoDataCheck getJxcFifoDataCheck(JxcFifoDataCheck t) {
		return this.jxcFifoDataCheckDao.selectEntity(t);
	}

	public Long getJxcFifoDataCheckCount(JxcFifoDataCheck t) {
		return this.jxcFifoDataCheckDao.selectEntityCount(t);
	}

	public List<JxcFifoDataCheck> getJxcFifoDataCheckList(JxcFifoDataCheck t) {
		return this.jxcFifoDataCheckDao.selectEntityList(t);
	}

	public int modifyJxcFifoDataCheck(JxcFifoDataCheck t) {
		return this.jxcFifoDataCheckDao.updateEntity(t);
	}

	public int removeJxcFifoDataCheck(JxcFifoDataCheck t) {
		return this.jxcFifoDataCheckDao.deleteEntity(t);
	}

	public List<JxcFifoDataCheck> getJxcFifoDataCheckPaginatedList(JxcFifoDataCheck t) {
		return this.jxcFifoDataCheckDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<JxcFifoDataCheck> getJxcFifoDataCheckForStockList(
			JxcFifoDataCheck jxcFifoDataCheck) {
		// TODO Auto-generated method stub
		return this.jxcFifoDataCheckDao.selectJxcFifoDataCheckForStockList(jxcFifoDataCheck);
	}

	@Override
	public int AutoRunUpdateFifoCheckByChannelDataImport() {
		// TODO Auto-generated method stub
	return	this.jxcFifoDataCheckDao.AutoRunUpdateFifoCheckByChannelDataImport();
	}

	
//	处理集采的数据到比对数据表
	@Override
	public int AutoRunUpdateFifoCheckByZlesDataImport() {
		// TODO Auto-generated method stub
		return	this.jxcFifoDataCheckDao.AutoRunUpdateFifoCheckByZlesDataImport();
	}
	

}
