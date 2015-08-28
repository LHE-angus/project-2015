<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>康佳</title>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/base.css"/>
<link rel="stylesheet" href="${ctx}/styles/zxmall/2015/css/index.css"/> 

<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/purchase.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/citybox.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/ping.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/customer/cloud-zoom/styles/image_show.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
</head>
<body id="web_body">
<jsp:include page="/zxmall/__inc/2015/top.jsp" flush="true" />  
<jsp:include page="/zxmall/__inc/2015/nav.jsp" flush="true" />  
<div class="main" style="padding-top:0px;">
<div class="w1200">
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt;评论信息</div>
    <div class="zxmalltab3">
      <p style="margin-top:15px;font-size:14px;">评论信息</p>
      <html-el:form action="/center/EcPdEavl">
        <html-el:hidden property="method" styleId="method" value="list" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"  class="zxmall_form_table0">
          <td width="40%"><html-el:text styleClass="input_txt" property="start_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="start_date" ></html-el:text>
              至
              <html-el:text styleClass="input_txt" property="end_date" size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="width:120px;cursor:pointer;text-align:center;" title="点击选择日期" styleId="end_date" ></html-el:text>
            </td>
            <td width="80%"><input class="inputbtn" type="submit" name="Submit" value="查询" />
            </td>
          </tr>
        </table>
      </html-el:form>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table2">
        <tr class="tr1">
          <td width="15%" nowrap="nowrap" align="center">商品名称</td>
          <td width="10%" nowrap="nowrap" align="center">评分</td>
          <td width="40%" nowrap="nowrap" align="center">评价信息</td> 
        </tr>
        <tbody>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr height="60">
              <td align="center" nowrap="nowrap"><a href="${ctx }/zxmall/PdShow.do?goods_id=${cur.goods_id }"><c:out value="${cur.map.pd_name }"/></a></td>
              <td align="center" nowrap="nowrap"><span class="star${cur.eval_score }"></span></td>
              <td align="left" style="line-height:22px;"> 
               <c:out value="${cur.eval_title}"/> &nbsp;&nbsp;&nbsp;<fmt:formatDate value="${cur.eval_date}" pattern="yyyy-MM-dd hh:mm:ss" />
               <br/><c:out value="${cur.eval_con_sumary}"/>
               <c:if test="${not empty cur.konkaPeAttachmentsList}"><div>			    
			      <c:forEach var="imgs" items="${cur.konkaPeAttachmentsList}" varStatus="vsi"> 
			       <a href="${ctx}/${imgs.save_path}" target="_blank"><img src="${ctx}/${imgs.save_path}" width="60"></img></a> 
			      </c:forEach> </div>
			  </c:if>	
			  <c:if test="${not empty cur.re_content}">
		      	  <p><span style="font-size:12px;line-height:40px;">客服回复</span>： <font color="red"><c:out value="${cur.re_content}"/></font></p>
		      	  </c:if>	
              </td> 
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
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
<!-- six footer -->
</body>
</html>
