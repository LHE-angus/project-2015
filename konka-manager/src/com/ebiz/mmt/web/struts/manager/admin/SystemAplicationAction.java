package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaSysAplication;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Liang houen
 * @version 2014.07.30
 */
public class SystemAplicationAction extends BaseAction {

	/**
	 * 初始化
	 * @author Angus
	 * @date 2014-7-21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		//位置信息
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		map.put("local_str", naviString);

		//回执单号
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Date date = new Date();
		map.put("hzd_id", "HZ-"+ui.getId()+date.getTime());

		//当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(date);
		map.put("today", today);

		//填写人
		map.put("write_man", ui.getUser_name());

		//所在部门
		map.put("dept_name", ui.getDepartment());

		//判断是否有审批记录，显示筛选条件
		KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
		entity.setNode_type(2);
		entity.setAudit_user_id(ui.getId());
		Long count = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNodeCount(entity);
		map.put("count", count);

		//转换为json数据
		JSONArray jsonArray = JSONArray.fromObject(map);

		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString().substring(start+1, end+1));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 初始化流程列表
	 * @author Angus
	 * @date 2014-7-18
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAuditNode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		KonkaoaFilesAuditNode filesAuditNode = new KonkaoaFilesAuditNode();
		filesAuditNode.setAudit_level(1l);
		filesAuditNode.setAudit_type(2);
		filesAuditNode.setNode_type(2);
		filesAuditNode.setDept_id(0L);
		List<KonkaoaFilesAuditNode> filesAuditNodeList = super.getFacade().getKonkaoaFilesAuditNodeService()
				.getKonkaoaFilesAuditNodeList(filesAuditNode);

		JSONArray jsonArray = JSONArray.fromObject(filesAuditNodeList);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 初始化类型列表
	 * @author Angus
	 * @date 2014-7-18
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		KonkaCategory kc = new KonkaCategory();
		kc.setC_type(84);
		kc.setIs_del(0);
		List<HashMap> konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryListNew(kc);

		List list = new ArrayList();
		HashMap m = new HashMap();
		m.put("C_INDEX", "");
		m.put("C_NAME", "-请选择-");
		list.add(m);
		list.addAll(konkaCategoryList);
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 查询列表
	 * @author Angus
	 * @date 2014-7-21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if(ui==null){
			return null;
		}
		KonkaSysAplication entity = this.getEntiy(mapping, form, request);

		entity.getMap().put("user_id", ui.getId());

		Long recordCount = super.getFacade().getKonkaSysAplicationService().getKonkaSysAplicationCount(entity);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		pager.init(recordCount, pager.getPageSize(), (String)entity.getMap().get("page"));
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<HashMap> entityList = null;
		HashMap allmap = null;
		if(recordCount>0){
			entityList = super.getFacade().getKonkaSysAplicationService().getKonkaSysAplicationList(entity);
		}

		//封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", recordCount);
		if(entityList==null){
			String[] str = {};
			m.put("rows", str);
		}else{
			m.put("rows", entityList);
		}
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 封装数据
	 * @author Angus
	 * @date 2014-7-22
	 * @param mapping
	 * @param form
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public KonkaSysAplication  getEntiy(ActionMapping mapping, ActionForm form, HttpServletRequest request) throws Exception{
		String back_id = request.getParameter("back_id");
		String s_title = request.getParameter("s_title");
		String type = request.getParameter("type");
		String deal_status = request.getParameter("deal_status");
		String audit_status = request.getParameter("audit_status");
		String page = request.getParameter("page");

		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		KonkaSysAplication entity = new KonkaSysAplication();
		if (StringUtils.isNotBlank(back_id)) {
			entity.setBack_id(back_id);
		}
		if (StringUtils.isNotBlank(s_title)) {
			entity.setTitle(s_title);
		}
		if (StringUtils.isNotBlank(type)) {
			entity.setType(type);
		}
		if (StringUtils.isNotBlank(deal_status)) {
			entity.setDeal_status(Integer.valueOf(deal_status));
		}
		if ("0".equals(audit_status)) {
			entity.getMap().put("wait_audit", true);
		}else if("1".equals(audit_status)){
			entity.getMap().put("have_audit", true);
		}

		String dept_id = ui.getDept_id().toString();
		// 数据级别判断开始
		Long __dept_id = StringUtils.isNotBlank(dept_id) ? Long.valueOf(dept_id) : ui.getDept_id(); // 默认为当前用户所在部门
		int max_dlevel = super.getMaxDLevel(ui.getId()); // 获取当前用户的最高可视部门级别
		logger.info("Max level : {}", max_dlevel);
		request.setAttribute("max_dlevel", max_dlevel);
		switch (max_dlevel) {
			case 9:
				// 集团及以下部门可见
//			__dept_id = 0L; // 0表示部门根节点，即“多媒体事业本部”
//			if (StringUtils.isNotBlank(dept_id)) {
//				entity.getMap().put("dept_id", null);
//				entity.getMap().put("fgs_dept_value", dept_id);
//			}

				break;
			case 8:
				// 分公司及以下部门可见
				KonkaDept dept_fgs = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
				if (null != dept_fgs && null != dept_fgs.getDept_id()) {
					__dept_id = dept_fgs.getDept_id(); // 分公司部门ID
					entity.getMap().put("fgs_dept_value", __dept_id);
				}
				break;
//		case 7:
//			// 我所在的部门及以下部门可见
//			__dept_id = ui.getDept_id(); // 默认为当前用户所在部门
//			entity.getMap().put("dept_id_start", __dept_id);
//			break;
			case 0:
				KonkaDept dept_fgs1 = super.getKonkaDeptForFgs(ui.getDept_id()); // 查询部门分公司
				if (null != dept_fgs1 && null != dept_fgs1.getDept_id()) {
					__dept_id = dept_fgs1.getDept_id(); // 分公司部门ID
					entity.getMap().put("fgs_dept_value", __dept_id);
				}
				break;
			default:
				// 出错
		}
		entity.getMap().put("session_user_id", ui.getId());
		// 数据级别判断结束

		entity.getMap().put("page", page);
		return entity;
	}

	/**
	 * 提交保存申请
	 * @author Angus
	 * @date 2014-7-30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							 HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String hzd_id = (String) dynaBean.get("hzd_id");
		String sq_name = (String) dynaBean.get("sq_name");
		String type = (String) dynaBean.get("type");
		String req_title = (String) dynaBean.get("req_title");
		String content = (String) dynaBean.get("content");
		String audit_node = (String) dynaBean.get("audit_node");
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaSysAplication entity = new KonkaSysAplication();
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if(null!=ui){
			entity.setDept_id(ui.getDept_id());
			entity.setWrite_name(ui.getUser_name());
			entity.setUser_id(ui.getId());
		}

		if (StringUtils.isNotBlank(hzd_id)) {
			entity.setBack_id(hzd_id);
		}
		if (StringUtils.isNotBlank(req_title)) {
			entity.setTitle(req_title);
		}
		if (StringUtils.isNotBlank(type)) {
			entity.setType(type);
		}
		if (StringUtils.isNotBlank(content)) {
			entity.setContent(content);
		}
		if (StringUtils.isNotBlank(sq_name)) {
			entity.setName(sq_name);
		}

		//判断是否为自定义流程
		if (StringUtils.isNotBlank(audit_node)) {
			entity.setLink_id(audit_node);
		}else{
			String[] node_user_names = request.getParameterValues("audit_user_name");
			String[] node_user_ids = request.getParameterValues("audit_user_id");

			List name_temp = new ArrayList();
			List id_temp = new ArrayList();
			for(int i=0 ; i<node_user_names.length; i++){
				if(null!=node_user_names[i]&&!"".equals(node_user_names[i])){
					name_temp.add(node_user_names[i]);
					id_temp.add(node_user_ids[i]);
				}
			}

			KonkaoaFilesAuditNode t = new KonkaoaFilesAuditNode();
			List<KonkaoaFilesAuditNode> konkaoaFilesAuditNodeList = new ArrayList<KonkaoaFilesAuditNode>();
			for (int i = 0; i < name_temp.size(); i++) {
				KonkaoaFilesAuditNode kfan = new KonkaoaFilesAuditNode();
				kfan.setAudit_user((String)name_temp.get(i));
				kfan.setAudit_user_id(new Long((String)id_temp.get(i)));
				kfan.setAudit_level(new Long(i + 1));
				kfan.setNode_type(2);
				kfan.setDept_id(ui.getDept_id());
				konkaoaFilesAuditNodeList.add(kfan);
			}
			t.setAudit_type(0);
			t.setKonkaoaFilesAuditNodeList(konkaoaFilesAuditNodeList);
			//首先保存流程信息，获取LINK_ID
			Long link_id = super.getFacade().getKonkaoaFilesAuditNodeService().createKonkaoaFilesAuditNodeList(t);
			entity.setLink_id(link_id.toString());
			if(StringUtils.isNotBlank(node_user_ids[0])){
				entity.setAudit_id1(Long.valueOf(node_user_ids[0]));
			}
			if(StringUtils.isNotBlank(node_user_ids[1])){
				entity.setAudit_id2(Long.valueOf(node_user_ids[1]));
			}
			if(StringUtils.isNotBlank(node_user_ids[2])){
				entity.setAudit_id3(Long.valueOf(node_user_ids[2]));
			}
			if(StringUtils.isNotBlank(node_user_ids[3])){
				entity.setAudit_id4(Long.valueOf(node_user_ids[3]));
			}
			if(StringUtils.isNotBlank(node_user_names[0])){
				entity.setAudit_name1(node_user_names[0]);
			}
			if(StringUtils.isNotBlank(node_user_names[1])){
				entity.setAudit_name2(node_user_names[1]);
			}
			if(StringUtils.isNotBlank(node_user_names[2])){
				entity.setAudit_name3(node_user_names[2]);
			}
			if(StringUtils.isNotBlank(node_user_names[3])){
				entity.setAudit_name4(node_user_names[3]);
			}
		}
		//申请时间
		entity.setCreate_date(new Date());
		//处理状态
		entity.setDeal_status(0);
		entity.setIs_del(0);

		//判断审批层次,初始化为0
		entity.setBak1("0");

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
			attachment.setLink_tab("FILES");
			attachment.setAdd_user_name(ui.getUser_name());
			attachment.setAdd_user_id(ui.getId());
			attachmentList.add(attachment);
		}
		entity.setAttachmentList(attachmentList);

		Long count = 0L;
		HashMap map = new HashMap();
		try{
			count = super.getFacade().getKonkaSysAplicationService().createKonkaSysAplication(entity);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(count!=null&&count>0){
			super.renderJavaScript(response, "alert('申请提交成功！');location.href='SystemAplication/list.jsp?mod_id="+mod_id+"'");
		}else{
			super.renderJavaScript(response, "alert('申请提交失败！');history.back();");
		}
		return null;
	}

	/**
	 * 查看需求申请
	 * @author Angus
	 * @date 2014-8-4
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");

		KonkaSysAplication entity = new KonkaSysAplication();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(Long.valueOf(id));
		}
		entity.setIs_del(0);

		HashMap	map = super.getFacade().getKonkaSysAplicationService().getKonkaSysAplication(entity);

		//位置信息
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = request.getParameter("mod_id");
		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList, " > ");
		}
		map.put("local_str", naviString);

		//审批结果
		KonkaoaFilesAuditNode en = new KonkaoaFilesAuditNode();
		en.setLink_id(Long.valueOf(map.get("LINK_ID").toString()));
		en.setNode_type(2);
		List<HashMap> auditList = super.getFacade().getKonkaoaFilesAuditNodeService().getAplicationAuditList(en);
		map.put("auditList", auditList);

		// 附件
		KonkaPeAttachments attachment = new KonkaPeAttachments();
		attachment.setLink_id(Long.valueOf(id));
		attachment.setLink_tab("FILES");
		attachment.setIs_del(0l);
		List<HashMap> kpaList = super.getFacade().getKonkaPeAttachmentsService().getKonkaPeAttachmentsMap(attachment);
		map.put("kpaList", kpaList);
		JSONArray jsonArray = JSONArray.fromObject(map);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 删除附件
	 * @author Angus
	 * @date 2014-8-5
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
									HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		int res = 0;
		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			KonkaPeAttachments entity = new KonkaPeAttachments();
			entity.setId(new Long(id));
			res = getFacade().getKonkaPeAttachmentsService().removeKonkaPeAttachments(entity);
		}

		HashMap	map = new HashMap();
		map.put("flag", res);
		JSONArray jsonArray = JSONArray.fromObject(map);
		int start =jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start+1, end+1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 提交审批结果
	 * @author Angus
	 * @date 2014-8-6
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								  HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;

		String audit_res = (String) dynaBean.get("audit_res");
		String audit_comment = (String) dynaBean.get("audit_comment");
		String link_id = (String) dynaBean.get("link_id");
		String mod_id = (String) dynaBean.get("mod_id");

		KonkaoaFilesAuditNode entity = new KonkaoaFilesAuditNode();
		entity.setLink_id(Long.valueOf(link_id));
		if(StringUtils.isNotBlank(audit_res)){
			entity.setAudit_result(Integer.valueOf(audit_res));
		}
		if(StringUtils.isNotBlank(audit_comment)){
			entity.setAudit_comment(audit_comment);
		}

		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		entity.setAudit_user_id(ui.getId());
		entity.setAudit_datetime(new Date());
		entity.setNode_type(2);

		int count = super.getFacade().getKonkaoaFilesAuditNodeService().modifySystemAplication(entity);

		if(count>0){
			//更新处理状态
			KonkaSysAplication en = new KonkaSysAplication();
			en.setLink_id(link_id);
			//纪录审批级别
			KonkaoaFilesAuditNode entity1 = super.getFacade().getKonkaoaFilesAuditNodeService().getKonkaoaFilesAuditNode(entity);
			en.setBak1(entity1.getAudit_level().toString());

			Long max_user_id = super.getFacade().getKonkaoaFilesAuditNodeService().getMaxKonkaoaFilesAuditNode(entity);

			//通过则判断是否为最后一个审批人，不通过则该申请状态为已处理
			if("0".equals(audit_res)){
				if(max_user_id.equals(ui.getId())){
					en.setDeal_status(2);
				}else{
					en.setDeal_status(1);
				}
			}else{
				en.setDeal_status(-1);
			}

			super.getFacade().getKonkaSysAplicationService().modifyAplicationStatus(en);
			super.renderJavaScript(response, "alert('审批完成！');location.href='SystemAplication/list.jsp?mod_id="+mod_id+"'");
		}else{
			super.renderJavaScript(response, "alert('审批失败！');history.back();");
		}
		return null;
	}
}