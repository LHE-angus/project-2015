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
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaPindexAreaGdp">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td height="36" align="left" style="padding-left:5px;">
               &nbsp;&nbsp;
               地区名：<html-el:text property="p_name_like" size="16" maxlength="30" styleId="p_name_like" styleClass="webinput" />
            &nbsp;&nbsp; &nbsp;&nbsp; <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
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
          <input name="button" type="button" class="but_excel" class="websub" id="btn_import" value="导入" />
          <a class="fblue" href="${ctx }/manager/admin/KonkaPindexAreaGdp/pindex.xls">excel模版下载</a>
				</td>
			</tr>
		</table>		
	</div>
  <div class="rtabcont1">
    <div style="overflow-x:auto;height: 340px;">
    <form id="listForm" name="listForm" method="post" action="KonkaPindexAreaGdp.do?method=updateBatch">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="10%" nowrap="nowrap" align="center" >区域编号</td>
         <td width="10%" nowrap="nowrap" align="center" >地区名</td>
        <td width="10%" nowrap="nowrap" align="center">GDP</td>
        <td width="10%" nowrap="nowrap" align="center">行政区域内人口(人)</td>
         <td width="10%" nowrap="nowrap" align="center">面积（平米）</td>
         <td width="10%" nowrap="nowrap" align="center">家电客户数</td>
         <td width="5%" nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${vs.count}</td>
          <td align="left" nowrap="nowrap">${cur.p_index}</td>
          <td align="center" nowrap="nowrap">${cur.p_name}</td>
          <td align="center" nowrap="nowrap">
          <fmt:formatNumber value="${cur.gdp}"  pattern="#0.00"></fmt:formatNumber>
          </td>
          <td align="center" nowrap="nowrap">
          <fmt:formatNumber value="${cur.p_area}" maxFractionDigits="0"></fmt:formatNumber>
          </td>
         
          <td align="center" nowrap="nowrap">
           <fmt:formatNumber value="${cur.column_1}"  pattern="#0.00"></fmt:formatNumber>
          </td>
           <td align="center" nowrap="nowrap">
            <fmt:formatNumber value="${cur.column_2}" maxFractionDigits="0"></fmt:formatNumber>
          </td>
          <td  align="center" nowrap="nowrap">
          	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaPindexAreaGdp.do','edit' ,'p_index=${cur.p_index}&' + $('#bottomPageForm').serialize())">修改</span> 
          </td>
        </tr>
      </c:forEach>
    </table>
    </form>
    </div>
    <c:if test="${not vs.last}">
      <div style="height:40px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaPindexAreaGdp.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
								var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("p_name_like", "${af.map.p_name_like}");
								document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div class="float_div" id="uploadPanel" style="z-index:10000;position:absolute;">
      <div style="font-size:14px;">
        <b>请选择Excel文件</b>
      </div><br />
	<form action="KonkaPindexAreaGdp.do?method=excelImport" method="post" enctype="multipart/form-data">
      <html-el:hidden property="method" value="excelImport" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	<input type="file" name="excel"/> <input type="button" name="btn_submit" class="but4" id="btn_submit" value="导入"/>
	</form>
	<span style="float: right;bottom: 0px;" id="btn_close"><span style="color:red;">取消</span>&nbsp;</span>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
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


function updateBatchAll(form) {
	var checkedCount = 0;
	if (!form.pks) {
		return;
	}
	if(!form.pks.length) {
		if (form.pks.checked == true) {
			checkedCount = 1;
		}
	}
	for (var i = 0; i < form.pks.length; i++) {
		if (form.pks[i].checked == true) {
			checkedCount++;
		}
	}
	if (checkedCount == 0) {
		alert("\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u5361\u53f7\uff01");
	} else {
		if(confirm("\u4f60\u786e\u8ba4\u6279\u91cf\u66f4\u65b0\u8fd9\u4e9b\u7528\u6237\u5417\uff1f")) {
			form.method.value = "updateBatch";
			form.submit();
		}
	}
}

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
