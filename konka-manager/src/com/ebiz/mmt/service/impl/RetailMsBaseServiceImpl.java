package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePdClazzDao;
import com.ebiz.mmt.dao.BasePropValItemDao;
import com.ebiz.mmt.dao.PePdModelDao;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.service.RetailMsBaseService;

@Service
public class RetailMsBaseServiceImpl implements RetailMsBaseService{

	@Resource
	private BasePdClazzDao basePdClazzDao;
	
	@Resource
	private PePdModelDao pePdModelDao;
	
	@Resource
	private BasePropValItemDao basePropValItemDao;
	
	public List<BasePdClazz> getKonkaBasePdClazzListByClsIds() {
		String[] cls_ids = {"1010100", "1010200", "1010300", "1010400","1010500"};
		BasePdClazz basePdClazz = new BasePdClazz();
		basePdClazz.getMap().put("cls_ids", cls_ids);
		return basePdClazzDao.selectEntityList(basePdClazz);
	}

	public List<BasePropValItem> getBasePropValItemListByPdId(String pdId) {
		BasePropValItem basePropValItem = new BasePropValItem();
		basePropValItem.setIs_del(0);
		basePropValItem.getMap().put("pdIdForExtral", "10749");
		return basePropValItemDao.selectEntityList(basePropValItem);
	}

	public List<PePdModel> getKonkaPePdModelListByClsIds(String[] cls_ids) {
		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("cls_ids", cls_ids);
		return pePdModelDao.selectEntityList(pePdModel);
	}

}
