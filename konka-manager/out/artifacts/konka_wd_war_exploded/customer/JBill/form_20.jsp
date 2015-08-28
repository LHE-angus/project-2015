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
<style type="text/css">
.tab {
	margin-bottom:5px;
}
.tab td {
	padding:5px;
}
.titleTab {
	border-bottom:1px solid #CCC;
	margin-top:10px;
	font-weight:800;
	font-size:12px;
}
.item-title {
	text-align:right;
	font-weight:700;
	white-space:nowrap;
}
input[type='text'][readonly] {
	background-color:#eee;
	border:1px solid #ccc;
}
span.sel-tab {
	height: 30px;
	display: block;
	float : right;
	padding: 0px 10px;
	margin-top: 2px;
	margin-right: 10px;
	border-radius:3px 3px 0px 0px;
}
span.sel-tab-active {
	border-bottom : 0px;
	border-top: 1px solid #ccc;
	border-left: 1px solid #ccc;
	border-right: 1px solid #ccc;
	background-color: #FFF;
}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div class="oarcont">
  <div class="oartop" >
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}
          <c:if test="${isFanLiZmd}">
          	<span class="sel-tab sel-tab-active">一步价经营模式</span>
          	<span class="sel-tab"><a href="${ctx}/customer/manager/KonkaXxZmdAddSalesOrder.do?method=add&mod_id=${af.map.mod_id}">分公司返利经营模式</a></span>
          </c:if>
        </td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2" style="padding-left: 10px; padding-right: 10px">
    <!-- <div align="right">进入<a href="${ctx}/customer/manager/JBill.do?method=list&bill_type=${af.map.bill_type}&mod_id=${af.map.history_list_mod_id}" ><font style="font-weight:bold;">${j_bill_history_list_title}</font></a>页面</div> -->
    <%@ include file="/commons/pages/messages.jsp" %>
    <html-el:form action="/manager/JBill.do">
      <html-el:hidden property="method" value="save"/>
      <html-el:hidden property="partner_id" styleId="partner_id" value="${af.map.jBasePartner.partner_id}" />
      <html-el:hidden property="bill_id" value="${af.map.bill_id}"/>
      <html-el:hidden property="mod_id" value="${af.map.mod_id}"/>
      <html-el:hidden property="bill_type" value="${af.map.bill_type}"/>
      <html-el:hidden property="c_id" value="${af.map.c_id}"/>
      <html-el:hidden property="queryString" />
      <c:set var="readonly" value="${empty af.map.bill_id ? false : true}"/>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab">
        <!-- <tr id="partner_tip" style="display:${empty af.map.jBasePartner.partner_id ? 'none' : ''};">
          <td colspan="6" style="background-color:#F2F5A9;border:1px solid #F3E2A9;">注：您已选择了供应商（<span id="partner_id_2">${af.map.jBasePartner.partner_sn}</span>），修改将会覆盖其系统中的信息。<a href="javascript:void(0);" onclick="clearPartnerInfo();" style="float:right;">重新填写</a></td>
        </tr> -->
        <tr>
          <td width="10%" class="item-title">销售日期：</td>
          <td width="30%">
            <fmt:formatDate value="${af.map.opr_date}" pattern="yyyy-MM-dd" var="_opr_date" />
            ${_opr_date}
            <html-el:hidden property="opr_date" styleId="opr_date" value="${_opr_date}"/>
          </td>
          <td width="10%" class="item-title">零售单号：</td>
          <td width="10%">
            ${af.map.bill_sn }
            <html-el:hidden property="bill_sn" styleId="bill_sn" />
          </td>
          <td width="10%" class="item-title">制单人：</td>
          <td width="10%">
            ${empty customerUserInfo.real_name ? customerUserInfo.user_name : customerUserInfo.real_name}
          </td>
        </tr>
        <tr>
          <td class="item-title">销售单位：</td>
          <td colspan="5">
          	<html-el:select property="xs_id" styleId="xs_id">
          		<c:forEach var="cur" items="${partnerList}" varStatus="vs">
                  <html-el:option value="${cur.ID}">${cur.NAME}</html-el:option>
                </c:forEach>
          	</html-el:select>
          </td>
        </tr>
        <tr class="consignee">
          <td width="10%" class="item-title"><span style="color:red;">* </span><span id="partner_name">顾客姓名</span>：</td>
          <td><html-el:text property="link_name" size="20" maxlength="20" styleId="link_name" value="${af.map.jBasePartner.link_name}" onblur="this.form.consignee_name.value = this.value;" />
            <span style="display:none;"><input type="button" name="sel_partener" id="sel_partener" value=" 选择 " /></span>
            <span id="loading" style="display:none;"><img src="${ctx}/images/ajax-loader.gif" /></span></td>
          <td width="10%" class="item-title"><span style="color:red;">* </span>联系电话：</td>
          <td><html-el:text property="link_mobile" size="20" maxlength="20" styleId="link_mobile" value="${af.map.jBasePartner.link_mobile}" onblur="this.form.consignee_tel.value = this.value;" /></td>
          <td width="10%" class="item-title"></td>
          <td nowrap="nowrap">
          	<html-el:hidden property="partner_sn" styleId="partner_sn" value="${af.map.jBasePartner.partner_sn}" />
          </td>
        </tr>
        <tr>
          <td width="10%" class="item-title">收货人：</td>
          <td width="20%" class="consignee"><html-el:text property="consignee_name" size="20" maxlength="20" styleId="consignee_name" value="${af.map.jBasePartner.consignee_name}" /></td>
          <td width="10%" class="item-title">收货人电话：</td>
          <td width="20%" class="consignee"><html-el:text property="consignee_tel" size="20" maxlength="20" styleId="consignee_tel" value="${af.map.jBasePartner.consignee_tel}" /></td>
          <td width="10%" class="item-title">预约送货时间：</td>
          <td width="30%">
          	<fmt:formatDate value="${af.map.plan_hand_time}" pattern="yyyy-MM-dd" var="_plan_hand_time" />
            <html-el:text property="plan_hand_time" styleId="plan_hand_time" value="${_plan_hand_time }" size="10" maxlength="10" readonly="true" title="点击选择日期" styleClass="Wdate" style="cursor:pointer;" onclick="WdatePicker({readOnly:true})" />
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="item-title"><span style="color:red;">* </span>交货方式：</td>
          <td colspan="5">
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
        </tr>
        <tr class="consignee">
          <td class="item-title">送货地址：</td>
          <td colspan="5">
          	<html-el:select property="province" styleId="province" style="width:130px;"></html-el:select>
            &nbsp;
            <html-el:select property="city" styleId="city" style="width:130px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
            &nbsp;
            <html-el:select property="country" styleId="country" style="width:100px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
            &nbsp;
            <html-el:select property="town" styleId="town" style="width:100px;">
            	<html-el:option value="">-请选择-</html-el:option>
            </html-el:select>
            &nbsp;<html-el:text property="consignee_street" size="50" maxlength="50" styleId="consignee_street" value="${af.map.jBasePartner.consignee_street}" />
          </td>
        </tr>
        <tr>
          <td class="item-title">开票开具：</td>
          <td><html-el:text property="bills_title" size="30" maxlength="20" styleId="bills_title" /></td>
          <td class="item-title">单据摘要：</td>
          <td colspan="3"><html-el:text property="bills_sumary" size="40" maxlength="40" styleId="bills_sumary" /></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab titleTab">
        <tr>
          <td>商品明细</td>
        </tr>
      </table>
      <div style="overflow-x:auto;">
          <table class="rtable2" id="detailstable">
              <tr style="height: 50px">
                <td width="9%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${store_title}&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseStore.do?method=add&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010500';return true;" style="color:blue;cursor:pointer;">新建</a></td>
                <td width="10%" align="center" nowrap="nowrap"><span style="color:red;">* </span>商品类型</td>
                <td align="center" nowrap="nowrap"><span style="color:red;">* </span>商品&nbsp;&nbsp;<a onclick="javascript:location.href='${ctx}/customer/manager/JBaseGoods.do?method=add&returnUrl=' + encodeURIComponent(location.href) + '&mod_id=199010100';return true;" style="color:blue;cursor:pointer;">新建</a></td>
                <td width="10%" align="center" nowrap="nowrap" style="display:none;">条码</td>
                <td width="3%" align="center" nowrap="nowrap">单位</td>
                <td width="6%" align="center" nowrap="nowrap">库存</td>
                <td width="6%" align="center" nowrap="nowrap"><span style="color:red;">* </span>数量</td>
                <td width="8%" align="center" nowrap="nowrap"><span style="color:red;">* </span>${price_title}（元）</td>
                <td width="8%" align="center" nowrap="nowrap">${goods_money_title}（元）</td>
                <td width="6%" align="center" nowrap="nowrap">赠品</td>
                <td width="6%" align="center" nowrap="nowrap">赠品数量</td>
                <td width="6%" align="center" nowrap="nowrap">赠品说明</td>
                <td width="6%" align="center" nowrap="nowrap">行备注</td>
                <td width="5%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
              </tr>
              <c:set var="details" />
              <c:if test="${readonly}">
                <html-el:hidden property="method_type" value="1" />
                <c:forEach var="cur1" items="${detailsList}">
                  <c:set var="details" value="1" />
                  <tr class="tr_pd">
                    <td width="17%" align="center" class="td_pd" nowrap="nowrap">
                    	<html-el:select property="store_id" styleClass="store_id" value="${cur1.store_id }" style="width:80px" >
                        <c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
                          <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
                        </c:forEach>
                      </html-el:select></td>
                    <td align="center" nowrap="nowrap">
                    	<html-el:select property="type_id" styleClass="type_id"  value="${cur1.type_id }" onchange="selectGoodType(this)" style="width:90px">
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
                    <td align="center" nowrap="nowrap" style="display:none;"><input name="goods_sn" size="20" value="${cur1.map.goods_sn}" maxlength="30" class="goods_sn"  /></td>
                    <td align="center" nowrap="nowrap"><span class="dw">${cur1.map.unit}</span></td>
                    <td align="center" nowrap="nowrap"><span class="dw">${cur1.map.stocks}</span></td>
                    <td align="center" nowrap="nowrap"><input name="num" id="num" size="10" onfocus="javascript:setOnlyInt(this)" value="${cur1.num}" maxlength="4" class="num"  /></td>
                    <td align="center" nowrap="nowrap"><input name="price" id="price" size="10" value="${cur1.price}" onfocus="javascript:setOnlyNum(this);" maxlength="10" class="price"  /></td>
                    <td align="center" nowrap="nowrap"><input name="goods_money" value="${cur1.money}" class="goods_money"  size="10" /></td>
                    <td align="center" nowrap="nowrap">
                    	<html-el:select property="gift_id" styleClass="gift_id" value="${cur1.gift_id }">
		                    <html-el:option value="">选择</html-el:option>
		                  	<c:forEach var="c" items="${giftInfoList}">
		                   		<html-el:option value="${c.gift_id}">[${c.map.c_name}]${c.gift_name}</html-el:option>
		                   	</c:forEach>
		                   	<html-el:option value="-1">其他</html-el:option>
	                   	</html-el:select>
                    </td>
                    <td align="center" nowrap="nowrap"><input name="gift_count" id="gift_count" value="${cur1.gift_count}"  size="5" maxlength="4" /></td>
                    <td align="center" nowrap="nowrap"><input name="gift_desc" id="gift_desc" value="${cur1.gift_desc}"  size="10" /></td>
                   	<td align="center" nowrap="nowrap"><input name="notes" id="notes" value="${cur1.notes}"  size="10"  /></td>
                    <td align="center" style="cursor:pointer;" class="td_del"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
                  </tr>
                </c:forEach>
              </c:if>
              <c:if test="${not readonly}">
                <html-el:hidden property="method_type" value="0" />
                <c:set var="details" value="0" />
                <tr class="tr_pd">
                  <td align="center" nowrap="nowrap">
                  	<html-el:select property="store_id" styleClass="store_id" style="width:80px">
                      <c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
                        <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
                      </c:forEach>
                    </html-el:select></td>
                  <td align="center" nowrap="nowrap">
                  	<html-el:select property="type_id" styleClass="type_id" onchange="selectGoodType(this)" style="width:90px">
                      <html-el:option value="">请选择</html-el:option>
                      <c:forEach var="cur" items="${jBaseTypes}" varStatus="vs">
                        <html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>
                      </c:forEach>
                    </html-el:select></td>
                  <td align="center" nowrap="nowrap">
                  	<html-el:select property="goods_id" styleClass="goods_id" style="width:150px">
                      <html-el:option value="">请选择</html-el:option>
                    </html-el:select></td>
                  <td align="center" nowrap="nowrap" style="display:none;"><html-el:text property="goods_sn" size="20" maxlength="30" styleClass="goods_sn" /></td>
                  <td align="center" nowrap="nowrap"><span class="dw"></span></td>
                  <td align="center" nowrap="nowrap"><span class="stock"></span></td>
                  <td align="center" nowrap="nowrap"><html-el:text property="num" styleId="num" size="4" onfocus="javascript:setOnlyInt(this)" value="1" maxlength="6" styleClass="num" /></td>
                  <td align="center" nowrap="nowrap"><html-el:text property="price" styleId="price" size="6" onfocus="javascript:setOnlyNum(this);"
          			 maxlength="10" styleClass="price" /></td>
                  <td align="center" nowrap="nowrap"><html-el:text property="goods_money" styleClass="goods_money" size="6" readonly="true" /></td>
                  <td align="center" nowrap="nowrap">
                    <select name="gift_id">
                    <option value="">选择</option>
                  	<c:forEach var="c" items="${giftInfoList}">
                   		<option value="${c.gift_id}">[${c.map.c_name}]${c.gift_name}</option>
                   	</c:forEach>
                   	<option value="-1">其他</option>
                   	</select>
                  </td>
                  <td align="center" nowrap="nowrap"><html-el:text property="gift_count" styleClass="gift_count" size="5" maxlength="4" onfocus="javascript:setOnlyNum(this)" /></td>
                  <td align="center" nowrap="nowrap"><html-el:text property="gift_desc" styleClass="gift_desc" size="10" /></td>
                  <td align="center" nowrap="nowrap"><html-el:text property="notes" styleClass="notes" size="10" /></td>
                  <td align="center" id="del"><img src="${ctx}/images/x_gray.gif" style="vertical-align:middle;top:0px;" /></td>
                </tr>
              </c:if>
              <tr id="tr_model" style="display:none;">
                <td align="center" nowrap="nowrap">
                  <html-el:select property="store_id" styleClass="store_id" style="width:80px">
                    <c:forEach var="cur" items="${jBaseStores}" varStatus="vs">
                      <html-el:option value="${cur.store_id}">${cur.store_name}</html-el:option>
                    </c:forEach>
                  </html-el:select></td>
                <td align="center" nowrap="nowrap">
                  <html-el:select property="type_id" styleClass="type_id" onchange="selectGoodType(this)" style="width:90px">
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
                <td align="center" nowrap="nowrap"><html-el:text property="num" size="4" onfocus="javascript:setOnlyInt(this)" maxlength="6" value="1" styleClass="num" /></td>
                <td align="center" nowrap="nowrap"><html-el:text property="price" size="6" onfocus="javascript:setOnlyNum(this);"
          			 maxlength="10" styleClass="price" /></td>
                <td align="center" nowrap="nowrap"><html-el:text property="goods_money" size="6" styleClass="goods_money" readonly="true" /></td>
                <td align="center" nowrap="nowrap">
                    <select name="gift_id">
                    <option value="">选择</option>
                  	<c:forEach var="c" items="${giftInfoList}">
                   		<option value="${c.gift_id}">[${c.map.c_name}]${c.gift_name}</option>
                   	</c:forEach>
                   	<option value="-1">其他</option>
                   	</select></td>
                <td align="center" nowrap="nowrap"><html-el:text property="gift_count" size="5" styleClass="gift_count" maxlength="5" onfocus="javascript:setOnlyNum(this)" /></td>
                <td align="center" nowrap="nowrap"><html-el:text property="gift_desc" size="10" styleClass="gift_desc" /></td>
                <td align="center" nowrap="nowrap"><html-el:text property="notes" size="10" styleClass="notes" /></td>
                <td align="center" style="cursor:pointer;"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
              </tr>
              <tbody id="showAddTrsTbody">
              </tbody>
            </table>
            <br/>
      	</div>
     <c:if test="${af.map.bill_type eq 20}">
      	<div style="color:#F00;">注：康佳产品销售数据来自促销员、业务员通过手机端或电脑端的零售通上报，请慎重填写，以防重复上报销售数据。</div>
      </c:if>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab">
        <tr>
          <td nowrap="nowrap" class="title_item">${rec_money_title}：
          	<span id="rec_money_t">
          	<c:if test="${not empty af.map.rec_money }">${af.map.rec_money }</c:if>
          	<c:if test="${empty af.map.rec_money }">0</c:if>
          	</span>
            <html-el:hidden property="rec_money" styleId="rec_money"/>&nbsp;元
            <html-el:hidden property="money" styleId="money"/>
          </td>
<!--           <td nowrap="nowrap" class="title_item">折扣金额： -->
<%--             <html-el:text property="dis_money" styleId="dis_money" size="10" styleClass="webinput" onfocus="javascript:setOnlyNum(this)" onblur="setMoney(this)" maxlength="10" value="0"/> --%>
<!--             &nbsp;元</td> -->
<!--           <td nowrap="nowrap" class="title_item">折扣率： -->
<%--             <html-el:text property="discount" styleId="discount" size="10" onfocus="javascript:setOnlyNum(this)" onblur="setMoney(this)" styleClass="webinput" maxlength="5"/> --%>
<!--             &nbsp;%</td> -->
        </tr>
<!--         <tr> -->
<%--         	<td nowrap="nowrap" class="title_item" colspan="3">${money_title}： --%>
<!--         		<span id="money_t"> -->
<%--         		<c:if test="${not empty af.map.money }">${af.map.money }</c:if> --%>
<%--           		<c:if test="${empty af.map.money }">0</c:if> --%>
<!--         		</span> -->
<%--             	<html-el:hidden property="money" styleId="money"/>&nbsp;元 --%>
<!--             </td> -->
<!--         </tr> -->
      </table>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab titleTab">
        <tr>
          <td>备注</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab">
        <tr>
          <td colspan="2"><html-el:textarea property="bill_memo" styleId="bill_memo" rows="3" styleClass="webinput" style="width:100%;"/></td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          	<html-el:button property="" value="提 交" styleClass="but4" styleId="btn_submit" />&nbsp;
            <html-el:button property="" value="重 置" styleClass="but5" styleId="btn_reset" onclick="this.form.reset();" />
            <c:if test="${not empty detailsList}">
              &nbsp;<html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back();" />
            </c:if></td>
        </tr>
      </table>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript">//<![CDATA[
function clearPartnerInfo() {
	$(".consignee").find("input[type='text'], select").val("");
	$("#partner_id").val("");
	$("#partner_tip").slideUp();
	genSN();
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
	
	$("#sel_partener").click(function(){
		$("#loading").show();
		var partner_id = window.showModalDialog("<c:url value="/customer/manager/JBasePartner.do?method=list_select" />", window, "dialogWidth:1024px;dialogHeight:500px;");
		if (!partner_id) partner_id = window.returnValue;
		$.post("${ctx}/customer/manager/JBasePartner.do?method=ajaxGetJBasePartner", { partner_id : partner_id }, function(json){
			$("#partner_id").val(partner_id); // 往来单位ID
			$("#partner_id_2").text(json.partner_sn);
			$("#partner_sn").val(json.partner_sn);
			$("#link_name").val(json.link_name);
			$("#link_mobile").val(json.link_mobile);
			$("#consignee_name").val(json.consignee_name);
			$("#consignee_tel").val(json.consignee_tel);
			$("#consignee_street").val(json.consignee_street);
			$("#partner_name").html((json.partner_obj == 0) ? '顾客姓名' : '单位名称');
			$("#partner_tip").slideDown();
			
			var p_index = json.consignee_p_index;
			var province = p_index.substr(0, 2).concat("0000");
			var city = p_index.substr(0, 4).concat("00");
			var country = p_index.substr(0, 6);
			var town = (p_index.length == 8) ? p_index : "";
			$("#province").attr({"selectedValue": province});
			$("#city").attr({"selectedValue": city});
			$("#country").attr({"selectedValue": country});
			$("#town").attr({"selectedValue": town});
			$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
			$("#province").change();
		});
		$("#loading").hide();

		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});

	$("#province").attr({"subElement": "city", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country").attr({"subElement": "town","defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.town}"});
	$("#province").cs_ext("${ctx}/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();
	
	$("#consignee_street").attr("placeholder", "请输入街道地址：格式：X街道X号X小区X栋X单元X");
	$("#bills_title").attr("placeholder", "可输入发票抬头，不超过20个字符");
	$("#bills_sumary").attr("placeholder", "可输入发票摘要信息，不超过40个字符");
	
	$("#opr_date").attr("dataType", "Require").attr("msg", "请选择日期！");
	$("#link_name").attr("dataType", "Require").attr("msg", "不能为空！");
	$("#bill_memo").attr("dataType", "Limit").attr("max", "100").attr("msg", "不要超过100个字符。");
	$("#link_mobile").attr("dataType", "Mobile").attr("msg", "请填正确的手机号码！").attr("require", "false");
	$("#link_mobile").focus(function() {
	    //获得焦点时，如果值为默认值，则设置为空
	    if (this.value == vdefault) {
	        this.value = "";
	        this.style.color='#333';
	    }
	}).blur(function() {
	    //失去焦点时，如果值为空，则设置为默认值
	    if (this.value == "") {
	        this.value = vdefault;
	        this.style.color='#999999';
	    }
	  	//begin 转化全角为半角
         $("#link_mobile").val($.trim(DBC2SBC(this.value)));
		//end 
	});

	$("#consignee_tel").focus(function() {
	    //获得焦点时，如果值为默认值，则设置为空
	    if (this.value == vdefault) {
	        this.value = "";
	        this.style.color='#333';
	    }
	}).blur(function() {
	    //失去焦点时，如果值为空，则设置为默认值
	    if (this.value == "") {
	        this.value = vdefault;
	        this.style.color='#999999';
	    }
	  	//begin 转化全角为半角
         $("#consignee_tel").val($.trim(DBC2SBC(this.value)));
		//end 
	});
	
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

	//1为修改页面
	if("${details}" == 1){
		
		$(".td_del").click(function(){
			$(this).parent().remove();	
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

	//选择商品后触发
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
			 $("#rec_money_t").html(total_money);
			 //var dis_money  = $("#dis_money").val();
			 $("#money").val(total_money);
			 $("#money_t").html(total_money);
			 //$("#discount").val((dis_money/total_money*100).toFixed(2));
		}
	});

	//修改单价后触发
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
			 $("#rec_money_t").html(total_money);
			 //var dis_money  = $("#dis_money").val();
			 $("#money").val(total_money);
			 $("#money_t").html(total_money);
			 //$("#discount").val((dis_money/total_money*100).toFixed(2));
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
					var num = $(this).parent().parent().children('td').eq(6).children().val();
					var store_id = $(this).parent().parent().children('td').eq(0).children().val();
					goods_nums = goods_nums + $.trim(goods_id+':'+num+':'+store_id+',');
				});
				$.ajax({
					type: "POST",
					url: "JBill.do",
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
			
			
// 			$("#tr_model").remove();
//             $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
//             $("#btn_reset").attr("disabled", "true");
//             $("#btn_back").attr("disabled", "true");
// 			this.form.submit();
		}
	});
});

function genSN(){
	$('#partner_sn').val(['WLDW', 'KH', 'GR', '${customerUserInfo.cust_id}', new Date().getTime().toString().substr(-6)].join('-'));
	$("#partner_sn_t").html(['WLDW', 'KH', 'GR', '${customerUserInfo.cust_id}', new Date().getTime().toString().substr(-6)].join('-'));
}
<c:if test="${empty af.map.jBasePartner.partner_id}">
genSN();
</c:if>

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
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).keyup(function (){
		if(!obj.value.match(/^[\+\-]?\d*?\.?\d*?$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))obj.o_value=obj.value;
	}).blur(function (){
		if(!obj.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\.\d+$/))obj.value=0+obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
		if(isNaN(obj.value)) obj.value = "";
	});
}

//折扣金额与折扣率互算折后金额     add by Liang Houen on 2015-07-01
function setMoney(obj){
	var rec_money = $("#rec_money").val();
	if(rec_money != 0){
		if(obj.id == 'dis_money'){
			if(obj.value == ''){
				obj.value = 0;
			}
			$("#discount").val((obj.value/rec_money*100).toFixed(2));
			$("#money").val(rec_money-obj.value);
			$("#money_t").html(rec_money-obj.value);
		}
		if(obj.id == 'discount'){
			if(obj.value == ''){
				obj.value = 0;
			}
			$("#dis_money").val(obj.value/100*rec_money);
			$("#money").val(rec_money-$("#dis_money").val());
			$("#money_t").html(rec_money-$("#dis_money").val());
		}
	}else{
		$("#discount").val(0);
		$("#money").val(rec_money-$("#dis_money").val());
		$("#money_t").html(rec_money-$("#dis_money").val());
	}
}


function DBC2SBC(str)
{
 var result = '';
 for (i=0 ; i<str.length; i++)
 {
  code = str.charCodeAt(i);//获取当前字符的unicode编码
  if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母已及各种字符
  {
   result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
  }else if (code == 12288)//空格
  {
   result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
  }else
  {
   result += str.charAt(i);
  }
 }
 return result;
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
