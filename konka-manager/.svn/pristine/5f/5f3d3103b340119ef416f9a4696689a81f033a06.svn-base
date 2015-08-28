package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcBrandApplyDao;
import com.ebiz.mmt.domain.JxcBrandApply;
import com.ebiz.mmt.service.JxcBrandApplyService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcBrandApplyServiceImpl implements JxcBrandApplyService {

	@Resource
	private JxcBrandApplyDao jxcBrandApplyDao;

	public Long createJxcBrandApply(JxcBrandApply t) {
		return this.jxcBrandApplyDao.insertEntity(t);
	}

	public JxcBrandApply getJxcBrandApply(JxcBrandApply t) {
		return this.jxcBrandApplyDao.selectEntity(t);
	}

	public Long getJxcBrandApplyCount(JxcBrandApply t) {
		return this.jxcBrandApplyDao.selectEntityCount(t);
	}

	public List<JxcBrandApply> getJxcBrandApplyList(JxcBrandApply t) {
		return this.jxcBrandApplyDao.selectEntityList(t);
	}

	public int modifyJxcBrandApply(JxcBrandApply t) {
		return this.jxcBrandApplyDao.updateEntity(t);
	}

	public int removeJxcBrandApply(JxcBrandApply t) {
		return this.jxcBrandApplyDao.deleteEntity(t);
	}

	public List<JxcBrandApply> getJxcBrandApplyPaginatedList(JxcBrandApply t) {
		return this.jxcBrandApplyDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Zhang,ShiMing
	 */
	@Override
	public List<JxcBrandApply> getJxcBrandApplyListWithShopName(JxcBrandApply entity) {
		return this.jxcBrandApplyDao.selectEntityListWithShopName(entity);
	}

	/**
	 * @author Zhang,ShiMing
	 */
	public Long getJxcBrandApplyWithShopNameCount(JxcBrandApply entity) {
		return this.jxcBrandApplyDao.selectEntityWithShopNameCount(entity);
	}

	/**
	 * @author Zhang,ShiMing
	 */
	@Override
	public List<JxcBrandApply> getJxcBrandApplyXPaginatedList(JxcBrandApply entity) {
		return this.jxcBrandApplyDao.selectJxcBrandApplyXPaginatedList(entity);
	}

	/**
	 * @author Zhang,ShiMing
	 */
	public Long getJxcBrandApplyXCount(JxcBrandApply entity) {
		return this.jxcBrandApplyDao.selectJxcBrandApplyXCount(entity);
	}

}
