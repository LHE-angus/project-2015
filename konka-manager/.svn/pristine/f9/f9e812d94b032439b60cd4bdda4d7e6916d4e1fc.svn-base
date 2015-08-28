package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PshowOrdeExchangeDao;
import com.ebiz.mmt.domain.PshowOrdeExchange;
import com.ebiz.mmt.service.PshowOrdeExchangeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2014-06-27 17:31:26
 */
@Service
public class PshowOrdeExchangeServiceImpl implements PshowOrdeExchangeService {

	@Resource
	private PshowOrdeExchangeDao pshowOrdeExchangeDao;
	

	public Long createPshowOrdeExchange(PshowOrdeExchange t) {
		return this.pshowOrdeExchangeDao.insertEntity(t);
	}

	public PshowOrdeExchange getPshowOrdeExchange(PshowOrdeExchange t) {
		return this.pshowOrdeExchangeDao.selectEntity(t);
	}

	public Long getPshowOrdeExchangeCount(PshowOrdeExchange t) {
		return this.pshowOrdeExchangeDao.selectEntityCount(t);
	}

	public List<PshowOrdeExchange> getPshowOrdeExchangeList(PshowOrdeExchange t) {
		return this.pshowOrdeExchangeDao.selectEntityList(t);
	}

	public int modifyPshowOrdeExchange(PshowOrdeExchange t) {
		return this.pshowOrdeExchangeDao.updateEntity(t);
	}

	public int removePshowOrdeExchange(PshowOrdeExchange t) {
		return this.pshowOrdeExchangeDao.deleteEntity(t);
	}

	public List<PshowOrdeExchange> getPshowOrdeExchangePaginatedList(PshowOrdeExchange t) {
		return this.pshowOrdeExchangeDao.selectEntityPaginatedList(t);
	}

}
