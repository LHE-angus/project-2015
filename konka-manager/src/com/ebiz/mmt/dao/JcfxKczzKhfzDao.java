package com.ebiz.mmt.dao;

import java.util.List;
import com.ebiz.mmt.domain.JcfxKczzKhfz;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-23 21:16:25
 */
public interface JcfxKczzKhfzDao extends EntityDao<JcfxKczzKhfz> {
	 Long selectJcfxKczzKhfzLBCount(JcfxKczzKhfz v);
		
	 List<JcfxKczzKhfz> selectJcfxKczzKhfzLBPaginatedList(JcfxKczzKhfz v);

}
