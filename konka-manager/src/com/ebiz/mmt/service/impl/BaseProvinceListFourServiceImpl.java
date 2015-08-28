package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.service.BaseProvinceListFourService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-03 17:04:13
 */
@Service
public class BaseProvinceListFourServiceImpl implements BaseProvinceListFourService {

	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;

	public Long createBaseProvinceListFour(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.insertEntity(t);
	}

	public BaseProvinceListFour getBaseProvinceListFour(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectEntity(t);
	}

	public Long getBaseProvinceListFourCount(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectEntityCount(t);
	}

	public List<BaseProvinceListFour> getBaseProvinceListFourList(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectEntityList(t);
	}

	public int modifyBaseProvinceListFour(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.updateEntity(t);
	}

	public int removeBaseProvinceListFour(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.deleteEntity(t);
	}

	public List<BaseProvinceListFour> getBaseProvinceListFourPaginatedList(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Chen,LiLin
	 * @date 2011-05-16
	 */
	public List<BaseProvinceListFour> getBaseProvinceListFourParentList(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectBaseProvinceListFourParentList(t);
	}

	/**
	 * @author Cheng,Bing
	 * @date 2011-11-7
	 */
	public List<BaseProvinceListFour> getBaseProvinceListFourSonTreeList(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectBaseProvinceListFourSonTreeList(t);
	}

	public BaseProvinceListFour getBaseProvinceListFourParent(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectBaseProvinceListFourParent(t);
	}

	public List<BaseProvinceListFour> getBaseProvinceListFourForProvinceInList(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectBaseProvinceListFourForProvinceInList(t);
	}

	public List<BaseProvinceListFour> getBaseProvinceListFourForProvinceNotInList(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectBaseProvinceListFourForProvinceNotInList(t);
	}

	public List<BaseProvinceListFour> getBaseProvinceListFourForProvinceList(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectBaseProvinceListFourForProvinceList(t);
	}

	public List<BaseProvinceListFour> getBaseProvinceListFourForCityList(BaseProvinceListFour t) {
		return this.baseProvinceListFourDao.selectBaseProvinceListFourForCityList(t);
	}

	@Override
	public List<BaseProvinceListFour> getBaseProvinceListFourByCityNameList(BaseProvinceListFour t)
	        throws DataAccessException {
		return this.baseProvinceListFourDao.selectBaseProvinceListFourByCityNameList(t);
	}

}
