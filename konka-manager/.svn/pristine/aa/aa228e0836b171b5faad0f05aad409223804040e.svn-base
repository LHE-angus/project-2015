package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.StatisticalDimensionMdDao;
import com.ebiz.mmt.domain.StatisticalDimensionMd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-10 10:28:25
 */
@Service
public class StatisticalDimensionMdDaoSqlMapImpl extends EntityDaoSqlMapImpl<StatisticalDimensionMd> implements
		StatisticalDimensionMdDao {
	@Override
	public int initStatisticalDimensionMd() {
		try {
			this.getSqlMapClientTemplate().update("initStatisticalDimensionMd");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
//	品牌 
	@Override
		public List<Map<String, String>> selectBrandList(StatisticalDimensionMd v) {
			return super.getSqlMapClientTemplate().queryForList("selectBrandList", v);
		}
//品类
		@Override
		public List<Map<String, String>> selectBrandTypeList(StatisticalDimensionMd v) {
			return super.getSqlMapClientTemplate().queryForList("selectBrandTypeList", v);
		}
//尺寸
		@Override
		public List<Map<String, String>> selectMdSizeList(StatisticalDimensionMd v) {
			return super.getSqlMapClientTemplate().queryForList("selectMdSizeList", v);
		}
//价格段
		@Override
		public List<Map<String, String>> selectPriceDuanList(StatisticalDimensionMd v) {
			return super.getSqlMapClientTemplate().queryForList("selectPriceDuanList", v);
		}
//尺寸段
		@Override
		public List<Map<String, String>> selectSizeSecList(StatisticalDimensionMd v) {
			return super.getSqlMapClientTemplate().queryForList("selectSizeSecList", v);
		}
	@Override
	public int deleteStatisticalDimensionMdAll(StatisticalDimensionMd t) {
		try {
			this.getSqlMapClientTemplate().delete("deleteStatisticalDimensionMdAll", t);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
