package com.ebiz.mmt.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.PePdModelDao;
import com.ebiz.mmt.dao.SysSettingDao;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.SysSetting;
import com.ebiz.mmt.r3.MARA;
import com.ebiz.mmt.service.PePdModelService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-03-21 09:45:12
 */
@Service
public class PePdModelServiceImpl implements PePdModelService {

	@Resource
	private PePdModelDao pePdModelDao;

	@Resource
	private SysSettingDao sysSettingDao;

	@Override
	public Long createPePdModel(PePdModel t) {
		return this.pePdModelDao.insertEntity(t);
	}

	@Override
	public PePdModel getPePdModel(PePdModel t) {
		return this.pePdModelDao.selectEntity(t);
	}

	@Override
	public Long getPePdModelCount(PePdModel t) {
		return this.pePdModelDao.selectEntityCount(t);
	}

	@Override
	public List<PePdModel> getPePdModelList(PePdModel t) {
		return this.pePdModelDao.selectEntityList(t);
	}

	@Override
	public int modifyPePdModel(PePdModel t) {
		return this.pePdModelDao.updateEntity(t);
	}

	@Override
	public int removePePdModel(PePdModel t) {
		return this.pePdModelDao.deleteEntity(t);
	}

	@Override
	public List<PePdModel> getPePdModelPaginatedList(PePdModel t) {
		return this.pePdModelDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Gao,YongXiang
	 * @version 2011-09-22
	 */
	@Override
	public List<PePdModel> getPePdModulePaginatedIncludeMdNameList(PePdModel t) {
		return this.pePdModelDao.selectPePdModulePaginatedIncludeMdNameList(t);
	}

	/**
	 * @author Gao,YongXiang
	 * @version 2011-09-22
	 */
	@Override
	public List<PePdModel> getPePdModelForB2bList(PePdModel t) {
		return this.pePdModelDao.selectPePdModelForB2bList(t);
	}

	/**
	 * @author Gao,YongXiang
	 * @version 2011-09-22
	 */
	@Override
	public List<PePdModel> getPePdModelForB2bWithNameList(PePdModel t) {
		return this.pePdModelDao.selectPePdModelForB2bWithNameList(t);
	}

	/**
	 * @author Li,Ka
	 * @version 2011-12-01 去康佳品牌的型号带大类名称
	 */
	@Override
	public List<PePdModel> getPePdModelWithClsNameBrandNameList(PePdModel t) {
		return this.pePdModelDao.selectPePdModelWithClsNameBrandNameList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-06-25 同步R3产品型号
	 * 
	 * 
	 *          产品信息同步,不作更新,只做新增
	 * @author zhou
	 * @version 2014-11-18
	 * 
	 */
	@Override
	public Long createPePdModelForTb(List<MARA> t) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sformat = new SimpleDateFormat("yyyyMMddHHmmss");
		Long count = 0L;

		List<String> kktvs = getkktvs();

		Object[] kktv = kktvs.toArray();

		if (t.size() > 0) {
			for (MARA temp : t) {
				if (null != temp.getMATNR() && !"".equals(temp.getMATNR())) {
					PePdModel entity = new PePdModel();
					entity.setMd_name(StringUtils.trim(temp.getMATNR()));
					List<PePdModel> entityList = this.pePdModelDao.selectEntityList(entity);
					// only do update
					if (entityList.size() == 0) {
						count++;

						if (null != temp.getMakt()) {
							if (null != temp.getMakt().getMAKTG()) {
								entity.setPd_desc(temp.getMakt().getMAKTG());
							}
						}
						if (null != temp.getMTART()) {
							entity.setMat_type(temp.getMTART());
						}
						if (null != temp.getMATKL()) {
							entity.setMat_group(temp.getMATKL());
						}
						if (null != temp.getSPART()) {
							entity.setCp_group(temp.getSPART());
						}
						if (null != temp.getMEINS()) {
							entity.setMd_dw(temp.getMEINS());
						}

						entity.setIs_sell(1);
						entity.setOrder_value(0);
						entity.setCls_id(1010100L);
						entity.setPar_cls_id(1010000L);
						entity.setAudit_state(1);

						// 新增产品两个属性值初始化
						entity.setEntp_prod_id(new Long(274));
						entity.setEntp_id(new Long(134));

						/**
						 * 114:康佳(规则未知)<br>
						 * 115:现代(产口组为28)子品牌<br>
						 * 116:KKTV(子品牌)
						 */
						// 默认114康佳产品
						entity.setBrand_id(114L);
						// 现代
						if (temp.getSPART().equals("28")) {
							entity.setSub_brand_id(115L);
						}
						// kktv
						if (org.apache.commons.lang.ArrayUtils.contains(kktv, temp.getMATNR().toUpperCase())) {
							entity.setSub_brand_id(116L);
						}

						// height
						entity.setHoehe(Float.valueOf(temp.getHOEHE()));
						// width
						entity.setBreit(Float.valueOf(temp.getBREIT()));
						// long
						entity.setLaeng(Float.valueOf(temp.getLAENG()));

						try {
							if (StringUtils.isNotBlank(temp.getERSDA()))
								entity.setAdd_date(sdf.parse(temp.getERSDA()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						String md_name = StringUtils.trim((temp.getMATNR())).toUpperCase();
						if (StringUtils.startsWith(md_name, "LED")) {
							String sizes = StringUtils.substring(md_name, 3, 5);
							if (GenericValidator.isInt(sizes)) {
								Integer size = Integer.valueOf(sizes);
								if (GenericValidator.isInRange(size, 0, 26)) {
									entity.setSize_sec(1);
								} else if (size == 32) {
									entity.setSize_sec(2);
								} else if (size <= 39 && size >= 37) {
									entity.setSize_sec(3);
								} else if (size <= 43 && size >= 40) {
									entity.setSize_sec(4);
								} else if (size <= 50 && size >= 46) {
									entity.setSize_sec(5);
								} else if (size >= 55) {
									entity.setSize_sec(6);
								} else {
									entity.setSize_sec(-1);
								}
								entity.setMd_size(String.valueOf(sizes));
							} else {
								entity.setSize_sec(-1);
								entity.setMd_size("0");
							}
						} else {
							entity.setSize_sec(-1);
							entity.setMd_size("0");
						}
						entity.setIs_del(0);
						entity.setLast_edit_date(new Date());
						this.pePdModelDao.insertEntity(entity);
					}
					SysSetting ss = new SysSetting();
					ss.setTitle("datapatch");
					ss.setContent(sformat.format(new Date()));
					this.sysSettingDao.updateEntity(ss);
				}
			}
		}
		return count;
	}

	/**
	 * @author Ren,zhong
	 * @version 2013-07-04
	 */
	@Override
	public List<PePdModel> getPePdModelWithClsNameAndParClsNameList(PePdModel t) {
		return this.pePdModelDao.selectPePdModelWithClsNameAndParClsNameList(t);
	}

	/**
	 * @author Li,ZhiXiang
	 * @version 2013-09-23
	 */
	@Override
	public List<PePdModel> getPePdModelListForMdSerise(PePdModel t) {
		return this.pePdModelDao.selectPePdModelListForMdSerise(t);
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-03-09
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List<HashMap> getPePdModelListForMdSize(PePdModel t) {
		return this.pePdModelDao.selectPePdModelListForMdSize(t);
	}

	@Override
	public List<HashMap> getPePdModelMapList(PePdModel t) {
		
		return this.pePdModelDao.selectPePdModelMapList(t);
	}

	private static List<String> getkktvs() {

		List<String> kktvlist = new ArrayList<String>(20);
		InputStream ins = PePdModelServiceImpl.class.getResourceAsStream("/kktv.properties");
		Properties p = new Properties();
		try {
			p.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Object key : p.keySet()) {
			kktvlist.add(p.getProperty((String) key).trim().toUpperCase());
		}
		return kktvlist;

	}

	// private static boolean contains(String[] array, String value) {
	// if (value == null) {
	// return false;
	// }
	// if (array == null || array.length == 0) {
	// return false;
	// }
	// for(int index = 0 ;index<array.length;index++){
	// if (value.equals(array[index])) {
	// return true;
	// }
	// }
	// return false;
	// }

	// public static void main(String[] args) {
	//
	// List<String> kktvs = getkktvs();
	//
	// Object[] kktv = kktvs.toArray();
	//
	// if (ArrayUtils.contains(kktv, "LED49K70T")) {
	// //System.out.println("in");
	// }
	// }
}
