package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdSalaryDao;
import com.ebiz.mmt.domain.KonkaXxZmdSalary;
import com.ebiz.mmt.service.KonkaXxZmdSalaryService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-31 14:24:51
 */
@Service
public class KonkaXxZmdSalaryServiceImpl implements KonkaXxZmdSalaryService {

	@Resource
	private KonkaXxZmdSalaryDao konkaXxZmdSalaryDao;
	

	public Long createKonkaXxZmdSalary(KonkaXxZmdSalary t) {
		return this.konkaXxZmdSalaryDao.insertEntity(t);
	}

	public KonkaXxZmdSalary getKonkaXxZmdSalary(KonkaXxZmdSalary t) {
		return this.konkaXxZmdSalaryDao.selectEntity(t);
	}

	public Long getKonkaXxZmdSalaryCount(KonkaXxZmdSalary t) {
		return this.konkaXxZmdSalaryDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdSalary> getKonkaXxZmdSalaryList(KonkaXxZmdSalary t) {
		return this.konkaXxZmdSalaryDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdSalary(KonkaXxZmdSalary t) {
		return this.konkaXxZmdSalaryDao.updateEntity(t);
	}

	public int removeKonkaXxZmdSalary(KonkaXxZmdSalary t) {
		return this.konkaXxZmdSalaryDao.deleteEntity(t);
	}

	public List<KonkaXxZmdSalary> getKonkaXxZmdSalaryPaginatedList(KonkaXxZmdSalary t) {
		return this.konkaXxZmdSalaryDao.selectEntityPaginatedList(t);
	}

}
