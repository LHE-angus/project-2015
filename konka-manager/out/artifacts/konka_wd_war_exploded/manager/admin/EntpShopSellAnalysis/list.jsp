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
<div class="oarcont">
<div class="oartop">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
      <td nowrap="nowrap">当前位置：${naviString} > 经营情况</td>
    </tr>
  </table>
</div>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
  <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
</div>
<div class="rtabcont1">
<html-el:form action="/admin/EntpShopSellAnalysis">
 <html-el:hidden property="method" value="list" />
  <html-el:hidden property="shop_id" value="${af.map.shop_id}" />
  <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
  <html-el:hidden property="tree_param" value="${tree_param}" />
  <html-el:hidden property="chart_type" styleId="chart_type" value="" />
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable2">
    <tr>
      <td width="25%" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable2">
          <tr>
            <td colspan="2" align="center"><c:out value="${entpShop.shop_name}" /></td>
          </tr>
          <tr>
            <td width="70" nowrap="nowrap" align="right">地区邮编：</td>
            <td align="left"><c:out value="${entpShop.post_code}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap" align="right">所属地区：</td>
            <td align="left"><c:forEach var="cur" items="${baseProvinceListList}">${cur.p_name}&nbsp;</c:forEach></td>
          </tr>
          <tr>
            <td nowrap="nowrap" align="right">网点地址：</td>
            <td align="left"><c:out value="${entpShop.street_addr}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap" align="right">联系人：</td>
            <td align="left"><c:out value="${entpShop.link_user}" /></td>
          </tr>
          <tr>
            <td nowrap="nowrap" align="right">联系电话：</td>
            <td align="left"><c:out value="${entpShop.link_phone}" /></td>
          </tr>
        </table>
      </td>
      <td width="75%"><table width="100%">
          <tbody>
            <!--1-->
            <tr>
              <td width="100%" style="border-right:0px solid #E3E3E3;">
                  <ul>
                    <li>当前月与上个月该网点品类销售额环比图</li>
                    <li>&nbsp;年份：
                      <html-el:select property="year_1" styleId="year_1" style="width:80px;">
                        <html-el:option value="">-请选择-</html-el:option>
                        <html-el:optionsCollection name="yearMap" label="value" value="key" />
                      </html-el:select>
                      <div style="float: right;padding-right:30px;">
                        <html-el:button property="search" styleId="btn_1" styleClass="but1" value="查看"/>
                      </div>
                    </li>
                  </ul>
                </td>
            </tr>
            <!--2-->
            <tr>
              <td width="100%" style="border-right:0px solid #E3E3E3;">
                  <ul>
                    <li>当前与去年同期该网点品类销售额同比图</li>
                    <li>&nbsp;品类：
                      <html-el:select property="pd_type_2" styleId="pd_type_2" style="width:80px;">
                        <html-el:option value="">-请选择-</html-el:option>
                        <html-el:optionsCollection name="basePdTypeMap" label="value" value="key" />
                      </html-el:select>
                      <div style="float: right;padding-right:30px;">
                        <html-el:button property="search" styleId="btn_2" styleClass="but1" value="查看" />
                      </div>
                    </li>
                  </ul>
                </td>
            </tr>
            <!--3-->
            <tr>
              <td width="100%" style="border-right:0px solid #E3E3E3;">
                  <ul>
                    <li>当前月与上个月该网点品类品牌销售量环比图</li>
                    <li>&nbsp;年份：
                      <html-el:select property="year_3" styleId="year_3" style="width:80px;">
                        <html-el:option value="">-请选择-</html-el:option>
                         <html-el:optionsCollection name="yearMap" label="value" value="key" />
                      </html-el:select>
                      &nbsp;品类：
                      <html-el:select property="pd_type_3" styleId="pd_type_3" style="width:80px;">
                        <html-el:option value="">-请选择-</html-el:option>
                        <html-el:optionsCollection name="basePdTypeMap" label="value" value="key" />
                      </html-el:select>
                      <div style="float: right;padding-right:30px;">
                        <html-el:button property="search" styleId="btn_3" styleClass="but1" value="查看" />
                      </div>
                    </li>
                  </ul>
               </td>
            </tr>
            <!--4-->
            <tr>
              <td width="100%" style="border-right:0px solid #E3E3E3;border-bottom:0px solid #E3E3E3;">
                  <ul>
                    <li>当前与去年同期该网点品类品牌销售量同比图</li>
                    <li>&nbsp;品类：
                      <html-el:select property="pd_type_4" styleId="pd_type_4" style="width:80px;">
                        <html-el:option value="">-请选择-</html-el:option>
                        <html-el:optionsCollection name="basePdTypeMap" label="value" value="key" />
                      </html-el:select>
                      &nbsp;品牌：
                      <html-el:select property="brand_id_4" styleId="brand_id_4" style="width:80px;">
                        <html-el:option value="">-请选择-</html-el:option>
                      </html-el:select>
                      <div style="float: right;padding-right:30px;">
                        <html-el:button property="search" styleId="btn_4" styleClass="but1" value="查看" />
                      </div>
                    </li>
                  </ul>
              </td>
            </tr>
          </tbody>
        </table></td>
    </tr>
  </table>
 </html-el:form>
</div>

<%@ include file="chart.jsp" %>
<div class="clear"></div>
</div>
<input type="hidden" value="" id="brand_list" />
<c:forEach items="${mdasShopBrandsalesInBasePdTypeWithBrandList}" var="cur"></c:forEach>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
// 页面常量
var type = '${af.map.type}';
function requiredCheck(id,msg){
	  var obj = document.getElementById(id);
	  if(obj.value.length == 0){
		  var span = document.createElement("SPAN");
		  span.setAttribute("id","err_"+id);
		  span.style.color = "red";
		  span.innerHTML = msg.replace(/\d+\. /,"&nbsp;* ");
		  obj.parentNode.appendChild(span);		  
		  return false;
	  }else{
		  var obj_err = document.getElementById("err_"+id);
          if(obj_err){
        	  obj.parentNode.removeChild(obj_err);
          }
		  return true;
	  }		  
}

$(document).ready(function(){
	$("#btn_1").click(function (){
		if(requiredCheck("year_1","请选择年份！")){
			$("#chart_type").val("1");
			openChart("chart_type,year_1");
		}
	});
	
	$("#btn_2").click(function (){
		if(requiredCheck("pd_type_2","请选择品类！")){
			$("#chart_type").val("2");
			openChart("chart_type,pd_type_2");
		}
	});

	$("#btn_3").click(function (){
		if(requiredCheck("year_3","请选择年份！") && requiredCheck("pd_type_3","请选择品类！")){
			$("#chart_type").val("3");
			openChart("chart_type,year_3,pd_type_3");
		}
	});
	
	$("#btn_4").click(function (){
		$("#chart_type").val("4");
		if(requiredCheck("pd_type_4","请选择品类！") && requiredCheck("brand_id_4","请选择品牌！")){
			$("#chart_type").val("4");
			openChart("chart_type,pd_type_4,brand_id_4");
		}
	});

	$("#pd_type_4").change(function (){
		$("#brand_id_4").empty();
		$("#brand_id_4").get(0).options.add(new Option("-请选择-", ""));

		for(var i = 0; i < array_s.length; i ++){
			if(array_s[i].split(",")[0] == $("#pd_type_4").val()){
				var opt = new Option(array_s[i].split(",")[2], array_s[i].split(",")[1]); 
				$("#brand_id_4").get(0).options.add(opt);
			}
		}
	});
	
	var msbInbptWithBrandListString = '${msbInbptWithBrandListString}';
	var array_s = msbInbptWithBrandListString.split(":");
    if(array_s != null && array_s.length >0){
    	for(var i = 0; i < array_s.length; i ++){
			if(array_s[i].split(",")[0] == '${af.map.pd_type_4}'){
				var opt = new Option(array_s[i].split(",")[2], array_s[i].split(",")[1]);
				if(array_s[i].split(",")[1] == '${af.map.brand_id_4}'){
					opt.setAttribute("selected",true);
				}
				$("#brand_id_4").get(0).options.add(opt);
			}
		}
    }
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>