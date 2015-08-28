package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CmsCustomerDao;
import com.ebiz.mmt.service.CmsCustomerService;
import com.ebiz.mmt.web.struts.inter.form.CmsCustomerVO;

@Service
public class CmsCustomerServiceImpl implements CmsCustomerService {

	@Resource
	CmsCustomerDao cmsCustomerDao;
		
	@Override
	public List<CmsCustomerVO> getCmsCustomerList(CmsCustomerVO t) {
		return cmsCustomerDao.selectCmsCustomerList(t);
	}

	@Override
	public int getCmsCustomerCount(CmsCustomerVO t) {
		return cmsCustomerDao.selectCmsCustomerCount(t);
	}

}
