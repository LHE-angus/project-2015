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
    <html-el:form action="/zmd/KonkaXxStdPd">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15" align="center"></td>
          <td align="center"><strong class="fb">产品型号名称：</strong>
            <html-el:text property="md_name_like" styleId="md_name_like" size="20" maxlength="30"></html-el:text>
            <a id="clearText" href="javascript:void(0);" onclick="$(this).prev().val('').focus();$('#af').submit();" onmousemove="$(this).css('color', '#FF0000');" onmouseout="$(this).css('color', '#6699ff');" style="color:#6699ff;margin-left:5px;text-decoration:underline;">清空</a> &nbsp;&nbsp;&nbsp; <strong class="fb">产品种类：</strong>
            <html-el:select property="md_type" onchange="this.form.submit();">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="0">主销</html-el:option>
              <html-el:option value="1">停产（清理）</html-el:option>
              <html-el:option value="2">退市</html-el:option>
            </html-el:select>
            &nbsp;&nbsp;&nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxStdPd.do?method=add&mod_id=${af.map.mod_id}';" />
          <input class="but_excel" type="button" id="import" name="import" value="导入" /></td>
        <td><input class="but8" type="button" id="download" name="download" value="下载模板" onclick="location.href='KonkaXxStdPdImport.do?method=download&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" /></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center">产品型号名称</td>
        <td nowrap="nowrap" align="center" width="12%">产品大类</td>
        <td nowrap="nowrap" align="center" width="10%">产品规格</td>
        <td nowrap="nowrap" align="center" width="10%">产品种类</td>
        <td nowrap="nowrap" align="center" width="10%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr id="tr_${cur.md_type}">
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${cur.md_name}</td>
            <td align="left">${cur.map.pd_name}</td>
            <td align="center">${cur.spec}</td>
            <td align="center"><c:set var="md_types" value="${fn:split('主销,停产（清理）,退市', ',')}" />
              ${md_types[cur.md_type]}</td>
            <td><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxStdPd.do', 'edit','md_name=${cur.md_name}&'+$('#bottomPageForm').serialize())">修改</span> | <span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxStdPd.do', 'editProperty','md_name=${cur.md_name}&'+$('#bottomPageForm').serialize())">属性</span></td>
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
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxStdPd.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("md_name_like", "${af.map.md_name_like}");
				pager.addHiddenInputs("md_type", "${af.map.md_type}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
  </div>
  <!-- 弹出物流数据导入窗口 START -->
  <div id="div_import" style="display: none;" class="rtabcont1">
    <form action="KonkaXxStdPdImport.do" method="post" enctype="multipart/form-data" id="excel_form">
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lightBox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}

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

	$("tr[id^=tr_]").each(function(){
		var type = $(this).attr("id").replace("tr_", "");
		if ("0" == type) {
			$("td", this).css({'color':'#F00','font-weight':'700'});
		} else if ("1" == type) {
			$("td", this).css('background-color', '#F3F781');
		} else if ("2" == type) {
			$("td", this).css('background-color', '#eee');
		}
	});
});
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
