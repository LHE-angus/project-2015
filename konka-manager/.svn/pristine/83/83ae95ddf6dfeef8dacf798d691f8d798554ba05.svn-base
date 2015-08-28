<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>康佳渠道信息管理系统</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/frame3/css/css.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/frame3/css/global.css" />
<script language="javascript" type="text/javascript" src="${ctx}/styles/frame3/js/jquery.js"></script>
</head>
<body>
<div class="right_contcontleft"  style=" border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #C9CFCD;">
  <table  border="0" class="k_tab" style="_width:100%;">
    <tr>
      <td><img src="${ctx}/styles/frame3/images/k_tup.jpg" width="33" height="32" /></td>
      <td nowrap="nowrap" width="97%">&nbsp; 当前位置：首页&gt; 专卖店</td>
    </tr>
  </table>
</div>
<div style=" width:99%">
  <div class="m_l1"><div style="width:99%">
    <div class="k_tongzhi111"><font class="">当年截止到昨日总体情况</font></div>
    <div class="right_contcont2">
      <div class="t11" >
        <div style="margin:10px; margin-top: 50px; height: 89px;"><img src="${ctx}/styles/frame3/images/kehushuliang.jpg" width="94" height="100" />
          <div class="shujul">${sum_count}个</div>
        </div>
        <div style="margin:10px; margin-top: 30px; color: #4b4b4b;">专卖店总数 </div>
        </div><div style="float:left; width: 356px;_width: 380px;"><div class="t1" >
          <div  class="f_dangnian">
            <p><strong>${dy_xz_count}个</strong></p>
 
          </div>
          <div style="margin:10px; margin-top: 10px; color: #4b4b4b;">当月新增数量</div>
          </div><div class="t111" style="margin-left:2px; float: right;" >
            <div  class="lingshouliang">
              <p><strong>${dsp_count}</strong><strong>个</strong></p>
            
            </div>
            <div style="margin:10px; margin-top: 10px; color: #4b4b4b;">待审批专卖店数量</div>
            </div><div class="t354" >
              <div  class="lianshoue354">
                <p><strong>当月审批通过总数：${sp_tg}个</strong></p>

              </div>
            </div></div><div style="float:left; width: 180px; margin-left: 5px;"><div class="t1" >
          <div  class="f_dangnian">
            <p><strong>${hy_count}个</strong></p>
 
          </div>
          <div style="margin:10px; margin-top: 10px; color: #4b4b4b;">会员总数</div>
        </div><div class="t1"  style="margin-top:5px">
              <div  class="lianshoue">
                <p><strong>${dy_hy_xz}个</strong></p>
             
              </div>
              <div style="margin:10px; margin-top: 10px; color: #4b4b4b;">当月新增会员 </div>
              </div></div>
      <div class="clear"></div>
      <div> </div>
      </div>
    <div class="k_tongzhi111" style="margin-bottom:10px"><span style="float:right; font-size: 12px; color: blue;"><a href="${ctx}/manager/zmd/KonkaXxZmdAuditUserInfoAudit.do?mod_id=810200">更多</a></span><font class="">待审批专卖店客户</font></div>
    <div class="right_contcont2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5">
          <tr>
            <th align="center">客户/专卖店</th>
            <th width="15%">申请类别</th>
            <th width="15%">申请人</th>
            <th align="center" width="12%">申请时间</th>
            <th align="center" width="10%">操作</th>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs" begin="0" end="4">
            <tr id="tr_${cur.id}">
              <td align="left" width="40px;">${cur.map.title}</td>
               <td>${cur.eventType}</td>
              <td>${cur.fromPerson}</td>
              <td align="center" nowrap="nowrap"><fmt:formatDate value="${cur.enterDate}" pattern="yyyy/MM/dd" /></td>
              <td align="center" nowrap="nowrap">${cur.eventDo}</td>
            </tr>
            <c:if test="${vs.last eq true}">
              <c:set var="i" value="${vs.count}" />
            </c:if>
          </c:forEach>
          <c:forEach begin="${i}" end="4">
            <tr align="center">
              <td width="40px;">&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </c:forEach>
        </table>
        <div> </div>
      </div>
    <div class="right_contcont2">
      <div> </div>
      </div>
    <div class="clear"></div>
  </div></div>
</div>

<jsp:include page="/__analytics.jsp" />
</body>
</html>
