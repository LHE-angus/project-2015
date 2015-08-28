package com.ebiz.mmt.dao.ibatis;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3StoreShowDao;
import com.ebiz.mmt.domain.KonkaR3StoreShow;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-05-05 14:37:11
 */
@Service
public class KonkaR3StoreShowDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3StoreShow> implements KonkaR3StoreShowDao {

	public String insertKonkaR3StoreShowList(List<KonkaR3StoreShow> list) {
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			KonkaR3StoreShow t = list.get(i);
			KonkaR3StoreShow ks = new KonkaR3StoreShow();
			ks.setDept_name(t.getDept_name());
			ks.setYear(t.getYear());
			ks.setMonth(t.getMonth());
			ks.setStore_name(t.getStore_name());
			ks.setTask_type(t.getTask_type());
			ks.setCategory_name(t.getCategory_name());
			List<KonkaR3StoreShow> entityList = this.selectEntityList(ks);
			//以前有限制，每年月，每个分店，每个类型，每个品类只能有一条记录，现在不需要限制。
			
			//2014-08-15日提出还是需要限制的。
			
			if (null != entityList && entityList.size() > 0) {
				for (KonkaR3StoreShow konkaR3StoreShow : entityList) {
					this.getSqlMapClientTemplate().delete("deleteKonkaR3StoreShow", konkaR3StoreShow);
				}
				this.getSqlMapClientTemplate().insert("insertKonkaR3StoreShow", t);
			} else {
				this.getSqlMapClientTemplate().insert("insertKonkaR3StoreShow", t);
			}
		}

		return msg;
	}

	public String insertKonkaR3StoreShowList(HashSet<KonkaR3StoreShow> list) {
		String msg = "";

		for (KonkaR3StoreShow konkaR3StoreShow1 : list) {
			KonkaR3StoreShow ks = new KonkaR3StoreShow();
			ks.setDept_name(konkaR3StoreShow1.getDept_name());
			ks.setYear(konkaR3StoreShow1.getYear());
			ks.setMonth(konkaR3StoreShow1.getMonth());
			ks.setStore_name(konkaR3StoreShow1.getStore_name());
			ks.setTask_type(konkaR3StoreShow1.getTask_type());
			List<KonkaR3StoreShow> entityList = this.selectEntityList(ks);
			if (null != entityList && entityList.size() > 0) {
				for (KonkaR3StoreShow konkaR3StoreShow : entityList) {
					this.getSqlMapClientTemplate().delete("deleteKonkaR3StoreShow", konkaR3StoreShow);
				}
				this.getSqlMapClientTemplate().insert("insertKonkaR3StoreShow", konkaR3StoreShow1);
			} else {
				this.getSqlMapClientTemplate().insert("insertKonkaR3StoreShow", konkaR3StoreShow1);
			}
		}

		return msg;
	}

	@Override
	public Long selectKonkaR3StoreShowLBCount(KonkaR3StoreShow entity) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaR3StoreShowLBCount",entity);
	}

	@Override
	public List<KonkaR3StoreShow> selectKonkaR3StoreShowLBPaginatedList(
			KonkaR3StoreShow entity) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3StoreShowLBPaginatedList", entity);
	}

}
