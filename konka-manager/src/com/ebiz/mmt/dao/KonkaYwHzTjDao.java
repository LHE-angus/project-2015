package com.ebiz.mmt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ebiz.mmt.domain.KonkaYwHzTj;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-08-23 17:18:32
 */
public interface KonkaYwHzTjDao extends EntityDao<KonkaYwHzTj> {
	
	void insertKonkaYwHzTjForTongJi(KonkaYwHzTj v);
	
	HashMap selectLastUpdateTime(KonkaYwHzTj v);
	
}
