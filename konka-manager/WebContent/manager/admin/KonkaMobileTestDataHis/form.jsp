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
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
 <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaMobileTestDataHis" method="post" enctype="multipart/form-data">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
       <c:set value="${not empty af.map.id ? true:false}" var="disabled"> </c:set> 
      <c:set value="${not empty af.map.plan_fp_id ? true:false}" var="readyonly"> </c:set> 
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
	    <td colspan="2" align="right" nowrap="nowrap" class="title_item"></td> 
       </tr>
        <tr>
	    <td width="25%" align="center" nowrap="nowrap" class="title_item"><span style="color:red">*</span>门店</td>
	    <td width="75%" align="left" nowrap="nowrap">
	    	<html-el:select property="store_id" styleId="store_id" disabled="${readyonly}">
	    		<c:forEach items="${storeList}" var="cur">
    		
	    		<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	    
	    		</c:forEach>
	    	</html-el:select>
	    </td>
       </tr>
       <tr>
    		<td align="center" nowrap="nowrap" class="title_item"><font color="red">*</font>型号</td>
    		<td align="left" nowrap="nowrap">
    		<html-el:hidden styleClass="title_item" property="pd_id" styleId="pd_id" value="${af.map.pd_id}" />
    		<html-el:text property="pd_id_1" styleId="pd_id_1" maxlength="50" readonly="${readyonly}" /></td>
  	   </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="center"><font color="red">*</font>数量</td>
          <td width="88%" align="left"><html-el:text property="count" size="8" maxlength="4" styleId="count"   onfocus="javascript:setOnlyInt(this);" readonly="${readyonly}" />&nbsp;</td>
        </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">价格</td>
    <td align="left" nowrap="nowrap"><html-el:text property="price" size="20" maxlength="8" styleId="price" /></td>
  </tr>
   <tr >  
          <td width="12%" nowrap="nowrap" class="title_item" align="center"><font color="red">* </font>上样时间：</td>  
          <td width="88%" align="left">
          	<fmt:formatDate value="${af.map.up_date}" pattern="yyyy-MM-dd" var="_up_date" /> 
				<html-el:text property="up_date" styleId="up_date" size="10" maxlength="10" readonly="true"  disabled="${disabled}" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期"  value="${_up_date}"/> 
          </td>
   </tr>
     <tr >
          <td width="12%" nowrap="nowrap" class="title_item" align="center">下样时间：</td>
          <td width="88%" align="left">
          	<fmt:formatDate value="${af.map.down_date}" pattern="yyyy-MM-dd" var="_down_date" />
			<html-el:text property="down_date" styleId="down_date" size="10" maxlength="10" readonly="true"  onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期"  value="${_down_date}"/>
          </td>
   </tr>
   <tr> 
	    <td width="25%" align="center" nowrap="nowrap" class="title_item">下样原因:</td>
	    <td width="75%" align="left" nowrap="nowrap">
	    	<html-el:select property="down_cause" styleId="down_cause">
	    	<html-el:option value="">-请选择-</html-el:option>
	    	<html-el:option value="0">库存不足</html-el:option>
	    	<html-el:option value="1">门店调拨</html-el:option>
	    	</html-el:select>  
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
    <td align="left" nowrap="nowrap"><html-el:text property="code" size="60" maxlength="100" styleId="code" /><span>一个串码，直接填写即可，多个串码请用英文逗号隔开，如333,444</span></td>
  </tr>
  <tr>
    <td align="center" nowrap="nowrap" class="title_item">上样照片:</td>
    <td align="left" nowrap="nowrap">
       <c:if test="${empty af.map.id}">
           <table>
            <tr><td><input type="text" id="fronttext" disabled="disabled" name="fronttext" value="正面"/></td><td><html-el:file property="front" size="20" maxlength="8" styleId="front" /></td></tr>
            <tr><td><input type="text" id="backtext" disabled="disabled" name="backtext" value="背面"/></td><td><html-el:file property="back" size="20" maxlength="8" styleId="back" /></td></tr>
            <tr><td><input type="text" id="sidetext" disabled="disabled" name="sidetext" value="侧面"/></td><td><html-el:file property="side" size="20" maxlength="8" styleId="side" /></td></tr>
         </table>
       </c:if>
       <c:if test="${not empty af.map.id}">
           <table>
            <tr><td><input type="text" id="fronttext" disabled="disabled" name="fronttext" value="正面"/></td><td><html-el:file property="front" size="20" maxlength="8" styleId="front" /></td><td><a href="${ctx}/${af.map.front.save_path}" target="_blank" >${af.map.front.file_name}</a></td></tr> 
            <tr><td><input type="text" id="backtext" disabled="disabled" name="backtext" value="背面"/></td><td><html-el:file property="back" size="20" maxlength="8" styleId="back" /></td><td><a href="${ctx}/${af.map.back.save_path}" target="_blank" >${af.map.back.file_name}</a></td></tr> 
            <tr><td><input type="text" id="sidetext" disabled="disabled" name="sidetext" value="侧面"/></td><td><html-el:file property="side" size="20" maxlength="8" styleId="side" /></td><td><a href="${ctx}/${af.map.side.save_path}" target="_blank" >${af.map.side.file_name}</a></td></tr> 
         </table>
       </c:if>
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

	$("#store_id").attr("datatype", "Require").attr("msg", "请选择门店");
	$("#pd_id").attr("datatype", "Require").attr("msg", "请输入型号");
	$("#count").attr("datatype", "Number").attr("msg", "请填写数量");
	$("#up_date").attr("datatype", "Require").attr("msg", "请填写上样时间");
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
			resizeFrameHeight(); 
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
		resizeFrameHeight(); 
	});

	//附件
	 var id=$("#id").val();
	 if (id) {
		 $("#fj").imgshow({
				ctx:"${ctx}",
				delUrl:"${ctx}/manager/admin/KonkaSpActivityManager.do?method=deleteFile1",
				data:jQuery.parseJSON('${fj_paths}')
		       });
	  }else{
		  $("#fj").imgshow();
		  window.parent.resizeFrameHeight('mainFrame', 3);  
	  }
	 
	$("#btn_submit").click(function(){
		
		var count = $("#count").val();
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
			if(count!=strs.length){
				alert(count+"台数量，请填写"+count+"个串码！"); 
				return false;
			} 
			//for(var i=0;i<strs.length;i++){
				//if(strs[i]==""){
					//alert("不能包含空条码!"); 
				//}
			//}

		}
		var up_date = $("#up_date").val();
		var now_date = '${date}';
		var down_date = $("#down_date").val();
		
		var _up_date = up_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");
		var _now_date = now_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");	
		var _down_date = down_date.replace(/-/g,"").replace(/:/g,"").replace(/ /g,"");

		<c:if test="${empty af.map.id}">   
		if(_up_date >_now_date){  
			alert("上样时间不能大于今天");   
			return false; 
		}
	   </c:if> 

	   if(_down_date!=""){
		  if(_down_date>_now_date){
			  alert("下样时间不能大于今天");     
				return false; 
		  }	

	   }
	   
		
		if(_down_date!=""&&_up_date > _down_date){
			alert("下样时间不能小于上样时间"); 
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

//自动改变外层框架的大小
function resizeFrameHeight(offset, min_height) {
		// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
		$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
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
