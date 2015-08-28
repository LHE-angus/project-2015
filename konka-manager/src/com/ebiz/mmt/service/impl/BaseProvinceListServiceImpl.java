package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseProvinceListDao;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.service.BaseProvinceListService;

/**
 * @author Hui,Gang
 */
@Service
public class BaseProvinceListServiceImpl implements BaseProvinceListService {

	@Resource
	private BaseProvinceListDao baseProvinceListDao;


	@Override
	public BaseProvinceList getBaseProvinceList(BaseProvinceList t) {
		return this.baseProvinceListDao.selectEntity(t);
	}

	@Override
	public Long getBaseProvinceListCount(BaseProvinceList t) {
		return this.baseProvinceListDao.selectEntityCount(t);
	}

	@Override
	public List<BaseProvinceList> getBaseProvinceListList(BaseProvinceList t) {
		return this.baseProvinceListDao.selectEntityList(t);
	}

	@Override
	public int modifyBaseProvinceList(BaseProvinceList t) {
		return this.baseProvinceListDao.updateEntity(t);
	}


	@Override
	public List<BaseProvinceList> getBaseProvinceListPaginatedList(BaseProvinceList t) {
		return this.baseProvinceListDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Xing,XiuDong
	 */
	@Override
	public List<BaseProvinceList> getBaseProvinceListParentList(BaseProvinceList t) {
		return this.baseProvinceListDao.selectBaseProvinceListParentList(t);
	}


	/**
	 * @author Chen,Lilin
	 */
	@Override
	public List<BaseProvinceList> getBaseProvinceListChildrenList(BaseProvinceList t) {
		return this.baseProvinceListDao.selectBaseProvinceListChildrenList(t);
	}



	/**
	 * @author Li,Ka
	 * @version 2011-11-28
	 * 根据p_index查找上级P_index
	 */
	@Override
	public List<BaseProvinceList> getBaseProvinceListAllParPindexByPindex(BaseProvinceList t) {
		return this.baseProvinceListDao.selectBaseProvinceListAllParPindexByPindex(t);
	}
}
