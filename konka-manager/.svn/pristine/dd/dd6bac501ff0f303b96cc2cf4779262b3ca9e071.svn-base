package com.ebiz.mmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcBcompBindingDao;
import com.ebiz.mmt.dao.EcExtendDao;
import com.ebiz.mmt.dao.EcGoodsSallareaDao;
import com.ebiz.mmt.dao.EcRuleGoodsDao;
import com.ebiz.mmt.dao.KonkaBcompPdContentDao;
import com.ebiz.mmt.dao.KonkaBcompPdDao;
import com.ebiz.mmt.domain.EcBcompBinding;
import com.ebiz.mmt.domain.EcExtend;
import com.ebiz.mmt.domain.EcGoodsSallarea;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.service.KonkaBcompPdService;

/**
 * @author Ren,zhong
 * @version 2013-07-05 10:59
 */
@Service
public class KonkaBcompPdServiceImpl implements KonkaBcompPdService {

	@Resource
	private KonkaBcompPdDao konkaBcompPdDao;

	@Resource
	private KonkaBcompPdContentDao konkaBcompPdContentDao;

	@Resource
	private EcBcompBindingDao ecBcompBindingDao;

	@Resource
	private EcExtendDao ecExtendDao;

	@Resource
	private EcRuleGoodsDao ecRuleGoodsDao;

	@Resource
	private EcGoodsSallareaDao ecGoodsSallareaDao;

	public Long createKonkaBcompPd(KonkaBcompPd t) {
		String insert_batch = (String) t.getMap().get("insert_batch");
		if (insert_batch.equals("1")) {
			List<KonkaBcompPdContent> konkaBcompPdContentList = t.getKonkaBcompPdContentList();
			if (null != konkaBcompPdContentList && 0 != konkaBcompPdContentList.size())
				for (KonkaBcompPdContent konkaBcompPdContent : konkaBcompPdContentList) {
					konkaBcompPdContent.setKbp_id(t.getId());
					this.konkaBcompPdContentDao.insertEntity(konkaBcompPdContent);
				}
		}
		return this.konkaBcompPdDao.insertEntity(t);
	}

	public KonkaBcompPd getKonkaBcompPd(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectEntity(t);
	}

	public Long getKonkaBcompPdCount(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectEntityCount(t);
	}

	public List<KonkaBcompPd> getKonkaBcompPdList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectEntityList(t);
	}

	public int modifyKonkaBcompPd(KonkaBcompPd t) {
		return this.konkaBcompPdDao.updateEntity(t);
	}

	public int removeKonkaBcompPd(KonkaBcompPd t) {
		return this.konkaBcompPdDao.deleteEntity(t);
	}

	public List<KonkaBcompPd> getKonkaBcompPdPaginatedList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	public Long getKonkaBcompPdWithDeptAndMdCount(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdWithDeptAndMdCount(t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-07-04
	 */
	public List<KonkaBcompPd> getKonkaBcompPdWithDeptAndMdPaginatedList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdWithDeptAndMdPaginatedList(t);
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-07-05
	 */
	public List<KonkaBcompPd> getKonkaBcompPdWidhDeptAndMdList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdWidhDeptAndMdList(t);
	}

	/**
	 * @author Pan, Gang
	 * @date 2013-08-21
	 */
	public int createKonkaBcompPdIncludeContent(KonkaBcompPd t, String e_id, String e_id2) {
		this.konkaBcompPdDao.insertEntity(t);
		List<KonkaBcompPdContent> konkaBcompPdContentList = t.getKonkaBcompPdContentList();
		if (null != konkaBcompPdContentList && 0 != konkaBcompPdContentList.size())
			for (KonkaBcompPdContent konkaBcompPdContent : konkaBcompPdContentList) {
				konkaBcompPdContent.setKbp_id(t.getId());
				this.konkaBcompPdContentDao.insertEntity(konkaBcompPdContent);
			}
		if (StringUtils.isNotBlank(e_id)) {
			String[] ids = e_id.split("@");
			for (String c_id : ids) {
				EcBcompBinding ebb = new EcBcompBinding();
				ebb.setBinding_id(Long.valueOf(c_id));
				ebb.setGoods_id(t.getId());
				this.ecBcompBindingDao.insertEntity(ebb);
			}
		}

		if (StringUtils.isNotBlank(e_id2)) {
			String[] ids = e_id2.split("@");
			for (String c_id : ids) {
				EcBcompBinding ebb = new EcBcompBinding();
				ebb.setBinding_id(Long.valueOf(c_id));
				ebb.setGoods_id(t.getId());
				this.ecBcompBindingDao.insertEntity(ebb);
			}
		}

		// 新增商品的同时 导入商品信息到顺丰
		/**
		 * SfOrderService sf = new SfOrderService(); String spmlOpName =
		 * "wmsMerchantCatalogService"; Spml spml = new Spml();
		 * spml.setCheckword("01f18980363f40e48416464baf4cc7c0");
		 * spml.setCompany("KONKA"); spml.setItem(t.getId().toString());
		 * spml.setDescription(t.getPd_name()); spml.setStorage_template("个");
		 * spml.setSequence_1("1"); spml.setConversion_qty_1("1");
		 * spml.setHeight_1("1"); spml.setWidth_1("1"); spml.setLength_1("1");
		 * spml.setWeight_1("1"); spml.setQuantity_um_1("个");
		 * spml.setWeight_um_1("KG"); spml.setDimension_um_1("CM");
		 * spml.setTreat_as_loose_1("Y"); spml.setLot_controlled("否");
		 * spml.setSerial_num_track_inbound("否");
		 * spml.setSerial_num_track_outbound("否"); spml.setBom_action("否");
		 * spml.setInterface_action_code("NEW"); String spmlXml = spml.toXml();
		 * String returnXml; returnXml = sf.sfWebService(spmlXml, spmlOpName);
		 * //System.out.println("商品目录导入返回xml------>>>" + returnXml);
		 */

		return 0;
	}

	public int createKonkaBcompPdIncludeContent2(KonkaBcompPd t, String e_id, String e_id2, List<EcExtend> ecList,
	        List<EcExtend> ecList2) {
		this.konkaBcompPdDao.insertEntity(t);
		List<KonkaBcompPdContent> konkaBcompPdContentList = t.getKonkaBcompPdContentList();
		if (null != konkaBcompPdContentList && 0 != konkaBcompPdContentList.size())
			for (KonkaBcompPdContent konkaBcompPdContent : konkaBcompPdContentList) {
				konkaBcompPdContent.setKbp_id(t.getId());
				this.konkaBcompPdContentDao.insertEntity(konkaBcompPdContent);
			}
		if (StringUtils.isNotBlank(e_id)) {
			String[] ids = e_id.split("@");
			for (String c_id : ids) {
				EcBcompBinding ebb = new EcBcompBinding();
				ebb.setBinding_id(Long.valueOf(c_id));
				ebb.setGoods_id(t.getId());
				this.ecBcompBindingDao.insertEntity(ebb);
			}
		}

		if (StringUtils.isNotBlank(e_id2)) {
			String[] ids = e_id2.split("@");
			for (String c_id : ids) {
				EcBcompBinding ebb = new EcBcompBinding();
				ebb.setBinding_id(Long.valueOf(c_id));
				ebb.setGoods_id(t.getId());
				this.ecBcompBindingDao.insertEntity(ebb);
			}
		}

		if (null != ecList && ecList.size() > 0) {
			for (EcExtend cur : ecList) {
				if (!"".equals(cur.getProp_name()) && !"".equals(cur.getProp_value())) {
					// EcExtend ee = new EcExtend();
					cur.setLink_id(t.getId());
					this.ecExtendDao.insertEntity(cur);
				}
			}
		}
		if (null != ecList2 && ecList2.size() > 0) {
			for (EcExtend cur : ecList2) {
				if (!"".equals(cur.getProp_name()) && !"".equals(cur.getProp_value())) {
					// EcExtend ee = new EcExtend();
					cur.setLink_id(t.getId());
					this.ecExtendDao.insertEntity(cur);
				}
			}
		}
		String[] select2 = (String[]) t.getMap().get("select2");
		if (null != select2 && select2.length > 0) {
			for (String string : select2) {
				EcRuleGoods ee = new EcRuleGoods();
				ee.setGoods_id(t.getId());
				ee.setAdd_date(new Date());
				ee.setIs_del(0);
				ee.setRule_id(Long.valueOf(string));
				ee.setAdd_user_id((Long) t.getMap().get("user_id"));
				this.ecRuleGoodsDao.insertEntity(ee);
			}
		}

		String template_id = (String) t.getMap().get("template_id");
		if (StringUtils.isNotBlank(template_id)) {
			EcGoodsSallarea es = new EcGoodsSallarea();
			es.setAdd_date(new Date());
			es.setAdd_u_id(t.getAdd_u_id());
			es.setGoods_id(t.getId());
			es.setOwn_sys(t.getOwn_sys());
			es.setTemplate_id(Long.valueOf(template_id));
			this.ecGoodsSallareaDao.insertEntity(es);
		}

		return 0;
	}

	/**
	 * @author Pan, Gang
	 * @date 2013-08-21
	 */
	public int modifyKonkaBcompPdIncludeContent(KonkaBcompPd t, String e_id, String e_id2) {
		// 先删除
		KonkaBcompPdContent konkaBcompPdContent = new KonkaBcompPdContent();
		konkaBcompPdContent.setKbp_id(t.getId());
		this.konkaBcompPdContentDao.deleteKonkaBcompPdContentWithKPDID(konkaBcompPdContent);

		List<KonkaBcompPdContent> konkaBcompPdContentList = t.getKonkaBcompPdContentList();
		if (null != konkaBcompPdContentList && 0 != konkaBcompPdContentList.size())
			for (KonkaBcompPdContent tt : konkaBcompPdContentList) {
				tt.setKbp_id(t.getId());
				this.konkaBcompPdContentDao.insertEntity(tt);
			}
		// 服务类套餐先清空 再增加
		if (StringUtils.isNotBlank(e_id)) {
			EcBcompBinding ecb = new EcBcompBinding();
			ecb.setGoods_id(t.getId());
			List<EcBcompBinding> ecbList = this.ecBcompBindingDao.selectEcBcompBindingForFuWuTaoCanList(ecb);
			for (EcBcompBinding ee : ecbList) {
				EcBcompBinding e2 = new EcBcompBinding();
				e2.setGoods_id(ee.getGoods_id());
				this.ecBcompBindingDao.deleteEntity(ee);
			}

			String[] ids = e_id.split("@");
			for (String c_id : ids) {
				EcBcompBinding ebb = new EcBcompBinding();
				ebb.setBinding_id(Long.valueOf(c_id));
				ebb.setGoods_id(t.getId());
				this.ecBcompBindingDao.insertEntity(ebb);
			}
		} else {
			EcBcompBinding ecb = new EcBcompBinding();
			ecb.setGoods_id(t.getId());
			List<EcBcompBinding> ecbList = this.ecBcompBindingDao.selectEcBcompBindingForFuWuTaoCanList(ecb);
			for (EcBcompBinding ee : ecbList) {
				EcBcompBinding e2 = new EcBcompBinding();
				e2.setGoods_id(ee.getGoods_id());
				e2.setBinding_id(ee.getBinding_id());
				this.ecBcompBindingDao.deleteEntity(ee);
			}
		}

		// 商品类套餐先清空 再增加
		if (StringUtils.isNotBlank(e_id2)) {
			EcBcompBinding ecb = new EcBcompBinding();
			ecb.setGoods_id(t.getId());
			List<EcBcompBinding> ecbList = this.ecBcompBindingDao.selectEcBcompBindingForPdTaoCanList(ecb);
			for (EcBcompBinding ee : ecbList) {
				EcBcompBinding e2 = new EcBcompBinding();
				e2.setGoods_id(ee.getGoods_id());
				this.ecBcompBindingDao.deleteEntity(ee);
			}

			String[] ids = e_id2.split("@");
			for (String c_id : ids) {
				EcBcompBinding ebb = new EcBcompBinding();
				ebb.setBinding_id(Long.valueOf(c_id));
				ebb.setGoods_id(t.getId());
				this.ecBcompBindingDao.insertEntity(ebb);
			}
		} else {
			EcBcompBinding ecb = new EcBcompBinding();
			ecb.setGoods_id(t.getId());
			List<EcBcompBinding> ecbList = this.ecBcompBindingDao.selectEcBcompBindingForPdTaoCanList(ecb);
			for (EcBcompBinding ee : ecbList) {
				EcBcompBinding e2 = new EcBcompBinding();
				e2.setGoods_id(ee.getGoods_id());
				e2.setBinding_id(ee.getBinding_id());
				this.ecBcompBindingDao.deleteEntity(ee);
			}
		}

		return this.konkaBcompPdDao.updateEntity(t);
	}

	public int modifyKonkaBcompPdIncludeContent2(KonkaBcompPd t, String e_id, String e_id2, List<EcExtend> list,
	        List<EcExtend> list2) {
		// 先删除
		KonkaBcompPdContent konkaBcompPdContent = new KonkaBcompPdContent();
		konkaBcompPdContent.setKbp_id(t.getId());
		this.konkaBcompPdContentDao.deleteKonkaBcompPdContentWithKPDID(konkaBcompPdContent);

		List<KonkaBcompPdContent> konkaBcompPdContentList = t.getKonkaBcompPdContentList();
		if (null != konkaBcompPdContentList && 0 != konkaBcompPdContentList.size())
			for (KonkaBcompPdContent tt : konkaBcompPdContentList) {
				tt.setKbp_id(t.getId());
				this.konkaBcompPdContentDao.insertEntity(tt);
			}
		// 服务类套餐先清空 再增加
		if (StringUtils.isNotBlank(e_id)) {
			EcBcompBinding ecb = new EcBcompBinding();
			ecb.setGoods_id(t.getId());
			List<EcBcompBinding> ecbList = this.ecBcompBindingDao.selectEcBcompBindingForFuWuTaoCanList(ecb);
			for (EcBcompBinding ee : ecbList) {
				EcBcompBinding e2 = new EcBcompBinding();
				e2.setGoods_id(ee.getGoods_id());
				this.ecBcompBindingDao.deleteEntity(ee);
			}

			String[] ids = e_id.split("@");
			for (String c_id : ids) {
				EcBcompBinding ebb = new EcBcompBinding();
				ebb.setBinding_id(Long.valueOf(c_id));
				ebb.setGoods_id(t.getId());
				this.ecBcompBindingDao.insertEntity(ebb);
			}
		} else {
			EcBcompBinding ecb = new EcBcompBinding();
			ecb.setGoods_id(t.getId());
			List<EcBcompBinding> ecbList = this.ecBcompBindingDao.selectEcBcompBindingForFuWuTaoCanList(ecb);
			for (EcBcompBinding ee : ecbList) {
				EcBcompBinding e2 = new EcBcompBinding();
				e2.setGoods_id(ee.getGoods_id());
				e2.setBinding_id(ee.getBinding_id());
				this.ecBcompBindingDao.deleteEntity(ee);
			}
		}

		// 商品类套餐先清空 再增加
		/*
		 * if (StringUtils.isNotBlank(e_id2)) { EcBcompBinding ecb = new
		 * EcBcompBinding(); ecb.setGoods_id(t.getId()); List<EcBcompBinding>
		 * ecbList =
		 * this.ecBcompBindingDao.selectEcBcompBindingForPdTaoCanList(ecb); for
		 * (EcBcompBinding ee : ecbList) { EcBcompBinding e2 = new
		 * EcBcompBinding(); e2.setGoods_id(ee.getGoods_id());
		 * this.ecBcompBindingDao.deleteEntity(ee); }
		 * 
		 * String[] ids = e_id2.split("@"); for (String c_id : ids) {
		 * EcBcompBinding ebb = new EcBcompBinding();
		 * ebb.setBinding_id(Long.valueOf(c_id)); ebb.setGoods_id(t.getId());
		 * this.ecBcompBindingDao.insertEntity(ebb); } } else { EcBcompBinding
		 * ecb = new EcBcompBinding(); ecb.setGoods_id(t.getId());
		 * List<EcBcompBinding> ecbList =
		 * this.ecBcompBindingDao.selectEcBcompBindingForPdTaoCanList(ecb); for
		 * (EcBcompBinding ee : ecbList) { EcBcompBinding e2 = new
		 * EcBcompBinding(); e2.setGoods_id(ee.getGoods_id());
		 * e2.setBinding_id(ee.getBinding_id());
		 * this.ecBcompBindingDao.deleteEntity(ee); } }
		 */

		// 商品规则，先删后增

		List<EcRuleGoods> select1 = (List<EcRuleGoods>) t.getMap().get("select1");
		if (null != select1 && select1.size() > 0) {
			for (EcRuleGoods ecRuleGoods : select1) {
				this.ecRuleGoodsDao.deleteEntity(ecRuleGoods);
			}
		}

		String[] select2 = (String[]) t.getMap().get("select2");
		if (null != select2 && select2.length > 0) {
			for (String string : select2) {
				EcRuleGoods ee = new EcRuleGoods();
				ee.setGoods_id(t.getId());
				ee.setAdd_date(new Date());
				ee.setIs_del(0);
				ee.setRule_id(Long.valueOf(string));
				ee.setAdd_user_id((Long) t.getMap().get("user_id"));
				this.ecRuleGoodsDao.insertEntity(ee);
			}
		}

		if (null != list && list.size() > 0) {
			for (EcExtend cur : list) {
				if (!"".equals(cur.getProp_name()) && !"".equals(cur.getProp_value())) {
					this.ecExtendDao.insertEntity(cur);
				}
			}
		}
		if (null != list2 && list2.size() > 0) {
			for (EcExtend cur : list2) {
				if (!"".equals(cur.getProp_name()) && !"".equals(cur.getProp_value())) {
					this.ecExtendDao.updateEntity(cur);
				}
			}
		}
		String template_id = (String) t.getMap().get("template_id");
		if (StringUtils.isNotBlank(template_id)) {
			EcGoodsSallarea ee = new EcGoodsSallarea();
			ee.setGoods_id(t.getId());
			this.ecGoodsSallareaDao.deleteEntity(ee);

			EcGoodsSallarea es = new EcGoodsSallarea();
			es.setAdd_date(new Date());
			es.setAdd_u_id(t.getAdd_u_id());
			es.setGoods_id(t.getId());
			es.setOwn_sys(t.getOwn_sys());
			es.setTemplate_id(Long.valueOf(template_id));
			this.ecGoodsSallareaDao.insertEntity(es);
		} else {
			EcGoodsSallarea es = new EcGoodsSallarea();
			es.setGoods_id(t.getId());
			this.ecGoodsSallareaDao.deleteEntity(es);
		}

		return this.konkaBcompPdDao.updateEntity(t);
	}

	public Long getKonkaBcompPdForEcPriceCount(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdForEcPriceCount(t);
	}

	public List<KonkaBcompPd> getKonkaBcompPdForEcPricePaginatedList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdForEcPricePaginatedList(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @date 2013-09-13
	 */
	public Long getKonkaBcompPdForEcPriceAndSaleCount(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdForEcPriceAndSaleCount(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @date 2013-09-13
	 */
	public List<KonkaBcompPd> getKonkaBcompPdForEcPriceAndSalePaginatedList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdForEcPriceAndSalePaginatedList(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @date 2013-09-17
	 */
	public int modifyKonkaBcompPdForViewCountOrSaleNum(KonkaBcompPd t) {
		return this.konkaBcompPdDao.updateKonkaBcompPdForViewCountOrSaleNum(t);
	}

	public Long getKonkaBcompPdForIdCount(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdForIdCount(t);
	}

	public Long getKonkaBcompPdWithDeptAndMdForCustCount(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdWithDeptAndMdForCustCount(t);
	}

	public List<KonkaBcompPd> getKonkaBcompPdWithDeptAndMdForCustPaginatedList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdWithDeptAndMdForCustPaginatedList(t);
	}

	@Override
	public Long getKonkaBcompPdWithDeptAndMdNewCount(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdWithDeptAndMdNewCount(t);
	}

	@Override
	public List<KonkaBcompPd> getKonkaBcompPdWithDeptAndMdNewPaginatedList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdWithDeptAndMdNewPaginatedList(t);
	}

	@Override
	public int modifyKonkaBcompPdForRule(KonkaBcompPd t) {

		// 商品规则，先删后增

		List<EcRuleGoods> select1 = (List<EcRuleGoods>) t.getMap().get("select1");
		if (null != select1 && select1.size() > 0) {
			for (EcRuleGoods ecRuleGoods : select1) {
				this.ecRuleGoodsDao.deleteEntity(ecRuleGoods);
			}
		}

		String[] select2 = (String[]) t.getMap().get("select2");
		if (null != select2 && select2.length > 0) {
			for (String string : select2) {
				EcRuleGoods ee = new EcRuleGoods();
				ee.setGoods_id(t.getId());
				ee.setAdd_date(new Date());
				ee.setIs_del(0);
				ee.setRule_id(Long.valueOf(string));
				ee.setAdd_user_id((Long) t.getMap().get("user_id"));
				this.ecRuleGoodsDao.insertEntity(ee);
			}
		}

		return 0;
	}

	public List<KonkaBcompPd> getKonkaBcompPdNewList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdNewList(t);
	}

	@Override
	public Long getKonkaBcompPdForEcPriceAndSaleNewCount(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdForEcPriceAndSaleNewCount(t);
	}

	@Override
	public List<KonkaBcompPd> getKonkaBcompPdForEcPriceAndSaleNewPaginatedList(KonkaBcompPd t) {
		return this.konkaBcompPdDao.selectKonkaBcompPdForEcPriceAndSaleNewPaginatedList(t);
	}

}
