package com.ebiz.mmt.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JcfxKczzKhDao;
import com.ebiz.mmt.domain.JcfxKczzKh;
import com.ebiz.mmt.domain.JcfxKczzKhfz;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-23 21:16:26
 */
@Service
public class JcfxKczzKhDaoSqlMapImpl extends EntityDaoSqlMapImpl<JcfxKczzKh> implements JcfxKczzKhDao {
	
	 /**
	  * 财务部库存周转率计算list
	  */
	@Override
	public List<Map<String, String>> selectJcfxCwbkczzlPaginatedList(JcfxKczzKh v) {
		return super.getSqlMapClientTemplate().queryForList("selectJcfxCwbkczzlPaginatedList", v);
	}
	
	/**
	 * 全国连锁渠道周转情况分公司排名汇总表
	 */
	@Override
	public List<Map<String, String>> selectselectJcfxQglsqdzzqkfgspmPaginatedList(
			JcfxKczzKh v) {
		return super.getSqlMapClientTemplate().queryForList("selectJcfxQglsqdzzqkfgspmPaginatedList", v);
	}

}
