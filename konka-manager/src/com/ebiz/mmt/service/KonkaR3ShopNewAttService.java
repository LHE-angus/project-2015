package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaR3ShopNewAtt;


public interface KonkaR3ShopNewAttService {

	Long createKonkaR3ShopNewAtt(KonkaR3ShopNewAtt t);

	int modifyKonkaR3ShopNewAtt(KonkaR3ShopNewAtt t);

	int removeKonkaR3ShopNewAtt(KonkaR3ShopNewAtt t);

	KonkaR3ShopNewAtt getKonkaR3ShopNewAtt(KonkaR3ShopNewAtt t);

	List<KonkaR3ShopNewAtt> getKonkaR3ShopNewAttList(KonkaR3ShopNewAtt t);

	Long getKonkaR3ShopNewAttCount(KonkaR3ShopNewAtt t);

	List<KonkaR3ShopNewAtt> getKonkaR3ShopNewAttPaginatedList(KonkaR3ShopNewAtt t);

}