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
<link href="${ctx}/styles/customer/css/tab.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/fancybox/fancybox.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
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
  <div class="rtabcont2" style="padding-left:0px;overflow-x:auto;overflow-y:hidden;">
	<html-el:form action="/admin/KonkaR3MmtMatch" enctype="multipart/form-data">
		<html-el:hidden property="id" styleId="id" />
		<html-el:hidden property="mod_id" styleId="mod_id" />
		<html-el:hidden property="method" styleId="method" value="modify" />
		<html-el:hidden property="queryString" styleId="queryString" />
		<div style="float:right;">
			<html-el:button property="" value="提&nbsp;交" styleClass="bgButtonSave" styleId="btn_submit" style="padding-left:25px"/>&nbsp;
			<html-el:button property="" value="重&nbsp;置" styleClass="btn_reset" styleId="btn_reset" onclick="this.form.reset();" style="padding-left:25px;"/>&nbsp;
			<html-el:button property="" value="返&nbsp;回" styleClass="bgButtonBack" styleId="btn_back" onclick="history.back();" style="padding-left:25px"/>
		</div>
		<ul id="tabs">
			<li><a href="#" name="tab1">常规信息</a></li>
			<li><a href="#" name="tab2">客户资料</a></li>
			<li><a href="#" name="tab3">联系信息</a></li>
		</ul>
		<div id="content">
			<div id="tab1">
				<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="20%"  height="30" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';" align="right"><span>上次维护时间：</span></td>
						<td width="40%" ><fmt:formatDate value="${af.map.create_date}" pattern="yyyy年MM月dd日" /></td>
						<td width="15%" height="30" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';" align="right"><span>上次维护人：</span></td>
						<td width="35%">${af.map.add_name}</td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>客户名称：</td>
						<td align="left">
							<html-el:hidden property="customer_name_old" styleId="customer_name_old" value="${af.map.customer_name }" />
							<html-el:text property="customer_name" size="40" maxlength="30" styleId="customer_name" />
						</td>
						<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>R3编码：</td>
						<td align="left">
							<html-el:text property="r3_code" size="18" maxlength="30" styleId="r3_code" readonly="true"/>
						 	&nbsp;<span style="color:red">*</span><span style="color:#f00;display:none;" id="r3_code_exist_error">该R3编码已存在，请重新输入！</span></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所在区域名称：</td>
						<td align="left">
							<html-el:hidden property="area_name_old" styleId="area_name_old" value="${af.map.area_name }" />
							<html-el:select property="area_name" styleId="area_name" style="width:100px">
								<html-el:option value="">-请选择-</html-el:option>
								<html-el:option value="华东">华东</html-el:option>
				                <html-el:option value="山东">山东</html-el:option>
								<html-el:option value="东北">东北</html-el:option>
								<html-el:option value="华北">华北</html-el:option>
								<html-el:option value="华南">华南</html-el:option>
								<html-el:option value="西南">西南</html-el:option>
								<html-el:option value="华中">华中</html-el:option>
								<html-el:option value="西北">西北</html-el:option>
                            </html-el:select>
						</td>
						<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>合并客户编码：</td>
						<td align="left">
							<html-el:hidden property="customer_code_old" styleId="customer_code_old" value="${af.map.customer_code }" />
							<html-el:text property="customer_code" size="18" maxlength="30" styleId="customer_code" />
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>事业部：</td>
						<td align="left">
					         <html-el:select property="branch_name" styleId="branch_name" style="width:100px">
					         	<html-el:option value="">请选择...</html-el:option>
					         	<html-el:option value="1">白电</html-el:option>
					         	<html-el:option value="2">多媒体</html-el:option>
					         </html-el:select>
					    </td>
						<td nowrap="nowrap" class="title_item" align="right">
					    	<font color="red">* </font>客户状态：
					    </td>
						<td align="left">
							<html-el:hidden property="shop_status_old" styleId="shop_status_old" value="${af.map.shop_status }" />
							<html-el:select property="shop_status" styleId="shop_status" style="width:100px">
								<html-el:option value="">请选择...</html-el:option>
								<html-el:option value="1">新客户</html-el:option>
								<html-el:option value="2">正式客户</html-el:option>
								<html-el:option value="3">静止客户</html-el:option>
								<html-el:option value="4">无效客户</html-el:option>
							</html-el:select>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>分公司：</td>
						<td align="left">
							<c:if test="${not empty sys_admin }">
							 	<html-el:hidden property="branch_area_name" styleId="branch_area_name" />
						        <html-el:select property="branch_area_name_2" styleId="branch_area_name_2" onchange="if($(this).val() != '${af.map.branch_area_name_2}') { $(this).next().show(); } else { $(this).next().hide(); }" style="width:100px">
						         	<html-el:option value="">请选择</html-el:option>
						         	<c:forEach items="${BranchList}" var="cur">
						         		<html-el:option value="${cur.dept_sn}">${cur.dept_name}</html-el:option>
						         	</c:forEach>
						        </html-el:select>
						        <span style="color:#F00;display:none;">注意：您变更了 “${af.map.branch_area_name}” 分公司，修改后请及时分配该该客户的业务员。</span>
							</c:if>
							<c:if test="${empty sys_admin }">
								${af.map.branch_area_name}
							</c:if>
					    </td>
						<td nowrap="nowrap" class="title_item" align="right">R3分类：</td>
						<td align="left"><html-el:select property="is_sdf" styleId="is_sdf" disabled="true" style="width:100px">
								<html-el:option value="0">售达方</html-el:option>
								<html-el:option value="1">送达方</html-el:option>
							 </html-el:select></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>客户类别：</td>
						<td align="left" colspan="3">
						<c:if test="${af.map.request_from eq 'zmd'}">专卖店客户 <html-el:hidden property="customer_type" value="49" /></c:if>
						<c:if test="${af.map.request_from ne 'zmd'}">
							<html-el:select property="customer_type" styleId="customer_type" disabled="true">
								<html-el:option value="">请选择...</html-el:option>
									<c:forEach var="cur" items="${konkaCategoryList}">
									<html-el:option value="${cur.c_index}">[${cur.c_comm}]${cur.c_name}</html-el:option>
									</c:forEach>
							</html-el:select>
						</c:if>
						</td>
					</tr>
					<tr>
			         <td nowrap="nowrap" class="title_item" align="right">R/3删除：</td>
			         <td>
			         	<c:if test="${af.map.is_loevm eq 1}">
			              <c:out value="已删除"/>
			            </c:if>
			            <c:if test="${af.map.is_loevm eq 0}">
			              <c:out value="未删除"/>
			            </c:if>
			         </td>
			         <td nowrap="nowrap" class="title_item" align="right">R/3销售冻结：</td>
			         <td>
			         	<c:if test="${af.map.is_cassd eq 1}">
			              <c:out value="已冻结"/>
			            </c:if>
			            <c:if test="${af.map.is_cassd eq 0}">
			              <c:out  value="未冻结" />
			            </c:if>
			         </td>
			        </tr>
					<c:if test="${role_id_lt_10_gt_30 eq 1}">
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">允许负卖：</td>
						<td align="left">
							<html-el:hidden property="is_minus_sales_old" styleId="is_minus_sales_old" value="${af.map.is_minus_sales }" />
							<html-el:select property="is_minus_sales" styleId="is_minus_sales" style="width:100px">
								<html-el:option value="0">允许</html-el:option>
								<html-el:option value="1">不允许</html-el:option>
							</html-el:select></td>	
						<td nowrap="nowrap" class="title_item" align="right">触网类型 ：</td>
						<td align="left">
							<html-el:hidden property="web_type_old" styleId="web_type_old" value="${af.map.web_type }" />
							<html-el:select property="web_type" styleId="web_type" style="width:100px">
								<html-el:option value="">请选择...</html-el:option>
								<html-el:option value="1">触网1</html-el:option>
								<html-el:option value="2">触网2</html-el:option>
							 </html-el:select></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">允许盘存：</td>
						<td align="left">
							<html-el:hidden property="is_inventory_old" styleId="is_inventory_old" value="${af.map.is_inventory }" />
							<html-el:select property="is_inventory" styleId="is_inventory" style="width:100px">
								<html-el:option value="0">允许</html-el:option>
								<html-el:option value="1">不允许</html-el:option>
							 </html-el:select>
						</td>	
						<td nowrap="nowrap" class="title_item" align="right">加盟时间：</td>
						<td align="left"><fmt:formatDate value="${af.map.add_date}" var="_add_date" pattern="yyyy-MM-dd" />
							<html-el:text property="add_date" styleId="add_date" size="10" maxlength="10" value="${_add_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
					</tr>
					</c:if>	
					<c:if test="${role_id_lt_10_gt_30 ne 1}">
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">允许负卖：</td>
						<td align="left">
							<c:if test="${af.map.is_minus_sales eq 0}">允许</c:if>
							<c:if test="${af.map.is_minus_sales eq 1}">不允许</c:if>
						</td>	
						<td nowrap="nowrap" class="title_item" align="right">触网类型 ：</td>
						<td align="left">
						   <c:if test="${af.map.web_type eq 1}">触网1</c:if>
							<c:if test="${af.map.web_type eq 2}">触网2</c:if>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">允许盘存：</td>
						<td align="left">
							<c:choose>
								<c:when test="${allow_pd eq 0 }">
									<html-el:hidden property="is_inventory_old" styleId="is_inventory_old" value="${af.map.is_inventory }" />
									<html-el:select property="is_inventory" styleId="is_inventory" style="width:100px">
										<html-el:option value="0">允许</html-el:option>
										<html-el:option value="1">不允许</html-el:option>
									 </html-el:select>
								</c:when>
								<c:otherwise>
									<c:if test="${af.map.is_inventory eq 0}">允许</c:if>
									<c:if test="${af.map.is_inventory eq 1}">不允许</c:if>
								</c:otherwise>
							</c:choose>
						</td>	
						<td nowrap="nowrap" class="title_item" align="right">加盟时间：</td>
						<td align="left"><fmt:formatDate value="${af.map.add_date}" var="_add_date" pattern="yyyy-MM-dd" />
							<html-el:text property="add_date" styleId="add_date" size="10" maxlength="10" value="${_add_date}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
						</td>
					</tr>
					</c:if>							 
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">当月结算额：</td>
						<td align="left"><c:if test="${not empty af.map.map.stat.map.cur_cls_money}"><fmt:formatNumber value="${af.map.map.stat.map.cur_cls_money}" pattern="#,##0.00"/>&nbsp;万元</c:if></td>
						<td nowrap="nowrap" class="title_item" align="right">去年同期：</td>
						<td align="left"><c:if test="${not empty af.map.map.stat.map.lastyear_cls_money}"><fmt:formatNumber value="${af.map.map.stat.map.lastyear_cls_money}" pattern="#,##0.00"/>&nbsp;万元</c:if></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">当月回款额：</td>
						<td align="left"><c:if test="${not empty af.map.map.stat.map.cur_back_money}"><fmt:formatNumber value="${af.map.map.stat.map.cur_back_money}" pattern="#,##0.00"/>&nbsp;万元</c:if></td>
						<td nowrap="nowrap" class="title_item" align="right">去年同期：</td>
						<td align="left"><c:if test="${not empty af.map.map.stat.map.lastyear_back_money}"><fmt:formatNumber value="${af.map.map.stat.map.lastyear_back_money}" pattern="#,##0.00"/>&nbsp;万元</c:if></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">备注：</td>
						<td align="left" colspan="3"><html-el:textarea property="r3_desc" rows="5" cols="60" styleId="r3_desc" ></html-el:textarea></td>
					</tr>
				</table>
			</div>
			<div id="tab2" style="padding-left:0px;overflow-x:auto;overflow-y:hidden;padding-bottom: 30px">
				<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">企业类型：</td>
						<td align="left">
							<html-el:select property="entp_type" styleId="entp_type" style="width:100px">
								<html-el:option value="">请选择...</html-el:option>
								<c:forEach items="${entpTypeList}" var="cur">
									<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
								</c:forEach>
							</html-el:select>
						</td>
						<td nowrap="nowrap" class="title_item" align="right">注册资金：</td>
						<td align="left"><html-el:text property="entp_reg_money" styleId="entp_reg_money" size="18" maxlength="10" onfocus="javascript:setOnlyNum(this);" />&nbsp;万元</td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">客户规模（年销售额）：</td>
						<td align="left">
							<html-el:select property="entp_scale" styleId="entp_scale" style="width:100px">
								<html-el:option value="">请选择...</html-el:option>
								<c:forEach items="${entpScaleList}" var="cur">
									<html-el:option value="${cur.c_index}">${cur.c_name}</html-el:option>
								</c:forEach>
							</html-el:select>
						</td>
						<td nowrap="nowrap" class="title_item" align="right">员工总数：</td>
						<td align="left">
							<html-el:select property="entp_man_count" styleId="entp_man_count" style="width:100px">
								<html-el:option value="">请选择...</html-el:option>
								<html-el:option value="1">1000以上</html-el:option>
								<html-el:option value="2">500~1000以下</html-el:option>
								<html-el:option value="3">100~500以下</html-el:option>
								<html-el:option value="4">100以下</html-el:option>
							</html-el:select>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">公司电话：</td>
						<td align="left"><html-el:text property="entp_tel" styleId="entp_tel" size="40" maxlength="40" />&nbsp;<span style="color:red;">多个用逗号隔开</span></td>
						<td nowrap="nowrap" class="title_item" align="right">公司传真：</td>
						<td align="left"><html-el:text property="entp_fax" styleId="entp_fax" size="40" maxlength="40" />&nbsp;<span style="color:red;">多个用逗号隔开</span></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">成立时间：</td>
						<td align="left"><fmt:formatDate value="${af.map.entp_birthday}" var="_entp_birthday" pattern="yyyy-MM-dd" />
							<html-el:text property="entp_birthday" styleId="entp_birthday" size="10" maxlength="10" value="${_entp_birthday}" readonly="true" onclick="new Calendar(1900, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
						<td nowrap="nowrap" class="title_item" align="right">邮编：</td>
						<td align="left"><html-el:text property="entp_post" styleId="entp_post" size="18" maxlength="20" /></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right"><font color="red">* </font>所在城市：</td>
						<td align="left" colspan="3">
							<select name="province" id="province" style="width:180px;">
			                  <option value="">-请选择省/直辖市/自治区-</option>
			                </select>
			                &nbsp;
			                <select name="city" id="city" style="width:100px;">
			                  <option value="">-请选择市-</option>
			                </select>
			                &nbsp;
			                <select name="country" id="country" style="width:100px;">
			                  <option value="">-请选择县-</option>
			                </select>
			                &nbsp;
			                <select name="town" id="town" style="width:100px;">
			                  <option value="">-请选择乡镇-</option>
			                </select>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">市场级别：</td>
						<td align="left" colspan="3">
							<html-el:select property="entp_p_level" styleId="entp_p_level" style="width:100px">
								<html-el:option value="">请选择...</html-el:option>
								<html-el:option value="1">一线城市</html-el:option>
								<html-el:option value="2">二线城市</html-el:option>
								<html-el:option value="3">三线城市</html-el:option>
								<html-el:option value="4">四线城市</html-el:option>
							</html-el:select>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">公司地址：</td>
						<td align="left" colspan="3"><html-el:text property="entp_addr" styleId="entp_addr" size="60" maxlength="40" /></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">公司网址：</td>
						<td align="left" colspan="3"><html-el:text property="entp_www" styleId="entp_www" size="60" maxlength="40" /></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">主营产品：</td>
						<td align="left" colspan="3"><html-el:text property="entp_main_pds" styleId="entp_main_pds" size="60" maxlength="40" />&nbsp;<span style="color:red;">多个用逗号隔开</span></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">销售区域：</td>
						<td align="left" colspan="3"><html-el:text property="entp_sale_area" styleId="entp_sale_area" size="60" maxlength="40" />&nbsp;<span style="color:red;">多个用逗号隔开</span></td>
					</tr>
					<tr>
						<td nowrap="nowrap" class="title_item" align="right">客户简介：</td>
						<td align="left" colspan="3"><html-el:textarea property="entp_inro" styleId="entp_inro" rows="5" style="width:400px;"></html-el:textarea></td>
					</tr>
					<tr>
			            <td nowrap="nowrap" class="title_item" align="right">上传附件：</td>
			            <td colspan="3"><div id="divFileHidden" style="display: none;">
			                <input name="file_hidden" type="file" id="file_hidden" style="width: 500px;" />
			                <img src="../../images/x.gif" style="vertical-align:middle; cursor: pointer;" id="imgDelTr" title="删除"/></div>
			              <div id="divFile">
			                <input name="file_show" type="file" id="file_show" style="width: 500px;" />
			                <img src="../../images/+.gif" style="vertical-align:middle; cursor: pointer;" id="imgAddTr" title="再添加一个" /></div>
			            </td>
			          </tr>
			          <c:if test="${not empty attachmentList}">
			          <tr>
			            <td height="28" class="title_item" align="right">已上传的附件：</td>
			            <td colspan="3"><ol>
			                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
			                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;<a href="#" id="att_del_${cur.id}">删除</a></li>
			                </c:forEach>
			              </ol></td>
			            </tr>
			           </c:if>
					<tr>
						<th colspan="4" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>品牌销售额</span></th>
					</tr>
					<tr>
						<td colspan="4" align="left">
							<table width="100%" border="0">
								<tr align="center">
									<td width="10%">品牌名称</td>
									<td width="10%">年份</td>
									<td width="10%">年销额(保留两位小数)</td>
									<td width="10%">排名</td>
									<td width="2%" align="center" nowrap="nowrap" id="addBrandTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" name="imgAddTr" style="vertical-align:middle;" /></td>
								</tr>
								<c:forEach items="${konkaR3ShopBrandList}" var="curbl">
									<tr align="center">
										<td width="10%">
											<html-el:select property="brand_id" value="${curbl.brand_id}" styleId="brand_id" name="brand_id">
				         						<html-el:option value="">-请选择-</html-el:option>
				         						<c:forEach items="${BrandList}" var="curb1">
				         							<html-el:option value="${curb1.brand_id}">${curb1.brand_name}</html-el:option>
				         						</c:forEach>
				        					</html-el:select>
										</td>
										<td width="5%">
											<html-el:select property="sale_year" styleId="sale_year" value="${curbl.sale_year}">
				         						<html-el:option value="">-请选择-</html-el:option>
				         						<c:forEach items="${yearList}" var="cury1">
				         							<html-el:option value="${cury1}">${cury1}年</html-el:option>
				         						</c:forEach>
				        					</html-el:select>
										</td>
										<td width="5%">
											<input type="text" name="annual_salse" size="13" maxlength="20" value="${curbl.annual_salse }" style="text-align: right"/>元
										</td>
										<td width="5%">
											<html-el:select property="sale_rank" styleId="sale_year" value="${curbl.ranks}" name="sale_rank">
				         						<html-el:option value="">-请选择-</html-el:option>
				         						<c:forEach items="${rankList}" var="curr1">
				         							<html-el:option value="${curr1.val}">${curr1.name}</html-el:option>
				         						</c:forEach>
				        					</html-el:select>
										</td>
										<td width="2%" align="center" class="td_del"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
									</tr>
								</c:forEach>
								<tr id="divBrandTr" style="display: none" align="center">
									<td width="5%">
										<html-el:select property="brand_id" styleId="brand_id">
				         					<html-el:option value="">-请选择-</html-el:option>
				         					<c:forEach items="${BrandList}" var="curb2">
				         						<html-el:option value="${curb2.brand_id}">${curb2.brand_name}</html-el:option>
				         					</c:forEach>
				        				</html-el:select>
									</td>
									<td width="5%">
										<html-el:select property="sale_year" styleId="sale_year">
				         					<html-el:option value="">-请选择-</html-el:option>
				         					<c:forEach items="${yearList}" var="cury2">
				         						<html-el:option value="${cury2}">${cury2}年</html-el:option>
				         					</c:forEach>
				        				</html-el:select>
									</td>
									<td width="5%">
										<input type="text" name="annual_salse" size="13" maxlength="20" />元
									</td>
									<td width="5%">
										<select name="sale_rank" style="width:75px">
											<option value="">-请选择-</option>
											<option value="1" >第1名</option>
											<option value="2" >第2名</option>
											<option value="3" >第3名</option>
											<option value="4" >第4名</option>
											<option value="5" >第5名</option>
											<option value="6" >第6名</option>
											<option value="7" >第7名</option>
											<option value="8" >第8名</option>
											<option value="9" >第9名</option>
											<option value="10" >第10名</option>
											<option value="11" >10名以上</option>
										</select>
									</td>
									<td width="2%" align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
								</tr>
								<tbody id="showAddTrsTbody1"></tbody>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div id="tab3" style="padding-left:0px;overflow-x:auto;overflow-y:hidden;padding-bottom: 30px">
				<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td colspan="4" align="center">
							<table width="1250px" border="0">
								<tr align="center">
									<td width="65px">职务</td>
									<td width="65px">姓名</td>
									<td width="65px">岗位</td>
									<td width="65px">性别</td>
									<td width="80px">出生日期</td>
									<td width="120px">固定电话</td>
									<td width="100px">移动电话</td>
									<td width="120px">传真</td>
									<td width="130px">电子邮箱</td>
									<td width="120px">微信号</td>
									<td width="120px">QQ号</td>
									<td width="60px">是否默认</td>
									<td width="60px">是否有效</td>
									<td align="center" nowrap="nowrap" id="addLinkTD" style="cursor:pointer;"><img src="${ctx}/images/+.gif" name="imgAddTr" style="vertical-align:middle;" /></td>
								</tr>
								<c:forEach items="${konkaR3ShopLinkList}" var="cur">
									<tr align="center">
										<td>
											<select name="r3_link_position">
												<option value="" >——</option>
												<option value="1" ${cur.position eq '1' ? "selected='selected'":""}>付款</option>
												<option value="2" ${cur.position eq '2' ? "selected='selected'":""}>对账</option>
												<option value="3" ${cur.position eq '3' ? "selected='selected'":""}>业务</option>
												<option value="4" ${cur.position eq '4' ? "selected='selected'":""}>法人</option>
												<option value="5" ${cur.position eq '5' ? "selected='selected'":""}>售后</option>
												<option value="6" ${cur.position eq '6' ? "selected='selected'":""}>收货</option>
												<option value="7" ${cur.position eq '7' ? "selected='selected'":""}>送货</option>
												<option value="8" ${cur.position eq '8' ? "selected='selected'":""}>发票</option>
												<option value="9" ${cur.position eq '9' ? "selected='selected'":""}>其他</option>
											</select>
										</td>
										<td><input type="text" name="r3_link_real_name" size="3" maxlength="20" value="${cur.real_name}" /></td>
										<td><input type="text" name="r3_link_job" size="4" maxlength="20" value="${cur.job}" /></td>
										<td>
											<select name="r3_link_sex">
												<option value="">——</option>
												<option value="0" ${cur.sex eq '0' ? "selected='selected'":""}>男</option>
												<option value="1" ${cur.sex eq '1' ? "selected='selected'":""}>女</option>
												<option value="2" ${cur.sex eq '2' ? "selected='selected'":""}>未知</option>
											</select>
										</td>
										<fmt:formatDate var="_birthday" pattern="yyyy-MM-dd" value="${cur.birthday}" />
										<%-- <td><input type="text" name="r3_link_birthday" size="6" maxlength="10" value="${_birthday}" readonly="readonly" onclick="new Calendar(1900, 2021, 0).show(this);" /></td> --%>
										<td><input name="r3_link_birthday" size="12" value="${_birthday}" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'1900-01-01'})" /></td>
										<td><input type="text" name="r3_link_phone" size="8" maxlength="40" value="${cur.telephone}" class='phonecheck'/></td>
										<td><input type="text" name="r3_link_tel" size="12" maxlength="40" value="${cur.tel}" class='telephonecheck'/></td>
										<td><input type="text" name="r3_link_fax" size="12" maxlength="40" value="${cur.fax}" class='faxcheck'/></td>
										<td><input type="text" name="r3_link_email" size="15" maxlength="40" value="${cur.email}" class='emailcheck'/></td>
										<td><input type="text" name="r3_link_weixin" size="12" maxlength="40" value="${cur.weixin}" /></td>
										<td><input type="text" name="r3_link_qq" size="12" maxlength="40" value="${cur.qq}" /></td>
										<td>
											<select name="r3_is_default" >
												<option value="0" ${cur.is_default eq '0' ? "selected='selected'":""}>是</option>
												<option value="1" ${cur.is_default eq '1' ? "selected='selected'":""}>否</option>
											</select>
										</td>
										<td>
											<select name="r3_is_valid" >
												<option value="0" ${cur.is_valid eq '0' ? "selected='selected'":""}>是</option>
												<option value="1" ${cur.is_valid eq '1' ? "selected='selected'":""}>否</option>
											</select>
										</td>
										<td align="center" class="td_del"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
									</tr>
								</c:forEach>
								<tr id="divTr" style="display: none" align="center">
									<td>
										<html-el:select property="r3_link_position">
											<html-el:option value="">——</html-el:option>
											<html-el:option value="1">付款</html-el:option>
											<html-el:option value="2">对账</html-el:option>
											<html-el:option value="3">业务</html-el:option>
											<html-el:option value="4">法人</html-el:option>
											<html-el:option value="5">售后</html-el:option>
											<html-el:option value="6">收货</html-el:option>
											<html-el:option value="7">送货</html-el:option>
											<html-el:option value="8">发票</html-el:option>
											<html-el:option value="9">其他</html-el:option>
										</html-el:select>
									</td>
									<td><input type="text" name="r3_link_real_name" size="3" maxlength="20" /></td>
									<td><input type="text" name="r3_link_job" size="4" maxlength="20" /></td>
									<td>
										<select name="r3_link_sex">
											<option value="">——</option>
											<option value="0">男</option>
											<option value="1">女</option>
											<option value="2">未知</option>
										</select>
									</td>
									<!-- <td><input type="text" name="r3_link_birthday" size="6" maxlength="10" readonly="readonly" onclick="new Calendar(1900, 2021, 0).show(this);" /></td> -->
									<td><input name="r3_link_birthday" size="12" readonly="readonly" class="Wdate" type="text" onclick="WdatePicker({readOnly:true,minDate:'1900-01-01'})" /></td>
									<td><input type="text" name="r3_link_phone" size="8" maxlength="40" class='phonecheck'/></td>
									<td><input type="text" name="r3_link_tel" size="12" maxlength="40" class='telephonecheck'/></td>
									<td><input type="text" name="r3_link_fax" size="12" maxlength="40" class='faxcheck'/></td>
									<td><input type="text" name="r3_link_email" size="15" maxlength="40" class='emailcheck'/></td>
									<td><input type="text" name="r3_link_weixin" size="12" maxlength="100" /></td>
									<td><input type="text" name="r3_link_qq" size="12" maxlength="100" /></td>
									<td>
										<select name="r3_is_default">
											<option value="0">是</option>
											<option value="1" selected="selected">否</option>
										</select>
									</td>
									<td>
										<select name="r3_is_valid">
											<option value="0">是</option>
											<option value="1">否</option>
										</select>
									</td>
									<td align="center"><img src="${ctx}/images/x.gif" style="vertical-align:middle;top:0px;" /></td>
								</tr>
								<tbody id="showAddTrsTbody"></tbody>
							</table>
						</td>
					</tr>
					</table>
				</div>
			</div>
		</html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<%-- <script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> --%>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
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
//验证手机号
$(".telephonecheck").blur(function(){
	var val = $(this).val();
	var re = /^1\d{10}$/;
	if(val!=''){
	    if (!re.test(val)) {
			alert("请输入正确的移动电话号码！");
	    }
	}
});

//验证固定电话
$(".phonecheck").blur(function(){
	var val = $(this).val();
	var re = /^[\d]{11,12}$/;
	if(val!=''){
		if (!re.test(val)) {
			alert("请输入正确的固定电话号码！");
	    }
	}
});

//验证传真
$(".faxcheck").blur(function(){
	var val = $(this).val();
	var re = /^[\d]{11,12}$/;
	if(val!=''){
		if (!re.test(val)) {
			alert("请输入正确的传真！");
	    }
	}
});

//验证邮箱
$(".emailcheck").blur(function(){
	var val = $(this).val();
	var re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	if(val!=''){
		if (!re.test(val)) {
			alert("请输入正确的邮箱地址！");
	    }
	}
});
                                          
$(document).ready(function(){
	
	
	 $("#imgAddTr").click(function (){
	        $("#divFileHidden").clone().find("#file_hidden").attr("name", "file_" + new Date().getTime()).end().appendTo($("#divFile")).show();
	        resizeFrameHeight();
	        $("img[id='imgDelTr']").each(function(){
	            $(this).click(function (){
	                $(this).parent().remove();
	                resizeFrameHeight();
	            });
	        });
  });

	 $("a[id^='att_del_']").click(function() {
	  	  var a = this;
	  	   if(!confirm('确实要删除此附件？')) return;
	  	    $.post("KonkaR3MmtMatch.do", { method : "deleteFile", id : $(this).attr("id").replace(/att_del_/g, '')}, function(success) {
	  	   if (success){alert("恭喜您，删除附件成功!");$(a).parent().remove();} else alert(" 很抱歉，删除附件出错!"); 
	  	  });
	   }); 
	
	$("#area_name").attr("dataType", "Require").attr("msg", "请选择所在区域");
	$("#branch_area_name_2").attr("dataType", "Require").attr("msg", "请选择分公司");
	$("#customer_type").attr("dataType", "Require").attr("msg", "请选择客户类型");
	$("#branch_name").attr("dataType", "Require").attr("msg", "请选择事业部");
	$("#shop_status").attr("dataType", "Require").attr("msg", "请选择客户状态");
	$("#r3_code").attr("dataType", "Require").attr("msg", "请填写R3编码");
	$("#country").attr("dataType", "Require").attr("msg", "请选择完整所在城市信息");
	$("#customer_name").attr("dataType", "Require").attr("msg", "请填写客户名称");
	$("#customer_code").attr("dataType", "Require").attr("msg", "请填写合并编码");
	

	$("#entp_inro").attr("dataType", "LimitB").attr("min","0").attr("max","80").attr("msg", "最多只能填写40个汉字！");

	$("#branch_area_name_2").change(function(){
		var dept_name = $(this).find("option:selected").text();
		$("#branch_area_name").val(dept_name);
	});
	
	$("#btn_submit").click(function(){
		var count = 0;
		var nums = 0;
		//联系人中，是否默认有且仅有一个为“是”
		$("*[name='r3_is_default']").each(function(){
			if($(this).val()=='0'){
				count++;
			}
			nums++;
		});
		if(count==1 || nums==1){
			if(Validator.Validate(this.form, 2)){
	            $("#btn_submit").attr("value", "正在提交...").attr("disabled", "true");
	            $("#btn_reset").attr("disabled", "true");
	            $("#btn_back").attr("disabled", "true");
				this.form.submit();
			}
		}else if(count==0&&nums>1){
			alert("必须选择一个联系人为默认联系人！");
		}else{
			alert("默认联系人只能有一个！");
		}
	});
	
	$("#customer_type").change(function(){
		if ($(this).val() == '49') {
			$("#user_role_desc").html("（专卖店店长）");
		} else {
			$("#user_role_desc").html("");
		}
	});
	
	// 验证R3编码是否存在
	$("#r3_code").blur(function(){
		$("#btn_submit").attr("disabled", "disabled");
		var r3_code = $("#r3_code").val();
		if(null == $(this).val() || $(this).val() == ''){
			$("#r3_code_exist_error").hide();
			$(this).css("background-color", "#fff");
			return ;
		}
		if($(this).val().indexOf(' ')>-1){
			$("#r3_code_exist_error").hide();
			return;
		}

		$("#r3_code_exist_error").hide();
		$(this).css("background-color", "#fff");
		
		$.ajax({
			type: "POST",
			url: "KonkaR3Shop.do",
			data: "method=validateR3Code&now_id=${af.map.id}&r3_code=" + $(this).val(),
			dataType: "json",
			error: function(request, settings) {
				alert("检查失败，请稍候再次尝试。");
				$("#r3_code_exist_error").show();
				$(this).css("background-color", "#ddcc00");
				$(this).focus();
			},
			success: function(oper) {
				if (oper.result) {
					$("#r3_code_exist_error").show();
					$("#btn_submit").attr("disabled", "disabled");
					$("#r3_code").css("background-color", "#ddcc00");
					$("#r3_code").focus();
				} else {
					$("#r3_code_exist_error").hide();
					$("#r3_code").css("background-color", "#fff");
					$("#btn_submit").removeAttr("disabled");
				}
			}
		});
	});	

	//添加联系人输入行
	$("#addLinkTD").click(function(){
		var tr_pd = $("#divTr").clone(true).attr("class","tr_pd");
		//tr_pd.children().eq(7).attr("class","td_del");
		tr_pd.children().eq(7).children().eq(0).attr("src","${ctx}/images/x.gif");
		tr_pd.appendTo($("#showAddTrsTbody")).show();
		
		var lastTR = $("tr:last", "#showAddTrsTbody");

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
	    }).css("cursor", "pointer");
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});
	
	//添加品牌销售输入行
	$("#addBrandTD").click(function(){
		var tr_pd = $("#divBrandTr").clone(true).attr("class","tr_pd");
		tr_pd.children().eq(7).children().eq(0).attr("src","${ctx}/images/x.gif");
		tr_pd.appendTo($("#showAddTrsTbody1")).show();
		
		var lastTR = $("tr:last", "#showAddTrsTbody1");

		$("td:last", lastTR).click(function (){
			$(this).parent().remove();
	    }).css("cursor", "pointer");
		//iframe高度自适应
		window.parent.resizeFrameHeight('mainFrame', 3);
	});

	$(".td_del").click(function(){
		$(this).parent().remove();	
	});

	//所在地市联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${province}"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${city}"});
	$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${country}"});
	$("#town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${town}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
	
});

function resizeFrameHeight(offset, min_height) {
	// frame id can be found in page /konka-wd/WebContent/manager/admin/Frames2/indexFrame.jsp, and search 'iframe' to get.
	$("#mainFrame", window.parent.document).height(Math.max((min_height || 0), $(document).find("body").height(), $(document).children().height()) + (offset || 0));
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
