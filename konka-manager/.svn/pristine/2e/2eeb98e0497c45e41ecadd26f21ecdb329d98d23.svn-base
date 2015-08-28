package com.ebiz.mmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AreaFightInfoDao;
import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.domain.AreaFightInfo;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.service.AreaFightInfoService;


@Service
public class AreaFightInfoServiceImpl implements AreaFightInfoService {

	@Resource
	private AreaFightInfoDao areaFightInfoDao;
	
	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;
	

	public Long createAreaFightInfo(AreaFightInfo t) {
		return this.areaFightInfoDao.insertEntity(t);
	}

	public AreaFightInfo getAreaFightInfo(AreaFightInfo t) {
		return this.areaFightInfoDao.selectEntity(t);
	}

	public Long getAreaFightInfoCount(AreaFightInfo t) {
		return this.areaFightInfoDao.selectEntityCount(t);
	}

	public List<AreaFightInfo> getAreaFightInfoList(AreaFightInfo t) {
		return this.areaFightInfoDao.selectEntityList(t);
	}

	public int modifyAreaFightInfo(AreaFightInfo t) {
		return this.areaFightInfoDao.updateEntity(t);
	}

	public int removeAreaFightInfo(AreaFightInfo t) {
		return this.areaFightInfoDao.deleteEntity(t);
	}

	public List<AreaFightInfo> getAreaFightInfoPaginatedList(AreaFightInfo t) {
		return this.areaFightInfoDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getTotalCount(AreaFightInfo t) {
		return this.areaFightInfoDao.selectTotalCount(t);
	}

	@Override
	public List<HashMap> getAreaFightListForMap(AreaFightInfo t) {
		return this.areaFightInfoDao.selectAreaFightListForMap(t);
	}

	@Override
	public AreaFightInfo getAreaFightDetail(AreaFightInfo t) {
		AreaFightInfo res = new AreaFightInfo();
		AreaFightInfo temp = this.getAreaFightInfo(t);
		if(null!=temp){
			res = temp;
		}
		
		// 处理所在城市
		if (t.getP_index() != null && String.valueOf(t.getP_index()).length() >= 6) {
			// 省/直辖市/自治区
			BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
			baseProvinceFour.setP_index(new Long(String.valueOf(t.getP_index()).substring(0, 2) + "0000"));
			baseProvinceFour.setDel_mark(0);
			List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao
					.selectEntityList(baseProvinceFour);
			if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
				BaseProvinceListFour b = baseProvinceFourList.get(0);
				res.getMap().put("PROVINCE", b.getP_name());
			}
			if (!(String.valueOf(t.getP_index()).substring(0, 2) + "0000").equals(String.valueOf(
					t.getP_index()).substring(0, 4)
					+ "00")) {
				// 市
				baseProvinceFour.setP_index(new Long(String.valueOf(t.getP_index()).substring(0, 4) + "00"));
				baseProvinceFourList = null;
				baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					res.getMap().put("CITY", b.getP_name());
				}
			}
			if (!String.valueOf(t.getP_index()).substring(0, 6).equals(
					String.valueOf(t.getP_index()).substring(0, 4) + "00")) {
				// 县
				baseProvinceFour.setP_index(new Long(String.valueOf(t.getP_index()).substring(0, 6)));
				baseProvinceFourList = null;
				baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					res.getMap().put("COUNTRY", b.getP_name());
				}
			}
			if (!String.valueOf(t.getP_index()).substring(0, 6).equals(String.valueOf(t.getP_index()))) {
				// 乡镇
				baseProvinceFour.setP_index(new Long(String.valueOf(t.getP_index())));
				baseProvinceFourList = null;
				baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
				if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
					BaseProvinceListFour b = baseProvinceFourList.get(0);
					res.getMap().put("TOWN", b.getP_name());
				}
			}
		}
		res.setP_index(t.getP_index());
		
		return res;
	}

	@Override
	public Long getDetailCount(AreaFightInfo t) {
		return this.areaFightInfoDao.selectDetailCount(t);
	}

	@Override
	public List<HashMap> getAreaFightDetailList(AreaFightInfo t) {
		return this.areaFightInfoDao.selectAreaFightDetailList(t);
	}

}
