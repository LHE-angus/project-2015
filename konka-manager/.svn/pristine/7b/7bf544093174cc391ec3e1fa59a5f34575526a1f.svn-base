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
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ebiz.mmt.domain.EcBcompBinding;
import com.ebiz.mmt.domain.EcBindingPd;
import com.ebiz.mmt.domain.EcExtend;
import com.ebiz.mmt.domain.EcGoodsSallarea;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcProduct;
import com.ebiz.mmt.domain.EcRule;
import com.ebiz.mmt.domain.EcRuleGoods;
import com.ebiz.mmt.domain.EcSallareaTemplate;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.KonkaBcompPdContent;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-11
 */
public class EcProductAction extends BaseAction {
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
		String own_sys = (String) dynaBean.get("own_sys");
		// String dept_sn = (String) dynaBean.get("dept_sn");
		String state = (String) dynaBean.get("state");
		// logger.info("own_sys" + own_sys);
		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcProduct entity = new EcProduct();
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

		if (StringUtils.isNotBlank(own_sys)) {
			entity.setOwn_sys(Integer.valueOf(own_sys));
		}

		// if (StringUtils.isNotBlank(dept_sn)) {
		// entity.setDept_sn(dept_sn);
		// }
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
		// entity.getMap().put("today", DateFormatUtils.format(new Date(),
		// "yyyy-MM-dd HH:mm:ss"));

		if (StringUtils.isNotBlank(state)) {
			entity.setState(Integer.valueOf(state));
		} else {
			entity.setState(1);
		}

		entity.setOwn_sys(2);// 触网
		// entity.getMap().put("own_sys_in", new Integer[] { 1, 2, 4 });

		Long recordCount = super.getFacade().getEcProductService().getEcProductCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcProduct> entityList = super.getFacade().getEcProductService().getEcProductPaginatedList(entity);

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

		dynaBean.set("prod_type", "0");

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		// String dept_sn = (String) dynaBean.get("dept_sn");
		String[] label_3d = request.getParameterValues("label_3d");
		String[] label_int = request.getParameterValues("label_int");
		String[] label_www = request.getParameterValues("label_www");
		String[] label_of_cate = request.getParameterValues("label_of_cate");
		String[] label_4k = request.getParameterValues("label_4k");
		String pd_sn = (String) dynaBean.get("pd_sn");
		// String[] prod_type = request.getParameterValues("prod_type");
		String main_pic_hidden = (String) dynaBean.get("main_pic_hidden");
		String pd_res_x = (String) dynaBean.get("pd_res_x");
		String pd_res_y = (String) dynaBean.get("pd_res_y");
		String[] pic_hidden = request.getParameterValues("pic_hidden");

		String content1 = (String) dynaBean.get("content1");
		String content2 = (String) dynaBean.get("content2");
		String content3 = (String) dynaBean.get("content3");
		String own_sys = (String) dynaBean.get("own_sys");

		String prod_type = (String) dynaBean.get("prod_type");
		String pd_spec = (String) dynaBean.get("pd_spec");

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

		EcProduct entity = new EcProduct();
		super.copyProperties(entity, form);

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

		logger.info("label_of_cate----->{}" + label_of_cate);

		if (ArrayUtils.isNotEmpty(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate[0]));
			//System.out.println("111111111111");
		} else {
			entity.setLabel_of_cate(null);
			//System.out.println("222222222");
		}

		if (StringUtils.isNotBlank(prod_type)) {
			entity.setProd_type(Integer.valueOf(prod_type));
		}

		logger.info("+++++++++++++id" + id);
		if (StringUtils.isBlank(id)) {

			if (StringUtils.isNotBlank(pd_sn)) {
				EcProduct ep = new EcProduct();
				ep.setPd_sn(pd_sn);
				Long count = super.getFacade().getEcProductService().getEcProductCount(ep);
				if (count > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('产品型号重复，请重新选择 ');history.back();}");
					return null;
				}
			}

			entity.setOwn_sys(2);
			entity.setAdd_u_id(user.getId());
			List<KonkaBcompPdContent> konkaBcompPdContentList = new ArrayList<KonkaBcompPdContent>();
			KonkaBcompPdContent konkaBcompPdContent_1 = new KonkaBcompPdContent();

			konkaBcompPdContent_1.setType(1);
			konkaBcompPdContent_1.setContent(content1);
			konkaBcompPdContentList.add(konkaBcompPdContent_1);

			KonkaBcompPdContent konkaBcompPdContent_2 = new KonkaBcompPdContent();
			konkaBcompPdContent_2.setType(2);
			konkaBcompPdContent_2.setContent(content2);
			konkaBcompPdContentList.add(konkaBcompPdContent_2);

			KonkaBcompPdContent konkaBcompPdContent_3 = new KonkaBcompPdContent();
			konkaBcompPdContent_3.setType(3);
			konkaBcompPdContent_3.setContent(content3);
			konkaBcompPdContentList.add(konkaBcompPdContent_3);

			entity.setKonkaBcompPdContentList(konkaBcompPdContentList);

			super.getFacade().getEcProductService().createEcProduct(entity);

		} else {
			entity.setId(Long.valueOf(id));

			EcProduct ep1 = new EcProduct();
			ep1.setId(Long.valueOf(id));
			ep1 = super.getFacade().getEcProductService().getEcProduct(ep1);
			if (StringUtils.isNotBlank(pd_sn) && !ep1.getPd_sn().equals(pd_sn)) {
				EcProduct ep = new EcProduct();
				ep.setPd_sn(pd_sn);
				Long count = super.getFacade().getEcProductService().getEcProductCount(ep);
				if (count > 0) {
					super.renderJavaScript(response, "window.onload=function(){alert('产品型号重复，请重新选择 ');history.back();}");
					return null;
				}
			}

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

			super.getFacade().getEcProductService().modifyEcProduct(entity);

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

			EcProduct entity = new EcProduct();
			entity.setId(Long.valueOf(id));
			entity = getFacade().getEcProductService().getEcProduct(entity);
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

			if (user == null) {
				return null;
			}

			EcProduct entity = new EcProduct();
			entity.setId(Long.valueOf(id));
			// entity.setOwn_sys(Integer.valueOf(own_sys));
			entity = getFacade().getEcProductService().getEcProduct(entity);
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

		EcProduct entity = new EcProduct();
		entity.setId(Long.valueOf(id));
		entity.setState(0);
		super.getFacade().getEcProductService().modifyEcProduct(entity);

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

		EcProduct entity = new EcProduct();
		entity.setId(Long.valueOf(id));
		entity.setState(1);
		super.getFacade().getEcProductService().modifyEcProduct(entity);

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

	public ActionForward validatePdSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		StringBuffer oper = new StringBuffer("{\"result\":");
		String pd_sn = (String) dynaBean.get("pd_sn");
		EcProduct entity = new EcProduct();
		entity.setPd_sn(pd_sn);
		// entity.setIs_del(0);
		Long count = super.getFacade().getEcProductService().getEcProductCount(entity);
		if ("0".equals(count.toString())) {
			oper.append(false);
		} else {
			oper.append(true);
		}
		oper.append("}");
		super.render(response, oper.toString(), "text/x-json;charset=UTF-8");
		return null;
	}

}
