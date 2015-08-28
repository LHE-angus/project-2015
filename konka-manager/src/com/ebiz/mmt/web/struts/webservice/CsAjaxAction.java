package com.ebiz.mmt.web.struts.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.ImportDataTypes;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaXxStdStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Liu,ZhiXiang
 * @version 2013-10-08
 * @desc 手机页面使用
 */
public class CsAjaxAction extends BaseAction {

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
		if (!"-1".equals(par_index)) {
			baseProvince.setPar_index(new Long(par_index));
		}
		baseProvince.setDel_mark(new Short("0"));

		// 分公司：管辖区域----add by douqr
		String many_p_index = (String) dynaBean.get("many_p_index"); // 管辖区域
		if (StringUtils.isNotBlank(many_p_index)) {
			baseProvince.getMap().put("many_p_index", many_p_index);
		}

		// 分公司及分公司以下的管辖区域不能重合
		String is_managered = (String) dynaBean.get("is_managered");
		if (StringUtils.isNotBlank(is_managered)) {
			baseProvince.getMap().put("is_managered", is_managered);
		}

		List<BaseProvinceList> baseProvinceList = super.getFacade().getBaseProvinceListService()
				.getBaseProvinceListList(baseProvince);

		List<String> dataList = new ArrayList<String>();
		for (BaseProvinceList entity : baseProvinceList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.getP_name(), "\",\"",
					String.valueOf(entity.getP_index()), "\"]" }));
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}

	/**
	 * @author Li,Yuan
	 * @version 2012-01-12
	 */
	public ActionForward getBaseFactoryAndStoreIdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String param = (String) dynaBean.get("param");

		Long dept_id = super.getSessionUserInfo(request).getDept_id();
		if (dept_id == null) {
			return null;
		}

		KonkaXxStdStore konkaXxStdStore = new KonkaXxStdStore();
		konkaXxStdStore.getMap().put("dept_id", dept_id);
		List<KonkaXxStdStore> konkaXxStdStoreList = new ArrayList<KonkaXxStdStore>();
		List<String> dataList = new ArrayList<String>();
		if ("0".equals(param)) {
			konkaXxStdStoreList = super.getFacade().getKonkaXxStdStoreService()
					.getKonkaXxFactoryIdList(konkaXxStdStore);
			for (KonkaXxStdStore entity : konkaXxStdStoreList) {
				dataList.add(StringUtils.join(new String[] { "[\"", entity.getFactory_id(), "\",\"",
						String.valueOf(entity.getFactory_id()), "\"]" }));
			}
		} else {
			konkaXxStdStore.setFactory_id(param);
			konkaXxStdStoreList = super.getFacade().getKonkaXxStdStoreService().getKonkaXxStoreIdList(konkaXxStdStore);
			for (KonkaXxStdStore entity : konkaXxStdStoreList) {
				dataList.add(StringUtils.join(new String[] { "[\"", entity.getStore_desc(), "\",\"",
						String.valueOf(entity.getStore_id()), "\"]" }));
			}
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
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

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_10_le_39 = false;
		boolean role_id_ge_40_le_49 = false;
		boolean role_id_ge_50_le_59 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() <= 39L) {
				role_id_ge_10_le_39 = true;
			}
			if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 49L) {
				role_id_ge_40_le_49 = true;
			}
			if (peRoleUser.getRole_id() >= 50L && peRoleUser.getRole_id() <= 59L) {
				role_id_ge_50_le_59 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		KonkaDept dept = new KonkaDept();
		dept.setDept_id(userInfo.getDept_id());
		dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

		if (role_id_ge_10_le_39) {
			entity.setPar_id(Long.valueOf(fgs_dept_id));
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (role_id_ge_40_le_49) {
			entity.setDept_id(userInfo.getDept_id());
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (role_id_ge_50_le_59) {
			KonkaDept _dept = this.getSuperiorForDeptType(userInfo.getDept_id(), 4);
			if (null != _dept && null != _dept.getDept_id()) {
				entity.setDept_id(_dept.getDept_id());
				entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			}
		}
		if (role_id_eq_60) {
			if (dept.getDept_type() == 4) {
				entity.setDept_id(userInfo.getDept_id());
				entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			}
			if (dept.getDept_type() == 5) {
				KonkaDept _dept = this.getSuperiorForDeptType(userInfo.getDept_id(), 4);
				if (null != _dept && null != _dept.getDept_id()) {
					entity.setDept_id(_dept.getDept_id());
					entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
				}
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

	/**
	 * 根据经营部或办事处的部门ID找出业务员
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getYwyUserListByDeptId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (!GenericValidator.isLong(dept_id)) {
			return null;
		}

		if (GenericValidator.isLong(pageCount)) {
			count = Integer.valueOf(pageCount);
		}

		PeProdUser entity = new PeProdUser();
		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (role_id_eq_60) {
			entity.setId(userInfo.getId());
		} else {
			entity.setDept_id(Long.valueOf(dept_id));
		}
		// entity.setDept_id(Long.valueOf(jyb_dept_id));
		// entity.getMap().put("dept_id_all_prod_user", dept_id);
		// }
		// by:wh 2011-12-02 经营部下级机构的人全部取，包括业务员和办事处管理员
		// entity.getMap().put("role_id", 60l);//职务（角色）60为业务员
		entity.getRow().setCount(count);
		List<PeProdUser> entityList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		for (PeProdUser t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getId()) + "\",");
			sb.append("\"name\":\""
					+ StringEscapeUtils.escapeJavaScript(t.getUser_name() + "[" + t.getReal_name() + "]") + "\"},");

		}
		sb.append("{\"end\":\"\"}]");

		log.info(sb.toString());
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * 根据分公司Id取经营部和办事处的数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJybDeptListByFgs(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
		entity.setPar_id(Long.valueOf(fgs_dept_id));
		List<KonkaDept> entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		for (KonkaDept t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * 根据经营部Id取办事处的数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBscDeptListByJyb(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		entity.setPar_id(Long.valueOf(jyb_dept_id));
		List<KonkaDept> entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		for (KonkaDept t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * 根据经营部或办事处的部门ID找出业务员
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getYwyUserListByDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (!GenericValidator.isLong(dept_id)) {
			return null;
		}

		if (GenericValidator.isLong(pageCount)) {
			count = Integer.valueOf(pageCount);
		}

		PeProdUser entity = new PeProdUser();

		entity.setDept_id(Long.valueOf(dept_id));
		// entity.setDept_id(Long.valueOf(jyb_dept_id));
		// entity.getMap().put("dept_id_all_prod_user", dept_id);
		// }
		// by:wh 2011-12-02 经营部下级机构的人全部取，包括业务员和办事处管理员
		// entity.getMap().put("role_id", 60l);//职务（角色）60为业务员
		entity.getRow().setCount(count);
		List<PeProdUser> entityList = getFacade().getPeProdUserService().getPeProdUserList(entity);
		for (PeProdUser t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getId()) + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getReal_name()) + "\"},");

		}
		sb.append("{\"end\":\"\"}]");

		log.info(sb.toString());
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * 根据登录用户所在部门的管辖区域列出省、市、县、镇
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBaseProvinceFourListByUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_index = (String) dynaBean.get("p_index");
		String p_index_join = (String) dynaBean.get("p_index_join");

		if (!GenericValidator.isLong(par_index)) {
			return null;
		}
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30_le_39 = false;
		boolean role_id_ge_40_le_59 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 39L) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 59L) {
				role_id_ge_40_le_59 = true;
			}
		}

		KonkaDept dept = new KonkaDept();

		BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
		baseProvinceFour.setPar_index(new Long(par_index));
		baseProvinceFour.setDel_mark(0);

		if (role_id_ge_30_le_39) {
			dept.setDept_id(ui.getDept_id());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			String index = dept.getM_areas_ids();
			if (index == null) {
				return null;
			}

			if (("0").equals(par_index)) {
				String[] indexes = index.split(",");
				String[] p_indexes = new String[indexes.length];
				for (int i = 0; i < indexes.length; i++) {
					p_indexes[i] = indexes[i].substring(0, 2) + "0000";
				}
				baseProvinceFour.getMap().put("many_p_index", StringUtils.join(p_indexes, ","));
			} else if (("0000").equals(par_index.substring(2, 6))) {
				String[] indexes = index.split(",");
				String[] p_indexes = new String[indexes.length];
				for (int i = 0; i < indexes.length; i++) {
					p_indexes[i] = indexes[i].substring(0, 4) + "00";
				}
				baseProvinceFour.getMap().put("many_p_index", StringUtils.join(p_indexes, ","));
			} else if (("00").equals(par_index.substring(4, 6))) {

				BaseProvinceListFour baseProvince = new BaseProvinceListFour();
				baseProvince.setPar_index(new Long(par_index));
				baseProvince.setDel_mark(0);
				baseProvince.getMap().put("many_p_index", index);

				List<BaseProvinceListFour> baseProvinceList = super.getFacade().getBaseProvinceListFourService()
						.getBaseProvinceListFourList(baseProvince);
				if (!baseProvinceList.isEmpty()) {
					baseProvinceFour.getMap().put("many_p_index", index);
				}
			}
		}
		if (role_id_ge_40_le_59) {

			dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
			String index = dept.getM_areas_ids();
			if (index == null) {
				return null;
			}

			if (("0").equals(par_index)) {
				String[] indexes = index.split(",");
				String[] p_indexes = new String[indexes.length];
				for (int i = 0; i < indexes.length; i++) {
					p_indexes[i] = indexes[i].substring(0, 2) + "0000";
				}
				baseProvinceFour.getMap().put("many_p_index", StringUtils.join(p_indexes, ","));
			} else if (("0000").equals(par_index.substring(2, 6))) {
				String[] indexes = index.split(",");
				String[] p_indexes = new String[indexes.length];
				for (int i = 0; i < indexes.length; i++) {
					p_indexes[i] = indexes[i].substring(0, 4) + "00";
				}
				baseProvinceFour.getMap().put("many_p_index", StringUtils.join(p_indexes, ","));
			} else if (("00").equals(par_index.substring(4, 6))) {
				BaseProvinceListFour baseProvince = new BaseProvinceListFour();
				baseProvince.setPar_index(new Long(par_index));
				baseProvince.setDel_mark(0);
				baseProvince.getMap().put("many_p_index", index);

				List<BaseProvinceListFour> baseProvinceList = super.getFacade().getBaseProvinceListFourService()
						.getBaseProvinceListFourList(baseProvince);
				if (!baseProvinceList.isEmpty()) {
					baseProvinceFour.getMap().put("many_p_index", index);
				}
			}
		}

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
	 * 根据类型取类别
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWlTypeListByWlIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String wl_index = (String) dynaBean.get("wl_index");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (!GenericValidator.isLong(wl_index)) {
			return null;
		}

		if (GenericValidator.isLong(pageCount)) {
			count = Integer.valueOf(pageCount);
		}
		KonkaMobileCategory entity = new KonkaMobileCategory();
		entity.setC_type(new Integer(wl_index));
		entity.setIs_type(1);
		entity.setIs_del(0);
		entity.getRow().setCount(count);
		List<KonkaMobileCategory> entityList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(entity);

		for (KonkaMobileCategory t : entityList) {
			sb.append("{\"id\":\"" + String.valueOf(t.getC_index()) + "\",");
			sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getC_name()) + "\"},");

		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());

		return null;
	}

	/**
	 * 根据仓库类型选择仓库所属部门
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStorageAreacom(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String storage_type = (String) dynaBean.get("storage_type");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (!GenericValidator.isLong(storage_type)) {
			return null;
		}

		if (GenericValidator.isLong(pageCount)) {
			count = Integer.valueOf(pageCount);
		}
		if (StringUtils.isNotBlank(storage_type) && storage_type.equals("1")) {
			ImportDataTypes entity = new ImportDataTypes();
			entity.setData_type(5l);
			entity.getMap().put("parId", 1);
			entity.getRow().setCount(count);
			List<ImportDataTypes> entityList = super.getFacade().getImportDataTypesService().getImportDataTypesList(
					entity);

			for (ImportDataTypes t : entityList) {
				sb.append("{\"id\":\"" + String.valueOf(t.getId()) + "\",");
				sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getType_name()) + "\"},");
			}
		}
		if (StringUtils.isNotBlank(storage_type) && storage_type.equals("2")) {
			request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
			for (KonkaDept t : super.getDeptInfoList(mapping, form, request, response, null)) {
				sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
				sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");
			}
		}
		sb.append("{\"end\":\"\"}]");
		super.renderJson(response, sb.toString());
		return null;
	}

	// 根据分公司找经办
	public ActionForward getChannelId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String part_company_id = (String) dynaBean.get("part_company_id");
		String pageCount = (String) dynaBean.get("pageCount");
		StringBuffer sb = new StringBuffer("[");
		int count = 500; // 每次最多取出数量

		if (!GenericValidator.isLong(part_company_id)) {
			return null;
		}

		if (GenericValidator.isLong(pageCount)) {
			count = Integer.valueOf(pageCount);
		}

		KonkaDept entity = new KonkaDept();
		entity.getRow().setCount(count);
		entity.getMap().put("is_jingban", 1);
		entity.setPar_id(new Long(part_company_id));
		List<KonkaDept> entityList = new ArrayList<KonkaDept>();

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaDept dept = new KonkaDept();
		dept.setDept_id(userInfo.getDept_id());
		dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_10_le_49 = false;
		boolean role_id_ge_50_lt_59 = false;
		boolean role_id_eq_60 = false;
		boolean role_id_eq_1000 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10L || peRoleUser.getRole_id() == 49L) {
				role_id_ge_10_le_49 = true;
			}
			if (peRoleUser.getRole_id() >= 50L && peRoleUser.getRole_id() < 59L) {
				role_id_ge_50_lt_59 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
			if (peRoleUser.getRole_id() == 1000L) {
				role_id_eq_1000 = true;
			}
		}

		if (role_id_ge_10_le_49) {
			entity.setPar_id(Long.valueOf(part_company_id));
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (role_id_ge_50_lt_59) {
			entity.setDept_id(userInfo.getDept_id());
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (role_id_eq_60) {
			if (dept.getDept_type() == 5) {
				entity.setDept_id(userInfo.getDept_id());
				entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			}
		}
		if (role_id_eq_1000) {
			entity.setPar_id(Long.valueOf(part_company_id));
			entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
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
	 * @author Liu,ZhiXiang
	 * @version 2013-07-02
	 */
	// 分公司 三级联动
	public ActionForward getKonkaDeptForParId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_id = (String) dynaBean.get("par_id");
		// String dept_type = (String) dynaBean.get("dept_type");
		KonkaDept dept = new KonkaDept();
		dept.getMap().put("order_by_dept_name", "no_empty");
		// if (StringUtils.isNotBlank(dept_type)) {
		// dept.setDept_type(Integer.valueOf(dept_type));
		// }
		if (StringUtils.isNotBlank(par_id) && !"0".equals(par_id)) {
			dept.setPar_id(new Long(par_id));
		} else {
			dept.setDept_type(3);
		}
		List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
		// StringBuffer sb = new StringBuffer("[");
		// for (KonkaDept t : entityList) {
		// sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
		// sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");
		//
		// }
		// sb.append("{\"end\":\"\"}]");
		// super.renderJson(response, sb.toString());

		List<String> dataList = new ArrayList<String>();
		for (KonkaDept entity : entityList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.getDept_name(), "\",\"",
					String.valueOf(entity.getDept_id()), "\"]" }));
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}

}
