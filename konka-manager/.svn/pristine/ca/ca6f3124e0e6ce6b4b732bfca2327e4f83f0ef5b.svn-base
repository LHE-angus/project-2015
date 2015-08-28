package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcSupplierDao;
import com.ebiz.mmt.dao.JxcSzDetailsDao;
import com.ebiz.mmt.domain.JxcSupplier;
import com.ebiz.mmt.domain.JxcSzDetails;
import com.ebiz.mmt.service.JxcSupplierService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcSupplierServiceImpl implements JxcSupplierService {

	@Resource
	private JxcSupplierDao jxcSupplierDao;

	@Resource
	private JxcSzDetailsDao jxcSzDetailsDao;

	public Long createJxcSupplier(JxcSupplier t) {
		return this.jxcSupplierDao.insertEntity(t);
	}

	public JxcSupplier getJxcSupplier(JxcSupplier t) {
		return this.jxcSupplierDao.selectEntity(t);
	}

	public Long getJxcSupplierCount(JxcSupplier t) {
		return this.jxcSupplierDao.selectEntityCount(t);
	}

	public List<JxcSupplier> getJxcSupplierList(JxcSupplier t) {
		return this.jxcSupplierDao.selectEntityList(t);
	}

	public int modifyJxcSupplier(JxcSupplier t) {
		if (t.getMap().get("jxcSzDetails") != null) {
			JxcSzDetails jxcSzDetails = (JxcSzDetails) t.getMap().get("jxcSzDetails");
			this.jxcSzDetailsDao.insertEntity(jxcSzDetails);
		}
		return this.jxcSupplierDao.updateEntity(t);
	}

	public int removeJxcSupplier(JxcSupplier t) {
		return this.jxcSupplierDao.deleteEntity(t);
	}

	public List<JxcSupplier> getJxcSupplierPaginatedList(JxcSupplier t) {
		return this.jxcSupplierDao.selectEntityPaginatedList(t);
	}

}
