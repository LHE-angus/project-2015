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
    <html-el:form action="/spgl/EcSenderInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;发货人姓名 ： <html-el:text property="real_name_like" styleId="real_name_like" styleClass="webinput"  maxlength="30"/>
					&nbsp;&nbsp;分公司  ： <html-el:text property="dept_name_like" styleId="dept_name_like" styleClass="webinput"  maxlength="30"/>&nbsp;&nbsp;
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
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
					&nbsp;&nbsp;<input type="button" class="but2" value="新 增" onclick="location.href='EcSenderInfo.do?method=add&mod_id=${af.map.mod_id}';" />
				</td>
			</tr>
		</table>		
	</div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >发货人姓名</td>
        <td nowrap="nowrap" width="10%" align="center">省</td>
        <td width="10%" nowrap="nowrap" align="center">市</td>
        <td width="10%" nowrap="nowrap" align="center">县/镇</td>
        <td width="10%" nowrap="nowrap" align="center">详细地址</td>
        <td width="10%" nowrap="nowrap" align="center">分公司</td>
        <td width="10%" nowrap="nowrap" align="center">电话</td>
        <td width="10%" nowrap="nowrap" align="center">手机</td>
        <td width="10%" nowrap="nowrap" align="center">月结账号</td>
        <td width="10%" nowrap="nowrap" align="center">添加人</td>
        <td width="10%" nowrap="nowrap" align="center">添加时间</td>
        <td width="6%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="right" nowrap="nowrap">${cur.real_name}</td>
          <td align="right" nowrap="nowrap">${cur.province}</td>
          <td align="right" nowrap="nowrap">${cur.city}</td>
          <td align="right" nowrap="nowrap">${cur.country}</td>
          <td align="left" nowrap="nowrap" title="${cur.addr}">${fn:substring(cur.addr,0,30)}</td>
          <td align="left" nowrap="nowrap">${cur.dept_name} </td>
          <td align="left" nowrap="nowrap">${cur.tel} </td>
          <td align="left" nowrap="nowrap">${cur.mobile} </td>
          <td align="left" nowrap="nowrap">${cur.month_account} </td>
          <td align="left" nowrap="nowrap">${cur.add_user_name} </td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap">
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcSenderInfo.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
            <c:if test="${cur.add_user_id eq user_id}">
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定删除吗？', 'EcSenderInfo.do','delete' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
         	 <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcSenderInfo.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span> 
          	</c:if>
          	<c:if test="${cur.add_user_id ne user_id}">
          	<span  class="fblue" style="color:#ccc;">删除</span>
         	<span class="fblue" style="color:#ccc;" >修改</span> 
          	</c:if> 
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="EcSenderInfo.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("real_name_like", "${af.map.real_name_like}");
								pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
