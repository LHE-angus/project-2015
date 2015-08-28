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
  <div class="rtabcont2">
    <html-el:form action="/admin/KonkaR3MmtMatch.do" enctype="multipart/form-data">
      <html-el:hidden property="method" value="Match"/>
      <html-el:hidden property="id" styleId="id"/>
      <html-el:hidden property="mod_id" styleId="mod_id"/>
      <div style="float:right;">
      	<html-el:button property="" value="返 回" style="display: ${not_merge_back };padding-left:25px;" styleClass="but5" styleId="btn_back" onclick="history.back();" />
      	<html-el:button property="" value="返 回" style="display: ${is_merge_back };padding-left:25px;" styleClass="but5" styleId="btn_back" onclick="location.href='${ctx}/manager/admin/R3UserCodeMerge/list.jsp?mod_id=-101070'" />
      </div>
      <ul id="tabs">
		<li><a href="#" name="tab1">常规信息</a></li>
		<li><a href="#" name="tab2">客户资料</a></li>
		<li><a href="#" name="tab3">联系信息</a></li>
		<li><a href="#" name="tab4">维护历史</a></li>
	   </ul>
		<div id="content">
			<div id="tab1">
				<table id="table" width="100%" border="0" cellspacing="0" cellpadding="0"></table>
      			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
			        <tr>
						<td width="20%"  height="30" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';" align="right"><span>上次维护时间：</span></td>
						<td width="40%" ><fmt:formatDate value="${af.map.create_date}" pattern="yyyy年MM月dd日" /></td>
						<td width="15%" height="30" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';" align="right"><span>上次维护人：</span></td>
						<td width="35%">${af.map.add_name}</td>
					</tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">客户名称：</td>
			          <td><c:out value="${af.map.customer_name}"/></td>
			          <td nowrap="nowrap" class="title_item" align="right">R3编码：</td>
			          <td><c:out value="${af.map.r3_code}"/></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">所在区域名称：</td>
			          <td><c:out value="${af.map.area_name}"/></td>
			          <td nowrap="nowrap" class="title_item" align="right">合并客户编码：</td>
			          <td><c:out value="${af.map.customer_code}"/></td>
			        </tr>
			        <tr>
			        	<td nowrap="nowrap" class="title_item" align="right">事业部：</td>
			        	<td>
			        		<c:if test="${af.map.branch_name eq 1}">
			        			白电
			            	</c:if>
			            	<c:if test="${af.map.branch_name eq 2}">
			            		多媒体
			            	</c:if>
			        	</td>
			          	<td nowrap="nowrap" class="title_item" align="right">客户状态：</td>
			          	<td>
			          		<c:if test="${af.map.shop_status eq 1}">
			              		新客户
			              	</c:if>
			              	<c:if test="${af.map.shop_status eq 2}">
			              		正式客户
			              	</c:if>
			              	<c:if test="${af.map.shop_status eq 3}">
			              		静止客户
			              	</c:if>
			              	<c:if test="${af.map.shop_status eq 4}">
			              		无效客户
			              	</c:if>
			              	<c:if test="${af.map.shop_status eq 5}">
			              		门店网点
			              	</c:if>
			          	</td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">分公司：</td>
			          <td><c:out value="${af.map.branch_area_name}"/></td>
			          <td nowrap="nowrap" class="title_item" align="right">R3分类：</td>
			          <td><c:if test="${af.map.is_sdf eq 1}">
			              <c:out value="送达方"/>
			            </c:if>
			            <c:if test="${af.map.is_sdf eq 0}">
			              <c:out value="售达方"/>
			            </c:if>
			          </td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">客户类型：</td>
			          <td>${customer_type_name} </td>
			          <td></td>
			          <td></td>
			          <td></td>
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
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">允许负卖：</td>
			          <td><c:if test="${af.map.is_minus_sales eq 0}">
			              <c:out value="允许"/>
			            </c:if>
			            <c:if test="${af.map.is_minus_sales eq 1}">
			              <c:out value="不允许"/>
			            </c:if>
			          </td>
			          <td nowrap="nowrap" class="title_item" align="right">触网类型：</td>
			          <td><c:if test="${af.map.web_type eq 1}">
			              <c:out value="触网1"/>
			            </c:if>
			            <c:if test="${af.map.web_type eq 2}">
			              <c:out value="触网2"/>
			            </c:if>
			          </td>
			        </tr>
			         <tr>
			          <td nowrap="nowrap" class="title_item" align="right">允许盘存：</td>
			          <td ><c:if test="${af.map.is_inventory eq 0}">
			              <c:out value="允许"/>
			            </c:if>
			            <c:if test="${af.map.is_inventory eq 1}">
			              <c:out value="不允许"/>
			            </c:if>
			          </td>
			          <td nowrap="nowrap" class="title_item" align="right">加盟时间：</td>
			          <td><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd" /></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">当月结算额：</td>
			          <td><c:if test="${not empty af.map.map.stat.map.cur_cls_money}"><fmt:formatNumber value="${af.map.map.stat.map.cur_cls_money}" pattern="#,##0.00"/>&nbsp;万元</c:if></td>
			          <td nowrap="nowrap" class="title_item" align="right">去年同期：</td>
			          <td><c:if test="${not empty af.map.map.stat.map.lastyear_cls_money}"><fmt:formatNumber value="${af.map.map.stat.map.lastyear_cls_money}" pattern="#,##0.00"/>&nbsp;万元</c:if></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">当月回款额：</td>
			          <td><c:if test="${not empty af.map.map.stat.map.cur_back_money}"><fmt:formatNumber value="${af.map.map.stat.map.cur_back_money}" pattern="#,##0.00"/>&nbsp;万元</c:if></td>
			          <td nowrap="nowrap" class="title_item" align="right">去年同期：</td>
			          <td><c:if test="${not empty af.map.map.stat.map.lastyear_back_money}"><fmt:formatNumber value="${af.map.map.stat.map.lastyear_back_money}" pattern="#,##0.00"/>&nbsp;万元</c:if></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">导入日期：</td>
			          <td><fmt:formatDate value="${af.map.import_date}" pattern="yyyy-MM-dd" /></td>
			          <td nowrap="nowrap" class="title_item" align="right">导入人姓名：</td>
			          <td>${af.map.map.import_user_name}</td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">备注：</td>
			          <td colspan="3"><c:out value="${af.map.r3_desc}"/></td>
			        </tr>
			  	</table>
			  </div>
			  <div id="tab2" style="padding-left:0px;overflow-x:auto;overflow-y:hidden;padding-bottom: 30px">
				<table class="rtable3" width="100%" border="0" cellspacing="1" cellpadding="0">
			        <tr>
			          <td width="20%" nowrap="nowrap" class="title_item" align="right">企业类型：</td>
			          <td width="40%">${entp_type_name}</td>
			          <td width="15%" nowrap="nowrap" class="title_item" align="right">注册资金：</td>
			          <td width="35%"><c:out value="${af.map.entp_reg_money}"/>
			            &nbsp;万元</td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">客户规模（年销售额）：</td>
			          <td>${entp_scale_name} </td>
			          <td nowrap="nowrap" class="title_item" align="right">员工总数：</td>
			          <td>${af.map.entp_man_count eq 1 ? "1000以上":(af.map.entp_man_count eq 2 ? "500~1000以下":(af.map.entp_man_count eq 3 ? "100~500以下":(af.map.entp_man_count eq 4 ? "100以下":""))) } </td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">公司电话：</td>
			          <td><c:out value="${af.map.entp_tel}"/></td>
			          <td nowrap="nowrap" class="title_item" align="right">公司传真：</td>
			          <td><c:out value="${af.map.entp_fax}"/></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">成立时间：</td>
			          <td><fmt:formatDate value="${af.map.entp_birthday}" pattern="yyyy-mm-dd" /></td>
			          <td nowrap="nowrap" class="title_item" align="right">邮编：</td>
			          <td><c:out value="${af.map.entp_post}"/></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">所在城市：</td>
			          <td>${province}&nbsp;${city}&nbsp;${country}&nbsp;${town} </td>
			          <td nowrap="nowrap" class="title_item" align="right">市场级别：</td>
			          <td>${af.map.entp_p_level eq 1 ? "一线城市":(af.map.entp_p_level eq 2 ? "二线城市":(af.map.entp_p_level eq 3 ?"三线城市":(af.map.entp_p_level eq 4 ? "四线城市":""))) } </td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">公司地址：</td>
			          <td colspan="3"><c:out value="${af.map.entp_addr}"/></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">公司网址：</td>
			          <td colspan="3"><c:out value="${af.map.entp_www}"/></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">主营产品：</td>
			          <td colspan="3"><c:out value="${af.map.entp_main_pds}"/></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">销售区域：</td>
			          <td colspan="3"><c:out value="${af.map.entp_sale_area}"/></td>
			        </tr>
			        <tr>
			          <td nowrap="nowrap" class="title_item" align="right">客户简介：</td>
			          <td colspan="3"><c:out value="${af.map.entp_inro}"/></td>
			        </tr>
			        <c:if test="${not empty attachmentList }">
			        <tr>
			            <td width="12%" nowrap="nowrap" class="title_item" align="right">已上传的附件：</td>
			            <td colspan="3"><ol>
			                <c:forEach var="cur" items="${attachmentList}" varStatus="vs">
			                  <li><a href="${ctx}/manager/admin/Download.do?method=downloadFile1&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a>&nbsp;&nbsp;&nbsp;</li>
			                </c:forEach>
			              </ol></td>
			      	</tr>
			      	</c:if>
			        <c:if test="${!is_not_match}">
			        <tr style="display: none">
			          <td width="10%" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>客户分配信息</span></td>
					  <td width="40%" colspan="3">&nbsp;</td>
			        </tr>
				     <tr style="display: none">
			          <td nowrap="nowrap" class="title_item" align="right">分配经营部：</td>
			          <td><c:out value="${jyb_name}"/></td>
			        </tr>
			        <tr style="display: none">
			          <td nowrap="nowrap" class="title_item" align="right">分配办事处：</td>
			          <td><c:out value="${bsc_name}"/></td>
			        </tr>
			        <tr style="display: none">
			          <td nowrap="nowrap" class="title_item" align="right">分配业务员：</td>
			          <td><c:out value="${ywy_name}"/></td>
			        </tr>
			        </c:if>
			        <tr style="display: none">
			          <th width="10%" height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';"><span>品牌销售额</span></th>
					  <th width="40%" colspan="3">&nbsp;</th>
			        </tr>
			        <c:if test="${not empty konkaR3ShopBrandList }">
			        <tr >
			          <td colspan="4"><table width="100%">
			              <tr align="center">
			                <td width="10%">品牌名称</td>
							<td width="10%">年份</td>
							<td width="10%">年销额</td>
							<td width="10%">排名</td>
			              </tr>
			              <c:forEach items="${konkaR3ShopBrandList}" var="curkrsb">
			                <tr align="center">
			                  <td width="3%">
			                  	${curkrsb.map.brand_name }
			                  </td>
			                  <td width="4%">${curkrsb.sale_year}年</td>
			                  <td width="4%">${curkrsb.annual_salse}元</td>
			                  <td width="3%">
			                  	<c:choose>  
			   						<c:when test="${curkrsb.ranks eq 11 }">10名以上</c:when>  
			   						<c:otherwise>第${curkrsb.ranks}名</c:otherwise>  
								</c:choose>
			                  </td>
			                </tr>
			              </c:forEach>
			            </table></td>
			        </tr>
			        </c:if>
			  	</table>
			</div>
			<div id="tab3" style="overflow-x:auto;overflow-y:auto;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
				  <tr class="tabtt1" align="center">
	                <td width="3%">职务</td>
					<td width="6%">姓名</td>
					<td width="6%">岗位</td>
					<td width="3%">性别</td>
					<td width="5%">出生日期</td>
					<td width="5%">移动电话</td>
					<td width="5%">固定电话</td>
					<td width="10%">传真</td>
					<td width="10%">电子邮箱</td>
					<td width="8%">微信号</td>
					<td width="7%">QQ号</td>
					<td width="4%">默认</td>
					<td width="4%">有效</td>
	              </tr>
	              <c:forEach items="${konkaR3ShopLinkList}" var="cur">
	                <tr align="center">
	                  <td width="3%">
	                  	<c:if test="${cur.position eq 1}">
	        				付款
		            	</c:if>
		            	<c:if test="${cur.position eq 2}">
		            		对账
		            	</c:if>
		            	<c:if test="${cur.position eq 3}">
		            		业务
		            	</c:if>
		            	<c:if test="${cur.position eq 4}">
		            		法人
		            	</c:if>
		            	<c:if test="${cur.position eq 5}">
		            		售后
		            	</c:if>
		            	<c:if test="${cur.position eq 6}">
		            		收货
		            	</c:if>
		            	<c:if test="${cur.position eq 7}">
		            		送货
		            	</c:if>
		            	<c:if test="${cur.position eq 8}">
		            		发票
		            	</c:if>
		            	<c:if test="${cur.position eq 9}">
		            		其他
		            	</c:if>
	                  </td>
	                  <td width="4%">${cur.real_name}</td>
	                  <td width="4%">${cur.job}</td>
	                  <td width="3%" align="center">${cur.sex eq 0 ? "男":(cur.sex eq 1 ? "女":(cur.sex eq 2 ? "未知":""))} </td>
	                  <td width="6%"><fmt:formatDate value="${cur.birthday }" pattern="yyyy-MM-dd" /></td>
	                  <td width="8%">${cur.telephone} </td>
	                  <td width="10%">${cur.tel} </td>
	                  <td width="10%">${cur.fax}</td>
	                  <td width="10%">${cur.email}</td>
	                  <td width="8%">${cur.weixin}</td>
	                  <td width="7%">${cur.qq}</td>
	                  <td width="4%" align="center">${cur.is_default eq 0 ? "是":(cur.is_default eq 1 ? "否":"")}</td>
	                  <td width="4%" align="center">${cur.is_valid eq 0 ? "是":(cur.is_valid eq 1 ? "否":"")}</td>
	                </tr>
	              </c:forEach>
	            </table>
		        <c:if test="${not empty mmt_shop_name}">
      			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
			          <tr>
			            <td colspan="4" bgcolor="#CCCCCC">&nbsp;&nbsp;匹配买卖提客户信息</td>
			          </tr>
			          <tr>
			            <td nowrap="nowrap" class="title_item" align="right">匹配买卖提客户名称：</td>
			            <td colspan="3"><c:out value="${mmt_shop_name}"/>
			            </td>
			          </tr>
			          <tr>
			            <td width="12%" nowrap="nowrap" class="title_item" align="right">地区邮编：</td>
			            <td colspan="3" width="88%" align="left"><c:out value="${entpShop.post_code}" /></td>
			          </tr>
			          <tr>
			            <td width="12%" nowrap="nowrap" class="title_item" align="right">所属地区：</td>
			            <td colspan="3" width="88%" align="left"><c:forEach var="cur" items="${baseProvinceListList}">${cur.p_name}</c:forEach></td>
			          </tr>
			          <tr>
			            <td width="12%" nowrap="nowrap" class="title_item" align="right">客户地址：</td>
			            <td colspan="3" width="88%" align="left"><c:out value="${entpShop.street_addr}" /></td>
			          </tr>
			          <tr>
			            <td width="12%" nowrap="nowrap" class="title_item" align="right">联系人：</td>
			            <td colspan="3" width="88%" align="left"><c:out value="${entpShop.link_user}" /></td>
			          </tr>
			          <tr>
			            <td width="12%" nowrap="nowrap" class="title_item" align="right">联系电话：</td>
			            <td colspan="3" width="88%" align="left"><c:out value="${entpShop.link_phone}" /></td>
			          </tr>
			          <tr>
			            <td width="12%" nowrap="nowrap" class="title_item" align="right">客户级别：</td>
			            <td colspan="3" width="88%" align="left"><c:choose>
			                <c:when test="${entpShop.shop_level eq 0}">普通客户</c:when>
			                <c:when test="${entpShop.shop_level eq 1}">铜牌客户</c:when>
			                <c:when test="${entpShop.shop_level eq 3}">金牌客户</c:when>
			              </c:choose></td>
			          </tr>
			  	</table>
	        </c:if>
			</div>
			<div id="tab4" style="overflow-x:auto;overflow-y:auto; height: 400px">
				<span>此历史记录仅查询停用、启用和修改部分客户信息相关记录</span>
				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
					<tr class="tabtt1">
			            <td nowrap="nowrap" align="center" width="5%">序号</td>
			            <td nowrap="nowrap" align="center" width="5%">类型</td>
			            <td nowrap="nowrap" >维护内容</td>
			            <td nowrap="nowrap" align="center" width="10%">维护时间</td>
						<td nowrap="nowrap" width="5%" align="center">维护人</td>
			            <td nowrap="nowrap" align="center" width="10%">确认时间</td>
			            <td nowrap="nowrap" width="5%" align="center">确认人</td>
			         </tr>
			         <c:forEach var="cur" items="${history}" varStatus="vs">
			          	<tr>
			          		<td nowrap="nowrap" align="center" width="5%">${vs.count }</td>
				            <td nowrap="nowrap" align="center" width="5%">
				            	<c:choose>
				            		<c:when test="${cur.oper_type eq 'stop' }">停用</c:when>
				            		<c:when test="${cur.oper_type eq 'start' }">启用</c:when>
				            		<c:when test="${cur.oper_type eq 'modify' }">修改</c:when>
				            	</c:choose>
				            </td>
				            <td nowrap="nowrap">${cur.oper_reason }</td>
				            <td nowrap="nowrap" align="center" width="15%"><fmt:formatDate value="${cur.create_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td nowrap="nowrap" align="center" width="10%">${cur.map.create_name }</td>
				            <td nowrap="nowrap" align="center" width="15%"><fmt:formatDate value="${cur.modify_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				            <td nowrap="nowrap" align="center" width="10%">${cur.map.modify_name }</td>
			          	</tr>
			          </c:forEach>
				</table>
			</div>
		</div>
    </html-el:form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>

<script type="text/javascript"><!--//<![CDATA[
	
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
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
