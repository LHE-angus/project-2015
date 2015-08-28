package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaSoFifo;


public interface KonkaSoFifoService {

	Long createKonkaSoFifo(KonkaSoFifo t);

	int modifyKonkaSoFifo(KonkaSoFifo t);

	int removeKonkaSoFifo(KonkaSoFifo t);

	KonkaSoFifo getKonkaSoFifo(KonkaSoFifo t);

	List<KonkaSoFifo> getKonkaSoFifoList(KonkaSoFifo t);

	Long getKonkaSoFifoCount(KonkaSoFifo t);

	List<KonkaSoFifo> getKonkaSoFifoPaginatedList(KonkaSoFifo t);

}