package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcProductDao;
import com.ebiz.mmt.dao.KonkaBcompPdContentDao;
import com.ebiz.mmt.domain.EcProduct;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.service.EcProductService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2015-03-11 15:01:37
 */
@Service
public class EcProductServiceImpl implements EcProductService {

	@Resource
	private EcProductDao ecProductDao;

	@Resource
	private KonkaBcompPdContentDao konkaBcompPdContentDao;

	public Long createEcProduct(EcProduct t) {
		Long id = this.ecProductDao.insertEntity(t);

		if (t.getKonkaBcompPdContentList() != null && t.getKonkaBcompPdContentList().size() > 0) {
			for (KonkaBcompPdContent kb : t.getKonkaBcompPdContentList()) {
				kb.setKbp_id(id);
				this.konkaBcompPdContentDao.insertEntity(kb);
			}
		}

		return id;
	}

	public EcProduct getEcProduct(EcProduct t) {
		return this.ecProductDao.selectEntity(t);
	}

	public Long getEcProductCount(EcProduct t) {
		return this.ecProductDao.selectEntityCount(t);
	}

	public List<EcProduct> getEcProductList(EcProduct t) {
		return this.ecProductDao.selectEntityList(t);
	}

	public int modifyEcProduct(EcProduct t) {

		KonkaBcompPdContent konkaBcompPdContent = new KonkaBcompPdContent();
		konkaBcompPdContent.setKbp_id(t.getId());
		this.konkaBcompPdContentDao.deleteKonkaBcompPdContentWithKPDID(konkaBcompPdContent);

		List<KonkaBcompPdContent> konkaBcompPdContentList = t.getKonkaBcompPdContentList();
		if (null != konkaBcompPdContentList && 0 != konkaBcompPdContentList.size())
			for (KonkaBcompPdContent tt : konkaBcompPdContentList) {
				tt.setKbp_id(t.getId());
				this.konkaBcompPdContentDao.insertEntity(tt);
			}

		return this.ecProductDao.updateEntity(t);
	}

	public int removeEcProduct(EcProduct t) {
		return this.ecProductDao.deleteEntity(t);
	}

	public List<EcProduct> getEcProductPaginatedList(EcProduct t) {
		return this.ecProductDao.selectEntityPaginatedList(t);
	}

}
