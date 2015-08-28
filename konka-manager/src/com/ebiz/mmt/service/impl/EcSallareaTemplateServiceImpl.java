package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.EcSallareaDao;
import com.ebiz.mmt.dao.EcSallareaTemplateDao;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcSallarea;
import com.ebiz.mmt.domain.EcSallareaTemplate;
import com.ebiz.mmt.service.EcSallareaTemplateService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-12 10:43:11
 */
@Service
public class EcSallareaTemplateServiceImpl implements EcSallareaTemplateService {

	@Resource
	private EcSallareaTemplateDao ecSallareaTemplateDao;

	@Resource
	private EcSallareaDao ecSallareaDao;

	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;

	public Long createEcSallareaTemplate(EcSallareaTemplate t) {
		Long id = this.ecSallareaTemplateDao.insertEntity(t);
		if (t.getPindexList() != null && t.getPindexList().size() > 0) {
			for (String p_index : t.getPindexList()) {
				EcSallarea ea = new EcSallarea();
				ea.setTemplate_id(id);
				ea.setP_index(Long.valueOf(p_index));
				BaseProvinceListFour bf = new BaseProvinceListFour();
				bf.setP_index(Long.valueOf(p_index));
				bf = this.baseProvinceListFourDao.selectEntity(bf);
				ea.setP_name(bf.getP_name());
				this.ecSallareaDao.insertEntity(ea);
			}
		}

		return id;
	}

	public EcSallareaTemplate getEcSallareaTemplate(EcSallareaTemplate t) {
		return this.ecSallareaTemplateDao.selectEntity(t);
	}

	public Long getEcSallareaTemplateCount(EcSallareaTemplate t) {

		return this.ecSallareaTemplateDao.selectEntityCount(t);
	}

	public List<EcSallareaTemplate> getEcSallareaTemplateList(EcSallareaTemplate t) {
		return this.ecSallareaTemplateDao.selectEntityList(t);
	}

	public int modifyEcSallareaTemplate(EcSallareaTemplate t) {

		Long id = t.getId();
		EcSallarea es = new EcSallarea();
		es.setTemplate_id(id);
		List<EcSallarea> esList = this.ecSallareaDao.selectEntityList(es);
		if (esList != null && esList.size() > 0) {
			for (EcSallarea ecSallarea : esList) {
				this.ecSallareaDao.deleteEntity(ecSallarea);
			}
		}
		if (t.getPindexList() != null && t.getPindexList().size() > 0) {
			for (String p_index : t.getPindexList()) {
				EcSallarea ea = new EcSallarea();
				ea.setTemplate_id(id);
				ea.setP_index(Long.valueOf(p_index));
				BaseProvinceListFour bf = new BaseProvinceListFour();
				bf.setP_index(Long.valueOf(p_index));
				bf = this.baseProvinceListFourDao.selectEntity(bf);
				ea.setP_name(bf.getP_name());
				this.ecSallareaDao.insertEntity(ea);
			}
		}

		return this.ecSallareaTemplateDao.updateEntity(t);
	}

	public int removeEcSallareaTemplate(EcSallareaTemplate t) {
		return this.ecSallareaTemplateDao.deleteEntity(t);
	}

	public List<EcSallareaTemplate> getEcSallareaTemplatePaginatedList(EcSallareaTemplate t) {
		return this.ecSallareaTemplateDao.selectEntityPaginatedList(t);
	}

}
