<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<META charset="UTF-8">
<META name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>预约点上报管理</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div class="oarcont">
  <div class="rtabcont2">
    <html-el:form action="/KonkaSpActivityBookReport" enctype="multipart/form-data">
      <html-el:hidden property="method" value="save" styleId="method" />
	<html-el:hidden property="id" value="${af.map.id}" />
	<html-el:hidden property="user_id" value="${user_id}" />
      <html-el:hidden property="userpass" value="${userpass}" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab2">
      <tr>
          <th colspan="2" width="90%" align="left" class="bno" id="filetitle">预约基本信息</th>
        </tr>
      <tr>
			<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>门店：</td>
			<td align="left" width="80%" colspan="3"><html-el:select
				property="store_id" styleId="store_id" value="${af.map.store_id}">
				<html-el:option value="">--请选择--</html-el:option>
				<c:forEach items="${storeList}" var="cur" varStatus="vs">
					<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
				</c:forEach>
			</html-el:select>
			<html-el:hidden property="store_name"
				styleId="store_name" />
			</td>
		</tr>
    <%--  <tr>
			<td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>客户：</td>
			<td align="left" width="80%" colspan="3"><html-el:select 
				property="r3_code" styleId="r3_code" value="${af.map.r3_code}">
				<html-el:option value="">--请选择--</html-el:option>
				<c:forEach items="${customerList}" var="cur" varStatus="vs">
					<html-el:option value="${cur.map.customer_code}">${cur.map.customer_name}</html-el:option>
				</c:forEach>
			</html-el:select>
			</td>
		</tr>
		 --%> 
      <tr>
      <td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>促销活动：</td>
       <td>
       <html-el:select property="sp_id" styleId="sp_id" style="width:170px;">
       <html-el:option value="">--请选择--</html-el:option>
       </html-el:select>
      </td>
      </tr>
      <tr>
      <td class="title_item" align="right" nowrap="nowrap"><font
				color="red">*</font>预约点：</td>
       <td>
       <html-el:select property="addr_id" styleId="addr_id" style="width:170px;">
       <html-el:option value="">--请选择--</html-el:option>
       </html-el:select>
      </td>
      </tr>
      
       <tr>
      <td class="title_item" align="right" nowrap="nowrap">消费者姓名: </td> 
        <td><html-el:text property="comsumer_name" styleId="comsumer_name" ></html-el:text> </td> 
       </tr>
      
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">消费者电话: </td> 
        <td><html-el:text property="comsumer_phone" styleId="comsumer_phone" >
        </html-el:text> </td> 
       </tr>
      
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约尺寸: </td> 
        <td>
        
<!--        <html-el:text property="size_section" styleId="size_section" > </html-el:text>-->
		
        <html-el:select property="size_section" styleId="size_section" >
        <html-el:option value="">-请选择-</html-el:option>
        <c:forEach items="${sizeSecList}" var="cur" varStatus="vs">
			<html-el:option value="${cur.field1}">${cur.type_name}</html-el:option>
		</c:forEach>
        </html-el:select>
        </td> 
       </tr>
       
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约型号: </td> 
        <td><html-el:text property="model_name" styleId="model_name" >
        </html-el:text> </td> 
       </tr>
       
        <tr>
      	<td class="title_item" align="right" nowrap="nowrap">预约数量: </td> 
        <td><html-el:text property="num" styleId="num" >
        </html-el:text> </td> 
       </tr>
       
      <tr>
      	<td class="title_item" align="right" nowrap="nowrap">支付定金: </td> 
        <td><html-el:radio  styleClass="prepay_state" property="prepay_state" value="0" />是
            &nbsp;&nbsp;<html-el:radio  styleClass="prepay_state" property="prepay_state" value="1" />否
            <div id="prepay_money_par">定金金额：<html-el:text property="prepay_money" style="width:80px;" styleId="prepay_money"/></div>
         </td> 
       </tr> 
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">备注: </td> 
        <td><html-el:textarea property="memo" styleId="memo"  rows="3" style="width:50%;">
        </html-el:textarea> </td> 
       </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="submit" name="Submit4" value="提交" />
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back()" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<!-- ****** Main Frame End ****** -->
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/commons.plugin.jxc.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=idialog"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){	
//	$("#prepay_money").attr("hidden","hidden");
		$(".prepay_state").click(function(){
		var radiovalue=$(this).val();
		if(radiovalue==1){
			$("#prepay_money_par").attr("hidden","hidden");
			$("#prepay_money").attr("disabled","disabled");	
			}else{
				$("#prepay_money_par").removeAttr("hidden");
				$("#prepay_money").removeAttr("disabled");
				}
			});
		
		$("#store_id").change(function(){
					$("#store_name").val(
							$(this).find("option:selected").text());
					ajaxGetSp(ui.value);
					ajaxGetAddr(ui.value);
		});	
		
		ajaxGetSp($("#store_id").val());
		ajaxGetAddr($("#store_id").val());
});

function ajaxGetSp(store_id){
	 $.ajax({
		  type: "POST",
		  url: "${ctx}/webservice/KonkaSpActivityBookReport.do?method=ajaxGetSp",
		  data: {"store_id" : store_id},
		  dataType: "json",
		  sync: true, 
		  error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		  success: function(result) {
			  if(null!=result && result.length > 0){
				$("#sp_id").empty();
				$("#sp_id").append("<option value=''>-请选择-</option>");
				for(var i = 0 ;i< result.length ;i++){
					if('${af.map.sp_id}'==result[i].id){
               var str="<option selected='true' value='"+result[i].id+"'>"+result[i].sp_name+"</option>";
					}else{
						var str="<option value='"+result[i].id+"'>"+result[i].sp_name+"</option>";
						}
               $("#sp_id").append(str);
				}
			  }
		  }
	  }); 
}
function ajaxGetAddr(store_id){
	 $.ajax({
		  type: "POST",
		  url: "${ctx}/webservice/KonkaSpActivityBookReport.do?method=ajaxGetAddr",
		  data: {"store_id" : store_id},
		  dataType: "json",
		  sync: true, 
		  error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
		  success: function(result) {
				$("#addr_id").empty();
				$("#addr_id").append("<option value=''>-请选择-</option>");
				if(null!=result && result.length > 0){
				for(var i = 0 ;i< result.length ;i++){
					if('${af.map.addr_id}'==result[i].id){
                var str="<option selected='true' value='"+result[i].id+"'>"+result[i].addr+"</option>";
					}else{
				var str="<option  value='"+result[i].id+"'>"+result[i].addr+"</option>";
						}
                $("#addr_id").append(str);
					}
				}
		  }
	  }); 
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
