package com.ebiz.mmt.web.struts.manager.admin;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.MmtFilePathConfig;
import com.ebiz.mmt.web.struts.MobileBaseAction;
import com.ebiz.ssi.web.struts.bean.UploadFile;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

public class KonkaMobileSaleDataInputAction extends MobileBaseAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        return this.add(mapping, form, request, response);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                             HttpServletResponse response) throws Exception {

        // 判断session是否超时
        PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
        if (null == peProdUser) {
            String msg = super.getMessage(request, "user.session.isEmpty");
            super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
            return null;
        }

        super.saveToken(request);

        if (null == super.checkUserModPopeDom(form, request, "0")) {
            return super.checkPopedomInvalid(request, response);
        }

        setNaviStringToRequestScope(form, request);
        super.getModPopeDom(form, request);

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
        request.setAttribute("storeList", _eList);

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

        request.setAttribute("pePdModelList", pePdModelList);
        request.setAttribute("pePdModelJson", JSON.toJSONString(entityList));

        return mapping.findForward("input");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!super.isTokenValid(request, true)) {
            super.renderJavaScript(response, "alert('" + super.getMessage(request, "errors.token")
                    + "');history.back();");
            return null;
        }

        DynaBean dynaBean = (DynaBean) form;
        String mod_id = (String) dynaBean.get("mod_id");

        PeProdUser peProdUser = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);

        String[] goods_ids = request.getParameterValues("goods_id"); // 产品ID
        String[] nums = request.getParameterValues("num"); // 数量
        String[] prices = request.getParameterValues("price"); // 单价
        String[] goods_moneys = request.getParameterValues("goods_money"); // 总额
        String[] memos = request.getParameterValues("memo"); // 备注

        String[] realnames = request.getParameterValues("realname"); // 备注
        String[] phonenums = request.getParameterValues("phonenum"); // 备注
        String[] addressss = request.getParameterValues("addresss"); // 备注
        String[] mastercodes = request.getParameterValues("mastercode"); // 备注

        KonkaMobileSailData entity = new KonkaMobileSailData();

        entity.setReport_id(peProdUser.getId());
        entity.setReport_date(new java.util.Date());
        entity.setReport_name(peProdUser.getUser_name());

        // 销售日期
        // 销售日期，默认当前日期
        entity.setSale_date(new java.util.Date());

        // 分公司
        KonkaDept fgs = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
        if (null != fgs) {
            entity.setCust_subcomp_id(fgs.getDept_id());
            entity.setCust_subcomp_name(fgs.getDept_name());
        } else {
            entity.setCust_subcomp_id(peProdUser.getDept_id());
            entity.setCust_subcomp_name(peProdUser.getDepartment());
        }

        // 门店
        String store_id = (String) dynaBean.get("store_id");
        if (!GenericValidator.isLong(store_id)) {
            super.renderTextOrJsonp(request, response, "请选择门店！");
            return null;
        }
        boolean flag = true;// 判断客户是否允许负卖
        store_id = store_id.trim();
        entity.setDept_id(new Long(store_id));
        KonkaR3Store r3s = new KonkaR3Store();
        r3s.setStore_id(Long.parseLong(store_id));
        r3s = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(r3s);
        if (r3s != null) {

            entity.setDept_name(r3s.getStore_name());
            // 塞送达方r3编码
            if (StringUtils.isNotBlank(r3s.getMf_sn())) {
                entity.setSdf_r3_code(r3s.getMf_sn());
            } else if (StringUtils.isNotBlank(r3s.getR3_code())) {
                entity.setSdf_r3_code(r3s.getR3_code());
            }
            // 经办
            entity.setOffice_name(r3s.getJb_name());


            // 塞入出货仓名称
            if (null != r3s.getCk_name()) {
                entity.setChc_name(r3s.getCk_name());
            }


            // 根据门店找分公司，经办
            VOrgOfDept vOrgOfDept = new VOrgOfDept();
            if (null != r3s.getDept_id()) {
                vOrgOfDept.setCur_dept_id(Long.valueOf(r3s.getDept_id()));
                List<VOrgOfDept> vOrgOfDeptList = super.getFacade().getVOrgOfDeptService()
                        .getVOrgOfDeptList(vOrgOfDept);
                if (null != vOrgOfDeptList && vOrgOfDeptList.size() > 0) {
                    vOrgOfDept = vOrgOfDeptList.get(0);
                    if (null != vOrgOfDept.getDept_id() && GenericValidator.isLong(vOrgOfDept.getDept_id().toString())) {
                        entity.setSubcomp_id(Long.valueOf(vOrgOfDept.getDept_id().toString()));
                    }
                    if (null != vOrgOfDept.getDept_name()) {
                        entity.setSubcomp_name(vOrgOfDept.getDept_name());
                    }
                }
            }
            VOrgOfDept vOrgOfDept1 = new VOrgOfDept();
            if (null != r3s.getJb_job_id() && GenericValidator.isLong(r3s.getJb_job_id())) {
                vOrgOfDept1.setCur_dept_id(Long.valueOf(r3s.getJb_job_id()));
                List<VOrgOfDept> vOrgOfDeptList = super.getFacade().getVOrgOfDeptService()
                        .getVOrgOfDeptList(vOrgOfDept1);
                if (null != vOrgOfDeptList && vOrgOfDeptList.size() > 0) {
                    vOrgOfDept = vOrgOfDeptList.get(0);
                    if (null != vOrgOfDept.getCur_dept_id()) {
                        entity.setOffice_id(vOrgOfDept.getCur_dept_id());
                    }
                    if (null != vOrgOfDept.getCur_dept_name()) {
                        entity.setOffice_name(vOrgOfDept.getCur_dept_name());
                    }
                }
            }
            // 查询客户信息
            if (null != r3s.getR3_code()) {
                KonkaR3Shop s = new KonkaR3Shop();
                s.setR3_code(r3s.getR3_code());
                s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

                if (null != s) {
                    entity.setCustomer_id(s.getId());
                    entity.setCustomer_name(s.getCustomer_name());
                    entity.setCustomer_r3_code(s.getR3_code());

                    if (s.getIs_minus_sales() == 0) {// 判断客户是否允许负卖
                        flag = true;
                    } else {
                        flag = false;
                    }

                    if (null != s.getR3_code()) {
                        MvOrgOfCustomer vOrgOfCustomer = new MvOrgOfCustomer();
                        vOrgOfCustomer.setR3_code(s.getR3_code());
                        List<MvOrgOfCustomer> vlist = super.getFacade().getMvOrgOfCustomerService()
                                .getMvOrgOfCustomerList(vOrgOfCustomer);
                        if (null != vlist && vlist.size() > 0) {
                            vOrgOfCustomer = vlist.get(0);
                            entity.setCust_office_id(vOrgOfCustomer.getCur_dept_id());// 客户经办id
                            entity.setCust_office_name(vOrgOfCustomer.getCur_dept_name());// 客户经办名称
                        }
                    }

                    if (GenericValidator.isLong(s.getCustomer_type())) {
                        // entity.setCategory_id(new
                        // Long(s.getCustomer_type()));
                        entity.setCustomer_cate_id(new Long(s.getCustomer_type()));

                        KonkaCategory kc = new KonkaCategory();
                        kc.setC_index(new Long(s.getCustomer_type()));
                        kc.setC_type(10);
                        kc = super.getFacade().getKonkaCategoryService().getKonkaCategory(kc);

                        if (null != kc) {
                            entity.setCustomer_cate_name("[" + kc.getC_comm() + "]" + kc.getC_name());
                        }
                    }
                }
            }
        }

        // 来自web端
        entity.setData_source(1);

        entity.setR_sn(peProdUser.getJob_id() + "_" + DateFormatUtils.format(new Date(), "yyyyMMdd_HHmmss"));

        // 附件处理
        List<UploadFile> uploadFileList = super
                .uploadFile(form, MmtFilePathConfig.LST_PATH, true, 0, new int[]{960});
        List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
        Attachment filesAttachment = null;
        for (UploadFile uploadFile : uploadFileList) {
            filesAttachment = new Attachment();
            filesAttachment.setFile_name(uploadFile.getFileName());
            filesAttachment.setFile_type(uploadFile.getContentType());
            filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
            filesAttachment.setSave_path(uploadFile.getFileSavePath());
            filesAttachment.setSave_name(uploadFile.getFileSaveName());
            filesAttachment.setDel_mark(new Short("0"));
            filesAttachmentList.add(filesAttachment);
        }
        logger.info("fj-------------------->{}" + uploadFileList.size());

        // Map<String, List<Attachment>> map = new HashMap<String,
        // List<Attachment>>();
        Map<String, List<UploadFile>> map = new HashMap<String, List<UploadFile>>();
        // key
        if (null != uploadFileList && uploadFileList.size() > 0) {
            for (UploadFile s : uploadFileList) {
                map.put(s.getFormName().substring(s.getFormName().length() - 1, s.getFormName().length()) + "", null);
            }
            // 组list
            for (String s0 : map.keySet()) {
                List<UploadFile> list = new ArrayList<UploadFile>();
                for (UploadFile s : uploadFileList) {
                    if (s0.equals(s.getFormName().substring(s.getFormName().length() - 1, s.getFormName().length())
                            + "")) {
                        list.add(s);
                    }
                }
                map.put(s0, list);
            }
        }
        String[] xl = request.getParameterValues("xl");// 1.3.5
        // for (String ss : xl) {
        // //System.out.println(ss + "------------------>" + map.get(ss).size());
        // }

        List<KonkaMobileSailData> konkaMobileSailDataList = new ArrayList<KonkaMobileSailData>();

        for (int i = 0; i < goods_ids.length; i++) {// 0.1.2
            KonkaMobileSailData cur = new KonkaMobileSailData();
            super.copyProperties(cur, entity);

            // 客户姓名
            String realname = realnames[i];
            // 客户电话
            String phonenum = phonenums[i];
            // 客户住址
            String addresss = addressss[i];
            // 客户身份证号
            String mastercode = mastercodes[i];

            // 客户信息
            cur.setRealname(realname);
            cur.setPhonenum(phonenum);
            cur.setAddresss(addresss);
            cur.setMastercode(mastercode);
            if (null != map && map.size() > 0) {
                List<UploadFile> upList = map.get(xl[i]);

                Attachment filesAttachment1 = null;
                List<Attachment> attList = new ArrayList<Attachment>();
                if (null != upList && upList.size() > 0) {
                    for (UploadFile uploadFile : upList) {
                        filesAttachment1 = new Attachment();
                        filesAttachment1.setFile_name(uploadFile.getFileName());
                        filesAttachment1.setFile_type(uploadFile.getContentType());
                        filesAttachment1.setFile_size(new Integer(uploadFile.getFileSize()));
                        filesAttachment1.setSave_path(uploadFile.getFileSavePath());
                        filesAttachment1.setSave_name(uploadFile.getFileSaveName());
                        filesAttachment1.setDel_mark(new Short("0"));
                        attList.add(filesAttachment1);
                    }
                    cur.setAttachmentList(attList);
                }
            }
            if (StringUtils.isNotBlank(realname) && StringUtils.isNotBlank(phonenum)) {
                cur.setStatus(1);
            } else {
                cur.setStatus(0);
            }

            // 1. 销量
            String count = nums[i];
            if (!GenericValidator.isLong(count)) {
                super.renderJavaScript(response, "window.onload=function(){alert('数量填写错误！');history.back();}");
                return null;
            }
            cur.setNum(Long.parseLong(count)); // 销量

            // 2. 型号
            String pd_id = goods_ids[i];
            // 型号名称
            if (!"0".equalsIgnoreCase(count)) {
                if (StringUtils.isNotBlank(pd_id)) {
                    pd_id = pd_id.trim();
                    cur.setModel_id(new Long(pd_id));
                    PePdModel pePdModel = new PePdModel();
                    pePdModel.setPd_id(Long.valueOf(pd_id));
                    pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
                    if (pePdModel != null) {
                        cur.setModel_name(pePdModel.getMd_name());
                        cur.setCategory_id(pePdModel.getCls_id());
                        cur.setPct_code(pePdModel.getMat_num());
                        if (null != pePdModel.getMd_size() && !"".equals(pePdModel.getMd_size())) {
                            cur.setMeasure_id(Long.parseLong(pePdModel.getMd_size()));
                        }
                        cur.setMeasure_name(pePdModel.getMd_size());
                    } else {
                        super.renderJavaScript(response,
                                "window.onload=function(){alert('系统中不存在您选择的型号，请重新选择型号并填报！');history.back();}");
                        return null;
                    }
                } else {
                    super.renderJavaScript(response,
                            "window.onload=function(){alert('未选择型号，请选择型号并填报！');history.back();}");
                    return null;
                }
            }

            if (null == r3s.getCk_id() || null == cur.getModel_name() || null == r3s.getR3_code()) {
                cur.setSale_cost(new BigDecimal(0));
            } else {
                BigDecimal sale_cost = getSaleCost(r3s.getR3_code(), cur.getModel_name(), r3s.getCk_id(), new BigDecimal(
                        nums[i]));
                cur.setSale_cost(sale_cost);
            }
            //插入参考价
            //	cur.setPrice_ref(BigDecimal.valueOf(getKonkaR3PdPrice2(cur.getModel_name(),r3s.getR3_code())));

            // 3. 单价
            String single_price = prices[i];
            if (!GenericValidator.isFloat(single_price)) {
                super.renderJavaScript(response, "window.onload=function(){alert('单价填写错误！');history.back();}");
                return null;
            }
            cur.setSingle_price(new BigDecimal(single_price));

            // 3. 总价
            String price = goods_moneys[i];
            if (!GenericValidator.isFloat(price)) {
                super.renderJavaScript(response, "window.onload=function(){alert('总价填写错误！');history.back();}");
                return null;
            }
            cur.setAll_price(BigDecimal.valueOf(Double.parseDouble(price)));

            // 4. 备注
            cur.setMemo(memos[i]);

            // 5. 提成设置
            if (!"0".equalsIgnoreCase(count) && null != fgs) {
                KonkaMobileMdPercent kmm = new KonkaMobileMdPercent();
                kmm.setStatus(0);
                kmm.setPd_id(cur.getModel_id());
                kmm.setDept_id(fgs.getDept_id());
                kmm = super.getFacade().getKonkaMobileMdPercentService().getKonkaMobileMdPercent(kmm);
                if (kmm != null) {
                    if (kmm.getType() == 0) {
                        BigDecimal deduct = new BigDecimal(0);
                        deduct = cur.getAll_price().multiply(kmm.getPercent());
                        deduct = deduct.divide(new BigDecimal(100));
                        cur.setDeduct(deduct);
                        cur.setRule_id(kmm.getId());
                    } else if (kmm.getType() == 1) {
                        cur.setDeduct(kmm.getPercent());
                        cur.setRule_id(kmm.getId());
                    }
                } else {
                    cur.setDeduct(new BigDecimal(0));
                }
            } else {
                cur.setDeduct(new BigDecimal(0));
            }

            // 判断门店是否允许负卖（负销售），如果允许的话则不需要判断，如果不允许负卖的话 ，则需要判断库存与上报数量 start
            if (!flag) {// 不允许负卖
                // long stock = super.getKhStocksOld(cur.getCustomer_r3_code(),
                // cur.getModel_name(), null);
                long stock = super.getKhStocksWithMoney(cur.getCustomer_r3_code(), cur.getModel_name(), null);
                logger.info("不允许负卖的情况下，比较当前库存和销售数量的大小，当前的库存为：" + stock + ",销售数量为:" + cur.getNum());
                if (stock < cur.getNum()) {// 如果库存小于数量
                    super.renderJavaScript(response,
                            "window.onload=function(){alert('销售数量大于库存数量，不能销售，请联系业务人员执行下订单后再进行销售，剩余库存数量为" + stock
                                    + "台');history.back();}");
                    return null;
                }
            }
            // 判断门店是否允许负卖（负销售），如果允许的话则不需要判断，如果不允许负卖的话 ，则需要判断库存与上报数量 end

            konkaMobileSailDataList.add(cur);
        }

        Long counts = super.getFacade().getKonkaMobileSailDataService()
                .createKonkaMobileSailDataList(konkaMobileSailDataList);

        if (0L == counts) {
            super.renderJavaScript(response, "window.onload=function(){alert('未选择型号，请选择型号并填报！');history.back();}");
            return null;
        }
        for (KonkaMobileSailData sailData : konkaMobileSailDataList) {
			try {
                // 先进先出的东西
                if (sailData.getNum() > 0) {
                    // 销售stock_out_type 530
                    super.getFacade()
                            .getJxcFifoStocksService()
                            .stock_out(new Long(store_id), sailData.getModel_id(), sailData.getModel_name(),
                                    entity.getNum(), sailData.getSingle_price(), r3s.getR3_code(), r3s.getCk_id(), 530,
                                    peProdUser.getId());

                } else {

                    super.getFacade()
                            .getJxcFifoStocksService()
                            .stock_in(r3s.getCk_id(), sailData.getModel_id(), sailData.getSingle_price(),
                                    sailData.getNum().intValue() * -1, new Date(), 60);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//		先进先出的东西


        // generateHis(entity, null, null, null, null, null, null);

        super.saveMessage(request, "entity.submit.success");

        StringBuffer pathBuffer = new StringBuffer();
        pathBuffer.append("/admin/KonkaMobileSaleDataInput.do?method=add");
        pathBuffer.append("&mod_id=" + mod_id);
        pathBuffer.append("&");
        ActionForward forward = new ActionForward(pathBuffer.toString(), true);
        // end

        return forward;

    }

    //拿参考价
    public Double getKonkaR3PdPrice2(String pd_sn, String r3_code) throws Exception {

//		String pd_sn = (String) dynaBean.get("pd_sn");// 机型编码
//		String r3_code = (String) dynaBean.get("r3_code");// 客户编码
        Double price = 0d;
        // 根据客户找到唯一的客户群组
        Long deptid = super.getFacade().getKonkaR3ShopService().getDeptIdOfKonkaR3Code(r3_code);
        if (null != deptid) {


            KonkaR3Shop ks = new KonkaR3Shop();
            ks.setR3_code(r3_code);
            ks.setIs_del(0l);
            ks = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(ks);
            CrmCustomerGroup group = new CrmCustomerGroup();
            // group 不能多于2条数据
            group = super.getFacade().getCrmCustomerGroupService()
                    .getCrmCustomerGroupByCustomerIdAndDeptId(String.valueOf(ks.getId()), deptid);

            Map<String, Double> map = new HashMap<String, Double>();

            // 确认是否为该客户群组维护了价格表<限定在时间段内>
            if (group != null) {
                CrmPriceHeader header = new CrmPriceHeader();
                header.setDept_id(deptid);
                header.setGroupcode(group.getGroupcode());// group code 是唯一的
                header.getMap().put("effective_flag", "yes");// 加上时间过滤
                header = super.getFacade().getCrmPriceHeaderService().getCrmPriceHeader(header);
                if (header != null) {
                    // 价格表下是否维护了所需机型
                    CrmPriceLines line = new CrmPriceLines();
                    line.setHeadid(header.getId());
                    line.setModelcode(pd_sn);
                    line = super.getFacade().getCrmPriceLinesService().getCrmPriceLines(line);
                    if (line != null) {
                        price = line.getForprice() == null ? 0d : line.getForprice();
//					map.put("discount", line.getDiscount() == null ? 0d : line.getDiscount());
                    }
                }

            }
        }
        return price;
    }
}