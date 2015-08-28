package com.ebiz.mmt.dao.ibatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaDeptDao;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-08-25 08:32:14
 */
@Service
public class KonkaDeptDaoSqlMapImpl extends EntityDaoSqlMapImpl<KonkaDept> implements KonkaDeptDao {

	/**
	 * @author Xing,XiuDong
	 * @version 2011-09-01
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaDept> selectKonkaDeptListWithTreeNameAndFullName(KonkaDept t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptListWithTreeNameAndFullName", t);
	}

	/**
	 * @author Cheng,Bing
	 * @version 2011-09-29
	 */
	@Override
	public Long selectKonkaDeptTreeNameByUserForResultCount(KonkaDept t) throws DataAccessException {
		KonkaDept dept = (KonkaDept) this.getSqlMapClientTemplate().queryForObject(
		        "selectKonkaDeptTreeNameByUserForResultCount", t);
		Integer count = (Integer) dept.getMap().get("dept_cout");
		return count.longValue();
	}

	/**
	 * @author Cheng,Bing
	 * @version 2011-09-29
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaDept> selectKonkaDeptTreeNameByUserForResultList(KonkaDept t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptTreeNameByUserForResultList", t);
	}

	/**
	 * 子查父部门
	 */
	@Override
	public List<KonkaDept> selectKonkaDeptByDeptId(KonkaDept t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptByDeptId", t);
	}

	/**
	 * @author Cheng,Bing
	 * @version 2011-09-29
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaDept> selectKonkaDeptListWithTreeNameForProdUser(KonkaDept t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptListWithTreeNameForProdUser", t);
	}

	/**
	 * @author Dou,QingRen
	 * @version 2012-03-31
	 * @desc 由下往上查询
	 * @return 父节点树状图
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaDept> selectKonkaDeptListWithParTreeName(KonkaDept t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptListWithParTreeName", t);
	}

	/**
	 * 子查父
	 */
	@Override
	public KonkaDept selectKonkaDeptSuperiorByDeptId(KonkaDept t) throws DataAccessException {
		return (KonkaDept) this.getSqlMapClientTemplate().queryForObject("selectKonkaDeptSuperiorByDeptId", t);
	}


	/**
	 * @author Pan,Gang
	 * @version 2013-08-12
	 * @desc 父查子
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaDept> selectKonkaDeptListForPaifa(KonkaDept t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptListForPaifa", t);
	}

	/**
	 * 查用户的部门
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaDept> selectKonkaDeptListOfUser(KonkaDept t) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptListOfUser", t);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<KonkaDept> selectKonkaDeptAndJbNameList(KonkaDept t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptAndJbNameList", t);
	}

	@Override
	public List<HashMap> selectAjaxKonkaDeptList(KonkaDept t){
		return this.getSqlMapClientTemplate().queryForList("selectAjaxKonkaDeptList", t);
	}

	/**
	 * 获取用户获取所在下拉部门列表
	 * 
	 * 父查子
	 */
	@Override
	public List<KonkaDept> selectKonkaDeptListForUser(KonkaDept t)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptListForUser", t);
	}

	@Override
	public List<HashMap> selectKonkaDeptTreeNameForProdUser(KonkaDept t) {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptTreeNameForProdUser", t);
	}

	@Override
	public KonkaDept selectFgsByDeptId(KonkaDept t) throws DataAccessException {
		return (KonkaDept) this.getSqlMapClientTemplate().queryForObject("selectFgsByDeptId", t);
	}

//通过上级部门取下级部门
	@Override
	public List<KonkaDept> selectKonkaDeptListWithUserDeptId(KonkaDept t)
		throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectKonkaDeptListWithUserDeptId", t);
	}
	//查询无考勤记录的分公司
	public List<KonkaDept> selectDept() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("selectDept");
		
	}
}
