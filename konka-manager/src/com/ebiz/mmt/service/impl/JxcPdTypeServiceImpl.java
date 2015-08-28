package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcPdTypeDao;
import com.ebiz.mmt.domain.JxcPdType;
import com.ebiz.mmt.service.JxcPdTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-03 09:42:37
 */
@Service
public class JxcPdTypeServiceImpl implements JxcPdTypeService {

	@Resource
	private JxcPdTypeDao jxcPdTypeDao;
	

	public Long createJxcPdType(JxcPdType t) {
		return this.jxcPdTypeDao.insertEntity(t);
	}

	public JxcPdType getJxcPdType(JxcPdType t) {
		return this.jxcPdTypeDao.selectEntity(t);
	}

	public Long getJxcPdTypeCount(JxcPdType t) {
		return this.jxcPdTypeDao.selectEntityCount(t);
	}

	public List<JxcPdType> getJxcPdTypeList(JxcPdType t) {
		return this.jxcPdTypeDao.selectEntityList(t);
	}

	public int modifyJxcPdType(JxcPdType t) {
		return this.jxcPdTypeDao.updateEntity(t);
	}

	public int removeJxcPdType(JxcPdType t) {
		return this.jxcPdTypeDao.deleteEntity(t);
	}

	public List<JxcPdType> getJxcPdTypePaginatedList(JxcPdType t) {
		return this.jxcPdTypeDao.selectEntityPaginatedList(t);
	}

}
