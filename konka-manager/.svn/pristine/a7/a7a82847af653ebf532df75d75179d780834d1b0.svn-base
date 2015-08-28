package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcRuleDao;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.service.EcRuleService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-09 17:52:25
 */
@Service
public class EcRuleServiceImpl implements EcRuleService {

	@Resource
	private EcRuleDao ecRuleDao;
	

	public Long createEcRule(EcRule t) {
		return this.ecRuleDao.insertEntity(t);
	}

	public EcRule getEcRule(EcRule t) {
		return this.ecRuleDao.selectEntity(t);
	}

	public Long getEcRuleCount(EcRule t) {
		return this.ecRuleDao.selectEntityCount(t);
	}

	public List<EcRule> getEcRuleList(EcRule t) {
		return this.ecRuleDao.selectEntityList(t);
	}

	public int modifyEcRule(EcRule t) {
		return this.ecRuleDao.updateEntity(t);
	}

	public int removeEcRule(EcRule t) {
		return this.ecRuleDao.deleteEntity(t);
	}

	public List<EcRule> getEcRulePaginatedList(EcRule t) {
		return this.ecRuleDao.selectEntityPaginatedList(t);
	}
	
	public List<EcRule> getEcRuleForGoodsList(EcRule t) {
		return this.ecRuleDao.selectEcRuleForGoodsList(t);
	}

}
