<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>活动类型维护</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/multiselect/jquery.multiselect2side.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：活动类型维护</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaSpActivityType" method="post">
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <html-el:hidden property="p_type" styleId="p_type" value="1"/>
      <html-el:hidden property="meeting_type" styleId="meeting_type" value="1"/>
      <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">【必填】 </font>活动类型：</td>
          <td width="88%" align="left"><html-el:text property="hd_type" size="40" maxlength="40" styleId="hd_type" /></td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">【必填】</font>活动类型编码：</td>
          <td width="88%" align="left"><html-el:text property="hd_type_sn" size="40" maxlength="40" styleId="hd_type_sn" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><font color="red">【必填】 </font>开始时间：</td>
          <td>
          <fmt:formatDate value="${af.map.s_date}" pattern="yyyy-MM-dd" var="_s_date" />
		          <input name="s_date" id="s_date" size="12" value="${_s_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'2013-11-01',maxDate:'#F{$dp.$D(\'e_date\')}'})" />
          </td>
          </tr>
        <tr>
          <td nowrap="nowrap" height="28" class="title_item" align="right"><font color="red">【必填】 </font>结束时间：</td>
          <td>
           <fmt:formatDate value="${af.map.e_date}" pattern="yyyy-MM-dd" var="_e_date" />
		          <input name="e_date" id="e_date" size="12" value="${_e_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'s_date\')||\'%y-%M-{%d}\'}'})" /></td>
        </tr>
        <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item">指定品类：</td>
            <td colspan="3">
	              <div id="sel">
				      <select name="md_name_pls_" id="md_name_pls" multiple="multiple" size="8">
	                  		 <option value="2D" title=""><c:out value="2D" /></option>
	                  		<option value="3D" title=""><c:out value="3D" /></option>
	                  		<option value="安卓" title=""><c:out value="安卓" /></option>
	                  		<option value="网络" title=""><c:out value="网络" /></option>
	                  		<option value="8核" title=""><c:out value="8核" /></option>
	                  		<option value="大板" title=""><c:out value="大板" /></option>
	                  		<option value="4K" title=""><c:out value="4K" /></option>
	                  		<option value="易TV" title=""><c:out value="易TV" /></option>
	                  		<option value="9500系列" title=""><c:out value="9500系列" /></option>
	                  		<option value="9600系列" title=""><c:out value="9600系列" /></option>
	                  		<option value="9200系列" title=""><c:out value="9200系列" /></option>
	                  		<option value="8300系列" title=""><c:out value="8300系列" /></option>
	                  		<option value="8100系列" title=""><c:out value="8100系列" /></option>
	                  		<option value="5600系列" title=""><c:out value="5600系列" /></option>
	                  		<option value="5500系列" title=""><c:out value="5500系列" /></option>
	                  		<option value="1200系列" title=""><c:out value="1200系列" /></option>
	                  		<option value="2200系列" title=""><c:out value="2200系列" /></option>
	                  		<option value="1100系列" title=""><c:out value="1100系列" /></option>
	                  		<option value="3300系列" title=""><c:out value="3300系列" /></option>
	                  		<option value="9100系列" title=""><c:out value="9100系列" /></option>
	                  		<option value="9000系列" title=""><c:out value="9000系列" /></option>
	                  		<option value="8200系列" title=""><c:out value="8200系列" /></option>
	                  		<option value="6100系列" title=""><c:out value="6100系列" /></option>
	                  		<option value="5200系列" title=""><c:out value="5200系列" /></option>
	                  		<option value="5100系列" title=""><c:out value="5100系列" /></option>
	                  		<option value="3700系列" title=""><c:out value="3700系列" /></option>
	                  		<option value="3500系列" title=""><c:out value="3500系列" /></option>
	                  		<option value="2800系列" title=""><c:out value="2800系列" /></option>
	                  		<option value="5300系列" title=""><c:out value="5300系列" /></option>
	                  		<option value="5400系列" title=""><c:out value="5400系列" /></option>
	                  		<option value="3560系列" title=""><c:out value="3560系列" /></option>
	                  		<option value="1800系列" title=""><c:out value="1800系列" /></option>
	                  		<option value="9800系列" title=""><c:out value="9800系列" /></option>
	                  		<option value="6680系列" title=""><c:out value="6680系列" /></option>
	                  		<option value="8800系列" title=""><c:out value="8800系列" /></option>
				      </select>
				      
				      <html-el:hidden property="md_name_pls" styleId="md_name_plsHidden" />
			      </div>
            </td>
          </tr>
        <tr>
            <td height="28" width="15%" nowrap="nowrap" class="title_item">指定机型：</td>
            <td colspan="3">
	              <div id="sel">
				      <select name="md_name_jxs_" id="md_name_jxs" multiple="multiple" size="8">
				      	<c:forEach var="cur" items="${pePdModelList}">
	                  		<option value="${cur.md_name}" title=""><c:out value="${fn:escapeXml(cur.md_name)}" /></option>
	                	</c:forEach>
				      </select>
				      
				      <html-el:hidden property="md_name_jxs" styleId="md_name_jxsHidden" />
			      </div>
            </td>
          </tr>
        <tr>
          <td>&nbsp;</td>
         <td><label>
              <input class="but4" type="button" name="Submit4" id="btn_submit" value="提交" />
              <input class="but5" type="button" name="Submit5" id="btn_back"value="返回" onclick="history.back();return false;" />
            </label></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/multiselect/jquery.multiselect2sideYwy.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	
	var isRomveYwy = '${isRomveYwy}';	
	
	//处理品类多选
	if(isRomveYwy.length > 0){//修改
		$("#md_name_jxs").multiselect2side({
		  selectedPosition: 'right',
		    moveOptions: false,
			labelsx: '待选区',
			labeldx: '已选区',
			isAddTips: isRomveYwy,
			isRomveYwy: isRomveYwy   //如果该用户对应是业务员角色；false：表示不能移出，true:表示可以移出
	   	});
	} else {//新增
		$("#md_name_jxs").multiselect2side({
			  selectedPosition: 'right',
			    moveOptions: false,
				labelsx: '待选区',
				labeldx: '已选区'
		   	});
	}

	
   	//回显品类
   	if ("" != "${mdTypeJxList}") {
	   //	$("#roleIds_ms2side__sx").val("");//2013-06-27，解决总是选中【系统管理员】的bug
	   	var arrs = new Array();
   		<c:forEach var="cur" items="${mdTypeJxList}">
			//alert("${cur.md_name}");
			$("#md_name_jxs_ms2side__sx option[value='${cur.md_name}']").attr("selected", true).dblclick();
			arrs.push("${cur.md_name}");
		</c:forEach>
		$("#md_name_jxsHidden").val(arrs);
	}
   	
   	
 var isRomveYwy2 = '${isRomveYwy}';	
	
	//处理机型多选
	if(isRomveYwy2.length > 0){//修改
		$("#md_name_pls").multiselect2side({
		  selectedPosition: 'right',
		    moveOptions: false,
			labelsx: '待选区',
			labeldx: '已选区',
			isAddTips: isRomveYwy2,
			isRomveYwy: isRomveYwy2  //如果该用户对应是业务员角色；false：表示不能移出，true:表示可以移出
	   	});
	} else {//新增
		$("#md_name_pls").multiselect2side({
			  selectedPosition: 'right',
			    moveOptions: false,
				labelsx: '待选区',
				labeldx: '已选区'
		   	});
	} 

	
   	//回显机型
   	if ("" != "${mdTypePlList}") {
	   	var arrs = new Array();
   		<c:forEach var="cur" items="${mdTypePlList}">
			//alert("${cur.md_name}");
			$("#md_name_pls_ms2side__sx option[value='${cur.md_name}']").attr("selected", true).dblclick();
			arrs.push("${cur.md_name}");
		</c:forEach>
		$("#md_name_plsHidden").val(arrs);
	}
	
	$("#hd_type").attr("dataType" , "Require").attr("msg" , "请填写活动类型.");
	$("#hd_type_sn").attr("dataType" , "Require").attr("msg" , "请填写活动类型编码.");
	$("#s_date").attr("dataType" , "Require").attr("msg" , "请选择活动开始时间.");
	$("#e_date").attr("dataType" , "Require").attr("msg" , "请选择活动结束时间.");
	$("#btn_submit").click(function(){

		var arrs_jxs = new Array();
		var obj_jxs = document.forms[0].md_name_jxs_ms2side__dx;
		for(var i = 0; i < obj_jxs.length; i++){
			arrs_jxs.push(obj_jxs.options[i].value);
		}
		//var roleIds = arrs.join(",");
		$("#md_name_jxsHidden").val(arrs_jxs);
		
		var arrs_pls = new Array();
		var obj_pls = document.forms[0].md_name_pls_ms2side__dx;
		for(var i = 0; i < obj_pls.length; i++){
			arrs_pls.push(obj_pls.options[i].value);
		}
		//var roleIds = arrs.join(",");
		$("#md_name_plsHidden").val(arrs_pls); 
		//处理、验证职务多选，end
		
		if(Validator.Validate(this.form, 3)){
			 $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	         $("#btn_back").attr("disabled", "true");
			 this.form.submit();
		}
	});
});

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
