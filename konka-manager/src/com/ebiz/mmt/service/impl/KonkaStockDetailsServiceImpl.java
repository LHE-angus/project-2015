package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaStockDetailsDao;
import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.mmt.service.KonkaStockDetailsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
@Service
public class KonkaStockDetailsServiceImpl implements KonkaStockDetailsService {

	@Resource
	private KonkaStockDetailsDao konkaStockDetailsDao;

	public Long createKonkaStockDetails(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.insertEntity(t);
	}

	public KonkaStockDetails getKonkaStockDetails(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.selectEntity(t);
	}

	public Long getKonkaStockDetailsCount(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.selectEntityCount(t);
	}

	public List<KonkaStockDetails> getKonkaStockDetailsList(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.selectEntityList(t);
	}

	public int modifyKonkaStockDetails(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.updateEntity(t);
	}

	public int removeKonkaStockDetails(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.deleteEntity(t);
	}

	public List<KonkaStockDetails> getKonkaStockDetailsPaginatedList(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public int removeKonkaStockDetailsWithSID(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.deleteKonkaStockDetailsWithSID(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public Long getKonkaStockDetailsForStatisticsCount(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.selectKonkaStockDetailsForStatisticsCount(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-08-25
	 */
	public List<KonkaStockDetails> getKonkaStockDetailsForStatisticsPaginatedList(KonkaStockDetails t) {
		return this.konkaStockDetailsDao.selectKonkaStockDetailsForStatisticsPaginatedList(t);
	}
	/**
	 * @author Wang,Yang
	 * @version 2011-11-09
	 */
	public void createKonkaStockDetailsWithR3Code(List<KonkaStockDetails> list){
		for(KonkaStockDetails t :list){
			KonkaStockDetails details = new KonkaStockDetails();
			details.setPd_id(t.getPd_id());
			details.setR3_code(t.getR3_code());
			List<KonkaStockDetails> DetailsList = this.konkaStockDetailsDao.selectEntityList(details);
		    if(DetailsList.size()==0){
		    	this.konkaStockDetailsDao.insertEntity(t);//若数据库中针对此网点此产品没有记录 ，则直接插入
		    }else{//若数据库中针对此网点此产品有一条以上记录，先删除 再插入；
		    	this.konkaStockDetailsDao.deleteEntity(details);
		    	this.konkaStockDetailsDao.insertEntity(t);
		    }
		}
	}
}
