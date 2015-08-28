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
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop" >
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
	  <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JBill.do">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="bill_id" value="${af.map.bill_id}"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" styleId="mod_id"/>
      <html-el:hidden property="bill_type" value="${af.map.bill_type}" styleId="bill_type"/>
      <html-el:hidden property="queryString" />
      <c:set var="readonly" value="${empty af.map.bill_id ? false : true}"/>
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="5"  bgcolor="#CCCCCC" style="font-weight:bold;">登记信息填写</td>
        </tr>
		<tr>
			<td width="10px"></td>
			<td width='8%' nowrap="nowrap" class="title_item"><span style="color:red;">* </span>供&nbsp;应&nbsp;商：</td>
			<td width="30%">
				<html-el:select property="partner_id" styleId="partner_id" value="${af.map.partner_id}" onchange="getAgentInfo(this)">
					<html-el:option value="">请选择</html-el:option>
					<c:forEach var="cur" items="${jBasePartners}" varStatus="vs">
						<html-el:option value="${cur.partner_id}">
							${cur.partner_name}
						</html-el:option>
					</c:forEach>
				</html-el:select>
				&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBasePartner.do?method=add&returnUrl=' + encodeURIComponent(location.href.replace(/(&)*partner_id=\d+/g, '')) + '&mod_id=199010400';return true;" style="color:blue;cursor:pointer;">新建供应商</a>
			</td>
			<td width="8%" nowrap="nowrap" class="title_item" ><span style="color:red;">* </span>${bill_sn_title}：</td>
			<td><html-el:text property="bill_sn" size="40" maxlength="30" readonly="true" styleId="bill_sn" /></td>
		</tr>
		<tr>
			<td width="10px"></td>
			<td nowrap="nowrap" class="title_item"><span style="color:red;">* </span>交货方式：</td>
			<td>
				<c:if test="${af.map.send_type eq 0 }">
					<label><input type='checkbox' id="send_type1" checked="checked" name="send_type" value="0" onclick="chSendType(this)"></input>自提</label>
					&nbsp;&nbsp;
					<label><input type='checkbox' id="send_type2" name="send_type" value="1" onclick="chSendType(this)"></input>配送</label>
				</c:if>
				<c:if test="${af.map.send_type eq 1 }">
					<label><input type='checkbox' id="send_type1" name="send_type" value="0" onclick="chSendType(this)"></input>自提</label>
					&nbsp;&nbsp;
					<label><input type='checkbox' id="send_type2" checked="checked" name="send_type" value="1" onclick="chSendType(this)"></input>配送</label>
				</c:if>
				<c:if test="${empty af.map.send_type }">
					<label><input type='checkbox' id="send_type1" name="send_type" value="0" onclick="chSendType(this)"></input>自提</label>
					&nbsp;&nbsp;
					<label><input type='checkbox' id="send_type2" name="send_type" value="1" onclick="chSendType(this)"></input>配送</label>
				</c:if>
			</td>
          	<td nowrap="nowrap" class="title_item"><span style="color:red;">* </span>退货日期：</td>
          	<td>
				<fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd" var="_opr_date" />
          		<html-el:text property="opr_date" styleId="opr_date" size="10" maxlength="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100px;" value="${_opr_date}" disabled="${readonly}" />
			</td>
        </tr>
		<tr>
			<td width="10px"></td>
		  	<td nowrap="nowrap" class="title_item" style="padding-left: 14px">联&nbsp;系&nbsp;人：</td>
		  	<td>
			  	<span id='link_name_t'>${af.map.jBasePartner.link_name }</span>
		  	</td>
		  	<td nowrap="nowrap" class="title_item" style="padding-left: 14px">联系电话：</td>
		  	<td>
			  	<span id="link_mobile_t">${af.map.jBasePartner.link_mobile }</span>
		  	</td>
	    </tr>
		<tr>
		  	<td width="10px"></td>
			<td nowrap="nowrap" class="title_item" style="padding-left: 14px">创&nbsp;建&nbsp;人：</td>
			<td>${add_user_name }</td>
		  	<td nowrap="nowrap" class="title_item" style="padding-left: 14px">创建时间：</td>
		  	<td>
				<fmt:formatDate value="${af.map.opr_date }" pattern="yyyy-MM-dd HH:mm:ss" var="c_opr_date" />
				<html-el:hidden property="create_date" ></html-el:hidden>
				${c_opr_date}
		  	</td>
	  	</tr>
		<tr>
		  	<td width="10px"></td>
		  	<td nowrap="nowrap" class="title_item" style="padding-left: 14px">单据状态：</td>
		  	<td>
				<c:choose>
					<c:when test="${af.map.bill_state eq 0 }">待确认</c:when>
					<c:when test="${af.map.bill_state eq 1 }">已确认</c:when>
					<c:otherwise>待确认</c:otherwise>
				</c:choose>
			  	<html-el:hidden property="bill_state" styleId="bill_state" value="${af.map.bill_state }"></html-el:hidden>
		  	</td>
		  	<td nowrap="nowrap" class="title_item" style="padding-left: 14px">送货地址：</td>
		  	<td>
			  	<span id="send_addr_t">${af.map.jBasePartner.map._PROVINCE }${af.map.jBasePartner.map._CITY }${af.map.jBasePartner.map._COUNTRY }${af.map.jBasePartner.map._TOWN }${af.map.jBasePartner.consignee_street}</span>
		  	</td>
	  	</tr>
		<tr>
			<td width="10px"></td>
			<td nowrap="nowrap" class="title_item" style="padding-left: 14px">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
			<td colspan="3">
				<html-el:textarea property="bill_memo" styleId="bill_memo" rows="5" cols="80" style="resize:none" />
				<div id="info_tip" style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" /></div>
			</td>
		</tr>
	  </table>
	  <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="2" bgcolor="#CCCCCC">商品明细</td>
        </tr>
        <tr>
          <td colspan="2">
			  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td width="15%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${store_title}&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseStore.do?method=add&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010500';return true;" style="color:blue;cursor:pointer;">新建</a></td>
          			<td width="10%" align="center" nowrap="nowrap"><span style="color:red;">* </span>商品类型</td>
          			<td width="15%" align="center" nowrap="nowrap"><span style="color:red;">* </span>商品&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseGoods.do?method=add&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010100';return true;" style="color:blue;cursor:pointer;">新建</a></td>
          			<td width="10%" align="center" nowrap="nowrap" style="display:none;">条码</td>
          			<td width="5%" align="center" nowrap="nowrap">单位</td>
          			<td width="6%" align="center" nowrap="nowrap">库存</td>
          			<td width="9%" align="center" nowrap="nowrap"><span style="color:red;">* </span>数量</td>
          			<td width="9%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${price_title}（元）</td>
          			<td width="9%" align="center" nowrap="nowrap">${goods_money_title}（元）</td>
          			<td width="10%" align="center" nowrap="nowrap">行备注</td>
          			<td width="5%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
          		</tr>
          		<c:set var="details" />
          		<c:if test="${readonly}">
          			<html-el:hidden property="method_type" value="1" />
          			<c:forEach var="cur1" items="${detailsList}">
          			<c:set var="details" value="1" />
          			<tr class="tr_pd">
	          			<td width="17%" align="center" class="td_pd" nowrap="nowrap">
							<html-el:select property="store_id" styleClass="store_id" value="${cur1.store_id}" style="width:80px" >
				          		<c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
				          			<option value="${cur.store_id}">${cur.store_name}</option>
				          		</c:forEach>
				          </html-el:select></td>
	          			<td align="center" nowrap="nowrap">
							<html-el:select property="type_id" styleClass="type_id"  value="${cur1.type_id }" style="width:90px">
								<html-el:option value="">请选择</html-el:option>
								<c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
									<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
								</c:forEach>
							</html-el:select>
						</td>
	          			<td align="center" class="td_pd" nowrap="nowrap">
							<html-el:select property="goods_id" styleClass="goods_id" value="${cur1.goods_id }" style="width:150px">
								<html-el:option value="">请选择</html-el:option>
								<c:forEach var="goods" items="${cur1.map.goodsList }" varStatus="vs">
									<html-el:option value="${goods.goods_id}">${goods.goods_name}</html-el:option>
								</c:forEach>
							</html-el:select>
						</td>
	          			<td align="center" nowrap="nowrap" style="display:none;"><input name="goods_sn" size="10" value="${cur1.map.goods_sn}" maxlength="30" class="goods_sn" disabled="${readonly}" /></td>
	          			<td align="center" nowrap="nowrap"><span class="dw">${cur1.map.unit}</span></td>
	          			<td align="center" nowrap="nowrap"><span class="dw">${cur1.map.stocks}</span></td>
	          			<td align="center" nowrap="nowrap"><input name="num" id="num" size="6" onfocus="javascript:setOnlyInt(this)" value="${-cur1.num}" maxlength="4" class="num" /></td>
	          			<td align="center" nowrap="nowrap"><input name="price" id="price" size="8" value="${cur1.price}" onfocus="javascript:setOnlyNum(this);" maxlength="10" class="price"  /></td>
	          			<td align="center" nowrap="nowrap"><input name="goods_money" size="8" value="${cur1.money}" class="goods_money" /></td>
	          			<td align="center" nowrap="nowrap"><input name="notes" size="15" value="${cur1.notes}" class="notes" /></td>
	          			<td align="center" style="cursor:pointer;" class="td_del"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
	          		</tr>
	          		</c:forEach>
          		</c:if>
          		<c:if test="${not readonly}">
          		<html-el:hidden property="method_type" value="0" />
          		<c:set var="details" value="0" />
          		<tr class="tr_pd">
          			<td align="center" nowrap="nowrap"><html-el:select property="store_id" styleClass="store_id" style="width:80px">
			          		<c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
			          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
			          		</c:forEach>
			          </html-el:select></td>
			        <td align="center" nowrap="nowrap"><html-el:select property="type_id" styleClass="type_id" onchange="selectGoodType(this)" style="width:80px">
			        		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
			          			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
			          		</c:forEach>
			          </html-el:select></td>
          			<td align="center" nowrap="nowrap"><html-el:select property="goods_id" styleClass="goods_id" style="width:150px">
				          		<html-el:option value="">请选择</html-el:option>
				          </html-el:select></td>
          			<td align="center" nowrap="nowrap" style="display:none;"><html-el:text property="goods_sn" size="10" maxlength="30" styleClass="goods_sn" /></td>
          			<td align="center" nowrap="nowrap"><span class="dw"></span></td>
          			<td align="center" nowrap="nowrap"><span class="stock"></span></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="num" styleId="num" size="6" onfocus="javascript:setOnlyInt(this)" value="1" maxlength="6" styleClass="num" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="price" styleId="price" size="8" onfocus="javascript:setOnlyNum(this);"
          			 maxlength="10" styleClass="price" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="goods_money" styleClass="goods_money" size="6" readonly="true" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="notes" styleClass="notes" size="15" /></td>
          			<td align="center" id="del"><img src="${ctx}/images/x_gray.gif" style="vertical-align:middle;top:0px;" /></td>
          		</tr>
          		</c:if>
          		<tr id="tr_model" style="display:none;">
          			<td align="center" nowrap="nowrap"><html-el:select property="store_id" styleClass="store_id" style="width:80px">
			          		<c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
			          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
			          		</c:forEach>
			          </html-el:select></td>
			        <td align="center" nowrap="nowrap"><html-el:select property="type_id" styleClass="type_id" onchange="selectGoodType(this)" style="width:80px">
			        		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
			          			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
			          		</c:forEach>
			          </html-el:select></td>
          			<td align="center" nowrap="nowrap"><html-el:select property="goods_id" styleClass="goods_id" style="width:150px">
				          		<html-el:option value="">请选择</html-el:option>
				          </html-el:select></td>
          			<td align="center" nowrap="nowrap" style="display:none;"><html-el:text property="goods_sn" size="20" maxlength="30" styleClass="goods_sn" /></td>
          			<td align="center" nowrap="nowrap"><span class="dw"></span></td>
          			<td align="center" nowrap="nowrap"><span class="stock"></span></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="num" size="6" onfocus="javascript:setOnlyInt(this)" maxlength="6" value="1" styleClass="num" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="price" size="8" onfocus="javascript:setOnlyNum(this);"
          			 maxlength="10" styleClass="price" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="goods_money" size="6" styleClass="goods_money" readonly="true" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="notes" size="15" styleClass="notes" /></td>
          			<td align="center" style="cursor:pointer;"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
          		</tr>
          		<tbody id="showAddTrsTbody"></tbody>
			  </table>
          <c:if test="${af.map.bill_type eq 10}">
	      	<div style="color:#F00;">注：康佳产品的进货数据来自康佳R3系统，商品列表已过滤康佳产品型号。</div>
	      </c:if>
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="title_item" align="center">
          	${rec_money_title}：<html-el:text property="rec_money" styleId="rec_money" size="30" readonly="true" styleClass="webinput" maxlength="10" />&nbsp;元
		  </td>
		  <td nowrap="nowrap" class="title_item" align="center">
          	折扣金额：<html-el:text property="dis_money" styleId="dis_money" size="30" readonly="true" styleClass="webinput" maxlength="10" disabled="${readonly}" />&nbsp;元
		  </td>
        </tr>
       	<tr>
          <td nowrap="nowrap" class="title_item" align="center">
          	${money_title}：<html-el:text property="money" styleId="money" size="30" styleClass="webinput" onfocus="javascript:setOnlyNum(this)" maxlength="10" />&nbsp;元
		  </td>
		  <td></td>
        </tr>
        <tr>
          <td colspan="2" align="center">
			  <html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />&nbsp;&nbsp;
              <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
              <c:if test="${not empty detailsList}">
				&nbsp;&nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
              </c:if></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">//<![CDATA[

	//选择网点后，自动带出信息
	function getAgentInfo(obj){
		if(obj.value!=''){
			$.post('${ctx}/customer/manager/JSubSellRec.do?method=getAgentInfo&partner_id='+obj.value,function(result){
				$("#link_name_t").html(result.entry.link_name);
				$("#link_mobile_t").html(result.entry.link_mobile);
				var str = "";
				if(result.entry.map._PROVINCE!=null){
					str += result.entry.map._PROVINCE;
				}
				if(result.entry.map._CITY!=null){
					str += result.entry.map._CITY;
				}
				if(result.entry.map._COUNTRY!=null){
					str += result.entry.map._COUNTRY;
				}
				if(result.entry.map._TOWN!=null){
					str += result.entry.map._TOWN;
				}
				if(result.entry.consignee_street!=null){
					str += result.entry.consignee_street;
				}
				$("#send_addr_t").html(str);
			},'json');
		}else{
			$("#link_name_t").html("");
			$("#link_mobile_t").html("");
			$("#send_addr_t").html("");
		}
	}
	//交货方式选择
	function chSendType(obj){
		if(obj.checked==true){
			if(obj.id=='send_type1'){
				$("#send_type2").removeAttr("checked");
			}
			if(obj.id=='send_type2'){
				$("#send_type1").removeAttr("checked");
			}

		}
	}
$(document).ready(function(){
	$("#opr_date").attr("dataType", "Require").attr("msg", "请选择日期！");
	$("#partner_id").attr("dataType", "Require").attr("msg", "请选择供应商！");

	$("#addPdTD").click(function(){
		$("#tr_model").clone(true).attr("class","tr_pd").appendTo($("#showAddTrsTbody")).show();
		var lastTR = $("tr:last", "#showAddTrsTbody");
		

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
			if($(".rtable2 tr").length==2){
				$("#rec_money").val(0);
				$("#money").val(0);
				$("#dis_money").val(0);
			}
			$(".price").blur();  //重新计算总金额
			window.parent.resizeFrameHeight('mainFrame', 3);
	    }).css("cursor", "pointer");
		   
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});

	if("${details}" == 1){                      //修改页面
		$(".td_del").click(function(){
			$(this).parent().remove();
			if($(".rtable2 tr").length==2){
				$("#rec_money").val(0);
				$("#money").val(0);
				$("#dis_money").val(0);
			}
		});

		$(".tr_pd .td_pd .store_id").each(function(){
			//$(this).val($(this).attr("id"));
		});

		$(".tr_pd .td_pd .goods_id").each(function(){
			//$(this).val($(this).attr("id"));
		});
	}

	$(".store_id").change(function(){
		var $this = $(this);
		var $goods_id = $this.parent().parent().children('td').eq(2).children();
		var $stock = $this.parent().parent().children('td').eq(5).children();
		var $unit = $this.parent().parent().children('td').eq(4).children();
		var $price = $this.parent().parent().children('td').eq(7).children();
		var store_id = $this.val();
		var goods_id = $goods_id.val();
		var bill_type = "${af.map.bill_type}";

		setGoodsInfo($stock,$unit,$price,goods_id,bill_type,store_id);
	});

	$(".goods_id").change(function(){
		var $this = $(this);
		var $store = $this.parent().parent().children('td').eq(0).children();
		var $stock = $this.parent().parent().children('td').eq(5).children();
		var $unit = $this.parent().parent().children('td').eq(4).children();
		var $price = $this.parent().parent().children('td').eq(7).children();
		var goods_id = $this.val();
		var store_id = $store.val();
		var bill_type = "${af.map.bill_type}";
		setGoodsInfo($stock,$unit,$price,goods_id,bill_type,store_id);
	});

	$(".goods_sn").blur(function(){
		var $this = $(this);
		var goods_sn = $this.val();
		$.ajax({
			type: "POST",
			url: "JBill.do",
			data: {method : "ajaxSetGoodsBysn", "goods_sn": goods_sn},
			dataType: "json",
			cache:false,
			error: function(){alert("数据加载请求失败！");},
			success: function(result){
				var $goods_id = $this.parent().parent().children('td').eq(2).children();
				if(result.status == 0){
					$goods_id.val("");
				}else if(result.status == 1){
					$goods_id.val(result.goods_id);
				}
			}
	   });
	});

	$(".num").live("blur",function(){
		var num = $(this).val();
		var price = $(this).parent().parent().children('td').eq(7).children().val();
		if($.trim(num).length > 0 && $.trim(price).length > 0){
			 var money = num * price;
			 var $money = $(this).parent().parent().children('td').eq(8).children();
			 $money.val(money);   //金额

			 var total_money = 0;
			 $(".goods_money").each(function(){
				var goods_money = $(this).val();
				if($.trim(goods_money).length > 0 ){
					total_money = parseFloat(total_money) + parseFloat(goods_money);
				}
			 });
			 $("#rec_money").val(total_money);
			 $("#money").val(total_money);
			 $("#dis_money").val(0);

		}
	});

	$(".price").live("blur",function(){
		var price = $(this).val();
		var num = $(this).parent().parent().children('td').eq(6).children().val();
		if($.trim(num).length > 0 && $.trim(price).length > 0){
			 var money = num * price;
			 var $money = $(this).parent().parent().children('td').eq(8).children();
			 $money.val(money);   //金额

			 var total_money = 0;
			 $(".goods_money").each(function(){
					var goods_money = $(this).val();
					if($.trim(goods_money).length > 0 ){
						total_money = parseFloat(total_money) + parseFloat(goods_money);
					}
			 });
			 $("#rec_money").val(total_money);
			 $("#money").val(total_money);
			 $("#dis_money").val(0);
		}
	});


	//输入实收金额后，自动计算折扣金额
	$("#money").live("blur",function(){
		var rec = $("#rec_money").val();
		if($(this).val()==''){
		   return;
		}
		/*if(parseFloat($(this).val())>parseFloat(rec)){
			alert("实付金额不能大于应收金额！");
			$(this).val("");
			return;
		}*/
		$("#dis_money").val(rec-$(this).val());
	});

	$("#bill_memo").textbox({
		maxLength: 100,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_tip").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
		}
	}).blur(function() {
		$("#info_tip").slideUp("normal");
	});
	

	$("#btn_submit").click(function(){
		$(".tr_pd .goods_id").attr("dataType", "Require").attr("msg", "请选择！");
		$(".tr_pd .num").attr("dataType", "Require").attr("msg", "请填写！");
		$(".tr_pd .price").attr("dataType", "Require").attr("msg", "请填写！");

		var c1 = document.getElementById("send_type1").checked;
		var c2 = document.getElementById("send_type2").checked;
		if(c1==false&&c2==false){
			alert("请选择交货方式");
			return;
		}
		if($(".rtable2 tr").length<3){
			alert("商品明细不能为空！");
			return;
		}

		if(Validator.Validate(this.form, 3)){
			$("#tr_model").remove();
            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
            $("#btn_reset").attr("disabled", "true");
            $("#btn_back").attr("disabled", "true");
			this.form.submit();
		}
	});
});

function selectGoodType(obj){
	var $this = $(obj);
	var $goods_id = $this.parent().parent().children('td').eq(2).children();
	var $dw = $this.parent().parent().children('td').eq(4).children();
	var $stock = $this.parent().parent().children('td').eq(5).children();
	var $num = $this.parent().parent().children('td').eq(6).children();
	var $price = $this.parent().parent().children('td').eq(7).children();
	var $money = $this.parent().parent().children('td').eq(8).children();
	var type_id = $this.val();
	$.ajax({
		type: "POST",
		url: "JBill.do",
		data: {method : "ajaxGetGoodsList", "type_id": type_id},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(ret){
			if(ret){
				$goods_id.empty();
				var html = "<option value=''>请选择</option>";
				for(var i=0; i<ret.list.length; i++){
					if ("${af.map.bill_type}" == 10 && ret.list[i].is_konka == 1) continue; // 进货时过滤康佳产品
					html += "<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>";
				}
				$goods_id.html(html);

				$dw.html("");
				$stock.html("");
				$num.val("1");
				$price.val("");
				$money.val("");
				$(".price").blur();  //重新计算总金额
			}
		}
   });
}

function setGoodsInfo($stock,$unit,$price,goods_id,bill_type,store_id){
	$.ajax({
		type: "POST",
		url: "JBill.do",
		data: {method : "ajaxSetGoodsByid", "goods_id": goods_id, "bill_type": bill_type, "store_id": store_id},
		dataType: "json",
		cache:false,
		error: function(){alert("数据加载请求失败！");},
		success: function(result){
			if(result.status == 0){
				$price.val("");
				$unit.html("");
				$stock.html("");
				$(".price").blur();
			}else if(result.status == 1){
				$price.val(result.price);
				$unit.html(result.unit);
				$stock.html(result.stocks);
				$(".price").blur();
			}
		}
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
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))
			obj.value=obj.t_value;
		else
			obj.t_value=obj.value;
		if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))
			obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))
			obj.value=obj.t_value;
		else
			obj.t_value=obj.value;
		if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))
			obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))
			obj.value=obj.o_value;
		else{
			if(obj.value.match(/^\.\d+$/))
				obj.value=0+obj.value;
			if(obj.value.match(/^\.$/))
				obj.value=0;
			obj.o_value=obj.value;
		}
		if(isNaN(obj.value))
			obj.value = "";
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
		if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "1";
	});
}

//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
