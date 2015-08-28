package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBaseCardApply;
import com.ebiz.mmt.domain.EcCust;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-23
 */
public class EcBaseCardApplyAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String apply_real_name_like = (String) dynaBean.get("apply_real_name_like");
		String apply_user_name_like = (String) dynaBean.get("apply_user_name_like");
		String apply_dept = (String) dynaBean.get("apply_dept");
		String info_state = (String) dynaBean.get("info_state");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
						|| pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		EcBaseCardApply entity = new EcBaseCardApply();
		if (zb) {
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);

		} else if (!zb && fgs) {
			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
			if (deptList != null && deptList.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids.add(ecGroup.getId().toString());
				}
				dynaBean.set("group_id", StringUtils.join(ids, ","));
				dynaBean.set("is_binding", true);
				entity.getMap().put("is_fgs", ids);
			} else {
				entity.getMap().put("is_fgs", new String[] { "999999" });
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限查看价格！');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(apply_real_name_like)) {
			entity.getMap().put("apply_real_name_like", apply_real_name_like);

		}
		if (StringUtils.isNotBlank(apply_user_name_like)) {
			entity.getMap().put("apply_user_name_like", apply_user_name_like);

		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}

		if (StringUtils.isNotBlank(info_state)) {
			entity.setInfo_state(Integer.valueOf(info_state));
		}
		if (StringUtils.isNotBlank(apply_dept)) {
			entity.setApply_dept(apply_dept);
		}

		Long recordCount = super.getFacade().getEcBaseCardApplyService().getEcBaseCardApplyCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcBaseCardApply> entityList = super.getFacade().getEcBaseCardApplyService()
				.getEcBaseCardApplyPaginatedList(entity);
		for (EcBaseCardApply ecBaseCardApply : entityList) {
			if (ecBaseCardApply.getApply_dept() != null) {
				EcGroup eg = new EcGroup();
				eg.setId(Long.valueOf(ecBaseCardApply.getApply_dept()));
				eg = super.getFacade().getEcGroupService().getEcGroup(eg);
				if (eg != null) {
					ecBaseCardApply.getMap().put("group_name", eg.getGroup_name());
				}
			}
		}

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String info_state = (String) dynaBean.get("info_state");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcBaseCardApply eca = new EcBaseCardApply();
		eca.setId(Long.valueOf(id));
		eca = super.getFacade().getEcBaseCardApplyService().getEcBaseCardApply(eca);

		String MM = "";
		if (null != eca.getApply_dept()) {
			EcGroup ef = new EcGroup();
			ef.setId(Long.valueOf(eca.getApply_dept()));
			ef = super.getFacade().getEcGroupService().getEcGroup(ef);
			int dept_id = 0;
			if (ef != null && ef.getLink_dept_id() != null) {
				dept_id = ef.getLink_dept_id().intValue();
			}
			switch (dept_id) {
			case 0:
				MM = "DMT";
				break;
			case 2:
				MM = "BJ";
				break;
			case 3:
				MM = "CC";
				break;
			case 4:
				MM = "CS";
				break;
			case 5:
				MM = "CD";
				break;
			case 6:
				MM = "DG";
				break;
			case 7:
				MM = "FZ";
				break;
			case 8:
				MM = "GZ";
				break;
			case 9:
				MM = "GY";
				break;
			case 10:
				MM = "HEB";
				break;
			case 11:
				MM = "HK";
				break;
			case 12:
				MM = "HZ";
				break;
			case 13:
				MM = "HF";
				break;
			case 14:
				MM = "HY";
				break;
			case 15:
				MM = "HHHT";
				break;
			case 17:
				MM = "JZ";
				break;
			case 18:
				MM = "JZ";
				break;
			case 19:
				MM = "KM";
				break;
			case 20:
				MM = "LZ";
				break;
			case 21:
				MM = "NJ";
				break;
			case 22:
				MM = "NC";
				break;
			case 23:
				MM = "NJ";
				break;
			case 24:
				MM = "NN";
				break;
			case 25:
				MM = "NT";
				break;
			case 26:
				MM = "NY";
				break;
			case 27:
				MM = "SH";
				break;
			case 28:
				MM = "SZ";
				break;
			case 29:
				MM = "SY";
				break;
			case 30:
				MM = "SJZ";
				break;
			case 31:
				MM = "SZ";
				break;
			case 32:
				MM = "TY";
				break;
			case 33:
				MM = "TJ";
				break;
			case 34:
				MM = "WZ";
				break;
			case 35:
				MM = "WLMQ";
				break;
			case 36:
				MM = "WH";
				break;
			case 37:
				MM = "WH";
				break;
			case 38:
				MM = "XA";
				break;
			case 39:
				MM = "XM";
				break;
			case 40:
				MM = "XZ";
				break;
			case 41:
				MM = "ZZ";
				break;
			case 42:
				MM = "CQ";
				break;
			case 48:
				MM = "BD";
				break;
			case 673:
				MM = "CS";
				break;
			case 798:
				MM = "TS";
				break;
			case 799:
				MM = "DL";
				break;
			case 815:
				MM = "DM";
				break;
			case 930:
				MM = "FS";
				break;
			case 972:
				MM = "NC";
				break;
			case 979:
				MM = "HH";
				break;
			case 1074:
				MM = "HD";
				break;
			case 1175:
				MM = "JN";
				break;
			case 1234:
				MM = "LZ";
				break;
			case 1266:
				MM = "QQHE";
				break;
			case 1267:
				MM = "NB";
				break;
			case 1268:
				MM = "QD";
				break;
			case 1269:
				MM = "ZS";
				break;
			case 1766:
				MM = "JN";
				break;
			case 1970:
				MM = "DZSW";
				break;
			case 2034:
				MM = "MM";
				break;
			case 2035:
				MM = "ZB";
				break;
			case 2036:
				MM = "LY";
				break;
			case 2037:
				MM = "MDJ";
				break;
			default:
				MM = "KK";
				break;
			}
		}

		EcBaseCardApply entity = new EcBaseCardApply();
		super.copyProperties(entity, form);
		entity.setDeal_date(new Date());
		entity.setDeal_user(user.getUser_name());

		List<String> usernameList = new ArrayList<String>();
		List<String> custcodeList = new ArrayList<String>();
		List<String> custnameList = new ArrayList<String>();
		if (StringUtils.isEmpty(id)) {
			super.getFacade().getEcBaseCardApplyService().createEcBaseCardApply(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setApply_dept(eca.getApply_dept());
			entity.setApply_user_name(eca.getApply_user_name());
			if (info_state.equals("1")) {
				int app_num = eca.getApply_num().intValue();
				for (int i = 0; i < app_num; i++) {
					String user_name = GetUserName(MM);
					usernameList.add(user_name);
					String cust_code = GetCustCode(MM);
					custcodeList.add(cust_code);
					String cust_name = GetCustName(MM);
					custnameList.add(cust_name);
				}
			}

			entity.getMap().put("user_id", user.getId());
			// super.getFacade().getEcBaseCardApplyService().modifyEcBaseCardApply(entity,
			// cardNoList);
			super.getFacade().getEcBaseCardApplyService()
					.modifyEcBaseCardApply(entity, usernameList, custcodeList, custnameList);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcBaseCardApply entity = new EcBaseCardApply();
		entity.setId(Long.valueOf(id));
		super.getFacade().getEcBaseCardApplyService().removeEcBaseCardApply(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		EcBaseCardApply entity = new EcBaseCardApply();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcBaseCardApplyService().getEcBaseCardApply(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));

		super.copyProperties(form, entity);

		EcGroup kd = new EcGroup();
		kd.setId(Long.valueOf(entity.getApply_dept()));
		kd = super.getFacade().getEcGroupService().getEcGroup(kd);
		if (null != kd) {
			dynaBean.set("group_name", kd.getGroup_name());
		}

		return mapping.findForward("input");
	}

	public static List<String> GetRandomNumber() {
		// 使用SET以此保证写入的数据不重复
		List<String> set = new ArrayList<String>();
		String[] arg = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "x", "0", "1", "2", "3", "3", "4", "5", "6", "7", "8", "9" };
		// 随机数
		Random random = new Random();
		int ss = 0;
		for (int i = 0; i < arg.length; i++) {
			while (set.size() < 8) {
				// nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）
				// 和指定值（不包括）之间均匀分布的 int 值。
				ss = random.nextInt(arg.length);
				set.add(arg[ss]);
			}
		}

		return set;
	}

	public String GetUserName(String MM) {
		EcBaseCardApply entity = new EcBaseCardApply();
		Long temp = super.getFacade().getEcBaseCardApplyService().getApplyCardNoCount(entity);
		String tt = MM + "NO" + temp.toString();

		EcUser ec = new EcUser();
		List<EcUser> entityList = super.getFacade().getEcUserService().getEcUserList(ec);

		List<String> usernameList = new ArrayList<String>();
		if (entityList != null && entityList.size() > 0) {
			for (EcUser ee : entityList) {
				usernameList.add(ee.getUser_name());
			}
		}

		String ulist = StringUtils.join(usernameList, ",");
		if (ulist != null && !ulist.equals("")) {
			if (ulist.contains(tt)) {
				GetUserName(MM);
			}
		}

		return tt;
	}

	public String GetCustCode(String MM) {
		EcBaseCardApply entity = new EcBaseCardApply();
		Long temp = super.getFacade().getEcBaseCardApplyService().getApplyCardNoCount(entity);
		String tt = MM + "CC" + temp.toString();

		EcCust ec = new EcCust();
		List<EcCust> entityList = super.getFacade().getEcCustService().getEcCustList(ec);

		List<String> usernameList = new ArrayList<String>();
		if (entityList != null && entityList.size() > 0) {
			for (EcCust ee : entityList) {
				usernameList.add(ee.getCust_code());
			}
		}

		String ulist = StringUtils.join(usernameList, ",");
		if (ulist != null && !ulist.equals("")) {
			if (ulist.contains(tt)) {
				GetUserName(MM);
			}
		}

		return tt;
	}

	public String GetCustName(String MM) {
		EcBaseCardApply entity = new EcBaseCardApply();
		Long temp = super.getFacade().getEcBaseCardApplyService().getApplyCardNoCount(entity);
		String tt = MM + "CM" + temp.toString();
		return tt;
	}

}
