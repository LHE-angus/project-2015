package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdQuotaDao;
import com.ebiz.mmt.domain.KonkaXxZmdQuota;
import com.ebiz.mmt.service.KonkaXxZmdQuotaService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2012-03-19 15:33:05
 */
@Service
public class KonkaXxZmdQuotaServiceImpl implements KonkaXxZmdQuotaService {

	@Resource
	private KonkaXxZmdQuotaDao konkaXxZmdQuotaDao;
	

	public Long createKonkaXxZmdQuota(KonkaXxZmdQuota t) {
		return this.konkaXxZmdQuotaDao.insertEntity(t);
	}

	public KonkaXxZmdQuota getKonkaXxZmdQuota(KonkaXxZmdQuota t) {
		return this.konkaXxZmdQuotaDao.selectEntity(t);
	}

	public Long getKonkaXxZmdQuotaCount(KonkaXxZmdQuota t) {
		return this.konkaXxZmdQuotaDao.selectEntityCount(t);
	}

	public List<KonkaXxZmdQuota> getKonkaXxZmdQuotaList(KonkaXxZmdQuota t) {
		return this.konkaXxZmdQuotaDao.selectEntityList(t);
	}

	public int modifyKonkaXxZmdQuota(KonkaXxZmdQuota t) {
		return this.konkaXxZmdQuotaDao.updateEntity(t);
	}

	public int removeKonkaXxZmdQuota(KonkaXxZmdQuota t) {
		return this.konkaXxZmdQuotaDao.deleteEntity(t);
	}

	public List<KonkaXxZmdQuota> getKonkaXxZmdQuotaPaginatedList(KonkaXxZmdQuota t) {
		return this.konkaXxZmdQuotaDao.selectEntityPaginatedList(t);
	}

	public Long createKonkaXxZmdQuotaByCustom(KonkaXxZmdQuota t) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String quota_date_year = (String) t.getMap().get("quota_date_year");
		for (int i = 1; i <= 12; i++) {
			// 先删除数据
			if(i < 10){
				t.setQuota_date(sdf.parse(quota_date_year + "-0" +String.valueOf(i)));
			} else {
				t.setQuota_date(sdf.parse(quota_date_year +"-" +String.valueOf(i)));
			}
			this.konkaXxZmdQuotaDao.deleteKonkaXxZmdQuotaByDidAndZidAndQdate(t);
			
			String quota_date_value = (String) t.getMap().get("quota_date_" + i);
			t.setQuota_value(new BigDecimal(quota_date_value).divide(new  BigDecimal(1), 4, BigDecimal.ROUND_HALF_UP));
			this.konkaXxZmdQuotaDao.insertEntity(t);
		}
		
		return 12L;
	}

}
