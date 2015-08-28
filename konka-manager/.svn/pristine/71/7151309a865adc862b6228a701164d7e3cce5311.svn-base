package com.ebiz.mmt.web.struts.manager.zmd;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdAuditUserHis;
import com.ebiz.mmt.domain.KonkaXxZmdGcys;
import com.ebiz.mmt.domain.KonkaXxZmdRewardSet;
import com.ebiz.mmt.domain.KonkaXxZmdStore;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Zheng,Kun
 * @version 2012-1-11
 */
public class KonkaXxZmdAction extends BaseZmdAction {

	private static String CHECK_BOX_CHECKED_VALUE = "on";

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;

		Pager pager = (Pager) dynaBean.get("pager");
		String zmd_sn_like = (String) dynaBean.get("zmd_sn_like");
		String busi_type = (String) dynaBean.get("busi_type");
		String arc_state = (String) dynaBean.get("arc_state");
		KonkaXxZmd entity = new KonkaXxZmd();
		if (StringUtils.isNotBlank(zmd_sn_like)) {
			entity.getMap().put("zmd_sn_like", zmd_sn_like);
		}
		if (StringUtils.isNotBlank(busi_type)) {
			entity.setBusi_type(Long.valueOf(busi_type));
		}
		if (StringUtils.isNotBlank(arc_state)) {
			entity.setArc_state(Integer.valueOf(arc_state));
		}
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		List<PeRoleUser> peRoleUserList = getPeRoleList(peProdUser.getId());

		Boolean role_id_btw_300_400 = false;
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() < 400 && temp.getRole_id() >= 300) {
					role_id_btw_300_400 = true;
				}
			}
		}
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (role_id_btw_300_400) {
			KonkaDept kDept = getKonkaDeptForFgs(peProdUser.getDept_id());
			if (kDept != null)
				entity.setDept_id(kDept.getDept_id());
		}
		entity.setIs_del(0);
		entity.getMap().put("apply_date_desc", true);
		Long recordCount = getFacade().getKonkaXxZmdService().getKonkaXxZmdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxZmd> entityList = super.getFacade().getKonkaXxZmdService().getKonkaXxZmdPaginatedList(entity);

		setBaseTypeListByParIdToRequest(10000L, request);
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");

		if (!GenericValidator.isLong(zmd_id)) {
			String msg = super.getMessage(request, "konka.xx.zmd.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(entity);
		if (null == entity) {
			String msg = super.getMessage(request, "konka.xx.zmd.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		String t1 = null;
		if (null != entity.getP_index()) {
			t1 = super.getPIndexName(entity.getP_index().toString(), "");
		}
		String t2 = entity.getAddr();
		String zmd_addr_1 = t1 + t2;
		entity.getMap().put("zmd_addr_1", zmd_addr_1);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		entity.getMap().put("dept_name", konkaDept.getDept_name());

		// 取专卖点返佣设置信息
		KonkaXxZmdRewardSet konkaXxZmdRewardSet = new KonkaXxZmdRewardSet();
		konkaXxZmdRewardSet.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdRewardSet> konkaXxZmdRewardSetList = super.getFacade().getKonkaXxZmdRewardSetService()
				.getKonkaXxZmdRewardSetList(konkaXxZmdRewardSet);
		request.setAttribute("konkaXxZmdRewardSetList", konkaXxZmdRewardSetList);

		// 取专卖店仓库信息
		KonkaXxZmdStore konkaXxZmdStore = new KonkaXxZmdStore();
		konkaXxZmdStore.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdStore> konkaXxZmdStoreList = super.getFacade().getKonkaXxZmdStoreService()
				.getKonkaXxZmdStoreList(konkaXxZmdStore);
		request.setAttribute("konkaXxZmdStoreList", konkaXxZmdStoreList);

		super.copyProperties(form, entity);

		// 取基础类型表里相对应的数据
		setBaseTypeListByParIdToRequest(10000L, request);
		setBaseTypeListByParIdToRequest(20000L, request);
		setBaseTypeListByParIdToRequest(80000L, request);
		setBaseTypeListByParIdToRequest(30000L, request);
		setBaseTypeListByParIdToRequest(100000L, request);
		setBaseTypeListByParIdToRequest(110000L, request);

		// 工程项目
		KonkaXxZmdGcys konkaXxZmdGcys = new KonkaXxZmdGcys();
		konkaXxZmdGcys.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdGcys> konkaXxZmdGcysList = super.getFacade().getKonkaXxZmdGcysService().getKonkaXxZmdGcysList(
				konkaXxZmdGcys);
		request.setAttribute("konkaXxZmdGcysList", konkaXxZmdGcysList);

		BigDecimal total_money = new BigDecimal(0);
		if (konkaXxZmdGcysList.size() > 0) {
			for (KonkaXxZmdGcys temp : konkaXxZmdGcysList) {
				if (null != temp.getTotal() && !"".equals(String.valueOf(temp.getTotal()))) {
					total_money = total_money.add(temp.getTotal());
				}
			}
		}

		request.setAttribute("total_money", total_money);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(entity.getZmd_id());
		attachment.setLink_tab("konka_xx_zmd");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		// 审核意见
		KonkaXxZmdAuditUserHis konkaXxZmdAuditUserHis = new KonkaXxZmdAuditUserHis();
		konkaXxZmdAuditUserHis.setZmd_user_id(Long.valueOf(entity.getZmd_user_id()));
		konkaXxZmdAuditUserHis.setAudit_type(20000L);
		konkaXxZmdAuditUserHis.getMap().put("order_by_id", true);
		List<KonkaXxZmdAuditUserHis> kHis = super.getFacade().getKonkaXxZmdAuditUserHisService()
				.getKonkaXxZmdAuditUserHisList(konkaXxZmdAuditUserHis);
		request.setAttribute("kHis", kHis);

		return mapping.findForward("view");
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

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String edit_value = (String) dynaBean.get("edit_value");

		dynaBean.set("edit_value", edit_value);

		if (!GenericValidator.isLong(zmd_id)) {
			this.saveError(request, "errors.long", new String[] { zmd_id });
			return mapping.findForward("list");
		}

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		// the line below is added for pagination
		entity.setQueryString(super.serialize(request, "zmd_id", "method"));
		// end

		// 回显地区信息
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		entity.getMap().put("dept_name", konkaDept.getDept_name());
		if (entity.getP_index() != null && entity.getP_index() != -1) {
			String p_index = entity.getP_index().toString();
			if (StringUtils.isNotBlank(p_index)) {
				if (!p_index.endsWith("00")) {
					if (p_index.length() == 6) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", p_index);
					} else if (p_index.length() == 8) {
						request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
						request.setAttribute("city", StringUtils.substring(p_index, 0, 4) + "00");
						request.setAttribute("country", StringUtils.substring(p_index, 0, 6));
						request.setAttribute("town", p_index);
					}
				} else if (p_index.endsWith("0000")) {
					request.setAttribute("province", p_index);
				} else if (p_index.endsWith("00")) {
					request.setAttribute("province", StringUtils.substring(p_index, 0, 2) + "0000");
					request.setAttribute("city", p_index);
				}
			}
		}

		// 回显专卖店返佣设置信息
		KonkaXxZmdRewardSet konkaXxZmdRewardSet = new KonkaXxZmdRewardSet();
		konkaXxZmdRewardSet.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdRewardSet> konkaXxZmdRewardSetList = super.getFacade().getKonkaXxZmdRewardSetService()
				.getKonkaXxZmdRewardSetList(konkaXxZmdRewardSet);
		List<Integer> list = new ArrayList<Integer>();
		for (KonkaXxZmdRewardSet set : konkaXxZmdRewardSetList) {
			dynaBean.set("reward_ratio_" + set.getReward_type(), set.getReward_ratio());
			dynaBean.set("is_enabled_" + set.getReward_type(), set.getIs_enabled() == 1 ? CHECK_BOX_CHECKED_VALUE : "");
			dynaBean.set("is_locked_" + set.getReward_type(), set.getIs_locked() == 1 ? CHECK_BOX_CHECKED_VALUE : "");
			list.add(set.getIs_locked());
		}
		request.setAttribute("list", list);
		super.copyProperties(form, entity);

		PeProdUser ui = super.getSessionUserInfo(request);
		// 取符合条件的仓位
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		List<PeRoleUser> peRoleUserList = getPeRoleList(ui.getId());

		Boolean role_id_btw_300_400 = false;
		Boolean role_id_btw_200_300 = false;

		String is_show ="";

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() < 400 && temp.getRole_id() >= 300) {
					role_id_btw_300_400 = true;
				}
				if (temp.getRole_id() < 300 && temp.getRole_id() >= 200) {
					role_id_btw_200_300 = true;
				}
				if (temp.getRole_id() == 220)
					is_show = "2";
			}
		}
		dynaBean.set("is_show", is_show);
		
		if (role_id_btw_300_400) {
			request.setAttribute("role_is_dept", "300");
		}
		if (role_id_btw_200_300) {
			request.setAttribute("role_is_dept", "200");
		}

		KonkaDept kDept = new KonkaDept();
		kDept.setDept_id(Long.valueOf(dept_id));
		kDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);

		String dept_name = kDept.getDept_name();
	
		KonkaR3DeptStore kStore = new KonkaR3DeptStore();
		if (konkaDept != null)
			kStore.setDept_name(dept_name);
		List<KonkaR3DeptStore> kStoreList = super.getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStoreList(kStore);

		// KonkaXxStdStore konkaXxStdStore = new KonkaXxStdStore();
		// konkaXxStdStore.setDept_name(dept_name);
		// List<KonkaXxStdStore> konkaXxStdStoreList =
		// super.getFacade().getKonkaXxStdStoreService()
		// .getKonkaXxStdStoreList(konkaXxStdStore);
		
		request.setAttribute("konkaXxStdStoreList", kStoreList);

		// 回显专卖店仓位信息
		KonkaXxZmdStore konkaXxZmdStore = new KonkaXxZmdStore();
		konkaXxZmdStore.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdStore> konkaXxZmdStoreList = super.getFacade().getKonkaXxZmdStoreService()
				.getKonkaXxZmdStoreList(konkaXxZmdStore);
		for (KonkaXxZmdStore set : konkaXxZmdStoreList) {
			dynaBean.set(set.getFactory_id() + "_" + set.getStore_id(),
					set.getStore_id() != null ? CHECK_BOX_CHECKED_VALUE : "");

		}

		setBaseTypeListByParIdToRequest(30000L, request);
		setBaseTypeListByParIdToRequest(20000L, request);
		setBaseTypeListByParIdToRequest(80000L, request);
		setBaseTypeListByParIdToRequest(10000L, request);
		setBaseTypeListByParIdToRequest(100000L, request);// 经营模式
		setBaseTypeListByParIdToRequest(110000L, request);// 专卖店状态

		// // 专卖店资源信息
		// KonkaXxZmdRes konkaXxZmdRes = new KonkaXxZmdRes();
		// konkaXxZmdRes.setZmd_id(Long.valueOf(zmd_id));
		// List<KonkaXxZmdRes> konkaXxZmdResList =
		// super.getFacade().getKonkaXxZmdResService().getKonkaXxZmdResList(
		// konkaXxZmdRes);
		// String res_ids = "";
		// if (konkaXxZmdResList.size() > 0) {
		// request.setAttribute("konkaXxZmdResList", konkaXxZmdResList);
		// for (KonkaXxZmdRes temp : konkaXxZmdResList) {
		// if ("".equals(res_ids)) {
		// res_ids = "" + temp.getId();
		// } else {
		// res_ids = res_ids + "_" + temp.getId();
		// }
		// }
		// } else {
		// List<KonkaXxZmdRes> arrlist = new ArrayList<KonkaXxZmdRes>();
		// for (int i = 0; i < ZmdRole.res_name.length; i++) {
		// KonkaXxZmdRes res = new KonkaXxZmdRes();
		// res.setRes_name(ZmdRole.res_name[i]);
		// res.setRes_id(Long.valueOf(ZmdRole.res_num[i]));
		// arrlist.add(res);
		// }
		// request.setAttribute("konkaXxZmdResList", arrlist);
		// }
		// dynaBean.set("res_ids", res_ids);
		// 工程项目
		KonkaXxZmdGcys konkaXxZmdGcys = new KonkaXxZmdGcys();
		konkaXxZmdGcys.setZmd_id(Long.valueOf(zmd_id));
		List<KonkaXxZmdGcys> konkaXxZmdGcysList = super.getFacade().getKonkaXxZmdGcysService().getKonkaXxZmdGcysList(
				konkaXxZmdGcys);
		request.setAttribute("konkaXxZmdGcysList", konkaXxZmdGcysList);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(Long.valueOf(zmd_id));
		attachment.setLink_tab("konka_xx_zmd");
		attachment.setIs_del(0l);
		request.setAttribute("attachmentList", super.getFacade().getKonkaPeAttachmentsService()
				.getKonkaPeAttachmentsList(attachment));

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String store_ids = (String) dynaBean.get("store_ids");
		String mod_id = (String) dynaBean.get("mod_id");
		String edit_value = (String) dynaBean.get("edit_value");
		KonkaXxZmd entity = new KonkaXxZmd();

		// 工程预算
		String[] item_name = request.getParameterValues("item_name");
		String[] pd_name = request.getParameterValues("pd_name");
		String[] model_name = request.getParameterValues("model_name");
		String[] item_num = request.getParameterValues("item_num");
		String[] unit = request.getParameterValues("unit");
		String[] price = request.getParameterValues("price");
		String[] total = request.getParameterValues("total");

		if (StringUtils.isNotBlank(zmd_id)) {
			entity.setZmd_id(Long.valueOf(zmd_id));
			entity = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(entity);
			if (entity != null) {
				entity.getMap().put("store_ids", store_ids);
				super.copyProperties(entity, form);
				entity.getMap().put("Verification", "0");

				// List<KonkaXxZmdRes> konkaXxZmdResList = new
				// ArrayList<KonkaXxZmdRes>();// 资源数据
				// if (StringUtils.isNotBlank(res_ids)) {
				// String ids[] = res_ids.split("_");
				// for (int i = 0; i < ids.length; i++) {
				// KonkaXxZmdRes konkaXxZmdRes = new KonkaXxZmdRes();
				// konkaXxZmdRes.setId(Long.valueOf(ids[i]));
				// BeanUtils.setProperty(konkaXxZmdRes, "res_pro",
				// dynaBean.get("res_pro_" + ids[i]));
				// konkaXxZmdResList.add(konkaXxZmdRes);
				// }
				// entity.getMap().put("is_add", "-1");
				// } else {
				// entity.getMap().put("is_add", "1");
				//
				// for (int i = 0; i < ZmdRole.res_name.length; i++) {
				// KonkaXxZmdRes konkaXxZmdRes = new KonkaXxZmdRes();
				// konkaXxZmdRes.setRes_id(Long.valueOf(ZmdRole.res_num[i]));
				// konkaXxZmdRes.setRes_name(ZmdRole.res_name[i]);
				// BeanUtils.setProperty(konkaXxZmdRes, "res_pro",
				// dynaBean.get("res_pro_" + ZmdRole.res_num[i]));
				// konkaXxZmdResList.add(konkaXxZmdRes);
				// }
				// }
				if (StringUtils.isNotBlank(edit_value)) {
					entity.setArc_state(20300);
				} else {
					entity.setArc_state(20100);
				}

				// 工程预算
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
					if (null != item_num[i] && StringUtils.isNotBlank(item_num[i])
							&& GenericValidator.isDouble(item_num[i])) {
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

				PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
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

				// entity.setKonkaXxZmdResList(konkaXxZmdResList);
				super.getFacade().getKonkaXxZmdService().modifyKonkaXxZmd(entity);
				saveMessage(request, "entity.updated");
			}
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		if (StringUtils.isNotBlank(edit_value)) {
			pathBuffer.append("KonkaXxZmdAuditUserInfo.do?method=list");
		} else {
			pathBuffer.append("KonkaXxZmdAuditUserInfo.do?");
		}
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward ajaxSaveRewardType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String type_id = (String) dynaBean.get("type_id");
		String reward_ratio = (String) dynaBean.get("reward_ratio");
		String is_enabled = (String) dynaBean.get("is_enabled");
		String is_locked = (String) dynaBean.get("is_locked");

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		if (StringUtils.isBlank(is_enabled) || StringUtils.isBlank(is_locked) || StringUtils.isBlank(reward_ratio)) {
			super.renderText(response, "0");
			return null;
		}

		KonkaXxZmdRewardSet entity = new KonkaXxZmdRewardSet();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.setReward_type(Long.valueOf(type_id));
		entity = super.getFacade().getKonkaXxZmdRewardSetService().getKonkaXxZmdRewardSet(entity);
		if (null == entity) {
			// insert
			KonkaXxZmdRewardSet set = new KonkaXxZmdRewardSet();
			set.setZmd_id(Long.valueOf(zmd_id));
			set.setReward_type(Long.valueOf(type_id));
			set.setReward_ratio(new BigDecimal(reward_ratio));
			set.setIs_enabled(Integer.valueOf(is_enabled));
			set.setIs_locked(Integer.valueOf(is_locked));

			KonkaDept kDept = getKonkaDeptForFgs(userInfo.getDept_id());
			if (kDept != null)
				set.setDept_id(kDept.getDept_id());
			set.setSet_user_id(userInfo.getId());
			set.setUpdate_user_id(userInfo.getId());
			super.getFacade().getKonkaXxZmdRewardSetService().createKonkaXxZmdRewardSet(set);
		} else {
			// update
			entity.setZmd_id(Long.valueOf(zmd_id));
			entity.setReward_type(Long.valueOf(type_id));
			entity.setReward_ratio(new BigDecimal(reward_ratio));
			entity.setIs_enabled(Integer.valueOf(is_enabled));
			entity.setIs_locked(Integer.valueOf(is_locked));

			entity.setLast_update_time(new Date());
			entity.setUpdate_user_id(userInfo.getId());
			super.getFacade().getKonkaXxZmdRewardSetService().modifyKonkaXxZmdRewardSet(entity);
		}

		super.renderText(response, "1");
		return null;
	}

	public ActionForward ajaxOpenRewardType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String zmd_id = (String) dynaBean.get("zmd_id");
		String type_id = (String) dynaBean.get("type_id");

		HttpSession session = request.getSession();
		PeProdUser userInfo = new PeProdUser();
		userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaXxZmdRewardSet entity = new KonkaXxZmdRewardSet();
		entity.setZmd_id(Long.valueOf(zmd_id));
		entity.setReward_type(Long.valueOf(type_id));
		entity = super.getFacade().getKonkaXxZmdRewardSetService().getKonkaXxZmdRewardSet(entity);
		if (entity != null) {
			entity.setIs_locked(Integer.valueOf(0));
			entity.setLast_update_time(new Date());
			entity.setUpdate_user_id(userInfo.getId());
			super.getFacade().getKonkaXxZmdRewardSetService().modifyKonkaXxZmdRewardSet(entity);
			super.renderText(response, "1");
		} else {
			super.renderText(response, "0");
			return null;
		}
		return null;
	}

	public ActionForward zmdView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		KonkaXxZmdUsers konkaXxZmdUsers = new KonkaXxZmdUsers();
		konkaXxZmdUsers.setUser_id(userInfo.getId());
		List<KonkaXxZmdUsers> konkaXxZmdUsersList = super.getFacade().getKonkaXxZmdUsersService()
				.getKonkaXxZmdUsersList(konkaXxZmdUsers);

		if (konkaXxZmdUsersList.size() == 0 || null == konkaXxZmdUsersList) {
			String msg = super.getMessage(request, "konka.xx.property.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		Long zmd_id = konkaXxZmdUsersList.get(0).getZmd_id();

		KonkaXxZmd entity = new KonkaXxZmd();
		entity.setZmd_id(zmd_id);
		entity = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
		String t1 = super.getPIndexName(entity.getP_index().toString(), "");
		String t2 = entity.getAddr();
		String zmd_addr_1 = t1 + t2;
		entity.getMap().put("zmd_addr_1", zmd_addr_1);

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(entity.getDept_id());
		konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
		entity.getMap().put("dept_name", konkaDept.getDept_name());

		// 取专卖点返佣设置信息
		KonkaXxZmdRewardSet konkaXxZmdRewardSet = new KonkaXxZmdRewardSet();
		konkaXxZmdRewardSet.setZmd_id(zmd_id);
		List<KonkaXxZmdRewardSet> konkaXxZmdRewardSetList = super.getFacade().getKonkaXxZmdRewardSetService()
				.getKonkaXxZmdRewardSetList(konkaXxZmdRewardSet);
		request.setAttribute("konkaXxZmdRewardSetList", konkaXxZmdRewardSetList);

		// 取专卖店仓库信息
		KonkaXxZmdStore konkaXxZmdStore = new KonkaXxZmdStore();
		konkaXxZmdStore.setZmd_id(zmd_id);
		List<KonkaXxZmdStore> konkaXxZmdStoreList = super.getFacade().getKonkaXxZmdStoreService()
				.getKonkaXxZmdStoreList(konkaXxZmdStore);
		request.setAttribute("konkaXxZmdStoreList", konkaXxZmdStoreList);

		super.copyProperties(form, entity);

		// 取基础类型表里相对应的数据
		setBaseTypeListByParIdToRequest(10000L, request);
		setBaseTypeListByParIdToRequest(20000L, request);
		setBaseTypeListByParIdToRequest(80000L, request);
		setBaseTypeListByParIdToRequest(30000L, request);
		setBaseTypeListByParIdToRequest(100000L, request);
		setBaseTypeListByParIdToRequest(110000L, request);

		// // 专卖店资源
		// KonkaXxZmdRes konkaXxZmdRes = new KonkaXxZmdRes();
		// konkaXxZmdRes.setZmd_id(zmd_id);
		// List<KonkaXxZmdRes> konkaXxZmdResList =
		// super.getFacade().getKonkaXxZmdResService().getKonkaXxZmdResList(
		// konkaXxZmdRes);
		// if (konkaXxZmdResList.size() > 0) {
		// request.setAttribute("konkaXxZmdResList", konkaXxZmdResList);
		// } else {
		// List<KonkaXxZmdRes> arrlist = new ArrayList<KonkaXxZmdRes>();
		// for (int i = 0; i < ZmdRole.res_name.length; i++) {
		// KonkaXxZmdRes res = new KonkaXxZmdRes();
		// res.setRes_name(ZmdRole.res_name[i]);
		// res.setRes_id(Long.valueOf(ZmdRole.res_num[i]));
		// arrlist.add(res);
		// }
		// request.setAttribute("konkaXxZmdResList", arrlist);
		// }

		return mapping.findForward("view");
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			KonkaPeAttachments entity = new KonkaPeAttachments();
			entity.setId(new Long(id));
			super.getFacade().getKonkaPeAttachmentsService().removeKonkaPeAttachments(entity);
			// saveMessage(request, "entity.deleted");
		}

		super.renderText(response, "success");
		return null;
	}
}
