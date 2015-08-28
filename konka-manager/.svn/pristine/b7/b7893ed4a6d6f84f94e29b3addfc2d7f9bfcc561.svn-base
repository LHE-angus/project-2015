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
	.but_import {width:95px;height:20px;background:url(${ctx}/images/manager/but_l.gif) no-repeat;font:normal 12px/20px "宋体";text-align:left;color:#fff;padding-left:27px;border:1px #ccc solid;border-left:0;cursor:pointer;}
	.but_import1 {width:145px;height:20px;background:url(${ctx}/images/manager/but_l.gif) no-repeat;font:normal 12px/20px "宋体";text-align:left;color:#fff;padding-left:27px;border:1px #ccc solid;border-left:0;cursor:pointer;}
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
    <html-el:form action="/zmd/KonkaXxPd">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td><strong class="fb">产品型号：</strong>
          	<html-el:text property="md_name_like" styleId="md_name_like" maxlength="20" />
          	&nbsp;<strong class="fb">审核状态：</strong>
            <html-el:select property="audit_state" styleId="audit_state" onchange="this.form.submit();" style="width:160px;">
              <html-el:option value="">==请选择==</html-el:option>
              <html-el:option value="0">==未审核==</html-el:option>
              <html-el:option value="1">==审核通过==</html-el:option>
              <html-el:option value="2">==审核不通过==</html-el:option>
            </html-el:select>
            &nbsp; <strong class="fb">最低价格：</strong>
            <html-el:text property="price_min_ge" styleId="price_min_ge" maxlength="9" size="4" />
            -
            <html-el:text property="price_min_le" styleId="price_min_le" maxlength="9" size="4" />
            &nbsp;
            <input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input class="but2" type="submit" name="Submit2" value="新增" onclick="location.href='KonkaXxPd.do?method=add&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" />
        &nbsp;
	    <input class="but_excel" type="button" name="Submit3" value="导入" onclick="location.href='KonkaXxPd.do?method=add_excel&mod_id=${af.map.mod_id}&tree_param=${tree_param}';" />
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <div style="overflow-x: auto">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="t_1"> 
     <tr style="display:none;" id="t_2">
        <td colspan="13" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">康佳专卖店资源管理数据表</td>
     </tr>
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="5%">序号</td>
        <td nowrap="nowrap" align="center" width="8%">分公司</td>
        <td nowrap="nowrap"  align="center" width="8%">产品型号</td>
        <td nowrap="nowrap" align="center">工厂仓位</td>
        <td nowrap="nowrap"  align="center" width="8%">参考价格</td>
        <td nowrap="nowrap"  align="center" width="8%">价格下限</td>
        <td nowrap="nowrap"  align="center" width="8%">上架时间</td>
        <td nowrap="nowrap"  align="center" width="8%">下架时间</td>
        <td nowrap="nowrap"  align="center" width="6%">状态</td>
        <td nowrap="nowrap" align="center" width="8%">操作</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.map.dept_name)}</td>
            <td align="left">${fn:escapeXml(cur.md_name)}</td>
            <td align="left">${fn:escapeXml(cur.map.fac_store_name)}</td>
            <td align="right"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.price_ref}" /></span></td>
            <td align="right"><span class="kz-price-12"><fmt:formatNumber type="currency" value="${cur.price_min}" /></span></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.up_time}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.down_time}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">
            <c:if test="${cur.audit_state == 0}">未审核</c:if>
            <c:if test="${cur.audit_state == 1}">审核通过</c:if>
            <c:if test="${cur.audit_state == 2}">审核不通过</c:if></td>
            <td align="center" nowrap="nowrap"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPd.do', 'view','dept_pd_id=${cur.dept_pd_id}&dept_id=${cur.dept_id}&'+$('#bottomPageForm').serialize())">查看</span>|
            <c:if test="${cur.audit_state eq 1 or cur.audit_state eq 2}"><span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPd.do', 'edit','dept_pd_id=${cur.dept_pd_id}&'+$('#bottomPageForm').serialize())">修改</span></c:if>
            <c:if test="${cur.audit_state eq 0}"><span style="color:gray">修改</span></c:if>|<span style="cursor: pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaXxPd.do', 'historyList','dept_pd_id=${cur.dept_pd_id}&md_name=${cur.md_name}&'+$('#bottomPageForm').serialize())">历史</span></td>
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxPd.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><input class="but_excel" type="button" name="import_excel_btn" value="导出" id="import_excel_btn" /></td>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("tree_param", "${tree_param}");
				pager.addHiddenInputs("audit_state", "${af.map.audit_state}");
				pager.addHiddenInputs("cls_id", "${af.map.cls_id}");
				pager.addHiddenInputs("price_min_ge", "${af.map.price_min_ge}");
				pager.addHiddenInputs("price_min_le", "${af.map.price_min_le}");
				pager.addHiddenInputs("md_name_like", "${af.map.md_name_like}");
				document.write(pager.toString());
			  </script>
            </div></td>
        </tr>
      </table>
    </form>
    <div style="overflow-x: auto">
  </div>
  <div class="clear"></div>
</div>
</div>
<div style="display:none;background-color:white;" id="import_body">
 <table border="0" cellpadding="0" cellspacing="0" width="260">
  <tr>
    <td colspan="2" align="right" height="22" valign="middle"><img src="${ctx}/images/ajax-denied.gif" width="16" height="16" style="cursor:pointer;margin-right:8px;" id="import_body_colse" /></td>
  </tr>
  <tr>
  	<td align="center" height="50"><input class="but_import" type="button" name="import_excel_btn" value="导出当前页" id="import_excel_show_btn" /></td>
  	<td align="center"><input class="but_import1" type="button" name="import_excel_btn" value="导出全部 ${af.map.pager.recordCount} 条结果" id="import_excel_all_btn" /></td>
  </tr>
 </table>
</div>
<div class="pageContent" id="divExcel_all" title="商品报表" style="display: none;">
  <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
    <tr class="tabtt1">
      <td nowrap="nowrap" align="center" width="5%">序号</td>
      <td nowrap="nowrap" align="center" width="8%">分公司</td>
      <td nowrap="nowrap"  align="center" width="8%">产品型号</td>
      <td nowrap="nowrap" align="center" width="8%">工厂仓位</td>
      <td nowrap="nowrap"  align="center" width="8%">参考价格（元）</td>
      <td nowrap="nowrap"  align="center" width="8%">价格上限（元）</td>
      <td nowrap="nowrap"  align="center" width="8%">价格下限（元）</td>
      <td nowrap="nowrap"  align="center"  width="7%">安装费用（元）</td>
      <td nowrap="nowrap"  align="center" width="7%">上架时间</td>
      <td nowrap="nowrap"  align="center" width="7%">下架时间</td>
      <td nowrap="nowrap"  align="center" width="7%">备注</td>
      <td nowrap="nowrap"  align="center" width="7%">添加时间</td>
    </tr>
    <tbody>
      <c:forEach var="cur" items="${allExcelList}" varStatus="vs">
        <tr>
          <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
          <td align="left">${fn:escapeXml(cur.map.dept_name)}</td>
          <td  align="left">${cur.md_name}</td>
          <td align="left">${fn:escapeXml(cur.map.fac_store_name)}</td>
          <td align="right"><fmt:formatNumber value="${cur.price_ref}" pattern="0.00" /></td>
          <td align="right"><fmt:formatNumber value="${cur.price_max}" pattern="0.00" /></td>
          <td align="right"><fmt:formatNumber value="${cur.price_min}" pattern="0.00" /></td>
          <td align="right"><fmt:formatNumber value="${cur.fix_fee}" pattern="0.00" /></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.up_time}" pattern="yyyy-MM-dd" /></td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.down_time}" pattern="yyyy-MM-dd" /></td>
          <td align="center" nowrap="nowrap">${fn:escapeXml(cur.remarks)}</td>
          <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/scripts/lightBox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#price_min_ge").attr("focus",setOnlyNum);
	$("#price_min_le").attr("focus",setOnlyNum);
	
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}

	//覆盖层
	var import_body = new LightBox("import_body");
	import_body.Over = true;  //是否启用覆盖层  :true 、 false;
	import_body.OverLay.Color = "#000"; //覆盖层颜色 ，必须启用覆盖层才有作用
	import_body.OverLay.Opacity = 10; //覆盖层透明度 
	import_body.Fixed = true; // 是否定位
	import_body.Center = true; //是否居中

	$("#import_excel_btn").click(function (){
		import_body.Show();
	});
	$("#import_body_colse").click(function (){
		import_body.Close();
	});
	$("#import_excel_show_btn").click(function (){
		document.getElementById("t_1").border = 1;
		document.getElementById("t_2").style.display = "";
		$(".td_addr").each(function (){
			$(this).text(this.title);
		});
		toExcel('divExcel_all', '${ctx}/manager/zmd/KonkaXxPd.do?method=toExcel');
		document.getElementById("t_1").border = 0;
		document.getElementById("t_2").style.display = "none";
		import_body.Close();
	});

	$("#import_excel_all_btn").click(function (){
		$("#bottomPageForm").append("<input type='hidden' name='allToExcel' value='ebiz' />");
		$("#bottomPageForm").submit();
	});

	// 返回页面根据需求导出全部数据
	var allToExcel = "${af.map.allToExcel}";
	if("ebiz" == allToExcel){
		toExcel('divExcel_all', '${ctx}/manager/zmd/KonkaXxPd.do?method=toExcel');
	}
});

function setOnlyNum() {
	$(this).css("ime-mode", "disabled").attr("t_value", "").attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "";
	});
	//this.text.selected;
}
//]]>
</script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</div>
</body>
</html>
