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
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.EcAuctionMain;
import com.ebiz.mmt.domain.EcBaseExpress;
import com.ebiz.mmt.domain.EcLuckyTime;
import com.ebiz.mmt.domain.KonkaBcompPd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Pan,Gang
 * @version 2013-09-24
 */
public class EcAuctionMainAction extends BaseAction {
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String title_like = (String) dynaBean.get("title_like");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcAuctionMain entity = new EcAuctionMain();
		if (StringUtils.isNotBlank(title_like)) {
			entity.getMap().put("title_like", title_like);

		}

		Long recordCount = super.getFacade().getEcAuctionMainService().getEcAuctionMainCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcAuctionMain> entityList = super.getFacade().getEcAuctionMainService().getEcAuctionMainPaginatedList(
		        entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		dynaBean.set("need_addr", "0");
		dynaBean.set("need_mobile", "0");
		dynaBean.set("is_del", "0");
		dynaBean.set("lucky_state", "0");
		dynaBean.set("is_pub", "0");

		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String main_pic_hidden = (String) dynaBean.get("main_pic_hidden");
		String[] pic_hidden = request.getParameterValues("pic_hidden");
		String goods_id = (String) dynaBean.get("goods_id");

		String auction_start_time = (String) dynaBean.get("auction_start_time");
		String auction_end_time = (String) dynaBean.get("auction_end_time");
		String auction_order_time = (String) dynaBean.get("auction_order_time");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcAuctionMain entity = new EcAuctionMain();
		super.copyProperties(entity, form);

		String main_pic_file = "";
		String pic_file = "";
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

		if (StringUtils.isNotBlank(goods_id)) {
			KonkaBcompPd kp = new KonkaBcompPd();
			kp.setId(Long.valueOf(goods_id));
			kp = super.getFacade().getKonkaBcompPdService().getKonkaBcompPd(kp);

			if (null != kp) {
				entity.setGoods_id(Long.valueOf(goods_id));
				entity.setGoods_name(kp.getPd_sn());
			}

		}
		if (StringUtils.isNotBlank(auction_start_time)) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setAuction_start_time(sf.parse(auction_start_time));
		}
		if (StringUtils.isNotBlank(auction_end_time)) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setAuction_end_time(sf.parse(auction_end_time));
		}
		if (StringUtils.isNotBlank(auction_order_time)) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setAuction_order_time(sf.parse(auction_order_time));
		}

		if (StringUtils.isEmpty(id)) {
			entity.setAdd_date(new Date());
			entity.setAdd_user_id(user.getId());
			entity.setDelay_num(0);
			super.getFacade().getEcAuctionMainService().createEcAuctionMain(entity);
			super.saveMessage(request, "entity.inserted");
		} else {
			entity.setId(Long.valueOf(id));
			super.getFacade().getEcAuctionMainService().modifyEcAuctionMain(entity);
			super.saveMessage(request, "entity.updated");
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String express_id = (String) dynaBean.get("express_id");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!GenericValidator.isLong(express_id)) {
			super.saveError(request, "errors.param");
			return mapping.findForward("list");
		}

		EcBaseExpress entity = new EcBaseExpress();
		entity.setExpress_id(Long.valueOf(express_id));
		super.getFacade().getEcBaseExpressService().removeEcBaseExpress(entity);

		saveMessage(request, "entity.deleted");

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.serialize(request, "express_id", "method"));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		if (!GenericValidator.isLong(id)) {
			super.saveError(request, "errors.long", "id");
			return this.list(mapping, form, request, response);
		}

		EcAuctionMain entity = new EcAuctionMain();
		entity.setId(Long.valueOf(id));

		entity = super.getFacade().getEcAuctionMainService().getEcAuctionMain(entity);
		entity.setQueryString(super.serialize(request, "id", "method"));
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

		dynaBean.set("pd_name", entity.getGoods_name());

		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		HttpSession session = request.getSession();

		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		EcAuctionMain entity = new EcAuctionMain();
		entity.setId(Long.valueOf(id));
		entity = super.getFacade().getEcAuctionMainService().getEcAuctionMain(entity);

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

		request.setAttribute("ecAuctionMain", entity);

		return mapping.findForward("view");
	}

	public ActionForward selectEcLuckyTime(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		return new ActionForward("/../manager/spgl/EcLuckyMain/selectEcLuckyTime.jsp");
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			EcLuckyTime entity = new EcLuckyTime();
			entity.setId(new Long(id));
			super.getFacade().getEcLuckyTimeService().removeEcLuckyTime(entity);
		}

		super.renderText(response, "success");
		return null;
	}

	public ActionForward selectList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String pd_sn_like = (String) dynaBean.get("pd_sn_like");
		String pd_name_like = (String) dynaBean.get("pd_name_like");

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

		if (StringUtils.isNotBlank(pd_sn_like)) {
			entity.getMap().put("pd_sn_like", pd_sn_like);
		}
		if (StringUtils.isNotBlank(pd_name_like)) {
			entity.getMap().put("pd_name_like", pd_name_like);
		}
		// 所属系统：1-工卡，2-触网，3-会员
		entity.setOwn_sys(1);
		entity.setState(1);

		// entity.getMap().put("is_upself", true);

		Long recordCount = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaBcompPd> entityList = super.getFacade().getKonkaBcompPdService().getKonkaBcompPdPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return new ActionForward("/../manager/spgl/EcAuctionMain/listforPd.jsp");
	}

}
