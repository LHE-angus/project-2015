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
<tr>
 		  <td width="5%" >序号</td>
          <td width="4%">分公司</td>
           <td width="4%">门店ID</td>
          <td width="10%">门店名称</td>
          <td width="10%">客户名称</td>
          <td width="4%">客户R3编码</td>
          <td>客户类型</td>
          <td>细分类型</td>
          <td width="4%">业务员岗位</td>
          <td width="4%">业务员姓名</td>
          <td width="5%">有/无促销员</td>
          <td width="10%">促销员姓名</td>
          <td width="10%">促销员类型</td>
           <td width="10%">促销员ID</td>
          <td width="4%">经办名称</td>
          <td width="4%">经办经理</td>
          <td width="4%">省</td>
          <td width="4%">市</td>
          <td width="4%">县</td>
          <td width="4%">乡/镇</td>
          <td width="4%">街道地址</td>
          <td width="4%">仓库名称</td>
          <td width="10%">仓库送达方编码</td>
          <td width="10%">门店R3编码</td>
          <td width="5%" >创建时间</td>
          <td width="5%" >维护时间</td>
          <td width="10%">创建人</td>
          <td width="10%">维护人</td>
      </tr>
 <c:forEach var="cur" items="${allList}" varStatus="vs">
          <tr>
            <td >${(af.map.pager.currentPage - 1)*af.map.pager.pageSize + vs.count}</td>
            <td ><font class="blue12px">${fn:escapeXml(cur.DEPT_NAME)}</font></td>
             <td ><font class="blue12px">${cur.STORE_ID}</font></td>
            <td ><font class="blue12px">${cur.STORE_NAME}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.KH_NAME)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.R3_CODE)}</font></td>
            <td><font class="blue12px">${cur.CUSTOMER_TYPE1}</font></td>
            <td><font class="blue12px">${cur.CUSTOMER_TYPE2}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.YWY_JOB_ID)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.YWY_NAME)}</font></td>
            <td ><font class="blue12px">${cur.HAVAMAN}</font></td>
            <td><font class="blue12px">${cur.CXY_NAME_ALL}</font></td>
            <td><font class="blue12px">${cur.CXY_TYPE_NAME}</font></td>
             <td ><font class="blue12px">${cur.CXY_ID_ALL}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.JB_NAME)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.V_JBJL_NAME)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.PROVINCE)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.CITY)}</font></td>
           <td ><font class="blue12px">${fn:escapeXml(cur.COUNTRY)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.TOWN)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.ADDR)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.CK_NAME)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.MF_SN)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.R3_SDF_SN)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.ADD_DATE)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.MODIFY_DATE)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.CREATE_MAN)}</font></td>
            <td ><font class="blue12px">${fn:escapeXml(cur.MODIFY_MAN)}</font></td>
          </tr>
        </c:forEach>
    </table>
</body>
</html>
