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
<style type="text/css">
	.tabtt1 td{background-color:#eff;}
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
    <html-el:form action="/admin/StatisticalDimensionData">
      <html-el:hidden property="method" value="storeType" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td>
           <strong class="fb">年度(自然)：</strong>
           <html-el:select property="year_id" styleId="year_id">
           	  <c:forEach var="cur" items="${yearList}">
                <html-el:option value="${cur.id}">${cur.year}</html-el:option>
              </c:forEach>
           	</html-el:select>
<!--            &nbsp;&nbsp; -->
<!--            <strong class="fb">年度类别：</strong> -->
<%--            	<html-el:select property="is_cw" styleId="is_cw" style="width:100px"> --%>
<%--               <html-el:option value="0">自然年度</html-el:option> --%>
<%--               <html-el:option value="1">财务年度</html-el:option> --%>
<%--             </html-el:select> --%>
<!--            &nbsp;&nbsp; -->
<!--            <strong class="fb">分公司：</strong> -->
<%--            	<html-el:select property="fgs_id" styleId="fgs_id" style="width:100px"> --%>
<%--               <html-el:option value="">请选择...</html-el:option> --%>
<%--               <c:forEach var="cur" items="${sybDeptInfoList}"> --%>
<%--                 <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option> --%>
<%--               </c:forEach> --%>
<%--             </html-el:select> --%>
           &nbsp;&nbsp;
           </td>
          <td>
           <strong class="fb">客户类型：</strong>
           	<html-el:select property="v_customer_type1" styleId="v_customer_type1" style="width:80px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
            <html-el:select property="v_customer_type2" styleId="v_customer_type2" style="width:130px;">
                <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
           &nbsp;&nbsp;
           <input class="but1" type="submit" name="Submit" value="搜索" />
           <input class="but_excel" type="button" name="export_excel"
						    id="export_excel" value="导出" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont1">
    <table width="100%" border="0" cellspacing="0" cellpadding="1" class="rtable2">
      <tr class="tabtt1">
        <td rowspan="2" nowrap="nowrap" align="center" width="4%">序号</td>
        <td rowspan="2" nowrap="nowrap" align="center" width="8%">客户类型</td>
        <td colspan="3" nowrap="nowrap" align="center" width="24%">上一年度(${year_pre})</td>
        <td colspan="3" nowrap="nowrap" align="center" width="24%">本年度(${year_now})</td>
        <td colspan="5" nowrap="nowrap" align="center" width="40%">(${year_now})结算区间(万元)</td>
       </tr>
      <tr class="tabtt1">
        <td nowrap="nowrap" align="center" width="8%">门店总数</td>
        <td nowrap="nowrap" align="center" width="8%">新增无结算</td>
        <td nowrap="nowrap" align="center" width="8%">新增已结算</td>
        <td nowrap="nowrap" align="center" width="8%">门店总数</td>
        <td nowrap="nowrap" align="center" width="8%">新增无结算</td>
        <td nowrap="nowrap" align="center" width="8%">新增已结算</td>
        <td nowrap="nowrap" align="center" width="8%">≥500</td>
        <td nowrap="nowrap" align="center" width="8%">200~501</td>
        <td nowrap="nowrap" align="center" width="8%">100~201</td>
        <td nowrap="nowrap" align="center" width="8%">0~101</td>
        <td nowrap="nowrap" align="center" width="8%">≤0</td>
      </tr>
      <tbody>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
            <td align="left">${fn:escapeXml(cur.map.c_name)}</td>
            <c:if test="${year_pre ge 2012}">
	            <td align="right">${fn:escapeXml(cur.map.store_num_pre)}</td>
	            <td align="right">${fn:escapeXml(cur.map.new_without_settle_pre)}</td>
	            <td align="right">${fn:escapeXml(cur.map.new_with_settle_pre)}</td>
            </c:if>
            <c:if test="${year_pre lt 2012}">
	            <td align="center"><font color="grey">无数据</font></td>
	            <td align="center"><font color="grey">无数据</font></td>
	            <td align="center"><font color="grey">无数据</font></td>
            </c:if>
            <td align="right">${fn:escapeXml(cur.map.store_num_now)}</td>
            <td align="right">${fn:escapeXml(cur.map.new_without_settle_now)}</td>
            <td align="right">${fn:escapeXml(cur.map.new_with_settle_now)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_805)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_804)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_803)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_802)}</td>
            <td align="right">${fn:escapeXml(cur.map.settle_type_801)}</td>
           
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="StatisticalDimensionData.do">
     <input id='export_id' style="display: none" name='excel_all'
					value='0' />
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40"><div style="text-align: right; padding-right: 5px;">
              <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "storeType");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("year_id", "${af.map.year_id}");
// 				pager.addHiddenInputs("fgs_id", "${af.map.fgs_id}");
				pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
	            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
// 				pager.addHiddenInputs("is_cw", "${af.map.is_cw}");
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	//I8右宽度不能自动适应加载，IE7,FireFox都可以的
	if(document.body.scrollLeft > 0 || document.body.scrollWidth > document.body.offsetWidth){
		$(".frame_right").width($(window).width() - 163);
	}else{
		$(".frame_right").width($(window).width() - 150);
	}
	
	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});

	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();

    //导出
	$("#export_excel").click(function(){
		if(confirm("提示，您确认导出数据？")){
			$("#export_id").val(1);
			$("#bottomPageForm").submit();
		}
		$("#export_id").val(0);
	});
});
//]]>
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
