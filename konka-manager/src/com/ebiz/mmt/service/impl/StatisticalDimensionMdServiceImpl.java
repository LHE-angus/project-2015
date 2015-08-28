package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionMdDao;
import com.ebiz.mmt.domain.StatisticalDimensionMd;
import com.ebiz.mmt.service.StatisticalDimensionMdService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-10 10:28:25
 */
@Service
public class StatisticalDimensionMdServiceImpl implements StatisticalDimensionMdService {

	@Resource
	private StatisticalDimensionMdDao statisticalDimensionMdDao;

	public Long createStatisticalDimensionMd(StatisticalDimensionMd t) {
		return this.statisticalDimensionMdDao.insertEntity(t);
	}

	public StatisticalDimensionMd getStatisticalDimensionMd(StatisticalDimensionMd t) {
		return this.statisticalDimensionMdDao.selectEntity(t);
	}

	public Long getStatisticalDimensionMdCount(StatisticalDimensionMd t) {
		return this.statisticalDimensionMdDao.selectEntityCount(t);
	}

	public List<StatisticalDimensionMd> getStatisticalDimensionMdList(StatisticalDimensionMd t) {
		return this.statisticalDimensionMdDao.selectEntityList(t);
	}

	public int modifyStatisticalDimensionMd(StatisticalDimensionMd t) {
		return this.statisticalDimensionMdDao.updateEntity(t);
	}

	public int removeStatisticalDimensionMd(StatisticalDimensionMd t) {
		return this.statisticalDimensionMdDao.deleteEntity(t);
	}

	public List<StatisticalDimensionMd> getStatisticalDimensionMdPaginatedList(StatisticalDimensionMd t) {
		return this.statisticalDimensionMdDao.selectEntityPaginatedList(t);
	}
	 /**
     * 品牌
     */
	@Override
	public List<Map<String, String>> getBrandList(StatisticalDimensionMd v) {
		return this.statisticalDimensionMdDao.selectBrandList(v);
	}
	/**
	 * 品类
	 */
	@Override
	public List<Map<String, String>> getBrandTypeList(StatisticalDimensionMd v) {
		return this.statisticalDimensionMdDao.selectBrandTypeList(v);
	}
    /**
     * 尺寸
     */
	@Override
	public List<Map<String, String>> getMdSizeList(StatisticalDimensionMd v) {
		return this.statisticalDimensionMdDao.selectMdSizeList(v);
	}
    /**
     * 价格段
     */
	@Override
	public List<Map<String, String>> getPriceDuanList(StatisticalDimensionMd v) {
		return this.statisticalDimensionMdDao.selectPriceDuanList(v);
	}
    /**
     * 尺寸段
     */
	@Override
	public List<Map<String, String>> getSizeSecList(StatisticalDimensionMd v) {
		return this.statisticalDimensionMdDao.selectSizeSecList(v);
	}
	@Override
	public int initStatisticalDimensionMd() {
		return this.statisticalDimensionMdDao.initStatisticalDimensionMd();
	}

	@Override
	public int removeStatisticalDimensionMdAll(StatisticalDimensionMd t) {
		return this.statisticalDimensionMdDao.deleteStatisticalDimensionMdAll(t);
	}

}
