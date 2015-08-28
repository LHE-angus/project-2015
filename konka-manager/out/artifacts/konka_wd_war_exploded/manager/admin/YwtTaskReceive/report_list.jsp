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
    <table width="100%" border="1" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >分公司</td>
        <td nowrap="nowrap" align="center" >接收人</td>
         <td nowrap="nowrap" align="center" >父任务名称</td>
        <td  nowrap="nowrap" align="center">任务名称</td>
         <td  nowrap="nowrap" align="center">子任务数</td>
         <td nowrap="nowrap" align="center" >接收状态</td>
        <td nowrap="nowrap" align="center">接收日期</td>
        <td nowrap="nowrap" align="center">任务开始日期</td>
        <td nowrap="nowrap" align="center" >任务完成期限 </td>
        <td nowrap="nowrap" align="center" >完成状态</td>
         <td nowrap="nowrap" align="center" >完成日期</td>
         <td nowrap="nowrap" align="center" >审核状态</td>
         <td nowrap="nowrap" align="center" >审核日期</td>
         <td nowrap="nowrap" align="center" >审核意见</td>
         <td nowrap="nowrap" align="center" >附件</td>
      </tr>
      <c:forEach items="${allList}" var="cur" varStatus="vs">
        <tr>
         <td align="center" nowrap="nowrap">${vs.count}</td>
         <td align="left" nowrap="nowrap">${cur.map.fgs_Dept}</td>
         <td align="left" nowrap="nowrap">${cur.map.user_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.par_task_name}</td>
           <td align="left" nowrap="nowrap">${cur.task_name}</td>
           <td align="left" nowrap="nowrap">${cur.map.sub_task_count}</td>
             <td align="left" nowrap="nowrap">
             <c:if test="${cur.is_receive eq 0}">接收</c:if>
             <c:if test="${cur.is_receive eq 1}">拒绝</c:if>
             <c:if test="${cur.is_receive eq 2}">未接受</c:if>
           </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
           <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.map.begin_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
            <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.map.finsh_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
           <td align="left" nowrap="nowrap">
            <c:if test="${cur.state eq 0}">完成</c:if>
             <c:if test="${cur.state eq 1}">未完成</c:if>
           </td>
           <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.reve_finsh_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
           <td align="left" nowrap="nowrap">
            <c:if test="${cur.audit_state eq 0}">已审核</c:if>
            <c:if test="${cur.audit_state eq 1}">未审核</c:if>
           </td >
           <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.audit_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
           <td align="left" nowrap="nowrap">
             ${cur.audit_info}
           </td >
           <td align="left" nowrap="nowrap">
           <c:if test="${not empty cur.map.fj_paths}">
	          <c:set var="fj_paths" value="${fn:split(cur.map.fj_paths,',')}" />
	          <c:forEach items="${fj_paths}" var="fj_path" varStatus="vs1">
	          	<a href="http://qdgl.konka.com/${fj_path}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
	          </c:forEach>
           </c:if>
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
