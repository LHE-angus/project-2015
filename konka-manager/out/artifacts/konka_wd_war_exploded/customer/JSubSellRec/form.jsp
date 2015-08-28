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
<link href="${ctx}/commons/styles/select2.min.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.font-color{
		color:#202020
	}
</style>
</head>
<body style="font-family:Microsoft Yahei;font-size: 12px;">
<div class="oarcont">
  <div class="oartop">
    <table width="400" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2" style="padding: 0">
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JSubSellRec.do">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <html-el:hidden property="bill_id" value="${af.map.bill_id}"/>
      <html-el:hidden property="bill_type" value="${af.map.bill_type}"/>
      <html-el:hidden property="queryString" />
      <c:set var="readonly" value="${empty af.map.bill_id ? false : true}"/>
      <div style="overflow-x:auto;">
      <table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr>
          <td colspan="5"  bgcolor="#CCCCCC" style="font-weight:bold;"><span class="font-color">登记信息填写</span></td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td width="8%" nowrap="nowrap" class="title_item"><span style="color:red;">* </span>网&nbsp;&nbsp;&nbsp;&nbsp;点：</td>
          <td width="30%">
          <c:if test="${empty flag }">
	          <html-el:select property="partner_id" styleId="partner_id" onchange="getAgentInfo(this)">
	          		<html-el:option value="">请选择</html-el:option>
	          		<c:forEach var="cur" items="${jBasePartners}" varStatus="vs">
	          				<html-el:option value="${cur.partner_id}">${cur.partner_name}</html-el:option>
	          		</c:forEach>
	          </html-el:select>
	          &nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/AgentsList.do?method=add&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=110020200';return true;" style="color:blue;cursor:pointer;">新建网点</a>
          </c:if>
          <c:if test="${not empty flag }">${partner_name }</c:if>
          </td>
          <td width="8%" nowrap="nowrap" class="title_item"><span style="color:red;">* </span>${bill_sn_title}：</td>
          <td nowrap="nowrap">
          	<c:if test="${empty flag }">
          		<html-el:hidden property="bill_sn" styleId="bill_sn" />
          		${af.map.bill_sn }
          	</c:if>
          	<c:if test="${not empty flag }">${af.map.bill_sn }</c:if>
          </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item"><span style="color:red;">* </span>交货方式：</td>
          <td>
          	<c:if test="${empty flag }">
	          	<label><input type='checkbox' id="send_type1" name="send_type" value="0" onclick="chSendType(this)"></input>自提</label>
	          	&nbsp;&nbsp;
	          	<label><input type='checkbox' id="send_type2" name="send_type" value="1" onclick="chSendType(this)"></input>配送</label>
          	</c:if>
          	<c:if test="${not empty flag }">
          		<c:if test="${af.map.send_type eq 0 }">
		          	<label><input type='checkbox' id="send_type1" name="send_type" value="0" checked="checked" disabled="disabled"></input>自提</label>
		          	&nbsp;&nbsp;
		          	<label><input type='checkbox' id="send_type2" name="send_type" value="1" disabled="disabled"></input>配送</label>
          		</c:if>
          		<c:if test="${af.map.send_type eq 1 }">
		          	<label><input type='checkbox' id="send_type1" name="send_type" value="0" disabled="disabled"></input>自提</label>
		          	&nbsp;&nbsp;
		          	<label><input type='checkbox' id="send_type2" name="send_type" value="1" checked="checked" disabled="disabled"></input>配送</label>
          		</c:if>
          	</c:if>
          </td>
          <td nowrap="nowrap" class="title_item">
          	<span style="color:red;">* </span>创建日期：
          </td>
          <td>
          	<fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd" var="_opr_date"  />
          	<html-el:hidden property="opr_date" styleId="opr_date" value="${_opr_date}" />
          	${_opr_date }
          </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">联&nbsp;系&nbsp;人：</td>
          <td>
              	<span id='link_name_t'>${link_name }</span>
          </td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">联系电话：</td>
          <td>
          		<span id="link_mobile_t">${link_mobile }</span>
          </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">单据状态：</td>
          <td>
          	<c:if test="${af.map.bill_state eq 0 }">待确认</c:if>
          	<c:if test="${af.map.bill_state eq 1 }">已确认</c:if>
          	<html-el:hidden property="bill_state" styleId="bill_state" value="${af.map.bill_state }"></html-el:hidden>
          </td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">创&nbsp;建&nbsp;人：</td>
          <td>${add_user_name }</td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">网点地址：</td>
          <td colspan="3">
          	<span id="send_addr_t">${send_addr }</span>
          </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
          <td colspan="3">
          	<html-el:textarea property="bill_memo" cols="70" rows="3"></html-el:textarea>
          </td>
        </tr>
        <tr>
          <td colspan="5"  bgcolor="#CCCCCC">商品明细</td>
        </tr>
        <tr>
          <td colspan="5">
          	<table width="100%" border="1" cellpadding="0" cellspacing="1" class="rtable2">
          		<tr>
          			<td width="10%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${store_title}&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseStore.do?method=add&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010500';return true;" style="color:blue;cursor:pointer;">新建</a></td>
          			<td width="10%" align="center" nowrap="nowrap"><span style="color:red;">* </span>商品类型</td>
          			<td align="center" nowrap="nowrap"><span style="color:red;">* </span>商品&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseGoods.do?method=add&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010100';return true;" style="color:blue;cursor:pointer;">新建</a></td>
          			<td width="10%" align="center" nowrap="nowrap" style="display:none;">条码</td>
          			<td width="5%" align="center" nowrap="nowrap">单位</td>
          			<td width="6%" align="center" nowrap="nowrap">库存</td>
          			<td width="6%" align="center" nowrap="nowrap"><span style="color:red;">* </span>数量</td>
          			<td width="8%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${price_title}（元）</td>
          			<td width="8%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${goods_money_title}（元）</td>
          			<td width="8%" align="center" nowrap="nowrap"><span style="color:red;">* </span>折扣金额（元）</td>
          			<td width="8%" align="center" nowrap="nowrap"><span style="color:red;">* </span>应收金额（元）</td>
          			<td width="9%" align="center" nowrap="nowrap">行备注</td>
          			<td width="2%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
          		</tr>
          		<c:set var="details" />
          		<c:if test="${readonly}">
          			<html-el:hidden property="method_type" value="1" />
          			<c:forEach var="cur1" items="${detailsList}">
          			<c:set var="details" value="1" />
          			<tr class="tr_pd">
	          			<td align="center" class="td_pd" nowrap="nowrap">
	          				<select name="store_id" id="${cur1.store_id}" class="store_id" style="width:100px">
				          		<c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
				          			<option value="${cur.store_id}">${cur.store_name}</option>
				          		</c:forEach>
				          	</select>
				        </td>
	          			<td align="center" nowrap="nowrap">${cur1.map.type}</td>
	          			<td align="center" class="td_pd" nowrap="nowrap">${cur1.map.goods_name}</td>
	          			<td align="center" nowrap="nowrap"><span class="dw">${cur1.map.unit}</span></td>
	          			<td align="center" nowrap="nowrap"><input name="num" id="num" size="4" onfocus="javascript:setOnlyInt(this)" value="${cur1.num}" maxlength="6" class="num" /></td>
	          			<td align="center" nowrap="nowrap"><input name="price" id="price" size="6" value="${cur1.price}" onfocus="javascript:setOnlyNum(this);" maxlength="10" class="price" /></td>
	          			<td align="center" nowrap="nowrap"><input name="goods_money" value="${cur1.money}" size="6" class="goods_money" readonly="readonly" /></td>
	          			<td align="center" nowrap="nowrap"><input name="dis_money_d" value="${cur1.dis_money}" size="6" class="dis_money_d" readonly="readonly" onfocus="javascript:setOnlyNum(this);" maxlength="10" /></td>
	          			<td align="center" nowrap="nowrap"><input name="rec_money_d" value="${cur1.rec_money}" size="6" class="rec_money_d" readonly="readonly" onfocus="javascript:setOnlyNum(this);" maxlength="10"  /></td>
	          			<td align="center" nowrap="nowrap"><input name="notes"/></td>
	          			<td align="center" style="cursor:pointer;" class="td_del"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
	          		</tr>
	          		</c:forEach>
          		</c:if>
          		
          		<!-- 分销登记时默认第一行商品 start-->
          		<c:if test="${not readonly}">
	          		<html-el:hidden property="method_type" value="0" />
	          		<c:set var="details" value="0" />
	          		<tr class="tr_pd">
	          			<td align="center" nowrap="nowrap">
	          				<html-el:select property="store_id" styleClass="store_id" style="width:100px">
				          		<c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
				          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
				          		</c:forEach>
				          	</html-el:select>
				        </td>
				        <td align="center" nowrap="nowrap">
				        	<html-el:select property="type_id" styleClass="type_id" onchange="selectGoodType(this)" style="width:100px">
				        		<html-el:option value="">请选择</html-el:option>
				          		<c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
				          			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
				          		</c:forEach>
				          </html-el:select></td>
	          			<td align="center" nowrap="nowrap">
	          				<html-el:select property="goods_id" styleClass="goods_id" style="width:150px">
					          		<html-el:option value="">请选择</html-el:option>
					          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
					          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
					          		</c:forEach>
					          </html-el:select></td>
	          			<td align="center" nowrap="nowrap"><span class="dw"></span></td>
	          			<td align="center" nowrap="nowrap"><span class="stock"></span></td>
	          			<td align="center" nowrap="nowrap">
	          				<html-el:text property="num" styleId="num" size="4" onfocus="javascript:setOnlyInt(this)" value="1" maxlength="6" styleClass="num" />
	          			</td>
	          			<td align="center" nowrap="nowrap">
	          				<html-el:text property="price" styleId="price" size="6" onfocus="javascript:setOnlyPositiveNum(this);" maxlength="10" styleClass="price" />
	          			</td>
	          			<td align="center" nowrap="nowrap">
	          				<span>0</span>
	          				<html-el:hidden property="goods_money" styleClass="goods_money" />
	          			</td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="dis_money_d" size="6" styleClass="dis_money_d" onfocus="javascript:setOnlyPositiveNum(this);" maxlength="10" value="0"/></td>
		          		<td align="center" nowrap="nowrap">
		          			<span>0</span>
		          			<html-el:hidden property="rec_money_d" styleClass="rec_money_d" />
		          		</td>
	          			<td align="center" nowrap="nowrap"><html-el:text property="notes" styleClass="notes"></html-el:text></td>
	          			<td align="center" id="del"><img src="${ctx}/images/x_gray.gif" style="vertical-align:middle;top:0px;" /></td>
	          		</tr>
          		</c:if>
          		<!-- 分销登记时默认第一行商品 end -->
          		<!-- 添加商品行 start -->
          		<tr id="tr_model" style="display: none">
          			<td align="center" nowrap="nowrap">
          				<html-el:select property="store_id" styleClass="store_id" style="width:100px">
			          		<c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
			          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
			          		</c:forEach>
			          </html-el:select></td>
			        <td align="center" nowrap="nowrap">
			        	<html-el:select property="type_id" styleClass="type_id" onchange="selectGoodType(this)" style="width:100px">
			        		<html-el:option value="">请选择</html-el:option>
			          		<c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
			          			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
			          		</c:forEach>
			          </html-el:select></td>
          			<td align="center" nowrap="nowrap">
          				<html-el:select property="goods_id" styleClass="goods_id" style="width:150px">
				          		<html-el:option value="">请选择</html-el:option>
				          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
				          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
				          		</c:forEach>
				          </html-el:select></td>
          			<td align="center" nowrap="nowrap"><span class="dw"></span></td>
          			<td align="center" nowrap="nowrap"><span class="stock"></span></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="num" size="4" onfocus="javascript:setOnlyInt(this)" maxlength="6" value="1" styleClass="num" /></td>
          			<td align="center" nowrap="nowrap"><html-el:text property="price" size="6" onfocus="javascript:setOnlyPositiveNum(this);"
          			 maxlength="10" styleClass="price" /></td>
          			<td align="center" nowrap="nowrap">
          				<span>0</span>
	          			<html-el:hidden property="goods_money" styleClass="goods_money" />	
          			</td>
          			<td align="center" nowrap="nowrap"><html-el:text property="dis_money_d" size="6" styleClass="dis_money_d" readonly="false" onfocus="javascript:setOnlyPositiveNum(this);" maxlength="10" value="0"/></td>
	          		<td align="center" nowrap="nowrap">
	          			<span>0</span>
		          		<html-el:hidden property="rec_money_d" styleClass="rec_money_d" />
	          		</td>
          			<td align="center" nowrap="nowrap"><html-el:text property="notes" styleClass="notes"></html-el:text></td>
          			<td align="center" style="cursor:pointer;"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
          		</tr>
          		<!-- 添加商品行 end -->
          		<tbody id="showAddTrsTbody">
	          		<c:forEach var="dcur" items="${jbillDetailsList}" varStatus="dvs">
	          			<tr id="tr_model">
		          			<td align="center" nowrap="nowrap">
		          				<html-el:select property="store_id" styleClass="store_id" style="width:100px" value="${dcur.store_id }">
					          		<c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
					          			<html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
					          		</c:forEach>
					          </html-el:select></td>
					        <td align="center" nowrap="nowrap">
					        	<html-el:select property="type_id" styleClass="type_id" onchange="selectGoodType(this)" style="width:100px" value="${dcur.map.type_id }">
					        		<html-el:option value="">请选择</html-el:option>
					          		<c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
					          			<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
					          		</c:forEach>
					          </html-el:select></td>
		          			<td align="center" nowrap="nowrap">
		          				<html-el:select property="goods_id" styleClass="goods_id" style="width:150px" value="${dcur.map.goods_id }">
						          		<html-el:option value="">请选择</html-el:option>
						          		<c:forEach var="cur" items="${jBaseGoodsList}" varStatus="vs">
						          			<html-el:option value="${cur.goods_id}">${cur.goods_name}</html-el:option>
						          		</c:forEach>
						          </html-el:select></td>
		          			<td align="center" nowrap="nowrap"><span class="dw">${dcur.map.unit }</span></td>
		          			<td align="center" nowrap="nowrap"><span class="stock">${dcur.map.stocks }</span></td>
		          			<td align="center" nowrap="nowrap"><html-el:text property="num" size="4" onfocus="javascript:setOnlyInt(this)" maxlength="6" value="${dcur.num }" styleClass="num" /></td>
		          			<td align="center" nowrap="nowrap"><html-el:text property="price" size="6" onfocus="javascript:setOnlyPositiveNum(this);" maxlength="10" styleClass="price" value="${dcur.price }"/></td>
		          			<td align="center" nowrap="nowrap">
		          				<span>${dcur.map.sell_money }</span>
	          					<html-el:hidden property="goods_money" styleClass="goods_money" value="${dcur.map.sell_money }"/>
		          			</td>
		          			<td align="center" nowrap="nowrap"><html-el:text property="dis_money_d" size="6" styleClass="dis_money_d" readonly="false" onfocus="javascript:setOnlyPositiveNum(this);" maxlength="10" value="${dcur.dis_money }"/></td>
			          		<td align="center" nowrap="nowrap">
			          			<span>${dcur.rec_money }</span>
		          				<html-el:hidden property="rec_money_d" styleClass="rec_money_d" />
			          		</td>
		          			<td align="center" nowrap="nowrap"><html-el:text property="notes" styleClass="notes" value="${dcur.notes }"></html-el:text></td>
		          			<td align="center" style="cursor:pointer;"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
		          		</tr>
	          		</c:forEach>
          		</tbody>
          </table>
         </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px"><span class="font-color">总&nbsp;金&nbsp;额：</span></td>
          <td colspan="3">
          	<span id="sum_money_t">
          	<c:if test="${not empty af.map.sum_money }">${af.map.sum_money }</c:if>
          	<c:if test="${empty af.map.sum_money }">0</c:if>
          	</span>&nbsp;元
          	<html-el:hidden property="sum_money" styleId="sum_money" />
          </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">折扣金额：</td>
          <td colspan="3">
          	<span id="dis_money_t">
          	<c:if test="${not empty af.map.dis_money }">${af.map.dis_money }</c:if>
          	<c:if test="${empty af.map.dis_money }">0</c:if>
          	</span>&nbsp;元
          	<html-el:hidden property="dis_money" styleId="dis_money"  styleClass="webinput" value="0"/>
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<b>折扣率：&nbsp;</b>
          	<span id="discount_t">
          	<c:if test="${not empty af.map.discount }">${af.map.discount }</c:if>
          	<c:if test="${empty af.map.discount }">0</c:if>
          	</span>&nbsp;%
          	<html-el:hidden property="discount" styleId="discount" styleClass="webinput" />
          </td>
        </tr>
        <tr>
          <td width="10px"></td>
          <td nowrap="nowrap" class="title_item" style="padding-left: 14px">折后金额：</td>
          <td colspan="3">
          	<span id="rec_money_t">
          	<c:if test="${not empty af.map.rec_money }">${af.map.rec_money }</c:if>
          	<c:if test="${empty af.map.rec_money }">0</c:if>
          	</span>&nbsp;元
          	<html-el:hidden property="rec_money" styleId="rec_money" />
          </td>
        </tr>
        <tr>
          <td colspan="5" align="center">
          	<html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />&nbsp;
            <c:if test="${empty flag }">
            	<html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
            </c:if>
            <c:if test="${not empty detailsList or not empty flag}">
            	&nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
            </c:if>
          </td>
        </tr>
      </table>
      </div>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/select2.min.js?t=1"></script>
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
	$("#partner_id").attr("dataType", "Require").attr("msg", "请选择网点！");
	$("#sum_money").attr("dataType", "Require").attr("msg", "总金不能为空！");
	$("#dis_money").attr("dataType", "Require").attr("msg", "折扣金额不能为空！");
	$("#rec_money").attr("dataType", "Require").attr("msg", "应收金额不能为空！");
	$("#money").attr("dataType", "Require").attr("msg", "实收金额不能为空！");
	$("#addPdTD").click(function(){
		$("#tr_model").clone(true).attr("class","tr_pd").appendTo($("#showAddTrsTbody")).show();
		var lastTR = $("tr:last", "#showAddTrsTbody");
		

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
			$(".price").blur();  //重新计算总金额
			window.parent.resizeFrameHeight('mainFrame', 3);
	   }).css("cursor", "pointer");
		   
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});

	if("${details}" == 1){                      //修改页面
		$(".td_del").click(function(){
			$(this).parent().remove();	
		});

		$(".tr_pd .td_pd .store_id").each(function(){
			$(this).val($(this).attr("id"));
		});

		$(".tr_pd .td_pd .goods_id").each(function(){
			$(this).val($(this).attr("id"));
		});
	}

	$(".store_id").change(function(){
		var $this = $(this);
		var $goods_id = $this.parent().parent().children('td').eq(2).children();
		var $stock = $this.parent().parent().children('td').eq(5).children();
		var $unit = $this.parent().parent().children('td').eq(4).children();
		var $price = $this.parent().parent().children('td').eq(7).children();
		var $dis_money_d = $this.parent().parent().children('td').eq(9).children();
		$dis_money_d.val(0);
		var store_id = $this.val();
		var goods_id = $goods_id.val();
		var bill_type = "${af.map.bill_type}";

		setGoodsInfo($stock,$unit,$price,goods_id,bill_type,store_id);
	});

	
	//选择商品后
	$(".goods_id").change(function(){
		var $this = $(this);
		var $store = $this.parent().parent().children('td').eq(0).children();
		var $num = $this.parent().parent().children('td').eq(5).children();
		var $stock = $this.parent().parent().children('td').eq(4).children();
		var $unit = $this.parent().parent().children('td').eq(3).children();
		var $price = $this.parent().parent().children('td').eq(6).children();
		var $sale_money = $this.parent().parent().children('td').eq(7).children("input");
		var $sale_money_t = $this.parent().parent().children('td').eq(7).children("span");
		var $rec_money = $this.parent().parent().children('td').eq(9).children("input");
		var $rec_money_t = $this.parent().parent().children('td').eq(9).children("span");
		var $dis_money_d = $this.parent().parent().children('td').eq(8).children();
		$dis_money_d.val(0);
		var goods_id = $this.val();
		var store_id = $store.val();
		var bill_type = "${af.map.bill_type}";
		if(goods_id==''){
			$unit.html("");
			$stock.html("");
			$price.val("");
			$num.val("1");
			$sale_money.val("0");
			$rec_money.val("0");
			return;	
		}
		setGoodsInfo($stock,$unit,$price,goods_id,bill_type,store_id,$sale_money,$sale_money_t,$num,$rec_money,$rec_money_t,$dis_money_d);
	});

	//输入数量后
	$(".num").blur(function(){
		if($(this).val()==''||$(this).val()=='0'){
			$(this).val(1);
		}
		var num = $(this).val();
		var price = $(this).parent().parent().children('td').eq(6).children().val();
		 var goods_moneys = num * price;
		 var $goods_moneys = $(this).parent().parent().children('td').eq(7).children("input");
		 $goods_moneys.val(goods_moneys.toFixed(2));   //金额
		 var $goods_moneys_t = $(this).parent().parent().children('td').eq(7).children("span");
		 $goods_moneys_t.html(goods_moneys.toFixed(2));
		 
		 var total_money = 0;
		 $(".goods_money").each(function(){
			var goods_money = $(this).val();
			if($.trim(goods_money).length > 0 ){
				total_money = parseFloat(total_money) + parseFloat(goods_money);
			}
		 });
		 $("#sum_money_t").html(total_money.toFixed(2));
		 $("#sum_money").val(total_money.toFixed(2));
		 
		 var $dis_money_d = $(this).parent().parent().children('td').eq(8).children();
		 var rec_money_d = num * price - $dis_money_d.val();//应付金额
		 var $rec_money_d = $(this).parent().parent().children('td').eq(9).children("input");
		 $rec_money_d.val(rec_money_d.toFixed(2));
		 var $rec_money_d_t = $(this).parent().parent().children('td').eq(9).children("span");
		 $rec_money_d_t.html(rec_money_d.toFixed(2));

		 var dis_money = 0;//折扣总金额赋值
		 $(".dis_money_d").each(function(){
				var dis_money_ds = $(this).val();
				if($.trim(dis_money_ds).length > 0 ){
					dis_money = parseFloat(dis_money) + parseFloat(dis_money_ds);
				}
		 });
		 $("#dis_money").val(dis_money.toFixed(2));
		 $("#dis_money_t").html(dis_money.toFixed(2));
		 $("#rec_money").val((total_money-dis_money).toFixed(2));
		 $("#rec_money_t").html((total_money-dis_money).toFixed(2));
		 
		 if(total_money==0){
			 $("#discount").val("100");
			 $("#discount_t").html("100");
		 }else{
			 $("#discount").val((dis_money/total_money*100).toFixed(2));
			 $("#discount_t").html((dis_money/total_money*100).toFixed(2));
		 }
	});

	//输入单价后
	$(".price").blur(function(){
		var price = $(this).val();
		var num = $(this).parent().parent().children('td').eq(5).children().val();
		 var goods_moneys = num * price;
		 var $goods_moneys = $(this).parent().parent().children('td').eq(7).children("input");
		 $goods_moneys.val(goods_moneys.toFixed(2));   //单个销售金额
		 var $goods_moneys_t = $(this).parent().parent().children('td').eq(7).children("span");
		 $goods_moneys_t.html(goods_moneys.toFixed(2));
		 
		 var $dis_money_d = $(this).parent().parent().children('td').eq(8).children();
		 var rec_money_d = num * price - $dis_money_d.val();//应付金额
		 var $rec_money_d = $(this).parent().parent().children('td').eq(9).children("input");
		 $rec_money_d.val(rec_money_d.toFixed(2));
		 var $rec_money_d_t = $(this).parent().parent().children('td').eq(9).children("span");
		 $rec_money_d_t.html(rec_money_d.toFixed(2));
	
		 var total_money = 0;
		 $(".goods_money").each(function(){
				var goods_money = $(this).val();
				if($.trim(goods_money).length > 0 ){
					total_money = parseFloat(total_money) + parseFloat(goods_money);
				}
		 });
		 $("#sum_money_t").html(total_money.toFixed(2));
		 $("#sum_money").val(total_money.toFixed(2));
	
		 var dis_money = 0;//折扣总金额赋值
		 $(".dis_money_d").each(function(){
				var dis_money_ds = $(this).val();
				if($.trim(dis_money_ds).length > 0 ){
					dis_money = parseFloat(dis_money) + parseFloat(dis_money_ds);
				}
		 });
		 $("#dis_money").val(dis_money.toFixed(2));
		 $("#dis_money_t").html(dis_money.toFixed(2));
		 
		 $("#rec_money").val((total_money-dis_money).toFixed(2));
		 $("#rec_money_t").html((total_money-dis_money).toFixed(2));
		 
		 if(total_money==0){
			 $("#discount").val("100");
			 $("#discount_t").html("100");
		 }else{
			 $("#discount").val((dis_money/total_money*100).toFixed(2));
			 $("#discount_t").html((dis_money/total_money*100).toFixed(2));
		 }
	});

	//输入折扣金额后
	$(".dis_money_d").blur(function(){
		if($(this).val()==''){
			$(this).val("0");
		}
		var dis_money_d = $(this).val();
		var num = $(this).parent().parent().children('td').eq(5).children().val();
		var price = $(this).parent().parent().children('td').eq(6).children().val();
		if($.trim(num).length > 0 && $.trim(price).length > 0 && $.trim(dis_money_d).length > 0){
			 var rec_money_d = num * price - dis_money_d;//应付金额
			 var $rec_money_d = $(this).parent().parent().children('td').eq(9).children("input");
			 $rec_money_d.val(rec_money_d.toFixed(2));
			 var $rec_money_t = $(this).parent().parent().children('td').eq(9).children("span");
			 $rec_money_t.html(rec_money_d.toFixed(2));

			 var dis_money = 0;//折扣总金额赋值
			 $(".dis_money_d").each(function(){
					var dis_money_ds = $(this).val();
					if($.trim(dis_money_ds).length > 0 ){
						dis_money = parseFloat(dis_money) + parseFloat(dis_money_ds);
					}
			 });
			 $("#dis_money").val(dis_money.toFixed(2));
			 $("#dis_money_t").html(dis_money.toFixed(2));
			 
			 var rec_money = 0;//应付总金额赋值
			 $(".rec_money_d").each(function(){
					var rec_money_ds = $(this).val();
					if($.trim(rec_money_ds).length > 0 ){
						rec_money = parseFloat(rec_money) + parseFloat(rec_money_ds);
					}
			 });
			 $("#rec_money").val(rec_money.toFixed(2));
			 $("#rec_money_t").html(rec_money.toFixed(2));
			 
			 var total_money = 0;//计算总金额
			 $(".goods_money").each(function(){
					var goods_money = $(this).val();
					if($.trim(goods_money).length > 0 ){
						total_money = parseFloat(total_money) + parseFloat(goods_money);
					}
			 });
			 $("#sum_money_t").html(total_money.toFixed(2));
			 $("#sum_money").val(total_money.toFixed(2));
			 
			 if(total_money==0){
				 $("#discount").val("100");
				 $("#discount_t").html("100");
			 }else{
				 $("#discount").val((dis_money/total_money*100).toFixed(2));
				 $("#discount_t").html((dis_money/total_money*100).toFixed(2));
			 }
		}
	});

	var f  = document.forms[0];
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
		if(Validator.Validate(this.form, 3)){
			if($(".tr_pd .goods_id")!=null){
				var goods_nums = "";
				$(".tr_pd .goods_id").each(function(){
					var goods_id= $(this).val();
					var num = $(this).parent().parent().children('td').eq(5).children().val();
					var store_id = $(this).parent().parent().children('td').eq(0).children().val();
					goods_nums = goods_nums + $.trim(goods_id+':'+num+':'+store_id+',');
				});
				$.ajax({
					type: "POST",
					url: "JSubSellRec.do",
					data: {method : "ajaxGetStockList", "goods_nums": goods_nums},
					dataType: "text",
// 					cache:false,
					error: function(){alert("数据加载请求失败！");return false;},
					success: function(result){
						if(result!=null && result.length>0){
							alert(result);
							return false;
						}
						$("#tr_model").remove();
			            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
			            $("#btn_reset").attr("disabled", "true");
			            $("#btn_back").attr("disabled", "true");
						f.submit();
					}
			   });
			}
		}
	});
	
});

function selectGoodType(obj){
	var $this = $(obj);
	var $goods_id = $this.parent().parent().children('td').eq(2).children();
	var $dw = $this.parent().parent().children('td').eq(3).children();
	var $stock = $this.parent().parent().children('td').eq(4).children();
	var $num = $this.parent().parent().children('td').eq(5).children();
	var $price = $this.parent().parent().children('td').eq(6).children();
	var $money = $this.parent().parent().children('td').eq(7).children("input");
	var $money_t = $this.parent().parent().children('td').eq(7).children("span");
	var $dis_money_d = $this.parent().parent().children('td').eq(8).children();
	$dis_money_d.val(0);
	var $rec_money_d = $this.parent().parent().children('td').eq(9).children("input");
	var $rec_money_d_t = $this.parent().parent().children('td').eq(9).children("span");
	$rec_money_d.val(0);
	$rec_money_d_t.html(0);
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
				$goods_id.append("<option value=''>请选择</option>");
				for(var i=0; i<ret.list.length; i++){			
					$goods_id.append("<option value='" + ret.list[i].goods_id + "'>" + ret.list[i].goods_name +"</option>");
				}
// 				$goods_id.html(html);

				$dw.html("");
				$stock.html("");
				$num.val("1");
				$price.val("");
				$money.val("");
				$money_t.html("");
				$(".price").blur();  //重新计算总金额
			}
		}
   });
}

//获取商品信息
function setGoodsInfo($stock,$unit,$price,goods_id,bill_type,store_id,$sale_money,$sale_money_t,$num,$rec_money,$rec_money_t,$dis_money_d){
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
				$sale_money.val(result.price*$num.val());
				$sale_money_t.html(result.price*$num.val());
				$rec_money.val(result.price*$num.val()-$dis_money_d.val());
				$rec_money_t.html(result.price*$num.val()-$dis_money_d.val());
				$unit.html(result.unit);
				$stock.html(result.stocks);
				$(".price").blur();
			}
		}
	});
}
//正则表达式：只能输入数字（不包括负数）
function setOnlyPositiveNum(obj) {
	if(obj.value==0){
		obj.value="";
	}
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

//正则表达式：只能输入数字（包括负数）
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

//正则表达式：只能输入正数字
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


//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>