package com.ebiz.mmt.web.struts.webservice;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.axis2.databinding.types.soapencoding.Array;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaFightActivityManager;
import com.ebiz.mmt.domain.KonkaMobilePd;
import com.ebiz.mmt.domain.KonkaR3ShopLink;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaFightActivityManagerAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String mod_id = (String) dynaBean.get("mod_id");
		// 用户名
		String user_id = (String) dynaBean.get("user_id");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		//版本号,android_version,ios_version
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		if (StringUtils.isEmpty(user_id) || StringUtils.isEmpty(userpass)) {
			return null;
		} else {
			request.setAttribute("user_id", user_id);
			request.setAttribute("userpass", userpass);
		}

		PeProdUser ui = checkUserid(user_id, userpass,android_version,ios_version);
		if (null == ui) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaFightActivityManager entity = new KonkaFightActivityManager();

		String r3_code = (String) dynaBean.get("r3_code");
		String customer_name_like = (String) dynaBean.get("customer_name_like");// 客户聪明
		String model_like = (String) dynaBean.get("model_like");
		String brand_id = (String) dynaBean.get("brand_id");
		String is_del = (String) dynaBean.get("is_del");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		String store_name_like = (String) dynaBean.get("store_name_like");
		String dept_id = (String) dynaBean.get("dept_id");
		String store_id = (String) dynaBean.get("store_id");
		String begin_date_start = (String) dynaBean.get("begin_date_start");
		String begin_date_end = (String) dynaBean.get("begin_date_end");
		String activity_name_like = (String) dynaBean.get("activity_name_like");

		boolean flag = false;
		boolean fgsgly = false;
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
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
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		switch (max_dlevel) {
		case 9:
			// 集团及以下部门可见
			_dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
			// break;
			// return null;
		case 8:
			// 分公司及以下部门可见
			if (!fgsgly) {
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
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
			_dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			entity.getMap().put("dept_id_start", _dept_id);
			break;
		case 0:
			_dept_id = ui.getDept_id(); // 默认为当前用户所在部门
			// entity.getMap().put("dept_id_start", __dept_id);
			entity.getMap().put("filter_by_ywy_id_eq", ui.getId());
			break;
		default:
			// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());

		if (StringUtils.isNotBlank(r3_code)) {
			entity.getMap().put("r3_code", r3_code);
		}
		if (StringUtils.isNotBlank(activity_name_like)) {
			entity.getMap().put("activity_name_like", activity_name_like);
		}
		if (StringUtils.isNotBlank(customer_name_like)) {
			entity.getMap().put("customer_name_like", customer_name_like);
		}
		if (StringUtils.isNotBlank(model_like)) {
			entity.getMap().put("model_like", model_like);
		}
		if (StringUtils.isNotBlank(store_name_like)) {
			entity.getMap().put("store_name_like", store_name_like);
		}
		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.parseInt(is_del));
		}
		if (StringUtils.isNotBlank(dept_id)) {
			entity.getMap().put("dept_id", dept_id);
		}
		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start + "00:00:00");
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_start + "23:59:59");
		}
		if (StringUtils.isNotBlank(begin_date_start)) {
			entity.getMap().put("begin_date_start", begin_date_start + "00:00:00");
		}
		if (StringUtils.isNotBlank(begin_date_end)) {
			entity.getMap().put("begin_date_end", begin_date_end + "23:59:59");
		}

		if (StringUtils.isNotBlank(brand_id)) {
			entity.setBrand_id(Long.valueOf(brand_id));
		}
		if (StringUtils.isNotBlank(store_id)) {
			entity.setStore_id(Long.valueOf(store_id));
		}

		Long recordCount = getFacade().getKonkaFightActivityManagerService().getKonkaFightActivityManagerMainCount(
				entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaFightActivityManager> entityList = getFacade().getKonkaFightActivityManagerService()
				.getKonkaFightActivityManagerMainPaginatedList(entity);

		List<KonkaMobilePd> pdList = GetListJP();
		request.setAttribute("pdList", pdList);

		// request.setAttribute("deptList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));
		request.setAttribute("entityList", entityList);
		request.setAttribute("mod_id", mod_id);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)) {
			return null;
		} else {
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		List<KonkaR3Store> storeList = getShop(userInfo);
		request.setAttribute("storeList", storeList);
		List<KonkaMobilePd> pdList = GetListJP();
		request.setAttribute("pdList", pdList);

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)) {
			return null;
		} else {
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaFightActivityManager entity = new KonkaFightActivityManager();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		} else {
			super.renderJavaScript(response, "alert('参数不正确');history.back();");
			return null;
		}
		entity = getFacade().getKonkaFightActivityManagerService().getKonkaFightActivityManager(entity);
		super.copyProperties(form, entity);

		List<KonkaR3Store> storeList = getShop(userInfo);
		request.setAttribute("storeList", storeList);

		List<KonkaMobilePd> pdList = GetListJP();
		request.setAttribute("pdList", pdList);
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		if (StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)) {
			return null;
		} else {
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaFightActivityManager entity = new KonkaFightActivityManager();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaFightActivityManagerService().getKonkaFightActivityManager(entity);

		if (entity != null) {
			if (entity.getStore_id() != null) {
				KonkaR3Store ks = new KonkaR3Store();
				ks.setStore_id(entity.getStore_id());
				ks = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(ks);
				if (ks != null) {
					dynaBean.set("store_name", ks.getStore_name());
				}
			}

			if (entity.getBrand_id() != null) {
				KonkaMobilePd km = new KonkaMobilePd();
				km.setBrand_id(entity.getBrand_id());
				List<KonkaMobilePd> kmList = super.getFacade().getKonkaMobilePdService().getKonkaMobilePdBrandList(km);
				if (kmList != null && kmList.size() > 0) {
					dynaBean.set("brand_name", kmList.get(0).getBrand_name());
				}

			}

		}

		super.copyProperties(form, entity);
		return mapping.findForward("view");
	}

	public ActionForward test(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ActionForward("/KonkaFightActivityManager/hello.jsp");
	}

	public ActionForward ajaxGetName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String name = (String) dynaBean.get("name");

		PeProdUser entity = new PeProdUser();
		if (StringUtils.isNotBlank(name)) {
			entity.getMap().put("name_like", name);
		}

		entity.getRow().setFirst(0);
		entity.getRow().setCount(10);

		// 查询
		List<PeProdUser> entityList = super.getFacade().getPeProdUserService().getPeProdUserList(entity);

		List<PeProdUser> entityList1 = new ArrayList<PeProdUser>();

		for (PeProdUser PeProdUser : entityList) {
			PeProdUser kk = new PeProdUser();
			kk.setId(PeProdUser.getId());
			kk.setUser_name(PeProdUser.getUser_name());
			entityList1.add(kk);
		}

		JSONArray jsonArray = JSONArray.fromObject(entityList1);

		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		PrintWriter out = response.getWriter();
		String json = "";
		if (entityList.size() == 0) {
			json = "{" + "\"state\":" + "-1}";
		} else {
			json = "{" + "\"result\":" + "[" + jsonArray.toString().substring(start + 1, end + 1) + "]," + "\"state\":"
					+ "1}";
		}
		out.print(json);
		out.flush();
		out.close();
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		if (StringUtils.isEmpty(inuser_id) || StringUtils.isEmpty(inuserpass)) {
			return null;
		} else {
			request.setAttribute("user_id", inuser_id);
			request.setAttribute("userpass", inuserpass);
		}
		PeProdUser userInfo = checkUserid(inuser_id, inuserpass,android_version,ios_version);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaFightActivityManager entity = new KonkaFightActivityManager();

		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(id)) {
			// 修改
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaFightActivityManagerService().modifyKonkaFightActivityManager(entity);
			saveMessage(request, "entity.updated");
		} else {

			// 添加方法
			entity.setAdd_user(userInfo.getId());
			entity.setAdd_date(new Date());
			entity.setIs_del(0);
			entity.setAdd_user_name(userInfo.getUser_name());
			getFacade().getKonkaFightActivityManagerService().createKonkaFightActivityManager(entity);
			saveMessage(request, "entity.inserted");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&user_id=" + inuser_id);
		pathBuffer.append("&userpass=" + inuserpass);
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	private List<KonkaMobilePd> GetListJP() {
		KonkaMobilePd t1 = new KonkaMobilePd();
		List<KonkaMobilePd> brandList = super.getFacade().getKonkaMobilePdService().getKonkaMobilePdBrandList(t1);
		return brandList;
	}

	private List<KonkaR3Store> getShop(PeProdUser userInfo) {
		KonkaR3Store entity = new KonkaR3Store();

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
				break;
			// return null;
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

}
