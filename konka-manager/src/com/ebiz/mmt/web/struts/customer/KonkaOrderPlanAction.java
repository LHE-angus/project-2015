package com.ebiz.mmt.web.struts.customer;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.JStocksSummary;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaOrderPlan;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Wang,KunLin
 * @version 2014-11-24
 */

public class KonkaOrderPlanAction extends BaseAction {

	private static final String[] size_strs = { "24", "26", "28", "29", "32", "37", "39", "40", "42", "43", "46", "47",
			"48", "49", "50", "55", "57", "58", "60", "65", "84" };

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Calendar calendar = Calendar.getInstance();
		dynaBean.set("plan_year", String.valueOf(calendar.get(calendar.YEAR)));
		dynaBean.set("plan_month", String.valueOf(calendar.get(calendar.MONTH)+1));
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
//		String dept_id = (String) dynaBean.get("dept_id");// 分公司id
		String plan_year = (String) dynaBean.get("plan_year");// 计划年份
		String plan_month = (String) dynaBean.get("plan_month");// 计划月份
		String pd_name_like = (String) dynaBean.get("pd_name_like");// 机型
		
		//判断用户是否为空
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user || null==user.getCust_id()) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		//根据用户拿到客户信息
		KonkaR3Shop entityKonkaR3Shop = new KonkaR3Shop();
		entityKonkaR3Shop.setId(user.getCust_id());
		entityKonkaR3Shop.setIs_del(0L);
		entityKonkaR3Shop=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entityKonkaR3Shop);
		if(null==entityKonkaR3Shop || null==entityKonkaR3Shop.getR3_code()){
			super.renderJavaScript(response, "window.onload=function(){alert('The customer  is not exist!');history.back();}");
			return null;
		}
		
		KonkaOrderPlan entity = new KonkaOrderPlan();
		
		entity.setR3_code(entityKonkaR3Shop.getR3_code());
//		entity.setCustomer_name(entityKonkaR3Shop.getCustomer_name());
        if (StringUtils.isNotBlank(plan_year) && GenericValidator.isInt(plan_year)) {
			entity.setPlan_year(Integer.parseInt(plan_year));
		}
        if (StringUtils.isNotBlank(plan_month) && GenericValidator.isInt(plan_month)) {
        	entity.setPlan_month(Integer.parseInt(plan_month));
		}
        
        if(StringUtils.isNotBlank(pd_name_like)){
        	entity.getMap().put("pd_name_like", pd_name_like);
        }

		// 分页
		Long recordCount = super.getFacade().getKonkaOrderPlanService().getKonkaOrderPlanForCustomerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		// 查询
		List<KonkaOrderPlan> entityList = super.getFacade().getKonkaOrderPlanService()
				.getKonkaOrderPlanForCustomerPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}
	
	 //待选择机型
	public ActionForward selectModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		
		List<PePdModel> tList = new ArrayList<PePdModel>();
		tList = super.getFacade().getPePdModelService().getPePdModelList(new PePdModel());
		List<MobileSelectItem> entityList = new ArrayList<MobileSelectItem>();
		for (PePdModel pePdModel : tList) {
			MobileSelectItem entity = new MobileSelectItem();
			entity.setId(pePdModel.getPd_id().toString());
			entity.setName(pePdModel.getMd_name());
			entityList.add(entity);
		}
		
		if (null!=entityList) {
			super.renderTextJsonOrJsonp(request, response, JSON
					.toJSONString(entityList));
		}else {
			super.renderTextJsonOrJsonp(request, response, JSON
					.toJSONString("status:"+-1));
		}
		return null;
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		//判断用户是否为空
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user || null==user.getCust_id()) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		//设置默认年月
		Calendar calendar = Calendar.getInstance();
		dynaBean.set("plan_year", String.valueOf(calendar.get(calendar.YEAR)));
		dynaBean.set("plan_month", String.valueOf(calendar.get(calendar.MONTH)+1));
		
		//根据用户拿到客户信息
		KonkaR3Shop entityKonkaR3Shop = new KonkaR3Shop();
		entityKonkaR3Shop.setId(user.getCust_id());
		entityKonkaR3Shop.setIs_del(0L);
		entityKonkaR3Shop=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entityKonkaR3Shop);
		if(null==entityKonkaR3Shop || null==entityKonkaR3Shop.getR3_code()){
			super.renderJavaScript(response, "window.onload=function(){alert('The customer  is not exist!');history.back();}");
			return null;
		}else {
			String r3_code = entityKonkaR3Shop.getR3_code();
			String customer_name = entityKonkaR3Shop.getCustomer_name();
			dynaBean.set("r3_code", r3_code);
			dynaBean.set("customer_name", customer_name);
			dynaBean.set("_r3_code", r3_code);
			dynaBean.set("_customer_name", customer_name);
		}

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		//判断用户是否为空
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user || null==user.getCust_id()) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		//根据用户拿到客户信息
		KonkaR3Shop entityKonkaR3Shop = new KonkaR3Shop();
		entityKonkaR3Shop.setId(user.getCust_id());
		entityKonkaR3Shop.setIs_del(0L);
		entityKonkaR3Shop=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entityKonkaR3Shop);
		if(null==entityKonkaR3Shop || null==entityKonkaR3Shop.getR3_code()){
			super.renderJavaScript(response, "window.onload=function(){alert('The customer  is not exist!');history.back();}");
			return null;
		}
		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('请选择需要修改的记录！');history.back();}");
			return null;
		}

		KonkaOrderPlan entity = new KonkaOrderPlan();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaOrderPlanService().getKonkaOrderPlan(entity);
		if (null != entity) {
			super.copyProperties(form, entity);
		}
//
//		// 拿到客户的列表
//		List<KonkaR3Shop> customerList = getCustomer(user);
//		request.setAttribute("customerList", customerList);
		
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		
		//判断用户是否为空
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user || null==user.getCust_id()) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		//根据用户拿到客户信息
		KonkaR3Shop entityKonkaR3Shop = new KonkaR3Shop();
		entityKonkaR3Shop.setId(user.getCust_id());
		entityKonkaR3Shop.setIs_del(0L);
		entityKonkaR3Shop=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entityKonkaR3Shop);
		if(null==entityKonkaR3Shop || null==entityKonkaR3Shop.getR3_code()){
			super.renderJavaScript(response, "window.onload=function(){alert('The customer  is not exist!');history.back();}");
			return null;
		}
		
		KonkaOrderPlan entity = new KonkaOrderPlan();
		super.copyProperties(entity, form);
		if (StringUtils.isBlank(id)) {// 添加
			entity.setAdd_user_id(user.getId());
			entity.setAdd_user_name(user.getUser_name());
			entity.setAdd_date(new Date());
			super.getFacade().getKonkaOrderPlanService().createKonkaOrderPlan(entity);
		} else {// 修改
			entity.setId(Long.valueOf(id));
			entity.setModify_user_id(user.getId());
			entity.setModify_user_name(user.getUser_name());
			entity.setModify_date(new Date());
			super.getFacade().getKonkaOrderPlanService().modifyKonkaOrderPlan(entity);
		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/manager/KonkaOrderPlan.do?method=list");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "pks", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		//判断用户是否为空
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user || null==user.getCust_id()) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		//根据用户拿到客户信息
		KonkaR3Shop entityKonkaR3Shop = new KonkaR3Shop();
		entityKonkaR3Shop.setId(user.getCust_id());
		entityKonkaR3Shop.setIs_del(0L);
		entityKonkaR3Shop=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entityKonkaR3Shop);
		if(null==entityKonkaR3Shop || null==entityKonkaR3Shop.getR3_code()){
			super.renderJavaScript(response, "window.onload=function(){alert('The customer  is not exist!');history.back();}");
			return null;
		}
		
		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('请选择需要修改的记录！');history.back();}");
			return null;
		}

		KonkaOrderPlan entity = new KonkaOrderPlan();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaOrderPlanService().getKonkaOrderPlan(entity);
		if (null != entity) {
			super.copyProperties(form, entity);
		}

		return mapping.findForward("view");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		if (null == super.checkUserModPopeDom(form, request, "4")) {
//			return super.checkPopedomInvalid(request, response);
//		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaOrderPlan entity = new KonkaOrderPlan();
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaOrderPlanService().removeKonkaOrderPlan(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaOrderPlan entity = new KonkaOrderPlan();
			entity.getMap().put("pks", pks);
			getFacade().getKonkaOrderPlanService().removeKonkaOrderPlan(entity);
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	/**
	 * 根据用户按照部门级别获取客户
	 * 
	 * @param userInfo
	 * @return
	 */
	protected List<KonkaR3Shop> getCustomer(PeProdUser userInfo) {
		KonkaR3Shop entity = new KonkaR3Shop();

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());

		Long _dept_id = 0L;
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		// return null;
		case 8:
			// 分公司及以下部门可见

			KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
			if (null != dept_fgs && null != dept_fgs.getDept_id()) {
				_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
				entity.getMap().put("dept_id_start", _dept_id);
				// entity.getMap().put("fgs_dept_value", _dept_id);
			}
			break;

		case 7:
			// 我所在的部门及以下部门可见
			_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = userInfo.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", userInfo.getId());

		// entity.setIs_del(0L);// 未被停用的
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);
		logger.info("++++++++++++++++++++++++++++++++");
		return entityList;
	}
	/*
	 * 导入数据
	 */
	public ActionForward excelImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		KonkaOrderPlan entity = new KonkaOrderPlan();
//		KonkaR3StoreShow entity = new KonkaR3StoreShow();
		super.copyProperties(entity, form);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.CUSTOMER_USER_INFO);
		if (null == user) {
			return null;
		}
		File isFile = null;
		String fileSavePath = null;
		String ctxDir = "";
		Calendar calendar = Calendar.getInstance();
		
		int cur_year = calendar.get(Calendar.YEAR);
		int cur_month= calendar.get(Calendar.MONTH);
		
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		for (UploadFile uploadFile : uploadFileList) {
			if ("excel".equals(uploadFile.getFormName())) {
				fileSavePath = uploadFile.getFileSavePath();
				break;
			}
		}

		// 附件保存路径更改遗留问题
		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
			ctxDir = "";
			fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
		}

		if (fileSavePath != null) {
			String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
			if (!filetype.equalsIgnoreCase("xls") && !filetype.equalsIgnoreCase("xlsx")) {
				super.renderJavaScript(response, "alert('上传数据格式不正确！');history.back();");
				return null;
			}
			fileSavePath = ctxDir + fileSavePath;
			isFile = new File(fileSavePath);
			Map<Object,String> map = new HashMap<Object,String>();
			map.put(1, "plan_year");
			map.put(2, "plan_month");
		//	map.put(3, "r3_code");
			map.put(3, "pd_name");
			map.put(4, "last_stock_num");
			map.put(5, "plan_stock_num");
			map.put(6, "plan_sale_num");
			map.put(7, "memo");
			List<Object> list = super.getExcelList(map, KonkaOrderPlan.class, isFile, 1);
			List<KonkaOrderPlan> rList = new ArrayList<KonkaOrderPlan>();
			if ( null != list) {
				for (int i = 0; i < list.size(); i++) {
					KonkaOrderPlan carno = (KonkaOrderPlan)list.get(i);

					if(carno.getPlan_year() < cur_year){
						super.renderJavaScript(response,
						"alert('计划的年必须是今年或以后！');history.back();");
						return null;
					}else if(carno.getPlan_year() - cur_year == 0){
						if(carno.getPlan_month() < cur_month ){
							super.renderJavaScript(response,
							"alert('计划的月份必须是本月或以后！');history.back();");
							return null;
						}
					}
					
					
					if(null==carno.getLast_stock_num()){
						super.renderJavaScript(response,
						"alert('有上月库存为空的行！');history.back();");
						return null;
					}
					if(null==carno.getPlan_stock_num()){
						super.renderJavaScript(response,
						"alert('有本月计划进货量为空的行！');history.back();");
						return null;
					}
					//拿客户名称
					KonkaR3Shop shop = new KonkaR3Shop();
					if(null!=user.getCust_id()){
						 shop = new KonkaR3Shop();
					   shop.setId(user.getCust_id());
							List<KonkaR3Shop> shoplist = getFacade()
									.getKonkaR3ShopService().getKonkaR3ShopList(shop);
							if (null != shoplist && shoplist.size() > 0) {
								shop = shoplist.get(0);
								carno.setR3_code(shop.getR3_code());
								carno.setCustomer_name(shop.getCustomer_name());
							} 
					} else {
						super.renderJavaScript(response,
								"alert('有客户编码未填写的行！');history.back();");
						return null;
					}
					
					
					//拿机型id
					PePdModel model = new PePdModel();
					if(null != carno.getPd_name()){
						model.setMd_name(carno.getPd_name());
						List<PePdModel> modellist = getFacade().getPePdModelService().getPePdModelList(model);
						if (null != modellist && modellist.size() > 0) {
							model = modellist.get(0);
							carno.setPd_name(model.getMd_name());
							carno.setPd_id(model.getPd_id());
						}else {
							super.renderJavaScript(response,"alert('有机型填写不正确！');history.back();");
							return null;
						}
					} else {
						super.renderJavaScript(response,
								"alert('有机型未填写的行！');history.back();");
						return null;

					}
					carno.setAdd_date(new Date());
					carno.setAdd_user_id(user.getId());
					rList.add(carno);
				}
			}
			List<KonkaOrderPlan> rList2 = new ArrayList<KonkaOrderPlan>();
			HashSet<KonkaOrderPlan> result = new HashSet<KonkaOrderPlan>();
			for (int i = 0; i < rList.size(); i++) {
				if(!result.add(rList.get(i))){
				}else {
					result.add(rList.get(i));
					rList2.add(rList.get(i));
				}
			}
			String msg = super.getFacade().getKonkaOrderPlanService().createKonkaOrderPlan(rList2);
//			String msg = super.getFacade().getKonkaR3StoreShowService().createKonkaR3StoreShow(rList2);
			if (!"".equals(msg)) {
				msg = " 系统提示 ,信息导入失败：\\n" + msg;
				log.info(msg);
				super.renderJavaScript(response, "alert('" + msg + "');history.back();");
				return null;
			}
		} else {
			super.renderJavaScript(response, "alert('请选择上传Excel文件！');history.back();");
			return null;
		}
		super.saveMessage(request, "entity.inserted");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}
	
	public static boolean isValidLong(String str) {
		try {
			long _v = Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidInt(String str) {
		try {
			int _v = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isValidDouble(String str) {
		try {
			double _v = Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	// 查上月库存
	public ActionForward getLastStock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String year = (String) dynaBean.get("year");
		String month = (String) dynaBean.get("month");
		String r3_code = (String) dynaBean.get("r3_code");
		String pd_name = (String) dynaBean.get("pd_name");
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month) && StringUtils
				.isNotBlank(r3_code) && StringUtils.isNotBlank(pd_name)) {
		}else{
			super.renderJavaScript(response, "alert(参数不完整);history.back();");
			super.renderJsonp(request, response, 0+"");
			return null;
		}
		if(Integer.parseInt(month)-1==0){
			year=""+(Integer.parseInt(year)-1);
			month="12";
		}else{
			if(Integer.parseInt(month)-10 > 0){
				month=""+(Integer.parseInt(month)-1);
			}else{
			month="0"+(Integer.parseInt(month)-1);
			}
		}
		JStocksSummary jStocksSummary = new JStocksSummary();
		jStocksSummary.setR3_code(r3_code);
		jStocksSummary.setGoods_name(pd_name);
		jStocksSummary.setType(1);//月度库存计算的。
		jStocksSummary.getMap().put("year_month", year+"-"+month);
		Long stocks = super.getFacade().getJStocksSummaryService().getJStocksSummaryLastStocks(jStocksSummary);
		if(null==stocks){
			stocks=0l;
		}
		super.renderJsonp(request, response, stocks+"");
		return null;
	}
}
