package com.ebiz.mmt.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JcfxKczzKhfzDao;
import com.ebiz.mmt.domain.JcfxKczzKhfz;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-23 21:16:26
 */
@Service
public class JcfxKczzKhfzDaoSqlMapImpl extends EntityDaoSqlMapImpl<JcfxKczzKhfz> implements JcfxKczzKhfzDao {

	@Override
	public Long selectJcfxKczzKhfzLBCount(JcfxKczzKhfz v) {
		 return (Long) super.getSqlMapClientTemplate().queryForObject("selectJcfxKczzKhfzLBCount", v);
	}

	@Override
	public List<JcfxKczzKhfz> selectJcfxKczzKhfzLBPaginatedList(JcfxKczzKhfz v) {
		return super.getSqlMapClientTemplate().queryForList("selectJcfxKczzKhfzLBPaginatedList", v);
	}

}
