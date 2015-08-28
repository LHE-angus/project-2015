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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaZles23ResultInfo">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="current_fgs_code" styleId="current_fgs_code" value="${current_fgs_code}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td>
			<strong class="fb">分公司：</strong>
	       	<html-el:select property="dept_id" styleId="dept_id" style="width:153px" >
              <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
          </td>
          <td>
          	<strong class="fb">客户R3编码：</strong>
          	<html-el:text property="customer_code_like" size="20" maxlength="20" styleId="customer_code_like" />
          </td>
          <td>
          	<strong class="fb">机型：</strong>
          	<html-el:text property="matnr_like" size="20" maxlength="20" styleId="matnr_like" />
          </td>
       </tr>
       <tr>
       <td>
          	<strong class="fb">收货仓库：</strong>
          	<html-el:text property="lgort_like" size="20" maxlength="20" styleId="lgort_like" />
          </td>
          
          
       	  <td>
          	<strong class="fb">凭证单号：</strong>
          	<html-el:text property="ebeln" size="20" maxlength="20" styleId="ebeln" />
          </td>
          
        
          
          <td>
          	<strong class="fb">交货单号：</strong>
          	<html-el:text property="vbeln" size="20" maxlength="20" styleId="vbeln" />
          </td>
          
        </tr>
        
        <tr>
        
            <td ><strong class="fb">凭证时间：</strong>
	          <html-el:text property="bedat_s" styleId="bedat_s"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
	          <html-el:text property="bedat_e" styleId="bedat_e"  size="20" maxlength="10" readonly="true" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
          </td>
          
          
          <td>
          	<html-el:submit styleClass="but1" value="搜索" />
           </td>
           
           <td> <input type="button" value="Excel" id="export_excel" class="but_excel" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  	<div style="overflow-x:auto;">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td nowrap="nowrap" align="center" >分公司</td>
          <td nowrap="nowrap" align="center" >凭证单号</td>
          <td nowrap="nowrap" align="center" >项目号</td>
          <td nowrap="nowrap" align="center" >产品型号</td>
          <td nowrap="nowrap" align="center" >STO订单数量(S)</td>
          <td nowrap="nowrap" align="center" >STO发货数(S)</td>
          <td nowrap="nowrap" align="center" >STO收货数(S)</td>
          <td nowrap="nowrap" align="center" >交货数量(S)</td>
          <td nowrap="nowrap" align="center" >发货仓位库存</td>
          <td nowrap="nowrap" align="center" >收货仓位库存</td>
          <td nowrap="nowrap" align="center" >发货仓位</td>
          <td nowrap="nowrap" align="center" >收货仓位</td>
          <td nowrap="nowrap" align="center" >客户编码</td>
          <td nowrap="nowrap" align="center" >请求号码</td>
          <td nowrap="nowrap" align="center" >交货单号</td>
          <td nowrap="nowrap" align="center" >交货单创建日期</td>
          <td nowrap="nowrap" align="center" >交货单项目号</td>
          <td nowrap="nowrap" align="center" >交货订单数量</td>
          <td nowrap="nowrap" align="center" >总部发货数量</td>
          <td nowrap="nowrap" align="center" >发货日期</td>
          <td nowrap="nowrap" align="center" >分公司已收数量</td>
          <td nowrap="nowrap" align="center" >收货日期</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
          	<td align="center" nowrap="nowrap"> ${vs.count}</td>
            <td align="left" nowrap="nowrap">${cur.map.dept_name}</td>
            <td align="left" nowrap="nowrap">${cur.ebeln}</td>
	        <td align="right" nowrap="nowrap">${cur.ebelp}</td> 
            <td align="left" nowrap="nowrap">${cur.matnr}</td>
            <td align="right" nowrap="nowrap">${cur.menge}</td>
            <td align="right" nowrap="nowrap">${cur.wamng}</td>
            <td align="right" nowrap="nowrap">${cur.wemng}</td>
            <td align="right" nowrap="nowrap">${cur.lfimg}</td>
            <td align="right" nowrap="nowrap">${cur.labst}</td>
            <td align="right" nowrap="nowrap">${cur.labst1}</td>
            <td align="right" nowrap="nowrap">${cur.reslo}</td>
            <td align="right" nowrap="nowrap">${cur.lgort}</td>
            <td align="left" nowrap="nowrap">${cur.kunnr}</td>
            <td align="left" nowrap="nowrap">${cur.bednr}</td>
            <td align="left" nowrap="nowrap">${cur.vbeln}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.erdat}" pattern="yyyy-MM-dd"/></td>
            <td align="right" nowrap="nowrap">${cur.posnr}</td>
            <td align="right" nowrap="nowrap">${cur.lfimg1}</td>
            <td align="right" nowrap="nowrap">${cur.menge1}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.budat1}" pattern="yyyy-MM-dd"/></td>
            <td align="right" nowrap="nowrap">${cur.menge2}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.budat2}" pattern="yyyy-MM-dd"/></td>
           
          </tr>
          <c:if test="${vs.last eq true}">
            <c:set var="i" value="${vs.count}" />
          </c:if>
        </c:forEach>
        <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
            <tr align="center">
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
      </table>
      </div>
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaZles23ResultInfo.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
               <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
					pager.addHiddenInputs("customer_code_like", "${fn:escapeXml(af.map.customer_code_like)}");
					pager.addHiddenInputs("matnr_like", "${fn:escapeXml(af.map.matnr_like)}");
					pager.addHiddenInputs("lgort_like", "${fn:escapeXml(af.map.lgort_like)}");
					pager.addHiddenInputs("current_fgs_code", "${af.map.current_fgs_code}");
					pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
					pager.addHiddenInputs("ebeln", "${af.map.ebeln}");
					pager.addHiddenInputs("vbeln", "${af.map.vbeln}");
					pager.addHiddenInputs("bedat_s", "${af.map.bedat_s}");
					pager.addHiddenInputs("bedat_e", "${af.map.bedat_e}");
		            document.write(pager.toString());
            	</script>
           </td>
          </tr>
        </table>
      </form>
    </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//分公司列表初始化
		$("#dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
		$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
		$("#dept_id").change();
		
		//默认当前分公司
		var fgs = $("#current_fgs_code").val();
		if(fgs!=""){
			$("#dept_id").val(fgs);
			$("#dept_id").change();
		}
		 // 导出excel
	    $("#export_excel").click(function(){
	    	if(confirm("提示，您确认导出数据？")){
	    		loading();
	    		//CNZZ统计代码
				//_czc.push(["_trackEvent", "集采订单查询下载", "导出", "Excel", 0, "export_excel"]);
	    		$('#af').append("<input type='hidden' id='excel_all' name='excel_all' value='1' />");
	    		$('#af').submit();
	    		$("#excel_all").remove();//导出后再删除ID 
	    		
	    	}
	    });
	});
	
	function loading(){
		jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:true ,TimeShown:5000, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
	}
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>