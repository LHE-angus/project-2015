package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaR3DeptStockInfoDao;
import com.ebiz.mmt.domain.KonkaR3DeptStockInfo;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-11 12:22:48
 */
@Service
public class KonkaR3DeptStockInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaR3DeptStockInfo> implements
		KonkaR3DeptStockInfoDao {

	/**
	 * @author Hu,Hao
	 * @version 2013-12-13
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3DeptStockInfo> selectKonkaR3DeptStockInfoListForClass(KonkaR3DeptStockInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3DeptStockInfoListForClass", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-13
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3DeptStockInfo> selectKonkaR3DeptStockInfoListForZzl(KonkaR3DeptStockInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3DeptStockInfoListForZzl", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-16
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3DeptStockInfo> selectKonkaR3DeptStockInfoListForFgsZzl(KonkaR3DeptStockInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3DeptStockInfoListForFgsZzl", t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-16
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaR3DeptStockInfo> selectKonkaR3DeptStockInfoListForMaxDate(KonkaR3DeptStockInfo t) {
		return super.getSqlMapClientTemplate().queryForList("selectKonkaR3DeptStockInfoListForMaxDate", t);
	}
}
