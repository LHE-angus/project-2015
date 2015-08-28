package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3StoreDao;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-04-26 10:40:24
 */
@Service
public class KonkaR3StoreDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3Store> implements KonkaR3StoreDao {

	/**
	 * @author Xing,XiuDong 根据业务员用户ID查询门店
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3Store> selectKonkaR3StoreListWithYwyUserId(KonkaR3Store t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreListWithYwyUserId", t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-25
	 * @return 根据p_index查询所能获取到的门店数量
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3Store> selectKonkaR3StoreCountByPIndex(KonkaR3Store t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreCountByPIndex", t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-25
	 * @return 根据p_index查询所能获取到的门店零售额
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3Store> selectKonkaR3StoreListByPIndex(KonkaR3Store t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreListByPIndex", t);
	}

	public Long selectKonkaR3StoreForRoleDataCount(KonkaR3Store t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaR3StoreForRoleDataCount", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaR3Store> selectKonkaR3StoreForRoleDataPaginatedList(KonkaR3Store t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreForRoleDataPaginatedList", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-11-26
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3Store> selectKonkaR3StoreForStoresList(KonkaR3Store t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreForStoresList", t);
	}

	public Long selectKonkaR3StoreAndYwyNameCount(KonkaR3Store t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaR3StoreAndYwyNameCount", t);
	}
	
	public Long selectStoreDataCountForVIP(KonkaR3Store t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectStoreDataCountForVIP", t);
	}

	@SuppressWarnings("unchecked")
	public List<KonkaR3Store> selectKonkaR3StoreAndYwyNamePaginatedList(KonkaR3Store t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreAndYwyNamePaginatedList", t);
	}
	
	public List<HashMap> selectStoreDataListForVIP(KonkaR3Store t) {
		return super.getSqlMapClientTemplate().queryForList("selectStoreDataListForVIP", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaR3Store> selectKonkaR3StoreForCustVisit(KonkaR3Store entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectkonkaR3StoreForCustVisit", entity);
	}

	/**
	 * 非促销员查询门店列表
	 */
	@Override
	public List<HashMap> selectKonkaR3StoreAndYwyNamePaginatedNew(KonkaR3Store t) {
		Map map = new HashMap();
		if(null != t.getDept_id()){
			map.put("I_DEPT_ID", t.getDept_id().toString());
		}
        if (null != t.getJb_job_id()) {
            map.put("I_JB_JOB_ID", t.getJb_job_id().toString());
        }
		if(null != t.getMap().get("store_name_like")){
			map.put("I_STORE_NAME", t.getMap().get("store_name_like").toString());
        }
		if(null != t.getStore_id()){
			map.put("I_STORE_CODE", t.getStore_id().toString());
		}
		if(null != t.getMap().get("ywy_user_name_like")){
			map.put("I_YWY_NAME", t.getMap().get("ywy_user_name_like").toString());
		}
		if(null != t.getMap().get("zxy_name_like")){
			map.put("I_CXY_NAME", t.getMap().get("zxy_name_like").toString());
		}
		if(null != t.getMap().get("r3_code_like")){
			map.put("I_R3_CODE_LIKE", t.getMap().get("r3_code_like").toString());
		}
		if(null != t.getR3_code()){
			map.put("I_R3_CODE", t.getR3_code().toString());
		}
		if(null != t.getMap().get("customer_type1")){
			map.put("I_CUST_TYPE1", t.getMap().get("customer_type1").toString());
		}
		if(null != t.getMap().get("customer_type2")){
			map.put("I_CUST_TYPE2", t.getMap().get("customer_type2").toString());
		}
		if(null != t.getIs_del()){
			map.put("I_IS_STOP", t.getIs_del().toString());
		}
		if(null != t.getJb_name()){
			map.put("I_JB_NAME", t.getJb_name().toString());
		}
		if(null != t.getMap().get("kh_name_like")){
			map.put("I_CUSTOMER_NAME", t.getMap().get("kh_name_like").toString());
		}
		if(null != t.getHavaman()){
			map.put("I_HAVE_CXY", t.getHavaman().toString());
		}
		if(null != t.getMap().get("dept_id_start")){
			map.put("I_DEPT_START", t.getMap().get("dept_id_start").toString());
		}
		if(null != t.getMap().get("filter_by_ywy_job_id_eq")){
			map.put("I_YWY_JOB_ID", t.getMap().get("filter_by_ywy_job_id_eq").toString());
		}
		if(null != t.getMap().get("filter_by_job_id_eq")){
			map.put("I_FILTER_JOB_ID", t.getMap().get("filter_by_job_id_eq").toString());
		}
		if(null != t.getMap().get("session_user_id")){
			map.put("SESSION_ID", t.getMap().get("session_user_id").toString());
		}
		if(null != t.getMap().get("only_cxy")){
			map.put("ONLY_CXY", t.getMap().get("only_cxy").toString());
		}
		if(null != t.getMap().get("is_sure1")){
			map.put("IS_SURE1", t.getMap().get("is_sure1").toString());
		}
		if(null != t.getMap().get("is_sure2")){
			map.put("IS_SURE2", t.getMap().get("is_sure2").toString());
		}
		map.put("FIRST_ROW", t.getRow().getFirst()+1);
		map.put("COUNT_ROW", t.getRow().getCount()+t.getRow().getFirst());

		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("queryStoreList", map);//调用
		logger.info(map.get("SQL_CODE").toString());  //打印查询语句
		
		return list;
//		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreAndYwyNamePaginatedNew", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaR3Store> selectKonkaR3StoreForYwyAndCxyList(KonkaR3Store t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreForYwyAndCxyList", t);
	}

	/**
	 *根据促销员查门店
	 */

	@Override
	public Long selectKonkaR3StoreOnlyByCxyCount(KonkaR3Store entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaR3StoreOnlyByCxyCount", entity);
	}

	/**
	 *根据促销员查门店
	 */
	@Override
	public List<HashMap> selectKonkaR3StoreOnlyByCxyPaginatedList(KonkaR3Store entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreOnlyByCxyPaginatedList", entity);
	}

	/**
	 *返回手机首页的终端
	 */
	@Override
	public List<HashMap> selectKonkaR3StoreAndJbasePartnerForMainPage(KonkaR3Store entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreAndJbasePartnerForMainPage", entity);
	}

	@Override
	public List<HashMap> selectKonkaR3StoreAndJbasePartnerForManager(KonkaR3Store entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreAndJbasePartnerForManager", entity);
	}

	@Override
	public Long selectKonkaR3StoreSaleCount(KonkaR3Store entity) {
		
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaR3StoreSaleCount", entity);
	}

	@Override
	public List<KonkaR3Store> selectKonkaR3StoreSalePaginatedList(KonkaR3Store entity) {
		
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreSalePaginatedList", entity);
	}

	@Override
	public List<KonkaR3Store> selectKonkaR3StoreForCustVisit1(
			KonkaR3Store entity) {
		return super.getSqlMapClientTemplate().queryForList("selectkonkaR3StoreForCustVisit1", entity);
	}

	
	/**
	 * 根据客户id查询门店
	 * @author Liang Houen
	 * @since 2015-06-23
	 * @param c_id
	 * @return
	 */
	@Override
	public List<HashMap> selectStoreListByCID(Long c_id) {
		return super.getSqlMapClientTemplate().queryForList("selectStoreListByCID",c_id);
	}

}
