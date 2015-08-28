package com.ebiz.mmt.web.struts.manager.zmd;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaXxBaseType;
import com.ebiz.mmt.domain.KonkaXxPd;
import com.ebiz.mmt.domain.KonkaXxSellBill;
import com.ebiz.mmt.domain.KonkaXxSellBillDetails;
import com.ebiz.mmt.domain.KonkaXxStdStore;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdPdStore;
import com.ebiz.mmt.domain.KonkaXxZmdStore;
import com.ebiz.mmt.domain.MaterialObject;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.service.impl.InteractWebServiceImpl;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Jiang,JiaYong
 * @version 2012-1-9
 */
public class BaseZmdAction extends BaseAction {
	protected static final Logger logger = LoggerFactory.getLogger(BaseZmdAction.class);

	private static HashMap<String, String> properties = new HashMap<String, String>();

	private static final Double default_storge = 10000D;

	private static final boolean enable_call_konka_r3_interface = false;

	private static final String __GetMaterialStockListByArray = "getMaterialStockListByArray";

	private static String SERVER_ADDRESS;

	private static String NAMESPACE_URI;

	private static RPCServiceClient WEB_SERVICE_CLIENT;

	static {
		InputStream inputStream = InteractWebServiceImpl.class.getClassLoader().getResourceAsStream(
				"webservice-url.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Object key : p.keySet()) {
			properties.put((String) key, (String) p.get(key));
		}

		if (null == SERVER_ADDRESS) {
			SERVER_ADDRESS = properties.get("kongka.kc.webservice");
		}
		if (null == NAMESPACE_URI) {
			NAMESPACE_URI = properties.get("kongka.kc.qname");
		}

		EndpointReference targetEPR = new EndpointReference(SERVER_ADDRESS);

		if (WEB_SERVICE_CLIENT == null) {
			try {
				WEB_SERVICE_CLIENT = new RPCServiceClient();
			} catch (AxisFault e) {
				logger.error("Create RPCServiceClient Exception.");
			}
		}

		Options options = new Options();
		options.setTo(targetEPR);
		options.setCallTransportCleanup(true);
		WEB_SERVICE_CLIENT.setOptions(options);
	}

	/**
	 * @author Hu, hao
	 * @version 2012-01-09
	 * @desc 根据par_id取KONKA_XX_BASE_TYPE【基础类型表】中基础信息，保存【"baseTypesList" +
	 *       par_id】至request
	 */
	protected void setBaseTypeListByParIdToRequest(Long par_id, HttpServletRequest request) {
		request.setAttribute("baseTypesList".concat(par_id.toString()), getBaseTypeListByParId(par_id));
	}

	protected List<KonkaXxBaseType> getBaseTypeListByParId(Long par_id) {
		KonkaXxBaseType baseTypes = new KonkaXxBaseType();
		baseTypes.setPar_id(par_id);
		baseTypes.setIs_del(0);
		return getFacade().getKonkaXxBaseTypeService().getKonkaXxBaseTypeList(baseTypes);
	}

	protected Long getStocks2(String md_name, Long zmd_id) throws MalformedURLException, RemoteException,
			ServiceException {
		Long ret = 0L;
		// 如果md_name为空，返回库存量为0。
		if (StringUtils.isBlank(md_name)) {
			return ret;
		}
		// 根据zmd_id查找dept_id
		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setZmd_id(zmd_id);
		konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
		// 该专卖店不存在，返回库存量为0
		if (konkaXxZmd == null) {
			return ret;
		}
		// 专卖店到KONKAXX_ZMD_STORE找factory_id和store_id
		KonkaXxZmdStore kkXxZmdStore = new KonkaXxZmdStore();
		kkXxZmdStore.setZmd_id(zmd_id);
		List<KonkaXxZmdStore> kkXxZmdStoreList = super.getFacade().getKonkaXxZmdStoreService().getKonkaXxZmdStoreList(
				kkXxZmdStore);
		String[] factoryStores;
		// 如果专卖店在KONKAXX_ZMD_STORE没有对应的factory_id和store_id，返回库存量为0
		if (null == kkXxZmdStoreList || kkXxZmdStoreList.size() == 0) {
			return ret;
		}

		for (KonkaXxZmdStore entity : kkXxZmdStoreList) {
			// 调用接口,累加该专卖店所有factory_id和store_id所对应的库存量总和
			ret = ret + getStocks(md_name, entity.getFactory_id(), entity.getStore_id());
		}
		factoryStores = new String[kkXxZmdStoreList.size()];
		for (int i = 0; i < kkXxZmdStoreList.size(); i++) {
			factoryStores[i] = kkXxZmdStoreList.get(i).getFactory_id() + kkXxZmdStoreList.get(i).getStore_id();
		}

		// 专卖店>>>>对应分公司--->销售单锁定的库存
		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		// konkaXxSellBillDetails.getMap().put("dept_id",
		// konkaXxZmd.getDept_id());
		konkaXxSellBillDetails.getMap().put("factoryStores", factoryStores);
		konkaXxSellBillDetails.setMd_name(md_name);
		Long lockRet = super.getFacade().getKonkaXxSellBillDetailsService().getKonkaXxSellBillDetailsForKcCount(
				konkaXxSellBillDetails);

		// ★[实时库存查询公式] 某专卖店某产品型号实时库存 = 专卖店多库位的库存和 - 专卖店销售单锁定的库存（且分公司未进行库位物流分派的）
		// - 专卖店保留库存数量；
		Long retReturn = ret - lockRet - konkaXxZmd.getReserverd_stock();
		return retReturn;
	}

	protected Boolean verifyStocks(String md_name, Long zmd_id, Long counts, String v) throws MalformedURLException,
			RemoteException, ServiceException {
		Boolean ret = false;
		Long retCounts = this.getStocks2(md_name, zmd_id);
		if ("1".equals(v)) {
			retCounts = retCounts + counts;
		}

		// 库存量 > counts，返回为true。
		if (retCounts.compareTo(counts) >= 0) {
			ret = true;
		}
		return ret;
	}

	/**
	 * @author Li,Yuan
	 * @param md_name
	 * @param factory_id
	 * @param store_id
	 * @return 康佳的库存量
	 * @throws ServiceException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	protected Long getStocks(String md_name, String factory_id, String store_id) throws ServiceException,
			MalformedURLException, RemoteException {
		MaterialObject materialObject = new MaterialObject();
		materialObject.setMaterial(md_name);
		materialObject.setPlant(factory_id);
		materialObject.setStorage(store_id);

		MaterialObject[] materialObjects = new MaterialObject[] { materialObject };

		materialObjects = callWebServiceGetMaterialStockListByArray(materialObjects);
		if (null != materialObjects && materialObjects.length > 0) {
			return Long.valueOf(materialObjects[0].getLabst().intValue());
		}
		return 0L;
	}

	/**
	 * <b>Description : Query a product's stocks what the shop can access.</b>
	 * 
	 * @Author Xing,XiuDong
	 * @Date 2012-04-13
	 */
	protected Long getStocks(String md_name, Long zmd_id) throws AxisFault {
		if (null == md_name || null == zmd_id) {
			throw new NullPointerException();
		}

		Long ret = 0L;

		KonkaXxZmd konkaXxZmd = new KonkaXxZmd();
		konkaXxZmd.setZmd_id(zmd_id);
		konkaXxZmd = super.getFacade().getKonkaXxZmdService().getKonkaXxZmd(konkaXxZmd);
		// 该专卖店不存在，返回库存量为0
		if (konkaXxZmd == null) {
			return ret;
		}
		// Integer reserverd_stock = konkaXxZmd.getReserverd_stock();

		KonkaXxPd konkaXxPd = getKonkaXxPdWithStocks(md_name, zmd_id);

		for (KonkaXxZmdPdStore store : konkaXxPd.getKonkaXxZmdPdStoreList()) {
			ret += store.getStock_count();
		}

		// return (ret - reserverd_stock);//不减去库存保留量，页面已有相关验证
		return ret;
	}

	/**
	 * <b>Description : Query a product's real stocks and locked stocks in the
	 * factory store which the shop can access.</b>
	 * 
	 * @Author Xing,XiuDong
	 * @Date 2012-04-13
	 */
	protected KonkaXxPd getKonkaXxPdWithStocks(String md_name, Long zmd_id) throws AxisFault {
		List<KonkaXxPd> konkaXxPdList = new ArrayList<KonkaXxPd>();
		konkaXxPdList.add(new KonkaXxPd(md_name));
		konkaXxPdList = getKonkaXxPdListWithStocks(konkaXxPdList, zmd_id);
		return konkaXxPdList.get(0);
	}

	/**
	 * <b>Description : Query product list real stocks and locked stocks in the
	 * factory store which the shop can access.</b>
	 * 
	 * @Author Xing,XiuDong
	 * @Date 2012-04-13
	 */
	protected List<KonkaXxPd> getKonkaXxPdListWithStocks(List<KonkaXxPd> pdList, Long zmd_id) throws AxisFault {

		
		if (null == pdList || pdList.size() == 0) {
			throw new NullPointerException();
		} 

		KonkaXxZmdStore kkXxZmdStore = new KonkaXxZmdStore();
		kkXxZmdStore.setZmd_id(zmd_id);
		kkXxZmdStore.setFactory_id(pdList.get(0).getFac_sn());
		kkXxZmdStore.setStore_id(pdList.get(0).getStore_sn());
		List<KonkaXxZmdStore> kkXxZmdStoreList = getFacade().getKonkaXxZmdStoreService().getKonkaXxZmdStoreList(
				kkXxZmdStore);
		if (null == kkXxZmdStoreList || kkXxZmdStoreList.size() == 0) {
			return null;
		}

		List<KonkaXxStdStore> stdStoreList = new ArrayList<KonkaXxStdStore>();
		for (KonkaXxZmdStore zmdStore : kkXxZmdStoreList) {
			KonkaXxStdStore stdstore = new KonkaXxStdStore();
			stdstore.setFactory_id(zmdStore.getFactory_id());
			stdstore.setStore_id(zmdStore.getStore_id());

			stdStoreList.add(stdstore);
		}

		return getKonkaXxPdListWithStocks(pdList, stdStoreList);
	}

	/**
	 * <b>Description : Query stocks of every product in the list, which in all
	 * of factory store.</b>
	 * 
	 * @Author Xing,XiuDong
	 * @Date 2012-04-13
	 */
	protected List<KonkaXxPd> getKonkaXxPdListWithStocks(List<KonkaXxPd> pdList, List<KonkaXxStdStore> stdStoreList)
			throws AxisFault {
		if (null == pdList || pdList.size() == 0) {
			throw new NullPointerException();
		}

		if (null == stdStoreList || stdStoreList.size() == 0) {
			return null;
		}

		MaterialObject[] materialObjectArray = new MaterialObject[stdStoreList.size() * pdList.size()];

		List<MaterialObject> objList = new ArrayList<MaterialObject>();
		// 注册产品型号和仓位的对应关系
		for (KonkaXxPd konkaXxPd : pdList) {
			String md_name = konkaXxPd.getMd_name();
			if (null == md_name) {
				continue;
			}

			for (int i = 0; i < stdStoreList.size(); i++) {
				// 循环遍历专卖店所拥有的仓位
				KonkaXxStdStore stdStore = stdStoreList.get(i);

				MaterialObject materialObject = new MaterialObject();
				materialObject.setMaterial(md_name);
				materialObject.setPlant(stdStore.getFactory_id());
				materialObject.setStorage(stdStore.getStore_id());

				// materialObjectArray[i] = materialObject;
				objList.add(materialObject);
			}

			konkaXxPd.setKonkaXxZmdPdStoreList(new ArrayList<KonkaXxZmdPdStore>());
		}

		for (int j = 0; j < objList.size(); j++) {
			materialObjectArray[j] = objList.get(j);
		}

		// Call Interface start...
		materialObjectArray = callWebServiceGetMaterialStockListByArray(materialObjectArray);

		for (KonkaXxPd konkaXxPd : pdList) {
			if (null == konkaXxPd.getMd_name()) {
				continue;
			}

			for (MaterialObject mo : materialObjectArray) {
				String md_name = mo.getMaterial();

				if (StringUtils.equals(md_name, konkaXxPd.getMd_name())) {
					KonkaXxZmdPdStore store = new KonkaXxZmdPdStore();
					store.setMd_name(md_name);

					store.setFactory_id(mo.getPlant());
					store.setStore_id(mo.getStorage());

					// 工厂仓位实际库存量
					store.setReal_stock_count(Long.valueOf(mo.getLabst().intValue()));

					// 查询某产品在某仓位已锁定的库存量
					String[] factoryStores = new String[] { store.getFactory_id() + store.getStore_id() };

					KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
					konkaXxSellBillDetails.getMap().put("factoryStores", factoryStores);
					konkaXxSellBillDetails.setMd_name(md_name);
					Long locked_stocks = getFacade().getKonkaXxSellBillDetailsService()
							.getKonkaXxSellBillDetailsForKcCount(konkaXxSellBillDetails);

					// 工厂仓位已锁定的库存量
					store.setLocked_stock_count(locked_stocks);

					// 系统可用库存量
					store.setStock_count(store.getReal_stock_count() - store.getLocked_stock_count());

					konkaXxPd.getKonkaXxZmdPdStoreList().add(store);
				}
			}
		}

		if (logger.isInfoEnabled()) {
			String pattern = "Query Result - [ md_name : {}, factory : {}, store : {}, real_stocks : {}, locked_stock_count : {}, stock_count : {} ]";
			for (KonkaXxPd pd : pdList) {
				for (KonkaXxZmdPdStore store : pd.getKonkaXxZmdPdStoreList()) {
					logger.info(pattern, new String[] { store.getMd_name(), store.getFactory_id(), store.getStore_id(),
							String.valueOf(store.getReal_stock_count()), String.valueOf(store.getLocked_stock_count()),
							String.valueOf(store.getStock_count()) });
				}
			}
		}

		return pdList;
	}

	/**
	 * <b>Call Konka R3 Interface to get factory storage stockc count.</b>
	 * 
	 * @Author Xing,XiuDong
	 * @Date 2012-04-13
	 */
	@SuppressWarnings("unused")
	protected MaterialObject[] callWebServiceGetMaterialStockListByArray(MaterialObject[] materialObjects)
			throws AxisFault {
		if (null == materialObjects) {
			throw new NullPointerException();
		}

		if (materialObjects.length == 0) {
			logger.warn("Parameter 'materialObjects' is blank.");
			return null;
		}

		logger.info("Enable_call_konka_r3_interface : {}", enable_call_konka_r3_interface);
		if (!enable_call_konka_r3_interface) {
			for (MaterialObject obj : materialObjects) {
				obj.setLabst(default_storge);
			}
			return materialObjects;
		}

		QName opName = new QName(NAMESPACE_URI, __GetMaterialStockListByArray);

		if (logger.isInfoEnabled()) {
			for (MaterialObject one : materialObjects) {
				String[] paramArr = new String[] { one.getMaterial(), one.getPlant(), one.getStorage() };
				logger.info("Server Ready Parameters - [ material : {}, plant : {}, storage : {} ]", paramArr);
			}
		}

		logger.info("Calling interface starting through url : {}", SERVER_ADDRESS);
		Date d1 = new Date();
		MaterialObject[] retArrayMaterialObject = (MaterialObject[]) WEB_SERVICE_CLIENT.invokeBlocking(opName,
				new Object[] { materialObjects }, new Class[] { MaterialObject[].class })[0];
		Date d2 = new Date();
		logger.info("Call end. Time cost : {} ms.", d2.getTime() - d1.getTime());

		if (logger.isInfoEnabled()) {
			for (MaterialObject one : retArrayMaterialObject) {
				if (null == one.getLabst()) {
					continue;
				}
				String[] paramArr = new String[] { one.getMaterial(), one.getPlant(), one.getStorage(),
						one.getLabst().toString() };
				logger.info("Server Return - [ material : {}, plant : {}, storage : {}, labst : {} ]", paramArr);
			}
		}

		return retArrayMaterialObject;
	}

	/**
	 * @author Li,Yuan
	 * @param md_name
	 * @param factory_id
	 * @param store_id
	 * @return 返回本地可用的某个产品在某个仓库的库存量
	 * @throws ServiceException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	protected Long getStocksRealTime(String md_name, String factory_id, String store_id) throws ServiceException,
			MalformedURLException, RemoteException {

		// 2012-03-03 接口拒绝访问 // Long ret = 100L; // return ret;

		Long real_stocks = 0L;
		// call interface
		real_stocks = this.getStocks(md_name, factory_id, store_id);

		KonkaXxSellBillDetails konkaXxSellBillDetails = new KonkaXxSellBillDetails();
		konkaXxSellBillDetails.getMap().put("factoryStores", new String[] { factory_id + store_id });
		konkaXxSellBillDetails.setMd_name(md_name);
		Long lockRet = super.getFacade().getKonkaXxSellBillDetailsService().getKonkaXxSellBillDetailsForKcCount(
				konkaXxSellBillDetails);

		logger.info("Results - {}_{}_{}'s Stocks : {}; [real stocks : {}, locked stocks : {}]", new String[] {
				factory_id, store_id, md_name, String.valueOf(real_stocks - lockRet), String.valueOf(real_stocks),
				String.valueOf(lockRet) });

		return real_stocks - lockRet;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-01-10
	 * @desc 获取所有专卖店的分公司
	 */
	protected List<KonkaDept> getKonkaDepts() {
		List<KonkaDept> depts = new ArrayList<KonkaDept>();
		KonkaDept dept = new KonkaDept();
		dept.setDept_type(3); // 1:总部；2：事业部；3：分公司
		dept.setPar_id(0L);
		dept.getMap().put("order_by_dept_name", true);
		depts = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);

		return depts;

	}

	/**
	 * @author Hu,Hao
	 * @version 2013-06-19
	 * @desc 获取用户所有的角色
	 */
	protected String getRoleIds(Long user_id) {
		String roleIds = ",";
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_id);
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		if (peRoleUserList.size() > 0) {
			for (PeRoleUser temp : peRoleUserList) {
				if (null != temp.getRole_id()) {
					roleIds = roleIds + temp.getRole_id() + ",";
				}
			}
		}

		return roleIds;
	}

	/**
	 * @author Wu, ShangLong
	 * @version 2012-03-08
	 * @desc 根据sell_bill_id取KONKA_XX_SELL_BILL【订单表】中当前订单状态
	 */
	protected void setOrderNavigationRequest(Long sell_bill_id, HttpServletRequest request) {
		KonkaXxSellBill bill = new KonkaXxSellBill();
		bill.setSell_bill_id(sell_bill_id);

		bill = super.getFacade().getKonkaXxSellBillService().getKonkaXxSellBill(bill);

		request.setAttribute("bill", bill);
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-04-06
	 * @param encoding
	 *            编码，用GBK图形才能正常显示
	 */
	protected void renderXmlWithEncoding(HttpServletResponse response, String text, String encoding) {
		render(response, text, "text/xml;charset=".concat(encoding));
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-22
	 * @desc 根据用户ID和角色ID来判断是否是符合的角色 --固定角色
	 */
	protected Boolean getRoleIdFlag(Long user_id, Long role_id) {
		Boolean flag = false;
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_id);
		List<PeRoleUser> entityList = super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		if (entityList.size() > 0) {
			for (PeRoleUser temp : entityList) {
				if (temp.getRole_id() == role_id) {
					flag = true;
				}
			}
		}

		return flag;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-22
	 * @desc 根据用户ID和角色ID来判断是否是符合的角色 --角色范围
	 */
	protected Boolean getRoleIdFlag(Long user_id, Long role_id_s, Long role_id_e) {
		Boolean flag = false;
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_id);
		List<PeRoleUser> entityList = super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		if (entityList.size() > 0) {
			for (PeRoleUser temp : entityList) {
				if (temp.getRole_id() > role_id_s && temp.getRole_id() < role_id_e) {
					flag = true;
				}
			}
		}

		return flag;
	}

	/**
	 * @author Hu,Hao
	 * @version 2013-07-22
	 * @desc 根据用户ID，取用户的所有角色
	 */
	protected List<PeRoleUser> getPeRoleList(Long user_id) {

		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_id);
		List<PeRoleUser> entityList = super.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);

		return entityList;
	}

	/**
	 * @authur xingxiudong
	 * @desc 根据客户ID查询业务员用户对象
	 */
	protected PeProdUser getYwyOfCustomerByCustomerId(Long cust_id) {
		if (null == cust_id) {
			return null;
		}

		logger.info("****************************** cust_id : {}", cust_id);

		BranchAssign t = new BranchAssign();
		t.setKonka_r3_id(cust_id);
		// 根据客户分配的结果查询业务员， 一个客户只能属于一个业务员
		t = getFacade().getBranchAssignService().getBranchAssign(t);

		if (null == t) {
			return null;
		}

		try {
			logger.info("****************************** User : {}", BeanUtils.describe(t));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Long user_id = t.getUser_id(); // 业务员ID
		if (null == user_id) {
			return null;
		}

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(user_id);
		_peRoleUser.setRole_id(60L);
		if (0L == this.getFacade().getPeRoleUserService().getPeRoleUserCount(_peRoleUser)) {
			return null;
		}

		PeProdUser peProdUser = new PeProdUser();
		peProdUser.setId(user_id);
		peProdUser = getFacade().getPeProdUserService().getPeProdUser(peProdUser);

		return peProdUser;
	}
}
