<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div id="albums">
    <input type="hidden" id="curr_bigimg_eq" value="0">
	<div class="albums_title">康佳彩电<a href="javascript:void(0);"></a></div>
    <div class="albums_conn">
    	<div class="show_bigimgs_desk">
            <div id="bigimg_show">
            	<div id="iviewer_ui_title"></div>
            	<div id="iviewer_ui"></div>
            </div>
        </div>
        <div class="bigimg_list">
            <ul>
                <li class="curr"><a href="${ctx}/${af.map.main_pic_file}" title=""><img src="${ctx}/${fn:substringBefore(af.map.main_pic_file, '.')}_120.jpg" alt="" width="60" height="80" /></a></li>
                <c:if test="${not empty picList}">
	        		<c:forEach items="${picList}" var="cur" varStatus="vs">
		                <li><a href="${ctx}/${cur}" title=""><img src="${ctx}/${fn:substringBefore(cur, '.')}_120.jpg" alt="" width="60" /></a></li>
	        		</c:forEach>
	        	</c:if>
            </ul>
        </div>
    </div>
</div>
<script src="${ctx}/styles/customer/cloud-zoom/scripts/jquery-ui-1.9.1.custom.js"></script>
<script src="${ctx}/styles/customer/cloud-zoom/scripts/jquery.mousewheel.min.js"></script>
<script src="${ctx}/styles/customer/cloud-zoom/scripts/jquery.iviewer.js"></script>
<script>
jQuery(function($){
	//初始化
	var _iv = $("#bigimg_show").iviewer({
		src: 'images/1.jpg',
		zoom: "fit",
		zoom_min: 40,
		zoom_max: 600
	});
	$('#iviewer_ui div').hover(function(){
		$('#iviewer_ui_title').text($(this).data('title'));
	}, function(){
		$('#iviewer_ui_title').empty();
	});
	$('#albums .bigimg_list li a').click(function(){
		var index = $('#albums .bigimg_list li a').index(this);
		changeBigimg(index);
		return false;
	});
	//打开相册模式
	$('#img_show .album_link').click(function(){
		changeBigimg($('#curr_bigimg_eq').val());
		$('#albums').show(200);
	});
	//关闭相册模式
	$('#albums .albums_title a').click(function(){
		$('#albums').hide(200);
	});
	//改变大图
	function changeBigimg(i){
		var _o = $('#albums .bigimg_list li a');
		var _imgsrc = _o.eq(i).attr('href');
		_o.eq(i).parent().addClass('curr').siblings().removeClass('curr');
		_iv.iviewer('loadImage', _imgsrc);
	}
});
</script>