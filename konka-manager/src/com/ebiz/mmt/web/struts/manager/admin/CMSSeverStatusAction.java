package com.ebiz.mmt.web.struts.manager.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.web.struts.BaseAction;

import net.sf.json.JSONArray;

/**
 * 获取渠道系统服务状态
 * 
 * 在线用户数
 * 
 * CPU等信息后续增加
 * 
 * @author zhou
 * 
 */
public class CMSSeverStatusAction extends BaseAction {

    Lock lock = new ReentrantLock();// 锁

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        long sessioncount = 0L;
        Map<String, String> online = new HashMap<String, String>();
        online = (Map<String, String>) request.getSession().getServletContext().getAttribute("online");
        List<String> onlineUserAccounts = new ArrayList<String>();

        try {
            lock.lock();
            if (online != null) {
                for (Map.Entry<String, String> entry : online.entrySet()) {
                    if (entry.getValue() != null) {
                        if (!"未登录".equals(entry.getValue())) {
                            onlineUserAccounts.add(entry.getValue());
                        }
                    }
                }
                sessioncount = online.size();
            }
        } catch (Exception e) {} finally {
            lock.unlock();
        }
        request.setAttribute("sessioncount", sessioncount);
        request.setAttribute("onlineUserAccounts", onlineUserAccounts);
        return mapping.findForward("list");
    }


    /**
     * 获取登陆系统人数
     * 
     * @author LiangHouen
     * @date 2015-05-04
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getCount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap allmap = new HashMap();

        long sessioncount = 0L;
        Map<String, String> online = new HashMap<String, String>();
        online = (Map<String, String>) request.getSession().getServletContext().getAttribute("online");

        try {
            lock.lock();
            if (online != null) {
                for (Map.Entry<String, String> entry : online.entrySet()) {
                    if (entry.getValue() != null) {
                        if (!"未登录".equals(entry.getValue())) {
                            sessioncount++;
                        }
                    }
                }
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        allmap.put("count", sessioncount);

        // 转换为json数据
        JSONArray jsonArray = JSONArray.fromObject(allmap);

        int start = jsonArray.toString().indexOf("[");
        int end = jsonArray.toString().lastIndexOf("}");

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString().substring(start + 1, end + 1));
        out.flush();
        out.close();
        return null;
    }
}
