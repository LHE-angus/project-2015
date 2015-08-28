package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.BaseProvinceListFourDao;
import com.ebiz.mmt.dao.BranchAssignDao;
import com.ebiz.mmt.dao.JBasePartnerDao;
import com.ebiz.mmt.dao.KonkaDeptDao;
import com.ebiz.mmt.dao.KonkaR3MmtMatchDao;
import com.ebiz.mmt.dao.KonkaR3ShopBrandDao;
import com.ebiz.mmt.dao.KonkaR3ShopDao;
import com.ebiz.mmt.dao.KonkaR3ShopLinkDao;
import com.ebiz.mmt.dao.KonkaSalesDeptDao;
import com.ebiz.mmt.dao.MvOrgOfCustomerAllDao;
import com.ebiz.mmt.dao.MvOrgOfCustomerDao;
import com.ebiz.mmt.dao.MvOrgOfCxyAllDao;
import com.ebiz.mmt.dao.MvOrgOfCxyDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.EntpShop;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3ShopBrand;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.domain.KonkaSalesDept;
import com.ebiz.mmt.domain.MvOrgOfCustomer;
import com.ebiz.mmt.domain.MvOrgOfCustomerAll;
import com.ebiz.mmt.domain.MvOrgOfCxy;
import com.ebiz.mmt.domain.MvOrgOfCxyAll;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.r3.KNA1;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.service.KonkaR3ShopService;
import com.ebiz.mmt.service.R3WebInterfaceService;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.org.apache.commons.lang3.StringUtils;

@Service
public class KonkaR3ShopServiceImpl implements KonkaR3ShopService {

	@Resource
	private KonkaR3MmtMatchDao konkaR3MmtMatchDao;

	@Resource
	private KonkaR3ShopDao konkaR3ShopDao;

	@Resource
	private KonkaDeptDao konkaDeptDao;

	@Resource
	private KonkaSalesDeptDao konkaSalesDeptDao;

	@Resource
	private KonkaR3ShopLinkDao konkaR3ShopLinkDao;

	@Resource
	private KonkaR3ShopBrandDao konkaR3ShopBrandDao;

	@Resource
	private JBasePartnerDao jBasePartnerDao;

	@Resource
	private BranchAssignDao branchAssignDao;

	@Resource
	private AttachmentDao attachmentDao;

	@Resource
	private BaseProvinceListFourDao baseProvinceListFourDao;

	@Resource
	private KonkaR3ShopLinkDao KonkaR3ShopLinkDao;

	@Resource
	private R3WebInterfaceService r3webinterfaceservice;

	@Resource
	private MvOrgOfCxyDao mvOrgOfCxyDao;

	@Resource
	private MvOrgOfCxyAllDao mvOrgOfCxyAllDao;

	@Resource
	private MvOrgOfCustomerDao mvOrgOfCustomerDao;

	@Resource
	private MvOrgOfCustomerAllDao mvOrgOfCustomerAllDao;

	@Override
	public Long createKonkaR3Shop(KonkaR3Shop t) {
		return this.konkaR3ShopDao.insertEntity(t);
	}

	@Override
	public KonkaR3Shop getKonkaR3Shop(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectEntity(t);
	}

	@Override
	public KonkaR3Shop getKonkaR3ShopSimpleStat(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopSimpleStat(t);
	}

	@Override
	public KonkaR3Shop getKonkaR3ShopForResult(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopForResult(t);
	}

	@Override
	public Long getKonkaR3ShopCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectEntityCount(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectEntityList(t);
	}

	@Override
	public int modifyKonkaR3Shop(KonkaR3Shop t) {
		return this.konkaR3ShopDao.updateEntity(t);
	}

	@Override
	public int removeKonkaR3Shop(KonkaR3Shop t) {
		return this.konkaR3ShopDao.deleteEntity(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopPaginatedList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectEntityPaginatedList(t);
	}

	/** Li,Ka :网点端进货选择康佳品牌时，通过shop_id查询其代理商（供应商） */
	@Override
	public KonkaR3Shop getKonkaR3ShopByShopId(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopByShopId(t);
	}

	@Override
	public void create(KonkaR3Shop r3Shop, EntpShop entpShop) {

		Long r3_shop_id = this.konkaR3ShopDao.insertEntity(r3Shop);

		KonkaR3MmtMatch r3MmtMatch = new KonkaR3MmtMatch();
		r3MmtMatch.setR3_shop_id(r3_shop_id);
		r3MmtMatch.setMmt_shop_id(entpShop.getShop_id());
		r3MmtMatch.setMmt_shop_name(entpShop.getShop_name());
		r3MmtMatch.setMatch_date(new Date());
		this.konkaR3MmtMatchDao.insertEntity(r3MmtMatch);

	}

	/**
	 * @author wangyang 根据业务数据上报时间取R3网点
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopPaginatedListByAddDate(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopPaginatedListByAddDate(t);
	}

	@Override
	public Long getKonkaR3ShopCountByAddDate(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopCountByAddDate(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopMatchAndAssignList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopMatchAndAssignList(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopMatchAndAssignPaginatedList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopMatchAndAssignPaginatedList(t);
	}

	@Override
	public Long getKonkaR3ShopMatchAndAssignCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopMatchAndAssignCount(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopGroupByHandleName(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopGroupByHandleName(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopGroupByBranchAreaName(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopGroupByBranchAreaName(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopPaginatedListForStat(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopPaginatedListForStat(t);
	}

	@Override
	public Long getKonkaR3ShopCountForHandle(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopCountForHandle(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopPaginatedListGroupByName(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopPaginatedListGroupByName(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopStaticsGroupByFGS(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopStaticsGroupByFGS(t);
	}

	@Resource
	private PeProdUserDao peProdUserDao;

	/**
	 * @author Ren,zhong
	 * @date 2013-06-06
	 */
	@Override
	public Long createKonkaR3ShopWithPeProdUser(KonkaR3Shop t) {
		Long sId = this.konkaR3ShopDao.insertEntity(t);

		PeProdUser user = t.getPeProdUser();
		if (null != user) {
			user.setCust_id(sId);
			this.peProdUserDao.insertEntity(user);
		}

		return sId;
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-06
	 */
	@Override
	public int modifyKonkaR3ShopWithPeProdUser(KonkaR3Shop t) {
		this.konkaR3ShopDao.updateEntity(t);

		PeProdUser user = t.getPeProdUser();
		if (null != user) {
			Long user_id = user.getId();
			if (null != user_id) { // 修改
				this.peProdUserDao.updateEntity(user);
			} else { // 新增
				user.setCust_id(t.getId());
				this.peProdUserDao.insertEntity(user);
			}
		}

		return 0;
	}

	/**
	 * @author Ren,zhong
	 * @date 2013-06-09
	 */
	@Override
	public KonkaR3Shop getKonkaR3ShopWithBranchAssign(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopWithBranchAssign(t);
	}

	/**
	 * @author Hu,Hao
	 * @date 2013-06-25
	 */
	@Override
	public Long createKonkaR3ShopForTb() {
		long i = 0L;
		String str_date = "1970-01-01";
		// 销售组织
		KonkaSalesDept ksd = new KonkaSalesDept();
		// konkaDept.setDept_type(3);
		// ksd.setSales_org_code("F055");
		ksd.setIs_del(0);
		List<KonkaSalesDept> ksdList = this.konkaSalesDeptDao.selectEntityList(ksd);
		// long j = 0l;
		if (ksdList.size() > 0) {
			// 取分公司
			for (KonkaSalesDept temp : ksdList) {

				String area_name = "";// 大区
				Long p_index = null;// 所在地区
				Integer p_area = null;// 地区编码
				String dept_name = "";// 分公司名称

				KonkaDept kDept = new KonkaDept();// 取分公司

				KonkaDept kDept1 = new KonkaDept();
				kDept1.setDept_sn(temp.getP_sales_org_code());
				if (this.konkaDeptDao.selectEntityList(kDept1).size() > 0) {
					kDept = this.konkaDeptDao.selectEntityList(kDept1).get(0);
				}

				if (null != kDept) {
					if (null != kDept.getP_index()) {
						p_index = kDept.getP_index();
					}

					if (null != kDept.getP_area()) {
						p_area = kDept.getP_area();
					}

					if (null != kDept.getDept_name()) {
						dept_name = kDept.getDept_name();
					}

					if (kDept.getP_area() != null && !"".equals(String.valueOf(kDept.getP_area()))) {
						switch (kDept.getP_area()) {
							case 10:
	                            area_name = "华东";
	                            break;
	                        case 20:
	                            area_name = "山东";
	                            break;
	                        case 30:
	                            area_name = "东北";
	                            break;
	                        case 40:
	                            area_name = "华北";
	                            break;
	                        case 50:
	                        	area_name = "华南";
	                        	break;
	                        case 60:
	                        	area_name = "西南";
	                        	break;
	                        case 70:
	                        	area_name = "华中";
	                        	break;
	                        case 80:
	                        	area_name = "西北";
	                        	break;
						}
					}
				}

				SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
				ReturnInfo<KNA1> info = new ReturnInfo<KNA1>();
				info = r3webinterfaceservice.getCustomerList(str_date, temp.getSales_org_code(), null);
				// List<KNA1> kak1List =
				// r3webinterfaceservice.getCustomerList(str_date,
				// temp.getSales_org_code(), null);
				List<KNA1> kak1List = info.getDataResult();

				if (kak1List.size() > 0) {
					for (KNA1 kna1 : kak1List) {
						i++;
						KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
						konkaR3Shop.setR3_code(kna1.getKUNNR());
						List<KonkaR3Shop> konkaR3ShopList = this.konkaR3ShopDao.selectEntityList(konkaR3Shop);

						// update by zhou 2013/09/26
						konkaR3Shop.setImport_date(new Date());

						// 取消同步合并编码 2014-09-25 LiangHouEn
						// konkaR3Shop.setCustomer_code(kna1.getKUNNR());
						konkaR3Shop.setCustomer_name(kna1.getNAME1());
						konkaR3Shop.setImport_user_id(1L);
						konkaR3Shop.setR3_sales_org_code(temp.getSales_org_code());
						konkaR3Shop.setCustomer_type(kna1.getKUKLA());
						if (konkaR3ShopList.size() < 1) {
							if (p_area != null && !"".equals(String.valueOf(p_area))) {
								konkaR3Shop.setArea_code(Long.valueOf(p_area));
							}
							konkaR3Shop.setArea_name(area_name);
							konkaR3Shop.setBranch_area_code(p_index);
							konkaR3Shop.setBranch_area_name(dept_name);
							konkaR3Shop.setBranch_area_name_2(temp.getP_sales_org_code());// 分公司编码
							if (null != kna1.getERDAT()) {
								try {
									konkaR3Shop.setAdd_date(sFormat.parse(kna1.getERDAT()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
							konkaR3Shop.setHandle_name(temp.getSales_org_name());
							konkaR3Shop.setIs_del(0L);
							// 标志为售达方
							// if (kna1.getKTOKD() != null &&
							// "F001".equals(kna1.getKTOKD())) {
							// konkaR3Shop.setIs_sdf(0);
							// }

							// ag is 售达方
							if (kna1.getIS_AG()) {
								konkaR3Shop.setIs_sdf(0);
							} else {
								konkaR3Shop.setIs_sdf(1);
							}
							// r3删除与冻结标志
							konkaR3Shop.setIs_loevm(Long.valueOf(kna1.getLOEVM()));
							konkaR3Shop.setIs_cassd(Long.valueOf(kna1.getCASSD()));
							// 设置默认客户状态为新客户
							konkaR3Shop.setShop_status("1");
							// insert
							this.konkaR3ShopDao.insertEntity(konkaR3Shop);

						} else {
							KonkaR3Shop konkaR3Shop1 = konkaR3ShopList.get(0);

							if (konkaR3Shop1.getArea_code() == null
									|| "".equals(String.valueOf(konkaR3Shop1.getArea_code()))) {
								if (p_area != null && !"".equals(String.valueOf(p_area))) {
									konkaR3Shop.setArea_code(Long.valueOf(p_area));
								}
							}
							if (konkaR3Shop1.getArea_name() == null || "".equals(konkaR3Shop1.getArea_name())) {
								konkaR3Shop.setArea_name(area_name);
							}
							if (konkaR3Shop1.getBranch_area_code() == null
									|| "".equals(String.valueOf(konkaR3Shop1.getBranch_area_code()))) {
								konkaR3Shop.setBranch_area_code(p_index);
							}
							if (konkaR3Shop1.getBranch_area_name() == null
									|| "".equals(konkaR3Shop1.getBranch_area_name())) {
								konkaR3Shop.setBranch_area_name(dept_name);
							}
							if (konkaR3Shop1.getBranch_area_name_2() == null
									|| "".equals(konkaR3Shop1.getBranch_area_name_2())) {
								konkaR3Shop.setBranch_area_name_2(temp.getP_sales_org_code());// 分公司编码
							}

							if (konkaR3Shop1.getHandle_name() == null || "".equals(konkaR3Shop1.getHandle_name())) {
								konkaR3Shop.setHandle_name(temp.getSales_org_name());
							}

							if (null != kna1.getERDAT()) {
								try {
									konkaR3Shop.setAdd_date(sFormat.parse(kna1.getERDAT()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
							}
							konkaR3Shop.setId(konkaR3Shop1.getId());
							if (kna1.getIS_AG()) {
								konkaR3Shop.setIs_sdf(0);
							} else {
								konkaR3Shop.setIs_sdf(1);
							}

							// r3删除与冻结标志
							konkaR3Shop.setIs_loevm(Long.valueOf(kna1.getLOEVM()));
							konkaR3Shop.setIs_cassd(Long.valueOf(kna1.getCASSD()));

							// 不更新合并编码
							// konkaR3Shop.setCustomer_code(null);
							// update
							this.konkaR3ShopDao.updateEntity(konkaR3Shop);
						}
					}
				}

			}
		}

		return i;
	}

	@Override
	public Long createKonkaR3ShopForTb2(String bukrs, String str_date, String[] kunnr) {
		long i = 0L;
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 通过部门求销售织列表
		KonkaSalesDept ksd = new KonkaSalesDept();
		ksd.setP_sales_org_code(bukrs);
		ksd.setIs_del(0);
		List<KonkaSalesDept> ksdList = this.konkaSalesDeptDao.selectEntityList(ksd);// 可能有多个销售组织
		for (KonkaSalesDept temp : ksdList) {
			String area_name = "";// 大区
			Long p_index = null;// 所在地区
			Integer p_area = null;// 地区编码
			String dept_name = "";// 分公司名称
			KonkaDept kDept = new KonkaDept();

			KonkaDept kdpt = new KonkaDept();
			kdpt.setDept_sn(temp.getP_sales_org_code());// 公司编码
			if (this.konkaDeptDao.selectEntityList(kdpt).size() > 0) {
				kDept = this.konkaDeptDao.selectEntityList(kdpt).get(0);
			}

			if (null != kDept) {
				if (null != kDept.getP_index()) {
					p_index = kDept.getP_index();
				}

				if (null != kDept.getP_area()) {
					p_area = kDept.getP_area();
				}

				if (null != kDept.getDept_name()) {
					dept_name = kDept.getDept_name();
				}

				if (kDept.getP_area() != null && !"".equals(String.valueOf(kDept.getP_area()))) {
					switch (kDept.getP_area()) {
					case 10:
                        area_name = "华东";
                        break;
                    case 20:
                        area_name = "山东";
                        break;
                    case 30:
                        area_name = "东北";
                        break;
                    case 40:
                        area_name = "华北";
                        break;
                    case 50:
                    	area_name = "华南";
                    	break;
                    case 60:
                    	area_name = "西南";
                    	break;
                    case 70:
                    	area_name = "华中";
                    	break;
                    case 80:
                    	area_name = "西北";
                    	break;
					}
				}
			}

			// 取客户数据
			ReturnInfo<KNA1> info = new ReturnInfo<KNA1>();
			info = r3webinterfaceservice.getCustomerList(str_date, temp.getSales_org_code(), kunnr);
			List<KNA1> kak1List = info.getDataResult();

			for (KNA1 kna1 : kak1List) {
				i++;
				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.setR3_code(kna1.getKUNNR());
				List<KonkaR3Shop> konkaR3ShopList = this.konkaR3ShopDao.selectEntityList(konkaR3Shop);
				// update by zhou 2013/09/26
				konkaR3Shop.setImport_date(new Date());

				// 取消同步合并编码 2014-09-25 LiangHouEn
				// konkaR3Shop.setCustomer_code(kna1.getKUNNR());
				konkaR3Shop.setCustomer_name(kna1.getNAME1());
				konkaR3Shop.setImport_user_id(1L);// dead code
				konkaR3Shop.setR3_sales_org_code(temp.getSales_org_code());
				konkaR3Shop.setCustomer_type(kna1.getKUKLA());
				if (konkaR3ShopList.size() < 1) {
					if (p_area != null && !"".equals(String.valueOf(p_area))) {
						konkaR3Shop.setArea_code(Long.valueOf(p_area));
					}
					konkaR3Shop.setCustomer_code(kna1.getKUNNR());
					konkaR3Shop.setArea_name(area_name);
					konkaR3Shop.setBranch_area_code(p_index);
					konkaR3Shop.setBranch_area_name(dept_name);
					konkaR3Shop.setBranch_area_name_2(temp.getP_sales_org_code());// 分公司编码
					if (null != kna1.getERDAT()) {
						try {
							konkaR3Shop.setAdd_date(sFormat.parse(kna1.getERDAT()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					konkaR3Shop.setHandle_name(temp.getSales_org_name());
					konkaR3Shop.setIs_del(0L);
					// ag is 售达方
					if (kna1.getIS_AG()) {
						konkaR3Shop.setIs_sdf(0);
					} else {
						konkaR3Shop.setIs_sdf(1);
					}

					// r3删除与冻结标志
					konkaR3Shop.setIs_loevm(Long.valueOf(kna1.getLOEVM()));
					konkaR3Shop.setIs_cassd(Long.valueOf(kna1.getCASSD()));

					konkaR3Shop.setIs_minus_sales(0);
					// insert
					this.konkaR3ShopDao.insertEntity(konkaR3Shop);
				} else {
					KonkaR3Shop konkaR3Shop1 = konkaR3ShopList.get(0);

					if (konkaR3Shop1.getArea_code() == null || "".equals(String.valueOf(konkaR3Shop1.getArea_code()))) {
						if (p_area != null && !"".equals(String.valueOf(p_area))) {
							konkaR3Shop.setArea_code(Long.valueOf(p_area));
						}
					}
					if (konkaR3Shop1.getArea_name() == null || "".equals(konkaR3Shop1.getArea_name())) {
						konkaR3Shop.setArea_name(area_name);
					}
					if (konkaR3Shop1.getBranch_area_code() == null
							|| "".equals(String.valueOf(konkaR3Shop1.getBranch_area_code()))) {
						konkaR3Shop.setBranch_area_code(p_index);
					}
					if (konkaR3Shop1.getBranch_area_name() == null || "".equals(konkaR3Shop1.getBranch_area_name())) {
						konkaR3Shop.setBranch_area_name(dept_name);
					}
					if (konkaR3Shop1.getBranch_area_name_2() == null || "".equals(konkaR3Shop1.getBranch_area_name_2())) {
						konkaR3Shop.setBranch_area_name_2(temp.getP_sales_org_code());// 分公司编码
					}

					if (konkaR3Shop1.getHandle_name() == null || "".equals(konkaR3Shop1.getHandle_name())) {
						konkaR3Shop.setHandle_name(temp.getSales_org_name());
					}
					if (null != kna1.getERDAT()) {
						try {
							konkaR3Shop.setAdd_date(sFormat.parse(kna1.getERDAT()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					konkaR3Shop.setId(konkaR3Shop1.getId());
					if (kna1.getIS_AG()) {
						konkaR3Shop.setIs_sdf(0);
					} else {
						konkaR3Shop.setIs_sdf(1);
					}
					// r3删除与冻结标志
					konkaR3Shop.setIs_loevm(Long.valueOf(kna1.getLOEVM()));
					konkaR3Shop.setIs_cassd(Long.valueOf(kna1.getCASSD()));
					// konkaR3Shop.setCustomer_code(kna1.getKUNNR());
					// update
					this.konkaR3ShopDao.updateEntity(konkaR3Shop);
				}
			}

		}

		return i;
	}

	/**
	 * @author pan,gang
	 * @date 2013-7-5
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopForYwyPaginatedList(KonkaR3Shop t) {

		List<KonkaR3Shop> result = konkaR3ShopDao.selectKonkaR3ShopForYwyPaginatedList(t);

		// 业务处理
		if (null != result && result.size() > 0) {
			for (KonkaR3Shop c : result) {
				KonkaR3Shop temp = new KonkaR3Shop();
				temp.setR3_code(c.getR3_code());
				temp = this.getKonkaR3ShopSimpleStat(temp);
				c.getMap().put("stat", temp);
			}
		}

		return result;
	}

	/**
	 * @author ZHOU
	 * @date 2013-7-12
	 * @param customer_code
	 * @param ids
	 */
	@Override
	public void modifyKonkaR3ShopBatch(String customer_code, Set<String> set) {
		KonkaR3Shop shop = new KonkaR3Shop();
		shop.setCustomer_code(customer_code);
		for (String id : set) {
			shop.setId(Long.valueOf(id));
			konkaR3ShopDao.updateEntity(shop);
		}
	}

	/**
	 * @author ZHOU
	 * @param deptName 分公司
	 * @return 经办信息列表
	 */
	@Override
	public List<KonkaR3Shop> getJBDataBydeptName(KonkaR3Shop t) {
		return konkaR3ShopDao.selectJBDataBydeptName(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-08-11
	 * @return 返回R3客户列表
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopListByUserName(KonkaR3Shop t) {
		return konkaR3ShopDao.selectKonkaR3ShopListByUserName(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-08-29
	 * @return 新增客户信息以及客户联系人信息
	 */
	@Override
	public Long createKonkaR3ShopAndLink(KonkaR3Shop t) {

		String[] r3_link_position = t.getR3_link_position();
		String[] r3_link_real_name = t.getR3_link_real_name();
		String[] r3_link_sex = t.getR3_link_sex();
		String[] r3_link_birthday = t.getR3_link_birthday();
		String[] r3_link_tel = t.getR3_link_tel();
		String[] r3_link_email = t.getR3_link_email();
		String[] r3_link_favor = t.getR3_link_favor();

		Long r3_shop_id = this.konkaR3ShopDao.insertEntity(t);
		if (r3_link_position != null && r3_link_real_name != null && r3_link_sex != null && r3_link_birthday != null
				&& r3_link_tel != null && r3_link_email != null && r3_link_favor != null
				&& r3_link_position.length == r3_link_real_name.length && r3_link_position.length == r3_link_sex.length
				&& r3_link_position.length == r3_link_birthday.length && r3_link_position.length == r3_link_tel.length
				&& r3_link_position.length == r3_link_email.length && r3_link_position.length == r3_link_favor.length) {
			for (int i = 0; i < r3_link_position.length; i++) {
				if (!"".equals(r3_link_position[i]) || !"".equals(r3_link_real_name[i])) {
					KonkaR3ShopLink kr = new KonkaR3ShopLink();
					kr.setR3_shop_id(r3_shop_id);
					kr.setPosition(r3_link_position[i]);
					kr.setReal_name(r3_link_real_name[i]);
					if (!"".equals(r3_link_sex[i])) {
						kr.setSex(Integer.valueOf(r3_link_sex[i]));
					}
					kr.setTel(r3_link_tel[i]);
					kr.setEmail(r3_link_email[i]);
					kr.setFavor(r3_link_favor[i]);
					if (!"".equals(r3_link_birthday[i])) {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						try {
							kr.setBirthday(df.parse(r3_link_birthday[i]));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					kr.setAdd_user_id(t.getImport_user_id());
					kr.setIs_del(0);
					this.konkaR3ShopLinkDao.insertEntity(kr);
				}
			}
		}
		return r3_shop_id;

	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-08-29
	 * @return 修改客户信息以及客户联系人信息
	 */
	@Override
	public int modifyKonkaR3ShopAndLink(KonkaR3Shop t) {
		String[] r3_link_position = t.getR3_link_position();
		String[] r3_link_real_name = t.getR3_link_real_name();
		String[] r3_link_sex = t.getR3_link_sex();
		String[] r3_link_birthday = t.getR3_link_birthday();
		String[] r3_link_tel = t.getR3_link_tel();
		String[] r3_link_email = t.getR3_link_email();

		// IHS需求字段值 2014-09-23
		String[] r3_link_job = t.getR3_link_job();
		String[] r3_link_telephone = t.getR3_link_telephone();
		String[] r3_link_fax = t.getR3_link_fax();
		String[] r3_link_weixin = t.getR3_link_weixin();
		String[] r3_link_qq = t.getR3_link_qq();
		String[] r3_link_default = t.getR3_link_default();
		String[] r3_link_valid = t.getR3_link_valid();

		// 品牌销售信息 2014-09-23
		String[] brand_id = t.getBrand_id();
		String[] sale_year = t.getSale_year();
		String[] annual_salse = t.getAnnual_salse();
		String[] sale_rank = t.getSale_rank();

		// 添加联系人信息
		if (r3_link_position != null && r3_link_real_name != null && r3_link_sex != null && r3_link_tel != null
				&& r3_link_email != null && r3_link_email != null && r3_link_job != null && r3_link_telephone != null
				&& r3_link_fax != null && r3_link_weixin != null && r3_link_qq != null
				&& r3_link_position.length == r3_link_real_name.length && r3_link_position.length == r3_link_sex.length
				&& r3_link_position.length == r3_link_tel.length && r3_link_position.length == r3_link_email.length
				&& r3_link_position.length == r3_link_job.length && r3_link_position.length == r3_link_telephone.length
				&& r3_link_position.length == r3_link_fax.length && r3_link_position.length == r3_link_weixin.length
				&& r3_link_position.length == r3_link_qq.length && r3_link_position.length == r3_link_default.length
				&& r3_link_position.length == r3_link_valid.length
				&& r3_link_position.length == r3_link_birthday.length) {
			KonkaR3ShopLink krs = new KonkaR3ShopLink();
			krs.setR3_shop_id(t.getId());
			krs.setIs_del(1);
			this.konkaR3ShopLinkDao.deleteKonkaR3ShopLinkForR3(krs);
			for (int i = 0; i < r3_link_position.length; i++) {
				if (!"".equals(r3_link_position[i]) || !"".equals(r3_link_real_name[i])) {
					KonkaR3ShopLink kr = new KonkaR3ShopLink();
					kr.setR3_shop_id(t.getId());
					kr.setPosition(r3_link_position[i]);
					kr.setReal_name(r3_link_real_name[i]);
					if (!"".equals(r3_link_sex[i])) {
						kr.setSex(Integer.valueOf(r3_link_sex[i]));
					}
					if (!"".equals(r3_link_birthday[i])) {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						try {
							kr.setBirthday(df.parse(r3_link_birthday[i]));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					kr.setTel(r3_link_tel[i]);
					kr.setEmail(r3_link_email[i]);
					// kr.setAdd_user_id(t.getImport_user_id());
					kr.setAdd_user_id((Long) (t.getMap().get("add_user_id")));
					kr.setIs_del(0);

					// 2014-09-22新增字段值
					kr.setJob(r3_link_job[i]);
					kr.setTelephone(r3_link_telephone[i]);
					kr.setFax(r3_link_fax[i]);
					kr.setWeixin(r3_link_weixin[i]);
					kr.setQq(r3_link_qq[i]);
					kr.setIs_default(r3_link_default[i]);
					kr.setIs_valid(r3_link_valid[i]);

					this.konkaR3ShopLinkDao.insertEntity(kr);
				}
			}
		}

		// 添加品牌信息
		if (brand_id != null && sale_year != null && annual_salse != null && sale_rank != null
				&& brand_id.length == sale_year.length && brand_id.length == annual_salse.length
				&& brand_id.length == sale_rank.length) {

			// 先删除原关联记录
			KonkaR3ShopBrand krs = new KonkaR3ShopBrand();
			krs.setR3_shop_id(t.getId());
			krs.setIs_del(1);
			this.konkaR3ShopBrandDao.deleteKonkaR3ShopBrandForR3(krs);
			for (int i = 0; i < brand_id.length; i++) {
				if (!"".equals(brand_id[i]) && !"".equals(sale_year[i])) {
					KonkaR3ShopBrand kr = new KonkaR3ShopBrand();
					kr.setR3_shop_id(t.getId());
					kr.setBrand_id(Long.valueOf(brand_id[i]));
					kr.setSale_year(Long.valueOf(sale_year[i]));
					BigDecimal bd = new BigDecimal(annual_salse[i]);
					kr.setAnnual_salse(bd);
					kr.setRanks(Integer.valueOf(sale_rank[i]));
					kr.setAdd_user_id((Long) (t.getMap().get("add_user_id")));
					kr.setIs_del(0);

					this.konkaR3ShopBrandDao.insertEntity(kr);
				}
			}
		}

		if (null != t.getId() && null != t.getMap().get("remove_branch_assign")) {
			BranchAssign ba = new BranchAssign();
			ba.setKonka_r3_id(t.getId());
			this.branchAssignDao.deleteEntity(ba);// 解除客户的绑定
		}

		if (t.getAttachmentList() != null && t.getAttachmentList().size() > 0) {
			for (Attachment a : t.getAttachmentList()) {
				a.setLink_tab("KONKA_R3_SHOP");
				a.setLink_id(t.getId());
				this.attachmentDao.insertEntity(a);
			}
		}

		return this.konkaR3ShopDao.updateEntity(t);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-29
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopStockByR3CodeList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopStockByR3CodeList(t);
	}

	/**
	 * 新增网点记录
	 * 
	 * @author Pan,Gang
	 * @version 2013-09-06
	 * @throws Exception
	 */
	@Override
	public Long createKonkaR3ShopWithJBasePartner(JBasePartner t) throws Exception {

		// 根据上级客户计算当前网点级别
		JBasePartner en = new JBasePartner();
		en.setPartner_id(t.getPar_c_id());
		JBasePartner res = this.jBasePartnerDao.selectEntity(en);
		Long level = 0L;
		if (res == null) {
			level = 1L;
		} else {
			level = res.getP_level() + 1;
		}
		t.setP_level(level);

		// 复制JBasePartner 信息到KonkaR3Shop
		if (null != t.getMap().get("user_name")) {
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			if (StringUtils.isNotBlank(t.getLink_mobile())) {
				konkaR3Shop.setLink_man_mobile(t.getLink_mobile());
			}
			if (StringUtils.isNotBlank(t.getPartner_name())) {
				konkaR3Shop.setCustomer_name(t.getPartner_name());
			}
			if (StringUtils.isNotBlank(t.getLink_name())) {
				konkaR3Shop.setLink_man_name(t.getLink_name());
			}
			if (StringUtils.isNotBlank(t.getLink_addr())) {
				konkaR3Shop.setLink_man_addr(t.getLink_addr());
			}
			if (StringUtils.isNotBlank(t.getLink_tel())) {
				konkaR3Shop.setLink_man_tel(t.getLink_tel());
			}
			konkaR3Shop.setIs_konka(0);
			konkaR3Shop.setR3_code(t.getPartner_sn());// 以往来单位编号作为r3编码

			// 保存分公司、经办信息
			KonkaDept kd = new KonkaDept();

			kd.setDept_id(t.getDept_id());
			kd = this.konkaDeptDao.selectEntity(kd);
			konkaR3Shop.setBranch_area_name(kd.getDept_name());
			konkaR3Shop.setBranch_area_name_2(kd.getDept_sn());

			kd = new KonkaDept();
			if (null != t.getJb_job_id()) {
				kd.setDept_id(t.getJb_job_id());
				kd = this.konkaDeptDao.selectEntity(kd);
				konkaR3Shop.setHandle_name(kd.getDept_name());
			}
			Long r3_id = this.konkaR3ShopDao.insertEntity(konkaR3Shop);

			// 创建客户端账户
			PeProdUser peProdUser1 = new PeProdUser();
			peProdUser1.setUser_type(2);// user_type=2 表示客户端用户
			peProdUser1.setUser_name((String) t.getMap().get("user_name"));
			DESPlus des = new DESPlus();
			if (null != t.getMap().get("pass_word")) {
				peProdUser1.setPass_word(des.encrypt((String) t.getMap().get("pass_word")));
			}
			peProdUser1.setCust_id(r3_id);
			peProdUser1.setDept_id(t.getDept_id());
			peProdUser1.setAdd_e_user_id(t.getCreate_user_id());
			this.peProdUserDao.insertEntity(peProdUser1);

			t.setPartner_c_id(r3_id);// 往来单位客户id
		}

		// 在网点分配表中新增记录
		BranchAssign ba = new BranchAssign();
		ba.setKonka_r3_id(t.getPar_c_id());
		ba = this.branchAssignDao.selectEntity(ba);

		if (ba == null) {
			BranchAssign ban = new BranchAssign();
			ban.setKonka_r3_id(t.getPar_c_id());
			ban.setUser_id(Long.valueOf(t.getMap().get("current_user_id").toString()));
			ban.setBranch_type(1);
			ban.setAdd_user_id(Long.valueOf(t.getMap().get("current_user_id").toString()));
			ban.setAdd_date(new Date());
			this.branchAssignDao.insertEntity(ban);
		}

		Long jbs_ids = this.jBasePartnerDao.insertEntity(t);

		return jbs_ids;
	}

	/**
	 * @author Pan,Gang
	 * @version 2013-09-06
	 * @throws Exception
	 */
	@Override
	public int modifyKonkaR3ShopWithJBasePartner(JBasePartner t) throws Exception {

		// 根据上级客户计算当前网点级别
		JBasePartner en = new JBasePartner();
		en.setPartner_id(t.getPar_c_id());
		JBasePartner res = this.jBasePartnerDao.selectEntity(en);
		Long level = 0L;
		if (res == null) {
			level = 1L;
		} else {
			level = res.getP_level() + 1;
		}
		t.setP_level(level);

		// 以下代码导致修改网点时，更改了客户的信息，暂注释 2014-09-18
		// KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		// if (StringUtils.isNotBlank(t.getLink_mobile())) {
		// konkaR3Shop.setLink_man_mobile(t.getLink_mobile());
		// }
		// if (StringUtils.isNotBlank(t.getLink_name())) {
		// konkaR3Shop.setCustomer_name(t.getLink_name());
		// }
		// if (StringUtils.isNotBlank(t.getLink_addr())) {
		// konkaR3Shop.setLink_man_addr(t.getLink_addr());
		// }
		// if (StringUtils.isNotBlank(t.getLink_tel())) {
		// konkaR3Shop.setLink_man_tel(t.getLink_tel());
		// }
		// konkaR3Shop.setR3_code(t.getPartner_sn());// 以往来单位编号作为r3编码
		//
		// if (null == t.getPartner_c_id()) {
		// Long id = this.konkaR3ShopDao.insertEntity(konkaR3Shop);
		// t.setPartner_c_id(id);
		// } else {
		// konkaR3Shop.setId(t.getPartner_c_id());
		// this.konkaR3ShopDao.updateEntity(konkaR3Shop);
		// }

		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setCust_id(t.getPartner_c_id());
		peProdUser = this.peProdUserDao.selectEntity(peProdUser);
		if (null == peProdUser) {
			PeProdUser peProdUser_insert = new PeProdUser();
			peProdUser_insert.setUser_type(2);// user_type=2 表示客户端用户
			if (null != t.getMap().get("user_name"))
				peProdUser_insert.setUser_name((String) t.getMap().get("user_name"));
			if (null != t.getMap().get("pass_word"))
				peProdUser_insert.setPass_word(new DESPlus().encrypt((String) t.getMap().get("pass_word")));
			peProdUser_insert.setCust_id(t.getPartner_c_id());
			this.peProdUserDao.insertEntity(peProdUser_insert);
		} else {
			if (null != t.getMap().get("pass_word")) {
				peProdUser.setPass_word(new DESPlus().encrypt((String) t.getMap().get("pass_word")));// 修改密码
			}
            if (null != t.getMap().get("user_name")) {
				peProdUser.setUser_name(String.valueOf(t.getMap().get("user_name")));
				// updated by zhou 20150702 允许修改用户名,因为有的门店会更换用户

            }  
            this.peProdUserDao.updateEntity(peProdUser);
		}

		int jbs_id = this.jBasePartnerDao.updateEntity(t);

		return jbs_id;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-23
	 * @return 根据p_index查询所能获取到的客户数量
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopCountByPIndex(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopCountByPIndex(t);
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2013-09-23
	 * @return 根据p_index查询所能获取到的客户销售额
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopListByPIndex(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopListByPIndex(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-10-09
	 * @return 根据用户ID查询客户R3_CODE
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopForUserIdList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopForUserIdList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-10-14
	 */
	@Override
	public Long getKonkaR3ShopForYwyCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopForYwyCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-10-28
	 */
	@Override
	public Long getKonkaR3ShopForOrderCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopForOrderCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-10-28
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopForOrderList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopForOrderList(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-04
	 */
	@Override
	public Long getKonkaR3ShopForStocksCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopForStocksCount(t);
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-12-04
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopForStocksList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopForStocksList(t);
	}

	/**
	 * 订货会 参会客户自动提出
	 * 
	 * @author Zhang,Chao
	 * @version 2014-01-25
	 */
	@Override
	public Long getKonkaR3ShopResultListByViewCustomerCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopResultListByViewCustomerCount(t);
	}

	/**
	 * 订货会 参会客户自动提出
	 * 
	 * @author Zhang,Chao
	 * @version 2014-01-25
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopResultListByViewCustomerList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopResultListByViewCustomerList(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopListForYwyAndJb(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopListForYwyAndJb(t);
	}

	@Override
	public Long getDeptIdOfKonkaR3Code(String r3code) {
		return this.konkaR3ShopDao.selectDeptIdFromR3code(r3code);
	}

	/**
	 * 合作伙伴关系R3编码
	 * 
	 * @author Xiao,GuoJian
	 * @version 2014-05-05
	 */
	@Override
	public Long getKonkaR3ShopForR3CodeCount(KonkaR3Shop t) {
		return konkaR3ShopDao.selectKonkaR3ShopForR3CodeCount(t);
	}

	/**
	 * 合作伙伴关系R3编码
	 * 
	 * @author Xiao,GuoJian
	 * @date 2014-05-05
	 */
	@Override
	public List<KonkaR3Shop> getKonkaR3ShopForR3CodePaginatedList(KonkaR3Shop t) {
		return konkaR3ShopDao.selectKonkaR3ShopForR3CodePaginatedList(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopForCustVisit(KonkaR3Shop entity) {
		
		return konkaR3ShopDao.selectKonkaR3ShopForCustVisit(entity);
	}

	@Override
	public List<KonkaR3Shop> exportData(KonkaR3Shop t) {
		List<KonkaR3Shop> result = konkaR3ShopDao.selectKonkaR3ShopForYwyPaginatedList(t);

		int max = 0;
		if (null != result && result.size() > 0) {
			for (KonkaR3Shop c : result) {
				KonkaR3Shop temp = new KonkaR3Shop();
				temp.setR3_code(c.getR3_code());
				temp = this.getKonkaR3ShopSimpleStat(temp);
				c.getMap().put("stat", temp);

				// 处理所在城市
				if (c.getEntp_p_index() != null && String.valueOf(c.getEntp_p_index()).length() >= 6) {
					// 省/直辖市/自治区
					BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
					baseProvinceFour.setP_index(new Long(String.valueOf(c.getEntp_p_index()).substring(0, 2) + "0000"));
					baseProvinceFour.setDel_mark(0);
					List<BaseProvinceListFour> baseProvinceFourList = this.baseProvinceListFourDao
							.selectEntityList(baseProvinceFour);
					if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
						BaseProvinceListFour b = baseProvinceFourList.get(0);
						c.getMap().put("province", b.getP_name());
					}
					if (!(String.valueOf(c.getEntp_p_index()).substring(0, 2) + "0000").equals(String.valueOf(
							c.getEntp_p_index()).substring(0, 4)
							+ "00")) {
						// 市
						baseProvinceFour
								.setP_index(new Long(String.valueOf(c.getEntp_p_index()).substring(0, 4) + "00"));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.getMap().put("city", b.getP_name());
						}
					}
					if (!String.valueOf(c.getEntp_p_index()).substring(0, 6)
							.equals(String.valueOf(c.getEntp_p_index()).substring(0, 4) + "00")) {
						// 县
						baseProvinceFour.setP_index(new Long(String.valueOf(c.getEntp_p_index()).substring(0, 6)));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.getMap().put("country", b.getP_name());
						}
					}
					if (!String.valueOf(c.getEntp_p_index()).substring(0, 6)
							.equals(String.valueOf(c.getEntp_p_index()))) {
						// 乡镇
						baseProvinceFour.setP_index(new Long(String.valueOf(c.getEntp_p_index())));
						baseProvinceFourList = null;
						baseProvinceFourList = this.baseProvinceListFourDao.selectEntityList(baseProvinceFour);
						if (null != baseProvinceFourList && baseProvinceFourList.size() > 0) {
							BaseProvinceListFour b = baseProvinceFourList.get(0);
							c.getMap().put("town", b.getP_name());
						}
					}
				}

				// 处理联系人信息
				KonkaR3ShopLink kr = new KonkaR3ShopLink();
				kr.setR3_shop_id(Long.valueOf(c.getId()));
				kr.setIs_del(0);
				List<KonkaR3ShopLink> linkList = KonkaR3ShopLinkDao.selectEntityList(kr);
				if (null != linkList && linkList.size() > 0) {
					c.getMap().put("linkList", linkList);
					if (max < linkList.size()) {
						max = linkList.size();
					}
				}
			}
		}

		// 传递最多联系人数
		KonkaR3Shop ta = new KonkaR3Shop();
		ta.getMap().put("MaxNum", max);
		result.add(ta);

		return result;
	}

	/**
	 * 客户 零售 结算 汇款统计list
	 */
	@Override
	public List<HashMap> getKonkaR3ShopMoneryPaginatedList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopMoneryPaginatedList(t);
	}

	/**
	 * 客户 零售 结算 汇款统计count
	 */
	@Override
	public Long getKonkaR3ShopMoneryPaginatedListCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopMoneryPaginatedListCount(t);
	}

	@Override
	public HashMap getKonkaR3ShopOrJbasePartner(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopOrJbasePartner(t);
	}

	@Override
	public Long getMergeR3ShopCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectMergeR3ShopCount(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> getMergeR3ShopList(KonkaR3Shop t) {

		List<HashMap> result = this.konkaR3ShopDao.selectMergeR3ShopList(t);

		// 业务处理
		// DecimalFormat df = new DecimalFormat("#.00");
		// if (null != result && result.size() > 0) {
		// for (HashMap c : result) {
		// KonkaR3Shop subtemp = null;
		// String R3_CODE_LIST = (String)c.get("R3_CODE_LIST");
		// String[] arr = R3_CODE_LIST.split(",");
		// for(int i=0; i<arr.length; i++){
		// KonkaR3Shop temp = new KonkaR3Shop();
		// temp.setR3_code(arr[i]);
		// temp = this.getKonkaR3ShopSimpleStat(temp);
		// if(null==subtemp){
		// subtemp = temp;
		// }else{
		// Object obj_ccm_1 = subtemp.getMap().get("cur_cls_money");
		// double cur_cls_money1 = (obj_ccm_1==null ? 0 :
		// Double.valueOf(obj_ccm_1.toString()));
		// Object obj_ccm_2 = temp.getMap().get("cur_cls_money");
		// double cur_cls_money2 = (obj_ccm_2==null ? 0 :
		// Double.valueOf(obj_ccm_2.toString()));
		// subtemp.getMap().put("cur_cls_money", cur_cls_money1+cur_cls_money2);
		//
		// Object obj_lcm_1 = subtemp.getMap().get("lastyear_cls_money");
		// double lastyear_cls_money1 = (obj_lcm_1==null ? 0 :
		// Double.valueOf(obj_lcm_1.toString()));
		// Object obj_lcm_2 = temp.getMap().get("lastyear_cls_money");
		// double lastyear_cls_money2 = (obj_lcm_2==null ? 0 :
		// Double.valueOf(obj_lcm_2.toString()));
		// subtemp.getMap().put("lastyear_cls_money",
		// lastyear_cls_money1+lastyear_cls_money2);
		//
		// Object obj_cbm_1 = subtemp.getMap().get("cur_back_money");
		// double cur_back_money1 = (obj_cbm_1==null ? 0 :
		// Double.valueOf(obj_cbm_1.toString()));
		// Object obj_cbm_2 = temp.getMap().get("cur_back_money");
		// double cur_back_money2 = (obj_cbm_2==null ? 0 :
		// Double.valueOf(obj_cbm_2.toString()));
		// subtemp.getMap().put("cur_back_money",
		// cur_back_money1+cur_back_money2);
		//
		// Object obj_lbm_1 = subtemp.getMap().get("lastyear_back_money");
		// double lastyear_back_money1 = (obj_lbm_1==null ? 0 :
		// Double.valueOf(obj_lbm_1.toString()));
		// Object obj_lbm_2 = temp.getMap().get("lastyear_back_money");
		// double lastyear_back_money2 = (obj_lbm_2==null ? 0 :
		// Double.valueOf(obj_lbm_2.toString()));
		// subtemp.getMap().put("lastyear_back_money",
		// lastyear_back_money1+lastyear_back_money2);
		//
		// Object obj_cb_1 = subtemp.getMap().get("credit_balance");
		// double credit_balance1 = (obj_cb_1==null ? 0 :
		// Double.valueOf(obj_cb_1.toString()));
		// Object obj_cb_2 = temp.getMap().get("credit_balance");
		// double credit_balance2 = (obj_cb_2==null ? 0 :
		// Double.valueOf(obj_cb_2.toString()));
		// subtemp.getMap().put("credit_balance",
		// credit_balance1+credit_balance2);
		// }
		// }
		// Object clsm = subtemp.getMap().get("cur_cls_money");
		// if(null==clsm){
		// c.put("CUR_CLS_MONEY","");
		// }else{
		// String temp = df.format(clsm);
		// if(temp.length()==3){
		// c.put("CUR_CLS_MONEY", "0"+temp);
		// }else{
		// if(temp.contains("-.")){
		// c.put("CUR_CLS_MONEY","-0"+temp.substring(1, temp.length()));
		// }else{
		// c.put("CUR_CLS_MONEY",temp);
		// }
		// }
		// }
		// Object lclsm = subtemp.getMap().get("lastyear_cls_money");
		// if(null==lclsm){
		// c.put("LASTYEAR_CLS_MONEY","");
		// }else{
		// String temp = df.format(lclsm);
		// if(temp.length()==3){
		// c.put("LASTYEAR_CLS_MONEY", "0"+temp);
		// }else{
		// if(temp.contains("-.")){
		// c.put("LASTYEAR_CLS_MONEY","-0"+temp.substring(1, temp.length()));
		// }else{
		// c.put("LASTYEAR_CLS_MONEY",temp);
		// }
		// }
		// }
		// Object backm = subtemp.getMap().get("cur_back_money");
		// if(null==backm){
		// c.put("CUR_BACK_MONEY","");
		// }else{
		// String temp = df.format(backm);
		// if(temp.length()==3){
		// c.put("CUR_BACK_MONEY", "0"+temp);
		// }else{
		// if(temp.contains("-.")){
		// c.put("CUR_BACK_MONEY","-0"+temp.substring(1, temp.length()));
		// }else{
		// c.put("CUR_BACK_MONEY",temp);
		// }
		// }
		// }
		// Object lbackm = subtemp.getMap().get("lastyear_back_money");
		// if(null==lbackm){
		// c.put("LASTYEAR_BACK_MONEY","");
		// }else{
		// String temp = df.format(lbackm);
		// if(temp.length()==3){
		// c.put("LASTYEAR_BACK_MONEY", "0"+temp);
		// }else{
		// if(temp.contains("-.")){
		// c.put("LASTYEAR_BACK_MONEY","-0"+temp.substring(1, temp.length()));
		// }else{
		// c.put("LASTYEAR_BACK_MONEY",temp);
		// }
		// }
		// }
		// Object cb = subtemp.getMap().get("credit_balance");
		// if(null==cb){
		// c.put("CREDIT_BALANCE","");
		// }else{
		// String temp = df.format(cb);
		// if(temp.length()==3){
		// c.put("CREDIT_BALANCE", "0"+temp);
		// }else{
		// if(temp.contains("-.")){
		// c.put("CREDIT_BALANCE","-0"+temp.substring(1, temp.length()));
		// }else{
		// c.put("CREDIT_BALANCE",temp);
		// }
		// }
		// }
		// }
		// }

		return result;
	}

	@Override
	public Long getMergeR3ShopcustCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectMergeR3ShopcustCount(t);
	}

	@Override
	public Long getKonkaR3ShopWithJzAndPdCount(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopWithJzAndPdCount(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopWithJzAndPdList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopWithJzAndPdList(t);
	}

	@Override
	public List<KonkaR3Shop> getKonkaR3ShopWithJzAndPdPaginatedList(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectKonkaR3ShopWithJzAndPdPaginatedList(t);
	}

	@Override
	public List<KonkaR3Shop> getShopForAgent(KonkaR3Shop t) {
		return this.konkaR3ShopDao.selectShopForAgent(t);
	}

	@Override
	public int modifyKonkaR3ShopByID(KonkaR3Shop t) {

		MvOrgOfCxy cxy = new MvOrgOfCxy();
		cxy.setKonka_r3_id(t.getId());
		this.mvOrgOfCxyDao.deleteEntity(cxy);

		cxy.getMap().put("konka_r3_id", t.getId().toString());
		this.mvOrgOfCxyDao.updateMvOrgOfCxyByCxyUserId(cxy);

		MvOrgOfCxyAll call = new MvOrgOfCxyAll();
		call.setKonka_r3_id(t.getId());
		this.mvOrgOfCxyAllDao.deleteEntity(call);

		call.getMap().put("konka_r3_id", t.getId().toString());
		this.mvOrgOfCxyAllDao.updateMvOrgOfCxyAllByCxyUserId(call);

		MvOrgOfCustomer mc = new MvOrgOfCustomer();
		mc.setKonka_r3_id(t.getId());
		this.mvOrgOfCustomerDao.deleteEntity(mc);

		mc.getMap().put("konka_r3_id", t.getId());
		this.mvOrgOfCustomerDao.updateMvOrgOfCustomerByUserId(mc);

		MvOrgOfCustomerAll mall = new MvOrgOfCustomerAll();
		mall.setKonka_r3_id(t.getId());
		this.mvOrgOfCustomerAllDao.deleteEntity(mall);

		mall.getMap().put("konka_r3_id", t.getId());
		this.mvOrgOfCustomerAllDao.updateMvOrgOfCustomerAllByUserId(mall);

		return this.konkaR3ShopDao.updateKonkaR3ShopByID(t);
	}

}