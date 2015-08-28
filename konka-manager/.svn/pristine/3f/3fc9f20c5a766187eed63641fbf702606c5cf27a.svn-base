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
select{font-family:Microsoft YAHEI;font-size:12px;} 
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
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
  		<html-el:form action="/manager/JxcKonkaOrderRegister" enctype="multipart/form-data" method="post">
	      	<html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      	<html-el:hidden property="trade_index" styleId="trade_index" value="${af.map.trade_index}" />
	      	<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id}" />
	      	<html-el:hidden property="order_id" styleId="order_id" value="${order_id}" />
	      	<html-el:hidden property="is_submit" styleId="is_submit" value="0" />
	      	<html-el:hidden property="forward_type" styleId="forward_type" value="0" />
	      	<html-el:hidden property="pro_ids" styleId="pro_ids"/>
	      	<html-el:hidden property="pro_names" styleId="pro_names"/>
			<ul id="tabs">
			    <li><a href="#" name="tab1">退货信息</a></li>
			    <li><a href="#" name="tab2">审核信息</a></li>
			    <li><a href="#" name="tab3">附件</a></li>
			    <li><a href="#" name="tab4">意见反馈</a></li>
			</ul>
			<div id="content"> 
			    <div id="tab1">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				        <tr>
				        
				        	<td class="title_item" colspan="4" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">订单信息</td>
				        </tr>
				        <tr>
							<td class="title_item" width="15%"><font color="red">*</font>订单流水号：</td>
							<td colspan="3"><span>NO.<font color="red"></font><font color="red">${af.map.trade_index}</font></span></td>
						</tr>
				        <tr>
							<td class="title_item" width="15%"><font color="red">*</font>退货类型：</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${af.map.return_type eq '1'}">滞销退货</c:when>
									<c:when test="${af.map.return_type eq '2'}">残次品退货</c:when>
									<c:when test="${af.map.return_type eq '3'}">当月拒收</c:when>
									<c:when test="${af.map.return_type eq '6'}">跨月拒收</c:when>
									<c:when test="${af.map.return_type eq '4'}">异型换机</c:when>
									<c:when test="${af.map.return_type eq '5'}">其他原因</c:when>
								</c:choose>
							</td>
						</tr>
				        <tr>
							<td class="title_item" width="15%"><font color="red">*</font>原因说明：</td>
							<td colspan="3">${af.map.return_type_remark}</td>
						</tr>
						<tr>
							<td class="title_item" width="15%">客户名称：</td>
							<td width="35%">${af.map.user_shop_name}</td>
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
								<c:if test="${not empty af.map.process_id}">
					          		<c:forEach var="cur" items="${processList}">
					          			<c:if test="${af.map.process_id eq cur.id}">
						          			    <c:if test="${cur.add_dept_id eq 0}">★[统一流程] </c:if>
							          			<c:if test="${cur.add_dept_id ne 0}">[${cur.add_dept_name}]</c:if>
							          			${cur.process_desc}
						          		</c:if>
						          	</c:forEach>
					          	</c:if>
				          		<c:if test="${empty af.map.process_id}">未确定订单流程</c:if>
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
								<html-el:select property="is_delivered" styleId="is_delivered" disabled="true">
									<html-el:option value="0">未出货</html-el:option>
									<html-el:option value="1">已出货</html-el:option>
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
							<td>${af.map.third_cg_order_num}</td>
							<td class="title_item">采购订单日期：</td>
							<td><fmt:formatDate value="${af.map.cg_order_date}" pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<td class="title_item">备注：</td>
							<td colspan="3">${af.map.remark}<c:if test="${empty af.map.remark}"><span style="color:#ccc;">未填写备注信息</span></c:if></td>
						</tr>
						<!-- 产品明细 -->
						<tr>
							<td colspan="4" align="left" style="font-weight:bold;color:#74685F;">产品明细</td>
						</tr>
						<tr>
          					<td colspan="4">
          						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
              						<tr class="title_top">
                						<td width="14%" nowrap="nowrap">产品型号</td>
                						<td width="6%" nowrap="nowrap">数量</td>
						                <td width="6%" nowrap="nowrap">单位</td>
						                <td width="10%" nowrap="nowrap">单价</td>
						                <td width="10%" nowrap="nowrap">金额</td>
						                <td nowrap="nowrap">产品备注</td>
						                <td nowrap="nowrap">订单流水号</td>
              						</tr>
              						<c:set var="sum_count" value="0" />
              						<c:set var="sum_price" value="0" />
      								<c:set var="sum_discount_count" value="0" />
              						<c:forEach items="${konkaOrderInfoDetailsList}" var="cur">
              							<c:set var="sum_count" value="${cur.good_count + sum_count}" />
              							<c:set var="sum_price" value="${cur.good_sum_price + sum_price}" />
      									<c:set var="sum_discount_count" value="${cur.good_discount_price + sum_discount_count}" />
      									<c:set var="cur_discount" value="${cur.good_discount_price + cur.good_discount*(cur.good_count*cur.good_price)/100}" />
                						<c:set var="sum_cur_discount" value="${cur_discount + sum_cur_discount}" />
                						<tr>
		                  					<td align="center">${fn:escapeXml(cur.pd_name)}</td>
											<td align="right">${fn:escapeXml(cur.good_count)}</td>
											<td align="center">${fn:escapeXml(cur.good_unit)}</td>
											<td align="right"><fmt:formatNumber value="${fn:escapeXml(cur.good_price)}" pattern="0.00" /></td>
											<td align="right"><fmt:formatNumber value="${fn:escapeXml(cur.good_sum_price)}" pattern="0.00" /></td>
											<td align="left">${fn:escapeXml(cur.pd_remark)}</td>
											<td align="left">${fn:escapeXml(cur.pd_trade_index)}</td>
										</tr>
									</c:forEach>
									<tr>
										<td align="center">合计</td>
										<td align="right" style="padding-right:3px;"><fmt:formatNumber value="${sum_count}" pattern="0" /></td>
										<td align="center" style="padding-right:3px;"></td>
										<td align="right" style="padding-right:3px;"></td>
										<td align="right" style="padding-right:3px;"><fmt:formatNumber value="${sum_price}" pattern="0.00" /></td>
										<td align="left" style="padding-right:3px;"></td>
										<td align="left" style="padding-right:3px;"></td>
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
							<td width="12%">${af.map.doc_type}</td>
							<td width="8%" align="left" class="title_item">销售组织</td>
							<td width="12%">${sales_org}</td>
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
						        	<jsp:include page="../../manager/admin/_inc/_order_progress.jsp" />
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
						        				<th width="8%" align="center">审核人</th>
						        				<th width="8%" align="center">职位</th>
						        				<th width="8%" align="center">结果</th>
						        				<th width="8%" align="center">日期</th>
						        				<th align="center">审核意见</th>
						        			</tr>
					        			</thead>
					        			<c:forEach items="${af.map.konkaOrderInfoAuditList}" var="cur1" varStatus="vs">
						        			<tr>
						        				<td align="center">${vs.count}</td>
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
						        				<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur1.audit_datetime}" pattern="yyyy/MM/dd HH:mm"/></td>
						        				<td>
						        					<c:if test="${empty cur1.audit_comment}">无</c:if>
						                  			<c:if test="${not empty cur1.audit_comment }">${fn:escapeXml(cur1.audit_comment)}</c:if>
						                  		</td>
						                  	</tr>
        								</c:forEach>
        	    					</table>
					            </td>
					        </tr>
      						</c:if>
					</table>
			    </div>
			    <!-- 附件 -->			    
			    <div id="tab3">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
			    		<tr>
				        	<td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">附件</td>
				        </tr>
				        <tr>
				        	<td align="center">
				        		<div style="padding:5px 5px 0 5px;">
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
			    	
<!-- 			    	<div style="height:400px;font-size: 25px;" align="center">   -->
<%-- 			    		<c:if test="${not empty af.map.show}"> --%>
<!-- 			    		<div style="margin-top:50px;">  -->
<%-- 			    		<span style="margin-left: 15px;"> <a id="various3" style="color: #08C;" href="${ctx}/customer/manager/JxcKonkaOrderRegister.do?method=openiframe&t_id=${af.map.t_id}">点击查看反馈信息和回复</a></span> --%>
<!-- 			    		</div>   -->
<%-- 			    		</c:if> --%>
<!-- 			    	</div>  -->
			    </div>
			    
			</div> 
			
			<div style="position:absolute;top:0;right:0;">
				<!--<html-el:button property="btn_submit" styleId="btn_submit" value=" 提 交 " />
				<html-el:button property="btn_retract" styleId="btn_retract" value=" 撤 回 " />
				<html-el:button property="btn_temp_save" styleId="btn_temp_save" value=" 保 存 " />
				<html-el:button property="btn_temp_save_add" styleId="btn_temp_save_add" value=" 保存并新建 " />-->
				<span style="font-size:13px;margin-right:10px;">NO.<font color="red">${af.map.trade_index}</font></span>
				<html-el:button property="btn_back" styleId="btn_back" value=" 返 回 " onclick="history.back();" />
			</div>
		</html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.jxc.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
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
	$("#pay_type_6").attr("dataType", "Group").attr("msg", "至少选择一种货款支付方式！");
	$("#send_type_2").attr("dataType", "Group").attr("msg", "至少选择一种配送方式！");
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
	//tabs切换End
	
	$("#various3").fancybox({
	    'overlayOpacity':'0.6', 
		'width':'40%',   
		'height':'90%',  
		'autoScale':false,
		'transitionIn':'none',
		'transitionOut':'none',
		'type':'iframe'
	});

	$("td").each(function(){
		if ($(this).html().trim().length == 0) {
			$(this).append("<span style='color:#999;'>未填写</span");
		}
	});
	
	ajax();
	
	$("#sbt_login").click(function(){
		var content=$('#content1').val();
		var feed_id ='${feed_id}'; 
		if(content.length>100){$('#u_msg').html('最多只能输入100个字');return false;	}
		if(content==''||$.trim(content).length==0){$('#u_msg').html('请输入评论信息');return false;	}
		$.ajax({
			type: "POST",url: "<c:url value='/customer/manager/JxcKonkaOrderRegister.do' />",
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

var flag = 0;
var f_id = 0;
function ajax(){ 
	var feed_id = '${feed_id}';   
	if(flag==0){  
		flag=1;
		$.ajax({
			type: "POST",url: "<c:url value='/customer/manager/JxcKonkaOrderRegister.do' />",
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
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>