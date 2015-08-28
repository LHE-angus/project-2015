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
    <html-el:form action="/admin/KonkaR3ShopStock.do">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          	<td class="fb" width="10%" align="right">部门：</td>
          	<td>
          		<html-el:select property="branch_area_name" styleId="branch_area_name" onchange="changeFgsSn();">
                  <html-el:option value="">-请选择-</html-el:option>
                  <c:forEach items="${konkaDeptList}" var="cur">
	                  <html-el:option value="${cur.dept_name}">${cur.dept_name}</html-el:option>
                  </c:forEach>
                </html-el:select>
                
                <html-el:select property="handle_name_like" styleId="handle_name_like" >
                  <html-el:option value="">-请选择-</html-el:option>
                </html-el:select>
          	</td>
<!--           <td width="10%" class="fb" align="right">客户编码：</td> -->
<%--           <td><html-el:text property="r3_code" size="20" maxlength="20" styleId="r3_code" /></td> --%>
           <td width="10%" class="fb" align="right">客户编码：</td> 
       <td><html-el:text property="r3_code_like" size="20" maxlength="20" styleId="r3_code_like" /></td>
          <td width="10%" class="fb" align="right">客户名称：</td>
          <td><html-el:text property="customer_name_like" size="20" maxlength="20" styleId="customer_name_like" /></td>
          
         </tr>
          
          
          <tr>
          	<td class="fb" width="10%" align="right">客户类型：</td>
          <td>
            <html-el:select property="v_customer_type1" styleId="v_customer_type1" >
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
            <html-el:select property="v_customer_type2" styleId="v_customer_type2" >
                <html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
           </td>
           
          <td width="10%" class="fb" align="right">上月是否全部盘点：</td>
          <td>
	          <html-el:select property="is_pd" styleId="is_pd" >
	            <html-el:option value="">-请选择-</html-el:option>
	          	<html-el:option value="0">否</html-el:option>
	          	<html-el:option value="1">是</html-el:option>
	          </html-el:select>
          </td>
          
          <td width="10%" class="fb" align="right">本月是否结转：</td>
          <td>
	          <html-el:select property="is_jz" styleId="is_jz" >
                  <html-el:option value="">-请选择-</html-el:option>
	              <html-el:option value="0">否</html-el:option>
	              <html-el:option value="1">是</html-el:option>
	          </html-el:select>
          </td>
        </tr>
        
        <tr>
        	<td colspan="4"></td>
        	<td width="10%" align="center">
	          <html-el:submit styleClass="but1" value="搜索" />
            </td>
            <td><input class="but_excel" type="button" name="export_excel" id="export_excel" value="导出" /></td>
            
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1">
  <div style="color:red">* 全部盘点：对客户已经初始化过的产品进行全部盘点，日期必须在每月的月末(25-月末)之间；结转：若上月末没有全部盘点，则本月初(月初-5)需结转库存。</div>
  <div style="overflow-x: auto">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="8%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" align="left" >客户名称</td>
           <td nowrap="nowrap" align="center">客户类型</td>
          <td nowrap="nowrap" align="center">细分类型</td>
          <td width="5%" align="center" nowrap="nowrap">客户群类型</td>
          <td nowrap="nowrap" width="10%">R3编码</td>
          <td nowrap="nowrap" width="10%">经办名称</td>
          <td nowrap="nowrap" width="10%">分公司所在地名称</td>
          <td nowrap="nowrap" width="10%">合并客户编码</td>
          <td nowrap="nowrap" width="10%">上月是否全部盘点</td>
          <td nowrap="nowrap" width="10%">是否结转</td>
          <td nowrap="nowrap" align="center" width="10%">操作</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.customer_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.par_cust_type_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.cust_type_name}</td>
            <td align="center" nowrap="nowrap">${cur.customer_type}</td>
            <td align="left">${fn:escapeXml(cur.r3_code)}</td>
            <td align="left" >${fn:escapeXml(cur.map.ext_handle_name)}</td>
            <td align="left" >${fn:escapeXml(cur.branch_area_name)}</td>
            <td align="left" >${fn:escapeXml(cur.customer_code)}</td>
            <td align="center" >
            	<c:if test="${cur.map.is_pd eq 1}"><font color="green">是</font></c:if>
            	<c:if test="${cur.map.is_pd eq 0}">否</c:if>
            </td>
            <td align="center" >
            	<c:if test="${cur.map.is_jz eq 1}"><font color="green">是</font></c:if>
            	<c:if test="${cur.map.is_jz eq 0}">否</c:if>
            </td>
            <td align="center" nowrap="nowrap">
            	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3ShopStock.do','view', '&mod_id=${af.map.mod_id}&cust_id=${cur.id}&'+$('#bottomPageForm').serialize())">查询实时库存</span>
            	<c:if test="${cur.map.is_jz eq 1}"><span style='color:#CCC;'>结转</span></c:if>
            	<c:if test="${cur.map.is_jz eq 0}">
	            	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaR3ShopStock.do','jiezhuan', '&mod_id=${af.map.mod_id}&cust_id=${cur.id}&'+$('#bottomPageForm').serialize())">结转</span>
            	</c:if>
            </td>
          </tr>
        </c:forEach>
      </table>
      </div>
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaR3ShopStock.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
	            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
	            pager.addHiddenInputs("r3_code", "${af.map.r3_code}");
	            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
	            pager.addHiddenInputs("customer_name_like", "${af.map.customer_name_like}");
	            pager.addHiddenInputs("branch_area_name", "${af.map.branch_area_name}");
	            pager.addHiddenInputs("is_pd", "${af.map.is_pd}");
	            pager.addHiddenInputs("is_jz", "${af.map.is_jz}");
	            pager.addHiddenInputs("v_customer_type1", "${af.map.v_customer_type1}");
	            pager.addHiddenInputs("v_customer_type2", "${af.map.v_customer_type2}");
	            pager.addHiddenInputs("handle_name_like", "${af.map.handle_name_like}");
	            document.write(pager.toString());
	      </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="rtabcont1" style="overflow-x: auto; display: none;" id="divExcel_all" title="客户库存">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" align="center" nowrap="nowrap">序号</td>
          <td nowrap="nowrap" align="left" >客户名称</td>
           <td nowrap="nowrap" align="center">客户类型</td>
          <td nowrap="nowrap" align="center">细分类型</td>
          <td width="5%" align="center" nowrap="nowrap">客户群类型</td>
          <td nowrap="nowrap" width="10%">R3编码</td>
          <td nowrap="nowrap" width="10%">分公司所在地名称</td>
          <td nowrap="nowrap" width="10%">合并客户编码</td>
          <td nowrap="nowrap" width="10%">上月是否全部盘点</td>
          <td nowrap="nowrap" width="10%">是否结转</td>
        </tr>
        <c:forEach var="cur" items="${allList}" varStatus="vs">
          <tr>
            <td align="center" nowrap="nowrap">${vs.count }</td>
            <td align="left" nowrap="nowrap">${cur.customer_name}</td>
             <td align="left" nowrap="nowrap">${cur.map.par_cust_type_name}</td>
            <td align="left" nowrap="nowrap">${cur.map.cust_type_name}</td>
            <td align="center" nowrap="nowrap">${cur.customer_type}</td>
            <td align="left">${fn:escapeXml(cur.r3_code)}</td>
            <td align="left" >${fn:escapeXml(cur.branch_area_name)}</td>
            <td align="left" >${fn:escapeXml(cur.customer_code)}</td>
            <td align="left" >
            	<c:if test="${cur.map.is_pd eq 1}">是</c:if>
            	<c:if test="${cur.map.is_pd eq 0}">否</c:if>
            </td>
            <td align="left" >
            	<c:if test="${cur.map.is_jz eq 1}">是</c:if>
            	<c:if test="${cur.map.is_jz eq 0}">否</c:if>
            </td>
          </tr>
        </c:forEach>
      </table>
  </div>
  
  
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	//客户类型初始化
	$("#v_customer_type1").attr({"subElement": "v_customer_type2", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type1}"});
	$("#v_customer_type2").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.v_customer_type2}"});

	$("#v_customer_type1").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaShopType", "par_id", "0", false);
	$("#v_customer_type1").change();
	
	$("#handle_name_like").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.handle_name_like}"});
	
	changeFgsSn();
	
});

function changeFgsSn(){
	
	//取得分公司
	var vcompany = $("#branch_area_name").val();
	if(vcompany !=""){
		$.ajax({
			type: "post",
			url: "${ctx}/manager/admin/KonkaR3ShopStock.do?method=getJBDataBydeptName",
			data: {"branch_area_name" : vcompany},
			dataType: "json",
			error: function(request, settings) {
				var html = "<option value=''>请选择</option>";
				$("#handle_name_like").empty();
				$("#handle_name_like").html(html);
				}, 
			success: function(result) {
				var html = "<option value=''>请选择</option>";
				for(var i = 0 ;i<result.length ;i++){
					if(result[i] == "${af.map.handle_name_like}"){
						html += "<option selected='selected' value='"+result[i]+"'>"+result[i]+"</option>";
						}else{
							html += "<option value='"+result[i]+"'>"+result[i]+"</option>";
							}
				}
				$("#handle_name_like").empty();
				$("#handle_name_like").html(html);
			}
		});
	}else{
		var html = "<option value=''>请选择</option>";
		$("#handle_name_like").empty();
		$("#handle_name_like").html(html);
	}
	
}


//导出excel
$("#export_excel").click(function(){
	if(confirm("提示，您确认导出数据？")){
		$("#bottomPageForm").append("<input type='hidden' name='excel_all' value='1' />");
		$("#bottomPageForm").submit();
	}
});

var excel_all = "${af.map.excel_all}";
if("1" == excel_all){
	toExcel('divExcel_all', '${ctx}/manager/admin/KonkaR3ShopStock.do?method=toExcel');
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>