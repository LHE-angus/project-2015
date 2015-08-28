package com.ebiz.mmt.dao;

import com.ebiz.ssi.dao.EntityDao;
import com.ebiz.mmt.domain.KonkaParagonShowinfo;
import com.ebiz.mmt.domain.KonkaParagonShowshopDetail;

public interface KonkaParagonShowshopDetailDao extends
		EntityDao<KonkaParagonShowshopDetail> {
	public void auotInsertKonkaParagonEQU(KonkaParagonShowinfo t);

	public void auotInsertKonkaParagonSHT(KonkaParagonShowinfo t);

	public void auotInsertKonkaParagonSAL(KonkaParagonShowinfo t);

	public void auotInsertKonkaParagonETC(KonkaParagonShowinfo t);

	public void auotInsertKonkaParagonMAN(KonkaParagonShowinfo t);
}