<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"><\/script >'); </script>
<script type="text/javascript"> !window.jQuery && document.write('<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"><\/script >'); </script>
<script type="text/javascript" src="${ctx}/scripts/slides4epp.js"></script>

<div class="nav_advcont1">
  <div class="nav_bigadv">
    <div id="full-screen-slider">
      <ul id="slides">
      	<c:forEach var="cur" items="${imgList}">
        	<li style="background:url('${ctx}/${cur.image_path}') no-repeat center top"><a href="/${cur.image_url}" target="_blank">${cur.title}</a></li>
		</c:forEach>
      </ul>
    </div>
  </div>
  <div class="nav_clear"></div>
</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	 
});
//]]></script>