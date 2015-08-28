<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css"	rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet"	type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css"	rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css"	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oarcont">
  		<div class="oartop">
		    <table width="400" border="0" cellpadding="0" cellspacing="0">
		      <tr>
		        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
		        <td>当前位置：${naviString}</td>
		      </tr>
		    </table>
  		</div>
  		<div class="rtabcont2">
	    	<html-el:form action="/admin/CreateCustomer" enctype="multipart/form-data">
				<html-el:hidden property="mod_id" styleId="mod_id" value='${mod_id }'/>
				<html-el:hidden property="method" styleId="method" value="save" />
				<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id }" />
				<html-el:hidden property="source_flag" styleId="source_flag" value="${source_flag }" />
				<html-el:hidden property="queryString" styleId="queryString" />
				<div style="float:right;">
					<label> 
						<html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" style="display: ${oper};padding-left: 30px"/>&nbsp;&nbsp;
				  		<c:if test="${not empty syn }">
					  		<html-el:button property="" value="同步" styleClass="but4" styleId="btn_syn" style="padding-left: 30px"/>&nbsp;&nbsp;
				  		</c:if>
					 	<input class="but5" type="button" name="Submit5" value="返回" id='btn_back' onclick="history.back();" style="padding-left: 30px"/>
					</label>
				</div>
				<ul id="tabs">
					<li><a href="#" name="tab1">基本信息</a></li>
					<li><a href="#" name="tab2">建户材料</a></li>
				</ul>
				<div id="content">
					<div id="tab1">
					<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb">创建日期：</strong>
							</td>
							<td>
								<fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" />
							</td>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb">创建人：</strong>
							</td>
							<td>
								${create_man }
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb"><font color="red">* </font>客户名称：</strong></td>
							<td colspan="3"><c:out value="${af.map.cust_name}"/></td>
						</tr>
						<c:if test="${not empty af.map.link_r3_code}">
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb">R3客户编码：</strong></td>
							<td nowrap="nowrap" class="title_item" colspan="3">${af.map.link_r3_code }</td>
						</tr>
						</c:if>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb"><font color="red">* </font>所属经办：</strong></td>
							<td>${jb_name}</td>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb"><font color="red">* </font>客户类别：</strong></td>
							<td>${customer_type_name}</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb"><font color="red">* </font>所在城市：</strong></td>
							<td colspan="3">${province}&nbsp;${city}&nbsp;${country}&nbsp;${town}</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb"><font color="red">* </font>详细地址：</strong></td>
							<td colspan="3"><c:out value="${af.map.entp_addr}"/></td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb"><font color="red">* </font>法定代表人：</strong></td>
							<td><c:out value="${af.map.host_name}"/></td>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb"><font color="red">* </font>客户负责人：</strong></td>
							<td><c:out value="${af.map.link_man_name}"/></td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb"><font color="red">* </font>经营项目：</strong></td>
							<td><c:out value="${af.map.entp_main_pds}"/></td>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb"><font color="red">* </font>联系电话：</strong></td>
							<td><c:out value="${af.map.link_man_tel}"/></td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb">门店面积（m²）：</strong></td>
							<td><c:out value="${af.map.total_area}"/></td>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb">年销售额（万元）：</strong></td>
							<td>${entp_scale_name}</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb">当地卖场数量：</strong></td>
							<td colspan="3"><c:out value="${af.map.total_malls}"/></td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right"><strong class="fb">前期是否销售过康佳产品：</strong></td>
							<td colspan="3"><c:if test="${af.map.is_saled eq 1 }">是</c:if><c:if test="${af.map.is_saled eq 0 }">否</c:if></td>
						</tr>
						<c:if test="${af.map.is_saled eq 1 }">
							<tr>
								<td nowrap="nowrap" class="title_item" align="right">&nbsp;</td>
								<td colspan="3"><strong class="fb">退出时间：</strong><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" /></td>
							</tr>
							<tr class="saled">
								<td nowrap="nowrap" class="title_item" align="right">&nbsp;</td>
								<td colspan="3"><strong class="fb">退出原因：</strong>${af.map.out_reason }</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="title_item" align="right">&nbsp;</td>
								<td colspan="3"><strong class="fb">是否有遗留问题：</strong>${af.map.leave_question }</td>
							</tr>
						</c:if>
						<tr>
							<td nowrap="nowrap" class="title_item" align="right">备注说明：</td>
							<td colspan="3"><textarea rows="5" style="width:400px;" readonly="readonly">${af.map.memo }</textarea></td>
						</tr>
						<tr id='r3_tr' style="display: none">
			    			<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>R3编码：</td>
			    			<td colspan="3"><input type="text" id='r3_code' name='r3_code'/></td>
			    		</tr>
					</table>
						<br/>
						<table id="shenpi" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" align="center">
					    	<tr>
					          <td width="10%" align="center" nowrap="nowrap" class="td_bord">审批状况</td>
					          <td width="20%" align="center" nowrap="nowrap" class="td_bord">审批时间</td>
					          <td width="40%" align="center" nowrap="nowrap" class="td_bord">审批意见</td>
					          <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批人角色</td>
					          <td width="15%" align="center" nowrap="nowrap" class="td_bord">审批人</td>
					        </tr>
					        <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
					        	<c:set var="i" value="${vs.count}" />
					          	<c:if test="${cur.AUDIT_RESULT eq 0}">
					            	<c:set var="audit_result" value="同意" />
					          	</c:if>
					          	<c:if test="${cur.AUDIT_RESULT eq 1}">
					            	<c:set var="audit_result" value="<span style='color:#f00;'>驳回至${cur.AUDIT_NODE_NAME }</span>" />
					          	</c:if>
					        	<tr>
						            <td align="center" valign="top">${audit_result}</td>
						            <td align="center" valign="top"><fmt:formatDate value="${cur.AUDIT_DATETIME}" pattern="yyyy-MM-dd HH:mm"/></td>
						            <td align="center" valign="top">${fn:escapeXml(cur.AUDIT_COMMENT)}</td>
						            <td align="center" valign="top">${cur.AUDIT_USER }</td>
						            <td align="center" valign="top">${cur.AGENT_AUDIT_USER}</td>
					          	</tr>
					        </c:forEach>
					    </table>
					    <br/>
					    
						<html-el:hidden property="id" styleId="id" />
						<html-el:hidden property="mod_id" styleId="mod_id" value='${mod_id }'/>
						<html-el:hidden property="method" styleId="method" value="addAudit" />
						<html-el:hidden property="cust_id" styleId="cust_id" value="${cust_id }" />
						<html-el:hidden property="role_id" styleId="role_id" value="${role_id }" />
						<html-el:hidden property="syn" styleId="syn" value="${syn }" />
					    <table id='audit_show' width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1" style="display: ${oper}">
						<tr class="oartop" style="margin-bottom: 10px; height: 12px; line-height: 12px;">
							<td colspan="2"><strong class="fb">提交审批：</strong></td>
						</tr>
				        <tr>
						   	<td width='20%' align="right">
						   		<strong class="fb">审批结果：</strong>
						   	</td>
						   	<td>
						   		<select id="audit_res" name="audit_res" style="width:150px">
									<option value="">-请选择-</option>
									<option value="0">同意</option>
									<option value="1">驳回</option>
						   		</select>		   		
						   	</td>
						</tr>
						<tr id='back_tr' style="display: none">
							<td align="right">
						   		<strong class="fb">驳回至：</strong>
						   	</td>
						   	<td>
						   		<select id="to_back" name="to_back" style="width:150px;">
						   		</select>			   		
						   	</td>
						</tr>
				        <tr>
						   	<td align="right">
						   		<strong class="fb">审批意见：</strong>
						   	</td>
						   	<td>
						   		<textarea rows="5" cols="70" id="audit_comment" name='audit_comment'></textarea>		   		
						   	</td>
						</tr>
				    </table>
				  	<%-- <div id="tb" style="height: auto;margin-bottom: 20px;margin-top: 5px" align="center">
				  		<html-el:button property="" value="提交" styleClass="but4" styleId="btn_submit" style="display: ${oper}"/>
				  		<c:if test="${not empty syn }">
					  		<html-el:button property="" value="同步" styleClass="but4" styleId="btn_syn"/>
				  		</c:if>
					    <html-el:button property="" value="返 回" styleClass="but5" styleId="btn_back" onclick="history.back()"/>
					</div> --%>
					</div>
					<div id="tab2">
						<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
						<c:forEach var="cur" items="${attachmentList}" varStatus="vs" end="12">
							<c:if test="${cur.file_desc eq 'ayyzzfyj' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、营业执照复印件</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'bgsswdjzfyj' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、国税税务登记证复印件</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'cdsswdjzfyj' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、地税税务登记证复印件</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'dswh' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、商务函</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'efddbrzms' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、法定代表人证明书</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'ffptkzl' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、发票填开资料</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'gshqzzm' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、收货签章证明</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'hfpqzzm' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、发票签章证明</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'iyzylh' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、印章预留函</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'jyhkhzl' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、银行开户资料</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'kzdjybzs' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、正当交易保证书</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'lsqfcsms' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、售前返厂声明书</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'mkhkhsqb' }">
								<tr><td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">${vs.count}、客户开户申请表</strong></td>
							</c:if>
								
							<td ><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a></td>
							</tr>
				         </c:forEach>
						 <c:forEach var="cur" items="${attachmentList}" varStatus="vs" begin="13">
							<c:if test="${cur.file_desc eq 'nfrdbwtsqzms' }">
								<tr><td width="30%" style="padding-left:10px"><strong class="fb">${vs.count+13}、法人代表委托授权证明书</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'owtzksqs' }">
								<tr><td width="30%" style="padding-left:10px"><strong class="fb">${vs.count+13}、委托转款授权书</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'pwtthzm' }">
								<tr><td width="30%" style="padding-left:10px"><strong class="fb">${vs.count+13}、委托提货证明</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'qlsthzm' }">
								<tr><td width="30%" style="padding-left:10px"><strong class="fb">${vs.count+13}、临时提货证明</strong></td>
							</c:if>
							<c:if test="${cur.file_desc eq 'rwlpsxys' }">
								<tr><td width="30%" style="padding-left:10px"><strong class="fb">${vs.count+13}、物流配送协议书</strong></td>
							</c:if>
							
							<td ><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a></td>
							</tr>
						 </c:forEach>
						</table>
					</div>
				</div>
				</html-el:form>
		  	</div>
		<jsp:include page="/__analytics.jsp" />
	</div>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
		<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
		<script type="text/javascript">
			
		//初始化
		$(document).ready(function(){
			var shen = '${oper}';
			if(shen==''){
				$("#audit_res").attr("dataType", "Require").attr("msg", "请选择");
				var role_id = $('#role_id').val();
				if(role_id=='35'){ //财务部
					$("#to_back").append("<option value='1'>业务员</option>");
				}
				if(role_id=='39'){ //财务经理
					$("#to_back").append("<option value='1'>业务员</option>");
					$("#to_back").append("<option value='2'>财务部</option>");
				}
				if(role_id=='34'){ //总经理
					$("#to_back").append("<option value='1'>业务员</option>");
					$("#to_back").append("<option value='2'>财务部</option>");
					$("#to_back").append("<option value='3'>财务经理</option>");
				}
			}
			
			var syn = $("#syn").val();
			if(syn=='1'){
				$("#r3_code").attr("dataType", "Require").attr("msg", "请填写");
				$("#r3_tr").show();
			}
			
			//提交审批
			$("#btn_submit").bind('click',function(){
				var isSubmit = Validator.Validate(this.form, 3);
				if (isSubmit) {
					this.form.submit();
				}
			});
			
			//同步
			$("#btn_syn").bind('click',function(){
				if(Validator.Validate(this.form, 3)){
					$("#method").attr("value","addToKonkaR3Shop");
					this.form.submit();
				}
			});
			
			//审批结果为驳回时，显示驳回节点
			$("#audit_res").bind('change',function(){
				var audit_res = $('#audit_res').val();
				if(audit_res=='1'){
					$('#back_tr').show();
				}else{
					$('#back_tr').hide();
				}
			});
			
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
			
		});
		</script>
	</body>
</html>
