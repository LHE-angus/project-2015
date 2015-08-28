<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8" ></meta>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv=Expires content=0 />
<meta http-equiv=Cache-Control content=no-cache />
<meta http-equiv=Pragma content=no-cache />
<title>竞品活动上报</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>

<div class="oarcont">
  <div class="rtabcont2">
    <html-el:form action="/KonkaFightActivityManager" >
      <html-el:hidden property="method" value="save" styleId="method" />
	<html-el:hidden property="id" value="${af.map.id}" />
	<html-el:hidden property="user_id" value="${user_id}" />
      <html-el:hidden property="userpass" value="${userpass}" />
	<html-el:hidden property="queryString" styleId="queryString" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtab2">
       
        
       <tr>
			<td class="title_item" align="right" nowrap="nowrap"><font style="color:red">*&nbsp;</font>所属门店：</td>
			<td >
			<html-el:select styleId="store_id" property="store_id" styleClass="webinput" >
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${storeList}" var="cur">
              <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
               </c:forEach>
            </html-el:select>
			</td>
		</tr>
       <tr>
		   <td class="title_item" align="right" nowrap="nowrap"><font color="red">*</font>活动名称：</td>
			<td><html-el:text property="activity_name" styleId="activity_name" maxlength="20">
        </html-el:text>
			</td>
		</tr> 
      <tr>
      <td class="title_item" align="right" nowrap="nowrap"><font color="red">*</font>活动开始时间: </td> 
        <td><fmt:formatDate value="${af.map.begin_date}" var="_s_date" pattern="yyyy-MM-dd" />
			<html-el:text property="begin_date" styleId="begin_date" size="10" maxlength="10" value="${_s_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td> 
       </tr>
       <tr>
      <td class="title_item" align="right" nowrap="nowrap"><font color="red">*</font>活动结束时间 </td> 
        <td>
        	<fmt:formatDate value="${af.map.end_date}" var="_e_date" pattern="yyyy-MM-dd" />
			<html-el:text property="end_date" styleId="end_date" size="10" maxlength="10" value="${_e_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
         
        </td> 
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">竞品品牌: </td> 
        <td><html-el:select styleId="brand_id" property="brand_id" styleClass="webinput" >
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${pdList}" var="cur">
              <html-el:option value="${cur.brand_id}">${cur.brand_name}</html-el:option>
               </c:forEach>
         </html-el:select></td> 
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">主推型号: </td> 
        <td>
        <html-el:text property="model" styleId="model" maxlength="30"></html-el:text>
        </td> 
       </tr>
       <tr>
      	<td class="title_item" align="right" nowrap="nowrap">实际销售量: </td> 
        <td><html-el:text property="sale_num" styleId="sale_num" maxlength="8"></html-el:text></td> 
       </tr>
        <tr>
	   <td class="title_item" align="right" nowrap="nowrap">实际销售额 </td>
	    <td align="left" nowrap="nowrap">
	    	  <html-el:text property="sale_money" styleId="sale_money" maxlength="8"></html-el:text>
	    </td>
	  </tr> 
      <tr>
      	<td class="title_item" align="right" nowrap="nowrap">其他说明: </td> 
        <td><html-el:textarea property="memo" styleId="memo"  rows="3" style="width:50%;">
        </html-el:textarea> </td> 
       </tr>
        <tr>
          <td>&nbsp;&nbsp;</td>
          <td><input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
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
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){	  
	$("#store_id").attr("dataType", "Require").attr("msg", "请选择门店！");
	$("#brand_id").attr("dataType", "Require").attr("msg", "请选择品牌！");
	$("#activity_name").attr("dataType", "Require").attr("msg", "请填写活动名称！");
	$("#begin_date").attr("dataType", "Require").attr("msg", "请选择活动开始时间！");
	$("#end_date").attr("dataType", "Require").attr("msg", "请选择活动结束时间！");
	$("#sale_num").attr("dataType", "Integer").attr("Require","false").attr("msg", "实际销售量只能为整数！");
	$("#sale_money").attr("dataType", "Double").attr("Require","false").attr("msg", "实际销售额只能为数字！");

	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
	        $("#btn_submit").attr("value", "提交...").attr("disabled", "true");
			this.form.submit();
		}
	});
});



function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
