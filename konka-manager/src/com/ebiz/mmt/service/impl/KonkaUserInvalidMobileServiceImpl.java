package com.ebiz.mmt.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ebiz.mmt.dao.KonkaUserInvalidMobileDao;
import com.ebiz.mmt.domain.KonkaUserInvalidMobile;
import com.ebiz.mmt.domain.KonkaUserMobile;
import com.ebiz.mmt.service.KonkaUserInvalidMobileService;


/**
 * @author Cheng,Bing
 * @version 2012-01-09
 */
@Service
public class KonkaUserInvalidMobileServiceImpl implements KonkaUserInvalidMobileService {

	@Resource
	private KonkaUserInvalidMobileDao konkaUserInvalidMobileDao;

	public long createKonkaUserInvalidMobile(KonkaUserInvalidMobile t) {
		return this.konkaUserInvalidMobileDao.insertEntity(t);
	}

	public long modifyKonkaUserInvalidMobile(KonkaUserInvalidMobile t) {
		return this.konkaUserInvalidMobileDao.updateEntity(t);
	}

	public long removeKonkaUserInvalidMobile(KonkaUserInvalidMobile t) {
		return this.konkaUserInvalidMobileDao.deleteEntity(t);
	}

	public KonkaUserInvalidMobile getKonkaUserInvalidMobile(KonkaUserInvalidMobile t) {
		return this.konkaUserInvalidMobileDao.selectEntity(t);
	}

	public long getKonkaUserInvalidMobileCount(KonkaUserInvalidMobile t) {
		return this.konkaUserInvalidMobileDao.selectEntityCount(t);
	}

	public List<KonkaUserInvalidMobile> getKonkaUserInvalidMobileList(KonkaUserInvalidMobile t) {
		return this.konkaUserInvalidMobileDao.selectEntityList(t);
	}

	public List<KonkaUserInvalidMobile> getKonkaUserInvalidMobilePaginatedList(KonkaUserInvalidMobile t) {
		return this.konkaUserInvalidMobileDao.selectEntityPaginatedList(t);
	}
}
