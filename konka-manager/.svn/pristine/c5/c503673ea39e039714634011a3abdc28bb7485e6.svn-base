package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.GcxmProjBuyinfoDao;
import com.ebiz.mmt.domain.GcxmProjBuyinfo;
import com.ebiz.mmt.service.GcxmProjBuyinfoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-23 13:53:09
 */
@Service
public class GcxmProjBuyinfoServiceImpl implements GcxmProjBuyinfoService {

	@Resource
	private GcxmProjBuyinfoDao gcxmProjBuyinfoDao;
	

	public Long createGcxmProjBuyinfo(GcxmProjBuyinfo t) {
		return this.gcxmProjBuyinfoDao.insertEntity(t);
	}

	public GcxmProjBuyinfo getGcxmProjBuyinfo(GcxmProjBuyinfo t) {
		return this.gcxmProjBuyinfoDao.selectEntity(t);
	}

	public Long getGcxmProjBuyinfoCount(GcxmProjBuyinfo t) {
		return this.gcxmProjBuyinfoDao.selectEntityCount(t);
	}

	public List<GcxmProjBuyinfo> getGcxmProjBuyinfoList(GcxmProjBuyinfo t) {
		return this.gcxmProjBuyinfoDao.selectEntityList(t);
	}

	public int modifyGcxmProjBuyinfo(GcxmProjBuyinfo t) {
		return this.gcxmProjBuyinfoDao.updateEntity(t);
	}

	public int removeGcxmProjBuyinfo(GcxmProjBuyinfo t) {
		return this.gcxmProjBuyinfoDao.deleteEntity(t);
	}

	public List<GcxmProjBuyinfo> getGcxmProjBuyinfoPaginatedList(GcxmProjBuyinfo t) {
		return this.gcxmProjBuyinfoDao.selectEntityPaginatedList(t);
	}

}
