<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<div class="menu-nav">
        	<h3 onclick="location.href='<c:url value='/zxmall/KonkaBcompPd.do?' />';">全部商品分类</h3>
            <ul class="menu-list">
            	<li onclick="location.href='<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0' />';"><span class="pingban">平板电视</span></li>
                <li onclick="location.href='<c:url value='/zxmall/KonkaBcompPd.do?prod_type=4' />';"><span class="bingx">冰 箱</span></li>
                <li onclick="location.href='<c:url value='/zxmall/KonkaBcompPd.do?prod_type=5' />';"><span class="xiyiji">洗衣机</span></li>
                <li onclick="location.href='<c:url value='/zxmall/KonkaBcompPd.do?prod_type=7' />';"><span class="shouji">手 机</span></li>
                <li onclick="location.href='<c:url value='/zxmall/KonkaBcompPd.do?prod_type=3' />';"><span class="shenghuo">生活电器</span></li>
                <li onclick="location.href='<c:url value='/zxmall/KonkaBcompPd.do?prod_type=10' />';"><span class="peijian">配件专区</span></li>
            </ul>
        </div>
        <div class="menu-cont" >
        	<div class="opacity-layout"></div>
        	<div class="menu-cont-c" >
            	<dl>
                	<dt><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0' />" >品牌</a></dt>
                    <dd>
                    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&brand_name=KONKA' />">KONKA</a> 
                    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&brand_name=KKTV' />">KKTV</a>
              		<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&brand_name=HYUNDAI' />">HYUNDAI</a> 
                    </dd>
                    <dt><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0' />">康佳电视</a></dt>
                    <dd>
                    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_type=4' />">4K电视</a> 
                    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_type=1' />">3D电视</a> 
                    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_type=2' />">智能电视</a> 
                    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_type=9' />">其他</a>
                    </dd>
                    <dt><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=31' />" >屏幕尺寸</a></dt>
                    <dd>
                    	<p><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=31' />">32英寸以下</a> 
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=32' />" >32英寸</a> 
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=37' />" >37英寸</a> 
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=39' />" >39/40英寸</a> 
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=42' />" >42英寸</a> 
                    	</p>
                        <p>
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=46' />" >46英寸</a> 
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=47' />" >47英寸</a> 
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=48' />" >48英寸</a> 
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=49' />" >49/50英寸</a> 
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=55' />" >55英寸</a> 
                    	<a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_size_type=59' />">58英寸及以上</a>
                    	</p>
                    </dd>
                    <dt>分类标签</dt>
                    <dd><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&label_of_cate=0' />">新 品</a>
			           <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&label_of_cate=7' />">精品</a>
					   <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&label_of_cate=2' />">热 销</a>
					   <a href="<c:url value='/zxmall/KonkaBcompPd.do?lprod_type=0&abel_of_cate=3' />">特 惠</a>
					   <!--<a href="<c:url value='/zxmall/PshowOrderPanicBuying.do' />">限时抢购</a>  -->
					 </dd>
                    <dt>价格</dt>
                    <dd>
                    	<p><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_price=1' />">1000元以下</a>
						    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_price=2' />">1000-2000元</a>
						    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_price=3' />">2000-3000元</a>
						</p>
                        <p>
						    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_price=4' />">3000-5000元</a>
						    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_price=5' />">5000-10000元</a>
						    <a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=0&pd_price=6' />" style="width:80px">10000元以上</a>
						 </p>
                    </dd>
                </dl>
            </div>
            <div class="menu-cont-c">
            	<dl>
                	<dt><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=4' />">冰 箱</a></dt>
                    <dd><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=4' />">冰 箱</a></dd> 
                </dl>
            </div>
            <div class="menu-cont-c">
            	<dl>
                	<dt><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=5' />">洗衣机</a></dt>
                    <dd><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=5' />">洗衣机</a></dd>
                    
                </dl>
            </div>
            <div class="menu-cont-c">
            	<dl>
                	<dt><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=7' />">手 机</a></dt>
                    <dd><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=7' />">手 机</a></dd> 
                </dl>
            </div>
            <div class="menu-cont-c">
            	<dl>
                	<dt><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=3' />">生活电器</a></dt>
                    <dd><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=3' />">生活电器</a></dd> 
                </dl>
            </div>
            <div class="menu-cont-c">
            	<dl>
                	<dt><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=10' />">配件专区</a></dt>
                    <dd><a href="<c:url value='/zxmall/KonkaBcompPd.do?prod_type=10' />">配件专区</a></dd> 
                </dl>
            </div>
        </div>