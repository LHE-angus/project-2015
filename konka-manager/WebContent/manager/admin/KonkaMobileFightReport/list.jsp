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
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
        <td align="center"><strong class="fb" align="center">分公司/经办</strong></td>
          <td><html-el:select property="l3_dept_id" styleId="l3_dept_id">
          		<html-el:option value="">-分公司/经营部-</html-el:option>
          	</html-el:select>
          	&nbsp;
          	<html-el:select property="l4_dept_id" styleId="l4_dept_id">
          		<html-el:option value="">-请选择-</html-el:option>
          	</html-el:select>
          	&nbsp;
          	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
          		<html-el:option value="">-请选择-</html-el:option>
          	</html-el:select></td>
          <td align="center"><strong class="fb">上报人：</strong></td>
          <td><html-el:text property="report_name_like" size="16" maxlength="16" styleId="report_name_like" styleClass="webinput" /></td>
          <td align="center"><strong class="fb" >上报门店：</strong></td>
          <td><html-el:text property="dept_name" size="16" maxlength="16" styleId="dept_name" styleClass="webinput" style="cursor:pointer;"/></td>
          <tr>
          <td align="center"><strong class="fb">客户名称：</strong></td>
          <td><html-el:text property="store_name" size="16" styleId="store_name" styleClass="webinput" /></td>
          <td width="8%" align="center"><strong class="fb">时间范围：</strong></td>
          <td nowrap="nowrap"><html-el:text property="date_begin" styleId="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" />
            -
            <html-el:text property="date_end" styleId="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;"
				onclick="new Calendar(2005, 2030, 0).show(this);" /></td>
		  <td width="8%" style="text-align:center"><strong class="fb">尺寸：</strong></td>
          <td><html-el:text property="e_size" styleId="e_size" styleClass="webinput" style="cursor:pointer;" maxlength="20" ></html-el:text></td>
          </tr>
          <tr>
         <td align="center" align="center"><strong class="fb">R3编码：</strong></td>
          <td><html-el:text property="r3_code" size="16" maxlength="16" styleId="r3_code" styleClass="webinput" /></td>
          <td width="8%" align="center"><strong class="fb" >品牌名称：</strong></td>
          <td><html-el:select property="brand_id" styleId="brand_id">
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${pdBrandList}" var="cur">
                <html-el:option value="${cur.brand_id}">${cur.brand_name}</html-el:option>
              </c:forEach>
            </html-el:select></td>
          <td width="8%" align="center"><strong class="fb">型号：</strong></td>
          <td><html-el:text property="model_id_like" styleId="model_id_like" styleClass="webinput" maxlength="50"></html-el:text></td>
          </tr>
        <tr>
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
         <td  width="10%" align="center">
           		<strong class="fb" >规格段：</strong></td>
           		<td><html-el:select property="par_type_id" styleId="par_type_id">
            		<html-el:option value="">-请选择-</html-el:option>
            		<c:forEach items="${eList}" var="cur">
            			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
            		</c:forEach>
            	</html-el:select>
            </td >
            <td align="center" > <html-el:submit styleId="btn_submit" styleClass="but1">搜索</html-el:submit></td>
            <td align="center"><input type="button" value="Excel" id="export_excel" class="but_excel" /> </td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <%@ include file="/commons/pages/messages.jsp"%>
  <div class="rtabcont1" style="overflow:auto;">
    <table width="200%" border="0" cellspacing="0" cellpadding="0"
				class="rtable2">
      <tr class="tabtt1">
        <td width="50px" nowrap="nowrap" align="center">序号</td>
        <td width="100px" nowrap="nowrap" align="center">上报时间</td>
        <td width="100px" nowrap="nowrap" align="center">分公司</td>
        <td width="150px" nowrap="nowrap" align="center">客户名称</td>
        <td width="100px" nowrap="nowrap" align="center">客户R3编码</td>
        <td width="100px" nowrap="nowrap" align="center">客户类型</td>
        <td width="150px" nowrap="nowrap" align="center">细分类型</td>
        <td width="100px" nowrap="nowrap" align="center">上报人</td>
        <td width="150px" nowrap="nowrap" align="center">上报门店</td>
        <td width="50px" nowrap="nowrap" align="center">门店ID</td>
        <td width="100px" nowrap="nowrap" align="center">门店所属经办</td>
        <td width="80px" nowrap="nowrap" align="center">品牌</td>
         <td width="80px" nowrap="nowrap" align="center">规格段</td>
        <td width="60px" nowrap="nowrap" align="center">尺寸</td>
        <td width="80px" nowrap="nowrap" align="center">型号</td>
        <td width="60px" nowrap="nowrap" align="center">销量</td>
        <td width="100px" nowrap="nowrap" align="center">单价</td>
        <td width="100px" nowrap="nowrap" align="center">金额</td>
        <td width="150px" nowrap="nowrap" align="center">备注</td>
        <td width="50px" nowrap="nowrap" align="center">数据来源</td>
        <td width="100px" nowrap="nowrap" align="center">附件</td>
        <td width="100px" nowrap="nowrap" align="center">操作</td>
      </tr>
      <tbody> 
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.report_time}" pattern="yyyy-MM-dd" /></td>
            <td align="left" nowrap="nowrap">${cur.map.vo_dept_name}</td>
            <td align="center">${cur.map.store_name}</td>
            <td align="center">${cur.map.r3_code}</td>
            <td align="center">${cur.map.par_customer_type_name}</td>
       		<td align="center">${cur.map.customer_type_name}</td>
       		<td align="left" nowrap="nowrap">${cur.report_name}</td>
       		<td align="left">${cur.dept_name}</td>
            <td align="center">${cur.dept_id}</td>
            <td align="left" nowrap="nowrap">${cur.map.l5_dept_name}</td>
           
            <td align="center" nowrap="nowrap">${cur.map.brand_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.type_name}</td>
            <td align="center" nowrap="nowrap">${cur.map.e_size}</td>
            
             <c:if test="${cur.model_id=='1001'}">
            	<td nowrap="nowrap">全部</td>
            </c:if>
            <c:if test="${cur.model_id!='1001'}">
            	 <td align="left" nowrap="nowrap">${cur.model_id}</td>
            </c:if>
           
            <td align="right" nowrap="nowrap">${cur.num}</td>
            <td align="right" nowrap="nowrap" class="kz-price-12">
            <fmt:formatNumber value="${cur.price/cur.num}" type="currency"></fmt:formatNumber>
            </td>
            <td align="right" nowrap="nowrap" class="kz-price-12">
            <fmt:formatNumber value="${cur.price}" type="currency"></fmt:formatNumber>
            </td>
          	<td align="center">${cur.memo}</td>
          	<td align="center">
          		<c:if test="${cur.data_source==0}">电脑端</c:if>
          		<c:if test="${cur.data_source==1}">手机端</c:if>
          	</td>
            <td>
          	 	<c:forEach items="${cur.map.attachlist}" var="tt" varStatus="vs1">
          	 	<a href="${ctx}/${tt.save_path}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
         		</c:forEach>
	       </td>
	       <td>
	       <c:if test="${role_id_gt_10 eq true}"><span style="cursor:pointer;color:blue;" class="fblue" onclick="del('${cur.id}')">无效</span></c:if>
          	 <c:if test="${role_id_gt_10 ne true}"><span style="cursor:pointer; color:grey;" class="fblue" >无效</span></c:if>	 &nbsp;&nbsp;
          	 	<span style="cursor:pointer;color:blue;" class="fblue" onclick="edit('${cur.id}','${cur.map.e_size}','${cur.report_time}')">修改</span>
	       </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaMobileFightReport.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
				var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
				pager.addHiddenInputs("method", "list");
				pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
				pager.addHiddenInputs("report_name_like", "${af.map.report_name_like}");
				pager.addHiddenInputs("model_id_like", "${af.map.model_id_like}");
				pager.addHiddenInputs("brand_id", "${af.map.brand_id}");
				pager.addHiddenInputs("par_type_id", "${af.map.par_type_id}");	
 				pager.addHiddenInputs("e_size", "${af.map.e_size}");
				pager.addHiddenInputs("c_comm", "${af.map.c_comm}");
				pager.addHiddenInputs("subcomp_id", "${af.map.subcomp_id}");
				pager.addHiddenInputs("office_name", "${af.map.office_name}");
				pager.addHiddenInputs("store_name", "${af.map.store_name}");
				pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
				
				pager.addHiddenInputs("customer_cate_id", "${af.map.customer_cate_id}");
				pager.addHiddenInputs("dept_name_like", "${af.map.dept_name_like}");
				pager.addHiddenInputs("dept_name", "${af.map.dept_name}");
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
  <div class="rtabcont1" id="divExcel_all" style="overflow-x: auto; display: none;" title="竞品信息">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="rtable2">
      <tr class="tabtt1">
        <td width="5%" nowrap="nowrap" align="center">序号</td>
        <td width="8%" nowrap="nowrap" align="center">品牌</td>
        <td width="6%" nowrap="nowrap" align="center">尺寸</td>
        <td width="8%" nowrap="nowrap" align="center">型号</td>
        <td width="8%" nowrap="nowrap" align="center">规格段</td>
        <td width="6%" nowrap="nowrap" align="center">销量</td>
        <td width="10%" nowrap="nowrap" align="center">单价</td>
        <td width="10%" nowrap="nowrap" align="center">金额</td>
        <!--td width="8%" nowrap="nowrap" align="center">分公司</td-->
        <td width="14%" nowrap="nowrap" align="center">办事处</td>
        <td width="150" nowrap="nowrap" align="center">门店ID</td>
        <td nowrap="nowrap" align="center">上报门店</td>
        <td width="8%" nowrap="nowrap" align="center">上报人</td>
        <td width="8%" nowrap="nowrap" align="center">上报时间</td>
        <td width="130" nowrap="nowrap" align="center">备注</td>
        <td width="8%" nowrap="nowrap" align="center">附件</td>
      </tr>
      <tbody> 
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count}</td>
            <td align="center" nowrap="nowrap">${cur.map.brand_name}</td>
            <td align="center" nowrap="nowrap">${cur.map.e_size}</td>
            <td align="left" nowrap="nowrap">${cur.model_id}</td>
            <td align="left" nowrap="nowrap">${cur.map.type_name}</td>
            <td align="right" nowrap="nowrap">${cur.num}</td>
            <td align="right" nowrap="nowrap" class="kz-price-12">
            <fmt:formatNumber value="${cur.single_price}" type="currency"></fmt:formatNumber>
            </td>
            <td align="right" nowrap="nowrap" class="kz-price-12">
            <fmt:formatNumber value="${cur.price}" type="currency"></fmt:formatNumber>
            </td>
            <td align="left" nowrap="nowrap">${cur.map.l45_dept_name}</td>
            <td align="center">${cur.dept_id}</td>
            <td align="left">${cur.dept_name}</td>
            <td align="left" nowrap="nowrap">${cur.report_name}</td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.report_time}" pattern="yyyy-MM-dd" /></td>
          	<td align="center">${cur.memo}</td>
            <td>
          	 	<c:forEach items="${cur.map.attachlist}" var="tt" varStatus="vs1">
          	 	<a href="${ctx}/${tt.save_path}" target="_blank">&nbsp;附件${vs1.count}&nbsp;</a>
         		</c:forEach>
	       </td>
          </tr>
          </c:forEach>
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
function edit(id,nane,dateTime)
{
	var mod_id=$("#mod_id").val();
	window.location.href="${ctx}/manager/admin/KonkaMobileFightReport.do?method=edit&id="+id+"&mod_id="+mod_id+"&select_id=1";
		
	 
};
function del(id)
{
	var mod_id=$("#mod_id").val();
	window.location.href="${ctx}/manager/admin/KonkaMobileFightReport.do?method=delete&id="+id+"&mod_id="+mod_id+"&select_id=2";
	 
};

</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
