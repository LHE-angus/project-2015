package com.ebiz.mmt.web.struts.mobile.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.web.struts.BaseAction;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BasePropValItem;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;

public class MobileBaseAction extends BaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.getClass(mapping, form, request, response);
	}

	// 取品类
	public List<MobileSelectItem> getClazz() {
		// String[] cls_ids = { "1010100", "1010200", "1010300", "1010400",
		// "1010500" };
		List<BasePdClazz> baseList = super.getFacade().getRetailMsBaseService()
				.getKonkaBasePdClazzListByClsIds();

		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (BasePdClazz _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getCls_id().toString());
			String[] fullname = _t.getFull_name().split(",");
			t.setName(fullname[fullname.length - 1]);
			list.add(t);
		}
		if (list.size() > 0)
			return list;
		else
			return null;
	}

	public ActionForward getClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<MobileSelectItem> list = this.getClazz();
		if (list != null)
			super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	// 取尺寸
	public List<MobileSelectItem> getSizes(String id) {
		List<BasePropValItem> baseList = super.getFacade()
				.getRetailMsBaseService().getBasePropValItemListByPdId(id);

		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (BasePropValItem _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getProp_item_id().toString());
			t.setName(_t.getProp_item_name());
			list.add(t);
		}
		if (list.size() > 0)
			return list;
		else
			return null;
	}

	public ActionForward getSize(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("pd_id");
		List<MobileSelectItem> list = this.getSizes(id);
		if (list != null)
			super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	// 取型号
	public ActionForward getModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("cls_id");
		String[] cls_ids = { id };
		List<PePdModel> baseList = super.getFacade().getRetailMsBaseService()
				.getKonkaPePdModelListByClsIds(cls_ids);

		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (PePdModel _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getPd_id().toString());
			t.setName(_t.getMd_name());
			list.add(t);
		}
		if (list.size() > 0)
			super.renderJson(response, JSON.toJSONString(list));

		return null;
	}

	// 取样机
	public ActionForward getDemo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<KonkaMobileCategory> baseList = super.getFacade()
				.getKonkaMobileCategoryService().getCategoryList();

		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (KonkaMobileCategory _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getC_index().toString());
			t.setName(_t.getC_name());
			list.add(t);
		}
		if (list.size() > 0)
			super.renderJson(response, JSON.toJSONString(list));

		return null;
	}

	// 取品牌
	public ActionForward getBrand(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<KonkaMobileCategory> baseList = super.getFacade()
				.getKonkaMobileCategoryService().getJingpinCategoryList();
		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (KonkaMobileCategory _t : baseList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getC_index().toString());
			t.setName(_t.getC_name());
			list.add(t);
		}
		if (list.size() > 0)
			super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	// 取物料
	public ActionForward getThings(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<KonkaMobileCategory> wuliaoList = getFacade()
				.getKonkaMobileCategoryService().getWuliaoCategoryList();
		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		for (KonkaMobileCategory _t : wuliaoList) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(_t.getC_index().toString());
			t.setName(_t.getC_name());
			list.add(t);
		}
		if (list.size() > 0)
			super.renderJson(response, JSON.toJSONString(list));
		return null;
	}

	// 存历史上报数据信息

	public void insertReportHistory(KonkaMobileReportHistory entity) {
		entity.setReport_time(new Date());
		super.getFacade().getKonkaMobileReportHistoryService()
				.createKonkaMobileReportHistory(entity);
	}

	public List<MobileSelectItem> getMobileCategories(int type) {
		List<MobileSelectItem> list = new ArrayList<MobileSelectItem>();
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setC_type(type);
		List<KonkaMobileCategory> _list = super.getFacade()
				.getKonkaMobileCategoryService().getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _t : _list) {
			MobileSelectItem entity = new MobileSelectItem();
			entity.setId(_t.getC_index().toString());
			entity.setName(_t.getC_name());
			list.add(entity);
		}
		return list;
	}

	/**
	 * @param request
	 *            自动生成登录用户所在部门的提交文件编号
	 * @author Cheng,Bing
	 */
	public String getFilesMaxNo(String file_no_lm) {

		Long max_fileno = null;

		KonkaoaDocInfo kd = new KonkaoaDocInfo();
		kd.getMap().put("file_no_lm", file_no_lm);
		Long max_fileno_1 = super.getFacade().getKonkaoaDocInfoService()
				.getKonkaoaDocInfoNoMax(kd);
		max_fileno_1 = max_fileno_1 == null ? 0 : max_fileno_1;

		KonkaoaFiles kf = new KonkaoaFiles();
		kf.getMap().put("file_no_lm", file_no_lm);
		Long max_fileno_2 = super.getFacade().getKonkaoaFilesService()
				.getKonkaoaFilesNoMax(kf);
		max_fileno_2 = max_fileno_2 == null ? 0 : max_fileno_2;

		max_fileno = max_fileno_1 >= max_fileno_2 ? max_fileno_1 : max_fileno_2;
		max_fileno = max_fileno + 1;
		String file_no_r = "";
		if (max_fileno < 1000) {
			file_no_r = "0000".substring(0, 4 - ("" + max_fileno).length())
					+ max_fileno;
		} else {
			file_no_r = "" + max_fileno;
		}

		return file_no_lm + file_no_r;
	}

	/**
	 * @desc 根据部门id获取所属部门id
	 * @param dept_type
	 *            2:事业部 3:分公司
	 * @author Hui,Gang
	 * @version 2011-11-10 下午2:45:29
	 */
	protected KonkaDept getSuperiorForDeptType(Long dept_id, Integer dept_type) {
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(dept_id);
		konkaDept.getMap().put("dept_type_eq", dept_type);
		return this.getFacade().getKonkaDeptService()
				.getKonkaDeptSuperiorByDeptId(konkaDept);
	}
}
