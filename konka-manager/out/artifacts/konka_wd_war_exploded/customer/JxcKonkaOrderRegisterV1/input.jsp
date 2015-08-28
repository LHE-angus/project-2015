<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />-->
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}

/* ------------------------------------------------- */
#tabs{
  overflow: hidden;
  width: 100%;
  margin: 0;
  padding: 0;
  list-style: none;
}
#tabs li{
  float: left;
  margin: 0 .2em 0 0;
  font-size: 15px;
  font-weight: bold;
}
#tabs a{
  position: relative;
  background: #ddd;
  background-image: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#ddd));
  background-image: -webkit-linear-gradient(top, #fff, #ddd);
  background-image: -moz-linear-gradient(top, #fff, #ddd);
  background-image: -ms-linear-gradient(top, #fff, #ddd);
  background-image: -o-linear-gradient(top, #fff, #ddd);
  background-image: linear-gradient(to bottom, #fff, #ddd);  
  padding: .4em 2em;
  float: left;
  text-decoration: none;
  color: #74685F;
  border-left: 1px solid #CCC;
  border-top: 1px solid #CCC;
  border-right: 1px solid #CCC;
  text-shadow: 0 1px 0 rgba(255,255,255,.8);
  -webkit-border-radius: 5px 5px 0 0;
  -moz-border-radius: 5px 5px 0 0;
  border-radius: 5px 5px 0 0;
  -moz-box-shadow: 0 2px 2px rgba(0,0,0,.4);
  -webkit-box-shadow: 0 2px 2px rgba(0,0,0,.4);
  box-shadow: 0 2px 2px rgba(0,0,0,.4);
}
#tabs a:hover,
#tabs a:hover::after,
#tabs a:focus,
#tabs a:focus::after{background: #fff;}
#tabs a:focus{outline: 0;}
#tabs a::after{
  content:'';
  position:absolute;
  z-index: 1;
  top: 0;
  right: -3px;/*-.5em;*/
  bottom: 0;
  width: 1em;
  background: #ddd;
  background-image: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#ddd));
  background-image: -webkit-linear-gradient(top, #fff, #ddd);
  background-image: -moz-linear-gradient(top, #fff, #ddd);
  background-image: -ms-linear-gradient(top, #fff, #ddd);
  background-image: -o-linear-gradient(top, #fff, #ddd);
  background-image: linear-gradient(to bottom, #fff, #ddd);  
  -moz-box-shadow: 2px 2px 2px rgba(0,0,0,.4);
  -webkit-box-shadow: 2px 2px 2px rgba(0,0,0,.4);
  box-shadow: 2px 2px 2px rgba(0,0,0,.4);
  -webkit-transform: skew(10deg);
  -moz-transform: skew(10deg);
  -ms-transform: skew(10deg);
  -o-transform: skew(10deg);
  transform: skew(10deg);
  -webkit-border-radius: 0 5px 0 0;
  -moz-border-radius: 0 5px 0 0;
  border-radius: 0 5px 0 0;
}
#tabs #current a{background: #fff;z-index: 3;color: #8b0000;}
#tabs #current a::after{background: #fff;z-index: 3;}
/* ------------------------------------------------- */
#content{
    background: #fff;
    padding: 2em;
	/*height: 220px;*/
	position: relative;
	z-index: 2;	
    /*-moz-border-radius: 0 5px 5px 5px;
    -webkit-border-radius: 0 5px 5px 5px;
    border-radius: 0 5px 5px 5px;
    -moz-box-shadow: 0 -2px 3px -2px rgba(0, 0, 0, .5);
    -webkit-box-shadow: 0 -2px 3px -2px rgba(0, 0, 0, .5);
    box-shadow: 0 -2px 3px -2px rgba(0, 0, 0, .5);*/
    border:1px solid #ccc;
    -moz-border-radius: 0 5px 5px 5px;
    -webkit-border-radius: 0 5px 5px 5px;
    border-radius: 0 5px 5px 5px;
    -moz-box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5), 0 0 40px rgba(0, 0, 0, 0.06) inset; 
    -webkit-box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5), 0 0 60px rgba(0, 0, 0, 0.06) inset;
    box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5), 0 0 40px rgba(0, 0, 0, 0.06) inset;
}
#content h2, #content h3, #content p{margin: 0 0 15px 0;}
/* ------------------------------------------------- */
#about{color: #999;}
#about a{color: #eee;}

/*table css*/
.navClass {background-color: #abd589;border-collapse:collapse;}
.navClass th {background:#F5F4F4;color:#74685F;font-size:12px;font-weight:bold;height:20px;line-height:20px;text-align:center;padding:3px;border:1px solid #CCC;}
.navClass td {padding:3px;height:18px;background-color:#fff;border:1px solid #CCC;}

a:HOVER {color:#CD0000;text-decoration:none;}

input[type='submit'],input[type='button'] { border-radius:3px; border:1px solid #CCC; padding:3px 5px;background-color:#eee; color:#555; cursor: pointer; margin-right: 1px;}
input[type='submit']:hover,input[type='button']:hover { background-color:#ccc; border:1px solid #74685F;}
input[type='submit']:active,input[type='button']:active { color:#F00; }
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont1" style="position:relative;">
  		<html-el:form action="/manager/JxcKonkaOrderRegisterV1" enctype="multipart/form-data">
			<html-el:hidden property="method" value="saveOrder" styleId="method"/>
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="trade_index" styleId="trade_index" value="${af.map.tradeIndex}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
	      	<html-el:hidden property="is_submit" styleId="is_submit" value="0" />
	      	<html-el:hidden property="pro_ids" styleId="pro_ids"/>
			<html-el:hidden property="pro_names" styleId="pro_names"/>
			<ul id="tabs">
			    <li><a href="#" name="tab1">订单信息</a></li>
			    <li><a href="#" name="tab2">审核信息</a></li>
			    <li><a href="#" name="tab3">附件</a></li>
			</ul>
			<div id="content"> 
			    <div id="tab1">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				        <tr>
				        	<!--<th colspan="4" style="font-size:15px;">订单信息</th>-->
				        	<td class="title_item" colspan="4" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">订单信息</td>
				        </tr>
				        <tr>
							<td class="title_item" width="15%"><font color="red">*</font>订单流水号：</td>
							<td colspan="3"><font color="red">${af.map.tradeIndex}</font></span></td>
						</tr>
						<tr>
							<td class="title_item" width="15%">客户名称：</td>
							<td width="35%">
								<c:if test="${empty konkaR3ShopList}">
									<html-el:text property="user_shop_name" styleId="user_shop_name" value="${af.map.userShopName}" style="width:90%" maxlength="20" styleClass="webinput" readonly="true" />
								</c:if>
								<c:if test="${not empty konkaR3ShopList}">
									<html-el:select property="ag">
										<c:forEach var="cur" items="${konkaR3ShopList}" varStatus="vs">
											<html-el:option value="${cur.id}">${cur.customer_name}</html-el:option>
										</c:forEach>
									</html-el:select>
								</c:if>
							</td>
							<td class="title_item" width="15%">售达方编码：</td>
							<td width="15%">${af.map.r3_code}</td>
						</tr>
						<!-- 常规信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">常规信息</td>
						</tr>
						<tr>
							<td class="title_item"><font color="red">*</font>订单类型：</td>
							<td>
								<html-el:select property="process_state" styleId="process_state">
									<html-el:option value="1">普通流程</html-el:option>
									<html-el:option value="2">特殊流程</html-el:option>
								</html-el:select> 
							</td>
							<td class="title_item">订单日期：</td>
							<td><html-el:text property="order_date" styleId="order_date" size="10" maxlength="9" readonly="true" styleClass="webinput" value="${af.map.today}"/></td>
						</tr>
						<tr>
							<td class="title_item">审核状态：</td>
							<td>
								<html-el:select property="audit_state" styleId="audit_state" disabled="true">
									<html-el:option value="0">未审核</html-el:option>
									<html-el:option value="1">审核中</html-el:option>
									<html-el:option value="2">审核未通过</html-el:option>
									<html-el:option value="3">审核通过</html-el:option>
								</html-el:select>
							</td>
							<td class="title_item">出货状态：</td>
							<td>
								<html-el:select property="" styleId="" disabled="true">
									<html-el:option value="">未出货</html-el:option>
									<html-el:option value="">出货中</html-el:option>
									<html-el:option value="">已出货</html-el:option>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td class="title_item">制单人：</td>
							<td></td>
							<td class="title_item">业务员：</td>
							<td></td>
						</tr>
						<tr>
							<td class="title_item">备注：</td>
							<td colspan="3"><html-el:text styleId="remark" property="remark" maxlength="120" styleClass="webinput" style="width:80%"/></td>
						</tr>
						<!-- 付款信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">付款信息</td>
						</tr>
						<tr>
							<td class="title_item"><font color="red">*</font>货款支付方式：</td>
							<td>
								<label for="pay_type_4"><html-el:radio property="pay_type" styleId="pay_type_4" value="4" /> 现汇</label>
								<span style="color:#ccc;margin-left:5em;">采用现金汇款或转帐的方式支付货款</span><br />
								<label for="pay_type_5"><html-el:radio property="pay_type" styleId="pay_type_5" value="5" /> 帐期</label>
								<span style="color:#ccc;margin-left:5em;">使用您的信用额度余额支付货款</span><br />
								<label for="pay_type_6"><html-el:radio property="pay_type" styleId="pay_type_6" value="6" /> 承兑</label>
								<span style="color:#ccc;margin-left:5em;">使用银行提供的承兑支票支付货款</span>
							</td>
							<td class="title_item">可用额度：</td>
							<td></td>
		        		</tr>
						<!-- 收货信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">收货信息</td>
						</tr>
						<tr>
							<td class="title_item" width="15%"><font color="red">*</font>配送方式：</td>
							<td>
								<label for="send_type_1"><html-el:radio property="send_type" styleId="send_type_1" value="1" /> 自提</label>
								<label for="send_type_2"><html-el:radio property="send_type" styleId="send_type_2" value="2" /> 配送</label>
							</td>
							<td class="title_item" width="15%">收货人姓名：</td>
		          			<td width="35%"><html-el:text property="user_name" styleId="user_name" value="${u.real_name}" style="width:50%" maxlength="20" styleClass="webinput"/></td>
						</tr>
						<tr>
							<td class="title_item" width="15%">收货人固定电话：</td>
							<td width="35%">
								<html-el:text property="user_tel" styleId="user_tel" value="${af.map.userTel}" style="width:40%" maxlength="20" styleClass="webinput"/>
								<span id="span_msg_tel__error" style="display: none;"><font style="color: red">正确格式,如：0551-3698754</font></span>
							</td>
							<td class="title_item" width="15%">收货人手机：</td>
							<td>
								<html-el:text property="user_phone" styleId="user_phone" value="${u.link_phone}" style="width:40%" maxlength="11" styleClass="webinput"/>
								<span id="span_msg_phone__error" style="display: none;"><font style="color: red">正确格式,如：13966557733</font></span>
							</td>
						</tr>
						<tr>
							<td class="title_item" width="15%">收货人所属地区：</td>
							<td colspan="3" width="85%">
								<html-el:hidden property="user_p_index" styleId="user_p_index" />
								<select name="province" id="province" style="width:175px;">
  									<option value="${af.map.province}">请选择...</option>
								</select>
								&nbsp;
								<select name="city" id="city" style="width:157px;">
  									<option value="${af.map.city}">请选择...</option>
								</select>
								&nbsp;
								<select name="country" id="country" style="width:157px;">
  									<option value="${af.map.country}">请选择...</option>
    							</select>
  							</td>
						</tr>
						<tr>
							<td class="title_item">收货人地址：</td>
							<td colspan="3"><html-el:text property="user_addr" styleId="user_addr" value="${u.link_addr}" style="width:80%" maxlength="100" styleClass="webinput"/></td>
						</tr>
						<tr>
							<td class="title_item">收货人备注：</td>
							<td colspan="3"><html-el:text property="user_remark" styleId="user_remark" style="width:80%" maxlength="100" styleClass="webinput"/></td>
						</tr>
						<!-- 汇总信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">汇总信息</td>
						</tr>
						<tr>
							<td class="title_item">申请数量：</td>
							<td><html-el:text property="" styleId="" style="width:40%" maxlength="11" styleClass="webinput"/></td>
							<td class="title_item">申请金额：</td>
							<td><html-el:text property="" styleId="" style="width:40%" maxlength="11" styleClass="webinput"/></td>
						</tr>
						<tr>
							<td class="title_item">审核数量：</td>
							<td><html-el:text property="" styleId="" style="width:40%" maxlength="11" styleClass="webinput"/></td>
							<td class="title_item">审核金额：</td>
							<td><html-el:text property="" styleId="" style="width:40%" maxlength="11" styleClass="webinput"/></td>
						</tr>
						<!-- 产品明细 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">产品明细</td>
						</tr>
						<tr>
							<td colspan="4">
  								<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
    								<tr class="title_top">
										<td nowrap="nowrap">产品型号</td>
										<!--<td width="12%" nowrap="nowrap">产品类型</td>-->
										<td width="8%" nowrap="nowrap">数量</td>
										<td width="5%" nowrap="nowrap">单位</td>
										<td width="8%" nowrap="nowrap">单价</td>
										<td width="10%" nowrap="nowrap">金额</td>
										<td width="10%" nowrap="nowrap">折扣金额</td>
										<td width="5%" nowrap="nowrap">折扣(%)</td>
										<td width="12%" nowrap="nowrap">产品备注</td>
										<td width="5%" align="center" style="cursor:pointer;" title="添加" onclick="getPePdModel();"><img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:text-bottom;"/></td>
									</tr>
									<tr id="trHidden" style="display:none;">
										<td align="center">
											<html-el:hidden property="pd_ids" styleId="pd_ids" />
											<html-el:hidden property="pd_id" styleId="pd_id" />
											<html-el:hidden property="md_name" styleId="md_name1" styleClass="md_name1" /> 
											<span id="md_name" class="md_name"></span><span id="span_1"></span>
										</td>
										<td align="center"><html-el:text property="good_count" styleId="good_count" value="1" size="4" maxlength="4" styleClass="webinput good_count" /></td>
										<td align="center"><html-el:text property="good_unit" styleId="good_unit" value="台" maxlength="20" size="5" styleClass="webinput" /></td>
										<td align="center"><html-el:text property="good_price" value="0" styleId="good_price"  maxlength="8" size="8" styleClass="webinput" /></td>
										<td align="center">
											<span id="good_sum_price">0</span>
											<html-el:hidden property="sum_price" styleId="sum_price" />
										</td>
										<td align="center">
											<html-el:text property="good_discount_price" value="0" styleId="good_discount_price"  maxlength="10" size="10" styleClass="webinput" />
											<html-el:hidden property="discount_price" styleId="discount_price" />
										</td>
										<td align="center"><html-el:text property="good_discount" value="0" styleId="good_discount"  maxlength="5" size="5" styleClass="webinput" /></td>
										<td align="center"><html-el:text property="pd_remark" size="20" styleId="pd_remark" maxlength="100" styleClass="webinput" /></td>
										<td align="center" title="删除"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:text-bottom;"/></td>
									</tr>
									<tbody id="tbodyContent"></tbody>
									<tr class="title_top">
										<td>合计</td>
										<td>
											<span id="dd_count_sum"></span>
											<html-el:hidden property="order_num" styleId="order_num" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>
											<span id="dd_money_sum"></span>
											<html-el:hidden property="money_sum" styleId="money_sum" />
										</td>
										<td>
											<span id="dd_discount_sum"></span>
											<html-el:hidden property="discount_price_sum" styleId="discount_price_sum" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
				<!-- 同步信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">同步信息</td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item">销售凭证类型</td>
							<td width="12%"><input type="text" name="doc_type" value="ZFOR" class="webinput" readonly="readonly" /></td>
							<td width="8%" align="left" class="title_item">销售组织</td>
							<td width="12%"><input type="text" name="sales_org" id="sales_org" value="${sales_org}" class="webinput" readonly="readonly" /></td>
						</tr>
						<tr>
							<td width="8%" colspan="1" align="left" class="title_item">分销渠道</td>
							<td width="12%"><input type="text" name="distr_chan" value="10" class="webinput" readonly="readonly" /></td>
							<td width="8%" align="left" class="title_item">产品组</td>
							<td width="12%"><input type="text" name="division" value="10" class="webinput" readonly="readonly" /></td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item">售达方</td>
							<td width="12%">
								<c:if test="${empty agList}"><input type="text" name="ag" value="${ag}" class="webinput" readonly="readonly" /></c:if>
								<c:if test="${not empty agList}">
									<html-el:select property="ag">
										<c:forEach var="cur" items="${agList}" varStatus="vs">
											<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
										</c:forEach>
					            	</html-el:select>
					            </c:if>
							</td>
							<td width="8%" colspan="1" align="left" class="title_item">出具发票方</td>
							<td width="12%">
								<c:if test="${empty reList}"><input type="text" name="re" value="${re}" class="webinput" readonly="readonly" /></c:if>
								<c:if test="${not empty reList}">
									<html-el:select property="re">
										<c:forEach var="cur" items="${reList}" varStatus="vs">
											<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
										</c:forEach>
				            		</html-el:select>
				            	</c:if>
				            </td>
						</tr>
						<tr>
							<td width="8%" colspan="1" align="left" class="title_item">付款方</td>
							<td width="12%">
								<c:if test="${empty rgList}"><input type="text" name="rg" value="${rg}" class="webinput" readonly="readonly" /></c:if>
								<c:if test="${not empty rgList}">
									<html-el:select property="rg">
										<c:forEach var="cur" items="${rgList}" varStatus="vs">
											<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
										</c:forEach>
					            	</html-el:select>
					            </c:if>
				            </td>
							<td width="8%" colspan="1" align="left" class="title_item">送达方</td>
							<td width="12%">
								<c:if test="${empty weList}"><input type="text" name="we" value="${we}" class="webinput" readonly="readonly" /></c:if>
								<c:if test="${not empty weList}">
									<html-el:select property="we">
										<c:forEach var="cur" items="${weList}" varStatus="vs">
											<html-el:option value="${cur.KUNN2}">${cur.KUNN2}</html-el:option>
										</c:forEach>
					            	</html-el:select>
					            </c:if>
							</td>
						</tr>
				    </table>
			    </div>
			    <div id="tab2"></div>
				<!-- 附件 -->			    
			    <div id="tab3">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
			    		<tr>
				        	<td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">附件</td>
				        </tr>
				        <tr>
				        	<td align="center">
				        		<div style="width:90%;padding:5px 5px 0 5px;border-bottom:1px solid #74685F;display:block;text-align:left;font-size:14px;">
				        			<a href="javascript:addFile();">添加附件<img src="${ctx}/styles/jxc/images/+.gif" style="vertical-align:middle;"/></a>
				        		</div>
				        		<div style="width:90%;padding:5px 5px 0 5px;">
				        			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;">
										<thead id="nav" style="width:99%;background:#abd589;">
								  			<tr>
									  			<th width="3%" nowrap="nowrap">序号</th>
									  			<th nowrap="nowrap">附件</th>
									  			<th nowrap="nowrap">备注</th>
									  			<th width="3%" nowrap="nowrap">操作</th>
									  		</tr>
								  		</thead>
								  		<tbody id="fileTbody"></tbody>
								  	</table>
				        		</div>
				        	</td>
				        </tr>
			    	</table>
			    </div>
			</div>
			
			<div style="position:absolute;top:0;right:0;">
				<html-el:button property="btn_submit" styleId="btn_submit" value=" 提 交 " />
				<html-el:button property="btn_retract" styleId="btn_retract" value=" 撤 回 " />
				<html-el:button property="btn_temp_save" styleId="btn_temp_save" value=" 保 存 " />
				<html-el:button property="btn_temp_save_add" styleId="btn_temp_save_add" value=" 保存并新建 " />
				<html-el:button property="btn_back" styleId="btn_back" value=" 返 回 " onclick="history.back();" />
			</div>
		</html-el:form>
	</div>
</div>
<!--<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>-->
<!--<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>--> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery.jgrowl.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/external/bgiframe/jquery.bgiframe.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.min.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/jquery.multiselect.filter.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/jquery-multiSelect/i18n/jquery.multiselect.zh.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script>
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];
$(document).ready(function(){
	//$("#right_contcont", window.parent.document).css("background", "#8B0000");//"#D4CDC7");//"#EBE3E2");
	
	$("#user_zip").keyup(function(){//邮编必须为数字
		var _reg = /^\d+$/;
		var user_zip =(this.value);
   		if (!_reg.test(user_zip)) {
   			$("#user_zip").val("");
   		}
	});
	$("#pay_type_6").attr("dataType","Group").attr("msg","至少选择一种货款支付方式！");
	$("#send_type_2").attr("dataType","Group").attr("msg","至少选择一种配送方式！");
	//所在地市联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city").attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#province").cs("${ctx}/jxc/CsAjax.do?method=getBaseProvinceList", "p_index", "0", false);

	//tabs切换Begin
	$("#content div[id^=tab]").hide(); // Initially hide all content
    $("#tabs li:first").attr("id","current"); // Activate first tab
    $("#content div[id^=tab]:first").fadeIn(); // Show first tab content
    $('#tabs a').click(function(e) {
        e.preventDefault();
        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
         	return       
        } else{             
	        $("#content div[id^=tab]").hide(); //Hide all content
	        $("#tabs li").attr("id",""); //Reset id's
	        $(this).parent().attr("id","current"); // Activate this
	        $('#' + $(this).attr('name')).fadeIn(); // Show content for current tab
        }
        window.parent.resizeFrameHeight('mainFrame', 3);
    });
	//tabs切换End

	//提交表单(验证价格是否为负和字段是否超过长度)
	$("#btn_submit").click(function(){
		if ($("#user_tel").val() != "") {
			var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			if (phone.exec($("#user_tel").val())){
				$("#span_msg_tel__error").hide();
			}else{
				$("#span_msg_tel__error").show();
				return false;
			}
		}
		if ($("#user_phone").val() != "") {
			var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/;
			if (mobile.exec($("#user_phone").val())){
				$("#span_msg_phone__error").hide();
			}else{
				$("#span_msg_phone__error").show();
				return false;
			}
		}
		if ($("#pd_id", "#tbodyContent").length == 0) {
	        alert("请至少添加一个订单产品信息！");
	        return false;
		}
		var notSelectedPd = false;
		$("#pd_id", "#tbodyContent").each(function(){
			if ("" == $.trim(this.value)){
				notSelectedPd = true;
			}
		});
		if (notSelectedPd) {
	        alert("您还没有选择产品，请选择！");
	        return false;
		}
		
	    if (judgeSelectedValueIsRepeat("pd_id", $("#tbodyContent"))) {
	        alert("您选择的产品型号有重复，请重新选择！");
	        return false;
	    }

		if (Validator.Validate(this.form, 1)){
			if (confirm("确定提交表单？")) {
				if($("#country").val() != ""){
					$("#user_p_index").val($("#country").val());
				}else{
					if($("#city").val() != ""){
						$("#user_p_index").val($("#city").val());
					}else{
						if($("#province").val() != ""){
							$("#user_p_index").val($("#province").val());
						}
					}
				}
				if ("${confirm}" == "1") {
					if (!confirm("没有找到上级分公司，订单将会丢失无法审核，确定要提交吗？（提示：您可以联系管理员进行客户分配后再登记）")) {
						return false;
					}
				}
				$("#is_submit").val(1);
				$("#btn_submit").attr("value", "提交中").attr("disabled", "true");
		        $("#btn_reset").attr("disabled", "true");
				f.submit();
			} else {
				return false;
			}
		}
	});

	// 暂存表单(验证价格是否为负和字段是否超过长度)
	$("#btn_temp_save").click(function(){
		if ($("#user_tel").val() != "") {
			var phone=/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			if (phone.exec($("#user_tel").val())){
				$("#span_msg_tel__error").hide();
			}else{
				$("#span_msg_tel__error").show();
				return false;
			}
		}
		if ($("#user_phone").val() != "") {
			var mobile=/^((\(\d{2,3}\))|(\d{3}\-))?(1[3458]\d{9})$/;
			if (mobile.exec($("#user_phone").val())){
				$("#span_msg_phone__error").hide();
			}else{
				$("#span_msg_phone__error").show();
				return false;
			}
		}
		if ($("#pd_id", "#tbodyContent").length == 0) {
	        alert("请至少添加一个订单产品信息！");
	        return false;
		}
		var notSelectedPd = false;
		$("#pd_id", "#tbodyContent").each(function(){
			if ("" == $.trim(this.value)){
				notSelectedPd = true;
			}
		});
		if (notSelectedPd) {
	        alert("您还没有选择产品，请选择！");
	        return false;
		}
		
	    if (judgeSelectedValueIsRepeat("pd_id", $("#tbodyContent"))) {
	        alert("您选择的产品型号有重复，请重新选择！");
	        return false;
	    }

		if (Validator.Validate(this.form, 1)){
			if($("#country").val() != ""){
				$("#user_p_index").val($("#country").val());
			}else{
				if($("#city").val() != ""){
					$("#user_p_index").val($("#city").val());
				}else{
					if($("#province").val() != ""){
						$("#user_p_index").val($("#province").val());
					}
				}
			}
			if (${confirm == 1}) {
				if (!confirm("没有找到上级分公司，订单将会丢失无法审核，确定要提交吗？（提示：您可以联系管理员进行客户分配后再登记）")) {
					return false;
				}
			}
			$("#btn_submit").attr("value", "提交中").attr("disabled", "true");
	        $("#btn_reset").attr("disabled", "true");
			f.submit();
		}
	});
});

window.task_index = 0;
window.index = 0;
function addFile(){
	index++; //解决file控件
	$("#fileTbody").append('<tr id="picModelTr_' + index + '">' +
								'<td align="center">1</td>' +
								'<td align="left" nowrap="nowrap"><input type="file" name="pic_file_' + index + '" id="pic_file_' + index + '" class="webinput" style="width:150px;" /></td>' +
								'<td align="center"><input type="text" name="file_desc" id="file_desc" class="webinput" style="width:94%" /></td>' +
								'<td align="center" nowrap="nowrap" style="cursor:pointer;"><img src="${ctx}/styles/jxc/images/x.gif" style="vertical-align:middle;" /></td>' +
						   '</tr>');

	task_index++;
	var lastTR = $("tr:last", "#fileTbody");
	lastTR.children().eq(0).text(task_index);
	$("input[type='file']", lastTR).attr("dataType", "Require").attr("msg", "请上传附件！");
	$("td:last", lastTR).click(function (){
		task_index--;
		$(this).parent().remove();
		var i = 1;
		$("tr", "#fileTbody").each(function(){
			if (i <= task_index) {
				$(this).find("td").eq(0).text(i);
				i++;
			}
		});
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	window.parent.resizeFrameHeight('mainFrame', 3);
}

/*处理返回值数据 ===start===*/
function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

function getPePdModel() { 
	//var returnValue = window.showModalDialog("${ctx}/jxcnokey/SelectPePdModel.do?selectype=mutiple&selects=" + $("#pd_ids").val() + "&azaz=" + Math.random(),window,"dialogWidth:600px;status:no;dialogHeight:370px");
    
	//if (returnValue != null && returnValue.ids != "") {
		$.dialog({
		title:  "产品列表",
		width:  400,
		height: 390,
        lock:true ,
		content:"url:${ctx}/customer/manager/SelectPePdModel.do?method=newList&selectype=mutiple&selects=" + $("#pd_ids").val() + "&azaz=" + Math.random()
	});
}

//取得窗口选择的产品并回显
function getProInfo(){
	var ids = $("#pro_ids").val();
	var names = $("#pro_names").val();
	if (ids != "") {
		//前一次操作返回的值  + 当前操作返回的值 = 当前的值
		var pd_ids_temp = "";
		if("" != $("#pd_ids").val()){
			pd_ids_temp = $("#pd_ids").val()+ "," + ids ;
		}else{
			pd_ids_temp = ids ;
		}
	    $("#pd_ids").val(pd_ids_temp);
	    
	    var pd_id_array = new Array();
	    var md_name_array = new Array();
	
	    pd_id_array = ids.split(",");
	    md_name_array = names.split(",");
	    
		for(var i = 0; i < pd_id_array.length; i++){

			$("#trHidden").clone().appendTo($("#tbodyContent")).show();
	    	var lastTR = $("tr:last", "#tbodyContent");
	    	
	    	var pd_id = $("#pd_id", lastTR);//产品型号id
	    	var md_name = $("#md_name", lastTR);//隐藏域产品型号名称
	    	var span_1 = $("#span_1", lastTR);//隐藏域产品型号名称
	    	var md_name1 = $("#md_name1", lastTR);//产品编号
	    	
			var good_count = $("#good_count", lastTR);//数量
			var good_unit = $("#good_unit", lastTR);//单位
			var good_price = $("#good_price", lastTR);//单价
			
			var good_sum_price = $("#good_sum_price", lastTR);//金额
			var sum_price = $("#sum_price", lastTR);//隐藏域金额
			var good_discount = $("#good_discount", lastTR);//折扣
			var good_discount_price = $("#good_discount_price", lastTR);//折扣金额
			var discount_price = $("#discount_price", lastTR);//隐藏域折扣金额
			
			var pd_remark = $("#pd_remark", lastTR);//产品备注
			
			good_count.attr("dataType","Number").attr("msg","请填写数量,且必须为正整数！");
			good_price.attr("dataType","Currency").attr("msg","请填写单价，且必须为正数！");
			good_discount.attr("dataType","Currency").attr("msg","请填写折扣，且必须为正数！");
			
			pd_id.val(pd_id_array[i]);//隐藏域选择的产品
	      	md_name.text(md_name_array[i]);
	      	md_name1.val(md_name_array[i]);
	      	
	      	
			good_count.val("1");
			good_unit.val("台");
			
			good_price.val("0");
			
			span_1.attr("id",md_name_array[i]);//改变id
			
			good_sum_price.text("0");
			sum_price.val("0");
			
			good_discount.val("0");
			good_discount_price.val("0");

			pd_remark.val("");

			calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price);
			good_discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//折扣金额
			discount_price.val(parseFloat(good_sum_price.text())*parseFloat(good_discount.val())/100);//隐藏域折扣金额
			calcPdNumAndPrice("tbodyContent");

			bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR);

			//删除操作，影响弹出页面的返回值
			$("td:last", lastTR).click(function (){
				var pd_id_td1 = $("#pd_id", $(this).parent()).val()+",";
				var pd_id_td2 = ","+$("#pd_id", $(this).parent()).val();
				var pd_ids_new1 = $("#pd_ids").val().replace(pd_id_td1,"");
				pd_ids_new2 = pd_ids_new1.replace(pd_id_td2,"");
				
				$("#pd_ids").val(pd_ids_new2);//删除掉的id，得到新的已经选择的id数组
				
		    	$(this).parent().remove();
		    	calcPdNumAndPrice("tbodyContent");

		    	//iframe高度动态变化
		    	window.parent.resizeFrameHeight('mainFrame', 3); 
		    }).css("cursor", "pointer");
		}
		resizeFrameHeight(2);
	}
}
/*处理返回值数据 ===end===*/

//判断产品型号是否选择重复
function judgeSelectedValueIsRepeat(objId, parObj){
	var isRepeat = false;
	var ary = new Array();
	$("#" + objId, parObj).each(function() {
		if ("" != $(this).val()) {
			ary.push($(this).val());
		}
	});
	var nary = ary.sort();  
	for(var i = 0; i < nary.length - 1; i++) {
		if (nary[i] == nary[i + 1]) {
			isRepeat = true;
			break;
		}  
    }  
	return isRepeat;
}

//输入数量和台数的绑定计算函数
function bindJhCountAndJhPrice(good_count, good_price, good_sum_price, sum_price, good_discount, good_discount_price, discount_price, lastTR) {
	good_count.keyup(function(){//数量
		var _reg = /^\d+$/;
		var count =(this.value);
   		if (!_reg.test(count)) {
   			good_count.val(1);
   			count = 1;
   		}
   		var price = parseFloat(good_price.val());//单价
   		if(isNaN(price)) price = 0;
   		
   		var discount = parseFloat(good_discount.val());//折扣
   		if(isNaN(discount)) discount = 0;
   		
   		count = parseFloat(count);//数量
   		if(isNaN(count)) count = 1;
   	   	
   		good_sum_price.text((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		//good_discount_price.val((count * price * discount/100).toFixed(2));//折扣金额
   		//discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
   		
   		dis_money = good_discount_price.val();
   		if(isNaN(dis_money)) dis_money = 0;
   		good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
		calcPdNumAndPrice("tbodyContent");
	});

	good_price.keyup(function(){//单价
		var price = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(price)) {
			good_price.val(0);
			price = 0;
		}
		price = parseFloat(this.value);
		if(isNaN(price)) price = 0;
		if(price > parseFloat(99999.99)){
			alert("价格太大了，上限为10万！");
			good_price.val(99999.99);
			price = 99999.99;
		}
   		var count = parseFloat(good_count.val());//数量
   		if(isNaN(count)) count = 1;

   		var discount = parseFloat(good_discount.val());//折扣
   		if(isNaN(discount)) discount = 0;
   		good_sum_price.text((count * price).toFixed(2));//金额
   		sum_price.val((count * price).toFixed(2));//隐藏域金额
   		//good_discount_price.val((count * price * discount/100).toFixed(2));//折扣金额
   		//discount_price.val((count * price * discount/100).toFixed(2));//隐藏域折扣金额
   		
   		dis_money = good_discount_price.val();
   		if(isNaN(dis_money)) dis_money = 0;
   		good_discount.val((count * price) == 0 ? '-' : (dis_money * 100 / (count * price)).toFixed(2));//折扣率
   		
		calcPdNumAndPrice("tbodyContent");
	});

	good_discount.keyup(function(){//折扣
		var discount = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			good_discount.val(0);
			discount = 0;
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
		if(discount > 100) {
			discount = 0;
			this.value = 0;
		}
   		var sum_price = parseFloat(good_sum_price.text());//金额
   		if(isNaN(sum_price)) sum_price = 0;
	   	
   		good_discount_price.val((discount * sum_price/100).toFixed(2));//折扣金额
   		discount_price.val((discount * sum_price/100).toFixed(2));//隐藏域折扣金额
		calcPdNumAndPrice("tbodyContent");
	});
	
	// 下面的代码是xxd新增，为放开可修改折扣金额输入框 add by xxd @20130702
	good_discount_price.keyup(function(){ // 折扣金额
		var discount = (this.value);
		var _reg = /^[\+]?\d*?\.?\d*?$/;
		if (!_reg.test(discount)) {
			good_discount_price.val(0);
			discount = 0;
		}
		discount = parseFloat(this.value);
		if(isNaN(discount)) discount = 0;
   		var sum_price = parseFloat(good_sum_price.text());//金额
   		if(isNaN(sum_price)) sum_price = 0;
	   	
   		good_discount.val(sum_price == 0 ? '-' : (discount * 100 / sum_price).toFixed(2));//折扣率
		calcPdNumAndPrice("tbodyContent");
	});
	// end. add by xxd @20130702
	
	calcPdNumAndPrice("tbodyContent");
}

//计算 单个的金额
function calcOneCountAndJhPrice(good_count, good_price, good_sum_price, sum_price) {
	var good_count = parseFloat(good_count.val());//数量
   	if(isNaN(good_count)) good_count = 1;
   	var good_price =  parseFloat(good_price.val());//单价
   	if(isNaN(good_price)) good_price = 0;
   	
   	good_sum_price.text((good_count * good_price).toFixed(2));//金额
   	sum_price.val((good_count * good_price).toFixed(2));//隐藏域金额
}

//计算 合计的 数量、 金额和折扣金额
function calcPdNumAndPrice(tbody_id) {
	var dd_count_sum = 0;
	var dd_money_sum = 0;
	var dd_discount_sum = 0;
	$("#good_count", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).val());
		if(isNaN(this_value)) this_value = 0;
		dd_count_sum += this_value;
	});

	$("#good_sum_price", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).text());
		if(isNaN(this_value)) this_value = 0;
		dd_money_sum += this_value;
	});
	
	$("#good_discount_price", $("#" + tbody_id)).each(function(){
		var this_value = parseFloat($(this).text());
		if(isNaN(this_value)) this_value = 0;
		dd_discount_sum += this_value;
	});
	
	$("#dd_count_sum").text(dd_count_sum);
	$("#dd_money_sum").text("￥" + (dd_money_sum.toFixed(2)));
	$("#dd_discount_sum").text("￥" + (dd_discount_sum.toFixed(2)));
	$("#pay_money").val(parseFloat(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2)).toFixed(2));
	//$("#money").val(dd_money_sum.toFixed(2)-dd_discount_sum.toFixed(2));
	$("#order_num").val(dd_count_sum);//隐藏域订单总数
	$("#money_sum").val(dd_money_sum.toFixed(2));//隐藏域订单金额
	$("#discount_price_sum").val(dd_discount_sum.toFixed(2));//隐藏域订单折扣金额
}
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>