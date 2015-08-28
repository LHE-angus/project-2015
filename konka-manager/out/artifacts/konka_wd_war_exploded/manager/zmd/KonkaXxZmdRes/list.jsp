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
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/zmd/KonkaXxZmdRes">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15">&nbsp;</td>
          <td><strong class="fb">审批通过时间：</strong>
            <html-el:text property="audit_date_start" styleId="audit_date_start" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
            &nbsp;至&nbsp;
            <html-el:text property="audit_date_end" styleId="audit_date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          <c:if test="${role_id_btw_200_300_ge_30}">
            <td><strong class="fb">所属分公司：</strong>
              <html-el:select property="dept_id">
                <html-el:option value="">==请选择==</html-el:option>
                <c:forEach items="${konkaDeptList}" var="cur">
                  <c:if test="${af.map.dept_id eq cur.dept_id}">
                    <option value="${cur.dept_id}" selected="selected">${cur.dept_name}</option>
                  </c:if>
                  <c:if test="${af.map.dept_id ne cur.dept_id}">
                    <option value="${cur.dept_id}">${cur.dept_name}</option>
                  </c:if>
                </c:forEach>
              </html-el:select></td>
          </c:if>
          <td><input class="but1" type="submit" name="Submit" value="搜索" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      <tr>
        <td colspan="13" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">康佳专卖店资源管理数据表</td>
      </tr>
    </table>
    <div style="overflow-x:auto;padding-bottom:5px;border-left:1px solid #ccc;border-right:1px solid #ccc;" id="divExcel" title="康佳专卖店资源管理数据表${af.map.now_date}">
      <table border="0" cellspacing="0" cellpadding="0" class="rtable2" id="t_1" style="border-left:none;border-right:none;">
        <tr style="display:none;" id="t_2">
          <td colspan="13" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">康佳专卖店资源管理数据表</td>
        </tr>
        <tr class="tabtt1">
          <td width="120" align="center" nowrap="nowrap"><font class="blue">分公司</font></td>
          <td width="150" align="center" nowrap="nowrap"><font class="blue">专卖店编号</font></td>
          <td width="150" align="center" nowrap="nowrap"><font class="blue">R3编码</font></td>
          <td width="150" align="center" nowrap="nowrap"><font class="blue">R3送达方编码</font></td>
          <td width="240" align="center" nowrap="nowrap"><font class="blue">地址</font></td>
          <td width="100" align="center" nowrap="nowrap"><font class="blue">面积（㎡）</font></td>
          <td width="150" align="center" nowrap="nowrap"><font class="blue">租期</font></td>
          <td width="120" align="center" nowrap="nowrap"><font class="blue">经营性质</font></td>
          <td width="120" align="center" nowrap="nowrap"><font class="blue">年租金（万元）</font></td>
          <td width="150" align="center" nowrap="nowrap"><font class="blue">投入装修费用（元）</font></td>
          <td width="300" align="center" nowrap="nowrap"><font class="blue">固定资产配置</font></td>
          <td width="150" align="center" nowrap="nowrap"><font class="blue">预计年销售额（万元）</font></td>
          <td width="100" align="center" nowrap="nowrap"><font class="blue">专卖店负责人</font></td>
          <td width="150" align="center" nowrap="nowrap"><font class="blue">联系电话</font></td>
          <td width="150" align="center" nowrap="nowrap"><font class="blue">专卖店押金（万元）</font></td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="left">${cur.map.dept_name}</td>
            <td align="left">${cur.zmd_sn}</td>
            <td align="left">${cur.r3_id}</td>
            <td align="left">${cur.r3_send_num}</td>
            <td align="left" title="${cur.addr}" nowrap="nowrap" class="td_addr">${fnx:abbreviate(cur.addr, 2 * 20, '...')}</td>
            <td align="right" style="mso-number-format:'\@';"><fmt:formatNumber value="${cur.busi_area}" pattern="#,##0.00#" /></td>
            <td align="left" nowrap="nowrap"><c:if test="${empty cur.rent_start and empty cur.rent_end}"> <span style="color:#999;">没有填写</span> </c:if>
              <c:if test="${(not empty cur.rent_start) or (not empty cur.rent_end)}">
                <fmt:formatDate value="${cur.rent_start}" pattern="yyyy年MM月" />
                至
                <fmt:formatDate value="${cur.rent_end}" pattern="yyyy年MM月" />
              </c:if></td>
            <td align="left">${cur.map.type_name}
              <c:if test="${empty cur.map.type_name}"><span style="color:#999;">未填写</span></c:if></td>
            <td align="right" style="mso-number-format:'\@';"><span style="color:red;">
              <fmt:formatNumber value="${cur.rent_fee}" pattern="#,##0.0000#" />
              </span></td>
            <td align="right" style="mso-number-format:'\@';"><span style="color:red;">
              <fmt:formatNumber value="${cur.money_of_dcrt}" pattern="#,##0.00#" />
              </span></td>
            <td align="left">${cur.fixed_asset}
              <c:if test="${empty cur.fixed_asset}"><span style="color:#999;">未填写</span></c:if></td>
            <td align="right" style="mso-number-format:'\@';"><span style="color:red;">
              <fmt:formatNumber value="${cur.money_of_sell_by_year_plan}" pattern="#,##0.0000#" />
              </span></td>
            <td align="left">${cur.host_name}
              <c:if test="${empty cur.host_name}"><span style="color:#999;">未填写</span></c:if></td>
            <td align="left">${cur.host_phone}
              <c:if test="${empty cur.host_phone}"><span style="color:#999;">未填写</span></c:if></td>
            <td align="right" style="mso-number-format:'\@';"><span style="color:red;">
              <fmt:formatNumber value="${cur.money_of_deposit}" pattern="#,##0.0000#" />
              </span></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
        <tr>
          <td align="center" nowrap="nowrap"><font class="blue">合计</font></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${af.map.busi_area}" pattern="#,##0.00#" /></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><span style="color:red;">
            <fmt:formatNumber value="${af.map.rent_fee}" pattern="#,##0.0000#" />
            </span></td>
          <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><span style="color:red;">
            <fmt:formatNumber value="${af.map.money_of_dcrt}" pattern="#,##0.00#" />
            </span></td>
          <td>&nbsp;</td>
          <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><span style="color:red;">
            <fmt:formatNumber value="${af.map.money_of_sell_by_year_plan}" pattern="#,##0.0000#" />
            </span></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><span style="color:red;">
            <fmt:formatNumber value="${af.map.money_of_deposit}" pattern="#,##0.0000#" />
            </span></td>
        </tr>
      </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdRes.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><input class="but_excel" type="button" name="import_excel_btn" value="导出" id="import_excel_btn" /></td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("audit_date_start", "${af.map.audit_date_start}");
	            pager.addHiddenInputs("audit_date_end", "${af.map.audit_date_end}");
	            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}这里所显示的专卖店信息为全部总部审核通过的!</div>
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
<c:if test="${not empty allExcelList}">
  <div style="100%;overflow-x:auto;padding-bottom:5px;display:none;" id="divExcel_all" title="康佳专卖店资源管理数据表${af.map.now_date}">
    <table width="100%" border="1" cellspacing="0" cellpadding="0" class="rtable2">
      <tr>
        <td colspan="13" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">康佳专卖店资源管理数据表</td>
      </tr>
      <tr class="tabtt1">
        <td width="120" align="center" nowrap="nowrap"><font class="blue">分公司</font></td>
        <td width="150" align="center" nowrap="nowrap"><font class="blue">专卖店编码</font></td>
        <td width="150" align="center" nowrap="nowrap"><font class="blue">R3编码</font></td>
        <td width="150" align="center" nowrap="nowrap"><font class="blue">R3送达方编码</font></td>
        <td width="240" align="center" nowrap="nowrap"><font class="blue">地址</font></td>
        <td width="100" align="center" nowrap="nowrap"><font class="blue">面积（㎡）</font></td>
        <td width="150" align="center" nowrap="nowrap"><font class="blue">租期</font></td>
        <td width="120" align="center" nowrap="nowrap"><font class="blue">经营性质</font></td>
        <td width="120" align="center" nowrap="nowrap"><font class="blue">年租金（万元）</font></td>
        <td width="150" align="center" nowrap="nowrap"><font class="blue">投入装修费用（元）</font></td>
        <td width="300" align="center" nowrap="nowrap"><font class="blue">固定资产配置</font></td>
        <td width="150" align="center" nowrap="nowrap"><font class="blue">预计年销售额（万元）</font></td>
        <td width="100" align="center" nowrap="nowrap"><font class="blue">专卖店负责人</font></td>
        <td width="150" align="center" nowrap="nowrap"><font class="blue">联系电话</font></td>
        <td width="150" align="center" nowrap="nowrap"><font class="blue">专卖店押金（万元）</font></td>
      </tr>
      <c:forEach var="cur" items="${allExcelList}" varStatus="vs">
        <tr>
          <td align="left">${cur.map.dept_name}</td>
          <td align="left">${cur.zmd_sn}</td>
          <td align="left">${cur.zmd_sn}</td>
          <td align="left">${cur.r3_id}</td>
          <td align="left" nowrap="nowrap">${cur.addr}</td>
          <td align="right" style="mso-number-format:'\@';"><fmt:formatNumber value="${cur.busi_area}" pattern="#,##0.00#" /></td>
          <td align="left"><c:if test="${empty cur.rent_start and empty cur.rent_end}"> <span style="color:#999;">没有填写</span> </c:if>
            <c:if test="${(not empty cur.rent_start) or (not empty cur.rent_end)}">
              <fmt:formatDate value="${cur.rent_start}" pattern="yyyy年MM月" />
              -
              <fmt:formatDate value="${cur.rent_end}" pattern="yyyy年MM月" />
            </c:if></td>
          <td align="left">${cur.map.type_name}</td>
          <td align="right" style="mso-number-format:'\@';"><fmt:formatNumber value="${cur.rent_fee}" pattern="#,##0.0000#" /></td>
          <td align="right" style="mso-number-format:'\@';"><fmt:formatNumber value="${cur.money_of_dcrt}" pattern="#,##0.00#" /></td>
          <td align="left">${empty cur.fixed_asset ? '<span style="color:#999;">没有填写</span>' : cur.fixed_asset}</td>
          <td align="right" style="mso-number-format:'\@';"><fmt:formatNumber value="${cur.money_of_sell_by_year_plan}" pattern="#,##0.0000#" /></td>
          <td align="left">${cur.dept_leader_name}</td>
          <td align="left">${cur.dept_leader_phone}</td>
          <td align="right" style="mso-number-format:'\@';"><fmt:formatNumber value="${cur.money_of_deposit}" pattern="#,##0.0000#" /></td>
        </tr>
      </c:forEach>
      <tr>
        <td align="center" nowrap="nowrap"><font class="blue">合计</font></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${af.map.excel_busi_area}" pattern="#,##0.00#" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${af.map.excel_rent_fee}" pattern="#,##0.0000#" /></td>
        <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${af.map.excel_money_of_dcrt}" pattern="#,##0.00#" /></td>
        <td>&nbsp;</td>
        <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${af.map.excel_money_of_sell_by_year_plan}" pattern="#,##0.0000#" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${af.map.excel_money_of_deposit}" pattern="#,##0.0000#" /></td>
      </tr>
    </table>
  </div>
</c:if>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lightBox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期控件
	$("#audit_date_start").datepicker();
	$("#audit_date_end").datepicker();

	
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
		toExcel('divExcel', '${ctx}/manager/zmd/KonkaXxZmdRes.do?method=toExcel');
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
		toExcel('divExcel_all', '${ctx}/manager/zmd/KonkaXxZmdRes.do?method=toExcel');
	}
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>