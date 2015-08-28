<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:if test="${ecUser.user_type eq 1 }">康佳EPP内部优惠购机平台</c:if><c:if test="${ecUser.user_type eq 2 }">康佳网上直销商城</c:if></title>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/global1.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/member/css/epp.css" />  
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/css/slider.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/sjb/css/global.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/member/sjb/css/worldcup.css" />
<style type="text/css">

</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<!-- head start -->
<!-- top start -->
<div class="topbox">
<jsp:include page="/member/__inc/top.jsp" flush="true" />
<jsp:include page="/member/__inc/search.jsp" flush="true" />
</div> 
<jsp:include page="/member/__inc/nav.jsp" flush="true" />
<!-- top end --> 
<!-- first end --> 
<!-- second start -->
<div class="maincont">
	<div class="l"><img src="../styles/member/sjb/images/wcuptop_1.jpg" /></div>
    <div class="topmid">
    	<div class="l"><img alt="2014巴西世界杯 世界最强球队猜想 猜冠军球队赢双倍大奖" src="../styles/member/sjb/images/wcuptop_2.jpg" /></div>
    	<div class="topnav"><ul class="topnavul"><li><a href="#main1">球队介绍</a></li><li><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826079' />">赛程查询</a></li><li><a href="#main3">赛事结果</a></li><li><a href="#main4">竞猜</a></li><li><a href="#main5">竞猜规则</a></li><li><a href="#main6">竞猜积分榜</a></li></ul></div>
  </div>
    <div class="l"><img src="../styles/member/sjb/images/wcuptop_3.jpg" /></div>
	<div class="clear"></div>
</div>
<!--head end-->
<!--first start-->
<c:forEach items="${entityList}" var="cur" varStatus="vs">
<div class="maincont">
	<div <c:if test="${vs.count eq 1}">class="left1"</c:if><c:if test="${vs.count eq 2}">class="left2"</c:if><c:if test="${vs.count eq 3}">class="left3"</c:if>><img src="../styles/member/sjb/images/leftbg_${vs.count}.jpg" /></div>
    <div <c:if test="${vs.count eq 1}">class="midbox1"</c:if><c:if test="${vs.count eq 2}">class="midbox2"</c:if><c:if test="${vs.count eq 3}">class="midbox3"</c:if>>
    	<c:if test="${vs.count eq 1}">
    	<div class="tit_guess"><img alt="赛前竞猜" src="../styles/member/sjb/images/tit_guess.gif" /></div>
        </c:if>
        <form action="<c:url value='/member/Sjb.do' />" id="sjb_${cur.match_id}" method="post">
        <input type="hidden" name="method" value="save" />
    	<input type="hidden" name="id"  value="${cur.match_id}" />
        <div <c:if test="${vs.count eq 1}">class="vstable1"</c:if><c:if test="${vs.count eq 2}">class="vstable2"</c:if><c:if test="${vs.count eq 3}">class="vstable3"</c:if>>
        	<div class="ttime">
            	<div class="ltime">${cur.map.month}月${cur.map.day}日</div>
                <div class="mtime">${cur.map.hour}：${cur.map.minute}   北京时间   ${cur.match_desc}</div>
                <div class="rtime">${cur.match_addr}</div>
                <div class="clear"></div>
            </div>
            <div class="lefteam">
            	<div class="team_pic">
            	<a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=${cur.a_team_id}' />">
            	<img  src="${ctx}/${fn:substringBefore(cur.map.team_main_pic_1, '.')}_191.jpg" />
            	</a>
            	</div>
                <div class="team_name"><img width="30px" height="30px" src="${ctx}/${cur.map.team_pic_1}" /> ${cur.map.team_name_1}</div>
            </div>
            <div class="midresult">
            	<div class="vstit"><img alt="vs" src="../styles/member/sjb/images/vs.png" /></div>
                <ul <c:if test="${vs.count eq 1}">class="vstab1"</c:if><c:if test="${vs.count eq 2}">class="vstab1"</c:if><c:if test="${vs.count eq 3}">class="vstab1"</c:if>>
                	<li>
                	<img src="../styles/member/sjb/images/victory.gif" /><br/>
                	<input type="radio" name="is_win" class="is_win_${cur.match_id}" id="is_win_${cur.match_id}" value="0" />
                	</li>
                    <li class="draw"> <img src="../styles/member/sjb/images/draw.gif" /><br/>
                    <input type="radio" name="is_win" class="is_win_${cur.match_id}" id="is_win_${cur.match_id}" value="1" />
                    </li>
                    <li class="fail"><img src="../styles/member/sjb/images/fail.gif" /><br/>
                    <input type="radio" name="is_win" class="is_win_${cur.match_id}"  id="is_win_${cur.match_id}" value="2" />
                    </li>
                </ul>
                 <div class="vs_result">
                	<ul class="vs_resultul">
                    <li>
                      <label>
                      <input class="a_team_result_${cur.match_id}" name="a_team_result" type="text"   maxlength="3" onfocus="javascript:setOnlyInt(this);" style="width:72px;height:25px;border:1px solid #e8e8e8;font-size:24px;font-weight:bold;font-family:Arial, Helvetica, sans-serif;color:#1f55a5;line-height:27px;text-align:center;"/>
                      </label>
                    </li>
                    <li class="rcenter">:</li>
                    <li class="rnumber"><input type="text" class="b_team_result_${cur.match_id}" name="b_team_result" maxlength="3" onfocus="javascript:setOnlyInt(this);" style="width:72px;height:25px;border:1px solid #e8e8e8;font-size:24px;font-weight:bold;font-family:Arial, Helvetica, sans-serif;color:#1f55a5;line-height:27px;text-align:center;"/></li>
                    </ul>
              </div>
                <div class="but_submit"><a href="#"><img alt="提交" src="../styles/member/sjb/images/but_submit.gif"  data="${cur.match_id}" id="${cur.match_id}"  class="submit"/></a></div>
            </div>
            <div class="lefteam">
            	<div class="team_pic">
            	<a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=${cur.b_team_id}' />">
            	<img src="${ctx}/${fn:substringBefore(cur.map.team_main_pic_2, '.')}_191.jpg" />
            	</a>
            	</div>
                <div class="team_name"><img width="30px" height="30px" src="${ctx}/${cur.map.team_pic_2}" /> ${cur.map.team_name_2}</div>
            </div>
        </div>
        </form>
    </div>
    <div <c:if test="${vs.count eq 1}">class="right1"</c:if><c:if test="${vs.count eq 2}">class="right2"</c:if><c:if test="${vs.count eq 3}">class="right3"</c:if>><img src="../styles/member/sjb/images/rightbg${vs.count}.jpg" /></div>
	<div class="clear"></div>
</div>
</c:forEach>
<!--first end-->
<!--second start-->

<!--second end-->
<!--third start-->

<!--third end-->
<!--four start-->
<div class="maincont">
	<div class="left4"><img src="../styles/member/sjb/images/leftbg_4.jpg" /></div>
    <div class="midbox4" id="main3">
	<div class="tit_integral">小组赛积分</div>
      <div class="integraltab1">
         	<ul class="integteamul">
            <li class="curteam pd_content" id="A">A</li>
            <li class="pd_content" id="B">B</li>
            <li class="pd_content" id="C">C</li>
            <li class="pd_content" id="D">D</li>
            </ul>
        	<table id="A_A" width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel" style="display:'';overflow-x:hidden;">
              <tr>
                <th width="15%">排名</th>
                <th width="23%">球队</th>
                <th width="7%">场次</th>
                <th width="7%">胜</th>
                <th width="7%">平</th>
                <th width="7%">负</th>
                <th width="6%">进</th>
                <th width="6%">失</th>
                <th width="11%">净胜球</th>
                <th width="11%">积分</th>
              </tr>
              <c:forEach items="${groupAlist}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><span <c:if test="${vs.count eq 1}">class="number1"</c:if><c:if test="${vs.count eq 2}">class="number2"</c:if> >${vs.count}</span></td>
                <td>${cur.map.team_name_1}</td>
                <td>${cur.map.total_count}</td>
                <td>${cur.map.win_count}</td>
                <td>${cur.map.ping_count}</td>
                <td>${cur.map.shibai_count}</td>
                <td>${cur.map.win_goal}</td>
                <td>${cur.map.shu_goal}</td>
                 <td><c:if test="${empty cur.map.js_goal}">0</c:if><c:if test="${not empty cur.map.js_goal}">${cur.map.js_goal}</c:if></td>
                <td>${cur.map.total_score}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty groupAlist}">
               <tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td>3</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td>4</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              </c:if>
            </table>
            <table id="B_B" width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel" style="display:none;overflow-x:hidden;">
              <tr>
                <th width="15%">排名</th>
                <th width="23%">球队</th>
                <th width="7%">场次</th>
                <th width="7%">胜</th>
                <th width="7%">平</th>
                <th width="7%">负</th>
                <th width="6%">进</th>
                <th width="6%">失</th>
                <th width="11%">净胜球</th>
                <th width="11%">积分</th>
              </tr>
              <c:forEach items="${groupBlist}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><span <c:if test="${vs.count eq 1}">class="number1"</c:if><c:if test="${vs.count eq 2}">class="number2"</c:if> >${vs.count}</span></td>
                <td>${cur.map.team_name_1}</td>
                <td>${cur.map.total_count}</td>
                <td>${cur.map.win_count}</td>
                <td>${cur.map.ping_count}</td>
                <td>${cur.map.shibai_count}</td>
                <td>${cur.map.win_goal}</td>
                <td>${cur.map.shu_goal}</td>
                 <td><c:if test="${empty cur.map.js_goal}">0</c:if><c:if test="${not empty cur.map.js_goal}">${cur.map.js_goal}</c:if></td>
                <td>${cur.map.total_score}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty groupBlist}">
               <tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td>3</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td>4</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              </c:if>
            </table>
            <table id="C_C" width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel" style="display:none;overflow-x:hidden;">
              <tr>
                <th width="15%">排名</th>
                <th width="23%">球队</th>
                <th width="7%">场次</th>
                <th width="7%">胜</th>
                <th width="7%">平</th>
                <th width="7%">负</th>
                <th width="6%">进</th>
                <th width="6%">失</th>
                <th width="11%">净胜球</th>
                <th width="11%">积分</th>
              </tr>
              <c:forEach items="${groupClist}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><span <c:if test="${vs.count eq 1}">class="number1"</c:if><c:if test="${vs.count eq 2}">class="number2"</c:if> >${vs.count}</span></td>
                <td>${cur.map.team_name_1}</td>
                <td>${cur.map.total_count}</td>
                <td>${cur.map.win_count}</td>
                <td>${cur.map.ping_count}</td>
                <td>${cur.map.shibai_count}</td>
                <td>${cur.map.win_goal}</td>
                <td>${cur.map.shu_goal}</td>
                  <td><c:if test="${empty cur.map.js_goal}">0</c:if><c:if test="${not empty cur.map.js_goal}">${cur.map.js_goal}</c:if></td>
                <td>${cur.map.total_score}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty groupClist}">
               <tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td>3</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td>4</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              </c:if>
            </table>
            <table id="D_D" width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel" style="display:none;overflow-x:hidden;">
              <tr>
                <th width="15%">排名</th>
                <th width="23%">球队</th>
                <th width="7%">场次</th>
                <th width="7%">胜</th>
                <th width="7%">平</th>
                <th width="7%">负</th>
                <th width="6%">进</th>
                <th width="6%">失</th>
                <th width="11%">净胜球</th>
                <th width="11%">积分</th>
              </tr>
              <c:forEach items="${groupDlist}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><span <c:if test="${vs.count eq 1}">class="number1"</c:if><c:if test="${vs.count eq 2}">class="number2"</c:if> >${vs.count}</span></td>
                <td>${cur.map.team_name_1}</td>
                <td>${cur.map.total_count}</td>
                <td>${cur.map.win_count}</td>
                <td>${cur.map.ping_count}</td>
                <td>${cur.map.shibai_count}</td>
                <td>${cur.map.win_goal}</td>
                <td>${cur.map.shu_goal}</td>
                  <td><c:if test="${empty cur.map.js_goal}">0</c:if><c:if test="${not empty cur.map.js_goal}">${cur.map.js_goal}</c:if></td>
                <td>${cur.map.total_score}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty groupDlist}">
               <tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td>3</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td>4</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              </c:if>
            </table>
        </div>
        <div class="integraltab1">
         	<ul class="integteamul">
            <li class="curteam pd_content_2" id="E">E</li>
            <li class="pd_content_2" id="F">F</li>
            <li class="pd_content_2" id="G">G</li>
            <li class="pd_content_2" id="H">H</li>
            </ul>
        	<table id="E_E" width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel" style="display:'';overflow-x:hidden;">
              <tr>
                <th width="15%">排名</th>
                <th width="23%">球队</th>
                <th width="7%">场次</th>
                <th width="7%">胜</th>
                <th width="7%">平</th>
                <th width="7%">负</th>
                <th width="6%">进</th>
                <th width="6%">失</th>
                <th width="11%">净胜球</th>
                <th width="11%">积分</th>
              </tr>
              <c:forEach items="${groupElist}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><span <c:if test="${vs.count eq 1}">class="number1"</c:if><c:if test="${vs.count eq 2}">class="number2"</c:if> >${vs.count}</span></td>
                <td>${cur.map.team_name_1}</td>
                <td>${cur.map.total_count}</td>
                <td>${cur.map.win_count}</td>
                <td>${cur.map.ping_count}</td>
                <td>${cur.map.shibai_count}</td>
                <td>${cur.map.win_goal}</td>
                <td>${cur.map.shu_goal}</td>
                  <td><c:if test="${empty cur.map.js_goal}">0</c:if><c:if test="${not empty cur.map.js_goal}">${cur.map.js_goal}</c:if></td>
                <td>${cur.map.total_score}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty groupElist}">
               <tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td>3</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td>4</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              </c:if>
            </table>
            <table id="F_F" width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel" style="display:none;overflow-x:hidden;">
              <tr>
                <th width="15%">排名</th>
                <th width="23%">球队</th>
                <th width="7%">场次</th>
                <th width="7%">胜</th>
                <th width="7%">平</th>
                <th width="7%">负</th>
                <th width="6%">进</th>
                <th width="6%">失</th>
                <th width="11%">净胜球</th>
                <th width="11%">积分</th>
              </tr>
              <c:forEach items="${groupFlist}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><span <c:if test="${vs.count eq 1}">class="number1"</c:if><c:if test="${vs.count eq 2}">class="number2"</c:if> >${vs.count}</span></td>
                <td>${cur.map.team_name_1}</td>
                <td>${cur.map.total_count}</td>
                <td>${cur.map.win_count}</td>
                <td>${cur.map.ping_count}</td>
                <td>${cur.map.shibai_count}</td>
                <td>${cur.map.win_goal}</td>
                <td>${cur.map.shu_goal}</td>
                  <td><c:if test="${empty cur.map.js_goal}">0</c:if><c:if test="${not empty cur.map.js_goal}">${cur.map.js_goal}</c:if></td>
                <td>${cur.map.total_score}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty groupFlist}">
               <tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td>3</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td>4</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              </c:if>
            </table>
            <table id="G_G" width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel" style="display:none;overflow-x:hidden;">
              <tr>
                <th width="15%">排名</th>
                <th width="23%">球队</th>
                <th width="7%">场次</th>
                <th width="7%">胜</th>
                <th width="7%">平</th>
                <th width="7%">负</th>
                <th width="6%">进</th>
                <th width="6%">失</th>
                <th width="11%">净胜球</th>
                <th width="11%">积分</th>
              </tr>
              <c:forEach items="${groupGlist}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><span <c:if test="${vs.count eq 1}">class="number1"</c:if><c:if test="${vs.count eq 2}">class="number2"</c:if> >${vs.count}</span></td>
                <td>${cur.map.team_name_1}</td>
                <td>${cur.map.total_count}</td>
                <td>${cur.map.win_count}</td>
                <td>${cur.map.ping_count}</td>
                <td>${cur.map.shibai_count}</td>
                <td>${cur.map.win_goal}</td>
                <td>${cur.map.shu_goal}</td>
                <td><c:if test="${empty cur.map.js_goal}">0</c:if><c:if test="${not empty cur.map.js_goal}">${cur.map.js_goal}</c:if></td>
                <td>${cur.map.total_score}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty groupGlist}">
               <tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td>3</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td>4</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              </c:if>
            </table>
            <table id="H_H" width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel" style="display:none;overflow-x:hidden;">
              <tr>
                <th width="15%">排名</th>
                <th width="23%">球队</th>
                <th width="7%">场次</th>
                <th width="7%">胜</th>
                <th width="7%">平</th>
                <th width="7%">负</th>
                <th width="6%">进</th>
                <th width="6%">失</th>
                <th width="11%">净胜球</th>
                <th width="11%">积分</th>
              </tr>
              <c:forEach items="${groupHlist}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><span <c:if test="${vs.count eq 1}">class="number1"</c:if><c:if test="${vs.count eq 2}">class="number2"</c:if> >${vs.count}</span></td>
                <td>${cur.map.team_name_1}</td>
                <td>${cur.map.total_count}</td>
                <td>${cur.map.win_count}</td>
                <td>${cur.map.ping_count}</td>
                <td>${cur.map.shibai_count}</td>
                <td>${cur.map.win_goal}</td>
                <td>${cur.map.shu_goal}</td>
                  <td><c:if test="${empty cur.map.js_goal}">0</c:if><c:if test="${not empty cur.map.js_goal}">${cur.map.js_goal}</c:if></td>
                <td>${cur.map.total_score}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty groupHlist}">
               <tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr>
                <td>3</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              <tr class="orgtd">
                <td>4</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
              </c:if>
            </table>
        </div>
        <div class="integraltab1" id="main6">
        	<div class="tit_integral2">积分榜</div>
        	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel">
              <tr>
                <th width="14%">排名</th>
                <th width="15%">工卡号</th>
                <th width="23%">姓名</th>
                <th width="17%">猜中输赢</th>
                <th width="17%">猜中比分</th>
                <th width="14%">积分</th>
              </tr>
              <c:forEach items="${jfList}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><span <c:if test="${vs.count eq 1}">class="number1"</c:if><c:if test="${vs.count eq 2}">class="number2"</c:if>>${vs.count}</span></td>
                <td>${cur.map.card_no}</td>
                <td>${cur.map.real_name}</td>
                <td>${cur.map.ww2_count}</td>
                <td>${cur.map.ww1_count}</td>
                <td>${cur.map.ww3_count}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty jfList}">
              	<tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                </tr>
                <tr>
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                </tr>
                 <tr>
                <td><span >3</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                </tr>
                 <tr>
                <td><span >4</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                </tr>
              </c:if>
            </table>
        </div>
        <div class="integraltab1" id="main4">
        	<div class="tit_integral2">个人竞猜查询</div>
        	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="gametabel">
              <tr>
                <th width="16%">竞猜时间</th>
                <th width="40%">竞猜记录</th>
                <th width="22%">竞猜积分</th>
                <th width="22%">个人积分</th>
              </tr>
              <c:forEach items="${grList}" var="cur" varStatus="vs">
              <tr <c:if test="${vs.count mod 2 eq 0}"> class="orgtd" </c:if>>
                <td><fmt:formatDate value="${cur.guess_date}" pattern="MM-dd" /></td>
                <td>${cur.map.team_a}-${cur.map.team_b}(
                <c:if test="${cur.guess_win eq 0}">${cur.map.team_a}赢</c:if> 
                <c:if test="${cur.guess_win eq 2}">${cur.map.team_b}赢</c:if>
                <c:if test="${cur.guess_win eq 1}">平局</c:if>
                 <c:if test="${not empty cur.guess_a_team_goal}">
                 	<c:if test="${not empty cur.guess_b_team_goal}">
                 		${cur.guess_a_team_goal}:${cur.guess_b_team_goal}
                 	</c:if>
                 </c:if>
                )</td>
                <td>${cur.map.total_jf}</td>
                <td>${cur.map.total_score}</td>
              </tr>
              </c:forEach>
              <c:if test="${empty grList}">
              	<tr>
                <td><span class="number1">1</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                </tr>
                <tr>
                <td><span class="number2">2</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                </tr>
                 <tr>
                <td><span >3</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                </tr>
                 <tr>
                <td><span >4</span></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                </tr>
              </c:if>
            </table>
        </div>
    </div>
    <div class="right4"><img src="../styles/member/sjb/images/rightbg4.jpg" /></div>
	<div class="clear"></div>
</div>
<!--four end-->
<!--five start-->
<div class="maincont" id="main5">
	<div class="left5"><img src="../styles/member/sjb/images/leftbg_5.jpg" /></div>
    <div class="midbox5">
    	<h2 class="tit_intro">竞猜详解</h2>
        <div class="introtab">
        	<p>1.竞猜原则：每场比赛每个工号只可竞猜一次（登录密码、支付密码均可参与）；</p>
            <p>2.竞猜时间：6.12-7.14赛前一小时停止竞猜；</p>
            <p class="orgfont">3.竞猜方式：猜单场输赢、猜单场比分两种方式（可同时参加）；</p>
            <p>4.竞猜积分：猜输赢：猜对一场获30奖励积分，猜错不扣分；</p>
            <p><span style="margin-left:80px;">猜比分：猜对一场比分获50奖励积分，猜错一场比分扣减15分；</span></p>
            <p>5.竞猜奖项：积分累积叠加，根据积分榜排名，在世界杯关键节点进行竞猜颁奖；</p>
            <p>6.奖项设置：根据世界杯进程，按照积分榜积分名次，给予相应奖品设置：</p>
        </div>
		<div class="tit_rule">竞猜奖项</div>
        <div class="ruletab">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ruletable">
              <tr>
                <th width="13%">时间</th>
                <th width="15%">节点</th>
                <th width="18%">金奖<br />
                （第1名）</th>
                <th width="18%">银奖<br />
                （第2-4名）</th>
                <th width="18%">铜奖<br />
                （第5-10名）</th>
                <th width="18%">幸运奖<br />
                （第11-20名）</th>
              </tr>
              <tr>
                <td>6.20</td>
                <td>小组赛半程</td>
                <td>热水壶及优<br />
                惠券100元</td>
                <td>优惠券100元</td>
                <td>优惠券50元</td>
                <td>优惠券20元</td>
              </tr>
              <tr class="red1td">
                <td>6.27</td>
                <td>小组赛完赛</td>
                <td>电饭煲及优<br />
                惠券100元</td>
                <td>优惠券120元</td>
                <td>优惠券80元</td>
                <td>优惠券40元</td>
              </tr>
              <tr class="red2td">
                <td>7.2</td>
                <td>1/8决赛完赛</td>
                <td>养生壶及优<br />
                惠券150元</td>
                <td>优惠券150元</td>
                <td>优惠券100元</td>
                <td>优惠券60元</td>
              </tr>
              <tr class="red1td">
                <td>7.10</td>
                <td>1/2决赛完赛</td>
                <td>电风扇及优<br />
                惠券200元</td>
                <td>优惠券200元</td>
                <td>优惠券150元</td>
                <td>优惠券80元</td>
              </tr>
              <tr>
                <td>7.14</td>
                <td>总决赛完赛</td>
                <td>电视机及优<br />
                惠券300元</td>
                <td>优惠券300元</td>
                <td>优惠券200元</td>
                <td>优惠券100元</td>
              </tr>
            </table>
        </div>
    </div>
    <div class="right5"><img src="../styles/member/sjb/images/rightbg5.jpg" /></div>
	<div class="clear"></div>
</div>
<!--five end-->
<!--six start-->
<div class="maincont">
	<div class="left7"><img src="../styles/member/sjb/images/leftbg_7.jpg" /></div>
    <div class="midbox7">
    	<h3 class="tit_directions">奖项说明：</h3>
        <div class="directab">
        	<p>1.以上奖项名单公布后，奖品当天发放；</p>
            <p>2.以上购物券有效使用时间为2014年7月31日，仅限购买新品区、精品区、热卖区电视；</p>
            <p>3.总决赛完赛后，根据积分榜最终分数赠送积分；</p>
        </div>
        <h2 class="tit_intro2">特价机抢购详解</h2>
        <div class="tit_rule">比赛日当天18:00~18:15，开展特价机抢购，参与竞猜可获抢购资格，特价机型价格及数量如下：</div>
      <div class="directions">
       	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="directionstab">
            <tr>
              <th width="12%">时间</th>
              <th width="18%">机型</th>
              <th width="11%">市场价</th>
              <th width="11%">会员价</th>
              <th width="11%">抢购价</th>
              <th width="9%">数量</th>
                <th width="28%">备注</th>
            </tr>
            <tr>
              <td>6.13-6.27</td>
              <td>LED48M1200AF</td>
              <td align="center">3499</td>
              <td align="center">3199</td>
              <td align="center">2900</td>
              <td align="center">16</td>
                <td>小组赛每天一台，最后一天2台</td>
            </tr>
            <tr class="green1td">
              <td>6.29-7.2</td>
              <td>LED50X1200AF</td>
              <td align="center">3899</td>
              <td align="center">3509</td>
              <td align="center">3200</td>
              <td align="center">8</td>
                <td>1/8决赛每场比赛一台</td>
            </tr>
            <tr class="green2td">
              <td>7.5-7.6</td>
              <td>LED55X9600UF</td>
              <td align="center">6999</td>
              <td align="center">5999</td>
              <td align="center">5600</td>
              <td align="center">4</td>
                <td>1/4决赛每场比赛一台</td>
            </tr>
            <tr class="red1td">
              <td>7.9-7.10</td>
              <td>LED55X1800A</td>
              <td align="center">5999</td>
              <td align="center">5399</td>
              <td align="center">4800</td>
              <td align="center">2</td>
                <td>半决赛每场比赛一台</td>
            </tr>
            <tr class="green2td">
              <td>7.12</td>
              <td>LED58X9600UE</td>
              <td align="center">6999</td>
              <td align="center">6299</td>
              <td align="center">5600</td>
              <td align="center">1</td>
                <td>3、4名决赛</td>
            </tr>
            <tr class="green1td">
              <td>7.14</td>
              <td>LED65X9600UE</td>
              <td align="center">9999</td>
              <td align="center">9300</td>
              <td align="center">8500</td>
              <td align="center">1</td>
                <td>总决赛</td>
            </tr>
          </table>
      </div>
    </div>
    <div class="right7"><img src="../styles/member/sjb/images/rightbg7.jpg" /></div>
	<div class="clear"></div>
</div>
<!--six end-->
<!--seven start-->
<div class="maincont">
	<div class="left6"><img src="../styles/member/sjb/images/leftbg_6.jpg" /></div>
    <div class="midbox6" id="main1">
		<div class="botteam">
        	<ul class="teamul">
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826035' />"> <img alt="巴西" src="../styles/member/sjb/images/logo1.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826041' />"><img alt="克罗地亚" src="../styles/member/sjb/images/logo2.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826092' />"><img alt="喀麦隆" src="../styles/member/sjb/images/logo3.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826083' />"><img alt="墨西哥" src="../styles/member/sjb/images/logo4.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826133' />"><img alt="澳大利亚" src="../styles/member/sjb/images/logo5.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826129' />"><img alt="智利" src="../styles/member/sjb/images/logo6.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826127' />"><img alt="荷兰" src="../styles/member/sjb/images/logo7.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826250' />"><img alt="葡萄牙" src="../styles/member/sjb/images/logo8.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826138' />"><img alt="哥伦比亚" src="../styles/member/sjb/images/logo9.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826222' />"><img alt="希腊" src="../styles/member/sjb/images/logo10.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826225' />"><img alt="科特迪瓦" src="../styles/member/sjb/images/logo11.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826226' />"><img alt="日本" src="../styles/member/sjb/images/logo12.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826229' />"><img alt="哥斯达黎加" src="../styles/member/sjb/images/logo13.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826231' />"><img alt="英格兰" src="../styles/member/sjb/images/logo14.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826234' />"><img alt="意大利" src="../styles/member/sjb/images/logo15.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826228' />"><img alt="乌拉圭" src="../styles/member/sjb/images/logo16.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826237' />"><img alt="厄瓜多尔" src="../styles/member/sjb/images/logo17.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826242' />"><img alt="法国" src="../styles/member/sjb/images/logo18.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826243' />"><img alt="洪都拉斯" src="../styles/member/sjb/images/logo19.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826236' />"><img alt="瑞士" src="../styles/member/sjb/images/logo20.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826244' />"><img alt="阿根廷" src="../styles/member/sjb/images/logo21.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826245' />"><img alt="波黑" src="../styles/member/sjb/images/logo22.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826246' />"><img alt="伊朗" src="../styles/member/sjb/images/logo23.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826247' />"><img alt="尼日利亚" src="../styles/member/sjb/images/logo24.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826248' />"><img alt="德国" src="../styles/member/sjb/images/logo25.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826251' />"><img alt="加纳" src="../styles/member/sjb/images/logo26.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826120' />"><img alt="西班牙" src="../styles/member/sjb/images/logo27.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826253' />"><img alt="美国" src="../styles/member/sjb/images/logo28.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826256' />"><img alt="阿尔及利亚" src="../styles/member/sjb/images/logo29.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826255' />"><img alt="比利时" src="../styles/member/sjb/images/logo30.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826257' />"><img alt="俄罗斯" src="../styles/member/sjb/images/logo31.png" /></a></div></li>
            <li><div class="teamlogo"><a href="<c:url value='/member/KonkaGroupPeArticleInfo.do?method=view&id=826259' />"><img alt="韩国" src="../styles/member/sjb/images/logo32.png" /></a></div></li>
            </ul>
        </div>
        <div class="btit"><img alt="参与赛前预热活动，猜球队，赢取积分" src="../styles/member/sjb/images/tit_b1.gif" /></div>
    </div>
    <div class="right6"><img src="../styles/member/sjb/images/rightbg6.jpg" /></div>
	<div class="clear"></div>
</div>
<jsp:include page="../__inc/footer.jsp" flush="true" /> 
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.textbox.js"></script>
<script type="text/javascript">

$(document).ready(function(){

	// A,B,C,D组切换
	$(document).delegate(".pd_content", "click", function(){
		$(this).attr("hover", "hover"); // 这里标记鼠标指向的那个图片
	    var class_name = $(this).attr("class");
		$(".pd_content").each(function(){
			var id = $(this).attr("id");
		    if($(this).attr("hover") == "hover"){
				 $(this).removeClass("curteam pd_content").addClass("curteam pd_content");
				 $("#" + id + "_" + id).show();
				 
			} else {
				$(this).removeClass("curteam pd_content").addClass("pd_content");
				 $("#" + id + "_" + id).hide();
			}
		});

		$(this).removeAttr("hover");
	});

	// E,F,G,H组切换
	$(document).delegate(".pd_content_2", "click", function(){
		$(this).attr("hover", "hover"); // 这里标记鼠标指向的那个图片

	    var class_name = $(this).attr("class");
		$(".pd_content_2").each(function(){
			var id = $(this).attr("id");
		    if($(this).attr("hover") == "hover"){
				 $(this).removeClass("curteam pd_content_2").addClass("curteam pd_content_2");
				 $("#" + id + "_" + id).show();
				 
			} else {
				$(this).removeClass("curteam pd_content_2").addClass("pd_content_2");
				 $("#" + id + "_" + id).hide();
			}
		});

		$(this).removeAttr("hover");
	});




	

	$(document).delegate(".submit", "click", function(){
		var tt = $(this).attr("id");
		var win_value=	$('input:radio:checked').val();
		var a_team_result = $(".a_team_result_"+tt).val();
		var b_team_result = $(".b_team_result_"+tt).val();

		if(undefined == win_value &&(""==a_team_result || ""==b_team_result)){
			alert("请至少选择一种竞猜方式");
			return false;
		}

		if(undefined == win_value||""==a_team_result||""==b_team_result){
			if(!confirm("每个人每场比赛只能提交一次，如只选一项（胜平负/比分），另一项（胜平负/比分）将视为放弃竞猜，您确定提交吗？")){
				return false;
			}
		}

		this.disabled=true;
		this.value="正在提交。。。"; 
		$.ajax({
			type: "POST",url: "<c:url value='/member/Sjb.do' />",
			data: {"method":"ajax", "id":tt, "is_win":win_value ,"a_team_result":a_team_result ,"b_team_result":b_team_result , "timestamp":new Date().getTime() },
			dataType: "json",sync: true,
			error: function (xhr, ajaxOptions, thrownError) {alert('网络过慢，发生异常，请稍后再试'); 
			},success: function(result){
				if(result.status=='1'){ 
					alert(result.msg);location.href='${ctx}/member/Sjb.do';
				}else if(result.status=='0'){ 
					alert(result.msg);document.getElementById("sjb_"+tt).value="提交";document.getElementById("sjb_"+tt).disabled=false; location.href='${ctx}/member/Sjb.do';
				}else if(result.status=='2'){
					alert(result.msg);document.getElementById("sjb_"+tt).value="提交";document.getElementById("sjb_"+tt).disabled=false; location.href='${ctx}/member/Sjb.do';
				}else if(result.status=='3'){
					alert(result.msg);document.getElementById("sjb_"+tt).value="提交";document.getElementById("sjb_"+tt).disabled=false; location.href='${ctx}/member/Sjb.do';
				}else if(result.status=='4'){
					alert(result.msg);document.getElementById("sjb_"+tt).value="提交";document.getElementById("sjb_"+tt).disabled=false; location.href='${ctx}/member/Sjb.do';
				}else if(result.status=='5'){
					alert(result.msg);document.getElementById("sjb_"+tt).value="提交";document.getElementById("sjb_"+tt).disabled=false; location.href='${ctx}/member/Sjb.do';
				}
				
			}
		});
	
	});

});

	function setOnlyInt(obj) {
		$(obj).css("ime-mode", "disabled");
		$(obj).attr("t_value", "");
		$(obj).attr("o_value", "");
		$(obj).bind("dragenter",function(){
			return false;
		});
		$(obj).keypress(function (){
			if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
		}).keyup(function (){
			if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
		}).blur(function (){
			if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
			if(obj.value.length == 0 || isNaN(obj.value) ) obj.value = "";
		});
	}
	

</script>
</body>
</html>