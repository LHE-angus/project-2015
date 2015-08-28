package com.ebiz.mmt.web.struts.mobile.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileFightReport;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wang Hao
 */
public class CompeteReportAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		dynaBean.set("mod_id", mod_id);

		request.setAttribute("baseList", getClazz());
		List<KonkaMobileCategory> baseList = super.getFacade()
				.getKonkaMobileCategoryService().getJingpinCategoryList();
		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (KonkaMobileCategory _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getC_index().toString());
			t.setName(_t.getC_name());
			list.add(t);
		}
		request.setAttribute("brandList", list);

		KonkaMobileCategory size = new KonkaMobileCategory();
		size.setC_type(20);
		baseList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(size);
		list = new ArrayList<MobileSelectItem>();
		for (KonkaMobileCategory _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getC_index().toString());
			t.setName(_t.getC_name());
			list.add(t);
		}
		request.setAttribute("sizeList", list);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		// 品牌
		String brand_id = (String) dynaBean.get("select-choice-1");
		// 品类
		String cls_id = (String) dynaBean.get("select-choice-2");
		// 尺寸
		String pro_id = (String) dynaBean.get("select-choice-3");
		// 型号
		String pd_name = (String) dynaBean.get("model");
		// 单价
		String price = (String) dynaBean.get("sales_price");
		// 销量
		String count = (String) dynaBean.get("sales_count");

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_MOBILE);
		KonkaMobileFightReport entity = new KonkaMobileFightReport();
		if (StringUtils.isNotEmpty(brand_id))
			entity.setBrand_id(new Long(brand_id));
		else {
			super.renderText(response, "请选择品牌！");
			return null;
		}
		if (StringUtils.isNotEmpty(cls_id))
			entity.setCls_id(new Long(cls_id));
		else {
			super.renderText(response, "请选择品类！");
			return null;
		}
		if (StringUtils.isNotEmpty(pro_id))
			entity.setSize_id(new Long(pro_id));
		else {
			super.renderText(response, "请选择尺寸！");
			return null;
		}
		if (StringUtils.isNotEmpty(pd_name))
			entity.setModel_id(pd_name);
		else {
			super.renderText(response, "请填写型号！");
			return null;
		}
		if (StringUtils.isNotEmpty(count)) {
			if (NumberUtils.isNumber(count))
				entity.setNum(Long.parseLong(count));
			else {
				super.renderText(response, "销量必须为数字！");
				return null;
			}
		} else {
			super.renderText(response, "请填写销量！");
			return null;
		}
		if (StringUtils.isNotEmpty(price)) {
			if (NumberUtils.isNumber(price))
				entity.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
			else {
				super.renderText(response, "价格必须为数字！");
				return null;
			}
		} else {
			super.renderText(response, "请填写价格！");
			return null;
		}

		entity.setReport_man(peProdUser.getId());
		entity.setReport_time(new java.util.Date());
		entity.setReport_name(peProdUser.getUser_name());// 上报人名

		// 办事处
		entity.setOffice_id(peProdUser.getDept_id());
		entity.setOffice_name(peProdUser.getDepartment());

		// 分公司
		if (peProdUser.getPar_dept_id() != null) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getPar_dept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(
					konkaDept);
			if (konkaDept != null) {
				entity.setSubcomp_id(konkaDept.getDept_id());
				entity.setSubcomp_name(konkaDept.getDept_name());
			}
		}

		super.getFacade().getKonkaMobileFightReportService()
				.createKonkaMobileFightReportWithHis(entity);

		@SuppressWarnings("unused")
		String cls_name = "", brand_name = "";

		// 类别名称
		if (entity.getCls_id() != null) {
			BasePdClazz basePdClazz = new BasePdClazz();
			basePdClazz.setCls_id(entity.getCls_id());
			basePdClazz = super.getFacade().getBasePdClazzService()
					.getBasePdClazz(basePdClazz);
			cls_name = basePdClazz.getCls_name();
		}

		KonkaMobileCategory konkaMobileCategory = new KonkaMobileCategory();
		konkaMobileCategory.setC_index(entity.getBrand_id());
		brand_name = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategory(konkaMobileCategory).getC_name();

		// super.createMobileSysOperLog(request, "KONKA_MOBILE_SAIL_DATA",
		// entity
		// .getId(), mod_id, "" + "新增：新增了一条品牌：" + brand_name + " 品类："
		// + cls_name + "的数据", BeanUtils.describe(entity).toString());

		super.renderText(response, "success");
		return null;
	}
}
