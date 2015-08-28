package com.ebiz.mmt.web.struts;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.KonkaMobileAnnouncement;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileDataPatch;
import com.ebiz.mmt.domain.KonkaMobileFightReport;
import com.ebiz.mmt.domain.KonkaMobileMdPercent;
import com.ebiz.mmt.domain.KonkaMobilePayment;
import com.ebiz.mmt.domain.KonkaMobilePd;
import com.ebiz.mmt.domain.KonkaMobileReportHistory;
import com.ebiz.mmt.domain.KonkaMobileSailData;
import com.ebiz.mmt.domain.KonkaMobileSailDataBill;
import com.ebiz.mmt.domain.KonkaMobileSailDatas;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.KonkaMobileTestData;
import com.ebiz.mmt.domain.KonkaR3Store;
import com.ebiz.mmt.domain.KonkaYjglPlanFp;
import com.ebiz.mmt.domain.Konkamobilesaledatas;
import com.ebiz.mmt.domain.MobileList;
import com.ebiz.mmt.domain.MobileListNew;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.MobileSelectItemNew;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SysSetting;

/**
 * @author Derek
 */
public class MobileListAction extends MobileSubmitAction {

	SimpleDateFormat df = new SimpleDateFormat("yyyy/M/d");

	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");

	BigDecimal bd = new BigDecimal("100000.0000");

	private static SerializeConfig mapping = new SerializeConfig();
	static {
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
	}

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.unknow(mapping, form, request, response);
	}

	protected static String toJSONString(Object object, SerializerFeature... features) {
		SerializeWriter out = new SerializeWriter();
		String s;
		JSONSerializer serializer = new JSONSerializer(out);
		SerializerFeature arr$[] = features;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			SerializerFeature feature = arr$[i$];
			serializer.config(feature, true);
		}

		serializer.getValueFilters().add(new ValueFilter() {
			@Override
			public Object process(Object obj, String s, Object value) {
				if (null != value) {
					if (value instanceof java.util.Date) {
						// return String.format("%1$tF %1tT", value);
						return String.format("%1$tF", value);
					}
					return value;
				} else {
					return "";
				}
			}
		});
		serializer.write(object);
		s = out.toString();
		out.close();
		return s;
	}

	@Override
	public ActionForward unknow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		String patch = "";
		KonkaMobileDataPatch _entity = new KonkaMobileDataPatch();

		// 初始化门店基础数据
		patch = "STORE201305062126";
		_entity.setStore(patch);

		// 初始化品牌基础数据
		patch = "BRAND201305062126";
		_entity.setBrand(patch);

		// 初始化意见反馈基础数据
		patch = "BIDEA201305062126";
		_entity.setIdea(patch);

		// 初始化退货原因基础数据
		patch = "REASN201305062126";
		_entity.setBack(patch);

		// 初始化物料基础数据
		patch = "GOODS201305062126";
		_entity.setGood(patch);

		// 初始化产品基础数据
		patch = "MODEL201305062126";
		_entity.setModel(patch);

		// 初始化尺寸基础数据
		patch = "SIZES201305062126";
		_entity.setSize(patch);

		// 初始化品类基础数据
		patch = "TYPES201305062126";
		_entity.setPl(patch);

		super.renderText(response, JSON.toJSONString(_entity));
		return null;
	}

	/**
	 * 默认获取基础数据功能，每10分钟更新一次
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		ServletContext ctx = getServlet().getServletContext();

		// 获取更新缓存基础数据的时机时间
		// SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
		// Date jn = new Date();
		// long remark_now = Long.parseLong(df.format(jn));
		// long remark = 0;
		// 类型，如果为空，去全部，否则去
		String type = (String) dynaBean.get("type");

		// 取数据库
		Long _dataPatch = getDataBaseVer();
		String userDataPatch = (String) dynaBean.get("dataPatch");
		if (userDataPatch == null || "".equals(userDataPatch)) {
			userDataPatch = "1";
		}
		// if (getServlet().getServletContext().getAttribute("remark") != null)
		// {
		// remark =
		// Long.parseLong(getServlet().getServletContext().getAttribute("remark").toString());
		// }
		//
		// if (remark_now - remark > 10l) {
		// String ss = toJSONString(getBaseData(_dataPatch, peProdUser, type,
		// response));
		// getServlet().getServletContext().removeAttribute("remark");
		// getServlet().getServletContext().removeAttribute("newData");
		// getServlet().getServletContext().setAttribute("remark", remark_now);
		// getServlet().getServletContext().setAttribute("newData", ss); //
		// 缓存到内存
		//
		// }

		int update_intervals_min = 5;
		String update_time_key = "LAST_UPDATE_TIME";

		Date now = new Date();
		Date lastUpdate = DateUtils.addDays(now, -1);
		if (ctx.getAttribute(update_time_key) != null) {
			// 取上次更新的时间
			lastUpdate = (Date) ctx.getAttribute(update_time_key);
		}

		// 计算时间差（分钟）
		if (null != type && "1".equals(type)) {// 返回门店，不从缓存获取
			super.renderText(response, toJSONString(getBaseData(_dataPatch, peProdUser, type)));
			return null;
		} else {
			String intervals = DurationFormatUtils.formatPeriod(lastUpdate.getTime(), now.getTime(), "m");
			if (Integer.valueOf(intervals) > update_intervals_min) {
				logger.info("Refresh cache data.");
				ctx.removeAttribute(update_time_key);
				ctx.removeAttribute("newData");

				ctx.setAttribute(update_time_key, now);
				ctx.setAttribute("newData", toJSONString(getBaseData(_dataPatch, peProdUser, type)));
			} else {
				logger.info("Read cache data.");
			}
		}
		Object str = "";
		if (_dataPatch > Long.parseLong(userDataPatch)) { // 如果用户传输的基础数据版本低于当前服务器版本，则获取最新基础数据，否则返回空
			str = getServlet().getServletContext().getAttribute("newData");
		}
		if (str != null) {
			super.renderText(response, str.toString());
		} else {
			super.renderText(response, "");
		}
		return null;
	}
	
	public ActionForward newForData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		ServletContext ctx = getServlet().getServletContext();

		// 获取更新缓存基础数据的时机时间
		// SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
		// Date jn = new Date();
		// long remark_now = Long.parseLong(df.format(jn));
		// long remark = 0;
		// 类型，如果为空，去全部，否则去
		String type = (String) dynaBean.get("type");

		// 取数据库
		Long _dataPatch = getDataBaseVer();
		String userDataPatch = (String) dynaBean.get("dataPatch");
		if (userDataPatch == null || "".equals(userDataPatch)) {
			userDataPatch = "1";
		}
		// if (getServlet().getServletContext().getAttribute("remark") != null)
		// {
		// remark =
		// Long.parseLong(getServlet().getServletContext().getAttribute("remark").toString());
		// }
		//
		// if (remark_now - remark > 10l) {
		// String ss = toJSONString(getBaseData(_dataPatch, peProdUser, type,
		// response));
		// getServlet().getServletContext().removeAttribute("remark");
		// getServlet().getServletContext().removeAttribute("newData");
		// getServlet().getServletContext().setAttribute("remark", remark_now);
		// getServlet().getServletContext().setAttribute("newData", ss); //
		// 缓存到内存
		//
		// }

		int update_intervals_min = 5;
		String update_time_key = "LAST_UPDATE_TIME";

		Date now = new Date();
		Date lastUpdate = DateUtils.addDays(now, -1);
		if (ctx.getAttribute(update_time_key) != null) {
			// 取上次更新的时间
			lastUpdate = (Date) ctx.getAttribute(update_time_key);
		}

		// 计算时间差（分钟）
		if (null != type && "1".equals(type)) {// 返回门店，不从缓存获取
			super.renderText(response, toJSONString(getBaseDataNew(_dataPatch, peProdUser, type)));
			return null;
		} else {
			String intervals = DurationFormatUtils.formatPeriod(lastUpdate.getTime(), now.getTime(), "m");
			if (Integer.valueOf(intervals) > update_intervals_min) {
				logger.info("Refresh cache data.");
				ctx.removeAttribute(update_time_key);
				ctx.removeAttribute("newData");

				ctx.setAttribute(update_time_key, now);
				ctx.setAttribute("newData", toJSONString(getBaseDataNew(_dataPatch, peProdUser, type)));
			} else {
				logger.info("Read cache data.");
			}
		}
		Object str = "";
		if (_dataPatch > Long.parseLong(userDataPatch)) { // 如果用户传输的基础数据版本低于当前服务器版本，则获取最新基础数据，否则返回空
			str = getServlet().getServletContext().getAttribute("newData");
		}
		if (str != null) {
			super.renderText(response, str.toString());
		} else {
			super.renderText(response, "");
		}
		return null;
	}


	/**
	 * 实时刷新基础数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newDataNow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");

		// 用户名
		String userid = (String) dynaBean.get("userid");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		// if (null == peProdUser ) {
		// peProdUser = checkUserid(userid, userpass);//支持userid验证
		// }
		// peProdUser = checkUserid(userid, userpass);
		if (null == peProdUser) {

			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		String dataPatch = (String) dynaBean.get("dataPatch");
		if (StringUtils.isBlank(dataPatch)) {
			super.renderText(response, "parameter 'dataPatch' is blank.");
			return null;
		}
		// 类型，如果为空，去全部，否则去
		String type = (String) dynaBean.get("type");

		Long _dataPatch = getDataBaseVer();

		if (_dataPatch > Long.parseLong(dataPatch)) {

			MobileList _entity = getBaseData(_dataPatch, peProdUser, type);

			super.renderText(response, toJSONString(_entity));
		} else {
			super.renderText(response, "");
		}
		return null;
	}

	/**
	 * 获取基础数据版本号
	 * 
	 * @return
	 */
	public Long getDataBaseVer() {
		SysSetting ss = new SysSetting();
		ss.setTitle("datapatch");
		ss = getFacade().getSysSettingService().getSysSetting(ss);
		Long _dataPatch = 01222333233l;
		if (ss != null)
			_dataPatch = Long.parseLong(ss.getContent());
		return _dataPatch;
	}

	/**
	 * 获取基础数据
	 * 
	 * @param _dataPatch 基础数据版本号，从Sys_Setting表获取
	 * @param peProdUser 当前登录用户对象
	 * @param type 基础数据类型，1表示门店
	 * @param response
	 * @return
	 */
	
	public MobileListNew getBaseDataNew(Long _dataPatch, PeProdUser peProdUser, String type) {
		MobileListNew _entity = new MobileListNew();

		// 设置数据版本号
		_entity.setDataPatch(getDataBaseVer().toString());

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

		List<MobileSelectItemNew> _eList = new ArrayList<MobileSelectItemNew>();
		Long[] storeIds = null;
		if (!role_id_eq_188) {// 如果不是促销员
			// 业务员
			logger.info("*************** 业务员:{}", peProdUser.getUser_name());
			KonkaR3Store store = new KonkaR3Store();
			// store.setIs_del(0);
			store.getMap().put("user_id", currentUserId);
			store.getMap().put("ywy_job_id", peProdUser.getJob_id());
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService()
					.getKonkaR3StoreListWithYwyUserId(store);

			if (null != storeList) {
				storeIds = new Long[storeList.size()];

				int i = 0;
				for (KonkaR3Store t : storeList) {
					MobileSelectItemNew _t = new MobileSelectItemNew();
					_t.setId(t.getStore_id().toString());
					if (t.getStore_name() != null) {
						// String customer_name = (String)
						// t.getMap().get("customer_name");
						// _t.setName("[" + customer_name + "] " +
						// t.getStore_name());
						_t.setName(t.getStore_name());
					} else {
						_t.setName("名称维护有误的门店");
					}
					_eList.add(_t);
					storeIds[i++] = t.getStore_id();
				}
			}
		}
		if (role_id_eq_188) {
			// 促销员
			logger.info("*************** 促销员:{}", peProdUser.getUser_name());
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(currentUserId); // 登录用户ID作为促销员的ID
			storeList = super.getFacade().getKonkaMobileSpRelationService()
					.getKonkaMobileSpRelationInShopNameList(entity);

			for (KonkaMobileSpRelation t : storeList) {
				if (null != storeIds && ArrayUtils.contains(storeIds, t.getShop_id())) {
					continue;
				}

				MobileSelectItemNew _t = new MobileSelectItemNew();
				_t.setId(t.getShop_id().toString());
				if (t.getMap().get("store_name") != null)
					_t.setName(t.getMap().get("store_name").toString());
				else {
					_t.setName("名称维护有误的门店");
				}

				_eList.add(_t);
			}
		}

		_entity.setStoreList(_eList);

		if ("1".equals(type)) {
			// 设置数据版本号
			_entity.setDataPatch(_dataPatch.toString());
			// super.renderText(response, toJSONString(_entity));
			return _entity;
		}

		// 初始化品牌基础数据 竞品
	    _eList = new ArrayList<MobileSelectItemNew>();
		KonkaMobilePd t1 = new KonkaMobilePd();
		t1.setIs_del(0);
		List<KonkaMobilePd> brandList = super.getFacade().getKonkaMobilePdService().getKonkaMobilePdList(t1);
		for (KonkaMobilePd _m : brandList) {
			MobileSelectItemNew _mm = new MobileSelectItemNew();
			_mm.setId(_m.getId().toString());
			_mm.setName(_m.getBrand_name()+_m.getMd_name());
			_mm.setAddon1(_m.getBrand_id().toString());
			_mm.setAddon2(_m.getBrand_name());
			_mm.setAddon3(_m.getPar_type_id()+"");
			
			_eList.add(_mm);
		}
		_entity.setBrandList(_eList);

		// 初始化意见反馈基础数据
		_eList = new ArrayList<MobileSelectItemNew>();
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setC_type(7);
		List<KonkaMobileCategory> ideaList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _m : ideaList) {
			MobileSelectItemNew _mm = new MobileSelectItemNew();
			_mm.setId(_m.getC_index().toString());
			_mm.setName(_m.getC_name().toString());
			_eList.add(_mm);
		}
		_entity.setIdeaList(_eList);

		// 初始化退货原因基础数据
		_eList = new ArrayList<MobileSelectItemNew>();
		t = new KonkaMobileCategory();
		t.setC_type(6);
		List<KonkaMobileCategory> backList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _m : backList) {
			MobileSelectItemNew _mm = new MobileSelectItemNew();
			_mm.setId(_m.getC_index().toString());
			_mm.setName(_m.getC_name().toString());
			_eList.add(_mm);
		}
		_entity.setBackList(_eList);

		// 初始化物料基础数据
		_eList = new ArrayList<MobileSelectItemNew>();
		t = new KonkaMobileCategory();
		t.setC_type(4);
		List<KonkaMobileCategory> goodList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _m : goodList) {
			MobileSelectItemNew _mm = new MobileSelectItemNew();
			_mm.setId(_m.getC_index().toString());
			_mm.setName(_m.getC_name().toString());
			_eList.add(_mm);
		}
		_entity.setGoodList(_eList);

		// 初始化产品基础数据

		List<MobileSelectItemNew> modelList = new ArrayList<MobileSelectItemNew>();
		List<PePdModel> _modelList = new ArrayList<PePdModel>();
		PePdModel m = new PePdModel();
		m.setIs_del(0);
		_modelList = super.getFacade().getPePdModelService().getPePdModelList(m);
		for (PePdModel _m : _modelList) {
			MobileSelectItemNew _mm = new MobileSelectItemNew();
			_mm.setId(_m.getPd_id().toString());
			_mm.setName(_m.getMd_name().toString());
			modelList.add(_mm);
		}
		_entity.setModelList(modelList);

		// 初始化尺寸基础数据
		List<MobileSelectItemNew> sizeList = new ArrayList<MobileSelectItemNew>();
		for (String str : size_strs) {
			MobileSelectItemNew _t = new MobileSelectItemNew();
			_t.setId(str);
			_t.setName(str);
			sizeList.add(_t);
		}
		_entity.setSizeList(sizeList);

		// 初始化品类基础数据
		List<MobileSelectItemNew> plList = getClazzNew();
		_entity.setPlList(plList);

		// 初始化提成比例/金额
		List<MobileSelectItemNew> mmpList = new ArrayList<MobileSelectItemNew>();
		KonkaMobileMdPercent mmp = new KonkaMobileMdPercent();
		mmp.setDept_id(peProdUser.getDept_id());
		mmp.setStatus(0);
		List<KonkaMobileMdPercent> _mmpList = super.getFacade().getKonkaMobileMdPercentService()
				.getKonkaMobileMdPercentList(mmp);
		for (KonkaMobileMdPercent _mmp : _mmpList) {
			MobileSelectItemNew _t = new MobileSelectItemNew();
			_t.setId(_mmp.getId().toString());
			_t.setName(_mmp.getPercent().toString());
			_t.setAddon1(_mmp.getType().toString());
			_t.setAddon2(_mmp.getPd_id().toString());
			mmpList.add(_t);
		}
		_entity.setPeList(mmpList);
		return _entity;
	}

	public MobileList getBaseData(Long _dataPatch, PeProdUser peProdUser, String type) {
		MobileList _entity = new MobileList();

		// 设置数据版本号
		_entity.setDataPatch(getDataBaseVer().toString());

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

		List<MobileSelectItem> _eList = new ArrayList<MobileSelectItem>();
		Long[] storeIds = null;
		if (!role_id_eq_188) {// 如果不是促销员
			// 业务员
			logger.info("*************** 业务员:{}", peProdUser.getUser_name());
			KonkaR3Store store = new KonkaR3Store();
			// store.setIs_del(0);
			store.getMap().put("user_id", currentUserId);
			store.getMap().put("ywy_job_id", peProdUser.getJob_id());
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService()
					.getKonkaR3StoreListWithYwyUserId(store);

			if (null != storeList) {
				storeIds = new Long[storeList.size()];

				int i = 0;
				for (KonkaR3Store t : storeList) {
					MobileSelectItem _t = new MobileSelectItem();
					_t.setId(t.getStore_id().toString());
					if (t.getStore_name() != null) {
						// String customer_name = (String)
						// t.getMap().get("customer_name");
						// _t.setName("[" + customer_name + "] " +
						// t.getStore_name());
						_t.setName(t.getStore_name());
					} else {
						_t.setName("名称维护有误的门店");
					}
					_eList.add(_t);
					storeIds[i++] = t.getStore_id();
				}
			}
		}
		if (role_id_eq_188) {
			// 促销员
			logger.info("*************** 促销员:{}", peProdUser.getUser_name());
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(currentUserId); // 登录用户ID作为促销员的ID
			storeList = super.getFacade().getKonkaMobileSpRelationService()
					.getKonkaMobileSpRelationInShopNameList(entity);

			for (KonkaMobileSpRelation t : storeList) {
				if (null != storeIds && ArrayUtils.contains(storeIds, t.getShop_id())) {
					continue;
				}

				MobileSelectItem _t = new MobileSelectItem();
				_t.setId(t.getShop_id().toString());
				if (t.getMap().get("store_name") != null)
					_t.setName(t.getMap().get("store_name").toString());
				else {
					_t.setName("名称维护有误的门店");
				}

				_eList.add(_t);
			}
		}

		_entity.setStoreList(_eList);

		if ("1".equals(type)) {
			// 设置数据版本号
			_entity.setDataPatch(_dataPatch.toString());
			// super.renderText(response, toJSONString(_entity));
			return _entity;
		}

		// 初始化品牌基础数据 竞品
		_eList = new ArrayList<MobileSelectItem>();
		KonkaMobilePd t1 = new KonkaMobilePd();
		t1.setIs_del(0);

		List<KonkaMobilePd> brandList = super.getFacade().getKonkaMobilePdService().getKonkaMobilePdList(t1);

		for (KonkaMobilePd _m : brandList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getId().toString());
			_mm.setName(_m.getBrand_name() + _m.getMd_name().toString());
			_mm.setAddon1(_m.getBrand_id().toString());
			_mm.setAddon2(_m.getBrand_name());
			_eList.add(_mm);
		}
		_entity.setBrandList(_eList);

		// 初始化意见反馈基础数据
		_eList = new ArrayList<MobileSelectItem>();
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setC_type(7);
		List<KonkaMobileCategory> ideaList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _m : ideaList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getC_index().toString());
			_mm.setName(_m.getC_name().toString());
			_eList.add(_mm);
		}
		_entity.setIdeaList(_eList);

		// 初始化退货原因基础数据
		_eList = new ArrayList<MobileSelectItem>();
		t = new KonkaMobileCategory();
		t.setC_type(6);
		List<KonkaMobileCategory> backList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _m : backList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getC_index().toString());
			_mm.setName(_m.getC_name().toString());
			_eList.add(_mm);
		}
		_entity.setBackList(_eList);

		// 初始化物料基础数据
		_eList = new ArrayList<MobileSelectItem>();
		t = new KonkaMobileCategory();
		t.setC_type(4);
		List<KonkaMobileCategory> goodList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _m : goodList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getC_index().toString());
			_mm.setName(_m.getC_name().toString());
			_eList.add(_mm);
		}
		_entity.setGoodList(_eList);

		// 初始化产品基础数据

		List<MobileSelectItem> modelList = new ArrayList<MobileSelectItem>();
		List<PePdModel> _modelList = new ArrayList<PePdModel>();
		PePdModel m = new PePdModel();
		m.setIs_del(0);
		_modelList = super.getFacade().getPePdModelService().getPePdModelList(m);
		for (PePdModel _m : _modelList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getPd_id().toString());
			_mm.setName(_m.getMd_name().toString());
			modelList.add(_mm);
		}
		_entity.setModelList(modelList);

		// 初始化尺寸基础数据
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem _t = new MobileSelectItem();
			_t.setId(str);
			_t.setName(str);
			sizeList.add(_t);
		}
		_entity.setSizeList(sizeList);

		// 初始化品类基础数据
		List<MobileSelectItem> plList = getClazz();
		_entity.setPlList(plList);

		// 初始化提成比例/金额
		List<MobileSelectItem> mmpList = new ArrayList<MobileSelectItem>();
		KonkaMobileMdPercent mmp = new KonkaMobileMdPercent();
		mmp.setDept_id(peProdUser.getDept_id());
		mmp.setStatus(0);
		List<KonkaMobileMdPercent> _mmpList = super.getFacade().getKonkaMobileMdPercentService()
				.getKonkaMobileMdPercentList(mmp);
		for (KonkaMobileMdPercent _mmp : _mmpList) {
			MobileSelectItem _t = new MobileSelectItem();
			_t.setId(_mmp.getId().toString());
			_t.setName(_mmp.getPercent().toString());
			_t.setAddon1(_mmp.getType().toString());
			_t.setAddon2(_mmp.getPd_id().toString());
			mmpList.add(_t);
		}
		_entity.setPeList(mmpList);
		return _entity;
	}

	public ActionForward GetHisList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		super.renderText(response, "success");
		return null;
	}

	/**
	 * @desc 促销员关联的门店, web静态页面Ajax调用使用,2013-05-31
	 */
	public ActionForward GetList01(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"用户信息出错，请联系管理员！\"}");
			return null;
		}

		Long currentUserId = peProdUser.getId();

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

		List<MobileSelectItem> _eList = new ArrayList<MobileSelectItem>();
		Long[] role_id_60_array = null;
		if (role_id_eq_60) {
			// 业务员
			logger.info("*************** 业务员:{}", peProdUser.getUser_name());
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", currentUserId);
			store.getMap().put("ywy_job_id", peProdUser.getJob_id());
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService()
					.getKonkaR3StoreListWithYwyUserId(store);

			if (null != storeList) {

				role_id_60_array = new Long[storeList.size()];
				int i = 0;

				for (KonkaR3Store t : storeList) {
					role_id_60_array[i++] = t.getStore_id();

					MobileSelectItem _t = new MobileSelectItem();
					_t.setId(t.getStore_id().toString());
					if (t.getStore_name() != null) {
						String customer_name = (String) t.getMap().get("customer_name");
						if (customer_name == null || customer_name.equals(t.getStore_name())) {
							_t.setName(t.getStore_name());
						} else {
							_t.setName(t.getStore_name() + "[" + customer_name + "]");
						}
					} else {
						_t.setName("名称维护有误的门店");
					}
					_eList.add(_t);
				}

			}
		}

		if (role_id_eq_188) {
			// 促销员
			logger.info("*************** 促销员:{}", peProdUser.getUser_name());
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(currentUserId); // 登录用户ID作为促销员的ID
			storeList = super.getFacade().getKonkaMobileSpRelationService()
					.getKonkaMobileSpRelationInShopNameList(entity);

			for (KonkaMobileSpRelation t : storeList) {
				if (null != role_id_60_array && ArrayUtils.contains(role_id_60_array, t.getShop_id())) {
					continue;
				}

				MobileSelectItem _t = new MobileSelectItem();
				_t.setId(t.getShop_id().toString());
				if (t.getMap().get("store_name") != null)
					_t.setName(t.getMap().get("store_name").toString());
				else {
					_t.setName("名称维护有误的门店");
				}
				_eList.add(_t);
			}
		}

		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(_eList));
		return null;
	}

	// 获取品类
	public ActionForward GetList02(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<MobileSelectItem> entityList = getClazz();
		super.renderText(response, JSON.toJSONString(entityList));
		return null;
	}

	// 获取尺寸
	public ActionForward GetList03(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		List<MobileSelectItem> entityList = new ArrayList<MobileSelectItem>();

		for (String str : size_strs) {
			MobileSelectItem t = new MobileSelectItem();
			t.setId(str);
			t.setName(str);
			entityList.add(t);
		}

		super.renderText(response, JSON.toJSONString(entityList));
		return null;
	}

	// 获取型号
	public ActionForward GetList04(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"用户信息出错，请联系管理员！\"}");
			return null;
		}

		// 尺寸逻辑
		// String size = (String) dynaBean.get("size");
		PePdModel t = new PePdModel();
		t.setIs_del(0);
		t.setAudit_state(1);
		// if (StringUtils.isBlank(size)) {
		// t.getRow().setFirst(0);
		// t.getRow().setCount(30);
		// }
		// t.getMap().put("led_name_like", size);
		List<PePdModel> tList = new ArrayList<PePdModel>();
		tList = super.getFacade().getPePdModelService().getPePdModelList(t);
		List<MobileSelectItem> entityList = new ArrayList<MobileSelectItem>();
		for (PePdModel _t : tList) {
			MobileSelectItem entity = new MobileSelectItem();
			entity.setId(_t.getPd_id().toString());
			entity.setName(_t.getMd_name());
			entityList.add(entity);
		}
		super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(entityList));
		return null;
	}

	/**
	 * @desc 获取竞品数据 , web静态页面Ajax调用使用,2013-05-31
	 */
	public ActionForward GetListJP(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<MobileSelectItem> _eList = new ArrayList<MobileSelectItem>();
		KonkaMobilePd t1 = new KonkaMobilePd();

		List<KonkaMobilePd> brandList = super.getFacade().getKonkaMobilePdService().getKonkaMobilePdList(t1);
		for (KonkaMobilePd _m : brandList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getId().toString());
			_mm.setName(_m.getBrand_name() + _m.getMd_name().toString());
			_mm.setAddon1(_m.getBrand_id().toString());
			_mm.setAddon2(_m.getBrand_name());
			_eList.add(_mm);
		}

		if (_eList.size() > 0) {
			super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(_eList));
		} else {
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"null\"}");
		}
		return null;
	}
	/**
	 * @desc 获取竞品数据 , web静态页面Ajax调用使用,2013-05-31
	 */
	public ActionForward GetListNewJP(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<MobileSelectItemNew> _eList = new ArrayList<MobileSelectItemNew>();
		KonkaMobilePd t1 = new KonkaMobilePd();
		t1.setIs_del(0);
		List<KonkaMobilePd> brandList = super.getFacade().getKonkaMobilePdService().getKonkaMobilePdList(t1);
		for (KonkaMobilePd _m : brandList) {
			MobileSelectItemNew _mm = new MobileSelectItemNew();
			_mm.setId(_m.getId().toString());
			_mm.setName( _m.getMd_name().toString());
			_mm.setAddon1(_m.getBrand_id().toString());
			_mm.setAddon2(_m.getBrand_name());
			_mm.setAddon3(_m.getPar_type_id()+"");
			_eList.add(_mm);
		}

		if (_eList.size() > 0) {
			super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(_eList));
		} else {
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"null\"}");
		}
		return null;
	}
	
	
	
	
	// 获取枚举
	public ActionForward GetListST(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 类型
		String type = (String) dynaBean.get("type");
		KonkaMobileCategory t = new KonkaMobileCategory();
		if (StringUtils.isNotBlank(type))
			t.setC_type(Integer.parseInt(type));
		List<KonkaMobileCategory> entityList = super.getFacade().getKonkaMobileCategoryService()
				.getKonkaMobileCategoryList(t);
		if (entityList.size() > 0)
			super.renderTextJsonOrJsonp(request, response, JSON.toJSONString(entityList));
		else
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"null\"}");
		return null;
	}

	// 获取新闻
	public ActionForward GetListNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		// 分页参数
		String start = (String) dynaBean.get("start");
		String limit = (String) dynaBean.get("limit");

		KonkaMobileAnnouncement entity = new KonkaMobileAnnouncement();
		if (StringUtils.isNotBlank(start))
			entity.getRow().setFirst(Integer.parseInt(start));
		else {
			entity.getRow().setFirst(0);
		}
		if (StringUtils.isNotBlank(limit))
			entity.getRow().setCount(Integer.parseInt(limit));
		else
			entity.getRow().setCount(10);
		List<KonkaMobileAnnouncement> entityList = new ArrayList<KonkaMobileAnnouncement>();
		entityList = super.getFacade().getKonkaMobileAnnouncementService()
				.getKonkaMobileAnnouncementPaginatedList(entity);
		if (entityList.size() > 0)
			super.renderText(response, JSON.toJSONString(entityList, MobileListAction.mapping));
		else
			super.renderText(response, "null");
		return null;
	}

	// 获取上报历史
	public ActionForward GetListHis(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		// 类型
		// 销售数据 1
		// 门店库存 2
		// 退货登记 3
		// 样机管理 4
		// 物料终端 5
		// 竞品上报 6
		String type = (String) dynaBean.get("type");

		// 分页参数
		String start = (String) dynaBean.get("start");
		String limit = (String) dynaBean.get("limit");

		KonkaMobileReportHistory entity = new KonkaMobileReportHistory();
		entity.setReport_man(peProdUser.getId());
		if (StringUtils.isNotBlank(start))
			entity.getRow().setFirst(Integer.parseInt(start));
		else {
			entity.getRow().setFirst(0);
		}
		if (StringUtils.isNotBlank(limit))
			entity.getRow().setCount(Integer.parseInt(limit));
		else
			entity.getRow().setCount(10);
		if (StringUtils.isNotEmpty(type))
			entity.setType_id(Long.parseLong(type));
		// 关键词查询
		String keys = (String) dynaBean.get("keyword");
		entity.setContent(keys);
		List<KonkaMobileReportHistory> entityList = new ArrayList<KonkaMobileReportHistory>();
		entityList = super.getFacade().getKonkaMobileReportHistoryService()
				.getKonkaMobileReportHistoryPaginatedList(entity);
		if (entityList.size() > 0)
			super.renderText(response, JSON.toJSONString(entityList));
		else
			super.renderText(response, "null");
		return null;
	}

	/**
	 * @desc 历史明细 , web静态页面Ajax调用使用,2013-05-31
	 */
	public ActionForward GetHis(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		// String user_id = (String) dynaBean.get("user_id");
		// PeProdUser peProdUser = new PeProdUser();
		// peProdUser.setId(Long.valueOf(user_id));
		// peProdUser =
		// super.getFacade().getPeProdUserService().getPeProdUser(peProdUser);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"用户信息出错，请联系管理员！\"}");
			return null;
		}

		// 类型: 1-销售明细 ,6-竞品信息 ,9-销售汇总,2-样机管理，3-相机明细 , 99-广州发票上传审核,98-待上样计划样机
		String type = (String) dynaBean.get("type");
		if (StringUtils.isEmpty(type)) {
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"关键信息丢失！\"}");
			return null;
		}

		String startime = (String) dynaBean.get("startime");
		String endtime = (String) dynaBean.get("endtime");

		int typei = Integer.parseInt(type);
		switch (typei) {
		case 1:
			KonkaMobileSailData t1 = new KonkaMobileSailData();
			// t1.getMap().put("num_price_not_null", true);
			t1.setReport_id(peProdUser.getId());
			t1.getMap().put("startime", startime);
			t1.setIs_del(0);
			t1.getMap().put("endtime", endtime);
			t1.getMap().put("is_not_gz", "yes");// 不是广州零售通，（不带发票的）
			String status = (String) dynaBean.get("status");
			if ("0".equals(status)) {
				t1.setStatus(0);
			}
			t1.setIs_del(0);
			List<KonkaMobileSailData> t1List = super.getFacade().getKonkaMobileSailDataService()
					.getKonkaMobileSailDataList(t1);

			// add 2013-10-29 by Jiang,JiaYong
			// 判断是否是2天内的数据，如果是2天内的允许编辑
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			StringBuffer dateStr = new StringBuffer();
			dateStr.append(DateFormatUtils.format(cal, "yyyy-MM-dd"));
			dateStr.append(" 00:00:00");
			Date compareDate = DateUtils.parseDate(dateStr.toString(), new String[] { "yyyy-MM-dd HH:mm:ss" });
			for (KonkaMobileSailData konkaMobileSailData : t1List) {
				konkaMobileSailData.getMap().put("can_edit", "0");
				if (konkaMobileSailData.getReport_date().after(compareDate)) {
					konkaMobileSailData.getMap().put("can_edit", "1");
				}
				// 如果型號名稱為空，則不允許修改
				if (konkaMobileSailData.getModel_name() == null || "".equals(konkaMobileSailData.getModel_name())) {
					konkaMobileSailData.getMap().put("can_edit", "0");
				}

				Attachment a = new Attachment();
				a.setLink_tab("KONKA_MOBILE_SAIL_DATA");
				a.setLink_id(konkaMobileSailData.getId());
				List<Attachment> aList = super.getFacade().getAttachmentService().getAttachmentList(a);
				konkaMobileSailData.getMap().put("attachment", aList);
			}

			super.renderTextJsonOrJsonp(request, response, toJSONString(t1List));
			break;
		case 2:
			// 返回样机列表
			KonkaMobileTestData t2 = new KonkaMobileTestData();
			String status2 = (String) dynaBean.get("status");
			String id2 = (String) dynaBean.get("id");
			String is_up = (String) dynaBean.get("is_up");
			String plan_flag = (String) dynaBean.get("plan_flag");
			String temp_flag = (String) dynaBean.get("temp_flag");
			String code_like = (String) dynaBean.get("code_like");
			if (GenericValidator.isInt(status2)) {
				t2.setStatus(Integer.parseInt(status2));
			} else {
				t2.setStatus(0);
			}
			if (GenericValidator.isLong(id2)) {
				t2.setId(Long.valueOf(id2));
			}
			if ("1".equals(is_up)) // 已上架
				t2.getMap().put("is_up_1", true);
			if ("0".equals(is_up)) // 未上架
				t2.getMap().put("is_up_0", true);
			if (StringUtils.isNotBlank(plan_flag)) {
				t2.getMap().put("plan_flag", true);
			}
			if (StringUtils.isNotBlank(temp_flag)) {
				t2.getMap().put("temp_flag", true);
			}
			if (null != code_like && StringUtils.isNotBlank(code_like)) {
				t2.getMap().put("code_like", code_like);
			}
			t2.setReport_id(peProdUser.getId());

			t2.getMap().put("startime", startime);

			t2.getMap().put("endtime", endtime);
			List<KonkaMobileTestData> t2List = super.getFacade().getKonkaMobileTestDataService()
					.getKonkaMobileTestDataList(t2);
			for (KonkaMobileTestData konkaMobileTestData : t2List) {
				if (null != konkaMobileTestData && null != konkaMobileTestData.getId()) {
					List<Attachment> fj_paths = new ArrayList<Attachment>();
					Attachment att = new Attachment();
					att.setLink_id(konkaMobileTestData.getId());
					att.setLink_tab("KONKA_MOBILE_TEST_DATA");
					fj_paths = super.getFacade().getAttachmentService().getAttachmentList(att);
					if (null != fj_paths) {
						konkaMobileTestData.getMap().put("fj_paths", fj_paths);
					}
				}
			}
			super.renderTextJsonOrJsonp(request, response, toJSONString(t2List));
			break;
		case 3:
			// 返回样机明细数据
			KonkaMobileTestData t3 = new KonkaMobileTestData();
			String id = (String) dynaBean.get("id");
			long idl = Long.parseLong(id);
			t3.setId(idl);
			List<KonkaMobileTestData> t3List = super.getFacade().getKonkaMobileTestDataService()
					.getKonkaMobileTestDataList(t3);
			for (KonkaMobileTestData konkaMobileTestData : t3List) {
				if (null != konkaMobileTestData && null != konkaMobileTestData.getId()) {
					List<Attachment> fj_paths = new ArrayList<Attachment>();
					Attachment att = new Attachment();
					att.setLink_id(konkaMobileTestData.getId());
					att.setLink_tab("KONKA_MOBILE_TEST_DATA");
					fj_paths = super.getFacade().getAttachmentService().getAttachmentList(att);
					if (null != fj_paths) {
						konkaMobileTestData.getMap().put("fj_paths", fj_paths);
					}
				}
			}
			super.renderTextJsonOrJsonp(request, response, toJSONString(t3List));
			break;
		case 6:
			KonkaMobileFightReport t6 = new KonkaMobileFightReport();
			t6.getMap().put("num_price_not_null", true);
			t6.setReport_man(peProdUser.getId());
			t6.getMap().put("startime", startime);
			// 如果id不为空，则获取明细信息
			String id__fight = (String) dynaBean.get("id");
			if (!"".equals(id__fight) && id__fight != null) {
				long lid__fight = Long.parseLong(id__fight);
				t6.setId(lid__fight);
			}

			t6.getMap().put("endtime", endtime);
			t6.getRow().setFirst(0);
			t6.getRow().setCount(10000);
			List<KonkaMobileFightReport> t6List = super.getFacade().getKonkaMobileFightReportService()
					.getKonkaMobileFightReportPaginatedListForQuery(t6);
			
			
			for(KonkaMobileFightReport report: t6List){
				Attachment ath1 = new Attachment();
				ath1.setLink_tab("KONKA_MOBILE_FIGHT_REPORT");
				ath1.setLink_id(report.getId());
				ath1.setDel_mark(new Short("0"));
				List<Attachment> athList = super.getFacade().getAttachmentService().getAttachmentList(ath1);
				report.getMap().put("athList", athList);
			}
			super.renderTextJsonOrJsonp(request, response, toJSONString(t6List));
			break;
		case 9:
			KonkaMobileSailDatas t9 = new KonkaMobileSailDatas();
			t9.setReport_id(peProdUser.getId());
			t9.getMap().put("startime", startime);
			t9.getMap().put("endtime", endtime);
			List<KonkaMobileSailDatas> t9List = super.getFacade().getKonkaMobileSailDatasService()
					.getKonkaMobileSailDatasList(t9);
			super.renderTextJsonOrJsonp(request, response, toJSONString(t9List));
			break;
		case 99:
			KonkaMobileSailData t99 = new KonkaMobileSailData();
			// t1.getMap().put("num_price_not_null", true);
			t99.setReport_id(peProdUser.getId());
			t99.getMap().put("startime", startime);
			t99.setIs_del(0);
			t99.getMap().put("endtime", endtime);
			String status99 = (String) dynaBean.get("status");
			if ("0".equals(status99)) {
				t99.setStatus(0);
			}
			t99.setIs_del(0);
			List<KonkaMobileSailData> t1List99 =
			// 这个是查必须有发票的
			// super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataBillFileList(t99);
			super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailDataList(t99);// 这个查的是所有的
			// add 2013-10-29 by Jiang,JiaYong
			// 判断是否是2天内的数据，如果是2天内的允许编辑
			Calendar cal99 = Calendar.getInstance();
			cal99.add(Calendar.DATE, -59);
			StringBuffer dateStr99 = new StringBuffer();
			dateStr99.append(DateFormatUtils.format(cal99, "yyyy-MM-dd"));
			dateStr99.append(" 00:00:00");
			Date compareDate99 = DateUtils.parseDate(dateStr99.toString(), new String[] { "yyyy-MM-dd HH:mm:ss" });
			for (KonkaMobileSailData konkaMobileSailData : t1List99) {
				konkaMobileSailData.getMap().put("can_edit", "0");
				if (konkaMobileSailData.getReport_date().after(compareDate99)
						&& (null != konkaMobileSailData.getAudit_state() && (konkaMobileSailData.getAudit_state() == 0 || konkaMobileSailData
								.getAudit_state() == 4))) {
					konkaMobileSailData.getMap().put("can_edit", "1");
				}
				// 如果型號名稱為空，則不允許修改
				if (konkaMobileSailData.getModel_name() == null || "".equals(konkaMobileSailData.getModel_name())) {
					konkaMobileSailData.getMap().put("can_edit", "0");
				}

				Attachment a = new Attachment();
				a.setLink_tab("KONKA_MOBILE_SAIL_DATA");
				a.setLink_id(konkaMobileSailData.getId());
				List<Attachment> aList = super.getFacade().getAttachmentService().getAttachmentList(a);
				konkaMobileSailData.getMap().put("attachment", aList);
				KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
				konkaMobileSailDataBill.setSail_id(konkaMobileSailData.getId());
				List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
						.getKonkaMobileSailDataBillService().getKonkaMobileSailDataBillList(konkaMobileSailDataBill);
				konkaMobileSailData.getMap().put("konkaMobileSailDataBillList", konkaMobileSailDataBillList);

			}

			super.renderTextJsonOrJsonp(request, response, toJSONString(t1List99));
			break;

		case 98:

			KonkaYjglPlanFp kp = new KonkaYjglPlanFp();
			kp.setIs_del(0);// 未删除的
			kp.setIs_confirm(1);// 已确认的

			// 根据登录人的角色 取门店id
			PeRoleUser _peRoleUser = new PeRoleUser();
			_peRoleUser.setUser_id(peProdUser.getId());
			List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);
			List<Long> roles = new ArrayList<Long>();
			for (PeRoleUser peRoleUser : peRoleUserList) {
				roles.add(peRoleUser.getRole_id());
			}
			List<Long> s_ids = new ArrayList<Long>();
			if (roles.contains(60L) && !roles.contains(188L)) {// 业务员
				s_ids = getR3StoreIds(peProdUser.getId(), 60L);
			} else if (roles.contains(188L) && !roles.contains(60L)) {// 促销员
				s_ids = getR3StoreIds(peProdUser.getId(), 188L);
			} else if (roles.contains(188L) && roles.contains(60L)) {// 既是业务员又是促销员
				s_ids = getR3StoreIds(peProdUser.getId(), 0L);
			}
			if (s_ids.size() > 0) {
				kp.getMap().put("store_ids", s_ids);
			}

			if (StringUtils.isNotBlank(startime)) {
				kp.getMap().put("startime", startime);
			}
			if (StringUtils.isNotBlank(endtime)) {
				kp.getMap().put("endtime", endtime);
			}

			Long recordCount = super.getFacade().getKonkaYjglPlanFpService().getKonkaYjglPlanFpAndDeptNameCount(kp);
			kp.getRow().setFirst(0);
			kp.getRow().setCount(recordCount.intValue());
			List<KonkaYjglPlanFp> entityList = super.getFacade().getKonkaYjglPlanFpService()
					.getKonkaYjglPlanFpAndDeptNamePaginatedList(kp);
			super.renderTextJsonOrJsonp(request, response, toJSONString(entityList));
			break;

		default:
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"null\"}");
			break;
		}
		return null;
	}

	/**
	 * @desc model 实例 , web静态页面Ajax调用使用,2013-05-31
	 */
	public ActionForward GetModel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		// / 类型: 1-销售明细 ,6-竞品信息 ,9-销售汇总
		String type = (String) dynaBean.get("type");
		// id
		String id = (String) dynaBean.get("id");
		String url = request.getRequestURI();
		//System.out.println("URL------------>" + url);

		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"用户信息出错，请联系管理员！\"}");
			return null;
		}

		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(type)) {
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"关键信息丢失！\"}");
			return null;
		}

		int typei = Integer.parseInt(type);
		long idl = Long.parseLong(id);
		switch (typei) {
		case 1:
			KonkaMobileSailData t1 = new KonkaMobileSailData();
			t1.setId(idl);
			t1 = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailData(t1);
			Attachment a = new Attachment();
			a.setLink_tab("KONKA_MOBILE_SAIL_DATA");
			a.setLink_id(t1.getId());
			a.setDel_mark(new Short("0"));
			List<Attachment> aList = super.getFacade().getAttachmentService().getAttachmentList(a);
			t1.getMap().put("attachment", aList);
			logger.info("----toJSONString(t1)--->{}", toJSONString(t1));
			super.renderTextJsonOrJsonp(request, response, toJSONString(t1));
			break;
		case 6:
			KonkaMobileFightReport t6 = new KonkaMobileFightReport();
			t6.setId(idl);
			List<KonkaMobileFightReport> list= super.getFacade().getKonkaMobileFightReportService().getKonkaMobileFightReportPaginatedListForQueryNew(t6);
			if(null!=list && list.size()>0)
			{
				t6=list.get(0);
			}
			
			Attachment ath1 = new Attachment();
			ath1.setLink_tab("KONKA_MOBILE_FIGHT_REPORT");
			ath1.setLink_id(t6.getId());
			ath1.setDel_mark(new Short("0"));
			List<Attachment> athList = super.getFacade().getAttachmentService().getAttachmentList(ath1);
			t6.getMap().put("athList", athList);
//			KonkaMobilePdBrand tt = new KonkaMobilePdBrand();
//			tt.setBrand_id(t6.getBrand_id());
//			tt = super.getFacade().getKonkaMobilePdBrandService().getKonkaMobilePdBrand(tt);
//			t6.getMap().put("brand_name", tt.getBrand_name());
			logger.info("----toJSONString(t6)--->{}", toJSONString(t6));
			super.renderTextJsonOrJsonp(request, response, toJSONString(t6));
			break;
		case 9:
			KonkaMobileSailDatas t9 = new KonkaMobileSailDatas();
			t9.setId(idl);
			t9 = super.getFacade().getKonkaMobileSailDatasService().getKonkaMobileSailDatas(t9);
			super.renderTextJsonOrJsonp(request, response, toJSONString(t9));
			break;

		case 99:
			KonkaMobileSailData t99 = new KonkaMobileSailData();
			t99.setId(idl);
			t99 = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailData(t99);
			// Attachment a99 = new Attachment();
			// a99.setLink_tab("KONKA_MOBILE_SAIL_DATA");
			// a99.setLink_id(t99.getId());
			// a99.setDel_mark(new Short("0"));
			KonkaMobileSailDataBill konkaMobileSailDataBill99 = new KonkaMobileSailDataBill();
			konkaMobileSailDataBill99.setSail_id(t99.getId());
			List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
					.getKonkaMobileSailDataBillService()
					.getKonkaMobileSailDataBillAndAttachmentList(konkaMobileSailDataBill99);

			// List<Attachment> aList99 =
			// super.getFacade().getAttachmentService().getAttachmentAndBillAuditList(a99);
			t99.getMap().put("konkaMobileSailDataBillList", konkaMobileSailDataBillList);
			logger.info("----toJSONString(t1)--->{}", toJSONString(t99));
			super.renderTextJsonOrJsonp(request, response, toJSONString(t99));
			break;
		default:
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"null\"}");
			break;
		}
		return null;
	}

	public ActionForward CheckBillNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String bill_no = (String) dynaBean.get("bill_no");
		// id
		String id = (String) dynaBean.get("id");
		String url = request.getRequestURI();
		//System.out.println("URL------------>" + url);

		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"用户信息出错，请联系管理员！\"}");
			return null;
		}
		KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
		konkaMobileSailDataBill.setBill_no(bill_no);
		konkaMobileSailDataBill.setIs_valid(0);
		List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
				.getKonkaMobileSailDataBillService().getKonkaMobileSailDataBillList(konkaMobileSailDataBill);
		if (null != konkaMobileSailDataBillList && konkaMobileSailDataBillList.size() > 0) {
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"1\", \"msg\":\"该发票编号已经存在！\"}");
		} else {
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"0\", \"msg\":\"该发票编号尚未存在！\"}");
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public ActionForward CheckBillNoS(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String bill_no = (String) dynaBean.get("bill_no");
		// id
		String id = (String) dynaBean.get("id");
		String url = request.getRequestURI();
		//System.out.println("URL------------>" + url);
		//System.out.println("bill_no------------>" + bill_no);

		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderTextJsonOrJsonp(request, response, "{\"status\":\"-1\", \"msg\":\"用户信息出错，请联系管理员！\"}");
			return null;
		}
		if (null == bill_no) {
			return null;
		}
		List<HashMap> list = new ArrayList<HashMap>();
		HashMap map = null;
		String[] bill_nos = bill_no.split(",");
		if (null != bill_nos && bill_nos.length > 0) {
			for (String s : bill_nos) {
				if (StringUtils.isNotEmpty(s)) {
					map = new HashMap();
					KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
					konkaMobileSailDataBill.setBill_no(s);
					konkaMobileSailDataBill.setIs_valid(0);
					List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
							.getKonkaMobileSailDataBillService()
							.getKonkaMobileSailDataBillList(konkaMobileSailDataBill);
					if (null != konkaMobileSailDataBillList && konkaMobileSailDataBillList.size() > 0) {
						map.put(s, konkaMobileSailDataBillList.size());
					} else {
						map.put(s, 0);
					}
					list.add(map);
				}
			}

		}
		super.renderJson(response, JSON.toJSONString(list));
		return null;

		/*
		 * KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
		 * konkaMobileSailDataBill.setBill_no(bill_no); konkaMobileSailDataBill.setIs_valid(0);
		 * List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
		 * .getKonkaMobileSailDataBillService ().getKonkaMobileSailDataBillList(konkaMobileSailDataBill); if (null !=
		 * konkaMobileSailDataBillList && konkaMobileSailDataBillList.size() > 0) { super.renderTextJsonOrJsonp(request,
		 * response, "{\"status\":\"1\", \"msg\":\"该发票编号已经存在！\"}"); } else { super.renderTextJsonOrJsonp(request,
		 * response, "{\"status\":\"0\", \"msg\":\"该发票编号尚未存在！\"}"); } return null;
		 */
	}

	// 算工资
	public ActionForward CalPayment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}
		String startime = (String) dynaBean.get("startime");
		String endtime = (String) dynaBean.get("endtime");
		if (StringUtils.isEmpty(startime) || StringUtils.isEmpty(endtime)) {
			super.renderText(response, "关键信息缺失！");
			return null;
		}

		// 取促销员所在地的基本工资
		KonkaMobilePayment kmp = new KonkaMobilePayment();
		kmp.setDept_id(peProdUser.getDept_id());
		// kmp.setUser_id(peProdUser.getId());//如果基本工资设定到个人的话 去掉此注释
		kmp.setStatus(0);
		kmp = super.getFacade().getKonkaMobilePaymentService().getKonkaMobilePayment(kmp);

		// 取促销员所在地的提成比例
		// KonkaMobileMdPercent kmm = new KonkaMobileMdPercent();
		// kmm.setDept_id(peProdUser.getDept_id());
		// kmm.setStatus(0);
		// List<KonkaMobileMdPercent> mpList = super.getFacade()
		// .getKonkaMobileMdPercentService().getKonkaMobileMdPercentList(
		// kmm);

		// 取该促销员的销售记录明细
		KonkaMobileSailData kms = new KonkaMobileSailData();
		kms.setReport_id(peProdUser.getId());
		kms.getMap().put("startime", startime);
		kms.getMap().put("endtime", endtime);
		List<KonkaMobileSailData> sdList = super.getFacade().getKonkaMobileSailDataService()
				.getKonkaMobileSailDataList(kms);
		Long num = 0l;
		BigDecimal price = new BigDecimal(0);
		BigDecimal deduct = new BigDecimal(0);
		for (KonkaMobileSailData _kms : sdList) {
			num += _kms.getNum();
			if (null != _kms.getAll_price())
				price = price.add(_kms.getAll_price());
			if (null != _kms.getDeduct())
				deduct = deduct.add(_kms.getDeduct());
			// for (KonkaMobileMdPercent _kmm : mpList) {
			// BigDecimal _deduct = new BigDecimal(0);
			// if (_kmm.getPd_id() - _kms.getModel_id() == 0) {
			// _deduct = _kmm.getPercent().multiply(_kms.getAll_price())
			// .divide(new BigDecimal(100));
			// deduct = deduct.add(_deduct);
			// }
			// }
		}

		HashMap<String, String> str = new HashMap<String, String>();
		// 基本工资
		if (kmp != null) {
			str.put("basePayment", kmp.getBase_payment().toString());
		} else {
			str.put("basePayment", "0.0");
		}

		// 销售台数
		str.put("num", num.toString());
		// 销售额
		str.put("price", price.toString());
		// 提成
		str.put("deduct", deduct.toString());
		// 总额
		if (null != kmp && null != kmp.getBase_payment() && null != deduct) {
			str.put("payment", kmp.getBase_payment().add(deduct).toString());
		} else {
			str.put("payment", "0");
		}

		super.renderText(response, JSON.toJSONString(str));
		return null;
	}

	@SuppressWarnings("deprecation")
	protected String generateMonth(Date start, Date end) throws ParseException {
		int yearPl = end.getYear() - start.getYear();
		int monthPl = end.getMonth() - start.getMonth();
		int mPl = yearPl * 12 + monthPl;
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(start);
		StringBuffer outStr = new StringBuffer();
		for (int i = 0; i <= mPl; i++) {
			if (i > 0) {
				outStr.append(" union ");
				rightNow.add(Calendar.MONTH, 1);
			}
			Date rn = rightNow.getTime();
			String str = df2.format(rn);
			outStr.append("select '" + str + "' as dater from dual");
		}
		return outStr.toString();
	}

	// 分析汇总
	public ActionForward GetStatistic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			logger.warn("用户 {}/{} 不存在", username, userpass);
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}
		String startime = (String) dynaBean.get("startime");
		String endtime = (String) dynaBean.get("endtime");
		if (StringUtils.isEmpty(startime) || StringUtils.isEmpty(endtime)) {
			super.renderText(response, "未设置时间区间！");
			return null;
		}
		String type = (String) dynaBean.get("type");
		if (StringUtils.isEmpty(type)) {
			super.renderText(response, "未指定结果集类型！");
			return null;
		}

		Konkamobilesaledatas entity = new Konkamobilesaledatas();
		entity.getMap().put("date_begin", startime);
		entity.getMap().put("date_end", endtime);
		entity.setCxuser(peProdUser.getId());

		if ("1".equals(type))
			entity.getMap().put("selected01", "1");
		if ("2".equals(type))
			entity.getMap().put("selected02", "1");
		if ("3".equals(type)) {
			entity.getMap().put("selected03", "1");
		}

		List<HashMap<String, String>> entityList = new ArrayList<HashMap<String, String>>();
		if ("3".equals(type)) {
			Date start = df1.parse(startime);
			Date end = df1.parse(endtime);
			String strSql = generateMonth(start, end);
			entity.getMap().put("dateSql", strSql);
			entityList = getFacade().getKonkamobilesaledatasService().getKonkamobilesaledatasMobileFlot3(entity);
			super.renderText(response, toJSONString(entityList));
			return null;
		} else {
			if ("1".equals(type)) {
				entity.getMap().put("order_by_salecash_desc", "true");
			}
			entityList = getFacade().getKonkamobilesaledatasService().getKonkamobilesaledatasMobileFlot(entity);
			for (HashMap<String, String> _h : entityList) {
				String i = String.valueOf(_h.get("SALECASH"));
				_h.put("SALECASH", i);
				String j = String.valueOf(_h.get("SALENUM"));
				_h.put("SALENUM", j);
			}
		}
		String datatype = (String) dynaBean.get("datatype");
		if (StringUtils.isNotEmpty(datatype))
			super.renderJsonp(request, response, JSON.toJSONString(entityList));
		else {
			super.renderText(response, toJSONString(entityList));
		}
		return null;
	}

	public ActionForward downloadFile1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DynaBean dynaBean = (DynaBean) form;
		String save_name = (String) dynaBean.get("save_name");
		if (StringUtils.isBlank(save_name)) {
			super.renderJavaScript(response, "window.onload=function(){alert('错误！');history.back();}");
			return null;
		}
		Attachment attachments = new Attachment();
		attachments.setSave_name(save_name);
		List<Attachment> attachmentsList = getFacade().getAttachmentService().getAttachmentList(attachments);

		if (attachmentsList.size() == 0) {
			super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
			return null;
		}

		Attachment peAttachments = attachmentsList.get(0);

		if (peAttachments.getSave_path() == null) {
			super.renderJavaScript(response, "window.onload=function(){alert('文件不存在！');history.back();}");
			return null;
		} else {
			String ctx = super.getCtxPath(request);
			if (!StringUtils.endsWith(ctx, File.separator)) {
				ctx += File.separator;
			}

			response.sendRedirect(ctx + peAttachments.getSave_path());
			return null;

		}
	}

	public ActionForward deleteFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String id = (String) dynaBean.get("id");
		// String username = (String) dynaBean.get("username");
		// // 密码
		// String userpass = (String) dynaBean.get("userpass");
		logger.info("id-------->" + id);
		// logger.info("username-------->" + username);
		// logger.info("userpass-------->" + userpass);
		//
		// PeProdUser peProdUser = checkUser(username, userpass);
		// if (null == peProdUser) {
		// logger.warn("用户 {}/{} 不存在", username, userpass);
		// super.renderText(response, "用户信息出错，请联系管理员！");
		// return null;
		// }

		if (StringUtils.isNotBlank(id) && GenericValidator.isLong(id)) {
			Attachment entity = new Attachment();
			entity.setId(new Long(id));
			super.getFacade().getAttachmentService().removeAttachment(entity);
		}

		super.renderTextOrJsonp(request, response, "success");
		return null;
	}

	public ActionForward deleteFileAndBill(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;

		String bill_id = (String) dynaBean.get("bill_id");
		// String username = (String) dynaBean.get("username");
		// // 密码
		// String userpass = (String) dynaBean.get("userpass");
		logger.info("bill_id-------->" + bill_id);
		// logger.info("username-------->" + username);
		// logger.info("userpass-------->" + userpass);
		//
		// PeProdUser peProdUser = checkUser(username, userpass);
		// if (null == peProdUser) {
		// logger.warn("用户 {}/{} 不存在", username, userpass);
		// super.renderText(response, "用户信息出错，请联系管理员！");
		// return null;
		// }

		if (StringUtils.isNotBlank(bill_id) && GenericValidator.isLong(bill_id)) {
			KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
			konkaMobileSailDataBill.setBill_id(Long.valueOf(bill_id));
			konkaMobileSailDataBill = super.getFacade().getKonkaMobileSailDataBillService()
					.getKonkaMobileSailDataBill(konkaMobileSailDataBill);
			if (null != konkaMobileSailDataBill && konkaMobileSailDataBill.getAdjunct_link_id() != null) {
				Attachment entity = new Attachment();
				entity.setId(new Long(konkaMobileSailDataBill.getAdjunct_link_id()));
				super.getFacade().getAttachmentService().removeAttachment(entity);
			}
			super.getFacade().getKonkaMobileSailDataBillService()
					.removeKonkaMobileSailDataBill(konkaMobileSailDataBill);

		}
		super.renderTextOrJsonp(request, response, "success");
		return null;
	}

	public List<Long> getR3StoreIds(Long user_id, Long role_id) {

		List<Long> store_ids = new ArrayList<Long>();
		if (role_id.intValue() == 60) {// 业务员
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", user_id);
			store.setIs_del(0);
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService()
					.getKonkaR3StoreListWithYwyUserId(store);
			if (storeList != null && storeList.size() > 0) {
				for (KonkaR3Store konkaR3Store : storeList) {
					store_ids.add(konkaR3Store.getStore_id());
				}
			}
		} else if (role_id.intValue() == 188) {// 促销员
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(user_id); // 登录用户ID作为促销员的ID
			storeList = super.getFacade().getKonkaMobileSpRelationService()
					.getKonkaMobileSpRelationInShopNameList(entity);
			if (storeList != null && storeList.size() > 0) {
				for (KonkaMobileSpRelation t : storeList) {
					KonkaR3Store store = new KonkaR3Store();
					if (null != t && null != t.getShop_id()) {
						store.setStore_id(t.getShop_id());
						store.setIs_del(0);
						store = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store);
						if (null != store) {
							store_ids.add(store.getStore_id());
						}
					}

				}
			}
		} else {
			KonkaR3Store store = new KonkaR3Store();
			store.getMap().put("user_id", user_id);
			store.setIs_del(0);
			List<KonkaR3Store> storeList = super.getFacade().getKonkaR3StoreService()
					.getKonkaR3StoreListWithYwyUserId(store);
			if (storeList != null && storeList.size() > 0) {
				for (KonkaR3Store konkaR3Store : storeList) {
					store_ids.add(konkaR3Store.getStore_id());
				}
			}

			List<KonkaMobileSpRelation> storeList1 = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(user_id); // 登录用户ID作为促销员的ID
			storeList1 = super.getFacade().getKonkaMobileSpRelationService()
					.getKonkaMobileSpRelationInShopNameList(entity);
			if (storeList1 != null && storeList1.size() > 0) {
				for (KonkaMobileSpRelation t : storeList1) {
					KonkaR3Store store1 = new KonkaR3Store();
					if (null != t && null != t.getShop_id()) {
						store1.setStore_id(t.getShop_id());
						store1.setIs_del(0);
						store1 = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(store1);
						if (null != store1) {
							store_ids.add(store1.getStore_id());
						}
					}

				}
			}

		}
		//System.out.println("store_ids" + store_ids);

		return store_ids;
	}

}
