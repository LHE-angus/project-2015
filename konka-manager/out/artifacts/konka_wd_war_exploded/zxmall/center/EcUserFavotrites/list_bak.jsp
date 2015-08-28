<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳网上直销商城</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global1.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/epp.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/zxmall.css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body>
<!-- head start -->
<div class="topbox">
<jsp:include page="/zxmall/__inc/top.jsp" flush="true" />
<jsp:include page="/zxmall/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/zxmall/__inc/nav.jsp" flush="true" />
<!-- topnav end -->
<!-- second start -->
<div class="maincont">
  <jsp:include page="/zxmall/__inc/_mleft.jsp" flush="true" />
  <!--right-->
  <div class="zxmall_right padbot45">
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 我的关注</div>
    <div class="zxmalltab3">
      <p style="margin-top:15px;font-size:14px;"> 我的关注</p>
      <form action="EcUserFavotrites.do?method=delete" method="post" name="favotForm">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="zxmall_form_table0">
          <c:if test="${not empty entityList}">
            <tr style="border:1px solid #e3e3e3;" >
              <td width="8%" ><input type="checkbox" name="checkbox1" onclick="checkAll(this);">
                全选
                </input></td>
              <td width="15%" nowrap="nowrap" align="center">商品图片</td>
              <td width="15%" nowrap="nowrap" align="center">商品名称</td>
              <td width="8%" nowrap="nowrap" align="center">网友反馈</td>
              <td width="8%" nowrap="nowrap" align="center">单价</td>
              <td width="10%" nowrap="nowrap" align="center">操作</td>
            </tr>
            <tbody>
              <c:forEach var="cur" items="${entityList}" varStatus="vs">
                <tr style="border:1px solid #e3e3e3;" >
                  <td ><input type="checkbox" name="pks" value="${cur.id }"/>
                  </td>
                  <td align="center" nowrap="nowrap"> <a href="${ctx }/zxmall/PdShow.do?goods_id=${cur.goods_id}"><img src="${ctx}/${cur.img_url }" width="81" height="80"/></a></td>
                  <td align="center" nowrap="nowrap"><c:out value="${cur.md_name }" /></td>
                  <td align="center" nowrap="nowrap"><c:if test="${cur.map.ec_pd_eavl_num eq 0 }" >暂无评价</c:if>
                    <c:if test="${cur.map.ec_pd_eavl_num lt 0 }" >共有<font color="#2233ff">${cur.map.ec_pd_eavl_num lt 0 }</font>次评价</c:if>
                  </td>
                  <td align="center" nowrap="nowrap">${cur.price }</td>
                  <td align="center" nowrap="nowrap"><a href="#" id="t${cur.id }" onclick="deleteInfo(this);">取消关注</a></td>
                </tr>
              </c:forEach>
              <tr style="border:1px solid #e3e3e3;" >
                <td colspan="6"><input type="checkbox" name="checkbox2" onclick="checkAll(this);">
                  全选
                  </input>
                  <a href="#" id="t" onclick="deleteInfo(this);">取消所选关注</a></td>
              </tr>
            </tbody>
          </c:if>
          <c:if test="${empty entityList}">
            <tr style="border:1px solid #e3e3e3;height:46px;" >
              <td colspan="6" align="center"> 您还没有关注任何商品,点击<a href="${ctx }/zxmall/Index.do"><font color="#2233ff">这里</font></a>去关注您喜爱的商品吧</td>
            </tr>
          </c:if>
        </table>
      </form>
      <c:if test="${af.map.pager.pageCount>1}">
        <form id="bottomPageForm" name="bottomPageForm" method="post" action="?">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="40"><div style="text-align: right; padding-right: 5px;">
                  <script type="text/javascript">
			                     var pager = new Pager(document.bottomPageForm, parseInt('${af.map.pager.recordCount}'), parseInt('${af.map.pager.pageSize}'), parseInt('${af.map.pager.currentPage}'));
			                     pager.addHiddenInputs("method", "list"); 
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
<jsp:include page="/zxmall/__inc/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){ 
});
function deleteInfo(obj){
	var	id ="&id="+ obj.id.replace("t",""); 
	if(confirm("您确定取消关注吗？")){
		if(obj.id=="t"){
			var boxs = document.getElementsByName("pks"); 
			var checked_counts = 0;
			for(var i=0;i<boxs.length;i++){
				if(boxs[i].checked){
					checked_counts++;
				}
			}
			
			if(checked_counts==0){
				alert("请至少选中一个商品！");
				return false;
			}
			document.favotForm.submit();
		}else{
		location.href="${ctx}/zxmall/center/EcUserFavotrites.do?method=delete"+id;
		}
	}
}
 
//]]></script>
</html>
