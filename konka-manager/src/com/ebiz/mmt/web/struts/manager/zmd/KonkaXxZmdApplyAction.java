package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.KonkaXxAuditNote;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserInfo;
import com.ebiz.mmt.domain.KonkaXxZmdGcys;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSet;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Zheng,Kun
 * @version 2012-1-9
 */
public class KonkaXxZmdApplyAction extends BaseZmdAction {

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String zmd_user_id = (String) dynaBean.get("zmd_user_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(zmd_user_id)) {
			String msg = super.getMessage(request, "errors.parm");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmdAuditUserInfo entity = new KonkaXxZmdAuditUserInfo();// 获取客户资质信息
		entity.setZmd_user_id(Long.valueOf(zmd_user_id));
		entity = super.getFacade().getKonkaXxZmdAuditUserInfoService().getKonkaXxZmdAuditUserInfo(entity);

		if (entity == null) {
			String msg = super.getMessage(request, "entity.missing");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		dynaBean.set("zmd_user_id", zmd_user_id);
		dynaBean.set("zmd_user_name", entity.getUser_name());
		dynaBean.set("zmd_sn", entity.getZmd_sn());
		dynaBean.set("tel", entity.getTel());
		dynaBean.set("mod_id", mod_id);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		KonkaDept konkaDept = getKonkaDeptForFgs(peProdUser.getDept_id());
		if (konkaDept != null) {
			request.setAttribute("dept_name", konkaDept.getDept_name());
			request.setAttribute("dept_id", konkaDept.getDept_id());
		}

		setBaseTypeListByParIdToRequest(80000L, request);
		setBaseTypeListByParIdToRequest(10000L, request);
		setBaseTypeListByParIdToRequest(100000L, request);// 经营模式
		setBaseTypeListByParIdToRequest(110000L, request);// 专卖店状态
		setBaseTypeListByParIdToRequest(140000L, request);// 区域、大区
		setBaseTypeListByParIdToRequest(150000L, request);// 工程预算

		KonkaR3DeptStore kStore = new KonkaR3DeptStore();
		if (konkaDept != null)
			kStore.setDept_name(konkaDept.getDept_name());
		List<KonkaR3DeptStore> kStoreList = super.getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStoreList(kStore);

		// KonkaXxStdStore konkaXxStdStore = new KonkaXxStdStore();
		// if (konkaDept != null)
		// konkaXxStdStore.setDept_name(konkaDept.getDept_name());
		// List<KonkaXxStdStore> konkaXxStdStoreList =
		// super.getFacade().getKonkaXxStdStoreService()
		// .getKonkaXxStdStoreList(konkaXxStdStore);
		request.setAttribute("konkaXxStdStoreList", kStoreList);

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String province = (String) dynaBean.get("province");
		String city = (String) dynaBean.get("city");
		String country = (String) dynaBean.get("country");
		String town = (String) dynaBean.get("town");
		String store_ids = (String) dynaBean.get("store_ids");
		String mod_id = (String) dynaBean.get("mod_id");
		String zmd_user_id = (String) dynaBean.get("zmd_user_id");

		// 工程预算
		String[] item_name = request.getParameterValues("item_name");
		String[] pd_name = request.getParameterValues("pd_name");
		String[] model_name = request.getParameterValues("model_name");
		String[] item_num = request.getParameterValues("item_num");
		String[] unit = request.getParameterValues("unit");
		String[] price = request.getParameterValues("price");
		String[] total = request.getParameterValues("total");

		KonkaXxZmd entity = new KonkaXxZmd();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(zmd_user_id)) {
			entity.setZmd_user_id(Long.valueOf(zmd_user_id));
		}
		if (GenericValidator.isLong(province) && !GenericValidator.isLong(city)) {
			entity.setP_index(Long.valueOf(province));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && !GenericValidator.isLong(country)) {
			entity.setP_index(Long.valueOf(city));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
				&& !GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(country));
		}
		if (GenericValidator.isLong(province) && GenericValidator.isLong(city) && GenericValidator.isLong(country)
				&& GenericValidator.isLong(town)) {
			entity.setP_index(Long.valueOf(town));
		}

		// 初始化状态位
		entity.setArc_state(20100);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		List<KonkaXxBaseType> konkaXxBaseTypeList = super.getBaseTypeListByParId(80000L);
		List<KonkaXxZmdRewardSet> konkaXxZmdRewardSetList = new ArrayList<KonkaXxZmdRewardSet>();
		for (KonkaXxBaseType type : konkaXxBaseTypeList) {
			KonkaXxZmdRewardSet set = new KonkaXxZmdRewardSet();
			Long type_id = type.getType_id();

			String reward_ratio = (String) dynaBean.get("reward_ratio_" + type_id);
			String is_enabled = (String) dynaBean.get("is_enabled_" + type_id);
			String is_locked = (String) dynaBean.get("is_locked_" + type_id);

			KonkaDept kDept = getKonkaDeptForFgs(peProdUser.getDept_id());
			if (kDept != null)
			set.setDept_id(kDept.getDept_id());
			set.setReward_type(type_id);
			set.setIs_enabled(null == is_enabled ? 0 : 1);
			set.setIs_locked(null == is_locked ? 0 : 1);
			if (null != reward_ratio && StringUtils.isNotBlank(reward_ratio) && GenericValidator.isDouble(reward_ratio)) {
				set.setReward_ratio(new BigDecimal(reward_ratio));
			} else {
				set.setReward_ratio(new BigDecimal(0));
			}
			set.setSet_user_id(peProdUser.getId());
			konkaXxZmdRewardSetList.add(set);
		}
		entity.getMap().put("store_ids", store_ids);
		entity.setKonkaXxZmdRewardSetList(konkaXxZmdRewardSetList);

		List<KonkaXxZmdGcys> konkaXxZmdGcysList = new ArrayList<KonkaXxZmdGcys>();
		for (int i = 0; i < item_name.length; i++) {
			KonkaXxZmdGcys konkaXxZmdGcys = new KonkaXxZmdGcys();
			if (StringUtils.isNotBlank(item_name[i])) {
				konkaXxZmdGcys.setItem_name(item_name[i]);
			} else {
				break;
			}
			if (StringUtils.isNotBlank(pd_name[i])) {
				konkaXxZmdGcys.setPd_name(pd_name[i]);
			}
			if (StringUtils.isNotBlank(model_name[i])) {
				konkaXxZmdGcys.setModel_name(model_name[i]);
			}
			if (null != item_num[i] && StringUtils.isNotBlank(item_num[i]) && GenericValidator.isDouble(item_num[i])) {
				konkaXxZmdGcys.setItem_num(new BigDecimal(item_num[i]));
			}
			if (StringUtils.isNotBlank(unit[i])) {
				konkaXxZmdGcys.setUnit(unit[i]);
			}
			if (null != price[i] && StringUtils.isNotBlank(price[i]) && GenericValidator.isDouble(price[i])) {
				konkaXxZmdGcys.setPrice(new BigDecimal(price[i]));
			}
			if (null != total[i] && StringUtils.isNotBlank(total[i]) && GenericValidator.isDouble(total[i])) {
				konkaXxZmdGcys.setTotal(new BigDecimal(total[i]));
			}
			konkaXxZmdGcysList.add(konkaXxZmdGcys);
		}
		entity.setKonkaXxZmdGcysList(konkaXxZmdGcysList);

		// 附件
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, true, 0,
				new int[] { 240 });
		List<KonkaPeAttachments> attachmentList = new ArrayList<KonkaPeAttachments>();
		KonkaPeAttachments attachment = null;
		for (UploadFile uploadFile : uploadFileList) {
			attachment = new KonkaPeAttachments();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(new Long(uploadFile.getFileSize()));
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setIs_del(0l);
			attachment.setLink_tab("konka_xx_zmd");
			attachment.setAdd_user_name(peProdUser.getUser_name());
			attachment.setAdd_user_id(peProdUser.getId());
			attachmentList.add(attachment);
		}
		entity.setAttachmentList(attachmentList);

		KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis = new KonkaXxZmdAuditUserHis();
		KonkaXxAuditNote konkaXxAuditNote = new KonkaXxAuditNote();
		konkaXxAuditNote.setAudit_type(20L);// 资质审核
		konkaXxAuditNote.setAudit_node_id(300L);
		List<KonkaXxAuditNote> konkaXxAuditNoteList = super.getFacade().getKonkaXxAuditNoteService()
				.getKonkaXxAuditNoteList(konkaXxAuditNote);
		if (konkaXxAuditNoteList.size() > 0) {
			konkaXxZmdAuditUserHis.setIs_end(0);
			konkaXxZmdAuditUserHis.setAudit_next_node_id(konkaXxAuditNoteList.get(0).getAudit_next_node_id());
			konkaXxZmdAuditUserHis.setAudit_next_node_name(konkaXxAuditNoteList.get(0).getAudit_next_node_name());
			konkaXxZmdAuditUserHis.setAudit_status(20100L);
			konkaXxZmdAuditUserHis.setAudit_desc("备案申请");
			konkaXxZmdAuditUserHis.setAudit_type(20000L);
			konkaXxZmdAuditUserHis.setAudit_status_desc("提交备案申请");
			konkaXxZmdAuditUserHis.setAudit_user_id(peProdUser.getId());
			konkaXxZmdAuditUserHis.setAudit_user_name("--");
			konkaXxZmdAuditUserHis.setAudit_date(new Date());
		}

		entity.setKonkaXxZmdAuditUserHis(konkaXxZmdAuditUserHis);
		super.getFacade().getKonkaXxZmdService().createKonkaXxZmd(entity);
		saveMessage(request, "entity.inserted");

		// return new ActionForward("/../manager/zmd/KonkaXxZmdApply.do", true);

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("KonkaXxZmdAuditUserInfo.do?");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward validateZmsSn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String zmd_sn = (String) dynaBean.get("zmd_sn");
		KonkaXxZmd entity = new KonkaXxZmd();
		String isExist = "null";

		if (StringUtils.isNotBlank(zmd_sn)) {
			entity.setZmd_sn(zmd_sn);
			List<KonkaXxZmd> entityList = getFacade().getKonkaXxZmdService().getKonkaXxZmdList(entity);
			if (entityList.size() == 0) {// 可用
				isExist = String.valueOf("0");
			} else {

				isExist = String.valueOf("11");
			}
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}
}
