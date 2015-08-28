package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdRemitRecDao;
import com.ebiz.mmt.domain.KonkaXxZmdRemitRec;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-22 22:22:29
 */
@Service
public class KonkaXxZmdRemitRecDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdRemitRec> implements KonkaXxZmdRemitRecDao {

	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-03-27
	 */
	public Long  selectKonkaXxZmdRemitRecForZmdSnCount(KonkaXxZmdRemitRec t){
		return (Long)super.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdRemitRecForZmdSnCount", t);
	}
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-03-27
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdRemitRec>  selectKonkaXxZmdRemitRecForPaZmdSnginatedList(KonkaXxZmdRemitRec t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdRemitRecForPaZmdSnginatedList", t);
	}
}
