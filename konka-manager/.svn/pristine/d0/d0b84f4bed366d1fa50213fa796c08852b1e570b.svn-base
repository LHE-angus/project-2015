<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<body>
    <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
      <thead>
        <tr>
          <td>序号</td>
          <td>分公司</td>
          <td>R3编码</td>
          <td>客户名称</td>
          <td>客户类型</td>
          <td>事业部</td>
          <td>客户状态</td>
          <td>区域信息</td>
          <td>合并编码</td>
          <td>业务员</td>
          <td>当月结算</td>
          <td>去年同期</td>
          <td>当月回款</td>
          <td>去年同期</td>
          <td>帐期</td>
          <td>R/3删除</td>
          <td>R/3销售冻结</td>
          <td>加盟时间</td>
          <td>更新时间</td>
          <td>维护人</td>
          <td>省</td>
          <td>市</td>
          <td>县</td>
          <td>镇</td>
          <td>公司地址</td>
          <td>客户端账号</td>
          <c:forEach var="i" begin="1" end="${MaxNum}" varStatus="vs">
          	<td>职务${i}</td>
          	<td>姓名${i}</td>
          	<td>岗位${i}</td>
          	<td>性别${i}</td>
          	<td>固定${i}</td>
          	<td>移动电话${i}</td>
          	<td>传真${i}</td>
          	<td>电子邮箱${i}</td>
          	<td>微信号${i}</td>
          	<td>QQ号${i}</td>
          	<td>是否默认${i}</td>
          	<td>是否有效${i}</td>
          </c:forEach>
        </tr>
      </thead>
        <c:forEach var="cur" items="${entityList1}" varStatus="vs">
          <tr>
            <td>${vs.count}</td>
            <td>${cur.branch_area_name}</td>
            <td>${fn:escapeXml(cur.r3_code)}</td>
            <td>${cur.customer_name}</td>
            <td>${fn:escapeXml(cur.map.c_name)}
            	<c:if test="${empty cur.map.c_name}">
            		<span style="color:#ccc;">未指定</span>
            	</c:if>
            </td>
            <td>
            	<c:if test="${cur.branch_name eq 1}">
        			白电
            	</c:if>
            	<c:if test="${cur.branch_name eq 2}">
            		多媒体
            	</c:if>
            </td>
            <td>
              	<c:if test="${cur.shop_status eq 1}">
              		新客户
              	</c:if>
              	<c:if test="${cur.shop_status eq 2}">
              		正式客户
              	</c:if>
              	<c:if test="${cur.shop_status eq 3}">
              		静止客户
              	</c:if>
              	<c:if test="${cur.shop_status eq 4}">
              		无效客户
              	</c:if>
              	<c:if test="${cur.shop_status eq 5}">
              		门店网点
              	</c:if>
              	<c:if test="${empty cur.shop_status}"><span style="color:#ccc;">未指定</span></c:if>
            </td>
            <td>${fn:escapeXml(cur.area_name)}-${fn:escapeXml(cur.branch_area_name)}</td>
            <td>${fn:escapeXml(cur.customer_code)}</td>
            <td>${fn:escapeXml(cur.map.user_name)}</td>
            <td><fmt:formatNumber value="${cur.map.stat.map.cur_cls_money}" pattern="#,##0.00"/></td>
            <td><fmt:formatNumber value="${cur.map.stat.map.lastyear_cls_money}" pattern="#,##0.00"/></td>
            <td><fmt:formatNumber value="${cur.map.stat.map.cur_back_money}" pattern="#,##0.00"/></td>
            <td><fmt:formatNumber value="${cur.map.stat.map.lastyear_back_money}" pattern="#,##0.00"/></td>
            <td>${fn:escapeXml(cur.map.stat.map.credit_balance)}</td>
            <td>
            	<c:if test="${cur.is_loevm eq 1}">
              		已删除
            	</c:if>
            	<c:if test="${cur.is_loevm eq 0}">
              		未删除
            	</c:if>
            </td>
            <td>
            	<c:if test="${cur.is_cassd eq 1}">
              		已冻结
            	</c:if>
            	<c:if test="${cur.is_cassd eq 0}">
              		未冻结
            	</c:if>
            </td>
            <td><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd" /></td>
            <td><fmt:formatDate value="${cur.create_date}" pattern="yyyy-MM-dd" /></td>
            <td>${cur.add_name}</td>
            <td>${cur.map.province}</td>
            <td>${cur.map.city}</td>
            <td>${cur.map.country}</td>
            <td>${cur.map.town}</td>
	        <td>${cur.entp_addr }</td>
	        <td>${cur.map.account_list }</td>
	        <c:if test="${ not empty cur.map.linkList}">
		        <c:forEach var="link" items="${cur.map.linkList}">
		          	<td>
		          		<c:if test="${link.position eq 1}">
	        				付款
		            	</c:if>
		            	<c:if test="${link.position eq 2}">
		            		对账
		            	</c:if>
		            	<c:if test="${link.position eq 3}">
		            		业务
		            	</c:if>
		            	<c:if test="${link.position eq 4}">
		            		法人
		            	</c:if>
		            	<c:if test="${link.position eq 5}">
		            		售后
		            	</c:if>
		            	<c:if test="${link.position eq 6}">
		            		收货
		            	</c:if>
		            	<c:if test="${link.position eq 7}">
		            		送货
		            	</c:if>
		            	<c:if test="${link.position eq 8}">
		            		发票
		            	</c:if>
		            	<c:if test="${link.position eq 9}">
		            		其他
		            	</c:if>
		          	</td>
		          	<td>${link.real_name}</td>
		          	<td>${link.job}</td>
		          	<td>
		          		<c:if test="${link.sex eq 0}">
	              			男
		              	</c:if>
		              	<c:if test="${link.sex eq 1}">
		              		女
		              	</c:if>
		          	</td>
		          	<td>${link.telephone }</td>
		          	<td>${link.tel}</td>
		          	<td>${link.fax}</td>
		          	<td>${link.email}</td>
		          	<td>${link.weixin}</td>
		          	<td>${link.qq}</td>
		          	<td>${link.is_default eq 0 ? "是":(link.is_default eq 1 ? "否":"")}</td>
		          	<td>${link.is_valid eq 0 ? "是":(link.is_valid eq 1 ? "否":"")}</td>
		        </c:forEach>
		    </c:if>
		    <c:if test="${ empty cur.map.linkList}">
		      <c:forEach var="i" begin="1" end="${MaxNum}" varStatus="vs">
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          	<td></td>
	          </c:forEach>
		    </c:if>  
          </tr>
        </c:forEach>
    </table>
</body>
</html>