package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBindingPdDao;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-10 11:43:03
 */
@Service
public class EcBindingPdDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcBindingPd> implements EcBindingPdDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-09-10
	 */
	@SuppressWarnings("unchecked")
	public List<EcBindingPd> selectEcBindingPdWithGoodsIdAndTypeList(EcBindingPd t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectEcBindingPdWithGoodsIdAndTypeList", t);
	}

}
