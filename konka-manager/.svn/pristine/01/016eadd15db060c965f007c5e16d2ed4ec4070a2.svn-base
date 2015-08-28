package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaMobileImpDeptDao;
import com.ebiz.mmt.dao.KonkaMobileImpHisDao;
import com.ebiz.mmt.domain.KonkaMobileImpDept;
import com.ebiz.mmt.domain.KonkaMobileImpHis;
import com.ebiz.mmt.service.KonkaMobileImpDeptService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-05-23 15:45:29
 */
@Service
public class KonkaMobileImpDeptServiceImpl implements KonkaMobileImpDeptService {

	@Resource
	private KonkaMobileImpDeptDao konkaMobileImpDeptDao;
	
	@Resource
	private KonkaMobileImpHisDao konkaMobileImpHisDao;
	

	public Long createKonkaMobileImpDept(KonkaMobileImpDept t) {
		return this.konkaMobileImpDeptDao.insertEntity(t);
	}

	public Long createKonkaMobileImpDeptListAndHis(List<KonkaMobileImpDept> konkaMobileImpDeptList, KonkaMobileImpHis konkaMobileImpHis) {
		Long his_id = this.konkaMobileImpHisDao.insertEntity(konkaMobileImpHis);
		for (KonkaMobileImpDept konkaMobileImpDept : konkaMobileImpDeptList) {
			konkaMobileImpDept.setOpr_his_id(his_id);
			this.konkaMobileImpDeptDao.insertEntity(konkaMobileImpDept);
		}
		
		return his_id;
	}
	
	public KonkaMobileImpDept getKonkaMobileImpDept(KonkaMobileImpDept t) {
		return this.konkaMobileImpDeptDao.selectEntity(t);
	}

	public Long getKonkaMobileImpDeptCount(KonkaMobileImpDept t) {
		return this.konkaMobileImpDeptDao.selectEntityCount(t);
	}

	public List<KonkaMobileImpDept> getKonkaMobileImpDeptList(KonkaMobileImpDept t) {
		return this.konkaMobileImpDeptDao.selectEntityList(t);
	}

	public int modifyKonkaMobileImpDept(KonkaMobileImpDept t) {
		return this.konkaMobileImpDeptDao.updateEntity(t);
	}

	public int removeKonkaMobileImpDept(KonkaMobileImpDept t) {
		return this.konkaMobileImpDeptDao.deleteEntity(t);
	}

	public List<KonkaMobileImpDept> getKonkaMobileImpDeptPaginatedList(KonkaMobileImpDept t) {
		return this.konkaMobileImpDeptDao.selectEntityPaginatedList(t);
	}

}
