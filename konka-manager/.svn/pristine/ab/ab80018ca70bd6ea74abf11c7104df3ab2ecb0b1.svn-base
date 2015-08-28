<%@ page language="java" contentType="application/octet-stream;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td width="5%" align="center" nowrap="nowrap">分公司</td>
          <td align="center" nowrap="nowrap">门店名称</td>
          <td width="6%" align="center" nowrap="nowrap">门店编号</td>
          <td width="10%" align="center" nowrap="nowrap">客户名称</td>
          <td width="5%" align="center" nowrap="nowrap">客户R3编码</td>
          <td align="center" nowrap="nowrap">客户类型</td>
          <td align="center" nowrap="nowrap">细分类型</td>
          <td width="5%" align="center" nowrap="nowrap">业务员姓名</td>
          <td align="center" nowrap="nowrap">促销员姓名</td>

          <td align="center" nowrap="nowrap">上年同期量</td>
          <td align="center" nowrap="nowrap">上年同期额</td>
          <td align="center" nowrap="nowrap">本期零售量</td>
          <td align="center" nowrap="nowrap">本期零售额</td>
          <td align="center" nowrap="nowrap">易TV量</td>
          <td align="center" nowrap="nowrap">易TV额</td>
          <td align="center" nowrap="nowrap">4K量</td>
          <td align="center" nowrap="nowrap">4K额</td>
           <td align="center" nowrap="nowrap">大板量</td>
          <td align="center" nowrap="nowrap">大板额</td>
		  <td>照片</td>
		 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${allList}" varStatus="vs">
            <tr>
              <td nowrap="nowrap" align="center">${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.dept_name)}</font></td>
              <td align="left" nowrap="nowrap"><font class="blue12px">${cur.store_name}</font></td>
              <td align="right" nowrap="nowrap"><font class="blue12px">${cur.store_id}</font></td>
               <td align="left" nowrap="nowrap">${cur.map.ext_customer_name}</td>
              <td align="left" nowrap="nowrap"><a href="${ctx}/manager/admin/KonkaR3Store.do?mod_id=${af.map.mod_id}&r3_code=${cur.r3_code}" class="fblue" title="点击可查询该客户全部门店">${fn:escapeXml(cur.r3_code)}</a></td>
            
              <td align="left" nowrap="nowrap">${cur.map.ext_par_customer_type_name}</td>
              <td align="left" nowrap="nowrap">${cur.map.ext_customer_type_name}</td>

              <td align="left" nowrap="nowrap"><font class="blue12px">${fn:escapeXml(cur.map.ext_ywy_name)}</font></td>
             
              <td align="left" nowrap="nowrap">
              	<font class="blue12px">${cur.map.ext_cxy_name}</font>
              </td>
          <td align="center" nowrap="nowrap">${cur.map.last_all_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.last_all_price}</td>
          <td align="center" nowrap="nowrap">${cur.map.cur_all_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.cur_all_price}</td>
          <td align="center" nowrap="nowrap">${cur.map.ytv_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.ytv_price}</td>
          <td align="center" nowrap="nowrap">${cur.map.k_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.k_price}</td>
           <td align="center" nowrap="nowrap">${cur.map.db_num}</td>
          <td align="center" nowrap="nowrap">${cur.map.db_price}</td> 
             
             
            <c:set var="file_desc" value="${fn:split(cur.map.file_desc,',')}" />
           <c:forEach items="${file_desc}" var="tt" varStatus="vs1">
           <c:set var="step" value="${vs1.count-1}" />
           <c:set var="cur_path" value="${fn:split(cur.map.save_path,',')[step]}" />
           <c:set var="num" value="${fn:length(cur_path)}" />
           <c:set var="cur_save_name" value="${fn:substring(cur_path,24,num)}" />
          	 <td align="center" nowrap="nowrap">
          	<a href="http://qdgl.konka.com/${cur_path}" target="_blank">${tt}</a>
          </td>
          </c:forEach>
            </tr>
          </c:forEach></tbody>
      </table>
</body>
</html>
