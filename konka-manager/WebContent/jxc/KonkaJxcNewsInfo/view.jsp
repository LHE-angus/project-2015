<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳进销存 &gt; 系统信息</title>
<style type="text/css">
<!--
body {padding:0;  line-height:26px; font-size:12px;color:#333;font-family:宋体;}
body,div,dl,dt,dd,ul,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,img,textarea,blockquote,p {padding:0; margin:0;} 
input,select,textarea,table,td {font-size:12px;}  
.newsfont18{ font-size:22px; font-weight:bold; line-height:60px;}
a,a:visited{color:#1b1b1b; text-decoration:none;}
a:hover{color:#ff4e00; text-decoration:none;}
-->
</style>
</head>
<body>
 <table width="94%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
        <tr>
          <td height="68" align="center" class="newsfont18"> ${fn:escapeXml(af.map.title)}</td>
        </tr>
        <tr>
          <td height="28" align="center" style="border-top:1px solid #eee;border-bottom:1px solid #eee;color:#666">更新时间：
            <fmt:formatDate value="${af.map.add_date}" pattern="yyyy-MM-dd HH:mm"/>
            信息来源：${fn:escapeXml(af.map.source)}　　字体选择：[<a href="javascript:doZoom('16px');">大</a> <a href="javascript:doZoom('14px');">中</a> <a href="javascript:doZoom('12px');">小</a>]</td>
        </tr>
      </table>
      <table width="681" border="0" align="center" cellpadding="0" cellspacing="0" style="padding-bottom:15px;margin-bottom:10px;">
        <tr>
          <td><c:if test="${not empty (af.map.img_path)}"> <img src="${ctx}/${fn:substringBefore(af.map.img_path, '.')}_600.jpg" alt="" title="${fn:escapeXml(af.map.image_desc)}" /></c:if></td>
        </tr>
        <tr>
          <td width="681" align="left" valign="top" id="newscontent"><p style="margin:0 auto;">${af.map.content} </p></td>
        </tr>
        <tr valign="top">
          <td><c:if test="${not empty (attachmentList)}">
              <fieldset style="margin:5px; padding:10px; border:1px solid #ccc;">
                <legend style="font-size:12px;">附件下载</legend>
                <c:forEach var="cur" items="${attachmentList}" varStatus="vs"><a href="${ctx}/manager/admin/Download.do?method=downloadFile&save_name=${cur.save_name}">${cur.file_name}</a><br />
                </c:forEach>
              </fieldset>
            </c:if></td>
        </tr>
        <tr>
          <td align="center"  style="border-top:1px solid #E0E0E0;padding-top:10px;"><a href="javascript:printWindow();">【 打印此页 】</a></td>
        </tr>
      </table>
<script type="text/javascript" src="${ctx}/scripts/news.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<jsp:include page="../public_page.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>
