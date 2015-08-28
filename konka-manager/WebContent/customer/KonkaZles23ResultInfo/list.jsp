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
<style type="text/css">
input.but1 {
  background: url("../../images/manager/but_export1.jpg") no-repeat;
  height: 30px;
  width: 60px;
}
</style>
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
    <html-el:form action="/manager/KonkaZles23ResultInfo.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td style="text-align: right;"><strong class="fb" >客户名称：</strong></td>
          <td style="text-align: left;"><html-el:text property="customer_name_like" style="width:150px;" maxlength="40" styleId="customer_name_like" /></td>
          <td style="text-align: right;"><strong class="fb">R3客户编码：</strong></td>
          <td style="text-align: left;" colspan="2"><html-el:text property="r3_code_like" style="width:90px;" maxlength="20" styleId="r3_code_like" /></td>
          <td style="text-align: right;"><strong class="fb">型号：</strong></td>
          <td style="text-align: left;"><html-el:text property="matnr_like" style="width:90px;" maxlength="20" styleId="matnr_like" /></td>
          <td style="text-align: right;"><strong class="fb">收货仓库：</strong></td>
          <td style="text-align: left;"><html-el:text property="lgort_like" style="width:90px;" maxlength="20" styleId="lgort_like" /></td>
          <td style="text-align: right;"><strong class="fb">发机单号：</strong></td>
          <td style="text-align: left;"><html-el:text property="vbeln_like" style="width:90px;" maxlength="20" styleId="vbeln_like" /></td>
        </tr>
        <tr>
         
          	 <td style="text-align: right;"><strong class="fb" >发货仓位：</strong> </td>
          	 <td style="text-align: left;"><html-el:text property="reslo_like" style="width:90px;" maxlength="20" styleId="reslo_like" /> </td>
          	 <td style="text-align: right;"><strong class="fb">发货日期：</strong> </td>
	          <td colspan="2" style="text-align: left;"><input name="budat1_s" id="budat1_s" size="12" value="${af.map.budat1_s}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'budat1_e\')}'})" />
	            	    至
	         <input name="budat1_e" id="budat1_e" size="12"  value="${af.map.budat1_e}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'budat1_s\')||\'2013-11-01\'}'})" /> </td>
	         <td style="text-align: right;"><strong class="fb">收货日期：</strong> </td>
	          <td colspan="2" style="text-align: left;"><input name="budat2_s" id="budat2_s" size="12" value="${af.map.budat2_s}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'budat2_e\')}'})" />
	            	    至
	          <input name="budat2_e" id="budat2_e" size="12"  value="${af.map.budat2_e}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'budat2_s\')||\'2013-11-01\'}'})" /> </td>
             <td colspan="3" style="text-align: center;"><html-el:submit styleClass="but1" value=" " /> </td>
           
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
          <td nowrap="nowrap" align="center" >客户名称</td>
          <td nowrap="nowrap" align="center" >客户R3编码</td>
          <td nowrap="nowrap" align="center" >机型</td>
          <td nowrap="nowrap" align="center" >发机单号</td>
          <td nowrap="nowrap" align="center" >订单数量</td>
          <td nowrap="nowrap" align="center" >总部发货数量</td>
          <td nowrap="nowrap" align="center" >价格</td>
          <td nowrap="nowrap" align="center" >交货单创建日期</td>
          <td nowrap="nowrap" align="center" >发货仓位</td>
          <td nowrap="nowrap" align="center" >发货日期</td>
          <td nowrap="nowrap" align="center" >分公司已收数量</td>
          <td nowrap="nowrap" align="center" >收货仓位</td>
          <td nowrap="nowrap" align="center" >收货日期</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
          	<td align="center" nowrap="nowrap"> ${vs.count}</td>
            <td align="left" nowrap="nowrap">${cur.map.customer}</td>
            <td align="left" nowrap="nowrap">${cur.kunnr}</td>
            <td align="left" nowrap="nowrap">${cur.matnr}</td>
            <td align="left" nowrap="nowrap">${cur.vbeln}</td>
            <td align="right" nowrap="nowrap">${cur.lfimg1}</td>
            <td align="right" nowrap="nowrap">${cur.menge1}</td>
            <td align="right" nowrap="nowrap">${cur.map.price}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.erdat}" pattern="yyyy-MM-dd"/></td>
            <td align="right" nowrap="nowrap">${cur.reslo}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.budat1}" pattern="yyyy-MM-dd"/></td>
            <td align="right" nowrap="nowrap">${cur.menge2}</td>
            <td align="right" nowrap="nowrap">${cur.lgort}</td>
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
            </tr>
         </c:forEach>
      </table>
      </div>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaZles23ResultInfo.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
              <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			pager.addHiddenInputs("customer_name_like", "${fn:escapeXml(af.map.customer_name_like)}");
			pager.addHiddenInputs("r3_code_like", "${fn:escapeXml(af.map.r3_code_like)}");
			pager.addHiddenInputs("matnr_like", "${fn:escapeXml(af.map.matnr_like)}");
			pager.addHiddenInputs("lgort_like", "${fn:escapeXml(af.map.lgort_like)}");
			pager.addHiddenInputs("vbeln_like", "${fn:escapeXml(af.map.vbeln_like)}");
			pager.addHiddenInputs("reslo_like", "${fn:escapeXml(af.map.reslo_like)}");
			pager.addHiddenInputs("budat1_s", "${fn:escapeXml(af.map.budat1_s)}");
			pager.addHiddenInputs("budat1_e", "${fn:escapeXml(af.map.budat1_e)}");
			pager.addHiddenInputs("budat2_s", "${fn:escapeXml(af.map.budat2_s)}");
			pager.addHiddenInputs("budat2_e", "${fn:escapeXml(af.map.budat2_e)}");
            document.write(pager.toString());
            </script></td>
          </tr>
        </table>
      </form>
    <br />
    
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>