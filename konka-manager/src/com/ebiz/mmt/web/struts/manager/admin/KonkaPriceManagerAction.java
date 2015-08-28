package com.ebiz.mmt.web.struts.manager.admin;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaPriceManager;
import com.ebiz.mmt.domain.KonkaR3DeptStore;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaPriceManagerAction extends BaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		if (null == peProdUser) {
			String msg = super.getMessage(request, "user.session.isEmpty");
			super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
			return null;
		} else if (peProdUser.getUser_type() == 0) {
			// 康佳总部
			dynaBean.set("dept_type", "1");
		} else if (peProdUser.getUser_type() == 1) {
			// 康佳分公司
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getDept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (null == konkaDept) {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else if (konkaDept.getDept_type() == 3) {
				// 分公司
				dynaBean.set("fgs_sn", konkaDept.getDept_sn());
				dynaBean.set("dept_type", "3");
			} else if (konkaDept.getDept_type() == 4 || konkaDept.getDept_type() == 5) {
				// 经营处、办事处
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			} else {
				String msg = super.getMessage(request, "popedom.check.invalid");
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}
		}
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");
		String goods_name = (String) dynaBean.get("goods_name");
		String store_sn = (String) dynaBean.get("store_sn");

		KonkaPriceManager entity = new KonkaPriceManager();
		if (StringUtils.isNotBlank(goods_name)) {
			entity.getMap().put("goods_name_like", goods_name);
		}
		if (StringUtils.isNotBlank(store_sn)) {
			entity.setStore_sn(store_sn);
		}

		Long recordCount = super.getFacade().getKonkaPriceManagerService().getKonkaPriceManagerCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<KonkaPriceManager> entityList = super.getFacade().getKonkaPriceManagerService()
				.getKonkaPriceManagerPaginatedList(entity);
		request.setAttribute("entityList", entityList);

		return mapping.findForward("list");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		return mapping.findForward("input");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "2")) {
			return super.checkPopedomInvalid(request, response);
		}
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");

		KonkaPriceManager entity = new KonkaPriceManager();
		if (StringUtils.isNotBlank(id)) {
			entity.setId(new Long(id));
		}
		entity = super.getFacade().getKonkaPriceManagerService().getKonkaPriceManager(entity);
		super.copyProperties(form, entity);
		return mapping.findForward("input");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		// 分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			// kd.setDept_id(0L);
		} else {
			kd.setDept_id(peProdUser.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		}
		String dept_name = "";
		if (kd != null && StringUtils.isNotEmpty(kd.getDept_name())) {
			dept_name = kd.getDept_name();
		}

		KonkaPriceManager entity = new KonkaPriceManager();
		super.copyProperties(entity, form);

		if (StringUtils.isNotBlank(entity.getStore_sn())) {
			KonkaR3DeptStore t = new KonkaR3DeptStore();
			t.setStore_sn(entity.getStore_sn());
			t.setIs_del(0);
			if (!dept_name.equals("")) {
				t.setDept_name(dept_name);
			}
			List<KonkaR3DeptStore> tList = super.getFacade().getKonkaR3DeptStoreService().getKonkaR3DeptStoreList(t);
			if (tList != null && tList.size() > 0) {
				entity.setStore_name(tList.get(0).getStore_desc());
			} else {
				super.renderJavaScript(response, "window.onload=function(){alert('仓位不存在！');history.back();}");
				return null;
			}
		}

		// 判断时间有没有重复
		KonkaPriceManager entity1 = new KonkaPriceManager();
		entity1.setGoods_name(entity.getGoods_name());
		entity1.setGoods_sn(entity.getGoods_sn());
		entity1.setStore_name(entity.getStore_name());
		entity1.setStore_sn(entity.getStore_sn());
		List<KonkaPriceManager> list1 = super.getFacade().getKonkaPriceManagerService()
				.getKonkaPriceManagerList(entity1);
		if (list1 != null && list1.size() > 0) {
			boolean flag = false;
			for (KonkaPriceManager pm : list1) {
				if (entity.getStart_date().getTime() > pm.getEnd_date().getTime()
						|| entity.getEnd_date().getTime() < pm.getStart_date().getTime()) {// 添加的最大时间小于之前的最小时间，或者添加的最小时间大于之前的最大时间
				} else {
					flag = true;
					break;
				}
			}
			if (flag) {
				super.renderJavaScript(response, "alert('型号，库存地点相同的情况下，时间不能有重复！');history.back();");
				return null;
			}
		}

		if (null == entity.getId()) {
			if (null == super.checkUserModPopeDom(form, request, "1")) {
				return super.checkPopedomInvalid(request, response);
			}

			super.getFacade().getKonkaPriceManagerService().createKonkaPriceManager(entity);
			saveMessage(request, "entity.inserted");
		} else {
			// update
			if (null == super.checkUserModPopeDom(form, request, "2")) {
				return super.checkPopedomInvalid(request, response);
			}
			super.getFacade().getKonkaPriceManagerService().modifyKonkaPriceManager(entity);
			saveMessage(request, "entity.updated");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(entity.getQueryString()));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;
	}

	public ActionForward add_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		saveToken(request);
		setNaviStringToRequestScope(form, request);
		if (null == super.checkUserModPopeDom(form, request, "1")) {
			return super.checkPopedomInvalid(request, response);
		}

		return new ActionForward("/admin/KonkaPriceManager/form_excel.jsp");
	}

	public ActionForward save_excel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		int max_dlevel = super.getMaxDLevel(peProdUser.getId()); // 获取当前用户的最高可视部门级别
		// 分公司
		KonkaDept kd = new KonkaDept();
		if (max_dlevel == 9) {
			// kd.setDept_id(0L);
		} else {
			kd.setDept_id(peProdUser.getDept_id());
			kd = super.getFacade().getKonkaDeptService().getKonkaDept(kd);
		}
		String dept_name = "";
		if (kd != null && StringUtils.isNotEmpty(kd.getDept_name())) {
			dept_name = kd.getDept_name();
		}

		List<UploadFile> uploadFilesList = super.uploadFile(form, MmtFilePathConfig.OTHERS_PATH);
		if (uploadFilesList.size() == 1) {
			String file_save_path = uploadFilesList.get(0).getFileSavePath();
			String ctxDir = getServlet().getServletContext().getRealPath(String.valueOf(File.separatorChar));

			// 附件保存路径更改遗留问题
			if (StringUtils.contains(file_save_path, MmtFilePathConfig.S_OTHERS_PATH)) {
				ctxDir = "";
				file_save_path = "/Attachment_new/konka-files/" + file_save_path;
			}

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
				List<KonkaPriceManager> entityList = new ArrayList<KonkaPriceManager>();
				for (int i = start_row; i < end_row; i++) {
					KonkaPriceManager entity = new KonkaPriceManager();
					KonkaPriceManager entity1 = new KonkaPriceManager();
					if (StringUtils.isNotBlank(sheet.getCell(1, i).getContents())) {// 型号
						if (sheet.getCell(1, i).getContents().length() > 30) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，型号长度大于30！');history.back();}");
							return null;
						} else {
							entity.setGoods_name(sheet.getCell(1, i).getContents());
							entity1.setGoods_name(sheet.getCell(1, i).getContents());
						}

					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，型号为空！');history.back();}");
						return null;
					}

					if (StringUtils.isNotBlank(sheet.getCell(2, i).getContents())) {// 仓位
						if (sheet.getCell(2, i).getContents().length() > 30) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，仓位长度大于30！');history.back();}");
							return null;
						} else {
							entity.setStore_sn(sheet.getCell(2, i).getContents());
							entity1.setStore_sn(sheet.getCell(2, i).getContents());
							KonkaR3DeptStore t = new KonkaR3DeptStore();
							t.setStore_sn(sheet.getCell(2, i).getContents());
							t.setIs_del(0);
							if (!dept_name.equals("")) {
								t.setDept_name(dept_name);
							}
							List<KonkaR3DeptStore> tList = super.getFacade().getKonkaR3DeptStoreService()
									.getKonkaR3DeptStoreList(t);
							if (tList != null && tList.size() > 0) {
								entity.setStore_name(tList.get(0).getStore_desc());
								entity1.setStore_name(tList.get(0).getStore_desc());
							} else {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
										+ "行，仓位不存在！');history.back();}");
								return null;
							}
						}

					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，仓位为空！');history.back();}");
						return null;
					}

					if (StringUtils.isNotBlank(sheet.getCell(3, i).getContents())) {// 供货价格
						if (sheet.getCell(3, i).getContents().length() > 30) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，供货价格长度大于30！');history.back();}");
							return null;
						} else {
							entity.setPrice(new BigDecimal(sheet.getCell(3, i).getContents()));
						}

					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，供货价格为空！');history.back();}");
						return null;
					}

					if (StringUtils.isNotBlank(sheet.getCell(4, i).getContents())) {// 开始时间
						if (sheet.getCell(4, i).getContents().length() > 10) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，开始时间长度大于10！');history.back();}");
							return null;
						} else {
							Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
							Matcher m = p.matcher(sheet.getCell(4, i).getContents());
							if (!m.matches()) {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
										+ "行，开始时间格式不正确！');history.back();}");
								return null;
							} else {
								String[] array = sheet.getCell(4, i).getContents().split("-");
								int year = Integer.valueOf(array[0]);
								int month = Integer.valueOf(array[1]);
								int day = Integer.valueOf(array[2]);

								if (month < 1 || month > 12) {
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，开始时间不正确！');history.back();}");
									return null;
								}
								int[] monthLengths = new int[] { 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
								if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {// 判断是否是闰年
									monthLengths[2] = 29;
								} else {
									monthLengths[2] = 28;
								}
								int monthLength = monthLengths[month];
								if (day < 1 || day > monthLength) {
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，开始时间不正确！');history.back();}");
									return null;
								}
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								entity.setStart_date(df.parse(sheet.getCell(4, i).getContents() + " 00:00:00"));
							}
						}

					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，开始时间为空！');history.back();}");
						return null;
					}

					if (StringUtils.isNotBlank(sheet.getCell(5, i).getContents())) {// 开始时间
						if (sheet.getCell(5, i).getContents().length() > 10) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，结束时间长度大于10！');history.back();}");
							return null;
						} else {
							Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
							Matcher m = p.matcher(sheet.getCell(5, i).getContents());
							if (!m.matches()) {
								super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
										+ "行，结束时间格式不正确！');history.back();}");
								return null;
							} else {
								String[] array = sheet.getCell(5, i).getContents().split("-");
								int year = Integer.valueOf(array[0]);
								int month = Integer.valueOf(array[1]);
								int day = Integer.valueOf(array[2]);

								if (month < 1 || month > 12) {
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，结束时间不正确！');history.back();}");
									return null;
								}
								int[] monthLengths = new int[] { 0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
								if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {// 判断是否是闰年
									monthLengths[2] = 29;
								} else {
									monthLengths[2] = 28;
								}
								int monthLength = monthLengths[month];
								if (day < 1 || day > monthLength) {
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，结束时间不正确！');history.back();}");
									return null;
								}
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								entity.setEnd_date(df.parse(sheet.getCell(5, i).getContents() + " 00:00:00"));
								if (entity.getStart_date().after(entity.getEnd_date())) {
									super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
											+ "行，结束时间大于开始时间！');history.back();}");
									return null;
								}
							}
						}

					} else {
						super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
								+ "行，结束时间为空！');history.back();}");
						return null;
					}
					List<KonkaPriceManager> list1 = super.getFacade().getKonkaPriceManagerService()
							.getKonkaPriceManagerList(entity1);
					if (list1 != null && list1.size() > 0) {
						boolean flag = false;
						for (KonkaPriceManager pm : list1) {
							if (entity.getStart_date().getTime() > pm.getEnd_date().getTime()
									|| entity.getEnd_date().getTime() < pm.getStart_date().getTime()) {// 添加的最大时间小于之前的最小时间，或者添加的最小时间大于之前的最大时间
							} else {
								flag = true;
								break;
							}
						}
						if (flag) {
							super.renderJavaScript(response, "window.onload=function(){alert('第" + (i + 1)
									+ "行，型号，库存地点相同的情况下，时间不能有重复！');history.back();}");
							return null;
						}
					}

					entityList.add(entity);
				}

				// 插入数据库

				super.getFacade().getKonkaPriceManagerService().createKonkaPriceManagerForExcel(entityList);
				saveMessage(request, "entity.inserted");
				StringBuffer pathBuffer = new StringBuffer();
				pathBuffer.append("/admin/KonkaPriceManager.do");
				pathBuffer.append("?mod_id=" + mod_id);
				ActionForward forward = new ActionForward(pathBuffer.toString(), true);
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

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (null == super.checkUserModPopeDom(form, request, "4")) {
			return super.checkPopedomInvalid(request, response);
		}

		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String mod_id = (String) dynaBean.get("mod_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			KonkaPriceManager entity = new KonkaPriceManager();
			entity.setId(Long.valueOf(id));
			getFacade().getKonkaPriceManagerService().removeKonkaPriceManager(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			KonkaPriceManager entity = new KonkaPriceManager();
			entity.getMap().put("pks", pks);
			for (String xx : pks) {
				entity.setId(Long.valueOf(xx));
				getFacade().getKonkaPriceManagerService().removeKonkaPriceManager(entity);
			}
			saveMessage(request, "entity.deleted");
		}
		// the line below is added for pagination
		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append(mapping.findForward("success").getPath());
		pathBuffer.append("&").append("mod_id=").append(mod_id);
		pathBuffer.append("&");
		pathBuffer.append(super.encodeSerializedQueryString(super.serialize(request, "id", "method")));
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end
		return forward;
	}

}