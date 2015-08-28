package com.ebiz.mmt.web.struts.webservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaSpActivityAddr;
import com.ebiz.mmt.domain.KonkaSpTask;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaSpActivityAddrAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
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

		String param = (String) dynaBean.get("param");
		String[] params = param.split(",");

		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");

		if (StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)) {
			return null;
		} else {
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
	/*	PeProdUser userInfo = checkUser(inuser_id, inuserpass);
				
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}*/
		// 拿到客户的列表，门店的列表
//		List<KonkaR3Shop> customerList = getCustomer(userInfo);
//		List<KonkaR3Store> storeList=getShop(userInfo);
		
//		request.setAttribute("customerList", customerList);
//		request.setAttribute("storeList", storeList);
//		dynaBean.set("addr_index", generateAddrIndex());
//		dynaBean.set("add_user_name", userInfo.getUser_name());
		return new ActionForward("/manager/admin/KonkaSpActivityAddr");
	
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// if (null == super.checkUserModPopeDom(form, request, "0")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		// setNaviStringToRequestScope(form, request);//导航
		// super.getModPopeDom(form, request);//操作权限

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		String state = (String) dynaBean.get("state");

		String addr_index_like = (String) dynaBean.get("addr_index_like");
		String add_user_like = (String) dynaBean.get("add_user_like");
		String hander_user_like = (String) dynaBean.get("hander_user_like");
		String r3_code = (String) dynaBean.get("r3_code");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");

		//导出标志
		String export= (String) dynaBean.get("export");
		if(StringUtils.isEmpty(inuser_id)||StringUtils.isEmpty(inuserpass)){
			return null;
		}else{
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在
		PeProdUser peProdUser = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}
		KonkaSpActivityAddr entity = new KonkaSpActivityAddr();
		// 根据部门关系筛选
		if (StringUtils.isNotBlank(state) && GenericValidator.isInt(state)) {
			entity.setState(Integer.parseInt(state));
		}
		if (StringUtils.isNotBlank(add_user_like)) {
			entity.getMap().put("add_user_like", add_user_like);
		}
		if (StringUtils.isNotBlank(hander_user_like)) {
			entity.getMap().put("hander_user_like", hander_user_like);
		}
		if (StringUtils.isNotBlank(r3_code)) {
			entity.setR3_code(r3_code);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(addr_index_like)) {
			entity.getMap().put("addr_index_like", addr_index_like);
		}
		if(StringUtils.isBlank(add_date_start) && StringUtils.isBlank(add_date_end)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal=Calendar.getInstance();
			add_date_end=df.format(cal.getTime());	
			cal.add(Calendar.DATE, -10);
			add_date_start=df.format(cal.getTime());
		}
		if(StringUtils.isNotBlank(add_date_start)){
			entity.getMap().put("add_date_start", add_date_start+" 00:00:00");
		}
		if(StringUtils.isNotBlank(add_date_end)){
			entity.getMap().put("add_date_end", add_date_end+" 23:59:59");
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
			// break;
		//	return null;
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
			// return null;
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
	

		Long recordCount = super.getFacade().getKonkaSpActivityAddrService()
				.getKonkaSpActivityAddrCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		if (StringUtils.isNotBlank(export)) {
			pager.init(recordCount, pager.getPageSize(), pager
							.getRequestPage());
			entity.getRow().setFirst(pager.getFirstRow());
			entity.getRow().setCount(recordCount.intValue());

			List<KonkaSpActivityAddr> entityList = super.getFacade()
			.getKonkaSpActivityAddrService()
			.getKonkaSpActivityAddrPaginatedList(entity);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + EncryptUtils.encodingFileName("预约点")
			        + ".xls");
			
			request.setAttribute("entityList", entityList);
			return new ActionForward(
					"/admin/KonkaSpActivityAddr/export.jsp");
		}
		
		List<KonkaSpActivityAddr> entityList = super.getFacade()
				.getKonkaSpActivityAddrService()
				.getKonkaSpActivityAddrPaginatedList(entity);

		request.setAttribute("entityList", entityList);

//		request.setAttribute("deptList", super.getDeptInfoListWithOutLimit(
//				mapping, form, request, response));

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
				
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}
		// 拿到客户的列表，门店的列表
//		List<KonkaR3Shop> customerList = getCustomer(userInfo);
		List<KonkaR3Store> storeList=getShop(userInfo);
		
//		request.setAttribute("customerList", customerList);
		request.setAttribute("storeList", storeList);
		dynaBean.set("addr_index", generateAddrIndex());
		dynaBean.set("add_user_name", userInfo.getUser_name());
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// saveToken(request);
		// setNaviStringToRequestScope(form, request);
		// if (null == super.checkUserModPopeDom(form, request, "2")) {
		// return super.checkPopedomInvalid(request, response);
		// }
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
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
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
				
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		KonkaSpActivityAddr entity = new KonkaSpActivityAddr();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(new Long(id));
		}
		entity = super.getFacade().getKonkaSpActivityAddrService()
				.getKonkaSpActivityAddr(entity);
		entity.setQueryString(super.serialize(request, "mod_id"));
		Attachment att = new Attachment();
		att.setLink_id(Long.valueOf(id));
		att.setLink_tab("KONKA_SP_ACTIVITY_ADDR");
		att.setDel_mark(new Short(new String("0")));
		List<Attachment> attachmentList = super.getFacade()
				.getAttachmentService().getAttachmentList(att);
		request.setAttribute("attachmentList", attachmentList);
		super.copyProperties(form, entity);

		// 拿到客户的列表，门店的列表
		List<KonkaR3Shop> customerList = getCustomer(userInfo);
		List<KonkaR3Store> storeList=getShop(userInfo);
		
		request.setAttribute("customerList", customerList);
		request.setAttribute("storeList", storeList);

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
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
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
				
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('"
					+ msg + "');history.back();}");
			return null;
		}

		KonkaSpActivityAddr entity = new KonkaSpActivityAddr();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(new Long(id));
		}
		entity = super.getFacade().getKonkaSpActivityAddrService()
				.getKonkaSpActivityAddr(entity);
		entity.setQueryString(super.serialize(request, "mod_id"));
		Attachment att = new Attachment();
		att.setLink_id(Long.valueOf(id));
		att.setLink_tab("KONKA_SP_ACTIVITY_ADDR");
		att.setDel_mark(new Short(new String("0")));
		List<Attachment> attachmentList = super.getFacade()
				.getAttachmentService().getAttachmentList(att);
		request.setAttribute("attachmentList", attachmentList);
		super.copyProperties(form, entity);


		return mapping.findForward("view");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String store_id = (String) dynaBean.get("store_id");
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		// 用户名或者密码不存在
		PeProdUser peProdUser = checkUserid(inuser_id, inuserpass,android_version,ios_version);
				

		KonkaSpActivityAddr entity = new KonkaSpActivityAddr();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(id)) {
			entity.setUpdate_user_name(peProdUser.getUser_name());
			entity.setUpdate_date(new Date());
		} else {
			entity.setAdd_user_id(peProdUser.getId());
			entity.setAdd_date(new Date());
		}
		//根据门店找客户
		if(StringUtils.isNotEmpty(store_id)){
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
					entity.setCustomer_name(shop.getCustomer_name());
				}
			}
		}
		// 附件处理
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.MD_PATH, true, 0,
				new int[] { 240 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
		Attachment filesAttachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			filesAttachment = new Attachment();
			filesAttachment.setFile_name(uploadFile.getFileName());
			filesAttachment.setFile_type(uploadFile.getContentType());
			filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
			filesAttachment.setSave_path(uploadFile.getFileSavePath());
			filesAttachment.setSave_name(uploadFile.getFileSaveName());
			filesAttachment.setDel_mark(new Short("0"));
			filesAttachmentList.add(filesAttachment);
		}
		entity.setAttachmentList(filesAttachmentList);

		if (StringUtils.isNotBlank(id)) {
			// update
			// if (null == super.checkUserModPopeDom(form, request, "2")) {
			// return super.checkPopedomInvalid(request, response);
			// }
			super.getFacade().getKonkaSpActivityAddrService()
					.modifyKonkaSpActivityAddr(entity);
			saveMessage(request, "entity.updated");
		} else {
			super.getFacade().getKonkaSpActivityAddrService()
					.createKonkaSpActivityAddr(entity);
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
//		pathBuffer.append("&mod_id=" + mod_id);
		// pathBuffer.append("&dept_type=" + dept_type);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity
				.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaSpTask entity = new KonkaSpTask();
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaSpTaskService().removeKonkaSpTask(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaSpTask entity = new KonkaSpTask();
			entity.getMap().put("pks", pks);
			for (String xx : pks) {
				entity.setId(Long.valueOf(xx));
				getFacade().getKonkaSpTaskService().removeKonkaSpTask(entity);
			}
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(
				request, "id", "method")));
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

			KonkaDept dept_fgs = super
					.getKonkaDeptForFgs(userInfo.getDept_id()); // 查询部门分公司
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
		List<KonkaR3Shop> entityList = super.getFacade()
				.getKonkaR3ShopService().getKonkaR3ShopForCustVisit(entity);
		logger.info("++++++++++++++++++++++++++++++++");
		return entityList;
	}

	@SuppressWarnings("unused")
	private List<KonkaR3Store> getShop(PeProdUser userInfo) {
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
	 * 取地点编码
	 * 
	 * @return
	 */
	protected String generateAddrIndex() {
		Long xx = Math.round(Math.random() * 900) + 100;// [100,1000)
		return "YYD" + DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS")
				+ xx;
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			Attachment entity = new Attachment();
			entity.setId(new Long(id));
			// entity.setLink_tab("KONKA_SP_ACTIVITY_ADDR");
			getFacade().getAttachmentService().removeAttachment(entity);
			saveMessage(request, "entity.deleted");
		}

		super.renderText(response, "success");
		return null;
	}
	
}
