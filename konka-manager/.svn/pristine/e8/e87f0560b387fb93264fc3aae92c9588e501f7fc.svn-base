package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcEntpSellDao;
import com.ebiz.mmt.domain.JxcEntpSell;
import com.ebiz.mmt.service.JxcEntpSellService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-02-10 11:05:59
 */
@Service
public class JxcEntpSellServiceImpl implements JxcEntpSellService {

	@Resource
	private JxcEntpSellDao jxcEntpSellDao;

	public Long createJxcEntpSell(JxcEntpSell t) {
		return this.jxcEntpSellDao.insertEntity(t);
	}

	public JxcEntpSell getJxcEntpSell(JxcEntpSell t) {
		return this.jxcEntpSellDao.selectEntity(t);
	}

	public Long getJxcEntpSellCount(JxcEntpSell t) {
		return this.jxcEntpSellDao.selectEntityCount(t);
	}

	public List<JxcEntpSell> getJxcEntpSellList(JxcEntpSell t) {
		return this.jxcEntpSellDao.selectEntityList(t);
	}

	public int modifyJxcEntpSell(JxcEntpSell t) {
		return this.jxcEntpSellDao.updateEntity(t);
	}

	public int removeJxcEntpSell(JxcEntpSell t) {
		return this.jxcEntpSellDao.deleteEntity(t);
	}

	public List<JxcEntpSell> getJxcEntpSellPaginatedList(JxcEntpSell t) {
		return this.jxcEntpSellDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-02-10
	 */
	public List<JxcEntpSell> getJxcEntpSellListForPart(JxcEntpSell t) {
		return this.jxcEntpSellDao.selectJxcEntpSellListForPart(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2011-02-10
	 */
	public Long getJxcEntpSellforDistinctCount(JxcEntpSell t) {
		return this.jxcEntpSellDao.selectJxcEntpSellforDistinctCount(t);
	}

}
