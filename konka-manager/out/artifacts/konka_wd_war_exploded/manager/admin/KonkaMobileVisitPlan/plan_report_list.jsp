<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<style type="text/css">
.webinput {
	background:#f5f4f4;
	padding-left: 5px;
	height: 19px;
	line-height: 19px;
	/*font-family: Arial, Helvetica, sans-serif;*/
	border: #ccc solid 1px;
}
ul.ckUl{list-style-type:none;display:inline;}
ul.ckUl li{float:left;margin:auto 5px auto 0px;/*padding:2px 5px;*/}
input,textarea,select{font-family:Microsoft Yahei;font-size:12px;}
.ck-li{position:relative;}
.ck-li .hidden-accessible{position:absolute !important;clip:rect(1px 1px 1px 1px);}
.ck-li .ck-default{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #CCC;background: #F6F6F6;font-weight: bold;color:#C4C4C4;cursor:pointer;}
.ck-li .ck-hover{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:1px solid #FBCB09; background:#FDF5CE;font-weight: bold;color:#C77405;cursor:pointer;}
.ck-li .ck-visited{font-family:Microsoft yahei,verdana,Geneva,Tahoma,Georgia;font-size:12px;display:inline-block;padding:1px 8px;height:16px;line-height:16px;border:2px solid #EF0F28/*#FF4800/*FBD850*/; background:white url("${ctx}/styles/customer/images/ck-visited.gif") right bottom no-repeat;font-weight:bold;color:#EF0F28/*#FF4800/*#EB8F00*/;cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
  <div class="rtabcont1" style="overflow-x:auto;">
   <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >计划月份</td>
        <td  nowrap="nowrap" align="center">分公司</td>
        <td nowrap="nowrap" align="center" title="业务员JOBID">上报人</td>
        <td nowrap="nowrap" align="center">计划拜访次数</td>
        <td nowrap="nowrap" align="center" >计划拜访客户数</td>
        <td nowrap="nowrap" align="center" >计划拜访终端数</td>
        <td nowrap="nowrap" align="center" >计划开拓数</td>
        <td  nowrap="nowrap" width="150px;" align="center">拜访客户</td>
        <td nowrap="nowrap" width="150px;" align="center">拜访终端</td>
        <td nowrap="nowrap" align="center" >计划说明</td>
        <td nowrap="nowrap" align="center" >创建日期</td>
         <td nowrap="nowrap" align="center" >最后更新日期</td>
        <td nowrap="nowrap" align="center">状态</td>
        <td nowrap="nowrap" align="center">数据来源</td>
      </tr>
      <c:forEach items="${allList}" var="cur" varStatus="vs">
        <tr>
         <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="center" nowrap="nowrap">
          	<fmt:formatDate value="${cur.plan_begin_date}" pattern="yyyyMM"></fmt:formatDate>
          </td>
          <td nowrap="nowrap" align="center">${cur.map.subcomp_name}</td>
          <td nowrap="nowrap" align="center">${cur.report_nae}</td>
          <td nowrap="nowrap" align="center">${cur.plan_of_access}</td>
          <td nowrap="nowrap" align="center">${cur.plan_of_access_cust}</td>
          <td nowrap="nowrap" align="center">${cur.plan_of_access_shop}</td>
          <td nowrap="nowrap" align="center">${cur.plan_of_dev_cust}</td>
          <td nowrap="nowrap" align="center" title="${cur.map.customer_names}">
            ${cur.map.customer_names}
          </td>
          <td nowrap="nowrap" align="center"  title="${cur.map.shop_names}">
            ${cur.map.shop_names}
          </td>
          <td nowrap="nowrap" align="left" title="${cur.plan_desc}">
            ${cur.plan_desc}
           </td>
           <td align="center" nowrap="nowrap">
            	<fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate>
           </td>
            <td align="center" nowrap="nowrap">
            	<fmt:formatDate value="${cur.update_date}" pattern="yyyy-MM-dd"></fmt:formatDate>
           </td>
           <td nowrap="nowrap" align="center">
             <c:if test="${cur.is_del eq 0}">正常</c:if>
             <c:if test="${cur.is_del eq 1}">删除</c:if>
           </td>
           <td nowrap="nowrap" align="center">
             <c:if test="${cur.data_source eq 0}">手机端</c:if>
	      	 <c:if test="${cur.data_source eq 1}">手机端</c:if>
             <c:if test="${cur.data_source eq 2}">web端</c:if>
             <c:if test="${cur.data_source eq 3}">手机端</c:if>
           </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
  </div>
</body>
</html>
