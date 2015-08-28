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
         <th nowrap="nowrap" align="center">分公司名称</th>
        <th nowrap="nowrap" align="center">创建人</th>
        <th nowrap="nowrap" align="center">创建日期</th>
	    <th nowrap="nowrap" align="center" >父任务名称</th>
	    <th nowrap="nowrap" align="center">任务名称</th>
	      <th nowrap="nowrap" align="center">当前子任务数</th>
	    <th nowrap="nowrap" align="center">优先级</th>
	    <th nowrap="nowrap" align="center">任务类型</th>
	    <th nowrap="nowrap" align="center" >任务内容</th>
	    <th nowrap="nowrap" align="center" >备注</th>
	    <th nowrap="nowrap" align="center">任务状态</th>
	     <th nowrap="nowrap" align="center">任务开始日期</th>
	     <th nowrap="nowrap" align="center">预计完成期限</th>
<!--	    <th nowrap="nowrap" align="center">任务交办人</th>-->
	    <th nowrap="nowrap" align="center">是否启用</th>
	    <th nowrap="nowrap" align="center">附件</th>
      </tr>
      <c:forEach items="${allList}" var="cur" varStatus="vs">
        <tr>
         <td align="center" nowrap="nowrap">${vs.count}</td>
         <td align="left" nowrap="nowrap">${cur.map.fgs_Dept}</td>
         <td align="left" nowrap="nowrap">${cur.real_name}</td>
         <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
           <td align="left" nowrap="nowrap">${cur.par_task_name}</td>
           <td align="left" nowrap="nowrap">${cur.task_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.subcount}</td>
            <td align="left" nowrap="nowrap">
             <c:if test="${cur.priority eq 0}">高</c:if>
             <c:if test="${cur.priority eq 1}">中</c:if>
             <c:if test="${cur.priority eq 2}">低</c:if>
            </td>
           <td align="left" nowrap="nowrap">${cur.map.type_name}</td>
           <td align="left" nowrap="nowrap">${cur.content}</td>
           <td align="left" nowrap="nowrap">${cur.remark}</td>
           <td align="left" nowrap="nowrap">
           <c:if test="${cur.task_state eq 0}">未开始</c:if>
           <c:if test="${cur.task_state eq 1}">进行中</c:if>
           <c:if test="${cur.task_state eq 2}">已完成</c:if>
           </td>
           <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.begin_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
            <td align="left" nowrap="nowrap"><fmt:formatDate value="${cur.finsh_date}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
<!--           <td align="left" nowrap="nowrap">${cur.map.reveUser}</td>-->
            <td align="left" nowrap="nowrap">
            <c:if test="${cur.is_del eq 0}">启用</c:if>
             <c:if test="${cur.is_del eq 1}">不启用</c:if>
            </td>
           <td align="left" nowrap="nowrap">
            <c:if test="${not empty cur.map.fj_paths}">
	          <c:set var="fj_paths" value="${fn:split(cur.map.fj_paths,',')}" />
	          <c:forEach items="${fj_paths}" var="fj_path" varStatus="vs1">
	          	<a href="http://qdgl.konka.com/${fj_path}" target="_blank">&nbsp;附件${vs1.count}</a>&nbsp;&nbsp;
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
