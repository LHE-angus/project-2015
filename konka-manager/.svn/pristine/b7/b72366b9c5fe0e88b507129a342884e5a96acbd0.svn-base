package com.ebiz.mmt.web.struts;


/**
 * @author Wu,Yang
 * @version 2010-04-22
 */
public abstract class BaseSecurityAction extends BaseAction {
	//
	// protected Short short0 = new Short("0");
	//
	// protected Short short1 = new Short("1");
	//
	// protected Short short6 = new Short("6");
	//
	// protected Short short7 = new Short("7");
	//
	// protected Short short8 = new Short("8");
	//
	// protected Short short9 = new Short("9");
	//
	// protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	//
	// /**
	// * 定义哪些角色可以访问
	// *
	// * @param request
	// * @param role_types 允许的角色
	// * @param mod_index 栏目的mod_index
	// * @return
	// * @throws Exception
	// * @modify Wanghao 20100615 对权限表BGUSERPOPDOM中进行判断
	// * @modify Wanghao 20100708 去除角色权限判断
	// */
	// public boolean isSecurity(HttpServletRequest request, Integer mod_index) throws Exception {
	// return this.isSecurity(request, null, mod_index);
	// }
	//
	// public boolean isSecurity(HttpServletRequest request, String popdom, Integer mod_index) throws Exception {
	//
	// HttpSession session = request.getSession();
	// EntpUser entpUser = (EntpUser) session.getAttribute(Constants.ENTP_USER);
	//
	// boolean isAllowed = false;
	// Bguserpopdom bguserpopdom = new Bguserpopdom();
	// bguserpopdom.setUser_id(entpUser.getUser_id().toString());
	// bguserpopdom.setMod_index(mod_index.toString());
	// List<Bguserpopdom> bguserpopdomList = super.getFacade().getBguserpopdomService()
	// .getBguserpopdomList(bguserpopdom);
	// if (bguserpopdomList.size() > 0) {
	// isAllowed = true;
	// if (null != popdom) {
	// if (StringUtils.indexOf(bguserpopdomList.get(0).getPopedom(), popdom) == -1) {
	// isAllowed = false;
	// }
	// }
	// request.setAttribute("popedom", bguserpopdomList.get(0).getPopedom());
	// }
	//
	// return isAllowed;
	// }
	//
	// /**
	// * 转向没有权限的提示
	// *
	// * @throws Exception
	// */
	// public ActionForward forwardNoPermission(HttpServletRequest request, HttpServletResponse response) throws
	// Exception {
	// String msg = super.getMessage(request, "errors.permission");
	// super.renderJavaScript(response, "alert('".concat(msg).concat("');history.back();"));
	// return null;
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-23
	// * @desc 产品大类(树)
	// */
	// public void setPdTypeTreeListToSession(HttpServletRequest request) {
	// List<PdType> pdTypeTreeList = getFacade().getPdTypeService().getPdTypeTreeList(new PdType());
	// request.setAttribute("pdTypeTreeList", pdTypeTreeList);
	//
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-23
	// * @desc 产品品牌
	// */
	// public void setPdBrandListToSession(HttpServletRequest request) {
	// PdBrand pdBrand = new PdBrand();
	// pdBrand.setDel_mark(0);
	// List<PdBrand> pdBrandList = getFacade().getPdBrandService().getPdBrandList(pdBrand);
	// request.setAttribute("pdBrandList", pdBrandList);
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-23
	// * @desc 从本地取凭证号，判断本地凭证是否存在
	// */
	// public boolean validateVoucherCodeInLocal(String voucher_code) {
	// boolean isExist = false;
	// if (voucher_code.length() > 10) {
	// int endIndex = voucher_code.length();
	// int beginIndex = voucher_code.length() - 10;
	// String voucher_serial = voucher_code.substring(beginIndex, endIndex);
	// VoucherListGov voucherListGov = new VoucherListGov();
	// voucherListGov.getMap().put("voucher_serial", voucher_serial);
	// Long count = getFacade().getVoucherListGovService().getVoucherListGovCount(voucherListGov);
	// if (count > 0) {
	// isExist = true;
	// }
	// }
	//
	// return isExist;
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-26
	// */
	// public void setBaseProvinceListToSession(HttpServletRequest request) {
	// BaseProvinceList baseProvinceList = new BaseProvinceList();
	// baseProvinceList.setP_level(1);// 第一级省列表
	// baseProvinceList.setDel_mark(0);
	// List<BaseProvinceList> baseProveinceListList = getFacade().getBaseProvinceListService()
	// .getBaseProvinceListList(baseProvinceList);
	// request.setAttribute("baseProveinceListList", baseProveinceListList);
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-26
	// * @desc 产品大类
	// */
	// public void setPdTypeListToSession(HttpServletRequest request) {
	// PdType pdType = new PdType();
	// pdType.setDel_mark(0);
	// pdType.setPar_pd_type(new Long(0));
	// List<PdType> pdTypeList = super.getFacade().getPdTypeService().getPdTypeList(pdType);
	// request.setAttribute("pdTypeList", pdTypeList);
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-27
	// * @desc 取需要类型的部门
	// * @param entp_type 企业类型
	// * @param isRename 是否重命名
	// */
	// public void setEntpMainListToSession(HttpServletRequest request, Integer entp_type, boolean isRename) {
	// EntpMain entpMain = new EntpMain();
	// entpMain.setEntp_type(entp_type);
	// entpMain.setInfo_state(0);
	// List<EntpMain> entpMainList = getFacade().getEntpMainService().getEntpMainList(entpMain);
	// if (isRename) {
	// request.setAttribute("entpMain".concat(String.valueOf(entp_type)).concat("List"), entpMainList);
	// } else {
	// request.setAttribute("entpMainList", entpMainList);
	// }
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-05-04
	// * @throws Exception
	// * @desc 向官网同步旧机信息
	// */
	// public Map<String, String> createHsInfoToYjhxGov(HttpServletRequest request, HsInfo hsInfo) throws Exception {
	// if (null == hsInfo.getHs_date()) {
	// hsInfo.setHs_date(new Date());
	// }
	// HttpSession session = request.getSession(false);
	// EntpUser entpUser = (EntpUser) session.getAttribute(Constants.ENTP_USER);
	//
	// Map<String, String> resuleMapTip = new HashMap<String, String>();
	//
	// KeyEntp keyEntpXs = new KeyEntp();
	// keyEntpXs.setLocal_dept_id(entpUser.getEntp_id());
	// keyEntpXs = getFacade().getKeyEntpService().getKeyEntp(keyEntpXs);
	// if (null == keyEntpXs || null == keyEntpXs.getLocal_hs_dept_id()) {
	// resuleMapTip.put("message", "系统中没有该用户对应企业的官网以旧换新初始化信息！");
	// resuleMapTip.put("result_code", "0");
	// return resuleMapTip;
	// }
	//
	// KeyEntp keyEntpHs = new KeyEntp();
	// keyEntpHs.setLocal_dept_id(keyEntpXs.getLocal_hs_dept_id());
	// keyEntpHs = getFacade().getKeyEntpService().getKeyEntp(keyEntpHs);
	// if (null == keyEntpHs || null == keyEntpHs.getUser_name() || null == keyEntpHs.getPass_word()) {
	// resuleMapTip.put("message", "系统中没有该用户对应企业的官网以旧换新初始化信息！");
	// resuleMapTip.put("result_code", "0");
	// return resuleMapTip;
	// }
	// String keySeq = keyEntpHs.getKey_sequence();
	// KeyEntp keyEntp = new KeyEntp();
	// keyEntp.setUser_name(keyEntpHs.getUser_name());
	// keyEntp.setPass_word(keyEntpHs.getPass_word());
	//
	// // 将本地的 回收产品规格 CA_SPEC 处理成官网的
	// HsPriceRef hsPriceRef = new HsPriceRef();
	// hsPriceRef.setCa_spec(hsInfo.getCa_spec());
	// hsPriceRef = getFacade().getHsPriceRefService().getHsPriceRef(hsPriceRef);
	// hsInfo.setCa_spec(hsPriceRef.getPar_ca_spec());
	// hsInfo.setHs_vol(hsPriceRef.getCa_spec_name());
	//
	// log.info(hsInfo.toString());
	//
	// Map<String, String> resuleMap = getFacade().getInteractWebService().createHsInfoToYjhxSystem(hsInfo, keyEntp,
	// keySeq);
	// if (null != resuleMap.get("result_code")) {
	// String result_code = String.valueOf(resuleMap.get("result_code"));
	// if ("0".equals(result_code)) { // 失败result_code=0
	// return resuleMap;
	// }
	// }
	// return null;
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-05-04
	// * @throws Exception
	// * @desc 向官网修改旧机信息
	// */
	// public Map<String, String> modifyHsInfoToYjhxGov(HttpServletRequest request, HsInfo hsInfo) throws Exception {
	// if (null == hsInfo.getHs_date()) {
	// hsInfo.setHs_date(new Date());
	// }
	// HttpSession session = request.getSession(false);
	// EntpUser entpUser = (EntpUser) session.getAttribute(Constants.ENTP_USER);
	//
	// Map<String, String> resuleMapTip = new HashMap<String, String>();
	//
	// KeyEntp keyEntpXs = new KeyEntp();
	// keyEntpXs.setLocal_dept_id(entpUser.getEntp_id());
	// keyEntpXs = getFacade().getKeyEntpService().getKeyEntp(keyEntpXs);
	// if (null == keyEntpXs || null == keyEntpXs.getLocal_hs_dept_id()) {
	// resuleMapTip.put("message", "系统中没有该用户对应企业的官网以旧换新初始化信息！");
	// resuleMapTip.put("result_code", "0");
	// return resuleMapTip;
	// }
	//
	// KeyEntp keyEntpHs = new KeyEntp();
	// keyEntpHs.setLocal_dept_id(keyEntpXs.getLocal_hs_dept_id());
	// keyEntpHs = getFacade().getKeyEntpService().getKeyEntp(keyEntpHs);
	// if (null == keyEntpHs || null == keyEntpHs.getUser_name() || null == keyEntpHs.getPass_word()) {
	// resuleMapTip.put("message", "系统中没有该用户对应企业的官网以旧换新初始化信息！");
	// resuleMapTip.put("result_code", "0");
	// return resuleMapTip;
	// }
	// String keySeq = keyEntpHs.getKey_sequence();
	// KeyEntp keyEntp = new KeyEntp();
	// keyEntp.setUser_name(keyEntpHs.getUser_name());
	// keyEntp.setPass_word(keyEntpHs.getPass_word());
	//
	// // 将本地的 回收产品规格 CA_SPEC 处理成官网的
	// HsPriceRef hsPriceRef = new HsPriceRef();
	// hsPriceRef.setCa_spec(hsInfo.getCa_spec());
	// hsPriceRef = getFacade().getHsPriceRefService().getHsPriceRef(hsPriceRef);
	// hsInfo.setCa_spec(hsPriceRef.getPar_ca_spec());
	// hsInfo.setHs_vol(hsPriceRef.getCa_spec_name());
	//
	// log.info(hsInfo.toString());
	//
	// Map<String, String> resuleMap = getFacade().getInteractWebService().modifyHsInfoToYjhxSystem(hsInfo, keyEntp,
	// keySeq);
	// if (null != resuleMap.get("result_code")) {
	// String result_code = String.valueOf(resuleMap.get("result_code"));
	// if ("0".equals(result_code)) { // 失败result_code=0
	// return resuleMap;
	// }
	// }
	// return null;
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-22
	// * @desc 生成拖机单号
	// */
	// public String generateTjdSn(HsInfo hsInfo) {
	// String tjd_sn = "";
	// Date now = new Date();
	// String date_string = DateFormatUtils.format(now, "yyMMddHHmmssSSS");
	// int k = RandomUtils.nextInt();
	// int j = Math.abs(k % 10);
	// String random_num = String.valueOf(j);
	// tjd_sn = date_string.concat(random_num);
	// return tjd_sn;
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-22
	// * @desc 向中间数据库插入收旧信息
	// */
	// public void sendHsInfoToCenterDb(HsInfo hsInfo) {
	// // SysDragMachineInfo sysDragMachineInfo = new SysDragMachineInfo();
	// // sysDragMachineInfo.setDrag_machine_id(hsInfo.getId());
	// //
	// // sysDragMachineInfo.setCustomer_name(hsInfo.getCustomer_name());
	// // sysDragMachineInfo.setCustomer_tel(hsInfo.getCustomer_tel());
	// // sysDragMachineInfo.setCustomer_mobile_tel(hsInfo.getCustomer_mobile());
	// // sysDragMachineInfo.setCustomer_p_index(String.valueOf(hsInfo.getP_index()));
	// // sysDragMachineInfo.setCustomer_address(hsInfo.getCustomer_addr());
	// //
	// // if (null != hsInfo.getCa_spec()) { // 回收产品规格 <==> 旧机品类代码
	// // sysDragMachineInfo.setOla_machine_code(String.valueOf(hsInfo.getCa_spec()));
	// // }
	// //
	// // String pd_type_name = "";
	// // if (null != hsInfo.getPd_type()) {
	// // PdType pdType = new PdType();
	// // pdType.setPd_type(hsInfo.getPd_type());
	// // pdType.setDel_mark(0);
	// // pdType = getFacade().getPdTypeService().getPdType(pdType);
	// // if (null != pdType) {
	// // pd_type_name = pdType.getPd_name();
	// // }
	// // }
	// // String hs_brand = hsInfo.getHs_brand();
	// // String hs_model = hsInfo.getHs_model();
	// // String ola_machine_name = pd_type_name;
	// // if (null != hs_brand && StringUtils.isNotBlank(ola_machine_name)) {
	// // ola_machine_name = ola_machine_name.concat("/").concat(hs_brand);
	// // }
	// // if (null != hs_model && StringUtils.isNotBlank(ola_machine_name)) {
	// // ola_machine_name = ola_machine_name.concat("/").concat(hs_model);
	// // }
	// // sysDragMachineInfo.setOla_machine_name(ola_machine_name);
	// //
	// // sysDragMachineInfo.setOla_machine_money(hsInfo.getHs_money());
	// // sysDragMachineInfo.setDrag_machine_date(hsInfo.getHs_date());
	// //
	// // getFacade().getInteractWebService().createSysDragMachineInfo(sysDragMachineInfo);
	// }
	//
	// public void setPIndexNameToSession(DynaBean dynaBean, HsInfo hsInfo) {
	// dynaBean.set("p_index_name", getPIndexName(hsInfo.getP_index(), " "));
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-22
	// * @desc 得到Pindex，并且取自己所属区域的数据，包括自己所属区的子区域
	// */
	// public String getPindexAndSetArea(HttpServletRequest request, DynaBean dynaBean, String province, String city,
	// String country) {
	// String p_index = "";
	// HttpSession session = request.getSession();
	// EntpUser entpUser = (EntpUser) session.getAttribute(Constants.ENTP_USER);
	// BaseProvinceList bpl = new BaseProvinceList();
	// bpl.setDel_mark(0);
	// bpl.setP_index(Integer.valueOf((String.valueOf(entpUser.getMap().get("p_index")))));
	// List<BaseProvinceList> bplList = getFacade().getBaseProvinceListService().getBaseProvinceListParentList(bpl);
	// boolean isSelectSameProvince = true;
	// for (BaseProvinceList entity : bplList) {
	// if (entity.getP_level().intValue() == 1) {
	// p_index = entity.getP_index().toString();
	// if (StringUtils.isNotBlank(province) && !province.equals(p_index)) {
	// isSelectSameProvince = false;
	// dynaBean.set("city", null);
	// dynaBean.set("country", null);
	// }
	// dynaBean.set("province", p_index);
	// }
	//
	// if (isSelectSameProvince && StringUtils.isNotBlank(city)) {
	// p_index = city;
	// dynaBean.set("city", p_index);
	// }
	// if (entity.getP_level().intValue() == 2) {
	// p_index = entity.getP_index().toString();
	// dynaBean.set("city", p_index);
	// }
	//
	// if (isSelectSameProvince && StringUtils.isNotBlank(country)) {
	// p_index = country;
	// dynaBean.set("country", p_index);
	// }
	// if (entity.getP_level().intValue() == 3) {
	// p_index = entity.getP_index().toString();
	// dynaBean.set("country", p_index);
	// }
	// }
	// return p_index;
	// }
	//
	// protected void renderExcelIgnoreCase(HttpServletRequest request, HttpServletResponse response) throws IOException
	// {
	// String hiddenHtml = request.getParameter("hiddenHtml");
	//
	// hiddenHtml = StringUtils.replace(hiddenHtml, "border=0", "border=1");
	// hiddenHtml = StringUtils.replace(hiddenHtml, "border=\"0\"", "border=\"1\"");
	//
	// String fname = EncryptUtils.encodingFileName(request.getParameter("hiddenName"));
	// response.setCharacterEncoding("UTF-8");
	// response.setContentType("application/octet-stream");
	// response.setHeader("Content-Disposition", "attachment;filename=" + fname);
	//
	// PrintWriter out = response.getWriter();
	// out.println(hiddenHtml);
	//
	// out.flush();
	// out.close();
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @version 2010-04-26
	// * @desc 收费类型+
	// */
	// protected void setChargeTypeListToSession(HttpServletRequest request, EntpUser entpUser) {
	// ChargeType chargeType = new ChargeType();
	// chargeType.setAdd_user_id(entpUser.getUser_id());
	// chargeType.setIs_del(0);
	// List<ChargeType> chargeTypeList = super.getFacade().getChargeTypeService().getChargeTypeList(chargeType);
	// request.setAttribute("chargeTypeList", chargeTypeList);
	// }
	//
	// /**
	// * @author WangHao
	// * @desc 取当前登录用户所在区域
	// */
	// protected BaseProvinceList getLoginUserEntpPindex(HttpServletRequest request) {
	// HttpSession session = request.getSession();
	// EntpUser entpUser = (EntpUser) session.getAttribute(Constants.ENTP_USER);
	//
	// EntpMain entpMain = new EntpMain();
	// entpMain.setEntp_id(entpUser.getEntp_id());
	// entpMain = super.getFacade().getEntpMainService().getEntpMain(entpMain);
	//
	// BaseProvinceList baseProvince = new BaseProvinceList();
	// baseProvince.setDel_mark(Integer.valueOf("0"));
	// baseProvince.setP_index(entpMain.getP_index());
	// baseProvince = super.getFacade().getBaseProvinceListService().getBaseProvinceList(baseProvince);
	// return baseProvince;
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @desc 取本地销售企业对应的本地的回收企业
	// * @version 2010-06-23
	// */
	// public EntpMain getLocalXsEntpWithLocalHsEntp(HttpServletRequest request) {
	// HttpSession session = request.getSession(false);
	// EntpUser entpUser = (EntpUser) session.getAttribute(Constants.ENTP_USER);
	//
	// EntpMain entpMainHs = new EntpMain();
	// KeyEntp keyEntpXs = new KeyEntp();
	// keyEntpXs.setLocal_dept_id(entpUser.getEntp_id());
	// keyEntpXs = getFacade().getKeyEntpService().getKeyEntp(keyEntpXs);
	// if (null != keyEntpXs && null != keyEntpXs.getLocal_hs_dept_id()) {
	// entpMainHs.setEntp_id(keyEntpXs.getLocal_hs_dept_id());
	// entpMainHs = super.getFacade().getEntpMainService().getEntpMain(entpMainHs);
	// }
	//
	// return entpMainHs;
	// }
	//
	// /**
	// * @author Wu,Yang
	// * @desc 判断是否超过补贴的限制
	// * @version 2010-06-30
	// */
	// public boolean isMoreConfineToYjhxAndBaida(String customer_idcard_type, String customer_idcard) {
	// boolean result = getFacade().getInteractWebService().isMoreConfineToYjhxSystem(
	// Integer.valueOf(customer_idcard_type), customer_idcard);
	// if (!result) {
	// // 政策有个最新的通知。个人用户最高垫付5台，企业用户最高50台
	// HsInfo hsInfoNew = new HsInfo();
	// hsInfoNew.setIs_del(0);
	// hsInfoNew.setIs_version(0);
	// hsInfoNew.setIs_sync(1);
	// hsInfoNew.setCustomer_idcard_type(Integer.valueOf(customer_idcard_type));
	// hsInfoNew.setCustomer_idcard(customer_idcard);
	// Long count = getFacade().getHsInfoService().getHsInfoCount(hsInfoNew);
	// if (Integer.valueOf(customer_idcard_type).intValue() != 5) {// 人用户最高垫付5台
	// if (count.intValue() > 5) {
	// result = true;
	// }
	// } else {// 企业用户最高垫付50台
	// if (count.intValue() > 50) {
	// result = true;
	// }
	// }
	// }
	// return result;
	// }
	//
	// public boolean isMoreConfineToYjhxAndBaidaFromCount(String customer_idcard_type, String customer_idcard) {
	// HashMap<String, String> resultMap = getFacade().getInteractWebService().getJjgxQueryFromYjhxSystem(
	// Integer.valueOf(customer_idcard_type), customer_idcard);
	// Integer hsCount = 0;
	// boolean result = true;
	// if (StringUtils.isNotBlank(resultMap.get("hsCount"))) {
	// hsCount = Integer.valueOf(resultMap.get("hsCount"));
	// }
	//
	// // 原判断有误，只对数量进行了判断，没有结合证件类型和数量进行判断
	// if (("5".equals(customer_idcard_type) && hsCount <= 50) || (!"5".equals(customer_idcard_type) && hsCount <= 5)) {
	// // 政策有个最新的通知。个人用户最高垫付5台，企业用户最高50台
	// HsInfo hsInfoNew = new HsInfo();
	// hsInfoNew.setIs_del(0);
	// hsInfoNew.setIs_version(0);
	// hsInfoNew.setIs_sync(1);
	// hsInfoNew.setCustomer_idcard_type(Integer.valueOf(customer_idcard_type));
	// hsInfoNew.setCustomer_idcard(customer_idcard);
	// Long count = getFacade().getHsInfoService().getHsInfoCount(hsInfoNew);
	// if (Integer.valueOf(customer_idcard_type).intValue() != 5) {// 人用户最高垫付5台
	// if (count.intValue() > 5) {
	// result = true;
	// } else {
	// result = false;
	// }
	// } else {// 企业用户最高垫付50台
	// if (count.intValue() > 50) {
	// result = true;
	// } else {
	// result = false;
	// }
	// }
	// }
	// return result;
	// }
	//
	// public boolean isMoreConfineToYjhxAndBaidaForHsInfoReg(String customer_idcard_type, String customer_idcard) {
	// boolean result = getFacade().getInteractWebService().isMoreConfineToYjhxSystem(
	// Integer.valueOf(customer_idcard_type), customer_idcard);
	// if (!result) {
	// // 政策有个最新的通知。个人用户最高垫付5台，企业用户最高50台
	// HsInfo hsInfoNew = new HsInfo();
	// hsInfoNew.setIs_del(0);
	// hsInfoNew.setIs_version(0);
	// // hsInfoNew.setIs_sync(1);
	// hsInfoNew.setCustomer_idcard_type(Integer.valueOf(customer_idcard_type));
	// hsInfoNew.setCustomer_idcard(customer_idcard);
	// Long count = getFacade().getHsInfoService().getHsInfoCount(hsInfoNew);
	// if (Integer.valueOf(customer_idcard_type).intValue() != 5) {// 人用户最高垫付5台
	// if (count.intValue() >= 5) {
	// result = true;
	// }
	// } else {// 企业用户最高垫付50台
	// if (count.intValue() >= 50) {
	// result = true;
	// }
	// }
	// }
	// return result;
	// }
	//
	// protected String getCtxPath(HttpServletRequest request) throws Exception {
	// StringBuffer ctx = new StringBuffer();
	// ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
	// .append(request.getServerPort());
	// ctx.append(request.getContextPath());
	//
	// return ctx.toString();
	// }
	//
	// /**
	// * @param id 回收信息id
	// * @desc 判断回收信息是否同步到官网
	// * @author Chen,LiDe
	// * @version 2010-08-26
	// */
	// public boolean isSyncToYjhx(Long id) throws Exception {
	// HsInfo hsInfo = new HsInfo();
	// hsInfo.setId(id);
	// hsInfo = super.getFacade().getHsInfoService().getHsInfo(hsInfo);
	//
	// if (null == hsInfo) {
	// return false;
	// }
	//
	// if (null != hsInfo.getIs_sync() && hsInfo.getIs_sync() == 1) {
	// return true;
	// }
	//
	// return false;
	// }
	//
	// /**
	// * @author WangHao
	// * @version 2010-10-29
	// * @desc 根据传入的回收表id，判断该回收信息在安徽省回收分类调整前还是调整后 true:旧信息 false:新信息
	// */
	// public boolean getIsHsInfoIsOldWithHsId(HttpServletRequest request, Long id) throws Exception {
	// HsInfo entity = new HsInfo();
	// entity.setId(id);
	// entity = super.getFacade().getHsInfoService().getHsInfo(entity);
	//
	// SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	// if (entity.getHs_input_date().getTime() >= sdf1.parse("2010-10-28").getTime()) {
	// request.setAttribute("is_old", "false");
	// return false;
	// } else {
	// request.setAttribute("is_old", "true");
	// return true;
	// }
	// }
}
