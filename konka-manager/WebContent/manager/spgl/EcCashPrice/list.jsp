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
.txt {float: left;padding-top:8px;}
.txt a {color: blue;}
.float_div{
	position: absolute;
	border: solid 1px #d1e3f5;
	top:220px;
	text-align: center;
	background: #f5f4f4;
	left:40%;
	width:400px;
	padding-bottom: 20px;
	padding-top: 0px;
	display: none;
	z-index:1000;
}
.float_div div{margin-top: 0px;}
.close{ float: right;bottom: 0px;color:red;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString} - 临时现款价管理</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/spgl/EcCashPrice">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
          			&nbsp;&nbsp;产品型号 ： <html-el:text property="pd_sn" styleId="pd_sn" styleClass="webinput" />&nbsp;&nbsp;
            <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
   <html-el:form action="/spgl/EcCashPrice.do?method=delete"> 
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				 <input type="button" class="but3" name="delete" id="delete" value="回收站" onclick="confirmDeleteAll(this.form);" />	&nbsp;<input type="button" class="but2" value="新 增" onclick="location.href='EcCashPrice.do?method=add&mod_id=${af.map.mod_id}';" />
           
          <input name="button" type="button" class="but_excel" class="websub" id="btn_import" value="导入" />
          <a href="${ctx }/manager/spgl/EcCashPrice/cashprice.xlsx">excel模版下载</a>
				</td>
			</tr>
		</table>		
	</div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" align="center" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></td>
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap"  width="15%"  align="center" >产品型号</td>
        <td width="10%" nowrap="nowrap" align="center">现款价</td> 
        <td width="10%" nowrap="nowrap" align="center">添加时间</td>
         <td width="6%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
       	  <td align="center" nowrap="nowrap"> <input name="pks" type="checkbox" id="pks" value="${cur.id}" /></td>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.pd_sn}</td>
          <td align="left" nowrap="nowrap">${cur.cash_price}</td> 
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}"  pattern="yyyy-MM-dd"></fmt:formatDate></td>
          <td align="center" nowrap="nowrap"> 
         	 <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定删除吗？', 'EcCashPrice.do','delete' ,'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">删除</span>
         	 <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'EcCashPrice.do','edit' ,'id=${cur.id}&mod_id=${af.map.mod_id}&' + $('#bottomPageForm').serialize())">修改</span> 
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
     
  </div>
   </html-el:form>
  <div class="clear"></div>
</div>
<div class="float_div" id="uploadPanel" style="z-index:10000;position:absolute;">
      <div style="font-size:14px;">
        <b>请选择Excel文件</b>
      </div><br />
	<form action="EcCashPrice.do?method=excelImport" method="post" enctype="multipart/form-data">
      <html-el:hidden property="method" value="excelImport" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<input type="file" name="excel"/> <input type="button" name="btn_submit" class="but4" id="btn_submit" value="导入"/>
	</form>
	<span style="float: right;bottom: 0px;" id="btn_close"><span style="color:red;">取消</span>&nbsp;</span>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#btn_import").click(function(){$("#uploadPanel").fadeIn(500);	});
	$("#btn_close").click(function(){$("#uploadPanel").fadeOut(500);		
		$("#excel").after($("#excel").clone().val(""));   
		$("#excel").remove();		
	}); 
	$("#btn_submit").click(function(){
		this.disabled=true;
		this.value="正在导入。。。";
		$("#btn_close").hide(); 
		this.form.submit();
	}); 
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
