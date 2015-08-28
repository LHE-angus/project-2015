package com.ebiz.mmt.web.struts.mobile.html;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSailsReturn;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

/**
 * @author Wang Hao
 */
public class ReturnsReportAction extends MobileBaseAction {

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
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		// 品类
		String cls_id = (String) dynaBean.get("select-choice-1");
		// 型号
		String pd_id = (String) dynaBean.get("select-choice-2");
		// 尺寸
		String pro_id = (String) dynaBean.get("select-choice-3");
		// 单价
		String price = (String) dynaBean.get("sales_price");
		// 销量
		String count = (String) dynaBean.get("sales_count");
		
		String mod_id = (String) dynaBean.get("mod_id");
		
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request,
				Constants.USER_MOBILE);

		KonkaMobileSailsReturn entity = new KonkaMobileSailsReturn();
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
		} else {
			super.renderText(response, "请重新登录！");
			return null;
		}

		// 品类名称
		BasePdClazz basePdClazz = new BasePdClazz();
		if (StringUtils.isNotEmpty(cls_id)) {
			entity.setCategory_id(new Long(cls_id));

			basePdClazz.setCls_id(Long.valueOf(cls_id));
			basePdClazz = super.getFacade().getBasePdClazzService()
					.getBasePdClazz(basePdClazz);
			if (basePdClazz != null) {
				String[] name = basePdClazz.getFull_name().split(",");
				entity.setCategory_name(name[name.length - 1]);
			}
		} else {
			super.renderText(response, "请选择品类！");
			return null;
		}

		// 尺寸名称
		BasePropValItem basePropValItem = new BasePropValItem();
		if (StringUtils.isNotEmpty(pro_id)) {
			entity.setMeasure_id(new Long(pro_id));

			basePropValItem.setProp_item_id(Long.valueOf(pro_id));
			basePropValItem = super.getFacade().getBasePropValItemService()
					.getBasePropValItem(basePropValItem);
			if (basePropValItem != null) {
				entity.setMeasure_name(basePropValItem.getProp_item_name());
			}
		} else {
			super.renderText(response, "请选择尺寸！");
			return null;
		}

		// 型号名称
//		if (StringUtils.isNotEmpty(pd_id)) {
//			entity.setModel_id(new Long(pd_id));
//			PePdModel pePdModel = new PePdModel();
//			pePdModel.setPd_id(Long.valueOf(pd_id));
//			pePdModel = super.getFacade().getPePdModelService().getPePdModel(
//					pePdModel);
//			if (pePdModel != null) {
//				entity.setModel_name(pePdModel.getMd_name());
//			}
//		} else {
//			super.renderText(response, "请选择型号！");
//			return null;
//		}
		if (StringUtils.isNotBlank(pd_id)) {
			PePdModel pePdModel = new PePdModel();
			pePdModel.setMd_name(pd_id);
			pePdModel.setIs_del(0);
			pePdModel = super.getFacade().getPePdModelService().getPePdModel(
					pePdModel);
			if (pePdModel != null) {
				entity.setModel_name(pePdModel.getMd_name());
			}else{
				super.renderText(response, "无此型号，请重新填写型号！");
				return null;
			}
		} else {
			super.renderText(response, "请填写型号！");
			return null;
		}
		entity.setReport_id(peProdUser.getId());
		entity.setReport_date(new java.util.Date());
		entity.setReport_name(peProdUser.getUser_name());

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
				entity.setSingle_price(BigDecimal.valueOf(Double
						.parseDouble(price)));
			else {
				super.renderText(response, "价格必须为数字！");
				return null;
			}
		} else {
			super.renderText(response, "请填写价格！");
			return null;
		}
		entity.setAll_price(BigDecimal.valueOf(Double.parseDouble(price)).multiply(BigDecimal.valueOf(Long.parseLong(count))));	//总金额
		super.getFacade().getKonkaMobileSailsReturnService()
				.createKonkaMobileSailsReturnForHis(entity);
		
		super.createMobileSysOperLog(request, "KONKA_MOBILE_SAIL_DATA", entity.getId(), mod_id, "" + "新增：新增了一条品类：" + entity.getCategory_name()
				+ " 尺寸：" + entity.getMeasure_name() +"的数据", BeanUtils.describe(entity).toString());


		super.renderText(response, "success");
		return null;
	}
}
