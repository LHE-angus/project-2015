package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3ShopBrandDao;
import com.ebiz.mmt.domain.KonkaR3ShopBrand;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class KonkaR3ShopBrandDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3ShopBrand> implements KonkaR3ShopBrandDao {

	@Override
	public void deleteKonkaR3ShopBrandForR3(KonkaR3ShopBrand t) {
		this.getSqlMapClientTemplate().update("deleteKonkaR3ShopBrandForR3", t);
	}

	@Override
	public String computerData() {
		Map map = new HashMap();
		map.put("my_res", "");
		super.getSqlMapClientTemplate().queryForObject("salesDataPro",map);
		return map.get("my_res").toString(); 
	}
}
