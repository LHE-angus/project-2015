package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePopedomDao;
import com.ebiz.mmt.domain.BasePopedom;
import com.ebiz.mmt.service.BasePopedomService;

/**
 * @author Jin,QingHua
 */
@Service
public class BasePopedomServiceImpl implements BasePopedomService {

	@Resource
	private BasePopedomDao basePopedomDao;

	public Long createBasePopedom(BasePopedom t) {
		return this.basePopedomDao.insertEntity(t);
	}

	public BasePopedom getBasePopedom(BasePopedom t) {
		return this.basePopedomDao.selectEntity(t);
	}

	public Long getBasePopedomCount(BasePopedom t) {
		return this.basePopedomDao.selectEntityCount(t);
	}

	public List<BasePopedom> getBasePopedomList(BasePopedom t) {
		return this.basePopedomDao.selectEntityList(t);
	}

	public int modifyBasePopedom(BasePopedom t) {
		return this.basePopedomDao.updateEntity(t);
	}

	public int removeBasePopedom(BasePopedom t) {
		return this.basePopedomDao.deleteEntity(t);
	}

	public List<BasePopedom> getBasePopedomPaginatedList(BasePopedom t) {
		return this.basePopedomDao.selectEntityPaginatedList(t);
	}

}
