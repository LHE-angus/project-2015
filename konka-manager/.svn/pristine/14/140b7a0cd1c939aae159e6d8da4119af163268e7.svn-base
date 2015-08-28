package com.ebiz.mmt.web.struts.manager.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Jin,QingHua
 */
public abstract class BaseMmtoaAction extends BaseAction {

	
	/**
	 * 当前年月日
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getNowYearAndMonth() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		String ym = sdf.format(date);
		return ym.substring(2, ym.length());
	}
	/**
	 * @author Hui,Gang
	 * @version Build 2010-12-29
	 * @desc 文件相关属性存放至RequestScope
	 */
	public void setCategoryListToRequestScope(HttpServletRequest request) {
		KonkaoaCategory category = new KonkaoaCategory();
		category.setIs_del(0);
		category.setC_type(11);
		request.setAttribute("category11List", getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(
				category));
		category.setC_type(12);
		request.setAttribute("category12List", getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(
				category));
		category.setC_type(13);
		request.setAttribute("category13List", getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(
				category));
		category.setC_type(14);
		request.setAttribute("category14List", getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(
				category));
	}

	/**
	 * @param form
	 * @param request
	 * @param entity
	 * @param id
	 * @desc 取文件发送呈数据
	 * @author Wang Hao
	 * @return
	 */
	public KonkaoaFiles getFilesProperty(ActionForm form, HttpServletRequest request, Long id) {
		DynaBean dynaBean = (DynaBean) form;
		String[] receive_type_ids = null;
		String[] receive_dept_ids = null;
		String[] receive_type_names = null;
		List<KonkaoaFilesRecipient> filesRecipientList = new ArrayList<KonkaoaFilesRecipient>();

		KonkaoaFiles entity = new KonkaoaFiles();
		// 是否下发
		entity.setId(id);
		// 发
		receive_type_ids = StringUtils.split((String) dynaBean.get("receive_type_1_ids"), ",");
		receive_type_names = StringUtils.split((String) dynaBean.get("receive_type_1_names"), ",");
		if (null != receive_type_ids) {
			for (int i = 0; i < receive_type_ids.length; i++) {
				KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
				filesRecipient.setReceive_id(new Long(receive_type_ids[i]));
				filesRecipient.setReceive_user(receive_type_names[i]);
				filesRecipient.setReceive_type(1);
				filesRecipient.setReceive_user_type(0);
				filesRecipientList.add(filesRecipient);
			}
		}

		// 下发给部门
		receive_dept_ids = StringUtils.split((String) dynaBean.get("receive_dept_1_ids"), ",");
		receive_type_names = StringUtils.split((String) dynaBean.get("receive_dept_1_names"), ",");
		if (null != receive_dept_ids) {
			for (int i = 0; i < receive_dept_ids.length; i++) {
				KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
				filesRecipient.setReceive_id(new Long(receive_dept_ids[i]));
				filesRecipient.setReceive_user(receive_type_names[i]);
				filesRecipient.setReceive_type(1);
				filesRecipient.setReceive_user_type(1);
				filesRecipientList.add(filesRecipient);
			}
		}

		// 送
		receive_type_ids = StringUtils.split((String) dynaBean.get("receive_type_2_ids"), ",");
		receive_type_names = StringUtils.split((String) dynaBean.get("receive_type_2_names"), ",");
		if (null != receive_type_ids) {
			for (int i = 0; i < receive_type_ids.length; i++) {
				KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
				filesRecipient.setReceive_id(new Long(receive_type_ids[i]));
				filesRecipient.setReceive_user(receive_type_names[i]);
				filesRecipient.setReceive_type(2);
				filesRecipient.setReceive_user_type(0);
				filesRecipientList.add(filesRecipient);
			}
		}

		// 呈
		receive_type_ids = StringUtils.split((String) dynaBean.get("receive_type_3_ids"), ",");
		receive_type_names = StringUtils.split((String) dynaBean.get("receive_type_3_names"), ",");
		if (null != receive_type_ids) {
			for (int i = 0; i < receive_type_ids.length; i++) {
				KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
				filesRecipient.setReceive_id(new Long(receive_type_ids[i]));
				filesRecipient.setReceive_user(receive_type_names[i]);
				filesRecipient.setReceive_type(3);
				filesRecipient.setReceive_user_type(0);
				filesRecipientList.add(filesRecipient);
			}
		}
		entity.setFilesRecipientList(filesRecipientList);
		return entity;
	}
	/**
	 * @param request
     * 自动生成登录用户所在部门的提交文件编号
	 * @author Cheng,Bing
	 */
	public String getFilesMaxNo(String file_no_lm) {
		
		Long max_fileno = null;
		
		KonkaoaDocInfo kd = new KonkaoaDocInfo();
		kd.getMap().put("file_no_lm", file_no_lm);
		int length=file_no_lm.length();
		kd.getMap().put("file_no_length", length);
		Long max_fileno_1 = super.getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfoNoMax(kd);
		max_fileno_1 = max_fileno_1 == null ? 0 : max_fileno_1;
		
		KonkaoaFiles kf = new KonkaoaFiles();
		kf.getMap().put("file_no_lm", file_no_lm);
		int length1=file_no_lm.length();
		kf.getMap().put("file_no_length", length1);
		Long max_fileno_2 = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesNoMax(kf);
		max_fileno_2 = max_fileno_2 == null ? 0 : max_fileno_2;
		
		max_fileno = max_fileno_1 >= max_fileno_2 ? max_fileno_1 : max_fileno_2;	
		max_fileno = max_fileno + 1;
		String file_no_r = "";
		if(max_fileno < 1000){
			file_no_r = "0000".substring(0,4-("" + max_fileno).length()) + max_fileno;
		}else{
			file_no_r = "" + max_fileno;
		}
		
		return file_no_lm + file_no_r;
	}
	
	/**
	 * @param request
	 * @param mod_id
	 *            操作栏目mod_id
	 * @param oper_desc
	 *            操作说明
	 * @param link_tab
	 *            关联表
	 * @param link_id
	 *            关联ID
	 * @param oper_type
	 *            操作类型，如：添加，删除，修改，审核等
	 * @throws Exception
	 * @author Chen,LiDe
	 */
//	public void createSysOperLog(HttpServletRequest request, String link_tab, Long link_id, String mod_id,
//			String oper_type, String oper_desc) throws Exception {
//		HttpSession session = request.getSession(false);
//		UserInfo userInfo = (UserInfo) session.getAttribute(Keys.ADMIN_USER);
//		// SysModule sysModule = new SysModule();
//		// sysModule.getMap().put("mod_id", mod_id);
//		// List<SysModule> sysModuleList =
//		// getFacade().getSysModuleService().getSysModuleList(sysModule);
//		MmtoaOperLog t = new MmtoaOperLog();
//		// for (SysModule sm : sysModuleList) {
//		// t.setPpdm_name((t.getPpdm_name() != null ? t.getPpdm_name() + "-" :
//		// "") + sm.getMod_name());
//		// }
//		if ("60000".equals(mod_id))
//			t.setPpdm_name((t.getPpdm_name() != null ? t.getPpdm_name() + "-" : "") + "公告通知");
//		t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
//		t.setOper_ip(IpUtils.getIpAddr(request));
//		t.setOper_desc(oper_desc);
//		t.setLink_tab(link_tab);
//		t.setLink_id(link_id);
//		t.setOper_type(oper_type);
//		if (userInfo != null) {
//			t.setOper_uid(userInfo.getId());
//			t.setOper_uname(userInfo.getUser_name());
//		}
//		getFacade().getKonkaoaOperLogService().createMmtoaOperLog(t);
//	}

}