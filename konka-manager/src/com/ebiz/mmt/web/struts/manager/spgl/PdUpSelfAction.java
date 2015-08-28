package com.ebiz.mmt.web.struts.manager.spgl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ebiz.mmt.domain.EcBcompPdUp;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Pan,Gang
 * @version 2013-09-11
 */
public class PdUpSelfAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String label_of_cate = (String) dynaBean.get("label_of_cate");
		String prod_type = (String) dynaBean.get("prod_type");
		String is_sj = (String) dynaBean.get("is_sj");
		String is_tj = (String) dynaBean.get("is_tj");
		String own_sys = (String) dynaBean.get("own_sys");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd entity = new KonkaBcompPd();
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
				}
			}
		}
		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			// entity.getMap().put("dept_sn_in", "0," +
			// fgs_dept.getDept_id().toString());
		}

		if (zb) {
			request.setAttribute("is_admin", "1");// 总部
		} else if (fgs) {
			request.setAttribute("is_fgs", "1");// 分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());
		} else {
			request.setAttribute("is_admin", "1");// 总部
		}

		if (StringUtils.isNotBlank(is_tj)) {
			entity.setIs_tj(Integer.valueOf(is_tj));
		}

		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate));
		}
		if (StringUtils.isNotBlank(prod_type)) {
			entity.setProd_type(Integer.valueOf(prod_type));
		}
		if (StringUtils.isNotBlank(own_sys)) {
			entity.setOwn_sys(Integer.valueOf(own_sys));
		}

		entity.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		if (StringUtils.isNotBlank(is_sj)) {
			if (is_sj.equals("1")) {// 上架
				if (zb) {
					entity.getMap().put("zb_goods_id_in", true);
				} else if (fgs) {
					entity.getMap().put("fgs_goods_id_in", true);
					entity.getMap().put("fgs_id",
					        super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id().toString());
				} else {// 单独处理白电事业部
					entity.getMap().put("zb_goods_id_in", true);
				}

			} else if (is_sj.equals("2")) {// 下架
				if (zb) {
					entity.getMap().put("zb_goods_id_not_in", true);
				} else if (fgs) {
					entity.getMap().put("fgs_goods_id_not_in", true);
					entity.getMap().put("fgs_id",
					        super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id().toString());
				} else {// 单独处理白电事业部
					entity.getMap().put("zb_goods_id_not_in", true);
				}

			}
		}

		entity.setState(1);
		entity.getMap().put("own_sys_in", new Integer[] { 1, 2, 4 });

		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdWithDeptAndMdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService()
		        .getKonkaBcompPdWithDeptAndMdPaginatedList(entity);
		Date dd = new Date();
		if (null != entityList && entityList.size() > 0)
			for (KonkaBcompPd kk : entityList) {
				EcBcompPdUp ep = new EcBcompPdUp();
				ep.setBcomp_pd_id(kk.getId());
				List<EcBcompPdUp> ecBcompPdUpList = super.getFacade().getEcBcompPdUpService().getEcBcompPdUpList(ep);
				if (null != entityList && ecBcompPdUpList.size() > 0) {
					for (EcBcompPdUp ecBcompPdUp : ecBcompPdUpList) {
						if (zb) {
							if (ecBcompPdUp.getOwn_sys() == 1 || ecBcompPdUp.getOwn_sys() == 3) {
								if (ecBcompPdUp.getDown_time().getTime() > dd.getTime()
								        && ecBcompPdUp.getAdded_dept_id() == 0) {
									kk.getMap().put("is_upSelf", true);
									break;
								}
							}
						} else if (fgs) {
							if (ecBcompPdUp.getOwn_sys() == 2) {
								if (ecBcompPdUp.getDown_time().getTime() > dd.getTime()
								        && super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id().longValue() == ecBcompPdUp
								                .getAdded_dept_id().longValue()) {
									kk.getMap().put("is_upSelf", true);
									break;
								}
							}
						} else {// 白电事业部
							if (ecBcompPdUp.getOwn_sys() == 1 || ecBcompPdUp.getOwn_sys() == 3) {
								if (ecBcompPdUp.getDown_time().getTime() > dd.getTime()
								        && ecBcompPdUp.getAdded_dept_id() == 0) {
									kk.getMap().put("is_upSelf", true);
									break;
								}
							}
						}
					}
				}
			}
		request.setAttribute("entityList", entityList);

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);
		request.setAttribute("today", new Date());

		if (StringUtils.isNotBlank(prod_type)) {
			dynaBean.set("prod_type", prod_type);
		}

		return mapping.findForward("list");
	}

	public ActionForward upShelf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String prod_type = (String) dynaBean.get("prod_type");

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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
				}
			}
		}

		if (GenericValidator.isLong(id)) {
			if (zb) {
				EcGoodsPrice ec = new EcGoodsPrice();
				ec.setGoods_id(Long.valueOf(id));
				ec.setOwn_sys(1);
				Long count = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceCount(ec);
				if (count == 0) {
					String msg = super.getMessage(request, "konka.price.zero");
					//System.out.println("++++++++++++++++" + super.getCtxPath(request));
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
			} else if (fgs) {
				EcGoodsPrice ec = new EcGoodsPrice();
				ec.setGoods_id(Long.valueOf(id));
				ec.setOwn_sys(2);
				ec.setDept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
				Long count = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceCount(ec);
				if (count == 0) {
					String msg = super.getMessage(request, "konka.price.zero");
					//System.out.println("++++++++++++++++" + super.getCtxPath(request));
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
			} else {
				EcGoodsPrice ec = new EcGoodsPrice();
				ec.setGoods_id(Long.valueOf(id));
				ec.setOwn_sys(1);
				Long count = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceCount(ec);
				if (count == 0) {
					String msg = super.getMessage(request, "konka.price.zero");
					//System.out.println("++++++++++++++++" + super.getCtxPath(request));
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				}
			}

			if (zb) {
				EcStocks ecs = new EcStocks();
				ecs.setGoods_id(Long.valueOf(id));
				ecs.getMap().put("own_sys", 1);
				List<EcStocks> ecsList = super.getFacade().getEcStocksService().getEcStocksForYzList(ecs);
				if (null == ecsList || 0 == ecsList.size()) {
					String msg = super.getMessage(request, "konka.store.zero");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				} else if (ecsList.size() > 0) {
					long num = 0;
					for (EcStocks ee : ecsList) {
						num = num + ee.getStocks();
					}
					if (num == 0L) {
						String msg = super.getMessage(request, "konka.store.zero");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						        + "');history.back();}");
						return null;
					}
				}
			} else if (fgs) {
				EcStocks ecs = new EcStocks();
				ecs.setGoods_id(Long.valueOf(id));
				ecs.getMap().put("own_sys", 2);
				ecs.getMap().put("dept_id", super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
				List<EcStocks> ecsList = super.getFacade().getEcStocksService().getEcStocksForYzList(ecs);
				if (null == ecsList || 0 == ecsList.size()) {
					String msg = super.getMessage(request, "konka.store.zero");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				} else if (ecsList.size() > 0) {
					long num = 0;
					for (EcStocks ee : ecsList) {
						num = num + ee.getStocks();
					}
					if (num == 0L) {
						String msg = super.getMessage(request, "konka.store.zero");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						        + "');history.back();}");
						return null;
					}
				}
			} else {
				EcStocks ecs = new EcStocks();
				ecs.setGoods_id(Long.valueOf(id));
				ecs.getMap().put("own_sys", 1);
				List<EcStocks> ecsList = super.getFacade().getEcStocksService().getEcStocksForYzList(ecs);
				if (null == ecsList || 0 == ecsList.size()) {
					String msg = super.getMessage(request, "konka.store.zero");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				} else if (ecsList.size() > 0) {
					long num = 0;
					for (EcStocks ee : ecsList) {
						num = num + ee.getStocks();
					}
					if (num == 0L) {
						String msg = super.getMessage(request, "konka.store.zero");
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
						        + "');history.back();}");
						return null;
					}
				}
			}

			KonkaBcompPd entity = new KonkaBcompPd();
			entity.setId(Long.valueOf(id));
			entity = getFacade().getKonkaBcompPdService().getKonkaBcompPd(entity);
			if (null != entity) {
				String main_pic = entity.getMain_pic();
				if (StringUtils.isNotBlank(main_pic)) {
					String main_pic_file = StringUtils.split(main_pic, ",")[0];
					dynaBean.set("main_pic_file", main_pic_file); // 主图

					List<String> list = new ArrayList<String>(); // 附图list
					String[] picArr = StringUtils.split(main_pic, ",");
					if (null != picArr && picArr.length > 0) {
						for (int i = 0; i < picArr.length; i++) {
							if (!StringUtils.equals(main_pic_file, picArr[i])) {
								list.add(picArr[i]);
							}
						}
					}
					request.setAttribute("picList", list);
				}
				logger.info("++++++++" + entity.getDept_sn());
				if (!"0".equals(entity.getDept_sn())) {
					KonkaDept dept = new KonkaDept();
					dept.setDept_id(Long.valueOf(entity.getDept_sn()));
					dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
					dynaBean.set("dept_name", dept.getDept_name());
					logger.info("++++++++dept_name" + dept.getDept_name());
				}

				PePdModel pdModel = new PePdModel();
				pdModel.setIs_del(0);
				pdModel.setAudit_state(1);
				pdModel.setPd_id(Long.valueOf(entity.getPd_spec()));
				pdModel = getFacade().getPePdModelService().getPePdModel(pdModel);
				if (pdModel != null && pdModel.getMd_name() != null) {
					dynaBean.set("md_name", pdModel.getMd_name());
				}

				// 取分公司列表
				List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
				request.setAttribute("deptList", deptList);

				if (StringUtils.isNotBlank(prod_type)) {
					dynaBean.set("prod_type", prod_type);
				}
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);
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
		return new ActionForward("/../manager/spgl/PdUpSelf/up_shelf.jsp");
	}

	public ActionForward upShelfSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String up_time = (String) dynaBean.get("up_time");
		String down_time = (String) dynaBean.get("down_time");
		String prod_type = (String) dynaBean.get("prod_type");
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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
				}
			}
		}

		EcBcompPdUp entity = new EcBcompPdUp();
		super.copyProperties(entity, form);
		if (GenericValidator.isLong(id)) {
			entity.setBcomp_pd_id(Long.valueOf(id));
			// entity.setUp_time(DateUtils.parseDate(up_time, new String[] {
			// "yyyy-MM-dd" }));
			// entity.setDown_time(DateUtils.parseDate(down_time, new String[] {
			// "yyyy-MM-dd" }));
			entity.setAdd_u_id(user.getId());
			entity.setAdd_date(new Date());
			if (zb) {
				entity.setOwn_sys(1);
				entity.setAdded_dept_id(0L);
			} else if (fgs) {
				entity.setOwn_sys(2);
				entity.setAdded_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			} else {
				entity.setOwn_sys(1);
				entity.setAdded_dept_id(0L);
			}

			if (StringUtils.isNotBlank(up_time)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				entity.setUp_time(sf.parse(up_time));
			}
			if (StringUtils.isNotBlank(down_time)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				entity.setDown_time(sf.parse(down_time));
			}

			super.getFacade().getEcBcompPdUpService().createEcBcompPdUp(entity);
			saveMessage(request, "entity.updated");

		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("prod_type=" + prod_type);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward offShelf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String prod_type = (String) dynaBean.get("prod_type");

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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
				}
			}
		}

		if (GenericValidator.isLong(id)) {
			EcBcompPdUp entity = new EcBcompPdUp();
			entity.setBcomp_pd_id(Long.valueOf(id));
			if (zb) {
				entity.setOwn_sys(1);
				entity.setAdded_dept_id(0L);
			} else if (fgs) {
				entity.setOwn_sys(2);
				entity.setAdded_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			} else {
				entity.setOwn_sys(1);
				entity.setAdded_dept_id(0L);
			}
			entity.setDown_time(new Date());
			super.getFacade().getEcBcompPdUpService().modifyEcBcompPdUp(entity);
			saveMessage(request, "entity.updated");

		}
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("prod_type=" + prod_type);
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;

	}

	public ActionForward getPdSpecList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		String state = "0";
		JSONObject result = new JSONObject();
		JSONArray list = new JSONArray();

		PePdModel entity = new PePdModel();
		entity.setIs_del(0);
		entity.setAudit_state(1);
		List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelWithClsNameAndParClsNameList(entity);// getPePdModelList(entity);

		if (null != pdList && pdList.size() > 0) {
			for (PePdModel pm : pdList) {
				JSONObject obj = new JSONObject();
				obj.put("pd_id", pm.getPd_id());
				obj.put("md_name", pm.getMd_name());
				obj.put("cls_name", pm.getMap().get("cls_name"));
				obj.put("par_cls_name", pm.getMap().get("par_cls_name"));
				list.put(obj);
			}
			state = "1";
			result.put("list", list);
		}

		result.put("state", state);
		// logger.info("_______________________________result = " +
		// result.toString());
		super.renderJson(response, result.toString());
		return null;
	}

}
