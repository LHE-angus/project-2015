package com.ebiz.mmt.web.struts.manager.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaMobileTestData;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.UploadFile;

public class KonkaMobileTestDataInputAction extends MobileBaseAction {

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return this.add(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		saveToken(request);
		if (null == super.checkUserModPopeDom(form, request, "0")) {
			return super.checkPopedomInvalid(request, response);
		}

		setNaviStringToRequestScope(form, request);
		super.getModPopeDom(form, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		Long currentUserId = peProdUser.getId();

		// 初始化门店基础数据
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(currentUserId);
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_188 = false; // 促销员
		boolean role_id_eq_60 = false; // 业务员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		Long[] role_id_60_array = null;
		if (role_id_eq_60) {
			// 业务员
			logger.info("*************** 业务员:{}", peProdUser.getUser_name());
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", currentUserId);
			store.setIs_del(0);
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreListWithYwyUserId(
			        store);

			if (null != storeList) {

				role_id_60_array = new Long[storeList.size()];
				int i = 0;

				for (KonkaR3Store t : storeList) {
					role_id_60_array[i++] = t.getStore_id();

					MobileSelectItem _t = new MobileSelectItem();
					_t.setId(t.getStore_id().toString());
					if (t.getStore_name() != null) {
						String customer_name = (String) t.getMap().get("customer_name");
						if (customer_name != null && customer_name.equals(t.getStore_name())) {
							_t.setName(t.getStore_name());
						} else {
							_t.setName(t.getStore_name() + "[" + customer_name + "]");
						}
					} else {
						_t.setName("名称维护有误的门店");
					}
				}
				request.setAttribute("storeList", storeList);

			}
		}

		if (role_id_eq_188) {
			// 促销员
			logger.info("*************** 促销员:{}", peProdUser.getUser_name());
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(currentUserId); // 登录用户ID作为促销员的ID
			List<KonkaR3Store> storeList1 = new ArrayList<KonkaR3Store>();

			storeList = super.getFacade().getKonkaMobileSpRelationService().getKonkaMobileSpRelationInShopNameList(
			        entity);

			for (KonkaMobileSpRelation t : storeList) {
				if (null != role_id_60_array && ArrayUtils.contains(role_id_60_array, t.getShop_id())) {
					continue;
				}
				KonkaR3Store store = new KonkaR3Store();
				if (null != t && null != t.getShop_id()) {
					store.setStore_id(t.getShop_id());
					store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
					if (null != store) {
						storeList1.add(store);
					}
				}
				MobileSelectItem _t = new MobileSelectItem();
				_t.setId(t.getShop_id().toString());
				if (t.getMap().get("store_name") != null)
					_t.setName(t.getMap().get("store_name").toString());
				else {
					_t.setName("名称维护有误的门店");
				}
				request.setAttribute("storeList", storeList1);
			}
		}

		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("OrderByMd", true);
		pePdModel.setIs_del(0);
		pePdModel.setAudit_state(1);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pePdModel);
		List<MobileSelectItem> entityList = new ArrayList<MobileSelectItem>();
		for (PePdModel _t : pePdModelList) {
			MobileSelectItem entity = new MobileSelectItem();
			entity.setId(_t.getPd_id().toString());
			entity.setName(_t.getMd_name());
			entityList.add(entity);
		}

		KonkaBaseTypeData taskType = new KonkaBaseTypeData();
		taskType.setType_name("下架原因");
		taskType = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);

		List<KonkaBaseTypeData> downCauseList = null;
		if (null != taskType && null != taskType.getType_id()) {
			Long parTypeId = taskType.getType_id();
			taskType = new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			downCauseList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("date", sf.format(date));

		request.setAttribute("user_id", peProdUser.getId());
		request.setAttribute("pePdModelList", pePdModelList);
		request.setAttribute("pePdModelJson", JSON.toJSONString(entityList));
		request.setAttribute("downCauseList", downCauseList);
		return mapping.findForward("input");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {		
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		// 修改的逻辑
		String id = (String) dynaBean.get("id");
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		String hms = sf.format(new Date());

		if (StringUtils.isNotBlank(id)) {
			KonkaMobileTestData _entity = new KonkaMobileTestData();
			_entity.setId(Long.parseLong(id));

			super.copyProperties(_entity, form);
			String up_date = (String) dynaBean.get("up_date");
			String down_date = (String) dynaBean.get("down_date");
			// 型号
			String pd_id = (String) dynaBean.get("pd_id");
			// 备注
			String memo = (String) dynaBean.get("memo");

			String down_cause = (String) dynaBean.get("down_cause");
			String code = (String) dynaBean.get("code");

			if (StringUtils.isNotEmpty(pd_id)) {
				pd_id = pd_id.trim();
				_entity.setModel_id(new Long(pd_id));
				PePdModel pePdModel = new PePdModel();
				pePdModel.setPd_id(Long.valueOf(pd_id));
				pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (pePdModel != null) {
					_entity.setModel_name(pePdModel.getMd_name());
					_entity.setCategory_id(pePdModel.getCls_id());
					_entity.setPct_code(pePdModel.getMat_num());
					if (null != pePdModel.getMd_size() && !"".equals(pePdModel.getMd_size()))
						_entity.setMeasure_id(Long.parseLong(pePdModel.getMd_size()));
					_entity.setMeasure_name(pePdModel.getMd_size());
				} else {
					super.renderTextOrJsonp(request, response, "型号选择出错，请重新选择型号并填报！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "请选择型号！");
				return null;
			}

			KonkaMobileTestData kt = new KonkaMobileTestData();
			kt.setId(Long.valueOf(id));
			kt = super.getFacade().getKonkaMobileTestDataService().getKonkaMobileTestData(kt);
			if (kt.getCode() != null && !kt.getCode().equals("")) {
				if (StringUtils.isNotBlank(code)) {
					if (!kt.getCode().equals(code)) {
						// 修改的时候验证串码是否重复 111,222 111,333

						String[] old_code = kt.getCode().split(",");
						String[] new_code = code.split(",");

						// 找出修改后的code 与 原先的code 不包含
						List<String> yy = new ArrayList<String>();
						List<String> ll = Arrays.asList(old_code);
						for (String string : new_code) {
							if (!ll.contains(string)) {
								yy.add(string);
							}
						}
						KonkaMobileTestData kd = new KonkaMobileTestData();
						kd.setStatus(0);// 有效的
						kd.getMap().put("code_arry",yy.toArray());
						//验正是否重复
						List<KonkaMobileTestData> KonkaMobileTestDataList = super.getFacade().getKonkaMobileTestDataService()
				        .getselectKonkaMobileTestDataAndCode(kd);

						if (null!=KonkaMobileTestDataList &&KonkaMobileTestDataList.size()> 0 ) {
							String msg = "串码";
							int i=0;
							for (KonkaMobileTestData konkaMobileTestData : KonkaMobileTestDataList) {
								if (Integer.parseInt(konkaMobileTestData.getMap().get("countCode").toString())>0) {
									msg+=konkaMobileTestData.getMap().get("code")+",";
									i=2;
								}
							}
							if(i==2)
							{
								msg+="已经重复， 请重新提交。";
								super.renderJavaScript(response, "window.onload=function(){alert('" + msg
							        + "');window.history.back();}");
								return null;
							}
						}
					}
				}

			} 
			else {// 之前没有，现在补填串码，相当于新增
				String[] code_arry =code.split(",");
				KonkaMobileTestData kd = new KonkaMobileTestData();
				kd.setStatus(0);// 有效的
				kd.getMap().put("code_arry",code_arry);
				
				//验正是否重复
				List<KonkaMobileTestData> KonkaMobileTestDataList = super.getFacade().getKonkaMobileTestDataService()
		        .getselectKonkaMobileTestDataAndCode(kd);

				if (null!=KonkaMobileTestDataList &&KonkaMobileTestDataList.size()> 0 ) {
					String msg = "串码";
					int i=0;
					for (KonkaMobileTestData konkaMobileTestData : KonkaMobileTestDataList) {
						if (Integer.parseInt(konkaMobileTestData.getMap().get("countCode").toString())>0) {
							msg+=konkaMobileTestData.getMap().get("code")+",";
							i=2;
						}
					}
					if(i==2)
					{
						msg+="已经重复， 请重新提交。";
						super.renderJavaScript(response, "window.onload=function(){alert('" + msg
					        + "');window.history.back();}");
						return null;
					}
				}
			}
		
			if (StringUtils.isNotBlank(code)) {
				_entity.setCode(code);
			}

			// 销量
			String count = (String) dynaBean.get("count");
			// 价格（总价）
			String price = (String) dynaBean.get("price");
			if (GenericValidator.isLong(count)) {
				_entity.setNum(Long.valueOf(count));
			}
			if (GenericValidator.isDouble(price)) {
				_entity.setPrice(new BigDecimal(price));
			}

			if (StringUtils.isNotBlank(up_date)) {
				_entity.setUp_date(df.parse(up_date + " " + hms));
			}
			if (StringUtils.isNotBlank(down_date)) {
				_entity.setDown_date(df.parse(down_date + " " + hms));
			} else {
				_entity.setDown_date(null);
			}
			if (StringUtils.isNotBlank(down_cause) && StringUtils.isNumeric(down_cause)) {
				_entity.setDown_cause(Integer.valueOf(down_cause));
			}
			if (StringUtils.isNotBlank(down_date)) {
				if (StringUtils.isNotBlank(up_date)) {
					String upStr = df.format(_entity.getUp_date());
					Long upLong = Long.valueOf(upStr.replace(":", "").replace("-", "").replaceAll(" ", ""));

					String downStr = df.format(_entity.getDown_date());
					Long downLong = Long.valueOf(downStr.replace(":", "").replace("-", "").replaceAll(" ", ""));

					if (upLong > downLong) {
						super.renderJavaScript(response, "alert('下架时间不得小于上架时间！');history.back();");
						return null;
					}
				}
			}
			//System.out.println("+++++++++++++++{}" + StringUtils.isNotBlank(down_date));

			_entity.setMemo(memo);
			super.getFacade().getKonkaMobileTestDataService().modifyKonkaMobileTestData(_entity);
			// 拿到上传的图片附件
			List<UploadFile> uploadFileList = super.uploadFile(form, MmtFilePathConfig.LST_PATH, true, 0,
					new int[] { 240 });
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			Attachment attachment = null;
			UploadFile uploadFile = null;
			for (int i = 0; i < uploadFileList.size(); i++) {
				uploadFile = uploadFileList.get(i);
				attachment = new Attachment();
				attachment.setFile_name(uploadFile.getFileName());
				attachment.setFile_type(uploadFile.getContentType());
				attachment.setFile_size(new Integer(uploadFile.getFileSize()));
				attachment.setSave_path(uploadFile.getFileSavePath());
				attachment.setSave_name(uploadFile.getFileSaveName());
				attachment.setLink_tab("KONKA_MOBILE_TEST_DATA");

				if ("front".equals(uploadFile.getFormName())) {
					attachment.setFile_desc("正面");
				} else if ("back".equals(uploadFile.getFormName())) {
					attachment.setFile_desc("背面");
				} else if ("side".equals(uploadFile.getFormName())) {
					attachment.setFile_desc("侧面");
				} else {
					attachment.setFile_desc(uploadFile.getFormName());
				}

				/*
				 * if (i == 0) { attachment.setFile_desc("正面"); } else if (i ==
				 * 1) { attachment.setFile_desc("背面"); } else if (i == 2) {
				 * attachment.setFile_desc("侧面"); } else {
				 * attachment.setFile_desc(uploadFile.getFormName()); }
				 */
				Short isdel = new Short("0");
				attachment.setDel_mark(isdel);
				attachmentList.add(attachment);
			}

			// 添加附件
			if (null != attachmentList) {
				for (Attachment att : attachmentList) {
					Attachment old = new Attachment();
					if (att.getFile_desc().equals("正面")) {
						old.setLink_id(Long.valueOf(id));
						old.setFile_desc("正面");
						super.getFacade().getAttachmentService().removeAttachment(old);
					} else if (att.getFile_desc().equals("背面")) {
						old.setLink_id(Long.valueOf(id));
						old.setFile_desc("背面");
						super.getFacade().getAttachmentService().removeAttachment(old);
					} else if (att.getFile_desc().equals("侧面")) {
						old.setLink_id(Long.valueOf(id));
						old.setFile_desc("侧面");
						super.getFacade().getAttachmentService().removeAttachment(old);
					}

					att.setLink_id(Long.valueOf(id));
					super.getFacade().getAttachmentService().createAttachment(att);
				}
			}

			logger.info("QueryString---->" + _entity.getQueryString());

			StringBuffer pathBuffer = new StringBuffer();
			pathBuffer.append("/admin/KonkaMobileTestDataInput.do?method=list");
			pathBuffer.append("&mod_id=" + mod_id);
			pathBuffer.append("&user_id=" + peProdUser.getId());
			pathBuffer.append("&");
			pathBuffer.append(super.encodeSerializedQueryString(_entity.getQueryString()));

			ActionForward forward = new ActionForward(pathBuffer.toString(), true);
			// end
			return forward;
		}
		KonkaMobileTestData entity = new KonkaMobileTestData();
		// 备注
		String memo = (String) dynaBean.get("memo");
		// 销量
		String count = (String) dynaBean.get("count");
		// 价格
		String price = (String) dynaBean.get("price");
		String up_date = (String) dynaBean.get("up_date");
		String down_date = (String) dynaBean.get("down_date");
		String down_cause = (String) dynaBean.get("down_cause");
		// 型号
		String pd_id = (String) dynaBean.get("pd_id");
		String code = (String) dynaBean.get("code");

		super.copyProperties(entity, form);

		// 提交的时候验证串码是否重复
		KonkaMobileTestData kd = new KonkaMobileTestData();
		kd.setStatus(0);// 有效的
		if(StringUtils.isNotBlank(code)){
			String[] code_arry =code.split(",");
			kd.setStatus(0);// 有效的
			kd.getMap().put("code_arry",code_arry);
			
			//验正是否重复
			List<KonkaMobileTestData> KonkaMobileTestDataList = super.getFacade().getKonkaMobileTestDataService()
	        .getselectKonkaMobileTestDataAndCode(kd);

			if (null!=KonkaMobileTestDataList &&KonkaMobileTestDataList.size()> 0 ) {
				String msg = "串码";
				int i=0;
				for (KonkaMobileTestData konkaMobileTestData : KonkaMobileTestDataList) {
					if (Integer.parseInt(konkaMobileTestData.getMap().get("countCode").toString())>0) {
						msg+=konkaMobileTestData.getMap().get("code")+",";
						i=2;
					}
				}
				if(i==2)
				{
					msg+="已经重复， 请重新提交。";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg
				        + "');window.history.back();}");
					return null;
				}
			}
		}
		if (StringUtils.isNotBlank(code)) {
			entity.setCode(code);
		}
		if (StringUtils.isNotBlank(up_date)) {
			entity.setUp_date(df.parse(up_date + " " + hms));
		}
		if (StringUtils.isNotBlank(down_date)) {
			entity.setDown_date(df.parse(down_date + " " + hms));
		}
		if (StringUtils.isNotBlank(down_cause) && StringUtils.isNumeric(down_cause)) {
			entity.setDown_cause(Integer.valueOf(down_cause));
		}
		if (StringUtils.isNotBlank(down_date)) {
			if (StringUtils.isNotBlank(up_date)) {
				String upStr = df.format(entity.getUp_date());
				Long upLong = Long.valueOf(upStr.replace(":", "").replace("-", "").replaceAll(" ", ""));

				String downStr = df.format(entity.getDown_date());
				Long downLong = Long.valueOf(downStr.replace(":", "").replace("-", "").replaceAll(" ", ""));

				if (upLong > downLong) {
					super.renderJavaScript(response, "alert('下架时间不得小于上架时间！');history.back();");
					return null;
				}
			}
		}
		entity.setMemo(memo);
		entity.setReport_date(new Date());

		// 分公司
		KonkaDept fgs = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
		if (null != fgs) {
			entity.setSubcomp_id(fgs.getDept_id());
			entity.setSubcomp_name(fgs.getDept_name());
		} else {
			entity.setSubcomp_id(peProdUser.getDept_id());
			entity.setSubcomp_name(peProdUser.getDepartment());
		}

		// 销量
		if (StringUtils.isNotEmpty(count)) {
			if (NumberUtils.isNumber(count)) {
				entity.setNum(Long.parseLong(count));
			} else {
				super.renderTextOrJsonp(request, response, "销量必须为数字！");
				return null;
			}
		} else {
			super.renderTextOrJsonp(request, response, "请填写数量！");
			return null;
		}

		// 型号名称
		if (!"0".equalsIgnoreCase(count)) {
			if (StringUtils.isNotEmpty(pd_id)) {
				pd_id = pd_id.trim();
				entity.setModel_id(new Long(pd_id));
				PePdModel pePdModel = new PePdModel();
				pePdModel.setPd_id(Long.valueOf(pd_id));
				pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (pePdModel != null) {
					entity.setModel_name(pePdModel.getMd_name());
					entity.setCategory_id(pePdModel.getCls_id());
					entity.setPct_code(pePdModel.getMat_num());
					if (null != pePdModel.getMd_size() && !"".equals(pePdModel.getMd_size()))
						entity.setMeasure_id(Long.parseLong(pePdModel.getMd_size()));
					entity.setMeasure_name(pePdModel.getMd_size());
				} else {
					super.renderTextOrJsonp(request, response, "型号选择出错，请重新选择型号并填报！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "请选择型号！");
				return null;
			}
		}

		// 门店/客户
		String store_id = (String) dynaBean.get("store_id");
		if (StringUtils.isBlank(store_id)) {
			super.renderTextOrJsonp(request, response, "请选择门店！");
			return null;
		}

		store_id = store_id.trim();
		entity.setDept_id(new Long(store_id));
		KonkaR3Store r3s = new KonkaR3Store();
		r3s.setStore_id(Long.parseLong(store_id));
		r3s = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(r3s);
		if (r3s != null) {
			entity.setDept_name(r3s.getStore_name());

			// 经办
			// entity.setOffice_id(r3s.getJb_job_id());
			entity.setOffice_name(r3s.getJb_name());

			// 查询客户信息
			if (null != r3s.getR3_code()) {
				KonkaR3Shop s = new KonkaR3Shop();
				s.setR3_code(StringUtils.upperCase(r3s.getR3_code()));
				s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

				if (null != s) {
					entity.setChannel_a_id(s.getId());
					entity.setChannel_a_name(s.getCustomer_name());
					entity.setChannel_b_name(s.getR3_code());

					if (GenericValidator.isLong(s.getCustomer_type())) {
						entity.setChannel_b_id(new Long(s.getCustomer_type()));

						KonkaCategory kc = new KonkaCategory();
						kc.setC_index(entity.getChannel_b_id());
						kc.setC_type(10);
						kc = super.getFacade().getKonkaCategoryService().getKonkaCategory(kc);

						if (null != kc) {
							entity.setOrg_name("[" + kc.getC_comm() + "]" + kc.getC_name());
						}
					}
				}
			}
		}

		// 单价
		if (StringUtils.isNotBlank(price)) {
			entity.setPrice(new BigDecimal(price));
		}

		entity.setStatus(0);
		entity.setReport_id(peProdUser.getId());
		entity.setReport_name(peProdUser.getReal_name());

		Long newId = super.getFacade().getKonkaMobileTestDataService().createKonkaMobileTestData(entity);
		// generateHis(entity, null, null, null, null, null, null);

		// 拿到上传的图片附件
		List<UploadFile> uploadFileList = super.uploadFile(form, true, 0, new int[] { 240 });
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachment = null;
		UploadFile uploadFile = null;
		for (int i = 0; i < uploadFileList.size(); i++) {
			uploadFile = uploadFileList.get(i);
			attachment = new Attachment();
			attachment.setFile_name(uploadFile.getFileName());
			attachment.setFile_type(uploadFile.getContentType());
			attachment.setFile_size(new Integer(uploadFile.getFileSize()));
			attachment.setSave_path(uploadFile.getFileSavePath());
			attachment.setSave_name(uploadFile.getFileSaveName());
			attachment.setLink_tab("KONKA_MOBILE_TEST_DATA");
			if ("front".equals(uploadFile.getFormName())) {
				attachment.setFile_desc("正面");
			} else if ("back".equals(uploadFile.getFormName())) {
				attachment.setFile_desc("背面");
			} else if ("side".equals(uploadFile.getFormName())) {
				attachment.setFile_desc("侧面");
			} else {
				attachment.setFile_desc(uploadFile.getFormName());
			}
			Short isdel = new Short("0");
			attachment.setDel_mark(isdel);
			attachmentList.add(attachment);
		}
		// 添加附件
		if (null != attachmentList) {
			for (Attachment att : attachmentList) {
				att.setLink_id(newId);
				super.getFacade().getAttachmentService().createAttachment(att);
			}
		}

		// logger.info("QueryString---->" + entity.getQueryString());

		super.saveMessage(request, "entity.submit.success");

		StringBuffer pathBuffer = new StringBuffer();
		pathBuffer.append("/admin/KonkaMobileTestDataInput.do?method=add");
		pathBuffer.append("&mod_id=" + mod_id);
		pathBuffer.append("&");
		ActionForward forward = new ActionForward(pathBuffer.toString(), true);
		// end

		return forward;

	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);

		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

		String dept_name_like = (String) dynaBean.get("dept_name_like");
		String model_name_like = (String) dynaBean.get("model_name_like");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		String user_id = (String) dynaBean.get("user_id");
		String sj_state = (String) dynaBean.get("sj_state");

		KonkaMobileTestData entity = new KonkaMobileTestData();
		if (StringUtils.isNotBlank(dept_name_like)) {
			entity.getMap().put("dept_name_like", dept_name_like);
		}
		if (StringUtils.isNotBlank(model_name_like)) {
			entity.getMap().put("model_name_like", model_name_like);
		}
		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start);
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end);
		}

		if (StringUtils.isNotBlank(user_id)) {
			entity.setReport_id(Long.valueOf(user_id));
		} else {
			entity.setReport_id(peProdUser.getId());
		}
		entity.getMap().put("today", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		if (StringUtils.isNotBlank(sj_state)) {
			if (sj_state.equals("1")) {// 上架中
				entity.getMap().put("is_up_1", true);
			} else if (sj_state.equals("2")) {// 已下架
				entity.getMap().put("is_up_0", true);
			} else if (sj_state.equals("3")) {// 全部

			}
		} else {
			entity.getMap().put("is_up_1", true);// 默认取上架的，并回显
			dynaBean.set("sj_state", "1");
		}

		super.copyProperties(entity, form);
		entity.setStatus(0);// 只要没被无效的数据。
		List<KonkaMobileTestData> entityList = getFacade().getKonkaMobileTestDataService().getKonkaMobileTestDataList(
		        entity);
		for (KonkaMobileTestData t : entityList) {
			if (t.getDown_date() != null) {// 20141112
				if (t.getDown_date().getTime() > new Date().getTime()
				        && t.getUp_date().getTime() < new Date().getTime()) {
					t.getMap().put("up_self", true);
				}
			} else if (t.getDown_date() == null) {
				if (t.getUp_date() != null) {
					if (t.getUp_date().getTime() < new Date().getTime()) {
						t.getMap().put("up_self", true);
					}
				}
			}
		}
		request.setAttribute("entityList", entityList);
		request.setAttribute("today", new Date());

		return mapping.findForward("list");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");

		if (!GenericValidator.isLong(id)) {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}

		KonkaMobileTestData km = new KonkaMobileTestData();
		km.setId(new Long(id));
		km = getFacade().getKonkaMobileTestDataService().getKonkaMobileTestData(km);
		if (null == km) {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}

		km.setQueryString(super.serialize(request, "id", "method"));

		logger.info("QueryString--->" + km.getQueryString());

		super.copyProperties(form, km);
		dynaBean.set("store_id", km.getDept_id());
		PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		Long currentUserId = peProdUser.getId();

		// 初始化门店基础数据
		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(currentUserId);
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_188 = false; // 促销员
		boolean role_id_eq_60 = false; // 业务员
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 188L) {
				role_id_eq_188 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		Long[] role_id_60_array = null;
		if (role_id_eq_60) {
			// 业务员
			logger.info("*************** 业务员:{}", peProdUser.getUser_name());
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", currentUserId);
			store.setIs_del(0);
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService().getKonkaR3StoreListWithYwyUserId(
			        store);

			if (null != storeList) {

				role_id_60_array = new Long[storeList.size()];
				int i = 0;

				for (KonkaR3Store t : storeList) {
					role_id_60_array[i++] = t.getStore_id();

					MobileSelectItem _t = new MobileSelectItem();
					_t.setId(t.getStore_id().toString());
					if (t.getStore_name() != null) {
						String customer_name = (String) t.getMap().get("customer_name");
						if (customer_name != null && customer_name.equals(t.getStore_name())) {
							_t.setName(t.getStore_name());
						} else {
							_t.setName(t.getStore_name() + "[" + customer_name + "]");
						}
					} else {
						_t.setName("名称维护有误的门店");
					}
				}
				request.setAttribute("storeList", storeList);

			}
		}

		if (role_id_eq_188) {
			// 促销员
			logger.info("*************** 促销员:{}", peProdUser.getUser_name());
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(currentUserId); // 登录用户ID作为促销员的ID
			List<KonkaR3Store> storeList1 = new ArrayList<KonkaR3Store>();

			storeList = super.getFacade().getKonkaMobileSpRelationService().getKonkaMobileSpRelationInShopNameList(
			        entity);

			for (KonkaMobileSpRelation t : storeList) {
				if (null != role_id_60_array && ArrayUtils.contains(role_id_60_array, t.getShop_id())) {
					continue;
				}
				KonkaR3Store store = new KonkaR3Store();
				if (null != t && null != t.getShop_id()) {
					store.setStore_id(t.getShop_id());
					store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
					if (null != store) {
						storeList1.add(store);
					}
				}
				MobileSelectItem _t = new MobileSelectItem();
				_t.setId(t.getShop_id().toString());
				if (t.getMap().get("store_name") != null)
					_t.setName(t.getMap().get("store_name").toString());
				else {
					_t.setName("名称维护有误的门店");
				}
				request.setAttribute("storeList", storeList1);
			}
		}

		PePdModel pePdModel = new PePdModel();
		pePdModel.getMap().put("OrderByMd", true);
		pePdModel.setIs_del(0);
		pePdModel.setAudit_state(1);
		List<PePdModel> pePdModelList = super.getFacade().getPePdModelService().getPePdModelList(pePdModel);
		List<MobileSelectItem> entityList = new ArrayList<MobileSelectItem>();
		for (PePdModel _t : pePdModelList) {
			MobileSelectItem entity = new MobileSelectItem();
			entity.setId(_t.getPd_id().toString());
			entity.setName(_t.getMd_name());
			entityList.add(entity);
		}
		KonkaBaseTypeData taskType = new KonkaBaseTypeData();
		taskType.setType_name("下架原因");
		taskType = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeData(taskType);

		List<KonkaBaseTypeData> downCauseList = null;
		if (null != taskType && null != taskType.getType_id()) {
			Long parTypeId = taskType.getType_id();
			taskType = new KonkaBaseTypeData();
			taskType.setPar_type_id(parTypeId);
			downCauseList = super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(taskType);
		}
		// 拿到图片
		Attachment attachment = new Attachment();
		attachment.setLink_tab("KONKA_MOBILE_TEST_DATA");
		attachment.setLink_id(Long.valueOf(id));
		attachment.setDel_mark(new Short("0"));
		List<Attachment> attList = super.getFacade().getAttachmentService().getAttachmentList(attachment);
		List<Attachment> attListtemp = new ArrayList<Attachment>();
		for (Attachment attachment2 : attList) {
			//System.out.println(attachment2.getFile_desc());
			if ("正面".equals(attachment2.getFile_desc())) {
				dynaBean.set("front", attachment2);
			} else if ("背面".equals(attachment2.getFile_desc())) {
				dynaBean.set("back", attachment2);
			} else if ("侧面".equals(attachment2.getFile_desc())) {
				dynaBean.set("side", attachment2);
			} else {
				attListtemp.add(attachment2);
			}
		}
		dynaBean.set("pd_id", km.getModel_id());
		dynaBean.set("count", km.getNum());
		dynaBean.set("pd_id_1", km.getModel_name());
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("date", sf.format(date));
		request.setAttribute("user_id", peProdUser.getId());
		request.setAttribute("pePdModelList", pePdModelList);
		request.setAttribute("pePdModelJson", JSON.toJSONString(entityList));
		request.setAttribute("downCauseList", downCauseList);
		request.setAttribute("fj_paths", JSON.toJSONString(attListtemp));
		return mapping.findForward("input");
	}
}