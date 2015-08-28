package com.ebiz.mmt.web.struts.manager.spgl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.json.JSONArray;
import org.json.JSONObject;

import com.ebiz.mmt.domain.EcBcompBinding;
import com.ebiz.mmt.domain.EcBcompPdUpNew;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.EcExtend;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcGoodsSallarea;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcProduct;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.domain.EcSallareaTemplate;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-11
 */
public class PdShowNewAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// super.getModPopeDom(form, request);
		// String own_sys = request.getParameter("own_sys");
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String d_like = (String) dynaBean.get("d_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String label_of_cate = (String) dynaBean.get("label_of_cate");
		String prod_type = (String) dynaBean.get("prod_type");
		String is_sj = (String) dynaBean.get("is_sj");
		String is_tj = (String) dynaBean.get("is_tj");
		String own_sys = (String) dynaBean.get("own_sys");
		String lock_state = (String) dynaBean.get("lock_state");
		String is_zb = (String) dynaBean.get("is_zb");
		String dept_sn = (String) dynaBean.get("dept_sn");
		String state = (String) dynaBean.get("state");
		// logger.info("own_sys" + own_sys);
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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
						|| pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
				}
			}
		}
		if (zb) {
			request.setAttribute("is_admin", "1");// 总部

			// entity.getMap().put("dept_id_eq", 0);// 用来区分 总部商品 分公司上下架
			// entity.getMap().put("plat_sys_eq", 0);

			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
		} else if (!zb && fgs) {
			request.setAttribute("is_fgs", "1");// 分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);
			if (deptList != null && deptList.size() > 0) {
				String ids = "";
				// List<String> gids = new ArrayList<String>();
				for (EcGroup ecGroup : deptList) {
					ids = ids + "'" + ecGroup.getId().toString() + "'" + ",";
					// gids.add(ecGroup.getId().toString());
				}
				ids = ids.substring(0, ids.length() - 1);
				//System.out.println("ids-->" + ids);

				// entity.getMap().put("group_id_in", gids);// 用来区分 总部商品 分公司上下架
				// entity.getMap().put("plat_sys_eq", 1);

				entity.getMap().put("dept_id_in", "(\'0\'," + ids + ")");
				dynaBean.set("group_id", ids);
				dynaBean.set("is_binding", true);
			} else {
				entity.getMap().put("dept_id_in", "(\'0\')");
			}
		}

		if (StringUtils.isNotBlank(is_tj)) {
			entity.setIs_tj(Integer.valueOf(is_tj));
		}
		if (StringUtils.isNotBlank(own_sys)) {
			entity.setOwn_sys(Integer.valueOf(own_sys));
		}
		if (StringUtils.isNotBlank(lock_state)) {
			entity.setLock_state(Integer.valueOf(lock_state));
		}
		if (StringUtils.isNotBlank(is_zb)) {
			if (is_zb.equals("1")) {
				entity.setDept_sn("0");
				dynaBean.set("is_zb", "1");
			} else if (is_zb.equals("2")) {
				entity.getMap().put("is_not_zb", true);
				dynaBean.set("is_zb", "2");
			}
		}

		if (StringUtils.isNotBlank(dept_sn)) {
			entity.setDept_sn(dept_sn);
		}
		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(d_like)) {
			entity.getMap().put("d_like", d_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate));
		}
		logger.info("prod_type-------------->{}" + prod_type);
		if (StringUtils.isNotBlank(prod_type)) {
			entity.setProd_type(Integer.valueOf(prod_type));
		}
		// entity.getMap().put("today", DateFormatUtils.format(new Date(),
		// "yyyy-MM-dd HH:mm:ss"));
		if (StringUtils.isNotBlank(is_sj)) {
			if (is_sj.equals("1")) {// 上架
				entity.getMap().put("is_upself", true);
			} else if (is_sj.equals("2")) {// 下架
				entity.getMap().put("is_downself_true", true);
			}
		}

		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		} else {
			entity.setState(1);
		}

		entity.setOwn_sys(2);// 触网
		// entity.getMap().put("own_sys_in", new Integer[] { 1, 2, 4 });

		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdWithDeptAndMdNewCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService()
				.getKonkaBcompPdWithDeptAndMdNewPaginatedList(entity);

		request.setAttribute("entityList", entityList);

		request.setAttribute("today", new Date());

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

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

		if (zb) {
			request.setAttribute("is_admin", "1");
			EcGroup eg = new EcGroup();
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);

			EcSallareaTemplate es = new EcSallareaTemplate();
			es.setState(0);
			List<EcSallareaTemplate> tempList = super.getFacade().getEcSallareaTemplateService()
					.getEcSallareaTemplateList(es);
			for (EcSallareaTemplate ec : tempList) {
				if (ec.getDept_id() != null) {
					EcGroup eg1 = new EcGroup();
					eg1.setId(ec.getDept_id());
					eg1 = super.getFacade().getEcGroupService().getEcGroup(eg1);
					if (eg1 != null) {
						ec.getMap().put("group_name", eg1.getGroup_name());
					}
				}
			}
			request.setAttribute("tempList", tempList);

		} else if (!zb && fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());
			request.setAttribute("is_fgs", "1");

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("deptList", deptList);

			List<EcSallareaTemplate> tempList = new ArrayList<EcSallareaTemplate>();
			if (deptList != null && deptList.size() > 0) {
				for (EcGroup ecGroup : deptList) {
					EcSallareaTemplate es = new EcSallareaTemplate();
					es.setState(0);
					es.setDept_id(ecGroup.getId());
					List<EcSallareaTemplate> tempList1 = super.getFacade().getEcSallareaTemplateService()
							.getEcSallareaTemplateList(es);
					for (EcSallareaTemplate ec : tempList1) {
						tempList.add(ec);
					}
				}
				if (tempList != null && tempList.size() > 0) {
					for (EcSallareaTemplate ec : tempList) {
						if (ec.getDept_id() != null) {
							EcGroup eg1 = new EcGroup();
							eg1.setId(ec.getDept_id());
							eg1 = super.getFacade().getEcGroupService().getEcGroup(eg1);
							if (eg1 != null) {
								ec.getMap().put("group_name", eg1.getGroup_name());
							}
						}
					}
				}
				request.setAttribute("tempList", tempList);
			}

		}

		// 获取主键序列id
		KonkaBcompPd entity = new KonkaBcompPd();
		Long id = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(entity);
		logger.info("id+++++=" + id);
		dynaBean.set("goods_id", id);

		dynaBean.set("is_tj", 0);// 默认不推荐
		dynaBean.set("lock_state", 0);// 默认不锁定
		dynaBean.set("own_sys", 2);// 默认触网
		dynaBean.set("is_allow_voucher", 0);// 默认允许
		dynaBean.set("prod_type", "0");

		return mapping.findForward("input");
	}

	public ActionForward addNew(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String label_of_cate = (String) dynaBean.get("label_of_cate");
		String prod_type = (String) dynaBean.get("prod_type");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcProduct ep = new EcProduct();
		ep.setState(1);
		ep.setOwn_sys(2);// 触网
		if (StringUtils.isNotBlank(pd_sn_like)) {
			ep.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			ep.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(label_of_cate)) {
			ep.setLabel_of_cate(Integer.valueOf(label_of_cate));
		}
		logger.info("prod_type-------------->{}" + prod_type);
		if (StringUtils.isNotBlank(prod_type)) {
			ep.setProd_type(Integer.valueOf(prod_type));
		}

		Long recordCount = super.getFacade().getEcProductService().getEcProductCount(ep);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		ep.getRow().setFirst(pager.getFirstRow());
		ep.getRow().setCount(pager.getRowCount());
		List<EcProduct> entityList = super.getFacade().getEcProductService().getEcProductPaginatedList(ep);

		dynaBean.set("mod_id", mod_id);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/PdShowNew/addNew.jsp");
	}

	public ActionForward addNext(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String ids = (String) dynaBean.get("ids");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		// if (!GenericValidator.isLong(pks)) {
		// return null;
		// }

		EcProduct entity = new EcProduct();
		entity.setId(Long.valueOf(ids));
		entity = super.getFacade().getEcProductService().getEcProduct(entity);

		if (entity == null) {
			return null;
		}

		// 回显产品描述、产品规格、售后服务
		KonkaBcompPdContent pc = new KonkaBcompPdContent();
		pc.setKbp_id(entity.getId());
		List<KonkaBcompPdContent> pct = super.getFacade().getKonkaBcompPdContentService()
				.getKonkaBcompPdContentList(pc);
		for (KonkaBcompPdContent pp : pct) {
			request.setAttribute("content" + pp.getType(), pp.getContent());
		}

		if (null != entity) {
			/* 10系统管理员 20事业部管理员 21事业部领导 22事业部市场部管理员 */
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
			if (zb) {
				request.setAttribute("is_admin", "1");
				EcGroup eg = new EcGroup();
				List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
				request.setAttribute("deptList", deptList);

				EcSallareaTemplate es = new EcSallareaTemplate();
				es.setState(0);
				List<EcSallareaTemplate> tempList = super.getFacade().getEcSallareaTemplateService()
						.getEcSallareaTemplateList(es);
				for (EcSallareaTemplate ec : tempList) {
					if (ec.getDept_id() != null) {
						EcGroup eg1 = new EcGroup();
						eg1.setId(ec.getDept_id());
						eg1 = super.getFacade().getEcGroupService().getEcGroup(eg1);
						if (eg1 != null) {
							ec.getMap().put("group_name", eg1.getGroup_name());
						}
					}
				}
				request.setAttribute("tempList", tempList);
			} else if (!zb && fgs) {
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				dynaBean.set("fgs_id", fgs_dept.getDept_id());
				dynaBean.set("fgs_name", fgs_dept.getDept_name());
				request.setAttribute("is_fgs", "1");

				EcGroup eg = new EcGroup();
				eg.setLink_dept_id(fgs_dept.getDept_id());
				List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
				request.setAttribute("deptList", deptList);

				List<EcSallareaTemplate> tempList = new ArrayList<EcSallareaTemplate>();
				if (deptList != null && deptList.size() > 0) {
					for (EcGroup ecGroup : deptList) {
						EcSallareaTemplate es = new EcSallareaTemplate();
						es.setState(0);
						es.setDept_id(ecGroup.getId());
						List<EcSallareaTemplate> tempList1 = super.getFacade().getEcSallareaTemplateService()
								.getEcSallareaTemplateList(es);
						for (EcSallareaTemplate ec : tempList1) {
							tempList.add(ec);
						}
					}
					if (tempList != null && tempList.size() > 0) {
						for (EcSallareaTemplate ec : tempList) {
							if (ec.getDept_id() != null) {
								EcGroup eg1 = new EcGroup();
								eg1.setId(ec.getDept_id());
								eg1 = super.getFacade().getEcGroupService().getEcGroup(eg1);
								if (eg1 != null) {
									ec.getMap().put("group_name", eg1.getGroup_name());
								}
							}
						}
					}
					request.setAttribute("tempList", tempList);
				}
			}

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
			if (entity.getPd_res() != null && !"".equals(entity.getPd_res())) {
				String pd_res = entity.getPd_res();
				if (StringUtils.split(pd_res, ",").length > 1) {
					dynaBean.set("pd_res_x", StringUtils.split(pd_res, ",")[0]);
					dynaBean.set("pd_res_y", StringUtils.split(pd_res, ",")[1]);
				}
			}
		}

		dynaBean.set("mod_id", mod_id);
		copyProperties(form, entity);
		dynaBean.set("id", null);

		KonkaBcompPd kp = new KonkaBcompPd();
		Long id = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(kp);
		logger.info("id+++++=" + id);
		dynaBean.set("goods_id", id);

		dynaBean.set("is_tj", 0);// 默认不推荐
		dynaBean.set("lock_state", 0);// 默认不锁定
		dynaBean.set("own_sys", 2);// 默认触网
		dynaBean.set("is_allow_voucher", 0);// 默认允许

		return new ActionForward("/../manager/spgl/PdShowNew/addNext.jsp");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String goods_id = (String) dynaBean.get("goods_id");
		logger.info("goods_id++++=" + goods_id);
		// String dept_sn = (String) dynaBean.get("dept_sn");
		String[] label_3d = request.getParameterValues("label_3d");
		String[] label_int = request.getParameterValues("label_int");
		String[] label_www = request.getParameterValues("label_www");
		String[] label_of_cate = request.getParameterValues("label_of_cate");
		String[] label_4k = request.getParameterValues("label_4k");
		// String[] prod_type = request.getParameterValues("prod_type");
		String main_pic_hidden = (String) dynaBean.get("main_pic_hidden");
		String pd_res_x = (String) dynaBean.get("pd_res_x");
		String pd_res_y = (String) dynaBean.get("pd_res_y");
		String[] pic_hidden = request.getParameterValues("pic_hidden");
		String integral_type = (String) dynaBean.get("integral_type");
		String integral = (String) dynaBean.get("integral");
		String lock_state = (String) dynaBean.get("lock_state");

		String content1 = (String) dynaBean.get("content1");
		String content2 = (String) dynaBean.get("content2");
		String content3 = (String) dynaBean.get("content3");
		String own_sys = (String) dynaBean.get("own_sys");

		String template_id = (String) dynaBean.get("template_id");

		String e_id = (String) dynaBean.get("e_id");// 选择服务类套餐id集合
		logger.info("e_id++++++++++++" + e_id);
		String e_id2 = (String) dynaBean.get("e_id2");// 选择商品类套餐id集合

		String[] prop_names = request.getParameterValues("prop_name");
		String[] prop_values = request.getParameterValues("prop_value");
		// logger.info("xxxxxxxx1" + prop_names[0]);
		// logger.info("xxxxxxxx2" + prop_names[1]);
		String[] p_ids = request.getParameterValues("p_ids");
		String[] prop_names_2 = request.getParameterValues("prop_name_2");
		String[] prop_values_2 = request.getParameterValues("prop_value_2");
		String prod_type = (String) dynaBean.get("prod_type");
		String pd_spec = (String) dynaBean.get("pd_spec");
		String[] select2 = request.getParameterValues("select2");

		String main_pic_file = "";
		String pic_file = "";
		String pd_res = "";

		if (StringUtils.isBlank(id)) {
			if (isCancelled(request)) {
				return list(mapping, form, request, response);
			}
			if (!isTokenValid(request)) {
				saveError(request, "errors.token");
				return list(mapping, form, request, response);
			}
			resetToken(request);
		}

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaBcompPd entity = new KonkaBcompPd();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(integral_type)) {
			entity.setIntegral_type(Long.valueOf(integral_type));
		}
		if (StringUtils.isNotBlank(integral)) {
			entity.setIntegral(Long.valueOf(integral));
		}
		if (StringUtils.isNotBlank(own_sys)) {
			entity.setOwn_sys(Integer.valueOf(own_sys));
		}
		if (StringUtils.isNotBlank(pd_spec)) {
			entity.setPd_spec(pd_spec);
		} else {
			entity.setPd_spec("0");
		}
		if (null != pd_res_x && !"".equals(pd_res_x) && null != pd_res_y && !"".equals(pd_res_y)) {
			pd_res = pd_res_x.concat("," + pd_res_y);
			entity.setPd_res(pd_res);
		}

		// 上传主图以及附图
		List<UploadFile> uploadFileList = super.uploadFile(form,MmtFilePathConfig.CHUWANG_PATH, true, 0, new int[] { 60, 86, 95, 120, 240, 280, 350,
				400, 600, 800 });
		if (null != uploadFileList && uploadFileList.size() > 0) {
			// String[] fileArrs = new String[uploadFileList.size()];
			List<String> fileList = new ArrayList<String>();
			for (int i = 0; i < uploadFileList.size(); i++) {
				if ("main_pic_file".equalsIgnoreCase(uploadFileList.get(i).getFormName())) {
					main_pic_file = uploadFileList.get(i).getFileSavePath();
				} else if (uploadFileList.get(i).getFormName().startsWith("pic_file_")) {
					fileList.add(uploadFileList.get(i).getFileSavePath());
				}
			}
			for (String string : fileList) {
				logger.info("______________________________pic_file_" + string);
			}
			pic_file = StringUtils.join(fileList.toArray(), ",");
		}

		if (StringUtils.isBlank(id)) {// 新增商品
			if (StringUtils.isBlank(main_pic_file)) { // 新增产品
				super.renderJavaScript(response, "alert('主图没有上传！');history.back();");
				return null;
			}
		} else {// 修改商品
			if (StringUtils.isBlank(main_pic_file)) { // 没用重新上传主图
				main_pic_file = main_pic_hidden;
			}
			if (StringUtils.isBlank(pic_file)) { // 没有重新上传附图
				if (null != pic_hidden && pic_hidden.length > 0) {
					pic_file = StringUtils.join(pic_hidden, ",");
				}
			} else {
				if (null != pic_hidden && pic_hidden.length > 0) {
					pic_file = pic_file.concat("," + StringUtils.join(pic_hidden, ","));
				}
			}
		}
		logger.info("____________________________pic_file = " + pic_file);
		if (StringUtils.isNotBlank(pic_file)) {
			entity.setMain_pic(main_pic_file.concat("," + pic_file));
		} else {
			entity.setMain_pic(main_pic_file);
		}

		if (ArrayUtils.isEmpty(label_3d)) {
			entity.setLabel_3d(0);
		} else {
			entity.setLabel_3d(Integer.valueOf(label_3d[0]));
		}
		if (ArrayUtils.isEmpty(label_int)) {
			entity.setLabel_int(0);
		} else {
			entity.setLabel_int(Integer.valueOf(label_int[0]));
		}
		if (ArrayUtils.isEmpty(label_www)) {
			entity.setLabel_www(0);
		} else {
			entity.setLabel_www(Integer.valueOf(label_www[0]));
		}
		if (ArrayUtils.isEmpty(label_4k)) {
			entity.setLabel_4k(0);
		} else {
			entity.setLabel_4k(Integer.valueOf(label_4k[0]));
		}
		if (ArrayUtils.isNotEmpty(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate[0]));
		} else {
			entity.setLabel_of_cate(null);
		}

		if (StringUtils.isNotBlank(prod_type)) {
			entity.setProd_type(Integer.valueOf(prod_type));
		}
		if (StringUtils.isNotBlank(lock_state)) {
			entity.setLock_state(Integer.valueOf(lock_state));
		}

		entity.getMap().put("template_id", template_id);

		logger.info("+++++++++++++id" + id);
		if (StringUtils.isBlank(id)) {
			entity.setId(Long.valueOf(goods_id));// 序列id
			entity.setAdd_u_id(user.getId());
			List<KonkaBcompPdContent> konkaBcompPdContentList = new ArrayList<KonkaBcompPdContent>();
			KonkaBcompPdContent konkaBcompPdContent_1 = new KonkaBcompPdContent();
			logger.info("+++++++goods_id" + goods_id);
			konkaBcompPdContent_1.setKbp_id(Long.valueOf(goods_id));
			konkaBcompPdContent_1.setType(1);
			konkaBcompPdContent_1.setContent(content1);
			konkaBcompPdContentList.add(konkaBcompPdContent_1);

			KonkaBcompPdContent konkaBcompPdContent_2 = new KonkaBcompPdContent();
			konkaBcompPdContent_2.setKbp_id(Long.valueOf(goods_id));
			konkaBcompPdContent_2.setType(2);
			konkaBcompPdContent_2.setContent(content2);
			konkaBcompPdContentList.add(konkaBcompPdContent_2);

			KonkaBcompPdContent konkaBcompPdContent_3 = new KonkaBcompPdContent();
			konkaBcompPdContent_3.setKbp_id(Long.valueOf(goods_id));
			konkaBcompPdContent_3.setType(3);
			konkaBcompPdContent_3.setContent(content3);
			konkaBcompPdContentList.add(konkaBcompPdContent_3);

			entity.setKonkaBcompPdContentList(konkaBcompPdContentList);

			List<EcExtend> ecList = new ArrayList<EcExtend>();
			if (null != prop_names && prop_names.length > 0) {
				for (int i = 0; i < prop_names.length; i++) {
					KonkaBcompPd kbp = new KonkaBcompPd();
					Long kbid = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(kbp);
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names[i];
					String prop_value = prop_values[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					cur.setAdd_date(new Date());
					cur.setAdd_user_id(user.getId());
					// cur.setLink_id(Long.valueOf(id));
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setProp_name_id(kbid);
					ecList.add(cur);
				}
			}

			List<EcExtend> ecList2 = new ArrayList<EcExtend>();
			if (null != prop_names_2 && prop_names_2.length > 0) {
				for (int i = 0; i < prop_names_2.length; i++) {
					KonkaBcompPd kbp = new KonkaBcompPd();
					Long kbid = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(kbp);
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names_2[i];
					String prop_value = prop_values_2[i];
					// String p_id = p_ids[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setProp_name_id(kbid);
					cur.setAdd_date(new Date());
					cur.setAdd_user_id(user.getId());
					// cur.setId(Long.valueOf(p_id));
					ecList2.add(cur);
				}
			}

			super.getFacade().getKonkaBcompPdService()
					.createKonkaBcompPdIncludeContent2(entity, e_id, e_id2, ecList, ecList2);

		} else {
			entity.setId(Long.valueOf(id));

			// 初始化 内容，规格，售后
			List<KonkaBcompPdContent> konkaBcompPdContentList = new ArrayList<KonkaBcompPdContent>();

			// 内容0000
			KonkaBcompPdContent konkaBcompPdContent_1 = new KonkaBcompPdContent();
			konkaBcompPdContent_1.setType(1);
			konkaBcompPdContent_1.setContent(content1);
			konkaBcompPdContentList.add(konkaBcompPdContent_1);

			// 规格
			KonkaBcompPdContent konkaBcompPdContent_2 = new KonkaBcompPdContent();
			konkaBcompPdContent_2.setType(2);
			konkaBcompPdContent_2.setContent(content2);
			konkaBcompPdContentList.add(konkaBcompPdContent_2);

			// 售后
			KonkaBcompPdContent konkaBcompPdContent_3 = new KonkaBcompPdContent();
			konkaBcompPdContent_3.setType(3);
			konkaBcompPdContent_3.setContent(content3);
			konkaBcompPdContentList.add(konkaBcompPdContent_3);

			entity.setKonkaBcompPdContentList(konkaBcompPdContentList);

			List<EcExtend> ecList = new ArrayList<EcExtend>();
			if (null != prop_names && prop_names.length > 0) {
				for (int i = 0; i < prop_names.length; i++) {
					KonkaBcompPd kbp = new KonkaBcompPd();
					Long kbid = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(kbp);
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names[i];
					String prop_value = prop_values[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					cur.setAdd_date(new Date());
					cur.setAdd_user_id(user.getId());
					cur.setLink_id(Long.valueOf(id));
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setProp_name_id(kbid);
					ecList.add(cur);
				}
			}

			List<EcExtend> ecList2 = new ArrayList<EcExtend>();
			if (null != prop_names_2 && prop_names_2.length > 0) {
				for (int i = 0; i < prop_names_2.length; i++) {
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names_2[i];
					String prop_value = prop_values_2[i];
					String p_id = p_ids[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setId(Long.valueOf(p_id));
					ecList2.add(cur);
				}
			}

			entity.getMap().put("select2", select2);
			EcRuleGoods erg = new EcRuleGoods();
			erg.setGoods_id(Long.valueOf(id));
			List<EcRuleGoods> listRules = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(erg);
			if (null != listRules && listRules.size() > 0) {
				entity.getMap().put("select1", listRules);
			}
			entity.getMap().put("user_id", user.getId());

			// super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPdIncludeContent(entity,
			// e_id, e_id2);
			super.getFacade().getKonkaBcompPdService()
					.modifyKonkaBcompPdIncludeContent2(entity, e_id, e_id2, ecList, ecList2);
			saveMessage(request, "entity.updated");
		}

		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward saveNew(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String goods_id = (String) dynaBean.get("goods_id");
		logger.info("goods_id++++=" + goods_id);
		// String dept_sn = (String) dynaBean.get("dept_sn");
		String[] label_3d = request.getParameterValues("label_3d");
		String[] label_int = request.getParameterValues("label_int");
		String[] label_www = request.getParameterValues("label_www");
		String[] label_of_cate = request.getParameterValues("label_of_cate");
		String[] label_4k = request.getParameterValues("label_4k");
		// String[] prod_type = request.getParameterValues("prod_type");
		String main_pic_hidden = (String) dynaBean.get("main_pic_hidden");
		String pd_res_x = (String) dynaBean.get("pd_res_x");
		String pd_res_y = (String) dynaBean.get("pd_res_y");
		String[] pic_hidden = request.getParameterValues("pic_hidden");
		String integral_type = (String) dynaBean.get("integral_type");
		String integral = (String) dynaBean.get("integral");
		String lock_state = (String) dynaBean.get("lock_state");

		String content1 = (String) dynaBean.get("content1");
		String content2 = (String) dynaBean.get("content2");
		String content3 = (String) dynaBean.get("content3");
		String own_sys = (String) dynaBean.get("own_sys");

		String template_id = (String) dynaBean.get("template_id");

		String e_id = (String) dynaBean.get("e_id");// 选择服务类套餐id集合
		logger.info("e_id++++++++++++" + e_id);
		String e_id2 = (String) dynaBean.get("e_id2");// 选择商品类套餐id集合

		String[] prop_names = request.getParameterValues("prop_name");
		String[] prop_values = request.getParameterValues("prop_value");
		// logger.info("xxxxxxxx1" + prop_names[0]);
		// logger.info("xxxxxxxx2" + prop_names[1]);
		String[] p_ids = request.getParameterValues("p_ids");
		String[] prop_names_2 = request.getParameterValues("prop_name_2");
		String[] prop_values_2 = request.getParameterValues("prop_value_2");
		String prod_type = (String) dynaBean.get("prod_type");
		String pd_spec = (String) dynaBean.get("pd_spec");
		String[] select2 = request.getParameterValues("select2");

		String main_pic_file = "";
		String pic_file = "";
		String pd_res = "";

		/*
		 * if (StringUtils.isBlank(id)) { if (isCancelled(request)) { return list(mapping, form, request, response); }
		 * if (!isTokenValid(request)) { saveError(request, "errors.token"); return list(mapping, form, request,
		 * response); } resetToken(request); }
		 */

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaBcompPd entity = new KonkaBcompPd();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(integral_type)) {
			entity.setIntegral_type(Long.valueOf(integral_type));
		}
		if (StringUtils.isNotBlank(integral)) {
			entity.setIntegral(Long.valueOf(integral));
		}
		if (StringUtils.isNotBlank(own_sys)) {
			entity.setOwn_sys(Integer.valueOf(own_sys));
		}
		if (StringUtils.isNotBlank(pd_spec)) {
			entity.setPd_spec(pd_spec);
		} else {
			entity.setPd_spec("0");
		}
		if (null != pd_res_x && !"".equals(pd_res_x) && null != pd_res_y && !"".equals(pd_res_y)) {
			pd_res = pd_res_x.concat("," + pd_res_y);
			entity.setPd_res(pd_res);
		}

		// 上传主图以及附图
		List<UploadFile> uploadFileList = super.uploadFile(form,MmtFilePathConfig.CHUWANG_PATH,  true, 0, new int[] { 60, 86, 95, 120, 240, 280, 350,
				400, 600, 800 });
		if (null != uploadFileList && uploadFileList.size() > 0) {
			// String[] fileArrs = new String[uploadFileList.size()];
			List<String> fileList = new ArrayList<String>();
			for (int i = 0; i < uploadFileList.size(); i++) {
				if ("main_pic_file".equalsIgnoreCase(uploadFileList.get(i).getFormName())) {
					main_pic_file = uploadFileList.get(i).getFileSavePath();
				} else if (uploadFileList.get(i).getFormName().startsWith("pic_file_")) {
					fileList.add(uploadFileList.get(i).getFileSavePath());
				}
			}
			for (String string : fileList) {
				logger.info("______________________________pic_file_" + string);
			}
			pic_file = StringUtils.join(fileList.toArray(), ",");
		}

		// 修改商品
		if (StringUtils.isBlank(main_pic_file)) { // 没用重新上传主图
			main_pic_file = main_pic_hidden;
		}
		if (StringUtils.isBlank(pic_file)) { // 没有重新上传附图
			if (null != pic_hidden && pic_hidden.length > 0) {
				pic_file = StringUtils.join(pic_hidden, ",");
			}
		} else {
			if (null != pic_hidden && pic_hidden.length > 0) {
				pic_file = pic_file.concat("," + StringUtils.join(pic_hidden, ","));
			}
		}
		logger.info("____________________________pic_file = " + pic_file);
		if (StringUtils.isNotBlank(pic_file)) {
			entity.setMain_pic(main_pic_file.concat("," + pic_file));
		} else {
			entity.setMain_pic(main_pic_file);
		}

		if (ArrayUtils.isEmpty(label_3d)) {
			entity.setLabel_3d(0);
		} else {
			entity.setLabel_3d(Integer.valueOf(label_3d[0]));
		}
		if (ArrayUtils.isEmpty(label_int)) {
			entity.setLabel_int(0);
		} else {
			entity.setLabel_int(Integer.valueOf(label_int[0]));
		}
		if (ArrayUtils.isEmpty(label_www)) {
			entity.setLabel_www(0);
		} else {
			entity.setLabel_www(Integer.valueOf(label_www[0]));
		}
		if (ArrayUtils.isEmpty(label_4k)) {
			entity.setLabel_4k(0);
		} else {
			entity.setLabel_4k(Integer.valueOf(label_4k[0]));
		}
		if (ArrayUtils.isNotEmpty(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate[0]));
		} else {
			entity.setLabel_of_cate(null);
		}

		if (StringUtils.isNotBlank(prod_type)) {
			entity.setProd_type(Integer.valueOf(prod_type));
		}
		if (StringUtils.isNotBlank(lock_state)) {
			entity.setLock_state(Integer.valueOf(lock_state));
		}

		entity.getMap().put("template_id", template_id);

		logger.info("+++++++++++++id" + id);
		if (StringUtils.isBlank(id)) {
			entity.setId(Long.valueOf(goods_id));// 序列id
			entity.setAdd_u_id(user.getId());
			List<KonkaBcompPdContent> konkaBcompPdContentList = new ArrayList<KonkaBcompPdContent>();
			KonkaBcompPdContent konkaBcompPdContent_1 = new KonkaBcompPdContent();
			logger.info("+++++++goods_id" + goods_id);
			konkaBcompPdContent_1.setKbp_id(Long.valueOf(goods_id));
			konkaBcompPdContent_1.setType(1);
			konkaBcompPdContent_1.setContent(content1);
			konkaBcompPdContentList.add(konkaBcompPdContent_1);

			KonkaBcompPdContent konkaBcompPdContent_2 = new KonkaBcompPdContent();
			konkaBcompPdContent_2.setKbp_id(Long.valueOf(goods_id));
			konkaBcompPdContent_2.setType(2);
			konkaBcompPdContent_2.setContent(content2);
			konkaBcompPdContentList.add(konkaBcompPdContent_2);

			KonkaBcompPdContent konkaBcompPdContent_3 = new KonkaBcompPdContent();
			konkaBcompPdContent_3.setKbp_id(Long.valueOf(goods_id));
			konkaBcompPdContent_3.setType(3);
			konkaBcompPdContent_3.setContent(content3);
			konkaBcompPdContentList.add(konkaBcompPdContent_3);

			entity.setKonkaBcompPdContentList(konkaBcompPdContentList);

			List<EcExtend> ecList = new ArrayList<EcExtend>();
			if (null != prop_names && prop_names.length > 0) {
				for (int i = 0; i < prop_names.length; i++) {
					KonkaBcompPd kbp = new KonkaBcompPd();
					Long kbid = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(kbp);
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names[i];
					String prop_value = prop_values[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					cur.setAdd_date(new Date());
					cur.setAdd_user_id(user.getId());
					// cur.setLink_id(Long.valueOf(id));
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setProp_name_id(kbid);
					ecList.add(cur);
				}
			}

			List<EcExtend> ecList2 = new ArrayList<EcExtend>();
			if (null != prop_names_2 && prop_names_2.length > 0) {
				for (int i = 0; i < prop_names_2.length; i++) {
					KonkaBcompPd kbp = new KonkaBcompPd();
					Long kbid = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(kbp);
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names_2[i];
					String prop_value = prop_values_2[i];
					// String p_id = p_ids[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setProp_name_id(kbid);
					cur.setAdd_date(new Date());
					cur.setAdd_user_id(user.getId());
					// cur.setId(Long.valueOf(p_id));
					ecList2.add(cur);
				}
			}

			super.getFacade().getKonkaBcompPdService()
					.createKonkaBcompPdIncludeContent2(entity, e_id, e_id2, ecList, ecList2);

		} else {
			entity.setId(Long.valueOf(id));

			// 初始化 内容，规格，售后
			List<KonkaBcompPdContent> konkaBcompPdContentList = new ArrayList<KonkaBcompPdContent>();

			// 内容0000
			KonkaBcompPdContent konkaBcompPdContent_1 = new KonkaBcompPdContent();
			konkaBcompPdContent_1.setType(1);
			konkaBcompPdContent_1.setContent(content1);
			konkaBcompPdContentList.add(konkaBcompPdContent_1);

			// 规格
			KonkaBcompPdContent konkaBcompPdContent_2 = new KonkaBcompPdContent();
			konkaBcompPdContent_2.setType(2);
			konkaBcompPdContent_2.setContent(content2);
			konkaBcompPdContentList.add(konkaBcompPdContent_2);

			// 售后
			KonkaBcompPdContent konkaBcompPdContent_3 = new KonkaBcompPdContent();
			konkaBcompPdContent_3.setType(3);
			konkaBcompPdContent_3.setContent(content3);
			konkaBcompPdContentList.add(konkaBcompPdContent_3);

			entity.setKonkaBcompPdContentList(konkaBcompPdContentList);

			List<EcExtend> ecList = new ArrayList<EcExtend>();
			if (null != prop_names && prop_names.length > 0) {
				for (int i = 0; i < prop_names.length; i++) {
					KonkaBcompPd kbp = new KonkaBcompPd();
					Long kbid = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(kbp);
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names[i];
					String prop_value = prop_values[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					cur.setAdd_date(new Date());
					cur.setAdd_user_id(user.getId());
					cur.setLink_id(Long.valueOf(id));
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setProp_name_id(kbid);
					ecList.add(cur);
				}
			}

			List<EcExtend> ecList2 = new ArrayList<EcExtend>();
			if (null != prop_names_2 && prop_names_2.length > 0) {
				for (int i = 0; i < prop_names_2.length; i++) {
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names_2[i];
					String prop_value = prop_values_2[i];
					String p_id = p_ids[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setId(Long.valueOf(p_id));
					ecList2.add(cur);
				}
			}

			entity.getMap().put("select2", select2);
			EcRuleGoods erg = new EcRuleGoods();
			erg.setGoods_id(Long.valueOf(id));
			List<EcRuleGoods> listRules = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(erg);
			if (null != listRules && listRules.size() > 0) {
				entity.getMap().put("select1", listRules);
			}
			entity.getMap().put("user_id", user.getId());

			// super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPdIncludeContent(entity,
			// e_id, e_id2);
			super.getFacade().getKonkaBcompPdService()
					.modifyKonkaBcompPdIncludeContent2(entity, e_id, e_id2, ecList, ecList2);
			saveMessage(request, "entity.updated");
		}

		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 复制保存
	public ActionForward saveCopy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String goods_id = (String) dynaBean.get("goods_id");
		logger.info("goods_id++++=" + goods_id);
		String[] label_3d = request.getParameterValues("label_3d");
		String[] label_int = request.getParameterValues("label_int");
		String[] label_www = request.getParameterValues("label_www");
		String[] label_of_cate = request.getParameterValues("label_of_cate");
		String[] label_4k = request.getParameterValues("label_4k");
		// String[] prod_type = request.getParameterValues("prod_type");
		String main_pic_hidden = (String) dynaBean.get("main_pic_hidden");
		String pd_res_x = (String) dynaBean.get("pd_res_x");
		String pd_res_y = (String) dynaBean.get("pd_res_y");
		String[] pic_hidden = request.getParameterValues("pic_hidden");
		String integral_type = (String) dynaBean.get("integral_type");
		String integral = (String) dynaBean.get("integral");
		String lock_state = (String) dynaBean.get("lock_state");

		String content1 = (String) dynaBean.get("content1");
		String content2 = (String) dynaBean.get("content2");
		String content3 = (String) dynaBean.get("content3");
		String own_sys = (String) dynaBean.get("own_sys");

		String e_id = (String) dynaBean.get("e_id");// 选择服务类套餐id集合
		logger.info("e_id++++++++++++" + e_id);
		String e_id2 = (String) dynaBean.get("e_id2");// 选择商品类套餐id集合

		String[] prop_names = request.getParameterValues("prop_name");
		String[] prop_values = request.getParameterValues("prop_value");
		// logger.info("xxxxxxxx1" + prop_names[0]);
		// logger.info("xxxxxxxx2" + prop_names[1]);
		// String[] p_ids = request.getParameterValues("p_ids");
		String[] prop_names_2 = request.getParameterValues("prop_name_2");
		String[] prop_values_2 = request.getParameterValues("prop_value_2");
		String prod_type = (String) dynaBean.get("prod_type");
		String pd_spec = (String) dynaBean.get("pd_spec");
		String[] select2 = request.getParameterValues("select2");

		String main_pic_file = "";
		String pic_file = "";
		String pd_res = "";

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaBcompPd entity = new KonkaBcompPd();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(integral_type)) {
			entity.setIntegral_type(Long.valueOf(integral_type));
		}
		if (StringUtils.isNotBlank(integral)) {
			entity.setIntegral(Long.valueOf(integral));
		}
		if (StringUtils.isNotBlank(pd_spec)) {
			entity.setPd_spec(pd_spec);
		} else {
			entity.setPd_spec("0");
		}

		if (StringUtils.isNotBlank(own_sys)) {
			entity.setOwn_sys(Integer.valueOf(own_sys));
		}

		if (null != pd_res_x && !"".equals(pd_res_x) && null != pd_res_y && !"".equals(pd_res_y)) {
			pd_res = pd_res_x.concat("," + pd_res_y);
			entity.setPd_res(pd_res);
		}

		// 上传主图以及附图
		List<UploadFile> uploadFileList = super.uploadFile(form,MmtFilePathConfig.CHUWANG_PATH,  true, 0, new int[] { 60, 86, 95, 120, 240, 280, 350,
				400, 600, 800 });
		if (null != uploadFileList && uploadFileList.size() > 0) {
			// String[] fileArrs = new String[uploadFileList.size()];
			List<String> fileList = new ArrayList<String>();
			for (int i = 0; i < uploadFileList.size(); i++) {
				if ("main_pic_file".equalsIgnoreCase(uploadFileList.get(i).getFormName())) {
					main_pic_file = uploadFileList.get(i).getFileSavePath();
				} else if (uploadFileList.get(i).getFormName().startsWith("pic_file_")) {
					fileList.add(uploadFileList.get(i).getFileSavePath());
				}
			}
			for (String string : fileList) {
				logger.info("______________________________pic_file_" + string);
			}
			pic_file = StringUtils.join(fileList.toArray(), ",");
		}

		if (StringUtils.isBlank(goods_id)) {// 新增商品
			if (StringUtils.isBlank(main_pic_file)) { // 新增产品
				super.renderJavaScript(response, "alert('主图没有上传！');history.back();");
				return null;
			}
		} else {// 修改商品
			if (StringUtils.isBlank(main_pic_file)) { // 没用重新上传主图
				main_pic_file = main_pic_hidden;
			}
			if (StringUtils.isBlank(pic_file)) { // 没有重新上传附图
				if (null != pic_hidden && pic_hidden.length > 0) {
					pic_file = StringUtils.join(pic_hidden, ",");
				}
			} else {
				if (null != pic_hidden && pic_hidden.length > 0) {
					pic_file = pic_file.concat("," + StringUtils.join(pic_hidden, ","));
				}
			}
		}
		logger.info("____________________________pic_file = " + pic_file);
		if (StringUtils.isNotBlank(pic_file)) {
			entity.setMain_pic(main_pic_file.concat("," + pic_file));
		} else {
			entity.setMain_pic(main_pic_file);
		}

		if (ArrayUtils.isEmpty(label_3d)) {
			entity.setLabel_3d(0);
		} else {
			entity.setLabel_3d(Integer.valueOf(label_3d[0]));
		}
		if (ArrayUtils.isEmpty(label_int)) {
			entity.setLabel_int(0);
		} else {
			entity.setLabel_int(Integer.valueOf(label_int[0]));
		}
		if (ArrayUtils.isEmpty(label_www)) {
			entity.setLabel_www(0);
		} else {
			entity.setLabel_www(Integer.valueOf(label_www[0]));
		}
		if (ArrayUtils.isEmpty(label_4k)) {
			entity.setLabel_4k(0);
		} else {
			entity.setLabel_4k(Integer.valueOf(label_4k[0]));
		}
		if (ArrayUtils.isNotEmpty(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate[0]));
		}
		// if (ArrayUtils.isNotEmpty(prod_type)) {
		// entity.setProd_type(Integer.valueOf(prod_type[0]));
		// }
		if (StringUtils.isNotBlank(prod_type)) {
			entity.setProd_type(Integer.valueOf(prod_type));
		}
		if (StringUtils.isNotBlank(lock_state)) {
			entity.setLock_state(Integer.valueOf(lock_state));
		}

		logger.info("+++++++++++++id" + id);
		if (StringUtils.isBlank(id)) {
			entity.setId(Long.valueOf(goods_id));// 序列id
			entity.setAdd_u_id(user.getId());
			List<KonkaBcompPdContent> konkaBcompPdContentList = new ArrayList<KonkaBcompPdContent>();
			KonkaBcompPdContent konkaBcompPdContent_1 = new KonkaBcompPdContent();
			logger.info("+++++++goods_id" + goods_id);
			konkaBcompPdContent_1.setKbp_id(Long.valueOf(goods_id));
			konkaBcompPdContent_1.setType(1);
			konkaBcompPdContent_1.setContent(content1);
			konkaBcompPdContentList.add(konkaBcompPdContent_1);

			KonkaBcompPdContent konkaBcompPdContent_2 = new KonkaBcompPdContent();
			konkaBcompPdContent_2.setKbp_id(Long.valueOf(goods_id));
			konkaBcompPdContent_2.setType(2);
			konkaBcompPdContent_2.setContent(content2);
			konkaBcompPdContentList.add(konkaBcompPdContent_2);

			KonkaBcompPdContent konkaBcompPdContent_3 = new KonkaBcompPdContent();
			konkaBcompPdContent_3.setKbp_id(Long.valueOf(goods_id));
			konkaBcompPdContent_3.setType(3);
			konkaBcompPdContent_3.setContent(content3);
			konkaBcompPdContentList.add(konkaBcompPdContent_3);

			entity.setKonkaBcompPdContentList(konkaBcompPdContentList);

			List<EcExtend> ecList = new ArrayList<EcExtend>();
			if (null != prop_names && prop_names.length > 0) {
				for (int i = 0; i < prop_names.length; i++) {
					KonkaBcompPd kbp = new KonkaBcompPd();
					Long kbid = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(kbp);
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names[i];
					String prop_value = prop_values[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					cur.setAdd_date(new Date());
					cur.setAdd_user_id(user.getId());
					// cur.setLink_id(Long.valueOf(id));
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setProp_name_id(kbid);
					ecList.add(cur);
				}
			}

			List<EcExtend> ecList2 = new ArrayList<EcExtend>();
			if (null != prop_names_2 && prop_names_2.length > 0) {
				for (int i = 0; i < prop_names_2.length; i++) {
					KonkaBcompPd kbp = new KonkaBcompPd();
					Long kbid = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(kbp);
					EcExtend cur = new EcExtend();
					super.copyProperties(cur, entity);
					String prop_name = prop_names_2[i];
					String prop_value = prop_values_2[i];
					// String p_id = p_ids[i];
					cur.setProp_name(prop_name);
					cur.setProp_value(prop_value);
					// if (StringUtils.isNotBlank(prod_type[0])) {
					// cur.setProd_type(Integer.valueOf(prod_type[0]));
					//
					// }
					if (StringUtils.isNotBlank(prod_type)) {
						cur.setProd_type(Integer.valueOf(prod_type));

					}
					cur.setProp_name_id(kbid);
					cur.setAdd_date(new Date());
					cur.setAdd_user_id(user.getId());
					// cur.setId(Long.valueOf(p_id));
					ecList2.add(cur);
				}
			}
			entity.getMap().put("select2", select2);
			super.getFacade().getKonkaBcompPdService()
					.createKonkaBcompPdIncludeContent2(entity, e_id, e_id2, ecList, ecList2);

		}

		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (GenericValidator.isLong(id)) {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
			/* 10系统管理员 20事业部管理员 21事业部领导 22事业部市场部管理员 */
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
			if (zb) {
				request.setAttribute("is_admin", "1");
				EcGroup eg = new EcGroup();
				List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
				request.setAttribute("deptList", deptList);

				EcSallareaTemplate es = new EcSallareaTemplate();
				es.setState(0);
				List<EcSallareaTemplate> tempList = super.getFacade().getEcSallareaTemplateService()
						.getEcSallareaTemplateList(es);
				for (EcSallareaTemplate ec : tempList) {
					if (ec.getDept_id() != null) {
						EcGroup eg1 = new EcGroup();
						eg1.setId(ec.getDept_id());
						eg1 = super.getFacade().getEcGroupService().getEcGroup(eg1);
						if (eg1 != null) {
							ec.getMap().put("group_name", eg1.getGroup_name());
						}
					}
				}
				request.setAttribute("tempList", tempList);
			} else if (!zb && fgs) {
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				dynaBean.set("fgs_id", fgs_dept.getDept_id());
				dynaBean.set("fgs_name", fgs_dept.getDept_name());
				request.setAttribute("is_fgs", "1");

				EcGroup eg = new EcGroup();
				eg.setLink_dept_id(fgs_dept.getDept_id());
				List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
				request.setAttribute("deptList", deptList);

				List<EcSallareaTemplate> tempList = new ArrayList<EcSallareaTemplate>();
				if (deptList != null && deptList.size() > 0) {
					for (EcGroup ecGroup : deptList) {
						EcSallareaTemplate es = new EcSallareaTemplate();
						es.setState(0);
						es.setDept_id(ecGroup.getId());
						List<EcSallareaTemplate> tempList1 = super.getFacade().getEcSallareaTemplateService()
								.getEcSallareaTemplateList(es);
						for (EcSallareaTemplate ec : tempList1) {
							tempList.add(ec);
						}
					}
					if (tempList != null && tempList.size() > 0) {
						for (EcSallareaTemplate ec : tempList) {
							if (ec.getDept_id() != null) {
								EcGroup eg1 = new EcGroup();
								eg1.setId(ec.getDept_id());
								eg1 = super.getFacade().getEcGroupService().getEcGroup(eg1);
								if (eg1 != null) {
									ec.getMap().put("group_name", eg1.getGroup_name());
								}
							}
						}
					}
					request.setAttribute("tempList", tempList);
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
				if (entity.getPd_res() != null && !"".equals(entity.getPd_res())) {
					String pd_res = entity.getPd_res();
					if (StringUtils.split(pd_res, ",").length > 1) {
						dynaBean.set("pd_res_x", StringUtils.split(pd_res, ",")[0]);
						dynaBean.set("pd_res_y", StringUtils.split(pd_res, ",")[1]);
					}
				}
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			EcGoodsSallarea es = new EcGoodsSallarea();
			es.setGoods_id(Long.valueOf(id));
			es = super.getFacade().getEcGoodsSallareaService().getEcGoodsSallarea(es);
			if (es != null) {
				dynaBean.set("template_id", es.getTemplate_id());
			}

			// 回显产品描述、产品规格、售后服务
			KonkaBcompPdContent pc = new KonkaBcompPdContent();
			pc.setKbp_id(Long.valueOf(id));
			List<KonkaBcompPdContent> pct = super.getFacade().getKonkaBcompPdContentService()
					.getKonkaBcompPdContentList(pc);
			for (KonkaBcompPdContent pp : pct) {
				request.setAttribute("content" + pp.getType(), pp.getContent());
			}

			// 回显商品规则
			EcRuleGoods yy = new EcRuleGoods();
			yy.setGoods_id(Long.valueOf(id));
			List<EcRuleGoods> yyList1 = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(yy);

			List<EcRuleGoods> yyList = new ArrayList<EcRuleGoods>();

			for (EcRuleGoods ecRuleGoods : yyList1) {
				EcRule ec = new EcRule();
				ec.setId(ecRuleGoods.getRule_id());
				ec = super.getFacade().getEcRuleService().getEcRule(ec);
				if (null != ec && ec.getInfo_state().intValue() == 1) {
					yyList.add(ecRuleGoods);
				}
			}

			List<String> ids = new ArrayList<String>();
			List<EcRule> list2 = new ArrayList<EcRule>();
			if (null != yyList && yyList.size() > 0) {
				for (EcRuleGoods ecRuleGoods : yyList) {
					EcRule rr = new EcRule();
					// rr.setInfo_state(1);
					rr.setId(ecRuleGoods.getRule_id());
					rr = super.getFacade().getEcRuleService().getEcRule(rr);
					ids.add(ecRuleGoods.getRule_id().toString());
					list2.add(rr);
				}
			}
			request.setAttribute("list2", list2);

			EcRule tt = new EcRule();
			// tt.setInfo_state(1);
			if (null != ids && ids.size() > 0) {
				tt.getMap().put("id_not_in", ids);
			}
			tt.setInfo_state(1);// 启用
			List<EcRule> list1 = super.getFacade().getEcRuleService().getEcRuleList(tt);
			request.setAttribute("list1", list1);

			// 回显绑定的套餐
			// 1829@1828@
			String e_ids = "";
			List<Long> had_binding_idList = new ArrayList<Long>();
			EcBcompBinding ebb = new EcBcompBinding();
			ebb.setGoods_id(Long.valueOf(id));
			List<EcBcompBinding> ebbList = super.getFacade().getEcBcompBindingService().getEcBcompBindingList(ebb);
			if (null != ebbList && 0 != ebbList.size()) {
				for (EcBcompBinding ecBcompBinding : ebbList) {
					had_binding_idList.add(ecBcompBinding.getBinding_id());
					e_ids += ecBcompBinding.getBinding_id().toString() + "@";
				}
				dynaBean.set("e_ids", e_ids);
				logger.info("e_ids======>{}" + e_ids);
			}

			// 回显服务套餐
			EcBindingPd epd = new EcBindingPd();
			epd.setBinding_type(0);
			if (null != had_binding_idList && 0 != had_binding_idList.size()) {
				// 查询绑定的服务
				epd.getMap().put("id_in", had_binding_idList);
				List<EcBindingPd> ecBindingPdList = super.getFacade().getEcBindingPdService().getEcBindingPdList(epd);
				request.setAttribute("hadEntityList", ecBindingPdList);
			} else {
				request.setAttribute("hadEntityList", null);
			}

			EcExtend ec = new EcExtend();
			ec.setLink_id(Long.valueOf(id));
			ec.setProd_type(entity.getProd_type());
			List<EcExtend> ecList = super.getFacade().getEcExtendService().getEcExtendList(ec);
			for (EcExtend ecExtend : ecList) {
				EcExtend ee = new EcExtend();
				ee.setProp_name(ecExtend.getProp_name());
				List<EcExtend> eeList = super.getFacade().getEcExtendService()
						.getEcExtendGroupByPropNameForPropValueList(ee);
				ecExtend.setEcExtendList(eeList);
			}
			request.setAttribute("ecList", ecList);

			// 回显商品套餐
			EcBindingPd epd1 = new EcBindingPd();
			epd1.setBinding_type(1);
			if (null != had_binding_idList && 0 != had_binding_idList.size()) {
				// 查询绑定的服务
				epd1.getMap().put("id_in", had_binding_idList);
				List<EcBindingPd> ecBindingPdList = super.getFacade().getEcBindingPdService().getEcBindingPdList(epd1);
				request.setAttribute("hadEntityList1", ecBindingPdList);
			} else {
				request.setAttribute("hadEntityList1", null);
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
		return mapping.findForward("input");
	}

	// 复制编辑
	public ActionForward editCopy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaBcompPd entity1 = new KonkaBcompPd();
		Long id1 = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(entity1);
		logger.info("id+++++=" + id1);

		if (GenericValidator.isLong(id)) {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
			/* 10系统管理员 20事业部管理员 21事业部领导 22事业部市场部管理员 */
			boolean zb = false;
			boolean fgs = false;
			PeRoleUser rUser = new PeRoleUser();
			rUser.setUser_id(user.getId());
			List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
			if (null != roleUserList && roleUserList.size() > 0) {
				for (PeRoleUser pu : roleUserList) {
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178
							|| pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}
			if (zb) {
				request.setAttribute("is_admin", "1");
				EcGroup eg = new EcGroup();
				List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
				request.setAttribute("deptList", deptList);
			} else if (!zb && fgs) {
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				dynaBean.set("fgs_id", fgs_dept.getDept_id());
				dynaBean.set("fgs_name", fgs_dept.getDept_name());
				request.setAttribute("is_fgs", "1");

				EcGroup eg = new EcGroup();
				eg.setLink_dept_id(fgs_dept.getDept_id());
				List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
				request.setAttribute("deptList", deptList);
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
				if (entity.getPd_res() != null && !"".equals(entity.getPd_res())) {
					String pd_res = entity.getPd_res();
					if (StringUtils.split(pd_res, ",").length > 1) {
						dynaBean.set("pd_res_x", StringUtils.split(pd_res, ",")[0]);
						dynaBean.set("pd_res_y", StringUtils.split(pd_res, ",")[1]);
					}
				}
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			// 回显产品描述、产品规格、售后服务
			KonkaBcompPdContent pc = new KonkaBcompPdContent();
			pc.setKbp_id(Long.valueOf(id));
			List<KonkaBcompPdContent> pct = super.getFacade().getKonkaBcompPdContentService()
					.getKonkaBcompPdContentList(pc);
			for (KonkaBcompPdContent pp : pct) {
				request.setAttribute("content" + pp.getType(), pp.getContent());
			}

			// 回显商品规则
			EcRuleGoods yy = new EcRuleGoods();
			// yy.setGoods_id(Long.valueOf(id));
			List<EcRuleGoods> yyList = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(yy);
			List<String> ids = new ArrayList<String>();
			// List<EcRule> list2 = new ArrayList<EcRule>();
			if (null != yyList && yyList.size() > 0) {
				for (EcRuleGoods ecRuleGoods : yyList) {
					/*
					 * EcRule rr = new EcRule(); rr.setInfo_state(1); rr.setId(ecRuleGoods.getRule_id()); rr =
					 * super.getFacade().getEcRuleService().getEcRule(rr);
					 */
					ids.add(ecRuleGoods.getRule_id().toString());
					// list2.add(rr);
				}
			}
			// request.setAttribute("list2", list2);

			EcRule tt = new EcRule();
			// tt.setInfo_state(1);
			/*
			 * if (null != ids && ids.size() > 0) { tt.getMap().put("id_not_in", ids); }
			 */
			List<EcRule> list1 = super.getFacade().getEcRuleService().getEcRuleList(tt);
			request.setAttribute("list1", list1);

			// 回显绑定的套餐
			// 1829@1828@
			/*
			 * String e_ids = ""; List<Long> had_binding_idList = new ArrayList<Long>(); EcBcompBinding ebb = new
			 * EcBcompBinding(); ebb.setGoods_id(Long.valueOf(id)); List<EcBcompBinding> ebbList = super
			 * .getFacade().getEcBcompBindingService().getEcBcompBindingList (ebb); if (null != ebbList && 0 !=
			 * ebbList.size()) { for (EcBcompBinding ecBcompBinding : ebbList) {
			 * had_binding_idList.add(ecBcompBinding.getBinding_id()); e_ids +=
			 * ecBcompBinding.getBinding_id().toString() + "@"; } dynaBean.set("e_ids", e_ids);
			 * logger.info("e_ids======>{}" + e_ids); }
			 */
			// 回显服务套餐
			/*
			 * EcBindingPd epd = new EcBindingPd(); epd.setBinding_type(0); if (null != had_binding_idList && 0 !=
			 * had_binding_idList.size()) { // 查询绑定的服务 epd.getMap().put("id_in", had_binding_idList); List<EcBindingPd>
			 * ecBindingPdList = super.getFacade().getEcBindingPdService ().getEcBindingPdList(epd);
			 * request.setAttribute("hadEntityList", ecBindingPdList); } else { request.setAttribute("hadEntityList",
			 * null); }
			 */
			// 属性名称
			/*
			 * EcExtend ec = new EcExtend(); ec.setLink_id(Long.valueOf(id)); ec.setProd_type(entity.getProd_type());
			 * List<EcExtend> ecList = super.getFacade().getEcExtendService().getEcExtendList(ec); for (EcExtend
			 * ecExtend : ecList) { EcExtend ee = new EcExtend(); ee.setProp_name(ecExtend.getProp_name());
			 * List<EcExtend> eeList = super.getFacade().getEcExtendService()
			 * .getEcExtendGroupByPropNameForPropValueList(ee); ecExtend.setEcExtendList(eeList); }
			 * request.setAttribute("ecList", ecList);
			 */

			// 回显商品套餐
			/*
			 * EcBindingPd epd1 = new EcBindingPd(); epd1.setBinding_type(1); if (null != had_binding_idList && 0 !=
			 * had_binding_idList.size()) { // 查询绑定的服务 epd1.getMap().put("id_in", had_binding_idList); List<EcBindingPd>
			 * ecBindingPdList = super.getFacade().getEcBindingPdService ().getEcBindingPdList(epd1);
			 * request.setAttribute("hadEntityList1", ecBindingPdList); } else { request.setAttribute("hadEntityList1",
			 * null); }
			 */

			// 取分公司列表
			List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
			request.setAttribute("deptList", deptList);
			// dynaBean.set("id", id1.toString());
			dynaBean.set("goods_id", id1.toString());
			dynaBean.set("own_sys", 2);// 默认触网
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		return new ActionForward("/../manager/spgl/PdShowNew/copy.jsp");
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

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		// String own_sys = (String) dynaBean.get("own_sys");

		if (GenericValidator.isLong(id)) {
			HttpSession session = request.getSession();
			PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
			/* 10系统管理员 20事业部管理员 21事业部领导 22事业部市场部管理员 */
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
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				dynaBean.set("fgs_id", fgs_dept.getDept_id());
				dynaBean.set("fgs_name", fgs_dept.getDept_name());
			}

			KonkaBcompPd entity = new KonkaBcompPd();
			entity.setId(Long.valueOf(id));
			// entity.setOwn_sys(Integer.valueOf(own_sys));
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

				String pd_res = entity.getPd_res();
				if (null != pd_res && !"".equals(pd_res)) {
					if (StringUtils.split(pd_res, ",").length > 1) {
						dynaBean.set("pd_res_x", StringUtils.split(pd_res, ",")[0]);
						dynaBean.set("pd_res_y", StringUtils.split(pd_res, ",")[1]);
					}
				}
			}
			entity.setQueryString(super.serialize(request, "id", "method"));
			super.copyProperties(form, entity);

			// 回显产品描述、产品规格、售后服务
			KonkaBcompPdContent pc = new KonkaBcompPdContent();
			pc.setKbp_id(Long.valueOf(id));
			List<KonkaBcompPdContent> pct = super.getFacade().getKonkaBcompPdContentService()
					.getKonkaBcompPdContentList(pc);
			for (KonkaBcompPdContent pp : pct) {
				request.setAttribute("content" + pp.getType(), pp.getContent());
			}

			// 回显绑定的套餐
			List<Long> had_binding_idList = new ArrayList<Long>();
			EcBcompBinding ebb = new EcBcompBinding();
			ebb.setGoods_id(Long.valueOf(id));
			List<EcBcompBinding> ebbList = super.getFacade().getEcBcompBindingService().getEcBcompBindingList(ebb);
			if (null != ebbList && 0 != ebbList.size()) {
				for (EcBcompBinding ecBcompBinding : ebbList) {
					had_binding_idList.add(ecBcompBinding.getBinding_id());
				}
			}

			// 回显服务套餐
			EcBindingPd epd = new EcBindingPd();
			epd.setBinding_type(0);
			if (null != had_binding_idList && 0 != had_binding_idList.size()) {
				// 查询绑定的服务
				epd.getMap().put("id_in", had_binding_idList);
				List<EcBindingPd> ecBindingPdList = super.getFacade().getEcBindingPdService().getEcBindingPdList(epd);
				request.setAttribute("hadEntityList", ecBindingPdList);
			} else {
				request.setAttribute("hadEntityList", null);
			}

			// 回显商品套餐
			EcBindingPd epd1 = new EcBindingPd();
			epd1.setBinding_type(1);
			if (null != had_binding_idList && 0 != had_binding_idList.size()) {
				// 查询绑定的服务
				epd1.getMap().put("id_in", had_binding_idList);
				List<EcBindingPd> ecBindingPdList = super.getFacade().getEcBindingPdService().getEcBindingPdList(epd1);
				request.setAttribute("hadEntityList1", ecBindingPdList);
			} else {
				request.setAttribute("hadEntityList1", null);
			}

			// 取分公司列表
			List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
			request.setAttribute("deptList", deptList);

		} else {
			this.saveError(request, "errors.long", new String[] { id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		return mapping.findForward("view");
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

		KonkaBcompPd entity = new KonkaBcompPd();
		entity.setId(Long.valueOf(id));
		entity.setState(0);
		super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPd(entity);

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

	public ActionForward restart(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		KonkaBcompPd entity = new KonkaBcompPd();
		entity.setId(Long.valueOf(id));
		entity.setState(1);
		super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPd(entity);

		saveMessage(request, "entity.updated");

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

	public ActionForward editForTj(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		KonkaBcompPd kk = new KonkaBcompPd();
		kk.setId(Long.valueOf(id));
		kk = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kk);

		KonkaBcompPd entity = new KonkaBcompPd();

		if (kk.getIs_tj() == null || kk.getIs_tj().intValue() == 0) {
			entity.setIs_tj(1);
		} else {
			entity.setIs_tj(0);
		}

		entity.setId(Long.valueOf(id));
		super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPd(entity);

		saveMessage(request, "entity.updated");

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

	public ActionForward editForSd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		KonkaBcompPd kk = new KonkaBcompPd();
		kk.setId(Long.valueOf(id));
		kk = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kk);

		KonkaBcompPd entity = new KonkaBcompPd();
		entity.setId(Long.valueOf(id));

		if (kk.getLock_state() == null || kk.getLock_state().intValue() == 0) {
			entity.setLock_state(1);
		} else {
			entity.setLock_state(0);
		}

		super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPd(entity);

		saveMessage(request, "entity.updated");

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

	public ActionForward ajaxModifyPrice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String price_stock = (String) dynaBean.get("price_stock");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"status\":");

		if (!GenericValidator.isLong(goods_id)) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		KonkaBcompPd kp = new KonkaBcompPd();
		kp.setId(Long.valueOf(goods_id));
		kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);

		if (kp != null) {
			if (kp.getDept_sn().equals("0")) {
				EcGoodsPrice ec = new EcGoodsPrice();
				ec.setGoods_id(Long.valueOf(goods_id));
				// ec.getMap().put("dept_id_is_null", true);
				ec.setPlat_sys(0);
				ec.setDept_id(0L);
				ec.setPrice_type(0);
				List<EcGoodsPrice> ecList = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceList(ec);
				if (ecList.size() == 0 || ecList.size() > 1) {
					sb = sb.append("-1");
					sb = sb.append("}");
					super.renderJson(response, sb.toString());
					return null;
				}
				ec = ecList.get(0);
				ec.setPrice(new BigDecimal(price_stock));
				super.getFacade().getEcGoodsPriceService().modifyEcGoodsPrice(ec);
				sb = sb.append("1").append(",");
				sb = sb.append("\"price\":\"").append(price_stock).append("\"");
				sb = sb.append(",\"goods_id\":\"").append(goods_id).append("\"");
				sb = sb.append("}");
				logger.info("sb {}", sb);
				super.renderJson(response, sb.toString());
				return null;
			} else if (!kp.getDept_sn().equals("0")) {
				EcGoodsPrice ec = new EcGoodsPrice();
				ec.setGoods_id(Long.valueOf(goods_id));
				ec.setDept_id(Long.valueOf(kp.getDept_sn()));
				ec.setPlat_sys(1);
				ec.setPrice_type(2);
				List<EcGoodsPrice> ecList = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceList(ec);
				if (ecList.size() == 0 || ecList.size() > 1) {
					sb = sb.append("-1");
					sb = sb.append("}");
					super.renderJson(response, sb.toString());
					return null;
				}
				ec = ecList.get(0);
				ec.setPrice(new BigDecimal(price_stock));
				super.getFacade().getEcGoodsPriceService().modifyEcGoodsPrice(ec);
				sb = sb.append("1").append(",");
				sb = sb.append("\"price\":\"").append(price_stock).append("\"");
				sb = sb.append(",\"goods_id\":\"").append(goods_id).append("\"");
				sb = sb.append("}");
				logger.info("sb {}", sb);
				super.renderJson(response, sb.toString());
				return null;
			} else {
				sb = sb.append("-1");
				sb = sb.append("}");
				super.renderJson(response, sb.toString());
				return null;
			}
		} else {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

	}

	public ActionForward ajaxModifyStock(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id");
		String price_stock = (String) dynaBean.get("price_stock");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"status\":");

		if (!GenericValidator.isLong(goods_id)) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		KonkaBcompPd kp = new KonkaBcompPd();
		kp.setId(Long.valueOf(goods_id));
		kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);

		if (kp != null) {
			if (kp.getDept_sn().equals("0")) {

				EcStocks es = new EcStocks();
				es.setGoods_id(Long.valueOf(goods_id));
				es.setPlat_sys(0);
				es.getMap().put("store_id_in", true);
				List<EcStocks> esList = super.getFacade().getEcStocksService().getEcStocksList(es);

				if (esList.size() == 0 || esList.size() > 1) {
					sb = sb.append("-1");
					sb = sb.append("}");
					super.renderJson(response, sb.toString());
					return null;
				}
				es = esList.get(0);
				es.setStocks(Long.valueOf(price_stock));
				super.getFacade().getEcStocksService().modifyEcStocks(es);
				sb = sb.append("1").append(",");
				sb = sb.append("\"stock\":\"").append(price_stock).append("\"");
				sb = sb.append(",\"goods_id\":\"").append(goods_id).append("\"");
				sb = sb.append("}");
				logger.info("sb {}", sb);
				super.renderJson(response, sb.toString());
				return null;
			} else if (!kp.getDept_sn().equals("0")) {
				EcStocks es = new EcStocks();
				es.setGoods_id(Long.valueOf(goods_id));
				es.setPlat_sys(1);
				es.getMap().put("store_id_in_2", true);
				es.getMap().put("dept_id_eq", kp.getDept_sn());
				List<EcStocks> esList = super.getFacade().getEcStocksService().getEcStocksList(es);

				if (esList.size() == 0 || esList.size() > 1) {
					sb = sb.append("-1");
					sb = sb.append("}");
					super.renderJson(response, sb.toString());
					return null;
				}
				es = esList.get(0);
				es.setStocks(Long.valueOf(price_stock));
				super.getFacade().getEcStocksService().modifyEcStocks(es);

				sb = sb.append("1").append(",");
				sb = sb.append("\"stock\":\"").append(price_stock).append("\"");
				sb = sb.append(",\"goods_id\":\"").append(goods_id).append("\"");
				sb = sb.append("}");
				logger.info("sb {}", sb);
				super.renderJson(response, sb.toString());
				return null;
			} else {
				sb = sb.append("-1");
				sb = sb.append("}");
				super.renderJson(response, sb.toString());
				return null;
			}
		} else {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

	}

	public ActionForward addBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		// String prod_type = (String) dynaBean.get("prod_type");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String label_of_cate = (String) dynaBean.get("label_of_cate");
		String mod_id = (String) dynaBean.get("mod_id");

		Pager pager = (Pager) dynaBean.get("pager");
		KonkaBcompPd pd = new KonkaBcompPd();
		pd.setState(1);
		pd.setOwn_sys(2);// 触网
		if (StringUtils.isNotBlank(pd_name_like)) {
			pd.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(pd_sn_like)) {
			pd.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(label_of_cate)) {
			pd.setLabel_of_cate(Integer.valueOf(label_of_cate));
		}
		// pd.setProd_type(Integer.valueOf(prod_type));
		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(pd);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		pd.getRow().setFirst(pager.getFirstRow());
		pd.getRow().setCount(pager.getRowCount());
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdPaginatedList(pd);

		request.setAttribute("entityList", entityList);
		dynaBean.set("mod_id", mod_id);

		Long flag = new Date().getTime();

		dynaBean.set("flag", flag.toString());

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		session.setAttribute("flag2", flag.toString());

		return new ActionForward("/../manager/spgl/PdShowNew/addBatchList.jsp");
	}

	public ActionForward insertBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String[] pks = request.getParameterValues("pks");
		String mod_id = (String) dynaBean.get("mod_id");
		String flag = (String) dynaBean.get("flag");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		String s_flag = (String) session.getAttribute("flag2");
		// if(s_flag!=null)
		if (s_flag.equals(flag)) {
			session.setAttribute("flag2", "1");
		} else {
			saveError(request, "errors.token");
			return list(mapping, form, request, response);
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

		logger.info("pks--->" + pks);
		if (null != pks && pks.length > 0) {
			for (String id : pks) {
				if (zb) {
					KonkaBcompPd kb = new KonkaBcompPd();
					kb.setId(Long.valueOf(id));
					kb = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kb);
					KonkaBcompPd entity = new KonkaBcompPd();
					super.copyProperties(entity, kb);
					Long new_id = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(entity);
					entity.setId(new_id);
					entity.setOwn_sys(2);
					entity.setLock_state(0);
					entity.setDept_sn("0");
					// 回显产品描述、产品规格、售后服务
					KonkaBcompPdContent pc = new KonkaBcompPdContent();
					pc.setKbp_id(Long.valueOf(id));
					List<KonkaBcompPdContent> pct = super.getFacade().getKonkaBcompPdContentService()
							.getKonkaBcompPdContentList(pc);
					entity.setKonkaBcompPdContentList(pct);

					entity.getMap().put("insert_batch", "1");

					super.getFacade().getKonkaBcompPdService().createKonkaBcompPd(entity);
				} else if (!zb && fgs) {
					EcGroup eg = new EcGroup();
					eg.setLink_dept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
					List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
					if (deptList != null && deptList.size() > 0) {
						for (EcGroup ecGroup : deptList) {
							KonkaBcompPd kb = new KonkaBcompPd();
							kb.setId(Long.valueOf(id));
							kb = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kb);
							KonkaBcompPd entity = new KonkaBcompPd();
							super.copyProperties(entity, kb);
							Long new_id = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(entity);
							entity.setId(new_id);
							entity.setOwn_sys(2);
							entity.setLock_state(0);
							// 回显产品描述、产品规格、售后服务
							KonkaBcompPdContent pc = new KonkaBcompPdContent();
							pc.setKbp_id(Long.valueOf(id));
							List<KonkaBcompPdContent> pct = super.getFacade().getKonkaBcompPdContentService()
									.getKonkaBcompPdContentList(pc);
							entity.setKonkaBcompPdContentList(pct);
							entity.setDept_sn(ecGroup.getId().toString());
							entity.getMap().put("insert_batch", "1");

							super.getFacade().getKonkaBcompPdService().createKonkaBcompPd(entity);
						}
					}

				}

			}
		}

		return new ActionForward("/../manager/spgl/PdShowNew.do?method=list&mod_id=" + mod_id);
	}

	public ActionForward batchMoidfy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String[] pks = request.getParameterValues("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		List<KonkaBcompPd> entityList = new ArrayList<KonkaBcompPd>();
		if (null != pks && pks.length > 0) {
			for (String id : pks) {
				KonkaBcompPd kp = new KonkaBcompPd();
				kp.setId(Long.valueOf(id));
				kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
				if (kp != null) {
					entityList.add(kp);
				}

			}
		}
		request.setAttribute("entityList", entityList);
		dynaBean.set("mod_id", mod_id);
		return new ActionForward("/../manager/spgl/PdShowNew/modifyBatchList.jsp");
	}

	public ActionForward moidfySave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String[] ids = request.getParameterValues("ids");
		String mod_id = (String) dynaBean.get("mod_id");
		String up_time = (String) dynaBean.get("up_time");
		String down_time = (String) dynaBean.get("down_time");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		if (ids.length > 0) {
			for (String id : ids) {
				KonkaBcompPd kp = new KonkaBcompPd();
				kp.setId(Long.valueOf(id));
				kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
				if (kp != null) {
					if (kp.getDept_sn().equals("0")) {// 总部商品
						EcBcompPdUpNew en = new EcBcompPdUpNew();
						en.setAdded_dept_id(0L);
						en.setBcomp_pd_id(kp.getId());
						en.setOwn_sys(2);
						en.setPlat_sys(0);
						super.getFacade().getEcBcompPdUpNewService().removeEcBcompPdUpNew(en);

						en.setBcomp_pd_id(Long.valueOf(id));
						en.setAdded_dept_id(0L);
						en.setAdd_u_id(user.getId());
						en.setAdd_date(new Date());
						en.setOwn_sys(2);
						if (StringUtils.isNotBlank(up_time)) {
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							en.setUp_time(sf.parse(up_time));
						}
						if (StringUtils.isNotBlank(down_time)) {
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							en.setDown_time(sf.parse(down_time));
						}

						super.getFacade().getEcBcompPdUpNewService().createEcBcompPdUpNew(en);

					} else if (!kp.getDept_sn().equals("0")) {
						EcBcompPdUpNew en = new EcBcompPdUpNew();
						en.setAdded_dept_id(Long.valueOf(kp.getDept_sn()));
						en.setBcomp_pd_id(kp.getId());
						en.setOwn_sys(2);
						en.setPlat_sys(1);
						super.getFacade().getEcBcompPdUpNewService().removeEcBcompPdUpNew(en);

						en.setBcomp_pd_id(Long.valueOf(id));
						en.setAdded_dept_id(Long.valueOf(kp.getDept_sn()));
						en.setAdd_u_id(user.getId());
						en.setAdd_date(new Date());
						en.setOwn_sys(2);
						en.setPlat_sys(1);
						if (StringUtils.isNotBlank(up_time)) {
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							en.setUp_time(sf.parse(up_time));
						}
						if (StringUtils.isNotBlank(down_time)) {
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							en.setDown_time(sf.parse(down_time));
						}
						super.getFacade().getEcBcompPdUpNewService().createEcBcompPdUpNew(en);
					}
				}
			}
		}

		return new ActionForward("/../manager/spgl/PdShowNew.do?method=list&mod_id=" + mod_id);
	}

	public ActionForward ruleShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		// String mod_id = (String) dynaBean.get("mod_id");
		// String own_sys = (String) dynaBean.get("own_sys");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		Long fgs_id = null;
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
						|| pu.getRole_id().intValue() == 1001) {
					zb = true;// 总部
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;// 分公司
				}
			}
		}
		if (zb) {
			request.setAttribute("is_admin", "1");// 总部
		} else if (!zb && fgs) {
			request.setAttribute("is_fgs", "1");// 分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			fgs_id = fgs_dept.getDept_id();

			EcGroup eg = new EcGroup();
			eg.setLink_dept_id(fgs_dept.getDept_id());
			List<EcGroup> deptList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			if (deptList != null && deptList.size() > 0) {
				String ids = "";
				for (EcGroup ecGroup : deptList) {
					ids = ids + "'" + ecGroup.getId().toString() + "'" + ",";
				}
				ids = ids.substring(0, ids.length() - 1);
				//System.out.println("ids-->" + ids);
				dynaBean.set("group_id", ids);
			}

		}

		KonkaBcompPd kp = new KonkaBcompPd();
		kp.setId(Long.valueOf(id));
		kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);
		if (kp != null) {
			dynaBean.set("pd_name", kp.getPd_name());
			dynaBean.set("pd_sn", kp.getPd_sn());
			dynaBean.set("is_lock", kp.getLock_state());
			dynaBean.set("dept_sn", kp.getDept_sn());
		}

		// 回显商品规则
		EcRuleGoods yy = new EcRuleGoods();
		yy.setGoods_id(Long.valueOf(id));
		List<EcRuleGoods> yyList1 = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(yy);

		List<EcRuleGoods> yyList = new ArrayList<EcRuleGoods>();

		for (EcRuleGoods ecRuleGoods : yyList1) {
			EcRule ec = new EcRule();
			ec.setId(ecRuleGoods.getRule_id());
			ec.setOwn_sys(2);

			if (kp.getDept_sn().equals("0")) {
				ec.setDept_id(0L);
			} else {
				EcGroup eg = new EcGroup();
				eg.setId(Long.valueOf(kp.getDept_sn()));
				eg = super.getFacade().getEcGroupService().getEcGroup(eg);
				if (eg != null && eg.getLink_dept_id() != null) {
					ec.setDept_id(eg.getLink_dept_id());
				}

			}
			ec = super.getFacade().getEcRuleService().getEcRule(ec);
			if (null != ec && ec.getInfo_state().intValue() == 1) {
				yyList.add(ecRuleGoods);
			}
		}

		List<String> ids = new ArrayList<String>();
		List<EcRule> list2 = new ArrayList<EcRule>();
		if (null != yyList && yyList.size() > 0) {
			for (EcRuleGoods ecRuleGoods : yyList) {
				EcRule rr = new EcRule();
				rr.setOwn_sys(2);
				if (kp.getDept_sn().equals("0")) {
					rr.setDept_id(0L);
				} else {
					EcGroup eg = new EcGroup();
					eg.setId(Long.valueOf(kp.getDept_sn()));
					eg = super.getFacade().getEcGroupService().getEcGroup(eg);
					if (eg != null && eg.getLink_dept_id() != null) {
						rr.setDept_id(eg.getLink_dept_id());
					}
				}
				rr.setId(ecRuleGoods.getRule_id());
				rr = super.getFacade().getEcRuleService().getEcRule(rr);
				ids.add(ecRuleGoods.getRule_id().toString());
				list2.add(rr);
			}
		}
		request.setAttribute("list2", list2);

		EcRule tt = new EcRule();
		tt.setOwn_sys(2);
		if (kp.getDept_sn().equals("0")) {
			tt.setDept_id(0L);
		} else {
			EcGroup eg = new EcGroup();
			eg.setId(Long.valueOf(kp.getDept_sn()));
			eg = super.getFacade().getEcGroupService().getEcGroup(eg);
			if (eg != null && eg.getLink_dept_id() != null) {
				tt.setDept_id(eg.getLink_dept_id());
			}
		}
		if (null != ids && ids.size() > 0) {
			tt.getMap().put("id_not_in", ids);
		}
		tt.setInfo_state(1);// 启用
		List<EcRule> list1 = super.getFacade().getEcRuleService().getEcRuleList(tt);
		request.setAttribute("list1", list1);

		dynaBean.set("id", id);

		return new ActionForward("/../manager/spgl/PdShowNew/rule.jsp");
	}

	public ActionForward saveShow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] select2 = request.getParameterValues("select2");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		KonkaBcompPd entity = new KonkaBcompPd();
		entity.getMap().put("select2", select2);
		EcRuleGoods erg = new EcRuleGoods();
		erg.setGoods_id(Long.valueOf(id));
		List<EcRuleGoods> listRules = super.getFacade().getEcRuleGoodsService().getEcRuleGoodsList(erg);
		if (null != listRules && listRules.size() > 0) {
			entity.getMap().put("select1", listRules);
		}
		entity.getMap().put("user_id", user.getId());

		// super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPdIncludeContent(entity,
		// e_id, e_id2);

		entity.setId(Long.valueOf(id));
		super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPdForRule(entity);
		dynaBean.set("id", id);

		return new ActionForward("/../manager/spgl/PdShowNew.do?method=ruleShow&id=" + id);
	}

}
