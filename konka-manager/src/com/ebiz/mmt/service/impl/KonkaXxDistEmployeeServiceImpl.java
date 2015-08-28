package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxDistEmployeeDao;
import com.ebiz.mmt.domain.KonkaXxDistEmployee;
import com.ebiz.mmt.service.KonkaXxDistEmployeeService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-04-10 12:30:55
 */
@Service
public class KonkaXxDistEmployeeServiceImpl implements KonkaXxDistEmployeeService {

	@Resource
	private KonkaXxDistEmployeeDao konkaXxDistEmployeeDao;

	public Long createKonkaXxDistEmployee(KonkaXxDistEmployee t) {
		return this.konkaXxDistEmployeeDao.insertEntity(t);
	}

	public KonkaXxDistEmployee getKonkaXxDistEmployee(KonkaXxDistEmployee t) {
		return this.konkaXxDistEmployeeDao.selectEntity(t);
	}

	public Long getKonkaXxDistEmployeeCount(KonkaXxDistEmployee t) {
		return this.konkaXxDistEmployeeDao.selectEntityCount(t);
	}

	public List<KonkaXxDistEmployee> getKonkaXxDistEmployeeList(KonkaXxDistEmployee t) {
		return this.konkaXxDistEmployeeDao.selectEntityList(t);
	}

	public int modifyKonkaXxDistEmployee(KonkaXxDistEmployee t) {
		return this.konkaXxDistEmployeeDao.updateEntity(t);
	}

	public int removeKonkaXxDistEmployee(KonkaXxDistEmployee t) {
		return this.konkaXxDistEmployeeDao.deleteEntity(t);
	}

	public List<KonkaXxDistEmployee> getKonkaXxDistEmployeePaginatedList(KonkaXxDistEmployee t) {
		return this.konkaXxDistEmployeeDao.selectEntityPaginatedList(t);
	}

	public List<KonkaXxDistEmployee> gettKonkaXxDistEmployeeForAddUserNamePaginatedList(KonkaXxDistEmployee t) {
		return this.konkaXxDistEmployeeDao.selectKonkaXxDistEmployeeForAddUserNamePaginatedList(t);
	}

}
