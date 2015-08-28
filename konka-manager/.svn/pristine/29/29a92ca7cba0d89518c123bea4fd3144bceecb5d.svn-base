<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳&顺丰网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/sfmall/css/sfmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/sfmall/__inc/top.jsp" flush="true" />
<jsp:include page="/sfmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/sfmall/__inc/nav.jsp" flush="true" />
<!-- second start -->
<div class="maincont">
  <jsp:include page="/sfmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="sfmall_right padbot45">
    <div class="position"><a href="${ctx }/sfmall/Index.do">首页</a> &gt; <a href="${ctx }/sfmall/center/Index.do">会员中心</a> &gt;评论信息</div>
    <div class="sfmalltab3">
      <p style="margin-top:15px;font-size:14px;">评论信息</p>
      <html-el:form action="/center/EcPdEavl">
        <html-el:hidden property="method" styleId="method" value="list" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="sfmall_form_table0">
          <td width="40%"><html-el:text styleClass="input_txt" property="start_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="start_date" ></html-el:text>
              至
              <html-el:text styleClass="input_txt" property="end_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="end_date" ></html-el:text>
            </td>
            <td width="80%"><input class="inputbtn" type="submit" name="Submit" value="查询" />
            </td>
          </tr>
        </table>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="sfmall_form_table2">
        <tr class="tr1">
          <td width="15%" nowrap="nowrap" align="center">商品名称</td>
          <td width="10%" nowrap="nowrap" align="center">评分</td>
          <td width="20%" nowrap="nowrap" align="center">评价信息</td>
          <td width="20%" nowrap="nowrap" align="center">评价时间</td>
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" nowrap="nowrap"><a href="${ctx }/sfmall/PdShow.do?goods_id=${cur.goods_id }"><c:out value="${cur.map.pd_name }"/></a></td>
              <td align="center" nowrap="nowrap"><span class="star${cur.eval_score }"></span></td>
              <td align="left" style="line-height:22px;"><p style="margin-top:2px;font-size:12px;"> <b>标题：</b>
                  <c:out value="${cur.eval_title}"/>
                </p>
                <p style="font-size:12px;"> <b>优点：</b>
                  <c:out value="${cur.eval_con_merit}"/>
                </p>
                <p style="font-size:12px;"> <b>缺点：</b>
                  <c:out value="${cur.eval_con_defect}"/>
                </p>
                <p style="font-size:12px;"> <b>总结：</b>
                  <c:out value="${cur.eval_con_sumary}"/>
                </p></td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.eval_date}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
            </tr>
          </c:forEach>
          <c:if test="${empty entityList }">
            <tr height="60">
              <td align="center" nowrap="nowrap" colspan="4"> 暂无商品评价记录 </td>
            </tr>
          </c:if>
        </tbody>
      </table>
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list");
			                     pager.addHiddenInputs("start_date", "<c:out value='${af.map.start_date}'/>"); 	
			                     pager.addHiddenInputs("end_date", "<c:out value='${af.map.end_date}'/>"); 	
			                     document.write(pager.toString());
			                 </script>
                </div></td>
            </tr>
          </table>
        </form>
      </c:if>
    </div>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/sfmall/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
</html>
