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
		        <td><fmt:message key="current.location" bundle="${lang}"/>：${naviString}</td>
		      </tr>
		    </table>
  		</div>
  		<div class="rtabcont2">
	    	<html-el:form action="/admin/CreateCustomer">
				<html-el:hidden property="mod_id" styleId="mod_id" value='${mod_id }'/>
				<html-el:hidden property="method" styleId="method" value="save" />
				<html-el:hidden property="cust_id" styleId="cust_id" />
				<html-el:hidden property="role_id" styleId="role_id" value="${role_id }"/>
				<html-el:hidden property="source_flag" styleId="source_flag" value="${source_flag }" />
				<html-el:hidden property="queryString" styleId="queryString" />
				<ul id="tabs">
					<li><a href="#" name="tab1"><fmt:message key="base.info" bundle="${lang}"/></a></li>
					<c:if test="${not empty af.map.cust_name }">
						<li><a href="#" name="tab2"><fmt:message key="audit.info" bundle="${lang}"/></a></li>
					</c:if>
				</ul>
				<div id="content">
					<div id="tab1">
					<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" width="5%">
								<strong class="fb"><font color="red">* </font><fmt:message key="customer.name" bundle="${lang}"/></strong>
							</td>
							<td width="40%">
								<html-el:text property="cust_name" size="30" maxlength="30" styleId="cust_name" />
							</td>
							<td nowrap="nowrap" class="title_item" width="5%">
								<strong class="fb"><font color="red">* </font><fmt:message key="ywy" bundle="${lang}"/></strong>
							</td>
							<td width="30%">
								<html-el:select property="ywy_job_id" styleId="ywy_job_id" style='width:216px'>
									<html-el:option value=""><fmt:message key="please.choose" bundle="${lang}"/></html-el:option>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" width="5%">
								<strong class="fb"><font color="red">* </font><fmt:message key="belong.jb" bundle="${lang}"/>：</strong>
							</td>
							<td width="40%">
								<html-el:select property="jb_id" styleId="jb_id" style='width:216px'>
									<html-el:option value=""><fmt:message key="please.choose" bundle="${lang}"/></html-el:option>
									<c:forEach var="cur" items="${jblist}">
										<html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
									</c:forEach>
								</html-el:select>
							</td>
							<td nowrap="nowrap" class="title_item" width="5%">
								<strong class="fb"><font color="red">* </font><fmt:message key="customer.type" bundle="${lang}"/>：</strong>
							</td>
							<td width="30%">
								<html-el:select property="customer_type" styleId="customer_type" style='width:216px'>
									<html-el:option value=""><fmt:message key="please.choose" bundle="${lang}"/></html-el:option>
									<c:forEach var="cur" items="${konkaCategoryList}">
										<html-el:option value="${cur.c_index}">[${cur.c_comm}]${cur.c_name}</html-el:option>
									</c:forEach>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								<strong class="fb"><font color="red">* </font><fmt:message key="belong.city" bundle="${lang}"/>：</strong>
							</td>
							<td colspan="3">
								<select name="province" id="province" style="width:180px;">
				                  <option value=""><fmt:message key="choose.province" bundle="${lang}"/></option>
				                </select>
				                &nbsp;
				                <select name="city" id="city" style="width:100px;">
				                  <option value=""><fmt:message key="choose.city" bundle="${lang}"/></option>
				                </select>
				                &nbsp;
				                <select name="country" id="country" style="width:100px;">
				                  <option value=""><fmt:message key="choose.country" bundle="${lang}"/></option>
				                </select>
				                &nbsp;
				                <select name="town" id="town" style="width:100px;">
				                  <option value=""><fmt:message key="choose.town" bundle="${lang}"/></option>
				                </select>
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								<strong class="fb"><font color="red">* </font><fmt:message key="address.detail" bundle="${lang}"/>：</strong>
							</td>
							<td colspan="3">
								<html-el:text property="entp_addr" styleId="entp_addr" size="83" maxlength="40" />
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								<strong class="fb"><font color="red">* </font><fmt:message key="legal.man" bundle="${lang}"/>：</strong>
							</td>
							<td>
								<html-el:text property="host_name" styleId="host_name" size="30" maxlength="20" />
							</td>
							<td nowrap="nowrap" class="title_item">
								<strong class="fb"><font color="red">* </font><fmt:message key="customer.charge.man" bundle="${lang}"/>：</strong>
							</td>
							<td>
								<html-el:text property="link_man_name" styleId="link_man_name" size="30" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								<strong class="fb"><font color="red">* </font><fmt:message key="engage.project" bundle="${lang}"/>：</strong>
							</td>
							<td>
								<html-el:text property="entp_main_pds" styleId="entp_main_pds" size="30" maxlength="20" />
							</td>
							<td nowrap="nowrap" class="title_item">
								<strong class="fb"><font color="red">* </font><fmt:message key="contact.phone" bundle="${lang}"/>：</strong>
							</td>
							<td>
								<html-el:text property="link_man_tel" styleId="link_man_tel" size="30" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								<strong class="fb"><fmt:message key="store.area" bundle="${lang}"/>：</strong>
							</td>
							<td>
								<html-el:text property="total_area" styleId="total_area" size="30" maxlength="20" />
							</td>
							<td nowrap="nowrap" class="title_item" align="right">
								<strong class="fb"><fmt:message key="year.sale.money" bundle="${lang}"/>：</strong>
							</td>
							<td>
								<html-el:select property="total_sale" styleId="total_sale" style='width:216px'>
									<html-el:option value=""><fmt:message key="please.choose" bundle="${lang}"/></html-el:option>
									<c:forEach var="cur" items="${entpScaleList}">
										<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
									</c:forEach>
								</html-el:select>
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								<strong class="fb"><fmt:message key="local.market.num" bundle="${lang}"/>：</strong>
							</td>
							<td colspan="3">
								<html-el:text property="total_malls" styleId="total_malls" size="30" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								<strong class="fb"><fmt:message key="before.is.sale.konka" bundle="${lang}"/>：</strong>
							</td>
							<td colspan="3">
								<html-el:radio property="is_saled" value="1" styleId="yes_radio"></html-el:radio><fmt:message key="yes" bundle="${lang}"/>&nbsp;
								<html-el:radio property="is_saled" value="0" styleId="no_radio"></html-el:radio><fmt:message key="no" bundle="${lang}"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<span class='yes_text'><fmt:message key="yes.choose.below" bundle="${lang}"/>：</span>
							</td>
						</tr>
						<tr class='yes_text'>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								&nbsp;
							</td>
							<td colspan="3">
								<strong class="fb"><fmt:message key="out.date" bundle="${lang}"/>：</strong>
								<html-el:text property="out_date" styleId="out_date" size="47" maxlength="10" /><font color="red">* </font><fmt:message key="format.is.date" bundle="${lang}"/>
								<!-- <html-el:text property="out_date" styleId="out_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />-->
							</td>
						</tr>
						<tr class='yes_text'>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">&nbsp;
							</td>
							<td colspan="3">
								<strong class="fb"><fmt:message key="out.ression" bundle="${lang}"/>：</strong>
								<html-el:text property="out_reason" styleId="out_reason" size="47" maxlength="50" />
							</td>
						</tr>
						<tr class='yes_text'>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								&nbsp;
							</td>
							<td colspan="3">
								<strong class="fb"><fmt:message key="is.have.question" bundle="${lang}"/>：</strong>
								<html-el:text property="leave_question" styleId="leave_question" size="40" maxlength="50" />
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item">
								<fmt:message key="remark.explain" bundle="${lang}"/>：
							</td>
							<td colspan="3">
								<html-el:textarea property="memo" styleId="memo" rows="5" cols="85" ></html-el:textarea>
							</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td colspan="4"><strong class="fb"><fmt:message key="create.customer.data" bundle="${lang}"/></strong>&nbsp;&nbsp;<font color="red"><fmt:message key="create.customer.data.explain" bundle="${lang}"/></font></td>
						</tr>
						<tr>
							<td colspan="5">
								<c:if test="${empty attachmentList }">
								<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
										<td width="18%">
											<label>
												<input type="checkbox" id="checkbox1" name="checkbox1" value="1"/>
												<font color="red">*</font>1、<fmt:message key="business.licence" bundle="${lang}"/>
											</label>
										</td>
										<td width="28%">
											<input name='file1_res' id='file1_res' type="text" size=35 maxlength="200" value=""/>
										</td>
										<td width="25%">
											<label>
											<input type="checkbox" id="checkbox2" name="checkbox2" value="1"/>
											<font color="red">*</font>2、<fmt:message key="country.revenue" bundle="${lang}"/>
											</label>
										</td>
										<td width="27%">
											<input name='file2_res' id='file2_res' type="text" size=35 maxlength="200" value=""/>
										</td>
									</tr>
									<tr>
										<td width="18%">
											<label>
											<input type="checkbox" id="checkbox3" name="checkbox3" value="1"/>
											<font color="red">*</font>3、<fmt:message key="local.revenue" bundle="${lang}"/>
											</label>
										</td>
										<td width="37%">
											<input name='file3_res' id='file3_res' type="text" size=35 maxlength="200" value=""/>
										</td>
										<td width="18%">
											<label>
											<input type="checkbox" id="checkbox4" name="checkbox4" value="1"/>
											<font color="red">*</font>4、<fmt:message key="business.letter" bundle="${lang}"/>
											</label>
										</td>
										<td width="27%">
											<input name='file4_res' id='file4_res' type="text" size=35 maxlength="200" value=""/>
										</td>
									</tr>
									<tr>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox5" name="checkbox5" value="1"/>
											<font color="red">*</font>5、<fmt:message key="legal.man.certificate" bundle="${lang}"/>
											</label>
										</td>
										<td width="38%">
											<input name='file5_res' id='file5_res' type="text" size=35 maxlength="200" value=""/>
										</td>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox6" name="checkbox6" value="1"/>
											<font color="red">*</font>6、<fmt:message key="invoice.data" bundle="${lang}"/>
											</label>
										</td>
										<td width="28%">
											<input name='file6_res' id='file6_res' type="text" size=35 maxlength="200" value=""/>
										</td>
									</tr>
									<tr>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox7" name="checkbox7" value="1"/>
											<font color="red">*</font>7、<fmt:message key="consignee.certificate" bundle="${lang}"/>
											</label>
										</td>
										<td width="38%">
											<input name='file7_res' id='file7_res' type="text" size=35 maxlength="200" value=""/>
										</td>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox8" name="checkbox8" value="1"/>
											<font color="red">*</font>8、<fmt:message key="invoice.certificate" bundle="${lang}"/>
											</label>
										</td>
										<td width="28%">
											<input name='file8_res' id='file8_res' type="text" size=35 maxlength="200" value=""/>
										</td>
									</tr>
									<tr>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox9" name="checkbox9" value="1"/>
											<font color="red">*</font>9、<fmt:message key="seal.reserve" bundle="${lang}"/>
											</label>
										</td>
										<td width="38%">
											<input name='file9_res' id='file9_res' type="text" size=35 maxlength="200" value=""/>
										</td>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox10" name="checkbox10" value="1"/>
											<font color="red">*</font>10、<fmt:message key="bank.data" bundle="${lang}"/>
											</label>
										</td>
										<td width="28%">
											<input name='file10_res' id='file10_res' type="text" size=35 maxlength="200" value=""/>
										</td>
									</tr>
									<tr>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox11" name="checkbox11" value="1"/>
											<font color="red">*</font>11、<fmt:message key="business.assurance" bundle="${lang}"/>
											</label>
										</td>
										<td width="38%">
											<input name='file11_res' id='file11_res' type="text" size=35 maxlength="200" value=""/>
										</td>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox12" name="checkbox12" value="1"/>
											<font color="red">*</font>12、<fmt:message key="back.factory.declaration" bundle="${lang}"/>
											</label>
										</td>
										<td width="28%">
											<input name='file12_res' id='file12_res' type="text" size=35 maxlength="200" value=""/>
										</td>
									</tr>
									<tr>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox13" name="checkbox13" value="1"/>
											<font color="red">*</font>13、<fmt:message key="customer.apply" bundle="${lang}"/>
											</label>
										</td>
										<td width="38%">
											<input name='file13_res' id='file13_res' type="text" size=35 maxlength="200" value=""/>
										</td>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox14" name="checkbox14" value="1"/>
											<font color="red">*</font>14、<fmt:message key="legal.man.entrust" bundle="${lang}"/>
											</label>
										</td>
										<td width="28%">
											<input name='file14_res' id='file14_res' type="text" size=35 maxlength="200" value=""/>
										</td>
									</tr>
									<tr>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox15" name="checkbox15" value="1"/>
											<font color="red">*</font>15、<fmt:message key="entrust.transfer" bundle="${lang}"/>
											</label>
										</td>
										<td width="38%">
											<input name='file15_res' id='file15_res' type="text" size=35 maxlength="200" value=""/>
										</td>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox16" name="checkbox16" value="1"/>
											<font color="red">*</font>16、<fmt:message key="entrust.get.goods" bundle="${lang}"/>
											</label>
										</td>
										<td width="28%">
											<input name='file16_res' id='file16_res' type="text" size=35 maxlength="200" value=""/>
										</td>
									</tr>
									<tr>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox17" name="checkbox17" value="1"/>
											<font color="red">*</font>17、<fmt:message key="temporary.get.goods" bundle="${lang}"/>
											</label>
										</td>
										<td width="38%">
											<input name='file17_res' id='file17_res' type="text" size=35 maxlength="200" value=""/>
										</td>
										<td width="17%">
											<label>
											<input type="checkbox" id="checkbox18" name="checkbox18" value="1"/>
											<font color="red">*</font>18、<fmt:message key="logistics.distribution" bundle="${lang}"/>
											</label>
										</td>
										<td width="28%">
											<input name='file18_res' id='file18_res' type="text" size=35 maxlength="200" value=""/>
										</td>
									</tr>
								</table>
								</c:if>
								<c:if test="${not empty attachmentList }">
									<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
										<c:forEach var="cur" items="${attachmentList}" varStatus="vs">
											<c:choose>
												<c:when test="${vs.count eq 1}"><c:set var="title_text">1、<fmt:message key="business.licence" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 2}"><c:set var="title_text">2、<fmt:message key="country.revenue" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 3}"><c:set var="title_text">3、<fmt:message key="local.revenue" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 4}"><c:set var="title_text">4、<fmt:message key="business.letter" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 5}"><c:set var="title_text">5、<fmt:message key="legal.man.certificate" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 6}"><c:set var="title_text">6、<fmt:message key="invoice.data" bundle="${lang}"/></c:set>></c:when>
												<c:when test="${vs.count eq 7}"><c:set var="title_text">7、<fmt:message key="consignee.certificate" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 8}"><c:set var="title_text">8、<fmt:message key="invoice.certificate" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 9}"><c:set var="title_text">9、<fmt:message key="seal.reserve" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 10}"><c:set var="title_text">10、<fmt:message key="bank.data" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 11}"><c:set var="title_text">11、<fmt:message key="business.assurance" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 12}"><c:set var="title_text">12、<fmt:message key="back.factory.declaration" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 13}"><c:set var="title_text">13、<fmt:message key="customer.apply" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 14}"><c:set var="title_text">14、<fmt:message key="legal.man.entrust" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 15}"><c:set var="title_text">15、<fmt:message key="entrust.transfer" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 16}"><c:set var="title_text">16、<fmt:message key="entrust.get.goods" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 17}"><c:set var="title_text">17、<fmt:message key="temporary.get.goods" bundle="${lang}"/></c:set></c:when>
												<c:when test="${vs.count eq 18}"><c:set var="title_text">18、<fmt:message key="logistics.distribution" bundle="${lang}"/></c:set></c:when>
											</c:choose>
											<c:if test="${vs.count%2 != 0 }">
												<tr>
													<td width="20%">
														<label>
															<c:if test="${cur.is_upload eq 1 }">
																<input type="checkbox" value='1' id='checkbox${vs.count }' name='checkbox${vs.count }' checked="checked"></input>
															</c:if>
															<c:if test="${empty cur.is_upload }">
																<input type="checkbox" value='1' id='checkbox${vs.count }' name='checkbox${vs.count }'></input>
															</c:if>
															<font color="red">*</font>${title_text }
														</label>
													</td>
													<td width="30%">
														<input type="text" size=35 name='file${vs.count }_res' id='file${vs.count }_res' value="${cur.resson }" />
													</td>
											</c:if>
											<c:if test="${vs.count%2 eq 0 }">
													<td width="23%">
														<label>
														<c:if test="${cur.is_upload eq 1 }">
															<input type="checkbox" value='1' id='checkbox${vs.count }' name='checkbox${vs.count }' checked="checked"></input>
														</c:if>
														<c:if test="${empty cur.is_upload }">
															<input type="checkbox" value='1' id='checkbox${vs.count }' name='checkbox${vs.count }'></input>
														</c:if>
														<font color="red">*</font>${title_text }
														</label>
													</td>
													<td width="25%">
														<input type="text" size=35 name='file${vs.count }_res' id='file${vs.count }_res' value="${cur.resson }" />
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</table>
								</c:if>
							</td>
						</tr>
						<c:if test="${not empty show_audit }">
						<tr>
							<td colspan="5">
								<table id='audit_show' width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
									<tr class="oartop" style="margin-bottom: 10px; height: 12px; line-height: 12px;">
										<td colspan="2"><strong class="fb"><fmt:message key="submit.audit" bundle="${lang}"/></strong></td>
									</tr>
							        <tr>
									   	<td width='20%' align="right">
									   		<strong class="fb"><font color="red">* </font><fmt:message key="audit.result" bundle="${lang}"/>：</strong>
									   	</td>
									   	<td>
									   		<select id="audit_res" name="audit_res" style="width:150px">
												<option value=""><fmt:message key="please.choose" bundle="${lang}"/></option>
												<option value="0"><fmt:message key="agree" bundle="${lang}"/></option>
												<option value="1"><fmt:message key="reject" bundle="${lang}"/></option>
									   		</select>		   		
									   	</td>
									</tr>
									<tr id='back_tr' style="display: none">
										<td align="right">
									   		<strong class="fb"><fmt:message key="reject.to" bundle="${lang}"/>：</strong>
									   	</td>
									   	<td>
									   		<select id="to_back" name="to_back" style="width:150px;">
									   		</select>			   		
									   	</td>
									</tr>
							        <tr>
									   	<td align="right">
									   		<strong class="fb"><fmt:message key="audit.opinion" bundle="${lang}"/>：</strong>
									   	</td>
									   	<td>
									   		<textarea rows="5" cols="70" id="audit_comment" name='audit_comment'></textarea>		   		
									   	</td>
									</tr>
			    				</table>
							</td>
						</tr>
						</c:if>
						<tr>
							<td colspan="5" align="center">
								<label> 
								<input class="but4" type="button" name="Submit4" value="<fmt:message key="button.submit" bundle="${lang}"/>" id="btn_submit" style="padding-left: 25px"></input>&nbsp;&nbsp;
								<input class="but5" type="button" name="Submit5" value="<fmt:message key="button.back" bundle="${lang}"/>" id='btn_back' onclick="history.back();" style="padding-left: 25px"></input>
								</label>
							</td>
						</tr>
					</table>
					</div>
					<div id="tab2">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				    		<tr>
					        	<td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F"><fmt:message key="audit.info" bundle="${lang}"/></td>
					        </tr>
					        <tr>
					        	<td align="center">
					        		<div style="width:100%;">
							        	<jsp:include page="../_inc/_audit_progress.jsp" />
							        </div>
					        	</td>
					        </tr>
					        <c:if test="${not empty filesAuditNodeList}">
					        	<tr>
					        		<td align="center">
						        		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="navClass" style="margin:10px 0 10px 0;">
						        		<thead id="nav" style="width:99%;background:#abd589;">
						        			<tr>
						        				<th width="5%" align="center"><fmt:message key="row.num" bundle="${lang}"/></th>
						        				<th width="10%" align="center"><fmt:message key="date" bundle="${lang}"/></th>
						        				<th width="10%" align="center"><fmt:message key="audit.man" bundle="${lang}"/></th>
						        				<th width="10%" align="center"><fmt:message key="job" bundle="${lang}"/></th>
						        				<th width="10%" align="center"><fmt:message key="result" bundle="${lang}"/></th>
						        				<th align="center"><fmt:message key="opinion" bundle="${lang}"/></th>
						        			</tr>
					        			</thead>
					        			 <c:forEach var="cur" items="${filesAuditNodeList}" varStatus="vs">
								        	<c:set var="i" value="${vs.count}" />
								          	<c:if test="${cur.AUDIT_RESULT eq 0}">
								            	<c:set var="audit_result" value="审核通过" />
								          	</c:if>
								          	<c:if test="${cur.AUDIT_RESULT eq 1}">
								            	<c:set var="audit_result" value="<span style='color:#f00;'>驳回至${cur.AUDIT_NODE_NAME }</span>" />
								          	</c:if>
								        	<tr>
								        		<td align="center" nowrap="nowrap">${vs.count}</td>
									            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.AUDIT_DATETIME}" pattern="yyyy-MM-dd HH:mm"/></td>
									            <td align="center" nowrap="nowrap">${cur.AGENT_AUDIT_USER}</td>
									            <td align="center" nowrap="nowrap">${cur.AUDIT_USER }</td>
								        		<td align="center" nowrap="nowrap">
								        			<c:choose>
						        						<c:when test="${cur.AUDIT_RESULT eq 0}"><span style="color:green;"><fmt:message key="audit.pass" bundle="${lang}"/></span></c:when>
						        						<c:when test="${cur.AUDIT_RESULT eq 1}"><span style="color:#F00;"><fmt:message key="reject.to" bundle="${lang}"/>${cur.AUDIT_NODE_NAME }</span></c:when>
						        					</c:choose>
								        		</td>
									            <td align="center" nowrap="nowrap">${fn:escapeXml(cur.AUDIT_COMMENT)}</td>
								          	</tr>
								        </c:forEach>
								 		</table>
								 	</td>
								 </tr>
        					</c:if>
						</table>
						<br/>
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
		<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=default"></script>
		<script type="text/javascript">
			
		//初始化
		$(document).ready(function(){
			//初始化业务员列表
			var url = "${ctx}/manager/admin/CsAjax.do?method=getYwyUserListByDeptId2&jb_id=${dept_id}";
			$.getJSON(url, function(data) {
				if(data != null){
					$.each(data, function(i, item) {
						var option = $("<option></option>").val(item[1]).text(item[0]);
						option.appendTo($("#ywy_job_id"));
					});
					if('${af.map.ywy_job_id}' != null || '${af.map.ywy_job_id}' != '' ){
						$("#ywy_job_id").val('${af.map.ywy_job_id}');
					}
				}
			});
			
			if('${af.map.cust_id}'!=''){
				if('${role_id}'=='35'){ //财务部
					$("#to_back").append("<option value='1'>业务员</option>");
					$("#sure_text").show();
				}
				if('${role_id}'=='39'){ //财务经理
					$("#to_back").append("<option value='1'>业务员</option>");
					$("#to_back").append("<option value='2'>财务部</option>");
				}
				if('${role_id}'=='34'){ //总经理
					$("#to_back").append("<option value='1'>业务员</option>");
					$("#to_back").append("<option value='2'>财务部</option>");
					$("#to_back").append("<option value='3'>财务经理</option>");
				}
				if('${role_id}'=='18'){ //总部
					$("#to_back").append("<option value='1'>业务员</option>");
					$("#to_back").append("<option value='2'>财务部</option>");
					$("#to_back").append("<option value='3'>财务经理</option>");
					$("#to_back").append("<option value='4'>分公司总经理</option>");
				}
			}
			
			$("#cust_name").attr("dataType", "Require").attr("msg", "请填写");
			$("#jb_id").attr("dataType", "Require").attr("msg", "请选择");
			$("#customer_type").attr("dataType", "Require").attr("msg", "请选择");
			$("#country").attr("dataType", "Require").attr("msg", "请选择完全");
			$("#host_name").attr("dataType", "Require").attr("msg", "请填写");
			$("#link_man_name").attr("dataType", "Require").attr("msg", "请填写");
			$("#entp_main_pds").attr("dataType", "Require").attr("msg", "请填写");
			$("#entp_addr").attr("dataType", "Require").attr("msg", "请填写");
			$("#link_man_tel").attr("dataType","Require").attr("msg","请填写");
			
			$(".yes_text").hide();
			
			//所在地市联动
			$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}"});
			$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
			$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
			$("#town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${town}"});
			$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
			
			//提交表单
			$("#btn_submit").click(function(){
				var isSubmit = Validator.Validate(this.form, 3);
				if (isSubmit) {
					var demo = $("#memo").val();
					if(demo.length>1000){
						alert("备注说明的文字长度不得超过1000个！");
						return;
					}
					if(checkInfo()){
						if('${show_audit}'){
							var isSubmit = $("#audit_res").val();
							if (''==isSubmit) {
								alert("请选择审核结果");
								return;
							}
						}
						$("#btn_submit").attr("value", "正在提交").attr("disabled", "true");
						this.form.submit();
					}
				}
			});
			
			//同步
			$("#btn_syn").bind('click',function(){
				if(Validator.Validate(this.form, 2)){
					$("#method").attr("value","addToKonkaR3Shop");
					this.form.submit();
				}
			});
			
			//审核结果为驳回时，显示驳回节点
			$("#audit_res").bind('change',function(){
				var audit_res = $('#audit_res').val();
				if(audit_res=='1'){
					$('#back_tr').show();
				}else{
					$('#back_tr').hide();
				}
			});
			
			//附件校验
			function checkInfo(){
				var flag = true;
				for(var i=1;i<=18;i++){
					if(!document.getElementById("checkbox"+i).checked){
						if($("#file"+i+"_res").val()==''){
							flag = false;
						}
					}
				}
				if(!flag){
					alert("请检查材料信息，若未提交，则需填写原因。");
				}
				return flag;
			}
			
			//单击“是”的事件
			$("#yes_radio").click(function(){
				$(".yes_text").show();
				//iframe高度自适应
				window.parent.resizeFrameHeight('mainFrame', 3);
			});
			//单击“否”的事件
			$("#no_radio").click(function(){
				$("#out_date").val("");
				$("#out_reason").val("");
				$("#leave_question").val("");
				$(".yes_text").hide();
				window.parent.resizeFrameHeight('mainFrame', 3);
			});
			
			//tabs切换Begin
			$("#content div[id^=tab]").hide(); // Initially hide all content
		    $("#tabs li:first").attr("id","current"); // Activate first tab
		    $("#content div[id^=tab]:first").fadeIn(); // Show first tab content
		    $('#tabs a').click(function(e){
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
		
		function resizeFrameHeight(offset, min_height) {
			// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
			$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
		}
		</script>
	</body>
</html>
