package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JsStocks;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
public interface JsStocksService {

	Long createJsStocks(JsStocks t);

	int modifyJsStocks(JsStocks t);

	int removeJsStocks(JsStocks t);

	JsStocks getJsStocks(JsStocks t);

	List<JsStocks> getJsStocksList(JsStocks t);

	Long getJsStocksCount(JsStocks t);

	List<JsStocks> getJsStocksPaginatedList(JsStocks t);
	
	List<JsStocks> getJsStockForTotal(JsStocks t);
	
	List<JsStocks> getJsStocksListForFX(JsStocks t);

}