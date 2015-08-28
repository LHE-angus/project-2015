package com.ebiz.mmt.web.struts.customer;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sourceforge.jtds.util.Logger;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.ImportDataTypes;
import com.ebiz.mmt.domain.JBasePartner;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaXxStdStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Xing,XiuDong
 */
public class CsAjaxAction extends BaseAction {

	/**
	 * 查询四级省市县镇列表
	 * 
	 * @return json: [[key, value],[key, value]..]
	 */
	public ActionForward getBaseProvinceFourList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String par_index = (String) dynaBean.get("p_index");
		String p_index_join = (String) dynaBean.get("p_index_join");

		if (!GenericValidator.isLong(par_index)) {
			return null;
		}

		BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
		baseProvinceFour.setPar_index(new Long(par_index));
		baseProvinceFour.setDel_mark(0);

		if (StringUtils.isNotBlank(p_index_join)) {
			baseProvinceFour.getMap().put("p_index_join", p_index_join);
		}

		List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService()
				.getBaseProvinceListFourList(baseProvinceFour);

		List<String> dataList = new ArrayList<String>();
		for (BaseProvinceListFour entity : baseProvinceFourList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.getP_name(), "\",\"",
					String.valueOf(entity.getP_index()), "\"]" }));
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}
	
	/**
	 * 获取现有的网点级别列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPartnerLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<HashMap> entityList = super.getFacade().getJBasePartnerService().getJBasePartnerLevelList();
		List<String> dataList = new ArrayList<String>();
		for (HashMap entity : entityList) {
			dataList.add(StringUtils.join(new String[] { "[\"", entity.get("LEVEL_NAME").toString(), "\",\"",
					String.valueOf(entity.get("P_LEVEL")), "\"]" }));
		}

		super.renderJson(response, StringUtils.join(new String[] { "[", StringUtils.join(dataList, ","), "]" }));
		return null;
	}

}
