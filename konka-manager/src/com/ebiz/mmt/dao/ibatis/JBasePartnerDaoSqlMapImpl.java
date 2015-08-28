package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JBasePartnerDao;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-06-08 17:03:35
 */
@Service
public class JBasePartnerDaoSqlMapImpl extends EntityDaoSqlMapImpl<JBasePartner> implements JBasePartnerDao {

	
	/**
	 * @author Wang,KunLin
	 * @version 2014-03-31
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<JBasePartner> selectEntityListOnlyName(JBasePartner t) {
		return super.getSqlMapClientTemplate().queryForList("selectEntityListOnlyName", t);
	}

	/**
	 * @author Hu,hao
	 * @version 2013-10-14
	 */
	@SuppressWarnings("unchecked")
	public List<JBasePartner> selectJBasePartnerForBillList(JBasePartner t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBasePartnerForBillList", t);
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	public Long selectJBasePartnerForLevelCount(JBasePartner t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJBasePartnerForLevelCount", t);
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	@SuppressWarnings("unchecked")
	public List<JBasePartner> selectJBasePartnerForLevelPaginatedList(JBasePartner t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBasePartnerForCustomerList", t);
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-02
	 */
	@SuppressWarnings("unchecked")
	public List<JBasePartner> selectJBasePartnerForSonPaginatedList(JBasePartner t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBasePartnerForSonPaginatedList", t);
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-04
	 */
	@SuppressWarnings("unchecked")
	public List<JBasePartner> selectJBasePartnerForFgsKhPaginatedList(JBasePartner t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBasePartnerForFgsKhPaginatedList", t);
	}

	/**
	 * @author Hu,hao
	 * @version 2014-01-04
	 */
	public Long selectJBasePartnerForFgsKhCount(JBasePartner t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJBasePartnerForFgsKhCount", t);
	}

	@Override
	public List<HashMap> selectJBasePartnerMapList(JBasePartner t) {
		return super.getSqlMapClientTemplate().queryForList("selectJBasePartnerMapList", t);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HashMap> selectJBasePartnerNewList(JBasePartner t) {
		Map map = new HashMap();
		if(null != t.getDept_id()){
			map.put("I_DEPT_ID", t.getDept_id().toString());
		}
		if(null != t.getMap().get("partner_name_like")){
			map.put("I_AGENT_NAME", t.getMap().get("partner_name_like").toString());
		}
		if(null != t.getPartner_sn()){
			map.put("I_AGENT_CODE", t.getPartner_sn().toString());
		}
		if(null != t.getP_level()){
			map.put("I_AGENT_LEVE", t.getP_level().toString());
		}
		if(null != t.getP_level()){
			map.put("I_AGENT_LEVE", t.getP_level().toString());
		}
		if(null != t.getMap().get("r3_code")){
			map.put("I_PAR_CODE", t.getMap().get("r3_code").toString());
		}
		if(null != t.getMap().get("kh_name_like")){
			map.put("I_PAR_NAME", t.getMap().get("kh_name_like").toString());
		}
		if(null != t.getMap().get("ywy_name_like")){
			map.put("I_YWY", t.getMap().get("ywy_name_like").toString());
		}
		if(null != t.getMap().get("link_name")){
			map.put("I_LINK_NAME", t.getMap().get("link_name").toString());
		}
		if(null != t.getMap().get("jb_name_like")){
			map.put("I_JING_NAME", t.getMap().get("jb_name_like").toString());
		}
		if(null != t.getIs_del()){
			map.put("I_IS_STOP", t.getIs_del().toString());
		}
		if(null != t.getMap().get("cus_type1")){
			map.put("I_CUST_TYPE1", t.getMap().get("cus_type1").toString());
		}
		if(null != t.getMap().get("cus_type2")){
			map.put("I_CUST_TYPE2", t.getMap().get("cus_type2").toString());
		}
		if(null != t.getMap().get("dept_id_start")){
			map.put("I_DEPT_START", t.getMap().get("dept_id_start").toString());
		}
		if(null != t.getMap().get("filter_by_ywy_id_eq")){
			map.put("I_YWY_ID", t.getMap().get("filter_by_ywy_id_eq").toString());
		}
		if(null != t.getMap().get("session_user_id")){
			map.put("SESSION_ID", t.getMap().get("session_user_id").toString());
		}
		if(null != t.getMap().get("is_sure1")){
			map.put("IS_SURE1", t.getMap().get("is_sure1").toString());
		}
		if(null != t.getMap().get("is_sure2")){
			map.put("IS_SURE2", t.getMap().get("is_sure2").toString());
		}
		map.put("FIRST_ROW", t.getRow().getFirst()+1);
		map.put("COUNT_ROW", t.getRow().getCount()+t.getRow().getFirst());

		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("queryAgentList", map);//调用
		logger.info(map.get("SQL_CODE").toString());  //打印查询语句
		
		return list;
		
//		return super.getSqlMapClientTemplate().queryForList("selectJBasePartnerNewList", t);
	}

	@Override
	public Long selectJBasePartnerNewListCount(JBasePartner t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectJBasePartnerNewListCount", t);
	}

	@Override
	public List<HashMap> selectJBasePartnerLevelList() {
		return super.getSqlMapClientTemplate().queryForList("selectJBasePartnerLevelList");
	}

	@Override
	public HashMap getYWYAndDept(String partner_sn) {
		return (HashMap) super.getSqlMapClientTemplate().queryForObject("getYWYAndDept", partner_sn);
	}
}
