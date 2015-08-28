package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.KonkaMobilePdBrand;

/**
 * @author Ren,zhong
 * @version 2013-05-31 14:00
 */
public interface KonkaMobilePdBrandService {

	Long createKonkaMobilePdBrand(KonkaMobilePdBrand t);

	int modifyKonkaMobilePdBrand(KonkaMobilePdBrand t);

	int removeKonkaMobilePdBrand(KonkaMobilePdBrand t);

	KonkaMobilePdBrand getKonkaMobilePdBrand(KonkaMobilePdBrand t);

	List<KonkaMobilePdBrand> getKonkaMobilePdBrandList(KonkaMobilePdBrand t);

	Long getKonkaMobilePdBrandCount(KonkaMobilePdBrand t);

	List<KonkaMobilePdBrand> getKonkaMobilePdBrandPaginatedList(KonkaMobilePdBrand t);

}