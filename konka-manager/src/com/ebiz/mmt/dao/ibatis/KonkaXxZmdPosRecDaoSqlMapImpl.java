package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxZmdPosRecDao;
import com.ebiz.mmt.domain.KonkaXxZmdPosRec;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Ren,zhong
 * @version 2012-05-02 15:14
 */
@Service
public class KonkaXxZmdPosRecDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaXxZmdPosRec> implements KonkaXxZmdPosRecDao {

	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-03
	 */
	public Long selectKonkaXxZmdPosRecForZmdSnCount(KonkaXxZmdPosRec t){
		return (Long) super.getSqlMapClientTemplate().queryForObject("selectKonkaXxZmdPosRecForZmdSnCount",t);
	}
	
	/**
	 * 
	 * @author Hu Hao
	 * @version 2012-05-03
	 */
	@SuppressWarnings("unchecked")
	public List<KonkaXxZmdPosRec> selectKonkaXxZmdPosRecForZmdSnPaginatedList(KonkaXxZmdPosRec t){
		return super.getSqlMapClientTemplate().queryForList("selectKonkaXxZmdPosRecForZmdSnPaginatedList", t);
	}
}
