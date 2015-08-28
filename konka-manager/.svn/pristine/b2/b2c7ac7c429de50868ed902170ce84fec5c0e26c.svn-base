package com.ebiz.mmt.web.struts.manager.chengduoa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import com.ebiz.mmt.domain.KonkaoaCategory;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.OaUserGroupL;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Jin,QingHua
 */
public abstract class BaseMmtoaAction extends BaseAction {


    /**
     * 当前年月日
     * 
     * @return
     * @throws Exception
     */
    public String getNowYearAndMonth() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String ym = sdf.format(date);
        return ym.substring(2, ym.length());
    }

    /**
     * @author Hui,Gang
     * @version Build 2010-12-29
     * @desc 文件相关属性存放至RequestScope
     */
    public void setCategoryListToRequestScope(HttpServletRequest request) {
        KonkaoaCategory category = new KonkaoaCategory();
        category.setIs_del(0);
        category.setC_type(11);
        request.setAttribute("category11List", getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(12);
        request.setAttribute("category12List", getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(13);
        request.setAttribute("category13List", getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
        category.setC_type(14);
        request.setAttribute("category14List", getFacade().getKonkaoaCategoryService().getKonkaoaCategoryListForFiles(category));
    }

    /**
     * @param form
     * @param request
     * @param entity
     * @param id
     * @desc 取文件发送呈数据
     * @author Wang Hao
     * @return
     */
    public KonkaoaFiles getFilesProperty(ActionForm form, HttpServletRequest request, Long id) {
        DynaBean dynaBean = (DynaBean) form;
        String[] receive_type_ids = null;// 人
        String[] receive_dept_ids = null;// 部门
        String[] receive_type_names = null;

        String[] receive_user_groups = null;// 群组

        // 参与者
        List<KonkaoaFilesRecipient> filesRecipientList = new ArrayList<KonkaoaFilesRecipient>();

        KonkaoaFiles entity = new KonkaoaFiles();

        // KonkaoaFiles的主键,新建记录时,id为null
        entity.setId(id);

        // 发
        receive_type_ids = StringUtils.split((String) dynaBean.get("receive_type_1_ids"), ",");
        receive_type_names = StringUtils.split((String) dynaBean.get("receive_type_1_names"), ",");
        receive_user_groups = StringUtils.split((String) dynaBean.get("receive_group_ids"), ",");// 下发的群组

        // 如果群组刚好又包含了receive_type_ids. 所以,两者之间要取补集
        if (null != receive_type_ids) {
            for (int i = 0; i < receive_type_ids.length; i++) {
                KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
                filesRecipient.setReceive_id(new Long(receive_type_ids[i]));
                filesRecipient.setReceive_user(receive_type_names[i]);
                filesRecipient.setReceive_type(1);// 发
                filesRecipient.setReceive_user_type(0);// 0人(群组转成0),1部门
                filesRecipientList.add(filesRecipient);
            }
        }

        // --------处理receive_type_ids 和群组receive_user_groups问题----------
        // 获取群组所有人
        Map<String, String> maps = new HashMap<String, String>();
        if (receive_user_groups != null && receive_user_groups.length > 0) {
            for (String groupid : receive_user_groups) {
                List<OaUserGroupL> alistOfOaUserGroup = getUsersByGroupId(Long.valueOf(groupid));
                if (alistOfOaUserGroup != null) {
                    for (OaUserGroupL ol : alistOfOaUserGroup) {
                        maps.put(String.valueOf(ol.getUser_id()), ol.getUser_name());
                    }
                }
            }
        }

        // 重复的人
        Map<String, String> maps2 = new HashMap<String, String>();
        // 下发给群组里面的人.但排除receive_type_ids
        if (receive_type_ids != null && receive_type_ids.length > 0) {
            Iterator<Entry<String, String>> iter = maps.entrySet().iterator();
            Entry<String, String> entry;
            while (iter.hasNext()) {
                entry = iter.next();
                if (isContainsTheObj(receive_type_ids, entry.getKey())) {
                    maps2.put(entry.getKey(), entry.getValue());
                }
            }
        }


        if (maps != null && maps.size() > 0) {
            Iterator<Entry<String, String>> iter = maps.entrySet().iterator();
            Entry<String, String> entry;
            while (iter.hasNext()) {
                entry = iter.next();
                if (!maps2.containsKey(entry.getKey())) {
                    // 增加接收人
                    KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
                    filesRecipient.setReceive_id(Long.valueOf(entry.getKey()));
                    filesRecipient.setReceive_user(entry.getValue());
                    filesRecipient.setReceive_type(1);
                    filesRecipient.setReceive_user_type(0);
                    filesRecipientList.add(filesRecipient);
                }
            }
        }

        // --------处理receive_type_ids 和群组问题----------

        // 下发给部门
        receive_dept_ids = StringUtils.split((String) dynaBean.get("receive_dept_1_ids"), ",");
        receive_type_names = StringUtils.split((String) dynaBean.get("receive_dept_1_names"), ",");
        if (null != receive_dept_ids) {
            for (int i = 0; i < receive_dept_ids.length; i++) {
                KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
                filesRecipient.setReceive_id(new Long(receive_dept_ids[i]));
                filesRecipient.setReceive_user(receive_type_names[i]);
                filesRecipient.setReceive_type(1);
                filesRecipient.setReceive_user_type(1);
                filesRecipientList.add(filesRecipient);
            }
        }

        // 送
        // receive_type_ids = StringUtils.split((String) dynaBean.get("receive_type_2_ids"), ",");
        // receive_type_names = StringUtils.split((String) dynaBean.get("receive_type_2_names"),
        // ",");
        // if (null != receive_type_ids) {
        // for (int i = 0; i < receive_type_ids.length; i++) {
        // KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
        // filesRecipient.setReceive_id(new Long(receive_type_ids[i]));
        // filesRecipient.setReceive_user(receive_type_names[i]);
        // filesRecipient.setReceive_type(2);
        // filesRecipient.setReceive_user_type(0);
        // filesRecipientList.add(filesRecipient);
        // }
        // }

        // 呈
        // receive_type_ids = StringUtils.split((String) dynaBean.get("receive_type_3_ids"), ",");
        // receive_type_names = StringUtils.split((String) dynaBean.get("receive_type_3_names"),
        // ",");
        // if (null != receive_type_ids) {
        // for (int i = 0; i < receive_type_ids.length; i++) {
        // KonkaoaFilesRecipient filesRecipient = new KonkaoaFilesRecipient();
        // filesRecipient.setReceive_id(new Long(receive_type_ids[i]));
        // filesRecipient.setReceive_user(receive_type_names[i]);
        // filesRecipient.setReceive_type(3);
        // filesRecipient.setReceive_user_type(0);
        // filesRecipientList.add(filesRecipient);
        // }
        // }
        entity.setFilesRecipientList(filesRecipientList);
        return entity;
    }

    private boolean isContainsTheObj(String[] src, String obj) {

        if (src == null) {
            return false;
        }

        return Arrays.asList(src).contains(obj);
    }

    private List<OaUserGroupL> getUsersByGroupId(Long groupid) {
        List<OaUserGroupL> list = new ArrayList<OaUserGroupL>();
        OaUserGroupL ol = new OaUserGroupL();
        ol.setHead_id(Long.valueOf(groupid));
        list = super.getFacade().getOaUserGroupLService().getOaUserGroupLList(ol);
        return list;
    }


    /**
     * @param request 自动生成登录用户所在部门的提交文件编号
     * @author Cheng,Bing
     */
    public String getFilesMaxNo(String file_no_lm) {

        Long max_fileno = null;

        KonkaoaDocInfo kd = new KonkaoaDocInfo();
        kd.getMap().put("file_no_lm", file_no_lm);
        int length = file_no_lm.length();
        kd.getMap().put("file_no_length", length);
        Long max_fileno_1 = super.getFacade().getKonkaoaDocInfoService().getKonkaoaDocInfoNoMax(kd);
        max_fileno_1 = max_fileno_1 == null ? 0 : max_fileno_1;

        KonkaoaFiles kf = new KonkaoaFiles();
        kf.getMap().put("file_no_lm", file_no_lm);
        int length1 = file_no_lm.length();
        kf.getMap().put("file_no_length", length1);
        Long max_fileno_2 = super.getFacade().getKonkaoaFilesService().getKonkaoaFilesNoMax(kf);
        max_fileno_2 = max_fileno_2 == null ? 0 : max_fileno_2;

        max_fileno = max_fileno_1 >= max_fileno_2 ? max_fileno_1 : max_fileno_2;
        max_fileno = max_fileno + 1;
        String file_no_r = "";
        if (max_fileno < 1000) {
            file_no_r = "0000".substring(0, 4 - ("" + max_fileno).length()) + max_fileno;
        } else {
            file_no_r = "" + max_fileno;
        }

        return file_no_lm + file_no_r;
    }



}