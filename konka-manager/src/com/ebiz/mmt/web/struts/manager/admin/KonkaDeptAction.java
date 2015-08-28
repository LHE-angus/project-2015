package com.ebiz.mmt.web.struts.manager.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

public class KonkaDeptAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);
		request.getSession().setAttribute("listFlg", "0");
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String id = (String) dynaBean.get("id"); // 部门ID
		String bj = (String) dynaBean.get("bj"); // 编辑

		KonkaDept entity = new KonkaDept();
		entity.setDept_id(Long.valueOf(id));
		// 获取部门信息
		entity = getFacade().getKonkaDeptService().getKonkaDept(entity);

		dynaBean.set("par_dept_name", entity.getDept_name());
		dynaBean.set("par_dept_id", entity.getDept_id());

		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// 获取登录用户 角色
		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		// 部门信息操作权限
		// 默认无权限
		dynaBean.set("operate_type", 0);
		if (StringUtils.isNotBlank(id)) {
			Long id1 = Long.valueOf(id);
			if (peRoleUser.getRole_id() <= 30) {
				if (id1 >= userInfo.getDept_id()) {
					dynaBean.set("operate_type", 1);
				}
			}
		}

		BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
		baseProvinceListFour.setDel_mark(0);
		baseProvinceListFour.setPar_index(0l);
		List<BaseProvinceListFour> provinceList = getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourList(baseProvinceListFour);
		request.setAttribute("provinceList", provinceList);

		// 部门领导
		PeProdUser konkaUser = new PeProdUser();
		konkaUser.setDept_id(Long.valueOf(id));
		konkaUser.setIs_del(0);
		konkaUser.getMap().put("not_admin", "true");
		List<PeProdUser> list = getFacade().getPeProdUserService().getKonkaUserListWithDeptNameForLeaderResultList(
				konkaUser);
		request.setAttribute("leaderList", list);

		KonkaDept konka_dept = new KonkaDept();
		// konka_dept.getMap().put("dept_id", "0");
		konka_dept.getMap().put("dept_id", id);
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptListWithTreeNameForProdUser(konka_dept);
		request.setAttribute("peDeptList", deptInfoList);

		dynaBean.set("bj", bj);
		dynaBean.set("id", id);
		dynaBean.set("add", "add");
		request.setAttribute("queryString", super.serialize(request, "dept_id", "method"));
		return mapping.findForward("input");
	}

	// 显示list页面后,会直接执行
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String dept_id = (String) dynaBean.get("id");
		String bj = (String) dynaBean.get("bj");
		String listFlg = (String) dynaBean.get("listFlg");
		String contain_subdepts = (String) dynaBean.get("contain_subdepts");

		if (StringUtils.isBlank(bj)) {
			bj = null;
		}
		if (listFlg == null) {
			listFlg = (String) request.getSession().getAttribute("listFlg");
		} else {
			request.getSession().setAttribute("listFlg", listFlg);
		}

		// 获取登录用户 企业信息
		PeProdUser userInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		// 获取登录用户 角色
		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		// 部门信息操作权限
		// 默认无权限
		dynaBean.set("operate_type", 0);
		if (StringUtils.isNotBlank(dept_id)) {
			Long id = Long.valueOf(dept_id);
			if (peRoleUser.getRole_id() <= 30) {
				if (id >= userInfo.getDept_id()) {
					dynaBean.set("operate_type", 1);
				}
			}
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
		boolean role_id_eq_10 = false;
		for (PeRoleUser __peRoleUser : peRoleUserList) {
			if (__peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
				break;
			}
		}
		if (userInfo.getDept_id() > 0L // 非 根部门（多媒体事业本部 dept_id=0） 人员
				&& "0".equals(dept_id) // 访问 根部门（多媒体事业本部 dept_id=0）
				&& !role_id_eq_10 // 又不是系统管理员
		) {
			// 无法访问
			return null;
		}

		// 当前部门ID
		KonkaDept entity = new KonkaDept();
		entity.setDept_id(Long.valueOf(dept_id));
		entity = getFacade().getKonkaDeptService().getKonkaDept(entity);
		super.copyProperties(form, entity);

		BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
		baseProvinceListFour.setDel_mark(0);
		baseProvinceListFour.setPar_index(0l);
		List<BaseProvinceListFour> provinceList = getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourList(baseProvinceListFour);
		request.setAttribute("provinceList", provinceList);

		// 部门领导
		PeProdUser PeProdUser = new PeProdUser();
		PeProdUser.setDept_id(Long.valueOf(dept_id));
		PeProdUser.setIs_del(0);
		PeProdUser.getMap().put("not_admin", "true");
		List<PeProdUser> list = getFacade().getPeProdUserService().getKonkaUserListWithDeptNameForLeaderResultList(
				PeProdUser);
		request.setAttribute("leaderList", list);

		if ("0".equals(listFlg) && bj == null) {
			// 部门人员列表
			PeProdUser kUser = new PeProdUser();
			kUser.setIs_del(0);
			if (userInfo.getPar_dept_id() != null) {
				kUser.setProd_entp_id(userInfo.getProd_entp_id());
			}

			if (StringUtils.isNotBlank(contain_subdepts)) {
				Integer a = Integer.valueOf(dept_id);
				if (userInfo.getDept_id() != null && a < userInfo.getDept_id()) {
					kUser.getMap().put("dept_id", userInfo.getDept_id());
				} else {
					kUser.getMap().put("dept_id", dept_id);
				}
			} else {
				kUser.setDept_id(Long.valueOf(dept_id));
			}

			// 采用分页
			kUser.getMap().put("pager_true", true);

			// 分公司总数
			Long recordCount = super.getFacade().getPeProdUserService()
					.getKonkaUserListWithDeptNameForResultCount(kUser);
			pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
			kUser.getRow().setFirst(pager.getFirstRow());
			kUser.getRow().setCount(pager.getRowCount());

			List<PeProdUser> deptUserlist = getFacade().getPeProdUserService()
					.getKonkaUserListWithDeptNameForResultList(kUser);
			for (PeProdUser t : deptUserlist) {
				PeRoleUser peRoleUser1 = new PeRoleUser();
				peRoleUser1.setUser_id(t.getId());
				List<PeRoleUser> peRoleUser1List = super.getFacade().getPeRoleUserService()
						.getPeRoleUserList(peRoleUser1);

				String[] role_name = new String[peRoleUser1List.size()];
				int i = 0;
				for (PeRoleUser roleuser : peRoleUser1List) {
					role_name[i] = (String) roleuser.getMap().get("role_name");
					i++;
				}

				t.getMap().put("role_name", StringUtils.join(role_name, ','));
			}

			request.setAttribute("deptUserlist", deptUserlist);
		} else if ("1".equals(listFlg) && bj == null) {
			// 部门网点取得
			List<KonkaR3Shop> entityList = super.getKonkaR3MMTShopPaginatedList(mapping, form, request, response,
					Long.valueOf(dept_id));
			super.setBranchNameForR3ShopListByKonkaR3ShopList(entityList);
			request.setAttribute("entityList", entityList);
		}
		dynaBean.set("listFlg", listFlg);

		// 获取父部门
		KonkaDept parent_dept = new KonkaDept();
		int max_dlevel = super.getMaxDLevel(userInfo.getId()); // 获取当前用户的最高可视部门级别
		if (max_dlevel == 9) {
			parent_dept.getMap().put("dept_id", 0);
		} else {
			parent_dept.getMap().put("dept_id", userInfo.getDept_id());
		}
		List<KonkaDept> deptInfoList = super.getFacade().getKonkaDeptService()
				.getKonkaDeptListWithTreeNameForProdUser(parent_dept);
		request.setAttribute("peDeptList", deptInfoList);
		dynaBean.set("par_dept_id", entity.getPar_id());
		dynaBean.set("edit", "edit");
		dynaBean.set("bj", bj);
		dynaBean.set("mod_id", dynaBean.get("mod_id"));

		// 取得当前部门下已经停用的子部门

		KonkaDept delete_dept = new KonkaDept();
		if (dept_id == null || dept_id.equals("")) {
			delete_dept.setPar_id(0L);
		} else {
			delete_dept.setPar_id(Long.valueOf(dept_id));
		}
		delete_dept.setIs_del(1);
		List<KonkaDept> deleteDeptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(delete_dept);
		request.setAttribute("deleteDeptList", deleteDeptList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String add = (String) dynaBean.get("add");
		String edit = (String) dynaBean.get("edit");
		String id_names[] = request.getParameterValues("id_name");

		KonkaDept entity = new KonkaDept();
		// 区域编号，区域名处理
		int len = 0;
		if (id_names != null)
			len = id_names.length;
		String[] area_ids = new String[len];
		String[] areas_names = new String[len];
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				area_ids[i] = id_names[i].split(",")[0];
				areas_names[i] = id_names[i].split(",")[1];
			}
			entity.setM_areas_ids(StringUtils.join(area_ids, ","));
			entity.setM_areas_names(StringUtils.join(areas_names, ","));
		} else {
			entity.setM_areas_ids("");
			entity.setM_areas_names("");
		}

		super.copyProperties(entity, form);
		dynaBean.set("id", "" + entity.getDept_id());
		dynaBean.set("mod_id", "" + dynaBean.get("mod_id"));

		// 获取登录用户 企业信息
		PeProdUser SxUserInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (null != entity.getP_index()) {
			Long p_index = entity.getP_index();
			BaseProvinceListFour b = new BaseProvinceListFour();
			b.setP_index(p_index);
			b.setDel_mark(0);
			b = getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(b);
			entity.setP_name(b.getP_name());
		}

		// 新增部门
		if (StringUtils.isNotBlank(add)) {
			entity.setAdd_user_id(SxUserInfo.getId());
			entity.setAdd_date(new Date());
			entity.setIs_del(0);
			super.getFacade().getKonkaDeptService().createKonkaDept(entity);
			super.saveMessage(request, "entity.inserted");
			request.setAttribute("add_dept", "add_dept");

		} else if (StringUtils.isNotBlank(edit)) {

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String time = sf.format(date);
			String start_time = time + " 08:00:00";
			String end_time = time + " 09:59:59";

			SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sf2.format(date);

			Calendar cc = Calendar.getInstance();
			boolean is_time = false;
			try {
				cc.setTime(sf2.parse(now));
				Long now_time = cc.getTime().getTime();
				cc.setTime(sf2.parse(start_time));
				Long s_time = cc.getTime().getTime();
				cc.setTime(sf2.parse(end_time));
				Long e_time = cc.getTime().getTime();
				if (now_time.longValue() > s_time.longValue() && now_time.longValue() < e_time.longValue()) {
					is_time = true;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!is_time) {
				//不在规定时间内
				request.setAttribute("out_time", true);
				return edit(mapping, form, request, response);
			}

			// 修改部门
			// 禁用需要判断,如果当前节点要禁用,那么子节点都要被禁用掉
			if (entity.getIs_del() == 1) {
				KonkaDept k = new KonkaDept();
				k.setIs_del(0);
				k.getMap().put("user_dept_Id", entity.getDept_id());
				List<KonkaDept> olist = super.getFacade().getKonkaDeptService().getKonkaDeptListForUser(k);
				boolean top = false;
				for (KonkaDept o : olist) {
					if (o.getDept_id() == 0) {
						top = true;
						break;
					}
				}
				if (top) {
					// 提示顶级部门不能被禁用
					saveDirectlyError(request, "顶级部门不能被禁用!");
					return edit(mapping, form, request, response);
				}

				boolean xflag = false;
				for (KonkaDept o : olist) {
					PeProdUser user = new PeProdUser();
					user.setDept_id(o.getDept_id());
					user.setIs_del(0);
					long size = super.getFacade().getPeProdUserService().getPeProdUserCount(user);
					if (size > 0) {
						xflag = true;
						break;
					}
				}
				if (xflag) {
					// 提示顶级部门不能被禁用
					saveDirectlyError(request, "当前部门或子部门存在用户,不能禁用此部门!");
					return edit(mapping, form, request, response);
				}

			}
			super.getFacade().getKonkaDeptService().modifyKonkaDept(entity);
			super.saveMessage(request, "entity.updated");
		}

		return edit(mapping, form, request, response);
	}

	// 显示list页面后,会直接执行
	public ActionForward showDeptInfoTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_types_Y = (String) dynaBean.get("dept_types_Y");

		// 获取登录用户信息
		PeProdUser SxUserInfo = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaDept entity = new KonkaDept();
		entity.getMap().put("dept_id", SxUserInfo.getDept_id());
		entity.getMap().put("dept_types_Y", dept_types_Y);
		//
		List<KonkaDept> deptInfoList = getFacade().getKonkaDeptService()
				.getKonkaDeptTreeNameByUserForResultList(entity);

		// 20150330 modify by zhou ,左边部门树过滤已经停用的部门
		List<KonkaDept> noDeleteDeptInfoList = new ArrayList<KonkaDept>();
		for (KonkaDept k : deptInfoList) {
			if (k.getIs_del() == 0) {
				noDeleteDeptInfoList.add(k);
			}
		}
		String treeNodes = getTreeNodesFromDeptInfoAndModIdList(noDeleteDeptInfoList, mod_id);
		request.setAttribute("treeNodes", treeNodes);

		return new ActionForward("/../manager/admin/KonkaDept/deptInfoTree.jsp");
	}

	public ActionForward showNavi(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		return new ActionForward("/../manager/admin/KonkaDept/navi.jsp");
	}

	public String getTreeNodesFromDeptInfoList(List<KonkaDept> deptInfoList) {

		StringBuffer sb = new StringBuffer();

		for (KonkaDept konkaDept : deptInfoList) {

			String _id = String.valueOf(konkaDept.getDept_id());
			String _par_id = String.valueOf(konkaDept.getPar_id());

			if ("0".equals(_par_id)) {
				if (StringUtils.equals(_id, _par_id)) {
					_par_id = "-1";
				}
			}
			String _text = StringUtils.replace(konkaDept.getDept_name(), ":", "&#58;");
			if (StringUtils.isEmpty(_text)) {
				continue;
			}
			String _hint = _text;
			// String _url = StringUtils.replace(sysModule.getMod_url(), ":",
			// "&#58;");

			sb.append("\ntree.nodes[\"").append(_par_id).append("_").append(_id).append("\"]=\"");
			sb.append("text:").append(_text).append(";");
			if (_hint.length() > 0) {
				if (null != konkaDept.getDept_desc()) {
					sb.append("hint:").append(konkaDept.getDept_desc()).append(";");
				} else {
					sb.append("hint:").append(_hint).append(";");
				}
			}

			sb.append("url:").append("KonkaDept.do").append(";");

			sb.append("data:").append("method=edit");
			sb.append("&par_id=").append(_par_id);
			sb.append("&id=").append(_id).append(";");

			sb.append("\";");
		}
		return sb.toString();
	}

	/**
	 * @author WangKunLin 2014-11-15 加入了mod_id,方便是用添加 修改等权限控制
	 * @param deptInfoList
	 * @param mod_id
	 * @return String
	 */
	private String getTreeNodesFromDeptInfoAndModIdList(List<KonkaDept> deptInfoList, String mod_id) {

		StringBuffer sb = new StringBuffer();

		if (deptInfoList != null && deptInfoList.size() > 0) {
			for (KonkaDept konkaDept : deptInfoList) {

				String _id = String.valueOf(konkaDept.getDept_id());
				String _par_id = String.valueOf(konkaDept.getPar_id());

				if ("0".equals(_par_id)) {
					if (StringUtils.equals(_id, _par_id)) {
						_par_id = "-1";
					}
				}
				String _text = StringUtils.replace(konkaDept.getDept_name(), ":", "&#58;");
				if (StringUtils.isEmpty(_text)) {
					continue;
				}
				if (konkaDept.getIs_del() == 1) {
					_text = _text + "(停用)";
				}
				String _hint = _text;
				sb.append("\ntree.nodes[\"").append(_par_id).append("_").append(_id).append("\"]=\"");
				sb.append("text:").append(_text).append(";");
				if (_hint.length() > 0) {
					if (null != konkaDept.getDept_desc()) {
						sb.append("hint:").append(konkaDept.getDept_desc()).append(";");
					} else {
						sb.append("hint:").append(_hint).append(";");
					}
				}

				sb.append("url:").append("KonkaDept.do").append(";");
				sb.append("data:").append("method=edit");
				sb.append("&mod_id=").append(mod_id);
				sb.append("&par_id=").append(_par_id);
				sb.append("&id=").append(_id).append(";");
				sb.append("\";");
			}
		}

		return sb.toString();
	}

	public ActionForward validateDeptName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String dept_name = (String) dynaBean.get("dept_name");
		String dept_id = (String) dynaBean.get("dept_id");
		KonkaDept entity = new KonkaDept();
		String isExist = "null";

		if (StringUtils.isNotBlank(dept_name)) {
			entity.setDept_name(dept_name);
			entity.getMap().put("map.not_konka", dept_id);
			if (null == super.getFacade().getKonkaDeptService().getKonkaDept(entity)) {
				isExist = String.valueOf("0");
			} else {
				isExist = String.valueOf("1");
			}
		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;

	}

	public ActionForward validateDeptSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_sn = (String) dynaBean.get("dept_sn");
		KonkaDept entity = new KonkaDept();
		String isExist = "null";
		if (StringUtils.isNotBlank(dept_sn)) {
			entity.setDept_sn(dept_sn);
			entity = super.getFacade().getKonkaDeptService().getKonkaDept(entity);
			if (null == entity) {
				isExist = String.valueOf("0");
			} else {
				isExist = String.valueOf("1");
			}
		}
		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("edit");
		}

		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(entity);

		if (null == entity) {
			saveError(request, "errors.long", new String[] { id });
			return mapping.findForward("edit");
		}
		entity.setQueryString(super.serialize(request, "id", "mod_id"));
		request.setAttribute("entity", entity);

		return mapping.findForward("view");
	}

	public ActionForward getBaseProvinecList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String province = (String) dynaBean.get("province");
		BaseProvinceList entity = new BaseProvinceList();
		entity.setDel_mark(new Short("0"));

		if (GenericValidator.isLong(province)) {
			entity.setP_index(new Long(province));
		}

		super.render(response, "", "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward changeKonkaDeptStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");// 左边树点击的那一条记录ID

		String par_id = (String) dynaBean.get("par_id");
		String is_del = (String) dynaBean.get("is_del");
		if (dept_id == null) {
			return null;
		}

		KonkaDept entity = new KonkaDept();
		entity.setPar_id(Long.valueOf(par_id));
		entity.setDept_id(Long.valueOf(dept_id));
		entity.setIs_del(Integer.valueOf(is_del));
		super.getFacade().getKonkaDeptService().modifyKonkaDept(entity);
		super.renderJavaScript(response, "alert('操作成功!');history.back();");
		return null;

	}

	public ActionForward listForDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String selects = (String) dynaBean.get("selects");

		KonkaDept entity = new KonkaDept();
		entity.setDept_type(3);
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		if (peProdUser.getDept_id() != 0) {// 非管理员
			entity.setDept_id(peProdUser.getDept_id());
		}

		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		request.setAttribute("entityList", entityList);

		if (StringUtils.isNotBlank(selects)) {
			List<KonkaDept> konkaDeptList = new ArrayList<KonkaDept>();
			String[] arr = selects.split(",");
			for (int i = 0; i < arr.length; i++) {
				KonkaDept konkadept = new KonkaDept();
				konkadept.setDept_id(Long.valueOf(arr[i]));
				konkaDeptList.add(konkadept);
			}
			request.setAttribute("konkaDeptList", konkaDeptList);
		}

		return new ActionForward("/../manager/admin/KonkaDept/dept_list.jsp");
	}

}
