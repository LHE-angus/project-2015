<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jGrowl.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/orderdetail.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/reveal/reveal.css" rel="stylesheet" type="text/css" />
<!--<style type="text/css">-->
<!--   html { overflow:hidden;} -->
<!--</style>-->
<style type="text/css">
.select_box {
	position: relative;
}
.select_box ul li {
	cursor: pointer;
}
.son_ul {
	width: 40px;
	position: absolute;
	z-index: 1;
	left: 27px;
	top: 20px;
	border: 1px solid #ccc;
	background: #f2f2f2;
}
.son_ul li {
	display: block;
	line-height: 25px;
}
.hover {
	background: #fff;
	color: #6699ff;
}
.disButton {
	background: url(${ctx}/images/manager/butbg1.gif) repeat-x;
	border: 1px #c4c4c4 solid;
	font: normal 12px/20px "宋体";
	color: #333;
	padding: 0px 1px 0px 1px !important;
	padding: 0px 1px 0px 1px;
	letter-spacing: 1px;
	font-size: 12px;
	cursor: pointer;
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
<!-- 这个是form.jsp页面的备份，可以删除 -->
<body>
<div class="oarcont" style="position:relative;overflow:hidden;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}（分公司返利经营模式）<span class="sel-tab"><a href="${ctx}/customer/manager/JBill.do?mod_id=${af.map.mod_id}&bill_type=20&to_default_page=1">一步价经营模式</a></span><span class="sel-tab sel-tab-active">分公司返利经营模式</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont2">
    <html-el:form action="/manager/KonkaXxZmdAddSalesOrder">
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="sell_bill_id" />
      <html-el:hidden property="sell_state" />
      <html-el:hidden property="is_edit" />
      <html-el:hidden property="queryString" />
      <table id="main_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
        <!-- 销售单信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">销售单信息
            <c:if test="${not empty af.map.sell_bill_id}">（订单流水号：<span style="color:#FF0000;font-size:15px;">${fnx:leftPad_sis(af.map.sell_bill_id, 12, '0')}</span>）</c:if></td>
        </tr>
        <tr>
          <td width="15%" class="title_item" nowrap="nowrap" align="right" ><strong>分公司：</strong></td>
          <td width="35%" align="left" style="padding-bottom:5px;"><html-el:hidden property="dept_id" styleId="dept_id" value="${af.map.dept_id}" />
            <html-el:hidden property="dept_name" styleId="dept_name" value="${af.map.dept_name}" />
            <c:out value="${af.map.dept_name}" /></td>
          <td width="15%" class="title_item" nowrap="nowrap" align="right">专卖店编号：</td>
          <td width="35%" align="left" style="padding-bottom:5px;font-weight:700;font-size:16px;color:#F00;font-style:italic;"><html-el:hidden property="zmd_id" styleId="zmd_id" value="${af.map.zmd_id}" />
            <html-el:hidden property="zmd_sn" styleId="zmd_sn" value="${af.map.zmd_sn}" />
            <c:out value="${af.map.zmd_sn}" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>销售人员：</td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="sell_man" styleId="sell_man" value="${userInfo.real_name}" maxlength="20" size="30" />
            <a id="delSalesUser" href="javascript:void(0);" onclick="$(this).prev().val('').focus();" onmousemove="$(this).css('color', '#FF0000');" onmouseout="$(this).css('color', '#6699ff');" style="color:#6699ff;margin-left:5px;text-decoration:underline;">清空</a></td>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>销售时间：</td>
          <td align="left" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.sell_date}" pattern="yyyy-MM-dd" var="sell_date_" />
            <html-el:text property="sell_date" styleId="sell_date" value="${sell_date_}" maxlength="10" size="10" readonly="readonly" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
        <!-- 购买人信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">购买人信息</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right" ><span style="color:#FF0000;">[必填]</span><strong>购买人姓名：</strong></td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="buyer_name" styleId="buyer_name" maxlength="20" /></td>
          <td class="title_item" nowrap="nowrap" align="right">身份证：</td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="buyer_id" styleId="buyer_id" maxlength="18" onblur="getBuyerInfo($(this).val())" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right" ><strong>性别：</strong></td>
          <td align="left" style="padding-bottom:5px;"><html-el:select property="buyer_sex" styleId="buyer_sex">
              <html-el:option value="">==请选择==</html-el:option>
              <html-el:option value="1">男</html-el:option>
              <html-el:option value="2">女</html-el:option>
              <html-el:option value="3">保密</html-el:option>
            </html-el:select></td>
          <td class="title_item" nowrap="nowrap" align="right">生日：</td>
          <td align="left" style="padding-bottom:5px;"><fmt:formatDate value="${af.map.buyer_birthday}" pattern="yyyy-MM-dd" var="buyer_birthday_" />
            <html-el:text property="buyer_birthday" styleId="buyer_birthday" value="${buyer_birthday_}" maxlength="10" size="10" readonly="readonly" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right" ><strong>电话：</strong></td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="buyer_tel" styleId="buyer_tel" maxlength="13"></html-el:text></td>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>手机：</td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="buyer_phone" styleId="buyer_phone" maxlength="11" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>购买人所在地区：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><html-el:select property="buyer_province" styleId="buyer_province" style="width:150px;"></html-el:select>
            &nbsp;
            <html-el:select property="buyer_city" styleId="buyer_city" style="width:150px;">
              <html-el:option value="">=请选择市=</html-el:option>
            </html-el:select>
            &nbsp;
            <html-el:select property="buyer_country" styleId="buyer_country" style="width:150px;">
              <html-el:option value="">=请选择区/县=</html-el:option>
            </html-el:select>
            &nbsp;
            <html-el:select property="buyer_town" styleId="buyer_town" style="width:150px;">
              <html-el:option value="">=请选择乡/镇=</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">购买人联系地址：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><html-el:text property="buyer_link_addr" styleId="buyer_link_addr" maxlength="30" size="80" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">会员卡号：</td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="consumer_grade_num" styleId="consumer_grade_num" maxlength="20" /></td>
          <td class="title_item" nowrap="nowrap" align="right">积分：</td>
          <td style="padding-bottom:5px;" align="left"><span id="jf_sec" style="color: red;"></span></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">购买人备注：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><html-el:textarea property="buyer_memo" styleId="buyer_memo" cols="70" rows="2" />
            <div id="info_chat_content_2"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <!-- 购买人信息end --> 
        <!-- 销售单信息End --> 
        <!-- 商品信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;"><span style="color:#FF0000;">[必填]</span>商品信息（商品由“${af.map.dept_name}”分公司统一维护管理）</td>
        </tr>
        <tr>
          <td colspan="4" align="left" style="padding-top:5px;padding-bottom:5px;"><div id="div_2"> 
              <!-- 产品信息Begin -->
              <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
                <tr class="tabtt1" style="background-image:url('${ctx}/images/manager/tabtitbg1.gif') repeat-x;background:#F0F0F0;">
                  <td width="3%" align="center" nowrap="nowrap">序号</td>
                  <td align="center" nowrap="nowrap">产品型号</td>
                  <td width="14%" align="center" nowrap="nowrap">赠品</td>
                  <td width="6%" align="center" nowrap="nowrap">库存</td>
                  <td width="6%" align="center" nowrap="nowrap">数量</td>
                  <td align="center" nowrap="nowrap">单价</td>
                  <td width="10%" align="center" nowrap="nowrap">金额</td>
                  <td width="10%" align="center" nowrap="nowrap">商品类型</td>
                  <!-- <td width="14%" align="center" nowrap="nowrap">销售备注</td> -->
                  <td width="5%" align="center" nowrap="nowrap" id="addPdTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" style="vertical-align:middle;" /></td>
                </tr>
                <!-- 隐藏TR模版Begin -->
                <tr id="pdModel" style="display:none;height:40px;">
                  <td align="center"></td>
                  <td align="left" class="pdIds"><select name="pdIdList" id="pdIdList" multiple="multiple">
                      <!--<option value="">==请选择==</option>-->
                      <c:forEach items="${pdIdList}" var="cur" varStatus="vs">
                        <option value="${cur.dept_pd_id}[#####]${cur.pd_cls}[#####]${cur.pd_cls_name}[#####]${cur.md_name}[#####]${cur.pd_type}[#####]${cur.price_ref}[#####]${cur.price_max}[#####]${cur.price_min}[#####]${cur.fac_sn}[#####]${cur.store_sn}">${cur.md_name}[${cur.fac_desc}][${cur.price_ref}]</option>
                      </c:forEach>
                    </select></td>
                  <td align="center" nowrap="nowrap"><html-el:text property="gift" styleId="gift" size="15" maxlength="200"></html-el:text></td>
                  <td align="center" nowrap="nowrap"></td>
                  <td align="center" nowrap="nowrap"><html-el:text property="counts" styleId="counts" disabled="true" style="width:40px;border:0;border-bottom:1 solid black;" maxlength="5" value="0" /></td>
                  <td align="center" nowrap="nowrap"><ul id="main_box">
                      <li class="select_box"> <img src="${ctx}/images/yen.png" alt="￥" title="RMB" />
                        <html-el:text property="price" styleId="price" disabled="true" style="width:70px;" maxlength="8" />
                       
                          <div id="son_li"></div>
                       
                      </li>
                    </ul></td>
                  <td align="right" nowrap="nowrap"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><span id="pdAmount"></span></td>
                  <td align="center" nowrap="nowrap"><select name="pd_type" id="pd_type">
                      <option value="">=请选择=</option>
                      <c:forEach items="${baseTypesList70000}" var="cur" varStatus="vs">
                        <option value="${cur.type_id}">${cur.type_name}</option>
                      </c:forEach>
                    </select></td>
                 <!-- <td align="center" nowrap="nowrap"><html-el:text property="xs_remarks" styleId="xs_remarks" size="15" maxlength="40"></html-el:text></td> -->
                  <html-el:hidden property="pdIds" styleId="pdIds" />
                  <html-el:hidden property="md_name" styleId="md_name" />
                  <html-el:hidden property="pd_cls" styleId="pd_cls" />
                  <html-el:hidden property="pd_cls_name" styleId="pd_cls_name" />
                  <html-el:hidden property="pdStocksNum" styleId="pdStocksNum" />
                  <html-el:hidden property="price_max" styleId="price_max" />
                  <html-el:hidden property="price_min" styleId="price_min" />
                  <html-el:hidden property="price_ref" styleId="price_ref" />
                  <html-el:hidden property="ck" styleId="ck" />
                  <td align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
                </tr>
                <!-- 隐藏TR模版End -->
                <tbody id="showSalesOrderTbody">
                  <c:forEach items="${sellBillDetailsList}" var="cur_" varStatus="vs">
                    <tr id="hx_tr_${vs.count}">
                      <td align="center">${vs.count}</td>
                      <td align="left">${cur_.md_name}</td>
                      <td align="center"><html-el:text property="gift" styleId="gift" size="15" maxlength="200" value="${cur_.gift}" /></td>
                      <td align="center"><c:if test="${cur_.map.pdStocksNum gt cur_.counts}"><span style="color:green;">有</span></c:if>
                      	<c:if test="${cur_.map.pdStocksNum lt cur_.counts}"><span style="color:red;">库存不足</span></c:if>
                     </td>
                      <td align="center"><html-el:text property="counts" styleId="counts" style="width:40px;cursor:pointer;border:0;border-bottom:1 solid black;background:#CBF7C5;" maxlength="5" value="${cur_.counts}" readonly="true" onclick="openDeliveryDiv('${cur_.md_name}', '${cur_.pd_cls_name}','${cur_.fac_sn}','${cur_.store_sn}',$('#hx_tr_${vs.count}'), '${af.map.reserverd_stock}')" /></td>
                      <td align="center"><ul id="main_box">
                          <li class="select_box"> <img src="${ctx}/images/yen.png" alt="￥" title="RMB" />
                            <html-el:text property="price" styleId="price" styleClass="price" style="width:45px;" maxlength="8" value="${cur_.price}" />
                            <ul class="son_ul">
                              <li id="son_li"><a href="#" data-reveal-id="myModal" onclick="discount('${cur_.md_name}', ${cur_.map.price_ref}, '${cur_.map.price_min}', $(this).parent().parent().parent().parent().parent(), $('#price', $(this).parent().parent().parent().parent().parent().parent()).val(),this)">折扣</a></li>
                            </ul>
                          </li>
                        </ul></td>
                      <td align="right"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><span id="pdAmount">
                        <fmt:formatNumber value="${cur_.counts * cur_.price}" pattern="0.00" />
                        </span></td>
                      <td align="center"><html-el:select property="pd_type" styleId="pd_type" value="${cur_.pd_type}">
                          <html-el:option value="">=请选择=</html-el:option>
                          <c:forEach items="${baseTypesList70000}" var="cur_y">
                            <html-el:option value="${cur_y.type_id}">${cur_y.type_name}</html-el:option>
                          </c:forEach>
                        </html-el:select></td>
                     <!--  <td align="center"><html-el:text property="xs_remarks" styleId="xs_remarks" size="15" maxlength="40" value="${cur_.xs_remarks}" /></td>-->
                      <html-el:hidden property="pdIds" styleId="pdIds" value="${cur_.map.dept_pd_id}" />
                      <html-el:hidden property="md_name" styleId="md_name" value="${cur_.md_name}" />
                      <html-el:hidden property="pd_cls" styleId="pd_cls" value="${cur_.pd_cls}" />
                      <html-el:hidden property="pd_cls_name" styleId="pd_cls_name" value="${cur_.pd_cls_name}" />
                      <html-el:hidden property="pdStocksNum" styleId="pdStocksNum" value="${cur_.map.pdStocksNum}" />
                      <html-el:hidden property="price_max" styleId="price_max" value="${cur_.map.price_max}" />
                      <html-el:hidden property="price_min" styleId="price_min" value="${cur_.map.price_min}" />
                      <html-el:hidden property="price_ref" styleId="price_ref" value="${cur_.map.price_ref}" />
                     <html-el:hidden property="ck" styleId="ck" value="${cur_.map.ck}" /> 
                      <td align="center" style="cursor:pointer;" onclick="deletePd($('#hx_tr_${vs.count}'))"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
                    </tr>
                  </c:forEach>
                </tbody>
                <tr id="total">
                  <td align="center" colspan="2" style="color:red;font-weight:bold;">合计</td>
                  <td colspan="2" align="left"></td>
                  <td align="center" nowrap="nowrap">0</td>
                  <td></td>
                  <td align="right" nowrap="nowrap"><font color="red" style="font-weight:bold;"><img src="${ctx}/images/yen.png" alt="￥" title="RMB" /><span id="allAmount">0.00</span></font>
                    <html-el:hidden property="total_money" styleId="total_money" value="0.00" /></td>
                  <td colspan="3"></td>
                </tr>
              </table>
              <!-- 产品信息End --> 
            </div></td>
        </tr>
        <!-- 商品信息End --> 
        <!-- 付款方式Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;"><span style="color:#FF0000;"></span>付款方式</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>付款方式：</td>
          <td align="left" style="padding-bottom:5px;"><!--		  			<c:forEach items="${baseTypesList50000}" var="cur">
		  					<label for="pay_way_${cur.type_id}" style="cursor: pointer;"><html-el:radio property="pay_way" styleId="pay_way_${cur.type_id}" value="${cur.type_id}">${cur.type_name}</html-el:radio></label>
		  				</c:forEach>-->
            
            <label for="pay_way_1" style="cursor:pointer;">
              <html-el:radio property="pay_way" styleId="pay_way_1" value="1">全额现金支付</html-el:radio>
            </label>
            <label for="pay_way_2" style="cursor:pointer;">
              <html-el:radio property="pay_way" styleId="pay_way_2" value="2">全额POS刷卡</html-el:radio>
            </label>
            <label for="pay_way_3" style="cursor:pointer;">
              <html-el:radio property="pay_way" styleId="pay_way_3" value="3">货到付款</html-el:radio>
            </label></td>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>发票类型：</td>
          <td align="left" style="padding-bottom:5px;"><c:forEach items="${baseTypesList90000}" var="cur">
              <label for="sell_bill_type_${cur.type_id}" style="cursor: pointer;">
                <html-el:radio property="sell_bill_type" styleId="sell_bill_type_${cur.type_id}" value="${cur.type_id}">${cur.type_name}</html-el:radio>
              </label>
            </c:forEach></td>
        </tr>
        <tr id="money_of_deposit_tr" style="display: none;">
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>订金：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><html-el:text property="money_of_deposit" styleId="money_of_deposit" maxlength="8" value="${empty af.map.money_of_deposit?0:af.map.money_of_deposit}" />
            &nbsp;<img src="${ctx}/images/yen.png" alt="￥" title="RMB" /></td>
        </tr>
        <tr id="pos_bill_sn_tr" style="display: none;">
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>POS单号：</td>
          <td align="left" colspan="3" style="padding-bottom:5px"><html-el:text property="pos_bill_sn" styleId="pos_bill_sn" maxlength="20"></html-el:text></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;" id="sell_bill_head_span"></span>发票抬头：</td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="sell_bill_head" styleId="sell_bill_head" maxlength="20"></html-el:text></td>
          <td class="title_item" nowrap="nowrap" align="right">发票纳税人识别号：</td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="sell_bill_taxpayer_id" styleId="sell_bill_taxpayer_id" maxlength="20"></html-el:text></td>
        </tr>
        <!-- 付款方式End --> 
        <!-- 收货人信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">收货人信息&nbsp;&nbsp;<span id="syncUser" class="fblue" style="font-weight:normal;cursor:pointer;" title="一键复制收货人信息" onmousemove="$(this).css('color', '#FF0000');" onmouseout="$(this).css('color', '#6699ff');">同步购买人信息</span></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>送货地区：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><html-el:select property="province" styleId="province" style="width:150px;"></html-el:select>
            &nbsp;
            <html-el:select property="city" styleId="city" style="width:150px;">
              <html-el:option value="">=请选择市=</html-el:option>
            </html-el:select>
            &nbsp;
            <html-el:select property="country" styleId="country" style="width:150px;">
              <html-el:option value="">=请选择区/县=</html-el:option>
            </html-el:select>
            &nbsp;
            <html-el:select property="town" styleId="town" style="width:150px;">
              <html-el:option value="">=请选择乡/镇=</html-el:option>
            </html-el:select></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>送货街道地址：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><html-el:text property="sell_post_addr" styleId="sell_post_addr" maxlength="30" size="80" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>收货人姓名：</td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="sell_rec_name" styleId="sell_rec_name" maxlength="20" /></td>
          <td class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>收货人联系电话：</td>
          <td align="left" style="padding-bottom:5px;"><html-el:text property="sell_rec_link_mp" styleId="sell_rec_link_mp" maxlength="13" /></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right">备注：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><html-el:textarea property="sell_remarks" styleId="sell_remarks" cols="70" rows="2" />
            <div id="info_chat_content_1"  style="color:#0066FF;font-size:12px;display:none"><img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" /></div></td>
        </tr>
        <!-- 收货人信息End --> 
        <!-- 财务审核信息Begin -->
        <c:if test="${af.map.sell_state gt 10}">
          <tr>
            <td colspan="4" style="font-weight:900;">财务审核信息</td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right">状态：</td>
            <td align="left" colspan="3" style="padding-bottom:5px;"><c:if test="${af.map.sell_state eq 20}">已审核通过</c:if>
              <c:if test="${af.map.sell_state eq 21}">已审核不通过</c:if>
              <c:if test="${af.map.sell_state gt 21}">已审核通过</c:if></td>
          </tr>
          <tr>
            <td class="title_item" nowrap="nowrap" align="right">备注：</td>
            <td align="left" colspan="3" style="padding-bottom:5px;">${af.map.cw_remarks}</td>
          </tr>
        </c:if>
        <!-- 财务审核信息End --> 
        <!-- 提交信息Begin -->
        <tr>
          <td colspan="4" style="font-weight:900;">专卖店确认收款</td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right" valign="top"><span style="color:#FF0000;">[必填]</span>消费者是否已支付：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><label for="submit_type_0" style="cursor:pointer;">
              <html-el:radio property="submit_type" styleId="submit_type_0" value="0">消费者已支付</html-el:radio>
            </label>
            <label for="submit_type_1" style="cursor:pointer;">
              <html-el:radio property="submit_type" styleId="submit_type_1" value="1">消费者未支付（货到付款除外）</html-el:radio>
            </label>
            
            <!-- <div style="color:#F00;">声明：选择任意一种“付款方式”均视为您已确认“消费者已支付”订单总额，总额将在分公司财务审核该订单时从您的账户余额（当前账户余额：<span style="cursor:pointer" class="fblue" title="您的账户余额"><fmt:formatNumber value="${af.map.zmd_total_credit_line}" pattern=".00" />元</span>）中扣除。</div> -->
            
            <div style="color:#F00;">声明：选择任意一种“付款方式”均视为您已确认“消费者已支付”订单总额。</div></td>
        </tr>
        <tr>
          <td class="title_item" nowrap="nowrap" align="right" valign="top">专卖店应收款：</td>
          <td align="left" colspan="3" style="padding-bottom:5px;"><span id="zmdRec">0.00</span>&nbsp;<img src="${ctx}/images/yen.png" alt="￥" title="RMB" /></td>
        </tr>
        <!-- 提交信息End --> 
        <!-- 销售单状态Begin --> 
        <!--        <tr>--> 
        <!--	  		<td colspan="4" style="font-weight:900;">销售单状态</td>--> 
        <!--	  	</tr>--> 
        <!--	  	<tr>--> 
        <!--	  		<td width="15%" class="title_item" nowrap="nowrap" align="right"><span style="color:#FF0000;">[必填]</span>销售单状态：</td>--> 
        <!--	  		<td align="left" colspan="3" style="padding-bottom:5px;">--> 
        <!--	  			<html-el:select property="sell_state" styleId="sell_state">--> 
        <!--	  				<html-el:option value="">==请选择==</html-el:option>--> 
        <!--	  				<c:forEach items="${baseTypesList40000}" var="cur">--> 
        <!--	  					<c:if test="${cucur_ype_id le 40200}">--> 
        <!--	  						<html-el:option value="${cur.type_id}">${cur.type_name}</html-el:option>--> 
        <!--	  					</c:if>--> 
        <!--	  				</c:forEach>--> 
        <!--					<html-el:option value="0">未付款</html-el:option>--> 
        <!--					<html-el:option value="10">已付款未审核</html-el:option>--> 
        <!--	  			</html-el:select>--> 
        <!--	  		</td>--> 
        <!--	  	</tr>--> 
        <!-- 销售单状态End -->
        <tr>
          <td colspan="4" style="font-weight:900;">R3系统订单凭证</td>
        </tr>
        <tr>
			<td align="right" class="title_item" width="15%">销售凭证类型：</td>
			<td width="35%"><select name="doc_type">
						<option value="ZFOR">ZFOR</option>
						<option value="ZFGC">ZFGC</option>
						<option value="ZFRE">ZFRE</option>
						<option value="ZFRC">ZFRC</option>
					</select></td>
			<td align="right" class="title_item">销售组织：</td>
			<td><input type="text" name="sales_org" id="sales_org" value="${sales_org}" readonly="readonly" /></td>
		</tr>
		<tr>
			<td colspan="1" align="right" class="title_item">分销渠道：</td>
			<td ><input type="text" name="distr_chan" value="10" readonly="readonly" /></td>
			<td align="right" class="title_item">产品组：</td>
			<td><input type="text" name="division" value="10" readonly="readonly" /></td>
		</tr>
		<tr>
			<td align="right" class="title_item">售达方：</td>
			<td>
			<c:if test="${empty agList}"><input type="text" name="ag" value="${ag}" /></c:if>
			<c:if test="${not empty agList}"><html-el:select property="ag">
				<c:forEach var="cur" items="${agList}" varStatus="vs">
					<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
				</c:forEach>
            </html-el:select></c:if>
			</td>
			<td colspan="1" align="right" class="title_item">出具发票方：</td>
			<td><c:if test="${empty reList}"><input type="text" name="re" value="${re}" /></c:if>
			<c:if test="${not empty reList}"><html-el:select property="re">
				<c:forEach var="cur" items="${reList}" varStatus="vs">
					<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
				</c:forEach>
            </html-el:select></c:if></td>
		</tr>
		<tr>
			<td colspan="1" align="right" class="title_item">付款方：</td>
			<td>
			<c:if test="${empty rgList}"><input type="text" name="rg" value="${rg}" /></c:if>
			<c:if test="${not empty rgList}"><html-el:select property="rg">
				<c:forEach var="cur" items="${rgList}" varStatus="vs">
					<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
				</c:forEach>
            </html-el:select></c:if>
            </td>
			<td colspan="1" align="right" class="title_item">送达方：</td>
			<td>
			<c:if test="${empty weList}"><input type="text" name="we" value="${we}" /></c:if>
			<c:if test="${not empty weList}"><html-el:select property="we">
				<c:forEach var="cur" items="${weList}" varStatus="vs">
					<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
				</c:forEach>
            </html-el:select></c:if>
			</td>
		</tr>
        <tr>
          <td colspan="4" height="40"  align="center"><input class="but4" type="button" name="Submit4" value="提交 " id="btn_submit" />
            
            <!--	            	<input class="but3" type="reset" style="width:100px;" value="重填" id="reset" />-->
            
            <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" id="btn_back" /></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <div id="message_tip" style="display:none;">
    <div class="ui-overlay">
      <div class="ui-widget-overlay"></div>
      <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:fixed;left:35%;top:30%"></div>
    </div>
    <div style="position:fixed;width:280px;height:130px;left:35%;top:30%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all"> <span><img id="loading" src="${ctx}/images/loading.gif" style="display:none;" /><img src="${ctx}/images/wait.gif" />&nbsp;正在提交订单，请稍等...</span> </div>
  </div>
  <div id="message_tip_2" style="display:none;">
    <div class="ui-overlay">
      <div class="ui-widget-overlay"></div>
      <div class="ui-widget-shadow ui-corner-all" style="width:302px;height:152px;position:fixed;left:35%;top:30%"></div>
    </div>
    <div style="position:fixed;width:280px;height:130px;left:35%;top:30%;padding:10px;" class="ui-widget ui-widget-content ui-corner-all">
      <div style="width:95%;margin-bottom:10px;margin-top:10px;font-size:15px;"><img src="${ctx}/styles/message/images/16.gif" />&nbsp;恭喜您，添加销售单成功
        <c:if test="${!empty jf_count}">，审核通过后，您将获得${jf_count}积分</c:if>
        ！</div>
      <button type="button" id="jxAdd">继续添加</button>
      <c:if test="${af.map.nn_state eq 1}">
        <button type="button" id="printRece">打印收款单</button>
      </c:if>
      <!--	  <span><img id="loading" src="${ctx}/images/loading.gif" />正在提交订单，请稍等...</span>--> 
    </div>
  </div>
</div>
<div id="newDeliveryDiv" style="display:none;">
  <div id="loadingDiv" align="left" style=""><img src="${ctx}/images/loading.gif" style="vertical-align:middle;" />正在查询仓库信息,请稍等...</div>
  <!-- 仓库列表 Begin -->
  <div id="tbody_div"></div>
  <!-- 仓库列表 End --> 
</div>
<div id="myModal" class="reveal-modal">
  <div>
    <table width="100%" align="left" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="right" width="25%" height="35" nowrap="nowrap" style="font-weight:bold;">产品型号：</td>
        <td id="discount_pd" class="fblue" style="font: normal 16px '微软雅黑','宋体'" nowrap="nowrap" align="left" colspan="2"></td>
      </tr>
      <tr>
        <td align="right" height="35" nowrap="nowrap" style="font-weight:bold;">参考价：</td>
        <td align="left" colspan="2" nowrap="nowrap"><span class="kz-price-12" id="discount_price_ref"></span><img src="${ctx}/images/yen.png" alt="￥" title="RMB" style="vertical-align:middle;" /></td>
      </tr>
      <tr>
        <td align="right" nowrap="nowrap" height="35" style="font-weight:bold;">折扣：</td>
        <td align="left" colspan="2" nowrap="nowrap"><label for="discount_radio_9" style="cursor:pointer;">
            <input type="radio" name="discount_radio" id="discount_radio_9" value="9.5" />
            九五折</label>
          <label for="discount_radio_8" style="cursor:pointer;">
            <input type="radio" name="discount_radio" id="discount_radio_8" value="9" />
            九折</label>
          <label for="discount_radio_7" style="cursor:pointer;">
            <input type="radio" name="discount_radio" id="discount_radio_7" value="8" />
            八折</label>
          <label for="discount_radio_0" style="cursor:pointer;">
            <input type="radio" name="discount_radio" id="discount_radio_0" value="0" />
            其他</label></td>
      </tr>
      <tr>
        <td align="right" nowrap="nowrap" height="35" style="font-weight:bold;">单价：</td>
        <td align="left" nowrap="nowrap"><input type="text" name="discount" id="discount" readonly="readonly" style="width:60px;background:#f2f2f2;" maxlength="8" />
          <img src="${ctx}/images/yen.png" alt="￥" title="RMB" /></td>
        <td align="right" nowrap="nowrap"><input type="button" name="dis_button" id="dis_button" class="disButton" value=" 确 定 " /></td>
      </tr>
    </table>
  </div>
  <!--<span class="title-reveal-modal">折扣</span>--> 
  <a class="close-reveal-modal">&#215;</a> </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<!--<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>--> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.timers.js"></script> 
<script type="text/javascript" src="${ctx}/styles/reveal/jquery.reveal.js"></script> 
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	if ("" == "${af.map.sell_bill_id}") {
		var today = new Date();
		today = today.format("yyyy-MM-dd"); 
		$("#sell_date").val(today);
	}

	if('${af.map.pay_way}' == 3){
		$("#zmdRec").text('${af.map.money_of_deposit}');
	} else if ('${af.map.pay_way}' == 1 || '${af.map.pay_way}' == 2){
		$("#zmdRec").text('${af.map.total_money}');
	}
		
	$("#sell_date").attr("readonly", "true").datepicker();
	$("#buyer_birthday").attr("readonly", "true").datepicker({yearRange:'1955:2030'});

	//折扣
	$("#discount").focus(setOnlyNum1);
	
	//回显Begin
	if ("" != "${af.map.sell_bill_id}") {
		$("#province").val("${af.map.province}").change();
		$("#city").val("${af.map.city}").change();
		$("#country").val("${af.map.country}").change();
		$("#town").val("${af.map.town}");
		
		$("#buyer_province").val("${af.map.buyer_province}").change();
		$("#buyer_city").val("${af.map.buyer_city}").change();
		$("#buyer_country").val("${af.map.buyer_country}").change();
		$("#buyer_town").val("${af.map.buyer_town}");

		if ("" != "${af.map.money_of_deposit}") {
			$("#money_of_deposit_tr").show();
			$("#money_of_deposit").attr("dataType", "Require").attr("msg", "请填写定金！").focus(setOnlyNum1);
			$("#pos_bill_sn").removeAttr("dataType");
			$("#pos_bill_sn_tr").hide();
		} 
		if ("" != "${af.map.pos_bill_sn}") {
			$("#pos_bill_sn_tr").show();
			$("#pos_bill_sn").attr("dataType", "Require").attr("msg", "请填写POS单号！");
			$("#money_of_deposit").removeAttr("dataType");
			$("#money_of_deposit_tr").hide();
		} 
		if ("1" == "${af.map.pay_way}") {
			$("#money_of_deposit").removeAttr("dataType");
			$("#money_of_deposit_tr").hide();
			$("#pos_bill_sn").removeAttr("dataType");
			$("#pos_bill_sn_tr").hide();
		}
		
		
		var total_count = 0;
		var total_price = 0;
		<c:forEach items="${sellBillDetailsList}" var="cur" varStatus="vs">
			var vs = "${vs.count}";
			var counts = "${cur.counts}";
			var price = "${cur.price}";
			var pd_type = "${cur.pd_type}";
			var xs_remarks = "${cur.xs_remarks}";
			total_count += parseInt(counts, 10);
			total_price = total_price + parseInt(counts, 10) * parseFloat(price);
			//$("#pd_type", "#hx_tr_" + vs).val(pd_type);
			//$("#xs_remarks", "#hx_tr_" + vs).val(xs_remarks);
		</c:forEach>
		$("#total").children().eq(2).text(total_count.toString());
		$("#allAmount").text(total_price.toFixed(2));
		$("#total_money").val(total_price.toFixed(2));

		for ( var i = 1; i <= parseInt("${fn:length(sellBillDetailsList)}", 10); i++) {
			$("#pd_type", "#hx_tr_" + i).attr("dataType", "Require").attr("msg", "请填写商品类型！");
		}

		$(".price", "#showSalesOrderTbody").each(function(){
			$(this).focus(setOnlyNum1).keyup(function(){
				var thisPrice = parseFloat($(this).val());
				if (isNaN(thisPrice)) {
					thisPrice = 0;
				}
				var price_max = parseFloat($("#price_max", $(this).parent().parent().parent().parent()).val());
				var price_min = parseFloat($("#price_min", $(this).parent().parent().parent().parent()).val());
//				if (thisPrice > price_max) {
//					alert("您输入的单价大于该商品的价格上限 〖￥" + price_max + "〗！");
//					$(this).val(0);
//					$("#pdAmount", $(this).parent().parent()).text(0.00);
					//计算合计金额
//					getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
//					return false;
//				}
				//if (thisPrice < price_min) {
				//	alert("您输入的单价小于该商品的价格下限 〖￥" + price_min + "〗！");
				//	return false;
				//}
				
				var thisPdNum = parseInt($("#counts", $(this).parent().parent().parent().parent()).val(), 10);
				if (isNaN(thisPdNum)) {
					thisPdNum = 0;
				}
				$("#pdAmount", $(this).parent().parent().parent().parent()).text((thisPdNum*thisPrice).toFixed(2));
				//计算合计金额
				getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
			});

			$(this).blur(function(){
				var thisPrice = $(this).val();
				if (isNaN(thisPrice)) {
					thisPrice = 0;
				}
				var price_min = parseFloat($("#price_min", $(this).parent().parent().parent().parent()).val());
				var price_ref = parseFloat($("#price_ref", $(this).parent().parent().parent().parent()).val());
//				alert("price_min = " + price_min + "\nprice_ref = " + price_ref);
				var counts = parseInt($("#counts", $(this).parent().parent().parent().parent()).val(), 10);
				var money = parseFloat(price_ref * counts);
				if (thisPrice < price_ref) {
					alert("您输入的单价小于该商品的参考价格〖￥" + price_ref + "〗，若要降价销售请点击“折扣”按钮进行设置！");
					$(this).val(price_ref);
					$("#pdAmount", $(this).parent().parent().parent().parent()).text(money.toFixed(2));
					//计算合计金额
					getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
					return false;
				}
				if (thisPrice < price_min) {
					alert("您输入的单价小于该商品的价格下限 〖￥" + price_min + "〗！");
					$(this).val(price_ref);
					$("#pdAmount", $(this).parent().parent().parent().parent()).text(money.toFixed(2));
					//计算合计金额
					getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
					return false;
				}
			}).hover(function(){
				$(this).parent().find('ul.son_ul').slideDown();  //找到ul.son_ul显示
			   	$(this).parent().find('li').hover(function(){$(this).addClass('hover');},function(){$(this).removeClass('hover');}); //li的hover效果
			   	$(this).parent().hover(function(){},function(){
	 				$(this).parent().find("ul.son_ul").delay(2000).slideUp(); 
	 		   	},function(){});
			});
		})
		
		//遮罩产品信息层
		var newMask = document.createElement("div");
	    newMask.id = "mask";
	    newMask.style.position = "absolute";
	    newMask.style.zIndex = "1";
	    //_scrollWidth = Math.max(document.body.scrollWidth, document.documentElement.scrollWidth);
	    //_scrollHeight = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
	    newMask.style.width = $("#div_2").css("width");
	    newMask.style.height = $("#div_2").css("height");
	    //alert($("#div_2").css("width") + "_______" + $("#div_2").css("height"));
	    //alert($("#div_2").offset().top + "_______" + $("#div_2").offset().left);
	    newMask.style.top = $("#div_2").offset().top + "px";
	    newMask.style.left = $("#div_2").offset().left + "px";
	    newMask.style.background = "#CCCCCC";//"#33393C";
	    newMask.style.filter = "alpha(opacity=40)";
	    newMask.style.opacity = "0.40";
		    
		if ("${af.map.sell_state}" == "20") { //财务审核通过
			$("input[type='radio'][name='pay_way']").attr("disabled", "true");
			$("#sell_date").attr("disabled", "true");
			//$("input[type='text'], select, textarea", "#div_2").attr("disabled", "true");
			//$("#div_2").css("filter", "alpha(opacity=40)").css("opacity", "0.40");
			
			$("#div_2").css("position", "relative");
		    document.body.appendChild(newMask);
		} else if ("${af.map.sell_state}" > 21) {
			
			$("input[type='text'], input[type='radio'], select, textarea").attr("disabled", "true");
			$("#btn_submit").attr("disabled", "true");
			$("#delSalesUser").hide();
			$("#syncUser").hide();

			$("#div_2").css("position", "relative");
		    document.body.appendChild(newMask);
		}
	}
	//回显End
	
	//css("overflow-x", "auto");
	//body自适应宽度End
	
	if ("" != "${af.map.msg}") {
		$.jGrowl("${af.map.msg}",
				 {theme:'success', 
			 	  life: 4500});
	}

	//打印收款单
	if ("0" != "${af.map.billId}" && "" != "${af.map.billId}") {
		$("#message_tip_2").show();
	}
	$("#printRece").button({icons: {primary: "ui-icon-arrowreturnthick-1-s"}}).click(function(){
		location.href = "KonkaXxPrintReceipt.do?method=showReceipt&sell_bill_id=${af.map.billId}&mod_id=820509";
	});
	$("#jxAdd").button({icons: {primary: "ui-icon-plusthick"}}).click(function(){ $("#message_tip_2").hide();});

	//表单验证
	$("#province").attr({"subElement": "city", "defaultText": "=请选择省/市/自治区=", "defaultValue": "", "selectedValue" : "${fn:substring(af.map.zmd_p_index, 0, 2)}${empty af.map.zmd_p_index ? af.map.province : '0000'}"});
	$("#city").attr({"subElement": "country", "defaultText": "=请选择市=", "defaultValue": "", "selectedValue" : "${fn:substring(af.map.zmd_p_index, 0, 4)}${empty af.map.zmd_p_index ? af.map.city : '00'}"});
	$("#country").attr({"subElement": "town","defaultText": "=请选择区/县=", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town").attr({"defaultText": "=请选择乡/镇=", "defaultValue": "", "selectedValue": "${af.map.town}"});
	$("#province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#province").change();

	$("#buyer_province").attr({"subElement": "buyer_city", "defaultText": "=请选择省/市/自治区=", "defaultValue": "", "selectedValue": "${fn:substring(af.map.buyer_p_index, 0, 2)}${empty af.map.buyer_p_index ? af.map.buyer_province : '0000'}"});
	$("#buyer_city").attr({"subElement": "buyer_country", "defaultText": "=请选择市=", "defaultValue": "", "selectedValue": "${fn:substring(af.map.buyer_p_index, 0, 4)}${empty af.map.buyer_p_index ? af.map.buyer_city : '00'}"});
	$("#buyer_country").attr({"subElement": "buyer_town","defaultText": "=请选择区/县=", "defaultValue": "", "selectedValue": "${af.map.buyer_country}"});
	$("#buyer_town").attr({"defaultText": "=请选择乡/镇=", "defaultValue": "", "selectedValue": "${af.map.buyer_town}"});
	$("#buyer_province").cs_ext("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false,"p_index_join","${af.map.p_index_join}");
	$("#buyer_province").change();
	
	$("#sell_man").attr("dataType", "Require").attr("msg", "请填写销售人员！");
	$("#sell_date").attr("dataType", "Require").attr("msg", "请填写销售时间！");
	$("input[type='radio'][name='pay_way']").eq(2).attr("dataType", "Group").attr("msg", "请选择付款方式！");
	$("#province").attr("dataType", "Require").attr("msg", "请选择省/直辖市/自治区！");
	$("#city").attr("dataType", "Require").attr("msg", "请选择市！");
	$("#country").attr("dataType", "Require").attr("msg", "请填写区/县！");
	$("#sell_rec_name").attr("dataType", "Require").attr("msg", "请填写收货人姓名！");
//	$("#sell_rec_link_mp").attr("dataType", "Require").attr("msg", "请填写收货人联系方式！");
	$("#sell_rec_link_mp").attr("datatype","Custom").attr("regexp","(^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$)|(^((13[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})|(18[0-9]{9}))$)").attr("msg","请正确填写收货人联系方式，格式为“电话：0755-88888888”，“手机：13888888888”！");
	$("input[type='radio'][name='sell_bill_type']").eq(1).attr("dataType", "Group").attr("msg", "请选择发票类型！");
	$("#buyer_province").attr("dataType", "Require").attr("msg", "请选择客户所在选择省/直辖市/自治区！");
	$("#buyer_city").attr("dataType", "Require").attr("msg", "请选择客户所在市！");
	$("#buyer_country").attr("dataType", "Require").attr("msg", "请选择客户所在区/县！");
	$("#sell_post_addr").attr("dataType", "Require").attr("msg", "请填写送货街道地址！");
	$("#buyer_name").attr("dataType", "Require").attr("msg", "请填写客户姓名！");
	$("#buyer_phone").attr("dataType","Mobile").attr("msg","手机格式不正确，正确格式为：“13888888888”！");
	$("#buyer_tel").attr("dataType","Phone").attr("msg","电话格式不正确，正确格式为：“0755-88888888”！").attr("Require", "false");
	$("#sell_state").attr("dataType","Require").attr("msg","请选择销售单状态！");
	$("input[type='radio'][name='submit_type']").eq(1).attr("dataType", "Group").attr("msg", "请选择销售单提交方式！");
	$("#sell_remarks").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content_1").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			 resizeFrameHeight();
		}
	}).blur(function() {
		$("#info_chat_content_1").slideUp("normal");
		 resizeFrameHeight();
	});
	$("#buyer_memo").textbox({
		maxLength: 200,
		onInput: function(event, status) {
			var img = '<img src="${ctx}/styles/jxc/images/tishi.gif" style="vertical-align:middle;" />';
			$("#info_chat_content_2").slideDown("fast").html(img + " 请将字数限制在：" + status.maxLength + " 个字内，您还可以输入：<b style='color:red;'>" + status.leftLength + "</b> 个字。");
			 resizeFrameHeight();
		}
	}).blur(function() {
		$("#info_chat_content_2").slideUp("normal");
		 resizeFrameHeight();
	});
	
	
	window.task_index = 0;
	var s = [];

	if ("" != "${af.map.sell_bill_id}") {
		task_index = "${fn:length(sellBillDetailsList)}";
	}
	
	$("#addPdTD").click(function(){
		$("#pdModel").clone().appendTo($("#showSalesOrderTbody")).show();
		task_index++;
		var lastTR = $("tr:last", "#showSalesOrderTbody");
		lastTR.children().eq(0).text(task_index);
		
		$("#md_name", lastTR).attr("dataType", "Require").attr("msg", "请选择产品型号！");
		$("#counts", lastTR).attr("dataType", "Require").attr("msg", "请填写商品数量！");
		$("#price", lastTR).attr("dataType", "Require").attr("msg", "请填写商品单价！");
		$("#pd_type", lastTR).attr("dataType", "Require").attr("msg", "请填写商品类型！");
		
		var totalNum = parseInt($("#total").children().eq(2).text(), 10);
		var addNum = totalNum;
		$("#total").children().eq(2).text(addNum.toString());
		
		//修改商品数量keyup事件
		$("#counts", lastTR).focus(setOnlyNum).keyup(function(){
			var thisPdNum = parseInt($(this).val(), 10);
			if(isNaN(thisPdNum)){
				thisPdNum = 0;
				$(this).val(thisPdNum);//Chrome error
			}
			var thisPrice = parseFloat($("#price", lastTR).val());
			if (isNaN(thisPrice)) {
				thisPrice = 0;
			}

			var pdStockNum = parseInt($("#pdStocksNum", lastTR).val(), 10);
			if (thisPdNum > pdStockNum) {
				alert("您输入的商品数量大于库存，请重新输入！");
				thisPdNum = 1;
				$(this).val(1);
			}
			
			$("#pdAmount", lastTR).text((thisPdNum*thisPrice).toFixed(2));
			//计算合计数量
			getTotalPdNum("counts", $("#showSalesOrderTbody"));
			//计算合计金额
			getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
		});
		//修改商品单价keyup事件
		$("#price", lastTR).focus(setOnlyNum1).keyup(function(){
			var thisPrice = parseFloat($(this).val());
			if (isNaN(thisPrice)) {
				thisPrice = 0;
			}
			var price_max = parseFloat($("#price_max", $(this).parent().parent()).val());
			var price_min = parseFloat($("#price_min", $(this).parent().parent()).val());
			var price_ref = parseFloat($("#price_ref", $(this).parent().parent()).val());
//			if (thisPrice > price_max) {
//				alert("您输入的单价大于该商品的价格上限 〖￥" + price_max + "〗！");
//				$(this).val(0);
//				$("#pdAmount", lastTR).text(0.00);
//				//计算合计金额
//				getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
//				return false;
//			}
//			if (thisPrice < price_min) {
//				alert("您输入的单价小于该商品的价格下限 〖￥" + price_min + "〗！");
//				$(this).val(price_ref);
//				$("#pdAmount", lastTR).text((thisPrice * parseInt($("#counts", lastTR).val(), 10)).toFixed(2));
//				//计算合计金额
//				getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
//				return false;
//			}
			
			var thisPdNum = parseInt($("#counts", lastTR).val(), 10);
			if (isNaN(thisPdNum)) {
				thisPdNum = 0;
			}
			$("#pdAmount", lastTR).text((thisPdNum*thisPrice).toFixed(2));
			//计算合计金额
			getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
		});

		$("#price", lastTR).blur(function(){
			var thisPrice = $(this).val();
			if (isNaN(thisPrice)) {
				thisPrice = 0;
			}
			var price_min = parseFloat($("#price_min", lastTR).val());
			var price_ref = parseFloat($("#price_ref", lastTR).val());
			var counts = parseInt($("#counts", lastTR).val(), 10);
			var money = parseFloat(price_ref * counts);
			if (thisPrice < price_ref) {
				alert("您输入的单价小于该商品的参考价格〖￥" + price_ref + "〗，若要降价销售请点击“折扣”按钮进行设置！");
				$(this).val(price_ref);
				$("#pdAmount", lastTR).text(money.toFixed(2));
				//计算合计金额
				getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
				return false;
			}
			if (thisPrice < price_min) {
				alert("您输入的单价小于该商品的价格下限 〖￥" + price_min + "〗！");
				$(this).val(price_ref);
				$("#pdAmount", lastTR).text(money.toFixed(2));
				//计算合计金额
				getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
				return false;
			}
		}).hover(function(){ //鼠标移动函数
		   	$(this).parent().find('ul.son_ul').slideDown();  //找到ul.son_ul显示
		   	$(this).parent().find('li').hover(function(){$(this).addClass('hover');},function(){$(this).removeClass('hover');}); //li的hover效果
		   	$(this).parent().hover(function(){},function(){
 				$(this).parent().find("ul.son_ul").delay(2000).slideUp(); 
 		   	},function(){});
	    });;

		//选择商品事件
		$("#pdIdList", lastTR).multiselect({
			noneSelectedText: '==请选择==',
			selectedList: 1,
			multiple: false,
			minWidth:160,
			click: function(event, ui){
		       if(ui.value != ""){
			       $("#counts", lastTR).removeAttr("disabled").val(0).attr("readonly", "true").css({"cursor":"pointer", "background":"#CBF7C5"});
			       $("#price", lastTR).removeAttr("disabled");
			       
			       s = ui.value.split("[#####]");
			       var dept_pd_id = s[0];
			       var pd_cls = s[1];
			       var pd_cls_name = s[2];
			       var md_name = s[3];
			       var pd_type = s[4];
			       var price_ref = s[5];
			       var price_max = s[6];
			       var price_min = s[7];
			       var fac_sn = s[8];
			       var store_sn = s[9];
			       $("#pdIds", lastTR).val(dept_pd_id); //分公司产品ID
			       
			       $("#md_name", lastTR).val(md_name); //产品型号
			       if (judgeSelectedValueIsRepeat("md_name", $("#showSalesOrderTbody"))) {
				        alert("商品选择有重复，请重新选择！");
				        return false;
				   }
			       
				   lastTR.children().eq(3).empty().append('<img id="loading" src="${ctx}/images/loading.gif" />');
				   
				   //查询库存Begin
				   var flag = getPdStocks("${af.map.zmd_id}", md_name,fac_sn,store_sn,lastTR);
			       //查询库存End

				   //$("#md_name", lastTR).val(md_name); //产品型号
			       $("#pd_cls", lastTR).val(pd_cls); //产品类别ID
			       $("#pd_cls_name", lastTR).val(pd_cls_name); //产品类别
			       $("#price", lastTR).val(price_ref); //产品参考价
			       $("#price_ref", lastTR).val(price_ref);
			       $("#price_max", lastTR).val(price_max); //产品价格上限
			       $("#price_min", lastTR).val(price_min); //产品价格下限
			       var thisPdNum = parseInt($("#counts", lastTR).val(), 10);
			       var thisPrice = parseFloat($("#price", lastTR).val());
			       var thisMoney = (thisPdNum * thisPrice).toFixed(2);
			       $("#pdAmount", lastTR).text(thisMoney); //产品金额
			       //计算合计数量
				   getTotalPdNum("counts", $("#showSalesOrderTbody"));
			       //计算合计金额
			       getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
				   
				   $("#ck", lastTR).attr("dataType", "Require").attr("msg", "请选择产品【" + md_name + "】库位填写购买数量！");

				   $("#counts", lastTR).click(function(){
					   openDeliveryDiv($("#md_name", lastTR).val(), $("#pd_cls_name", lastTR).val(),fac_sn,store_sn, lastTR, "${af.map.reserverd_stock}");
				   });

				   //折扣
				   $("#son_li", lastTR).empty().append('<a href="#" data-reveal-id="myModal" onclick="discount(\'' + md_name +'\', ' + price_ref + ', ' + price_min + ', $(this).parent().parent().parent().parent().parent(), $(\'#price\', $(this).parent().parent().parent().parent().parent().parent()).val(),this)">折扣</a>');
		       }
		       resizeFrameHeight();
			}
		}).multiselectfilter();//.attr("datatype", "Ms").attr("msg", "请选择商品！");
		
		
		$("td:last", lastTR).click(function (){
			task_index--;
			//alert("task_index = " + task_index);
			$("#md_name", $(this).parent()).removeAttr("dataType");
			$("#counts", $(this).parent()).removeAttr("dataType");
			$("#price", $(this).parent()).removeAttr("dataType");
			$(this).parent().remove();
			//处理删除字符
			$("#goods_title").empty().text("添加商品：");
			var i = 1;
			$("tr", "#showSalesOrderTbody").each(function(){
				if (i <= task_index) {
					$(this).find("td").eq(0).text(i);
					i++;
				}
			});
			//计算合计数量
			getTotalPdNum("counts", $("#showSalesOrderTbody"));
			//计算合计金额
			getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
			resizeFrameHeight();
	    }).css("cursor", "pointer");
		 resizeFrameHeight();
	});

	//选择付款方式
	$("input[type='radio'][name='pay_way']").click(function(){
		//alert($(this).val());
		if (3 == $(this).val()) { //货到付款
			$("#money_of_deposit_tr").show();
			$("#money_of_deposit").attr("dataType", "Require").attr("msg", "请填写定金！").focus(setOnlyNum1);
			$("#pos_bill_sn").removeAttr("dataType");
			$("#pos_bill_sn_tr").hide();
			$("#zmdRec").text(parseFloat($("#money_of_deposit").val()).toFixed(2));
			$("#money_of_deposit").keyup(function(){
				var money_of_deposit = parseFloat($(this).val()).toFixed(2);
				$("#zmdRec").text(money_of_deposit);
			});
		} else if (2 == $(this).val()) { //POS全额付款
			$("#pos_bill_sn_tr").show();
			$("#pos_bill_sn").attr("dataType", "Require").attr("msg", "请填写POS单号！");
			$("#money_of_deposit").removeAttr("dataType").val("0");
			$("#money_of_deposit_tr").hide();
			getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
		} else {
			$("#money_of_deposit").removeAttr("dataType").val("0");
			$("#money_of_deposit_tr").hide();
			$("#pos_bill_sn").removeAttr("dataType");
			$("#pos_bill_sn_tr").hide();
			getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
		}
		 resizeFrameHeight();
	});

	//选择发票类型
	$("input[type='radio'][name='sell_bill_type']").click(function(){
		if (90100 == $(this).val()) { //增值税发票
			$("#sell_bill_head").attr("dataType", "Require").attr("msg", "请填写发票抬头！");
			$("#sell_bill_head_span").text("[必填]");
		} else {
			$("#sell_bill_head").removeAttr("dataType");
			$("#sell_bill_head_span").text("");
		}
	});

	//同步收货人信息至购买人信息
	$("#syncUser").click(function(){
		//alert($("#sell_post_addr").val() + "_______\n" + $("#sell_rec_name").val() + "________\n" + $("#province").val() + "______\n" + $("#city").val() + "________\n" + $("#country").val() + "__________\n" + $("#town").val());
		var buyer_link_addr = $("#buyer_link_addr").val();
		var buyer_name = $("#buyer_name").val();
		var province = $("#buyer_province").val();
		var city = $("#buyer_city").val();
		var country = $("#buyer_country").val();
		var town = $("#buyer_town").val();
		var buyer_tel = $("#buyer_tel").val();
		var buyer_phone = $("#buyer_phone").val();
		
		$("#province").val(province).change();
		$("#city").val(city).change();
		$("#country").val(country).change();
		$("#town").val(town);
		$("#sell_rec_name").val(buyer_name);
		$("#sell_post_addr").val(buyer_link_addr);
		
		if (buyer_tel.length > 0) {
			//手机号不为空
			$("#sell_rec_link_mp").val(buyer_tel);
		} else {
			//包含"-"，固话号码
			$("#sell_rec_link_mp").val(buyer_phone);
		}
	});

	//提交表单
	$("#btn_submit").click(function(){
		//if ($(".pdIds").length <= 1) {
		//	alert("请至少添加一个商品！");
		//	return false;
		//}
		
		if ($("input[type='hidden'][name='pdIds']").length <= 1) {
			alert("请至少添加一个商品！");
			return false;
		}

		//提交表单验证购买商品数量Begin
		var flag = false;
		var flag1 = false;
		var flag2 = false;
		var mdName = "";
		var obj;
		$("input[type='text'][name='counts']", "#showSalesOrderTbody").each(function(){
			var n = $("#md_name", $(this).parent().parent()).val();
			obj = $(this).parent().parent();
			//判断购买商品数量是否存在0
			var count = parseInt($(this).val(), 10);
			if (isNaN(count)) {
				count = 0;
			}
			if (count == 0) {
				if (n != "" && n != undefined) {
					mdName = mdName.concat("〖").concat(n).concat("〗");
				}
				flag = true;
				return false;
			}
			//判断是否已查询库存
			var pdStockNum = parseInt($("#pdStocksNum", $(this).parent().parent()).val(), 10);
			if (isNaN(pdStockNum)) {
				flag1 = true;
				return false;
			}
			//判断购买的商品数量是否大于库存
			if (count > pdStockNum) {
				flag2 = true;
				return false;
			}
		});
		if (flag) {
			alert("您添加的商品" + mdName +"数量为 0 ，请输入购买数量！");
			$("#counts", obj).focus();
			return false;
		}
		if (flag1) {
			alert("请选择商品查询库存！");
			return false;
		}
		if (flag2) {
			alert("您输入的商品超过库存量");
			$("#counts", obj).focus();
			return false;
		}
		//提交表单验证购买商品数量End

		//提交表单验商品单价上限下限Begin
		var flag3 = false;
		var flag4 = false;
		var price_max;
		var price_min;
		var mdName = "";
		var obj;
		$("input[type='text'][name='price']", "#showSalesOrderTbody").each(function(){
			mdName = "";
			var n = $("#md_name", $(this).parent().parent()).val();
			obj = $(this).parent().parent();
			price_max = parseFloat($("#price_max", $(this).parent().parent()).val());
			price_min = parseFloat($("#price_min", $(this).parent().parent()).val());
			var	price = parseFloat($(this).val());

			if (n != "" && n != undefined) {
				mdName = mdName.concat("〖").concat(n).concat("〗");
			}
			if (price < price_min) {
				flag3 = true;
				return false;
			}
		});
		if (flag3) {
			alert("您销售的商品" + mdName + "低于该商品的价格下限" + price_min + "￥");
			$("#price", obj).focus();
			return false;
		}
		if (flag4) {
			alert("您销售的商品" + mdName + "高于该商品的价格上限" + price_max + "￥");
			$("#price", obj).focus();
			return false;
		}
		//提交表单验商品单价上限下限End
		
		//验证发货分配库位数量和客户购买数量是否一致Begin
		//修改不验证库存
		var is_edit = '${af.map.is_edit}';
		if(is_edit != 1){
		var flag5 = false;
		var flag6 = false;
		var mdName = "";
		var obj;
		$("#ck", "#showSalesOrderTbody").each(function(){
			var s1 = $(this).val();
			mdName = $(this).prev().prev().prev().prev().prev().prev().val();
			if ("pdModel" == $(this).parent().attr("id")) {
				obj = $(this).parent();
			} else if ("pdModel" == $(this).parent().parent().attr("id")) {
				obj = $(this).parent().parent();
			}
			if (s1 == "" || s1 == undefined) {
				flag5 = true;
				return false;
			} else {
				var arr = new Array();
				arr = s1.split("[@@@@@]");
				//alert("arr.length =" + arr.length);
				var n = 0;
				for ( var i = 0; i < arr.length; i++) {
					var arrs = new Array();
					arrs = arr[i].split("[#####]");
//					alert("arrs[0] = " + arrs[0]);
					if (arrs[0] == "" || arrs[0] == undefined) {
						arrs[0] = 0;
					}
					n = n + parseInt(arrs[0], 10);
				}
				var counts = parseInt($("#counts", obj).val(), 10);
				
				if (n != counts) {
					flag6 = true;
					return false;
				}
			}
		});

		if (flag5) {
			alert("您销售的商品〖" + mdName + "〗还未查看库存填写购买数量");
			$("#search_btn", $(obj)).focus();
			return false;
		}
		if (flag6) {
			alert("您销售的商品〖" + mdName + "〗更改了销售数量，请重新查看库存填写购买数量");
			$("#search_btn", $(obj)).focus();
			return false;
		}
		//验证发货分配库位数量和客户购买数量是否一致End
		}
		if(Validator.Validate(this.form, 1)){
			if(confirm("确定要提交表单？")){
				$('#message_tip').show();
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			} else {
				return false;
			}
		}
	});

	//添加销售单加载页面
	if ("" == "${af.map.sell_bill_id}") {
		$("#addPdTD").click();
		var obj = $("tr:first", "#showSalesOrderTbody");
		$("td:last", obj).unbind("click");
	}

	$("#consumer_grade_num").change(function(){
		var cus_num = this.value;
		
		if(cus_num.length > 0){
			$.ajax({
		    	type: "POST" , 
		        url: '../../webservice/JfWebInterface.do' , 
		        data: "method=getScores&user_sn=" + cus_num + "&n=" + Math.random(), 
		        dataType: "json" , 
		        async: true, 
		        error: function (request, settings) {alert("查询失败，请联系系统管理员！ "); }, 
		        success: function (result) {
		            if (result.status == 0) {
			            $("#jf_sec").text(result.scores);
					} else {
						$("#jf_sec").text('此卡号不是会员卡！ ');
					}
		        }
			}); 
		} else {
			$("#jf_sec").text('');
		}
	});
});

//计算合计数量
function getTotalPdNum(objId, parObj){
	var totalNum = 0;
	$("#" + objId, parObj).each(function(){
		var num = parseInt($(this).val(), 10);
		if (isNaN(num)) {
			num = 0;
		}
		totalNum = totalNum + num;
	});
	$("#total").children().eq(2).text(totalNum.toString());
}

//计算合计金额
function getTotalPdMoney(objId, parObj){
	var totalMoney = 0;
	$("#" + objId, parObj).each(function(){
		var money = parseFloat($(this).text());
		if (isNaN(money)) {
			money = 0;
		}
		totalMoney = totalMoney + money;
	});
	$("#allAmount").text(totalMoney.toFixed(2));
	$("#total_money").val(totalMoney.toFixed(2));
	//新增专卖店应收款显示
	if ($("input[type='radio'][name='pay_way']:checked").val() != 3) {
		$("#zmdRec").text(totalMoney.toFixed(2));
	}
}

function getPdStocks(zmd_id, md_name,fac_sn,store_sn, parObj) {
	//alert(md_name);
	var flag;
	//$(parObj).children().eq(4).append('<img id="loading" src="${ctx}/images/loading.gif" />');
	$("#loading", parObj).ajaxStart(function(){$(this).show();});	
	$("#loading", parObj).ajaxStop(function(){$(this).hide();});	
	$.ajax({
    	type: "POST" , 
        url: 'KonkaXxZmdAddSalesOrder.do' , 
        data: "method=getPdStocks&zmd_id=" + zmd_id + "&md_name=" + escape(encodeURIComponent(md_name))+"&fac_sn="+fac_sn+"&store_sn="+store_sn+ "&n=" + Math.random(), 
        dataType: "json" , 
        async: true, 
        error: function (request, settings) {alert(" 库存查询失败，请联系系统管理员！ "); }, 
        success: function (result) {
            if ("" == result[0].pdStocksNum || undefined == result[0].pdStocksNum) {
				flag = false;
			} else {
				var n = result[0].pdStocksNum;
            	$("#pdStocksNum", parObj).val(n);
            	if (n > 0) parObj.children().eq(3).html("<span style='color:green;'>有</span>");
            	else parObj.children().eq(4).html("<span style='color:#F00;'>无</span>");
            	//$("#total").children().eq(1).empty().append('型号为〖<span style="color:#6699ff;">' + md_name + '</span>〗的商品库存为：<span style="color:green;font-weight:bold;">' + result[0].pdStocksNum + '</span>');
            	flag = true;
			}
        }
	}); 
	return flag;
}

function openDeliveryDiv(md_name, pd_cls_name,fac_sn,store_sn, parObj, reserverd_stock){
	$("#tbody_div", "#newDeliveryDiv").empty();
	var counts = parseInt($("#counts", parObj).val(), 10);
	var str = $("#ck", parObj).val();
	$("#newDeliveryDiv").dialog({
	      title: '填写购买数量', 
	      width: 600,
	      height: 350,
	      draggable: true,
	      resizable: false,
	      position:'center',
	      modal : true,
	      open: function(event, ui) {
		  			getPdStoresList(md_name, counts, str,fac_sn,store_sn, parObj, reserverd_stock);
	      		},
	      close: function(event, ui) {
	    	  		$("#loadingDiv", "#newDeliveryDiv").show();
		  		},
	      buttons: {"确认": function() {
									var dts_num = 0;
									$(".dts_counts").each(function(){
										var dts_n = parseInt($(this).val(), 10);
										if (isNaN(dts_n)) {
											dts_n = 0;
										}
										dts_num += parseInt(dts_n, 10);
									});
									if (dts_num == 0) {
										alert("请至少选择一个仓库填写购买数量！");
										return false;
									} 

									//验证仓库保留数量-1：Begin
									var totStoreNum = 0;
									$(".storeNum", "#stores_tbody").each(function(){
										var storeNum = parseInt($(this).attr('store'), 10);
										if (isNaN(storeNum)) {
											storeNum = 0;
										}
										totStoreNum += parseInt(storeNum, 10);
									});
									var n = totStoreNum - dts_num;
									if (n < reserverd_stock) {
										alert("您填写的购买数量过多，仓库剩余产品〖" + md_name + "〗总库存量低于最低保留数量" + reserverd_stock + "个");

										$(".dts_counts").each(function(){
											$(this).val(0);
											var n = parseInt($(this).val(), 10);
										});
										$("#buy_count").text("0");
										$("#counts", parObj).val(0);
										$("#pdAmount", parObj).text(0.00);
										getTotalPdNum("counts", $("#showSalesOrderTbody"));
										getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
										
										return false;
									}
									//验证仓库保留数量-1：End

									if(confirm("确定该购买数量？")){
										//this.form.submit();
										/**出库信息数据封装
										 * 出库数量[#####]工厂ID[#####]仓库ID[#####]仓库名称[@@@@@]出库数量[#####]工厂ID[#####]仓库ID[#####]仓库名称........
										 */
										var ss = "";
										var s = "";
										var arrs = new Array();
										var i = 0;
										$("#dts_counts", "#stores_tbody").each(function(){
											if ($(this).val() != 0) {
												var arr = new Array();
												var dts_counts = $(this).val().toString();
												var factory_id = $("#factory_id", $(this).parent().parent()).val().toString();
												var store_id = $("#store_id", $(this).parent().parent()).val().toString();
												var store_desc = $("#store_desc", $(this).parent().parent()).val().toString();
												arr[0] = dts_counts;
												arr[1] = factory_id;
												arr[2] = store_id;
												arr[3] = store_desc;
												s = arr.join("[#####]");
												arrs[i] = s;
												i++;
											}
										});
										ss = arrs.join("[@@@@@]");
										
										$("#md_name", "#showSalesOrderTbody").each(function(){
											if ($(this).val() == md_name) {
												$("#ck", parObj).val(ss);
												//$("#gift", $(this).parent().parent().children().eq(3)).val(ss);
											}
										});
										//$("#ck", "#showSalesOrderTbody").each(function(){
										//	alert("$('#ck').val()=" + $(this).val());
										//});
										$(this).dialog("close");
									} else {
										return false;
									}
	      						},
					"关闭": function() {
	      						$(this).dialog("close");
	      					}
				   }
	}).dialog("open");
}

function getPdStoresList(md_name, counts, str,fac_sn,store_sn, parObj, reserverd_stock){
    $.ajax({
    	type: "POST" , 
        url: 'KonkaXxZmdAddSalesOrder.do' , 
        data: "method=getPdStoresList&zmd_id=${af.map.zmd_id}&md_name=" + escape(encodeURIComponent(md_name))+"&fac_sn="+fac_sn+"&store_sn="+store_sn+ "&sell_bill_id=${af.map.sell_bill_id}&n=" + Math.random(), 
        dataType: "json" , 
        async: true, 
        error: function (request, settings) {alert(" 数据加载请求失败！ "); },
        success: function (result) {
        	$("#loadingDiv", "#newDeliveryDiv").hide();
        	$("#tbody_div", "#newDeliveryDiv").append('<div class="rtabcont1" align="left">'+
								   		'<table width="95%" border="0" cellspacing="5" cellpadding="0" class="rtable1">' +
											'<tr>' +
												'<td>' +
												    //'<div class="rtabcont2">' +
												    	'<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">' + 
													    	'<tr>' +
							        						    '<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
												    				'<strong class="fb">分公司：</strong>' +
												    			'</td>' +
												    			'<td width="35%" align="left">${af.map.dept_name}</td>' +
												    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
												    				'<strong class="fb">专卖店编号：</strong>' +
												    			'</td>' +
												    			'<td align="left">${af.map.zmd_sn}</td>' +
					        						        '</tr>' +
						        						    '<tr>' +
							        						    '<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
												    				'<strong class="fb">商品型号：</strong>' +
												    			'</td>' +
												    			'<td width="35%" align="left"><span class="fblue">' + md_name + '</span></td>' +
												    			'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
												    				'<strong class="fb">仓库保留数量：</strong>' +
												    			'</td>' +
												    			'<td align="left">' + reserverd_stock + '</td>' +
					        						        '</tr>' +
					        						        '<tr>' +
					        						        	'<td width="15%" class="title_item" nowrap="nowrap" align="right">' +
					        						        		'<strong class="fb">购买数量：</strong>' +
					        						        	'</td>' +
					        						        	'<td colspan="3" id="buy_count" align="left">' + counts + '</td>' +
					        						        '</tr>' +
					        						    '</table>' +
					        						//'</div>' +  
												'</td>' +
											'</tr>' +
										'</table>'+
										'<div style="width:95%;color:#FF0000">注：产品库存取自R3系统，需要R3管理员进行库存同步操作，因此库存存在延时现象。请及时通知R3系统管理员作库存同步操作！</div>' +
								  		'<table width="95%" border="0" cellspacing="0" cellpadding="0" class="rtable2" style="margin-top:5px;">' +
									        '<tr class="tabtt1">' +
									          '<td width="3%" align="center" nowrap="nowrap">序号</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">工厂</td>' +
									          '<td width="15%" align="center" nowrap="nowrap">仓位编号</td>' +
									          '<td width="25%" align="center" nowrap="nowrap">仓位名称</td>' +
									          '<td width="5%" align="center" nowrap="nowrap">库存</td>' +
									          '<td width="7%" align="center" nowrap="nowrap">出库数量</td>' +
									          //'<td width="30%" align="center" nowrap="nowrap">出库备注</td>' +
									        '</tr>' +
						                    '<tbody id="stores_tbody"></tbody>' +
					 					'</table>' +
					 			   '</div>');
			if (result[0].count == 0) {
				$("#stores_tbody").append('<tr>' +
                			  				'<td colspan="8" style="color:red" align="center">没有查到相关仓库信息</td>' +
                       					  '</tr>');
			} else {
				for ( var i in result[0].list) {
	
						var factory_id;//工厂
						if (result[0].list[i].factory_id == "null") {
		  					factory_id = "";
						} else {
		  					factory_id = result[0].list[i].factory_id;
						}
						factory_id = factory_id.toString();
	
						var store_id;//仓库
						if (result[0].list[i].store_id == "null") {
							store_id = "";
						} else {
							store_id = result[0].list[i].store_id;
						}
	
						var storeNum;//仓位编号
						var color;
						if (result[0].list[i].storeNum == "null") {
							storeNum = "";
						} else {
							storeNum = result[0].list[i].storeNum;
							if (0 == parseInt(storeNum, 10)) {
								color = "";
							} else {
								color = "#6699FF";
							}
						}
	
						var store_desc;//仓位名称
						if (result[0].list[i].store_desc == "null") {
							store_desc = "";
						} else {
							store_desc = result[0].list[i].store_desc;
						}
						
						var exist_store_html = "";
						if (storeNum > 0) exist_store_html = "<span style='color:green;'>有</span>";
						else exist_store_html = "<span style='color:#F00;'>无</span>";
		
						$("#stores_tbody").append('<tr>' +
	                            '<td align="center" nowrap="nowrap">' + result[0].list[i].vs + '</td>' +
	                            '<td align="left" nowrap="nowrap"><input type="hidden" id="factory_id" name="factory_id" value="' + factory_id + '" />' + factory_id + '</td>' +
	                            '<td align="left" nowrap="nowrap"><input type="hidden" id="store_id" name="store_id" value="' + store_id + '" />' + store_id + '</td>' +
	                            '<td align="left" nowrap="nowrap"><input type="hidden" id="store_desc" name="store_desc" value="' + store_desc + '" />' + store_desc + '</td>' +
	                            '<td align="center" nowrap="nowrap" class="storeNum" style="color:' + color + '" store="' + storeNum + '">' + exist_store_html + '</td>' +
	                            '<td align="left" nowrap="nowrap"><input type="text" name="dts_counts" id="dts_counts" class="dts_counts" size="5" maxlength="10" value="0" /></td>' +
	                            //'<td align="left" nowrap="nowrap"><input type="text" name="dst_remarks" id="dst_remarks" size="20" maxlength="40" /></td>' +
	                         '</tr>');
					}

				$(".dts_counts").each(function(){
						$(this).focus(setOnlyNum).keyup(function(){
							var thisDtsCounts = parseInt($(this).val(), 10);
							if (isNaN(thisDtsCounts)) {
								thisDtsCounts = 0;
								$(this).val(thisDtsCounts);//Chrome error
							}
							//if (thisDtsCounts > counts) {
							//	alert("您输入的出货数量已超过〖该客户购买数量〗！");
							//	$(this).val(0);
							//	return false;
							//} 														//2012-03-03注释
							var thisStoreNum = parseInt($(this).parent().prev().attr('store'), 10);
							if (isNaN(thisStoreNum)) {
								thisStoreNum = 0;
							}
							
							var buy_count = 0;
							$(".dts_counts").each(function(){
								var n = parseInt($(this).val(), 10);
								if (isNaN(n)) {
									n = 0;
								}
								buy_count += n;
							});
							$("#buy_count").text(buy_count.toString());
							$("#counts", parObj).val(buy_count);
							var thisPrice = $("#price", parObj).val();
							$("#pdAmount", parObj).text((buy_count*thisPrice).toFixed(2));
							getTotalPdNum("counts", $("#showSalesOrderTbody"));
							getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
							
							if (thisDtsCounts > thisStoreNum) {
								alert("您输入的出货数量已超过〖该仓库最大库存〗！");
								$(this).val(0);

								var buy_num = 0;
								$(".dts_counts").each(function(){
									var n = parseInt($(this).val(), 10);
									if (isNaN(n)) {
										n = 0;
									}
									buy_num += n;
								});
								$("#buy_count").text(buy_num.toString());
								$("#counts", parObj).val(buy_num);
								$("#pdAmount", parObj).text((buy_num*thisPrice).toFixed(2));
								getTotalPdNum("counts", $("#showSalesOrderTbody"));
								getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
								
								return false;
							}

							//验证仓库保留数量-2：Begin
//							var difference = thisStoreNum - thisDtsCounts;
//							if (difference < reserverd_stock) {
//								alert("您填写的购买数量过多，仓库剩余产品〖" + md_name + "〗总库存量低于最低保留数量" + reserverd_stock + "个");
//								$(this).val(0);

//								var buy_num = 0;
//								$(".dts_counts").each(function(){
//									var n = parseInt($(this).val(), 10);
//									if (isNaN(n)) {
//										n = 0;
//									}
//									buy_num += n;
//								});
//								$("#buy_count").text(buy_num.toString());
//								$("#counts", parObj).val(buy_num);
//								$("#pdAmount", parObj).text((buy_num*thisPrice).toFixed(2));
//								getTotalPdNum("counts", $("#showSalesOrderTbody"));
//								getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
//								
//								return false;
//							}
							//验证仓库保留数量-2：End
						});
					});
				}
			//回显已分配的仓位信息
			//出库数量[#####]工厂ID[#####]仓库ID[#####]仓库名称[@@@@@]出库数量[#####]工厂ID[#####]仓库ID[#####]仓库名称........
			if ("" != str) {
				var total_arr = new Array(); 
				total_arr = str.split("[@@@@@]");
				var last_counts = 0;
				for ( var i = 0; i < total_arr.length; i++) {
					var arr = total_arr[i].split("[#####]");
					last_counts = last_counts + parseInt(arr[0], 10);
				}
				//alert("counts = " + counts + "_______" + "last_counts =" + last_counts);
				if (counts == last_counts) {
					//alert("相同");
					for ( var i = 0; i < total_arr.length; i++) {
						var arr = total_arr[i].split("[#####]");
						var num = arr[0];
						var factory_id = arr[1];
						var store_id = arr[2];
						$("tr", "#stores_tbody").each(function(){
							if ($("#factory_id", $(this)).val() == factory_id && $("#store_id", $(this)).val() == store_id) {
								$("#dts_counts", $(this)).val(num);
							}
						});
					}
				} else {
					//alert("不相同");
					$("#ck", parObj).val("");
					$.jGrowl("您更改了产品[" + md_name + "]购买数量，请重新选择仓库填写购买数量！", {theme:'success', life: 4500, header: '提示'});
				}
			}

        }
    }); 
}$("#discount").val()

//折扣
function discount(md_name, price_ref, price_min, parObj, price, dcount){
	var $price = $(dcount).parent().parent().children("#price");
// 	$("#dis_button").attr("disabled", "disabled");
	$("#discount_pd").text(md_name);
	$("#discount_price_ref").text(parseFloat(price_ref).toFixed(2));
	$("#discount").val("").unbind("blur");
	$("#dis_button").unbind("click");
	$("input[type='radio'][name='discount_radio']").unbind("click").attr("checked", false);

	$("input[type='radio'][name='discount_radio']").click(function(){
		if ($(this).val() == 0) {
			$("#discount").css({"background":"#fff", "color":"#000"}).attr("readonly", "");
//  			$("#dis_button").attr("disabled", "disabled");
		} else {
			var discount_price = price_ref * ($(this).val() * 0.1);
			$("#discount").css({"background":"#f2f2f2", "color":"#999999"}).attr("readonly", "readonly");
			if (parseFloat(discount_price) < parseFloat(price_min)) {
				alert("选择该折扣后的单价小于该商品的价格下限 〖￥" + price_min + "〗！");
//  				$("#dis_button").attr("disabled", "disabled");
				$("#discount").val("");
				$("input[type='radio'][name='discount_radio']").each(function(){
					$(this).attr("checked", false);
				});
				return;
			} else {
				$("#discount").val(discount_price.toFixed(2));
//  				$("#dis_button").removeAttr("disabled");
			}
		}
		
	});
	
	var thisPrice;
// 	$("#discount").blur(function(){
// 		alert($(this).attr("readonly"));
// 		if ($(this).attr("readonly") == false) {
// 			if ($(this).val() == "") {
// 				thisPrice = 0;
// 			} else {
// 				thisPrice = $(this).val();
// 			}
			
// 			if (parseFloat(thisPrice) < parseFloat(price_min)) {
// 				alert("您输入的单价小于该商品的价格下限 〖￥" + price_min + "〗！");
// //  				$("#dis_button").attr("disabled", "disabled");
// 				return false;
// 			}
// 			if (parseFloat(thisPrice) >= parseFloat(price_ref)) {
// 				alert("折扣价格必须低于该商品的参考价格〖￥" + price_ref + "〗！");
// //  				$("#dis_button").attr("disabled", "disabled");
// 				return false;
// 			}
// //  			$("#dis_button").removeAttr("disabled");
// 		}
// 	});
	
	$("#dis_button").click(function(){
			if ($(this).val() == "") {
				thisPrice = 0;
			} else {
				thisPrice = $(this).val();
			}
			
			if (parseFloat(thisPrice) < parseFloat(price_min)) {
				alert("您输入的单价小于该商品的价格下限 〖￥" + price_min + "〗！");
//  				$("#dis_button").attr("disabled", "disabled");
				return false;
			}
			if (parseFloat(thisPrice) >= parseFloat(price_ref)) {
				alert("折扣价格必须低于该商品的参考价格〖￥" + price_ref + "〗！");
//  				$("#dis_button").attr("disabled", "disabled");
				return false;
			}
//  			$("#dis_button").removeAttr("disabled");
			if(confirm("确定该折扣金额？")){
				$price.val($("#discount").val());
				var counts = parseInt($("#counts", parObj).val(), 10);
// 				$("#pdAmount", lastTR).text(money.toFixed(2));
				$("#pdAmount", parObj).text((counts * $("#discount").val()).toFixed(2));
				getTotalPdNum("counts", $("#showSalesOrderTbody"));
				getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
			} else {
				return false;
			}
		
	});
}

//修改订单页面删除已购买产品方法事件
function deletePd(obj){
	//alert(obj.attr("id"));
	task_index--;
	//alert("task_index = " + task_index);
	$("#md_name", obj).removeAttr("dataType");
	$("#counts", obj).removeAttr("dataType");
	$("#price", obj).removeAttr("dataType");
	obj.remove();
	//处理删除字符
	$("#goods_title").empty().text("添加商品：");
	var i = 1;
	$("tr", "#showSalesOrderTbody").each(function(){
		if (i <= task_index) {
			$(this).find("td").eq(0).text(i);
			i++;
		}
	});
	//计算合计数量
	getTotalPdNum("counts", $("#showSalesOrderTbody"));
	//计算合计金额
	getTotalPdMoney("pdAmount", $("#showSalesOrderTbody"));
}

function getBuyerInfo(buyerId){
	if ("" != buyerId) {
		if (isIdCard(buyerId)) {
			$.ajax({
				type: "POST" , 
		        url: 'KonkaXxZmdAddSalesOrder.do' , 
		        data: "method=getBuyerInfo&zmd_id=${af.map.zmd_id}" + "&buyer_id=" + buyerId + "&n=" + Math.random(), 
		        dataType: "json" , 
		        //async: false, 
		        error: function (request, settings) {alert(" 数据加载请求失败！ "); }, 
		        success: function (result) {
					if (result.count == 1) {
						$("#buyer_name").val(result.buyer_name);
						$("#buyer_sex").val(result.buyer_sex);
						$("#buyer_birthday").val(result.buyer_birthday);
						$("#buyer_tel").val(result.buyer_tel);
						$("#buyer_phone").val(result.buyer_phone);
						$("#buyer_link_addr").val(result.buyer_link_addr);
						//回显客户所在区域
						$("#buyer_province").val(result.buyer_province).change();
						$("#buyer_city").val(result.buyer_city).change();
						$("#buyer_country").val(result.buyer_country).change();
						$("#buyer_town").val(result.buyer_town);
					}
		        }
			});
		} else {
			alert("请填写正确的身份证号！");
			$("#buyer_id").focus();
		}
	}
}

function isIdCard(number){
    var date, Ai;  
    var verify = "10X98765432";  
    var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];  
    var area = ['','','','','','','','','','','','北京','天津','河北','山西','内蒙古','','','','','','辽宁','吉林','黑龙江','','','','','','','','上海','江苏','浙江','安微','福建','江西','山东','','','','河南','湖北','湖南','广东','广西','海南','','','','重庆','四川','贵州','云南','西藏','','','','','','','陕西','甘肃','青海','宁夏','新疆','','','','','','台湾','','','','','','','','','','香港','澳门','','','','','','','','','国外'];  
    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[xX0-9])))$/i);
    if(re == null) return false;
    if(re[1] >= area.length || area[re[1]] == "") return false;  
    if(re[2].length == 12){  
        Ai = number.substr(0, 17);  
        date = [re[9], re[10], re[11]].join("-");  
    }  
    else{  
        Ai = number.substr(0, 6) + "19" + number.substr(6);  
        date = ["19" + re[4], re[5], re[6]].join("-");  
    }  
    if(!this.IsDate(date, "ymd")) return false;
    var sum = 0;
    for(var i = 0;i<=16;i++){
        sum += Ai.charAt(i) * Wi[i];
    }
    Ai +=  verify.charAt(sum%11);  
    return (number.length ==15 || number.length == 18 && number.toUpperCase() == Ai); 
}

function IsDate(op, formatString){
	formatString = formatString || "ymd";
	var m, year, month, day;
	switch(formatString){
		case "ymd" :
			m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
			if(m == null ) return false;
			day = m[6];
			month = m[5]*1;
			year =  (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
			break;
		case "dmy" :
			m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
			if(m == null ) return false;
			day = m[1];
			month = m[3]*1;
			year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
			break;
		default :
			break;
	}
	if(!parseInt(month)) return false;
	month = month==0 ?12:month;
	var date = new Date(year, month-1, day);
    return (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth()+1) && day == date.getDate());
	function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
}

function setOnlyNum() {
	$(this).css("ime-mode", "disabled");
	$(this).attr("t_value", "");
	$(this).attr("o_value", "");
	$(this).bind("dragenter",function(){
		return false;
	});
	var regInteger = /^[-\+]?\d+$/;//整数
	$(this).keypress(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).keyup(function (){
		if(!this.value.match(regInteger))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(regInteger))this.o_value=this.value;
	}).blur(function (){
		if(!this.value.match(regInteger))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value;}
		if(this.value.length == 0) this.value = "0";
	});
}

function setOnlyNum1() {
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
		if(this.value.length == 0) this.value = "0";
	});
	//this.text.selected;
}

/**
 * @name replaceAll JS用于替换一个字符串中对应的所有的字符串
 * @param String  initStr 原字符串。
 * @param String  rstr  要转化的字符串。
 * @param String  rs  转换成的字符串
 * @return String str;
 */
function replaceAll(initStr,rstr,rs){ 
 	str='';
  	while(initStr.indexOf(rstr)!=-1){
	   k=initStr.indexOf(rstr);
	   initStr=initStr.replace(rstr,rs);
	   str+=initStr.substr(0,k+rs.length);
	   initStr=initStr.substr(k+rs.length);
  	}
  	str+=initStr;
 	return str;
}
 
 //自适应高度
 function resizeFrameHeight(offset, min_height) {
		// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
		$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]>--></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>