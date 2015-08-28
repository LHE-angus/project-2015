package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdCreditDao;
import com.ebiz.mmt.domain.KonkaXxZmdCredit;
import com.ebiz.mmt.service.KonkaXxZmdCreditService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-03-27 09:57:28
 */
@Service
public class KonkaXxZmdCreditServiceImpl implements KonkaXxZmdCreditService {

	@Resource
	private KonkaXxZmdCreditDao konkaXxZmdCreditDao;
	

	public Long createKonkaXxZmdCredit(KonkaXxZmdCredit t) {
		return this.konkaXxZmdCreditDao.insertEntity(t);
	}

	public KonkaXxZmdCredit getKonkaXxZmdCredit(KonkaXxZmdCredit t) {
		return this.konkaXxZmdCreditDao.selectEntity(t);
	}

	public Long getKonkaXxZmdCreditCount(KonkaXxZmdCredit t) {
		return this.konkaXxZmdCreditDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdCredit> getKonkaXxZmdCreditList(KonkaXxZmdCredit t) {
		return this.konkaXxZmdCreditDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdCredit(KonkaXxZmdCredit t) {
		return this.konkaXxZmdCreditDao.updateEntity(t);
	}

	public int removeKonkaXxZmdCredit(KonkaXxZmdCredit t) {
		return this.konkaXxZmdCreditDao.deleteEntity(t);
	}

	public List<KonkaXxZmdCredit> getKonkaXxZmdCreditPaginatedList(KonkaXxZmdCredit t) {
		return this.konkaXxZmdCreditDao.selectEntityPaginatedList(t);
	}

	public int modifyKonkaXxZmdCreditForUpdate(KonkaXxZmdCredit t){
		this.konkaXxZmdCreditDao.updateEntity(t);
		
		this.konkaXxZmdCreditDao.updateKonkaXxZmdCreditForZmdPro(t);
		return 0;
	}
	
}
