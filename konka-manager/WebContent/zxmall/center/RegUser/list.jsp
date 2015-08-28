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
    <div class="position"><a href="${ctx }/zxmall/Index.do">首页</a> &gt; <a href="${ctx }/zxmall/center/Index.do">会员中心</a> &gt; 完善用户资料</div>
   
    <div class="zxmalltab3" id="div_info">
     <p style="margin-left:20px;margin-top:15px;font-size:16px;line-height:36px;"> 
     	 您好， 欢迎您初次登陆康佳直销平台!
     </p>
     <p style="margin-left:50px;margin-top:15px;font-size:14px;color:red;line-height:36px; "> 
     	  为了保障您的权益，请认证阅读会员规则、会员权益、积分规则和佣金规则，并同意《康佳直销平台网站用户协议》，<br/>并完善您的基本信息
     </p>
     <c:if test="${zxmall.is_act ne 0 or zxmall.login_count <20 }">
        <div class="zxmalltab2">
        <a href="http://epp.konka.com/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=755139" target="_blank"><img src="${ctx}/styles/zxmall/images/ec_hygz.jpg" /></a>
        <a href="http://epp.konka.com/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=753541" target="_blank"><img src="${ctx}/styles/zxmall/images/ec_hyqy.jpg" /></a>
        <a href="http://epp.konka.com/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=755186" target="_blank"><img src="${ctx}/styles/zxmall/images/ec_jfgz.jpg" /></a>
        <a href="http://epp.konka.com/zxmall/KonkaGroupPeArticleInfo.do?method=view&id=755187" target="_blank"><img src="${ctx}/styles/zxmall/images/ec_yjgz.jpg" /></a>
        </div></c:if>
     <div style="margin-left:30px;margin-top:15px;font-size:14px;line-height:26px;"> 
		<div> 
			<div style="overflow-y:auto; text-align:left;padding:5px;width:720px;height:480px;font-size:12px;background-color:#cccccc;"> 
			<div style="text-align:center;font-size:14px;"><b>康佳直销平台网站用户协议</b>
			<p>请仔细阅读本协议</p></div>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;本协议是您与康佳直销平台网站（简称“本站”，网址：zx.konka.com）所有者（以下简称为“康佳直销平台”）就康佳直销平台网站服务等相关事宜所订立的契约。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;在此特别提醒，请您仔细阅读、充分理解本注册协议各条款。请您审慎阅读并选择是否接受本协议，除非接受所有条款，否则用户无权使用康佳直销平台所提供的服务。点击“同意以下协议并注册”按钮后，本协议即构成对双方有约束力的法律文件。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;一、本站服务条款的确认和接纳</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（一）本站各项电子服务的所有权属康佳直销平台所有。用户同意所有注册协议条款并完成注册程序，才能成为本站的正式用户。用户确认：本协议条款是处理双方权利义务的契约，始终有效，法律另有规定或约定的，依其约定。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（二）用户点击同意本协议的，即视为用户确认具有享受本站服务、下单购物等相应的权利能力和行为能力，能够独立承担法律责任。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（三）如果您在18周岁以下，您只能在父母或监护人的监护参与下使用本站；如果您16周岁以上不满18周岁，以自己的劳动收入为主要生活来源，可以使用本网站。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（四）康佳直销平台保留在中华人民共和国大陆地区法施行之法律允许的范围内独自决定拒绝服务、关闭用户账户、清除或编辑内容或取消订单的权利。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;二、本站服务</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（一）康佳直销平台通过互联网依法为用户提供互联网信息等服务，用户在完全同意本协议及本站规定的情况下，方有权使用本站的相关服务。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（二）用户须自行准备如下设备和承担如下费用：</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;1、上网设备，包括并不限于电脑或者其他上网终端、调制解调器及其他必备的上网装置。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;2、上网费用，包括并不限于网络接入费、上网设备租用费、手机流量费等。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;三、用户信息</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（一）用户应如实向本站提供注册资料，注册资料如有变动的，应及时更新。如提供的注册资料不合法、不真实、不准确、不详尽的，用户须承担因此引起的相应责任及后果，并且康佳直销平台保留终止用户使用康佳直销平台各项服务的权利。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（二）用户在本站进行浏览、下单购物等活动时，涉及用户真实姓名/名称、通信地址、联系电话、电子邮箱等隐私信息的，本站将予以严格保密，除非得到用户的授权或法律另有规定，本站承诺不会向外界披露用户隐私信息。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（三）用户注册成功后，将产生用户名和密码等账户信息，您可以修改密码。用户应谨慎合理的保存、使用其用户名和密码。若发现任何非法使用用户账号或存在安全漏洞的情况，请立即通知本站并向公安机关报案。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（四）用户同意，康佳直销平台拥有通过邮件、短信电话等形式，向在本站注册、购物用户、收货人发送订单信息、促销活动等告知信息的权利。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（五）用户不得将在本站注册获得的账户借给他人使用，否则用户应承担由此产生的全部责任，并与实际使用人承担连带责任。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（六）用户同意，康佳直销平台有权使用用户的注册信息、用户名、密码等，登陆进入用户的注册账户，进行证据保全，包括但不限于公证、见证等。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;四、用户依法言行义务</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（一）本协议依据国家相关法律法规制定，用户同意严格遵守以下义务：</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;1、不传输或发表：煽动抗拒、破坏宪法和法律、行政法规实施的言论，煽动颠覆国家政权，推翻社会主义制度的言论，煽动分裂国家、破坏国家统一的言论，煽动民族仇恨、民族歧视、破坏民族团结的言论。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;2、从中国大陆向境外传输资料信息时须符合中国有关法规。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;3、不利用本站从事洗钱、窃取商业秘密、窃取个人信息等违法犯罪活动。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;4、不干扰本站的正常运转，不侵入本站及国家计算机信息系统。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;5、不传输或发表任何违法犯罪的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的，淫秽的、不文明的等信息资料。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;6、不传输或发表损害国家社会公共利益和涉及国家安全的信息资料或言论。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;7、不教唆他人从事本条所禁止的行为。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;8、不利用在本站注册的账户进行牟利性经营活动。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;9、不发布任何侵犯他人著作权、商标权等知识产权或合法权利的内容。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（二）用户应不时关注并遵守本站不时公布或修改的各类合法规则规定。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（三）本站保留有删除站内各类不符合法律政策或不真实的信息内容而无须通知用户的权利。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（四）若用户未遵守以上规定的，本站有权作出独立判断并采取暂停或关闭用户账户等措施，用户须对自己在网上的言论和行为承担法律责任。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;五、商品信息</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;本站商品价格、数量、是否有货等商品信息随时都有可能发生变动，本站不作特别通知。网站商品信息数量庞大，本站会尽最大努力保证您所浏览商品信息的准确性，由于互联网技术因素等客观原因存在，本站网页显示的信息可能会有一定的滞后性或差错，对此情形您知悉并理解；康佳直销平台欢迎纠错，并会视情况给予纠错者一定的奖励。为表述便利，商品和服务简称为“商品”或“货物”。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;六、订单</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（一）下订单时，请您仔细确认所购商品的名称、价格、数量、型号、规格、尺寸、联系地址、电话、收货人等信息。收货人与用户不一致的，收货人的行为和意思表示视为用户的行为和意思表示，用户应对收货人的行为及意思表示的法律后果承担连带责任。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（二）除法律另有规定，双方约定如下：本站上销售方展示的商品和价格等信息仅为是要约邀请，下单时您须填写商品的数量、价款及支付方式、收货人、联系方式、收货地址（合同履行地）、合同履行方式等内容；系统生成的订单信息是计算机信息系统根据您填写的内容自动生成的数据，仅是您向销售方发出的合同要约；销售方收到您的订单信息后，只有在销售方将您在订单中订购的商品从仓库实际直接向您发出时（ 以商品出库为标志），方视为您与销售方之间就实际直接向您发出的商品建立了合同关系；如果您在一份订单里订购了多种商品并且销售方只给您发出了部分商品时，您与销售方之间仅就实际直接向您发出的商品建立了合同关系；只有在销售方实际直接向您发出了订单中订购的其他商品时，您和销售方之间就订单中该其他已实际直接向您发出的商品才成立合同关系。您可以随时登陆您在本站注册的账户，查询订单状态。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（三）因由于市场变化及各种以合理商业努力难以控制的不可控因素的影响，本站无法保证您提交的订单信息中希望购买的商品都会有货；如您拟购买的商品，有发生缺货情况，您有权取消订单。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;七、配送</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（一）销售方将会将把商品（货物）送到您所指定的收货地址，所有在本站上列出的送货时间仅为参考时间，参考时间的计算是根据库存状况、正常的处理过程和送货时间、送货地点的基础上估计得出的不作为销售方承诺的到货时间。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（二）因如下情况造成订单延迟或无法配送等，销售方不承担责任：</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;1、用户提供的信息错误、地址不详细等。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;2、货物送达后无人签收，导致无法配送或延迟配送的。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;3、情势变更因素导致的。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;4、不可抗因素导致的，例如：自然灾害、交通戒严、突发战争等。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;八、所有权及知识产权条款</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（一）一旦接受本协议，即表明该用户主动将其在任何时间段在本站发表的任何形式的信息（包括但不限于客户评价、客户咨询、各类话题文章等信息内容）的财产性权利等任何可转让的权利，如著作权财产权（包括并不限于：复制权、发行权、出租权、展览权、表演权、放映权、广播权、信息网络传播权、摄制权、改编权、翻译权、汇编权以及应当由著作权人享有的其他可转让权利），全部独家且不可撤销地转让给康佳直销平台所有，用户同意康佳直销平台有权就任何主体侵权而单独提起诉讼。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（二）本协议已构成《中华人民共和国著作权法》第二十五条（条文序号依照2011年版著作权法确定）及相关法律规定的著作财产权等权利转让书面协议，其效力及于用户在康佳直销平台网站上发布的任何受著作权法保护的作品内容，无论该等内容形成于本协议订立前还是本协议订立后。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（三）用户同意并已充分了解本协议的条款，承诺不将已发表于本站的信息，以任何形式发布或授权其它主体以任何方式使用。（包括但限于在各类网站、媒体上使用）</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（四）康佳直销平台是本站的制作者,拥有此网站内容及资源的著作权等合法权利,受国家法律保护,有权不时地对本协议及本站的内容进行修改，并在本站张贴，无须另行通知用户。在法律允许的最大限度范围内，康佳直销平台对本协议及本站内容拥有解释权。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（五）除法律另有规定，未经康佳直销平台书面许可,任何单位或个人不得以任何方式非法全部或部分复制、转载、引用、链接、抓取或以其他方式使用本站的信息内容，否则，康佳直销平台有权追究其法律责任。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（六）本站资料信息（诸如文字、图表、标识、按钮图标、图像、声音文件片段、数字下载、数据编辑和软件），均为康佳直销平台或其内容提供者的财产，受中国和国际版权法的保护。本站上所有内容的汇编是康佳直销平台的排他财产，受中国和国际版权法的保护。本站上所有软件都是康佳直销平台或其关联公司或其软件供应商的财产，受中国和国际版权法的保护。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;九、责任限制及不承诺担保</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;除非另有书面说明,本站及其所包含的或以其它方式通过本站提供给您的全部信息、内容、材料、产品（包括软件）和服务，均为在"按现状"和"按现有"的基础上提供。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;除非另有书面说明,康佳直销平台不对本站的运营及其包含在本网站上的信息、内容、材料、产品（包括软件）或服务作任何形式的、明示或默示的声明或担保。（根据中华人民共和国法律另有规定的以外）</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;康佳直销平台不担保本站所包含的或以其它方式通过本站提供给您的全部信息、内容、材料、产品（包括软件）和服务、其服务器或从本站发出的电子信件、信息没有病毒或其他有害成分。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;如因不可抗力或其它本站无法控制的原因使本站销售系统崩溃或无法正常使用导致网上交易无法完成或丢失有关的信息、记录等，康佳直销平台会合理地尽力协助处理善后事宜。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;十、协议更新及用户关注义务</p>
				<p>根据网站运营需要，康佳直销平台有权对本协议条款进行修改，修改后的协议一旦被张贴在本站上即生效，并代替原来的协议。用户可随时登陆查阅最新协议；用户有义务不时关注并阅读最新版的协议及网站公告。如用户不同意更新后的协议，可以且应立即停止接受本网站提供的服务；如用户继续使用，视为同意更新。康佳直销平台建议您在使用本站之前阅读本协议及本站的公告。 如果本协议中任何一条被视为废止、无效或因任何理由不可执行，该条应视为可分的且并不影响任何其余条款的有效性和可执行性。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;十一、法律管辖和适用</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;本协议的订立、执行和解释及争议的解决均适用在中华人民共和国大陆地区法律（但不包括其冲突法规则）。 如本协议与适用之法律相抵触，则次条款将完全按法律规定重新解释，其它有效条款继续有效。 如缔约方就本协议内容或其执行发生任何争议，双方应友好协商解决；协商不成时，任何一方均可向销售方所在地有管辖权的人民法院提起诉讼。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;十二、其他</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（一）网站所有者是指经政府依法许可或备案的网站经营主体。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（二）康佳直销平台尊重用户和消费者的合法权利，本协议及本网站上发布的各类规则、声明等其他内容，均是为了更好地为用户和消费者服务。本站欢迎用户和社会各界提出意见和建议，康佳直销平台将虚心接受并适时修改本协议及本站上的各类规则。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;（三）点击本协议左侧的“我已看过并接受《用户注册协议》”按钮即视为您完全接受本协议，在点击之前请您再次确认已知悉并完全理解本协议的全部内容。</p>
			</div>
		</div>
     </div>
     <div style="margin-left:230px;margin-top:15px;width:200px;"  > 
    	<input class="xz" id="xycheck" type="checkbox"  />我同意上述协议 ，下一步
     </div>
    </div>
     
  </div>
</div>
</div> 
</div>
<jsp:include page="/zxmall/__inc/2015/footer.jsp" flush="true" />
<!-- six footer -->
</body>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs_ext.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){ 
	$("#xycheck").click(function(){
		if(confirm("确定同意上述协议吗！")){
		 window.location.href="${ctx}/zxmall/center/RegUser.do?method=add";
		}
	});
}); 
//]]></script>
</html>
