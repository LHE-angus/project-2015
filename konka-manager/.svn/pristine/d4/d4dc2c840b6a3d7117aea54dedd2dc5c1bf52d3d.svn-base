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
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcCust">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;客户名称  ： <html-el:text property="cust_name_like" styleId="cust_name_like" maxlength="30" styleClass="webinput" />
					&nbsp;&nbsp;客户编码  ： <html-el:text property="cust_code_like" styleId="cust_code_like" maxlength="30" styleClass="webinput" />&nbsp;&nbsp;
					状态：<html-el:select property="del_mark" styleId="del_mark" onchange="this.form.submit();">
						 <html-el:option value="">全部</html-el:option>
						 <html-el:option value="0">正常</html-el:option>
						 <html-el:option value="1">已停用</html-el:option>
					</html-el:select>&nbsp;&nbsp;客户类型：<html-el:select property="cust_type" styleId="cust_type" >
						 <html-el:option value="">全部</html-el:option>
						 <html-el:option value="0">R3客户</html-el:option>
						 <html-el:option value="1">虚拟客户</html-el:option>
					</html-el:select>&nbsp;&nbsp;  
		  </td>
		  </tr>
		  <tr>
		  <td height="36" align="left" style="padding-left:5px;">
		  	&nbsp;&nbsp;
					R3编码  ： <html-el:text property="r3_code_like" styleId="r3_code_like" maxlength="30" styleClass="webinput" />&nbsp;&nbsp;
					添加人：<html-el:text property="add_user_name_like" styleId="add_user_name_like" maxlength="30" styleClass="webinput" />&nbsp;&nbsp;	所属组织：<html-el:select property="group_id" styleId="group_id" style="width:120px;">
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${deptList}" var="cur">
	          					<html-el:option value="${cur.id}">${cur.group_name}</html-el:option>
	          				</c:forEach>
	          			</html-el:select> &nbsp;&nbsp;
		 </td>
		 </tr>
		 <tr>
		  <td height="36" align="left" style="padding-left:5px;">
		   &nbsp;&nbsp;
	       绑定会员  ： <html-el:text property="user_name_like" styleId="user_name_like" maxlength="30" styleClass="webinput" />&nbsp;&nbsp;  
		   &nbsp;&nbsp;添加时间：       
		   <html-el:text property="add_time_start" styleId="add_time_start"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				至
				<html-el:text property="add_time_end" styleId="add_time_end"  size="9" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />&nbsp;&nbsp;							
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
             <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				    <logic-el:match name="popedom" value="+1+"> 
					&nbsp;&nbsp;<input type="button" class="but2" value="新 增" onclick="location.href='EcCust.do?method=add&mod_id=${af.map.mod_id}';" />
					</logic-el:match>
				</td>
			</tr>
		</table>		
	</div>
  <div class="rtabcont1" style="overflow: auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >所属组织</td>
        <td nowrap="nowrap" width="10%" align="center">客户名称</td>
        <td width="10%" nowrap="nowrap" align="center">客户编码</td>
        <td width="10%" nowrap="nowrap" align="center">R3编码</td>
        <td width="10%" nowrap="nowrap" align="center">客户类型</td>
        <td width="10%" nowrap="nowrap" align="center">绑定会员</td>
        <td width="10%" nowrap="nowrap" align="center">状态</td>
        <td width="10%" nowrap="nowrap" align="center">添加人</td>
        <td width="10%" nowrap="nowrap" align="center">添加时间</td>
        <td width="6%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.map.group_name}</td>
          <td align="left" nowrap="nowrap">${cur.cust_name}</td>
          <td align="left" nowrap="nowrap">${cur.cust_code}</td> 
          <td align="left" nowrap="nowrap">${cur.r3_code}</td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.cust_type eq 0}">R3客户</c:if>
          <c:if test="${cur.cust_type eq 1}">虚拟客户</c:if>
          </td>
          <td align="left" nowrap="nowrap" title="${cur.map.ec_user_names}"><a style="text-decoration:underline;" href="EcCust.do?method=viewEcUser&id=${cur.id}&mod_id=${af.map.mod_id}">
          <c:choose>
                 <c:when test="${fn:length(cur.map.ec_user_names) > 40}">
                 <c:out value="${fn:substring(cur.map.ec_user_names, 0, 40)}...." />
                 </c:when>
             <c:otherwise>
                 <c:out value="${cur.map.ec_user_names}" />
                 </c:otherwise>
             </c:choose>
         </a></td>
          <td align="left" nowrap="nowrap">
          <c:if test="${cur.del_mark eq 0}">正常</c:if>
          <c:if test="${cur.del_mark eq 1}">已停用</c:if>
          </td>
          <td align="left" nowrap="nowrap">
           <c:choose>
                 <c:when test="${fn:length(cur.map.add_user_name) > 20}">
                 <c:out value="${fn:substring(cur.map.add_user_name, 0, 20)}...." />
                 </c:when>
             <c:otherwise>
                 <c:out value="${cur.map.add_user_name}" />
                 </c:otherwise>
             </c:choose>
          </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap">
            <logic-el:match name="popedom" value="+4+"> 
            <c:if test="${cur.del_mark eq 0}">
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定停用吗？', 'EcCust.do','delete' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">停用</span>
         	</c:if>
         	<c:if test="${cur.del_mark eq 1}">
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定启用吗？', 'EcCust.do','restart' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">启用</span>
         	</c:if>
         	</logic-el:match>
         	<logic-el:match name="popedom" value="+2+"> 
         	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcCust.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span> 
			</logic-el:match>          
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcCust.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("del_mark", "${af.map.del_mark}");
								pager.addHiddenInputs("cust_name_like", "${af.map.cust_name_like}");
								pager.addHiddenInputs("cust_code_like", "${af.map.cust_code_like}");
								pager.addHiddenInputs("cust_type", "${af.map.cust_type}");
								pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
								pager.addHiddenInputs("add_user_name_like", "${af.map.add_user_name_like}");
								pager.addHiddenInputs("group_id", "${af.map.group_id}");
								pager.addHiddenInputs("user_name_like", "${af.map.user_name_like}");
								pager.addHiddenInputs("add_time_start", "${af.map.add_time_start}");
							    pager.addHiddenInputs("add_time_end", "${af.map.add_time_end}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 导出excel
    $("#export_excel").click(function(){
    	 this.value="正在提交...";
    	 this.disabled=true;
    	 this.form.action="${ctx}/manager/spgl/EcCust.do?method=sheet";
    	 this.form.submit();
    	 this.form.action="${ctx}/manager/spgl/EcCust.do";
    	 this.value="导出Excel";
    	 this.disabled=false;
    });
	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
