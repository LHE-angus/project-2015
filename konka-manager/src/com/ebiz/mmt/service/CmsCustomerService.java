package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.web.struts.inter.form.CmsCustomerVO;

public interface CmsCustomerService {
	List<CmsCustomerVO> getCmsCustomerList(CmsCustomerVO t);

	int getCmsCustomerCount(CmsCustomerVO t);
}
