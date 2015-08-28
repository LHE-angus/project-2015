<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="http://qdgl.konka.com/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="http://qdgl.konka.com/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font:12px "宋体","\5b8b\4f53",sans-serif;/*background-color:#d9d6d6;*/color:#1e3257;overflow-x:hidden;}
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}

ul.order_process_view {margin:0px;padding:0px;}
ul.order_process_view li { float:left; height:120px; text-align:center; font-size:12px; padding:5px; list-style:none; margin-left:5px; min-width: 90px;  position:relative;}
ul.order_process_view li .mark { font-size:60px;}
ul.order_process_view li .arrow { font-size:40px; position:absolute; top: 15px; right:-25px; color:#eee;padding:0px;margin:0px; z-index:9999;}

ul.order_process_view li.done .mark { color:#eee;  }
ul.order_process_view li.doing .mark { color:#aaa;  }
ul.order_process_view li.todo .mark { color:#666;  }

ul.order_process_view li.done .arrow { color:#eee;  }
ul.order_process_view li.doing .arrow { color:#aaa;  }
ul.order_process_view li.todo .arrow { color:#666;  }
</style>
<script type="text/javascript" src="http://qdgl.konka.com/commons/scripts/jquery.js"></script> 
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:1200px">
  	<div class="rtabcont1" style="position:relative;">
			<div id="content">
					<div align="left"><input class="bgButtonBack" type="reset" name="reset" value="返回" id="btn_back" onclick="history.back();"/></div> 
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				        <tr>
				        	<td class="title_item" colspan="4" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">订单信息</td>
				        </tr>
				        <tr>
							<td class="title_item" width="15%"><font color="red">*</font>订单流水号：</td>
							<td colspan="3"><span>NO.<font color="red"></font><font color="red">${af.map.trade_index}</font></span></td>
						</tr>
						<tr>
							<td class="title_item" width="15%">客户名称：</td>
							<td width="35%">${af.map.custmomer_name}</td>
							<td class="title_item" width="15%">售达方编码：</td>
							<td width="15%">${af.map.r3_code}</td>
						</tr>
						<!-- 常规信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">常规信息</td>
						</tr>
						<tr>
							<td class="title_item"><font color="red">*</font>订单流程：</td>
							<td>
				          		${af.map.process_name}
							</td>
							<td class="title_item">提交日期：</td>
							<td><fmt:formatDate value="${af.map.order_date}" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<td class="title_item">订单状态：</td>
							<td>
								${af.map.order_state }
							</td>
							<td class="title_item">出货状态：</td>
							<td>
								${af.map.is_delivered}
							</td>
						</tr>
						<tr>
							<td class="title_item">制单人：</td>
							<td>${af.map.make_order_user_name}</td>
							<td class="title_item">业务员：</td>
							<td>${af.map.ywy_user_name}</td>
						</tr>
						<tr>
							<td class="title_item">第三方采购订单编号：</td>
							<td>${af.map.third_cg_order_num}<c:if test="${empty af.map.third_cg_order_num}"><span style="color: gray;">未填写</span></c:if></td>
							<td class="title_item">采购订单日期：</td>
							<td><fmt:formatDate value="${af.map.cg_order_date}" pattern="yyyy-MM-dd" /><c:if test="${empty af.map.cg_order_date}"><span style="color: gray;">未填写</span></c:if></td>
						</tr>
						<tr>
						<td class="title_item">创建日期：</td>
							<td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" /></td>
						
							<td class="title_item">备注：</td>
							<td colspan="1">${af.map.remark}</td>
						</tr>
						<!-- 产品明细 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">产品明细</td>
						</tr>
						<tr>
          					<td colspan="4">
          						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              						<tr class="title_top">
                						<td nowrap="nowrap" width="12%">产品型号<c:if test="${call_r3_interface}">&nbsp;<span style="font-weight:normal;">(<span id="btn_check" style="text-decoration:underline;cursor:pointer;color:#F00;">库存检查</span>)</span></c:if></td>
                						<td width="5%" nowrap="nowrap">数量</td>
						                <td width="5%" nowrap="nowrap">单位</td>
						                <td width="8%" nowrap="nowrap">单价</td>
						                <td width="8%" nowrap="nowrap">金额</td>
						                <td width="10%" nowrap="nowrap">折扣金额-RB00</td>
						                <td width="8%" nowrap="nowrap">折扣(%)-K007</td>
						                <td width="8%" nowrap="nowrap">折后金额</td>
						                <td width="12%" nowrap="nowrap">工厂/仓位</td>
						                <td nowrap="nowrap">产品备注</td>
              						</tr>
              						<c:forEach items="${af.map.konkaOrderInfoDetailsSearchList}" var="cur">
                						<tr>
		                  					<td align="center">${fn:escapeXml(cur.pd_name)}</td>
											<td align="center">${fn:escapeXml(cur.good_count)}</td>
											<td align="center">${fn:escapeXml(cur.good_unit)}</td>
											<td align="right"><fmt:formatNumber value="${fn:escapeXml(cur.good_price)}" pattern="0.00" /></td>
											<td align="right"><fmt:formatNumber value="${fn:escapeXml(cur.good_sum_price)}" pattern="0.00" /></td>
											<!-- 折扣金额（元） -->
											<td align="right"><fmt:formatNumber value="${fn:escapeXml(cur.good_discount_price)}" pattern="0.00" /></td>
											<!-- 折扣(%) -->
											<td align="right"><fmt:formatNumber value="${fn:escapeXml(cur.good_discount)}" pattern="0.00" /></td>
											<td align="right"><fmt:formatNumber value="${fn:escapeXml(cur.good_discount_sum_price)}" pattern="0.00" /></td>
											<td align="center">${fn:escapeXml(cur.store_key)}</td>
											<td align="center">${fn:escapeXml(cur.pd_remark)}</td>
										</tr>
									</c:forEach>
<!--									<tr><td align="center">合计</td>-->
<!--									<td align="center">${af.map.order_num}</td>-->
<!--									<td align="center">台</td>-->
<!--									<td align="right">&nbsp;</td>-->
<!--									<td align="right"><fmt:formatNumber value="${good_sum_price_total}" pattern="0.00" /></td>-->
<!--									<td align="right" colspan="2"><fmt:formatNumber value="${s_dis_price}" pattern="0.00" /></td>-->
<!--									<td align="right"><fmt:formatNumber value="${good_sum_price_total + s_dis_price}" pattern="0.00" /></td>-->
<!--									 -->
<!--									<td align="right"><fmt:formatNumber value="${good_discount_pricr_total_all}" pattern="0.00" /></td>-->
<!--									 -->
<!--									<td>&nbsp;</td>-->
<!--									<td>&nbsp;</td>-->
<!--									</tr>-->
            					</table>
            				</td>
						</tr>	
						<!-- 汇总信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">汇总信息</td>
						</tr>
						<tr>
							<td class="title_item">申请数量：</td>
							<td style="color:#009900;font-family:tahoma;">${af.map.apply_number }</td>
							<td class="title_item">申请金额：</td>
							<td style="color:#CD0000;font-family:tahoma;">￥${af.map.apply_total_money }</td>
						</tr>
						<tr>
							<td class="title_item">审核数量：</td>
							<td>${af.map.order_num}</td>
							<td class="title_item">审核金额：</td>
							<td style="color:#CD0000;font-family:tahoma;">￥${af.map.money}</td>
						</tr>											
						<!-- 付款信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">付款信息</td>
						</tr>
						<tr>
							<td class="title_item"><font color="red">*</font>货款支付方式：</td>
							<td>${af.map.pay_type_name}</td>
							<td></td>
							<td></td>
		          			<td></td>
		        		</tr>
						<!-- 收货信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">收货信息</td>
						</tr>
						<tr>
							<td class="title_item" width="15%"><font color="red">*</font>配送方式：</td>
							<td>
								${af.map.send_type_name}
							</td>
							<td class="title_item" width="15%">收货人姓名：</td>
		          			<td width="35%">${af.map.user_name}</td>
						</tr>
						<tr>
							<td class="title_item" width="15%">收货人固定电话：</td>
							<td width="35%">${af.map.user_tel}</td>
							<td class="title_item" width="15%">收货人手机：</td>
							<td>${af.map.user_phone}</td>
						</tr>
						<tr>
							<td class="title_item" width="15%">收货人所属地区：</td>
							<td colspan="3" width="85%">${af.map.fullName}</td>
						</tr>
						<tr>
							<td class="title_item">收货人地址：</td>
							<td colspan="3">${af.map.user_addr}</td>
						</tr>
						<tr>
							<td class="title_item">收货人备注：</td>
							<td colspan="3">${af.map.user_remark}</td>
						</tr>
						<!-- 同步信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">同步信息</td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item">销售凭证类型</td>
							<td width="12%">${af.map.doc_type}</td>
							<td width="8%" align="left" class="title_item">销售组织</td>
							<td width="12%">${af.map.sales_org}</td>
						</tr>
						<tr>
							<td width="8%" colspan="1" align="left" class="title_item">分销渠道</td>
							<td width="12%">${af.map.fxqd }</td>
							<td width="8%" align="left" class="title_item">产品组</td>
							<td width="12%">${af.map.product_group }</td>
						</tr>
						<tr>
							<td width="8%" align="left" class="title_item">售达方</td>
							<td width="12%">${af.map.ag}</td>
							<td width="8%" colspan="1" align="left" class="title_item">出具发票方</td>
							<td width="12%">${af.map.re}</td>
						</tr>
						<tr>
							<td width="8%" colspan="1" align="left" class="title_item">付款方</td>
							<td width="12%">${af.map.rg}</td>
							<td width="8%" colspan="1" align="left" class="title_item">送达方</td>
							<td width="12%">${af.map.we}</td>
						</tr>
				    </table>
			    </div>
	</div>
</div>
</body>
</html>