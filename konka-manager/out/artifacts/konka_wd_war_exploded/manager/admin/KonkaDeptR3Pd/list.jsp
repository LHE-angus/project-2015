<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <html-el:form action="/admin/KonkaDeptR3Pd">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
        <tr>
          <td nowrap="nowrap">&nbsp;&nbsp;<strong class="fb">分公司：</strong>
          <html-el:select property="dept_id" styleId="dept_id">
            <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${deptList}" var="cur">
                <html-el:option value="${cur.dept_id}">${cur.dept_name}</html-el:option>
              </c:forEach>
            </html-el:select>
            &nbsp;&nbsp;<strong class="fb">客户R3编码：</strong>
            <html-el:text property="r3_code_like" styleId="r3_code_like" maxlength="40"/>
            &nbsp;&nbsp;&nbsp;&nbsp;<strong class="fb">年月：</strong>
            <html-el:select property="year" styleId="year">
              <html-el:option value="">-请选择-</html-el:option>
              <c:forEach items="${yearList}" var="cur">
                <html-el:option value="${cur}">${cur}年</html-el:option>
              </c:forEach>
            </html-el:select>
            <html-el:select property="month" styleId="month">
              <html-el:option value="">-请选择-</html-el:option>
              <html-el:option value="01">1月</html-el:option>
              <html-el:option value="02">2月</html-el:option>
              <html-el:option value="03">3月</html-el:option>
              <html-el:option value="04">4月</html-el:option>
              <html-el:option value="05">5月</html-el:option>
              <html-el:option value="06">6月</html-el:option>
              <html-el:option value="07">7月</html-el:option>
              <html-el:option value="08">8月</html-el:option>
              <html-el:option value="09">9月</html-el:option>
              <html-el:option value="10">10月</html-el:option>
              <html-el:option value="11">11月</html-el:option>
              <html-el:option value="12">12月</html-el:option>
            </html-el:select> 
            <input type="submit" name="" value="搜索" id="btn_submit" class="but1" />
            </td>
        </tr>
      </table>
    </html-el:form>
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
            <input class="but_excel" type="button" name="Submit3" value="导入" onclick="location.href='${ctx}/manager/admin/KonkaDeptR3Pd.do?method=imp&mod_id=${af.map.mod_id}'" /></td>
        </tr>
      </table>
    </div>
    <div class="rtabcont1">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
          <tr class="tabtt1">
            <td width="5%" align="center">序号</td>
            <td align="center" width="8%" nowrap="nowrap">分公司</td>
            <td align="center" nowrap="nowrap" width="8%">客户R3编码</td>
            <td align="center" nowrap="nowrap" >型号</td>
            <td width="6%" nowrap="nowrap" align="center">年</td>
            <td width="6%" nowrap="nowrap" align="center">月</td>
            <td width="8%" nowrap="nowrap" align="center">参考价格</td>
            <td width="8%" nowrap="nowrap" align="center" >最低价格</td>
            <td width="6%" nowrap="nowrap" align="center" >最高价格</td>
            <td width="12%" nowrap="nowrap" align="center" >添加时间</td>
            <td width="10%" nowrap="nowrap" align="center" >操作</td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td align="center" nowrap="nowrap">${(af.map.pager.currentPage - 1) * af.map.pager.pageSize + vs.count}</td>
              <td align="left" align="left">${fn:escapeXml(cur.dept_name)}</td>
              <td align="left" align="left">${fn:escapeXml(cur.r3_code)}</td>
              <td align="left" align="left">${fn:escapeXml(cur.pd_sn)}</td>
              <td align="right" nowrap="nowrap">${cur.year}</td>
              <td align="right" nowrap="nowrap">${cur.month}</td>
              <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.price}" pattern="#0.00" /></td>
              <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.min_price}" pattern="#0.00" /></td>
               <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.max_price}" pattern="#0.00" /></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.add_date}" pattern="yyyy-MM-dd hh:mm"/></td>
           	  <td align="center" nowrap="nowrap">
           	  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptR3Pd.do','view' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">查看</span>
           	  	<span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptR3Pd.do','edit' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">修改</span>
           	  <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod('您确定删除吗？', 'KonkaDeptR3Pd.do','delete' ,'id=${cur.id}&' + $('#bottomPageForm').serialize())">删除</span>
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
            </tr>
          </c:forEach>
        </table>
      <br />
      <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaDeptR3Pd.do">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
              <script type="text/javascript">
            	var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
			            pager.addHiddenInputs("method", "list");
			            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
			            pager.addHiddenInputs("r3_code_like", "${af.map.r3_code_like}");
			            pager.addHiddenInputs("dept_id", "${af.map.dept_id}");
			            pager.addHiddenInputs("month", "${af.map.month}");
			            pager.addHiddenInputs("year", "${af.map.year}");
				        document.write(pager.toString());
			   </script></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/My97DatePicker/WdatePicker.js"></script>
<script> 

</script> 
<jsp:include page="/__analytics.jsp" />
</body>
</html>
