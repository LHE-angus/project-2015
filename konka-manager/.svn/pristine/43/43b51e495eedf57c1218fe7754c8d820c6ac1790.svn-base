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
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaR3StoreShow" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司：</td>
          <td width="88%" align="left">
           <c:if test="${empty af.map.id}">
             <html-el:select property="dept_id" styleId="dept_id">
		       	<html-el:option value="">-请选择-</html-el:option>
		       	<c:forEach items="${konkaDeptList}" var="cur">
		       		<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
		       	</c:forEach>
	         </html-el:select>
	         <html-el:hidden property="dept_name" styleId="dept_name" />
	        
           </c:if>
            <c:if test="${not empty af.map.id}">
             	${af.map.dept_name}
             </c:if>
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>门店名称：</td>
          <td width="88%" align="left">
            <c:if test="${empty af.map.id}">
              <html-el:select property="store_id_name" styleId="store_id_name">
		       	<html-el:option value="">-请选择-</html-el:option>
	         </html-el:select>
	         <html-el:hidden property="store_id" styleId="store_id" />
	         <html-el:hidden property="store_name" styleId="store_name" />
           </c:if>
            <c:if test="${not empty af.map.id}">
             	${af.map.store_name}
             </c:if>
          </td>
        </tr>
        <c:if test="${not empty af.map.id}">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>门店ID：</td>
          <td width="88%" align="left">
            <c:if test="${empty af.map.id}">
              <html-el:text property="task_money" styleId="task_money" size="12" maxlength="12"/>
           </c:if>
            <c:if test="${not empty af.map.id}">
             	${af.map.store_id}
             </c:if>
          </td>
        </tr>
        </c:if>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">年：</td>
          <td width="88%" align="left"><html-el:text property="year" styleId="year" size="4" maxlength="4"/>
          </td>
        </tr>
        <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right">月：</td>
            <td width="88%" align="left"><html-el:text property="month" styleId="month" size="2" maxlength="2"/>
            </td>
          </tr>
          
          
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>类型：</td>
          <td width="88%" align="left">
               <html-el:select property="task_name" styleId="task_name" style="width:120px;">
			       <html-el:option value="">请选择拜访类型</html-el:option>
	      		   <html-el:option value="演示设备">演示设备</html-el:option>
	      		   <html-el:option value="展台展柜">展台展柜</html-el:option>
        	   </html-el:select>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>品类名称：</td>
          <td width="88%" align="left">
              <html-el:select property="category_name" styleId="category_name">
              	 <html-el:option value="">-请选择-</html-el:option>
	         </html-el:select>
	         <html-el:hidden property="category_name" styleId="category_name1" />
          </td>
        </tr>
         <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right">尺寸/规格：</td>
            <td width="88%" align="left"><html-el:text property="size" styleId="size" size="12" maxlength="12"/>
            </td>
          </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>数量：</td>
          <td width="88%" align="left"><html-el:text property="num" styleId="num" size="12" maxlength="12"/></td>
          </tr>
          <tr >
            <td width="12%" nowrap="nowrap" class="title_item" align="right">金额：</td>
            <td width="88%" align="left"><html-el:text property="task_money" styleId="task_money" size="12" maxlength="12"/>
            </td>
          </tr>
           
         
           <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td width="88%" align="left">
          <html-el:textarea property="remark" styleId="remark" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();return false;" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#dept_id").attr("dataType", "Require").attr("msg", "分公司不能为空");
	$("#store_id_name").attr("dataType", "Require").attr("msg", "门店名称不能为空");
	$("#task_name").attr("dataType", "Require").attr("msg", "类型不能为空");
	$("#category_name").attr("dataType", "Require").attr("msg", "品类名称不能为空");
	$("#num").attr("dataType", "Require").attr("msg", "数量不能为空");
	
	$("#task_money" ).attr("focus",setOnlyNum);
	$("#year" ).focus(function(){setOnlyInt(this);});
	$("#month" ).focus(function(){setOnlyInt(this);});

	$("#remark").textbox({
		maxLength: 450,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	
	
	$("#store_id_name").change(function() {
		var store_id = $(this).val();
		var store_name=$(this).find("option:selected").text();
		$("#store_id").val(store_id);
		$("#store_name").val(store_name);
	});
	
	function getcategory(obj) {
		var task_name = obj.val();
		var options="";
		$("#category_name").empty();
		options+="<option value=''>-请选择品类-</option>";
		if(task_name=='展台展柜'){
				 options+="<option value='展台'>展台</option>"; 
				 options+="<option value='展柜'>展柜</option>"; 
		}else if(task_name=='演示设备'){
			 options+="<option value='普通码流仪'>普通码流仪</option>"; 
			 options+="<option value='K4码流仪'>K4码流仪</option>"; 
			 options+="<option value='网线'>网线</option>"; 
			 options+="<option value='分配器'>分配器</option>"; 
			 options+="<option value='有线电视'>有线电视</option>"; 
			 options+="<option value='线材(HDMI)'>线材(HDMI)</option>"; 
			 options+="<option value='其它'>其它</option>"; 
		}
		$("#category_name").append(options);
		var category_name=$("#category_name1").val();
		$("#category_name option[value='"+category_name+"']").attr("selected", "selected"); 
	}
	var obj1 =$("#task_name");
	getcategory.call(null,obj1);
	
	$("#task_name").change(function() {
		var obj=$(this);
		getcategory(obj);
	});
	
	//Ajax列表查询
	$("#dept_id").change(function() {
		var dept_id = $(this).val();
		var dept_name=$(this).find("option:selected").text();
		$("#dept_name").val(dept_name);
		$.ajax({
				type : "POST",
				url : "${ctx}/manager/admin/KonkaR3StoreShow.do",
				data : {
					"method" : "ajaxKonkaR3StoreList",
					"dept_id" : dept_id
				},
				dataType : "json",
				sync : true, // jsonp不支持同步
				error : function(xhr,
						ajaxOptions,
						thrownError) {
					alert("数据加载请求失败【"
							+ xhr.statusText
							+ ","
							+ xhr.responseText
							+ ","
							+ xhr.status
							+ ","
							+ thrownError
							+ "】！");
				},
				success : function(data) {
					var options="";
					$("#store_id_name").empty();
					if(data.length>0){
					     options+="<option value=''>-请选择门店-</option>";
						 for(var i=0;i<data.length;i++){
							 var md=data[i];
							 options+="<option value="+md.store_id+">"+md.store_name+"</option>"; 
						  }
					   $("#store_id_name").append(options);
					}else{
					   $("#store_id_name").append("<option value=''>-请选择门店-</option>");
					}
				}
		  });
	});
	
	$("#btn_submit").click(function(){
		
		var month = $('#month').val();
		var reg = /^0[1-9]|1[0-2]|[1-9]$/;
		if(!reg.test(month)){
			alert("请填写正确的月份！");
			return false;
		}

		var task_money = $('#task_money').val();
		if(task_money.length>12){
			alert("任务金额过大，请重新填写！");
			return false;
		}
		
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});
	
});


function setOnlyInt(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}


function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
