<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加调拨信息</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
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
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JxcOutInDetail">
      <html-el:hidden property="method" value="save0and1"/>
      <html-el:hidden property="queryString" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="id" styleId="id" />
      <html-el:hidden property="type" value="${type }" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
      <c:if test="${type eq 0 }">
      	<tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">调入商品信息填写</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>调出客户：</td>
          <td align="left"><html-el:select property="out_r3_code" styleId="out_r3_code" multiple="multiple">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${r3shopList}" varStatus="vs">
	          			<html-el:option value="${cur.r3_code}">${cur.customer_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
          </td>
        </tr>
        <tr style="display:none" id="tr_r3_code">
          <td nowrap="nowrap" class="title_item" align="right">调出客户R3编码：</td>
          <td align="left"><b><div id="div_r3_code" style="color:red"></div></b></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>型号：</td>
          <td align="left"><html-el:select property="out_goods_id" styleId="out_goods_id" multiple="multiple">
		        		<html-el:option value="">请选择</html-el:option>
		          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
		          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
		          		</c:forEach>
		            </html-el:select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>调入仓库：</td>
          <td align="left"><html-el:select property="in_store_id" styleId="in_store_id" multiple="multiple">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
          </td>
        </tr>
      </c:if>
      <c:if test="${type eq 1 }">
        <tr>
          <td colspan="2"  bgcolor="#CCCCCC" style="font-weight:bold;">调出商品信息填写</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>调出仓库：</td>
          <td align="left"><html-el:select property="out_store_id" styleId="out_store_id" multiple="multiple">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
	          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>型号：</td>
          <td align="left"><html-el:select property="out_goods_id" styleId="out_goods_id" multiple="multiple">
		        		<html-el:option value="">请选择</html-el:option>
		          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
		          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
		          		</c:forEach>
		            </html-el:select></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>调入客户：</td>
          <td align="left"><html-el:select property="in_r3_code" styleId="in_r3_code" multiple="multiple">
	        		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${r3shopList}" varStatus="vs">
	          			<html-el:option value="${cur.r3_code}">${cur.customer_name}</html-el:option>
	          		</c:forEach>
	            </html-el:select>
          </td>
        </tr>
        <tr style="display:none" id="tr_r3_code">
          <td nowrap="nowrap" class="title_item" align="right">调入客户R3编码：</td>
          <td align="left"><b><div id="div_r3_code" style="color:red"></div></b></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>库存（单位：台）：</td>
          <td align="left"><div id="stocks"></div></td>
        </tr>
      </c:if>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>调拨数量（单位：台）：</td>
          <td align="left"><html-el:text property="in_num" styleId="in_num" styleClass="in_num" maxlength="6" onfocus="setOnlyInt(this);" style="width:100px;"/></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right"><span style="color:red;">* </span>调拨日期：</td>
          <td align="left"><input name="out_date" id="out_date" size="12" value="${af.map.out_date}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'${beforedate}',maxDate:'${nowdate }'})" /></td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
          <td align="left"><html-el:textarea property="memo" styleId="memo" style="resize:none;" cols="50" rows="5"></html-el:textarea>
          <div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#in_num").attr("dataType", "Require").attr("msg", "请填写！");
	$("#out_date").attr("dataType", "Require").attr("msg", "请选择！");
	
	$("#memo").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	
	<c:if test="${type eq 1 }">
		$("#in_r3_code").multiselect({
				noneSelectedText: '<span >=请选择=</span>',
				selectedList: 1,
				multiple: false,
				minWidth:160,
				click: function(event, ui){
					var r3_code = ui.value;
					$("#div_r3_code").text(r3_code);
					$("#tr_r3_code").show();
				}
			}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择客户！");
		$("#out_store_id").multiselect({
				noneSelectedText: '<span >=请选择=</span>',
				selectedList: 1,
				multiple: false,
				minWidth:160,
				click: function(event, ui){
					var storeId = ui.value;
					var goodId = $("#out_goods_id").val();
					if (null != storeId && null != goodId) {
						// 查选中商品的库存 
						getStockNumForSelectPd(storeId, goodId);
					}
				}
			}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择仓库！");
	
		$("#out_goods_id").multiselect({
				noneSelectedText: '<span >=请选择=</span>',
				selectedList: 1,
				multiple: false,
				minWidth:160,
				click: function(event, ui){
					var goodId = ui.value;
					var storeId = $("#out_store_id").val();
					if (null != storeId && null != goodId) {
						// 查选中商品的库存 
						getStockNumForSelectPd(storeId, goodId);
					}
				}
			}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择型号！");
		$("#in_num").blur(function(){
			var num = $(this).val();
			if(num==0){
				$(this).empty();
				jError("调拨数量不能为0！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000, onClosed:function(){$(this).val("").focus();}});
			}
			if(parseFloat(num) > parseFloat($("#stocks").text())){
				$(this).empty();
				jError("调拨数量不能大于库存！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000, onClosed:function(){$(this).val("").focus();}});
			}
		});
	</c:if>
	<c:if test="${type eq 0 }">
		$("#out_r3_code").multiselect({
				noneSelectedText: '<span >=请选择=</span>',
				selectedList: 1,
				multiple: false,
				minWidth:160,
				click: function(event, ui){
					var r3_code = ui.value;
					$("#div_r3_code").text(r3_code);
					$("#tr_r3_code").show();
					//根据客户获得客户 的商品数据
					$.ajax({
						type: "POST",
						url: "JxcOutInDetail.do",
						data: {method : "ajaxGetGoodsList", "r3_code": r3_code},
						dataType: "json",
						cache:false,
						error: function(){alert("数据加载请求失败！");},
						success: function(ret){
							if(ret){
								$("#out_goods_id").empty();
								var html = "<option value=''>请选择</option>";
								for(var i=0; i<ret.list.length; i++){			
									html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
								}
								$("#out_goods_id").html(html);
								$("#out_goods_id").multiselect("refresh");
							}
						}
				   });
				}
			}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择客户！");
		$("#in_store_id").multiselect({
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160
		}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择仓库！");
	
		$("#out_goods_id").multiselect({
				noneSelectedText: '<span >=请选择=</span>',
				selectedList: 1,
				multiple: false,
				minWidth:160
			}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择型号！");
		$("#in_num").blur(function(){
			var num = $(this).val();
			if(num==0){
				$(this).empty();
				jError("调拨数量不能为0！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000, onClosed:function(){$(this).val("").focus();}});
			}
		});
	</c:if>
	
	
	$("#btn_submit").click(function(){
		if(Validator.Validate(this.form, 3)){
			var out_store_id=$("#out_store_id").val();
			var in_store_id=$("#in_store_id").val();
			if(parseFloat(out_store_id) == parseFloat(in_store_id)){
				alert("移出仓库不能和移入仓库相同！");
				return false;
			}
			if($("#in_num").val() == 0){
				alert("移出数量必须大于0！");
				return false;
			}
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
//             $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
	
});

//取选择仓库中的商品库存
function getStockNumForSelectPd(storeId, goodId){
	//alert("仓库ID = " + storeId + "\n商品ID = " + goodId);
	if (null != storeId && null != goodId && "" != storeId && "" != goodId) {
		jLoading("&nbsp;&nbsp;正在查询当前系统库存...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
		$.ajax({
			type: "POST" , 
			url: "KonkaStockInventory.do" , 
			data:"method=getStockNumForSelectPd&store_id=" + storeId + "&good_id=" + goodId + "&t=" + new Date(),
			dataType: "json" , 
	        async: true, 
	        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
	        success: function (result) {
				if (result.state == -1) { //参数丢失
					jError("操作失败：参数丢失，请重试！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
				} else if(result.state == 0) { //查询得当前系统库存
					$("#in_num").val("");
					$("#stocks").text(result.stocks);
					$.jNotify._close();
				}
	        }
		});
	}
}

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
		//if(obj.value.length == 0) obj.value = "0";
	});
}

//正则表达式：只能输入数字
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
		//if(obj.value.length == 0) obj.value = "0";
	});
}
//正则表达式：只能输入数字（不包括负数）
function setOnlyPositiveNum(obj) {
	$(obj).css("ime-mode", "disabled");
	$(obj).attr("t_value", "");
	$(obj).attr("o_value", "");
	$(obj).bind("dragenter",function(){
		return false;
	});
	$(obj).keypress(function (){
		if(!obj.value.match(/^\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value)) obj.value = "";
	});
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
