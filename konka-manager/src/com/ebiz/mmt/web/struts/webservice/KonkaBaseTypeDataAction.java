package com.ebiz.mmt.web.struts.webservice;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.org.apache.commons.lang3.StringUtils;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Wang,KunLin
 */
public class KonkaBaseTypeDataAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

	/**
	 * @author Wang.Kunlin
	 * @param source
	 * @return json version:V14.1.4
	 * @throws Exception
	 */

	/**
	 * @val 数据来源
	 * @val 0表示手机端android端
	 * @val 1表示手机端ios端
	 * @val 2表示web端
	 * @val 3表示wap端
	 * @val 4表示VIP端
	 */
	public ActionForward getSingleVersion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String source = (String) dynaBean.get("source");
		String ext = (String) dynaBean.get("ext");
		if (StringUtils.isNotBlank(source) & GenericValidator.isInt(source)) {

		} else {
			super.renderJavaScript(response, "请输入正确数据来源");
			return null;
		}

		KonkaBaseTypeData konkaBaseTypeData = new KonkaBaseTypeData();

		konkaBaseTypeData.setPar_type_id(100001L);// 100001 表示所有和版本相关的基础数据

		konkaBaseTypeData.setIs_del(0);// 没有删除的

		int sourceId = Integer.parseInt(source);

		switch (sourceId) {
		case 0:
			konkaBaseTypeData.setType_id(10000100L);// 表示手机端android端
			break;
		case 1:
			konkaBaseTypeData.setType_id(10000101L);// 表示手机端ios端
			break;
		case 2:
			konkaBaseTypeData.setType_id(10000102L);// 表示web端
			break;
		case 3:
			konkaBaseTypeData.setType_id(10000103L);// 表示wap端
			break;
		case 4:
			konkaBaseTypeData.setType_id(10000104L);// 表示VIP端
			break;
		}
		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();

		List<KonkaBaseTypeData> konkaBaseTypeDataList = super.getFacade()
				.getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(konkaBaseTypeData);

		if (null != konkaBaseTypeDataList && konkaBaseTypeDataList.size() > 0) {
			konkaBaseTypeData = konkaBaseTypeDataList.get(0);
			map.put("version", null == konkaBaseTypeData.getMemo() ? ""
					: konkaBaseTypeData.getMemo());
			list.add(map);
		} else {
			map.put("version", "");
			list.add(map);
		}
		super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	/**
	 * @param user_id
	 *            用户id userpass 用户密码 查看用户是否使用新的业务通
	 * @return state=00表示使用 state=1表示不使用
	 * @throws Exception
	 */
	public ActionForward checkUserForLst(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");

		String result = "操作异常";
		
		//版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");

		// 用户名或者密码不存在
		PeProdUser user = checkUserid(inuser_id, inuserpass,android_version,ios_version);

		if (null == user) {
			super.renderHtml(response, "用户不存在");
			return null;
		}
		Long userFgsDept = 0l;
		if (null != user && null != user.getDept_id()) {

			KonkaDept konkaDept6 = new KonkaDept();
			konkaDept6.setDept_id(user.getDept_id());
			konkaDept6.getMap().put("dept_type_eq", 3);
			konkaDept6 = super.getFacade().getKonkaDeptService()
					.getKonkaDeptSuperiorByDeptId(konkaDept6);
			if (null != konkaDept6 && null != konkaDept6.getDept_id()) {
				// konkaOrderInfo.getMap().put("fgsName",
				// konkaDept6.getDept_name());
				userFgsDept = konkaDept6.getDept_id();
			}
		}

		List<HashMap> list = new ArrayList<HashMap>();

		HashMap map = new HashMap();

		KonkaBaseTypeData konkaBaseTypeData = new KonkaBaseTypeData();

		konkaBaseTypeData.setPar_type_id(100002L);// 100002 表示所有使用新版业务通的分公司
		konkaBaseTypeData.setIs_del(0);// 没有删除的
		konkaBaseTypeData.setMemo(userFgsDept.toString());
		List<KonkaBaseTypeData> konkaBaseTypeDataList = super.getFacade()
				.getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(konkaBaseTypeData);
		/**
		 * 如果找到了，则强制使用广州零售通版本的发票号上传功能 否则按照下载的APK来找处理
		 */
		if (null != konkaBaseTypeDataList && konkaBaseTypeDataList.size() > 0) {
			// super.renderJson(response, "state:0");
			// state=0表示使用
			map.put("state", 0);
			list.add(map);

		} else {
			// super.renderJson(response, "state:1");
			// state=1表示不使用
			/**
			 * 如果没有找到，则按照下载的APK包里面的设置来
			 */
			map.put("state", "");
			list.add(map);

		}
		super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	// 基础数据列表数据
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		// super.getModPopeDom(form, request);
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String _par_type_id = (String) dynaBean.get("_par_type_id");
		String _type_name_like = (String) dynaBean.get("_type_name_like");
		String _par_type_name_like = (String) dynaBean
				.get("_par_type_name_like");
		// String _is_del = (String) dynaBean.get("_is_del");
		String _is_lock = (String) dynaBean.get("_is_lock");
		String _begin_date = (String) dynaBean.get("_begin_date");
		String _end_date = (String) dynaBean.get("_end_date");
		String _field1 = (String) dynaBean.get("_field1");// 属性
		String _field2 = (String) dynaBean.get("_field2");// 级别
		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		String first = (String) dynaBean.get("first");
		String count = (String) dynaBean.get("count");

		// HttpSession session = request.getSession();
		// // 用户名或者密码不存在
		// PeProdUser user = checkUser(inuser_id, inuserpass);
		//
		// if (null == user) {
		// super.renderHtml(response, "用户不存在");
		// return null;
		// // }
		KonkaBaseTypeData entity = new KonkaBaseTypeData();
		if (StringUtils.isNotBlank(_par_type_id)) {
			entity.setPar_type_id(Long.parseLong(_par_type_id));
			dynaBean.set("_par_type_id", _par_type_id);
		}
		if (StringUtils.isNotBlank(_type_name_like)) {
			entity.getMap().put("_type_name_like", _type_name_like);
		}
		if (StringUtils.isNotBlank(_par_type_name_like)) {
			entity.getMap().put("_par_type_name_like", _par_type_name_like);
		}
		/*
		 * if (StringUtils.isNotBlank(_is_del) &&
		 * StringUtils.isNumeric(_is_del)) {
		 * entity.setIs_del(Integer.valueOf(_is_del)); }
		 */
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(_is_lock) && StringUtils.isNumeric(_is_lock)) {
			entity.setIs_lock(Integer.valueOf(_is_lock));
		}
		if (StringUtils.isNotBlank(_begin_date)) {
			entity.getMap().put("_begin_date", _begin_date);
		}
		if (StringUtils.isNotBlank(_end_date)) {
			entity.getMap().put("_end_date", _end_date);
		}
		if (StringUtils.isNotBlank(_field1)) {
			entity.getMap().put("_field1", _field1);
		}
		// 级别
		if (StringUtils.isNotBlank(_field2)) {
			entity.getMap().put("_field2", _field2);
		}
		Long recordCount = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaMobileCustVisitLBCount(entity);

		if (StringUtils.isNotBlank(first) && StringUtils.isNumeric(first)) {
			entity.getRow().setFirst(Integer.valueOf(first));
		} else {
			entity.getRow().setFirst(0);
		}
		// 没有传入查看几行默认查看起始行后面所有行数
		if (StringUtils.isNotBlank(count) && StringUtils.isNumeric(count)) {
			entity.getRow().setCount(Integer.valueOf(count));
		} else {
			entity.getRow().setCount(recordCount.intValue());
		}

		List<KonkaBaseTypeData> entityList = super.getFacade()
				.getKonkaBaseTypeDataService()
				.getKonkaMobileCustVisitPaginatedLBList(entity);
		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = new HashMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (null != entityList) {
			for (KonkaBaseTypeData kkcv : entityList) {
				map = new HashMap();
				map.put("type_name",
						kkcv.getType_name() == null ? "" : kkcv.getType_name());// 类型名称
				map.put("field1",
						kkcv.getField1() == null ? "" : kkcv.getField1());// url地址
				// 简介
				map.put("memo", kkcv.getMemo() == null ? "" : kkcv.getMemo());// 简介

				// 拿到图片
				Attachment attachment = new Attachment();
				attachment.setLink_tab("KONKA_BASE_TYPE_DATA");
				attachment.setLink_id(Long.valueOf(kkcv.getId()));
				attachment.setDel_mark(new Short("0"));
				List<Attachment> attList = super.getFacade()
						.getAttachmentService().getAttachmentList(attachment);
				List<Map<String, Object>> fj_paths = new ArrayList<Map<String, Object>>();
				Map<String, Object> tempMap = new HashMap<String, Object>();
				for (Attachment attachment2 : attList) {
					tempMap.put("file_name", attachment2.getFile_name());
					tempMap.put("save_path", attachment2.getSave_path());
					fj_paths.add(tempMap);
				}
				map.put("fj_paths", attList == null ? "" : fj_paths);// 附件路劲
				list.add(map);
			}
		}
		logger.info(entityList.toString());
		super.renderJson(response, JSON.toJSONString(list));
		return null;

	}

	// 根据父类别取下面的数据列表 （在使用产品型号列表）
	public ActionForward ajaxlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> allmap = new HashMap<String, Object>();
		String par_type_id = (String) dynaBean.get("par_type_id");
		setNaviStringToRequestScope(form, request);
		KonkaBaseTypeData entity = new KonkaBaseTypeData();
		entity.setIs_del(0);
		entity.setIs_lock(0);
		entity.setPar_type_id(Long.valueOf(par_type_id));
		List<KonkaBaseTypeData> entityList = super.getFacade()
				.getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(entity);
		allmap.put("entityList", entityList);
		JSONArray jsonArray = JSONArray.fromObject(entityList);

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
		out.flush();
		out.close();
		return null;

	}
	
	// 根据父类别取下面的数据列表 （在使用产品型号列表）
	public ActionForward ajaxforlist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		Map<String, Object> allmap = new HashMap<String, Object>();
		String par_type_id = (String) dynaBean.get("par_type_id");
		setNaviStringToRequestScope(form, request);
		KonkaBaseTypeData entity = new KonkaBaseTypeData();
		entity.setIs_del(0);
		entity.setIs_lock(0);
		entity.setPar_type_id(Long.valueOf(par_type_id));
		List<KonkaBaseTypeData> entityList = super.getFacade()
				.getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(entity);
		allmap.put("entityList", entityList);
		// JSONArray jsonArray = JSONArray.fromObject(entityList);

		if (entityList.size() > 0) {
			super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(entityList));
		} else {
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"null\"}");
		}
		return null;
	}


}