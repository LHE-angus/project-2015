package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxPropCategoryDao;
import com.ebiz.mmt.domain.KonkaXxPropCategory;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-01 16:53:38
 */
@Service
public class KonkaXxPropCategoryDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxPropCategory> implements KonkaXxPropCategoryDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-04-03
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxPropCategory> selectKonkaXxPropCategoryForPdNamePaginatedList(KonkaXxPropCategory t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxPropCategoryForPdNamePaginatedList", t);
	}
}
