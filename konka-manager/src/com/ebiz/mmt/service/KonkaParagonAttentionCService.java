package com.ebiz.mmt.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.dao.DataAccessException;
import com.ebiz.mmt.domain.KonkaParagonAttentionC;

public interface KonkaParagonAttentionCService {

	Long createKonkaParagonAttentionC(KonkaParagonAttentionC t);

	int modifyKonkaParagonAttentionC(KonkaParagonAttentionC t);

	int removeKonkaParagonAttentionC(KonkaParagonAttentionC t);

	KonkaParagonAttentionC getKonkaParagonAttentionC(KonkaParagonAttentionC t);

	List<KonkaParagonAttentionC> getKonkaParagonAttentionCList(
			KonkaParagonAttentionC t);

	Long getKonkaParagonAttentionCCount(KonkaParagonAttentionC t);

	List<KonkaParagonAttentionC> getKonkaParagonAttentionCPaginatedList(
			KonkaParagonAttentionC t);

	List<HashMap<String, String>> selectKonkaParagonAttentionPaginatedList(
			KonkaParagonAttentionC t) throws DataAccessException;
}
