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
    <html-el:form action="/admin/KonkaYjglPlanFp" method="post">
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="method" styleId="method" value="save" />
      <html-el:hidden property="queryString" styleId="queryString" />
      <c:set var="readonly" value="${empty af.map.id ? false : true}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
          <td width="88%" align="left">
          		${af.map.dept_name}
          </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>上样类型：</td>
          <td width="88%" align="left"><html-el:select property="plan_id" styleId="plan_id" styleClass="plan_id" style="width:120px;" >
	          				<html-el:option value="">请选择</html-el:option>
	          				<c:forEach items="${kpList}" var="cur">  
	          					<html-el:option value="${cur.id}">${cur.map.type_name}</html-el:option>
	          				</c:forEach>	
	          			</html-el:select>
	       </td>
        </tr>
        <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">年度：</td>
          <td width="88%" align="left"><span id="year"></span>
          </td>
        </tr>
         <tr>
          <td width="12%" nowrap="nowrap" class="title_item" align="right">额度：</td>
          <td width="88%" align="left">
          	<span id="ed"></span>
          </td>   
        </tr>
         <tr style="display: none;">
          <td width="12%" nowrap="nowrap" class="title_item" align="right">已使用额度：</td>
          <td width="88%" align="left">
          	<span id="used_ed"></span>
          </td>   
        </tr>
        <tr style="display: none;">  
          <td width="12%" nowrap="nowrap" class="title_item" align="right">本页额度：</td>
          <td width="88%" align="left"> <span id="byed"></span>
          </td>
        </tr>
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC">分配：</td>
        </tr>
        <tr>
          <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td width="15%" align="center" nowrap="nowrap"><span style="color:red;">* </span>门店</td>
          			<td width="15%" align="center" nowrap="nowrap"><span style="color:red;">* </span>型号</td>
          			<td width="5%" align="center" nowrap="nowrap" ><span style="color:red;">* </span>数量</td>
          			<td width="10%" align="center" nowrap="nowrap" ><span style="color:red;">* </span>上样时间</td>
          			<td width="10%" align="center" nowrap="nowrap">备注</td>
          			<td width="8%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
          		</tr>
          		<tr class="tr_pd">
          			<td width="17%" align="center" nowrap="nowrap"><html-el:select property="store_id" styleClass="store_id" style="width:150px">
			          		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${storeList}" varStatus="vs">
			          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
			          		</c:forEach>
			          </html-el:select></td>
			        <td align="center" nowrap="nowrap">
			            <input type="hidden" name="goods_id" class="goods_id" id="goods_id" value="" />
			    		<input class="change_class" type="text" id="goods_id_" size="18" maxlength="40" />
			        </td>
          			<td align="center" nowrap="nowrap"><html-el:text property="num" styleId="num" size="4" onfocus="javascript:setOnlyInt(this)" value="1" maxlength="6" styleClass="num" />
          			</td>
          			<td align="center" nowrap="nowrap" style="display:none;"><html-el:text property="price" size="10" value="100" maxlength="10" styleClass="price" /></td>
          			<td align="center" nowrap="nowrap" style="display:none;"><html-el:text property="good_price" size="20" maxlength="30" styleClass="good_price" /></td>
          			<td align="center" nowrap="nowrap"><input  name="sy_date" id="sy_date" class="sy_date"  size="9" maxlength="10" readonly="readonly" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="remark" size="50" maxlength="50" styleClass="remark" styleId="remark" /></td>
          			<td align="center" id="del"><img src="${ctx}/images/x_gray.gif" style="vertical-align:middle;top:0px;" /></td>
          		</tr>
          		<tr id="tr_model" style="display:none;">
          			<td width="17%" align="center" nowrap="nowrap"><html-el:select property="store_id" styleClass="store_id" style="width:150px">
			          		<html-el:option value="">请选择</html-el:option>  
			          		<c:forEach var="cur" items="${storeList}" varStatus="vs">
			          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
			          		</c:forEach>
			          </html-el:select></td>
			         <td align="center" nowrap="nowrap">
			            <input type="hidden" name="goods_id" class="goods_id" id="goods_id" value="" />
			    		<input class="change_class" type="text" id="goods_id_" size="18" maxlength="40" />
			        </td>
          			<td align="center" nowrap="nowrap"><html-el:text property="num" styleId="num" size="4" onfocus="javascript:setOnlyInt(this)" value="0" maxlength="6" styleClass="num" />
          			</td>
          			<td align="center" nowrap="nowrap" style="display:none;"><html-el:text property="price" size="10" value="100" maxlength="10" styleClass="price" /></td>
          			<td align="center" nowrap="nowrap" style="display:none;"><html-el:text property="good_price" size="20" maxlength="30" styleClass="good_price" /></td>
          			<td align="center" nowrap="nowrap"><input  name="sy_date" id="sy_date" class="sy_date"  size="9" maxlength="10" readonly="readonly" onclick="new Calendar(2010, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="remark" size="50"  maxlength="50" styleClass="remark" styleId="remark" /></td>
          			<td align="center" style="cursor:pointer;"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
          		</tr>
          		<tbody id="showAddTrsTbody"></tbody>
          </table></td>
        </tr>
       <tr>
       		<td colspan="2"><div style="height: 80px;"></div></td> 
       </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" />&nbsp;&nbsp;&nbsp;
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

	//定义AJAX获取的型号信息
	var pd_module_data = JSON.parse('${pePdModelJson}');  

	$("#plan_id").attr("datatype", "Require").attr("msg", "请选择额度类型");

	$(".plan_id").change(function(){
		var plan_id = $(this).val();
		setPlanInfo(plan_id);  
	});
	// 计数，处理点击添加以后输入行的ID问题
	var num_count = 1;
	// 搜索--判断和区分IE和非IE浏览器分别进行注册监听
	$("#goods_id_").bind("input propertychange", function() {
		initAutoDiv(0);
	});

	$("#addPdTD").click(function(){
		var tr_pd = $("#tr_model").clone(true).attr("class","tr_pd");
		tr_pd.find("input").eq(0).attr("id", "goods_id" + num_count);
		tr_pd.find("input").eq(1).attr("id", "goods_id_" + num_count);

		num_count++;  

		tr_pd.appendTo($("#showAddTrsTbody")).show();
		var lastTR = $("tr:last", "#showAddTrsTbody");
		
		$(".change_class").bind("input propertychange", function() {
			var index = $(this).attr("id").substring(9);
			initAutoDiv(index);
		});

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
			$(".num").blur();//计算总价格
			if (window.parent.resizeFrameHeight) window.parent.resizeFrameHeight('mainFrame', 3);
	    }).css("cursor", "pointer");
		   
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});


	$(".num").blur(function(){
		var num = $(this).val();
		var price = $(this).parent().parent().children('td').eq(3).children().val();
		if($.trim(num).length > 0 && $.trim(price).length > 0){
			var goods_moneys = num * price;
			var $goods_moneys = $(this).parent().parent().children('td').eq(4).children();  
			$goods_moneys.val(goods_moneys);   
			 var total_money = 0;
			 $(".good_price").each(function(){
				var goods_money = $(this).val();
				if($.trim(goods_money).length > 0 ){
					total_money = parseFloat(total_money) + parseFloat(goods_money);
				}
			 });
			 $("#byed").html(total_money); 
		}	
		  
		 
		
	});
	
	$("#btn_submit").click(function(){
		$(".tr_pd .goods_id").attr("dataType", "Require").attr("msg", "请填写！");
		$(".tr_pd .num").attr("dataType", "Require").attr("msg", "请填写！");
		$(".tr_pd .store_id").attr("dataType", "Require").attr("msg", "请填写！");
		$(".tr_pd .sy_date").attr("dataType", "Require").attr("msg", "请填写！");  
		
		var isSubmit = Validator.Validate(this.form, 3);
		if (isSubmit) {
			$(":button").attr("disabled", "true");
			$(":reset").attr("disabled", "true");
			this.form.submit();
		}
	});

	// 输入关键字搜索
	function initAutoDiv(index){
		// 隐藏下拉框
		if($("#auto_prompt_div")){
	        $("#auto_prompt_div").remove();
		}
		
	 	var id1 = "goods_id_";
	    var id2 = "goods_id";
	    if(index != 0) { 
	    	id1 = "goods_id_" + index;
	        id2 = "goods_id" + index;
	    }
	    
	    // 输入框有改变将型号置为空
	    $("#" + id2).val("");
	    
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
	                        $("#" + id2).val($(this).find("td").eq(0).attr("id"));
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

function setPlanInfo(plan_id){
	$.ajax({
		type: "POST",
		url: "KonkaYjglPlanFp.do",
		data: {method : "ajaxSetPlanInfo", "plan_id": plan_id},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(result){
			if(result.status != 1){  
				$("#year").html("");
				$("#ed").html("");
				$("#used_ed").html("");
			}else if(result.status == 1){
				$("#year").html(result.plan_year);
				$("#ed").html(result.plan_ed);
				$("#used_ed").html(result.used_ed);
			}
		}
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
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "0";
	});
}

//正数
function setOnlyPosNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^(?:\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
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
