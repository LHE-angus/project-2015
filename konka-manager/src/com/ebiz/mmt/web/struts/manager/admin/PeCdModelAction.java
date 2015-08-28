package com.ebiz.mmt.web.struts.manager.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

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

public class PeCdModelAction extends BaseAction {

	@Override
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
		String state = (String) dynaBean.get("state");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");
		String pd_type = (String) dynaBean.get("pd_type");
		logger.info("__________________________________pd_type = " + pd_type);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaBcompPd entity = new KonkaBcompPd();
		super.copyProperties(entity, form);

		/* 10系统管理员 20事业部管理员 21事业部领导 22事业部市场部管理员 */
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id().intValue() < 30) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			entity.getMap().put("dept_id_in", "0," + fgs_dept.getDept_id().toString());
		}

		if (StringUtils.isBlank(state)) {
			state = "1";
		}
		entity.setState(Integer.valueOf(state));
		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		if (StringUtils.isNotBlank(pd_type)) {
			entity.setOwn_sys(Integer.valueOf(pd_type));
		}
		// Long recordCount =
		// super.getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(entity);
		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdWithDeptAndMdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		// List<KonkaBcompPd> entityList =
		// super.getFacade().getKonkaBcompPdService().getKonkaBcompPdPaginatedList(entity);
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService()
		        .getKonkaBcompPdWithDeptAndMdPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		dynaBean.set("state", state);
		request.setAttribute("today", new Date());
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String pd_type = (String) dynaBean.get("pd_type");

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
				if (pu.getRole_id().intValue() < 30) {
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

		// 取分公司列表
		List<KonkaDept> deptList = getDeptInfoListWithOutLimit(mapping, form, request, response);
		request.setAttribute("deptList", deptList);

		// 取产品规格型号列表
		PePdModel entity = new PePdModel();
		entity.setIs_del(0);
		entity.setAudit_state(1);
		List<PePdModel> pdList = getFacade().getPePdModelService().getPePdModelList(entity);
		request.setAttribute("pdList", pdList);

		dynaBean.set("pd_type", pd_type);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String pd_type = (String) dynaBean.get("pd_type");
		String dept_id = (String) dynaBean.get("dept_id");
		String[] label_3d = request.getParameterValues("label_3d");
		String[] label_int = request.getParameterValues("label_int");
		String[] label_www = request.getParameterValues("label_www");
		String[] label_of_cate = request.getParameterValues("label_of_cate");
		String main_pic_hidden = (String) dynaBean.get("main_pic_hidden");
		String pd_res_x = (String) dynaBean.get("pd_res_x");
		String pd_res_y = (String) dynaBean.get("pd_res_y");
		String[] pic_hidden = request.getParameterValues("pic_hidden");

		String content1 = (String) dynaBean.get("content1");
		String content2 = (String) dynaBean.get("content2");
		String content3 = (String) dynaBean.get("content3");

		String main_pic_file = "";
		String pic_file = "";
		String pd_res = "";

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaBcompPd entity = new KonkaBcompPd();
		super.copyProperties(entity, form);

		pd_res = pd_res_x.concat("," + pd_res_y);
		entity.setPd_res(pd_res);

		// 上传主图以及附图
		List<UploadFile> uploadFileList = super
.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0, new int[] {
				60, 86, 120,
				240, 350, 400, 800 });
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
				if (pu.getRole_id().intValue() < 30) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (zb) { // session用户是总部用户
			entity.setDept_sn("0");
			if (StringUtils.isBlank(dept_id)) {
				entity.setDept_sn("0");
			} else {
				entity.setDept_sn(dept_id);
			}
		} else {
			if (fgs) {
				entity.setDept_sn(dept_id);
				entity.setDept_sn(dept_id);
			}
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
		if (ArrayUtils.isNotEmpty(label_of_cate)) {
			entity.setLabel_of_cate(Integer.valueOf(label_of_cate[0]));
		}
		logger.info("+++++++++++++id" + id);
		if (StringUtils.isBlank(id)) {

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

			// super.getFacade().getKonkaBcompPdService().createKonkaBcompPdIncludeContent(entity);
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

			// super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPdIncludeContent(entity);
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("pd_type=" + pd_type);
		pathBuffer.append("&");
		// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
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
		String pd_type = (String) dynaBean.get("pd_type");

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
					if (pu.getRole_id().intValue() < 30) {
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
			super.copyProperties(form, entity);

			// 回显产品描述、产品规格、售后服务
			KonkaBcompPdContent pc = new KonkaBcompPdContent();
			pc.setKbp_id(Long.valueOf(id));
			List<KonkaBcompPdContent> pct = super.getFacade().getKonkaBcompPdContentService()
			        .getKonkaBcompPdContentList(pc);
			for (KonkaBcompPdContent pp : pct) {
				request.setAttribute("content" + pp.getType(), pp.getContent());
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
			pathBuffer.append("&pd_type=").append(pd_type);
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		return mapping.findForward("input");
	}

	public ActionForward upShelf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String pd_type = (String) dynaBean.get("pd_type");

		if (GenericValidator.isLong(id)) {
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
				logger.info("++++" + entity.getDept_sn());
				if ("0".equals(entity.getDept_sn())) {
					KonkaDept dept = new KonkaDept();
					dept.setDept_id(Long.valueOf(entity.getDept_sn()));
					dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
					dynaBean.set("dept_name", dept.getDept_name());
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
			pathBuffer.append("&pd_type=").append(pd_type);
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		return new ActionForward("/../manager/admin/PeCdModel/up_shelf.jsp");
	}

	public ActionForward upShelfSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String up_date = (String) dynaBean.get("up_date");
		String down_date = (String) dynaBean.get("down_date");
		String pd_type = (String) dynaBean.get("pd_type");

		if (GenericValidator.isLong(id)) {
			KonkaBcompPd entity = new KonkaBcompPd();
			entity.setId(Long.valueOf(id));
			Long countRecord = getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(entity);
			if (countRecord.intValue() <= 0) {
				this.saveError(request, "errors.long", new String[] { id });
				// the line below is added for pagination
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&mod_id=").append(mod_id);
				pathBuffer.append("&pd_type=").append(pd_type);
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
				pathBuffer.append("&").append("mod_id=" + mod_id);
				pathBuffer.append("&pd_type=").append(pd_type);
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
			pathBuffer.append("&pd_type=").append(pd_type);
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
		String pd_type = (String) dynaBean.get("pd_type");

		if (GenericValidator.isLong(id)) {
			KonkaBcompPd entity = new KonkaBcompPd();
			entity.setId(Long.valueOf(id));
			Long countRecord = getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(entity);
			if (countRecord.intValue() <= 0) {
				this.saveError(request, "errors.long", new String[] { id });
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&mod_id=").append(mod_id);
				pathBuffer.append("&pd_type=").append(pd_type);
				pathBuffer.append("&");
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				return forward;
			} else {
				entity.setDown_time(new Date());
				super.getFacade().getKonkaBcompPdService().modifyKonkaBcompPd(entity);
				saveMessage(request, "entity.updated");

				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&").append("mod_id=" + mod_id);
				pathBuffer.append("&pd_type=").append(pd_type);
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
			pathBuffer.append("&pd_type=").append(pd_type);
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

	public ActionForward ajAxGetMdName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");

		if(!GenericValidator.isLong(pd_id)){
			super.renderJson(response, "{\"md_name\":\"\"}");
			return null;
		}
		
		PePdModel entity = new PePdModel();
		entity.setPd_id(Long.valueOf(pd_id));
		entity = super.getFacade().getPePdModelService().getPePdModel(entity);
		if(null == entity){
			super.renderJson(response, "{\"md_name\":\"\"}");
			return null;
		}
		super.renderJson(response, "{\"md_name\":\"" + entity.getMd_name() + "\"}");
		return null;
	}
}
