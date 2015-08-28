package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3DeptStockInfo;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-11 12:22:48
 */
public interface KonkaR3DeptStockInfoDao extends EntityDao<KonkaR3DeptStockInfo> {
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-13
	 */
	List<KonkaR3DeptStockInfo> selectKonkaR3DeptStockInfoListForClass(KonkaR3DeptStockInfo t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-13
	 */
	List<KonkaR3DeptStockInfo> selectKonkaR3DeptStockInfoListForZzl(KonkaR3DeptStockInfo t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-16
	 */
	List<KonkaR3DeptStockInfo> selectKonkaR3DeptStockInfoListForFgsZzl(KonkaR3DeptStockInfo t);
	
	/**
	 * @author Hu,Hao
	 * @version 2013-12-16
	 */
	List<KonkaR3DeptStockInfo> selectKonkaR3DeptStockInfoListForMaxDate(KonkaR3DeptStockInfo t);
}
