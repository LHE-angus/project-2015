package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.VADetailsOfSalesDataDao;
import com.ebiz.mmt.domain.VADetailsOfSalesData;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2013-09-08 12:53:15
 */
@Service
public class VADetailsOfSalesDataDaoSqlMapImpl extends EntityDaoSqlMapImpl<VADetailsOfSalesData> implements
		VADetailsOfSalesDataDao {

	@SuppressWarnings("unchecked")
	public List<VADetailsOfSalesData> selectVADetailsOfSalesDataListForFX(VADetailsOfSalesData t) {
		return this.getSqlMapClientTemplate().queryForList("selectVADetailsOfSalesDataListForFX", t);
	}
	
	@SuppressWarnings("unchecked")
	public List<HashMap> selectVADetailsOfSalesDataListForFXNew(VADetailsOfSalesData t) {
		return this.getSqlMapClientTemplate().queryForList("selectVADetailsOfSalesDataListForFXNew", t);
	}

	@Override
	public List<HashMap> selectKonkaMobileSailDataForSum(VADetailsOfSalesData t) {
		return this.getSqlMapClientTemplate().queryForList("selectVADetailsOfSalesDataListSum", t);
	}

	@Override
	public List<HashMap> selectVADetailsOfSalesDataListForMap(
			VADetailsOfSalesData t) {
		HashMap map = new HashMap();
		if(null != t.getDept_id()){
			map.put("I_DEPT_ID", t.getDept_id().toString());
		}
		if(null != t.getMap().get("kh_name_like")){
			map.put("I_CUST_NAME", t.getMap().get("kh_name_like").toString());
		}
		if(null != t.getCustomer_r3_code()){
			map.put("I_R3_CODE", t.getCustomer_r3_code());
		}
		if(null != t.getL4_dept_id()){
			map.put("I_L4_DEPT_ID", t.getL4_dept_id().toString());
		}
		if(null != t.getL5_dept_id()){
			map.put("I_L5_DEPT_ID", t.getL5_dept_id().toString());
		}
		if(null != t.getPar_customer_type()){
			map.put("I_CUST_TYPE1", t.getPar_customer_type());
		}
		if(null != t.getCustomer_type()){
			map.put("I_CUST_TYPE2", t.getCustomer_type());
		}
		if(null != t.getMap().get("is_4k")){
			map.put("I_IS_4K", t.getMap().get("is_4k").toString());
		}
		if(null != t.getLabel_db()){
			map.put("I_IS_DB", t.getLabel_db().toString());
		}
		if(null != t.getLabel_int()){
			map.put("I_IS_AZ", t.getLabel_int().toString());
		}
		if(null != t.getLabel_3d()){
			map.put("I_IS_3D", t.getLabel_3d().toString());
		}
		if(null != t.getMap().get("is_ytv")){
			map.put("I_IS_YTV", t.getMap().get("is_ytv").toString());
		}
		if(null != t.getMd_serise()){
			map.put("I_P_SERISE", t.getMd_serise());
		}
		if(null != t.getMap().get("md_serise_like")){
			map.put("I_P_SERISE_LIKE", t.getMap().get("md_serise_like").toString());
		}
		if(null != t.getSize_sec()){
			map.put("I_P_SIZE", t.getSize_sec().toString());
		}
		if(null != t.getMap().get("date_begin")){
			map.put("I_DATE_BEGIN", t.getMap().get("date_begin").toString());
		}
		if(null != t.getMap().get("date_end")){
			map.put("I_DATE_END", t.getMap().get("date_end").toString());
		}
		if(null != t.getMap().get("model_name_like")){
			map.put("I_MODEL", t.getMap().get("model_name_like").toString());
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

		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectVADetailsOfSalesDataListForMap", map);//调用
		list.add(map);
		return list;
	}
	
	@Override
	public List<HashMap> selectCustomModelList(VADetailsOfSalesData t) {
		HashMap map = new HashMap();
		if(null != t.getMap().get("s_r3_id")){
			map.put("I_R3_ID", t.getMap().get("s_r3_id").toString());
		}
		if(null != t.getMap().get("s_r3_code")){
			map.put("I_R3_CODE", t.getMap().get("s_r3_code").toString());
		}
		if(null != t.getMap().get("is_4k")){
			map.put("I_IS_4K", t.getMap().get("is_4k").toString());
		}
		if(null != t.getLabel_db()){
			map.put("I_IS_DB", t.getLabel_db().toString());
		}
		if(null != t.getLabel_int()){
			map.put("I_IS_AZ", t.getLabel_int().toString());
		}
		if(null != t.getLabel_3d()){
			map.put("I_IS_3D", t.getLabel_3d().toString());
		}
		if(null != t.getMap().get("is_ytv")){
			map.put("I_IS_YTV", t.getMap().get("is_ytv").toString());
		}
		if(null != t.getMd_serise()){
			map.put("I_P_SERISE", t.getMd_serise());
		}
		if(null != t.getMap().get("md_serise_like")){
			map.put("I_P_SERISE_LIKE", t.getMap().get("md_serise_like").toString());
		}
		if(null != t.getSize_sec()){
			map.put("I_P_SIZE", t.getSize_sec().toString());
		}
		if(null != t.getMap().get("date_begin")){
			map.put("I_DATE_BEGIN", t.getMap().get("date_begin").toString());
		}
		if(null != t.getMap().get("date_end")){
			map.put("I_DATE_END", t.getMap().get("date_end").toString());
		}
		if(null != t.getMap().get("model_name_like")){
			map.put("I_MODEL", t.getMap().get("model_name_like").toString());
		}
		
		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectCustomModelList", map);//调用
		return list;
	}

	@Override
	public List<HashMap> selectModelInDetailsList(VADetailsOfSalesData t) {
		return this.getSqlMapClientTemplate().queryForList("selectModelDetailsList", t);
	}
	
	@Override
	public List<HashMap> selectModelOutDetailsList(VADetailsOfSalesData t) {
		return this.getSqlMapClientTemplate().queryForList("selectSaleModelDetailsList", t);
	}
	
	@Override
	public List<HashMap> selectSalesDataOfDeptListForMap(VADetailsOfSalesData t) {
		HashMap map = new HashMap();
		if(null != t.getDept_id()){
			map.put("I_DEPT_ID", t.getDept_id().toString());
		}
		if(null != t.getL4_dept_id()){
			map.put("I_L4_DEPT_ID", t.getL4_dept_id().toString());
		}
		if(null != t.getL5_dept_id()){
			map.put("I_L5_DEPT_ID", t.getL5_dept_id().toString());
		}
		if(null != t.getMap().get("is_4k")){
			map.put("I_IS_4K", t.getMap().get("is_4k").toString());
		}
		if(null != t.getLabel_db()){
			map.put("I_IS_DB", t.getLabel_db().toString());
		}
		if(null != t.getLabel_int()){
			map.put("I_IS_AZ", t.getLabel_int().toString());
		}
		if(null != t.getLabel_3d()){
			map.put("I_IS_3D", t.getLabel_3d().toString());
		}
		if(null != t.getMap().get("is_ytv")){
			map.put("I_IS_YTV", t.getMap().get("is_ytv").toString());
		}
		if(null != t.getMd_serise()){
			map.put("I_P_SERISE", t.getMd_serise());
		}
		if(null != t.getMap().get("md_serise_like")){
			map.put("I_P_SERISE_LIKE", t.getMap().get("md_serise_like").toString());
		}
		if(null != t.getSize_sec()){
			map.put("I_P_SIZE", t.getSize_sec().toString());
		}
		if(null != t.getMap().get("date_begin")){
			map.put("I_DATE_BEGIN", t.getMap().get("date_begin").toString());
		}
		if(null != t.getMap().get("date_end")){
			map.put("I_DATE_END", t.getMap().get("date_end").toString());
		}
		if(null != t.getMap().get("model_name_like")){
			map.put("I_MODEL", t.getMap().get("model_name_like").toString());
		}
		
		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectSaleDataOfDeptList", map);//调用
		list.add(map);
		return list;
	}

	@Override
	public List<HashMap> selectSalesDataOfChannelListForMap(
			VADetailsOfSalesData t) {
		HashMap map = new HashMap();
		if(null != t.getDept_id()){
			map.put("I_DEPT_ID", t.getDept_id().toString());
		}
		if(null != t.getL4_dept_id()){
			map.put("I_L4_DEPT_ID", t.getL4_dept_id().toString());
		}
		if(null != t.getL5_dept_id()){
			map.put("I_L5_DEPT_ID", t.getL5_dept_id().toString());
		}
		if(null != t.getPar_customer_type()){
			map.put("I_CUST_TYPE1", t.getPar_customer_type());
		}
		if(null != t.getCustomer_type()){
			map.put("I_CUST_TYPE2", t.getCustomer_type());
		}
		if(null != t.getMap().get("is_4k")){
			map.put("I_IS_4K", t.getMap().get("is_4k").toString());
		}
		if(null != t.getLabel_db()){
			map.put("I_IS_DB", t.getLabel_db().toString());
		}
		if(null != t.getLabel_int()){
			map.put("I_IS_AZ", t.getLabel_int().toString());
		}
		if(null != t.getLabel_3d()){
			map.put("I_IS_3D", t.getLabel_3d().toString());
		}
		if(null != t.getMap().get("is_ytv")){
			map.put("I_IS_YTV", t.getMap().get("is_ytv").toString());
		}
		if(null != t.getMd_serise()){
			map.put("I_P_SERISE", t.getMd_serise());
		}
		if(null != t.getMap().get("md_serise_like")){
			map.put("I_P_SERISE_LIKE", t.getMap().get("md_serise_like").toString());
		}
		if(null != t.getSize_sec()){
			map.put("I_P_SIZE", t.getSize_sec().toString());
		}
		if(null != t.getMap().get("date_begin")){
			map.put("I_DATE_BEGIN", t.getMap().get("date_begin").toString());
		}
		if(null != t.getMap().get("date_end")){
			map.put("I_DATE_END", t.getMap().get("date_end").toString());
		}
		if(null != t.getMap().get("model_name_like")){
			map.put("I_MODEL", t.getMap().get("model_name_like").toString());
		}
		
		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectSaleDataOfChannelList", map);//调用
		list.add(map);
		return list;
	}

	@Override
	public List<HashMap> selectChannelDetailsList(VADetailsOfSalesData t) {
		HashMap map = new HashMap();
		if(null != t.getDept_id()){
			map.put("I_DEPT_ID", t.getDept_id().toString());
		}
		if(null != t.getL4_dept_id()){
			map.put("I_L4_DEPT_ID", t.getL4_dept_id().toString());
		}
		if(null != t.getL5_dept_id()){
			map.put("I_L5_DEPT_ID", t.getL5_dept_id().toString());
		}
		if(null != t.getMap().get("par_index")){
			map.put("I_PAR_INDEX", t.getMap().get("par_index").toString());
		}
		if(null != t.getMap().get("is_4k")){
			map.put("I_IS_4K", t.getMap().get("is_4k").toString());
		}
		if(null != t.getLabel_db()){
			map.put("I_IS_DB", t.getLabel_db().toString());
		}
		if(null != t.getLabel_int()){
			map.put("I_IS_AZ", t.getLabel_int().toString());
		}
		if(null != t.getLabel_3d()){
			map.put("I_IS_3D", t.getLabel_3d().toString());
		}
		if(null != t.getMap().get("is_ytv")){
			map.put("I_IS_YTV", t.getMap().get("is_ytv").toString());
		}
		if(null != t.getMd_serise()){
			map.put("I_P_SERISE", t.getMd_serise());
		}
		if(null != t.getMap().get("md_serise_like")){
			map.put("I_P_SERISE_LIKE", t.getMap().get("md_serise_like").toString());
		}
		if(null != t.getSize_sec()){
			map.put("I_P_SIZE", t.getSize_sec().toString());
		}
		if(null != t.getMap().get("date_begin")){
			map.put("I_DATE_BEGIN", t.getMap().get("date_begin").toString());
		}
		if(null != t.getMap().get("date_end")){
			map.put("I_DATE_END", t.getMap().get("date_end").toString());
		}
		if(null != t.getMap().get("model_name_like")){
			map.put("I_MODEL", t.getMap().get("model_name_like").toString());
		}
		
		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectChannelDetailsList", map);//调用
		return list;
	}
}
