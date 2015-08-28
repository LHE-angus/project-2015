package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.EcCashPrice;
import com.ebiz.ssi.dao.EntityDao;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-18 10:23:42
 */
public interface EcCashPriceDao extends EntityDao<EcCashPrice> {
	String  insertEcCashPrice(List<EcCashPrice> list); 
}
