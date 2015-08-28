package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePropValItemDao;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.service.BasePropValItemService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-21 09:45:12
 */
@Service
public class BasePropValItemServiceImpl implements BasePropValItemService {

	@Resource
	private BasePropValItemDao basePropValItemDao;
	

	public Long createBasePropValItem(BasePropValItem t) {
		return this.basePropValItemDao.insertEntity(t);
	}

	public BasePropValItem getBasePropValItem(BasePropValItem t) {
		return this.basePropValItemDao.selectEntity(t);
	}

	public Long getBasePropValItemCount(BasePropValItem t) {
		return this.basePropValItemDao.selectEntityCount(t);
	}

	public List<BasePropValItem> getBasePropValItemList(BasePropValItem t) {
		return this.basePropValItemDao.selectEntityList(t);
	}

	public int modifyBasePropValItem(BasePropValItem t) {
		return this.basePropValItemDao.updateEntity(t);
	}

	public int removeBasePropValItem(BasePropValItem t) {
		return this.basePropValItemDao.deleteEntity(t);
	}

	public List<BasePropValItem> getBasePropValItemPaginatedList(BasePropValItem t) {
		return this.basePropValItemDao.selectEntityPaginatedList(t);
	}

}
