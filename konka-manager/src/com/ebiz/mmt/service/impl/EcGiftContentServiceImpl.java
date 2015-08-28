package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcGiftContentDao;
import com.ebiz.mmt.domain.EcGiftContent;
import com.ebiz.mmt.service.EcGiftContentService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
@Service
public class EcGiftContentServiceImpl implements EcGiftContentService {

	@Resource
	private EcGiftContentDao ecGiftContentDao;
	

	public Long createEcGiftContent(EcGiftContent t) {
		return this.ecGiftContentDao.insertEntity(t);
	}

	public EcGiftContent getEcGiftContent(EcGiftContent t) {
		return this.ecGiftContentDao.selectEntity(t);
	}

	public Long getEcGiftContentCount(EcGiftContent t) {
		return this.ecGiftContentDao.selectEntityCount(t);
	}

	public List<EcGiftContent> getEcGiftContentList(EcGiftContent t) {
		return this.ecGiftContentDao.selectEntityList(t);
	}

	public int modifyEcGiftContent(EcGiftContent t) {
		return this.ecGiftContentDao.updateEntity(t);
	}

	public int removeEcGiftContent(EcGiftContent t) {
		return this.ecGiftContentDao.deleteEntity(t);
	}

	public List<EcGiftContent> getEcGiftContentPaginatedList(EcGiftContent t) {
		return this.ecGiftContentDao.selectEntityPaginatedList(t);
	}

}
