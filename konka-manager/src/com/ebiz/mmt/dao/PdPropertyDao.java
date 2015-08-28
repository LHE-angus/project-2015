package com.ebiz.mmt.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ebiz.mmt.domain.PdProperty;
import com.ebiz.ssi.dao.EntityDao;

/**
 * @author Liu,Huan
 */
public interface PdPropertyDao extends EntityDao<PdProperty> {

	List<PdProperty> selectPdPropertyForModelList(PdProperty t) throws DataAccessException;

	List<PdProperty> selectPdPropertyListForShopPdContrast(PdProperty t) throws DataAccessException;
}
