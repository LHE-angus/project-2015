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
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/manager/KonkaXxZmdDztzSearch">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <div id="condition_div" style="100%;" >
        <table id="condition_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable5">
          <tr>
            <td width="10%">&nbsp;<strong class="fb">分公司名称：</strong></td>
            <td width="40%"><c:if test="${empty dept_name}">
                <html-el:select property="dept_id" styleId="dept_id" onchange="this.form.submit();" style="width:154px;">
                  <html-el:option value="">==请选择==</html-el:option>
                  <c:forEach var="cur" items="${konkaDepts}">
                    <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
                  </c:forEach>
                </html-el:select>
              </c:if>
              <c:if test="${not empty dept_name}">
                <label style="color:blue;">${dept_name}</label>
              </c:if></td>
            <td width="10%"><strong class="fb">专卖店编号：</strong></td>
            <td width="40%"><c:if test="${empty zmd_sn}">
                <html-el:select property="zmd_id" onchange="this.form.submit();" style="width:154px;">
                  <html-el:option value="">==请选择==</html-el:option>
                  <c:forEach var="cur" items="${zmdList}">
                    <html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
                  </c:forEach>
                </html-el:select>
              </c:if>
              <c:if test="${not empty zmd_sn}">
                <label style="color:blue;">${zmd_sn}</label>
              </c:if></td>
          </tr>
          <tr>
            <td><strong class="fb">&nbsp;销售人姓名：</strong></td>
            <td><html-el:text property="sell_man_like" styleId="sell_man_like" maxlength="20" ></html-el:text></td>
            <td><strong class="fb">销售日期：</strong></td>
            <td><html-el:text property="sell_date_ge" styleId="sell_date_ge" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" />
              至
              <html-el:text property="sell_date_le" styleId="sell_date_le" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" /></td>
          </tr>
          <tr>
            <td><strong class="fb">&nbsp;产品型号：</strong></td>
            <td><html-el:text property="md_name_like" styleId="md_name_like" onkeyup="upperCase(this.id)" maxlength="20" ></html-el:text></td>
            <td><strong class="fb">产品类别：</strong></td>
            <td><html-el:select property="par_pd_cls" styleId="par_pd_cls" style="width:154px;" onchange="this.form.submit();">
                <c:forEach var="cur" items="${basePdClazzList}">
                  <html-el:option value="${cur.cls_id}">${fn:escapeXml(cur.tree_name)}</html-el:option>
                </c:forEach>
              </html-el:select>
              &nbsp;&nbsp;
              <input class="but1" type="submit" name="Submit" value="搜索" /></td>
          </tr>
        </table>
      </div>
    </html-el:form>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp"%>
    <div style="100%;overflow-x:scroll;height:340px;" >
      <table id="table1" width="1720" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tr class="tabtt1">
          <c:if test="${empty dept_name}">
            <td width="100" align="center" nowrap="nowrap">分公司名称</td>
          </c:if>
          <c:if test="${empty zmd_sn}">
            <td width="100" align="center" nowrap="nowrap">专卖店编号</td>
          </c:if>
          <td width="100" align="center" nowrap="nowrap">销售日期</td>
          <td width="120" align="center" nowrap="nowrap">销售品类</td>
          <td width="120" align="center" nowrap="nowrap">产品型号</td>
          <td width="250" align="center" nowrap="nowrap">出货仓位<font color="gray">（*小括号内为出库数量）</font></td>
          <td width="120" align="center" nowrap="nowrap">销售价格</td>
          <td width="100" align="center" nowrap="nowrap">数量</td>
          <td width="120" align="center" nowrap="nowrap">金额合计</td>
          <td width="100" align="center" nowrap="nowrap">销售人姓名</td>
          <td width="140" align="center" nowrap="nowrap">赠送礼品</td>
          <td width="80" align="center" nowrap="nowrap">佣金</td>
          <td width="270" align="center" nowrap="nowrap">备注</td>
        </tr>
        <c:forEach var="cur" items="${entityList}" varStatus="vs">
          <tr>
            <c:if test="${empty dept_name}">
              <td align="left" class="dept_name"><font class="blue12px">${cur.map.dept_name}</font></td>
            </c:if>
            <c:if test="${empty zmd_sn}">
              <td align="left"><font class="blue12px">${cur.map.zmd_sn}</font></td>
            </c:if>
            <td align="left"><font class="blue12px">
              <fmt:formatDate value="${cur.map.sell_date}" pattern="yyyy-MM-dd" />
              </font></td>
            <td align="left"><font class="blue12px">${cur.pd_cls_name}</font></td>
            <td align="left"><font class="blue12px">${cur.md_name}</font></td>
            <td align="left"><font class="blue12px">${cur.map.store_name}</font></td>
            <td align="right"><span class="kz-price-12">
              <fmt:formatNumber type="currency" value="${cur.price}" />
              </span></td>
            <td align="right"><font class="blue12px">${cur.counts}</font></td>
            <td align="right"><span class="kz-price-12">
              <fmt:formatNumber type="currency" value="${cur.price * cur.counts}" />
              </span></td>
            <td align="left" title="${cur.map.sell_man}"><font class="blue12px">${fnx:abbreviate(cur.map.sell_man, 2 * 7, '')}</font></td>
            <td align="left" title="${cur.gift}"><font class="blue12px">${fnx:abbreviate(cur.gift, 2 * 9, '')}</font></td>
            <td align="right"><span class="kz-price-12">
              <fmt:formatNumber type="currency" value="${cur.zmd_fee}" />
              </span></td>
            <td align="left" title="${cur.xs_remarks}"><font class="blue12px">
              <c:out value="${fnx:abbreviate(cur.xs_remarks, 2 * 20, '...')}" />
              </font></td>
          </tr>
        </c:forEach>
        <c:forEach begin="${fn:length(entityList)}" end="${af.map.pager.pageSize - 1}" step="1">
          <tr>
            <c:if test="${empty dept_name}">
              <td>&nbsp;</td>
            </c:if>
            <c:if test="${empty zmd_sn}">
              <td>&nbsp;</td>
            </c:if>
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
        <tr>
          <td align="center" nowrap="nowrap"><font class="blue">合计</font></td>
          <c:if test="${empty dept_name}">
            <td>&nbsp;</td>
          </c:if>
          <c:if test="${empty zmd_sn}">
            <td>&nbsp;</td>
          </c:if>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><span class="kz-price-12">
            <fmt:formatNumber type="currency" value="${total_pay}" />
            </span></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><span class="kz-price-12">
            <fmt:formatNumber type="currency" value="${total_fee}" />
            </span></td>
          <td>&nbsp;</td>
        </tr>
      </table>
    </div>
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaXxZmdDztzSearch.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="left"><input class="but_excel" type="button"  value="导出" id="export_all" /></td>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id","${af.map.mod_id}");
            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			pager.addHiddenInputs("zmd_id", "${af.map.zmd_id}");	
            pager.addHiddenInputs("sell_date_ge", "${af.map.sell_date_ge}");
            pager.addHiddenInputs("sell_date_le", "${af.map.sell_date_le}");
			pager.addHiddenInputs("sell_man", "${fn:escapeXml(af.map.sell_man)}");
			pager.addHiddenInputs("par_pd_cls", "${af.map.par_pd_cls}");
			pager.addHiddenInputs("md_name_like", "${fn:escapeXml(af.map.md_name_like)}");
			pager.addHiddenInputs("sell_man_like", "${fn:escapeXml(af.map.sell_man_like)}");
			
            document.write(pager.toString());
          </script></td>
        </tr>
      </table>
    </form>
  </div>
  <div class="clear"></div>
</div>
<c:if test="${not empty entityList_all}">
  <div style="100%;overflow-x:auto;padding-bottom:5px;display:none;" id="divExcel_all" title="康佳专卖店销售电子台账表${af.map.now_date}">
    <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" colspan="11"><font style="font-weight:bold;font-size:20px;">康佳专卖店销售电子台账表</font></td>
      </tr>
    </table>
    <table id="table2" width="100%" border="1" cellspacing="0" cellpadding="0">
      <tr>
        <td width="100" align="center" nowrap="nowrap">分公司名称</td>
        <td width="100" align="center" nowrap="nowrap">专卖店编号</td>
        <td width="100" align="center" nowrap="nowrap">销售日期</td>
        <td width="120" align="center" nowrap="nowrap">销售品类</td>
        <td width="120" align="center" nowrap="nowrap">产品型号</td>
        <td width="250" align="center" nowrap="nowrap">出货仓位（*小括号内为出库数量）</td>
        <td width="120" align="center" nowrap="nowrap">销售价格（元）</td>
        <td width="100" align="center" nowrap="nowrap">数量</td>
        <td width="120" align="center" nowrap="nowrap">金额合计（元）</td>
        <td width="120" align="center" nowrap="nowrap">销售人姓名</td>
        <td width="120" align="center" nowrap="nowrap">赠送礼品</td>
        <td width="120" align="center" nowrap="nowrap">佣金（元）</td>
        <td width="250" align="center" nowrap="nowrap">备注</td>
      </tr>
      <c:forEach var="cur" items="${entityList_all}" varStatus="vs">
        <tr>
          <td align="left" class="dept_name"><font class="blue12px">
            <c:out value="${cur.map.dept_name}" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.map.zmd_sn}" />
            </font></td>
          <td align="left"><font class="blue12px">
            <fmt:formatDate value="${cur.map.sell_date}" pattern="yyyy年MM月dd日" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.pd_cls_name}" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.md_name}" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.map.store_name}" />
            </font></td>
          <td align="right"><font class="blue12px">
            <c:out value="${cur.price}" />
            </font></td>
          <td align="right"><font class="blue12px">
            <c:out value="${cur.counts}" />
            </font></td>
          <td align="right" style="mso-number-format:'\@';"><font class="blue12px">
            <c:out value="${cur.price * cur.counts}" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.map.sell_man}" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.gift}" />
            </font></td>
          <td align="right"><font class="blue12px">
            <c:out value="${cur.zmd_fee}" />
            </font></td>
          <td align="left"><font class="blue12px">
            <c:out value="${cur.xs_remarks}" />
            </font></td>
        </tr>
      </c:forEach>
      <tr>
        <td align="center" nowrap="nowrap"><font class="blue">合计</font></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${total_pay_all}" pattern="#,#00.00#" /></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="right" nowrap="nowrap" style="mso-number-format:'\@';"><fmt:formatNumber value="${total_fee_all}" pattern="#,#00.00#" /></td>
        <td>&nbsp;</td>
      </tr>
    </table>
  </div>
</c:if>
<div class="clear"></div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script>
<script type="text/javascript" src="${ctx}/scripts/commons.plugin.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui/ui/minified/jquery-ui.custom.min.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	$("#sell_date_ge").datepicker();
	$("#sell_date_le").datepicker();
	
	trMerge("dept_name",$("#table1"));
	trMerge("dept_name",$("#table2"));

	// 返回页面根据需求导出全部数据
	var allToExcel = "${af.map.allToExcel}";
	if("ebiz" == allToExcel){
		toExcel('divExcel_all', '${ctx}/customer/manager/KonkaXxZmdDztzSearch.do?method=toExcel');
	}
	$("#export_all").click(function(){
		$("#bottomPageForm").append("<input type='hidden' name='allToExcel' value='ebiz' />");
		$("#bottomPageForm").submit();
	});

});
function upperCase(x)
{
var y=document.getElementById(x).value
document.getElementById(x).value=y.toUpperCase()
}

//]]></script>

<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
