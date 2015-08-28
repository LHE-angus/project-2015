package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdAccDetailsDao;
import com.ebiz.mmt.domain.KonkaXxZmdAccDetails;
import com.ebiz.mmt.service.KonkaXxZmdAccDetailsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:51
 */
@Service
public class KonkaXxZmdAccDetailsServiceImpl implements KonkaXxZmdAccDetailsService {

	@Resource
	private KonkaXxZmdAccDetailsDao konkaXxZmdAccDetailsDao;
	

	public Long createKonkaXxZmdAccDetails(KonkaXxZmdAccDetails t) {
		return this.konkaXxZmdAccDetailsDao.insertEntity(t);
	}

	public KonkaXxZmdAccDetails getKonkaXxZmdAccDetails(KonkaXxZmdAccDetails t) {
		return this.konkaXxZmdAccDetailsDao.selectEntity(t);
	}

	public Long getKonkaXxZmdAccDetailsCount(KonkaXxZmdAccDetails t) {
		return this.konkaXxZmdAccDetailsDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdAccDetails> getKonkaXxZmdAccDetailsList(KonkaXxZmdAccDetails t) {
		return this.konkaXxZmdAccDetailsDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdAccDetails(KonkaXxZmdAccDetails t) {
		return this.konkaXxZmdAccDetailsDao.updateEntity(t);
	}

	public int removeKonkaXxZmdAccDetails(KonkaXxZmdAccDetails t) {
		return this.konkaXxZmdAccDetailsDao.deleteEntity(t);
	}

	public List<KonkaXxZmdAccDetails> getKonkaXxZmdAccDetailsPaginatedList(KonkaXxZmdAccDetails t) {
		return this.konkaXxZmdAccDetailsDao.selectEntityPaginatedList(t);
	}

}
