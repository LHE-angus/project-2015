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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font:12px "宋体","\5b8b\4f53",sans-serif;color:#1e3257;overflow-x:hidden;}
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
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
  		<html-el:form action="/manager/JxcKonkaOrderRegister" enctype="multipart/form-data" method="post">
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="trade_index" styleId="trade_index" value="${af.map.trade_index}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
	      	<html-el:hidden property="order_id" styleId="order_id" value="${order_id}" />
	      	<html-el:hidden property="is_submit" styleId="is_submit" value="0" />
	      	<html-el:hidden property="forward_type" styleId="forward_type" value="0" />
	      	<html-el:hidden property="pro_ids" styleId="pro_ids"/>
			<html-el:hidden property="pro_names" styleId="pro_names"/>
			
			<div id="tabs">
				<ul>
				    <li><a href="#" name="tab1">订单信息</a></li>
				    <li><a href="#" name="tab2">审核信息</a></li>
				    <li><a href="#" name="tab3">附件</a></li>
				    <li><a href="#" name="tab4">意见反馈<c:if test="${not empty af.map.content1}"><font color="red">*</font></c:if></a></li>
				</ul>
			</div>
			<div id="content"> 
			    <div id="tab1">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				        <tr>
				        	<td class="title_item" colspan="4" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">订单信息</td>
				        </tr>
				        <tr>
							<td class="title_item" width="15%"><font color="red">*</font>退货流水号：</td>
							<td colspan="3"><span>NO.<font color="red">${af.map.trade_index}</font></span></td>
						</tr>
						<tr> 
							<td class="title_item" width="15%">客户名称：</td>
							<td width="35%">${af.map.user_shop_name}</td>
							<td class="title_item" width="15%">售达方编码：</td>
							<td width="15%">${af.map.r3_code}</td>
						</tr>
						<tr>
							<td class="title_item" width="15%"><font color="red">*</font>退货类型：</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${af.map.return_type eq '1'}">滞销退货</c:when>
									<c:when test="${af.map.return_type eq '2'}">残次品退货</c:when>
									<c:when test="${af.map.return_type eq '3'}">客户拒收</c:when>
									<c:when test="${af.map.return_type eq '4'}">异型换机</c:when>
									<c:when test="${af.map.return_type eq '5'}">其他原因</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="title_item" width="15%"><font color="red">*</font>原因说明：</td>
							<td colspan="3">${af.map.return_type_remark}</td>
						</tr>
						<!-- 常规信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">常规信息</td>
						</tr>
						<tr>
							<td class="title_item"><font color="red">*</font>订单流程：</td>
							<td>
				          		<c:forEach var="cur" items="${processList}">
				          			<!--<c:set var="add_dept_name" value="${cur.add_dept_name}" />
				          			<c:choose>
				          				<c:when test="${cur.process_type eq 1}"><c:set var="process" value="分公司普通流程" /></c:when>
				          				<c:when test="${cur.process_type eq 2}"><c:set var="process" value="分公司特殊流程" /></c:when>
				          			</c:choose>-->
				          			<c:if test="${af.map.process_id eq cur.id}">
				          			 <c:if test="${cur.add_dept_id eq 0}">★[统一流程] </c:if>
		          					<c:if test="${cur.add_dept_id ne 0}">[${cur.add_dept_name}]</c:if>
		          					${cur.process_desc}
		          					</c:if>
				          		</c:forEach>
							</td>
							<td class="title_item">订单日期：</td>
							<td><fmt:formatDate value="${af.map.order_date}" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<td class="title_item">订单状态：</td>
							<td>
								<c:choose>
									<c:when test="${af.map.is_submit eq 0}"><span style="color:#F00;">未提交</span></c:when>
									<c:when test="${af.map.is_submit eq 1}">
										<c:choose>
											<c:when test="${af.map.audit_state ne 3 and af.map.audit_state ne 4}"><span style="color:#F00;">审核中</span></c:when>
											<c:when test="${af.map.audit_state eq 3}"><span style="color:green;">审核通过</span></c:when>
											<c:when test="${af.map.audit_state eq 4}"><span style="color:orange;">已作废</span></c:when>
										</c:choose>
									</c:when>
								</c:choose>
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
							<td>${af.map.userName}</td>
							<td class="title_item">业务员：</td>
							<td>${ywy_user_name}</td>
						</tr>
						<tr>
							<td class="title_item">第三方采购订单编号：</td>
							<td>${af.map.third_cg_order_num}<c:if test="${empty af.map.third_cg_order_num}"><span style="color: gray;">未填写</span></c:if></td>
							<td class="title_item">采购订单日期：</td>
							<td><fmt:formatDate value="${af.map.cg_order_date}" pattern="yyyy-MM-dd" /><c:if test="${empty af.map.cg_order_date}"><span style="color: gray;">未填写</span></c:if></td>
						</tr>
						<tr>
							<td class="title_item">备注：</td>
							<td colspan="3">${af.map.remark}</td>
						</tr>
						<!-- 产品明细与产品周转天数tab-->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">
								<ul class="tabs">
								    <li><a href="#" name="tab5">产品明细</a></li>
								    <li><a href="#" name="tab6">产品/子仓周转</a></li>
								</ul>
							</td>
						</tr>
						<tr>	
          					<td colspan="4">
          							<div class="content">
	          							<div id="tab5">
			          						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
			              						<tr class="title_top">
			                						<td nowrap="nowrap" width="12%">产品型号</td>
			                						<td width="5%" nowrap="nowrap">数量</td>
									                <td width="5%" nowrap="nowrap">单位</td>
									                <td width="8%" nowrap="nowrap">单价</td>
									                <td width="8%" nowrap="nowrap">金额</td>
									                <td width="10%" nowrap="nowrap">折扣金额-RB00</td>
									                <td width="8%" nowrap="nowrap">折扣(%)-K007</td>
									                <td width="8%" nowrap="nowrap">折后金额</td>
									                <td width="12%" nowrap="nowrap">工厂/仓位</td>
									                <td nowrap="nowrap">产品备注</td>
									                <td nowrap="nowrap">流水号</td>
			              						</tr>
			              						<c:forEach items="${konkaOrderInfoDetailsList}" var="cur">
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
														<td align="right"><fmt:formatNumber value="${fn:escapeXml(cur.good_sum_price * cur.good_discount/100 + cur.good_discount_price + cur.good_sum_price)}" pattern="0.00" /></td>
														<td align="center">${fn:escapeXml(cur.store_key)}</td>
														<td align="center">${fn:escapeXml(cur.pd_remark)}</td>
														<td align="center">${fn:escapeXml(cur.pd_trade_index)}</td>
													</tr>
												</c:forEach>
												<tr>
												<td align="center">合计</td>
												<td align="center">${af.map.order_num}</td>
												<td align="center">台</td>
												<td align="right">&nbsp;</td>
												<td align="right"><fmt:formatNumber value="${good_sum_price_total}" pattern="0.00" /></td>
												<td align="right" colspan="2"><fmt:formatNumber value="${s_dis_price}" pattern="0.00" /></td>
												<td align="right"><fmt:formatNumber value="${good_sum_price_total + s_dis_price}" pattern="0.00" /></td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												</tr>
			            					</table>
		            					</div>	
		            					
		            					<div id="tab6">
		            						<div id="loding_txt"></div>
	          							 	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
												<tr class="title_top">
													<td>分公司</td>
													<td>客户</td>
													<td>送达方</td>
													<td>仓库编码</td>
													<td>仓库名称</td>
													<td>机型</td>
													<td>历史周转天数</td>
													<td>初始库存</td>
													<td>进货量</td>
													<td>销售量</td>
													<td>当前库存量</td>
													<td>上次盘存时间</td>
													<td>最近更新时间</td>
												</tr>
											</table>
	          							</div>
          							</div> 
            				</td>
            			</tr>
						
						<!-- 汇总信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">汇总信息</td>
						</tr>
						<tr>
							<td class="title_item">申请数量：</td>
							<td style="color:#009900;font-family:tahoma;"><span id="apply_number">正在加载..</span></td>
							<td class="title_item">申请金额：</td>
							<td style="color:#CD0000;font-family:tahoma;"><span id="apply_total_money">正在加载..</span></td>
						</tr>
						<tr>
							<td class="title_item">审核数量：</td>
							<td>${af.map.order_num}</td>
							<td class="title_item">审核金额：</td>
							<td style="color:#CD0000;font-family:tahoma;">￥${af.map.money}</td>
						</tr>											
						<!-- 同步信息 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">同步信息</td>
						</tr>
						<tr>
					          <td width="15%" class="title_item">退货原因：</td>
					          <td colspan="3">
					          	<c:choose>
									<c:when test="${af.map.return_reason eq '001'}">销售会谈</c:when>
									<c:when test="${af.map.return_reason eq '002'}">贸易展览会销售活动</c:when>
									<c:when test="${af.map.return_reason eq '003'}">电视商业</c:when>
									<c:when test="${af.map.return_reason eq '004'}">客户建议</c:when>
									<c:when test="${af.map.return_reason eq '005'}">报纸广告</c:when>
									<c:when test="${af.map.return_reason eq '006'}">极好的价格</c:when>
									<c:when test="${af.map.return_reason eq '007'}">快速交货</c:when>
									<c:when test="${af.map.return_reason eq '008'}">优良服务</c:when>
									<c:when test="${af.map.return_reason eq '100'}">价格差异：价格太高</c:when>
									<c:when test="${af.map.return_reason eq '101'}">质量低劣</c:when>
									<c:when test="${af.map.return_reason eq '102'}">转运中受损</c:when>
									<c:when test="${af.map.return_reason eq '103'}">数量不符</c:when>
									<c:when test="${af.map.return_reason eq '104'}">物料损坏</c:when>
									<c:when test="${af.map.return_reason eq '105'}">免费样本</c:when>
									<c:when test="${af.map.return_reason eq '200'}">价格差异：价格太低</c:when>
									<c:when test="${af.map.return_reason eq 'EDI'}">贷项凭单处理</c:when>
									<c:when test="${af.map.return_reason eq 'F01'}">客户退货</c:when>
									<c:when test="${af.map.return_reason eq 'F02'}">客户补差</c:when>
								</c:choose>
					          </td>
				        </tr>
						<tr>
							<td width="8%" align="left" class="title_item">销售凭证类型</td>
							<td width="12%">${af.map.doc_type}</td>
							<td width="8%" align="left" class="title_item">销售组织</td>
							<td width="12%">${af.map.sales_org}</td>
						</tr>
						<tr>
							<td width="8%" colspan="1" align="left" class="title_item">分销渠道</td>
							<td width="12%">10</td>
							<td width="8%" align="left" class="title_item">产品组</td>
							<td width="12%">10</td>
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
			    <div id="tab2">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
			    		<tr>
				        	<td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">审核信息</td>
				        </tr>
				        <tr>
				        	<td align="center">
				        		<div style="width:100%;padding:5px 5px 0 5px;">
						        	<jsp:include page="../_inc/_order_progress.jsp" />
						        </div>
				        	</td>
				        </tr>
		        		<c:if test="${not empty af.map.konkaOrderInfoAuditList}">
					        <tr>
					        	<td align="center">
						        		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;">
											<thead id="nav" style="width:99%;background:#abd589;">
							        			<tr>
							        				<th width="5%" align="center">序号</th>
							        				<th width="10%" align="center">日期</th>
							        				<th width="10%" align="center">审核人</th>
							        				<th width="10%" align="center">职务</th>
							        				<th width="10%" align="center">结果</th>
							        				<th align="center">意见</th>
							        			</tr>
						        			</thead>
						        			<c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1" varStatus="vs">
							        			<tr>
							        				<td align="center" nowrap="nowrap">${vs.count}</td>
							        				<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy/MM/dd HH:mm"/></td>
							        				<td align="center" nowrap="nowrap">${fn:escapeXml(cur1.audit_user)}</td>
							        				<td align="center" nowrap="nowrap">${fn:escapeXml(cur1.map.role_name)}</td>
							        				<td align="center" nowrap="nowrap">
							        					<c:choose>
							        						<c:when test="${cur1.audit_result eq 1}"><span style="color:green;">审核通过</span></c:when>
							        						<c:when test="${cur1.audit_result eq -1}"><span style="color:#F00;">驳回（至审核人）</span></c:when>
							        						<c:when test="${cur1.audit_result eq 0}"><span style="color:#00F;">驳回（至制单）</span></c:when>
							        						<c:when test="${cur1.audit_result eq -9}"><span style="color:#999;">（客户）撤销</span></c:when>
							        					</c:choose>
							        				</td>
							        				<td>
							        					<c:if test="${empty cur1.audit_comment }">无</c:if>
							                  			<c:if test="${not empty cur1.audit_comment }">${fn:escapeXml(cur1.audit_comment)}</c:if>
							                  		</td>
							                  	</tr>
	        								</c:forEach>
	        	    					</table>
					            </td>
					        </tr>
      						</c:if>
      						<tr>
      							<td><div style="width:100%; overflow-x:auto;">
      								<table width="320" border="0" cellpadding="5" cellspacing="0" class="navClass" style="margin:10px 0 10px 0; float:left;">
      									<tr>
      										<th colspan="5" align="center">申请信息<br />&nbsp;</th>
      									</tr>
      									<tr>
      										<th align="center" nowrap="nowrap">产品型号</th>
      										<th width="15%" align="center" nowrap="nowrap">数量</th>
      										<th width="15%" align="center" nowrap="nowrap">单价</th>
      										<th width="15%" align="center" nowrap="nowrap">备注</th>
      										<th width="15%" align="center" nowrap="nowrap">流水号</th>
      									</tr>
      									<c:set var="sum_count" value="0" />
      									<c:set var="sum_money" value="0" />
      									<c:forEach var="cur" items="${applyDetailsList}">
      										<c:set var="sum_count" value="${cur.good_count + sum_count}" />
      										<c:set var="sum_money" value="${cur.good_count * cur.good_price + sum_money}" />
	      									<tr>
	      										<td align="center">${cur.pd_name}</td>
	      										<td align="right" style="padding-right:3px;">${cur.good_count}</td>
	      										<td align="right" style="padding-right:3px;"><fmt:formatNumber value="${cur.good_price}" pattern="0.00" /></td>
	      										<td align="right" style="padding-right:3px;">${cur.pd_remark}</td>
	      										<td align="right" style="padding-right:3px;">${cur.pd_trade_index}</td>
	      									</tr>
      									</c:forEach>
      										<tr>
      											<th align="center">合计</th>
      											<td align="right" style="padding-right:3px;" title="合计 数量"><span id="apply_number_2">${sum_count}</span></td>
      											<td align="center" colspan="3"><span id="apply_total_money_2" style="display:none;">￥${sum_money}</span></td>
      										</tr>
      								</table>
      								<c:if test="${not empty updateRecordGroupList}">
      								<c:forEach var="cur" items="${updateRecordGroupList}" varStatus="vs">
      									<table width="200" border="0" cellpadding="5" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;float:left">
	      									<tr>
	      										<th colspan="4" align="center">第${vs.count}修改人<br/>姓名：${cur.map.recordList[0].update_user_name}</th>
	      									</tr>
	      									<tr>
	      										<th align="center" width="25%" nowrap="nowrap">数量</th>
	      										<th align="center" width="25%" nowrap="nowrap">单价</th>
	      									</tr>
	      									<c:set var="m_sum_count" value="0" />
      									    <c:set var="m_sum_money" value="0" />
	      									<c:forEach var="cur1" items="${cur.map.recordList}">
	      										<c:set var="m_sum_count" value="${cur1.num_after + m_sum_count}" />
      											<c:set var="m_sum_money" value="${cur1.num_after * cur1.price_after + m_sum_money}" />
		      									<tr>
		      										<td align="right" style="padding-right:3px;color:${cur1.num_before eq cur1.num_after ? 'black' : 'red'}" title="${cur1.num_before} 修改为 ${cur1.num_after}"><fmt:formatNumber value="${cur1.num_after}" pattern="0.00" /></td>
		      										<td align="right" style="padding-right:3px;color:${cur1.price_before eq cur1.price_after ? 'black' : 'red'}" title="${cur1.price_before} 修改为 ${cur1.price_after}"><fmt:formatNumber value="${cur1.price_after}" pattern="0.00" /></td>
		      									</tr>
	      									</c:forEach>
	      										<tr>
      												<td align="right" style="padding-right:3px;line-height:20px;" title="合计 数量">${m_sum_count}</td>
      												<td align="center">${m_sum_money}</td>
      											</tr>
	      								</table>
      								</c:forEach></c:if>
      								</div>
      							</td>
      						</tr>
					</table>
			    </div>
				<!-- 附件 -->			    
			    <div id="tab3">
			    	<c:if test="${not empty attachmentList}">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
			    		<tr>
				        	<td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">附件</td>
				        </tr>
				        <tr>
				        	<td align="center">
				        		<div style="width:100%;padding:5px 5px 0 5px;">
				        			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;">
										<thead id="nav" style="width:99%;background:#abd589;">
								  			<tr>
									  			<th width="3%" nowrap="nowrap">序号</th>
									  			<th width="40%" nowrap="nowrap">附件</th>
									  			<th nowrap="nowrap">备注</th>
									  		</tr>
								  		</thead>
								  		<tbody id="fileTbody">
								  			<c:forEach items="${attachmentList}" var="cur" varStatus="vs">
								  				<tr id="picModelTr_${vs.count}">
													<td align="center">${vs.count}</td>
													<td align="left" nowrap="nowrap">
														<a href="${ctx}/${cur.save_path}">${cur.file_name}</a>
														<html-el:hidden property="id_" value="${cur.id}" />
														<html-el:hidden property="link_id_" value="${cur.link_id}" />
														<html-el:hidden property="link_tab_" value="${cur.link_tab}" />
														<html-el:hidden property="file_name_" value="${cur.file_name}" />
														<html-el:hidden property="file_type_" value="${cur.file_type}" />
														<html-el:hidden property="file_size_" value="${cur.file_size}" />
														<html-el:hidden property="save_path_" value="${cur.save_path}" />
														<html-el:hidden property="save_name_" value="${cur.save_name}" />
														<html-el:hidden property="del_mark_" value="${cur.del_mark}" />
													</td>
													<td align="left">${cur.file_desc}</td>
											   </tr>
								  			</c:forEach>
								  		</tbody>
								  	</table>
				        		</div>
				        	</td>
				        </tr>
			    	</table>
				  	</c:if>
				  	<c:if test="${empty attachmentList}">
				  		<div style="color:#F00;text-align:center;">没有附件</div>
				  	</c:if>
			    </div>
			    
			     <!-- 意见反馈 -->
				<div id="tab4">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" style="table-layout:fixed;">
			    		<tr>
				        	<td width="120px" class="title_item_no_bc" style="text-align:right;font-size:15px;font-weight:bold;color:#868182">反馈客户：</td>
				        	<td nowrap="nowrap">&nbsp;${af.map.f_person }</td>
				        	<td width="120px" class="title_item_no_bc" style="text-align:right;font-size:15px;font-weight:bold;color:#868182">反馈时间：</td>
				        	<td nowrap="nowrap">&nbsp;<fmt:formatDate value="${af.map.f_add_date }" pattern="yyyy-MM-dd"/></td>
				        	<td width="120px" class="title_item_no_bc" style="text-align:right;font-size:15px;font-weight:bold;color:#868182">联系电话：</td>
				        	<td nowrap="nowrap">&nbsp;${af.map.f_tel }</td>
				        </tr>
				        <tr>
				        	<td width="120px" nowrap="nowrap" class="title_item_no_bc" style="text-align:right;font-size:15px;font-weight:bold;color:#868182">反馈内容：</td>
				        	<td colspan="5" style="color:#3E3E3E;font-size:15px; word-break: break-all; overflow:hidden;">   
				        		&nbsp;${af.map.f_content}
				        	</td>
				        </tr>
			    	</table>
			    	<div style="font-size: 16px;clear:both;padding: 5px 0;font-weight: 400;border-bottom: 1px solid #e8e8e8;"><span style="margin-left:8px;"><b>全部评论：</b></span></div>
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass" style="table-layout:fixed;">
			    		<tbody id="d2"></tbody>
			    	</table>
			    	<div style="height:10px"></div>
			    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableClass" style="table-layout:fixed;">
			    		<tr>
			    			<td width="130px" style="text-align:right;font-size:15px;font-weight:bold;color:#868182">我的评论：</td>
			    			<td><textarea name="content1" id="content1" rows="3" style="width:50%;resize:none;"></textarea><font color="red" id="u_msg"></font><br/></td>
			    		</tr>
			    		<tr>
			    			<td>&nbsp;</td>
			    			<td><input name="login791" id="sbt_login" type="button" class="bgButton" value=" 发 送 " /></td>
			    		</tr>
			    	</table>
			    	<div style="height:20px"></div>
			    </div>
			</div>
			
			<div style="position:absolute;top:0;right:0;">
				<span style="font-size:13px;margin-right:10px;">NO.<font color="red">${af.map.trade_index}</font></span>
				<html-el:button property="btn_back" styleId="btn_back" value=" 返 回 " onclick="history.back();" />
			</div>
		</html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=jtop"></script>
<script type="text/javascript">//<![CDATA[
var f = document.forms[0];
$(document).ready(function(){
	$("#process_id").attr("dataType", "Require").attr("msg", "请选择订单类型！");

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
    
    $(".tabs li:first").attr("id","current"); // Activate first tab
    $(".content div[id^=tab]:first").fadeIn(); // Show first tab content
    $(".tabs a").click(function(e) {
        e.preventDefault();
        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
         	return       
        } else{             
	        $(".content div[id^=tab]").hide(); //Hide all content
	        $(".tabs li").attr("id",""); //Reset id's
	        $(this).parent().attr("id","current"); // Activate this
	        $('#' + $(this).attr('name')).fadeIn(); // Show content for current tab
        }
    });
    
	//tabs切换End
	
	var clicked = false;
	var tradeIndex = '${af.map.trade_index}';
	//触发产品周转查询 
	$(".tabs>li>a:[name ='tab6']").click(function(e) {
        if(!clicked){
        	if(tradeIndex){
        		//1.根据订单流水号到后台找到客户,订单机型,时间范围 
            	//2.调用JStocksStoreSummaryAction#getCustomerInventoryDetail
    			//3.前端解释json
       			  $("#loding_txt").html("<font color='red'>正在努力加载...</font>");
       			  $.ajax({
       				  type: "get",
       				  url: "${ctx}/manager/admin/JStocksStoreSummary.do?method=getCustomerInventoryDetail",
       				  data: {"tradeIndex" : tradeIndex, "timestamp" : new Date().getTime()},
       				  dataType: "json",
       				  sync: true, 
       				  error: function (xhr, ajaxOptions, thrownError) { alert("数据加载请求失败【" + xhr.statusText + "," + xhr.responseText + "," + xhr.status + "," + thrownError +"】！"); },
       				  success: function(result) {
	       					var html ="";
	       					if(result.length>0){
	       						for(var i=0; i<result.length; i++) 
	           					{ 
									html +="<tr>";
	           						
	           						html += "<td>"+result[i].fgs+"</td>";
	           						html += "<td>"+result[i].r3code+"</td>";
	           						html += "<td>"+result[i].sdf+"</td>";
	           						html += "<td>"+result[i].inventoryCode+"</td>";
	           						html += "<td>"+result[i].inventoryName+"</td>";
	           						html += "<td>"+result[i].model_code+"</td>";
	           						html += "<td>"+result[i].zzts+"</td>";
	           						
	           						html += "<td>"+result[i].initnum+"</td>";
	           						html += "<td>"+result[i].innum+"</td>";
	           						html += "<td>"+result[i].salenum+"</td>";
	           						html += "<td>"+result[i].xynum+"</td>";
	           						html += "<td>"+result[i].pc_date+"</td>";
	           						html += "<td>"+result[i].sync_date+"</td>";
	           						html +="</tr>";
	           					} 
	       						$("#loding_txt").hide();
	       						$("#tab6 table tr:last").after(html);
	       					}else{
	       						$("#loding_txt").html("<font color='red'>暂时没法查询到库存信息,可能客户库存没有初始化.</font>");
	       					}
       				  }
       			  }); 
       			clicked = true ;
    		}
        }
    });
    
	
	
	$("#apply_number").text($("#apply_number_2").text());
	$("#apply_total_money").text($("#apply_total_money_2").text());
	
	ajax();
	
	$("#sbt_login").click(function(){
		var content=$('#content1').val();
		var feed_id ='${feed_id}'; 
		if(content.length>100){$('#u_msg').html('最多只能输入100个字');return false;	}
		if(content==''||$.trim(content).length==0){$('#u_msg').html('请输入评论信息');return false;	}
		$.ajax({
			type: "POST",url: "<c:url value='/manager/admin/KonkaOrderSearch.do' />",
			data: {"method":"ajaxSave", "content":content,"feed_id":feed_id,  "timestamp":new Date().getTime() },
			dataType: "text",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {},
			success: function(result){
				if(result=='1'){  
					$('#content1').val('');
					ajax();
				}else{ 
					$('#u_msg').html('数据异常');
				}
			}
		});
	});
});

/*处理返回值数据 ===start===*/
function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}

var flag = 0;
var f_id = 0;
function ajax(){ 
	var feed_id = '${feed_id}';   
	if(flag==0){  
		flag=1;
		$.ajax({
			type: "POST",url: "<c:url value='/manager/admin/KonkaOrderSearch.do' />",
			data: {"method":"ajaxmessage", "feed_id":feed_id,"f_id":f_id,"timestamp":new Date().getTime() },
			dataType: "json",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {flag=1;},
			success: function(data){
				flag=0;
				var ss="";
				if(data.list.length>0){
					for(i=0;i<data.list.length;i++){
						if(f_id<data.list[i].id){
							f_id=data.list[i].id;
						}
						ss = ss + "<tr>"; 
						ss = ss + "<td style='width:130px' align='right'><div class='np-post-name' style='margin-right:3px;font-size: 16px;font-family:SimHei;'>"+ data.list[i].question_person +"</div><div class='np-post-date' style='color:gray;'>"+ data.list[i].add_date +"</div></td>";
						ss = ss + "<td><div class='np-post-content' style=' word-break: break-all;'>"+ data.list[i].content +"</div></td>";
						ss = ss + "</tr>"; 
					}
					$("#d2").append(ss);  
				}  
			}
		});
	} 
	window.parent.resizeFrameHeight('mainFrame', 3);
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>