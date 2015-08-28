package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PePdModelDao;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-21 09:45:12
 */
@Service
public class PePdModelDaoSqlMapImpl extends EntityDaoSqlMapImpl<PePdModel> implements PePdModelDao {

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-04-02
	 */
	@SuppressWarnings("unchecked")
	public List<PePdModel> selectPePdModulePaginatedIncludeMdNameList(PePdModel t) {
		return this.getSqlMapClientTemplate().queryForList("selectPePdModulePaginatedIncludeMdNameList", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-05-31
	 */
	@SuppressWarnings("unchecked")
	public List<PePdModel> selectPePdModelForB2bList(PePdModel t) {
		return this.getSqlMapClientTemplate().queryForList("selectPePdModelForB2bList", t);
	}

	/**
	 * @author Li,Yuan
	 * @version 2011-06-02
	 */
	@SuppressWarnings("unchecked")
	public List<PePdModel> selectPePdModelForB2bWithNameList(PePdModel t) {
		return this.getSqlMapClientTemplate().queryForList("selectPePdModelForB2bWithNameList", t);
	}

	/**
	 * @author Li,Ka
	 * @version 2011-12-01 去康佳品牌的型号带大类名称
	 */
	@SuppressWarnings("unchecked")
	public List<PePdModel> selectPePdModelWithClsNameBrandNameList(PePdModel t) {
		return this.getSqlMapClientTemplate().queryForList("selectPePdModelWithClsNameBrandNameList", t);
	}

	/**
	 * @author Ren,zhong
	 * @version 2013-07-04
	 */
	@SuppressWarnings("unchecked")
	public List<PePdModel> selectPePdModelWithClsNameAndParClsNameList(PePdModel t) {
		return this.getSqlMapClientTemplate().queryForList("selectPePdModelWithClsNameAndParClsNameList", t);
	}

	/**
	 * @author Li,ZhiXiang
	 * @version 2013-09-23
	 */
	@SuppressWarnings("unchecked")
	public List<PePdModel> selectPePdModelListForMdSerise(PePdModel t) {
		return this.getSqlMapClientTemplate().queryForList("selectPePdModelListForMdSerise", t);
	}
	
	/**
	 * @author Xiao,GuoJian
	 * @version 2014-03-09
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HashMap> selectPePdModelListForMdSize(PePdModel t){
		return this.getSqlMapClientTemplate().queryForList("selectPePdModelForMdSize", t);
	}

	/**
	 * @author Liang,HouEn
	 * @date 2014-08-13
	 */
	@Override
	public List<HashMap> selectPePdModelMapList(PePdModel t) {
		
		return this.getSqlMapClientTemplate().queryForList("selectPePdModelMapList", t);
	}
}
