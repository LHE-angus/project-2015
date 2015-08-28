package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CmsCustomerDao;
import com.ebiz.mmt.web.struts.inter.form.CmsCustomerVO;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class CmsCustomerDaoSqlMapImpl extends EntityDaoSqlMapImpl<CmsCustomerVO> implements CmsCustomerDao {
	@Override
	public List<CmsCustomerVO> selectCmsCustomerList(CmsCustomerVO t) throws DataAccessException {
		return super.getSqlMapClientTemplate().queryForList("selectCmsCustomerPaginatedList", t);
	}

	@Override
	public int selectCmsCustomerCount(CmsCustomerVO t) throws DataAccessException {
		return (Integer) super.getSqlMapClientTemplate().queryForObject("selectCmsCustomerCount", t);
	}

}
