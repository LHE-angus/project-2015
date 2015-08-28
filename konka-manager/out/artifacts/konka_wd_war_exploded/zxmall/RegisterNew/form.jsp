<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康佳直销平台</title>    
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/global.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/login-new.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/zxmall/css/footer.css" />
<link href="${ctx}/styles/protocol/css/prot.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.form-login{font-family: "微软雅黑";line-height: 38px;color: #FFF;background-image: url(${ctx}/styles/zxmall/images/login-bg2.jpg);background-repeat: no-repeat;text-align: center;height: 38px;width: 106px;margin-top: 20px;cursor: pointer;}

#myHiddenDiv1 {display:none;}
.popup {border: 1px solid #ccc;background: url(../images/b1-bg06.gif) repeat-x left top #fff;padding-bottom: 20px;}
.popup a:hover{text-decoration:none; color:#fff;}
.popup-header {height:24px; padding-top:20px; height:38px; line-height:32px;}
.popup-header h2 {font-size:14px; width:100%; text-align:center;}
.popup-body { width:100%; padding-top:8px; }
.popup-body strong{ display:block; text-align:center; font-size:12px; font-weight:normal; margin-bottom:5px;}
.con{ padding:10px; width:692px; height:247px; margin:0 auto 20px auto; overflow:auto; border:1px solid #a4c9e3;}
.con p{ text-indent:2em; line-height:18px; margin-bottom:10px;}
.con b{ text-indent:2em;}


html{color:#000;}
body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form, fieldset, input, textarea, p, blockquote, th, td{padding:0;margin:0;}
table {border-collapse:collapse; border-spacing:0;}
fieldset, img{border:0;}
address, caption, cite, code, dfn, em, strong, th, var{font-weight:normal;font-style:normal;}
ol, ul{list-style:none;margin:0;padding:0;}
caption, th{text-align:left;}
h1, h2, h3, h4, h5, h6{font-weight:normal;font-size:100%;}
q:before,q:after{content:'';}
abbr, acronym {border: 0;}
a{text-decoration:none;}
a:hover{text-decoration:underline;}
input,textarea,select{font-family:inherit;font-size:inherit;font-weight:inherit;*font-size:100%;}
.fl{float:left;display:inline;}
.fr{float:right;display:inline;}
.clearfix:before,.clearfix:after {content:"";display:table;}
.clearfix:after {clear:both;overflow:hidden;}
.clearfix {zoom:1; /* for IE6 IE7 */}
.clear{clear:both;display:block;overflow:hidden;height:0;line-height:0;font-size:0;}

.mian-zc{margin:0 auto;padding:22px 30px;width:930px;overflow:hidden;background-color:#fff;border-radius:10px;}
.register-t{padding-left:20px;height:44px;line-height:44px;color:#d61328;font-size:24px;}
.register-cont{padding-top:20px;}
.register-form{padding-bottom:10px;float:left;width:606px;}
.register-form p{height:50px;line-height:40px;color:#666;font-size:14px;}
.register-form .xyyd{height:44px;line-height:34px;}
.register-form .register-f-t{display:block;padding-right:10px;float:left;width:126px;text-align:right;}
.register-form p .input-txt{padding:3px 5px;width:396px;height:32px;border:1px solid #d5d5d5;}
.register-form .xyyd label{margin-left:136px;}
.register-form .xyyd input{margin-top:-2px;margin-right:10px;vertical-align:middle;}  
.register-btn{margin-left:136px;width:272px;height:38px;background:url(${ctx}/styles/zxmall/images/register_btn.png) no-repeat center center;border:none;cursor:pointer;}
.reg-login{margin-top:3px;padding-left:50px;float:left;width:270px;height:416px;border-left:1px solid #f2f0f0;}
.reg-login h4{height:46px;}
.reg-login-tip{margin:16px 0;height:36px;line-height:36px;color:#666;font-size:16px;font-family:"宋体";}

</style>

<script type="text/javascript" src="${ctx}/commons/scripts/jquery-1.6.4.min.js"></script>
</head>
<body>   
<div class="head">  
  <div class="logo" style="padding-top: 0px;padding-bottom: 10px;"><span class="l"><img src="${ctx}/styles/zxmall/images/zxmall3.jpg" width="429" /></span><span class="r" style="padding-top: 20px;"><img src="${ctx}/styles/zxmall/images/tel.gif" width="166" height="33" /></span></div>
</div>    
  <div class="mian-zc" id="div_user"> 
  <h3 class="register-t">用户注册</h3>
  <div class=register-cont clearfix" id="div_user" > 
  		<div class="register-form">
        	<form action="${ctx}/zxmall/RegisterNew.do?method=save" method="post" style="margin:0;padding:0;" id="login1"> 
            <p><span class="register-f-t">用户名：</span><input class="input-txt" type="text" name="user_name" id="user_name" maxlength="20" onkeyup="FtoH(this);" /></p>
            <p><span class="register-f-t">登录密码：</span><input class="input-txt" type="password" name="pass_word"  maxlength="20" id="pass_word" /></p>
            <p><span class="register-f-t">重复登录密码：</span><input class="input-txt" type="password" name="pass_word_repeat" id="pass_word_repeat" maxlength="20" /></p>
            <p><span class="register-f-t">支付密码：</span><input class="input-txt" type="password" name="pay_pwd"  maxlength="20" id="pay_pwd" /></p>
            <p><span class="register-f-t">重复支付密码：</span><input class="input-txt" type="password" name="pay_pwd_repeat" id="pay_pwd_repeat" maxlength="20" /></p>
            <p><span class="register-f-t">关联码：</span><input class="input-txt" type="text" name="link_code" maxlength="8" /></p>
            <p><span class="register-f-t">真实姓名：</span><input class="input-txt" type="text" name="real_name" maxlength="10" /></p>
            <p><span class="register-f-t">手机号码：</span><input class="input-txt" type="text" name="link_phone"  maxlength="11" /></p>
            <p><span class="register-f-t">邮箱：</span><input class="input-txt" type="text" name="email"  maxlength="30"/></p>
            <p class="xyyd"><label><input class="xz" name="xycheck"  id="xycheck" type="checkbox"   />我已阅读<a href="javascript:openStaticPopup()">《康佳触网直销商城注册协议》</a></label></p> 
            <p><input class="register-btn" type="button" value="" id="btn_submit" /></p> 
            </form>
        </div>
        <div class="reg-login">
        	<h4><img src="${ctx}/styles/zxmall/images/register_old.png" /></h4>
            <p class="reg-login-tip">已有账号？请登录！</p>
            <p><a href="##" onclick="location.href='${ctx}/zxmall/login.do';"><img src="${ctx}/styles/zxmall/images/register_login.png" /></a></p>
        </div> 
      </div>
    <div id="myHiddenDiv1">         
	<div class="popup">
		<div class="popup-header">
			<h2>康佳触网直销商城用户注册协议</h2>
		</div>
		<div class="popup-body">
			<strong>请仔细阅读本协议</strong>
			<div class="con">
				<p>本协议是您与康佳直销平台网站（简称“本站”，网址：zx.konka.com）所有者（以下简称为“康佳直销平台”）就康佳直销平台网站服务等相关事宜所订立的契约。</p>
				<p>在此特别提醒，请您仔细阅读、充分理解本注册协议各条款。请您审慎阅读并选择是否接受本协议，除非接受所有条款，否则用户无权使用康佳直销平台所提供的服务。点击“同意以下协议并注册”按钮后，本协议即构成对双方有约束力的法律文件。</p>
				<p>一、本站服务条款的确认和接纳</p>
				<p>（一）本站各项电子服务的所有权属康佳直销平台所有。用户同意所有注册协议条款并完成注册程序，才能成为本站的正式用户。用户确认：本协议条款是处理双方权利义务的契约，始终有效，法律另有规定或约定的，依其约定。</p>
				<p>（二）用户点击同意本协议的，即视为用户确认具有享受本站服务、下单购物等相应的权利能力和行为能力，能够独立承担法律责任。</p>
				<p>（三）如果您在18周岁以下，您只能在父母或监护人的监护参与下使用本站；如果您16周岁以上不满18周岁，以自己的劳动收入为主要生活来源，可以使用本网站。</p>
				<p>（四）康佳直销平台保留在中华人民共和国大陆地区法施行之法律允许的范围内独自决定拒绝服务、关闭用户账户、清除或编辑内容或取消订单的权利。</p>
				<p>二、本站服务</p>
				<p>（一）康佳直销平台通过互联网依法为用户提供互联网信息等服务，用户在完全同意本协议及本站规定的情况下，方有权使用本站的相关服务。</p>
				<p>（二）用户须自行准备如下设备和承担如下费用：</p>
				<p>1、上网设备，包括并不限于电脑或者其他上网终端、调制解调器及其他必备的上网装置。</p>
				<p>2、上网费用，包括并不限于网络接入费、上网设备租用费、手机流量费等。</p>
				<p>三、用户信息</p>
				<p>（一）用户应如实向本站提供注册资料，注册资料如有变动的，应及时更新。如提供的注册资料不合法、不真实、不准确、不详尽的，用户须承担因此引起的相应责任及后果，并且康佳直销平台保留终止用户使用康佳直销平台各项服务的权利。</p>
				<p>（二）用户在本站进行浏览、下单购物等活动时，涉及用户真实姓名/名称、通信地址、联系电话、电子邮箱等隐私信息的，本站将予以严格保密，除非得到用户的授权或法律另有规定，本站承诺不会向外界披露用户隐私信息。</p>
				<p>（三）用户注册成功后，将产生用户名和密码等账户信息，您可以修改密码。用户应谨慎合理的保存、使用其用户名和密码。若发现任何非法使用用户账号或存在安全漏洞的情况，请立即通知本站并向公安机关报案。</p>
				<p>（四）用户同意，康佳直销平台拥有通过邮件、短信电话等形式，向在本站注册、购物用户、收货人发送订单信息、促销活动等告知信息的权利。</p>
				<p>（五）用户不得将在本站注册获得的账户借给他人使用，否则用户应承担由此产生的全部责任，并与实际使用人承担连带责任。</p>
				<p>（六）用户同意，康佳直销平台有权使用用户的注册信息、用户名、密码等，登陆进入用户的注册账户，进行证据保全，包括但不限于公证、见证等。</p>
				<p>四、用户依法言行义务</p>
				<p>（一）本协议依据国家相关法律法规制定，用户同意严格遵守以下义务：</p>
				<p>1、不传输或发表：煽动抗拒、破坏宪法和法律、行政法规实施的言论，煽动颠覆国家政权，推翻社会主义制度的言论，煽动分裂国家、破坏国家统一的言论，煽动民族仇恨、民族歧视、破坏民族团结的言论。</p>
				<p>2、从中国大陆向境外传输资料信息时须符合中国有关法规。</p>
				<p>3、不利用本站从事洗钱、窃取商业秘密、窃取个人信息等违法犯罪活动。</p>
				<p>4、不干扰本站的正常运转，不侵入本站及国家计算机信息系统。</p>
				<p>5、不传输或发表任何违法犯罪的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的，淫秽的、不文明的等信息资料。</p>
				<p>6、不传输或发表损害国家社会公共利益和涉及国家安全的信息资料或言论。</p>
				<p>7、不教唆他人从事本条所禁止的行为。</p>
				<p>8、不利用在本站注册的账户进行牟利性经营活动。</p>
				<p>9、不发布任何侵犯他人著作权、商标权等知识产权或合法权利的内容。</p>
				<p>（二）用户应不时关注并遵守本站不时公布或修改的各类合法规则规定。</p>
				<p>（三）本站保留有删除站内各类不符合法律政策或不真实的信息内容而无须通知用户的权利。</p>
				<p>（四）若用户未遵守以上规定的，本站有权作出独立判断并采取暂停或关闭用户账户等措施，用户须对自己在网上的言论和行为承担法律责任。</p>
				<p>五、商品信息</p>
				<p>本站商品价格、数量、是否有货等商品信息随时都有可能发生变动，本站不作特别通知。网站商品信息数量庞大，本站会尽最大努力保证您所浏览商品信息的准确性，由于互联网技术因素等客观原因存在，本站网页显示的信息可能会有一定的滞后性或差错，对此情形您知悉并理解；康佳直销平台欢迎纠错，并会视情况给予纠错者一定的奖励。为表述便利，商品和服务简称为“商品”或“货物”。</p>
				<p>六、订单</p>
				<p>（一）下订单时，请您仔细确认所购商品的名称、价格、数量、型号、规格、尺寸、联系地址、电话、收货人等信息。收货人与用户不一致的，收货人的行为和意思表示视为用户的行为和意思表示，用户应对收货人的行为及意思表示的法律后果承担连带责任。</p>
				<p>（二）除法律另有规定，双方约定如下：本站上销售方展示的商品和价格等信息仅为是要约邀请，下单时您须填写商品的数量、价款及支付方式、收货人、联系方式、收货地址（合同履行地）、合同履行方式等内容；系统生成的订单信息是计算机信息系统根据您填写的内容自动生成的数据，仅是您向销售方发出的合同要约；销售方收到您的订单信息后，只有在销售方将您在订单中订购的商品从仓库实际直接向您发出时（ 以商品出库为标志），方视为您与销售方之间就实际直接向您发出的商品建立了合同关系；如果您在一份订单里订购了多种商品并且销售方只给您发出了部分商品时，您与销售方之间仅就实际直接向您发出的商品建立了合同关系；只有在销售方实际直接向您发出了订单中订购的其他商品时，您和销售方之间就订单中该其他已实际直接向您发出的商品才成立合同关系。您可以随时登陆您在本站注册的账户，查询订单状态。</p>
				<p>（三）因由于市场变化及各种以合理商业努力难以控制的不可控因素的影响，本站无法保证您提交的订单信息中希望购买的商品都会有货；如您拟购买的商品，有发生缺货情况，您有权取消订单。</p>
				<p>七、配送</p>
				<p>（一）销售方将会将把商品（货物）送到您所指定的收货地址，所有在本站上列出的送货时间仅为参考时间，参考时间的计算是根据库存状况、正常的处理过程和送货时间、送货地点的基础上估计得出的不作为销售方承诺的到货时间。</p>
				<p>（二）因如下情况造成订单延迟或无法配送等，销售方不承担责任：</p>
				<p>1、用户提供的信息错误、地址不详细等。</p>
				<p>2、货物送达后无人签收，导致无法配送或延迟配送的。</p>
				<p>3、情势变更因素导致的。</p>
				<p>4、不可抗因素导致的，例如：自然灾害、交通戒严、突发战争等。</p>
				<p>八、所有权及知识产权条款</p>
				<p>（一）一旦接受本协议，即表明该用户主动将其在任何时间段在本站发表的任何形式的信息（包括但不限于客户评价、客户咨询、各类话题文章等信息内容）的财产性权利等任何可转让的权利，如著作权财产权（包括并不限于：复制权、发行权、出租权、展览权、表演权、放映权、广播权、信息网络传播权、摄制权、改编权、翻译权、汇编权以及应当由著作权人享有的其他可转让权利），全部独家且不可撤销地转让给康佳直销平台所有，用户同意康佳直销平台有权就任何主体侵权而单独提起诉讼。</p>
				<p>（二）本协议已构成《中华人民共和国著作权法》第二十五条（条文序号依照2011年版著作权法确定）及相关法律规定的著作财产权等权利转让书面协议，其效力及于用户在康佳直销平台网站上发布的任何受著作权法保护的作品内容，无论该等内容形成于本协议订立前还是本协议订立后。</p>
				<p>（三）用户同意并已充分了解本协议的条款，承诺不将已发表于本站的信息，以任何形式发布或授权其它主体以任何方式使用。（包括但限于在各类网站、媒体上使用）</p>
				<p>（四）康佳直销平台是本站的制作者,拥有此网站内容及资源的著作权等合法权利,受国家法律保护,有权不时地对本协议及本站的内容进行修改，并在本站张贴，无须另行通知用户。在法律允许的最大限度范围内，康佳直销平台对本协议及本站内容拥有解释权。</p>
				<p>（五）除法律另有规定，未经康佳直销平台书面许可,任何单位或个人不得以任何方式非法全部或部分复制、转载、引用、链接、抓取或以其他方式使用本站的信息内容，否则，康佳直销平台有权追究其法律责任。</p>
				<p>（六）本站资料信息（诸如文字、图表、标识、按钮图标、图像、声音文件片段、数字下载、数据编辑和软件），均为康佳直销平台或其内容提供者的财产，受中国和国际版权法的保护。本站上所有内容的汇编是康佳直销平台的排他财产，受中国和国际版权法的保护。本站上所有软件都是康佳直销平台或其关联公司或其软件供应商的财产，受中国和国际版权法的保护。</p>
				<p>九、责任限制及不承诺担保</p>
				<p>除非另有书面说明,本站及其所包含的或以其它方式通过本站提供给您的全部信息、内容、材料、产品（包括软件）和服务，均为在"按现状"和"按现有"的基础上提供。</p>
				<p>除非另有书面说明,康佳直销平台不对本站的运营及其包含在本网站上的信息、内容、材料、产品（包括软件）或服务作任何形式的、明示或默示的声明或担保。（根据中华人民共和国法律另有规定的以外）</p>
				<p>康佳直销平台不担保本站所包含的或以其它方式通过本站提供给您的全部信息、内容、材料、产品（包括软件）和服务、其服务器或从本站发出的电子信件、信息没有病毒或其他有害成分。</p>
				<p>如因不可抗力或其它本站无法控制的原因使本站销售系统崩溃或无法正常使用导致网上交易无法完成或丢失有关的信息、记录等，康佳直销平台会合理地尽力协助处理善后事宜。</p>
				<p>十、协议更新及用户关注义务</p>
				<p>根据网站运营需要，康佳直销平台有权对本协议条款进行修改，修改后的协议一旦被张贴在本站上即生效，并代替原来的协议。用户可随时登陆查阅最新协议；用户有义务不时关注并阅读最新版的协议及网站公告。如用户不同意更新后的协议，可以且应立即停止接受本网站提供的服务；如用户继续使用，视为同意更新。康佳直销平台建议您在使用本站之前阅读本协议及本站的公告。 如果本协议中任何一条被视为废止、无效或因任何理由不可执行，该条应视为可分的且并不影响任何其余条款的有效性和可执行性。</p>
				<p>十一、法律管辖和适用</p>
				<p>本协议的订立、执行和解释及争议的解决均适用在中华人民共和国大陆地区法律（但不包括其冲突法规则）。 如本协议与适用之法律相抵触，则次条款将完全按法律规定重新解释，其它有效条款继续有效。 如缔约方就本协议内容或其执行发生任何争议，双方应友好协商解决；协商不成时，任何一方均可向销售方所在地有管辖权的人民法院提起诉讼。</p>
				<p>十二、其他</p>
				<p>（一）网站所有者是指经政府依法许可或备案的网站经营主体。</p>
				<p>（二）康佳直销平台尊重用户和消费者的合法权利，本协议及本网站上发布的各类规则、声明等其他内容，均是为了更好地为用户和消费者服务。本站欢迎用户和社会各界提出意见和建议，康佳直销平台将虚心接受并适时修改本协议及本站上的各类规则。</p>
				<p>（三）点击本协议左侧的“我已看过并接受《用户注册协议》”按钮即视为您完全接受本协议，在点击之前请您再次确认已知悉并完全理解本协议的全部内容。</p>
			</div>
		</div>
		<a href="javascript:;" class="close" onclick="$.closePopupLayer('myStaticPopup')" title="关闭窗口" >阅读完毕，关闭窗口</a>
	</div>
</div>
    </div>  
    
   <div class="bottom2" style="width:100%"><span><img src="${ctx}/styles/zxmall/images/bottom4.gif"/></span>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/zxmall/images/bottom1.gif" height="38" width="114" border="0"/></div>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/zxmall/images/bottom2.gif"  height="38" width="114"border="0"/></div>
    <div class="b_service_pic_sub"><img src="${ctx}/styles/zxmall/images/bottom3.gif" height="38" width="114" border="0"/></div>
    <div class="clear"></div>
  </div>
  <div class="foot">康佳集团 版权所有 KONKA 2013.All Rights Reserved&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网站管理：多媒体渠道管理部&nbsp;&nbsp;&nbsp;运营电话：0755-26608866-6346 &nbsp;&nbsp;粤ICP备05059413</div>
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>    
<script type="text/javascript" src="${ctx}/scripts/jquery.jmpopups-0.5.1.js"></script>
<script type="text/javascript">//<![CDATA[ 

$(document).ready(function(){     
	
	$("#pass_word").attr("dataType","User_name").attr("Require","true").attr("msg", "请填写登录密码,且必须在6-20个字符之间！");
	$("#pass_word_repeat").attr("datatype","Repeat").attr("to", "pass_word").attr("msg","两次输入的密码不一致！");
	$("#pay_pwd").attr("dataType","User_name").attr("Require","true").attr("msg", "请填写支付密码,且必须在6-20个字符之间！");
	$("#pay_pwd_repeat").attr("datatype","Repeat").attr("to", "pay_pwd").attr("msg","两次输入的密码不一致！");
	$("input[name='link_code']").attr("dataType", "Require").attr("msg", "关联码必须填写！");
	$("input[name='real_name']").attr("dataType", "Require").attr("msg", "真实姓名必须填写！");
	$("input[name='user_name']").attr("dataType", "Require").attr("msg", "登录名必须填写！"); 
	$("input[name='link_phone']").attr("dataType", "Mobile").attr("msg", "手机格式不正确！");  
	$("input[name='email']").attr("dataType", "Email").attr("msg", "请填写正确格式邮箱"); 

	// 验证用户名是否存在     
	$("#user_name").blur(function(){  
		var user_name = $.trim(DBC2SBC($(this).val()));
		if(null == $(this).val() || $(this).val() == ''){
			$(this).css("background-color", "#fff");
			$("#user_name").focus(); 
			return ;
		}
		if($(this).val().indexOf(' ')>-1){
			alert("用户名不能包含空格");
			$("#user_name").val("");
			$("#user_name").focus();   
			return;
		}  
		$(this).css("background-color", "#fff");
		
			$.ajax({
				type: "POST",
				url: "RegisterNew.do",
				data: "method=validateName&user_name=" + $(this).val(),
				dataType: "json",
				error: function(request, settings) {
					alert("检查用户名重复失败，请稍候再次尝试。");
					$(this).css("background-color", "#ddcc00");
					$(this).focus();
				},
				success: function(oper) {
					if (oper.result) {
						alert("该用户名已存在！");
						$("#user_name").val("");
						$("#user_name").focus(); 
						return;
					} else {
						//$("#user_name").val($.trim(DBC2SBC(this.value)));
						var cc=$("#user_name").val();
						$("#user_name").val($.trim(DBC2SBC(cc)));   
						$("#user_name").css("background-color", "#fff"); 
						$("#btn_submit").removeAttr("disabled");
					}
				}
			});
	});	
	
	$("#btn_submit").click(function(){ 

		if($("#xycheck").attr("checked") != "checked"){
			alert("请选择阅读协议");
			return false;
		}
		
		var ff=document.getElementById("login1");  
		var isSubmit = Validator.Validate(ff,1);    
		if (isSubmit) {
			if($("#pass_word").val()==$("#pay_pwd").val()){
				 alert("请不要把支付密码设置与登录密码相同");
				 $("#pay_pwd").val("");
				 $("#pay_pwd_repeat").val("");
				 $("#pay_pwd").focus();
				 return false;
			 }
			 $("#btn_submit").attr("disabled", "true");    
			 ff.submit();   
		}
	});
	
});

function DBC2SBC(str){
    var result = '';
    for (i=0 ; i<str.length; i++){
        code = str.charCodeAt(i);//获取当前字符的unicode编码
        if (code >= 65281 && code <= 65373){//在这个unicode编码范围中的是所有的英文字母已及各种字符
   			result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
  		}else if (code == 12288){//空格
   			result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
  		}else{
   			result += str.charAt(i);
  		}
   }
   return result;
}

function FtoH(obj){ 
	var str=obj.value; 
	var result="";
	for (var i = 0; i < str.length; i++){ 
		if (str.charCodeAt(i)==12288){ 
			result+= String.fromCharCode(str.charCodeAt(i)-12256); 
			continue;
		}
		if (str.charCodeAt(i)>65280 && str.charCodeAt(i)<65375){
			result+= String.fromCharCode(str.charCodeAt(i)-65248); 
		}else{ 
			result+= String.fromCharCode(str.charCodeAt(i)); 
		}
	}
	obj.value=result; 
} 

$.setupJMPopups({
	screenLockerBackground: "#000000",
	screenLockerOpacity: "0.5"
});

function openStaticPopup() {
	$.openPopupLayer({
		name: "myStaticPopup",
		width: 820,
		target: "myHiddenDiv1"
	});
}

//]]></script>
</html>