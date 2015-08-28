package com.ebiz.mmt.web.struts.manager.admin;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaMobileFightReport;
import com.ebiz.mmt.domain.KonkaMobilePd;
import com.ebiz.mmt.domain.KonkaMobilePdBrand;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaCompetProdManageAction extends BaseAction {

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
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		super.encodeCharacterForGetMethod(dynaBean, request);
		String md_name_like = (String) dynaBean.get("md_name_like");
		String screen_size_like = (String) dynaBean.get("screen_size_like");
		String price_ref_ge = (String) dynaBean.get("price_ref_ge");
		String price_ref_le = (String) dynaBean.get("price_ref_le");
		String is_del = (String) dynaBean.get("is_del");
		String Par_type_id = (String) dynaBean.get("Par_type_id");
		String  excel_all =(String) dynaBean.get("excel_all"); //导出
		if (StringUtils.isBlank(is_del)) {
			is_del = "0";
		}
		KonkaMobilePd entity = new KonkaMobilePd();
		super.copyProperties(entity, form);
		entity.setIs_del(Integer.valueOf(is_del));
		dynaBean.set("is_del", is_del);
		entity.getMap().put("md_name_like", md_name_like);
		entity.getMap().put("screen_size_like", screen_size_like);
		entity.getMap().put("price_ref_ge", price_ref_ge);
		entity.getMap().put("price_ref_le", price_ref_le);
		
		if(null != Par_type_id && StringUtils.isNotBlank(Par_type_id)){
			entity.setPar_type_id(Long.valueOf(Par_type_id));
		}
		KonkaBaseTypeData entity1 = new KonkaBaseTypeData();
		entity1.setIs_del(0);
		entity1.setIs_lock(0);		
		entity1.setPar_type_id(Long.valueOf(100025));
		List<KonkaBaseTypeData> eList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(entity1);
		request.setAttribute("eList", eList);

		Long recordCount = getFacade().getKonkaMobilePdService().getKonkaMobilePdCount(entity);
		//导出
			if (StringUtils.isNotBlank(excel_all) && "1".equals(excel_all)) {

	            response.setCharacterEncoding("UTF-8");
	            response.setContentType("application/octet-stream");
	            response.setHeader("Content-Disposition", "attachment;filename="
	                    + EncryptUtils.encodingFileName("竞品管理数据") + ".xls");
	            entity.getRow().setFirst(0);
	            entity.getRow().setCount(recordCount.intValue());
	            List<KonkaMobilePd> entityList1 = getFacade().getKonkaMobilePdService().getKonkaMobilePdPaginatedList(entity);
	            request.setAttribute("allList", entityList1);
	            return new ActionForward("/admin/KonkaCompetProdManage/input.jsp");
	        }
		pager.init(recordCount, new Integer(10), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaMobilePd> list = getFacade().getKonkaMobilePdService().getKonkaMobilePdPaginatedList(entity);
		
		request.setAttribute("entityList", list);
		

		KonkaMobilePdBrand pdBrand = new KonkaMobilePdBrand();
		pdBrand.setIs_del(0);
		List<KonkaMobilePdBrand> pdBrandList = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrandList(
				pdBrand);
		request.setAttribute("pdBrandList", pdBrandList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		KonkaBaseTypeData entity1 = new KonkaBaseTypeData();
		entity1.setIs_del(0);
		entity1.setIs_lock(0);
		entity1.setPar_type_id(Long.valueOf(100025));
		List<KonkaBaseTypeData> eList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(entity1);
		request.setAttribute("eList", eList);
		KonkaMobilePdBrand kmpd = new KonkaMobilePdBrand();
		kmpd.setIs_del(0);
		List<KonkaMobilePdBrand> list = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrandList(kmpd);
		request.setAttribute("brandList", list);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String md_name = (String) dynaBean.get("md_name");
		String par_type_id=(String) dynaBean.get("Par_type_id");
		KonkaMobilePd temp = new KonkaMobilePd();
		if (StringUtils.isNotBlank(id)) {
			temp.getMap().put("notThisId", id);
			logger.info("_________________________brand_id = " + id);
		}
		temp.setMd_name(md_name);
		Long num = getFacade().getKonkaMobilePdService().getKonkaMobilePdCount(temp);
		if (num.intValue() > 0) {
			saveError(request, "konka.mobile.pd.mdName.error.is.exist", new String[] { md_name });
			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append(mapping.findForward("success").getPath());
			pathBuffer.append("&mod_id=").append(mod_id);
			pathBuffer.append("&");
			// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			return forward;
		}

		KonkaMobilePd entity = new KonkaMobilePd();
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(par_type_id)) {
			entity.setPar_type_id(Long.valueOf(par_type_id));
		}
		entity.setIs_del(0);
		if (StringUtils.isBlank(id)) {
			super.getFacade().getKonkaMobilePdService().createKonkaMobilePd(entity);
			saveMessage(request, "entity.inserted");
		} else {
			super.getFacade().getKonkaMobilePdService().modifyKonkaMobilePd(entity);
			saveMessage(request, "entity.updated");
		}

		updateDataPatch();

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		super.encodeCharacterForGetMethod(dynaBean, request);
	
		
		KonkaBaseTypeData entity1 = new KonkaBaseTypeData();
		entity1.setIs_del(0);
		entity1.setIs_lock(0);
		entity1.setPar_type_id(Long.valueOf(100025));
		List<KonkaBaseTypeData> eList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(entity1);
		request.setAttribute("eList", eList);
		
		
		
		if (GenericValidator.isLong(id)) {
			KonkaMobilePd entity = new KonkaMobilePd();
			entity.setId(Long.valueOf(id));
			entity = getFacade().getKonkaMobilePdService().getKonkaMobilePd(entity);
			super.copyProperties(form, entity);

			KonkaMobilePdBrand kmpd = new KonkaMobilePdBrand();
			kmpd.setIs_del(0);
			List<KonkaMobilePdBrand> list = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrandList(kmpd);
			request.setAttribute("brandList", list);
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return new ActionForward("KonkaCompetProdManage.do?&mod_id=" + mod_id, true);
		}
		return mapping.findForward("input");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String mod_id = (String) dynaBean.get("mod_id");
		String Par_type_id=(String) dynaBean.get("Par_type_id");
		super.encodeCharacterForGetMethod(dynaBean, request);

		KonkaBaseTypeData entity1 = new KonkaBaseTypeData();
		entity1.setIs_del(0);
		entity1.setIs_lock(0);	
		if(null != Par_type_id && StringUtils.isNotBlank(Par_type_id))
		{
			entity1.setPar_type_id(Long.valueOf(Par_type_id));
		}
		
		List<KonkaBaseTypeData> eList = super.getFacade().getKonkaBaseTypeDataService()
				.getKonkaBaseTypeDataList(entity1);
		request.setAttribute("eList", eList);

		if (GenericValidator.isLong(id)) {
			KonkaMobilePd entity = new KonkaMobilePd();
			entity.setId(Long.valueOf(id));
			entity = getFacade().getKonkaMobilePdService().getKonkaMobilePd(entity);
			super.copyProperties(form, entity);
		} else {
			this.saveError(request, "errors.long", new String[] { id });
			return new ActionForward("KonkaCompetProdManage.do?&mod_id=" + mod_id, true);
		}
		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");

		KonkaMobilePd entity = new KonkaMobilePd();
		entity.setIs_del(1);
		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			entity.setId(Long.valueOf(id));
			super.getFacade().getKonkaMobilePdService().removeKonkaMobilePd(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			entity.getMap().put("pks", pks);
			super.getFacade().getKonkaMobilePdService().removeKonkaMobilePd(entity);
			saveMessage(request, "entity.deleted");
		}

		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id + "&is_del=1");
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "mod_id")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	/*
	 * @desc 导入
	 */
	public ActionForward importMd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		setNaviStringToRequestScope(form, request);
		String mod_id = (String) dynaBean.get("mod_id");

		dynaBean.set("mod_id", mod_id);
		return new ActionForward("/admin/KonkaCompetProdManage/import.jsp");
	}

	public ActionForward importSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		FileInputStream isFile = null;
		String fileSavePath = null;
		String ctxDir = "";

		// List<UploadFile> uploadFileList = super.uploadFile(form, false);
		List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		for (UploadFile uploadFile : uploadFileList) {
			if ("up_load_file".equals(uploadFile.getFormName())) {
				fileSavePath = uploadFile.getFileSavePath();
				break;
			}
		}

		String filetype = fileSavePath.substring(fileSavePath.lastIndexOf(".") + 1);
		if (!filetype.equalsIgnoreCase("xls")) {
			super.renderJavaScript(response, "alert('上传数据格式不正确！');history.back();");
			return null;
		}
		fileSavePath =  "/Attachment_new/konka-files/"  + fileSavePath;
		// 附件保存路径更改遗留问题
//		if (StringUtils.contains(fileSavePath, MmtFilePathConfig.S_OTHERS_PATH)) {
//			ctxDir = "";
//			fileSavePath = "/Attachment_new/konka-files/" + fileSavePath;
//		}
//		fileSavePath = ctxDir + fileSavePath;
		isFile = new FileInputStream(fileSavePath);

		KonkaMobilePd entity = new KonkaMobilePd();
		List<KonkaMobilePd> csCompetProdList = new ArrayList<KonkaMobilePd>();
		boolean verifi_flag = true;// 验证通过标识位

		if (filetype.equalsIgnoreCase("xls") || filetype.equalsIgnoreCase("xlsx")) {
			HSSFWorkbook workbook = new HSSFWorkbook(isFile);
			HSSFSheet sheet = null;
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
				sheet = workbook.getSheetAt(i);
				int rowNum = sheet.getLastRowNum();// 行数
				for (int j = 1; j <= rowNum; j++) {// 获取每行
					HSSFRow row = sheet.getRow(j);

					KonkaMobilePd cs = new KonkaMobilePd();

					// 品牌(必填) 尺寸(必填) 智能电视(必填) 三维电视(必填) 背光源(必填) 机型 参考价格 排序值
					// 验证品牌
					if ("".equals(getCellText(row, 0))) {
						verifi_flag = false;
						cs.getMap().put("msg0", "<span style=\\'color:#f00;\\'>【品牌】</span>不能为空！");
						cs.getMap().put("con0", getCellText(row, 0));
					} else {
						KonkaMobilePdBrand temp = new KonkaMobilePdBrand();
						temp.setBrand_name(getCellText(row, 0));
						temp.setIs_del(0);
						Long num = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrandCount(temp);
						if (num.intValue() == 1) {
							KonkaMobilePdBrand kmpb = getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrand(
									temp);
							cs.setBrand_id(kmpb.getBrand_id());
							cs.setBrand_name(getCellText(row, 0));
						} else {
							verifi_flag = false;
							cs.getMap().put(
									"msg0",
									"填写值<span style=\\'color:#8B0000;\\'>【" + getCellText(row, 0)
											+ "】</span>非法！未在品牌库中查询到该品牌！");
							cs.getMap().put("con0", getCellText(row, 0));
						}
					}

					// 验证尺寸
					if ("".equals(subZeroAndDot(getCellText(row, 1)))) {
						verifi_flag = false;
						cs.getMap().put("msg1", "<span style=\\'color:#f00;\\'>【尺寸】</span>不能为空！");
					} else {
						if (GenericValidator.isInt(subZeroAndDot(getCellText(row, 1)))) {
							cs.setScreen_size(Integer.valueOf(subZeroAndDot(getCellText(row, 1))));
							Integer size=Integer.valueOf(subZeroAndDot(getCellText(row, 1)));
					//规格段根据尺寸添加
							if(size>65)
							{
								cs.setPar_type_id(Long.valueOf(10002508));
							}else if(size==65)
							{
								cs.setPar_type_id(Long.valueOf(10002507));
							}else if(size>=58 && size<=60)
							{
								cs.setPar_type_id(Long.valueOf(10002506));
							}else if(size==55)
							{
								cs.setPar_type_id(Long.valueOf(10002505));
							}else if(size>=46 && size<=50)
							{
								cs.setPar_type_id(Long.valueOf(10002504));
							}else if(size==42 || size==43)
							{
								cs.setPar_type_id(Long.valueOf(10002503));
							}else if(size==39 || size==40)
							{
								cs.setPar_type_id(Long.valueOf(10002502));
							}else if(size>=32)
							{
								cs.setPar_type_id(Long.valueOf(10002501));
							}else
							{
								cs.setPar_type_id(Long.valueOf(10002509));
							}
							
						} else {
							verifi_flag = false;
							cs.getMap().put(
									"msg1",
									"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(getCellText(row, 1))
											+ "】</span>非法！必须填写整数！");
							cs.getMap().put("con1", subZeroAndDot(getCellText(row, 1)));
						}
					}

					// 智能电视
					if ("".equals(getCellText(row, 2))) {
						verifi_flag = false;
						cs.getMap().put("msg2", "<span style=\\'color:#f00;\\'>【智能电视】</span>不能为空！");
					} else {
						if ("否".equals(getCellText(row, 2))) {
							cs.setIs_smart_tv(0);
						} else if ("是".equals(getCellText(row, 2))) {
							cs.setIs_smart_tv(1);
						} else {
							verifi_flag = false;
							cs.getMap()
									.put("msg2",
											"填写值<span style=\\'color:#8B0000;\\'>【"
													+ getCellText(row, 2)
													+ "】</span>非法！<br />必须填<span style=\\'color:#f00;\\'>【是】</span>或者<span style=\\'color:#f00;\\'>【否】</span>");
							cs.getMap().put("con2", getCellText(row, 2));
						}
					}

					// 三维电视
					if ("".equals(getCellText(row, 3))) {
						verifi_flag = false;
						cs.getMap().put("msg3", "<span style=\\'color:#f00;\\'>【三维电视】</span>不能为空！");
					} else {
						if ("2D".equals(getCellText(row, 3))) {
							cs.setD_tv(0);
						} else if ("3D".equals(getCellText(row, 3))) {
							cs.setD_tv(1);
						} else {
							verifi_flag = false;
							cs.getMap()
									.put("msg3",
											"填写值<span style=\\'color:#8B0000;\\'>【"
													+ getCellText(row, 3)
													+ "】</span>非法！<br />必须填<span style=\\'color:#f00;\\'>【2D】</span>或者<span style=\\'color:#f00;\\'>【3D】</span>");
							cs.getMap().put("con3", getCellText(row, 3));
						}
					}

					// 背光源
					if ("".equals(getCellText(row, 4))) {
						verifi_flag = false;
						cs.getMap().put("msg4", "<span style=\\'color:#f00;\\'>【背光源】</span>不能为空！");
					} else {
						if ("LED".equals(getCellText(row, 4))) {
							cs.setBack_light(0);
						} else if ("CCFL".equals(getCellText(row, 4))) {
							cs.setBack_light(1);
						} else if ("其他".equals(getCellText(row, 4))) {
							cs.setBack_light(9);
						} else {
							verifi_flag = false;
							cs.getMap()
									.put("msg4",
											"填写值<span style=\\'color:#8B0000;\\'>【"
													+ getCellText(row, 4)
													+ "】</span>非法！<br />必须填<span style=\\'color:#f00;\\'>【LED】</span>、<span style=\\'color:#f00;\\'>【CCFL】</span>或者<span style=\\'color:#f00;\\'>【其他】</span>");
							cs.getMap().put("con4", getCellText(row, 4));
						}
					}

					// 机型
					if ("".equals(getCellText(row, 5))) {
						verifi_flag = false;
						cs.getMap().put("msg5", "<span style=\\'color:#f00;\\'>【机型】</span>不能为空！");
					} else {
						cs.setMd_name(getCellText(row, 5));
					}

					// 参考价格
					if ("".equals(getCellText(row, 6))) {
						verifi_flag = false;
						cs.getMap().put("msg6", "<span style=\\'color:#f00;\\'>【参考价格】</span>不能为空！");
					} else {
						if (GenericValidator.isDouble(subZeroAndDot(getCellText(row, 6)))) {
							cs.setRef_price(new BigDecimal(subZeroAndDot(getCellText(row, 6))));
						} else {
							verifi_flag = false;
							cs.getMap().put(
									"msg6",
									"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(getCellText(row, 6))
											+ "】</span>非法！必须填写数字！");
							cs.getMap().put("con6", subZeroAndDot(getCellText(row, 6)));
						}
					}

					// 排序值
					if (StringUtils.isNotBlank(getCellText(row, 7))) {
						if (GenericValidator.isInt(subZeroAndDot(getCellText(row, 7)))) {
							cs.setOrder_value(Integer.valueOf(subZeroAndDot(getCellText(row, 7))));
						} else {
							verifi_flag = false;
							cs.getMap().put(
									"msg7",
									"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(getCellText(row, 7))
											+ "】</span>非法！必须填写整数！");
							cs.getMap().put("con7", subZeroAndDot(getCellText(row, 7)));
						}
					}

					// 状态
					if (StringUtils.isNotBlank(getCellText(row, 8))) {
						String v = subZeroAndDot(getCellText(row, 8));
						if (GenericValidator.isInt(v) && GenericValidator.isInRange(Integer.valueOf(v), 0, 1)) {
							v = (v == "1" ? "1" : "0");
							cs.setIs_del(Integer.valueOf(v));
						} else {
							verifi_flag = false;
							cs.getMap().put(
									"msg8",
									"填写值<span style=\\'color:#8B0000;\\'>【" + subZeroAndDot(getCellText(row, 8))
											+ "】</span>非法！必须填写“0”或者“1”！");
							cs.getMap().put("con8", subZeroAndDot(getCellText(row, 8)));
						}
					} else {
						cs.setIs_del(0);
					}

					csCompetProdList.add(cs);
				}
			}
		}

		if (!verifi_flag) {
			request.setAttribute("csCompetProdList", csCompetProdList);
			return importMd(mapping, form, request, response);
		}

		entity.setKonkaMobilePdList(csCompetProdList);

		super.getFacade().getKonkaMobilePdService().createKonkaMobilePdForImport(entity);
		super.saveMessage(request, "entity.imported");

		updateDataPatch();

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	private String getCellText(HSSFRow row, int col) {
		if (row.getCell(col) == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#.000000");
		HSSFCell cell = row.getCell(col);
		String data = "";
		if (null != cell) {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				data = df.format(cell.getNumericCellValue());
				// data = String.format("%.6f", cell.getNumericCellValue());
				// data = String.valueOf(cell.getNumericCellValue());
				// data = new BigDecimal(cell.getNumericCellValue()).toString();
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				data = cell.getRichStringCellValue().toString();
			}
		}
		return data.trim();
	}
	
	public ActionForward updateDel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String id = (String) dynaBean.get("id");
		String md_name = (String) dynaBean.get("md_name");
		String par_type_id=(String) dynaBean.get("Par_type_id");
		KonkaMobilePd temp = new KonkaMobilePd();
		if (StringUtils.isNotBlank(id)) {
			temp.getMap().put("notThisId", id);
			logger.info("_________________________brand_id = " + id);
		}
		temp.setMd_name(md_name);
		KonkaMobilePd entity = new KonkaMobilePd();
		super.copyProperties(entity, form);
		if (StringUtils.isNotBlank(par_type_id)) {
			entity.setPar_type_id(Long.valueOf(par_type_id));
		}
		entity.setIs_del(0);
		if (StringUtils.isNotBlank(id)) {
			super.getFacade().getKonkaMobilePdService().modifyKonkaMobilePd(entity);
			saveMessage(request, "entity.updated");
		}

		updateDataPatch();

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=").append(mod_id);
		pathBuffer.append("&");
		// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

}
