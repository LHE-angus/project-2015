<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request['contextPath']}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FusionCharts访问URL组合</title>
<link type="text/css" rel="stylesheet" href="${ctx}/${static_files_dir}/default.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/${static_files_dir}/colorpicker/js_color_picker_v2.css" media="screen" />
<style type="text/css">
.items_th {
	widows:20%;
	text-align:right;
	height:29px;
}
td.items_td {
	text-align:left;
	padding-left:3px;
}
.fontred12px {
	color:#f00;
	font-size:12px;
	font-weight:bold;
}
.p_class {
	height:25px;
}
</style>
</head>
<body>
<table cellpadding="3" cellspacing="0" border="0" width="100%" class="report_tab">
  <tr>
    <td class="items_th">JSON数据来源URL[<span class="fontred12px">*</span>]：</td>
    <td class="items_td">
      <form action="#" name="temp_form" id="temp_form"> 
    	<input type="text" name="dataUrl" id="dataUrl" value="${www_url}?chartMethod=demoJson" style="width:500px;text-align:left;" />
    	<input type="button" name="button" value="提示，输入地址以后点击加载“报表类型数据”" id="ajax_get_thead" style="margin-left:10px;cursor:pointer;" />
   	 	<input type="text" style="display:none;" name="text2013710" />
   	  </form>
    </td>
  </tr>
  <tr>
    <td class="items_th">报表类型[<span class="fontred12px">*</span>]：</td>
    <td class="items_td"><select name="type" id="type">
        <option value="">...请选择...</option>
        <option value="h">柱状图</option>
        <option value="l">曲线图</option>
        <option value="p">饼状图</option>
        <option value="hl">柱状、曲线图</option>
      </select></td>
  </tr>
  <tr id="colspan_1" style="display:none;">
    <td class="items_th">需要展示数据的列[<span class="fontred12px">*</span>]：</td>
    <td class="items_td"><table width="100%" cellpadding="0" cellspacing="0" border="0" class="report_tab">
        <tr class="colspan_1_tr">
          <td style="text-align:left;"><select name="select_cols" class="class_select">
              <option value="">--请选择列--</option>
              <c:forEach items="${thead_cols}" var="cur">
                <option value="${cur.value}">${cur.key}</option>
              </c:forEach>
            </select>
            <img id="add_tr_1" src="${ctx}/${static_files_dir}/+.gif" style="vertical-align:middle;cursor:pointer;" /></td>
        </tr>
        <tr id="colspan_1_id" style="display:none;" class="colspan_1_tr">
          <td style="text-align:left;"><select name="select_cols" class="class_select">
              <option value="">--请选择列--</option>
              <c:forEach items="${thead_cols}" var="cur">
                <option value="${cur.value}">${cur.key}</option>
              </c:forEach>
            </select>
            <img src="${ctx}/${static_files_dir}/x.gif" style="vertical-align:middle;cursor:pointer;" class="tr_remove"/></td>
        </tr>
        <tbody id="colspan_1_tbody" style="border:none;">
        </tbody>
      </table>
     </td>
  </tr>
  <tr id="colspan_2" style="display:none;">
    <td class="items_th">需要展示数据的列[<span class="fontred12px">*</span>]：</td>
    <td class="items_td"><table width="100%" cellpadding="0" cellspacing="0" border="0" class="report_tab">
        <tr class="colspan_2_tr">
          <td style="text-align:left;"><select name="select_cols" class="class_select">
              <option value="">--请选择列--</option>
              <c:forEach items="${thead_cols}" var="cur">
                <option value="${cur.value}">${cur.key}</option>
              </c:forEach>
            </select>
            <select name="colspan_2_type">
              <option value="p">主柱状</option>
              <option value="s">次曲线</option>
            </select>
            <img id="add_tr_2" src="${ctx}/${static_files_dir}/+.gif" style="vertical-align:middle;cursor:pointer;" /></td>
        </tr>
        <tr id="colspan_2_id" style="display:none;" class="colspan_2_tr">
          <td style="text-align:left;"><select name="select_cols" class="class_select">
              <option value="">--请选择列--</option>
              <c:forEach items="${thead_cols}" var="cur">
                <option value="${cur.value}">${cur.key}</option>
              </c:forEach>
            </select>
            <select name="colspan_2_type">
              <option value="p">主柱状</option>
              <option value="s">次曲线</option>
            </select>
            <img src="${ctx}/${static_files_dir}/x.gif" style="vertical-align:middle;cursor:pointer;" class="tr_remove"/></td>
        </tr>
        <tbody id="colspan_2_tbody" style="border:none;">
        </tbody>
      </table>
    </td>
  </tr>
  <tr>
    <td class="items_th">报表宽度：</td>
    <td class="items_td"><input type="text" name="width" id="width" value="600" style="width:60px;" maxlength="3" onfocus="javascript:setOnlyNum(this);" />
      &nbsp;PX</td>
  </tr>
  <tr>
    <td class="items_th">报表高度：</td>
    <td class="items_td"><input type="text" name="height" id="height" value="400" style="width:60px;" maxlength="3" onfocus="javascript:setOnlyNum(this);" />
      &nbsp;PX</td>
  </tr>
  <tr>
    <td class="items_th">预警线、提示线：</td>
    <td class="items_td"><table width="100%" cellpadding="0" cellspacing="0" border="0" class="report_tab">
        <tr class="trendlines_tr">
          <td width="520" nowrap="nowrap"> 预警、提示名称
            <input type="text" style="width:80px;margin:0 3px;" />
            预警、提示数值
            <input type="text" style="width:50px;margin:0 3px;" onfocus="javascript:setOnlyNum(this);" />
            预警、提示横线颜色
            <input type="text" style="width:60px;margin:0 3px;" maxlength="7" readonly="readonly" /></td>
          <td width="10" nowrap="nowrap"><input type="button" style="cursor:pointer;" value="选择颜色" onclick="showColorPicker(this, $('td:first', $(this).parent().parent()).children().eq(2)[0])" /></td>
          <td width="20" nowrap="nowrap"><select>
              <option value="0">...预警线的颜色，不作为“柱状”的颜色...</option>
              <option value="2">...预警线的颜色，作为大于预警值“柱状”的颜色（正数）...</option>
              <option value="1">...预警线的颜色，作为小于预警值“柱状”的颜色（正数）...</option>
              <option value="-1">...预警线的颜色，作为大于预警值“柱状”的颜色（负数）...</option>
              <option value="-2">...预警线的颜色，作为小于预警值“柱状”的颜色（负数）...</option>
            </select></td>
          <td style="text-align:left;"><img id="add_trendlines" src="${ctx}/${static_files_dir}/+.gif" style="vertical-align:middle;cursor:pointer;margin-left:10px;" /></td>
        </tr>
        <tr id="trendlines_id" style="display:none;" class="trendlines_tr">
          <td width="520" nowrap="nowrap"> 预警、提示名称
            <input type="text" style="width:80px;margin:0 3px;" />
            预警、提示数值
            <input type="text" style="width:50px;margin:0 3px;" onfocus="javascript:setOnlyNum(this);" />
            预警、提示横线颜色
            <input type="text" style="width:60px;margin:0 3px;" maxlength="7" readonly="readonly" /></td>
          <td width="10" nowrap="nowrap"><input type="button" value="选择颜色" onclick="showColorPicker(this, $('td:first', $(this).parent().parent()).children().eq(2)[0])" /></td>
          <td width="20" nowrap="nowrap"><select>
              <option value="0">...预警线的颜色，不作为“柱状”的颜色...</option>
              <option value="2">...预警线的颜色，作为大于预警值“柱状”的颜色（正数）...</option>
              <option value="1">...预警线的颜色，作为小于预警值“柱状”的颜色（正数）...</option>
              <option value="-1">...预警线的颜色，作为大于预警值“柱状”的颜色（负数）...</option>
              <option value="-2">...预警线的颜色，作为小于预警值“柱状”的颜色（负数）...</option>
            </select></td>
          <td class="remove_trendlines" style="text-align:left;"><img src="${ctx}/${static_files_dir}/x.gif" style="vertical-align:middle;cursor:pointer;margin-left:10px;" /></td>
        </tr>
        <tbody id="trendlines_tbody" style="border:none;">
        </tbody>
      </table></td>
  </tr>
  <tr>
    <td class="items_th">单位在数值前缀、后缀：</td>
    <td class="items_td"><select name="unitSite" id="unitSite">
        <option value="1">...数值后缀...</option>
        <option value="0">...数值前缀...</option>
      </select></td>
  </tr>
  <tr>
    <td class="items_th">数值是否进行1000(K)格式化：</td>
    <td class="items_td"><select name="formatNumberScale" id="formatNumberScale">
        <option value="0">...否...</option>
        <option value="1">...是...</option>
      </select>
      &nbsp;如果选择了"是"&nbsp;10000&nbsp;数字会格式化成&nbsp;10K。 </td>
  </tr>
  <tr>
    <td class="items_th">小数点精度：</td>
    <td class="items_td"><input type="text" id="decimals" name="decimals" value="2" style="width:60px;" maxlength="6" onfocus="javascript:setOnlyNum(this);" />
      位小数</td>
  </tr>
  <tr>
    <td class="items_th">是否显示边框：</td>
    <td class="items_td"><select name="showBorder" id="showBorder">
        <option value="0">...否...</option>
        <option value="1">...是...</option>
      </select></td>
  </tr>
  <tr>
    <td class="items_th">&nbsp;</td>
    <td class="items_td"><input type="button" name="button" value="确认生成" id="button" style="cursor:pointer;" /></td>
  </tr>
</table>
<div id="result" style="display:none;margin-top:10px;margin-bottom:20px;"> 
	<span style="color:#f00;cursor:pointer;margin-bottom:15px;font-weight:bold;font-size:18px;margin-left:5px;" id="copy_id">点击打开下列地址:</span>
	<span style='color:blue;margin-left:5px;'>下面的地址请作为IFRAME地址进行嵌套展示图表。或者使用其他方式嵌入！</span>
	<br /><br />
  <div id="result_span" style="overflow:auto;width:100%;padding-left:5px;"></div>
</div>
<!-- 覆盖层  -->
<div style="display:none;top:20%;left:20%;background:#fff;font-size:12px;" id="ajax_view">
  <table border="0" cellspacing="0" cellpadding="0" >
    <tr>
      <td><img src="${ctx}/${static_files_dir}/ajax-loader.gif" /></td>
    </tr>
  </table>
</div>
<script type="text/javascript" src="${ctx}/${static_files_dir}/jquery.js"></script>
<script type="text/javascript" src="${ctx}/${static_files_dir}/lightBox.js"></script>
<script type="text/javascript" src="${ctx}/${static_files_dir}/colorpicker/color_functions.js"></script>
<script type="text/javascript" src="${ctx}/${static_files_dir}/colorpicker/js_color_picker_v2.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var ajax_view = new LightBox("ajax_view");
	ajax_view.Over = true;  //是否启用覆盖层  :true 、 false
	ajax_view.OverLay.Color = "#000"; //覆盖层颜色 ，必须启用覆盖层才有作用
	ajax_view.OverLay.Opacity = 50; //覆盖层透明度 
	ajax_view.Fixed = true; // 是否定位
	ajax_view.Center = true; //是否居中
	
	$("#dataUrl" ).attr("dataType", "Url").attr("msg", "请输入正确的URL地址！");
	
	// 输入JSON数据源以后点击加载数据
	$("#ajax_get_thead").click(function(){
		var dataUrl = $("#dataUrl").val();
		ajax_view.Show(); // 启用覆盖层
		$.ajax({
			type: "POST",
			url: "${www_url}?chartMethod=getThead",
			data: { "dataUrl": dataUrl.replace(/\&/g, "_!_"), "timestamp":new Date().getTime() },
			dataType: "json",
			error: function (xhr, ajaxOptions, thrownError) { ajax_view.Close(); alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
			success: function(result) {
			  if (result.status == "-1"){
			        alert("提示，数据加载失败，请确保URL正确性！");
		      } else {
		    	  $(".class_select").each(function(){
		    		  $(this).empty();
		    		  $(this).append("<option value=''>--请选择列--</option>");
			  		  for(var i = 0; i < result.length; i++)
			  			$(this).append("<option value='" + result[i].key + "'>" + result[i].value + "</option>");
		    	  });
			  }
			  
			  ajax_view.Close(); // 关闭覆盖层
			}
		});
	});
	
	// 报表类型切换
	$("#type").change(function(){
		if("" == $(this).val()){
			$("#colspan_1, #colspan_2").hide();
		}
		if("h" == $(this).val() || "l" == $(this).val() || "p" == $(this).val()){
			$("#colspan_1").show();
			$("#colspan_2").hide();
		}
		if("hl" == $(this).val()){
			$("#colspan_2").show();
			$("#colspan_1").hide();
		}
	});

	//增加列，单图
	$("#add_tr_1").click(function(){
		$("#colspan_1_id").clone().appendTo($("#colspan_1_tbody")).show();
		
		$(".tr_remove").click(function(){
			$(this).parent().remove();
		});
	});

	// 混混合图
	$("#add_tr_2").click(function(){
		$("#colspan_2_id").clone().appendTo($("#colspan_2_tbody")).show();
		
		$(".tr_remove").click(function(){
			$(this).parent().remove();
		});
	});

	// 警示线
	$("#add_trendlines").click(function(){
		$("#trendlines_id").clone().appendTo($("#trendlines_tbody")).show();
		
		$(".remove_trendlines").click(function(){
			$(this).parent().remove();
		});
	});

	// 点击生成URL
	var url;
	$("#button").click(function(){
		url = "${www_url}?chartMethod=index&dataUrl=";
	
		// 数据来源url
		var dataUral = $("#dataUrl").val();
		if("" == dataUral) { alert("原始JSON数据地址不能为空！"); $("#dataUrl").focus(); return;}
		// 报表类型
		var type = $("#type").val();
		if("" == type)  { alert("请选择报表类型！"); $("#type").focus(); return;}

		// 需要显示数据的列
		var colspan = "";
		if("h" == type || "l" == type || "p" == type){
			$(".colspan_1_tr").each(function(){
				var val = $(this).find("select").val();
				if(null != val && "" != val){
					colspan += val + ",";
				}
			});
		}
		if("hl" == type){
			$(".colspan_2_tr").each(function(){
				var val = $(this).find("select").eq(0).val();
				var select = $(this).find("select").eq(1).val();
				if(null != val && "" != val && null != select && "" != select){
					colspan += val + "_" + select + ",";
				}
			});
		}
		if("" == colspan)  { alert("请选择需要显示数据的列！"); $("#type").focus(); return;}
		colspan = colspan.substr(0, colspan.length - 1);

		// 高宽
		var width = $("#width").val();
		var height = $("#height").val();

		// 预警值
		var trendlines = "";
		$(".trendlines_tr").each(function(){
			var $inputs = $(this).find("input");
			var val1 = $inputs.eq(0).val();
			var val2 = $inputs.eq(1).val();
			var val3 = $inputs.eq(2).val();
			var val4 = $(this).find("select").eq(0).val();
			if("" != val1 && "" != val2 && "" != val3){
				trendlines += val1 + "_" + val2 + "_" + val3.substr(1, val3.length) + "_" + val4 + ",";
			}
		});
		if("" != trendlines) { 
			trendlines = trendlines.substr(0, trendlines.length - 1);
		}
		
		var unitSite = $("#unitSite").val();
		var decimals = $("#decimals").val();
		var showBorder = $("#showBorder").val();
		var formatNumberScale = $("#formatNumberScale").val();

		url += escape(dataUral.replace(/\&/g, "_!_")) + "&type=" + type + "&colspan=" + colspan + "&width=" + width + "&height=" + height + "&trendlines=" + encodeURIComponent(trendlines) + "&unitSite=" + unitSite + "&decimals=" + decimals + "&showBorder=" + showBorder + "&formatNumberScale=" + formatNumberScale;

		$("#result_span").html(url);
		$("#result").show();
	});
	
	// 点击打开地址
	$("#copy_id,#result_span").click(function(){
		window.open(url);
		$("#result_span").select();
	});
});

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
		if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "";
	});
}
//]]></script>
</body>
</html>