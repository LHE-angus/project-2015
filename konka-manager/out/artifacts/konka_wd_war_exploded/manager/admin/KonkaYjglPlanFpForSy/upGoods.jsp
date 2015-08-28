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
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：进销存 > 销售管理 > 零售数据 > 计划性上样</td>
      </tr>
    </table>
  </div>
 <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaYjglPlanFpForSy" method="post" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="saveUpGoods" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <!--<tr>
	    <td colspan="2" align="right" nowrap="nowrap" class="title_item"><a href="${ctx}/manager/admin/KonkaMobileTestDataInput.do?method=list&user_id=${user_id}">历史记录</a></td>
       </tr>
        -->
        <tr>
	    <td width="25%" align="center" nowrap="nowrap" class="title_item">门店</td>
	    <td width="75%" align="left" nowrap="nowrap">
	             <html-el:text property="_store_name" disabled="true" styleId="_store_name" value="${af.map.store_name}" maxlength="50" />
	             <html-el:hidden property="store_name" styleId="store_name" value="${af.map.store_name}" />
	    	     <html-el:hidden property="shop_id" styleId="shop_id"/>
	    </td>
       </tr>
       <tr>
    		<td align="center" nowrap="nowrap" class="title_item">型号</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:hidden styleClass="title_item" property="pd_id" styleId="pd_id" value="${af.map.pd_id}" />
    		<html-el:hidden property="pd_name" styleId="pd_name"  />
    		<html-el:text property="_pd_name" disabled="true" styleId="_pd_name" maxlength="50" value="${af.map.pd_name}"/>
    		</td>
  	   </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="center">数量</td>
          <td width="88%" align="left">
          <html-el:text property="_num" size="8" disabled="true" maxlength="4" styleId="_num" value="1"  onfocus="javascript:setOnlyInt(this);" />
          <html-el:hidden property="num"  styleId="num" value="1"  onfocus="javascript:setOnlyInt(this);" />&nbsp;</td>
        </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">价格</td>
    <td align="left" nowrap="nowrap"><html-el:text property="price" size="20" maxlength="8" styleId="price" /></td>
  </tr>
   <tr >
          <td width="12%" nowrap="nowrap" class="title_item" align="center"><font color="red">* </font>上样时间：</td>
          <td width="88%" align="left">
          
          
          	<fmt:formatDate value="${af.map.sy_date}" pattern="yyyy-MM-dd" var="_sy_date" />
			<html-el:text property="sy_date" styleId="sy_date"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期"  value="${_sy_date}"/>
				
          </td>
   </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">备注:</td>  
    <td align="left" nowrap="nowrap">
    	<html-el:textarea property="memo" styleId="memo" cols="5" style="width:450px;height:70px;" />
          	<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">串码:</td>    
    <td align="left" nowrap="nowrap"><html-el:text property="code" size="60" maxlength="100" styleId="code" /></td>  
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">上样照片:</td>
    <td align="left" nowrap="nowrap">
           <table>
            <tr><td><input type="text" id="fronttext" disabled="disabled" name="fronttext" value="正面"/></td><td><html-el:file property="front" size="20" maxlength="8" styleId="front" /></td></tr>
            <tr><td><input type="text" id="backtext" disabled="disabled" name="backtext" value="背面"/></td><td><html-el:file property="back" size="20" maxlength="8" styleId="back" /></td></tr>
            <tr><td><input type="text" id="sidetext" disabled="disabled" name="sidetext" value="侧面"/></td><td><html-el:file property="side" size="20" maxlength="8" styleId="side" /></td></tr>
         </table>
          <div id="fj"></div>
    </td>
  </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />
          		<c:if test="${not empty af.map.id}">
          		<html-el:button property="" value="返 回" styleClass="but4" styleId="btn_back" onclick="history.back();return false;" />
          		</c:if>
          </td>
      
        </tr>
      </table>
    </html-el:form>
  </div>
</div>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.imgAddShow.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#sy_date").attr("datatype", "Require").attr("msg", "请填写上样时间");
	$("#front").attr("dataType","Filter").attr("require",false).attr("accept","bmp, gif, jpeg, jpg, png").attr("msg","请上传格式为(bmp, gif, jpeg, jpg, png)的图片");
	$("#back").attr("dataType","Filter").attr("require",false).attr("accept","bmp, gif, jpeg, jpg, png").attr("msg","请上传格式为(bmp, gif, jpeg, jpg, png)的图片");
	$("#side").attr("dataType","Filter").attr("require",false).attr("accept","bmp, gif, jpeg, jpg, png").attr("msg","请上传格式为(bmp, gif, jpeg, jpg, png)的 图片");   
	   
	$("#price").keypress(function(){//单价 
		var price = $("#price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price").val(0);
		}
	}).keyup(function(){
		var price = $("#price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price").val(0);
		}
	}).blur(function(){
		var price = $("#price").val();
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			$("#price").val(0);
		}
	});	

	//定义AJAX获取的型号信息
	var pd_module_data = eval('${pePdModelJson}');//JSON.parse();
	
	
	// 搜索--判断和区分IE和非IE浏览器分别进行注册监听
	$("#pd_id_1").bind("input propertychange", function() {
		initAutoDiv();
	});
	

	$("#memo").textbox({
		maxLength: 100,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});

	//附件
	 var id=$("#id").val();
	 if (id) {
		 $("#fj").imgshow({
				ctx:"${ctx}",
				data:jQuery.parseJSON('${fj_paths}')
		       });
	  }else{
		  $("#fj").imgshow();
	  }
	 
	$("#btn_submit").click(function(){
		var code = $("#code").val();
		if(code.indexOf("，") > -1 || code.indexOf(' ') > -1){
			alert("串码中不能含有空格或者中文逗号");
			return false;
		}  
		if(code.substring(code.length-1,code.length)==","){
			alert("串码中不能以逗号结尾");
			return false;  
		}
		if(code!=""){
			var strs= new Array(); //定义一数组
			strs = code.split(",");
			if(strs.length!=1){
				alert("1台数量，请填写"+count+"个串码！");      
				return false;
			} 
		}

		var up_date = $("#sy_date").val();
		var now_date = '${date}';
		
		var _up_date = up_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");
		var _now_date = now_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");	

		if(_up_date >_now_date){  
			alert("上样时间不能大于今天");   
			return false; 
		}     

		
		
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			 
			$(":button").attr("disabled", "true");
			this.form.submit();
		}
	});

	//输入关键字搜索
	function initAutoDiv(index){
			// 隐藏下拉框
			if($("#auto_prompt_div")){
		        $("#auto_prompt_div").remove();
			}
			
		 	var id1 = "pd_id_1";
		    // 输入框有改变将型号置为空
		   $("#pd_id").val("");
		    
			if($.trim($("#" + id1).val()).length >= 2){
				var val = $.trim($("#" + id1).val());
				var count = 0;
				for(var i = 0; i < pd_module_data.length; i++){
					var id = pd_module_data[i].id;
					var name = pd_module_data[i].name;
					if(name.toLowerCase().indexOf(val.toLowerCase()) != -1)
						count++;
				}

				var top = $("#" + id1).offset().top;
		        var left = $("#" + id1).offset().left;
		        var width = $("#" + id1).width();
		        if(width <= 90) width = 90; // 强制宽度最小90
		        var auto_prompt_div = $("<div />").width(width).css({"height":"200px", "overflow-y":"auto", "overflow-x":"hidden", "position":"absolute", "backgroundColor":"white", "border":"1px solid #1C86EE", "left" : left}).css("top", top + $("#" + id1).height() + 6).attr("id", "auto_prompt_div");
		        var table = $("<table width='100%' id=\"div_table\" />").attr("cellpadding", "0").attr("cellspacing", "0");
		        
				// 满足条件控制在30个内，如果超过30个则不显示
				if(count != 0){
					for(var i = 0; i < pd_module_data.length; i++){
						var id = pd_module_data[i].id;
						var name = pd_module_data[i].name;
						if(name.toLowerCase().indexOf(val.toLowerCase()) != -1){
		                    var tr = $("<tr />").css("cursor", "pointer").attr("class", "tr_value").mouseout(function(){
		                        $(this).css("backgroundColor", "white").css("color", "black");
		                    }).mouseover(function(){
		                        $(this).css("backgroundColor", "#1C86EE").css("color", "white");
		                    }).click(function(){
		                        $("#" + id1).val($(this).find("td").eq(0).html());
		                        $("#pd_id").val($(this).find("td").eq(0).attr("id"));
		                	    $("#auto_prompt_div").remove();
		                    });
		                    var td = $("<td class=\"td_class\" />").html(name).css("fontSize", "12px").css("margin", "5px 5px 5px 5px").css("padding-left", "6px").attr("align", "left").attr("id", id);
		                    tr.append(td);
		                    table.append(tr);
					    }
					}
				} else {
		            var tr = $("<tr />").css("cursor", "pointer").attr("class", "tr_value").mouseout(function(){
		                $(this).css("backgroundColor", "white").css("color", "black");
		            }).mouseover(function(){
		                $(this).css("backgroundColor", "#1C86EE").css("color", "white");
		            });
		            var td = $("<td />").html("提示，您搜索的型号“" + val + "”，共检索到 " + count + " 条数据，请精确搜索条件！").css("fontSize", "12px").css("margin", "5px 5px 5px 5px").css("padding-left", "6px").attr("align", "left");
		            tr.append(td);
		            table.append(tr);
				}

				auto_prompt_div.append(table);
		        $(document.body).append(auto_prompt_div);
			}
		}
});




//正则表达式：只能输入数字
function setOnlyNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value)) obj.value = "";
	});
}


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
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "1";
	});
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
