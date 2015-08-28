package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPePublicScope;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.PeShopCategory;
import com.ebiz.mmt.domain.PeShopMsg;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Chen,Shunhua
 * @version 2011-10-10
 */
public class PeShopMsgAction extends BaseAction {

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
										HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	/**
	 * @desc 收件箱
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String public_target = (String) dynaBean.get("public_target");

		PeProdUser sessionUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeShopMsg entity = new PeShopMsg();
		KonkaDept dept = super.getSuperiorForDeptType(sessionUser.getDept_id(), 3);// 获取当前用户所属分公司
		entity.getMap().put("user_id", sessionUser.getId());// 当public_type = 4时，存放的是分公司用户的id
		if (dept != null) {
			entity.getMap().put("dept_id", dept.getDept_id());// 当public_type = 5时，存放的是分公司的id
		} else {
			entity.getMap().put("dept_id", -1);
		}
		entity.getMap().put("is_konka_use", "true");// 表示进行的查询是康佳渠道系统中的
		if (StringUtils.isNotBlank(public_target)) {
			entity.getMap().put("public_target", new Long(1));// 表示进行的查询时当前节点的类型：进销存中存的值是3
		}
		entity.getMap().put("receive_user_id", sessionUser.getId());// 查询回复的信息
		entity.getMap().put("order_by_send_date", true);
		entity.getMap().put("order_by_send_date", true);

		Long recordCount = super.getFacade().getPeShopMsgService().getPeShopMsgCountForReceive(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PeShopMsg> entityList = super.getFacade().getPeShopMsgService()
				.getPeShopMsgPaginatedListForReceive(entity);
		request.setAttribute("tag_id", StringUtils.isNotEmpty((String) dynaBean.get("tag_id")) ? (String) dynaBean
				.get("tag_id") : "a");
		request.setAttribute("entityList", entityList);
		// request.setAttribute("receive_user_type", (String)dynaBean.get("receive_user_type"));
		// request.setAttribute("public_target", public_target);
		dynaBean.set("receive_user_type", dynaBean.get("receive_user_type"));
		dynaBean.set("public_target", public_target);
		return mapping.findForward("list");
	}

	public ActionForward listOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String receive_user_type = (String) dynaBean.get("receive_user_type");// 0:R3用户,1:代理商网点,2:经销网点

		if (!GenericValidator.isLong(receive_user_type)) {
			super.invalidOper(request, response, "errors.parm");
			return null;
		}

		PeProdUser sessionUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeShopMsg entity = new PeShopMsg();
		super.copyProperties(entity, form);

		entity.setSend_user_id(sessionUser.getId());
		entity.setReceive_user_type(new Integer(receive_user_type));
		entity.setIs_del(0l);
		entity.getMap().put("order_by_send_date", true);
		entity.getMap().put("not_eq_state", 0);

		Long recordCount = super.getFacade().getPeShopMsgService().getPeShopMsgCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PeShopMsg> entityList = super.getFacade().getPeShopMsgService().getPeShopMsgPaginatedList(entity);

		request.setAttribute("tag_id", StringUtils.isNotEmpty((String) dynaBean.get("tag_id")) ? (String) dynaBean
				.get("tag_id") : "b");

		request.setAttribute("entityList", entityList);
		return new ActionForward("/../manager/admin/PeShopMsg/list_out.jsp");
	}

	public ActionForward listDraft(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String receive_user_type = (String) dynaBean.get("receive_user_type");// 0:R3用户,1:代理商网点,2:经销网点

		if (!GenericValidator.isLong(receive_user_type)) {
			super.invalidOper(request, response, "errors.parm");
			return null;
		}

		PeProdUser sessionUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		PeShopMsg entity = new PeShopMsg();
		super.copyProperties(entity, form);

		entity.setSend_user_id(sessionUser.getId());
		entity.setReceive_user_type(new Integer(receive_user_type));
		entity.setState(0);

		// entity.getMap().put("send_date_is_null", true);
		entity.getMap().put("order_by_add_date", true);
		entity.setIs_del(0l);

		Long recordCount = super.getFacade().getPeShopMsgService().getPeShopMsgCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PeShopMsg> entityList = super.getFacade().getPeShopMsgService().getPeShopMsgPaginatedList(entity);

		request.setAttribute("tag_id", StringUtils.isNotEmpty((String) dynaBean.get("tag_id")) ? (String) dynaBean
				.get("tag_id") : "c");

		request.setAttribute("entityList", entityList);
		request.setAttribute("user_id", sessionUser.getId());
		return new ActionForward("/../manager/admin/PeShopMsg/list_draft.jsp");
	}

	public ActionForward editMsg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		// String receive_user_type = (String) dynaBean.get("receive_user_type");// 0:R3用户,1:代理商网点,2:经销网点

		String user_type = "";

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(ui.getDept_id());
		konkaDept = getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		if (konkaDept != null && konkaDept.getDept_type() == 3) { // 分公司
			dynaBean.set("many_p_index", konkaDept.getM_areas_ids()); // 分公司管辖范围
			user_type = "1"; // 分公司只可以选择管辖区域内的网点
		} else if (konkaDept != null && (konkaDept.getDept_type() == 5 || konkaDept.getDept_type() == 4)) { // 判断当前用户是否是经营部或办事处
			dynaBean.set("many_p_index", "");
			user_type = "2";// 经营部或办事处只可以选择分配给他的网点
		} else {
			user_type = "0";// admin 或 事业部用户
		}

		dynaBean.set("user_type", user_type);

		// 取得baseProvinceList省的值
		BaseProvinceList Province = new BaseProvinceList();
		Province.setDel_mark(new Short("0"));
		Province.setP_level(new Short("1"));
		List<BaseProvinceList> baseProvince1List = getFacade().getBaseProvinceListService().getBaseProvinceListList(
				Province);
		request.setAttribute("baseProvince1List", baseProvince1List);

		String select_type = "select_type0";// 按具体网点发布或 按网点类别发布，默认按按具体网点发布

		if (GenericValidator.isLong(id)) {
			PeShopMsg msg = new PeShopMsg();
			msg.setId(Long.valueOf(id));
			msg = super.getFacade().getPeShopMsgService().getPeShopMsg(msg);
			request.setAttribute("entity", msg);

			// 显示5个以内网点名称
			KonkaPePublicScope konkaPePublicScope = new KonkaPePublicScope();
			konkaPePublicScope.setArticle_id(Long.valueOf(id));

			List<KonkaPePublicScope> konkaPePublicScopeLIst = null;

			// 网点类别
			KonkaPePublicScope kpps = new KonkaPePublicScope();
			kpps.setArticle_id(konkaPePublicScope.getArticle_id());
			kpps.setPublic_type(2l);
			konkaPePublicScopeLIst = getFacade().getKonkaPePublicScopeService().getKonkaPePublicScopeList(kpps);
			if (null != konkaPePublicScopeLIst && konkaPePublicScopeLIst.size() > 0) {
				StringBuffer sbCategoryIds = new StringBuffer(), sbCategoryNames = new StringBuffer();
				for (KonkaPePublicScope _kpps : konkaPePublicScopeLIst) {
					sbCategoryIds.append(_kpps.getPublic_scope()).append(",");
				}
				String _ids = StringUtils.substringBeforeLast(sbCategoryIds.toString(), ",");
				PeShopCategory psc = new PeShopCategory();
				psc.setIs_del(0l);
				psc.getMap().put("selects", _ids);
				List<PeShopCategory> peShopCategoryList = getFacade().getPeShopCategoryService().getPeShopCategoryList(
						psc);
				if (null != peShopCategoryList) {
					for (PeShopCategory _psc : peShopCategoryList) {
						sbCategoryNames.append(_psc.getCategory_name()).append(",");
					}
					dynaBean.set("peShopCategoryId", _ids);
					dynaBean
							.set("peShopCategoryName", StringUtils.substringBeforeLast(sbCategoryNames.toString(), ","));
				}
				select_type = "select_type1"; // 按网点类别发布的
			} else {
				konkaPePublicScopeLIst = null;
			}

			if ("0".equals(user_type)) {// 管理员或事业部先判断是否对全部网点进行发布
				KonkaPePublicScope _konkaPePublicScope = new KonkaPePublicScope();
				_konkaPePublicScope.setArticle_id(konkaPePublicScope.getArticle_id());
				_konkaPePublicScope.setPublic_type(0l);
				konkaPePublicScopeLIst = getFacade().getKonkaPePublicScopeService().getKonkaPePublicScopeList(
						_konkaPePublicScope);
				if (null != konkaPePublicScopeLIst && konkaPePublicScopeLIst.size() == 1) {
					dynaBean.set("public_type", "0");
				} else {
					konkaPePublicScopeLIst = null;
				}
			}

			if (null == konkaPePublicScopeLIst) {
				dynaBean.set("areas_ids", msg.getAreas_ids());
				dynaBean.set("areas_names", msg.getAreas_names());

				if ("select_type0".equals(select_type)) {// 相等的话说明不是按类别投放，那就是按具体网点投放
					konkaPePublicScope.setPublic_type(3l);
					konkaPePublicScopeLIst = super.getFacade().getKonkaPePublicScopeService()
							.getKonkaPePublicScopeList(konkaPePublicScope);
					if (null != konkaPePublicScopeLIst && konkaPePublicScopeLIst.size() > 0) {
						StringBuffer sbName = new StringBuffer(), sbId = new StringBuffer();
						for (int i = 0; i < konkaPePublicScopeLIst.size(); i++) {
							sbId.append(",").append(konkaPePublicScopeLIst.get(i).getPublic_scope());
						}

						// TODO 选择网点回显
						MmtEntpShop konkaEntpShop = new MmtEntpShop();
						konkaEntpShop.getMap().put("selects", StringUtils.substring(sbId.toString(), 1));
						konkaEntpShop.getRow().setCount(5);
						List<MmtEntpShop> konkaEntpShop_list = super.getFacade().getMmtEntpShopService()
								.getMmtEntpShopList(konkaEntpShop);
						if (null != konkaEntpShop_list) {
							for (MmtEntpShop kes : konkaEntpShop_list) {
								sbName.append(kes.getShop_name()).append(",");
							}
						}

						dynaBean.set("shop_name", StringUtils.substringBeforeLast(sbName.toString(), ","));
						dynaBean.set("shop_id", StringUtils.substring(sbId.toString(), 1));
					}
				}
			}
		}

		dynaBean.set("select_type_id", select_type);
		request.setAttribute("tag_id", StringUtils.isNotEmpty((String) dynaBean.get("tag_id")) ? (String) dynaBean
				.get("tag_id") : "d");

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id_names[] = request.getParameterValues("id_name");
		String peShopCategoryId = (String) dynaBean.get("peShopCategoryId");// 网点类别ID
		// String peShopCategoryName = (String) dynaBean.get("peShopCategoryName");//网点类别名称
		String shop_id = (String) dynaBean.get("shop_id");
		String public_type = (String) dynaBean.get("public_type"); // 发布类型
		String public_target = (String) dynaBean.get("public_target");
		String title = (String) dynaBean.get("title");
		String content = (String) dynaBean.get("content");
		String id = (String) dynaBean.get("id");
		String user_type = (String) dynaBean.get("user_type");// 1分公司，2经营部或办事处，空则为admin或事业部
		String receive_user_type = (String) dynaBean.get("receive_user_type");// 0:R3用户,1:代理商网点,2:经销网点
		String select_type = (String) dynaBean.get("select_type");

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
		}

		PeShopMsg entity = new PeShopMsg();
		super.copyProperties(entity, form);

		// / 判断当前用户是否是分公司
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		List<KonkaPePublicScope> konkaPePublicScopeList = new ArrayList<KonkaPePublicScope>();
		if ("0".equals(select_type)) {// 按具体网点发布
			// public_type : 0全部 1选择的网点
			if ("0".equals(public_type)) {// 对全部地区的网点发送
				KonkaPePublicScope kps = new KonkaPePublicScope();
				kps.setPublic_type(0l);
				kps.setPublic_target(new Integer(public_target));
				konkaPePublicScopeList.add(kps);
			} else {// 对选择的网点进行发布
				String[] shop_ids = StringUtils.split(shop_id, ",");
				if ("0".equals(user_type) && shop_ids.length == 0) {// admin或事业部，可以只选择到区域而不需要选择到具体的网点
					for (String area_id : area_ids) {
						KonkaPePublicScope kps = new KonkaPePublicScope();
						kps.setPublic_type(1l);
						kps.setPublic_scope(new Long(area_id));
						kps.setPublic_target(new Integer(public_target));
						konkaPePublicScopeList.add(kps);
					}
				} else {
					for (String _shop_id : shop_ids) {
						KonkaPePublicScope kps = new KonkaPePublicScope();
						kps.setPublic_type(3l);
						kps.setPublic_scope(new Long(_shop_id));
						kps.setPublic_target(new Integer(public_target));
						konkaPePublicScopeList.add(kps);
					}
				}
			}
		} else if ("1".equals(select_type)) {// 按网点类别发布
			String[] peShopCategoryIds = StringUtils.split(peShopCategoryId, ",");
			if (null != peShopCategoryIds) {
				for (int i = 0; i < peShopCategoryIds.length; i++) {
					KonkaPePublicScope kps = new KonkaPePublicScope();
					kps.setPublic_type(2l);
					kps.setPublic_scope(new Long(peShopCategoryIds[i]));
					kps.setPublic_target(new Integer(public_target));
					konkaPePublicScopeList.add(kps);
				}
			}

			// 不是 admin或事业部发送信息按照网点类别进行发布时，存信息时将其所管辖的区域存进去，方便客户端进行查询
			KonkaDept dept = new KonkaDept();
			dept.setDept_id(ui.getDept_id());
			dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);
			if (null != dept) {
				entity.setAreas_ids(dept.getM_areas_ids());
				entity.setAreas_names(dept.getM_areas_names());
			}
		} else {
			super.invalidOper(request, response, "errors.parm");
			return null;
		}

		entity.setKonkaPePublicScopeList(konkaPePublicScopeList);

		if (area_ids.length > 0) {
			entity.setAreas_ids(StringUtils.join(area_ids, ","));
		}
		if (areas_names.length > 0) {
			entity.setAreas_names(StringUtils.join(areas_names, ","));
		}

		if (entity.getState() == 1) {
			entity.setSend_date(new Date());
		}

		entity.setPar_id(0l);// 未用到字段
		entity.setSend_user_id(ui.getId());
		entity.setSend_user_name(ui.getUser_name());
		entity.setSend_user_type(1);
		entity.setSend_ip(request.getRemoteAddr());
		entity.setContent(content);
		entity.setTitle(title);

		if (StringUtils.isNotBlank(id)) {
			super.getFacade().getPeShopMsgService().modifyPeShopMsg(entity);
		} else {
			super.getFacade().getPeShopMsgService().createPeShopMsg(entity);
		}

		super.saveMessage(request, entity.getState() == 1 ? "prod.msg.send.success" : "prod.msg.save.success");

		super.copyProperties(form, new PeShopMsg());
		dynaBean.set("receive_user_type", receive_user_type);

		// save_type : 1已发送， 0暂存
		if (entity.getState() == 1) {
			dynaBean.set("tag_id", "b");
			dynaBean.set("public_target", public_target);
			return this.listOut(mapping, form, request, response);
		} else {
			dynaBean.set("tag_id", "c");
			dynaBean.set("public_target", public_target);
			return this.listDraft(mapping, form, request, response);
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String state = (String) dynaBean.get("state");

		if (GenericValidator.isLong(id)) {
			PeShopMsg en = new PeShopMsg();
			en.setId(Long.valueOf(id));

			if (StringUtils.isNotEmpty(state)) {
				en.setState(Integer.valueOf(state));
			}

			PeProdUser sessionUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
			en.setSend_user_id(sessionUser.getId());
			// en.setSend_user_type(1);
			PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);
			request.setAttribute("entity", entity);

			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("listOut");
			}

			// 查出原件的内容
			if (entity.getPar_id() > 0) {
				PeShopMsg _entity = new PeShopMsg();
				_entity.setId(entity.getPar_id());
				_entity = super.getFacade().getPeShopMsgService().getPeShopMsg(_entity);
				request.setAttribute("_entity", _entity);
			}

			// 回显 发布对象信息,public_type标明发布对象类型： 0所有 ，1地区 ，3网点
			Long public_type = 0l;
			KonkaPePublicScope konkaPePublicScope = new KonkaPePublicScope();
			konkaPePublicScope.setArticle_id(Long.valueOf(id));

			konkaPePublicScope.setPublic_type(0l);
			List<KonkaPePublicScope> list = super.getFacade().getKonkaPePublicScopeService().getKonkaPePublicScopeList(
					konkaPePublicScope);
			if (list.size() == 0) {
				konkaPePublicScope.setPublic_type(1l);
				public_type = 1l;
				list = super.getFacade().getKonkaPePublicScopeService().getKonkaPePublicScopeList(konkaPePublicScope);
				if (list.size() == 0) {
					konkaPePublicScope.setPublic_type(2l);
					public_type = 2l;
					list = super.getFacade().getKonkaPePublicScopeService().getKonkaPePublicScopeList(
							konkaPePublicScope);
					if (list.size() == 0) {
						konkaPePublicScope.setPublic_type(3l);
						public_type = 3l;
						list = super.getFacade().getKonkaPePublicScopeService().getKonkaPePublicScopeList(
								konkaPePublicScope);
					}
				}
			}

			dynaBean.set("public_type", public_type);

			if (public_type == 0) {// 所有网点

			} else if (public_type == 1) {// 地区
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < list.size(); i++) {
					KonkaPePublicScope temp = list.get(i);
					if (i == 0) {
						sb.append(temp.getPublic_scope());
					} else
						sb.append(",").append(temp.getPublic_scope());
				}
				BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
				baseProvinceListFour.getMap().put("many_p_index", sb.toString());
				List<BaseProvinceListFour> baseProvinceListFour_list = super.getFacade()
						.getBaseProvinceListFourService().getBaseProvinceListFourList(baseProvinceListFour);
				sb = new StringBuffer();
				for (BaseProvinceListFour city : baseProvinceListFour_list) {
					sb.append(city.getFull_name()).append("&nbsp;");
				}
				dynaBean.set("citys", sb.toString());
			} else if (public_type == 2) {
				// 网点类别
				KonkaPePublicScope kpps = new KonkaPePublicScope();
				kpps.setArticle_id(Long.valueOf(id));
				kpps.setPublic_type(2l);
				List<KonkaPePublicScope> konkaPePublicScopeList = getFacade().getKonkaPePublicScopeService()
						.getKonkaPePublicScopeList(kpps);
				if (null != konkaPePublicScopeList && konkaPePublicScopeList.size() > 0) {
					StringBuffer sbCategoryIds = new StringBuffer(), sbCategoryNames = new StringBuffer();
					for (KonkaPePublicScope _kpps : konkaPePublicScopeList) {
						sbCategoryIds.append(_kpps.getPublic_scope()).append(",");
					}
					String _ids = StringUtils.substringBeforeLast(sbCategoryIds.toString(), ",");
					PeShopCategory psc = new PeShopCategory();
					psc.setIs_del(0l);
					psc.getMap().put("selects", _ids);
					List<PeShopCategory> _list = getFacade().getPeShopCategoryService().getPeShopCategoryList(psc);
					if (null != _list) {
						for (PeShopCategory _psc : _list) {
							sbCategoryNames.append(_psc.getCategory_name()).append("&nbsp;");
						}
						dynaBean.set("peShopCategoryName", sbCategoryNames.toString());
					}
				}

			} else if (public_type == 3) {// 网点
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < list.size(); i++) {
					KonkaPePublicScope temp = list.get(i);
					if (i == 0) {
						sb.append(temp.getPublic_scope());
					} else
						sb.append(",").append(temp.getPublic_scope());
				}
				MmtEntpShop konkaEntpShop = new MmtEntpShop();
				if (StringUtils.isNotBlank(sb.toString())) {
					konkaEntpShop.getMap().put("selects", sb.toString());
					List<MmtEntpShop> konkaEntpShop_list = getFacade().getMmtEntpShopService().getMmtEntpShopList(
							konkaEntpShop);
					sb = new StringBuffer();
					for (MmtEntpShop shop : konkaEntpShop_list) {
						sb.append(shop.getShop_name()).append("<br />");
					}
				}
				if (StringUtils.isNotBlank(sb.toString()) && sb.toString() != null) {
					dynaBean.set("shops", sb.toString());
				} else {
					dynaBean.set("shops", entity.getReceive_user_name());
				}
			}
			request.setAttribute("entity", entity);
			request.setAttribute("tag_id", dynaBean.get("tag_id"));
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward viewReceive(ActionMapping mapping, ActionForm form, HttpServletRequest request,
									 HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 进行回复的信息的ID
		String receive_user_id = (String) dynaBean.get("receive_user_id");// 进行回复的信息的收件人ID

		super.setNaviStringToRequestScope(form, request);

		PeProdUser sessionUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);

		if (GenericValidator.isLong(id)) {
			String user_ids = "";
			String user_names = "";
			String role_names = "";
			String role_ids = "";
			PeShopMsg en = new PeShopMsg();
			en.setId(Long.valueOf(id));
			PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);
			if (null != entity) {// 根据id查询信息接收人列表 根据接收人查询对应列表角色
				if (null != entity.getPar_id() && entity.getPar_id() > 0) {// 查出原件的内容
					PeShopMsg _en = new PeShopMsg();
					_en.setId(entity.getPar_id());
					_en = super.getFacade().getPeShopMsgService().getPeShopMsg(_en);
					if (null != _en) {
						request.setAttribute("_entity", _en);
						super.getFacade().getPeShopMsgService().preModifyPeShopMsg(_en);
					}
				}

				KonkaPePublicScope kps = new KonkaPePublicScope();
				kps.setArticle_id(entity.getId());
				List<KonkaPePublicScope> konkaPePublicScopeList = getFacade().getKonkaPePublicScopeService()
						.getKonkaPePublicScopeList(kps);
				if (null != konkaPePublicScopeList && konkaPePublicScopeList.size() > 0) {
					for (KonkaPePublicScope kkpps : konkaPePublicScopeList) {
						user_ids += kkpps.getPublic_scope().toString() + ",";
						if (null != kkpps.getPublic_scope()
								&& GenericValidator.isLong(kkpps.getPublic_scope().toString())) {// 根据id取用户名称
							PeProdUser ppUser = new PeProdUser();
							ppUser.setId(kkpps.getPublic_scope());
							ppUser = getFacade().getPeProdUserService().getPeProdUser(ppUser);
							if (null != ppUser) {
								user_names += ppUser.getUser_name() + ",";
							}
						}
					}
				}
				if (null != user_ids && user_ids.length() > 0) {// 根据用户id取对应角色
					user_ids = user_ids.substring(0, user_ids.lastIndexOf(","));
					PeRoleInfo peRoleInfo = new PeRoleInfo();
					peRoleInfo.getMap().put("user_ids", user_ids);
					List<PeRoleInfo> peRoleInfoList = getFacade().getPeRoleInfoService().getPeRoleInfoByUserIdsList(
							peRoleInfo);
					if (null != peRoleInfoList && peRoleInfoList.size() > 0) {
						for (PeRoleInfo pr : peRoleInfoList) {
							role_ids += pr.getRole_id() + ",";
							role_names += pr.getRole_name() + ",";
						}
					}
				}
				if (null != user_names && user_names.length() > 0) {
					user_names = user_names.substring(0, user_names.lastIndexOf(","));
				}
				if (null != role_ids && role_ids.length() > 0) {
					role_ids = role_ids.substring(0, role_ids.lastIndexOf(","));
				}
				if (null != role_names && role_names.length() > 0) {
					role_names = role_names.substring(0, role_names.lastIndexOf(","));
				}
			}

			dynaBean.set("state", 2);
			dynaBean.set("user_ids", user_ids);
			if (StringUtils.isNotBlank(user_names) && null != user_names) {
				dynaBean.set("user_names", user_names);
			} else {// 没有记录则显示当前用户的用户名
				dynaBean.set("user_names", sessionUser.getUser_name());
			}
			if (StringUtils.isNotBlank(role_names) && null != role_names) {
				dynaBean.set("role_names", role_names);
			} else if (peRoleUser.getRole_id() != null) {// 没有记录则显示当前用户的角色名
				PeRoleInfo peRoleInfo = new PeRoleInfo();
				peRoleInfo.setRole_id(peRoleUser.getRole_id());
				peRoleInfo = super.getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
				if (peRoleInfo != null) {
					dynaBean.set("role_names", peRoleInfo.getRole_name());
				}
			}
			dynaBean.set("role_ids", role_ids);
			super.copyProperties(dynaBean, entity);
			request.setAttribute("tag_id", dynaBean.get("tag_id"));
			request.setAttribute("receive_user_type", dynaBean.get("receive_user_type"));
			dynaBean.set("receive_user_type", dynaBean.get("receive_user_type"));
			request.setAttribute("send_user_id", receive_user_id);
			return new ActionForward("/../manager/admin/PeShopMsg/view_receive.jsp");
		} else {
			saveError(request, "errors.long", id);
			request.setAttribute("tag_id", "a");
			dynaBean.set("receive_user_type", dynaBean.get("receive_user_type"));
			return mapping.findForward("list");
		}
	}

	public ActionForward reply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							   HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 进行回复的信息的ID
		String receive_user_id = (String) dynaBean.get("receive_user_id");// 进行回复的信息的收件人ID
		String public_target = (String) dynaBean.get("public_target");
		String receive_user_type = (String) dynaBean.get("receive_user_type");
		if (null == receive_user_id) {
			return null;
		}

		if (GenericValidator.isLong(id)) {
			PeShopMsg en = new PeShopMsg();
			en.setId(Long.valueOf(id));
			// en.setSend_user_type(0);

			PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);
			request.setAttribute("entity", entity);

			request.setAttribute("tag_id", "d");
			dynaBean.set("receive_user_id", receive_user_id);
			dynaBean.set("receive_user_type", receive_user_type);
			dynaBean.set("public_target", public_target);
			return new ActionForward("/../manager/admin/PeShopMsg/replyForm.jsp");
		} else {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
	}

	public ActionForward saveReply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String receive_user_id = (String) dynaBean.get("receive_user_id");// 进行回复的信息的收件人ID
		String receive_user_type = (String) dynaBean.get("receive_user_type");
		if (null == receive_user_id) {
			return null;
		}

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);// 获取当前用户的信息

		String par_id = (String) dynaBean.get("par_id");// 站内信原件ID
		String id = (String) dynaBean.get("id");// 回复信ID

		if (StringUtils.isNotBlank(id)) {// 发送回复信息
			PeShopMsg entity = new PeShopMsg();
			super.copyProperties(entity, form);

			PeShopMsg en = new PeShopMsg();

			en.setId(Long.valueOf(par_id));
			en = super.getFacade().getPeShopMsgService().getPeShopMsg(en);// 原件内容

			if (null != en) {
				super.getFacade().getPeShopMsgService().modifyPeShopMsg(entity);// 更新回复信
				super.saveMessage(request, entity.getState() == 1 ? "prod.msg.send.success" : "prod.msg.save.success");

				super.copyProperties(form, new PeShopMsg());
				dynaBean.set("receive_user_type", receive_user_type);

				// save_type : 1已发送， 0暂存
				if (entity.getState() == 1) {
					dynaBean.set("tag_id", "b");
					dynaBean.set("public_target", dynaBean.get("public_target"));
					return this.listOut(mapping, form, request, response);
				} else {
					dynaBean.set("tag_id", "c");
					dynaBean.set("public_target", dynaBean.get("public_target"));
					return this.listDraft(mapping, form, request, response);
				}
			}
			saveMessage(request, "entity.missing");
			super.copyProperties(form, new PeShopMsg());
			dynaBean.set("receive_user_type", receive_user_type);
			dynaBean.set("tag_id", "c");
			return this.listDraft(mapping, form, request, response);
		} else {// 保存回复信息
			if (GenericValidator.isLong(par_id)) {
				PeShopMsg en = new PeShopMsg();
				en.setId(Long.valueOf(par_id));
				PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);// 原件内容
				if (null != entity) {
					PeShopMsg peShopMsg = new PeShopMsg();
					super.copyProperties(peShopMsg, form);

					peShopMsg.setPar_id(entity.getId());// 回复的原件ID
					peShopMsg.setSend_user_id(ui.getId());// 发件人ID：当前用户的id
					peShopMsg.setSend_user_name(ui.getUser_name());// 发件人姓名：当前用户的姓名
					peShopMsg.setSend_user_type(1);
					peShopMsg.setReceive_user_type(Integer.parseInt(receive_user_type));

					MmtEntpShop konkaEntpShop = new MmtEntpShop();
					konkaEntpShop.setShop_id(Long.parseLong(receive_user_id));
					konkaEntpShop = super.getFacade().getMmtEntpShopService().getMmtEntpShop(konkaEntpShop);
					if (null != konkaEntpShop) {
						peShopMsg.setReceive_user_id(konkaEntpShop.getShop_id());
						peShopMsg.setReceive_user_name(konkaEntpShop.getShop_name());
					}

					peShopMsg.setSend_date(new Date());

					super.getFacade().getPeShopMsgService().createPeShopMsg(peShopMsg);// 插入回复的信件、更新原件为已回复
					super.saveMessage(request, peShopMsg.getState() == 1 ? "prod.msg.send.success"
							: "prod.msg.save.success");
					super.copyProperties(form, new PeShopMsg());
					dynaBean.set("receive_user_type", receive_user_type);

					// save_type : 1已发送， 0暂存
					if (peShopMsg.getState() == 1) {
						dynaBean.set("tag_id", "b");
						return this.listOut(mapping, form, request, response);
					} else {
						dynaBean.set("tag_id", "c");
						return this.listDraft(mapping, form, request, response);
					}
				}
				saveMessage(request, "entity.missing");
				dynaBean.set("tag_id", "a");
				return mapping.findForward("list");
			} else {
				saveMessage(request, "entity.missing");
				super.copyProperties(form, new PeShopMsg());
				dynaBean.set("receive_user_type", receive_user_type);
				return this.list(mapping, form, request, response);
			}
		}
	}

	public ActionForward editReply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");// 回复信件ID

		if (GenericValidator.isLong(id)) {
			PeShopMsg en = new PeShopMsg();
			en.setId(Long.valueOf(id));
			PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);
			request.setAttribute("replyEntity", entity);

			if (null != entity) {
				PeShopMsg proPeShopeMsg = new PeShopMsg();
				proPeShopeMsg.setId(entity.getPar_id());
				proPeShopeMsg = super.getFacade().getPeShopMsgService().getPeShopMsg(proPeShopeMsg);
				request.setAttribute("entity", proPeShopeMsg);
				request.setAttribute("tag_id", "d");// 到发信息
				request.setAttribute("mod_id", dynaBean.get("mod_id"));
			}

			return new ActionForward("/../manager/admin/PeShopMsg/replyForm.jsp");
		} else {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String tag_id = (String) dynaBean.get("tag_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			PeShopMsg entity = new PeShopMsg();
			entity.setId(new Long(id));
			entity.setDel_date(new Date());
			entity.setIs_del((long) 1);
			super.getFacade().getPeShopMsgService().preModifyPeShopMsg(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			PeShopMsg entity = new PeShopMsg();
			entity.setDel_date(new Date());
			entity.setIs_del((long) 1);
			entity.getMap().put("pks", pks);
			super.getFacade().getPeShopMsgService().preModifyPeShopMsg(entity);
			saveMessage(request, "entity.deleted");
		}

		dynaBean.set("id", "");
		dynaBean.set("pks", null);

		if ("b".equals(tag_id)) {
			return this.listOut(mapping, form, request, response);
		} else {
			return this.list(mapping, form, request, response);
		}
	}
}