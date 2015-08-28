<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>mainFrame</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.process {line-height:20px;margin:10px;}
.process span {font-weight:800;font-size:1.3em;font-style:italic;}
.training {width:430px;float:left;margin:0px 10px 10px 0px;}
.training a:link {color:#1e3257;}
.training a:visited {color:#1e3257;}
.training a:hover {color:red;}
</style>
</head>
<body>
<div class="oarcont" align="left">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${roleNames}端 - 系统首页</td>
      </tr>
    </table>
  </div>
  <c:set var="role_id_eq_400" value="${false}" />
  <c:set var="role_id_ge_200_le_400" value="${false}" />
  <c:forEach var="cur" items="${roleInfoList}" varStatus="vs">
  	<c:if test="${cur.role_id eq 400}">
  		<c:set var="role_id_eq_400" value="${true}" />
  	</c:if>
  	<c:if test="${cur.role_id ge 200 and cur.role_id le 400}">
  		<c:set var="role_id_ge_200_le_400" value="${true}" />
  	</c:if>
  	<c:if test="${cur.role_id eq 188}">
  		<c:set var="role_id_eq_188" value="${true}" />
  	</c:if>
  </c:forEach>
  <c:if test="${role_id_eq_400}">
  	<div class="process">
  		<span>进：</span>
		<a href="${ctx}/manager/zmd/KonkaXxZmdOrderAdd.do?mod_id=820511">提交进货订单</a> →
		<a href="${ctx}/manager/zmd/KonkaXxZmdOrderAddSearch.do?mod_id=820512">进货订单查询</a>
  	</div>
  	<div class="process">
  		<span>销：</span>
		<a href="${ctx}/manager/zmd/KonkaXxZmdAddSalesOrder.do?mod_id=820501">添加销售订单</a> →
		<a href="${ctx}/manager/zmd/KonkaXxPrintReceipt.do?mod_id=820509">打印收款单</a> →
		<a href="${ctx}/manager/zmd/KonkaXxSellBillCstSatform.do?mod_id=820510">确认用户回访</a> →
		<a href="${ctx}/manager/zmd/KonkaXxZmdSearchSalesOrder.do?mod_id=820506">查询销售单</a>
  	</div>
  	<div class="process">
  		<span>存：</span>
		<a href="${ctx}/manager/zmd/KonkaXxZmdKcSearch.do?mod_id=820802">分公司库存</a>
		<!--  →
		<a href="${ctx}/manager/zmd/KonkaXxZmdKcSearch.do?mod_id=820802">我的库存</a> -->
  	</div>
  </c:if>
  <c:if test="${role_id_ge_200_le_400}">
  <!-- 新闻资讯 -->
  <div class="rtabcont1">
  	  <c:if test="${not empty articleInfoList860201}">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2 training">
		     <tr class="tabtt1">
		       <td nowrap="nowrap">培训资料</td>
		     </tr>
		     <tbody>
			     <c:forEach var="cur" items="${articleInfoList860201}" varStatus="vs">
				     <tr>
				     	<td>[<fmt:formatDate value="${cur.add_date}" pattern="MM/dd" />]
				     	<c:if test="${empty cur.link_out_addr}">
				     		<a href="${ctx}/manager/admin/KonkaPeArticleInfo.do?method=view&id=${cur.id}">${cur.title}</a>
				     	</c:if>
				     	<c:if test="${not empty cur.link_out_addr}">
				     		<a href="${cur.link_out_addr}" target="_blank">${cur.title}</a>
				     	</c:if>
				     	</td>
				     </tr>
				 </c:forEach>
				 <c:forEach begin="${fn:length(articleInfoList860201) + 1}" end="5">
				 	<tr><td>&nbsp;</td></tr>
				 </c:forEach>
		     </tbody>
		  </table>
	  </c:if>
	  <c:if test="${not empty articleInfoList860202}">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2 training">
		     <tr class="tabtt1">
		       <td nowrap="nowrap">培训安排</td>
		     </tr>
		     <tbody>
			     <c:forEach var="cur" items="${articleInfoList860202}" varStatus="vs">
				     <tr>
				     	<td>[<fmt:formatDate value="${cur.add_date}" pattern="MM/dd" />]
				     	<c:if test="${empty cur.link_out_addr}">
				     		<a href="${ctx}/manager/admin/KonkaPeArticleInfo.do?method=view&id=${cur.id}">${cur.title}</a>
				     	</c:if>
				     	<c:if test="${not empty cur.link_out_addr}">
				     		<a href="${cur.link_out_addr}" target="_blank">${cur.title}</a>
				     	</c:if>
				     	</td>
				     </tr>
				 </c:forEach>
				 <c:forEach begin="${fn:length(articleInfoList860202) + 1}" end="5">
				 	<tr><td>&nbsp;</td></tr>
				 </c:forEach>
		     </tbody>
		  </table>
	  </c:if>
	  <c:if test="${not empty articleInfoList860203}">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2 training">
		     <tr class="tabtt1">
		       <td nowrap="nowrap">竞品资料</td>
		     </tr>
		     <tbody>
			     <c:forEach var="cur" items="${articleInfoList860203}" varStatus="vs">
				     <tr>
				     	<td>[<fmt:formatDate value="${cur.add_date}" pattern="MM/dd" />]
				     	<c:if test="${empty cur.link_out_addr}">
				     		<a href="${ctx}/manager/admin/KonkaPeArticleInfo.do?method=view&id=${cur.id}">${cur.title}</a>
				     	</c:if>
				     	<c:if test="${not empty cur.link_out_addr}">
				     		<a href="${cur.link_out_addr}" target="_blank">${cur.title}</a>
				     	</c:if>
				     	</td>
				     </tr>
				 </c:forEach>
				 <c:forEach begin="${fn:length(articleInfoList860203) + 1}" end="5">
				 	<tr><td>&nbsp;</td></tr>
				 </c:forEach>
		     </tbody>
		  </table>
	  </c:if>
	  <c:if test="${not empty articleInfoList860204}">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2 training">
		     <tr class="tabtt1">
		       <td nowrap="nowrap">新品上市</td>
		     </tr>
		     <tbody>
			     <c:forEach var="cur" items="${articleInfoList860204}" varStatus="vs">
				     <tr>
				     	<td>[<fmt:formatDate value="${cur.add_date}" pattern="MM/dd" />]
				     	<c:if test="${empty cur.link_out_addr}">
				     		<a href="${ctx}/manager/admin/KonkaPeArticleInfo.do?method=view&id=${cur.id}">${cur.title}</a>
				     	</c:if>
				     	<c:if test="${not empty cur.link_out_addr}">
				     		<a href="${cur.link_out_addr}" target="_blank">${cur.title}</a>
				     	</c:if>
				     	</td>
				     </tr>
				 </c:forEach>
				 <c:forEach begin="${fn:length(articleInfoList860204) + 1}" end="5">
				 	<tr><td>&nbsp;</td></tr>
				 </c:forEach>
		     </tbody>
		  </table>
	  </c:if>
	  <c:if test="${not empty articleInfoList860205}">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2 training">
		     <tr class="tabtt1">
		       <td nowrap="nowrap">其他资料</td>
		     </tr>
		     <tbody>
			     <c:forEach var="cur" items="${articleInfoList860205}" varStatus="vs">
				     <tr>
				     	<td>[<fmt:formatDate value="${cur.add_date}" pattern="MM/dd" />]
				     	<c:if test="${empty cur.link_out_addr}">
				     		<a href="${ctx}/manager/admin/KonkaPeArticleInfo.do?method=view&id=${cur.id}">${cur.title}</a>
				     	</c:if>
				     	<c:if test="${not empty cur.link_out_addr}">
				     		<a href="${cur.link_out_addr}" target="_blank">${cur.title}</a>
				     	</c:if>
				     	</td>
				     </tr>
				 </c:forEach>
				 <c:forEach begin="${fn:length(articleInfoList860205) + 1}" end="5">
				 	<tr><td>&nbsp;</td></tr>
				 </c:forEach>
		     </tbody>
		  </table>
	  </c:if>
	  <div style="clear:left;"></div>
  </div>
  </c:if>
  <!-- 待办事项 -->
  <c:if test="${!role_id_eq_188}">
  <c:if test="${not empty entityList}"> <br/>
    <div> &nbsp;&nbsp;<span style="font-size:18px;color:red;font-weight:bold;">待办事项</span>
      <span style="font-size:15px;color:green;cursor:pointer;font-weight:bold;align:right;" onclick="javascript:window.location.href='${ctx}/manager/chengduoa/SelfEventCenter.do?method=list&mod_id=951000'">更多</span> 
    </div>
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="8%" nowrap="nowrap" align="center">事务类型</td>
          <td nowrap="nowrap">标题</td>
          <td width="15%" nowrap="nowrap">信息来源</td>
          <td width="10%" align="center" nowrap="nowrap">发起时间</td>
          <td width="10%" align="center" nowrap="nowrap">操作</td>
        </tr>
        <tbody>
          <c:set var="ids" value="0" />
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr id="tr_${cur.id}">
              <td align="center" nowrap="nowrap">${cur.sequence}</td>
              <td align="center" >${cur.eventType}</td>
              <td align="left">${cur.eventiltle}</td>
              <td>${cur.fromPerson}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.enterDate}" pattern="yyyy/MM/dd HH:mm" /></td>
              <td align="center" nowrap="nowrap">${cur.eventDo}</td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>
  <!-- 下发文件 -->
  <c:if test="${not empty _entityList}"> <br/>
    <div> &nbsp;&nbsp;<span style="font-size:18px;color:red;font-weight:bold;">下发文件</span>
     <span style="font-size:15px;color:green;cursor:pointer;font-weight:bold;align:right;" onclick="javascript:window.location.href='${ctx}/manager/chengduoa/SsuedDocument.do?method=list&mod_id=952000'">更多</span> 
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <td width="5%" nowrap="nowrap" align="center">序号</td>
          <td width="52" nowrap="nowrap" align="center">月份</td>
          <td nowrap="nowrap">文件主题</td>
          <td width="52" nowrap="nowrap" align="center">事务类型</td>
          <td nowrap="nowrap">发放人/部门</td>
          <td width="100" nowrap="nowrap" align="center">发文时间</td>
          <td width="40" nowrap="nowrap" align="center">操作</td>
        </tr>
        <tbody>
          <c:forEach var='_cur' items='${_entityList}' varStatus="vs">
            <tr id="tr_${_cur.id}">
              <td valign="top" align="center" nowrap="nowrap">${vs.count }</td>
              <td valign="top" align="center" nowrap="nowrap"><fmt:formatDate value="${_cur.add_date}" pattern="yyyy-MM" /></td>
              <td valign="top" align="left">${_cur.title}</td>
              <td valign="top" align="center">${_cur.mod_name}</td>
              <td valign="top" align="left">${_cur.pass_man_name}/${_cur.part_name}</td>
              <td valign="top" align="center" nowrap="nowrap"><fmt:formatDate value="${_cur.add_date}" pattern="yyyy/MM/dd HH:mm" /></td>
              <td align="center" valign="top"><c:choose>
                  <c:when test="${_cur.mod_type eq 'notice' }">
                    <span style="cursor:pointer;" onclick="javascript:window.location.href='${ctx}/manager/chengduoa/DocIssue.do?method=view&id=${_cur.id}&mod_id=952000'">查看</span> 
                  </c:when>
                  <c:when test="${_cur.mod_type eq 'file' }"> <span style="cursor:pointer;" onclick="view_and_print(${_cur.id});">查看</span> </c:when>
                  <c:otherwise></c:otherwise>
                </c:choose></td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>
  </c:if>
  <!-- 完美终端 --> 
  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
  <c:if test="${not empty atcList}"> <br/>
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr>
          <td colspan="4"> 以下门店信息有更新：</td>
        </tr>
        <tr>
          <td valign="top" align="center" nowrap="nowrap"></td>
          <td>门店代码</td>
          <td>门店名称</td>
          <td>数据期号</td>
        </tr>
        <c:forEach var="atc" items="${atcList}" varStatus="vc">
          <tr onmouseover="this.bgColor='#97CBFF';this.style.cursor='pointer';" onmouseout="this.bgColor=''" onclick="doNeedMethod(null, '../paragon/KonkaParagonAttentionC.do', 'view','aid=${atc.ATTENTION_ID}&sid=${atc.SHOW_SHOP_ID}&scode=${atc.SHOW_SHOP_CODE}&fixdate=${atc.FIXDATE}&mod_id=500000')" title="查看详细">
            <td>${vc.count}</td>
            <td>${atc.SHOW_SHOP_CODE}</td>
            <td>${atc.SHOW_SHOP_NAME}</td>
            <td>${atc.FIXDATE}</td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </c:if>
  <div class="rtabcont3"></div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript">//<![CDATA[
   $(document).ready(function() {
	   var role_id = '${roleInfo.role_id}';
	  	if(role_id == 1000 || role_id == 1010 || role_id == 1020){
	  		alert("目前系统只供预览和测试之用");
	  	}
   }); 
   function view_and_print(id) {
	    window.showModalDialog("${ctx}/manager/chengduoa/AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id, window, "dialogWidth:800px;status:no;dialogHeight:600px");
   }
//]]></script> 
<!-- 如果是康佳专卖店用户，显示消息提醒弹窗和消息bar  
200 新兴渠道总部管理员
300	新兴渠道分公司管理员
350 新兴渠道分公司财务
360 新兴渠道分公司物流
390 新兴渠道分公司业务员
400 新兴渠道专卖店管理员-->
<c:if test="${roleInfo.role_id eq 200 || roleInfo.role_id eq 300 || roleInfo.role_id eq 350 || roleInfo.role_id eq 360 || roleInfo.role_id eq 390 || roleInfo.role_id eq 400}">
  <jsp:include page="../../zmd/__message/message.jsp" flush="true" />
</c:if>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
