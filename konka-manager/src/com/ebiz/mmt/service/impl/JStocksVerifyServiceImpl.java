package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JStocksVerifyDao;
import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.mmt.service.JStocksVerifyService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JStocksVerifyServiceImpl implements JStocksVerifyService {

	@Resource
	private JStocksVerifyDao jStocksVerifyDao;

	public Long createJStocksVerify(JStocksVerify t) {
		return this.jStocksVerifyDao.insertEntity(t);
	}

	public JStocksVerify getJStocksVerify(JStocksVerify t) {
		return this.jStocksVerifyDao.selectEntity(t);
	}

	public Long getJStocksVerifyCount(JStocksVerify t) {
		return this.jStocksVerifyDao.selectEntityCount(t);
	}

	public List<JStocksVerify> getJStocksVerifyList(JStocksVerify t) {
		return this.jStocksVerifyDao.selectEntityList(t);
	}

	public int modifyJStocksVerify(JStocksVerify t) {
		return this.jStocksVerifyDao.updateEntity(t);
	}

	public int removeJStocksVerify(JStocksVerify t) {
		return this.jStocksVerifyDao.deleteEntity(t);
	}

	public List<JStocksVerify> getJStocksVerifyPaginatedList(JStocksVerify t) {
		return this.jStocksVerifyDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-15
	 */
	public JStocksVerify getJStocksVerifyForLastedVerify(JStocksVerify t) {
		return this.jStocksVerifyDao.selectJStocksVerifyForLastedVerify(t);
	}

	@Override
	public Long getJStocksVerifyForInventoryCount(JStocksVerify t) {
		return this.jStocksVerifyDao.selectJStocksVerifyForInventoryCount(t);
	}

	@Override
	public List<JStocksVerify> getJStocksVerifyForInventoryList(JStocksVerify t) {
		return this.jStocksVerifyDao.selectJStocksVerifyForInventoryList(t);
	}

	@Override
	public List<JStocksVerify> getJStocksVerifyForInventoryPaginatedList(JStocksVerify t) {
		return this.jStocksVerifyDao.selectJStocksVerifyForInventoryPaginatedList(t);
	}

	@Override
	public List<JStocksVerify> getJStocksVerifyForInventoryFormList(JStocksVerify t) {
		return this.jStocksVerifyDao.selectJStocksVerifyForInventoryFormList(t);
	}

	@Override
	public int AutoRunUpdateStatementForInsertJStocksVerify() {
		return this.jStocksVerifyDao.AutoRunUpdateStatementForInsertJStocksVerify();
	}

	@Override
	public List<JStocksVerify> getJStocksVerifyWithStoreAndGoodsNamePaginatedList(JStocksVerify t) {
		return this.jStocksVerifyDao.selectJStocksVerifyWithStoreAndGoodsNamePaginatedList(t);
	}

	@Override
	public long getJStocksVerifyForManagerCount(JStocksVerify entity) {
		return jStocksVerifyDao.selectJStocksVerifyForManagerCount(entity);
	}

	@Override
	public List<JStocksVerify> getJStocksVerifyForManagerPaginatedList(JStocksVerify entity) {
		return this.jStocksVerifyDao.selectJStocksVerifyForManagerPaginatedList(entity);
	}
}
