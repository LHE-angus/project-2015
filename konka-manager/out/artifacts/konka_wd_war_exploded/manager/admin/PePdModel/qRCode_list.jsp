<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style>
#img-container li {
	float:left;
	width:240px;
	height:260px;
	margin:5px;
	padding:5px;
}
#img-container span.img {
	display:block;
	width:240px;
	height:240px;
	text-align:center;
	border:1px solid #CCC;
	margin-left:auto;
	margin-right:auto;
}
#img-container span.img-title {
	display:block;
	width:100%;
	height:32px;
	line-height:16px;
	margin-bottom:10px;
}
span.clear {
	clear:both;
}
</style>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/PePdModel">
      <html-el:hidden property="method" value="qRCode" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="is_jdxx_pd" styleId="is_jdxx_pd" value="" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">产品型号：</strong>
            <html-el:text property="md_name_like" styleId="md_name_like" maxlength="40" /></td>
            <td><strong class="fb">产品类别：</strong>
            <html-el:select property="cls_id" styleId="cls_id" style="width:120px;">
              <c:forEach var="cur" items="${basePdClazzList}">
                <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
              </c:forEach>
            </html-el:select></td>
          <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
    <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
    	<td>
    	<div id="main_container">
          <ul id="img-container">
            <c:forEach var="cur" items="${entityList}" varStatus="vs">
              <li> <span class="img">
                <img src="${ctx}/images/QRCode.jpg?s=${cur.map.str}"></img>
                </span> <span class="img-title">型号：${fn:escapeXml(cur.md_name)}，大类：${cur.map.cls_name}</span></li>
            </c:forEach>
          </ul>
          <span class="clear"></span> </div>
    	</td>
    </tr>
    </table>
    </div>
  <div class="rtabcont1">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="PePdModel.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;"> 
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "qRCode");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("md_name_like", "${fn:escapeXml(af.map.md_name_like)}");
				pager.addHiddenInputs("cls_id", "${af.map.cls_id}");
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
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });	
	
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	
	$('.cls_name').click(function(){
		$("#cls_id option[value='"+ this.id + "']").attr("selected", true);
		document.forms[1].submit();
	});
	
	$('.jdxx_pd').click(function(){
		$("#is_jdxx_pd").val("1");
		document.forms[1].submit();
	});
});

//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
