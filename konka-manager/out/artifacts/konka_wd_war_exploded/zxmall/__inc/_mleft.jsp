<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
   <!--left-->
    <div class="zxmall_left">
    	<div class="zxmall_tit1">我的帐户</div>
        <div class="zxmalltab1">
        <h3 class="zxmalltit2">订单中心</h3>
        <ul class="zxmallul1">
        <li><a href="Orders.do">我的订单</a></li>
        <c:if test="${zxmall.is_allowed eq 0 and touch eq 1}">   
        <li><a href="EcBaseCardApply.do">我的会员申请</a></li>
        <li><a href="EcBaseSubCard.do">下级会员管理</a></li>
        <li><a href="OrdersForSub.do">下级会员订单查看</a></li>
        </c:if>
        <!--
        <li><a href="EcLuckyBuy.do">我的抽奖</a></li>
        <!-- li><a href="TuanOrders.do">我的团购</a></li -->
        <li><a href="EcUserFavotrites.do">我的关注</a></li>
        <c:if test="${touch eq 1}">  <li><a href="EcUserAddrs.do">地址簿</a></li></c:if>
        </ul>
        </div>
        <c:if test="${touch eq 1 }">
        <div class="zxmalltab1">
        <h3 class="zxmalltit2">帐户中心  </h3>
        <ul class="zxmallul1">
        <li><a href="User.do">账号信息</a></li>
        <li><a href="EcUserScoreRec.do">我的积分</a></li>
        <li><a href="EcVouchers.do">我的购物券</a></li>
        
        <li><a href="EcRebates.do">我的佣金</a></li>
        <li><a href="EcGoodsRebate.do">商品佣金查询</a></li>
        <li><a href="Pwd.do">登录密码设置</a></li>
        <li><a href="PayPwd.do">支付密码设置</a></li>
        <c:if test="${zxmall.user_type eq 2}">
        <li><a href="Skip.do?method=exit"><font color="red">退出账户中心</font></a></li>
        </c:if>
        </ul>
        </div>
        <div class="zxmalltab1">
        <h3 class="zxmalltit2">我的信息</h3>
        <ul class="zxmallul1">
        <li><a href="EcPdEavl.do">评论信息</a></li>
        <li><a href="EcQaInfo.do">咨询信息</a></li>
        <li><a href="EcGiftOrde.do">积分换礼品</a></li>
        </ul>
        </div> 
        </c:if>
        <c:if test="${zxmall.user_type eq 1 and touch ne 1 }">
          <!--  div class="zxmalltab1">
	        <h3 class="zxmalltit2">帐户中心</h3>
	        <ul class="zxmallul1"> 
	        <li><a href="Skip.do"><font color="red">进入账户中心</font></a></li> 
	        </ul>
	     </div--> </c:if> <c:if test="${zxmall.user_type eq 2 }">
	      <c:if test="${touch eq 1}">
        <div class="zxmalltab1">
        <h3 class="zxmalltit2">会员规则</h3>
        <ul class="zxmallul1">
        <script src="${ctx }/zxmall/KonkaGroupPeArticleInfo.do?method=jsNewsLi&article_type=2700"></script> 
        </ul>
        </div>
         </c:if></c:if>
    </div>