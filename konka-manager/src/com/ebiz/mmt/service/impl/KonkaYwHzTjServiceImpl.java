package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaYwHzTjDao;
import com.ebiz.mmt.domain.KonkaYwHzTj;
import com.ebiz.mmt.service.KonkaYwHzTjService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-23 17:18:32
 */
@Service
public class KonkaYwHzTjServiceImpl implements KonkaYwHzTjService {

	@Resource
	private KonkaYwHzTjDao konkaYwHzTjDao;
	

	@Override
	public Long createKonkaYwHzTj(KonkaYwHzTj t) {
		return this.konkaYwHzTjDao.insertEntity(t);
	}

	@Override
	public KonkaYwHzTj getKonkaYwHzTj(KonkaYwHzTj t) {
		return this.konkaYwHzTjDao.selectEntity(t);
	}

	@Override
	public Long getKonkaYwHzTjCount(KonkaYwHzTj t) {
		return this.konkaYwHzTjDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaYwHzTj> getKonkaYwHzTjList(KonkaYwHzTj t) {
		return this.konkaYwHzTjDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaYwHzTj(KonkaYwHzTj t) {
		return this.konkaYwHzTjDao.updateEntity(t);
	}
    //删除数据月数据
	@Override
	public int removeKonkaYwHzTj(KonkaYwHzTj t) {
		return this.konkaYwHzTjDao.deleteEntity(t);
	}

	@Override
	public List<KonkaYwHzTj> getKonkaYwHzTjPaginatedList(KonkaYwHzTj t) {
		return this.konkaYwHzTjDao.selectEntityPaginatedList(t);
	}
    //刷新数据月份数据
	@Override
	public void createKonkaYwHzTjForTongJi(KonkaYwHzTj v) {
		this.konkaYwHzTjDao.insertKonkaYwHzTjForTongJi(v);
	}

	@Override
	public HashMap getLastUpdateTime(KonkaYwHzTj v) {
		return this.konkaYwHzTjDao.selectLastUpdateTime(v);
	}
    /**
     * 跟新知道年月业务汇总数据到konka_yw_hz_tj
     */
	@Override
	public void removeAndCreate(KonkaYwHzTj v) {
		try {
			this.removeKonkaYwHzTj(v);
			this.createKonkaYwHzTjForTongJi(v);	
		} catch (Exception e) {
            //System.out.println("同步数据出现异常");
		}
		
	}
  
}
