package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.EcRule;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-09 17:52:25
 */
public interface EcRuleService {

	Long createEcRule(EcRule t);

	int modifyEcRule(EcRule t);

	int removeEcRule(EcRule t);

	EcRule getEcRule(EcRule t);

	List<EcRule> getEcRuleList(EcRule t);

	Long getEcRuleCount(EcRule t);

	List<EcRule> getEcRulePaginatedList(EcRule t);
	
	List<EcRule> getEcRuleForGoodsList(EcRule t);

}