package com.ebiz.mmt.web.struts.manager.spgl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcGift;
import com.ebiz.mmt.domain.EcGiftComm;
import com.ebiz.mmt.domain.EcGiftContent;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-10
 */
public class EcGiftAction extends BaseAction {
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
		String own_sys = request.getParameter("own_sys");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		EcGift entity = new EcGift();
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue()==178 || pu.getRole_id().intValue()==140317 || pu.getRole_id().intValue()==1001 ) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (zb && !fgs) {// session用户属于总部，不属于分公司
			entity.setDept_sn("-1");
		}
		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			entity.setDept_sn(fgs_dept.getDept_sn());
		}
		if (!zb && !fgs) {
			String msg = super.getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		// 所属系统：1-工卡，2-触网，3-会员
		entity.setOwn_sys(Integer.valueOf(own_sys));
		// 状态：0-已停用 1-正常 -1-已删除
		entity.setState(1);
		Long recordCount = super.getFacade().getEcGiftService().getEcGiftCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<EcGift> entityList = super.getFacade().getEcGiftService().getEcGiftPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		request.setAttribute("today", new Date());
		dynaBean.set("own_sys", own_sys);
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
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue()==178 || pu.getRole_id().intValue()==140317 || pu.getRole_id().intValue()==1001 ) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb && !fgs) {// session用户属于总部，不属于分公司
			dynaBean.set("dept_sn", "-1");
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
					if (pu.getRole_id() < 30L  || pu.getRole_id().intValue()==178|| pu.getRole_id().intValue()==140317 || pu.getRole_id().intValue()==1001 ) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}

			if (zb && !fgs) {// session用户属于总部，不属于分公司
				dynaBean.set("dept_sn", "-1");
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

			EcGift entity = new EcGift();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getEcGiftService().getEcGift(entity);

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
			}
			super.copyProperties(form, entity);

			// 回显产品描述、产品规格、售后服务
			EcGiftContent ec = new EcGiftContent();
			ec.setKbp_id(Long.valueOf(id));
			List<EcGiftContent> ecList = super.getFacade().getEcGiftContentService().getEcGiftContentList(ec);
			for (EcGiftContent e : ecList) {
				request.setAttribute("content" + e.getType(), e.getContent());
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
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue()==178 || pu.getRole_id().intValue()==140317 || pu.getRole_id().intValue()==1001 ) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}

			if (zb && !fgs) {// session用户属于总部，不属于分公司
				dynaBean.set("dept_sn", "-1");
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

			EcGift entity = new EcGift();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getEcGiftService().getEcGift(entity);

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
			}
			super.copyProperties(form, entity);

			// 回显产品描述、产品规格、售后服务
			EcGiftContent ec = new EcGiftContent();
			ec.setKbp_id(Long.valueOf(id));
			List<EcGiftContent> ecList = super.getFacade().getEcGiftContentService().getEcGiftContentList(ec);
			for (EcGiftContent e : ecList) {
				request.setAttribute("content" + e.getType(), e.getContent());
			}

			request.setAttribute("today", new Date());

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
		String own_sys = (String) dynaBean.get("own_sys");

		String main_pic_hidden = (String) dynaBean.get("main_pic_hidden");
		String[] pic_hidden = request.getParameterValues("pic_hidden");

		String content1 = (String) dynaBean.get("content1");
		String content2 = (String) dynaBean.get("content2");
		String content3 = (String) dynaBean.get("content3");
		
		String ecGiftComm_id = (String) dynaBean.get("ecGiftComm_id");
		String ecGiftComm_price = (String) dynaBean.get("ecGiftComm_price");
		String ecGiftComm_url = (String) dynaBean.get("ecGiftComm_url");
		

		EcGift entity = new EcGift();
		super.copyProperties(entity, form);
		
		EcGiftComm ecGiftComm = new EcGiftComm();
		if(ecGiftComm_id!=null&&!"".equals(ecGiftComm_id)){
			ecGiftComm.setId(new Long(ecGiftComm_id));
		}
		ecGiftComm.setGoods_url(ecGiftComm_url);
		if(ecGiftComm_price!=null &&!"".equals(ecGiftComm_price)){
			ecGiftComm.setPrice(new BigDecimal(ecGiftComm_price));
		}
		if(StringUtils.isBlank(ecGiftComm_id)&&StringUtils.isBlank(ecGiftComm_price)&&StringUtils.isBlank(ecGiftComm_url)){
			ecGiftComm=null;
		}
		entity.setEcGiftComm(ecGiftComm);
		
		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue()==178 || pu.getRole_id().intValue()==140317 || pu.getRole_id().intValue()==1001 ) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}
		if (zb && !fgs) {// session用户属于总部，不属于分公司
			entity.setDept_sn("-1");
		}
		if (!zb && fgs) { // session用户不属于总部，属于分公司
			KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);// 先暂用此方法，等待家勇公共方法....
			entity.setDept_sn(fgs_dept.getDept_sn());
		}
		if (!zb && !fgs) {
			String msg = super.getMessage(request, "popedom.check.invalid");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		// 上传主图以及附图
		String main_pic_file = "";
		String pic_file = "";
		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 60, 86, 95, 120, 240, 280, 350,
				400, 800 });
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
		if (StringUtils.isNotBlank(pic_file)) {
			entity.setMain_pic(main_pic_file.concat("," + pic_file));
		} else {
			entity.setMain_pic(main_pic_file);
		}

		// 1-产品描述
		List<EcGiftContent> ecGiftContentList = new ArrayList<EcGiftContent>();
		EcGiftContent ecGiftContent_1 = new EcGiftContent();
		ecGiftContent_1.setType(1);
		ecGiftContent_1.setContent(content1);
		ecGiftContentList.add(ecGiftContent_1);

		// 2-产品规格
		EcGiftContent ecGiftContent_2 = new EcGiftContent();
		ecGiftContent_2.setType(2);
		ecGiftContent_2.setContent(content2);
		ecGiftContentList.add(ecGiftContent_2);

		// 3-售后服务
		EcGiftContent ecGiftContent_3 = new EcGiftContent();
		ecGiftContent_3.setType(3);
		ecGiftContent_3.setContent(content3);
		ecGiftContentList.add(ecGiftContent_3);

		entity.setEcGiftContentList(ecGiftContentList);

		if (StringUtils.isBlank(id)) {
			// 新增
			entity.setView_counts(new Long(0));
			entity.setState(1);
			entity.setOwn_sys(Integer.valueOf(own_sys));
			entity.setLast_up_time(new Date());
			entity.setAdd_u_id(user.getId());

			super.getFacade().getEcGiftService().createEcGiftIncludeContent(entity);

			saveMessage(request, "entity.inserted");
		} else {
			entity.setId(Long.valueOf(id));
			super.getFacade().getEcGiftService().modifyEcGiftIncludeContent(entity);
			saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("own_sys=" + own_sys);
		pathBuffer.append("&");
		// pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward upShelf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
					if (pu.getRole_id() < 30L || pu.getRole_id().intValue()==178 || pu.getRole_id().intValue()==140317 || pu.getRole_id().intValue()==1001 ) {
						zb = true;
					} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
						fgs = true;
					}
				}
			}

			if (zb && !fgs) {// session用户属于总部，不属于分公司
				dynaBean.set("dept_sn", "-1");
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

			EcGift entity = new EcGift();
			entity.setId(Long.valueOf(id));

			entity = super.getFacade().getEcGiftService().getEcGift(entity);

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
			}
			super.copyProperties(form, entity);

			// 回显产品描述、产品规格、售后服务
			EcGiftContent ec = new EcGiftContent();
			ec.setKbp_id(Long.valueOf(id));
			List<EcGiftContent> ecList = super.getFacade().getEcGiftContentService().getEcGiftContentList(ec);
			for (EcGiftContent e : ecList) {
				request.setAttribute("content" + e.getType(), e.getContent());
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
		return new ActionForward("/../manager/spgl/EcGift/up_shelf.jsp");

	}

	public ActionForward upShelfSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String up_date = (String) dynaBean.get("up_date");
		String down_date = (String) dynaBean.get("down_date");
		String own_sys = (String) dynaBean.get("own_sys");

		if (GenericValidator.isLong(id)) {
			EcGift entity = new EcGift();
			entity.setId(Long.valueOf(id));
			Long countRecord = getFacade().getEcGiftService().getEcGiftCount(entity);
			if (countRecord.intValue() <= 0) {
				this.saveError(request, "errors.long", new String[] { id });
				// the line below is added for pagination
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&mod_id=").append(mod_id);
				pathBuffer.append("&").append("own_sys=" + own_sys);
				pathBuffer.append("&");
				// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
				// "user_id", "method")));
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				// end
				return forward;
			} else {
				entity.setLast_up_time(DateUtils.parseDate(up_date, new String[] { "yyyy-MM-dd" }));
				entity.setDown_time(DateUtils.parseDate(down_date, new String[] { "yyyy-MM-dd" }));
				super.getFacade().getEcGiftService().modifyEcGift(entity);
				saveMessage(request, "entity.updated");

				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&").append("mod_id=" + mod_id);
				pathBuffer.append("&").append("own_sys=" + own_sys);
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
			pathBuffer.append("&").append("own_sys=" + own_sys);
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
		String own_sys = (String) dynaBean.get("own_sys");

		if (GenericValidator.isLong(id)) {
			EcGift entity = new EcGift();
			entity.setId(Long.valueOf(id));
			Long countRecord = getFacade().getEcGiftService().getEcGiftCount(entity);
			if (countRecord.intValue() <= 0) {
				this.saveError(request, "errors.long", new String[] { id });
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&").append("own_sys=" + own_sys);
				pathBuffer.append("&mod_id=").append(mod_id);
				pathBuffer.append("&");
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				return forward;
			} else {
				entity.setDown_time(new Date());
				super.getFacade().getEcGiftService().modifyEcGift(entity);
				saveMessage(request, "entity.updated");

				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("&").append("own_sys=" + own_sys);
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
			pathBuffer.append("&").append("own_sys=" + own_sys);
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request,
			// "user_id", "method")));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
	}

}