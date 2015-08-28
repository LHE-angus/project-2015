<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width:100%">
  <div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：${naviString}</div>
  <div class="rtabcont1">
    <html-el:form action="/KonkaJxcFhBillRegister">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
        <tr>
          <td width="16%" nowrap="nowrap"><strong class="fb">发货编号：</strong></td>
          <td width="18%"><html-el:text property="sn_like" styleClass="webinput" styleId="sn_like" /></td>
          <td width="14%" nowrap="nowrap"><strong class="fb">起止日期：</strong></td>
          <td width="13%"><html-el:text property="start_date" styleId="start_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:80px;" onclick="new Calendar(2011, 2031, 0).show(this);" /></td>
          <td width="3%"><span>至</span></td>
          <td width="13%"><html-el:text property="end_date" styleId="end_date" size="9" maxlength="9" readonly="true" styleClass="webinput" style="cursor:pointer;text-align:left;width:80px;" onclick="new Calendar(2011, 2031, 0).show(this);" /></td>
          <td width="23%"></td>
        </tr>
        <tr>
          <td width="16%" nowrap="nowrap"><strong class="fb">是否确认收货：</strong></td>
          <td width="18%"><html-el:select property="is_confirm" styleId="is_confirm" style="width:80px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="0">未确认</html-el:option>
              <html-el:option value="1">已确认</html-el:option>
            </html-el:select></td>
          <td width="14%" nowrap="nowrap"><strong class="fb">数据来源：</strong></td>
          <td width="13%"><html-el:select property="data_src" styleId="data_src" style="width:80px;">
              <html-el:option value="">全部</html-el:option>
              <html-el:option value="1">系统录入</html-el:option>
              <html-el:option value="2">R3数据导入</html-el:option>
            </html-el:select></td>
          <td width="3%"></td>
          <td><html-el:submit value="搜 索" styleClass="bgSearch"/></td>
        </tr>
      </table>
    </html-el:form>
  </div>
  <form id="listForm" name="listForm" method="post" action="KonkaJxcFhBillRegister.do?method=delete">
    <div class="rtabcont1">
      <%@ include file="/commons/pages/messages.jsp" %>
      <!--     <input type="button" name="delete" id="delete" class="bgButton" value="删除全部" onclick="this.form.action += '&' + $('#bottomPageForm').serialize();confirmDeleteAll(this.form);" />&nbsp; -->
      <input type="button" name="add" id="add" class="bgButtonAdd" value="新 增" onClick="location.href='KonkaJxcFhBillRegister.do?method=add&mod_id=${203300}';" />
    </div>
    <div class="rtabcont1">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
        <tr> 
          <!--        <th width="5%" nowrap="nowrap"><input name="chkAll" type="checkbox" id="chkAll" value="-1" onclick="checkAll(this);" /></th>-->
          <th width="5%"nowrap="nowrap">序号</th>
          <th width="10%"nowrap="nowrap">发货编号</th>
          <th width="5%"nowrap="nowrap">发货总数</th>
          <th width="5%"nowrap="nowrap">应收金额（￥）</th>
          <th width="5%"nowrap="nowrap">实收金额（￥）</th>
          <th width="10%"nowrap="nowrap">发货日期</th>
          <th width="8%"nowrap="nowrap">添加人</th>
          <th width="10%"nowrap="nowrap">添加人部门</th>
          <th width="5%"nowrap="nowrap">是否确认收货</th>
          <th width="5%"nowrap="nowrap">数据来源</th>
          <th width="5%"nowrap="nowrap">操作</th>
        </tr>
        <c:forEach items="${entityList}" var="cur" varStatus="vs">
          <tr> 
            <!--         <td align="center">--> 
            <!--              <c:if test="${cur.is_del eq 0}">--> 
            <!--                <input name="pks" type="checkbox" id="pks" value="${cur.id}" />--> 
            <!--              </c:if>--> 
            <!--              <c:if test="${cur.is_del eq 1}">--> 
            <!--                <input name="pks" type="checkbox" id="pks" value="${cur.id}" disabled="disabled"/>--> 
            <!--            </c:if></td>-->
            <td align="center" nowrap="nowrap">${vs.count} </td>
            <td align="center" nowrap="nowrap">${cur.sn} </td>
            <td align="center" nowrap="nowrap">${cur.fh_sum_count} </td>
            <td align="center" nowrap="nowrap">${cur.money_must} </td>
            <td align="center" nowrap="nowrap">${cur.money_result} </td>
            <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.fh_date}" pattern="yyyy-MM-dd" /></td>
            <td align="center" nowrap="nowrap">${cur.add_user_name} </td>
            <td align="center" nowrap="nowrap">${cur.add_dept_name} </td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.is_confirm eq 0}"> <span style="color:#F00;">未确认</span></c:when>
                <c:when test="${cur.is_confirm eq 1}"> <span style="color:#060;">已确认</span></c:when>
              </c:choose></td>
            <td align="center" nowrap="nowrap"><c:choose>
                <c:when test="${cur.data_src eq 1}"> <span>系统录入</span></c:when>
                <c:when test="${cur.data_src eq 2}"> <span>R3数据导入</span></c:when>
              </c:choose></td>
            <td align="center" nowrap="nowrap"><span style="cursor:pointer;"  class="fblue"  onclick="doNeedMethod(null, 'KonkaJxcFhBillRegister.do', 'view','id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">查看</span> 
              <!--             <c:if test="${not empty cur.map.is_pc}">--> 
              <!--             |--> 
              <!--             <span title="已经盘存，不可修改" style="color: gray">修改</span>--> 
              <!--             </c:if>--> 
              <!--             <c:if test="${empty cur.map.is_pc}">--> 
              <!--             <span style="cursor:pointer;" onclick="confirmUpdate(null, 'KonkaJxcFhBillRegister.do', 'id=${cur.id}&mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">修改</span>--> 
              <!--             </c:if>--></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </form>
  <div class="rtabcont3">
    <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaJxcFhBillRegister.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="40" align="right"><script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
            <script type="text/javascript">
            var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
            pager.addHiddenInputs("method", "list");
            pager.addHiddenInputs("mod_id", "${af.map.mod_id}");
            pager.addHiddenInputs("is_confirm", "${af.map.is_confirm}");
            pager.addHiddenInputs("data_src", "${af.map.data_src}");
            pager.addHiddenInputs("sn_like", "${af.map.sn_like}");
            pager.addHiddenInputs("start_date", "${fn:escapeXml(af.map.start_date)}");
            pager.addHiddenInputs("end_date", "${fn:escapeXml(af.map.end_date)}");
            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var f=document.forms[0];
	 $(".bgSearch").click(function(){
	    	var s_time = $("#start_date").val();
			var e_time = $("#end_date").val();
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
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>