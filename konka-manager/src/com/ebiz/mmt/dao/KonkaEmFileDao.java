package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaEmFile;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-27 17:08:18
 */
public interface KonkaEmFileDao extends EntityDao<KonkaEmFile> {

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-28
	 */
	Long selectKonkaEmFileForViewCount(KonkaEmFile t);

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-28
	 */
	List<KonkaEmFile> selectKonkaEmFileForViewPaginatedList(KonkaEmFile t);

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-10-10
	 */
	List<KonkaEmFile> selectKonkaEmFileForViewList(KonkaEmFile t);
}
