package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AreaFightInfoDao;
import com.ebiz.mmt.domain.AreaFightInfo;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class AreaFightInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<AreaFightInfo> implements AreaFightInfoDao {

	@Override
	public Long selectTotalCount(AreaFightInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectTotalCount", t);
	}

	@Override
	public List<HashMap> selectAreaFightListForMap(AreaFightInfo t) {
		HashMap map = new HashMap();
		if(null != t.getArea_name()){
			map.put("I_AREA_NAME", t.getArea_name().toString());
		}
		if(null != t.getMap().get("province")){
			map.put("I_PROVINCE", t.getMap().get("province").toString());
		}
		if(null != t.getMap().get("city")){
			map.put("I_CITY", t.getMap().get("city").toString());
		}
		if(null != t.getMap().get("country")){
			map.put("I_COUNTRY", t.getMap().get("country").toString());
		}
		if(null != t.getMap().get("town")){
			map.put("I_TOWN", t.getMap().get("town").toString());
		}
		if(null != t.getMap().get("p_index")){
			map.put("I_P_INDEX", t.getMap().get("p_index").toString());
		}
		if(null != t.getT_type()){
			map.put("I_T_TYPE", t.getT_type().toString());
		}
		if(null != t.getT_status()){
			map.put("I_T_STATUS", t.getT_status().toString());
		}
		if(null != t.getMap().get("modify_name")){
			map.put("I_MODIFY_NAME", t.getMap().get("modify_name").toString());
		}
		if(null != t.getMap().get("modify_date")){
			map.put("I_MODIFY_DATE", t.getMap().get("modify_date").toString());
		}
		if(null != t.getJd_in()){
			map.put("I_JD_IN", t.getJd_in().toString());
		}
		if(null != t.getKonka_in()){
			map.put("I_KONKA_IN", t.getKonka_in().toString());
		}
		if(null != t.getKonka_rank()){
			map.put("I_KONKA_RANK", t.getKonka_rank().toString());
		}
		if(null != t.getFirst_comp()){
			map.put("I_FIRST_COMP", t.getFirst_comp().toString());
		}
		if(null != t.getSecond_comp()){
			map.put("I_SECOND_COMP", t.getSecond_comp().toString());
		}
		if(null != t.getThird_comp()){
			map.put("I_THIRD_COMP", t.getThird_comp().toString());
		}
		if(null != t.getMap().get("market_money_begin")){
			map.put("I_M_MONEY_B", t.getMap().get("market_money_begin").toString());
		}
		if(null != t.getMap().get("market_money_end")){
			map.put("I_M_MONEY_E", t.getMap().get("market_money_end").toString());
		}
		if(null != t.getMap().get("market_num_begin")){
			map.put("I_M_NUM_B", t.getMap().get("market_num_begin").toString());
		}
		if(null != t.getMap().get("market_num_end")){
			map.put("I_M_NUM_E", t.getMap().get("market_num_end").toString());
		}
		if(null != t.getMap().get("market_prop_begin")){
			map.put("I_M_RATE_B", t.getMap().get("market_prop_begin").toString());
		}
		if(null != t.getMap().get("market_prop_end")){
			map.put("I_M_RATE_E", t.getMap().get("market_prop_end").toString());
		}
		if(null != t.getMap().get("t_num_begin")){
			map.put("I_T_NUM_B", t.getMap().get("t_num_begin").toString());
		}
		if(null != t.getMap().get("t_num_end")){
			map.put("I_T_NUM_E", t.getMap().get("t_num_end").toString());
		}
		if(null != t.getMap().get("gdp_end")){
			map.put("I_GDP_B", t.getMap().get("gdp_begin").toString());
		}
		if(null != t.getMap().get("gdp_begin")){
			map.put("I_GDP_E", t.getMap().get("gdp_end").toString());
		}
		if(null != t.getMap().get("human_num_begin")){
			map.put("I_HUMAN_B", t.getMap().get("human_num_begin").toString());
		}
		if(null != t.getMap().get("human_num_end")){
			map.put("I_HUMAN_E", t.getMap().get("human_num_end").toString());
		}
		if(null != t.getMap().get("area_size_begin")){
			map.put("I_AREA_B", t.getMap().get("area_size_begin").toString());
		}
		if(null != t.getMap().get("area_size_end")){
			map.put("I_AREA_E", t.getMap().get("area_size_end").toString());
		}
		if(null != t.getMap().get("dept_id")){
			map.put("I_DEPT_ID", t.getMap().get("dept_id").toString());
		}
		if(null != t.getMap().get("export")){
			map.put("I_EXPORT", "1");
		}
		map.put("FIRST_ROW", t.getRow().getFirst()+1);
		map.put("COUNT_ROW", t.getRow().getCount()+t.getRow().getFirst());

		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectAreaFightListForMap", map);//调用
		list.add(map);
		return list;
	}

	@Override
	public Long selectDetailCount(AreaFightInfo t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectDetailCount", t);
	}

	@Override
	public List<HashMap> selectAreaFightDetailList(AreaFightInfo t) {
		Map map = new HashMap();
		if(null != t.getMap().get("p_index")){
			map.put("I_P_INDEX", t.getMap().get("p_index").toString());
		}
		map.put("FIRST_ROW", t.getRow().getFirst()+1);
		map.put("COUNT_ROW", t.getRow().getCount()+t.getRow().getFirst());

		List<HashMap> list = super.getSqlMapClientTemplate().queryForList("selectAreaFightDetailList", map);//调用
		
		return list;
	}

	@Override
	public void autoCountAreaInfo() throws Exception {
		super.getSqlMapClientTemplate().queryForObject("autoCountAreaInfo");
	}
}
