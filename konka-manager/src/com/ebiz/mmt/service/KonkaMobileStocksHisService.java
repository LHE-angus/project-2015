package com.ebiz.mmt.service;

import java.math.BigDecimal;
import java.util.List;
import com.ebiz.mmt.domain.KonkaMobileStocksHis;

public interface KonkaMobileStocksHisService {

	Long createKonkaMobileStocksHis(KonkaMobileStocksHis t);

	int modifyKonkaMobileStocksHis(KonkaMobileStocksHis t);

	int removeKonkaMobileStocksHis(KonkaMobileStocksHis t);

	KonkaMobileStocksHis getKonkaMobileStocksHis(KonkaMobileStocksHis t);

	List<KonkaMobileStocksHis> getKonkaMobileStocksHisList(
			KonkaMobileStocksHis t);

	Long getKonkaMobileStocksHisCount(KonkaMobileStocksHis t);

	List<KonkaMobileStocksHis> getKonkaMobileStocksHisPaginatedList(
			KonkaMobileStocksHis t);

	Long sendRecKonkaMobileStocksHis(KonkaMobileStocksHis t);

	Long resetKonkaMobileStocksHis(KonkaMobileStocksHis t,
			BigDecimal old_c_num, BigDecimal busi_num);

}
