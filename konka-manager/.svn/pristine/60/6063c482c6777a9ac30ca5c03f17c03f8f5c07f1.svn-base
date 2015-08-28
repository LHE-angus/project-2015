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
  	<div class="rtabcont2" style="padding-left: 10px; padding-right: 10px">
  		<html-el:form action="/manager/KonkaStockInventory">
  		<html-el:hidden property="method" value="save"/>
	    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	    <html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
		
		<div style="padding-left:0px;overflow-x:auto;overflow-y:hidden;padding-bottom: 30px">
			<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
	        	<tr><td  bgcolor="#CCCCCC" style="font-weight:bold;">盘点信息</td></tr>
	        	<tr>
	          		<td>
<%-- 	          	  		<html-el:text property="opr_date" styleId="opr_date" value="${today}" styleClass="webinput" size="10" readonly="true" style="cursor:pointer;" /> --%>
<!-- 		          		<select name="HH" id="opr_HH"> -->
<%-- 		          			<c:forEach begin="0" end="23" varStatus="vs"> --%>
<%-- 		          				<c:if test="${fnx:leftPad_sis(vs.index, 2, '0')==opr_HH}"> --%>
<%-- 		          					<option value="${fnx:leftPad_sis(vs.index, 2, '0')}" selected="selected">${fnx:leftPad_sis(vs.index, 2, '0')}时</option>	 --%>
<%-- 		          				</c:if> --%>
<%-- 		          				<c:if test="${fnx:leftPad_sis(vs.index, 2, '0')!=opr_HH}"> --%>
<%-- 		          					<option value="${fnx:leftPad_sis(vs.index, 2, '0')}">${fnx:leftPad_sis(vs.index, 2, '0')}时</option>	 --%>
<%-- 		          				</c:if> --%>
<%-- 							</c:forEach> --%>
<!-- 		          		</select> -->
<!-- 		          		<select name="mm" id="opr_mm"> -->
<%-- 		          			<c:forEach begin="0" end="59" varStatus="vs"> --%>
<%-- 								<c:if test="${fnx:leftPad_sis(vs.index, 2, '0')==opr_mm}"> --%>
<%-- 									<option value="${fnx:leftPad_sis(vs.index, 2, '0')}" selected="selected">${fnx:leftPad_sis(vs.index, 2, '0')}分</option>	 --%>
<%-- 		          				</c:if> --%>
<%-- 		          				<c:if test="${fnx:leftPad_sis(vs.index, 2, '0')!=opr_mm}"> --%>
<%-- 									<option value="${fnx:leftPad_sis(vs.index, 2, '0')}">${fnx:leftPad_sis(vs.index, 2, '0')}分</option>	 --%>
<%-- 		          				</c:if>	          	 --%>
<%-- 		          			</c:forEach> --%>
<!-- 		          		</select> -->
<!-- 		          		<span style="color:gray;padding-left: 20">(精确到日期时分)</span> -->
		          		<html-el:button property="" value="提 交" styleClass="bgButtonSave" styleId="btn_submit" />
		          		&nbsp;
	              		<html-el:button property="" value="返 回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();return false;" />
	              		&nbsp;&nbsp;&nbsp;&nbsp;
		          		<input name="input" type="button" id="but_excel" class="but_excel" value="导 入" onclick="location.href='KonkaStockInventory.do?method=excel&mod_id=${af.map.mod_id}&opr_date='+$('#opr_date').val()+'&opr_HH='+$('#opr_HH').val()+'&opr_mm='+$('#opr_mm').val();" /></td>
	          		</td>
	        	</tr>
	        	<tr>
	          		<td nowrap="nowrap" class="title_item" align="right">
	          			<div style="overflow-x:auto; height: 400px" >
	          				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable3">
	                			<tr>
									<td width="12%" align="center">仓库</td>
									<td width="12%" align="center">商品名称</td>
									<td width="10%" align="center">盘点前数量</td>
									<td width="10%" align="center">盘点前金额</td>
									<td width="10%" align="center">差异数量</td>
									<td width="6%" align="center">差异单价</td>
									<td width="10%" align="center">盘点后数量</td>
									<td width="10%" align="center">盘点后金额</td>
									<td width="4%" align="center">结果</td>
									<td width="12%" align="center">备注</td>
									<td width="8%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
	              				</tr>
	              				<tr id="orderHidden" style="display:none;">
									<td align="center" width="12%">
										<html-el:select  property="store_id" styleClass="store_id">
											<c:forEach items="${jBaseStoreList}" var="cur" varStatus="vs">
												<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
											</c:forEach>
										</html-el:select >
									</td>
									<td align="center" width="12%">
										<html-el:select property="goods_id" styleClass="goods_id">
			          						<html-el:option value="">请选择</html-el:option>
			          						<c:forEach items="${jBaseGoodsList}" var="cur" varStatus="vs">
			          							<html-el:option value="${cur.goods_id}">${cur.map.goods_name}</html-el:option>
			          						</c:forEach>
			          					</html-el:select ><br/>
									</td>
									<td align="center" width="10%"><html-el:text property="stocks" styleClass="stocks"  size="10" readonly="true" /><br /></td>
									<td align="center" width="10%"><html-el:text property="money" styleClass="money"  size="10" readonly="true" /><br /></td>
									<td align="center" width="10%"><html-el:text property="ver_stocks_diff" styleClass="ver_stocks_diff"  size="10" readonly="false"  onfocus="setAllNum();" maxlength="8"/><br /></td>
									<td align="center" width="6%">
										<html-el:text property="price" styleClass="price"  size="10" readonly="false" onfocus="setAllNum(this);" maxlength="10"/>
									</td>
									<td align="center" width="10%"><html-el:text property="ver_stocks" styleClass="ver_stocks"  size="10" readonly="false"  onfocus="setAllNum();" maxlength="8"/><br /></td>
									
									<td align="center" width="10%"><html-el:text property="ver_money" styleClass="ver_money"  size="10" readonly="false"  onfocus="setOnlyPositiveNum(this);" maxlength="8"/><br /></td>
									<td align="center" width="4%">
										<div class="result"></div>
									</td>
									<td align="center" width="12%"><html-el:text property="memo" styleClass="memo" maxlength="100" style="width:150px;"/></td>
					                <td align="center" width="8%" title="删除"  class="td_del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
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
										<td align="center"><html-el:text property="stocks" styleClass="stocks"  size="13" readonly="true" /><br /></td>
										<td align="center"><html-el:text property="money" styleClass="money"  size="13" readonly="true" /><br /></td>
										<td align="center" width="10%"><html-el:text property="ver_stocks_diff" styleClass="ver_stocks_diff"  size="10" readonly="false"  onfocus="setAllNum();" maxlength="8"/><br /></td>
										<td align="center">
											<html-el:text property="price" styleClass="price"  size="13" readonly="false"  onfocus="setAllNum();" maxlength="10"/>
										</td>
										<td align="center"><html-el:text property="ver_stocks" styleClass="ver_stocks"  size="13" readonly="false"  onfocus="setOnlyInt(this);" maxlength="8"/><br /></td>

										<td align="center"><html-el:text property="ver_money" styleClass="ver_money"  size="13" readonly="false"  onfocus="setOnlyPositiveNum(this);" maxlength="10"/><br /></td>
										<td align="center">
											<div class="result"></div>
										</td>
										<td align="center"><html-el:text property="memo" styleClass="memo" maxlength="100" style="width:150px;"/></td>
						                <td align="center" title="删除" style="cursor: pointer;"  class="td_del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
              						</tr>
              					</c:forEach>
	              				<!-- 从导入页面过来的数据 -->
	              				<c:forEach items="${excelMapList}" var="cur">
	              				<tr class="tr_pd">
	                				<td align="center">
										<html-el:select  property="store_id" styleClass="store_id" value="${cur.store_id}" onchange="getGoodsList($(this));">
							<c:forEach items="${jBaseStoreList}" var="jBaseStore" varStatus="vs">
								<html-el:option value="${jBaseStore.store_id}">${jBaseStore.store_name}</html-el:option>
							</c:forEach>
						</html-el:select >
					</td>
					<td align="center">
					<html-el:select property="goods_id" styleClass="goods_id" value="${cur.goods_id}" onchange="getStocksAndMoney($(this));">
			          		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach items="${cur.jBaseGoodsList}" var="jBaseGoods" varStatus="vs">
			          			<html-el:option value="${jBaseGoods.goods_id}">${jBaseGoods.map.goods_name}</html-el:option>
			          		</c:forEach>
			          	</html-el:select><br/>
					</td>
					<td align="center"><html-el:text property="stocks" styleClass="stocks"  size="13" readonly="true" value="${cur.stocks}"/><br /></td>
					<td align="center"><html-el:text property="money" styleClass="money"  size="13" readonly="true" value="${cur.money}" /><br /></td>
					<td align="center"><html-el:text property="ver_stocks_diff" styleClass="ver_stocks_diff"  size="13" readonly="false"  onfocus="setAllNum();" maxlength="8" value="${cur.ver_stocks-cur.stocks}"/><br /></td>
									<td align="center">
										<html-el:text property="price" styleClass="price"  size="13" readonly="false"  onfocus="setAllNum();" maxlength="10" value="${cur.price}"/>
									</td>
									<td align="center"><html-el:text property="ver_stocks" styleClass="ver_stocks"  size="13" readonly="false"  onfocus="setOnlyInt(this);" maxlength="8" value="${cur.ver_stocks}"/><br /></td>

					<td align="center"><html-el:text property="ver_money" styleClass="ver_money"  size="13" readonly="false"  onfocus="setOnlyPositiveNum(this);" maxlength="10" value="${cur.ver_money}"/><br /></td>
					<td align="center">
						<div class="result">
						<c:if test="${cur.ver_stocks==cur.stocks}">
							<span>库实相符</span>
						</c:if>
						<c:if test="${cur.ver_stocks>cur.stocks}">
							<span style="color:#009900;">盘盈</span>
						</c:if>
						<c:if test="${cur.ver_stocks<cur.stocks}">
							<span style="color:#CD0000;">盘亏</span>
						</c:if>
						</div>
					</td>
					<td align="center"><html-el:text property="memo" styleClass="memo" maxlength="100" style="width:150px;" value="${cur.memo}"/></td>
	                <td align="center" title="删除" style="cursor: pointer;"  class="td_del"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
	              </tr>
	              </c:forEach>
	              </tbody>
	              <tbody id="showAddTrsTbody"></tbody>
	            </table>
	            </div>
	          </td>
	        </tr>
	      </table>
	    </div>
  		</html-el:form>
  	</div>
  <div style="height: 200px;"></div>
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
				var $money = tr_pd.children("td").eq(3).children();
				var goods_id = ui.value;
				var store_id = $store_id.val();
				if(goods_id != null && store_id != null){
					getStockNumForSelectPd(store_id,goods_id,$stock ,$money);
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
	
	$(".ver_stocks_diff").blur(function(){
		$this = $(this);
		var $store = $this.parent().parent().children('td').eq(0).children();
		var $goods = $this.parent().parent().children('td').eq(1).children();
		var $stock = $this.parent().parent().children('td').eq(2).children();
		var $money = $this.parent().parent().children('td').eq(3).children();
		var $ver_stocks = $this.parent().parent().children('td').eq(6).children();
		var $price = $this.parent().parent().children('td').eq(5).children();
		var $ver_money = $this.parent().parent().children('td').eq(7).children();
		var $result = $this.parent().parent().children('td').eq(8).children();
		var stock = $stock.val();
		var ver_stocks_diff = $this.val();
		$result.html("");
		var count = parseInt(ver_stocks_diff,10) + parseInt(stock,10);
		$ver_stocks.val(count);
		if(ver_stocks_diff>0){//盘盈
				$result.html('<span style="color:#009900;">盘盈</span>');
				$price.focus();
		} else if(ver_stocks_diff == 0){//库实相符
				 $result.html('<span>库实相符</span>');
			     $price.focus();
				// $price.val("");
				// $ver_money.val("");
		} else if(ver_stocks_diff < 0){//盘亏
				 $result.html('<span style="color:#CD0000;">盘亏</span>');
					$price.focus();
				 //$price.val("");
				// $ver_money.val("");
		}
		
	});




	$(".ver_stocks").blur(function(){
		$this = $(this);
		var $store = $this.parent().parent().children('td').eq(0).children();
		var $goods = $this.parent().parent().children('td').eq(1).children();
		var $stock = $this.parent().parent().children('td').eq(2).children();
		var $money = $this.parent().parent().children('td').eq(3).children();
		var $ver_stocks_diff = $this.parent().parent().children('td').eq(4).children();
		var $price = $this.parent().parent().children('td').eq(5).children();
		var $ver_stocks = $this.parent().parent().children('td').eq(6).children();
		var $ver_money = $this.parent().parent().children('td').eq(7).children();
		var $result = $this.parent().parent().children('td').eq(8).children();
		var stock = $stock.val();
		//var ver_stocks_diff = $ver_stocks_diff.val();
		var ver_stocks=$this.val();
		$result.html("");
		var ver_stocks_diff = parseInt(ver_stocks,10) - parseInt(stock,10);
		$ver_stocks_diff.val(ver_stocks_diff);
		if(ver_stocks_diff>0){//盘盈
				$result.html('<span style="color:#009900;">盘盈</span>');
				$ver_money.focus();
		} else if(ver_stocks_diff == 0){//库实相符
				 $result.html('<span>库实相符</span>');
				 $ver_money.focus();
				// $price.val("");
				// $ver_money.val("");
		} else if(ver_stocks_diff < 0){//盘亏
				 $result.html('<span style="color:#CD0000;">盘亏</span>');
				 $ver_money.focus();
				 //$price.val("");
				// $ver_money.val("");
		}
		
	});
	$(".ver_money").blur(function(){
		$this = $(this);
		var $store = $this.parent().parent().children('td').eq(0).children();
		var $goods = $this.parent().parent().children('td').eq(1).children();
		var $stock = $this.parent().parent().children('td').eq(2).children();
		var $money = $this.parent().parent().children('td').eq(3).children();
		var $ver_stocks_diff = $this.parent().parent().children('td').eq(4).children();
		var $price = $this.parent().parent().children('td').eq(5).children();
		var $ver_stocks = $this.parent().parent().children('td').eq(6).children();
		var $ver_money = $this.parent().parent().children('td').eq(7).children();
		var $result = $this.parent().parent().children('td').eq(8).children();
		var stock = $stock.val();
		var money = $money.val();
		var ver_stocks_diff = $ver_stocks_diff.val();
		var ver_stocks =$ver_stocks_diff.val();
		var ver_money=$this.val();
        if(parseInt(ver_stocks_diff,10) ==0){
        	$price.val(0);
           }else{
        	   var diff_price=((ver_money-money)/parseInt(ver_stocks_diff,10)).toFixed(2);
        	   $price.val(diff_price);
           }
	});
	
//	$(".ver_stocks").blur(function(){
//		$this = $(this);
//		var $store = $this.parent().parent().children('td').eq(0).children();
//		var $goods = $this.parent().parent().children('td').eq(1).children();
//		var $stock = $this.parent().parent().children('td').eq(2).children();
//		var $money = $this.parent().parent().children('td').eq(3).children();
//		var $price = $this.parent().parent().children('td').eq(5).children();
//		var $ver_money = $this.parent().parent().children('td').eq(7).children();
//		var $result = $this.parent().parent().children('td').eq(8).children();
//		var stock = $stock.val();
//		var ver_stocks =$store.val()+ $this.val();
//		$result.html("");
//		var count = parseInt(ver_stocks,10) - parseInt(stock,10);
//		if(count>0){//盘盈
//				$result.html('<span style="color:#009900;">盘盈</span>');
//				$price.focus();
//		} else if(count == 0){//库实相符
//				 $result.html('<span>库实相符</span>');
//				 $price.val("");
//				 $ver_money.val("");
//		} else if(count < 0){//盘亏
//				 $result.html('<span style="color:#CD0000;">盘亏</span>');
//				 $price.val("");
//				 $ver_money.val("");
//		}
//
//	});
	
	$(".price").blur(function(){
		var $stock = $this.parent().parent().children('td').eq(2).children();
		var $money = $this.parent().parent().children('td').eq(3).children();
		var $ver_stocks_diff = $(this).parent().parent().children('td').eq(4).children();
		var $ver_stocks = $(this).parent().parent().children('td').eq(6).children();
		var $ver_money = $(this).parent().parent().children('td').eq(7).children();
		//计算初始化金额
		if($(this).val().length>0){
			$ver_money.val(formatFloat(
					parseFloat($money.val())+
					parseFloat($ver_stocks_diff.val()) * parseFloat($(this).val()),2));
		}
	});
//	$(".ver_money").blur(function(){
//		var $ver_stocks = $(this).parent().parent().children('td').eq(6).children();
//		var $price = $(this).parent().parent().children('td').eq(5).children();
//		//计算初始化金额
//		if($(this).val().length>0){
//			$price.val(formatFloat(parseFloat($(this).val()) / parseFloat($ver_stocks.val()),2));
//		}
//	});

	$(".td_del").click(function(){
		$(this).parent().remove();	
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	//表单提交
	$("#btn_submit").click(function(){
		$(".tr_pd .ver_stocks").attr("dataType", "Require").attr("msg", "请填写！");
		$(".tr_pd .ver_money").attr("dataType", "Require").attr("msg", "请填写！");
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
	
	/*
	for(var i=0; i<$(".tr_pd").length; i++){
		$(".tr_pd").eq(i).find("select").eq(0).multiselect({//仓库
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160,
			click: function(event, ui){
				var store_id = ui.value;
				var $goods_id = $(".tr_pd").eq(i).find("select").eq(1);
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
	
		$(".tr_pd").eq(i).find("select").eq(1).multiselect({//商品
			noneSelectedText: '<span >=请选择=</span>',
			selectedList: 1,
			multiple: false,
			minWidth:160,
			click: function(event, ui){
				var $store_id = $(".tr_pd").eq(i).find("select").eq(0);
				var $stock = $(".tr_pd").eq(i).children("td").eq(2).children();
				var $money = $(".tr_pd").eq(i).children("td").eq(3).children();
				var goods_id = ui.value;
				var store_id = $store_id.val();
				if(goods_id != null && store_id != null){
					getStockNumForSelectPd(store_id,goods_id,$stock ,$money);
				}
			}
		}).multiselectfilter({label:"<span>搜索：</span>"});
		
	}
	*/
	
});

// 查询仓库对应的商品
function getGoodsList($storeObj){
	$.ajax({
		type: "POST",
		url: "KonkaStockInventory.do",
		data: {method : "getGoodsListForSelectPd", "store_id": $storeObj.val()},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(ret){
			if(ret){
				var tr_pd = $storeObj.parent().parent();
				tr_pd.find("select").eq(1).empty();
				tr_pd.children("td").eq(2).children().val("");
				tr_pd.children("td").eq(3).children().val("");
				var html = "<option value=''>请选择</option>";
				for(var i=0; i<ret.list.length; i++){			
					html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
				}
				tr_pd.find("select").eq(1).html(html);
			}
		}
   });
}

// 查询商品库存
function getStocksAndMoney($goodsObj){
	var tr_pd = $goodsObj.parent().parent();
	var $store_id = tr_pd.find("select").eq(0);
	var $stock = tr_pd.children("td").eq(2).children();
	var $money = tr_pd.children("td").eq(3).children();
	$stock.val("");
	$money.val("");
	getStockNumForSelectPd($store_id.val(), $goodsObj.val(), $stock, $money);
}

//取选择仓库中的商品库存
function getStockNumForSelectPd(storeId, goodId, $stocks, $money){
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
					$money.val(result.money);
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
		if(!obj.value.match(/^[+-]\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^[+-](?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[+-]\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^[+-](?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^[+-](?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
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
function setAllNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	$(this).keypress(function (){
		if(!this.value.match(/^[+-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(/^[+-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:\d+(?:\.\d+)?)?$/))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(/^[+-]?(?:\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^[+-]?\.\d+$/))this.value=0+this.value;if(this.value.match(/^[+-]?\.$/))this.value=0;this.o_value=this.value}
		if(this.value.length == 0) this.value = "";
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