package com.ebiz.mmt.service;

import java.util.List;
import com.ebiz.mmt.domain.KonkaParagonSales;

public interface KonkaParagonSalesService {

	Long createKonkaParagonSales(KonkaParagonSales t);

	int modifyKonkaParagonSales(KonkaParagonSales t);

	int removeKonkaParagonSales(KonkaParagonSales t);

	KonkaParagonSales getKonkaParagonSales(KonkaParagonSales t);

	List<KonkaParagonSales> getKonkaParagonSalesList(KonkaParagonSales t);

	Long getKonkaParagonSalesCount(KonkaParagonSales t);

	List<KonkaParagonSales> getKonkaParagonSalesPaginatedList(KonkaParagonSales t);

}
