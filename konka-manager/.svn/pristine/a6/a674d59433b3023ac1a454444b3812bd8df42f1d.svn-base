package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ebiz.mmt.domain.EcBcompBinding;
import com.ebiz.mmt.domain.EcBcompPdUp;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.EcExtend;
import com.ebiz.mmt.domain.EcGoodsPrice;
import com.ebiz.mmt.domain.EcGoodsPriceArea;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.domain.EcStocks;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;
import com.sf.integration.warehouse.SfOrderService;
import com.sf.integration.warehouse.Spml;

/**
 * @author Pan,Gang
 * @version 2013-09-11
 */
public class PdShowAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		// String own_sys = request.getParameter("own_sys");
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
		if (StringUtils.isNotBlank(own_sys)) {
			entity.setOwn_sys(Integer.valueOf(own_sys));
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
		logger.info("prod_type-------------->{}" + prod_type);
		if (StringUtils.isNotBlank(prod_type)) {
			entity.setProd_type(Integer.valueOf(prod_type));
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
				} else {
					entity.getMap().put("zb_goods_id_in", true);
				}

			} else if (is_sj.equals("2")) {// 下架
				if (zb) {
					entity.getMap().put("zb_goods_id_not_in", true);
				} else if (fgs) {
					entity.getMap().put("fgs_goods_id_not_in", true);
					entity.getMap().put("fgs_id",
					        super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id().toString());
				} else {
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
						} else {
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

		// dynaBean.set("own_sys", own_sys);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 178 || pu.getRole_id().intValue() == 140317
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

		if (zb) {
			request.setAttribute("is_admin", "1");
		}
		if (fgs) {
			request.setAttribute("is_fgs", "1");
		}

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		// 取产品规格型号列表
		// PePdModel entity = new PePdModel();
		// entity.setIs_del(0);
		// entity.setAudit_state(1);
		// List<PePdModel> pdList =
		// getFacade().getPePdModelService().getPePdModelList(entity);
		// request.setAttribute("pdList", pdList);

		// 获取主键序列id
		KonkaBcompPd entity = new KonkaBcompPd();
		Long id = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdForIdCount(entity);
		logger.info("id+++++=" + id);
		dynaBean.set("goods_id", id);

		EcExtend ec = new EcExtend();
		if (StringUtils.isNotBlank(prod_type)) {
			ec.setProd_type(Integer.valueOf(prod_type));// 
		}
		List<EcExtend> ecList = super.getFacade().getEcExtendService().getEcExtendGroupByPropNameList(ec);
		for (EcExtend ecExtend : ecList) {
			EcExtend ee = new EcExtend();
			ee.setProp_name(ecExtend.getProp_name());
			List<EcExtend> eeList = super.getFacade().getEcExtendService().getEcExtendGroupByPropNameForPropValueList(
			        ee);
			ecExtend.setEcExtendList(eeList);
		}
		request.setAttribute("ecList", ecList);
		if (StringUtils.isNotBlank(prod_type)) {
			dynaBean.set("prod_type", prod_type);
		}

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String goods_id = (String) dynaBean.get("goods_id");
		logger.info("goods_id++++=" + goods_id);
		String dept_sn = (String) dynaBean.get("dept_sn");
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
		String[] p_ids = request.getParameterValues("p_ids");
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
		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 60, 86, 95, 120, 240, 280, 350,
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

		/* 10系统管理员 20事业部管理员 21事业部领导 22事业部市场部管理员 */
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
		if (zb) { // session用户是总部用户
			entity.setDept_sn("0");
			if (StringUtils.isBlank(dept_sn)) {
				entity.setDept_sn("0");
			} else {
				entity.setDept_sn(dept_sn);
			}
		} else if (fgs) {
			entity.setDept_sn(dept_sn);
		} else {
			entity.setDept_sn("0");
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

			super.getFacade().getKonkaBcompPdService().createKonkaBcompPdIncludeContent2(entity, e_id, e_id2, ecList,
			        ecList2);

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
			super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPdIncludeContent2(entity, e_id, e_id2, ecList,
			        ecList2);
			saveMessage(request, "entity.updated");
		}

		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("prod_type=" + prod_type);
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	// 复制保存
	public ActionForward saveCopy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String goods_id = (String) dynaBean.get("goods_id");
		logger.info("goods_id++++=" + goods_id);
		String dept_sn = (String) dynaBean.get("dept_sn");
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
		// String own_sys = (String) dynaBean.get("own_sys");

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
		if (null != pd_res_x && !"".equals(pd_res_x) && null != pd_res_y && !"".equals(pd_res_y)) {
			pd_res = pd_res_x.concat("," + pd_res_y);
			entity.setPd_res(pd_res);
		}

		// 上传主图以及附图
		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 60, 86, 95,
				120, 240, 280, 350,
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

		/* 10系统管理员 20事业部管理员 21事业部领导 22事业部市场部管理员 */
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
		if (zb) { // session用户是总部用户
			entity.setDept_sn("0");
			if (StringUtils.isBlank(dept_sn)) {
				entity.setDept_sn("0");
			} else {
				entity.setDept_sn(dept_sn);
			}
		} else if (fgs) {
			entity.setDept_sn(dept_sn);
		} else {
			entity.setDept_sn("0");
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
			super.getFacade().getKonkaBcompPdService().createKonkaBcompPdIncludeContent2(entity, e_id, e_id2, ecList,
			        ecList2);

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
			super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPdIncludeContent2(entity, e_id, e_id2, ecList,
			        ecList2);
			saveMessage(request, "entity.updated");
		}

		logger.info("++++++++++++++QueryString=" + super.encodeSerializedQueryString(entity.getQueryString()));
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("prod_type=" + prod_type);
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String prod_type = (String) dynaBean.get("prod_type");

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
			if (zb) {
				request.setAttribute("is_admin", "1");
			}
			if (fgs) {
				request.setAttribute("is_fgs", "1");
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

			// 取分公司列表
			List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
			request.setAttribute("deptList", deptList);
			if (StringUtils.isNotBlank(prod_type)) {
				dynaBean.set("prod_type", prod_type);
			}

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
		return new ActionForward("/../manager/spgl/PdShow/edit.jsp");
	}

	// 复制编辑
	public ActionForward editCopy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String prod_type = (String) dynaBean.get("prod_type");

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
			if (!zb && fgs) { // session用户不属于总部，属于分公司
				KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
				dynaBean.set("fgs_id", fgs_dept.getDept_id());
				dynaBean.set("fgs_name", fgs_dept.getDept_name());
			}
			if (zb) {
				request.setAttribute("is_admin", "1");
			}
			if (fgs) {
				request.setAttribute("is_fgs", "1");
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
					 * EcRule rr = new EcRule(); rr.setInfo_state(1);
					 * rr.setId(ecRuleGoods.getRule_id()); rr =
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
			 * if (null != ids && ids.size() > 0) { tt.getMap().put("id_not_in",
			 * ids); }
			 */
			List<EcRule> list1 = super.getFacade().getEcRuleService().getEcRuleList(tt);
			request.setAttribute("list1", list1);

			// 回显绑定的套餐
			// 1829@1828@
			/*
			 * String e_ids = ""; List<Long> had_binding_idList = new
			 * ArrayList<Long>(); EcBcompBinding ebb = new EcBcompBinding();
			 * ebb.setGoods_id(Long.valueOf(id)); List<EcBcompBinding> ebbList =
			 * super
			 * .getFacade().getEcBcompBindingService().getEcBcompBindingList
			 * (ebb); if (null != ebbList && 0 != ebbList.size()) { for
			 * (EcBcompBinding ecBcompBinding : ebbList) {
			 * had_binding_idList.add(ecBcompBinding.getBinding_id()); e_ids +=
			 * ecBcompBinding.getBinding_id().toString() + "@"; }
			 * dynaBean.set("e_ids", e_ids); logger.info("e_ids======>{}" +
			 * e_ids); }
			 */
			// 回显服务套餐
			/*
			 * EcBindingPd epd = new EcBindingPd(); epd.setBinding_type(0); if
			 * (null != had_binding_idList && 0 != had_binding_idList.size()) {
			 * // 查询绑定的服务 epd.getMap().put("id_in", had_binding_idList);
			 * List<EcBindingPd> ecBindingPdList =
			 * super.getFacade().getEcBindingPdService
			 * ().getEcBindingPdList(epd); request.setAttribute("hadEntityList",
			 * ecBindingPdList); } else { request.setAttribute("hadEntityList",
			 * null); }
			 */
			// 属性名称
			/*
			 * EcExtend ec = new EcExtend(); ec.setLink_id(Long.valueOf(id));
			 * ec.setProd_type(entity.getProd_type()); List<EcExtend> ecList =
			 * super.getFacade().getEcExtendService().getEcExtendList(ec); for
			 * (EcExtend ecExtend : ecList) { EcExtend ee = new EcExtend();
			 * ee.setProp_name(ecExtend.getProp_name()); List<EcExtend> eeList =
			 * super.getFacade().getEcExtendService()
			 * .getEcExtendGroupByPropNameForPropValueList(ee);
			 * ecExtend.setEcExtendList(eeList); }
			 * request.setAttribute("ecList", ecList);
			 */

			// 回显商品套餐
			/*
			 * EcBindingPd epd1 = new EcBindingPd(); epd1.setBinding_type(1); if
			 * (null != had_binding_idList && 0 != had_binding_idList.size()) {
			 * // 查询绑定的服务 epd1.getMap().put("id_in", had_binding_idList);
			 * List<EcBindingPd> ecBindingPdList =
			 * super.getFacade().getEcBindingPdService
			 * ().getEcBindingPdList(epd1);
			 * request.setAttribute("hadEntityList1", ecBindingPdList); } else {
			 * request.setAttribute("hadEntityList1", null); }
			 */

			// 取分公司列表
			List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
			request.setAttribute("deptList", deptList);
			if (StringUtils.isNotBlank(prod_type)) {
				dynaBean.set("prod_type", prod_type);
			}
			// dynaBean.set("id", id1.toString());
			dynaBean.set("goods_id", id1.toString());
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
		return new ActionForward("/../manager/spgl/PdShow/copy.jsp");
	}

	public ActionForward upShelf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (GenericValidator.isLong(id)) {

			EcGoodsPrice ec = new EcGoodsPrice();
			ec.setGoods_id(Long.valueOf(id));
			Long count = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceCount(ec);
			if (count == 0) {
				String msg = super.getMessage(request, "konka.price.zero");
				//System.out.println("++++++++++++++++" + super.getCtxPath(request));
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			EcStocks ecs = new EcStocks();
			ecs.setGoods_id(Long.valueOf(id));
			List<EcStocks> ecsList = super.getFacade().getEcStocksService().getEcStocksList(ecs);
			if (null == ecsList || 0 == ecsList.size()) {
				String msg = super.getMessage(request, "konka.store.zero");
				// super.renderJavaScript(response,
				// "window.onload=function(){alert('" + msg +
				// "');location.href='"
				// + super.getCtxPath(request) +
				// "/manager/spgl/EcStocks.do?method=list&mod_id=905201';}");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (ecsList.size() > 0) {
				long num = 0;
				for (EcStocks ee : ecsList) {
					num = +ee.getStocks();
				}
				if (num == 0L) {
					String msg = super.getMessage(request, "konka.store.zero");
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					// super.renderJavaScript(response,
					// "window.onload=function(){alert('" + msg +
					// "');location.href='"
					// + super.getCtxPath(request) +
					// "/manager/spgl/EcStocks.do?method=list&mod_id=905201';}");
					return null;
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
				dynaBean.set("md_name", pdModel.getMd_name());

				// 取分公司列表
				List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
				request.setAttribute("deptList", deptList);
			}
			super.copyProperties(form, entity);
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

		return new ActionForward("/../manager/spgl/PdShow/up_shelf.jsp");
	}

	public ActionForward upShelfSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String up_date = (String) dynaBean.get("up_date");
		String down_date = (String) dynaBean.get("down_date");
		// String own_sys = (String) dynaBean.get("own_sys");

		if (GenericValidator.isLong(id)) {
			KonkaBcompPd entity = new KonkaBcompPd();
			entity.setId(Long.valueOf(id));
			Long countRecord = getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(entity);
			if (countRecord.intValue() <= 0) {
				this.saveError(request, "errors.long", new String[] { id });
				// the line below is added for pagination
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				// pathBuffer.append("&").append("own_sys=" + own_sys);
				pathBuffer.append("&mod_id=").append(mod_id);
				pathBuffer.append("&");
				// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
				// "user_id", "method")));
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				// end
				return forward;
			} else {
				entity.setLast_up_time(DateUtils.parseDate(up_date, new String[] { "yyyy-MM-dd" }));
				entity.setDown_time(DateUtils.parseDate(down_date, new String[] { "yyyy-MM-dd" }));
				super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPd(entity);
				saveMessage(request, "entity.updated");

				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				// pathBuffer.append("&").append("own_sys=" + own_sys);
				pathBuffer.append("&").append("mod_id=" + mod_id);
				// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				// end
				return forward;
			}
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			// the line below is added for pagination
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			// pathBuffer.append("&").append("own_sys=" + own_sys);
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
	}

	public ActionForward offShelf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		// String own_sys = (String) dynaBean.get("own_sys");

		if (GenericValidator.isLong(id)) {
			KonkaBcompPd entity = new KonkaBcompPd();
			entity.setId(Long.valueOf(id));
			Long countRecord = getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(entity);
			if (countRecord.intValue() <= 0) {
				this.saveError(request, "errors.long", new String[] { id });
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&mod_id=").append(mod_id);
				pathBuffer.append("&");
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				return forward;
			} else {
				entity.setDown_time(new Date());
				super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPd(entity);
				saveMessage(request, "entity.updated");

				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				// pathBuffer.append("&").append("own_sys=" + own_sys);
				pathBuffer.append("&").append("mod_id=" + mod_id);
				pathBuffer.append("&");
				// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				// end
				return forward;
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
		return new ActionForward("/../manager/spgl/PdShow/view.jsp");
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
			if (zb) {
				request.setAttribute("is_admin", "1");
			}
			if (fgs) {
				request.setAttribute("is_fgs", "1");
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

				String pd_res = entity.getPd_res();
				dynaBean.set("pd_res_x", StringUtils.split(pd_res, ",")[0]);
				dynaBean.set("pd_res_y", StringUtils.split(pd_res, ",")[1]);
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
		return new ActionForward("/../manager/spgl/PdShow/edit2.jsp");
	}

	public ActionForward export(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		KonkaBcompPd p = new KonkaBcompPd();
		p.setIs_sf(0);
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdList(p);
		logger.info("entityList------>>>{}" + entityList.size());
		for (KonkaBcompPd pp : entityList) {
			try {
				SfOrderService sf = new SfOrderService();
				String spmlOpName = "wmsMerchantCatalogService";
				Spml spml = new Spml();
				spml.setCheckword("01f18980363f40e48416464baf4cc7c0");
				spml.setCompany("KONKA");
				spml.setItem(pp.getId().toString());
				spml.setDescription(pp.getPd_name());
				spml.setStorage_template("个");
				spml.setSequence_1("1");
				spml.setConversion_qty_1("1");
				spml.setHeight_1("1");
				spml.setWidth_1("1");
				spml.setLength_1("1");
				spml.setWeight_1("1");
				spml.setQuantity_um_1("个");
				spml.setWeight_um_1("KG");
				spml.setDimension_um_1("CM");
				spml.setTreat_as_loose_1("Y");
				spml.setLot_controlled("否");
				spml.setSerial_num_track_inbound("否");
				spml.setSerial_num_track_outbound("否");
				spml.setBom_action("否");
				spml.setInterface_action_code("NEW");
				String spmlXml = spml.toXml();
				String returnXml;
				returnXml = sf.sfWebService(spmlXml, spmlOpName);
				//System.out.println("商品目录导入返回xml------>>>" + returnXml);
				Document doc = DocumentHelper.parseText(returnXml);
				Element rootElt1 = doc.getRootElement(); // 获取根节点
				String result1 = rootElt1.elementTextTrim("result");
				String item = rootElt1.elementTextTrim("item");
				//System.out.println(result1);
				//System.out.println(item);
				if (StringUtils.isNotBlank(item)) {
					if (result1.equals("1")) {
						KonkaBcompPd kbp = new KonkaBcompPd();
						kbp.setId(Long.valueOf(item));
						kbp.setIs_sf(1);
						super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPd(kbp);
					}

				}
			} catch (Exception e) {
				throw e;
			}

		}
		//System.out.println("——————————————————————————————》发生错误22222222222");
		super.render(response, "结束", "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward showPrice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String goods_id = (String) dynaBean.get("goods_id");
		String pd_name = (String) dynaBean.get("pd_name");
		String is_fgs_id = (String) dynaBean.get("is_fgs_id");

		dynaBean.set("pd_name", pd_name);// 添加的时候 回显产品名称
		dynaBean.set("is_fgs_id", is_fgs_id);// 判断产品是否是分公司添加

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
				if (pu.getRole_id().intValue() < 30 || (pu.getRole_id() >= 200 && pu.getRole_id() < 300)) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		KonkaBcompPd kpd = new KonkaBcompPd();
		if (StringUtils.isNotBlank(goods_id)) {
			kpd.setId(Long.valueOf(goods_id));
			kpd = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kpd);
			if (null != kpd) {
				dynaBean.set("pd_dept", kpd.getDept_sn());
			} else {
				// 分公司添加商品
				dynaBean.set("pd_dept", super.getSuperiorForDeptType(user.getDept_id(), 3));
			}
		}

		EcGoodsPrice entity = new EcGoodsPrice();
		if (StringUtils.isNotBlank(goods_id)) {
			entity.setGoods_id(Long.valueOf(goods_id));
		}

		if (zb) {
			request.setAttribute("is_admin", "1");
			entity.getMap().put("own_sys_in", "(1)");
		} else if (fgs) {
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			dynaBean.set("fgs_id", fgs_dept.getDept_id());
			dynaBean.set("fgs_name", fgs_dept.getDept_name());

			request.setAttribute("is_fgs", "1");
			entity.getMap().put("is_fgs", super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());

			KonkaDept kd = new KonkaDept();
			kd.setDept_id(super.getSuperiorForDeptType(user.getDept_id(), 3).getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
			String[] ids = StringUtils.split(kd.getM_areas_ids(), ",");
			List<String> pindex_ids = new ArrayList<String>();
			if (null == ids || ids.length == 0) {
				super.renderJavaScript(response, "window.onload=function(){alert('请去维护该分司的管辖区域！');history.back();}");
				return null;
			} else {
				for (String ss : ids) {
					pindex_ids.add(ss.substring(0, 4));
				}
				entity.getMap().put("pindex_ids", pindex_ids);
			}

			if (kpd.getOwn_sys().intValue() == 5) {
				entity.getMap().put("own_sys_in", "(1)");// 分公司工卡
			} else {
				entity.getMap().put("own_sys_in", "(2)");
			}

			// 显示r3客户价格
			KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
			konkaR3Shop.setBranch_area_name_2(kd.getDept_sn());
			konkaR3Shop.setIs_del(0L);
			List<KonkaR3Shop> entityList1 = super.getFacade().getKonkaR3ShopService().getKonkaR3ShopList(konkaR3Shop);
			List<String> cidList = new ArrayList<String>();
			for (KonkaR3Shop konkaR3Shop2 : entityList1) {
				cidList.add(konkaR3Shop2.getId().toString());
			}
			entity.getMap().put("c_ids", cidList);

		} else {
			request.setAttribute("is_admin", "1");
			entity.getMap().put("own_sys_in", "(1)");
		}

		Long recordCount = super.getFacade().getEcGoodsPriceService().getEcGoodsPriceForDeptNameAndPdNameCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcGoodsPrice> entityList = super.getFacade().getEcGoodsPriceService()
		        .getEcGoodsPriceForDeptNameAndPdNameList(entity);
		// 查询分公司管辖区域
		for (EcGoodsPrice ecGoodsPrice : entityList) {
			EcGoodsPriceArea eca = new EcGoodsPriceArea();
			eca.setPrice_id(ecGoodsPrice.getId());
			List<EcGoodsPriceArea> areas = super.getFacade().getEcGoodsPriceAreaService().getEcGoodsPriceAreaList(eca);
			List<String> pNameList = new ArrayList<String>();
			for (EcGoodsPriceArea ecGoodsPriceArea : areas) {
				pNameList.add(ecGoodsPriceArea.getP_name());
			}
			String areaList = StringUtils.join(pNameList.toArray(), ',');
			ecGoodsPrice.getMap().put("areaList", areaList);

			if (null != ecGoodsPrice.getC_id()) {
				KonkaR3Shop kk = new KonkaR3Shop();
				kk.setId(ecGoodsPrice.getC_id());
				kk = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(kk);
				if (kk != null) {
					ecGoodsPrice.getMap().put("customer_name", kk.getCustomer_name());
				}
			}
		}

		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/PdShow/showprice.jsp");
	}

}
