package com.ebiz.mmt.web.struts.zxmall.center;

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

import com.ebiz.mmt.domain.EcPdEavl;
import com.ebiz.mmt.domain.EcUser;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.PshowOrdeDetails;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.struts.zxmall.BaseMemberAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author tudp
 * @version 2013-09-14
 */
public class EcPdEavlAction extends BaseMemberAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall");
		// 账户中心用户登录验证
		if (ecUser==null ) { 
			String ctx = super.getCtxPath(request);
			String url = ctx + "/zxmall/";
			response.sendRedirect(url);
			return null; 
		}
		Pager pager = (Pager) dynaBean.get("pager");
		String start_date = (String) dynaBean.get("start_date");
		String end_date = (String) dynaBean.get("end_date");
		EcPdEavl entity = new EcPdEavl();
		entity.setIs_del(0);
		entity.setEval_user_id(ecUser.getId());
		entity.getMap().put("eval_date_start", start_date);
		entity.getMap().put("eval_date_end", end_date);

		Long recordCount = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);

		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<EcPdEavl> entityList = super.getFacade().getEcPdEavlService().getEcPdEavlPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		
		DynaBean dynaBean = (DynaBean) form;
		String goods_id = (String) dynaBean.get("goods_id"); 
		String detail_id = (String) dynaBean.get("detail_id"); 
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall"); 
		if (ecUser==null ) { 
				String ctx = super.getCtxPath(request);
				String url = ctx + "/zxmall/center/Skip.do";
				response.sendRedirect(url);
				return null; 
		}
		
		if (GenericValidator.isLong(goods_id) && GenericValidator.isLong(detail_id)) { 		
			EcPdEavl entity = new EcPdEavl();
			entity.setIs_del(0);
			entity.setEval_user_id(ecUser.getId()); 
			entity.setDetail_id(new Long(detail_id));
			entity.setGoods_id(new Long(goods_id));
			Long recordCount = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);  
	
			String msg = "您已经评论过了";
			if(recordCount.intValue()>0){
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+ super.getCtxPath(request) + "/zxmall/center/EcPdEavl.do?method=list';}");
				return null; 
			}else{
				PshowOrdeDetails  details = new PshowOrdeDetails();
				details.setPd_id(new Long(goods_id)); 
				details.setBill_item_id(new Long (detail_id));
				details = super.getFacade().getPshowOrdeDetailsService().getPshowOrdeDetails(details); 
				request.setAttribute("details", details);
			}
		}else{
			String msg = "错误ID"; 
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+ super.getCtxPath(request) + "/zxmall/center/EcPdEavl.do?method=list';}");
			return null; 
		}
		
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		HttpSession session = request.getSession();
		EcUser ecUser = (EcUser) session.getAttribute("zxmall"); 
		String goods_id = (String) dynaBean.get("goods_id"); 
		String detail_id = (String) dynaBean.get("detail_id"); 
		String msg = "恭喜，发表评论成功！";
		if (null == ecUser) { 
			msg = super.getMessage(request, "ec.nologin");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='" + super.getCtxPath(request)+ "/zxmll/login.do';}");
			return null;
		}
		if (GenericValidator.isLong(goods_id) && GenericValidator.isLong(detail_id)) {
			EcPdEavl entity = new EcPdEavl();
			entity = new EcPdEavl();
			entity.setIs_del(0);
			entity.setEval_user_id(ecUser.getId()); 
			entity.setDetail_id(new Long(detail_id));
			entity.setGoods_id(new Long(goods_id));
			Long recordCount = super.getFacade().getEcPdEavlService().getEcPdEavlCount(entity);  

			msg = "您已经评论过了";
			if(recordCount.intValue()>0){
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+ super.getCtxPath(request) + "/zxmall/center/EcPdEavl.do?method=list';}");
				return null;
			}

			List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.CHUWANG_PATH, true, 0, new int[] {240, 400, 480, 600, 720 });
			List<KonkaPeAttachments> konkaPeAttachmentsList = new ArrayList<KonkaPeAttachments>();
			KonkaPeAttachments konkaPeAttachments = null;
			for (UploadFile uploadFile : uploadFileList) { 
				if (uploadFile.getContentType().indexOf("image/")!=-1) {				 
					konkaPeAttachments = new KonkaPeAttachments();
					konkaPeAttachments.setFile_name(uploadFile.getFileName());
					konkaPeAttachments.setFile_type(uploadFile.getContentType());
					konkaPeAttachments.setFile_size(new Long(uploadFile.getFileSize()));
					konkaPeAttachments.setSave_path(uploadFile.getFileSavePath());
					konkaPeAttachments.setSave_name(uploadFile.getFileSaveName());
					konkaPeAttachments.setIs_del((long) 0);
					konkaPeAttachments.setLink_tab("EC_PD_EAVL");
					konkaPeAttachments.setAdd_user_name(ecUser.getUser_name());
					konkaPeAttachments.setAdd_user_id(ecUser.getId());
					konkaPeAttachmentsList.add(konkaPeAttachments);
				} 
			} 
			
			//if (c.intValue() == 0||!StringUtils.isBlank(par_id)) {
				entity = new EcPdEavl();
				super.copyProperties(entity, form);
				entity.setEval_user_id(ecUser.getId());
				entity.setId(null); 
				entity.setAudit_state(new Integer(1));
				// entity.setIs_del(new Integer(0));
				if (entity.getIs_anon() == null) {
					entity.setIs_anon(new Integer(0));
				}
				entity.setEval_ip(super.getIpAddr(request));
				entity.setEval_useful(new Long(0));
				entity.setEval_useless(new Long(0));
				entity.setEval_date(new Date());
				entity.setOwn_sys(5);
				entity.setIs_del(0);
				entity.setKonkaPeAttachmentsList(konkaPeAttachmentsList);
				entity.setPar_id(0L);
				super.getFacade().getEcPdEavlService().createEcPdEavl(entity);
			//} else {
			//	msg = "对不起,您已经评论过了！";
			//}
		} else {
			msg = "错误ID！"; 
			
		}
		super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');location.href='"+ super.getCtxPath(request) + "/zxmall/center/EcPdEavl.do?method=list';}");
		return null;
	}
}
