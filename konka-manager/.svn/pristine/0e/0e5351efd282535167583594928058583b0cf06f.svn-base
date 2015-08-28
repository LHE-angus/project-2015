package com.ebiz.mmt.web.struts.manager.zmd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxPdHistory;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author hu hao
 *@version 2012-1-9
 */
public class KonkaXxPdAction extends BaseZmdAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
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
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		// String par_cls_id = (String) dynaBean.get("cls_id");
		String price_min_ge = (String) dynaBean.get("price_min_ge");
		String price_min_le = (String) dynaBean.get("price_min_le");
		String allToExcel = (String) dynaBean.get("allToExcel");
		String audit_state = (String) dynaBean.get("audit_state");
		String md_name_like = (String) dynaBean.get("md_name_like");
		KonkaXxPd entity = new KonkaXxPd();

		// if (StringUtils.isNotBlank(par_cls_id)) {
		// entity.getMap().put("par_cls_id", par_cls_id);
		// }
		if (StringUtils.isNotBlank(price_min_ge)) {
			entity.getMap().put("price_min_ge", price_min_ge);
		}
		if (StringUtils.isNotBlank(price_min_le)) {
			entity.getMap().put("price_min_le", price_min_le);
		}
		if (StringUtils.isNotBlank(audit_state)) {
			entity.setAudit_state(Integer.valueOf(audit_state));
		}

		if (StringUtils.isNotBlank(md_name_like))
			entity.getMap().put("md_name_like", md_name_like);

		PeProdUser user_id = super.getSessionUserInfo(request);

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;// 专卖店人员
		Boolean role_id_btw_300_400 = false;// 专卖店分公司人员

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)
						|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
			}
		}

		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);
		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (role_id_btw_300_400) {
			entity.setDept_id(getKonkaDeptForFgs(user_id.getDept_id()).getDept_id());
		}
		request.setAttribute("basePdClazzList", getBasePdClazzList());

		Long recordCount = super.getFacade().getKonkaXxPdService().getKonkaXxPdWithUserCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<KonkaXxPd> entityList = super.getFacade().getKonkaXxPdService().getKonkaXxWithUsersPdPaginatedList(entity);

		if (entityList.size() > 0) {
			for (KonkaXxPd temp : entityList) {
				if (null != temp.getFac_sn() && null != temp.getStore_sn() && null != temp.getStore_sn())
					temp.getMap().put("fac_store_name",
							temp.getFac_desc() + "[" + temp.getFac_sn() + "," + temp.getStore_sn() + "]");
			}
		}
		if (StringUtils.isNotBlank(allToExcel) && "ebiz".equals(allToExcel)) {
			entity.getRow().setCount(recordCount.intValue());
			List<KonkaXxPd> allExcelList = getFacade().getKonkaXxPdService().getKonkaXxWithUsersPdPaginatedList(entity);

			if (allExcelList.size() > 0) {
				for (KonkaXxPd temp1 : allExcelList) {
					if (null != temp1.getFac_sn() && null != temp1.getStore_sn() && null != temp1.getStore_sn())
						temp1.getMap().put("fac_store_name",
								temp1.getFac_desc() + "[" + temp1.getFac_sn() + "," + temp1.getStore_sn() + "]");
				}
			}

			request.setAttribute("allExcelList", allExcelList);
		} else {
			request.setAttribute("allExcelList", entityList);
		}
		request.setAttribute("entityList", entityList);
		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		PeProdUser user_id = super.getSessionUserInfo(request);
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;// 专卖店人员
		Boolean role_id_btw_300_400 = false;// 专卖店分公司人员

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
		if (role_id_btw_300_400 && kDept != null) {
			request.setAttribute("dept_id", kDept.getDept_id());
			request.setAttribute("dept_name", kDept.getDept_name());
		}

		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pdModel);
		request.setAttribute("konkaXxStdPdList", pePdModelList);
		request.setAttribute("konkaDeptList", super.getKonkaDepts());
		

		// 工厂、仓位
		KonkaR3DeptStore s = new KonkaR3DeptStore();
		if(kDept != null){
			s.setDept_name(kDept.getDept_name());
		}
		List<KonkaR3DeptStore> sList = super.getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStoreList(s);

		List<HashMap<String, String>> storeList = new ArrayList<HashMap<String, String>>();
		if (sList.size() > 0) {
			for (KonkaR3DeptStore st : sList) {
				HashMap<String, String> maps = new HashMap<String, String>();
				maps.put("fac_store", st.getFac_sn() + "##" + st.getStore_sn() + "##" + st.getStore_desc());
				maps.put("store_desc", st.getStore_desc() + "[" + st.getFac_sn() + "," + st.getStore_sn() + "]");
				storeList.add(maps);
			}

		}
		request.setAttribute("storeList", storeList);

		// request.setAttribute("basePdClazzList", getBasePdClazzList());
		return mapping.findForward("input");
	}
	
	public ActionForward add_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		return new ActionForward("/zmd/KonkaXxPd/form_excel.jsp");
	}
	
	public ActionForward save_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser user_id = super.getSessionUserInfo(request);
		
		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;// 专卖店人员
		Boolean role_id_btw_300_400 = false;// 专卖店分公司人员

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
//		if (role_id_btw_300_400 && kDept != null) {
//			request.setAttribute("dept_id", kDept.getDept_id());
//			request.setAttribute("dept_name", kDept.getDept_name());
//		}

		List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		if (uploadFilesList.size() == 1) {
			String file_save_path = uploadFilesList.get(0).getFileSavePath();
			String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
			// 附件保存路径更改遗留问题
			if (StringUtils.contains(file_save_path, MmtFilePathConfig.S_OTHERS_PATH)) {
				ctxDir = "/Attachment_new/konka-files/";
			}

			String end = file_save_path.substring(file_save_path.indexOf(".")+1, file_save_path.length());
			//System.out.println("file_save_path="+file_save_path+"    ctxDir="+ctxDir + "      end==="+end);
			try {
				Workbook workbook = Workbook.getWorkbook(new File(ctxDir + file_save_path));
				Sheet sheet = workbook.getSheet(0);
				int rows = sheet.getRows();
				if (rows <= 1) {
					super.renderJavaScript(response, "window.onload=function(){alert('Excel文件内容为空！');history.back();}");
					return null;
				}
				int start_row = 1;
				int end_row = rows;
				List<KonkaXxPd> entityList = new ArrayList<KonkaXxPd>();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				for (int i = start_row; i < end_row; i++) {
					BigDecimal price = new BigDecimal("0");
					String dept_name = "";
					KonkaXxPd entity = new KonkaXxPd();
					entity.setAdd_user_id(user_id.getId());
					entity.setPd_cls_name("LED");
					entity.setPd_cls(1010100l);
					//-------分公司名称----------------------------------------
					if (StringUtils.isNotBlank(sheet.getCell(1, i).getContents())) {//分公司名称
						if (sheet.getCell(1, i).getContents().length() > 30) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，分公司名称长度大于30！');history.back();}");
							return null;
						} else {
							KonkaDept dept = new KonkaDept();
							dept.setDept_name(sheet.getCell(1, i).getContents());
							dept.setDept_type(3);//分公司
							dept.setPar_id(0L);
							List<KonkaDept> listDept = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
							if(listDept!=null && listDept.size()==1){
								if(listDept.get(0).getDept_name().equals(sheet.getCell(1, i).getContents())){
									if (role_id_btw_300_400 && kDept != null) {//说明是分公司
										if(!sheet.getCell(1, i).getContents().equals(kDept.getDept_name())){//分公司名称不相等，则不能上传
											super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
													+ "行，分公司不能上传自己分公司以外的数据！');history.back();}");
											return null;
										}
									}
									entity.setDept_id(listDept.get(0).getDept_id());
									dept_name = listDept.get(0).getDept_name();
								} else {
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，分公司名称不准确或者不存在！');history.back();}");
									return null;
								}
							} else {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
										+ "行，分公司名称不准确或者不存在！');history.back();}");
								return null;
							}
							
						}
						
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，分公司名称为空！');history.back();}");
						return null;
					}
					//-------产品型号----------------------------------------
					if (StringUtils.isNotBlank(sheet.getCell(2, i).getContents())) {//产品型号
						if (sheet.getCell(2, i).getContents().length() > 30) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，型号长度大于30！');history.back();}");
							return null;
						} else {
							PePdModel pdModel = new PePdModel();
							pdModel.setIs_del(0);
							pdModel.setMd_name(sheet.getCell(2, i).getContents());
							pdModel = super.getFacade().getPePdModelService().getPePdModel(pdModel);
							if(pdModel != null){
								entity.setMd_name(sheet.getCell(2, i).getContents());
							} else {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
										+ "行，产品型号不存在！');history.back();}");
								return null;
							}
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，产品型号为空！');history.back();}");
						return null;
					}
					//-------工厂仓位----------------------------------------
					if (StringUtils.isNotBlank(sheet.getCell(3, i).getContents())) {//工厂仓位
						String fac_store_name = sheet.getCell(3, i).getContents();
						if (fac_store_name.length() > 30) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
							+ "行，工厂仓位长度大于30！');history.back();}");
							return null;	
						} else {
							//System.out.println("fac_store_name="+fac_store_name);
							// 工厂
							String fac_sn = fac_store_name.substring(fac_store_name.indexOf("[")+1,fac_store_name.indexOf(","));
							// 仓位
							String store_sn = fac_store_name.substring(fac_store_name.indexOf(",")+1,fac_store_name.indexOf("]"));
							// 工厂描述
							String fac_desc = fac_store_name.substring(0,fac_store_name.indexOf("["));
							
							//System.out.println("工厂    fac_sn== "+fac_sn+ "   仓位 store_sn== "+store_sn + "    fac_desc="+fac_desc);
							KonkaR3DeptStore konkaR3DeptStore = new KonkaR3DeptStore();
							konkaR3DeptStore.setDept_name(dept_name);
							konkaR3DeptStore.setFac_sn(fac_sn);
							konkaR3DeptStore.setStore_sn(store_sn);
							konkaR3DeptStore = super.getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStore(konkaR3DeptStore);
							if(konkaR3DeptStore != null){
								entity.setFac_sn(fac_sn);
								entity.setStore_sn(store_sn);
								entity.setFac_desc(konkaR3DeptStore.getStore_desc());
							} else {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，工厂仓位不存在！');history.back();}");
								return null;
							}
							
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，工厂仓位为空！');history.back();}");
						return null;
					}
					//-------上架时间----------------------------------------
					if (StringUtils.isNotBlank(sheet.getCell(4, i).getContents())) {
						if (sheet.getCell(4, i).getContents().length() > 10) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，上架时间长度大于10！');history.back();}");
							return null;
						} else {
							Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
							Matcher m = p.matcher(sheet.getCell(4, i).getContents());
							if(!m.matches()){
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
										+ "行，上架时间格式不正确！');history.back();}");
								return null;
							} else {
								String[] array = sheet.getCell(4, i).getContents().split("-");
								int year = Integer.valueOf(array[0]);
								int month = Integer.valueOf(array[1]);
								int day = Integer.valueOf(array[2]);
								
								if(month<1 || month>12){
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，上架时间不正确！');history.back();}");
									return null;
								}
								int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
								if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {//判断是否是闰年
									monthLengths[2] = 29;
								}else{
									monthLengths[2] = 28;
								}
								int monthLength = monthLengths[month];
								if(day<1 || day>monthLength){
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，上架时间不正确！');history.back();}");
									return null;
								}
								DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
								entity.setUp_time(format.parse(sheet.getCell(4, i).getContents()));
							}
						}
					} else {
//						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，上架时间为空！');history.back();}");
//						return null;
					}
					//-------下架时间----------------------------------------
					if (StringUtils.isNotBlank(sheet.getCell(5, i).getContents())) {
						if (sheet.getCell(5, i).getContents().length() > 10) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，下架时间长度大于10！');history.back();}");
							return null;
						} else {
							Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
							Matcher m = p.matcher(sheet.getCell(5, i).getContents());
							if(!m.matches()){
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
										+ "行，下架时间格式不正确！');history.back();}");
								return null;
							} else {
								String[] array = sheet.getCell(5, i).getContents().split("-");
								int year = Integer.valueOf(array[0]);
								int month = Integer.valueOf(array[1]);
								int day = Integer.valueOf(array[2]);
								
								if(month<1 || month>12){
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，下架时间不正确！');history.back();}");
									return null;
								}
								int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
								if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {//判断是否是闰年
									monthLengths[2] = 29;
								}else{
									monthLengths[2] = 28;
								}
								int monthLength = monthLengths[month];
								if(day<1 || day>monthLength){
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，下架时间不正确！');history.back();}");
									return null;
								}
								DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
								entity.setDown_time(format.parse(sheet.getCell(5, i).getContents()));
							}
						}
					} else {
//						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，上架时间为空！');history.back();}");
//						return null;
					}
					//-------零售参考价----------------------------------------
					if (StringUtils.isNotBlank(sheet.getCell(6, i).getContents())) {
						if (sheet.getCell(6, i).getContents().length() > 9) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，零售参考价长度大于9！');history.back();}");
							return null;
						} else {
							String regexp = "^[0-9]{1,6}+(.[0-9]{1,2})?$";//6位整数 2为小数
							if(sheet.getCell(6, i).getContents().matches(regexp)){
								price = new BigDecimal(sheet.getCell(6, i).getContents());
								entity.setPrice_ref(new BigDecimal(sheet.getCell(6, i).getContents()));
							} else {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，零售参考价格格式为6位整数和2为小数！');history.back();}");
								return null;
							}
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，零售参考价为空！');history.back();}");
						return null;
					}
					//-------零售价格下限----------------------------------------
					if (StringUtils.isNotBlank(sheet.getCell(7, i).getContents())) {
						if (sheet.getCell(7, i).getContents().length() > 9) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，零售价格下限大于9！');history.back();}");
							return null;
						} else {
							String regexp = "^[0-9]{1,6}+(.[0-9]{1,2})?$";//6位整数 2为小数
							if(sheet.getCell(7, i).getContents().matches(regexp)){
								if(price.compareTo(new BigDecimal(sheet.getCell(7, i).getContents())) == -1){
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，零售价格下限大于零售参考价格！');history.back();}");
									return null;
								}
								entity.setPrice_min(new BigDecimal(sheet.getCell(7, i).getContents()));
							} else {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，零售价格下限格式为6位整数和2为小数！');history.back();}");
								return null;
							}
						}
					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，零售价格下限为空！');history.back();}");
						return null;
					}
					//-------备注----------------------------------------
					if (StringUtils.isNotBlank(sheet.getCell(8, i).getContents())) {
						if (sheet.getCell(8, i).getContents().length() > 200) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，备注长度大于200！');history.back();}");
							return null;
						} else {
							entity.setRemarks(sheet.getCell(8, i).getContents());
						}
					} else {
//						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1) + "行，备注为空！');history.back();}");
//						return null;
					}
					
					// 判断同一分公司、同一商品型号、同一工厂仓位是否唯一
					KonkaXxPd konkaXxPd = new KonkaXxPd();
					konkaXxPd.setMd_name(entity.getMd_name());
					konkaXxPd.setDept_id(entity.getDept_id());
					konkaXxPd.setFac_sn(entity.getFac_sn());
					konkaXxPd.setStore_sn(entity.getStore_sn());
					List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
					if (null != konkaXxPdList && konkaXxPdList.size() > 0) {
						String msg = super.getMessage(request, "konka.zmd.pd.exist");
						super.renderJavaScript(response, "window.onload=function(){alert('第"+(i+1)+"行，" + msg + "');history.back();}");
						return null;
					}
					
					entityList.add(entity);
				}

				// 插入数据库
				super.getFacade().getKonkaXxPdService().createKonkaXxPdForExcel(entityList);
				saveMessage(request, "entity.inserted");
				// the line below is added for pagination
				StringBuffer pathBuffer = new StringBuffer();
//				pathBuffer.append(mapping.findForward("success").getPath());
				pathBuffer.append("/../manager/zmd/KonkaXxPd.do");
				pathBuffer.append("?mod_id=" + mod_id);
				// pathBuffer.append("&year=" + year);
				// pathBuffer.append("&month=" + month);
				// pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
				// end
				return forward;

			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('Excel文件未选择！');history.back();}");
			return null;
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String pd_cls = (String) dynaBean.get("pd_cls");
		String price_min = (String) dynaBean.get("price_min");
		String dept_id = (String) dynaBean.get("dept_id");
		String md_name = (String) dynaBean.get("md_name");
		String price_ref = (String) dynaBean.get("price_ref");
		String remarks = (String) dynaBean.get("remarks");
		String dept_pd_id = (String) dynaBean.get("dept_pd_id");
		String up_time = (String) dynaBean.get("up_time");
		String down_time = (String) dynaBean.get("down_time");
		String fac_store_name = (String) dynaBean.get("fac_store_name");
		PeProdUser userInfo = super.getSessionUserInfo(request);

		// 工厂
		String fac_sn = fac_store_name.split("##")[0];
		// 仓位
		String store_sn = fac_store_name.split("##")[1];
		// 工厂描述
		String fac_desc = fac_store_name.split("##")[2];
		//System.out.println("fac_sn=="+fac_sn+"     store_sn"+store_sn+"   fac_desc"+fac_desc);
		KonkaXxPd entity = new KonkaXxPd();
		entity.setPd_cls_name("LED");
		entity.setPd_cls(Long.valueOf(pd_cls));

		// 工厂仓位
		entity.setFac_sn(fac_sn);
		entity.setStore_sn(store_sn);
		entity.setFac_desc(fac_desc);

		Long dept_id1 = null;
		if (StringUtils.isNotBlank(dept_id)) {
			dept_id1 = Long.valueOf(dept_id);
		} else {
			dept_id1 = getKonkaDeptForFgs(userInfo.getDept_id()).getDept_id();
		}

		entity.setDept_id(dept_id1);
		if (StringUtils.isNotBlank(price_ref)) {
			entity.setPrice_ref(new BigDecimal(price_ref));
		}
		if (StringUtils.isNotBlank(price_min)) {
			entity.setPrice_min(new BigDecimal(price_min));
		}

		if (StringUtils.isNotBlank(md_name)) {
			entity.setMd_name(md_name);
		}
		if (StringUtils.isNotBlank(remarks)) {
			entity.setRemarks(remarks);
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(up_time)) {
			entity.setUp_time(format.parse(up_time));
		}
		if (StringUtils.isNotBlank(down_time)) {
			entity.setDown_time(format.parse(down_time));
		}
		entity.setAdd_user_id(userInfo.getId());

		// 判断同一分公司、同一商品型号、同一工厂仓位是否唯一
		KonkaXxPd konkaXxPd = new KonkaXxPd();
		konkaXxPd.setMd_name(md_name);
		konkaXxPd.setDept_id(dept_id1);
		konkaXxPd.setFac_sn(fac_sn);
		konkaXxPd.setStore_sn(store_sn);
		konkaXxPd.getMap().put("not_dept_pd_id", dept_pd_id);
		List<KonkaXxPd> konkaXxPdList = super.getFacade().getKonkaXxPdService().getKonkaXxPdList(konkaXxPd);
		if (null != konkaXxPdList && konkaXxPdList.size() > 0) {
			String msg = super.getMessage(request, "konka.zmd.pd.exist");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}

		if (!GenericValidator.isLong(dept_pd_id)) {
			super.getFacade().getKonkaXxPdService().createKonkaXxPd(entity);
			saveMessage(request, "entity.inserted");
		} else {
			KonkaXxPd konkaXxPd1 = new KonkaXxPd();
			konkaXxPd1.setDept_pd_id(Long.valueOf(dept_pd_id));
			konkaXxPd1.setFac_sn(fac_sn);
			konkaXxPd1.setStore_sn(store_sn);
			konkaXxPd1 = super.getFacade().getKonkaXxPdService().getKonkaXxPd(konkaXxPd1);
			if(konkaXxPd1 != null && konkaXxPd1.getAudit_state()==1){//审核通过的情况下
				if (null != konkaXxPd1 && konkaXxPd1.getPrice_min().equals(new BigDecimal(price_min))) {// 价格下限不变
					entity.setDept_pd_id(Long.valueOf(dept_pd_id));
					super.getFacade().getKonkaXxPdService().modifyKonkaXxPdForHistory(entity);
					saveMessage(request, "entity.updated");
				} else {// 价格下限改变
					entity.setAudit_state(0);
					entity.setDept_pd_id(Long.valueOf(dept_pd_id));
					super.getFacade().getKonkaXxPdService().modifyKonkaXxPd(entity);
					saveMessage(request, "konka.xx.zmd.message.pd.change");
				}
			} else if(konkaXxPd1 != null && konkaXxPd1.getAudit_state()==2){//审核不通过的情况下
				entity.setAudit_state(0);
				entity.setDept_pd_id(Long.valueOf(dept_pd_id));
				super.getFacade().getKonkaXxPdService().modifyKonkaXxPd(entity);
				saveMessage(request, "konka.xx.zmd.message.pd.change");
			}
		}

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dept_pd_id = (String) dynaBean.get("dept_pd_id");
		request.setAttribute("konkaDeptList", super.getKonkaDepts());

		PeProdUser user_id = super.getSessionUserInfo(request);

		KonkaDept kDept = getKonkaDeptForFgs(user_id.getDept_id());
		// 取用户角色
		// PeRoleUser peRoleUser = super.getRoleInfoByThisLogin(request);

		List<PeRoleUser> peRoleUserList = getPeRoleList(user_id.getId());

		Boolean role_id_gt_400 = false;// 专卖店人员
		Boolean role_id_btw_300_400 = false;// 专卖店分公司人员

		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (temp.getRole_id() <= 400) {
					role_id_gt_400 = true;
				}
				if ((temp.getRole_id() >= 300 && temp.getRole_id() < 400)|| (temp.getRole_id() >= 30 && temp.getRole_id() < 40)) {
					role_id_btw_300_400 = true;
				}
			}
		}

		if (!role_id_gt_400) {
			String msg = super.getMessage(request, "konka.xx.zmd.role.error");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		}
		if (role_id_btw_300_400 && kDept != null) {
			request.setAttribute("dept_id", kDept.getDept_id());
			request.setAttribute("dept_name", kDept.getDept_name());
		}

		PePdModel pdModel = new PePdModel();
		pdModel.setIs_del(0);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pdModel);

		request.setAttribute("konkaXxStdPdList", pePdModelList);

		if (GenericValidator.isLong(dept_pd_id)) {
			KonkaXxPd entity = new KonkaXxPd();
			entity.setDept_pd_id(Long.valueOf(dept_pd_id));

			entity = getFacade().getKonkaXxPdService().getKonkaXxPd(entity);
			if (null == entity) {
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			} else {

				// 工厂、仓位
				KonkaR3DeptStore s = new KonkaR3DeptStore();
				if(kDept != null){
					s.setDept_name(kDept.getDept_name());
				}
				List<KonkaR3DeptStore> sList = super.getFacade().getKonkaR3DeptStoreService()
						.getKonkaR3DeptStoreList(s);

				List<HashMap<String, String>> storeList = new ArrayList<HashMap<String, String>>();
				if (sList.size() > 0) {
					for (KonkaR3DeptStore st : sList) {
						HashMap<String, String> maps = new HashMap<String, String>();
						maps.put("fac_store", st.getFac_sn() + "##" + st.getStore_sn() + "##" + st.getStore_desc());
						maps
								.put("store_desc", st.getStore_desc() + "[" + st.getFac_sn() + "," + st.getStore_sn()
										+ "]");
						storeList.add(maps);
					}

				}
				request.setAttribute("storeList", storeList);

				entity.setQueryString(super.serialize(request, "id", "method"));
				super.copyProperties(form, entity);

				// 回显仓库信息
				if (null != entity.getFac_sn() && null != entity.getStore_sn() && null != entity.getStore_sn())
					request.setAttribute("fac_store_name", entity.getFac_sn() + "##" + entity.getStore_sn() + "##"
							+ entity.getFac_desc());

				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				if (null != entity.getUp_time()) {
					entity.getMap().put("up_time", format.format(entity.getUp_time()));
				}
				if (null != entity.getDown_time()) {
					entity.getMap().put("down_time", format.format(entity.getDown_time()));
				}
				request.setAttribute("md_name", entity.getMd_name());
				return mapping.findForward("input");
			}

		} else {
			saveError(request, "errors.long", dept_pd_id);
			return mapping.findForward("list");
		}
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dept_pd_id = (String) dynaBean.get("dept_pd_id");
		String dept_id = (String) dynaBean.get("dept_id");

		if (!GenericValidator.isLong(dept_pd_id)) {
			this.saveError(request, "errors.long", new String[] { dept_pd_id });
			return mapping.findForward("list");
		}

		KonkaXxPd entity = new KonkaXxPd();
		entity.setDept_pd_id(Long.valueOf(dept_pd_id));
		entity = super.getFacade().getKonkaXxPdService().getKonkaXxPd(entity);
		if (null == entity) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(Long.valueOf(dept_id));
		List<KonkaDept> konkaDeptsList = super.getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		if (null != konkaDeptsList && konkaDeptsList.size() > 0) {
			request.setAttribute("dept_name", konkaDeptsList.get(0).getDept_name());
		}

		super.copyProperties(form, entity);

		// 回显仓库信息
		if (null != entity.getFac_sn() && null != entity.getStore_sn() && null != entity.getStore_sn())
			request.setAttribute("fac_store_name", entity.getFac_desc() + "[" + entity.getFac_sn() + ","
					+ entity.getStore_sn() + "]");

		return mapping.findForward("view");
	}

	public ActionForward historyList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		super.getModPopeDom(form, request);
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		Pager pager = (Pager) dynaBean.get("pager");
		String dept_pd_id = (String) dynaBean.get("dept_pd_id");
		String md_name = (String) dynaBean.get("md_name");
		KonkaXxPdHistory entity = new KonkaXxPdHistory();

		if (StringUtils.isNotBlank(dept_pd_id)) {
			entity.setDept_pd_id(Long.valueOf(dept_pd_id));
		}

		Long recordCount = super.getFacade().getKonkaXxPdHistoryService().getKonkaXxPdHistoryCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaXxPdHistory> entityList = super.getFacade().getKonkaXxPdHistoryService()
				.getKonkaXxPdHistoryPaginatedList(entity);

		if (entityList.size() > 0) {
			for (KonkaXxPdHistory temp : entityList) {
				if (null != temp.getFac_sn() && null != temp.getStore_sn() && null != temp.getStore_sn())
					temp.getMap().put("fac_store_name",
							temp.getFac_desc() + "[" + temp.getFac_sn() + "," + temp.getStore_sn() + "]");
			}
		}

		request.setAttribute("entityList", entityList);

		request.setAttribute("md_name", md_name);

		return new ActionForward("/../manager/zmd/KonkaXxPd/historyList.jsp");
		// return mapping.findForward("list");
	}

	public ActionForward returnHistoryPd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		String mod_id = (String) dynaBean.get("mod_id");
		String history_id = (String) dynaBean.get("history_id");
		KonkaXxPdHistory entity = new KonkaXxPdHistory();

		if (StringUtils.isNotBlank(history_id)) {
			entity.setHistory_id(Long.valueOf(history_id));
		}
		entity = super.getFacade().getKonkaXxPdHistoryService().getKonkaXxPdHistory(entity);

		KonkaXxPd konkaXxPd = new KonkaXxPd();
		konkaXxPd.setAdd_date(entity.getAdd_date());
		konkaXxPd.setAdd_user_id(entity.getAdd_user_id());
		konkaXxPd.setDept_id(entity.getDept_id());
		konkaXxPd.setDept_pd_id(entity.getDept_pd_id());
		konkaXxPd.setDown_time(entity.getDown_time());
		konkaXxPd.setFix_fee(entity.getFix_fee());
		konkaXxPd.setMd_name(entity.getMd_name());
		konkaXxPd.setPd_cls(entity.getPd_cls());
		konkaXxPd.setPd_cls_name(entity.getPd_cls_name());
		konkaXxPd.setPrice_max(entity.getPrice_max());
		konkaXxPd.setPrice_min(entity.getPrice_min());
		konkaXxPd.setPrice_ref(entity.getPrice_ref());
		konkaXxPd.setRemarks(entity.getRemarks());
		konkaXxPd.setUp_time(entity.getUp_time());

		super.getFacade().getKonkaXxPdService().modifyKonkaXxPd(konkaXxPd);

		saveMessage(request, "entity.updated");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		return forward;
	}
	//导出方法
	@Override
	public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		response.setCharacterEncoding(Constants.SYS_ENCODING);
		String name = request.getParameter("hiddenName");
		String html = request.getParameter("hiddenHtml");

		// 不判断是否为空，下面的 replaceAll方法报错
		if (StringUtils.isBlank(html)) {
			this.renderHtml(response, "no html content to export.");
			return null;
		}

		html = html.replaceAll("&lt;", "<");
		html = html.replaceAll("&gt;", ">");
		try {
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=\""
			        + URLEncoder.encode(name, Constants.SYS_ENCODING) + ".xls\"");

			PrintWriter out = response.getWriter();
			out
			        .println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
			out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
			out.println("<meta http-equiv=\"MSThemeCompatible\" content=\"no\" />");
			out.println("<meta name=\"MSSmartTagsPreventParsing\" content=\"true\" />");
			out.println("<title>" + name + "</title>");
			out.println("</head>");
			html = html.replace("border=0", "border=1");
			html = StringUtils.replace(html, "border=\"0\"", "border=\"1\"");
			// html = html.replace("<A href=\"[^>]*?\">([^<]*?)<\/A>", "$1");
			out.println(html);

			out.println("</body>");
			out.println("</html>");
			out.println("<body>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
