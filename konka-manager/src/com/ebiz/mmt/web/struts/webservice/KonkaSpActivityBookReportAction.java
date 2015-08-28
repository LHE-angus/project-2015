package com.ebiz.mmt.web.struts.webservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaSpActivityAddr;
import com.ebiz.mmt.domain.KonkaSpActivityBookReport;
import com.ebiz.mmt.domain.KonkaSpActivityManager;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaSpActivityBookReportAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String param=(String)dynaBean.get("param");
		if(StringUtils.isNotBlank(param)){
			return this.addMobile(mapping, form, request, response);
		}
		
		
		return this.list(mapping, form, request, response);
	}

	private ActionForward addMobile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		// saveToken(request);
		// setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// if (null == super.checkUserModPopeDom(form, request, "1")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		// 判断用户是否是空
		// 用户名
		KonkaSpActivityAddr addr=new KonkaSpActivityAddr();
		//addr.setState(0);
		String param = (String) dynaBean.get("param");
		if(StringUtils.isNotBlank(param) && GenericValidator.isLong(param)){
			addr.setId(Long.valueOf(param));
		}else{
			super.renderHtml(response, "未找到与二维码匹配的预约点");
			return null;
		}
		List<KonkaSpActivityAddr> addrList=super.getFacade().getKonkaSpActivityAddrService().getKonkaSpActivityAddrList(addr);
		
		if(null==addrList || addrList.size()<1){
			super.renderHtml(response, "二维码不正确");
			return null;
		}
		addr=addrList.get(0);
		KonkaSpActivityManager entity = new KonkaSpActivityManager();
		Long store_id=addr.getStore_id();
		
			KonkaR3Store konkar3store=new KonkaR3Store();
			konkar3store.setStore_id(Long.valueOf(store_id));
			konkar3store.setIs_del(0);
			konkar3store=super.getFacade().getKonkaR3StoreService().getKonkaR3Store(konkar3store);
			if(null!=konkar3store && null!=konkar3store.getR3_code()){
				entity.getMap().put("ext_r3_code", konkar3store.getR3_code());
			}
		request.setAttribute("store_id", addr.getStore_id());
		request.setAttribute("store_name", addr.getStore_name());
		request.setAttribute("addr_id", addr.getId());
		request.setAttribute("addr_name", addr.getAddr());
		List<KonkaSpActivityManager> entityList = super.getFacade().getKonkaSpActivityManagerService()
				.getKonkaSpActivityManagerListForBookReport(entity);
		if (null != entityList) {
		request.setAttribute("spList",entityList);
		}
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");

		return new ActionForward("/KonkaSpActivityBookReport/formMobile.jsp");
	
	}
	
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		// setNaviStringToRequestScope(form, request);//导航
		// super.getModPopeDom(form, request);//操作权限

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String perpay_state = (String) dynaBean.get("perpay_state");

		String addr_name_like = (String) dynaBean.get("addr_name_like");
		String sp_name_like = (String) dynaBean.get("sp_name_like");
		String model_name_like = (String) dynaBean.get("model_name_like");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		String store_name_like = (String) dynaBean.get("store_name_like");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		
		// 导出标志
		String export = (String) dynaBean.get("export");

		if(StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)){
			super.renderJavaScript(response, "alert('用户信息出错');history.back();");
			return null;
		}else{
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}
		KonkaSpActivityBookReport entity = new KonkaSpActivityBookReport();
		// 根据部门关系筛选
		if (StringUtils.isNotBlank(perpay_state) && GenericValidator.isInt(perpay_state)) {
			entity.setPrepay_state(Integer.parseInt(perpay_state));
		}
		if (StringUtils.isNotBlank(sp_name_like)) {
			entity.getMap().put("sp_name_like", sp_name_like);
		}
		if (StringUtils.isNotBlank(addr_name_like)) {
			entity.getMap().put("addr_name_like", addr_name_like);
		}
		if (StringUtils.isNotBlank(model_name_like)) {
			entity.getMap().put("model_name_like", model_name_like);
		}
		if(StringUtils.isBlank(add_date_start) && StringUtils.isBlank(add_date_end)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal=Calendar.getInstance();
			add_date_end=df.format(cal.getTime());	
			cal.add(Calendar.DATE, -30);
			add_date_start=df.format(cal.getTime());
		}
		if(StringUtils.isNotBlank(add_date_start)){
			entity.getMap().put("add_date_start", add_date_start+" 00:00:00");
		}
		if(StringUtils.isNotBlank(add_date_end)){
			entity.getMap().put("add_date_end", add_date_end+" 23:59:59");
		}
		
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		
		boolean flag = false;
		boolean fgsgly = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
		}
		Long _dept_id = 0L;
		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			if (!fgsgly) {
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(peProdUser.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			_dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", peProdUser.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", peProdUser.getId());
	
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");

		Long recordCount = super.getFacade().getKonkaSpActivityBookReportService().getKonkaSpActivityBookReportCount(
				entity);

		if (StringUtils.isNotBlank(export)) {
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(recordCount.intValue());

			List<KonkaSpActivityBookReport> entityList = super.getFacade().getKonkaSpActivityBookReportService()
					.getKonkaSpActivityBookReportPaginatedList(entity);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("预约上报")
					+ ".xls");

			request.setAttribute("entityList", entityList);
			return new ActionForward("/admin/KonkaSpActivityBookReport/export.jsp");
		}
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaSpActivityBookReport> entityList = super.getFacade().getKonkaSpActivityBookReportService()
				.getKonkaSpActivityBookReportPaginatedList(entity);
		request.setAttribute("entityList", entityList);

//		request.setAttribute("deptList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// saveToken(request);
		// setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// if (null == super.checkUserModPopeDom(form, request, "1")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		// 判断用户是否是空
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		if(StringUtils.isEmpty(inuser_id)||StringUtils.isEmpty(inuserpass)){
			return null;
		}else{
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		// 拿到客户的列表
//		List<KonkaR3Shop> customerList = getCustomer(userInfo);
		List<KonkaR3Store> storeList=getShop(userInfo);
		
//		request.setAttribute("customerList", customerList);
		request.setAttribute("storeList", storeList);
		
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// saveToken(request);
		// setNaviStringToRequestScope(form, request);
		// if (null == super.checkUserModPopeDom(form, request, "2")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		if(StringUtils.isEmpty(inuser_id)||StringUtils.isEmpty(inuserpass)){
			return null;
		}else{
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		

		KonkaSpActivityBookReport entity = new KonkaSpActivityBookReport();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(new Long(id));
		}
		entity = super.getFacade().getKonkaSpActivityBookReportService().getKonkaSpActivityBookReport(entity);
		entity.setQueryString(super.serialize(request, "mod_id"));
		super.copyProperties(form, entity);

		// 拿到客户的列表
//		List<KonkaR3Shop> customerList = getCustomer(userInfo);
//		request.setAttribute("customerList", customerList);
		List<KonkaR3Store> storeList=getShop(userInfo);
		
		request.setAttribute("storeList", storeList);
		
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		if(StringUtils.isEmpty(inuser_id)||StringUtils.isEmpty(inuserpass)){
			return null;
		}else{
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaSpActivityBookReport entity = new KonkaSpActivityBookReport();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(new Long(id));
		}
		entity = super.getFacade().getKonkaSpActivityBookReportService().getKonkaSpActivityBookReport(entity);
		entity.setQueryString(super.serialize(request, "mod_id"));
		super.copyProperties(form, entity);
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		return mapping.findForward("view");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String store_id = (String) dynaBean.get("store_id");

		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		if(StringUtils.isEmpty(inuser_id)||StringUtils.isEmpty(inuserpass)){
			return null;
		}else{
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaSpActivityBookReport entity = new KonkaSpActivityBookReport();
		super.copyProperties(entity, form);

		//根据门店找客户
		if(null!=store_id){
			KonkaR3Store konkar3store=new KonkaR3Store();
			konkar3store.setStore_id(entity.getStore_id());
			konkar3store.setIs_del(0);
			konkar3store=super.getFacade().getKonkaR3StoreService().getKonkaR3Store(konkar3store);
			if(null!=konkar3store && null!= konkar3store.getR3_code()){
				KonkaR3Shop shop =new KonkaR3Shop();
				shop.setR3_code(konkar3store.getR3_code());
				shop.setIs_del(0L);
				shop=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
				if(null!=shop && null!= shop.getCustomer_name()){
					entity.setR3_code(konkar3store.getR3_code());
				}
			}
		}
		
		if (null != entity.getAddr_id()) {
			KonkaSpActivityAddr addr = new KonkaSpActivityAddr();
			addr.setId(entity.getAddr_id());
			addr.setState(0);
			addr = super.getFacade().getKonkaSpActivityAddrService().getKonkaSpActivityAddr(addr);
			if (null != addr && null != addr.getAddr()) {
				entity.setAddr_name(addr.getAddr());
			}
		}
		if (null != entity.getSp_id() && !"".equals(String.valueOf(entity.getSp_id()))) {
			KonkaSpActivityManager mange = new KonkaSpActivityManager();
			mange.setId(entity.getSp_id());
			mange = super.getFacade().getKonkaSpActivityManagerService().getKonkaSpActivityManager(mange);
			if (null != mange && null != mange.getSp_name()) {
				entity.setSp_name(mange.getSp_name());
			}
		}
		if (StringUtils.isBlank(id)) {
			entity.setAdd_date(new Date());
			entity.setAdd_user_name(userInfo.getUser_name());
			entity.setAdd_user_id(userInfo.getId());
			entity.setState(0);
		}
		if (StringUtils.isNotBlank(id)) {
			// update
			// if (null == super.checkUserModPopeDom(form, request, "2")) {
			// return super.checkPopedomInvalid(request, response);
			// }
			super.getFacade().getKonkaSpActivityBookReportService().modifyKonkaSpActivityBookReport(entity);

			saveMessage(request, "entity.updated");
		} else {
			super.getFacade().getKonkaSpActivityBookReportService().createKonkaSpActivityBookReport(entity);
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		// pathBuffer.append("&dept_type=" + dept_type);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
	public ActionForward saveMobile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setNaviStringToRequestScope(form, request);
		
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String store_id = (String) dynaBean.get("store_id");
		
//		String inuser_id = (String) dynaBean.get("user_id");
//		// 密码
//		String inuserpass = (String) dynaBean.get("userpass");
//		if(StringUtils.isEmpty(inuser_id)||StringUtils.isEmpty(inuserpass)){
//			return null;
//		}else{
//			request.setAttribute("user_id", inuser_id);
//			request.setAttribute("userpass", inuserpass);
//		}
//		PeProdUser userInfo = checkUser(inuser_id, inuserpass);
//		if (null == userInfo) {
//			String msg = super.getMessage(request, "user.session.isEmpty");
//			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
//			return null;
//		}
		KonkaSpActivityBookReport entity = new KonkaSpActivityBookReport();
		super.copyProperties(entity, form);
		
		//根据门店找客户
		if(null!=store_id){
			KonkaR3Store konkar3store=new KonkaR3Store();
			konkar3store.setStore_id(entity.getStore_id());
			konkar3store.setIs_del(0);
			konkar3store=super.getFacade().getKonkaR3StoreService().getKonkaR3Store(konkar3store);
			if(null!=konkar3store && null!= konkar3store.getR3_code()){
				KonkaR3Shop shop =new KonkaR3Shop();
				shop.setR3_code(konkar3store.getR3_code());
				shop.setIs_del(0L);
				shop=super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(shop);
				if(null!=shop && null!= shop.getCustomer_name()){
					entity.setR3_code(konkar3store.getR3_code());
				}
			}
		}
		
		if (null != entity.getAddr_id()) {
			KonkaSpActivityAddr addr = new KonkaSpActivityAddr();
			addr.setId(entity.getAddr_id());
			addr.setState(0);
			addr = super.getFacade().getKonkaSpActivityAddrService().getKonkaSpActivityAddr(addr);
			if (null != addr && null != addr.getAddr()) {
				entity.setAddr_name(addr.getAddr());
			}
		}
		if (null != entity.getSp_id() && !"".equals(String.valueOf(entity.getSp_id()))) {
			KonkaSpActivityManager mange = new KonkaSpActivityManager();
			mange.setId(entity.getSp_id());
			mange = super.getFacade().getKonkaSpActivityManagerService().getKonkaSpActivityManager(mange);
			if (null != mange && null != mange.getSp_name()) {
				entity.setSp_name(mange.getSp_name());
			}
		}
		if (StringUtils.isBlank(id)) {
			entity.setAdd_date(new Date());
//			entity.setAdd_user_name(userInfo.getUser_name());
//			entity.setAdd_user_id(userInfo.getId());
			entity.setState(0);
		}
		if (StringUtils.isNotBlank(id)) {
			// update
			// if (null == super.checkUserModPopeDom(form, request, "2")) {
			// return super.checkPopedomInvalid(request, response);
			// }
			super.getFacade().getKonkaSpActivityBookReportService().modifyKonkaSpActivityBookReport(entity);
			
			saveMessage(request, "entity.updated");
		} else {
			super.getFacade().getKonkaSpActivityBookReportService().createKonkaSpActivityBookReport(entity);
		}
		// the line below is added for pagination
		super.renderJavaScript(response, "alert('恭喜你预约成功');");
		// end
		return null;
	}

	// public ActionForward delete(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// if (null == super.checkUserModPopeDom(form, request, "4")) {
	// return super.checkPopedomInvalid(request, response);
	// }
	//
	// DynaBean dynaBean = (DynaBean) form;
	// // String id = (String) dynaBean.get("id");
	// // String[] pks = (String[]) dynaBean.get("pks");
	// // String mod_id = (String) dynaBean.get("mod_id");
	// //
	// // if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
	// // KonkaSpTask entity = new KonkaSpTask();
	// // entity.setId(Long.valueOf(id));
	// // getFacade().getKonkaSpTaskService().removeKonkaSpTask(entity);
	// // saveMessage(request, "entity.deleted");
	// // } else if (!ArrayUtils.isEmpty(pks)) {
	// // KonkaSpTask entity = new KonkaSpTask();
	// // entity.getMap().put("pks", pks);
	// // for (String xx : pks) {
	// // entity.setId(Long.valueOf(xx));
	// // getFacade().getKonkaSpTaskService().removeKonkaSpTask(entity);
	// // }
	// // saveMessage(request, "entity.deleted");
	// // }
	// // the line below is added for pagination
	// StringBuffer pathBuffer = new StringBuffer();
	// pathBuffer.append(mapping.findForward("success").getPath());
	// // pathBuffer.append("&").append("mod_id=").append(mod_id);
	// pathBuffer.append("&");
	// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
	// request, "id", "method")));
	// ActionForward forward = new ActionForward(pathBuffer.toString(), true);
	// // end
	// return forward;
	// }
	protected List<KonkaR3Store> getShop(PeProdUser userInfo) {
		KonkaR3Store entity = new KonkaR3Store();
//		if (null != report_type) {
//			if (report_type == 1) {
//
//				entity.getMap().put("cust_type", new String[] { "1", "2" });
//			}
//			if (report_type == 2) {
//				entity.getMap().put("cust_type", new String[] { "3", "4" });
//			}
//		}
		boolean flag = false;
		boolean fgsgly = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
		}
		// if (flag) {// 是业务员
		// entity.getMap().put("filter_by_ywy_id_eq", userInfo.getId());
		// entity.getMap().put("fgs_dept_value", "");
		// }else
		{

			Long _dept_id = 0L;
			int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
			logger.info("Max level : {}", max_dlevel);
			switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
				_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
				// break;
				return null;
			case 8:
				// 分公司及以下部门可见
				if (!fgsgly) {
					KonkaDept dept_fgs = super.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
					if (null != dept_fgs && null != dept_fgs.getDept_id()) {
						_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
						entity.getMap().put("dept_id_start", _dept_id);
						// entity.getMap().put("fgs_dept_value", _dept_id);
					}
					break;
				} else {
					return null;
				}
				// return null;
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
		}
		entity.setIs_del(0);// 未被停用的
		List<KonkaR3Store> entityList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreForCustVisit1(entity);
		return entityList;
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

		entity.setIs_del(0L);// 未被停用的
		List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);
		logger.info("++++++++++++++++++++++++++++++++");
		return entityList;
	}

	// 根据R3编码获取他参与的促销活动
	public ActionForward ajaxGetSp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		KonkaSpActivityManager entity = new KonkaSpActivityManager();

		if (StringUtils.isBlank(store_id)) {
			return null;
		} else {
			KonkaR3Store konkar3store=new KonkaR3Store();
			konkar3store.setStore_id(Long.valueOf(store_id));
			konkar3store.setIs_del(0);
			konkar3store=super.getFacade().getKonkaR3StoreService().getKonkaR3Store(konkar3store);
			if(null!=konkar3store && null!=konkar3store.getR3_code()){
				entity.getMap().put("ext_r3_code", konkar3store.getR3_code());
			}
		}
		List<KonkaSpActivityManager> entityList = super.getFacade().getKonkaSpActivityManagerService()
				.getKonkaSpActivityManagerListForBookReport(entity);
		if (null != entityList) {
			super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(entityList));
		}
		return null;
	}

	// 根据R3编码获取他参与的促销地点
	public ActionForward ajaxGetAddr(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String store_id = (String) dynaBean.get("store_id");
		if(StringUtils.isBlank(store_id)){
			return null;
		}
		KonkaSpActivityAddr addr = new KonkaSpActivityAddr();
		addr.setStore_id(Long.valueOf(store_id));
		addr.setState(0);

		List<KonkaSpActivityAddr> addrlist = super.getFacade().getKonkaSpActivityAddrService()
				.getKonkaSpActivityAddrList(addr);
		if (null != addrlist) {
			super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(addrlist)); 
		}
		return null;
	}

	public ActionForward listYytj(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String perpay_state = (String) dynaBean.get("perpay_state");

		String addr_name_like = (String) dynaBean.get("addr_name_like");
		String sp_name_like = (String) dynaBean.get("sp_name_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String size_section=(String) dynaBean.get("size_section");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		String store_name_like = (String) dynaBean.get("store_name_like");
		//分公司
		String dept_id = (String) dynaBean.get("dept_id");
		// 导出标志
		String export = (String) dynaBean.get("export");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaSpActivityBookReport entity = new KonkaSpActivityBookReport();
		
		
		if (StringUtils.isNotBlank(sp_name_like)) {
			entity.getMap().put("sp_name_like", sp_name_like);
		}
		if (StringUtils.isNotBlank(addr_name_like)) {
			entity.getMap().put("addr_name_like", addr_name_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(size_section)) {
			entity.setSize_section(size_section);
		}
		if(StringUtils.isNotBlank(dept_id) && GenericValidator.isInt(dept_id)){
			entity.getMap().put("dept_id", dept_id);
		}
		if(StringUtils.isBlank(add_date_start) && StringUtils.isBlank(add_date_end)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal=Calendar.getInstance();
			add_date_end=df.format(cal.getTime());	
			cal.add(Calendar.DATE, -30);
			add_date_start=df.format(cal.getTime());
		}
		if(StringUtils.isNotBlank(add_date_start)){
			entity.getMap().put("add_date_start", add_date_start+" 00:00:00");
		}
		if(StringUtils.isNotBlank(add_date_end)){
			entity.getMap().put("add_date_end", add_date_end+" 23:59:59");
		}
		// 根据部门关系筛选
		boolean flag = false;
		boolean fgsgly = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
		}
		Long _dept_id = 0L;
		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			if (!fgsgly) {
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(peProdUser.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			_dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", peProdUser.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", peProdUser.getId());
	

		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");

		Long recordCount = super.getFacade().getKonkaSpActivityBookReportService()
				.getKonkaSpActivityBookReportYytjCount(entity);

		if (StringUtils.isNotBlank(export)) {
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(recordCount.intValue());

			List<KonkaSpActivityBookReport> entityList = super.getFacade().getKonkaSpActivityBookReportService()
					.getKonkaSpActivityBookReportYytjPaginatedList(entity);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("预约统计")
					+ ".xls");

			request.setAttribute("entityList", entityList);
			return new ActionForward("/admin/KonkaSpActivityBookReport/exportYytj.jsp");
		}
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaSpActivityBookReport> entityList = super.getFacade().getKonkaSpActivityBookReportService()
				.getKonkaSpActivityBookReportYytjPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		List<KonkaDept>	deptList= super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);
		
		return new ActionForward("/admin/KonkaSpActivityBookReport/listYytj.jsp");
	}
	public ActionForward listYyLstj(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String perpay_state = (String) dynaBean.get("perpay_state");

		String addr_name_like = (String) dynaBean.get("addr_name_like");
		String sp_name_like = (String) dynaBean.get("sp_name_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String size_section=(String) dynaBean.get("size_section");
		
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		
		//分公司
		String dept_id = (String) dynaBean.get("dept_id");
		// 导出标志
		String export = (String) dynaBean.get("export");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaSpActivityBookReport entity = new KonkaSpActivityBookReport();
		
		
		if (StringUtils.isNotBlank(sp_name_like)) {
			entity.getMap().put("sp_name_like", sp_name_like);
		}
		if (StringUtils.isNotBlank(addr_name_like)) {
			entity.getMap().put("addr_name_like", addr_name_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(size_section)) {
			entity.setSize_section(size_section);
		}
		if(StringUtils.isNotBlank(dept_id) && GenericValidator.isInt(dept_id)){
			entity.getMap().put("dept_id", dept_id);
		}
		if(StringUtils.isBlank(add_date_start) && StringUtils.isBlank(add_date_end)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal=Calendar.getInstance();
			add_date_end=df.format(cal.getTime());	
			cal.add(Calendar.DATE, -30);
			add_date_start=df.format(cal.getTime());
		}
		if(StringUtils.isNotBlank(add_date_start)){
			entity.getMap().put("add_date_start", add_date_start+" 00:00:00");
		}
		if(StringUtils.isNotBlank(add_date_end)){
			entity.getMap().put("add_date_end", add_date_end+" 23:59:59");
		}
		// 根据部门关系筛选
		boolean flag = false;
		boolean fgsgly = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(peProdUser.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id().equals(new Long(60))) {
				flag = true;
			}
			if (peRoleUser.getRole_id().equals(new Long(30))) {
				fgsgly = true;
			}
		}
		Long _dept_id = 0L;
		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			break;
		case 8:
			// 分公司及以下部门可见
			if (!fgsgly) {
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(peProdUser.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					_dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("dept_id_start", _dept_id);
					// entity.getMap().put("fgs_dept_value", _dept_id);
				}
				break;
			}
			break;
		case 7:
			// 我所在的部门及以下部门可见
			_dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = peProdUser.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", peProdUser.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", peProdUser.getId());
	
		//尺寸段基础数据
		super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");

		Long recordCount = super.getFacade().getKonkaSpActivityBookReportService()
				.getKonkaSpActivityBookReportYyLstjCount(entity);

		if (StringUtils.isNotBlank(export)) {
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(recordCount.intValue());

			List<KonkaSpActivityBookReport> entityList = super.getFacade().getKonkaSpActivityBookReportService()
					.getKonkaSpActivityBookReportYyLstjPaginatedList(entity);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("预约零售统计")
					+ ".xls");

			request.setAttribute("entityList", entityList);
			return new ActionForward("/admin/KonkaSpActivityBookReport/exportYyLstj.jsp");
		}
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaSpActivityBookReport> entityList = super.getFacade().getKonkaSpActivityBookReportService()
				.getKonkaSpActivityBookReportYyLstjPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		List<KonkaDept>	deptList= super.getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);
		
		return new ActionForward("/admin/KonkaSpActivityBookReport/listYyLstj.jsp");
	}
	
}
