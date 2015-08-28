package com.ebiz.mmt.web.struts.jxc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseBrandInfo;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.JxcCustomer;
import com.ebiz.mmt.domain.JxcPd;
import com.ebiz.mmt.domain.JxcSupplier;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaDeptPdLink;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetailsAudit;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.MvPdTypeJoinBrand;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.StdEntpMain;
import com.ebiz.mmt.domain.StdEntpUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.EncryptUtilsV2;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wu,Yang
 * @version 2011-10-10
 */
public class BaseJxcAction extends com.ebiz.mmt.web.struts.customer.BaseClientJxcAction {
	/**
	 * 判断是否登陆
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean isSessionLoginForKonkaJxc(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if (null == session) {
			return false;
		}
		if (null == session.getAttribute(Constants.USER_INFO)) {
			return false;
		}
		PeProdUser entpUser = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == entpUser) {
			return false;
		}
		String requestURI = request.getRequestURI();
		String ctx = request.getContextPath();
		if (null == ctx) {
			return false;
		} else if ("/".equals(ctx)) {
			ctx = "";
		}
		if (null == requestURI) {
			return false;
		}

		return true;
	}

	/**
	 * 转向没有登陆的提示
	 * 
	 * @throws Exception
	 */
	public ActionForward forwardNoSessionForKonkaJxc(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sendUrl = super.getCtxPath(request) + "/login.do";
		String msg = "您没有登录或操作超时，请重新登录！";
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + sendUrl
				+ "'}");
		return null;
	}

	public PeProdUser getSessionUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		return ui;
	}

	/**
	 * @author Jiang,JiaYong @2010-06-07
	 * @editor last edit by Xing,XiuDong @2011-02-24
	 * @editor last edit by Wu,Yang @2011-10-13
	 *         增加用户登录后，换密钥时判断换的密钥是否和SESSION中保存的密钥是否匹配
	 * @refer com.ebiz.mmt.web.struts.client.BaseClientAction.authenticate
	 */
	public StdEntpUser getStdEntpUserFromRequest(HttpServletRequest request, HttpServletResponse response, String keySeq) {
		if (StringUtils.isBlank(keySeq)) {
			String msg = super.getMessage(request, "client.login.fail.keyseq.none");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 增加用户登录后，换密钥时判断换的密钥是否和SESSION中保存的密钥是否匹配
		HttpSession session = request.getSession();
		String[] keySeqs = (String[]) session.getAttribute(Constants.USER_OWN_ENTP_KEYS);
		if (ArrayUtils.isNotEmpty(keySeqs)) {
			boolean isAllowVisit = false;
			for (String key : keySeqs) {
				if (key.equalsIgnoreCase(keySeq)) {
					isAllowVisit = true;
				}
			}
			if (!isAllowVisit) {
				String msg = super.getMessage(request, "client.visituser.not.match.seessionuser");
				logger.info(msg);
				logger.warn(msg);
				return null;
			}
		}

		StdEntpUser seu = new StdEntpUser();
		seu.setKey_seq(keySeq);
		seu.getRow().setCount(2);

		List<StdEntpUser> seuList = super.getFacade().getStdEntpUserService().getStdEntpUserList(seu);// 只取chea_fill.std_entp_user
		// List<StdEntpUser> seuList =
		// super.getFacade().getStdEntpUserService().getStdEntpUserOrUserInfoList(seu);//取chea_fill.std_entp_user
		// union
		// chea_fill.user_info

		if (null == seuList || seuList.size() == 0) { // 密钥没有在本地绑定
			String msg = super.getMessage(request, "client.authenticate.keyseq.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 判断一把密钥是否存在多个值
		if (null != seuList && seuList.size() > 1) {
			String msg = super.getMessage(request, "client.login.stdentpuser.key.many");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		StdEntpUser currentUser = seuList.get(0); // 获得当前用户

		// 判断密钥所对应的企业是否正常
		StdEntpMain sem = new StdEntpMain();

		sem.setEntp_id(currentUser.getEntp_id());// 2012-3-16
													// 配合147行取用户list和176行取企业信息list，上下两种逻辑二选一
		/*
		 * if (null != currentUser.getMap().get("shop_id")) {//
		 * 从chea_fill.user_info中取得 sem.getMap().put("shop_id",
		 * currentUser.getMap().get("shop_id")); } else {//
		 * 从chea_fill.std_entp_user中取得 sem.setEntp_id(currentUser.getEntp_id());
		 * }
		 */

		sem.setOwn_sys(currentUser.getOwn_sys());
		sem.getRow().setCount(2);
		List<StdEntpMain> semList = super.getFacade().getStdEntpMainService().getStdEntpMainList(sem);
		// List<StdEntpMain> semList =
		// super.getFacade().getStdEntpMainService().getStdEntpMainOrEntpShopList(sem);

		if (null == semList || semList.size() == 0) {
			String msg = super.getMessage(request, "client.authenticate.keyseq.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 判断企业数据是否唯一
		if (null != semList && semList.size() > 1) {
			String msg = super.getMessage(request, "client.register.stdentman.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		StdEntpMain currentEntp = semList.get(0);

		// 没有备案
		String not_validate_record = (String) request.getAttribute("not_validate_record");
		if (StringUtils.isBlank(not_validate_record) && !"0".equals(currentEntp.getPar_id().toString())
				&& 1 != currentEntp.getIs_record()) {
			String msg = super.getMessage(request, "client.authenticate.record.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 当前企业所属地区编码p_index，针对安徽省用户出现（安徽省平台）
		request.setAttribute("shop_p_index", currentEntp.getP_index());

		currentUser.setStdEntpMain(currentEntp);
		request.setAttribute("KONKA_BRAND_ID", Constants.KONKA_BRAND_ID);
		return currentUser;
	}

	public void setCustomerListToRequest(HttpServletRequest request, Long shop_id) {
		JxcCustomer jxc_customer = new JxcCustomer();// 取客户名
		jxc_customer.setIs_del(0);
		jxc_customer.setShop_id(shop_id);
		List<JxcCustomer> jxc_customer_list = super.getFacade().getJxcCustomerService()
				.getJxcCustomerList(jxc_customer);
		request.setAttribute("customerList", jxc_customer_list);
	}

	public String queryDate(String gt, String lt) {
		String s = "查询时间：";
		if (StringUtils.isBlank(gt) && StringUtils.isBlank(lt)) {
			s = s.concat("无");
		} else if (StringUtils.isBlank(gt)) {
			s = s.concat("截止" + lt);
		} else if (StringUtils.isBlank(lt)) {
			s = s.concat(gt + "至今");
		} else {
			s = s.concat(gt + "至" + lt);
		}

		return s;
	}

	public void setSupplierListToRequest(HttpServletRequest request, Long shop_id) {
		JxcSupplier jxc_supplier = new JxcSupplier();
		jxc_supplier.setIs_del(0);
		jxc_supplier.setShop_id(shop_id);
		List<JxcSupplier> jxc_supplier_list = super.getFacade().getJxcSupplierService()
				.getJxcSupplierList(jxc_supplier);
		request.setAttribute("supplierList", jxc_supplier_list);
	}

	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-10-13
	 * @desc 根据部门id，取得部门下的仓库List
	 */
	protected List<KonkaJxcStoreInfo> getStoreInfoListByDeptId(Long dept_id) {
		KonkaJxcStoreInfo konkaJxcStoreInfo = new KonkaJxcStoreInfo();
		konkaJxcStoreInfo.setAdd_dept_id(dept_id);
		konkaJxcStoreInfo.setIs_del(0);
		return this.getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfoList(konkaJxcStoreInfo);
	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-10-13
	 * @desc 根据部门id，取得部门下的产品类型List
	 */
	protected List<BasePdClazz> getBasePdClazzListByDeptId(Long dept_id) {
		BasePdClazz basePdClazz = new BasePdClazz();
		basePdClazz.getMap().put("getOwnDeptCls", "ture");
		basePdClazz.getMap().put("dept_id", dept_id);
		basePdClazz.setIs_del(0);
		return this.getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-10-13
	 * @desc 根据部门id和品类id，取得部门下的产品型号List
	 */
	protected List<PePdModel> getPePdModelListByDeptIdAndClsId(Long dept_id, Long cls_id) {
		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("getOwnDeptCls", "ture");
		pePdModel.getMap().put("dept_id", dept_id);
		pePdModel.getMap().put("cls_id", cls_id);
		pePdModel.setIs_del(0);
		return this.getFacade().getPePdModelService().getPePdModelList(pePdModel);
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-10
	 * @desc 根据部门id和品类id、型号id，取得部门下授权的产品型号
	 */
	protected KonkaDeptPdLink getKonkaDeptPdLinkByDeptIdClsIdAndPdId(Long dept_id, Long cls_id, Long pd_id) {
		KonkaDeptPdLink konkaDeptPdLink = new KonkaDeptPdLink();
		konkaDeptPdLink.setDept_id(dept_id);
		konkaDeptPdLink.setCls_id(cls_id);
		konkaDeptPdLink.setPd_id(pd_id);
		return this.getFacade().getKonkaDeptPdLinkService().getKonkaDeptPdLink(konkaDeptPdLink);
	}

	/** 根据选择的产品大类，查询产品品牌供选择 */
	public ActionForward chooseBrand(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String brand_name_like = (String) dynaBean.get("brand_name_like");
		String pd_type = (String) dynaBean.get("pd_type");

		if ("0".equals(pd_type)) {
			BaseBrandInfo entity = new BaseBrandInfo();
			super.copyProperties(entity, dynaBean);
			entity.setIs_del(0);
			entity.getMap().put("brand_name_like", brand_name_like);

			Long recordCount = getFacade().getBaseBrandInfoService().getBaseBrandInfoCount(entity);
			pager.init(recordCount, 45, pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(45);
			List<BaseBrandInfo> list = getFacade().getBaseBrandInfoService().getBaseBrandInfoPaginatedList(entity);
			request.setAttribute("entityList", list);
		} else {
			MvPdTypeJoinBrand entity = new MvPdTypeJoinBrand();
			super.copyProperties(entity, form);
			if (StringUtils.isNotBlank(pd_type)) {
				entity.setPd_type(Long.valueOf(pd_type));
			}
			entity.getMap().put("brand_name_like", brand_name_like);

			Long recordCount = getFacade().getMvPdTypeJoinBrandService().getMvPdTypeJoinBrandCount(entity);
			pager.init(recordCount, 45, pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(45);
			List<MvPdTypeJoinBrand> list = getFacade().getMvPdTypeJoinBrandService().getMvPdTypeJoinBrandPaginatedList(
					entity);
			request.setAttribute("entityList", list);
		}

		dynaBean.set("pd_type", pd_type);
		return new ActionForward("/JxcStockBill/chooseBrand.jsp");
	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-10-17
	 * @desc 根据登录用户的id，取该用户的角色
	 */
	protected PeRoleInfo getPeRoleInfoByUserId(Long userId) {
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(userId);
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);

		PeRoleUser ret = null;
		if (null != peRoleUserList && peRoleUserList.size() > 0) {
			for (PeRoleUser c : peRoleUserList) {
				if (c.getRole_id() < 10000L) {
					ret = c;
					break;
				}
			}
		}
		
		PeRoleInfo peRoleInfo = null;
		if (null != ret) {
			peRoleInfo = new PeRoleInfo();
			peRoleInfo.setRole_id(ret.getRole_id());
			peRoleInfo.setIs_del(0);
			return this.getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
		}
		
		return null;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-10-19
	 * @desc 根据类型id，取产品类型
	 */
	protected BasePdClazz getBasePdClazz(Long pdTypeId) {
		BasePdClazz basePdClass = new BasePdClazz();
		basePdClass.setCls_id(pdTypeId);
		basePdClass.setIs_del(0);
		basePdClass = super.getFacade().getBasePdClazzService().getBasePdClazz(basePdClass);
		return basePdClass;
	}

	/**
	 * @author Wu,Yang
	 * @version 2011-10-31
	 * @desc 根据部门id，取产品型号
	 */
	protected List<PePdModel> getPePdModelListByDeptIds(String dept_ids) {
		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("getOwnDeptPdModel_brand_konka", "true");
		pePdModel.getMap().put("dept_ids", dept_ids);
		List<PePdModel> ppmList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		return ppmList;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-12-31
	 * @desc 根据部门id和系统类型，取产品型号
	 * @desc 392、393行为限制只查询所属部门分配的产品型号
	 */
	protected List<PePdModel> getPePdModelListByDeptIdsAndOwnSys(String dept_ids, Integer own_sys, Long brand_id,
			Long parClsId) {
		PePdModel pePdModel = new PePdModel();
		pePdModel.setBrand_id(brand_id);
		pePdModel.setPar_cls_id(parClsId);
		pePdModel.getMap().put("getOwnDeptPdModel_brand_konka", "true");
		pePdModel.getMap().put("dept_ids", dept_ids);
		if (own_sys == 0) {
			pePdModel.getMap().put("ownSys0", "true");
		} else {
			pePdModel.getMap().put("ownSys1", "true");
		}
		List<PePdModel> ppmList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		return ppmList;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-10-19
	 * @desc 根据品牌id，取产品品牌
	 */
	protected BaseBrandInfo getBaseBrandInfo(Long brandId) {
		BaseBrandInfo brandInfo = new BaseBrandInfo();
		brandInfo.setBrand_id(brandId);
		brandInfo.setIs_del(0);
		brandInfo = super.getFacade().getBaseBrandInfoService().getBaseBrandInfo(brandInfo);
		return brandInfo;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-10-19
	 * @desc 根据型号id，取产品型号
	 */
	protected PePdModel getPePdModel(Long pdId) {
		PePdModel pePdModel = new PePdModel();
		pePdModel.setPd_id(pdId);
		pePdModel.setIs_del(0);
		pePdModel = getFacade().getPePdModelService().getPePdModel(pePdModel);
		return pePdModel;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-10-20
	 * @desc 根据用户查询此部门分配的买卖提网点列表（包含所有下级部门的）：ID、型号部门id
	 */
	/*
	 * protected List<KonkaBranchAssign>
	 * getAssignShopByDeptIdOrUserId(PeProdUser user) { PeRoleInfo role =
	 * getPeRoleInfoByUserId(user.getId()); KonkaBranchAssign konkaBranchAssign
	 * = new KonkaBranchAssign(); konkaBranchAssign.getMap().put("is_m5",
	 * true);// 查找买卖提shop,而非R3 if (role.getRole_id() == 60) {// 业务员
	 * konkaBranchAssign.setUser_id(user.getId()); List<KonkaBranchAssign> list
	 * = getFacade().getKonkaBranchAssignService().getKonkaBranchAssignList(
	 * konkaBranchAssign); return list; } else {
	 * konkaBranchAssign.setDept_id(user.getDept_id()); List<KonkaBranchAssign>
	 * list =
	 * getFacade().getKonkaBranchAssignService().getKonkaBranchAssignList(
	 * konkaBranchAssign); return list; } }
	 */

	/**
	 * @author du,zhiming
	 * @version 2011-10-21
	 * @desc 根据部门Id、仓库Id、产品型号Id获取库存信息
	 */
	protected KonkaJxcStoreState getKonkaJxcStoreStateNumByDeptAndStoreAndPd(Long deptId, Long storeId, Long pdId) {
		KonkaJxcStoreState store_state = new KonkaJxcStoreState();
		store_state.setDept_id(deptId);
		store_state.setStore_id(storeId);
		store_state.setPd_id(pdId);
		store_state.setIs_del(0);
		store_state = getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreState(store_state);
		return store_state;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-10-13
	 * @desc 获取所有未删除产品类型List
	 */
	protected List<BasePdClazz> getBaseAllPdClazzList() {
		BasePdClazz basePdClazz = new BasePdClazz();
		basePdClazz.setIs_del(0);
		return this.getFacade().getBasePdClazzService().getBasePdClazzList(basePdClazz);
	}

	/**
	 * @author du,zhiming
	 * @version 2011-10-28
	 * @desc 根据门店商铺ID(shop_id)获取客户List
	 */
	protected List<JxcCustomer> getCustomerList(Long shop_id) {
		JxcCustomer customer = new JxcCustomer();
		customer.setShop_id(shop_id);
		customer.setIs_del(0);
		return getFacade().getJxcCustomerService().getJxcCustomerList(customer);
	}

	/**
	 * @author Wu,Yang
	 * @version 2011-10-29
	 * @param needGetDeptId
	 *            true 需要取得部门ids，false 不需要部门IDs
	 * @desc 获得商品是否是 代理商：10 ，经销商【直供或者非直供】：20，以及获得对应是部门ID
	 * @return r3_dept_ids 上级部门IDs：result.get("r3_dept_ids")
	 * @return result_code 信息状态 代理商10成功，经销商20成功，0失败：result.get("result_code")
	 */
	public HashMap<String, String> getKonkaDeptIdAndType(Long shop_id, boolean needGetDeptId) {
		HashMap<String, String> result = new HashMap<String, String>();
		List<KonkaR3MmtMatch> konkaR3MmtMatchList = super.getAgentKonkaR3IdByMmtShopId(shop_id);
		String type = "0";
		String r3_dept_ids_string = "-1";
		Long r3_dept_id = -1l;
		if (null != konkaR3MmtMatchList && konkaR3MmtMatchList.size() > 0) {// 判断是否为代理商
																			// 或者
																			// 是经销商【直供】
			List<KonkaR3MmtMatch> konkaR3MmtMatchList_base = new ArrayList<KonkaR3MmtMatch>();

			List<KonkaR3MmtMatch> konkaR3MmtMatchList_dls = new ArrayList<KonkaR3MmtMatch>();
			for (KonkaR3MmtMatch konkaR3MmtMatch1 : konkaR3MmtMatchList) {
				KonkaBranchCategory t = new KonkaBranchCategory();
				t.setCategory_id(10l);
				t.setKonka_r3_id(konkaR3MmtMatch1.getR3_shop_id());
				t = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategory(t);
				if (null != t) {
					konkaR3MmtMatchList_dls.add(konkaR3MmtMatch1);
				}
			}
			if (null != konkaR3MmtMatchList_dls && konkaR3MmtMatchList_dls.size() > 0) {
				type = "10";// 代理商
				konkaR3MmtMatchList_base.addAll(konkaR3MmtMatchList_dls);
			} else {
				type = "20";// 经销商【直供】
				konkaR3MmtMatchList_base.addAll(konkaR3MmtMatchList);
			}

			if (needGetDeptId) {
				String[] r3_dept_ids = new String[konkaR3MmtMatchList_base.size()];
				int i = 0;
				for (KonkaR3MmtMatch krmm : konkaR3MmtMatchList_base) {
					r3_dept_id = super.getDeptIdByAgentAgentKonkaR3Id(krmm.getR3_shop_id());// 获取代理商上级分公司部门Id
					r3_dept_ids[i++] = r3_dept_id.toString();
				}
				r3_dept_ids_string = StringUtils.join(r3_dept_ids, ",");
			}

		} else {// 代理商下级网点，判断是否是经销商【非直供】
			Long r3_id_shop = super.getSuperiorAgentKonkaR3IdByMmtShopId(shop_id);
			if (r3_id_shop.intValue() != -1) {
				type = "20";// 经销商【非直供】
				if (needGetDeptId) {
					r3_dept_id = super.getDeptIdByAgentAgentKonkaR3Id(r3_id_shop);// 获取网点代理商上级分公司部门Id
					r3_dept_ids_string = r3_dept_id.toString();
				}
			}
		}
		if (needGetDeptId) {
			result.put("r3_dept_ids", r3_dept_ids_string);
		}
		result.put("result_code", type);
		return result;
	}

	public HashMap<String, String> getKonkaDeptIdAndType(Long shop_id) {
		return this.getKonkaDeptIdAndType(shop_id, false);

	}

	/**
	 * @author Wu,Yang
	 * @version 2011-10-29
	 * @desc 判断登录的商铺是否已经匹配了康佳R3网点, ture 已经匹配，false 未匹配
	 */
	public boolean judgeShopIdIsMatchKonkaR3(Long shop_id) {
		boolean isMatch = false;
		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setMmt_shop_id(shop_id);
		Long count_krmm = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatchCount(konkaR3MmtMatch);
		if (count_krmm.intValue() > 0) {// R3用户匹配->用户匹配 节点，判断是否匹配
			isMatch = true;
			// 这种情况有点特殊：
			// 1、如果用户分配没有数据，则是经销商【直供】
			// 2、如果用户分配有数据，则是代理商
		}
		if (!isMatch) {
			KonkaBranchCategory konkaBranchCategory = new KonkaBranchCategory();
			konkaBranchCategory.setCategory_id(20l);
			konkaBranchCategory.setMmt_shop_id(shop_id);
			Long count_kbc = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategoryCount(
					konkaBranchCategory);
			if (count_kbc.intValue() > 0) {// 网点开拓->待开拓网点 节点，判断是否是经销商【非直供】
				isMatch = true;
			}
		}
		return isMatch;

	}

	/**
	 * @author du,zhiming
	 * @version 2011-10-13
	 * @desc 根据部门id获取部门信息
	 */
	public KonkaDept getKonkaDeptById(Long deptId) {
		KonkaDept peDept = new KonkaDept();
		peDept.setDept_id(deptId);
		peDept = getFacade().getKonkaDeptService().getKonkaDept(peDept);
		return peDept;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-8
	 * @desc 根据仓库id获取仓库信息
	 */
	public KonkaJxcStoreInfo getKonkaStockById(Long storeId) {
		KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
		store.setId(storeId);
		store = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(store);
		return store;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-17
	 * @desc 根据仓库名称和部门id获取仓库信息
	 */
	public KonkaJxcStoreInfo getKonkaStockByDeptIdAndStoreName(Long deptId, String name) {
		KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
		store.setStore_name(name);
		store.setAdd_dept_id(deptId);
		store = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(store);
		return store;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-10-13
	 * @desc 根据id获取进销存产品信息
	 */
	public JxcPd getJxcPdById(Long id) {
		JxcPd jxcPd = new JxcPd();
		jxcPd.setId(id);
		jxcPd.setIs_del(0);
		jxcPd = getFacade().getJxcPdService().getJxcPd(jxcPd);
		return jxcPd;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-11-16
	 * @desc 根据部门id获取所属网点信息列表
	 */

	public List<KonkaR3Shop> getShopListByDeptId(Long deptId) {
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.getMap().put("konka_jxc_dept_id", deptId);
		konkaR3Shop.setIs_del(0l);
		List<KonkaR3Shop> konkaR3ShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopMatchAndAssignList(
				konkaR3Shop);
		return konkaR3ShopList;

	}

	/**
	 * @author du,zhiming
	 * @version 2011-11-16
	 * @desc 根据部门id获取产品型号信息列表
	 */
	protected List<PePdModel> getPePdModelListByDeptId(Long dept_id) {
		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("getOwnDept", "ture");
		pePdModel.getMap().put("dept_id", dept_id);
		pePdModel.setIs_del(0);
		return this.getFacade().getPePdModelService().getPePdModelList(pePdModel);
	}

	/**
	 * @author du,zhiming
	 * @version 2011-11-16
	 * @desc 根据管理端产品型号id(pd_id)和网点id(shop_id)获取网点端对应产品型号信息列表
	 */
	protected List<JxcPd> getJxcPdByPdAndShopId(Long pd_id, Long shop_id) {
		JxcPd jxcPd = new JxcPd();
		jxcPd.setShop_id(shop_id);
		jxcPd.setOut_sys_id(pd_id);
		jxcPd.setIs_del(0);
		List<JxcPd> jxcPd_list = super.getFacade().getJxcPdService().getJxcPdList(jxcPd);
		return jxcPd_list;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-11-16
	 * @desc 根据管理端R3网点id(id)获取网点端对应MMT网点匹配信息
	 */

	public KonkaR3MmtMatch getKonkaR3MmtMatchByR3Id(Long r3_id) {
		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setR3_shop_id(r3_id);
		konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatchBySelf(konkaR3MmtMatch);
		return konkaR3MmtMatch;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-11-16
	 * @desc 根据管理端R3网点id(id)获取网点信息
	 */
	public KonkaR3Shop getKonkaR3ShopById(Long id) {
		KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
		konkaR3Shop.setId(id);
		konkaR3Shop.setIs_del(0l);
		konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);// 取R3网点名称
																						// 回显列表
		return konkaR3Shop;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-11-18
	 */
	protected void renderExcelWithEncoding(HttpServletRequest request, HttpServletResponse response, String encoding)
			throws IOException {
		String hiddenHtml = StringUtils.lowerCase(request.getParameter("hiddenHtml"));

		hiddenHtml = StringUtils.replace(hiddenHtml, "border=0", "border=1");
		hiddenHtml = StringUtils.replace(hiddenHtml, "border=\"0\"", "border=\"1\"");

		String fname = EncryptUtilsV2.encodingFileName(request.getParameter("hiddenName"));

		response.setCharacterEncoding(encoding);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname);

		PrintWriter out = response.getWriter();
		out.println(hiddenHtml);

		out.flush();
		out.close();
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-28 以时间方式生成流水号
	 */
	protected String generateTradeIndex() {
		return "LSH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-28
	 * @desc 回显市区县乡村单个的信息
	 */
	public void setprovinceAndcityAndcountryToFrom(DynaBean dynaBean, Long p_index) {
		if (null != p_index) {
			BaseProvinceList baseProvince = new BaseProvinceList();
			baseProvince.getMap().put("shop_p_index", p_index);
			List<BaseProvinceList> baseProvinceList = super.getFacade().getBaseProvinceListService()
					.getBaseProvinceListAllParPindexByPindex(baseProvince);
			for (BaseProvinceList bp : baseProvinceList) {
				if (bp.getP_level().intValue() == 1) {
					dynaBean.set("province", bp.getP_index());
				}
				if (bp.getP_level().intValue() == 2) {
					dynaBean.set("city", bp.getP_index());
				}
				if (bp.getP_level().intValue() == 3) {
					dynaBean.set("country", bp.getP_index());
				}
			}
		}
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-28
	 * @desc 根据区域编码取全称
	 */
	public String getPIndexFullName(Long p_index) {
		String fullName = "";
		if (null != p_index) {
			BaseProvinceList baseProvince = new BaseProvinceList();
			baseProvince.getMap().put("shop_p_index", p_index);
			List<BaseProvinceList> baseProvinceList = super.getFacade().getBaseProvinceListService()
					.getBaseProvinceListAllParPindexByPindex(baseProvince);
			for (BaseProvinceList bp : baseProvinceList) {
				if (bp.getP_level().intValue() == 1) {
					fullName = bp.getP_name();
				}
				if (bp.getP_level().intValue() == 2) {
					fullName = fullName + bp.getP_name();
				}
				if (bp.getP_level().intValue() == 3) {
					fullName = fullName + bp.getP_name();
				}
			}
		}
		return fullName;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-30
	 * @desc 年份列表
	 */
	public List<Integer> getYearList() {
		List<Integer> yearList = new ArrayList<Integer>();
		Integer startYear = 2011;
		Integer entYear = 2031;
		for (Integer i = startYear; i < entYear; i++) {
			yearList.add(i);
		}
		return yearList;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-30
	 * @desc 月份列表
	 */
	public List<Integer> getMonthList() {
		List<Integer> monthList = new ArrayList<Integer>();
		Integer startmonth = 1;
		Integer entmonth = 12;
		for (Integer i = startmonth; i <= entmonth; i++) {
			monthList.add(i);
		}
		return monthList;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-12-01
	 * @desc 根据角色id，取该角色
	 */
	protected PeRoleInfo getPeRoleInfoById(Long roleId) {
		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.setRole_id(roleId);
		peRoleInfo.setIs_del(0);
		return this.getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
	}

	/**
	 * @author du,zhiming
	 * @version 2011-12-01
	 * @desc 根据用户部门id找 所属分公司id
	 */
	protected KonkaDept getKonkaDeptByUserDeptId(Long uesrDeptId) {
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.getMap().put("dept_id_for_fgs", uesrDeptId);
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		return konkaDept;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-12-06
	 * @desc 根据角色范围取 角色列表 供流程选择使用
	 */
	protected List<PeRoleInfo> getPeRoleInfoListForProcess() {
		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.getMap().put("is_pe_prod_user", "true");
//		peRoleInfo.getMap().put("role_id_audit_lt", Constants.ROLE_ID_FGS);// 分公司角色管理员Id
		peRoleInfo.getMap().put("role_id_audit_lt", 10);// 分公司角色管理员Id
		peRoleInfo.getMap().put("role_id_audit_gt", 200);// 业务员角色Id
		peRoleInfo.setIs_del(0);
		// 取角色列表
		List<PeRoleInfo> peRoleInfo_list = getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);
		return peRoleInfo_list;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-12-08
	 * @desc 根据订单ID，取订单审核信息列表
	 */
	protected List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditList(Long orderId) {
		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		konkaOrderInfoAudit.setLink_id(orderId);
		List<KonkaOrderInfoAudit> konkaOrderInfoAuditList = getFacade().getKonkaOrderInfoAuditService()
				.getKonkaOrderInfoAuditList(konkaOrderInfoAudit);
		return konkaOrderInfoAuditList;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-12-08
	 * @desc 根据订单ID，取订单审核信息包含角色信息列表
	 */
	protected List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditWithRoleInfoList(Long orderId) {
		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		konkaOrderInfoAudit.setLink_id(orderId);
		List<KonkaOrderInfoAudit> konkaOrderInfoAuditWithRoleInfoList = getFacade().getKonkaOrderInfoAuditService()
				.getKonkaOrderInfoAuditWithRoleInfoList(konkaOrderInfoAudit);
		return konkaOrderInfoAuditWithRoleInfoList;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-12-09
	 * @desc 根据订单审核类型取得对应流程的最高审核级别 分公司dept_id， 订单order_id
	 */

	protected Long getKonkaOrderAuditProcessNodeMaxLevel(Long dept_id, Long order_id) {
		KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
		node.getMap().put("dept_id", dept_id);
		node.getMap().put("order_id", order_id);
		return getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeMaxLevel(node);
	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-12-26
	 * @desc 根据订单取对应的订单审核记录里面，最高的审核等级
	 */

	protected Long getKonkaOrderInfoAuditWithMaxAuditLevel(Long order_id) {
		KonkaOrderInfoAudit konkaOrderInfoAudit = new KonkaOrderInfoAudit();
		konkaOrderInfoAudit.getMap().put("link_id", order_id);
		return getFacade().getKonkaOrderInfoAuditService().getKonkaOrderInfoAuditWithMaxAuditLevel(konkaOrderInfoAudit);
	}

	/**
	 * @author du,zhiming
	 * @version 2011-12-13
	 * @desc 根据部门(分公司)ID，角色Id取流程角色信息列表
	 */
	protected List<KonkaOrderAuditProcessNode> getKonkaOrderAuditProcessNodeList(Long deptId, Long roleId) {
		KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
		node.setAdd_dept_id(deptId);
		node.setRole_id(roleId);
		node.setIs_del(0);
		return getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeList(node);

	}

	/**
	 * @author Zhang,Xufeng
	 * @version 2011-12-21
	 * @desc 根据订单ID，取对应的分公司信息
	 */
	protected KonkaDept getKonkaFGSByOrderId(Long orderId) {
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.getMap().put("order_id_for_fgs", orderId);
		return getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

	}

	/**
	 * @author du,zhiming
	 * @version 2011-12-30
	 * @desc 根据shopID，取对应的网点
	 */
	protected MmtEntpShop getKonkaShopById(Long shopId) {
		MmtEntpShop shop = new MmtEntpShop();
		shop.setShop_id(shopId);
		return getFacade().getMmtEntpShopService().getMmtEntpShop(shop);

	}

	/**
	 * @author du,zhiming
	 * @version 2012/2/2
	 * @desc 根据分公司部门Id，取对应的特殊流程
	 */
	protected KonkaOrderAuditProcess getSpecialProcessByFgsDeptId(Long deptId) {
		KonkaOrderAuditProcess kkoap = new KonkaOrderAuditProcess();
		kkoap.setAdd_dept_id(deptId);
		kkoap.setIs_del(0);
		kkoap.setProcess_type(2);
		return getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(kkoap);
	}

	/**
	 * @author du,zhiming
	 * @version 2012/2/8
	 * @desc 根据用户ID和订单Id 取审核记录
	 */
	protected KonkaOrderInfoAudit getKonkaOrderInfoAuditByOrderIdAndUserId(Long userId, Long orderId) {
		KonkaOrderInfoAudit koa = new KonkaOrderInfoAudit();
		koa.setLink_id(orderId);
		koa.setAudit_user_id(userId);
		koa = getFacade().getKonkaOrderInfoAuditService().getKonkaOrderInfoAudit(koa);
		return koa;
	}

	/**
	 * @author du,zhiming
	 * @version 2012/2/8
	 * @desc 根据分公司部门Id、流程类型，取对应的流程
	 */
	protected KonkaOrderAuditProcess getProcessByFgsDeptIdAndType(Long deptId, Integer processType) {
		KonkaOrderAuditProcess kkoap = new KonkaOrderAuditProcess();
		kkoap.setAdd_dept_id(deptId);
		kkoap.setIs_del(0);
		kkoap.setProcess_type(processType);
		return getFacade().getKonkaOrderAuditProcessService().getKonkaOrderAuditProcess(kkoap);
	}

	/**
	 * @author du,zhiming
	 * @version 2012/2/8
	 * @desc 根据流程Id、角色，取对应的结点信息
	 */
	protected KonkaOrderAuditProcessNode getProcessNodeByProcessIdAndRoleId(Long processId, Long roleId) {
		KonkaOrderAuditProcessNode kkoapn = new KonkaOrderAuditProcessNode();
		kkoapn.setAudit_proces_id(processId);
		kkoapn.setIs_del(0);
		kkoapn.setRole_id(roleId);
		return getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(kkoapn);
	}

	/**
	 * @author du,zhiming
	 * @version 2012/2/16
	 * @desc 根据订单Id、是否删除、产品Id，取对应的审核产品信息
	 */
	protected List<KonkaOrderInfoDetailsAudit> getKonkaOrderInfoDetailsAuditList(Long order_id, String is_del,
			String pd_id) {
		KonkaOrderInfoDetailsAudit kkoada = new KonkaOrderInfoDetailsAudit();
		kkoada.setOrder_id(order_id);
		if (StringUtils.isNotBlank(is_del)) {
			kkoada.setIs_del(new Integer(is_del));
		}
		if (GenericValidator.isLong(pd_id)) {
			kkoada.setPd_id(new Long(pd_id));
		}
		List<KonkaOrderInfoDetailsAudit> konkaOrderInfoDetailsAuditList = getFacade()
				.getKonkaOrderInfoDetailsAuditService().getKonkaOrderInfoDetailsAuditList(kkoada);
		return konkaOrderInfoDetailsAuditList;
	}

	/**
	 * @author Li,Ka
	 * @version 2012/2/16
	 * @desc 根据shop_id查询匹配的R3网点（代理商、直供经销商），或者开拓为非直供经销商对应的代理商（代理商一定是R3网点）
	 *       谁管理该网点，就可以从客户分配表（branch_assign）中找管理人
	 */
	protected KonkaR3Shop getKonkaR3ShopByShopId(Long shopId) {
		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setMmt_shop_id(shopId);
		konkaR3MmtMatch = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatch(konkaR3MmtMatch);
		if (null != konkaR3MmtMatch) {// 直接匹配了（客户分配中指定为 代理商 or 不是代理商）
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			konkaR3Shop.setId(konkaR3MmtMatch.getR3_shop_id());
			konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
			if (null != konkaR3Shop) {
				return konkaR3Shop;
			}

		} else {// 没有匹配，检查有没有被开拓
			KonkaBranchCategory konkaBranchCategory = new KonkaBranchCategory();
			konkaBranchCategory.setCategory_id(20l);
			konkaBranchCategory.setMmt_shop_id(shopId);
			konkaBranchCategory = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategory(
					konkaBranchCategory);
			if (null != konkaBranchCategory) {// 开拓为经销商（非直供）
				KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
				konkaR3Shop.setId(konkaBranchCategory.getKonka_r3_id());
				konkaR3Shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkaR3Shop);
				if (null != konkaR3Shop) {
					return konkaR3Shop;
				}
			}
		}
		return null;
	}

	/**
	 * @author Li,Ka
	 * @version 2012/2/16
	 * @desc 根据KONKA_R3_ID查找客户分配
	 */
	protected BranchAssign getBranchAssignByKonkaR3Id(Long konkaR3Id) {
		BranchAssign t = new BranchAssign();
		t.setKonka_r3_id(konkaR3Id);
		t = getFacade().getBranchAssignService().getBranchAssign(t);
		return t;
	}

	/**
	 * @author Li,Ka
	 * @version 2012/2/16
	 * @desc 根据登录用户的id，取该用户
	 */
	protected PeProdUser getPeProdUserByUserId(Long userId) {
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setId(userId);
		peProdUser = getFacade().getPeProdUserService().getPeProdUser(peProdUser);
		return peProdUser;
	}

	/**
	 * @param 20120302
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param konka_r3_id
	 *            为空时调用当前登录用户的角色相关联的数据，不为空时按指定的r3_id调用数据回显
	 * @return
	 */
	protected List<KonkaDept> getDeptInfoList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Long konka_r3_id) {
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
//		peRoleUser.setUser_id(userInfo.getId());
//		peRoleUser = this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);

		if (konka_r3_id == null) { // konka_r3_id 为空时调用当前登录用户的角色相关联的数据
			if (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() <= 39) {// 分公司
				dynaBean.set("fgs_dept_id", userInfo.getDept_id().toString());
			}
			if (peRoleUser.getRole_id() >= 40 && peRoleUser.getRole_id() <= 49) {// 经营部
				KonkaDept dept = new KonkaDept();
				dept.setDept_id(userInfo.getDept_id());
				dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
				dynaBean.set("fgs_dept_id", dept.getPar_id().toString());
				dynaBean.set("jyb_dept_id", userInfo.getDept_id().toString());
			}
			if (peRoleUser.getRole_id() >= 50 && peRoleUser.getRole_id() <= 59) {// 办事处
				KonkaDept dept = new KonkaDept();
				dept.setDept_id(userInfo.getDept_id());
				dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
				dynaBean.set("fgs_dept_id", this.getSuperiorForDeptType(userInfo.getDept_id(), 3).getDept_id()
						.toString());
				dynaBean.set("jyb_dept_id", dept.getPar_id().toString());
				dynaBean.set("bsc_dept_id", userInfo.getDept_id().toString());
			}
			if (peRoleUser.getRole_id() == 60) {// 业务员
				KonkaDept dept = new KonkaDept();
				dept.setDept_id(userInfo.getDept_id());
				dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

				if (dept.getDept_type() == 3) {
					dynaBean.set("fgs_dept_id", dept.getDept_id().toString());
					dynaBean.set("ywy_user_id", userInfo.getId().toString());
				}
				if (dept.getDept_type() == 4) {
					dynaBean.set("fgs_dept_id", this.getSuperiorForDeptType(userInfo.getDept_id(), 3).getDept_id()
							.toString());
					dynaBean.set("jyb_dept_id", dept.getDept_id().toString());
					dynaBean.set("ywy_user_id", userInfo.getId().toString());
				}
				if (dept.getDept_type() == 5) {
					dynaBean.set("fgs_dept_id", this.getSuperiorForDeptType(userInfo.getDept_id(), 3).getDept_id()
							.toString());
					dynaBean.set("jyb_dept_id", dept.getPar_id().toString());
					dynaBean.set("bsc_dept_id", userInfo.getDept_id().toString());
					dynaBean.set("ywy_user_id", userInfo.getId().toString());
				}
			}
		} else {// konka_r3_id 不为空时按指定的r3_id调用数据回显
			BranchAssign kba = new BranchAssign();
			kba.setKonka_r3_id(konka_r3_id);
			List<BranchAssign> kbaList = getFacade().getBranchAssignService().getBranchAssignList(kba);
			if (kbaList.size() > 0) {
				for (BranchAssign BranchAssign : kbaList) {
					if (BranchAssign.getFgs_id() != null) {
						dynaBean.set("fgs_dept_id", BranchAssign.getFgs_id().toString());
					}
					if (BranchAssign.getJyb_id() != null) {
						dynaBean.set("jyb_dept_id", BranchAssign.getJyb_id().toString());
					}
					if (BranchAssign.getBsc_id() != null) {
						dynaBean.set("bsc_dept_id", BranchAssign.getBsc_id().toString());
					}
					if (BranchAssign.getUser_id() != null) {
						dynaBean.set("ywy_user_id", BranchAssign.getUser_id().toString());
					}
				}
			}

		}

		KonkaDept entity = new KonkaDept();
		List<KonkaDept> deptInfoList = new ArrayList<KonkaDept>();
		if (peRoleUser.getRole_id() == 10) {// 管理员
			entity.setDept_type(3);
			entity.setPar_id(0L);
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (peRoleUser.getRole_id() >= 20 && peRoleUser.getRole_id() <= 29) {// 事业部
			entity.setPar_id(userInfo.getDept_id());
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() <= 39) {// 分公司
			entity.setDept_id(userInfo.getDept_id());
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (peRoleUser.getRole_id() >= 40 && peRoleUser.getRole_id() <= 60) {// 经营部
																				// 或者办事处
																				// 都通过父子查询级dept_type
																				// 查出所属分公司
			entity.setDept_id(this.getSuperiorForDeptType(userInfo.getDept_id(), 3).getDept_id());
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		// if(peRoleUser.getRole_id() ==60){
		//
		// }
		return deptInfoList;
	}
}
