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
.rtable1 td {
	padding:0px 2px;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<!-- Sidebar ends --> <!-- Main bar -->
<div class="mainbar"><!-- Page heading -->

<!-- Page heading ends --> <!-- Matter -->

<div class="matter">
<div class="container" align="center">




<div class="oarcont" id="body_oarcont">
  <html-el:form action="/leader/JcfxKczzKh">
  <div class="rtabcont1">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="report_type" styleId="report_type" value="${af.map.report_type}" />
      <html-el:hidden property="isfirst" value="first"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
       	<tr>
       		<td align="right"><strong class="fb">R3_编码：</strong></td>
       		<td>
       			<html-el:text property="_r3_code_like" styleId="_r3_code_like" size="20"/>
       		</td>
       		<td align="right"><strong class="fb">客户名称：</strong></td>
       		<td>
       			<html-el:text property="_customer_name_like" styleId="_customer_name_like" size="20"/>
       		</td>
       		<td align="right"><strong class="fb">添加人：</strong></td>
       		<td align="right">
       		    <html-el:text property="_add_user_name" styleId="_add_user_name" size="20"/>
       		</td>
        	<td align="right">
        		<input name="button" type="button" class="bgSearch" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" />
        	</td>
        	<td colspan="3"></td>
       	</tr>
      </table>
  </div>
  <div style="text-align: left;padding-left: 10px">
  	<input type="button" class="but2" value="新 增" onclick="location.href='JcfxKczzKh.do?method=add&mod_id=${af.map.mod_id}';" />&nbsp;&nbsp;&nbsp;&nbsp;
  	<input type="button" class="but2" value="分组" onclick="location.href='JcfxKczzKhfz.do';" />&nbsp;&nbsp;&nbsp;&nbsp;
  	<input type="button" class="but2" value="返回" onclick="history.back();return false;" />&nbsp;&nbsp;&nbsp;&nbsp;
	<input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" />
  </div>
  </html-el:form>
  <div class="rtabcont1">
		<%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1" style="overflow-x:auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td nowrap="nowrap" align="center" >R3_CODE</td>
        <td  nowrap="nowrap" align="center">客户名称</td> 
        <td nowrap="nowrap" align="center" >添加时间</td>
        <td nowrap="nowrap" width="150px;" align="center">添加人</td>
        <td  nowrap="nowrap" align="center">操作</td>
      </tr>
      <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
      	<td align="center" nowrap="nowrap">${vs.count}</td>
      	<td align="center" nowrap="nowrap">${cur.r3_code}</td>
      		<td align="center" nowrap="nowrap">${cur.coustmer_name}</td>
        <td align="center" nowrap="nowrap">
        	<fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd"></fmt:formatDate>
        </td>
        <td align="center" nowrap="nowrap">${cur.add_user_name}</td> 
        <td align="center" nowrap="nowrap"> 
             <a href="javascript:void;" onclick="delteCoustmer(${cur.id})">删除</a>
        </td> 
      </tr>
      </c:forEach>
    </table>
    <c:if test="${not vs.last}">
      <div style="height:10px;"></div>
    </c:if>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="JcfxKczzKh.do">
    <input id='export_id' style="display:none"  name='excel_all' value='0' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="20"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
						var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
								pager.addHiddenInputs("method", "list");
								pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
								pager.addHiddenInputs("report_type", "${af.map.report_type}");
								document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<!-- Matter ends --></div>

<!-- Mainbar ends -->
<div class="clearfix"></div>

</div>
<!-- Content ends -->

<!-- Footer starts -->
<footer>
<jsp:include page="/manager/leader/foot.jsp"></jsp:include>
</footer>

<!-- Footer ends -->

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/imgpreview.0.22.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$(".sidebar #nav > li > a").removeAttr("class");
	$(".sidebar #nav > li > a:eq(3)").attr("class","open subdrop");
$("#shop_id").attr("Require","false").attr("dataType", "Number").attr("msg", "终端名称必须填写数字");

$("#button").click(function(){
	if (Validator.Validate(this.form, 1)){
		this.form.submit();
		}
});
});

function delteCoustmer(id){
	var paras="method=delete";
	if(id){paras+="&id="+id;}else{alert("删除标示获取失败！");}
	$.ajax({
		   type: "POST",
		   url: "${ctx}/manager/admin/JcfxKczzKh.do",
		   data: paras,
		   success: function(result){
		         alert(result.res);
		         window.location.reload();
		   },
	       error:function(XMLHttpRequest, textStatus, errorThrown){
                alert("删除异常！");
		   }
	});
}
//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#export_id").val(1);
		$("#bottomPageForm").submit();
	}
	$("#export_id").val(0);
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
