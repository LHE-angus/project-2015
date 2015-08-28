package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JStocksDao;
import com.ebiz.mmt.domain.JStocks;
import com.ebiz.mmt.service.JStocksService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JStocksServiceImpl implements JStocksService {

	@Resource
	private JStocksDao jStocksDao;
	

	public Long createJStocks(JStocks t) {
		return this.jStocksDao.insertEntity(t);
	}

	public JStocks getJStocks(JStocks t) {
		return this.jStocksDao.selectEntity(t);
	}

	public Long getJStocksCount(JStocks t) {
		return this.jStocksDao.selectEntityCount(t);
	}

	public List<JStocks> getJStocksList(JStocks t) {
		return this.jStocksDao.selectEntityList(t);
	}

	public int modifyJStocks(JStocks t) {
		return this.jStocksDao.updateEntity(t);
	}

	public int removeJStocks(JStocks t) {
		return this.jStocksDao.deleteEntity(t);
	}

	public List<JStocks> getJStocksPaginatedList(JStocks t) {
		return this.jStocksDao.selectEntityPaginatedList(t);
	}

}
