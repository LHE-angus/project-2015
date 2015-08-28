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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img
						src="${ctx}/styles/admin-index/images/k_tup.jpg"
						style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif"
						style="vertical-align: middle;" /> <span id="span_help"
						style="cursor: pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
     <html-el:form action="/admin/KonkaMobileFightReport">
      <html-el:hidden property="method" value="fightSum" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
           
           <td  align="right"><strong class="fb" >分公司/经办</strong></td>
          <td  align="left" colspan='3'> 	<html-el:select property="l3_dept_id" styleId="l3_dept_id">
          		<html-el:option value="">-分公司/经营部-</html-el:option>
          	</html-el:select>
          	&nbsp;
          	<html-el:select property="l4_dept_id" styleId="l4_dept_id">
          		<html-el:option value="">-请选择-</html-el:option>
          	</html-el:select>
          	&nbsp;
          	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
          		<html-el:option value="">-请选择-</html-el:option>
          	</html-el:select>
           </td>
           <td align="right"><strong class="fb">上报人：</strong></td> 
           <td><html-el:text property="report_name_like" size="16" maxlength="16" styleId="report_name_like" styleClass="webinput" /></td>
        	 <td align="right"><strong class="fb">上报门店：</strong></td>
           <td><html-el:text property="dept_name" size="16" styleId="dept_name" styleClass="webinput" /></td>
          <tr>
          <td align="right"><strong class="fb">客户名称：</strong></td>
          <td><html-el:text property="store_name" size="16"  styleId="store_name" styleClass="webinput" /></td>
          <td  align="right"><strong class="fb">R3编码：</strong></td>
          <td><html-el:text property="r3_code" size="16" maxlength="16" styleId="r3_code" styleClass="webinput" /></td>
          <td width="10%" align="right"><strong class="fb">时间范围：</strong></td>
          <td width="10%" nowrap="nowrap" width="25%" align="left"> 
            <html-el:text property="date_begin" styleId="date_begin"  size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" />
            -
            <html-el:text property="date_end" styleId="date_end"  size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" />
		  </td>
		  <td></td>
		  <td></td>
          </tr>
          <tr>
          <td width="10%" align="right"><strong class="fb">品牌名称：</strong></td>
          <td width="10%" align="left">
            <html-el:select property="brand_id" styleId="brand_id">
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${pdBrandList}" var="cur">
                <html-el:option value="${cur.brand_id}">${cur.brand_name}</html-el:option>
              </c:forEach>
            </html-el:select>
          </td>
          <td align="center"><strong class="fb" align="center"> 客户分类：</strong></td>
          <td >
           <html-el:select property="c_comm" styleId="c_comm" onchange="customer_cate_id_chg();">
              <html-el:option value="">-客户类型-</html-el:option>
              <c:forEach var="cur" items="${konkaCategoryList}">
              	 <c:if test="${not empty cur.map.c_comm}"><html-el:option value="${cur.map.par_index}">${cur.map.c_comm}</html-el:option></c:if>
              </c:forEach>
            </html-el:select>
            <select name="customer_cate_id" id="customer_cate_id">
              <option value="">-细分类型-</option>
            </select>
          </td>
           <td width="10%" align="right"><strong class="fb" >统计方式 : </strong></td>
          <td width="10%">	
          		<html-el:select property="pbList" styleId="pbList">
          		<html-el:option value="0">-金额-</html-el:option>
          		<html-el:option value="1">-数量-</html-el:option>
          		</html-el:select>
          </td>
         	 <td width="10%"><html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
        	 <td width="10%" align="center"><input type="button" value="Excel" id="export_excel" class="but_excel" /></td>
          </tr>
        </table>
    </html-el:form>
  </div>
  <div style="float:left;"> <c:if test="${pbList==1}"><font style="color:red;nowrap:nowrap;">&nbsp;&nbsp;单位 ：数量/台</font></c:if><c:if test="${pbList==0}"><font style="color:red;nowrap:nowrap;">&nbsp;&nbsp;单位 ：金额/元</font></c:if> </div>
  <%@ include file="/commons/pages/messages.jsp"%>
  <div class="rtabcont1">
    <table width="100%" border="1" cellspacing="0" cellpadding="0"
				class="rtable2">
      <tr class="tabtt1">
      	<td width="8%" nowrap="nowrap" align="center">序号</td>
        <td width="8%" nowrap="nowrap" align="center">品牌</td>
        <td width="8%" nowrap="nowrap" align="center">小于32英寸</td>
        <td width="8%" nowrap="nowrap" align="center">39-40英寸</td>
        <td width="8%" nowrap="nowrap" align="center">42-43英寸</td>
        <td width="8%" nowrap="nowrap" align="center">46-50英寸</td>
        <td width="8%" nowrap="nowrap" align="center">55英寸</td>
        <td width="8%" nowrap="nowrap" align="center">58-60英寸</td>
        <td width="8%" nowrap="nowrap" align="center">65英寸</td>
        <td width="8%" nowrap="nowrap" align="center">大于65英寸</td>
        <td width="8%" nowrap="nowrap" align="center">其他</td>
        <td width="8%" nowrap="nowrap" align="center">时间</td>
      </tr>
      <tbody> 
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
         <c:if test="${pbList==1}">
          <tr>
          	<td width="8%" nowrap="nowrap" align="right">${vs.count}</td>
          	<td width="8%" nowrap="nowrap" align="right">${cur.map.brand_name }</td>
           	<td width="8%" nowrap="nowrap" align="right">${cur.map.yc32 }</td>
        	<td width="8%" nowrap="nowrap" align="right">${cur.map.yc40 }</td>
        	<td width="8%" nowrap="nowrap" align="right">${cur.map.yc43 }</td>
        	<td width="8%" nowrap="nowrap" align="right">${cur.map.yc50 }</td>
       		<td width="8%" nowrap="nowrap" align="right">${cur.map.yc55 }</td>
        	<td width="8%" nowrap="nowrap" align="right">${cur.map.yc60 }</td>
        	<td width="8%" nowrap="nowrap" align="right">${cur.map.yc65 }</td>
        	<td width="8%" nowrap="nowrap" align="right">${cur.map.ycd65 }</td>
        	<td width="8%" nowrap="nowrap" align="right">${cur.map.ycqt }</td>
        	<td width="8%" nowrap="nowrap" align="right">${fn:escapeXml(af.map.date_begin)}——${fn:escapeXml(af.map.date_end)}</td>
          </tr>
          </c:if>
           <c:if test="${pbList==0}">
          <tr>
          <td width="8%" nowrap="nowrap" align="right">${vs.count}</td>
          <td width="8%" nowrap="nowrap" align="right" >${cur.map.brand_name }</td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc32 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc40 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc43 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc50 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc55 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc60 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.yc65 }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.ycd65}"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right" ><fmt:formatNumber value="${cur.map.ycqt }"  pattern="0.00"></fmt:formatNumber></td>
          <td width="8%" nowrap="nowrap" align="right">${fn:escapeXml(af.map.date_begin)}—${fn:escapeXml(af.map.date_end)}</td>
          </tr>
          </c:if>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileFightReport.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "fightSum");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
				pager.addHiddenInputs("model_id_like", "${af.map.model_id_like}");
				pager.addHiddenInputs("brand_id", "${af.map.brand_id}");
				pager.addHiddenInputs("pbList", "${af.map.pbList}");
				pager.addHiddenInputs("c_comm", "${af.map.c_comm}");
				pager.addHiddenInputs("customer_cate_id", "${af.map.customer_cate_id}");
 				pager.addHiddenInputs("e_size", "${af.map.e_size}");
 				pager.addHiddenInputs("store_name", "${af.map.store_name}");
 				pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
				pager.addHiddenInputs("subcomp_id", "${af.map.subcomp_id}");
				pager.addHiddenInputs("office_name", "${af.map.office_name}");
				pager.addHiddenInputs("dept_name", "${af.map.dept_name}");
				pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");
				pager.addHiddenInputs("date_begin", "${fn:escapeXml(af.map.date_begin)}");							
				pager.addHiddenInputs("date_end", "${fn:escapeXml(af.map.date_end)}");							
				pager.addHiddenInputs("l3_dept_id", "${af.map.l3_dept_id}");							
				pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");			
				pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");	
				document.write(pager.toString());
		   </script></td>
        </tr>
      </table>
    </form>
  </div>
   <!-- 用于导出excel -->
  <div class="rtabcont1" id="divExcel_all" style="overflow-x: auto; display: none;" title="竞品统计信息">
   <table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="rtable2">
      <tr class="tabtt1">
        <td width="11%" nowrap="nowrap" align="center">品牌</td>
        <td width="11%" nowrap="nowrap" align="center">小于32英寸</td>
        <td width="11%" nowrap="nowrap" align="center">39-40英寸</td>
        <td width="11%" nowrap="nowrap" align="center">42-43英寸</td>
        <td width="11%" nowrap="nowrap" align="center">46-50英寸</td>
        <td width="11%" nowrap="nowrap" align="center">55英寸</td>
        <td width="11%" nowrap="nowrap" align="center">58-60英寸</td>
        <td width="11%" nowrap="nowrap" align="center">65英寸</td>
        <td width="11%" nowrap="nowrap" align="center">大于65英寸</td>
        <td width="11%" nowrap="nowrap" align="center">其他</td>
      </tr>
      <tbody> 
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
         <c:if test="${pbList==1}">
          <tr>
          	<td width="11%" nowrap="nowrap" align="center">${cur.map.brand_name }</td>
           	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc32 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc40 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc43 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc50 }</td>
       		<td width="11%" nowrap="nowrap" align="center">${cur.map.yc55 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc60 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.yc65 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.ycd65 }</td>
        	<td width="11%" nowrap="nowrap" align="center">${cur.map.ycqt }</td>
          </tr>
          </c:if>
           <c:if test="${pbList==0}">
          <tr >
          
          <td width="11%" nowrap="nowrap" align="center">${cur.map.brand_name }</td>
          <td width="11%" nowrap="nowrap" align="center" ><fmt:formatNumber value="${cur.map.yc32 }" type="currency"></fmt:formatNumber></td>
          <td width="11%" nowrap="nowrap" align="center" class="kz-price-12"><fmt:formatNumber value="${cur.map.yc40 }" type="currency"></fmt:formatNumber></td>
          <td width="11%" nowrap="nowrap" align="center" class="kz-price-12"><fmt:formatNumber value="${cur.map.yc43 }" type="currency"></fmt:formatNumber></td>
          <td width="11%" nowrap="nowrap" align="center" class="kz-price-12"><fmt:formatNumber value="${cur.map.yc50 }" type="currency"></fmt:formatNumber></td>
          <td width="11%" nowrap="nowrap" align="center" class="kz-price-12"><fmt:formatNumber value="${cur.map.yc55 }" type="currency"></fmt:formatNumber></td>
          <td width="11%" nowrap="nowrap" align="center" class="kz-price-12"><fmt:formatNumber value="${cur.map.yc60 }" type="currency"></fmt:formatNumber></td>
          <td width="11%" nowrap="nowrap" align="center" class="kz-price-12"><fmt:formatNumber value="${cur.map.yc65 }" type="currency"></fmt:formatNumber></td>
          <td width="11%" nowrap="nowrap" align="center" class="kz-price-12"><fmt:formatNumber value="${cur.map.ycqt }" type="currency"></fmt:formatNumber></td>
          
          </tr>
          </c:if>
        </c:forEach>
      </tbody>
    </table>
  </div>
      </tbody>
    </table>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#l3_dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l3_dept_id}"});
	$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#l3_dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "${empty cs_par_id ? 0 : cs_par_id}");
	$("#l3_dept_id").change();
	
	 // 导出excel
    $("#export_excel").click(function(){
    	$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
		$("#bottomPageForm").submit();
    });
    customer_cate_id_chg();
});
//类别-连动-客户类别
function customer_cate_id_chg(){
	$("#customer_cate_id").empty();
	$("<option value=''>-细分类型-</option>").appendTo($("#customer_cate_id"));
	var url = "${ctx}/manager/admin/KonkaMobileSailData.do?method=getCType&c_comm="+$('#c_comm').val();
	$.getJSON(url, function(data) {
		if(data != null){
			$.each(data, function(i, item) {
				var option = $("<option></option>").val(item[1]).text(item[0]);
				option.appendTo($("#customer_cate_id"));
			});
			if('${af.map.customer_cate_id }' != null || '${af.map.customer_cate_id}' != '' ){
				$("#customer_cate_id").val('${af.map.customer_cate_id }');
			}
		}
	});
}

</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
