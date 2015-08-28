package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JfRuleDao;
import com.ebiz.mmt.domain.JfRule;
import com.ebiz.mmt.service.JfRuleService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-25 15:25:02
 */
@Service
public class JfRuleServiceImpl implements JfRuleService {

	@Resource
	private JfRuleDao jfRuleDao;
	

	public Long createJfRule(JfRule t) {
		return this.jfRuleDao.insertEntity(t);
	}

	public JfRule getJfRule(JfRule t) {
		return this.jfRuleDao.selectEntity(t);
	}

	public Long getJfRuleCount(JfRule t) {
		return this.jfRuleDao.selectEntityCount(t);
	}

	public List<JfRule> getJfRuleList(JfRule t) {
		return this.jfRuleDao.selectEntityList(t);
	}

	public int modifyJfRule(JfRule t) {
		return this.jfRuleDao.updateEntity(t);
	}

	public int removeJfRule(JfRule t) {
		return this.jfRuleDao.deleteEntity(t);
	}

	public List<JfRule> getJfRulePaginatedList(JfRule t) {
		return this.jfRuleDao.selectEntityPaginatedList(t);
	}

}
