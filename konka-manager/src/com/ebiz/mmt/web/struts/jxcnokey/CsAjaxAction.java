package com.ebiz.mmt.web.struts.jxcnokey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaJxcStoreState;
import com.ebiz.mmt.domain.KonkaOrderAuditProcess;
import com.ebiz.mmt.domain.KonkaPePdModelLowestPrice;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wu,Yang
 * @version 2011-10-10
 */

@Deprecated
public class CsAjaxAction extends BaseJxcAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.getBaseProvinceList(mapping, form, request, response);
	}

	/**
	 * Ajax asynchronous request to get BaseProvince List
	 * 
	 * @return json: [[key, value],[key, value]..]
	 */
	public ActionForward getBaseProvinceList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_index = (String) dynaBean.get("p_index");

		if (!GenericValidator.isLong(par_index)) {
			return null;
		}

		BaseProvinceList baseProvince = new BaseProvinceList();
		baseProvince.setPar_index(new Long(par_index));
		baseProvince.setDel_mark(new Short("0"));

		List<BaseProvinceList> baseProvinceList = super.getFacade().getBaseProvinceListService()
				.getBaseProvinceListList(baseProvince);

		List<String> dataList = new ArrayList<String>();
		for (BaseProvinceList entity : baseProvinceList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.getP_name(), "\",\"",
					String.valueOf(entity.getP_index()), "\"]" }));
		}
		String ps = StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" });
		logger.info("ps:{}", ps);
		super.renderJson(response, ps);
		return null;
	}

	/**
	 * Ajax asynchronous request to get BaseProvinceFour List
	 * 
	 * @return json: [[key, value],[key, value]..]
	 */
	public ActionForward getBaseProvinceFourList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_index = (String) dynaBean.get("p_index");
		String p_index_join = (String) dynaBean.get("p_index_join");

		if (!GenericValidator.isLong(par_index)) {
			return null;
		}

		BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
		baseProvinceFour.setPar_index(new Long(par_index));
		baseProvinceFour.setDel_mark(0);

		if (StringUtils.isNotBlank(p_index_join)) {
			baseProvinceFour.getMap().put("p_index_join", p_index_join);
		}

		List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourList(baseProvinceFour);

		List<String> dataList = new ArrayList<String>();
		for (BaseProvinceListFour entity : baseProvinceFourList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.getP_name(), "\",\"",
					String.valueOf(entity.getP_index()), "\"]" }));
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}

	/**
	 * Ajax asynchronous request to get BasePdClass List
	 * 
	 * @return json: [[key, value],[key, value]..]
	 */
	public ActionForward getBasePdClassList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_id = (String) dynaBean.get("cls_id");

		if (!GenericValidator.isLong(par_id)) {
			return null;
		}

		BasePdClass basePdClass = new BasePdClass();
		basePdClass.setPar_id(new Long(par_id));
		basePdClass.setIs_del(0);

		List<BasePdClass> basePdClassList = super.getFacade().getBasePdClassService().getBasePdClassList(basePdClass);

		List<String> dataList = new ArrayList<String>();
		for (BasePdClass entity : basePdClassList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.getCls_name(), "\",\"",
					String.valueOf(entity.getCls_id()), "\"]" }));
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-10-13
	 * @desc 动态取得部门下的仓库信息
	 */
	public ActionForward getStoreInfoList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("parentElementValue");

		if (StringUtils.isEmpty(dept_id)) {
			return null;
		}
		KonkaJxcStoreInfo konkaJxcStoreInfo = new KonkaJxcStoreInfo();
		konkaJxcStoreInfo.setAdd_dept_id(new Long(dept_id));
		List<KonkaJxcStoreInfo> konkaJxcStoreInfoList = getFacade().getKonkaJxcStoreInfoService()
				.getKonkaJxcStoreInfoList(konkaJxcStoreInfo);

		StringBuffer sb = new StringBuffer("[");
		if (konkaJxcStoreInfoList != null && konkaJxcStoreInfoList.size() > 0) {
			for (KonkaJxcStoreInfo entity : konkaJxcStoreInfoList) {
				sb.append("{\"date_id\":\"" + entity.getId() + "\",");
				sb.append("\"date_desc\":\"" + entity.getStore_name() + "\"},");
			}
			// sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("{\"author\":\"wy\"}]");

		logger.info("=======sb:{}", sb.toString());

		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-10-13
	 * @desc 根据部门id，动态取得部门下仓库
	 */
	public ActionForward getStoreInfoListByDeptId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DynaBean dynaBean = (DynaBean) form;
		// String dept_id = (String) dynaBean.get("parentElementValue");//====

		// 该登录用户所属部门
		PeProdUser ui = super.getSessionUserInfo(request);
		Long dept_id = ui.getDept_id();

		List<KonkaJxcStoreInfo> konkaJxcStoreInfoList = super.getStoreInfoListByDeptId(dept_id);
		StringBuffer sb = new StringBuffer("[");
		if (konkaJxcStoreInfoList != null && konkaJxcStoreInfoList.size() > 0) {
			for (KonkaJxcStoreInfo entity : konkaJxcStoreInfoList) {
				sb.append("{\"date_id\":\"" + entity.getId() + "\",");
				sb.append("\"date_desc\":\"" + entity.getStore_name() + "\"},");
			}
		}
		sb.append("{\"author\":\"wy\"}]");
		logger.info("=======sb:{}", sb.toString());

		super.renderJson(response, sb.toString());
		return null;

	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-10-13
	 * @desc 根据部门id，动态取得部门下的产品类型
	 */
	public ActionForward getBasePdClazzListByDeptId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 该登录用户所属部门
		PeProdUser ui = super.getSessionUserInfo(request);
		Long dept_id = ui.getDept_id();

		PeRoleInfo role = super.getPeRoleInfoByUserId(ui.getId());
		List<BasePdClazz> basePdClazzlList = new ArrayList<BasePdClazz>();
		if (role.getRole_id().intValue() >= Constants.ROLE_ID_SYB
				&& role.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// 事业部
			basePdClazzlList = super.getBaseAllPdClazzList();
		} else {
			// 产品类型（选择条件中授权的大类）
			BasePdClazz basePdClass = new BasePdClazz();
			basePdClass.setIs_del(0);
			basePdClass.getMap().put("getOwnDeptCls", "ture");
			basePdClass.getMap().put("dept_id", dept_id);
			basePdClazzlList = super.getFacade().getBasePdClazzService().getBasePdClazzList(basePdClass);
		}

		StringBuffer sb = new StringBuffer("[");
		if (basePdClazzlList != null && basePdClazzlList.size() > 0) {
			for (BasePdClazz entity : basePdClazzlList) {
				sb.append("{\"date_id\":\"" + entity.getCls_id() + "\",");
				sb.append("\"date_desc\":\"" + entity.getTree_name() + "\"},");
			}
		}
		sb.append("{\"author\":\"wy\"}]");
		logger.info("=======sb:{}", sb.toString());

		super.renderJson(response, sb.toString());
		return null;

	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-10-13
	 * @desc 根据仓库id 部门id 品类id，动态取得部门下的产品型号 和当前库存
	 */
	public ActionForward getPePdModelListBySidAndDidAndCid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("parentElementValue1");
		String cls_id = (String) dynaBean.get("parentElementValue2");

		if (StringUtils.isEmpty(store_id) || StringUtils.isEmpty(cls_id)) {
			return null;
		}
		PeProdUser ui = super.getSessionUserInfo(request);
		Long dept_id = ui.getDept_id();

		List<PePdModel> pePdModelList = new ArrayList<PePdModel>();
		PeRoleInfo peRoleInfo = super.getPeRoleInfoByUserId(ui.getId());
		if (peRoleInfo.getRole_id().intValue() >= Constants.ROLE_ID_SYB
				&& peRoleInfo.getRole_id().intValue() < Constants.ROLE_ID_FGS) {// 事业部
			PePdModel pePdModel = new PePdModel();
			pePdModel.setCls_id(Long.valueOf(cls_id));
			pePdModel.setIs_del(0);
			pePdModelList = getFacade().getPePdModelService().getPePdModelList(pePdModel);
		} else {
			pePdModelList = super.getPePdModelListByDeptIdAndClsId(dept_id, new Long(cls_id));
		}
		StringBuffer sb = new StringBuffer("[");
		if (pePdModelList != null && pePdModelList.size() > 0) {
			for (PePdModel entity : pePdModelList) {
				Long store_pd_num = 0L;
				BigDecimal price = new BigDecimal("0.00");
				if (StringUtils.isNotBlank(store_id)) {
					KonkaJxcStoreState konkaJxcStoreState = new KonkaJxcStoreState();
					konkaJxcStoreState.setDept_id(dept_id);
					konkaJxcStoreState.setStore_id(new Long(store_id));
					konkaJxcStoreState.setPd_type_id(new Long(cls_id));
					konkaJxcStoreState.setPd_id(entity.getPd_id());
					konkaJxcStoreState = getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreState(
							konkaJxcStoreState);
					if (null != konkaJxcStoreState) {
						store_pd_num = konkaJxcStoreState.getPd_num();
						price = konkaJxcStoreState.getPrice_ref();// 进货参考价
					}
				}

				// 取库存数量
				String values = entity.getPd_id() + "@#" + store_pd_num + "@#" + price;
				sb.append("{\"date_id\":\"" + values + "\",");
				sb.append("\"date_desc\":\"" + entity.getMd_name() + "\"},");
			}
		}
		sb.append("{\"author\":\"wy\"}]");
		logger.info("=======sb:{}", sb.toString());

		super.renderJson(response, sb.toString());
		return null;

	}

	/**
	 * @author Du,ZhiMing
	 * @version 2011-10-18
	 * @desc 根据大类id 部门id ，动态取得部门下的所属的产品型号（ 分公司使用）
	 */

	public ActionForward getOwnPdModleList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String pd_big_type = (String) dynaBean.get("parentElementValue");

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("getOwnDeptCls", "ture");
		pePdModel.getMap().put("dept_id", userInfo.getDept_id());
		pePdModel.getMap().put("cls_id", pd_big_type);
		List<PePdModel> ppmList = getFacade().getPePdModelService().getPePdModelList(pePdModel);

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (PePdModel temp : ppmList) {
			sb.append("{\"date_id\":\"").append(temp.getPd_id()).append("\",");
			sb.append("\"date_desc\":\"").append(temp.getMd_name()).append("\"},");
		}
		sb.append("{\"x\":\"x\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * @author Du,ZhiMing
	 * @version 2011-10-21
	 * @desc 根据大类动态取得该大类的产品型号 （事业部使用）
	 */

	public ActionForward getOwnPdModleListToSyb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String cls_id = (String) dynaBean.get("parentElementValue");
		PePdModel pePdModel = new PePdModel();
		logger.info("cls_id---{}", cls_id);
		pePdModel.setCls_id(Long.valueOf(cls_id));
		pePdModel.setIs_del(0);
		List<PePdModel> ppmList = getFacade().getPePdModelService().getPePdModelList(pePdModel);

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (PePdModel temp : ppmList) {
			sb.append("{\"date_id\":\"").append(temp.getPd_id()).append("\",");
			sb.append("\"date_desc\":\"").append(temp.getMd_name()).append("\"},");
		}
		sb.append("{\"x\":\"x\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * @author Du,ZhiMing
	 * @version 2011-10-18
	 * @desc 根据倉庫id， 部门id ，產品型號Id,是否为残次品动态取得部门下的所属产品型号的庫存數
	 */

	public ActionForward getStoreState(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		String pd_id = (String) dynaBean.get("pd_id");
		String store_id = (String) dynaBean.get("store_id");
		String th_type = (String) dynaBean.get("th_type");
		KonkaJxcStoreState kjss = new KonkaJxcStoreState();
		kjss.setBrand_id(Constants.KONKA_BRAND_ID);
		kjss.setPd_id(Long.valueOf(pd_id));
		kjss.setDept_id(userInfo.getDept_id());
		kjss.setStore_id(Long.valueOf(store_id));
		kjss = super.getFacade().getKonkaJxcStoreStateService().getKonkaJxcStoreState(kjss);
		Long count = 0l;

		if (null != kjss && StringUtils.isNotBlank(th_type)) {
			if (th_type.equals("0")) {// 残次品数量 空值赋0
				count = kjss.getPd_bad_num() == null ? 0l : kjss.getPd_bad_num();
			}
			if (th_type.equals("1")) {// 正品数量 空值赋0
				count = kjss.getPd_num() == null ? 0l : kjss.getPd_num();
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("{\"pd_num\":\"").append(count).append("\"},");
		sb.append("{\"x\":\"x\"}]");
		logger.info(sb.toString());
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * @author Du,ZhiMing
	 * @version 2011-12-01
	 * @desc 根据部门和流程类型取未删除的流程列表
	 */
	public ActionForward getNotDelAuditProcessListByDeptAndType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");// 部门Id
		String process_type = (String) dynaBean.get("process_type");// 流程类型
		logger.info("dept_id---{}", dept_id);
		logger.info("type_process---{}", process_type);
		KonkaOrderAuditProcess konkaOrderAuditProcess = new KonkaOrderAuditProcess();
		konkaOrderAuditProcess.setAdd_dept_id(new Long(dept_id));
		konkaOrderAuditProcess.setProcess_type(new Integer(process_type));
		konkaOrderAuditProcess.setIs_del(0);

		List<KonkaOrderAuditProcess> konkaOrderAuditProcessList = getFacade().getKonkaOrderAuditProcessService()
				.getKonkaOrderAuditProcessList(konkaOrderAuditProcess);

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (konkaOrderAuditProcessList != null && konkaOrderAuditProcessList.size() > 0) {
			sb.append("{\"is_repeat\":\"").append("1").append("\"},");
		}

		sb.append("{\"audit_process\":\"audit_process\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * @author Zhang,Xufeng
	 * @version 2011-12-02
	 * @desc 根据大类、品牌、型号、部门确定最低限价
	 */
	public ActionForward getLowestPriceByTypeBrandPdAndDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		PeProdUser user = super.getSessionUserInfo(request);
		String pd_type_id = (String) dynaBean.get("pd_type_id");// 大类
		String brand_id = (String) dynaBean.get("brand_id");// 品牌
		String pd_id = (String) dynaBean.get("pd_id");// 型号
		KonkaPePdModelLowestPrice konkaPePdModelLowestPrice = new KonkaPePdModelLowestPrice();
		konkaPePdModelLowestPrice.setIs_del(0);
		konkaPePdModelLowestPrice.setPd_type_id(new Long(pd_type_id));
		konkaPePdModelLowestPrice.setBrand_id(new Long(brand_id));
		konkaPePdModelLowestPrice.setPd_id(new Long(pd_id));
		// 根据用户部门id找 所属分公司id
		KonkaDept kd = new KonkaDept();
		kd.getMap().put("dept_id_for_fgs", user.getDept_id());
		kd = getFacade().getKonkaDeptService().getKonkaDept(kd);
		konkaPePdModelLowestPrice.setAdd_dept_id(kd.getDept_id());
		Calendar c = Calendar.getInstance();
		konkaPePdModelLowestPrice.setSet_year(c.get(Calendar.YEAR));
		konkaPePdModelLowestPrice.setSet_month(c.get(Calendar.MONTH) + 1);
		konkaPePdModelLowestPrice = getFacade().getKonkaPePdModelLowestPriceService().getKonkaPePdModelLowestPrice(
				konkaPePdModelLowestPrice);

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (null != konkaPePdModelLowestPrice) {
			sb.append("{\"lowest_price\":\"").append(konkaPePdModelLowestPrice.getLowest_price()).append("\"}");
		} else {
			sb.append("{\"lowest_price\":\"").append(-1).append("\"}");
		}
		sb.append("]");
		logger.info("========{}:" + sb.toString());

		super.renderJson(response, sb.toString());

		return null;
	}
	/**
	 * 根据分公司Id以及登录人的权限取经营部和办事处的数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJybDeptListByFgsId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (!GenericValidator.isLong(fgs_dept_id)) {
			return null;
		}

		if (GenericValidator.isLong(pageCount)) {
			count = Integer.valueOf(pageCount);
		}

		KonkaDept entity = new KonkaDept();
		entity.getRow().setCount(count);
		List<KonkaDept> entityList = new ArrayList<KonkaDept>();

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(userInfo.getId());
		peRoleUser = this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);

		KonkaDept dept = new KonkaDept();
		dept.setDept_id(userInfo.getDept_id());
		dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

		if (peRoleUser.getRole_id() >= 10 && peRoleUser.getRole_id() <= 39) {
			entity.setPar_id(Long.valueOf(fgs_dept_id));
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (peRoleUser.getRole_id() >= 40 && peRoleUser.getRole_id() <= 49) {
			entity.setDept_id(userInfo.getDept_id());
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (peRoleUser.getRole_id() >= 50 && peRoleUser.getRole_id() <= 59) {
			entity.setDept_id(this.getSuperiorForDeptType(userInfo.getDept_id(), 4).getDept_id());
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (peRoleUser.getRole_id() == 60) {
			if (dept.getDept_type() == 4) {
				entity.setDept_id(userInfo.getDept_id());
				entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			}
			if (dept.getDept_type() == 5) {
				entity.setDept_id(this.getSuperiorForDeptType(userInfo.getDept_id(), 4).getDept_id());
				entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			}
		}
		for (KonkaDept t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * 根据经营部Id以及登录人的权限取办事处的数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBscDeptListByJybId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (!GenericValidator.isLong(jyb_dept_id)) {
			return null;
		}

		if (GenericValidator.isLong(pageCount)) {
			count = Integer.valueOf(pageCount);
		}

		KonkaDept entity = new KonkaDept();
		entity.getRow().setCount(count);
		List<KonkaDept> entityList = new ArrayList<KonkaDept>();

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaDept dept = new KonkaDept();
		dept.setDept_id(userInfo.getDept_id());
		dept = getFacade().getKonkaDeptService().getKonkaDept(dept);


		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_10_le_49 = false;
		boolean role_id_ge_50_le_59 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() <= 49L) {
				role_id_ge_10_le_49 = true;
			}
			if (peRoleUser.getRole_id() >= 50L && peRoleUser.getRole_id() <= 59L) {
				role_id_ge_50_le_59 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}
		
		if (role_id_ge_10_le_49) {
			entity.setPar_id(Long.valueOf(jyb_dept_id));
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (role_id_ge_50_le_59) {
			entity.setDept_id(userInfo.getDept_id());
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (role_id_eq_60) {
			if (dept.getDept_type() == 5) {
				entity.setDept_id(userInfo.getDept_id());
				entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			}
		}
		for (KonkaDept t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

}
