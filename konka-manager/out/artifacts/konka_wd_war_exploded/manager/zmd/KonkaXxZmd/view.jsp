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
<style type="text/css">
/**ul {width:100%;}
ul li {width:190px; float:left; overflow:hidden; }*/
#fact_str {
	width:100%;
}
#fact_str li {
	width:190px;
	float:left;
	overflow:hidden;
}
</style>
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
  <div class="rtabcont2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable4">
      <tr>
        <td colspan="13" style="font-weight:bold;font-size:18px;height:40px;text-align:center;border-bottom:none;">“${af.map.zmd_sn}”专卖店资料</td>
      </tr>
      <tr>
        <td width="18%" nowrap="nowrap" class="title_item" align="right">分公司：</td>
        <html-el:hidden property="dept_id" value="${userInfo.dept_id}" />
        <td  align="left">${af.map.map.dept_name}</td>
        <td class="title_item" align="right">大区：</td>
        <td ><font class="blue12px">
          <c:forEach var="cur_14" items="${baseTypesList140000}">
            <c:if test="${cur_14.type_id eq af.map.area_id}">${cur_14.type_name} </c:if>
          </c:forEach>
          </font></td>
      </tr>
      <tr>
        <td width="20%" nowrap="nowrap" class="title_item" align="right">R3专卖店名称：</td>
        <td width="30%">${af.map.r3_name}
          <c:if test="${empty af.map.r3_name}"><span style="color:#999">未填写</span></c:if></td>
        <td width="20%" nowrap="nowrap" class="title_item" align="right">专卖店编号：</td>
        <td width="30%">${af.map.zmd_sn}</td>
      </tr>
      <tr>
        <td width="20%" nowrap="nowrap" class="title_item" align="right">R3编码：</td>
        <td width="30%">${af.map.r3_id}
          <c:if test="${empty af.map.r3_id}"><span style="color:#999">未填写</span></c:if></td>
        <td width="18%" nowrap="nowrap" class="title_item" align="right">R3送达方编码：</td>
        <td width="32%">${af.map.r3_send_num}</td>
      </tr>
      <tr>
        <td class="title_item" align="right">专卖店地址：</td>
        <td>${af.map.addr}</td>
        <td class="title_item" align="right">营业面积(平方米)：</td>
        <td>${af.map.busi_area}
          <c:if test="${empty af.map.busi_area}"><span style="color:#999;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td class="title_item" align="right">租赁期：</td>
        <td><c:choose>
            <c:when test="${empty af.map.rent_start or empty af.map.rent_end}"> <span style="color:#999;">未填写</span> </c:when>
            <c:otherwise>
              <fmt:formatDate value="${af.map.rent_start}" pattern="yyyy-MM-dd" />
              至
              <fmt:formatDate value="${af.map.rent_end}" pattern="yyyy-MM-dd" />
            </c:otherwise>
          </c:choose></td>
        <td class="title_item" align="right">年租金（万元）：</td>
        <td>${af.map.rent_fee}
          <c:if test="${empty af.map.rent_fee}"><span style="color:#999;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td class="title_item" align="right">经营性质：</td>
        <td ><font class="blue12px">
          <c:forEach var="cur_2" items="${baseTypesList10000}">
            <c:if test="${cur_2.type_id eq af.map.busi_type}">${cur_2.type_name} </c:if>
          </c:forEach>
          </font>
          <c:if test="${empty af.map.busi_type}"><span style="color:#999;">未填写</span></c:if></td>
        <td class="title_item" align="right">经营模式：</td>
        <td ><font class="blue12px">
          <c:forEach var="cur_2" items="${baseTypesList100000}">
            <c:if test="${cur_2.type_id eq af.map.busi_mod}">${cur_2.type_name} </c:if>
          </c:forEach>
          </font>
          <c:if test="${empty af.map.busi_mod}"><span style="color:#999;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td class="title_item" align="right" >预计年销售（万元）：</td>
        <td colspan="3">${af.map.money_of_sell_by_year_plan}
          <c:if test="${empty af.map.money_of_sell_by_year_plan}"><span style="color:#999;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td width="20%" class="title_item" align="right" nowrap="nowrap">申请样机额度（万元）：</td>
        <td width="30%">${af.map.money_of_dm_apply}
          <c:if test="${empty af.map.money_of_dm_apply}"><span style="color:#999;">未填写</span></c:if></td>
        <td width="18%" class="title_item" align="right" nowrap="nowrap">计划投放专卖店样机：</td>
        <td width="32%" style="word-break:break-all; word-wrap:break-word;">${af.map.put_dm_plan}
          <c:if test="${empty af.map.put_dm_plan}"><span style="color:#999;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td class="title_item" align="right" nowrap="nowrap">申请建店费用（万元）：</td>
        <td colspan="3">${af.map.money_of_dcrt_apply}
          <c:if test="${empty af.map.money_of_dcrt_apply}"><span style="color:#999;">未填写</span></c:if></td>
      </tr>
      <tr>
         <td colspan="4" style="padding-left: 5%" width="90%">
       	 	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2" id="categorys_td">
              	<tr class="tabtt1" style="height: 28px;">
              	  <td width="" align="center" nowrap="nowrap">工程项目</td>
              	  <td width="10%" align="center" nowrap="nowrap">品名</td>
              	  <td width="10%" align="center" nowrap="nowrap">规格</td>
              	  <td width="10%" align="center" nowrap="nowrap">数量</td>
              	  <td width="10%" align="center" nowrap="nowrap">单位</td>
              	  <td width="12%" align="center" nowrap="nowrap">单价（元）</td>
              	  <td width="12%" align="center" nowrap="nowrap">小计（元）</td>
              	</tr>
              	<c:forEach items="${konkaXxZmdGcysList}" var="cur">
              	  <tr>
              	  	<td>${cur.item_name}</td>
              	  	<td align="left">${cur.pd_name}</td>
              	  	<td align="left">${cur.model_name}</td>
              	  	<td align="right">${cur.item_num}</td>
              	  	<td align="center">${cur.unit}</td>
              	  	<td align="right">${cur.price}</td>
              	  	<td align="right">${cur.total}</td>
              	  </tr>
              	</c:forEach>
              	<tr>
              		<td><span><strong>总计</strong></span></td>
              		<td colspan="7"><span class="title_item" style="color: red;"><fmt:formatNumber value="${total_money}" pattern="#0.00" /></span>&nbsp;元</td>
              	</tr>
            </table>
          </td>
        </tr>
      <tr>
        <td colspan="4" style="font-weight:900;">专卖店管理人员信息</td>
      </tr>
      <tr>
        <td class="title_item" align="right">专卖店负责人：</td>
        <td>${af.map.host_name}
          <c:if test="${empty af.map.host_name}"><span style="color:#999;">未填写</span></c:if></td>
        <td class="title_item" align="right">联系电话：</td>
        <td>${af.map.host_phone}
          <c:if test="${empty af.map.host_phone}"><span style="color:#999;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td class="title_item" align="right">分公司负责人：</td>
        <td>${af.map.dept_leader_name}
          <c:if test="${empty af.map.dept_leader_name}"><span style="color:#999;">未填写</span></c:if></td>
        <td class="title_item" align="right">联系电话：</td>
        <td>${af.map.dept_leader_phone}
          <c:if test="${empty af.map.dept_leader_phone}"><span style="color:#999;">未填写</span></c:if></td>
      </tr>
      <tr>
        <td class="title_item" align="right">分公司总经理：</td>
        <td>${af.map.dept_main_man}
          <c:if test="${empty af.map.dept_main_man}"><span style="color:#999;">未填写</span></c:if></td>
        <td class="title_item" align="right">分公司财务经理：</td>
        <td>${af.map.dept_fnc_man}
          <c:if test="${empty af.map.dept_fnc_man}"><span style="color:#999;">未填写</span></c:if></td>
      </tr>
      <c:if test="${af.map.busi_mod eq 100200}">
        <tr>
          <td class="title_item" align="right" >返佣比例设置信息：</td>
          <td ><c:forEach var="cur" items="${konkaXxZmdRewardSetList}">
              <c:forEach var="cur_1" items="${baseTypesList80000}">
                <c:if test="${cur_1.type_id eq cur.reward_type}"> ${cur_1.type_name} ：${cur.reward_ratio}% <br/>
                </c:if>
              </c:forEach>
            </c:forEach></td>
        </tr>
      </c:if>
      <tr>
        <td class="title_item" align="right">专卖店仓位：</td>
        <td colspan="4"><ul id="fact_str">
            <c:forEach var="cur" items="${konkaXxZmdStoreList}" varStatus="vs">
              <li>${vs.count}.工厂号：${cur.factory_id} 仓库号：${cur.store_id}</li>
            </c:forEach>
          </ul></td>
      </tr>
      <tr>
        <td align="right" class="title_item">附件下载：</td>
        <td colspan="3">
          <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><span>${vs.count}、<a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}" target="_blank">${cur.file_name}</a> <br />
              </span></c:forEach></td>
      </tr>
      <tr>
        <td colspan="4" align="right"> 拟制人：${af.map.write_man}
          <html-el:hidden property="write_man_id" value="${userInfo.id}" />
          <html-el:hidden property="write_man" value="${userInfo.real_name}" /></td>
      </tr>
      <tr>
      	<td colspan="4">审核信息</td>
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
	              	  	<td align="left" nowrap="nowrap"><c:if test="${!empty cur.audit_user_name and cur.audit_user_name ne '--'}">${cur.audit_user_name}</c:if>
	              	  	<c:if test="${empty cur.audit_user_name or cur.audit_user_name eq '--'}">${af.map.write_man}（申请人）</c:if>
	              	  	</td>
	              	  	<td align="left" nowrap="nowrap">${cur.audit_status_desc}</td>
	              	  	<td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.audit_date}" pattern="yyyy-MM-dd" /></td>
	              	  	<td align="left" nowrap="nowrap">${cur.audit_option}</td>
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
<script type="text/javascript" src="${ctx}/commons/scripts/jquery1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
