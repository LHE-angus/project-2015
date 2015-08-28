package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JcfxKczzKhDao;
import com.ebiz.mmt.dao.KonkaR3ShopDao;
import com.ebiz.mmt.domain.JcfxKczzKh;
import com.ebiz.mmt.domain.JcfxKczzKhfz;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.service.JcfxKczzKhService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-23 21:16:26
 */
@Service
public class JcfxKczzKhServiceImpl implements JcfxKczzKhService {

	@Resource
	private JcfxKczzKhDao jcfxKczzKhDao;
	
	@Resource
	private KonkaR3ShopDao konkaR3ShopDao;
	
	public Long createJcfxKczzKh(JcfxKczzKh t) {
		return this.jcfxKczzKhDao.insertEntity(t);
	}

	public JcfxKczzKh getJcfxKczzKh(JcfxKczzKh t) {
		return this.jcfxKczzKhDao.selectEntity(t);
	}

	public Long getJcfxKczzKhCount(JcfxKczzKh t) {
		return this.jcfxKczzKhDao.selectEntityCount(t);
	}

	public List<JcfxKczzKh> getJcfxKczzKhList(JcfxKczzKh t) {
		return this.jcfxKczzKhDao.selectEntityList(t);
	}

	public int modifyJcfxKczzKh(JcfxKczzKh t) {
		return this.jcfxKczzKhDao.updateEntity(t);
	}

	public int removeJcfxKczzKh(JcfxKczzKh t) {
		return this.jcfxKczzKhDao.deleteEntity(t);
	}

	public List<JcfxKczzKh> getJcfxKczzKhPaginatedList(JcfxKczzKh t) {
		return this.jcfxKczzKhDao.selectEntityPaginatedList(t);
	}
	/**
	 * 查找待选客户列表count
	 * 
	 */
	@Override
	public Long getKonkaR3ShopForYwyCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopForYwyCount(t);
	}
	 /**
     * 查找待选客户列表
     */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopForYwyPaginatedList(KonkaR3Shop v) {
		List<KonkaR3Shop> result = konkaR3ShopDao.selectKonkaR3ShopForYwyPaginatedList(v);
		return result;
	}
	 /**
	  * 财务部库存周转率计算list
	  */
	@Override
	public List<Map<String, String>> getJcfxCwbkczzlPaginatedList(JcfxKczzKh v) {
		return this.jcfxKczzKhDao.selectJcfxCwbkczzlPaginatedList(v);
	}
	
	/**
	 * 全国连锁渠道周转情况分公司排名汇总表
	 */
	@Override
	public List<Map<String, String>> getJcfxQglsqdzzqkfgspmPaginatedList(
			JcfxKczzKh v) {
		return  this.jcfxKczzKhDao.selectselectJcfxQglsqdzzqkfgspmPaginatedList(v);
	}


}
