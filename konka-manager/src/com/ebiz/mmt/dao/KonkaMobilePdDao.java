package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobilePd;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Ren,zhong
 * @version 2013-05-31 14:00
 */
public interface KonkaMobilePdDao extends EntityDao<KonkaMobilePd> {

	List<KonkaMobilePd> selectKonkaMobilePdBrandList(KonkaMobilePd t1);

}
