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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.but_import {
	width:95px;
	height:20px;
	background:url(${ctx}/images/manager/but_l.gif) no-repeat;
	font:normal 12px/20px "宋体";
	text-align:left;
	color:#fff;
	padding-left:27px;
	border:1px #ccc solid;
	border-left:0;
	cursor:pointer;
}
.but_import1 {
	width:145px;
	height:20px;
	background:url(${ctx}/images/manager/but_l.gif) no-repeat;
	font:normal 12px/20px "宋体";
	text-align:left;
	color:#fff;
	padding-left:27px;
	border:1px #ccc solid;
	border-left:0;
	cursor:pointer;
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
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxLogisticsSearch">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">分公司名称：</strong>
            <c:if test="${empty dept_name}">
              <html-el:select property="dept_id" styleId="dept_id" onchange="this.form.submit();" style="width:154px;">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach var="cur" items="${konkaDepts}">
                  <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                </c:forEach>
              </html-el:select>
            </c:if>
            <c:if test="${not empty dept_name}">
              <label style="color:blue;">${dept_name}</label>
            </c:if></td>
          <td><strong class="fb">收货地区：</strong>
            <html-el:select property="province" styleId="province" style="width:150px;">
              <option value="">-请选择省/直辖市/自治区-</option>
            </html-el:select>
            <html-el:select property="city" styleId="city" style="width:100px;" onchange="this.form.submit();">
              <option value="">-请选择市-</option>
            </html-el:select>
            <html-el:select property="country" styleId="country" style="width:100px;" onchange="this.form.submit();">
              <option value="">-请选择县-</option>
            </html-el:select>
            &nbsp;<strong class="fb">产品型号：</strong>
            <html-el:select property="md_name" styleId="md_name" style="width:150px;" onchange="this.form.submit();">
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${konkaXxStdPdList}" var="cur" varStatus="vs">
                <html-el:option value="${cur.md_name}">${cur.md_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="t_1">
      <tr style="display:none;" id="t_2">
        <td colspan="13" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">康佳专卖店物流模板数据表</td>
      </tr>
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="15%">分公司名称</td>
        <td nowrap="nowrap" align="center" width="25%">发货地区</td>
        <td nowrap="nowrap" align="center" width="25%">收货地区</td>
        <td nowrap="nowrap"  align="center" >产品型号</td>
        <td nowrap="nowrap"  align="center" width="15%">物流费用</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left">${fn:escapeXml(cur.map.p_index_name_s)}
              <c:if test="${empty cur.map.p_index_name_s}"><span style="color:gray;">未填写</span></c:if></td>
            <td align="left">${fn:escapeXml(cur.map.p_index_name_e)}
              <c:if test="${empty cur.map.p_index_name_e}"><span style="color:gray;">未填写</span></c:if></td>
            <td  align="left">${fn:escapeXml(cur.md_name)}</td>
            <td align="right"><span class="kz-price-12">
              <fmt:formatNumber type="currency" value="${cur.fee}" />
              </span></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxLogisticsSearch.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("province", "${af.map.province}");
				pager.addHiddenInputs("city", "${af.map.city}");
				pager.addHiddenInputs("country", "${af.map.country}");
				pager.addHiddenInputs("md_name_like", "${af.map.md_name_like}");
				pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
				pager.addHiddenInputs("md_name", "${af.map.md_name}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <!-- 弹出物流数据导入窗口 START -->
  <div id="div_import" style="display: none;" class="rtabcont1">
    <form action="LogisticsTemplateImport.do" method="post" enctype="multipart/form-data" id="excel_form">
      <input type="hidden" name="method" value="importData"  />
      <input type="hidden" name="mod_id" value="${af.map.mod_id}"  />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <input type="hidden" name="queryString" id="queryString" value="" />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable2" style="margin-top:5px;">
        <tr>
          <td width="15%" class="title_item">导入Excel：</td>
          <td><input type="file" name="ups" id="ups" style="width:400px;font-size:16px;" /></td>
        </tr>
      </table>
    </form>
  </div>
  <!-- 弹出物流数据导入窗口 END. -->
  <!-- Ajax 提交 覆盖层  -->
  <div style="display:none;top:20%;left:20%;background:#fff;font-size:12px;z-index:999999;" id="ajax_view">
    <table border="0" cellspacing="0" cellpadding="0" >
      <tr>
        <td><img src="${ctx}/images/ajax-loader.gif" /></td>
      </tr>
    </table>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lightBox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}

	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}","dataType":"Require","msg":"请选择省/直辖市/自治区！"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country").attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);
	$("#province").change(function(){
		this.form.submit();
	});

	var ajax_view = new LightBox("ajax_view");
	ajax_view.Over = true;  //是否启用覆盖层  :true 、 false
	ajax_view.OverLay.Color = "#000"; //覆盖层颜色 ，必须启用覆盖层才有作用
	ajax_view.OverLay.Opacity = 5; //覆盖层透明度 
	ajax_view.Fixed = true; // 是否定位
	ajax_view.Center = true; //是否居中

	$("#import").click(function(){
		$("#div_import").dialog({
		      title: '物流模板导入', 
		      width: 750,
		      height: 170,
		      draggable: true, //是否可以使用标题头进行拖动
		      resizable: false, //是否可以改变dialog的大小
		      position:'center', //dialog的显示位置
		      modal : true, //是否使用模式窗口，模式窗口打开后，页面其他元素将不能点击，直到关闭模式窗口
		      buttons: { "开始导入": import_form_submit , "关闭": function(){$(this).dialog("close");}} 
		}).dialog("open");	
	});

	// 开始导入处理
	function import_form_submit(){
		var ups = $("#ups").val();
		if($.trim(ups).length == 0){
			alert("请选择需要导入的文件！");
			return false;
		}
		$("#div_import").dialog("close");
		ajax_view.Show(); // 启动覆盖层
		
		$("#queryString").val($('#bottomPageForm').serialize());
		$("#excel_form").submit();
	}
});	
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
