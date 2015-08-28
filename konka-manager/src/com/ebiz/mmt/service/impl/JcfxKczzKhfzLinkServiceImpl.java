package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JcfxKczzKhfzLinkDao;
import com.ebiz.mmt.domain.JcfxKczzKhfzLink;
import com.ebiz.mmt.service.JcfxKczzKhfzLinkService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-23 21:16:26
 */
@Service
public class JcfxKczzKhfzLinkServiceImpl implements JcfxKczzKhfzLinkService {

	@Resource
	private JcfxKczzKhfzLinkDao jcfxKczzKhfzLinkDao;
	

	public Long createJcfxKczzKhfzLink(JcfxKczzKhfzLink t) {
		return this.jcfxKczzKhfzLinkDao.insertEntity(t);
	}

	public JcfxKczzKhfzLink getJcfxKczzKhfzLink(JcfxKczzKhfzLink t) {
		return this.jcfxKczzKhfzLinkDao.selectEntity(t);
	}

	public Long getJcfxKczzKhfzLinkCount(JcfxKczzKhfzLink t) {
		return this.jcfxKczzKhfzLinkDao.selectEntityCount(t);
	}

	public List<JcfxKczzKhfzLink> getJcfxKczzKhfzLinkList(JcfxKczzKhfzLink t) {
		return this.jcfxKczzKhfzLinkDao.selectEntityList(t);
	}

	public int modifyJcfxKczzKhfzLink(JcfxKczzKhfzLink t) {
		return this.jcfxKczzKhfzLinkDao.updateEntity(t);
	}

	public int removeJcfxKczzKhfzLink(JcfxKczzKhfzLink t) {
		return this.jcfxKczzKhfzLinkDao.deleteEntity(t);
	}

	public List<JcfxKczzKhfzLink> getJcfxKczzKhfzLinkPaginatedList(JcfxKczzKhfzLink t) {
		return this.jcfxKczzKhfzLinkDao.selectEntityPaginatedList(t);
	}

}
