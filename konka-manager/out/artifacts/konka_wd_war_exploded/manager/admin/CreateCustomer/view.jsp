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
				<html-el:hidden property="method" styleId="method" value='addToKonkaR3Shop'/>
				<html-el:hidden property="cust_id" styleId="cust_id" />
				<html-el:hidden property="queryString" styleId="queryString" />
				<div style="float:right;">
			  		<c:if test="${af.map.is_syn eq 0 and af.map.audit_stat eq 2 and not empty canSyn}">
				  		<input type="button" value="<fmt:message key="button.syn" bundle="${lang}"/>" class="but4" id="btn_syn" style="padding-left: 25px"/>&nbsp;&nbsp;&nbsp;
			  		</c:if>
				    <input class="but5" type="button" name="btn_back" value="<fmt:message key="button.back" bundle="${lang}"/>" id='btn_back' onclick="history.back();" style="padding-left: 25px"/>
				</div>
				<ul id="tabs">
					<li><a href="#" name="tab1"><fmt:message key="base.info" bundle="${lang}"/></a></li>
					<li><a href="#" name="tab2"><fmt:message key="audit.info" bundle="${lang}"/></a></li>
				</ul>
				<div id="content">
					<div id="tab1">
					<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
						<c:if test="${not empty af.map.link_r3_code}">
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb">R3客户编码：</strong></td>
							<td nowrap="nowrap" class="title_item" colspan="3">${af.map.link_r3_code }</td>
						</tr>
						</c:if>
						<c:if test="${not empty canSyn}">
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb">R3客户编码：</strong></td>
							<td nowrap="nowrap" class="title_item" colspan="3">
									<html-el:text property="link_r3_code" styleId="link_r3_code"></html-el:text>&nbsp;&nbsp;&nbsp;
				    				<font color="red">注：请填写已同步到R3客户管理的新客户R3编码</font>
							</td>
						</tr>
						</c:if>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" width="5%"><strong class="fb"><font color="red">* </font>客户名称：</strong></td>
							<td><c:out value="${af.map.cust_name}"/></td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb"><font color="red">* </font>业务员：</strong></td>
							<td width="50%">${af.map.map.ywy_name}</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb"><font color="red">* </font>所属经办：</strong></td>
							<td width="50%">${af.map.map.jb_name}</td>
							<td width="10%" nowrap="nowrap" class="title_item" ><strong class="fb"><font color="red">* </font>客户类别：</strong></td>
							<td>${customer_type_name}</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb"><font color="red">* </font>所在城市：</strong></td>
							<td colspan="3">${province}&nbsp;${city}&nbsp;${country}&nbsp;${town}</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb"><font color="red">* </font>详细地址：</strong></td>
							<td colspan="3"><c:out value="${af.map.entp_addr}"/></td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb"><font color="red">* </font>法定代表人：</strong></td>
							<td><c:out value="${af.map.host_name}"/></td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb"><font color="red">* </font>客户负责人：</strong></td>
							<td><c:out value="${af.map.link_man_name}"/></td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb"><font color="red">* </font>经营项目：</strong></td>
							<td><c:out value="${af.map.entp_main_pds}"/></td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb"><font color="red">* </font>联系电话：</strong></td>
							<td><c:out value="${af.map.link_man_tel}"/></td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb">门店面积（m²）：</strong></td>
							<td><c:out value="${af.map.total_area}"/></td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb">年销售额（万元）：</strong></td>
							<td>${entp_scale_name}</td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb">当地卖场数量：</strong></td>
							<td colspan="3"><c:out value="${af.map.total_malls}"/></td>
						</tr>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" ><strong class="fb">前期是否销售过康佳产品：</strong></td>
							<td colspan="3"><c:if test="${af.map.is_saled eq 1 }">是</c:if><c:if test="${af.map.is_saled eq 0 }">否</c:if></td>
						</tr>
						<c:if test="${af.map.is_saled eq 1 }">
							<tr>
								<td nowrap="nowrap" class="title_item" >&nbsp;</td>
								<td colspan="3"><strong class="fb">退出时间：</strong><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" /></td>
							</tr>
							<tr class="saled">
								<td nowrap="nowrap" class="title_item" >&nbsp;</td>
								<td colspan="3"><strong class="fb">退出原因：</strong>${af.map.out_reason }</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="title_item" >&nbsp;</td>
								<td colspan="3"><strong class="fb">是否有遗留问题：</strong>${af.map.leave_question }</td>
							</tr>
						</c:if>
						<tr>
							<td width="3%">&nbsp;</td>
							<td nowrap="nowrap" class="title_item" >备注说明：</td>
							<td colspan="3"><textarea rows="5" style="width:400px;" readonly="readonly">${af.map.memo }</textarea></td>
						</tr>
			    		
						<tr id='r3_tr' style="display: none">
			    			<td nowrap="nowrap" class="title_item" ><font color="red">* </font>R3编码：</td>
			    			<td colspan="3">
			    				<input type="text" id='r3_code' name='r3_code'/>
			    				<font color="red">注：请填写已同步到R3客户管理的新客户R3编码</font>
			    			</td>
			    		</tr>
			    		<tr>
							<td width="3%">&nbsp;</td>
							<td colspan="4"><strong class="fb">建户材料：</strong><font color="red">注：以下材料若提交则勾选，未提交请在输入框内写明原因。</font></td>
						</tr>
						<tr>
							<td colspan="5">
								<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
									<c:forEach var="cur" items="${attachmentList}" varStatus="vs">
										<c:choose>
											<c:when test="${vs.count eq 1}"><c:set var="title_text" value="1、营业执照复印件" /></c:when>
											<c:when test="${vs.count eq 2}"><c:set var="title_text" value="2、国税税务登记证"/></c:when>
											<c:when test="${vs.count eq 3}"><c:set var="title_text" value="3、地税税务登记证"/></c:when>
											<c:when test="${vs.count eq 4}"><c:set var="title_text" value="4、商务函"/></c:when>
											<c:when test="${vs.count eq 5}"><c:set var="title_text" value="5、法定代表人证明书"/></c:when>
											<c:when test="${vs.count eq 6}"><c:set var="title_text" value="6、发票填开资料"/></c:when>
											<c:when test="${vs.count eq 7}"><c:set var="title_text" value="7、收货签章证明"/></c:when>
											<c:when test="${vs.count eq 8}"><c:set var="title_text" value="8、发票签章证明"/></c:when>
											<c:when test="${vs.count eq 9}"><c:set var="title_text" value="9、印章预留函"/></c:when>
											<c:when test="${vs.count eq 10}"><c:set var="title_text" value="10、银行开户资料"/></c:when>
											<c:when test="${vs.count eq 11}"><c:set var="title_text" value="11、正当交易保证书"/></c:when>
											<c:when test="${vs.count eq 12}"><c:set var="title_text" value="12、售前返厂声明书"/></c:when>
											<c:when test="${vs.count eq 13}"><c:set var="title_text" value="13、客户开户申请表"/></c:when>
											<c:when test="${vs.count eq 14}"><c:set var="title_text" value="14、法人代表委托授权证明书"/></c:when>
											<c:when test="${vs.count eq 15}"><c:set var="title_text" value="15、委托转款授权书"/></c:when>
											<c:when test="${vs.count eq 16}"><c:set var="title_text" value="16、委托提货证明"/></c:when>
											<c:when test="${vs.count eq 17}"><c:set var="title_text" value="17、临时提货证明"/></c:when>
											<c:when test="${vs.count eq 18}"><c:set var="title_text" value="18、物流配送协议书"/></c:when>
										</c:choose>
										<c:if test="${vs.count%2 != 0 }">
											<tr>
												<td width="18%">
													<label>
														<c:if test="${cur.is_upload eq 1 }">
															<input type="checkbox" disabled="disabled" checked="checked"></input>
														</c:if>
														<c:if test="${empty cur.is_upload }">
															<input type="checkbox" disabled="disabled"></input>
														</c:if>
														<font color="red">*</font>${title_text }
													</label>
												</td>
												<td width="30%">
													<input type="text" size=35  value="${cur.resson }" disabled="disabled"/>
												</td>
										</c:if>
										<c:if test="${vs.count%2 eq 0 }">
												<td width="22%">
													<label>
													<c:if test="${cur.is_upload eq 1 }">
														<input type="checkbox" disabled="disabled" checked="checked"></input>
													</c:if>
													<c:if test="${empty cur.is_upload }">
														<input type="checkbox" disabled="disabled"></input>
													</c:if>
													<font color="red">*</font>${title_text }
													</label>
												</td>
												<td width="28%">
													<input type="text" size=35  value="${cur.resson }" disabled="disabled"/>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</table>
							</td>
						</tr>
					</table>
					</div>
					<!-- <div id='tab2'>
						<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
							<c:forEach var="cur" items="${attachmentList}" varStatus="vs">
								<tr>
									<td colspan="4" style="border-bottom:0px">
										<table width="100%" border="0">
											<tr>
												<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">
												${vs.count }、
												<c:choose>
													<c:when test="${vs.count eq 1}">营业执照复印件</c:when>
													<c:when test="${vs.count eq 2}">国税税务登记证复印件</c:when>
													<c:when test="${vs.count eq 3}">地税税务登记证复印件</c:when>
													<c:when test="${vs.count eq 4}">商务函</c:when>
													<c:when test="${vs.count eq 5}">法定代表人证明书</c:when>
													<c:when test="${vs.count eq 6}">发票填开资料</c:when>
													<c:when test="${vs.count eq 7}">收货签章证明</c:when>
													<c:when test="${vs.count eq 8}">发票签章证明</c:when>
													<c:when test="${vs.count eq 9}">印章预留函</c:when>
													<c:when test="${vs.count eq 10}">银行开户资料</c:when>
													<c:when test="${vs.count eq 11}">正当交易保证书</c:when>
													<c:when test="${vs.count eq 12}">售前返厂声明书</c:when>
													<c:when test="${vs.count eq 13}">客户开户申请表</c:when>
													<c:when test="${vs.count eq 14}">法人代表委托授权证明书</c:when>
													<c:when test="${vs.count eq 15}">委托转款授权书</c:when>
													<c:when test="${vs.count eq 16}">委托提货证明</c:when>
													<c:when test="${vs.count eq 17}">临时提货证明</c:when>
													<c:when test="${vs.count eq 18}">物流配送协议书</c:when>
												</c:choose>
												</strong></td>
												<td>
												<c:if test="${cur.is_upload eq 0}">未提交，
													原因为：${cur.resson}
												</c:if>
												<c:if test="${cur.is_upload eq 1}">已提交</c:if>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</c:forEach>
							<!-- <tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%" border="0">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">1、营业执照复印件</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='yyzz'/> 若未提交，请填写原因：<input name='res_text' id='yyzz_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">2、国税税务登记证复印件</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='gssw'/> 若未提交，请填写原因：<input name='res_text' id='gssw_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">3、地税税务登记证复印件</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='dssw'/> 若未提交，请填写原因：<input name='res_text' id='dssw_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">4、商务函</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='swh'/> 若未提交，请填写原因：<input name='res_text' id='swh_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">5、法定代表人证明书</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='fddbr'/> 若未提交，请填写原因：<input name='res_text' id='fadbr_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">6、发票填开资料</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='fptk'/> 若未提交，请填写原因：<input name='res_text' id='fptk_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">7、收货签章证明</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='shqz'/> 若未提交，请填写原因：<input name='res_text' id='shqz_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">8、发票签章证明</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='fpqz'/> 若未提交，请填写原因：<input name='res_text' id='fpqz_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">9、印章预留函</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='yzylh'/> 若未提交，请填写原因：<input name='res_text' id='yzylh_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">10、银行开户资料</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='yhkh'/> 若未提交，请填写原因：<input name='res_text' id='yhkh_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">11、正当交易保证书</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='zdjy'/> 若未提交，请填写原因：<input name='res_text' id='zdjy_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">12、售前返厂声明书</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='sqfc'/> 若未提交，请填写原因：<input name='res_text' id='sqfc_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">13、客户开户申请表</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='khkh'/> 若未提交，请填写原因：<input name='res_text' id='khkh_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">14、法人代表委托授权证明书</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='frdb'/> 若未提交，请填写原因：<input name='res_text' id='frdb_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">15、委托转款授权书</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='wtzk'/> 若未提交，请填写原因：<input name='res_text' id='wtzk_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">16、委托提货证明</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='wtth'/> 若未提交，请填写原因：<input name='res_text' id='wtth' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">17、临时提货证明</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='lsth'/> 若未提交，请填写原因：<input name='res_text' id='lsth_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="border-bottom:0px">
								<table width="100%">
									<tr>
										<td width="30%" style="padding-left:10px"><font color="red">* </font><strong class="fb">18、物流配送协议书</strong></td>
										<td>是否提交？<input type="checkbox" name='boxs' id='wlps'/> 若未提交，请填写原因：<input name='res_text' id='wlps_res' type="text" size=50 maxlength="200"/></td>
									</tr>
								</table>
							</td>
						</tr>
						</table>
					</div> -->
					<div id="tab2">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				    		<tr>
					        	<td class="title_item" style="text-align:center;font-size:15px;font-weight:bold;color:#74685F">审核信息</td>
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
						        				<th width="5%" align="center">序号</th>
						        				<th width="10%" align="center">日期</th>
						        				<th width="10%" align="center">审核人</th>
						        				<th width="10%" align="center">职务</th>
						        				<th width="10%" align="center">结果</th>
						        				<th align="center">意见</th>
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
						        						<c:when test="${cur.AUDIT_RESULT eq 0}"><span style="color:green;">审核通过</span></c:when>
						        						<c:when test="${cur.AUDIT_RESULT eq 1}"><span style="color:#F00;">驳回至${cur.AUDIT_NODE_NAME }</span></c:when>
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
			
		//初始化
		$(document).ready(function(){
			
			//同步
			$("#btn_syn").bind('click',function(){
				var link_r3_code = $("#link_r3_code").val();
				if(link_r3_code!=''){
					this.form.submit();
				}else{
					alert("请填写R3编码");
				}
			});
			
		});
		</script>
	</body>
</html>
