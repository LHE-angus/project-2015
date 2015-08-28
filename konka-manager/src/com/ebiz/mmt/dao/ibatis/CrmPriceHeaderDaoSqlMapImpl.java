package com.ebiz.mmt.dao.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.CrmPriceHeaderDao;
import com.ebiz.mmt.domain.CrmPriceHeader;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class CrmPriceHeaderDaoSqlMapImpl extends EntityDaoSqlMapImpl<CrmPriceHeader> implements CrmPriceHeaderDao {

	@Override
	public String getNextSeqId() {
		try {
			return (String) super.getSqlMapClient().queryForObject("selectNextIdFromSeq");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<CrmPriceHeader> selectEntityListFiterByTime(CrmPriceHeader arg0)
            throws DataAccessException, SQLException {
        return super.getSqlMapClientTemplate().queryForList("selectCrmPriceHeaderListFilterByTime",
                arg0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CrmPriceHeader> selectListOfDeptIdGroupCodePriceTypeAndTime(
            HashMap<String, Object> paramMap) {
        return super.getSqlMapClientTemplate().queryForList(
                "selectListOfDeptIdGroupCodePriceTypeAndTime",
                paramMap);
    }

}
