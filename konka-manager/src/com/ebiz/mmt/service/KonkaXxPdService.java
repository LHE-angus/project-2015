package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaXxPd;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-01-09 09:19:48
 */
public interface KonkaXxPdService {

	Long createKonkaXxPd(KonkaXxPd t);

	int modifyKonkaXxPd(KonkaXxPd t);

	int removeKonkaXxPd(KonkaXxPd t);

	KonkaXxPd getKonkaXxPd(KonkaXxPd t);

	List<KonkaXxPd> getKonkaXxPdList(KonkaXxPd t);

	Long getKonkaXxPdCount(KonkaXxPd t);

	List<KonkaXxPd> getKonkaXxPdPaginatedList(KonkaXxPd t);

	/**
	 * @author Li,Yuan
	 * @version 2012-01-09
	 */
	Long getKonkaXxPdCountForDemo(KonkaXxPd t);

	/**
	 * @author Li,Yuan
	 * @version 2012-01-09
	 */
	List<KonkaXxPd> getKonkaXxPdPaginatedListForDemo(KonkaXxPd t);

	/**
	 * @author Li,Yuan
	 * @version 2012-01-09
	 */
	List<KonkaXxPd> getKonkaXxPdToExcelList(KonkaXxPd t);

	/**
	 * @author hu,hao
	 * @version 2012-1-9
	 */
	List<KonkaXxPd> getKonkaXxWithUsersPdPaginatedList(KonkaXxPd t);

	/**
	 * @author hu,hao
	 * @version 2012-1-9
	 */
	Long getKonkaXxPdWithUserCount(KonkaXxPd t);

	/**
	 * @author hu,hao
	 * @version 2012-3-2
	 */
	void modifyKonkaXxPdForHistory(KonkaXxPd t);

	/**
	 * @author hu,hao
	 * @version 2012-3-2
	 */
	void createKonkaXxPdForHistory(KonkaXxPd t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-26
	 */
	List<KonkaXxPd> getKonkaXxPdForMdNameList(KonkaXxPd t);
	
	/**
	 * @author Xiao,GuoJian
	 * @version 2014-04-28
	 */
	Long createKonkaXxPdForExcel(List<KonkaXxPd> entityList);
}