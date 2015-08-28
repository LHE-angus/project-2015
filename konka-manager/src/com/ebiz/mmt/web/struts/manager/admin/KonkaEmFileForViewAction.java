package com.ebiz.mmt.web.struts.manager.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaEmFile;
import com.ebiz.mmt.domain.KonkaEmFileContent;
import com.ebiz.mmt.domain.KonkaEmFileReceiveUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-28
 */
public class KonkaEmFileForViewAction extends BaseAction {
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

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String file_no_like = (String) dynaBean.get("file_no_like");
		String file_title_like = (String) dynaBean.get("file_title_like");
		String file_type = (String) dynaBean.get("file_type");
		String add_time_start = (String) dynaBean.get("add_time_start");
		String add_time_end = (String) dynaBean.get("add_time_end");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (null == userInfo) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_le_8000 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 8000L) {
				role_id_le_8000 = true;
			}
		}

		// 角色判断处理
		if (role_id_le_8000) { // 工程机角色

		} else {
			String msg = super.getMessage(request, "popedom.check.invalid");
			//super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			//return null;
		}

		KonkaEmFile entity = new KonkaEmFile();
		if (StringUtils.isNotBlank(file_no_like)) {
			entity.getMap().put("file_no_like", file_no_like);
		}
		if (StringUtils.isNotBlank(file_title_like)) {
			entity.getMap().put("file_title_like", file_title_like);
		}
		if (StringUtils.isNotBlank(file_type)) {
			entity.setFile_type(new Integer(file_type));
		}
		if (StringUtils.isNotBlank(add_time_start)) {
			entity.getMap().put("add_time_start", add_time_start + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_time_end)) {
			entity.getMap().put("add_time_end", add_time_end + " 23:59:59");
		}
		//entity.getMap().put("user_id", userInfo.getId());
		entity.setIs_del(0);

		Long recordCount = getFacade().getKonkaEmFileService().getKonkaEmFileForViewCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaEmFile> entityList = getFacade().getKonkaEmFileService().getKonkaEmFileForViewPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		entity.getRow().setCount(null);// 去除数量限制
		List<KonkaEmFile> excelList = getFacade().getKonkaEmFileService().getKonkaEmFileForViewList(entity);
		request.setAttribute("excelList", excelList);

		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		// HttpSession session = request.getSession();
		// UserInfo ui = (UserInfo) session.getAttribute(Keys.ADMIN_USER);

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		// String mod_id = (String) dynaBean.get("mod_id");
		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaEmFile entity = new KonkaEmFile();
		entity.setId(new Long(id));
		entity = getFacade().getKonkaEmFileService().getKonkaEmFile(entity);

		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		super.copyProperties(form, entity);

		// 文件内容
		KonkaEmFileContent kc = new KonkaEmFileContent();
		kc.setLink_id(new Long(id));
		List<KonkaEmFileContent> kcList = super.getFacade().getKonkaEmFileContentService()
				.getKonkaEmFileContentList(kc);
		if (null != kcList && kcList.size() > 0) {
			kc = kcList.get(0);
			request.setAttribute("file_content", kc.getFile_content());
		}

		// 附件
		Attachment attachment = new Attachment();
		attachment.setLink_id(new Long(id));
		attachment.setLink_tab("KONKA_EM_FILE");
		attachment.setDel_mark(new Short("0"));
		request.setAttribute("attachmentList", getFacade().getAttachmentService().getAttachmentList(attachment));

		// 公件下发对象
		KonkaEmFileReceiveUser ku = new KonkaEmFileReceiveUser();
		ku.setLink_id(entity.getId());
		List<KonkaEmFileReceiveUser> kuList = super.getFacade().getKonkaEmFileReceiveUserService()
				.getKonkaEmFileReceiveUserList(ku);
		if (null != kuList && kuList.size() > 0) {
			Long[] user_ids = new Long[kuList.size()];
			String[] user_names = new String[kuList.size()];
			if (null != kuList && kuList.size() > 0) {
				for (int i = 0; i < kuList.size(); i++) {
					user_ids[i] = kuList.get(i).getFile_receive_id();
					// user_names[i] = kuList.get(i).getReceive_user();
				}
				PeProdUser peProdUser = new PeProdUser();
				String sumIds = StringUtils.join(user_ids, ",");

				peProdUser.getMap().put("ids", sumIds);
				List<PeProdUser> peProdUserList = getFacade().getPeProdUserService().getPeProdUserList(peProdUser);
				if (null != peProdUserList && peProdUserList.size() > 0) {
					// 防止异常数据报错
					int num = kuList.size();
					if (peProdUserList.size() < kuList.size()) {
						num = peProdUserList.size();
					}
					for (int i = 0; i < num; i++) {
						PeProdUser p = peProdUserList.get(i);
						user_names[i] = p.getDepartment() == null ? p.getReal_name() : (p.getDepartment() + "("
								+ p.getReal_name() + ")");
					}
				}

			}
			dynaBean.set("user_ids", StringUtils.join(user_ids, ",") + ",");// 为了和页面保持一致,需在之后加,
			logger.info("=====> user_ids is {}", StringUtils.join(user_ids, ","));
			dynaBean.set("user_names", StringUtils.join(user_names, ","));
		}

		return mapping.findForward("view");

	}

}