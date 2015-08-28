<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${naviString}</title>
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
<div class="oarcont" >
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont2">
  		<html-el:form action="/manager/KonkaStockInventory">
  			<html-el:hidden property="method" value="save"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
	<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td  bgcolor="#CCCCCC" style="font-weight:bold;">盘点信息</td>
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
          <div style="overflow-x:auto;">
          	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
                <tr>
				<td width="15%" align="center">仓库</td>
				<td width="15%" align="center">商品名称</td>
				<td width="10%" align="center">盘点前（单位：台）</td>
				<td width="10%" align="center">盘点后（单位：台）</td>
				<td width="10%" align="center">结果</td>
				<td width="20%" align="center">备注</td>
				<td width="8%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
				<td></td>
              </tr>
              <tr id="orderHidden" style="display:none;">
				<td align="center">
					<html-el:select  property="store_id" styleClass="store_id">
						<c:forEach items="${jBaseStoreList}" var="cur" varStatus="vs">
							<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
						</c:forEach>
					</html-el:select >
				</td>
				<td align="center">
				<html-el:select property="goods_id" styleClass="goods_id">
		          		<html-el:option value="">请选择</html-el:option>
		          		<c:forEach items="${jBaseGoodsList}" var="cur" varStatus="vs">
		          			<html-el:option value="${cur.goods_id}">${cur.map.goods_name}</html-el:option>
		          		</c:forEach>
		          	</html-el:select ><br/>
				</td>
				<td align="center"><html-el:text property="stocks" styleClass="stocks"  size="13" readonly="true" /><br /></td>
				<td align="center"><html-el:text property="ver_stocks" styleClass="ver_stocks"  size="13" readonly="false"  onfocus="setOnlyInt(this);" maxlength="8"/><br /></td>
				<td align="center">
					<div class="result"></div>
				</td>
				<td align="center"><html-el:text property="memo" styleClass="memo" maxlength="100" style="width:150px;"/></td>
                <td align="center" title="删除"  class="td_del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
              </tr>
              <tbody id="tbodyOrder">
               <c:forEach items="${af.map.konkaSpMdSailList}" var="cur">
              <tr id="trOrderHidden" style="">
                <td align="center">
					<html-el:select  property="store_id" styleClass="store_id">
						<c:forEach items="${jBaseStoreList}" var="cur" varStatus="vs">
							<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
						</c:forEach>
					</html-el:select >
				</td>
				<td align="center">
				<html-el:select property="goods_id" styleClass="goods_id">
		          		<html-el:option value="">请选择</html-el:option>
		          		<c:forEach items="${jBaseGoodsList}" var="cur" varStatus="vs">
		          			<html-el:option value="${cur.goods_id}">${cur.map.goods_name}</html-el:option>
		          		</c:forEach>
		          	</html-el:select><br/>
				</td>
				<td align="center"><html-el:text property="stocks" styleClass="stocks"  size="13" readonly="true" /></td>
				<td align="center"><html-el:text property="ver_stocks" styleClass="ver_stocks"  size="13" readonly="false"  onfocus="setOnlyInt(this);" maxlength="8"/></td>
				<td align="center">
				<div class="result"></div>
				</td>
				<td align="center"><html-el:text property="memo" styleClass="memo" maxlength="100" style="width:150px;"/></td>
                <td align="center" title="删除" style="cursor: pointer;"  class="td_del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
              </tr>
              </c:forEach>
              </tbody>
              <tbody id="showAddTrsTbody"></tbody>
            </table>
            </div>
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
  		</html-el:form>
  	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script>
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	// 日期限定 
	$("#opr_date").datepicker({yearRange:'2010:2050',maxDate:'${today}'});

	$("#opr_date").attr("dataType", "Require").attr("msg", "请选择盘点日期！");
	// 动态添加
	$("#addPdTD").click(function(){
		var tr_pd = $("#orderHidden").clone(true).attr("class","tr_pd");
		tr_pd.appendTo("#showAddTrsTbody").show();
		var trLast = $("tr:last","showAddTrsTbody");
		
		tr_pd.find("select").eq(0).multiselect({//仓库
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160,
			click: function(event, ui){
				var store_id = ui.value;
				var $goods_id = tr_pd.find("select").eq(1);
				$.ajax({
					type: "POST",
					url: "KonkaStockInventory.do",
					data: {method : "getGoodsListForSelectPd", "store_id": store_id},
					dataType: "json",
					cache:false,
					error: function(){alert("数据加载请求失败！");},
					success: function(ret){
						if(ret){
							$goods_id.empty();
							var html = "<option value=''>请选择</option>";
							for(var i=0; i<ret.list.length; i++){			
								html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
							}
							$goods_id.html(html);
							$goods_id.multiselect("refresh");
						}
					}
			   });
			}
		}).multiselectfilter({label:"<span>搜索：</span>"});
		
		tr_pd.find("select").eq(1).multiselect({//商品
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160,
			click: function(event, ui){
				var $store_id = tr_pd.find("select").eq(0);
				var $stock = tr_pd.children("td").eq(2).children();
				var goods_id = ui.value;
				var store_id = $store_id.val();
				if(goods_id != null && store_id != null){
					getStockNumForSelectPd(store_id,goods_id,$stock);
				}
			}
		}).multiselectfilter({label:"<span>搜索：</span>"});
		
		$("td:last",trLast).click(function(){
			$(this).parent().remove();
			//iframe高度自适应
			window.parent.resizeFrameHeight('mainFrame', 3);
		});
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
		
	}).css("cursor", "pointer");
	
	$(".ver_stocks").blur(function(){
		$this = $(this);
		var $stock = $this.parent().parent().children('td').eq(2).children();
		var $result = $this.parent().parent().children('td').eq(4).children();
		var stock = $stock.val();
		var ver_stocks = $this.val();
		$result.html("");
		if(parseFloat(ver_stocks) > parseFloat(stock)){
			$result.html('<span style="color:#009900;">盘盈</span>');
		} else if(parseFloat(ver_stocks) == parseFloat(stock)){
			$result.html('<span>库实相符</span>');
		} else if(parseFloat(ver_stocks) < parseFloat(stock)){
			$result.html('<span style="color:#CD0000;">盘亏</span>');
		}
	});

	$(".td_del").click(function(){
		$(this).parent().remove();	
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	//表单提交
	$("#btn_submit").click(function(){
		$(".tr_pd .ver_stocks").attr("dataType", "Require").attr("msg", "请填写！");
		$(".tr_pd .store_id").attr("dataType", "Require").attr("msg", "请选择！");
		$(".tr_pd .goods_id").attr("dataType", "Require").attr("msg", "请选择！");
		if(Validator.Validate(this.form, 3)){
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
//             $("#btn_back").attr("disabled", "true");
			this.form.submit();
		} else {
			//iframe高度自适应
			window.parent.resizeFrameHeight('mainFrame', 3);
		}
	});
	
});

//取选择仓库中的商品库存
function getStockNumForSelectPd(storeId, goodId, $stocks){
// 	alert("仓库ID = " + storeId + "\n商品ID = " + goodId);
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
					$("#opr_date").datepicker({yearRange:'2000:2040',maxDate:'${today}'}).datepicker('refresh');
					jError("操作失败：参数丢失，请重试！", {HorizontalPosition:"center", VerticalPosition:"center", TimeShown:3000});
				} else if(result.state == 0) { //查询得当前系统库存
					$stocks.val(result.stocks);
					var lastOprDate = result.lastOprDate;
					if ("" != lastOprDate) { //最近一次盘点日期
						$("#opr_date").datepicker("destroy");
						$("#opr_date").datepicker({yearRange:'2000:2040',maxDate:'${today}',minDate:lastOprDate}).datepicker('refresh');
					} else {
						$("#opr_date").datepicker({yearRange:'2000:2040',maxDate:'${today}'}).datepicker('refresh');
					}
					$.jNotify._close();
				}
	        }
		});
	}
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
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>