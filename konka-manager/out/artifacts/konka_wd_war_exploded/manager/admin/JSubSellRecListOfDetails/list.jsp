<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<title>${naviString}</title>
<base target="_self" />
<style type="text/css">
	.tableTop tr td{
		padding: 3px 0 4px 0;
	}
</style>
</head> 

<body style="font-family:Microsoft Yahei;">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  <html-el:form action="/admin/JSubSellRecListOfDetails.do">
    <html-el:hidden property="method" value="list" />
    <html-el:hidden property="mod_id" styleId="mod_id" />
    <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
        	<td nowrap="nowrap" align="right">
        		<strong class="fb">分 公 司：</strong>
          	</td>
			<c:if test="${empty userDept}">
            <td colspan="7">
            	<table>
            		<tr>
            			<td>
				            <html-el:select property="l3_dept_id" styleId="l3_dept_id" disabled="${disabled}">
				            	<html-el:option value="">-分公司/经营部-</html-el:option>
				            </html-el:select>
			            </td>
			            <td>
				            <html-el:select property="l4_dept_id" styleId="l4_dept_id">
				            	<html-el:option value="">-请选择-</html-el:option>
				            </html-el:select>
			            </td>
			            <td>
			              	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
			                	<html-el:option value="">-请选择-</html-el:option>
			              	</html-el:select>
			            </td>
            		</tr>
            	</table>
            </td>
            </c:if>
            <c:if test="${not empty userDept}">${fn:replace(userDept.full_name, ',', ' &gt; ')}</c:if>
     	</tr>
     	<tr>
          	<td nowrap="nowrap" align="right">
            	<strong class="fb">销售日期：</strong>
            </td>
            <td>
            	<input type="text" name="opr_date_gt" id="opr_date_gt" class="webinput" value="${af.map.opr_date_gt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
            	至
            	<input type="text" name="opr_date_lt" id="opr_date_lt" class="webinput" value="${af.map.opr_date_lt}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
            </td>
            <td nowrap="nowrap" align="right">
            	<strong class="fb">销售单号：</strong>
            </td>
            <td>
            	<html-el:text property="bill_sn_like" styleClass="webinput" styleId="bill_sn_like" maxlength="30" size="20"/>
            </td>
            <td nowrap="nowrap" align="right">
            	<strong class="fb">分销类型：</strong>
            </td>
            <td>
            	<html-el:select property="bill_type" styleId="bill_type" style="width:150px">
	              <html-el:option value="">-请选择-</html-el:option>
	              <html-el:option value="40">分销</html-el:option>
	              <html-el:option value="42">分销退货</html-el:option>
	            </html-el:select>
            </td>
            <td nowrap="nowrap" align="right">
            	<strong class="fb">R3编码：</strong>
            </td>
            <td>
            	<html-el:text property="sell_r3_code" styleClass="webinput" styleId="sell_r3_code" maxlength="30" size="20"/>
            </td>
        </tr>
        <tr>
        	<td nowrap="nowrap" align="right">
				<strong class="fb">财务确认：</strong>
			</td>
            <td>
            	<html-el:select property="status" styleId="status" style="width:150px;">
              	<html-el:option value="">-请选择-</html-el:option>
              	<html-el:option value="0">未确认</html-el:option>
              	<html-el:option value="1">已确认</html-el:option>
            	</html-el:select>
            </td>
        	<td nowrap="nowrap" align="right">
				<strong class="fb">网点确认：</strong>
			</td>
            <td>
            	<html-el:select property="wd_status" styleId="wd_status" style="width:150px;">
              	<html-el:option value="">-请选择-</html-el:option>
              	<html-el:option value="0">未确认</html-el:option>
              	<html-el:option value="1">已确认</html-el:option>
              	<html-el:option value="2">已退回</html-el:option>
            	</html-el:select>
            </td>
            <td nowrap="nowrap" align="right">
            	<strong class="fb">网点名称：</strong>
            </td>
            <td>
            	<html-el:text property="partner_name" styleClass="webinput" styleId="partner_name" maxlength="30" size="20"/>
            </td>
            <td nowrap="nowrap" align="right">
            	<strong class="fb">联系人：</strong>
            </td>
            <td>
            	<html-el:text property="link_name" styleClass="webinput" styleId="link_name" maxlength="30" size="20"/>
            </td>
        </tr>
        <tr>
            <td nowrap="nowrap" align="right">
            	<strong class="fb">产品型号：</strong>
		    </td>
            <td>
		    	<html-el:text property="map_pd_name_like" styleClass="webinput" styleId="map_pd_name_like" maxlength="30" size="20"/>
	        </td>
	        <td align="right"><strong class="fb">出货仓：</strong></td>
	        <td><html-el:text property="chc_store_name_like" styleClass="webinput" styleId="chc_store_name_like" maxlength="30" size="20"/></td>
            <td nowrap="nowrap" align="right">
            	<strong class="fb">尺寸：</strong>
            </td>
            <td>
            	<html-el:select property="md_size" styleClass="md_size">
            		<html-el:option value="">-请选择-</html-el:option>
         			<c:forEach var="cur" items="${pepdList}" varStatus="vs">
         				<html-el:option value="${cur.MD_SIZE}">${cur.MD_SIZE}</html-el:option>
         			</c:forEach>
	        	</html-el:select>
	        </td>
            <td colspan="2">
	        	<input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
            	<input type="button" class="but_excel" onClick="if(confirm('提示，您确认导出数据？')){doNeedMethod(null, 'JSubSellRecListOfDetails.do', 'list', 'export=true&' + $('#bottomPageForm').serialize())}" value="导出" />
            </td>
        </tr>
      </table>
    </div>
  </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  	<div style="overflow-x:auto;">
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
                <tr class="tabtt1">
                  <td nowrap="nowrap" align="center" width="3%">序号</td>
                  <td nowrap="nowrap" align="center" width="3%">分公司</td>
                  <td nowrap="nowrap" align="center" width="5%">销售时间</td>
                  <td nowrap="nowrap" align="center" width="5%">R3编码</td>
                  <td nowrap="nowrap" align="center" width="5%">卖方客户名称</td>
	              <td nowrap="nowrap" align="center" width="5%">卖方销售单号</td>
	              <td nowrap="nowrap" align="center" width="5%">买方网点名称</td>
	              <td nowrap="nowrap" align="center" width="5%">买方网点编码</td>
	              <td nowrap="nowrap" align="center" width="5%">买方联系人</td>
	              <td nowrap="nowrap" align="center" width="5%">网点业务员</td>
                  <td nowrap="nowrap" align="center" width="5%">商品类型</td>
                  <td nowrap="nowrap" align="center" width="5%">机型</td>
                  <td nowrap="nowrap" align="center" width="5%">尺寸</td>
                  <td nowrap="nowrap" align="center" width="5%">数量</td>
                  <td nowrap="nowrap" align="center" width="5%">销售单价</td>
                  <td nowrap="nowrap" align="center" width="5%">建议销售单价</td>
                  <td nowrap="nowrap" align="center" width="5%">销售金额</td>
                  <td nowrap="nowrap" align="center" width="5%">销售成本</td>
                  <td nowrap="nowrap" align="center" width="5%">销售毛利</td>
                  <td nowrap="nowrap" align="center" width="5%">折扣金额</td>
                  <td nowrap="nowrap" align="center" width="5%">出货仓</td>
                  <td nowrap="nowrap" align="center" width="5%">网点确认状态</td>
                  <td nowrap="nowrap" align="center" width="5%">财务确认状态</td>
                </tr>
                <c:forEach items="${entityList}" var="cur" varStatus="vs">
                  <tr>
                    <td nowrap="nowrap" align="center">${vs.count}</td>
                    <td nowrap="nowrap" align="center">${cur.map.fgs_name}</td>
                    <td nowrap="nowrap"><fmt:formatDate value="${cur.map.opr_date}" pattern="yyyy-MM-dd" /></td>
                    <td nowrap="nowrap" align="left">${cur.map.sell_r3_code}</td>
                    <td nowrap="nowrap" align="left">${cur.map.sell_cust_name}</td>
                    <td nowrap="nowrap" align="left">${cur.map.bill_sn}</td>
                    <td nowrap="nowrap" align="left">${cur.map.partner_name }</td>
                    <td nowrap="nowrap" align="left">${cur.map.partner_sn }</td>
                    <td nowrap="nowrap" align="left">${cur.map.link_name}</td>
                    <td nowrap="nowrap" align="left">${cur.map.ywy_user_name}</td>
                    <td nowrap="nowrap" align="left">${cur.map.type_name }</td>
                    <td nowrap="nowrap" align="left">${cur.map.goods_name }</td>
                    <td nowrap="nowrap" align="right">${cur.map.md_size }</td>
                    <td nowrap="nowrap" align="right">${cur.num }</td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="currency" value="${cur.price }"></fmt:formatNumber></td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="currency" value="${cur.map.sell_price }"></fmt:formatNumber></td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="currency" value="${cur.money }"></fmt:formatNumber></td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="currency" value="${cur.map.buy_price }"></fmt:formatNumber></td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="currency" value="${cur.money-cur.map.buy_price*cur.num }"></fmt:formatNumber></td>
                    <td nowrap="nowrap" align="right"><fmt:formatNumber type="currency" value="${cur.dis_money }"></fmt:formatNumber></td>
                   <td nowrap="nowrap" align="right">${cur.map.chc_store_name}</td>
                    <td nowrap="nowrap" align="center">
                    <c:choose>
	              		<c:when test="${fn:escapeXml(cur.map.wd_status) eq 0}"><span style="color:red;">未确认</span></c:when>
	              		<c:when test="${fn:escapeXml(cur.map.wd_status) eq 1}"><span style="color:green;">已确认</span></c:when>
	              		<c:when test="${fn:escapeXml(cur.map.wd_status) eq 2}"><span style="color:green;">已退回</span></c:when>
              		</c:choose>
                    </td>
                    <td nowrap="nowrap" align="center">
                    <c:choose>
	              		<c:when test="${fn:escapeXml(cur.map.status) eq 0}"><span style="color:red;">未确认</span></c:when>
	              		<c:when test="${fn:escapeXml(cur.map.status) eq 1}"><span style="color:green;">已确认</span></c:when>
              		</c:choose>
                    </td>
                  </tr>
                  <c:if test="${vs.last eq true}">
            		<c:set var="i" value="${vs.count}" />
          		  </c:if>
                </c:forEach>
                <c:forEach begin="${i}" end="${af.map.pager.pageSize - 1}">
		          <tr align="center">
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		            <td>&nbsp;</td>
		          </tr>
        </c:forEach>
        </table>
    <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="JSubSellRecListOfDetails.do">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td height="40" align="right">
            <script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
            <script type="text/javascript">
		            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
		            pager.addHiddenInputs("method", "list");
		            pager.addHiddenInputs("opr_date_gt", "${af.map.opr_date_gt}");
		            pager.addHiddenInputs("opr_date_lt", "${af.map.opr_date_lt}");
		            pager.addHiddenInputs("bill_sn_like", "${af.map.bill_sn_like}");
		            pager.addHiddenInputs("status", "${af.map.status}");
		            pager.addHiddenInputs("type_id", "${af.map.type_id}");
		            pager.addHiddenInputs("goods_id", "${af.map.goods_id}");
		            pager.addHiddenInputs("link_name", "${af.map.link_name}");
		            pager.addHiddenInputs("partner_name", "${af.map.partner_name}");
		            pager.addHiddenInputs("md_size", "${af.map.md_size}");
		            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
		            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
		            pager.addHiddenInputs("bill_type", "${af.map.bill_type}");
		            pager.addHiddenInputs("sell_r3_code", "${af.map.sell_r3_code}");
		            pager.addHiddenInputs("map_pd_name_like", "${af.map.map_pd_name_like}");
		            pager.addHiddenInputs("l3_dept_id", "${af.map.l3_dept_id}");
		            pager.addHiddenInputs("l4_dept_id", "${af.map.l4_dept_id}");
		            pager.addHiddenInputs("l5_dept_id", "${af.map.l5_dept_id}");
		            pager.addHiddenInputs("chc_store_name_like", "${af.map.chc_store_name_like}");
		            pager.addHiddenInputs("wd_status", "${af.map.wd_status}");
		            document.write(pager.toString());
		            </script></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#l3_dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l3_dept_id}"});
	$("#l4_dept_id").attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id").attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#l3_dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "${empty cs_par_id ? 0 : cs_par_id}");
	$("#l3_dept_id").change();
	
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
	    	var s_time = $("#opr_date_gt").val();
			var e_time = $("#opr_date_lt").val();
			if ("" != s_time && "" != e_time && s_time > e_time) {
				alert("开始日期不能大于结束日期！");
				return false;
			}
			if(!Validator.Validate(f, 1)){
				return false;
			}
	    });
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>