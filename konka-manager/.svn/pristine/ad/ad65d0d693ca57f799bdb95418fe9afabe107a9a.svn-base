package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcBcompPdRebates;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-11
 */
public class EcBcompPdRebatesAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String b_type = (String) dynaBean.get("b_type");
		String goods_id = (String) dynaBean.get("goods_id");

		super.encodeCharacterForGetMethod(dynaBean, request);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		EcBcompPdRebates entity = new EcBcompPdRebates();

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (zb && !fgs) {// session用户属于总部，不属于分公司
			// entity.getMap().put("dept_sn", "0");
		}
		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			entity.getMap().put("dept_sn_in", "0," + fgs_dept.getDept_sn());
		}
		// if (!zb && !fgs) {
		// String msg = super.getMessage(request, "popedom.check.invalid");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }

		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(b_type)) {
			entity.setB_type(new Integer(b_type));
		}
		if (StringUtils.isNotBlank(goods_id)) {
			entity.setGoods_id(Long.valueOf(goods_id));
		}

		// 商品所属系统：1-工卡，2-触网，3-会员
		// entity.getMap().put("own_sys", "1");
		// 商品状态：0-已停用 1-正常 -1-已删除
		entity.getMap().put("state", "1");
		// 返利是否删除:0-未删除 1-已删除
		entity.setIs_del(0);
		Long recordCount = super.getFacade().getEcBcompPdRebatesService().getEcBcompPdRebatesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcBcompPdRebates> entityList = super.getFacade().getEcBcompPdRebatesService()
		        .getEcBcompPdRebatesPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb && !fgs) {// session用户属于总部，不属于分公司
			dynaBean.set("dept_sn", "0");
		}
		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("dept_sn", fgs_dept.getDept_sn());
		}
		if (!zb && !fgs) {
			String msg = super.getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (GenericValidator.isLong(id)) {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
			if (null == user) {
				String msg = super.getMessage(request, "user.session.isEmpty");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			boolean zb = false;
			boolean fgs = false;
			PeRoleUser rUser = new PeRoleUser();
			rUser.setUser_id(user.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser pu : roleUserList) {
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317
					        || pu.getRole_id().intValue() == 1001) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}

			if (!zb && !fgs) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			EcBcompPdRebates entity = new EcBcompPdRebates();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(entity);

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			if (null != entity) {
				KonkaBcompPd k = new KonkaBcompPd();
				k.setId(entity.getGoods_id());
				k = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(k);
				if (null != k) {
					request.setAttribute("pd_name", "[" + k.getPd_sn() + "]" + k.getPd_name());
				}
			}

		} else {
			this.saveError(request, "errors.long", new String[] { id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (GenericValidator.isLong(id)) {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
			if (null == user) {
				String msg = super.getMessage(request, "user.session.isEmpty");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			boolean zb = false;
			boolean fgs = false;
			PeRoleUser rUser = new PeRoleUser();
			rUser.setUser_id(user.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser pu : roleUserList) {
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317
					        || pu.getRole_id().intValue() == 1001) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}

			if (!zb && !fgs) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			EcBcompPdRebates entity = new EcBcompPdRebates();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(entity);

			super.copyProperties(form, entity);

			if (null != entity) {
				KonkaBcompPd k = new KonkaBcompPd();
				k.setId(entity.getGoods_id());
				k = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(k);
				if (null != k) {
					request.setAttribute("pd_name", "[" + k.getPd_sn() + "]" + k.getPd_name());
				}
				// 添加人
				PeProdUser u = new PeProdUser();
				u.setId(entity.getAdd_u_id());
				u = super.getFacade().getPeProdUserService().getPeProdUser(u);
				if (null != u) {
					request.setAttribute("add_user_name", u.getReal_name());
				}
				// 最后修改人
				PeProdUser t = new PeProdUser();
				t.setId(entity.getAdd_u_id());
				t = super.getFacade().getPeProdUserService().getPeProdUser(t);
				if (null != t) {
					request.setAttribute("modify_user_name", t.getReal_name());
				}
			}

		} else {
			this.saveError(request, "errors.long", new String[] { id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		return mapping.findForward("view");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		EcBcompPdRebates entity = new EcBcompPdRebates();
		super.copyProperties(entity, form);

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (!zb && !fgs) {
			String msg = super.getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isBlank(id)) {
			// 新增
			EcBcompPdRebates e = new EcBcompPdRebates();
			e.setGoods_id(entity.getGoods_id());
			e.setC_id(entity.getC_id());
			log.info("c_id:" + entity.getC_id());
			e.setIs_del(0);
			Long count = super.getFacade().getEcBcompPdRebatesService().getEcBcompPdRebatesCount(e);
			if (count > 0) {
				String msg = "该商品的返利已经存在，请重新选择！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			entity.setIs_del(0);
			entity.setAdd_u_id(user.getId());
			entity.setModify_u_id(user.getId());

			super.getFacade().getEcBcompPdRebatesService().createEcBcompPdRebates(entity);

			saveMessage(request, "entity.inserted");
		} else {
			// 修改

			entity.setId(Long.valueOf(id));
			entity.setModify_u_id(user.getId());
			entity.setModify_date(new Date());

			super.getFacade().getEcBcompPdRebatesService().modifyEcBcompPdRebates(entity);
			saveMessage(request, "entity.updated");
		}

		logger.info("ssss=============={}", entity.getQueryString());
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			EcBcompPdRebates entity = new EcBcompPdRebates();
			super.copyProperties(entity, form);

			boolean zb = false;
			boolean fgs = false;
			PeRoleUser rUser = new PeRoleUser();
			rUser.setUser_id(user.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser pu : roleUserList) {
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317
					        || pu.getRole_id().intValue() == 1001) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}

			if (!zb && !fgs) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			entity.setIs_del(1);
			super.getFacade().getEcBcompPdRebatesService().modifyEcBcompPdRebates(entity);
			saveMessage(request, "entity.deleted");

		} else {
			this.saveError(request, "errors.long", new String[] { id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward list2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String goods_id = (String) dynaBean.get("goods_id");

		dynaBean.set("goods_id", goods_id);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		EcBcompPdRebates entity = new EcBcompPdRebates();

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (zb && !fgs) {
			entity.setOwn_sys(1);// 总部设置工卡的返利
		} else if (!zb && fgs) {
			KonkaBcompPd pd1 = new KonkaBcompPd();
			pd1.setId(Long.valueOf(goods_id));
			pd1 = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd1);
			if (null != pd1 && pd1.getOwn_sys().intValue() == 5) {
				entity.setOwn_sys(1);// 分公司设置触网的返利
			} else {
				entity.setOwn_sys(2);// 分公司设置触网的返利
			}
			entity.setDept_id(user.getDept_id());
		} else {
			entity.setOwn_sys(1);// 小家电单独处理
		}
		// if (!zb && !fgs) {
		// String msg = super.getMessage(request, "popedom.check.invalid");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }
		if (StringUtils.isNotBlank(goods_id)) {
			entity.setGoods_id(Long.valueOf(goods_id));
		}

		// entity.getMap().put("own_sys", "1");
		// 商品状态：0-已停用 1-正常 -1-已删除
		entity.getMap().put("state", "1");
		// 返利是否删除:0-未删除 1-已删除
		entity.setIs_del(0);
		Long recordCount = super.getFacade().getEcBcompPdRebatesService().getEcBcompPdRebatesCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcBcompPdRebates> entityList = super.getFacade().getEcBcompPdRebatesService()
		        .getEcBcompPdRebatesPaginatedList(entity);
		for (EcBcompPdRebates ecBcompPdRebates : entityList) {
			if (ecBcompPdRebates.getDept_id() != 0) {
				KonkaDept kd = new KonkaDept();
				kd.setDept_id(ecBcompPdRebates.getDept_id());
				kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
				ecBcompPdRebates.getMap().put("dept_name", kd.getDept_name());
			}
		}
		// 查询客户名称
		for (EcBcompPdRebates ecBcompPdRebates : entityList) {
			if (null != ecBcompPdRebates.getC_id()) {
				KonkaR3Shop kk = new KonkaR3Shop();
				kk.setId(ecBcompPdRebates.getC_id());
				kk = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kk);
				ecBcompPdRebates.getMap().put("customer_name", kk.getCustomer_name());
			}
		}
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/EcBcompPdRebates/list2.jsp");
	}

	public ActionForward add2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		logger.info("goods_id+++=" + goods_id);

		dynaBean.set("goods_id", goods_id);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (null != user.getDept_id()) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(user.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

			// 分公司取得
			if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
				dynaBean.set("fgs_id", fgs_dept.getDept_id());
			}

		}

		if (zb && !fgs) {// session用户属于总部，不属于分公司
			request.setAttribute("is_admin", "1");
			dynaBean.set("dept_sn", "0");
		} else if (!zb && fgs) { // session用户不属于总部，属于分公司
			request.setAttribute("is_fgs", "1");
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("dept_sn", fgs_dept.getDept_sn());

			KonkaBcompPd pd1 = new KonkaBcompPd();
			pd1.setId(Long.valueOf(goods_id));
			pd1 = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd1);
			if (null != pd1 && pd1.getOwn_sys().intValue() == 5) {
				dynaBean.set("epp_fgs", true);
			}

		} else {
			request.setAttribute("is_admin", "1");
			dynaBean.set("dept_sn", "0");
		}
		// if (!zb && !fgs) {
		// String msg = super.getMessage(request, "popedom.check.invalid");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }
		request.setAttribute("sybDeptInfoList", super.getDeptInfoListWithOutLimit(mapping, form, request, response));

		return new ActionForward("/../manager/spgl/EcBcompPdRebates/form2.jsp");
	}

	public ActionForward save2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String goods_id = (String) dynaBean.get("goods_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		EcBcompPdRebates entity = new EcBcompPdRebates();
		super.copyProperties(entity, form);

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		// if (!zb && !fgs) {
		// String msg = super.getMessage(request, "popedom.check.invalid");
		// super.renderJavaScript(response, "window.onload=function(){alert('" +
		// msg + "');history.back();}");
		// return null;
		// }

		if (StringUtils.isBlank(id)) {
			// 新增
			EcBcompPdRebates e = new EcBcompPdRebates();
			e.setGoods_id(Long.valueOf(goods_id));
			e.setC_id(entity.getC_id());
			e.setIs_del(0);
			if (zb) {
				e.setOwn_sys(1);
				e.setDept_id(0L);
			} else if (fgs) {
				KonkaBcompPd pd1 = new KonkaBcompPd();
				pd1.setId(Long.valueOf(goods_id));
				pd1 = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd1);
				if (null != pd1 && pd1.getOwn_sys().intValue() == 5) {
					e.setOwn_sys(1);
				} else {
					e.setOwn_sys(2);
				}
				e.setDept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			} else {
				e.setOwn_sys(1);
				e.setDept_id(0L);
			}

			Long count = super.getFacade().getEcBcompPdRebatesService().getEcBcompPdRebatesCount(e);
			if (count > 0) {
				String msg = "该商品的返利已经存在，请重新选择！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			entity.setIs_del(0);
			entity.setAdd_u_id(user.getId());
			entity.setModify_u_id(user.getId());
			if (zb) {
				entity.setOwn_sys(1);
				entity.setDept_id(0L);
			} else if (fgs) {
				KonkaBcompPd pd1 = new KonkaBcompPd();
				pd1.setId(Long.valueOf(goods_id));
				pd1 = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd1);
				if (null != pd1 && pd1.getOwn_sys().intValue() == 5) {
					entity.setOwn_sys(1);
				} else {
					entity.setOwn_sys(2);
				}
				entity.setDept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			} else {
				entity.setOwn_sys(1);
				entity.setDept_id(0L);
			}

			super.getFacade().getEcBcompPdRebatesService().createEcBcompPdRebates(entity);

			saveMessage(request, "entity.inserted");
		} else {
			// 修改
			entity.setId(Long.valueOf(id));
			entity.setModify_u_id(user.getId());
			entity.setModify_date(new Date());

			super.getFacade().getEcBcompPdRebatesService().modifyEcBcompPdRebates(entity);
			saveMessage(request, "entity.updated");
		}

		return new ActionForward("/../manager/spgl/EcBcompPdRebates.do?method=list2&goods_id=" + goods_id);

	}

	public ActionForward view2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (GenericValidator.isLong(id)) {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
			if (null == user) {
				String msg = super.getMessage(request, "user.session.isEmpty");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			boolean zb = false;
			boolean fgs = false;
			PeRoleUser rUser = new PeRoleUser();
			rUser.setUser_id(user.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser pu : roleUserList) {
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317
					        || pu.getRole_id().intValue() == 1001) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}

			// if (!zb && !fgs) {
			// String msg = super.getMessage(request, "popedom.check.invalid");
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('" + msg +
			// "');history.back();}");
			// return null;
			// }

			EcBcompPdRebates entity = new EcBcompPdRebates();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(entity);

			super.copyProperties(form, entity);

			if (null != entity) {
				KonkaBcompPd k = new KonkaBcompPd();
				k.setId(entity.getGoods_id());
				k = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(k);
				if (null != k) {
					request.setAttribute("pd_name", "[" + k.getPd_sn() + "]" + k.getPd_name());
				}
				// 添加人
				PeProdUser u = new PeProdUser();
				u.setId(entity.getAdd_u_id());
				u = super.getFacade().getPeProdUserService().getPeProdUser(u);
				if (null != u) {
					request.setAttribute("add_user_name", u.getReal_name());
				}
				// 最后修改人
				PeProdUser t = new PeProdUser();
				t.setId(entity.getAdd_u_id());
				t = super.getFacade().getPeProdUserService().getPeProdUser(t);
				if (null != t) {
					request.setAttribute("modify_user_name", t.getReal_name());
				}
			}

		} else {
			this.saveError(request, "errors.long", new String[] { id });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		return new ActionForward("/../manager/spgl/EcBcompPdRebates/view2.jsp");
	}

	public ActionForward edit2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (GenericValidator.isLong(id)) {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
			if (null == user) {
				String msg = super.getMessage(request, "user.session.isEmpty");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
			boolean zb = false;
			boolean fgs = false;
			PeRoleUser rUser = new PeRoleUser();
			rUser.setUser_id(user.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser pu : roleUserList) {
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317
					        || pu.getRole_id().intValue() == 1001) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}

			if (null != user.getDept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(user.getDept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

				// 分公司取得
				if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
					KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
					dynaBean.set("fgs_id", fgs_dept.getDept_id());
				}

			}

			// if (!zb && !fgs) {
			// String msg = super.getMessage(request, "popedom.check.invalid");
			// super.renderJavaScript(response,
			// "window.onload=function(){alert('" + msg +
			// "');history.back();}");
			// return null;
			// }

			EcBcompPdRebates entity = new EcBcompPdRebates();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getEcBcompPdRebatesService().getEcBcompPdRebates(entity);

			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			if (zb && !fgs) {// session用户属于总部，不属于分公司
				request.setAttribute("is_admin", "1");
				dynaBean.set("dept_sn", "0");
			} else if (!zb && fgs) { // session用户不属于总部，属于分公司
				request.setAttribute("is_fgs", "1");
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				dynaBean.set("dept_sn", fgs_dept.getDept_sn());

				KonkaBcompPd pd1 = new KonkaBcompPd();
				pd1.setId(Long.valueOf(entity.getGoods_id()));
				pd1 = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(pd1);
				if (null != pd1 && pd1.getOwn_sys().intValue() == 5) {
					dynaBean.set("epp_fgs", true);
				}

			} else {
				request.setAttribute("is_admin", "1");
				dynaBean.set("dept_sn", "0");
			}

			KonkaR3Shop kk = new KonkaR3Shop();
			if (null != entity.getC_id()) {
				kk.setId(entity.getC_id());
				kk = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kk);
				dynaBean.set("r3_code", kk.getCustomer_name());
			}

			if (null != entity) {
				KonkaBcompPd k = new KonkaBcompPd();
				k.setId(entity.getGoods_id());
				k = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(k);
				if (null != k) {
					request.setAttribute("pd_name", "[" + k.getPd_sn() + "]" + k.getPd_name());
				}
			}

		} else {
			this.saveError(request, "errors.long", new String[] { id });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		return new ActionForward("/../manager/spgl/EcBcompPdRebates/form2.jsp");
	}

	public ActionForward delete2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String goods_id = (String) dynaBean.get("goods_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		if (GenericValidator.isLong(id)) {
			EcBcompPdRebates entity = new EcBcompPdRebates();
			super.copyProperties(entity, form);

			boolean zb = false;
			boolean fgs = false;
			PeRoleUser rUser = new PeRoleUser();
			rUser.setUser_id(user.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser pu : roleUserList) {
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317
					        || pu.getRole_id().intValue() == 1001) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}

			if (!zb && !fgs) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			entity.setIs_del(1);
			super.getFacade().getEcBcompPdRebatesService().modifyEcBcompPdRebates(entity);
			saveMessage(request, "entity.deleted");

		} else {
			this.saveError(request, "errors.long", new String[] { id });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}

		return new ActionForward("/../manager/spgl/EcBcompPdRebates.do?method=list2&goods_id=" + goods_id);

	}

}