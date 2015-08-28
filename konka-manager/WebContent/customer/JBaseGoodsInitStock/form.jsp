<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>初始化库存</title>
<base target="_self" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
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
    <html-el:form action="/manager/JBaseGoodsInitStock">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="returnUrl" />
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td  bgcolor="#CCCCCC" style="font-weight:bold;">商品库存信息填写</td>
        </tr>
        <tr>
          <td>
          	<html-el:text property="opr_date" styleId="opr_date" value="${today}" styleClass="webinput" size="10" readonly="true" style="cursor:pointer;" />
			          <select name="HH">
			          	<c:forEach begin="0" end="23" varStatus="vs">
							<option value="${fnx:leftPad_sis(vs.index, 2, '0')}">${fnx:leftPad_sis(vs.index, 2, '0')}时</option>		          	
			          	</c:forEach>
			          </select>
			          <select name="mm">
			          	<c:forEach begin="0" end="59" varStatus="vs">
							<option value="${fnx:leftPad_sis(vs.index, 2, '0')}">${fnx:leftPad_sis(vs.index, 2, '0')}分</option>		          	
			          	</c:forEach>
			          </select>
			          <span style="color:gray;padding-left: 20">(精确到日期时分)</span>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="right">
          <div style="overflow-x:auto;solid navy;">
          	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
                <tr>
				<td width="18%" align="center">仓库</td>
				<td width="18%" align="center">机型</td>
				<td width="18%" align="center">初始化库存（单位：台）</td>
				<td width="18%" align="center">初始化单价（单位：元）</td>
				<td width="18%" align="center">初始化金额（单位：元）</td>
				<td width="20%" align="center">说明</td>
				<td width="8%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
				<td></td>
              </tr>
              <tr id="orderHidden" style="display:none;">
				<td align="center">
					<html-el:select property="store_id" styleClass="store_id">
		          		<c:forEach var="cur" items="${jBaseStoreList}" varStatus="vs">
		          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
		          		</c:forEach>
		            </html-el:select>
				<br />
				</td>
				<td align="center">
				<html-el:select property="goods_id" styleClass="goods_id">
		        		<html-el:option value="">请选择</html-el:option>
		          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
		          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
		          		</c:forEach>
		            </html-el:select><br />
				</td>
				<td align="center"><html-el:text property="init_count" styleId="init_count" styleClass="init_count" maxlength="6" onfocus="setOnlyInt(this);" style="width:100px;"/><br />
				</td>
				<td align="center"><html-el:text property="buy_price" styleId="buy_price" styleClass="buy_price" maxlength="8" onfocus="setOnlyPositiveNum(this);" style="width:100px;"/><br /></td>
				<td align="center"><html-el:text property="init_money" styleId="init_money" styleClass="init_money" maxlength="12" onfocus="setOnlyPositiveNum(this);" style="width:100px;"/><br /></td>
				<td align="center"><html-el:text property="init_desc" styleId="init_desc" styleClass="init_desc" maxlength="100" style="width:150px;"/><br /></td>
                <td align="center" title="删除"  class="td_del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
              </tr>
              <tbody id="tbodyOrder">
               <c:forEach items="${af.map.konkaSpMdSailList}" var="cur">
              <tr id="trOrderHidden" style="">
                <td align="center" >
					<html-el:select property="store_id" styleClass="store_id">
		        		<html-el:option value="">请选择</html-el:option>
		          		<c:forEach var="cur" items="${konkaSpMdTypeList}" varStatus="vs">
		          			<html-el:option value="${cur.md_name}">${cur.md_name}</html-el:option>
		          		</c:forEach>
		            </html-el:select>
				<br />
				</td>
				<td align="center">
				<html-el:select property="goods_id" styleClass="goods_id">
		        		<html-el:option value="">请选择</html-el:option>
		          		<c:forEach var="cur" items="${konkaSpMdTypeList}" varStatus="vs">
		          			<html-el:option value="${cur.md_name}">${cur.md_name}</html-el:option>
		          		</c:forEach>
		            </html-el:select><br />
				</td>
				<td align="center"><html-el:text property="init_count" styleId="init_count" styleClass="init_count" maxlength="6" onfocus="setOnlyInt(this);" style="width:100px;"/><br />
				</td>
				<td align="center"><html-el:text property="buy_price" styleId="buy_price" styleClass="buy_price" maxlength="8" onfocus="setOnlyPositiveNum(this);" style="width:100px;"/><br />
				</td>
				<td align="center"><html-el:text property="init_money" styleId="init_money" styleClass="init_money" maxlength="12" onfocus="setOnlyPositiveNum(this);" style="width:100px;"/><br />
				</td>
                <td align="center"><html-el:text property="init_desc" styleId="init_desc" styleClass="init_desc" maxlength="100" style="width:150px;"/><br /></td>
                <td align="center" title="删除" style="cursor: pointer;"  class="td_del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
              </tr>
              </c:forEach>
              </tbody>
              <tbody id="showAddTrsTbody"></tbody>
               <tr class="title_top">
                <td>合计</td>
                <td> &nbsp; </td>
                <td align="center">
                	<span id="sum_init_count" style="background-color: white;"></span>
                </td>
                <td> &nbsp; </td>
                <td align="center"> <span id="sum_init_money" style="background-color: white;"></td>
                <td> &nbsp; </td>
              </tr>
            </table>
            </div>
          </td>
        </tr>
        <tr>
          <td align="center">
          <br/><br/><br/><br/><br/><br/><br/><br/>
          <html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />
            <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期限定 
	$("#opr_date").datepicker({yearRange:'2010:2050',maxDate:'${today}'});
	$("#opr_date").attr("dataType", "Require").attr("msg", "请选择初始化日期！");
	// 动态添加
	$("#addPdTD").click(function(){
		var tr_pd = $("#orderHidden").clone(true).attr("class","tr_pd");
		tr_pd.appendTo("#showAddTrsTbody").show();
		var trLast = $("tr:last","showAddTrsTbody");
		
		tr_pd.find("select").eq(0).multiselect({//仓库
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160
		}).multiselectfilter({label:"<span>搜索：</span>"});
		tr_pd.find("select").eq(1).multiselect({//商品
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160
		}).multiselectfilter({label:"<span>搜索：</span>"});
		
		$("td:last",trLast).click(function(){
			$(this).parent().remove();
			window.parent.resizeFrameHeight('mainFrame', 3);
		});
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	}).css("cursor", "pointer");
	
	$(".init_count").blur(function(){
		//计算初始化库存的合计值
		var sum_init_count = 0;
		$(".init_count").each(function(){
			init_count = $(this).val();
			if($.trim(init_count).length > 0){
				sum_init_count = parseFloat(sum_init_count) + parseFloat(init_count);
			}
		});
		$("#sum_init_count").text(sum_init_count);
		
		//计算初始化金额
		var $buy_price = $(this).parent().parent().children('td').eq(3).children();
		var $init_money = $(this).parent().parent().children('td').eq(4).children();
		var buy_price = $buy_price.val();
		if(buy_price!=null && buy_price.length > 0 && $(this).val().length>0){
			var init_money = parseFloat(buy_price)*parseFloat($(this).val());
			$init_money.val(init_money);
		}
		
		//计算初始化金额的合计值
		var sum_init_money = 0;
		$(".init_money").each(function(){
			init_money = $(this).val();
			if($.trim(init_money).length > 0){
				sum_init_money = parseFloat(sum_init_money) + parseFloat(init_money);
			}
		});
		$("#sum_init_money").text(formatFloat(sum_init_money,2));
	});
	
	$(".buy_price").blur(function(){
		var $init_count = $(this).parent().parent().children('td').eq(2).children();
		var $init_money = $(this).parent().parent().children('td').eq(4).children();
		//计算初始化金额
		if($init_count.val()!=null && $init_count.val().length > 0 && $(this).val().length>0){
			$init_money.val(formatFloat(parseFloat($init_count.val()) * parseFloat($(this).val()),2));
		}
		
		//计算初始化金额的合计值
		var sum_init_money = 0;
		$(".init_money").each(function(){
			init_money = $(this).val();
			if($.trim(init_money).length > 0){
				sum_init_money = parseFloat(sum_init_money) + parseFloat(init_money);
			}
		});
		$("#sum_init_money").text(formatFloat(sum_init_money,2));
	});
	
	$(".init_money").blur(function(){
		var $init_count = $(this).parent().parent().children('td').eq(2).children();
		var $buy_price = $(this).parent().parent().children('td').eq(3).children();
		//计算初始化金额
		if($init_count.val()!=null && $init_count.val().length > 0 && $(this).val().length>0){
			$buy_price.val(formatFloat(parseFloat($(this).val()) / parseFloat($init_count.val()),2));
		}
		
		//计算初始化金额的合计值
		var sum_init_money = 0;
		$(".init_money").each(function(){
			init_money = $(this).val();
			if($.trim(init_money).length > 0){
				sum_init_money = parseFloat(sum_init_money) + parseFloat(init_money);
			}
		});
		$("#sum_init_money").text(formatFloat(sum_init_money,2));
	});
	
	$(".td_del").click(function(){
		$(this).parent().remove();	
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	$("#btn_submit").click(function(){
		$(".tr_pd .store_id").attr("dataType", "Require").attr("msg", "请选择！");
		$(".tr_pd .goods_id").attr("dataType", "Require").attr("msg", "请选择！");
		$(".tr_pd .init_count").attr("dataType", "Require").attr("msg", "请填写！");
		$(".tr_pd .buy_price").attr("dataType", "Require").attr("msg", "请填写！");
		$(".tr_pd .init_money").attr("dataType", "Require").attr("msg", "请填写！");
		if(Validator.Validate(this.form, 3)){
			$("#tr_model").remove();
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
	
});

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
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
//格式化数字，pos为保留几位小数
function formatFloat(src, pos) 
{ 
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos); 
} 
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
