package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfGiftInfoDao;
import com.ebiz.mmt.dao.JfScortsDao;
import com.ebiz.mmt.dao.JfScortsExchangeDao;
import com.ebiz.mmt.domain.JfGiftInfo;
import com.ebiz.mmt.domain.JfScorts;
import com.ebiz.mmt.domain.JfScortsExchange;
import com.ebiz.mmt.service.JfScortsExchangeService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
@Service
public class JfScortsExchangeServiceImpl implements JfScortsExchangeService {

	@Resource
	private JfScortsExchangeDao jfScortsExchangeDao;

	@Resource
	private JfScortsDao jfScortsDao;

	@Resource
	private JfGiftInfoDao jfGiftInfoDao;

	public Long createJfScortsExchange(JfScortsExchange t) {
		Long id = this.jfScortsExchangeDao.insertEntity(t);

		JfScorts jfScorts = new JfScorts();
		jfScorts.setUser_sn(t.getUser_sn());
		// 礼品数量
		JfGiftInfo jfGiftInfo = new JfGiftInfo();
		jfGiftInfo.setId(t.getGift_id());

		List<JfScorts> jfScortsList = this.jfScortsDao.selectEntityList(jfScorts);
		jfGiftInfo = this.jfGiftInfoDao.selectEntity(jfGiftInfo);

		if (null == jfScortsList || jfScortsList.size() == 0) {
			jfScorts.setTotal_scorts(new BigDecimal("0").subtract(t.getScorts()));
			this.jfScortsDao.insertEntity(jfScorts);
		} else {
			jfScorts = jfScortsList.get(0);
			jfScorts.setTotal_scorts(jfScorts.getTotal_scorts().subtract(t.getScorts()));
			this.jfScortsDao.updateEntity(jfScorts);
		}
		if (jfGiftInfo.getNum() > 0) {
			jfGiftInfo.setNum(jfGiftInfo.getNum() - 1);
			this.jfGiftInfoDao.updateEntity(jfGiftInfo);
		}
		return id;
	}

	public JfScortsExchange getJfScortsExchange(JfScortsExchange t) {
		return this.jfScortsExchangeDao.selectEntity(t);
	}

	public Long getJfScortsExchangeCount(JfScortsExchange t) {
		return this.jfScortsExchangeDao.selectEntityCount(t);
	}

	public List<JfScortsExchange> getJfScortsExchangeList(JfScortsExchange t) {
		return this.jfScortsExchangeDao.selectEntityList(t);
	}

	public int modifyJfScortsExchange(JfScortsExchange t) {
		return this.jfScortsExchangeDao.updateEntity(t);
	}

	public int removeJfScortsExchange(JfScortsExchange t) {
		return this.jfScortsExchangeDao.deleteEntity(t);
	}

	public List<JfScortsExchange> getJfScortsExchangePaginatedList(JfScortsExchange t) {
		return this.jfScortsExchangeDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-06-26
	 */
	public List<JfScortsExchange> getJfScortsExchangeAndGiftNameList(JfScortsExchange t) {
		return this.jfScortsExchangeDao.selectJfScortsExchangeAndGiftNameList(t);
	}

	/**
	 * @author pan,gang
	 * @date 2013-8-2
	 */
	public List<JfScortsExchange> getJfScortsExchangeAndGiftNameForMemberCardList(JfScortsExchange t) {
		return this.jfScortsExchangeDao.selectJfScortsExchangeAndGiftNameForMemberCardList(t);
	}

}
