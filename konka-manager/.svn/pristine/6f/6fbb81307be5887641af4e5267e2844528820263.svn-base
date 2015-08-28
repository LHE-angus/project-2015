package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.SxExtendedPermissionsDao;
import com.ebiz.mmt.domain.SxExtendedPermissions;
import com.ebiz.mmt.service.SxExtendedPermissionsService;

@Service
public class SxExtendedPermissionsServiceImpl implements SxExtendedPermissionsService {

	@Resource
	private SxExtendedPermissionsDao sxExtendedPermissionsDao;

	public Long createSxExtendedPermissions(SxExtendedPermissions t) {
		return this.sxExtendedPermissionsDao.insertEntity(t);
	}

	public SxExtendedPermissions getSxExtendedPermissions(SxExtendedPermissions t) {
		return this.sxExtendedPermissionsDao.selectEntity(t);
	}

	public Long getSxExtendedPermissionsCount(SxExtendedPermissions t) {
		return this.sxExtendedPermissionsDao.selectEntityCount(t);
	}

	public List<SxExtendedPermissions> getSxExtendedPermissionsList(SxExtendedPermissions t) {
		return this.sxExtendedPermissionsDao.selectEntityList(t);
	}

	public int modifySxExtendedPermissions(SxExtendedPermissions t) {
		String[] diqu_ids = (String[]) t.getMap().get("diqu_ids"); // 地区
		String[] gys_ids = (String[]) t.getMap().get("gys_ids");// 供应商
		String[] pp_ids = (String[]) t.getMap().get("pp_ids");// 品牌
		String[] pl_ids = (String[]) t.getMap().get("pl_ids");// 品类
		String[] pp_cls_brand_ids = StringUtils.split(((String) t.getMap().get("pp_cls_brand_ids")), ",");// cls_brand_id
		String step = (String) t.getMap().get("step");// 品牌

		SxExtendedPermissions sxExtendedPermissions = new SxExtendedPermissions();
		sxExtendedPermissions.setUser_id(t.getUser_id());

		if ("1".equals(step)) {
			sxExtendedPermissions.setExpand_type(1);
			this.sxExtendedPermissionsDao.deleteEntity(sxExtendedPermissions);
			if (null != gys_ids && gys_ids.length > 0) {
				for (int i = 0; i < gys_ids.length; i++) {
					t.setExpand_id(gys_ids[i]);
					t.setExpand_type(1);
					this.sxExtendedPermissionsDao.insertEntity(t);
				}
			}
		}
		if ("2".equals(step)) {
			sxExtendedPermissions.setExpand_type(3);
			this.sxExtendedPermissionsDao.deleteEntity(sxExtendedPermissions);
			if (null != diqu_ids && diqu_ids.length > 0) {
				for (int i = 0; i < diqu_ids.length; i++) {
					t.setExpand_id(diqu_ids[i]);
					t.setExpand_type(3);
					this.sxExtendedPermissionsDao.insertEntity(t);
				}
			}
		}
		if ("3".equals(step)) {
			sxExtendedPermissions.setExpand_type(2);
			this.sxExtendedPermissionsDao.deleteEntity(sxExtendedPermissions);
			if (null != pp_ids && pp_ids.length > 0) {
				for (int i = 0; i < pp_ids.length; i++) {
					t.setExpand_id(pp_ids[i]);
					t.setExpand_type(2);
					this.sxExtendedPermissionsDao.insertEntity(t);
				}
			}
			sxExtendedPermissions.setExpand_type(4);
			this.sxExtendedPermissionsDao.deleteEntity(sxExtendedPermissions);
			if (null != pl_ids && pl_ids.length > 0) {
				for (int i = 0; i < pl_ids.length; i++) {
					t.setExpand_id(pl_ids[i]);
					t.setExpand_type(4);
					this.sxExtendedPermissionsDao.insertEntity(t);
				}
			}
			sxExtendedPermissions.setExpand_type(5);
			this.sxExtendedPermissionsDao.deleteEntity(sxExtendedPermissions);
			if (null != pp_cls_brand_ids && pp_cls_brand_ids.length > 0) {
				for (int i = 0; i < pp_cls_brand_ids.length; i++) {
					t.setExpand_id(pp_cls_brand_ids[i]);
					t.setExpand_type(5);
					this.sxExtendedPermissionsDao.insertEntity(t);
				}
			}
		}

		return this.sxExtendedPermissionsDao.updateEntity(t);
	}

	public int removeSxExtendedPermissions(SxExtendedPermissions t) {
		return this.sxExtendedPermissionsDao.deleteEntity(t);
	}

	public List<SxExtendedPermissions> getSxExtendedPermissionsPaginatedList(SxExtendedPermissions t) {
		return this.sxExtendedPermissionsDao.selectEntityPaginatedList(t);
	}

	@Override
	public List<SxExtendedPermissions> getSxExtendedPermissionBrand(SxExtendedPermissions t) {
		return this.sxExtendedPermissionsDao.selectSxExtednPermissionBrand(t);
	}

	@Override
	public List<SxExtendedPermissions> getSxExtendedPermissionPd(SxExtendedPermissions t) {
		return this.sxExtendedPermissionsDao.selectSxExtendedPermissionPd(t);
	}

	@Override
	public List<SxExtendedPermissions> getSxExtendedPermissionSupplier(SxExtendedPermissions t) {
		return this.sxExtendedPermissionsDao.selectSxExtendedPermissionSupplier(t);
	}

	/**
	 * @author Li,GuoHu
	 * @version 2011-07-06
	 */
	public List<SxExtendedPermissions> getClsJoinBrandResultCorpPeShop(SxExtendedPermissions t) {
		return sxExtendedPermissionsDao.selectSxExtendedPermissionWithClsJoinBrand(t);
	}
}
