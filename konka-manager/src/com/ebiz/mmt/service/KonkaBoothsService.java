package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaBooths;

public interface KonkaBoothsService {

	Long createKonkaBooths(KonkaBooths t);

	int modifyKonkaBooths(KonkaBooths t);

	int removeKonkaBooths(KonkaBooths t);

	KonkaBooths getKonkaBooths(KonkaBooths t);

	List<KonkaBooths> getKonkaBoothsList(KonkaBooths t);

	Long getKonkaBoothsCount(KonkaBooths t);

	List<KonkaBooths> getKonkaBoothsPaginatedList(KonkaBooths t);

}
