package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GeoCnDao;
import com.ebiz.mmt.domain.GeoCn;
import com.ebiz.mmt.service.GeoCnService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2010-07-22 21:13:44
 */
@Service
public class GeoCnServiceImpl implements GeoCnService {

	@Resource
	private GeoCnDao geoCnDao;

	public Long createGeoCn(GeoCn t) {
		return this.geoCnDao.insertEntity(t);
	}

	public GeoCn getGeoCn(GeoCn t) {
		return this.geoCnDao.selectEntity(t);
	}

	public Long getGeoCnCount(GeoCn t) {
		return this.geoCnDao.selectEntityCount(t);
	}

	public List<GeoCn> getGeoCnList(GeoCn t) {
		return this.geoCnDao.selectEntityList(t);
	}

	public int modifyGeoCn(GeoCn t) {
		return this.geoCnDao.updateEntity(t);
	}

	public int removeGeoCn(GeoCn t) {
		return this.geoCnDao.deleteEntity(t);
	}

	public List<GeoCn> getGeoCnPaginatedList(GeoCn t) {
		return this.geoCnDao.selectEntityPaginatedList(t);
	}

}
