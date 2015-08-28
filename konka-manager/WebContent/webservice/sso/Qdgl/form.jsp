<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>SSO - 康佳渠道系统用户绑定</title>
<style> 
#cas form {display:block; margin:18px 0 0; padding:0; width:100%; background:#fff;}
#cas fieldset {border:0; margin:0; padding:0; float:left; clear:none; width:auto;}
#cas fieldset legend {display:none;}
#cas #login {margin:auto; padding:15px; width:258px; min-height:15em; background:#eee; position:relative; border:0;}
#cas #login:before {line-height:0.1; font-size:1px;   margin:-15px -15px 0 -15px; height:15px; display:block; }
#cas #login:after {display:block; clear:both; padding-top:15px; line-height:0.1; font-size:1px;   margin:-15px; height:8px;   }
#cas #login h2 {border-bottom:1px solid #ddd; padding:3px 0; font:normal 400 17px Georgia, "Times New Roman", Times, serif; color:#333;}
#cas #login label {font-size:11px;}
#cas #login label span.accesskey {text-decoration:underline;}
#cas #login input {letter-spacing:1px;}
#cas #login .check input {position:relative; left:-4px; height:auto;}
#cas #login .check label {float:none; xwidth:auto; line-height:1.8;}
#cas #login .btn-row {position:relative; top:15px; padding-top:10px; padding-left:40px; border-top:1px solid #ddd;}
* html #cas #login .btn-row {top:5px;} /* IE6 */
#cas #login .btn-submit {float:none; clear:none; display:inline; letter-spacing:0;}
* html #cas #login .btn-submit {background:#ffd;}
#cas #login .btn-reset {float:none; clear:none; margin-left:5px; border:0; border-left:1px solid #ddd; background:transparent; color:#777; text-transform:lowercase; letter-spacing:0;}
#cas #sidebar {float:left; margin-left:296px; padding:18px 15px;}
#cas #sidebar h3 {font:normal 400 14px Georgia, "Times New Roman", Times, serif; color:#555; margin:18px 0 0; padding:6px 0 3px;}
#cas #sidebar p {margin:0 0 18px; padding:0; color:#555; font-size:1.1em;}
#cas #list-languages ul {margin:0; padding:0; line-height:1.5; list-style:none;}
#cas #list-languages ul li {display:inline; padding:0 5px; border-right:1px solid #ccc;}
#cas #list-languages ul li.first {padding-left:0;}
#cas #list-languages ul li.last {padding-right:0; border:0;}
#cas #footer p {margin:0 0 1em 0; padding:0;}
/* RESET --------------------------------- */
/* reset some properties for elements since defaults are not crossbrowser - http://meyerweb.com/eric/thoughts/2007/05/01/reset-reloaded/ */
html,body,div,span,h1,h2,h3,p,a,img,ul,li,fieldset,form,label,legend {margin:0; padding:0; border:0; outline:0; font-weight:inherit; font-style:inherit; font-size:100%; font-family:inherit; vertical-align:baseline;}
:focus {outline:0;}
ul {list-style:none; font-size:1.1em; padding:0 0 18px 40px;}
/* browser default font-size is 16px which is too big so we make it 16px x 62.5% = 10px */
body {font:normal 400 62.5%/1.0 Verdana, sans-serif; min-width:960px; background:#fff; color:#333;}
/*
 - used to clear or contain floats within a non-floated container
 - this ruleset is used by UAs that handle :after - not IE - see ie_cas.css for IE fix
http://www.positioniseverything.net/easyclearing.html and http://www.ejeliot.com/blog/59 */
#header:after, #content:after, #footer:after, .clearfix:after {content:"."; clear:both; display:block; height:0; visibility:hidden;}
/* HEADER --------------------------------- */
#header {position:relative; top:0; left:0; padding-top:52px; background:#fff url(../images/ja-sig-logo.gif) no-repeat scroll 25px 10px;}
#header h1#app-name {clear:both; padding:0 0 0 25px; background:#210f7a; color:#fff; font:normal 400 2.8em/2.5em Georgia,"Times New Roman", serif;} /* d21033 */
/* CONTENT --------------------------------- */
#content {clear:both; padding:1px 0; margin:0 25px 2em;}
#content h2 {margin:0 0 .5em 0; font-size:1.3em; font-weight:400; color:#000; xborder-bottom:1px solid #eee; padding:3px 0; xletter-spacing:-1px;}
#content h3 {font:1em arial, helvetica, sans-serif; font-weight:400;}
#content p {line-height:1.5; font-size:1.1em; padding:0 0 18px;}
/* FOOTER --------------------------------- */
#footer {clear:both; position:relative; margin:0 25px 1em; border-top:1px solid #ccc; padding:0 0 1px 0; background:transparent; color:#999;}
#footer img#logo {position:absolute; right:0; top:0; margin-top:10px;}
#footer div {clear:left; margin:1em 5px .5em; overflow:hidden;}
/* MESSAGES --------------------------------- */
.info, .errors, .success {clear:both; margin:18px 0; padding:20px 20px 20px 100px; font-size:10px; line-height:1.5;}
.info {border:1px dotted 008;    background:#eff url(../images/info.gif) no-repeat 20px 18px; color:#008;}
.errors {border:1px dotted #d21033; background:#fed url(../images/error.gif) no-repeat 20px 18px; color:#d21033; padding-bottom: 40px;}
.success {border:1px dotted #390; background:#dfa url(../images/confirm.gif) no-repeat 20px 18px; color:#390;}
#content .errors h2, #content .success h2 {font-family:Georgia,"Times New Roman",Times,serif; font-size:18px; line-height:48px; font-weight:400; margin:0 18px 0 0; padding:0;}
#content .success h2 {color: #008 !important;}
#content .errors h2 {color:#b00 !important;}
#content .success h2 {color:#060 !important;}   
/* static messages */
#content #msg p {padding:0;}
/* FORMS --------------------------------- */
label {cursor:pointer; font-size:1.1em; color:#777;}
input {border-width:1px; font-family:Verdana,sans-serif; font-size:1.1em; color:#000; padding:3px; min-height:1.5em;}
input.btn-submit {border-width:2px;}
.fm-v div.row {float:left; margin:0; padding:.5em 0; width:100%;}
.fm-v div.row label {float:left; width:100%; line-height:1.5;}
.fm-v div.row input.btn-submit {display:block; margin:0;}
/* highlight errors */
input.error {background:#FFEFEF; color:#b00;}
/* mark as required */
.required {background:#ffd;}
.error {background:#ffefef;}
input.required {border-width:1px;}
</style> 
</head>
<body id="cas" class="fl-theme-iphone">
<div class="flc-screenNavigator-view-container">
     <div class="fl-screenNavigator-view"> 		
           <div id="content" class="fl-screenNavigator-scroll-container"> 
           <html-el:form action="/sso/Qdgl"> 
				<html-el:hidden property="method" styleId="method" value="save" />
				<html-el:hidden property="samaccount_name" value="${samaccount_name}"/>
                <div class="box fl-panel" id="login">
                	<div id="loginDiv">
	                    <h2>请输入康佳渠道系统用户名密码</h2>
	                    <div class="row fl-controls-left">
	                        <label for="username" class="fl-label">用户名:</label>
							<input id="username" name="username" class="required" tabindex="1" type="text" value="" size="25" autocomplete="false"/>
	                    </div>
	                    <div class="row fl-controls-left">
	                        <label for="password" class="fl-label">密　码:</label>
							<input id="password" name="password" class="required" tabindex="2" type="password" value="" size="25" autocomplete="off"/>
	                    </div>  
	                    <div id="submitDiv" class="row btn-row">
	                        <input class="btn-submit" name="submit" accesskey="l" value=" 绑 定 " tabindex="4" type="submit" />
	                        <input class="btn-reset" name="reset" accesskey="c" value="重置" tabindex="5" type="reset" /> <a href="${ctx }/login.do">跳过绑定</a>
	                    </div>
	                    <p style="font-color:#cccccc;margin-top:15px;"> 绑定说明：初次从OA系统自动登录渠道系统,需要绑定您的账号,请输入渠道系统的用户名密码,点击绑定按钮,绑定成功后自动登录到渠道系统;<br/>
						如果您不想绑定,请点击跳过绑定直接进入渠道系统登录界面.</p> 
                    </div> 
                </div>
          </html-el:form>
          </div>
       </div>
</div>
</body>
</html>