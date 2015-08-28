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
 <div class="rtabcont1">
    <html-el:form action="/admin/GcxmProj.do" > 
      <html-el:hidden property="method" value="chooseModel" /> 
       <html-el:hidden property="title" value="${af.map.title}" styleId="title" /> 
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td>
          	&nbsp;产品型号：
            <html-el:text property="md_name_like" styleId="md_name_like" maxlength="30" styleClass="webinput" onkeyup="upperCase(this.id);"/>
            &nbsp;产品名称：
            <html-el:text property="pd_desc_like" styleId="pd_desc_like"  maxlength="30" styleClass="webinput" />&nbsp;&nbsp;
            <html-el:submit styleClass="but1" value="搜索" />
          </td> 
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="overflow: auto;">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>  
        <td nowrap="nowrap" align="center" >产品型号</td>
        <td nowrap="nowrap" align="center" >产品名称</td>
        <td nowrap="nowrap" align="center" >操作</td>
      </tr>
       <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr style="height:60px;">
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="center" nowrap="nowrap"> ${cur.md_name}</td>  
            <td align="center"> ${cur.pd_desc}</td>
            <td align="center" nowrap="nowrap"><a href="#" style="cursor: pointer;color: blue;" onclick="choose('${cur.md_name}')">选择</a></td>
          </tr> 
        </c:forEach>
        <tr>
          <td align="center" nowrap="nowrap" colspan="8"><input type="button" value="关闭" onclick="clos();" style="height:30px;width:80px;"/></td>
        </tr>
    </table>
  </div>
  <div class="clear"></div>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="GcxmProj.do?method=chooseModel"> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("md_name_like", "${fn:escapeXml(af.map.md_name_like)}");
	            pager.addHiddenInputs("pd_desc_like", "${fn:escapeXml(af.map.pd_desc_like)}");
	            pager.addHiddenInputs("title", "${fn:escapeXml(af.map.title)}");
		        document.write(pager.toString()); 
	      </script></td>
        </tr>
      </table>
    </form>
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

//查找md_name
function choose(md_name){
      var title = $("#title").val();
      var model = "model_"+title; 
	  var api = frameElement.api, W = api.opener;
	  W.document.getElementById(model).value=md_name;
	  api.close(); 
}

function upperCase(x)
{
var y=document.getElementById(x).value;
document.getElementById(x).value=y.toUpperCase();
}


function clos(){
	 var api = frameElement.api, W = api.opener;
	 api.close();
}


//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
