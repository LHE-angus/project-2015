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
      <html-el:hidden property="type" value="${type }" />
      <c:if test="${type eq 0 }">
	      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
	        <tr>
	          <td  bgcolor="#CCCCCC" style="font-weight:bold;">调拨商品填写</td>
	        </tr>
	        <tr>
	          <td>
	          	调拨日期：<html-el:text property="out_date" styleId="out_date" size="12" value="${af.map.out_date}" readonly="readonly" styleClass="Wdate" onclick="WdatePicker({readOnly:true,minDate:'${beforedate}',maxDate:'${nowdate }'})" />
	          </td>
	        </tr>
	        <tr>
	          <td nowrap="nowrap" class="title_item" align="right">
	          	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
	                <tr>
					<td width="15%" align="center"><font color="red">*</font>调出客户</td>
					<td width="15%" align="center"><font color="red">*</font>调出客户R3编码</td>
					<td width="15%" align="center"><font color="red">*</font>型号</td>
					<td width="15%" align="center"><font color="red">*</font>调入仓库</td>
					<td width="10%" align="center"><font color="red">*</font>调拨数量（单位：台）</td>
					<td width="20%" align="center">备注</td>
					<td width="8%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
					<td></td>
	              </tr>
	              <tr id="orderHidden" style="display:none;">
					<td align="center">
						<html-el:select property="out_r3_code" styleClass="out_r3_code">
			        		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${r3shopList}" varStatus="vs">
			          			<html-el:option value="${cur.r3_code}">${cur.customer_name}</html-el:option>
			          		</c:forEach>
			            </html-el:select><br/>
					</td>
					<td align="center"><div class="div_r3_code" style="color:red"></div></td>
					<td align="center">
						<html-el:select property="out_goods_id" styleClass="out_goods_id">
			        		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
			          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
			          		</c:forEach>
			            </html-el:select><br/>
					</td>
					<td align="center">
						<html-el:select property="in_store_id" styleClass="in_store_id">
			        		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
			          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
			          		</c:forEach>
			            </html-el:select><br/>
					</td>
					<td align="center"><html-el:text property="in_num"  styleClass="in_num" maxlength="6" onfocus="setOnlyInt(this);" style="width:100px;"/><br/></td>
					<td align="center"><html-el:text property="memo" styleClass="memo" maxlength="100" style="width:200px;"/></td>
	                <td align="center" title="删除"  class="td_del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
	              </tr>
	              <tbody id="showAddTrsTbody"></tbody>
	            </table>
	          </td>
	        </tr>
	        <tr>
	          <td align="center">
          <br/><br/><br/><br/><br/><br/><br/><br/>
          <html-el:button property="" value="提 交" styleClass="bgButtonSave" styleId="btn_submit" />
	            <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();" />
	          </td>
	        </tr>
	      </table>
      </c:if>
      <c:if test="${type eq 1 }">
      	<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
	        <tr>
	          <td  bgcolor="#CCCCCC" style="font-weight:bold;">调拨商品填写</td>
	        </tr>
	        <tr>
	          <td>
	          	<font color="red">*</font>调拨日期：<html-el:text property="out_date" styleId="out_date" size="12" value="${af.map.out_date}" readonly="readonly" styleClass="Wdate" onclick="WdatePicker({readOnly:true,minDate:'${beforedate}',maxDate:'${nowdate }'})" />
	          </td>
	        </tr>
	        <tr>
	          <td nowrap="nowrap" class="title_item" align="right">
	          	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
	                <tr>
					<td width="15%" align="center"><font color="red">*</font>调出仓库</td>
					<td width="15%" align="center"><font color="red">*</font>型号</td>
					<td width="15%" align="center"><font color="red">*</font>库存（单位：台）</td>
					<td width="15%" align="center"><font color="red">*</font>调入客户</td>
					<td width="15%" align="center"><font color="red">*</font>调入客户R3编码</td>
					<td width="10%" align="center"><font color="red">*</font>调拨数量（单位：台）</td>
					<td width="20%" align="center">备注</td>
					<td width="8%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
					<td></td>
	              </tr>
	              <tr id="orderHidden" style="display:none;">
					<td align="center">
						<html-el:select property="out_store_id" styleClass="out_store_id" multiple="multiple">
			        		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
			          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
			          		</c:forEach>
			            </html-el:select><br/>
					</td>
					<td align="center">
						<html-el:select property="out_goods_id" styleClass="out_goods_id" multiple="multiple">
			        		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
			          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
			          		</c:forEach>
			            </html-el:select><br/>
					</td>
					<td align="center"><div id="stocks"></div></td>
					<td align="center">
						<html-el:select property="in_r3_code" styleClass="in_r3_code" multiple="multiple">
			        		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${r3shopList}" varStatus="vs">
			          			<html-el:option value="${cur.r3_code}">${cur.customer_name}</html-el:option>
			          		</c:forEach>
			            </html-el:select><br/>
					</td>
					<td align="center"><div id="div_r3_code" style="color:red"></div></td>
					<td align="center"><html-el:text property="in_num"  styleClass="in_num" maxlength="6" onfocus="setOnlyInt(this);" style="width:100px;"/><br/></td>
					<td align="center"><html-el:text property="memo" styleClass="memo" maxlength="100" style="width:200px;"/></td>
	                <td align="center" title="删除"  class="td_del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
	              </tr>
	              <tbody id="showAddTrsTbody"></tbody>
	            </table>
	          </td>
	        </tr>
	        <tr>
	          <td align="center">
          <br/><br/><br/><br/><br/><br/><br/><br/>
          <html-el:button property="" value="提 交" styleClass="bgButtonSave" styleId="btn_submit" />
	            <html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();" />
	          </td>
	        </tr>
	      </table>
      </c:if>
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
	$("#out_date").attr("dataType", "Require").attr("msg", "请选择！");

	<c:if test="${type eq 0 }">
	$("#addPdTD").click(function(){
		var tr_pd = $("#orderHidden").clone(true).attr("class","tr_pd");
		tr_pd.appendTo("#showAddTrsTbody").show();
		var trLast = $("tr:last","showAddTrsTbody");
		
		tr_pd.find("select").eq(0).multiselect({//out_r3_code
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160,
			click: function(event, ui){
				var r3_code = ui.value;
				var $div_r3_code = tr_pd.children("td").eq(1).children();
				var $out_goods_id = tr_pd.find("select").eq(1);
				$div_r3_code.text(r3_code);
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
							$out_goods_id.empty();
							var html = "<option value=''>=请选择=</option>";
							for(var i=0; i<ret.list.length; i++){			
								html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
							}
							$out_goods_id.html(html);
							$out_goods_id.multiselect("refresh");
						}
					}
			   });
			}
		}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择！");
	
		tr_pd.find("select").eq(2).multiselect({//in_store_id
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160
		}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择！");
	
		tr_pd.find("select").eq(1).multiselect({//out_goods_id
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160
		}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择！");
		$("td:last",trLast).click(function(){
			$(this).parent().remove();
			//iframe高度自适应
			window.parent.resizeFrameHeight('mainFrame', 3);
		});
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	}).css("cursor", "pointer");
	$(".in_num").blur(function(){
		var num = $(this).val();
		if(num == null || num == ''){
			jError("调拨数量不能为空！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000, onClosed:function(){$(this).val("").focus();}});
			$(this).val("");
		}
		if(num==0){
			jError("调拨数量不能为0！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000, onClosed:function(){$(this).val("").focus();}});
			$(this).val("");
		}
	});
	</c:if>
	<c:if test="${type eq 1 }">
	$("#addPdTD").click(function(){
		var tr_pd = $("#orderHidden").clone(true).attr("class","tr_pd");
		tr_pd.appendTo("#showAddTrsTbody").show();
		var trLast = $("tr:last","showAddTrsTbody");
		
		tr_pd.find("select").eq(0).multiselect({//out_store_id
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160,
			click: function(event, ui){
				var out_store_id = ui.value;
				var $out_goods_id = tr_pd.find("select").eq(1);
				//根据仓库获得 的商品数据
				$.ajax({
					type: "POST",
					url: "JxcOutInDetail.do",
					data: {method : "ajaxGetGoodsListForStoreId", "store_id": out_store_id},
					dataType: "json",
					cache:false,
					error: function(){alert("数据加载请求失败！");},
					success: function(ret){
						if(ret){
							$out_goods_id.empty();
							var html = "<option value=''>=请选择=</option>";
							for(var i=0; i<ret.list.length; i++){			
								html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
							}
							$out_goods_id.html(html);
							$out_goods_id.multiselect("refresh");
						}
					}
			   });
			}
		}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择！");
		tr_pd.find("select").eq(1).multiselect({//out_goods_id
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160,
			click: function(event, ui){
				var goodId = ui.value;
				var storeId = tr_pd.find("select").eq(0).val();
				var $stock = tr_pd.children("td").eq(2).children();
				if (null != storeId && null != goodId) {
					// 查选中商品的库存 
					getStockNumForSelectPd(storeId, goodId , $stock);
				}
			}
		}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择！");
		tr_pd.find("select").eq(2).multiselect({//in_r3_code
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160,
			click: function(event, ui){
				var r3_code = ui.value;
				var $div_r3_code = tr_pd.children("td").eq(4).children();
				$div_r3_code.text(r3_code);
			}
		}).multiselectfilter({label:"<span>搜索：</span>"}).attr("datatype", "Require").attr("msg", "请选择！");
	
		$("td:last",trLast).click(function(){
			$(this).parent().remove();
			//iframe高度自适应
			window.parent.resizeFrameHeight('mainFrame', 3);
		});
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	}).css("cursor", "pointer");
	$(".in_num").blur(function(){
		var $this = $(this);
		var num = $(this).val();
		var $stocks = $this.parent().parent().children('td').eq(2).children();
		if(num == null || num == ''){
			jError("调拨数量不能为空！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000, onClosed:function(){$(this).val("").focus();}});
			$this.val("");
		}
		if(num==0){
			jError("调拨数量不能为0！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000, onClosed:function(){$(this).val("").focus();}});
			$this.val("");
		}
		if(parseFloat(num) > parseFloat($stocks.text())){
			jError("调拨数量不能大于库存！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000, onClosed:function(){$(this).val("").focus();}});
			$this.val("");
		}
	});
	</c:if>
	
	$(".td_del").click(function(){
		$(this).parent().remove();	
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	
	$("#btn_submit").click(function(){

		if(Validator.Validate(this.form, 3)){
			$("#orderHidden:lt(1)").empty();
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
			this.form.submit();
		} else {
			//iframe高度自适应
			window.parent.resizeFrameHeight('mainFrame', 3);
		}
	});
	
});

//取选择仓库中的商品库存
function getStockNumForSelectPd(storeId, goodId ,$stock){
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
					$stock.text(result.stocks);
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
