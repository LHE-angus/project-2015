package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JxcOutInDetailDao;
import com.ebiz.mmt.domain.JxcOutInDetail;
import com.ebiz.mmt.service.JxcOutInDetailService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-07-02 09:52:43
 */
@Service
public class JxcOutInDetailServiceImpl implements JxcOutInDetailService {

	@Resource
	private JxcOutInDetailDao jxcOutInDetailDao;

	public Long createJxcOutInDetail(JxcOutInDetail t) {
		return this.jxcOutInDetailDao.insertEntity(t);
	}

	public JxcOutInDetail getJxcOutInDetail(JxcOutInDetail t) {
		return this.jxcOutInDetailDao.selectEntity(t);
	}

	public Long getJxcOutInDetailCount(JxcOutInDetail t) {
		return this.jxcOutInDetailDao.selectEntityCount(t);
	}

	public List<JxcOutInDetail> getJxcOutInDetailList(JxcOutInDetail t) {
		return this.jxcOutInDetailDao.selectEntityList(t);
	}

	public int modifyJxcOutInDetail(JxcOutInDetail t) {
		return this.jxcOutInDetailDao.updateEntity(t);
	}

	public int removeJxcOutInDetail(JxcOutInDetail t) {
		return this.jxcOutInDetailDao.deleteEntity(t);
	}

	public List<JxcOutInDetail> getJxcOutInDetailPaginatedList(JxcOutInDetail t) {
		return this.jxcOutInDetailDao.selectEntityPaginatedList(t);
	}

	public Long createJxcOutInDetailList(List<JxcOutInDetail> list) {
		Long result = 0l;
		if (list != null && list.size() > 0) {
			for (JxcOutInDetail entity : list) {
				this.jxcOutInDetailDao.insertEntity(entity);
				result = result + 1;
			}
		}
		return result;
	}

}
