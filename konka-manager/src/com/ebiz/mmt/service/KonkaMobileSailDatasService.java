package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobileSailDatas;

public interface KonkaMobileSailDatasService {

	Long createKonkaMobileSailDatas(KonkaMobileSailDatas t);

	int modifyKonkaMobileSailDatas(KonkaMobileSailDatas t);

	int removeKonkaMobileSailDatas(KonkaMobileSailDatas t);

	KonkaMobileSailDatas getKonkaMobileSailDatas(KonkaMobileSailDatas t);

	List<KonkaMobileSailDatas> getKonkaMobileSailDatasList(KonkaMobileSailDatas t);

	Long getKonkaMobileSailDatasCount(KonkaMobileSailDatas t);

	List<KonkaMobileSailDatas> getKonkaMobileSailDatasPaginatedList(KonkaMobileSailDatas t);

	Long createKonkaMobileSailDatasForHis(KonkaMobileSailDatas t);

}
