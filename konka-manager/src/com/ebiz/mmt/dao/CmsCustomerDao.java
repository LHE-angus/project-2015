package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.web.struts.inter.form.CmsCustomerVO;
import com.ebiz.ssi.dao.EntityDao;

public interface CmsCustomerDao extends EntityDao<CmsCustomerVO> {
	List<CmsCustomerVO> selectCmsCustomerList(CmsCustomerVO t);

	int selectCmsCustomerCount(CmsCustomerVO t) ;

}
