<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <html-el:form action="/admin/BaseVisitType">
  <div class="rtabcont1">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td align="right"><strong class="fb">上报类型：</strong></td>
          <td>
          	<html-el:select property="report_type" styleId="report_type" style="width:120px;">
	        	<html-el:option value="">－请选择－</html-el:option>
	       		<html-el:option value="-1">通用</html-el:option>
                <html-el:option value="1">正常客户拜访</html-el:option>
				<html-el:option value="2">老客户重拾</html-el:option>
				<html-el:option value="3">新客户开拓</html-el:option>
				<html-el:option value="4">事务上报</html-el:option>
            </html-el:select>
          </td>
          <td align="right"><strong class="fb">拜访类型：</strong></td>
          <td> 
		      <html-el:select property="visit_type_name" styleId="visit_type_name" style="width:120px;">
			   <html-el:option value="">－请选择－</html-el:option>
      		   <html-el:option value="库存">库存</html-el:option>
      		   <html-el:option value="样机">样机</html-el:option>
      		   <html-el:option value="培训">培训</html-el:option>
      		   <html-el:option value="售后">售后</html-el:option>
      		   <html-el:option value="费用">费用</html-el:option>
      		   <html-el:option value="结构调整">结构调整</html-el:option>
      		   <html-el:option value="价格">价格</html-el:option>
      		   <html-el:option value="窜货">窜货</html-el:option>
      		   <html-el:option value="演示设备">演示设备</html-el:option>
      		   <html-el:option value="其他">其他</html-el:option>
        	  </html-el:select>
          </td>
          <td align="right"><strong class="fb">状态：</strong></td>
          <td>
         	 <html-el:select property="state" styleId="state" style="width:120px;">
			 	<html-el:option value="">－请选择－</html-el:option>
      		    <html-el:option value="0">启用</html-el:option>
      		    <html-el:option value="1">停用</html-el:option>
        	 </html-el:select>
          </td>
          <td></td>
        </tr>
      </table>
  </div>
  <div style="text-align: right;padding-right: 15px">
  	<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />&nbsp;&nbsp;&nbsp;&nbsp;
  	<input type="button" class="but2" value="新 增" onclick="location.href='BaseVisitType.do?method=add&mod_id=${af.map.mod_id}';" />
  </div>
  </html-el:form>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >上报类型</td>
        <td nowrap="nowrap" align="center" >拜访类型</td>
        <td width="15%" nowrap="nowrap" align="center">添加人</td>
         <td width="15%" nowrap="nowrap" align="center">添加时间</td>
         <td width="6%" nowrap="nowrap" align="center">状态</td>
         <td width="6%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.report_type eq -1}">通用</c:if>
          <c:if test="${cur.report_type eq 1}">正常客户拜访</c:if>
          <c:if test="${cur.report_type eq 2}">老客户重拾</c:if>
          <c:if test="${cur.report_type eq 3}">新客户开拓</c:if>
          <c:if test="${cur.report_type eq 4}">事务上报</c:if>
          </td>
          <td align="left" nowrap="nowrap">${cur.visit_type_name}</td>
          <td align="left" nowrap="nowrap">${cur.map.add_name}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.state eq 0}">启用</c:if>
          <c:if test="${cur.state eq 1}">停用</c:if>
          </td>
          <td align="center" nowrap="nowrap"> 
            <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'BaseVisitType.do','edit' ,'visit_type_id=${cur.visit_type_id}&mod_id=${af.map.mod_id}')">修改</span>
            <span style="cursor:pointer;display: none;" class="fblue" onclick="doNeedMethod(null, 'BaseVisitType.do','delete' ,'visit_type_id=${cur.visit_type_id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())"')">删除</span> 
          </td> 
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:10px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="BaseVisitType.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="20"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("report_type", "${af.map.report_type}");
								pager.addHiddenInputs("visit_type_name", "${af.map.visit_type_name}");
								pager.addHiddenInputs("state", "${af.map.state}");
								document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
