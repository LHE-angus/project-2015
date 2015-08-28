package com.ebiz.mmt.web.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Wang,KunLin
 */
public class KonkaBaseTypeDataAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return null;
	}

	/**
	 *@author Wang.Kunlin
	 *@param source
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
	public ActionForward getSingleVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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

		List<KonkaBaseTypeData> konkaBaseTypeDataList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(konkaBaseTypeData);

		if (null != konkaBaseTypeDataList && konkaBaseTypeDataList.size() > 0) {
			konkaBaseTypeData = konkaBaseTypeDataList.get(0);
			map.put("version", null == konkaBaseTypeData.getMemo() ? "V14.1.4" : konkaBaseTypeData.getMemo());
			list.add(map);
		} else {
			map.put("version", "V14.1.4");
			list.add(map);
		}
		super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	/**
	 * @param user_id
	 *            用户id userpass 用户密码
	 *            查看用户是否使用新的业务通
	 * @return state=0表示不使用 state=1表示不使用
	 * @throws Exception
	 */
	public ActionForward checkUserForLst(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		// 用户名
		String inuser_id = (String) dynaBean.get("user_id");
		// 密码
		String inuserpass = (String) dynaBean.get("userpass");
		 //版本号
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		String result = "操作异常";

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
			konkaDept6 = super.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept6);
			if (null != konkaDept6 && null != konkaDept6.getDept_id()) {
				// konkaOrderInfo.getMap().put("fgsName", konkaDept6.getDept_name());
				userFgsDept = konkaDept6.getDept_id();
			}
		}

		List<HashMap> list = new ArrayList<HashMap>();

		HashMap map = new HashMap();

		KonkaBaseTypeData konkaBaseTypeData = new KonkaBaseTypeData();

		konkaBaseTypeData.setPar_type_id(100002L);// 100002 表示所有使用新版业务通的分公司
		konkaBaseTypeData.setIs_del(0);// 没有删除的
		konkaBaseTypeData.setMemo(userFgsDept.toString());
		List<KonkaBaseTypeData> konkaBaseTypeDataList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(konkaBaseTypeData);
		if (null != konkaBaseTypeDataList && konkaBaseTypeDataList.size() > 0) {
			// super.renderJson(response, "state:0");
			// state=0表示使用
			map.put("state", 0);
			list.add(map);

		} else {
			// super.renderJson(response, "state:1");
			// state=1表示不使用
			map.put("state", 1);
			list.add(map);

		}
		super.renderJson(response, JSON.toJSONString(list));
		return null;
	}
}