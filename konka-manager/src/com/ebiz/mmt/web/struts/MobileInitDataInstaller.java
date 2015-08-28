package com.ebiz.mmt.web.struts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.KonkaMobileCategory;
import com.ebiz.mmt.domain.KonkaMobileMdPercent;
import com.ebiz.mmt.domain.KonkaMobilePd;
import com.ebiz.mmt.domain.KonkaMobileSpRelation;
import com.ebiz.mmt.domain.MobileList;
import com.ebiz.mmt.domain.MobileSelectItem;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.SysSetting;
import com.ebiz.mmt.service.Facade;

public class MobileInitDataInstaller {

	protected static final Logger logger = LoggerFactory.getLogger(MobileInitDataInstaller.class);

	public String[] size_strs = { "24", "26", "28", "29", "32", "37", "39", "40", "42", "43", "46", "47", "48", "50",
			"55", "58", "60", "65", "84" };

	private final String init_data_key = "newData";

	public void refresh(ServletContext ctx, Facade facade, PeProdUser peProdUser, String type) {
		// 卸载
		this.uninstall(ctx);

		// 装载
		if (null != type && "1".equals(type)) {
			ctx.setAttribute(init_data_key, toJSONString(this.getMobileListOfStore(facade, peProdUser)));
		} else {
			ctx.setAttribute(init_data_key, toJSONString(this.getBaseData(facade, peProdUser, type)));
		}
	}

	public void refresh(ServletContext ctx, Facade facade, PeProdUser peProdUser) {
		// 卸载
		this.uninstall(ctx);
		// 装载
		this.install(ctx, facade);
	}

	public void uninstall(ServletContext ctx) {
		if (ctx.getAttribute(init_data_key) != null) {
			ctx.removeAttribute(init_data_key);
		}
	}

	public void install(ServletContext ctx, Facade facade) {
		if (ctx.getAttribute(init_data_key) == null) {
			ctx.setAttribute(init_data_key, toJSONString(this.getBaseData(facade, null, null)));
		}
	}

	/**
	 * 获取基础数据版本号
	 * 
	 * @return
	 */
	public Long getDataBaseVer(Facade facade) {
		SysSetting ss = new SysSetting();
		ss.setTitle("datapatch");
		ss = facade.getSysSettingService().getSysSetting(ss);
		Long _dataPatch = 01222333233l;
		if (ss != null)
			_dataPatch = Long.parseLong(ss.getContent());
		return _dataPatch;
	}

	/**
	 * 获取基础数据
	 * 
	 * @param serverDataVersion
	 *            基础数据版本号，从Sys_Setting表获取
	 * @param peProdUser
	 *            当前登录用户对象
	 * @param type
	 *            基础数据类型，1表示门店
	 * @param response
	 * @return
	 */
	public MobileList getMobileListOfStore(Facade facade, PeProdUser peProdUser) {
		MobileList _entity = new MobileList();
		// 设置时间戳标记
		_entity.setDataTimestamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

		// 设置数据版本号
		Long v = getDataBaseVer(facade);
		logger.info("Get mobile data : current version {}.", v);
		_entity.setDataPatch(null == v ? "" : String.valueOf(getDataBaseVer(facade)));
		// 初始化用户的门店基础数据
		List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
		KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
		entity.setSeller_id(peProdUser.getId());
		storeList = facade.getKonkaMobileSpRelationService().getKonkaMobileSpRelationInShopNameList(entity);

		List<MobileSelectItem> _eList = new ArrayList<MobileSelectItem>();

		for (KonkaMobileSpRelation t : storeList) {
			MobileSelectItem _t = new MobileSelectItem();
			_t.setId(t.getShop_id().toString());
			if (t.getMap().get("store_name") != null)
				_t.setName(t.getMap().get("store_name").toString());
			else {
				_t.setName("名称维护有误的门店");
			}
			_eList.add(_t);
		}
		_entity.setStoreList(_eList);
		logger.info("Get mobile data : Store list.");

		// 初始化用户的提成比例/金额
		List<MobileSelectItem> mmpList = new ArrayList<MobileSelectItem>();
		KonkaMobileMdPercent mmp = new KonkaMobileMdPercent();
		mmp.setDept_id(peProdUser.getDept_id());
		mmp.setStatus(0);
		List<KonkaMobileMdPercent> _mmpList = facade.getKonkaMobileMdPercentService().getKonkaMobileMdPercentList(mmp);
		for (KonkaMobileMdPercent _mmp : _mmpList) {
			MobileSelectItem _t = new MobileSelectItem();
			_t.setId(_mmp.getId().toString());
			_t.setName(_mmp.getPercent().toString());
			_t.setAddon1(_mmp.getType().toString());
			_t.setAddon2(_mmp.getPd_id().toString());
			mmpList.add(_t);
		}
		_entity.setPeList(mmpList);
		logger.info("Get mobile data : Model percent list.");

		return _entity;
	}

	/**
	 * 获取基础数据
	 * 
	 * @param serverDataVersion
	 *            基础数据版本号，从Sys_Setting表获取
	 * @param peProdUser
	 *            当前登录用户对象
	 * @param type
	 *            基础数据类型，1表示门店
	 * @param response
	 * @return
	 */
	public MobileList getBaseData(Facade facade, PeProdUser peProdUser, String type) {
		MobileList _entity = new MobileList();

		// 设置时间戳标记
		_entity.setDataTimestamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

		// 设置数据版本号
		Long v = getDataBaseVer(facade);
		logger.info("Get mobile data : current version {}.", v);
		_entity.setDataPatch(null == v ? "" : String.valueOf(getDataBaseVer(facade)));

		List<MobileSelectItem> _eList;

		if (null != peProdUser) {
			// 初始化用户的门店基础数据
			List<KonkaMobileSpRelation> storeList = new ArrayList<KonkaMobileSpRelation>();
			KonkaMobileSpRelation entity = new KonkaMobileSpRelation();
			entity.setSeller_id(peProdUser.getId());
			storeList = facade.getKonkaMobileSpRelationService().getKonkaMobileSpRelationInShopNameList(entity);

			_eList = new ArrayList<MobileSelectItem>();

			for (KonkaMobileSpRelation t : storeList) {
				MobileSelectItem _t = new MobileSelectItem();
				_t.setId(t.getShop_id().toString());
				if (t.getMap().get("store_name") != null)
					_t.setName(t.getMap().get("store_name").toString());
				else {
					_t.setName("名称维护有误的门店");
				}
				_eList.add(_t);
			}
			_entity.setStoreList(_eList);
			logger.info("Get mobile data : Store list.");

			// 初始化用户的提成比例/金额
			List<MobileSelectItem> mmpList = new ArrayList<MobileSelectItem>();
			KonkaMobileMdPercent mmp = new KonkaMobileMdPercent();
			mmp.setDept_id(peProdUser.getDept_id());
			mmp.setStatus(0);
			List<KonkaMobileMdPercent> _mmpList = facade.getKonkaMobileMdPercentService().getKonkaMobileMdPercentList(
					mmp);
			for (KonkaMobileMdPercent _mmp : _mmpList) {
				MobileSelectItem _t = new MobileSelectItem();
				_t.setId(_mmp.getId().toString());
				_t.setName(_mmp.getPercent().toString());
				_t.setAddon1(_mmp.getType().toString());
				_t.setAddon2(_mmp.getPd_id().toString());
				mmpList.add(_t);
			}
			_entity.setPeList(mmpList);
			logger.info("Get mobile data : Model percent list.");
		}

		if (StringUtils.isNotBlank(type) && "1".equals(type)) {
			// super.renderText(response, toJSONString(_entity));
			return _entity;
		}

		// 初始化品牌基础数据 竞品
		_eList = new ArrayList<MobileSelectItem>();
		KonkaMobilePd t1 = new KonkaMobilePd();

		List<KonkaMobilePd> brandList = facade.getKonkaMobilePdService().getKonkaMobilePdList(t1);

		for (KonkaMobilePd _m : brandList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getId().toString());
			_mm.setName(_m.getBrand_name() + _m.getMd_name().toString());
			_mm.setAddon1(_m.getBrand_id().toString());
			_mm.setAddon2(_m.getBrand_name());
			_eList.add(_mm);
		}
		_entity.setBrandList(_eList);

		logger.info("Get mobile data : Brand list.");

		// 初始化意见反馈基础数据
		_eList = new ArrayList<MobileSelectItem>();
		KonkaMobileCategory t = new KonkaMobileCategory();
		t.setC_type(7);
		List<KonkaMobileCategory> ideaList = facade.getKonkaMobileCategoryService().getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _m : ideaList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getC_index().toString());
			_mm.setName(_m.getC_name().toString());
			_eList.add(_mm);
		}
		_entity.setIdeaList(_eList);

		logger.info("Get mobile data : Idea list.");

		// 初始化退货原因基础数据
		_eList = new ArrayList<MobileSelectItem>();
		t = new KonkaMobileCategory();
		t.setC_type(6);
		List<KonkaMobileCategory> backList = facade.getKonkaMobileCategoryService().getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _m : backList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getC_index().toString());
			_mm.setName(_m.getC_name().toString());
			_eList.add(_mm);
		}
		_entity.setBackList(_eList);

		logger.info("Get mobile data : Back list.");

		// 初始化物料基础数据
		_eList = new ArrayList<MobileSelectItem>();
		t = new KonkaMobileCategory();
		t.setC_type(4);
		List<KonkaMobileCategory> goodList = facade.getKonkaMobileCategoryService().getKonkaMobileCategoryList(t);
		for (KonkaMobileCategory _m : goodList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getC_index().toString());
			_mm.setName(_m.getC_name().toString());
			_eList.add(_mm);
		}
		_entity.setGoodList(_eList);

		logger.info("Get mobile data : Good list.");

		// 初始化产品基础数据

		List<MobileSelectItem> modelList = new ArrayList<MobileSelectItem>();
		List<PePdModel> _modelList = new ArrayList<PePdModel>();
		PePdModel m = new PePdModel();
		m.setIs_del(0);
		_modelList = facade.getPePdModelService().getPePdModelPaginatedList(m);
		for (PePdModel _m : _modelList) {
			MobileSelectItem _mm = new MobileSelectItem();
			_mm.setId(_m.getPd_id().toString());
			_mm.setName(_m.getMd_name().toString());
			modelList.add(_mm);
		}
		_entity.setModelList(modelList);

		logger.info("Get mobile data : Model list.");

		// 初始化尺寸基础数据
		List<MobileSelectItem> sizeList = new ArrayList<MobileSelectItem>();
		for (String str : size_strs) {
			MobileSelectItem _t = new MobileSelectItem();
			_t.setId(str);
			_t.setName(str);
			sizeList.add(_t);
		}
		_entity.setSizeList(sizeList);

		logger.info("Get mobile data : Size list.");

		// 初始化品类基础数据
		List<MobileSelectItem> plList = getClazz(facade);
		_entity.setPlList(plList);

		logger.info("Get mobile data : Product Class list.");

		return _entity;
	}

	// 取品类
	public List<MobileSelectItem> getClazz(Facade facade) {
		// String[] cls_ids = { "1010100", "1010200", "1010300", "1010400",
		// "1010500" };
		List<BasePdClazz> baseList = facade.getRetailMsBaseService().getKonkaBasePdClazzListByClsIds();

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

	public String getInit_data_key() {
		return init_data_key;
	}

}
