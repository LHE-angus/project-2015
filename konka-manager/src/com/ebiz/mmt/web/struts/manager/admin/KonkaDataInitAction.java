package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ebiz.mmt.domain.KonkaMobileImpDept;
import com.ebiz.mmt.domain.KonkaMobileImpHis;
import com.ebiz.mmt.domain.KonkaMobileImpStore;
import com.ebiz.mmt.domain.KonkaMobileImpUser;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Jiang,JiaYong
 * @datetime 2013-05-23
 */
public class KonkaDataInitAction extends BaseAction {
	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}
		setNaviStringToRequestScope(form, request);
		KonkaDept dept = new KonkaDept();
		dept.setDept_type(3);
		List<KonkaDept> deptList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
		request.setAttribute("entityList", deptList);

		return mapping.findForward("list");
	}

	public ActionForward dataDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		if (!GenericValidator.isLong(dept_id)) {
			this.saveError(request, "errors.long", new String[] { dept_id });
			return mapping.findForward("list");
		}

		// 分公司信息
		KonkaDept dept = new KonkaDept();
		dept.setDept_id(Long.valueOf(dept_id));
		dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);

		// 判断是否导入部门信息
		KonkaMobileImpDept kmid = new KonkaMobileImpDept();
		kmid.setFgs_sn(dept.getDept_sn());
		Long kmid_count = super.getFacade().getKonkaMobileImpDeptService().getKonkaMobileImpDeptCount(kmid);
		dept.getMap().put("kmid_count", kmid_count);

		// 判断是否导入门店信息
		KonkaMobileImpStore kmis = new KonkaMobileImpStore();
		kmis.setFgs_sn(dept.getDept_sn());
		Long kmis_count = super.getFacade().getKonkaMobileImpStoreService().getKonkaMobileImpStoreCount(kmis);
		dept.getMap().put("kmis_count", kmis_count);

		// 判断是否导入人员信息
		KonkaMobileImpUser kmiu = new KonkaMobileImpUser();
		kmiu.setFgs_sn(dept.getDept_sn());
		Long kmiu_count = super.getFacade().getKonkaMobileImpUserService().getKonkaMobileImpUserCount(kmiu);
		dept.getMap().put("kmiu_count", kmiu_count);

		request.setAttribute("entity", dept);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaDataInit/dataDetail.jsp"));
	}

	public ActionForward addBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String dept_id = (String) dynaBean.get("dept_id");
		if (!GenericValidator.isLong(dept_id)) {
			this.saveError(request, "errors.long", new String[] { dept_id });
			return mapping.findForward("list");
		}

		// 分公司信息
		KonkaDept dept = new KonkaDept();
		dept.setDept_id(Long.valueOf(dept_id));
		dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);

		request.setAttribute("entity", dept);
		return new ActionForward(response.encodeRedirectURL("/admin/KonkaDataInit/addBatch.jsp"));
	}

	public ActionForward saveBatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		String dept_id = (String) dynaBean.get("dept_id");
		String type = (String) dynaBean.get("type"); // dept-部门信息, store-门店信息, user-人员信息
		if (!GenericValidator.isLong(dept_id)) {
			this.saveError(request, "errors.long", new String[] { dept_id });
			return mapping.findForward("list");
		}

		// 分公司信息
		KonkaDept dept = new KonkaDept();
		dept.setDept_id(Long.valueOf(dept_id));
		dept = super.getFacade().getKonkaDeptService().getKonkaDept(dept);

		int size = 0;// Excel表格执行验证的记录条数
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		// List<UploadFile> uploadFiles = super.uploadFile(form, false);
		List<UploadFile> uploadFiles = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH, false);
		String ctxDir = "";
		if (uploadFiles.size() == 1) {
			List<KonkaMobileImpDept> konkaMobileImpDeptList = new ArrayList<KonkaMobileImpDept>();
			List<KonkaMobileImpUser> konkaMobileImpUserList = new ArrayList<KonkaMobileImpUser>();
			List<KonkaMobileImpStore> konkaMobileImpStoreList = new ArrayList<KonkaMobileImpStore>();

			ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));
			String file_save_path = uploadFiles.get(0).getFileSavePath();
			if (StringUtils.contains(file_save_path, MmtFilePathConfig.S_OTHERS_PATH)) {
				ctxDir = "";
				file_save_path = "/Attachment_new/konka-files/" + file_save_path;
			}

			try {
				Workbook workbook = Workbook.getWorkbook(new File(ctxDir + file_save_path));
				Sheet sheet = workbook.getSheet(0);
				if (type.equals("dept")) { // 部门判断格式可正确
					if(sheet.getRow(0).length != 9){
						 super.renderJavaScript(response, "alert('对不起，excel格式错误！');history.go(-1);");
						 return null;
					}
				}
				if (type.equals("user")) { // 人员判断格式可正确
					if(sheet.getRow(0).length != 8){
						 super.renderJavaScript(response, "alert('对不起，excel格式错误！');history.go(-1);");
						 return null;
					}
				}
				if (type.equals("store")) { // 门店判断格式可正确
					if(sheet.getRow(0).length != 25){
						 super.renderJavaScript(response, "alert('对不起，excel格式错误！');history.go(-1);");
						 return null;
					}
				}
				for (int i = 1; i < sheet.getRows(); i++) { // 从第二行开始，第一行为标题
					if (type.equals("dept")) { // 部门
						KonkaMobileImpDept konkaMobileImpDept = new KonkaMobileImpDept();
						konkaMobileImpDept.setFgs_name(sheet.getCell(1, i).getContents().trim());
						konkaMobileImpDept.setFgs_sn(sheet.getCell(2, i).getContents().trim());
						konkaMobileImpDept.setDept_name(sheet.getCell(3, i).getContents().trim());
						konkaMobileImpDept.setDept_sn(sheet.getCell(4, i).getContents().trim());
						konkaMobileImpDept.setPar_dept_sn(sheet.getCell(5, i).getContents().trim());
						konkaMobileImpDept.setArea_sn(sheet.getCell(6, i).getContents().trim());
						konkaMobileImpDept.setP_index(sheet.getCell(7, i).getContents().trim());
						konkaMobileImpDept.setDept_type(sheet.getCell(8, i).getContents().trim());
						konkaMobileImpDept.setAdd_date(new Date());

						if (StringUtils.isNotBlank(konkaMobileImpDept.getFgs_name()) && StringUtils.isNotBlank(konkaMobileImpDept.getDept_name())) {
							konkaMobileImpDeptList.add(konkaMobileImpDept);
							size++;
						} else { // 出现空白行循环结束
							break;
						}
					}
					if (type.equals("user")) { // 人员
						KonkaMobileImpUser konkaMobileImpUser = new KonkaMobileImpUser();
						konkaMobileImpUser.setUser_name(sheet.getCell(0, i).getContents().trim());
						konkaMobileImpUser.setUser_py(sheet.getCell(1, i).getContents().trim());
						konkaMobileImpUser.setJb_name(sheet.getCell(2, i).getContents().trim());
						konkaMobileImpUser.setFgs_name(sheet.getCell(3, i).getContents().trim());
						konkaMobileImpUser.setUser_id(sheet.getCell(4, i).getContents().trim());
						konkaMobileImpUser.setTel(sheet.getCell(5, i).getContents().trim());
						konkaMobileImpUser.setEmail(sheet.getCell(6, i).getContents().trim());
						konkaMobileImpUser.setJob(sheet.getCell(7, i).getContents().trim());
						konkaMobileImpUser.setAdd_date(new Date());
						konkaMobileImpUser.setFgs_sn(dept.getDept_sn());
						
						if(StringUtils.isNotBlank(konkaMobileImpUser.getUser_name()) && StringUtils.isNotBlank(konkaMobileImpUser.getFgs_name())){
							konkaMobileImpUserList.add(konkaMobileImpUser);
							size++;
						} else { // 空白行循环结束
							break;
						}
					}
					if (type.equals("store")) { // 门店
						KonkaMobileImpStore konkaMobileImpStore = new KonkaMobileImpStore();
						konkaMobileImpStore.setStore_name(sheet.getCell(1, i).getContents().trim());
						konkaMobileImpStore.setMf_sn(sheet.getCell(2, i).getContents().trim());
						konkaMobileImpStore.setZxy_name(sheet.getCell(3, i).getContents().trim());
						konkaMobileImpStore.setZxy_tel(sheet.getCell(4, i).getContents().trim());
						konkaMobileImpStore.setZxy_job_id(sheet.getCell(5, i).getContents().trim());
						konkaMobileImpStore.setYwy_name(sheet.getCell(6, i).getContents().trim());
						konkaMobileImpStore.setYwy_job_id(sheet.getCell(7, i).getContents().trim());
						konkaMobileImpStore.setJb_name(sheet.getCell(8, i).getContents().trim());
						konkaMobileImpStore.setJb_tel(sheet.getCell(9, i).getContents().trim());
						konkaMobileImpStore.setProvince(sheet.getCell(10, i).getContents().trim());
						konkaMobileImpStore.setCity(sheet.getCell(11, i).getContents().trim());
						konkaMobileImpStore.setCountry(sheet.getCell(12, i).getContents().trim());
						konkaMobileImpStore.setTown(sheet.getCell(13, i).getContents().trim());
						konkaMobileImpStore.setMd_jb(sheet.getCell(14, i).getContents().trim());
						konkaMobileImpStore.setKh_name(sheet.getCell(15, i).getContents().trim());
						konkaMobileImpStore.setR3_shdf_sn(sheet.getCell(16, i).getContents().trim());
						konkaMobileImpStore.setR3_sdf_sn(sheet.getCell(17, i).getContents().trim());
						konkaMobileImpStore.setXszz(sheet.getCell(18, i).getContents().trim());
						konkaMobileImpStore.setYwzg_job_id(sheet.getCell(19, i).getContents().trim());
						konkaMobileImpStore.setYwzg_name(sheet.getCell(20, i).getContents().trim());
						konkaMobileImpStore.setYwzg_tel(sheet.getCell(21, i).getContents().trim());
						konkaMobileImpStore.setJbjl_job_id(sheet.getCell(22, i).getContents().trim());
						konkaMobileImpStore.setJbjl_name(sheet.getCell(23, i).getContents().trim());
						konkaMobileImpStore.setJbjl_tel(sheet.getCell(24, i).getContents().trim());
						konkaMobileImpStore.setAdd_date(new Date());
						konkaMobileImpStore.setFgs_sn(dept.getDept_sn());
						konkaMobileImpStore.setFgs_name(dept.getDept_name());

						if(StringUtils.isNotBlank(konkaMobileImpStore.getStore_name()) && StringUtils.isNotBlank(konkaMobileImpStore.getJb_name())){
							konkaMobileImpStoreList.add(konkaMobileImpStore);
							size++;
						}else { // 空白行循环结束
							break;
						}
					}

					/*
					 * 判断R3编码是否为空
					 */
					// if (!StringUtils.isNotBlank(r3_code)) {
					// msg += "请输入第" + (i + 1) + "行的R3编码！";
					// super.renderJavaScript(response, "alert('" + msg + "');history.go(-1);");
					// return null;
					// } else {
					// r3Shop.setR3_code(sheet.getCell(3, i).getContents());// R3编码
					// }
				}
				workbook.close();
				
				// 操作记录
				KonkaMobileImpHis kmih = new KonkaMobileImpHis();
				kmih.setOpr_user_id(ui.getId());
				kmih.setAdd_date(new Date());
				kmih.setDept_id(dept.getDept_id());
				kmih.setDept_name(dept.getDept_name());
				kmih.setIs_del(0);
				
				if (type.equals("dept")) { // 部门
					kmih.setOpr_title(dept.getDept_name() + "分公司部门信息");
					kmih.setOpr_obj(2);
					
					super.getFacade().getKonkaMobileImpDeptService().createKonkaMobileImpDeptListAndHis(konkaMobileImpDeptList, kmih);
				}
				if (type.equals("user")) { // 人员
					kmih.setOpr_title(dept.getDept_name() + "分公司人员信息");
					kmih.setOpr_obj(3);
					
					super.getFacade().getKonkaMobileImpUserService().createKonkaMobileImpUserListAndHis(konkaMobileImpUserList, kmih);
				}
				if (type.equals("store")) { // 门店
					kmih.setOpr_title(dept.getDept_name() + "分公司门店信息");
					kmih.setOpr_obj(1);
					
					super.getFacade().getKonkaMobileImpStoreService().createKonkaMobileImpStoreListAndHis(konkaMobileImpStoreList, kmih);
				}
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			super.renderJavaScript(response, "alert('" + "Excel为空" + "');history.go(-1);");
			return null;
		}

		String msg = "恭喜你，成功导入" + size + "条信息。";
		super.renderJavaScript(response, "alert('" + msg + "');location.href='KonkaDataInit.do?method=dataDetail&mod_id=" + mod_id + "&dept_id=" + dept_id + "';");
		return null;
	}
}