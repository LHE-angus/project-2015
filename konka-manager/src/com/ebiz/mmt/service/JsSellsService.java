package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.JsSells;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-01-09 15:57:14
 */
public interface JsSellsService {

	Long createJsSells(JsSells t);

	int modifyJsSells(JsSells t);

	int removeJsSells(JsSells t);

	JsSells getJsSells(JsSells t);

	List<JsSells> getJsSellsList(JsSells t);

	Long getJsSellsCount(JsSells t);

	List<JsSells> getJsSellsPaginatedList(JsSells t);

	List<JsSells> getJsSellsListForML(JsSells t);

}