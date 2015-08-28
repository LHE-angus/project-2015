<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
   <!--left-->
    <div class="member_left">
    	<div class="member_tit1">我的帐户</div>
        <div class="membertab1">
        <h3 class="membertit2">订单中心</h3>
        <ul class="memberul1">
        <li><a href="Orders.do">我的订单</a></li>
        <li><a href="EcLuckyBuy.do">我的抽奖</a></li>
        <c:if test="${touch eq 1}"><li><a href="EcGiftJfBuy.do">积分充值</a></li></c:if>
        <!-- li><a href="TuanOrders.do">我的团购</a></li -->
        <li><a href="EcUserFavotrites.do">我的关注</a></li>
        <c:if test="${touch eq 1}">  <li><a href="EcUserAddrs.do">地址簿</a></li></c:if>
        <!--  li><a href="#">退货单</a></li -->
        <!--  li><a href="#">换货单</a></li-->
        </ul>
        </div>
        <c:if test="${touch eq 1 }">
        <div class="membertab1">
        <h3 class="membertit2">帐户中心  </h3>
        <ul class="memberul1">
        <li><a href="User.do">账号信息</a></li>
        <li><a href="EcUserScoreRec.do">我的积分</a></li>
        <li><a href="EcGroupBuy.do">我的团购</a></li>
        <li><a href="EcVouchers.do">我的购物券</a></li>
        <li><a href="EcRebates.do">我的佣金</a></li>
        <li><a href="EcBcompPdRebates.do">商品佣金查询</a></li>
        <li><a href="Pwd.do">登录密码设置</a></li>
        <li><a href="PayPwd.do">支付密码设置</a></li>
        <c:if test="${ecUser.user_type eq 1}">
        <li><a href="Skip.do?method=exit"><font color="red">退出账户中心</font></a></li>
        </c:if>
        </ul>
        </div>
        <div class="membertab1">
        <h3 class="membertit2">我的信息</h3>
        <ul class="memberul1">
        <li><a href="EcPdEavl.do">评论信息</a></li>
        <li><a href="EcQaInfo.do">咨询信息</a></li>
        <li><a href="EcGiftOrde.do">积分换礼品</a></li>
        </ul>
        </div> 
        </c:if>
        <c:if test="${ecUser.user_type eq 1 and touch ne 1 }">
          <!--  div class="membertab1">
	        <h3 class="membertit2">帐户中心</h3>
	        <ul class="memberul1"> 
	        <li><a href="Skip.do"><font color="red">进入账户中心</font></a></li> 
	        </ul>
	     </div--> </c:if> <c:if test="${ecUser.user_type eq 1 }">
	      <c:if test="${touch eq 1}">
        <div class="membertab1">
        <h3 class="membertit2">会员规则</h3>
        <ul class="memberul1">
        <script src="${ctx }/member/KonkaGroupPeArticleInfo.do?method=jsNewsLi&article_type=2700"></script> 
        </ul>
        </div>
         </c:if></c:if>
    </div>