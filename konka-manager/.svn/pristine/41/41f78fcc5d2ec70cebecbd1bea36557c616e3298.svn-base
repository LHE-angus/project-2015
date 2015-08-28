package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.BasePropDao;
import com.ebiz.mmt.dao.BasePropValItemDao;
import com.ebiz.mmt.dao.PdModelPropsValDao;
import com.ebiz.mmt.domain.BaseProp;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.domain.PdModelPropsVal;
import com.ebiz.mmt.service.BasePropService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Chen,ShunHua
 * @date 2011-09-22 09:45:12
 */
@Service
public class BasePropServiceImpl implements BasePropService {

	@Resource
	private BasePropDao basePropDao;

	@Resource
	private BasePropValItemDao basePropValItemDao;

	@Resource
	private PdModelPropsValDao pdModelPropsValDao;

	public Long createBaseProp(BaseProp entity) {
		Long id = this.basePropDao.insertEntity(entity);
		String[] more_values = (String[]) (String[]) entity.getMap().get("more_values");
		if (entity.getProp_type() == 1 || entity.getProp_type() == 2) {
			if (null != more_values && more_values.length > 0) {
				for (int i = 0; i < more_values.length; i++) {
					if (StringUtils.isNotBlank(more_values[i])) {
						BasePropValItem bpvi = new BasePropValItem();
						bpvi.setProp_id(Long.valueOf(id));
						bpvi.setProp_item_name(more_values[i]);
						this.basePropValItemDao.insertEntity(bpvi);
					}
				}
			}
		}
		return id;
	}

	public BaseProp getBaseProp(BaseProp t) {
		return this.basePropDao.selectEntity(t);
	}

	public Long getBasePropCount(BaseProp t) {
		return this.basePropDao.selectEntityCount(t);
	}

	public List<BaseProp> getBasePropList(BaseProp t) {
		return this.basePropDao.selectEntityList(t);
	}

	public int modifyBaseProp(BaseProp entity) {
		String[] more_values = (String[]) entity.getMap().get("more_values");
		if (null != entity.getProp_type()) {
			if (entity.getProp_type() == 1 || entity.getProp_type() == 2) {
				BasePropValItem bpvi = new BasePropValItem();
				bpvi.setProp_id(Long.valueOf(entity.getProp_id()));
				this.basePropValItemDao.deleteEntity(bpvi);

				if (null != more_values && more_values.length > 0) {
					for (int i = 0; i < more_values.length; i++) {
						if (StringUtils.isNotBlank(more_values[i])) {
							BasePropValItem bpv = new BasePropValItem();
							bpv.setProp_id(Long.valueOf(entity.getProp_id()));
							bpv.setProp_item_name(more_values[i]);
							this.basePropValItemDao.insertEntity(bpv);
						}
					}
				}
			} else {
				BasePropValItem bpvi = new BasePropValItem();
				bpvi.setProp_id(Long.valueOf(entity.getProp_id()));
				this.basePropValItemDao.deleteEntity(bpvi);

				PdModelPropsVal pdModelPropsVal = new PdModelPropsVal();
				pdModelPropsVal.setProp_id(entity.getProp_id());
				pdModelPropsVal.setProp_item_ids(null);
				pdModelPropsValDao.updateEntity(pdModelPropsVal);
			}
		}
		return this.basePropDao.updateEntity(entity);
	}

	public int removeBaseProp(BaseProp t) {
		BasePropValItem bpvi = new BasePropValItem();
		bpvi.setProp_id(Long.valueOf(t.getProp_id()));
		this.basePropValItemDao.deleteEntity(bpvi);
		return this.basePropDao.deleteEntity(t);
	}

	public List<BaseProp> getBasePropPaginatedList(BaseProp t) {
		return this.basePropDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Chen,ShunHua
	 */
	public List<BaseProp> getBasePropWithCateNameList(BaseProp t) {
		return this.basePropDao.selectBasePropWithCateNameList(t);
	}

	/**
	 * @author Chen,ShunHua
	 * @version 2011-09-23
	 */
	public List<BaseProp> getBasePropWithTreeNameList(BaseProp t) {
		return this.basePropDao.selectBasePropWithTreeNameList(t);
	}
}
