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
<link href="${ctx}/scripts/jquery-ui/themes/blitzer_zmd_rz/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
<style type="text/css"></style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <%@ include file="/commons/pages/messages.jsp"%>
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
      <tr>
        <td colspan="4" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;"><span style="color: red;">${af.map.user_name}</span>&nbsp;的客户资料</td>
      </tr>
      <tr>
        <td colspan="2" align="left" class="title_item">分公司：<span style="color: red;">${dept_name}</span></td>
        <td align="right" class="title_item">提交日期：</td>
        <td align="left" class="title_item"><fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td colspan="4" style="font-weight:900;"><strong class="fb">个人信息</strong></td>
      </tr>
      <tr>
        <td width="20%" align="right" class="title_item">姓 名：</td>
        <td width="20%">${af.map.user_name}</td>
        <td width="20%" align="right" class="title_item">性 别：</td>
        <td width="30%"><c:choose>
            <c:when test="${af.map.sex eq 0}">保密</c:when>
            <c:when test="${af.map.sex eq 1}">男</c:when>
            <c:when test="${af.map.sex eq 2}">女</c:when>
            <c:when test="${af.map.sex eq 3}">不详</c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td width="13%" align="right" class="title_item">出生年月：</td>
        <td width="14%"><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" /></td>
        <td align="right" class="title_item">教育背景：</td>
        <td>${af.map.edu_bg}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">婚姻状况：</td>
        <td><c:choose>
            <c:when test="${af.map.marriage eq 0}">未婚</c:when>
            <c:when test="${af.map.marriage eq 1}">已婚</c:when>
          </c:choose></td>
        <td align="right" class="title_item">准备投入的资金、资源：</td>
        <td>${af.map.resources}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">是否有自有门店：</td>
        <td><c:choose>
            <c:when test="${af.map.is_stores eq 0}">否</c:when>
            <c:when test="${af.map.is_stores eq 1}">是</c:when>
          </c:choose></td>
        <td align="right" class="title_item">自有门店地址：</td>
        <td>${af.map.stores_addr}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">上一个工作单位：</td>
        <td>${af.map.last_unit}</td>
        <td align="right" class="title_item">上一个工作职务：</td>
        <td>${af.map.last_post}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">从事销售行业年限：</td>
        <td>${af.map.sell_work_year}</td>
        <td align="right" class="title_item">彩电从业年限：</td>
        <td>${af.map.tv_wor_year}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">通讯地址：</td>
        <td>${af.map.com_addr}</td>
        <td align="right" class="title_item">邮政编码：</td>
        <td>${af.map.post_code}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">电子邮箱：</td>
        <td>${af.map.email}</td>
        <td align="right" class="title_item">联系电话：</td>
        <td>${af.map.tel}</td>
      </tr>
      <tr>
        <td colspan="4" style="font-weight:900;"><strong class="fb">门店信息</strong></td>
      </tr>
      <tr>
        <td align="right" class="title_item">工商注册时间：</td>
        <td><fmt:formatDate value="${af.map.reg_date}" pattern="yyyy-MM-dd"/></td>
        <td align="right" class="title_item">注册资本（万元）：</td>
        <td>${af.map.reg_money}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">营业执照经营范围：</td>
        <td>${af.map.business_scope}</td>
        <td align="right" class="title_item">营业执照注册号：</td>
        <td>${af.map.reg_num}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">目前正在经营品牌：</td>
        <td>${af.map.business_brand}</td>
        <td align="right" class="title_item">正在经营门店的地址：</td>
        <td>${af.map.ope_sto_addr}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">是否已有R3编码：</td>
        <td><c:choose>
            <c:when test="${af.map.is_r3 eq 0}">否</c:when>
            <c:when test="${af.map.is_r3 eq 1}">是</c:when>
          </c:choose></td>
        <td align="right" class="title_item">能否参加节能补贴：</td>
        <td><c:choose>
            <c:when test="${af.map.is_e_subsidy eq 0}">否</c:when>
            <c:when test="${af.map.is_e_subsidy eq 1}">是</c:when>
          </c:choose></td>
      </tr>
      <tr>
        <td align="right" class="title_item">意向门店地址：</td>
        <td colspan="3">${af.map.in_stores_addr}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">附件：</td>
        <td colspan="3">
          <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> <br />
              </span></c:forEach></td>
      </tr>
      <tr>
        <td align="right" class="title_item">个人目标与事业目标：</td>
        <td colspan="3">${af.map.target}</td>
      </tr>
      <tr>
        <td style="font-weight:900;" align="right"><strong class="fb">主要简历：</strong> <br/>
          <span style="color: red;">（按起止年月、在何单位、任何职位顺序填写）</span></td>
        <td colspan="3">${mainly_resume}</td>
      </tr>
      <tr>
        <td align="right" class="title_item">自我评价及工作业绩：</td>
        <td colspan="3">${af.map.eva_performance}</td>
      </tr>
      <tr>
      	<td colspan="4" style="font-weight:900;"><strong class="fb">审核信息</strong></td>
      </tr>
      <tr>
        <td colspan="4">
        　　　　<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="categorys_td">
              	<tr>
              	  <td width="10%" align="center" nowrap="nowrap">序号</td>
              	  <td width="10%" align="center" nowrap="nowrap">审核人</td>
              	  <td width="8%" align="center" nowrap="nowrap">审核状态</td>
              	  <td width="10%" align="center" nowrap="nowrap">审核时间</td>
              	  <td align="center">审核意见</td>
              	  <td width="10%" align="center" nowrap="nowrap">下一个审核角色</td>
              	</tr>
              	<c:forEach items="${kHis}" var="cur" varStatus="vs">
                 <tr>
              	  	<td align="center" nowrap="nowrap">${vs.count}</td>
              	  	<td align="left" nowrap="nowrap"><c:if test="${empty cur.audit_user_name or cur.audit_user_name eq '--'}">${af.map.add_user_name}（申请人）</c:if>
              	  	<c:if test="${!empty cur.audit_user_name and cur.audit_user_name ne '--'}">${cur.audit_user_name}</c:if></td>
              	  	<td align="left" nowrap="nowrap">${cur.audit_status_desc}</td>
              	  	<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.audit_date}" pattern="yyyy-MM-dd" /></td>
              	  	<td align="left">${cur.audit_option}</td>
              	  	<td align="left" nowrap="nowrap">${cur.audit_next_node_name}<c:if test="${empty cur.audit_next_node_name}">无</c:if></td>
              	  </tr>
              	</c:forEach>
            </table>	
        </td>
      </tr>
      <tr id="table_footer">
        <td align="center" colspan="4"><input class="but5" type="button"  value="返回 " onclick="history.back();" />&nbsp;<html-el:button property="" value="打 印" styleClass="but4" styleId="btn_print" onclick="alert('请在浏览器中进行打印页面设置，将页眉页脚去掉，可以获得更好的打印效果。');$('#table_footer').hide();window.print();$('#table_footer').show();" /></td>
      </tr>
    </table>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="../__message/message.jsp" flush="true" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
