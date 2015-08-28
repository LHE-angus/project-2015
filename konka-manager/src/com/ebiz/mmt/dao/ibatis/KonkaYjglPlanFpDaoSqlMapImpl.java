package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaYjglPlanFpDao;
import com.ebiz.mmt.domain.KonkaYjglPlanFp;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-11-03 14:08:34
 */
@Service
public class KonkaYjglPlanFpDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaYjglPlanFp> implements KonkaYjglPlanFpDao {

	@Override
	public Long selectKonkaYjglPlanFpAndDeptNameCount(KonkaYjglPlanFp t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaYjglPlanFpAndDeptNameCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaYjglPlanFp> selectKonkaYjglPlanFpAndDeptNamePaginatedList(KonkaYjglPlanFp t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaYjglPlanFpAndDeptNamePaginatedList", t);
	}

	@Override
	public String insertKonkaYjglPlanFp(List<KonkaYjglPlanFp> list) {
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			KonkaYjglPlanFp t = list.get(i);
			this.getSqlMapClientTemplate().insert("insertKonkaYjglPlanFp", t);
			if (!"".equals(msg)) {
				try {
					throw new Exception(msg);
				} catch (Exception e) {
				}
			}
		}
		return msg;
	}

	@Override
	public Long selectKonkaYjglPlanFpAndTjCount(KonkaYjglPlanFp t) {
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaYjglPlanFpAndTjCount", t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KonkaYjglPlanFp> selectKonkaYjglPlanFpAndTjPaginatedList(KonkaYjglPlanFp t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaYjglPlanFpAndTjPaginatedList", t);
	}

}
