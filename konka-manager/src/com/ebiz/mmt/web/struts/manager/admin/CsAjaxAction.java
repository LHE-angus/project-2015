package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebiz.mmt.domain.*;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Xing,XiuDong
 */
public class CsAjaxAction extends BaseAction {

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.getBaseProvinceList(mapping, form, request, response);
    }

    /**
     * Ajax asynchronous request to get BaseProvince List
     * 
     * @return json: [[key, value],[key, value]..]
     */
    public ActionForward getBaseProvinceList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String par_index = (String) dynaBean.get("p_index");

        if (!GenericValidator.isLong(par_index)) {
            return null;
        }

        BaseProvinceList baseProvince = new BaseProvinceList();
        if (!"-1".equals(par_index)) {
            baseProvince.setPar_index(new Long(par_index));
        }
        baseProvince.setDel_mark(new Short("0"));

        // 分公司：管辖区域----add by douqr
        String many_p_index = (String) dynaBean.get("many_p_index"); // 管辖区域
        if (StringUtils.isNotBlank(many_p_index)) {
            baseProvince.getMap().put("many_p_index", many_p_index);
        }

        // 分公司及分公司以下的管辖区域不能重合
        String is_managered = (String) dynaBean.get("is_managered");
        if (StringUtils.isNotBlank(is_managered)) {
            baseProvince.getMap().put("is_managered", is_managered);
        }

        List<BaseProvinceList> baseProvinceList = super.getFacade().getBaseProvinceListService().getBaseProvinceListList(baseProvince);

        List<String> dataList = new ArrayList<String>();
        for (BaseProvinceList entity : baseProvinceList) {
            dataList.add(StringUtils.join(new String[] {"[\"", entity.getP_name(), "\",\"", String.valueOf(entity.getP_index()), "\"]"}));
        }

        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }

    /**
     * @author Li,Yuan
     * @version 2012-01-12
     */
    public ActionForward getBaseFactoryAndStoreIdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String param = (String) dynaBean.get("param");

        Long dept_id = super.getSessionUserInfo(request).getDept_id();
        if (dept_id == null) {
            return null;
        }

        KonkaXxStdStore konkaXxStdStore = new KonkaXxStdStore();
        konkaXxStdStore.getMap().put("dept_id", dept_id);
        List<KonkaXxStdStore> konkaXxStdStoreList = new ArrayList<KonkaXxStdStore>();
        List<String> dataList = new ArrayList<String>();
        if ("0".equals(param)) {
            konkaXxStdStoreList = super.getFacade().getKonkaXxStdStoreService().getKonkaXxFactoryIdList(konkaXxStdStore);
            for (KonkaXxStdStore entity : konkaXxStdStoreList) {
                dataList.add(StringUtils.join(new String[] {"[\"", entity.getFactory_id(), "\",\"", String.valueOf(entity.getFactory_id()), "\"]"}));
            }
        } else {
            konkaXxStdStore.setFactory_id(param);
            konkaXxStdStoreList = super.getFacade().getKonkaXxStdStoreService().getKonkaXxStoreIdList(konkaXxStdStore);
            for (KonkaXxStdStore entity : konkaXxStdStoreList) {
                dataList.add(StringUtils.join(new String[] {"[\"", entity.getStore_desc(), "\",\"", String.valueOf(entity.getStore_id()), "\"]"}));
            }
        }

        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }

    /**
     * Ajax asynchronous request to get BaseProvinceFour List
     * 
     * @return json: [[key, value],[key, value]..]
     */
    public ActionForward getBaseProvinceFourList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String par_index = (String) dynaBean.get("p_index");
        String p_index_join = (String) dynaBean.get("p_index_join");

        if (!GenericValidator.isLong(par_index)) {
            return null;
        }

        BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
        baseProvinceFour.setPar_index(new Long(par_index));
        baseProvinceFour.setDel_mark(0);

        if (StringUtils.isNotBlank(p_index_join)) {
            baseProvinceFour.getMap().put("p_index_join", p_index_join);
        }

        List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(baseProvinceFour);

        List<String> dataList = new ArrayList<String>();
        for (BaseProvinceListFour entity : baseProvinceFourList) {
            dataList.add(StringUtils.join(new String[] {"[\"", entity.getP_name(), "\",\"", String.valueOf(entity.getP_index()), "\"]"}));
        }

        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }

    /**
     * Ajax asynchronous request to get BasePdClass List
     * 
     * @return json: [[key, value],[key, value]..]
     */
    public ActionForward getBasePdClassList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String par_id = (String) dynaBean.get("cls_id");

        if (!GenericValidator.isLong(par_id)) {
            return null;
        }

        BasePdClass basePdClass = new BasePdClass();
        basePdClass.setPar_id(new Long(par_id));
        basePdClass.setIs_del(0);

        List<BasePdClass> basePdClassList = super.getFacade().getBasePdClassService().getBasePdClassList(basePdClass);

        List<String> dataList = new ArrayList<String>();
        for (BasePdClass entity : basePdClassList) {
            dataList.add(StringUtils.join(new String[] {"[\"", entity.getCls_name(), "\",\"", String.valueOf(entity.getCls_id()), "\"]"}));
        }

        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }

    /**
     * 根据分公司Id以及登录人的权限取经营部和办事处的数据
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getJybDeptListByFgsId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
        String pageCount = (String) dynaBean.get("pageCount");
        StringBuffer sb = new StringBuffer("[");
        int count = 500; // 每次最多取出数量

        if (!GenericValidator.isLong(fgs_dept_id)) {
            return null;
        }

        if (GenericValidator.isLong(pageCount)) {
            count = Integer.valueOf(pageCount);
        }

        KonkaDept entity = new KonkaDept();
        entity.getRow().setCount(count);
        List<KonkaDept> entityList = new ArrayList<KonkaDept>();

        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(userInfo.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_ge_10_le_39 = false;
        boolean role_id_ge_40_le_49 = false;
        boolean role_id_ge_50_le_59 = false;
        boolean role_id_eq_60 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() <= 39L) {
                role_id_ge_10_le_39 = true;
            }
            if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 49L) {
                role_id_ge_40_le_49 = true;
            }
            if (peRoleUser.getRole_id() >= 50L && peRoleUser.getRole_id() <= 59L) {
                role_id_ge_50_le_59 = true;
            }
            if (peRoleUser.getRole_id() == 60L) {
                role_id_eq_60 = true;
            }
        }

        KonkaDept dept = new KonkaDept();
        dept.setDept_id(userInfo.getDept_id());
        dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

        if (role_id_ge_10_le_39) {
            entity.setPar_id(Long.valueOf(fgs_dept_id));
            entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
        } else if (role_id_ge_40_le_49) {
            entity.setDept_id(userInfo.getDept_id());
            entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
        } else if (role_id_ge_50_le_59) {
            KonkaDept _dept = this.getSuperiorForDeptType(userInfo.getDept_id(), 4);
            if (null != _dept && null != _dept.getDept_id()) {
                entity.setDept_id(_dept.getDept_id());
                entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
            }
        } else if (role_id_eq_60) {
            if (dept.getDept_type() == 4) {
                entity.setDept_id(userInfo.getDept_id());
                entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
            }
            if (dept.getDept_type() == 5) {
                KonkaDept _dept = this.getSuperiorForDeptType(userInfo.getDept_id(), 4);
                if (null != _dept && null != _dept.getDept_id()) {
                    entity.setDept_id(_dept.getDept_id());
                    entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
                }
            }
        }
        for (KonkaDept t : entityList) {
            sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
            sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

        }
        sb.append("{\"end\":\"\"}]");
        super.renderJson(response, sb.toString());

        return null;
    }

    /**
     * 根据经营部Id以及登录人的权限取办事处的数据
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBscDeptListByJybId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
        String pageCount = (String) dynaBean.get("pageCount");
        StringBuffer sb = new StringBuffer("[");
        int count = 500; // 每次最多取出数量

        if (!GenericValidator.isLong(jyb_dept_id)) {
            return null;
        }

        if (GenericValidator.isLong(pageCount)) {
            count = Integer.valueOf(pageCount);
        }

        KonkaDept entity = new KonkaDept();
        entity.getRow().setCount(count);
        List<KonkaDept> entityList = new ArrayList<KonkaDept>();

        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        KonkaDept dept = new KonkaDept();
        dept.setDept_id(userInfo.getDept_id());
        dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(userInfo.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_ge_10_le_49 = false;
        boolean role_id_ge_50_le_59 = false;
        boolean role_id_eq_60 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() >= 10L && peRoleUser.getRole_id() <= 49L) {
                role_id_ge_10_le_49 = true;
            }
            if (peRoleUser.getRole_id() >= 50L && peRoleUser.getRole_id() <= 59L) {
                role_id_ge_50_le_59 = true;
            }
            if (peRoleUser.getRole_id() == 60L) {
                role_id_eq_60 = true;
            }
        }

        if (role_id_ge_10_le_49) {
            entity.setPar_id(Long.valueOf(jyb_dept_id));
            entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
        }
        if (role_id_ge_50_le_59) {
            entity.setDept_id(userInfo.getDept_id());
            entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
        }
        if (role_id_eq_60) {
            if (dept.getDept_type() == 5) {
                entity.setDept_id(userInfo.getDept_id());
                entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
            }
        }
        for (KonkaDept t : entityList) {
            sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
            sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

        }
        sb.append("{\"end\":\"\"}]");
        super.renderJson(response, sb.toString());

        return null;
    }

    /**
     * 根据经营部或办事处的部门ID找出业务员
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getYwyUserListByDeptId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String dept_id = (String) dynaBean.get("dept_id");
        String pageCount = (String) dynaBean.get("pageCount");
        StringBuffer sb = new StringBuffer("[");
        int count = 500; // 每次最多取出数量

        if (!GenericValidator.isLong(dept_id)) {
            return null;
        }

        if (GenericValidator.isLong(pageCount)) {
            count = Integer.valueOf(pageCount);
        }

        PeProdUser entity = new PeProdUser();
        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
        entity.setIs_del(0);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(userInfo.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_eq_60 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() == 60L) {
                role_id_eq_60 = true;
            }
        }

        if (role_id_eq_60) {
            entity.setId(userInfo.getId());
        } else {
            entity.setDept_id(Long.valueOf(dept_id));
            entity.getMap().put("select_role_id_eq_60", true);
        }
        entity.getRow().setCount(count);

        // 过滤网点账号
        entity.getMap().put("not_agent", true);

        List<PeProdUser> entityList = getFacade().getPeProdUserService().getPeProdUserList(entity);
        for (PeProdUser t : entityList) {
            sb.append("{\"id\":\"" + String.valueOf(t.getId()) + "\",");
            sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getUser_name() + "[" + t.getReal_name() + "]") + "\"},");

        }
        sb.append("{\"end\":\"\"}]");

        super.renderJson(response, sb.toString());

        return null;
    }

    /**
     * 根据分公司Id取经营部和办事处的数据
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getJybDeptListByFgs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
        String pageCount = (String) dynaBean.get("pageCount");
        StringBuffer sb = new StringBuffer("[");
        int count = 500; // 每次最多取出数量

        if (!GenericValidator.isLong(fgs_dept_id)) {
            return null;
        }

        if (GenericValidator.isLong(pageCount)) {
            count = Integer.valueOf(pageCount);
        }

        KonkaDept entity = new KonkaDept();
        entity.getRow().setCount(count);
        entity.setPar_id(Long.valueOf(fgs_dept_id));
        List<KonkaDept> entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
        for (KonkaDept t : entityList) {
            sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
            sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

        }
        sb.append("{\"end\":\"\"}]");
        super.renderJson(response, sb.toString());

        return null;
    }

    /**
     * 
     * @param mapping
     * @author Pan,Gang
     * @return 2013-11-04
     * @throws 根据分公司dept_id取分公司下面的经办
     */
    public ActionForward getJybDeptListByFgsId2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");

        if (!GenericValidator.isLong(fgs_dept_id)) {
            return null;
        }
        log.info("fgs_dept_id=" + fgs_dept_id);
        List<String> dataList = new ArrayList<String>();
        KonkaDept entity = new KonkaDept();
        entity.getMap().put("fgs_dept_id", fgs_dept_id);
        List<KonkaDept> entityList = getFacade().getKonkaDeptService().getKonkaDeptAndJbNameList(entity);

        for (KonkaDept t : entityList) {
            dataList.add(StringUtils.join(new String[] {"[\"", t.getDept_name(), "\",\"", String.valueOf(t.getDept_id()), "\"]"}));
        }
        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));

        log.info("dataList=" + StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }

    /**
     * 根据经营部Id取办事处的数据
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBscDeptListByJyb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
        String pageCount = (String) dynaBean.get("pageCount");
        StringBuffer sb = new StringBuffer("[");
        int count = 500; // 每次最多取出数量

        if (!GenericValidator.isLong(jyb_dept_id)) {
            return null;
        }

        if (GenericValidator.isLong(pageCount)) {
            count = Integer.valueOf(pageCount);
        }

        KonkaDept entity = new KonkaDept();
        entity.getRow().setCount(count);

        entity.setPar_id(Long.valueOf(jyb_dept_id));
        List<KonkaDept> entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
        for (KonkaDept t : entityList) {
            sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
            sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

        }
        sb.append("{\"end\":\"\"}]");
        super.renderJson(response, sb.toString());

        return null;
    }

    /**
     * 根据经营部或办事处的部门ID找出业务员
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getYwyUserListByDept(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String dept_id = (String) dynaBean.get("dept_id");
        String pageCount = (String) dynaBean.get("pageCount");
        StringBuffer sb = new StringBuffer("[");
        int count = 500; // 每次最多取出数量

        if (!GenericValidator.isLong(dept_id)) {
            return null;
        }

        if (GenericValidator.isLong(pageCount)) {
            count = Integer.valueOf(pageCount);
        }

        PeProdUser entity = new PeProdUser();

        entity.setDept_id(Long.valueOf(dept_id));
        entity.getRow().setCount(count);
        entity.setIs_del(0);
        // 过滤网点账号
        entity.getMap().put("not_agent", true);
        List<PeProdUser> entityList = getFacade().getPeProdUserService().getPeProdUserList(entity);
        for (PeProdUser t : entityList) {
            sb.append("{\"id\":\"" + String.valueOf(t.getId()) + "\",");
            sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getReal_name()) + "\"},");

        }
        sb.append("{\"end\":\"\"}]");

        log.info(sb.toString());
        super.renderJson(response, sb.toString());

        return null;
    }

    /**
     * 根据登录用户所在部门的管辖区域列出省、市、县、镇
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBaseProvinceFourListByUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String par_index = (String) dynaBean.get("p_index");
        String p_index_join = (String) dynaBean.get("p_index_join");

        if (!GenericValidator.isLong(par_index)) {
            return null;
        }
        PeProdUser ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_ge_30_le_39 = false;
        boolean role_id_ge_40_le_59 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 39L) {
                role_id_ge_30_le_39 = true;
            }
            if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 59L) {
                role_id_ge_40_le_59 = true;
            }
        }

        KonkaDept dept = new KonkaDept();

        BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
        baseProvinceFour.setPar_index(new Long(par_index));
        baseProvinceFour.setDel_mark(0);

        if (role_id_ge_30_le_39) {
            dept.setDept_id(ui.getDept_id());
            dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
            String index = dept.getM_areas_ids();
            if (index == null) {
                return null;
            }
            if (("0").equals(par_index)) {
                String[] indexes = index.split(",");
                String[] p_indexes = new String[indexes.length];
                for (int i = 0; i < indexes.length; i++) {
                    p_indexes[i] = indexes[i].substring(0, 2) + "0000";
                }
                baseProvinceFour.getMap().put("many_p_index", StringUtils.join(p_indexes, ","));
            } else if (("0000").equals(par_index.substring(2, 6))) {
                String[] indexes = index.split(",");
                String[] p_indexes = new String[indexes.length];
                for (int i = 0; i < indexes.length; i++) {
                    p_indexes[i] = indexes[i].substring(0, 4) + "00";
                }
                baseProvinceFour.getMap().put("many_p_index", StringUtils.join(p_indexes, ","));
            } else if (("00").equals(par_index.substring(4, 6))) {

                BaseProvinceListFour baseProvince = new BaseProvinceListFour();
                baseProvince.setPar_index(new Long(par_index));
                baseProvince.setDel_mark(0);
                baseProvince.getMap().put("many_p_index", index);

                List<BaseProvinceListFour> baseProvinceList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(baseProvince);
                if (!baseProvinceList.isEmpty()) {
                    baseProvinceFour.getMap().put("many_p_index", index);
                }
            }
        }
        if (role_id_ge_40_le_59) {
            dept = super.getSuperiorForDeptType(ui.getDept_id(), 3);
            String index = dept.getM_areas_ids();
            if (index == null) {
                return null;
            }

            if (("0").equals(par_index)) {
                String[] indexes = index.split(",");
                String[] p_indexes = new String[indexes.length];
                for (int i = 0; i < indexes.length; i++) {
                    p_indexes[i] = indexes[i].substring(0, 2) + "0000";
                }
                baseProvinceFour.getMap().put("many_p_index", StringUtils.join(p_indexes, ","));
            } else if (("0000").equals(par_index.substring(2, 6))) {
                String[] indexes = index.split(",");
                String[] p_indexes = new String[indexes.length];
                for (int i = 0; i < indexes.length; i++) {
                    p_indexes[i] = indexes[i].substring(0, 4) + "00";
                }
                baseProvinceFour.getMap().put("many_p_index", StringUtils.join(p_indexes, ","));
            } else if (("00").equals(par_index.substring(4, 6))) {
                BaseProvinceListFour baseProvince = new BaseProvinceListFour();
                baseProvince.setPar_index(new Long(par_index));
                baseProvince.setDel_mark(0);
                baseProvince.getMap().put("many_p_index", index);

                List<BaseProvinceListFour> baseProvinceList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(baseProvince);
                if (!baseProvinceList.isEmpty()) {
                    baseProvinceFour.getMap().put("many_p_index", index);
                }
            }
        }

        if (StringUtils.isNotBlank(p_index_join)) {
            baseProvinceFour.getMap().put("p_index_join", p_index_join);
        }

        List<BaseProvinceListFour> baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(baseProvinceFour);

        List<String> dataList = new ArrayList<String>();
        for (BaseProvinceListFour entity : baseProvinceFourList) {
            dataList.add(StringUtils.join(new String[] {"[\"", entity.getP_name(), "\",\"", String.valueOf(entity.getP_index()), "\"]"}));
        }

        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }

    /**
     * 根据类型取类别
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getWlTypeListByWlIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String wl_index = (String) dynaBean.get("wl_index");
        String pageCount = (String) dynaBean.get("pageCount");
        StringBuffer sb = new StringBuffer("[");
        int count = 500; // 每次最多取出数量

        if (!GenericValidator.isLong(wl_index)) {
            return null;
        }

        if (GenericValidator.isLong(pageCount)) {
            count = Integer.valueOf(pageCount);
        }
        KonkaMobileCategory entity = new KonkaMobileCategory();
        entity.setC_type(new Integer(wl_index));
        entity.setIs_type(1);
        entity.setIs_del(0);
        entity.getRow().setCount(count);
        List<KonkaMobileCategory> entityList = super.getFacade().getKonkaMobileCategoryService().getKonkaMobileCategoryList(entity);

        for (KonkaMobileCategory t : entityList) {
            sb.append("{\"id\":\"" + String.valueOf(t.getC_index()) + "\",");
            sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getC_name()) + "\"},");

        }
        sb.append("{\"end\":\"\"}]");
        super.renderJson(response, sb.toString());

        return null;
    }

    /**
     * 根据仓库类型选择仓库所属部门
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStorageAreacom(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String storage_type = (String) dynaBean.get("storage_type");
        String pageCount = (String) dynaBean.get("pageCount");
        StringBuffer sb = new StringBuffer("[");
        int count = 500; // 每次最多取出数量

        if (!GenericValidator.isLong(storage_type)) {
            return null;
        }

        if (GenericValidator.isLong(pageCount)) {
            count = Integer.valueOf(pageCount);
        }
        if (StringUtils.isNotBlank(storage_type) && storage_type.equals("1")) {
            ImportDataTypes entity = new ImportDataTypes();
            entity.setData_type(5l);
            entity.getMap().put("parId", 1);
            entity.getRow().setCount(count);
            List<ImportDataTypes> entityList = super.getFacade().getImportDataTypesService().getImportDataTypesList(entity);

            for (ImportDataTypes t : entityList) {
                sb.append("{\"id\":\"" + String.valueOf(t.getId()) + "\",");
                sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getType_name()) + "\"},");
            }
        }
        if (StringUtils.isNotBlank(storage_type) && storage_type.equals("2")) {
            request.setAttribute("sybDeptInfoList", super.getDeptInfoList(mapping, form, request, response, null));
            for (KonkaDept t : super.getDeptInfoList(mapping, form, request, response, null)) {
                sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
                sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");
            }
        }
        sb.append("{\"end\":\"\"}]");
        super.renderJson(response, sb.toString());
        return null;
    }

    // 根据分公司找经办
    public ActionForward getChannelId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String part_company_id = (String) dynaBean.get("part_company_id");
        String pageCount = (String) dynaBean.get("pageCount");
        StringBuffer sb = new StringBuffer("[");
        int count = 500; // 每次最多取出数量

        if (!GenericValidator.isLong(part_company_id)) {
            return null;
        }

        if (GenericValidator.isLong(pageCount)) {
            count = Integer.valueOf(pageCount);
        }

        KonkaDept entity = new KonkaDept();
        entity.getRow().setCount(count);
        entity.getMap().put("is_jingban", 1);
        entity.setPar_id(new Long(part_company_id));
        List<KonkaDept> entityList = new ArrayList<KonkaDept>();

        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        KonkaDept dept = new KonkaDept();
        dept.setDept_id(userInfo.getDept_id());
        dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(userInfo.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_ge_10_le_49 = false;
        boolean role_id_ge_50_lt_59 = false;
        boolean role_id_eq_60 = false;
        boolean role_id_eq_1000 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() == 10L || peRoleUser.getRole_id() == 49L) {
                role_id_ge_10_le_49 = true;
            }
            if (peRoleUser.getRole_id() >= 50L && peRoleUser.getRole_id() < 59L) {
                role_id_ge_50_lt_59 = true;
            }
            if (peRoleUser.getRole_id() == 60L) {
                role_id_eq_60 = true;
            }
            if (peRoleUser.getRole_id() == 1000L) {
                role_id_eq_1000 = true;
            }
        }

        if (role_id_ge_10_le_49) {
            entity.setPar_id(Long.valueOf(part_company_id));
            entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
        }
        if (role_id_ge_50_lt_59) {
            entity.setDept_id(userInfo.getDept_id());
            entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
        }
        if (role_id_eq_60) {
            if (dept.getDept_type() == 5) {
                entity.setDept_id(userInfo.getDept_id());
                entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
            }
        }
        if (role_id_eq_1000) {
            entity.setPar_id(Long.valueOf(part_company_id));
            entityList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
        }
        for (KonkaDept t : entityList) {
            sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
            sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

        }
        sb.append("{\"end\":\"\"}]");
        super.renderJson(response, sb.toString());

        return null;
    }

    /**
     * @author Liu,ZhiXiang
     * @version 2013-07-02
     */
    // 分公司 三级联动
    public ActionForward getKonkaDeptForParId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String par_id = (String) dynaBean.get("par_id");
        KonkaDept dept = new KonkaDept();
        dept.setIs_del(0);
        dept.getMap().put("order_by_dept_name", "no_empty");
        String isNotEpp = (String) dynaBean.get("isNotEpp");
        PeProdUser ui = new PeProdUser();
        ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_eq_10 = false; // 系统管理员
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() < 10L) {
                role_id_eq_10 = true;
            }
        }
        if (StringUtils.isNotBlank(par_id) && !"0".equals(par_id)) {
            dept.setPar_id(new Long(par_id));
        } else {
            if (!role_id_eq_10) {
                KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
                if (null != kDept) dept.setDept_id(kDept.getDept_id());

                dept.setDept_type(3);
            } else {
                dept.setDept_type(3);
            }
        }
        if (null != isNotEpp && StringUtils.isNotBlank(isNotEpp)) {
            dept.getMap().put("isNotEpp", "isNotEpp");
        }
        List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
        List<String> dataList = new ArrayList<String>();
        for (KonkaDept entity : entityList) {
            dataList.add(StringUtils.join(new String[] {"[\"", entity.getDept_name(), "\",\"", String.valueOf(entity.getDept_id()), "\"]"}));
        }

        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }

    /**
     * @author Liu,ZhiXiang
     * @version 2013-07-02
     */
    // 分公司 三级联动
    public ActionForward getKonkaDeptForParId2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String par_id = (String) dynaBean.get("par_id");
        KonkaDept dept = new KonkaDept();
        dept.getMap().put("order_by_dept_name", "no_empty");
        if (StringUtils.isNotBlank(par_id)) {
            dept.setPar_id(new Long(par_id));
        } else {
            return null;
        }
        List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);

        List<String> dataList = new ArrayList<String>();
        for (KonkaDept entity : entityList) {
            dataList.add(StringUtils.join(new String[] {"[\"", entity.getDept_name(), "\",\"", String.valueOf(entity.getDept_id()), "\"]"}));
        }

        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }

    /**
     * easyUI分公司二（三）级联动
     * 
     * @author Wang,KunLin
     * @version 2014-09-13
     */

    public ActionForward getAjaxKonkaDeptForParId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String par_id = (String) dynaBean.get("par_id");
        KonkaDept dept = new KonkaDept();
        dept.setIs_del(0);
        dept.getMap().put("order_by_dept_name", "no_empty");
        PeProdUser ui = new PeProdUser();
        ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_eq_10 = false; // 系统管理员
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() < 10L) {
                role_id_eq_10 = true;
            }
        }

        if (StringUtils.isNotBlank(par_id) && !"0".equals(par_id)) {
            dept.setPar_id(new Long(par_id));
        } else {
            if (!role_id_eq_10) {
                KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
                if (null != kDept) dept.setDept_id(kDept.getDept_id());

                dept.setDept_type(3);
            } else {
                dept.setDept_type(3);
            }
        }
        List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
        StringBuffer sb = new StringBuffer("[");
        sb.append("{\"id\":\" \",");
        sb.append("\"name\":\"请选择\"},");
        for (KonkaDept t : entityList) {
            sb.append("{\"id\":\"" + String.valueOf(t.getDept_id()) + "\",");
            sb.append("\"name\":\"" + StringEscapeUtils.escapeJavaScript(t.getDept_name()) + "\"},");

        }
        sb.append("{\"end\":\"\"}]");
        super.renderJson(response, sb.toString());
        return null;
    }

    /**
     * 
     * @param mapping
     * @author Pan,Gang
     * @return 2013-11-04
     * @throws 根据经办dept_id取经办下面的用户
     */
    public ActionForward getUserListByJybId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String jb_id = (String) dynaBean.get("jb_id");

        if (!GenericValidator.isLong(jb_id)) {
            return null;
        }

        List<String> dataList = new ArrayList<String>();
        PeProdUser entity = new PeProdUser();
        entity.setIs_del(0);
        entity.setIs_xx_user(0L);
        entity.getMap().put("dept_id_in", jb_id);
        entity.getMap().put("order_by_real_name", true);
        entity.setUser_type(1); // 去除客户端用户
        List<PeProdUser> entityList = getFacade().getPeProdUserService().getPeProdUserList(entity);

        for (PeProdUser t : entityList) {
            dataList.add(StringUtils.join(new String[] {"[\"", t.getReal_name(), "\",\"", String.valueOf(t.getJob_id()), "\"]"}));
        }
        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));

        return null;
    }

    /**
     * 
     * @param mapping
     * @author Pan,Gang
     * @return 2013-11-04
     * @throws 根据经办dept_id取经办下面的业务员
     */
    public ActionForward getYwyUserListByDeptId2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String jb_id = (String) dynaBean.get("jb_id");

        if (!GenericValidator.isLong(jb_id)) {
            return null;
        }

        PeProdUser entity = new PeProdUser();
        PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
        entity.setIs_del(0);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(userInfo.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_eq_60 = false;
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() == 60L) {
                role_id_eq_60 = true;
            }
        }

        if (role_id_eq_60) {
            entity.setId(userInfo.getId());
        } else {
            entity.getMap().put("dept_id_in", jb_id);
            entity.getMap().put("order_by_real_name", true);
            entity.getMap().put("select_role_id_eq_60", true);
        }
        // entity.setDept_id(Long.valueOf(jyb_dept_id));
        // entity.getMap().put("dept_id_all_prod_user", dept_id);
        // }
        // by:wh 2011-12-02 经营部下级机构的人全部取，包括业务员和办事处管理员
        // entity.getMap().put("role_id", 60l);//职务（角色）60为业务员
        List<String> dataList = new ArrayList<String>();
        List<PeProdUser> entityList = getFacade().getPeProdUserService().getPeProdUserList(entity);
        for (PeProdUser t : entityList) {
            dataList.add(StringUtils.join(new String[] {"[\"", t.getUser_name(), "\",\"", String.valueOf(t.getJob_id()), "\"]"}));
        }
        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }


    /**
     * 客户类型二级联动
     * 
     * @author Lianghouen
     * @version 2014-06-24
     */
    public ActionForward getKonkaShopType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        List<String> dataList = new ArrayList<String>();
        String par_id = (String) dynaBean.get("par_id");

        if (StringUtils.isNotBlank(par_id) && !"0".equals(par_id)) {
            List<KonkaCategory> konkaCategoryList = new ArrayList<KonkaCategory>();// 客户小类

            // 客户类型 网点类型
            KonkaCategory kc = new KonkaCategory();
            kc.setC_type(10);
            kc.setIs_del(0);
            kc.setPar_index(par_id);
            konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryList(kc);
            for (KonkaCategory entity : konkaCategoryList) {
                dataList.add(StringUtils.join(new String[] {"[\"", entity.getC_name(), "\",\"", String.valueOf(entity.getC_index()), "\"]"}));
            }
        } else if ("0".equals(par_id)) {
            KonkaCategory kc1 = new KonkaCategory();
            List<KonkaCategory> p_CategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryGroupByCCommList(kc1);

            for (KonkaCategory entity : p_CategoryList) {
                dataList.add(StringUtils.join(new String[] {"[\"", (String) (entity.getMap().get("c_comm")), "\",\"", String.valueOf(entity.getMap().get("par_index")), "\"]"}));
            }
        }


        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }

    /**
     * easyUI获取分公司列表
     * 
     * @author Angus
     * @date 2014-7-18
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAjaxKonkaDeptList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String par_id = (String) dynaBean.get("par_id");
        String isNotEpp = (String) dynaBean.get("isNotEpp");
        KonkaDept dept = new KonkaDept();
        dept.setIs_del(0);
        dept.getMap().put("order_by_dept_name", "no_empty");
        PeProdUser ui = new PeProdUser();
        ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

        PeRoleUser _peRoleUser = new PeRoleUser();
        _peRoleUser.setUser_id(ui.getId());
        List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

        boolean role_id_eq_10 = false; // 系统管理员
        for (PeRoleUser peRoleUser : peRoleUserList) {
            if (peRoleUser.getRole_id() < 10L) {
                role_id_eq_10 = true;
            }
        }

        if (StringUtils.isNotBlank(par_id) && !"0".equals(par_id)) {
            dept.setPar_id(new Long(par_id));
        } else {
            if (!role_id_eq_10) {
                KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
                if (null != kDept) dept.setDept_id(kDept.getDept_id());

                dept.setDept_type(3);
            } else {
                dept.setDept_type(3);
            }
        }
        // 过滤掉epp使用的伪分公司
        if (null != isNotEpp && StringUtils.isNotBlank(isNotEpp)) {
            dept.getMap().put("isNotEpp", "isNotEpp");
        }
        List<HashMap> entityList = super.getFacade().getKonkaDeptService().getAjaxKonkaDeptList(dept);
        List list = new ArrayList();
        if (entityList.size() == 1) {
            list = entityList;
        } else {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("DEPT_ID", "");
            m.put("DEPT_NAME", "-请选择-");
            list.add(m);
            list.addAll(entityList);
        }

        // 封装成JSON数据
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
        return null;
    }


    /**
     * easyUI客户类型二级联动
     * 
     * @author Lianghouen
     * @version 2014-07-24
     */
    public ActionForward getCusType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String par_id = request.getParameter("par_id");

        List<HashMap> konkaCategoryList = null;
        List list = new ArrayList();
        Map<String, Object> m = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(par_id) && !"0".equals(par_id)) {

            m.put("C_INDEX", "");
            m.put("C_NAME", "-请选择-");
            // 客户类型 网点类型
            KonkaCategory kc = new KonkaCategory();
            kc.setC_type(10);
            kc.setIs_del(0);
            kc.setPar_index(par_id);
            konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryListNew(kc);
        } else if ("0".equals(par_id) || "".equals(par_id)) {
            KonkaCategory kc1 = new KonkaCategory();
            konkaCategoryList = super.getFacade().getKonkaCategoryService().getKonkaCategoryGroupByCCommNew(kc1);
            m.put("PAR_INDEX", "");
            m.put("C_COMM", "-请选择-");
        }

        // 封装成JSON数据
        list.add(m);
        list.addAll(konkaCategoryList);
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 请求查询网点级别列表
     * 
     * @author Liang,HouEn 2014-9-12
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAjaxJBasePartnerList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<HashMap> entityList = super.getFacade().getJBasePartnerService().getJBasePartnerLevelList();
        List list = new ArrayList();
        if (entityList.size() == 1) {
            list = entityList;
        } else {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("P_LEVEL", "");
            m.put("LEVEL_NAME", "-请选择-");
            list.add(m);
            list.addAll(entityList);
        }

        // 封装成JSON数据
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 客户类型列表
     * 
     * @author Liang,HouEn 2014-9-17
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getCustomerTypeList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KonkaCategory kc = new KonkaCategory();
        kc.setC_type(10);
        List<HashMap> entityList = super.getFacade().getKonkaCategoryService().getKonkaCategoryMapList(kc);

        List list = new ArrayList();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("C_INDEX", "");
        m.put("C_NAME", "-请选择-");
        list.add(m);
        list.addAll(entityList);

        // 封装成JSON数据
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
        return null;
    }


    /**
     * 客户网点联动
     * 
     * @author Lianghouen
     * @version 2014-11-10
     */
    public ActionForward getCustomerAndAgent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaBean dynaBean = (DynaBean) form;
        List<String> dataList = new ArrayList<String>();
        String par_id = (String) dynaBean.get("par_id");
        String fgs_id = (String) dynaBean.get("fgs_id");
        String current_id = (String) dynaBean.get("current_id");

        if (StringUtils.isNotBlank(par_id) && !"0".equals(par_id)) { // 客户下的一级网点,不包含当前网点
            List<JBasePartner> JBasePartnerList = new ArrayList<JBasePartner>();

            JBasePartner jbp = new JBasePartner();
            jbp.setC_id(Long.valueOf(par_id));
            jbp.setPar_c_id(Long.valueOf(par_id));
            jbp.getMap().put("current_id", current_id);
            jbp.setIs_del(0);
            JBasePartnerList = super.getFacade().getJBasePartnerService().getJBasePartnerList(jbp);

            for (JBasePartner entity : JBasePartnerList) {
                dataList.add(StringUtils.join(new String[] {"[\"", entity.getPartner_name(), "\",\"", String.valueOf(entity.getPartner_id()), "\"]"}));
            }
        } else if ("0".equals(par_id)) { // 可选客户列表
            KonkaR3Shop konkaR3Shop = new KonkaR3Shop();
            if (!GenericValidator.isLong(fgs_id)) {} else {
                KonkaDept t = new KonkaDept();
                t.setDept_id(Long.valueOf(fgs_id));
                t = super.getFacade().getKonkaDeptService().getKonkaDept(t);

                if (null == t || t.getDept_sn() == null) {
                    return null;
                }
                konkaR3Shop.setBranch_area_name_2(t.getDept_sn());
            }

            // konkaR3Shop.getMap().put("agents", true);
            konkaR3Shop.setIs_del(0L);
            konkaR3Shop.setIs_konka(1);
            konkaR3Shop.setIs_sdf(0);
            List<KonkaR3Shop> entityList = super.getFacade().getKonkaR3ShopService().getShopForAgent(konkaR3Shop);

            for (KonkaR3Shop entity : entityList) {
                dataList.add(StringUtils.join(new String[] {"[\"", (String) (entity.getCustomer_name()), "\",\"", String.valueOf(entity.getId()), "\"]"}));
            }
        }


        super.renderJson(response, StringUtils.join(new String[] {"[", StringUtils.join(dataList, ","), "]"}));
        return null;
    }


    /**
     * 省市县镇四级联动
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBaseProvinceFourListForUI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        List list = new ArrayList();
        List<BaseProvinceListFour> baseProvinceFourList = null;
        String p_index = (String) dynaBean.get("p_index");
        String p_level = (String) dynaBean.get("p_level");

        if (null != p_index && !"".equals(p_index)) {
            BaseProvinceListFour baseProvinceFour = new BaseProvinceListFour();
            baseProvinceFour.setPar_index(new Long(p_index));
            baseProvinceFour.setDel_mark(0);
            baseProvinceFourList = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFourList(baseProvinceFour);

            HashMap m = new HashMap();
            if ("1".equals(p_level)) {
                m.put("p_index", "");
                m.put("p_name", "-请选择省份-");
            }
            if ("2".equals(p_level)) {
                m.put("p_index", "");
                m.put("p_name", "-请选择市-");
            }
            if ("3".equals(p_level)) {
                m.put("p_index", "");
                m.put("p_name", "-请选择县/区-");
            }
            if ("4".equals(p_level)) {
                m.put("p_index", "");
                m.put("p_name", "-请选择乡镇-");
            }
            // 封装成JSON数据
            list.add(m);
            list.addAll(baseProvinceFourList);
        }

        JSONArray jsonArray = JSONArray.fromObject(list);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
        return null;
    }


    /**
     * 查询尺寸段列表
     *
     * @author Liang,HouEn 2015-8-19
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getSizeSecList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KonkaBaseTypeData entity=new KonkaBaseTypeData();
        entity.setPar_type_id(100023L);
        List<KonkaBaseTypeData> sizeSecList= super.getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(entity);
        List list = new ArrayList();
        if (sizeSecList.size() == 1) {
            list = sizeSecList;
        } else {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("field1", "");
            m.put("type_name", "-请选择-");
            list.add(m);
            list.addAll(sizeSecList);
        }

        // 封装成JSON数据
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
        return null;
    }


    /**
     * easyUI获取分公司三级联动列表
     *
     * @author Angus
     * @date 2014-7-18
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAjaxDeptsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaBean dynaBean = (DynaBean) form;
        String par_id = (String) dynaBean.get("par_id");
        String dept_type = (String) dynaBean.get("dept_type");
        KonkaDept dept = new KonkaDept();
        dept.setIs_del(0);
        dept.getMap().put("order_by_dept_name", "no_empty");
        PeProdUser ui = new PeProdUser();
        ui = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
        List list = new ArrayList();
        //分公司
        if("3".equals(dept_type)){
            List<HashMap> entityList = null;
            PeRoleUser _peRoleUser = new PeRoleUser();
            _peRoleUser.setUser_id(ui.getId());
            List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

            boolean role_id_eq_10 = false; // 系统管理员
            for (PeRoleUser peRoleUser : peRoleUserList) {
                if (peRoleUser.getRole_id() < 10L) {
                    role_id_eq_10 = true;
                }
            }

            if (StringUtils.isNotBlank(par_id) && !"0".equals(par_id)) {
                dept.setPar_id(new Long(par_id));
            } else {
                if (!role_id_eq_10) {
                    KonkaDept kDept = getKonkaDeptForFgs(ui.getDept_id());
                    if (null != kDept) dept.setDept_id(kDept.getDept_id());

                    dept.setDept_type(3);
                } else {
                    dept.setDept_type(3);
                }
            }
            // 过滤掉epp使用的伪分公司
            dept.getMap().put("isNotEpp", "isNotEpp");
            entityList = super.getFacade().getKonkaDeptService().getAjaxKonkaDeptList(dept);
            if (entityList.size() == 1) {
                list = entityList;
            } else {
                Map<String, Object> m = new HashMap<String, Object>();
                m.put("DEPT_ID", "");
                m.put("DEPT_NAME", "-请选择-");
                list.add(m);
                list.addAll(entityList);
            }
        }
        //经办
        else if(StringUtils.isNotBlank(par_id)){
            dept.setPar_id(Long.parseLong(par_id));
            dept.getMap().put("jb_type",true);
            List<KonkaDept> entityList = super.getFacade().getKonkaDeptService().getKonkaDeptList(dept);
            if (null!=entityList) {
                Map<String, Object> m = new HashMap<String, Object>();
                m.put("dept_id", "");
                m.put("dept_name", "-请选择-");
                list.add(m);
                list.addAll(entityList);
            }
        }else{
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("dept_id", "");
            m.put("dept_name", "-请选择-");
            list.add(m);
        }

        // 封装成JSON数据
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
        return null;
    }
}
