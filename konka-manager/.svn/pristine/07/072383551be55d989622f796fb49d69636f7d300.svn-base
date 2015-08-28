package com.ebiz.mmt.web.struts.manager.admin;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BasePdClass;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BaseProp;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.PdModelPropsVal;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PePdSellarea;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.StdEntpProd;
import com.ebiz.mmt.domain.StdEntpProdJoinBrandId;
import com.ebiz.mmt.r3.MARA;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.Keys;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Gao,YongXiang
 * @version 2011-09-22
 */
public class PePdModelAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		// if (!super.initRequstParams(request, response)) {
		// return null;
		// }
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String md_name_like = (String) dynaBean.get("md_name_like");
		// String cls_id_par = (String) dynaBean.get("cls_id");
		String price_ref_ge = (String) dynaBean.get("price_ref_ge");
		String price_ref_le = (String) dynaBean.get("price_ref_le");
		String label_3d = (String) dynaBean.get("label_3d");
		String label_int = (String) dynaBean.get("label_int");
		String label_www = (String) dynaBean.get("label_www");
		String is_parts = (String) dynaBean.get("is_parts");
		String is_del = (String) dynaBean.get("is_del");
		String add_date_s = (String) dynaBean.get("add_date_s");
		String add_date_e = (String) dynaBean.get("add_date_e");
		String is_4k = (String) dynaBean.get("is_4k");
		String is_ytv = (String) dynaBean.get("is_ytv");
		String brand_id = (String) dynaBean.get("brand_id");
		String sub_brand_id = (String) dynaBean.get("sub_brand_id");

		PePdModel entity = new PePdModel();

		if (StringUtils.isNotBlank(brand_id)) {
			entity.setBrand_id(Long.valueOf(brand_id));
		}
		if (StringUtils.isNotBlank(sub_brand_id)) {
			entity.setSub_brand_id(Long.valueOf(sub_brand_id));
		}

		if (StringUtils.isNotBlank(is_del)) {
			entity.setIs_del(Integer.valueOf(is_del));
		} else {
			entity.setIs_del(0);
		}
		if (StringUtils.isNotBlank(md_name_like)) {
			entity.getMap().put("md_name_like", md_name_like);
		}
		if (StringUtils.isNotBlank(price_ref_ge)) {
			entity.getMap().put("price_ref_ge", price_ref_ge);
		}
		if (StringUtils.isNotBlank(price_ref_le)) {
			entity.getMap().put("price_ref_le", price_ref_le);
		}

		if (StringUtils.isNotBlank(label_3d)) {
			entity.setLabel_3d(Integer.valueOf(label_3d));
		}
		if (StringUtils.isNotBlank(is_parts)) {
			entity.setIs_parts(Integer.valueOf(is_parts));
		}
		if (StringUtils.isNotBlank(is_4k)) {
			entity.setIs_4k(Integer.valueOf(is_4k));
		}
		if (StringUtils.isNotBlank(is_ytv)) {
			entity.setIs_ytv(Integer.valueOf(is_ytv));
		}
		if (StringUtils.isNotBlank(label_int)) {
			entity.setLabel_int(Integer.valueOf(label_int));
		}
		if (StringUtils.isNotBlank(label_www)) {
			entity.setLabel_www(Integer.valueOf(label_www));
		}
		if (StringUtils.isNotBlank(add_date_s)) {
			entity.getMap().put("add_date_s", add_date_s + " 00:00:00");
		}
		if (StringUtils.isNotBlank(add_date_e)) {
			entity.getMap().put("add_date_e", add_date_e + " 23:59:59");
		}

		Long recordCount = super.getFacade().getPePdModelService().getPePdModelCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PePdModel> entityList = super.getFacade().getPePdModelService()
				.getPePdModulePaginatedIncludeMdNameList(entity);

		request.setAttribute("entityList", entityList);

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		return mapping.findForward("list");
	}

	@SuppressWarnings("deprecation")
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		request.setAttribute("basePdClazzList", getBasePdClazzList());

		BaseProvinceList baseProvince = new BaseProvinceList();
		baseProvince.setPar_index(new Long(0));
		baseProvince.setDel_mark(new Short("0"));

		List<BaseProvinceList> baseProvinceList = super.getFacade().getBaseProvinceListService()
				.getBaseProvinceListList(baseProvince);

		java.util.Date da = new java.util.Date();
		PePdModel entity = new PePdModel();
		entity.setAudit_state(1);
		entity.setIs_sell(1);
		entity.setUp_date(new java.util.Date());
		da.setYear(3000);
		entity.setDown_date(da);
		entity.setIs_spec_price(0);
		entity.setEnable_sellarea(0);
		super.copyProperties(form, entity);

		request.setAttribute("baseProvinceList", baseProvinceList);

		dynaBean.set("order_value", "0");
		dynaBean.set("label_int", "0");
		dynaBean.set("label_www", "0");
		dynaBean.set("is_parts", "0");
		dynaBean.set("is_4k", "0");
		dynaBean.set("is_ytv", "0");
		
		//尺寸段基础数据
        super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");

		if (!GenericValidator.isLong(pd_id)) {
			saveError(request, "errors.long", new String[] { pd_id });
			return mapping.findForward("list");
		}

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		PePdModel entity = new PePdModel();
		entity.setPd_id(Long.valueOf(pd_id));

		/**
		 * 114:康佳(规则未知)<br>
		 * 115:现代(产口组为28)<br>
		 * 116:KKTV()
		 */
		// entity.setBrand_id(114L);

		entity = super.getFacade().getPePdModelService().getPePdModel(entity);

		if (null != entity) {
			StdEntpProd stdEntpProd = new StdEntpProd();
			stdEntpProd.setEntp_id(entity.getEntp_id());
			stdEntpProd = super.getFacade().getStdEntpProdService().getStdEntpProd(stdEntpProd);

			if (null != stdEntpProd) {
				dynaBean.set("entp_name", stdEntpProd.getEntp_name());
			}

			BaseProvinceList baseProvince = new BaseProvinceList();
			baseProvince.setPar_index(new Long(0));
			baseProvince.setDel_mark(new Short("0"));

			List<BaseProvinceList> baseProvinceList = super.getFacade().getBaseProvinceListService()
					.getBaseProvinceListList(baseProvince);

			if (null != entity.getEnable_sellarea() && entity.getEnable_sellarea() == 1) {
				PePdSellarea pePdSellarea = new PePdSellarea();
				pePdSellarea.setPe_pd_id(Long.valueOf(pd_id));

				List<PePdSellarea> pePdSellareaList = super.getFacade().getPePdSellareaService()
						.getPePdSellareaList(pePdSellarea);

				if (null != baseProvinceList && baseProvinceList.size() > 0) {
					for (BaseProvinceList temp : baseProvinceList) {
						if (null != pePdSellareaList && pePdSellareaList.size() > 0) {
							for (PePdSellarea pePdSellareaTemp : pePdSellareaList) {
								if (temp.getP_index().equals(pePdSellareaTemp.getP_index())) {
									temp.getMap().put("select", "selected");
								}
							}
						}
					}
				}
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
			if (null != entity.getUp_date()) {
				String up_date_hour_str = sdf.format(entity.getUp_date()).split(" ")[1];
				if (StringUtils.isNotBlank(up_date_hour_str)) {
					int up_date_hour = Integer.parseInt(up_date_hour_str);
					dynaBean.set("up_date_hour", up_date_hour);
				}
			}
			if (null != entity.getUp_date()) {
				String down_date_hour_str = sdf.format(entity.getUp_date()).split(" ")[1];
				if (StringUtils.isNotBlank(down_date_hour_str)) {
					int down_date_hour = Integer.parseInt(sdf.format(entity.getDown_date()).split(" ")[1]);
					dynaBean.set("down_date_hour", down_date_hour);
				}
			}

			request.setAttribute("baseProvinceList", baseProvinceList);

		}
		
		entity.setQueryString(super.serialize(request, "pd_id", "method"));
		copyProperties(form, entity);
		//尺寸段基础数据
        super.getFacade().getKonkaBaseTypeDataService().setRequestSizeSecList(request, "sizeSecList", "100023");
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		updateDataPatch();
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String[] p_index_pks = (String[]) dynaBean.get("pks");
		String size_sec = (String) dynaBean.get("size_sec");

		PePdModel entity = new PePdModel();
		super.copyProperties(entity, form);

		if (null != entity && null != entity.getCls_id()) {
			BasePdClass basePdClass = new BasePdClass();
			basePdClass.setCls_id(entity.getCls_id());
			basePdClass.setIs_del(0);

			basePdClass = super.getFacade().getBasePdClassService().getBasePdClass(basePdClass);

			if (null != basePdClass) {
				entity.setPar_cls_id(basePdClass.getPar_id());
			}
		}
		if (StringUtils.isNotBlank(size_sec)) {
			entity.setSize_sec(Integer.valueOf(size_sec));
		}
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.NEWS_PATH, true, 0, new int[] { 120,
				240, 400, 640 });
		for (UploadFile uploadFile : uploadFileList) {
			if ("main_pic".equalsIgnoreCase(uploadFile.getFormName())) {
				entity.setMain_pic(uploadFile.getFileSavePath());
			}
		}
		if (StringUtils.isBlank(entity.getMain_pic())) {
			entity.setMain_pic(null);
		}

		entity.setIs_del(0);
		entity.setPd_src(Long.valueOf(0));

		if (StringUtils.isBlank(pd_id)) {

			// 检查型号名称是否已经存在
			PePdModel pePdModel = new PePdModel();
			pePdModel.setMd_name(entity.getMd_name());

			List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pePdModel);

			if (null != pePdModelList && pePdModelList.size() > 0) {
				String msg = super.getMessage(request, "prodadmin.md.name.repeat");
				for (PePdModel temp : pePdModelList) {
					if (temp.getIs_del() == 1) {
						msg = super.getMessage(request, "prodadmin.md.name.deleted");
						break;
					}
				}
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			// 新增产品两个属性值初始化
			entity.setEntp_prod_id(new Long(274));
			entity.setEntp_id(new Long(134));
			entity.setBrand_id(114L);// 114康佳产品
			pd_id = super.getFacade().getPePdModelService().createPePdModel(entity).toString();
			saveMessage(request, "entity.inserted");
			request.setAttribute("pd_id", pd_id);
		} else {
			if (!GenericValidator.isLong(pd_id)) {
				saveError(request, "errors.long", new String[] { pd_id });
				return mapping.findForward("list");
			}

			// 产品未上架
			if (null != entity && entity.getIs_sell() == 0) {
				entity.setIs_spec_price(0);
				entity.setEnable_sellarea(0);
				entity.getMap().put("up_date_set_null", true);
				entity.getMap().put("down_date_set_null", true);
				entity.getMap().put("spec_price_set_null", true);
			}
			entity.setBrand_id(114L);
			super.getFacade().getPePdModelService().modifyPePdModel(entity);
			saveMessage(request, "entity.updated");
		}

		if (null != p_index_pks) {
			PePdSellarea pePdSellarea = new PePdSellarea();
			pePdSellarea.setPe_pd_id(Long.valueOf(pd_id));
			super.getFacade().getPePdSellareaService().createPePdSellareaList(pePdSellarea, p_index_pks);
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		// pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&").append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");

		if (!GenericValidator.isLong(pd_id)) {
			saveError(request, "errors.long", new String[] { pd_id });
			return mapping.findForward("list");
		}

		PePdModel entity = new PePdModel();
		entity.setPd_id(Long.valueOf(pd_id));
		super.getFacade().getPePdModelService().removePePdModel(entity);

		saveMessage(request, "entity.deleted");

		updateDataPatch();
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "pd_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward listEntpMain(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PeProdUser sessionUser = (PeProdUser) super.getSessionAttribute(request, Keys.PE_PROD_USER_SESSION);

		StdEntpProdJoinBrandId entity = new StdEntpProdJoinBrandId();

		entity.setBrand_id(sessionUser.getEntpProd().getBrand_id());

		List<StdEntpProdJoinBrandId> entityList = super.getFacade().getStdEntpProdJoinBrandIdService()
				.getStdEntpProdJoinBrandIdWithEntpName(entity);

		request.setAttribute("entpMainList", entityList);

		return new ActionForward("/admin/PePdModel/list_entpMain.jsp");
	}

	public ActionForward editProperty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		// if (!super.initRequstParams(request, response)) {
		// return null;
		// }

		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");

		if (StringUtils.isNotBlank(pd_id)) {
			PePdModel entity = new PePdModel();
			entity.setPd_id(Long.valueOf(pd_id));
			entity = super.getFacade().getPePdModelService().getPePdModel(entity);
			if (null != entity && null != entity.getCls_id()) {
				BasePdClazz bpc = new BasePdClazz();
				bpc.setCls_id(entity.getCls_id());
				bpc.setIs_del(0);
				bpc = super.getFacade().getBasePdClazzService().getBasePdClazz(bpc);
				if (null != bpc) {
					request.setAttribute("full_cls_name", bpc.getFull_name().replace(",", ">>"));
				}

				// property
				BaseProp bp = new BaseProp();
				bp.getMap().put("par_cls_id", entity.getCls_id());
				bp.setIs_del(0);
				List<BaseProp> bpList = super.getFacade().getBasePropService().getBasePropWithCateNameList(bp);
				if (null == bpList || bpList.size() < 1) {
					String error = super.getMessage(request, "pdProperty.no.data");
					super.renderJavaScript(response, "alert('" + error + "');history.back();");
					return null;
				}

				for (BaseProp temp : bpList) {
					BasePropValItem bpvi = new BasePropValItem();
					bpvi.setProp_id(temp.getProp_id());
					bpvi.setIs_del(0);

					List<BasePropValItem> basePropValItemList = super.getFacade().getBasePropValItemService()
							.getBasePropValItemList(bpvi);
					temp.setBasePropValItemList(basePropValItemList);

					PdModelPropsVal propVal = new PdModelPropsVal();
					propVal.setPd_id(entity.getPd_id());
					propVal.setProp_id(temp.getProp_id());

					List<PdModelPropsVal> propValList = super.getFacade().getPdModelPropsValService()
							.getPdModelPropsValList(propVal);

					if (null != propValList && propValList.size() > 0) {
						String prop_value = propValList.get(0).getProp_value();
						temp.getMap().put("prop_value", prop_value);
					}
				}
				request.setAttribute("bpList", bpList);
				entity.setQueryString(super.serialize(request, "pd_id", "method"));
				super.copyProperties(form, entity);
				return new ActionForward("/admin/PePdModel/property.jsp");
			} else {
				String error = super.getMessage(request, "pdProperty.no.data");
				super.renderJavaScript(response, "alert('" + error + "');history.back();");
				return null;
			}
		}
		return this.list(mapping, form, request, response);
	}

	public ActionForward saveProperty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");
		String pd_id = (String) dynaBean.get("pd_id");
		String[] prop_ids = request.getParameterValues("prop_ids");
		String[] prop_values = request.getParameterValues("prop_values");

		if (!GenericValidator.isLong(pd_id)) {
			saveError(request, "errors.long", new String[] { pd_id });
			return mapping.findForward("list");
		}

		PdModelPropsVal entity = new PdModelPropsVal();
		if (null != prop_ids && prop_ids.length > 0) {
			int max_length = prop_ids.length;
			for (int i = 0; i < max_length; i++) {
				String[] selectedPdMoreProperties = request.getParameterValues("check_box_" + prop_ids[i]);
				if (null != selectedPdMoreProperties && selectedPdMoreProperties.length > 0) {
					StringBuffer temp_valuesBuffer = new StringBuffer();
					StringBuffer temp_idsBuffer = new StringBuffer();
					for (String temp : selectedPdMoreProperties) {
						temp_valuesBuffer.append(temp.split("-")[1] + ",");
						temp_idsBuffer.append(temp.split("-")[0] + ",");
					}
					String temp_valuesString = String.valueOf(temp_valuesBuffer);
					String temp_idsString = String.valueOf(temp_idsBuffer);
					if (StringUtils.isNotBlank(temp_idsString) && temp_idsString.length() > 1) {
						temp_idsString = StringUtils.substring(temp_idsString, 0, temp_idsString.length() - 1);
						temp_valuesString = StringUtils.substring(temp_valuesString, 0, temp_valuesString.length() - 1);
						prop_values[i] = temp_idsString + "|||" + temp_valuesString;
					}
				}
			}
			entity.setPd_id(Long.valueOf(pd_id));
			entity.getMap().put("prop_ids", prop_ids);
			entity.getMap().put("prop_values", prop_values);
			super.getFacade().getPdModelPropsValService().createPdModelPropsValList(entity);
			super.saveMessage(request, "entity.updated");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "pd_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward getMdName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String md_name = (String) dynaBean.get("md_name");

		PePdModel pePdModel = new PePdModel();
		pePdModel.setMd_name(md_name);

		String isExist = "null";

		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pePdModel);

		// StringBuffer sb = new StringBuffer("{isExist:");
		if (null != pePdModelList && pePdModelList.size() > 0) {
			isExist = String.valueOf("1"); // "1" 表示该产品型号已存在，不可用

			for (PePdModel temp : pePdModelList) {
				if (temp.getIs_del() == 1) {
					isExist = String.valueOf("2"); // "2" 表示该产品型号已存在并且已被删除，不可用
					break;
				}
			}

		} else {
			isExist = String.valueOf("0");
		}

		super.render(response, isExist, "text/x-json;charset=UTF-8");
		return null;
	}

	public ActionForward qRCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String md_name_like = (String) dynaBean.get("md_name_like");
		String cls_id_par = (String) dynaBean.get("cls_id");
		// String price_ref_ge = (String) dynaBean.get("price_ref_ge");
		// String price_ref_le = (String) dynaBean.get("price_ref_le");

		PePdModel entity = new PePdModel();

		entity.setIs_del(0);

		if (GenericValidator.isLong(md_name_like)) {
			entity.setPd_id(Long.valueOf(md_name_like));
		} else {
			entity.getMap().put("md_name_like", md_name_like);
		}
		if (StringUtils.isNotBlank(cls_id_par)) {
			entity.getMap().put("cls_id_par", cls_id_par);
		}

		Long recordCount = super.getFacade().getPePdModelService().getPePdModelCount(entity);
		pager.init(recordCount, 12, pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PePdModel> entityList = super.getFacade().getPePdModelService()
				.getPePdModulePaginatedIncludeMdNameList(entity);

		if (null != entityList && entityList.size() > 0) {

			for (PePdModel temp : entityList) {
				String str = "";
				// 物料编号
				if (null != temp.getMat_num()) {
					str = str + temp.getMat_num() + ",,";
				} else {
					str = str + ",,";
				}

				// 品类ID
				if (null != temp.getCls_id()) {
					str = str + temp.getCls_id() + ",,";
				} else {
					str = str + ",,";
				}

				// 品类描述
				if (null != temp.getMap().get("cls_name")) {
					str = str + temp.getMap().get("cls_name") + ",,";
				} else {
					str = str + ",,";
				}

				// 尺寸
				PdModelPropsVal pmpv = new PdModelPropsVal();
				pmpv.setPd_id(temp.getPd_id());
				pmpv.setProp_id(10749L);
				List<PdModelPropsVal> pmpvList = super.getFacade().getPdModelPropsValService()
						.getPdModelPropsValList(pmpv);
				if (null != pmpvList && pmpvList.size() > 0 && null != pmpvList.get(0).getProp_value()) {
					str = str + "10749,," + pmpvList.get(0).getProp_value() + ",,";
				} else {
					str = str + "10749,," + ",,";
				}

				// 型号ID
				if (null != temp.getPd_id()) {
					str = str + temp.getPd_id() + ",,";
				} else {
					str = str + ",,";
				}

				// 型号名称
				if (null != temp.getMd_name()) {
					str = str + temp.getMd_name() + ",,";
				} else {
					str = str + ",,";
				}
				temp.getMap().put("str", str);
			}

			request.setAttribute("entityList", entityList);
		}

		request.setAttribute("basePdClazzList", getBasePdClazzList());

		return new ActionForward("/admin/PePdModel/qRCode_list.jsp");
	}

	// 同步商品接口
	public ActionForward tbModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String tree_param = (String) dynaBean.get("tree_param");

		String erdat = "2006-01-01";
		PeProdUser ui = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		// get sap data
		List<MARA> list = new ArrayList<MARA>();
		ReturnInfo<MARA> info = new ReturnInfo<MARA>();
		info = super.getFacade().getR3WebInterfaceService().getMaraList(erdat);
		if (info.getSuccess() == true) {
			list = info.getDataResult();
		}

		// 添加记录日志
		OperLog operLog = new OperLog();
		operLog.setLink_tab("KONKA_PE_PD_MODEL");
		operLog.setOper_time(new Date());
		operLog.setOper_uname(ui.getUser_name());
		operLog.setPpdm_name("开始执行手动同步产品库");
		operLog.setOper_type("同步产品库 ");
		operLog.setLink_id(Long.parseLong("888882"));
		operLog.setOper_ip(ui.getLast_login_ip());
		operLog.setOper_uid(ui.getId());
		super.getFacade().getOperLogService().createOperLog(operLog);

		Long count = super.getFacade().getPePdModelService().createPePdModelForTb(list);

		operLog.setLink_tab("KONKA_PE_PD_MODEL");
		operLog.setOper_time(new Date());
		operLog.setOper_uname(ui.getUser_name());
		operLog.setPpdm_name("结束执行手动同步产品库");
		operLog.setOper_type("同步产品库 ");
		operLog.setLink_id(Long.parseLong("888882"));
		operLog.setOper_ip(ui.getLast_login_ip());
		operLog.setOper_uid(ui.getId());
		super.getFacade().getOperLogService().createOperLog(operLog);

		saveMessage(request, "prodadmin.md.tb.success", new String(count.toString()));

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "pd_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

	public ActionForward modifyDel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}
		updateDataPatch();
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String pd_id = (String) dynaBean.get("pd_id");
		String mod_id = (String) dynaBean.get("mod_id");
		String is_del = (String) dynaBean.get("is_del");
		String tree_param = (String) dynaBean.get("tree_param");

		if (!GenericValidator.isLong(pd_id)) {
			saveError(request, "errors.long", new String[] { pd_id });
			return mapping.findForward("list");
		}

		PePdModel entity = new PePdModel();
		entity.setPd_id(Long.valueOf(pd_id));
		entity.setIs_del(Integer.valueOf(is_del));
		logger.info("**********************************************{}", pd_id);
		super.getFacade().getPePdModelService().modifyPePdModel(entity);

		super.saveMessage(request, "entity.updated");

		updateDataPatch();
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=" + mod_id);
		pathBuffer.append("&").append("tree_param=" + tree_param);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "pd_id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}
}
