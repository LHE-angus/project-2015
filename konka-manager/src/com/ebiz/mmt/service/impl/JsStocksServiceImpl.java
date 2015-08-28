package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JsStocksDao;
import com.ebiz.mmt.domain.JsStocks;
import com.ebiz.mmt.service.JsStocksService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
@Service
public class JsStocksServiceImpl implements JsStocksService {

	@Resource
	private JsStocksDao jsStocksDao;

	public Long createJsStocks(JsStocks t) {
		return this.jsStocksDao.insertEntity(t);
	}

	public JsStocks getJsStocks(JsStocks t) {
		return this.jsStocksDao.selectEntity(t);
	}

	public Long getJsStocksCount(JsStocks t) {
		return this.jsStocksDao.selectEntityCount(t);
	}

	public List<JsStocks> getJsStocksList(JsStocks t) {
		return this.jsStocksDao.selectEntityList(t);
	}

	public int modifyJsStocks(JsStocks t) {
		return this.jsStocksDao.updateEntity(t);
	}

	public int removeJsStocks(JsStocks t) {
		return this.jsStocksDao.deleteEntity(t);
	}

	public List<JsStocks> getJsStocksPaginatedList(JsStocks t) {
		return this.jsStocksDao.selectEntityPaginatedList(t);
	}

	public List<JsStocks> getJsStockForTotal(JsStocks t) {
		return this.jsStocksDao.selectJsStockForTotal(t);
	}

	public List<JsStocks> getJsStocksListForFX(JsStocks t) {
		return this.jsStocksDao.selectJsStocksListForFX(t);
	}

}
