<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
  <div class="nav homenav" id="nav">
    <ul>
      <li>
        <div class="tit">按品类</div>
        <div class="titbot">
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&label_3d=1&mark=3d';">3D电视</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&label_int=1&mark=int';">智能电视</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&label_www=1&mark=www';">网络电视</p>
        </div>
        <div class="clear"></div>
        <div class="submenubox disn">
          <div class="subcate">
            <ul>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&label_3d=1&mark=3d">3D电视</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&label_int=1&mark=int">智能电视</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&label_www=1&mark=www">网络电视</a></li>
            </ul>
          </div>
          <div class="colright">
            <div class="featuredbrand">
              <h3>推荐商品</h3>
              <ul>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
              </ul>
              <div class="clear"></div>
            </div>
            <div class="merchantpromotion">
              <h3>推荐经销商</h3>
              <div class="txtList">
                <ul>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
          </div>
          <div class="clear"></div>
        </div>
      </li>
      <li>
        <div class="tit">按系列</div>
        <div class="titbot">
          <p>8300系列</p>
          <p>9200系列</p>
          <p>5500系列</p>
          <p>5100系列</p>
        </div>
        <div class="clear"></div>
        <div class="submenubox disn">
          <div class="subcate">
            <ul>
              <li><a href="#" >8300系列</a></li>
              <li><a href="#" >9200系列</a></li>
              <li><a href="#" >5500系列</a></li>
              <li><a href="#" >5100系列</a></li>
            </ul>
          </div>
          <div class="colright">
            <div class="featuredbrand">
              <h3>推荐商品</h3>
              <ul>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
              </ul>
              <div class="clear"></div>
            </div>
            <div class="merchantpromotion">
              <h3>推荐经销商</h3>
              <div class="txtList">
                <ul>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
          </div>
          <div class="clear"></div>
        </div>
      </li>
      <li>
        <div class="tit">按尺寸</div>
        <div class="titbot">
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&size_val=32%7Cl';">32英寸以下</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&size_val=32';">32英寸</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&size_val=37';">37英寸</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&size_val=39';">39英寸</p>
        </div>
        <div class="clear"></div>
        <div class="submenubox disn">
          <div class="subcate">
            <ul>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&size_val=32%7Cl">32英寸以下</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&size_val=32">32英寸</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&size_val=37">37英寸</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&size_val=39">39英寸</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&size_val=42">42英寸</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&size_val=47">47英寸</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&size_val=50">50英寸</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&size_val=55">55英寸</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&size_val=55%7Cg">55英寸以上</a></li>
            </ul>
          </div>
          <div class="colright">
            <div class="featuredbrand">
              <h3>推荐商品</h3>
              <ul>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
              </ul>
              <div class="clear"></div>
            </div>
            <div class="merchantpromotion">
              <h3>推荐经销商</h3>
              <div class="txtList">
                <ul>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
          </div>
          <div class="clear"></div>
        </div>
      </li>
      <li>
        <div class="tit">按分辨率</div>
        <div class="titbot">
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&resolution_val=1024%2C768';">1024*768</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&resolution_val=1366%2C768';">1366*768</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&resolution_val=1920%2C1080';">1920*1080</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&resolution_val=3840%2C2160';">3840*2160</p>
        </div>
        <div class="clear"></div>
        <div class="submenubox disn">
          <div class="subcate">
            <ul>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&resolution_val=1024%2C768">1024*768</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&resolution_val=1366%2C768">1366*768</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&resolution_val=1920%2C1080">1920*1080</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&resolution_val=3840%2C2160">3840*2160</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&resolution_val=9999">其它</a></li>
            </ul>
          </div>
          <div class="colright">
            <div class="featuredbrand">
              <h3>推荐商品</h3>
              <ul>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
              </ul>
              <div class="clear"></div>
            </div>
            <div class="merchantpromotion">
              <h3>推荐经销商</h3>
              <div class="txtList">
                <ul>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
          </div>
          <div class="clear"></div>
        </div>
      </li>
      <li>
        <div class="tit">按价格</div>
        <div class="titbot">
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&price_val=1-1999';">1-1999元</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&price_val=2000-3999';">2000-3999元</p>
          <p onclick="location.href='${ctx}/customer/manager/PShow.do?method=list&price_val=4000-5999';">4000-5999元</p>
        </div>
        <div class="clear"></div>
        <div class="submenubox disn">
          <div class="subcate">
            <ul>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&price_val=1-1999">1-1999元</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&price_val=2000-3999">2000-3999元</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&price_val=4000-5999">4000-5999元</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&price_val=6000-10000">6000-10000元</a></li>
              <li><a href="${ctx}/customer/manager/PShow.do?method=list&price_val=10000%7Cg">10000元以上</a></li>
            </ul>
          </div>
          <div class="colright">
            <div class="featuredbrand">
              <h3>推荐商品</h3>
              <ul>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
                <li><a href="#" title="" ><img src="${ctx}/styles/customer/pshow/images/p2.jpg" /></a></li>
              </ul>
              <div class="clear"></div>
            </div>
            <div class="merchantpromotion">
              <h3>推荐经销商</h3>
              <div class="txtList">
                <ul>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                  <li><a href="http://konka.mymyty.com" title="康佳康佳">康佳康佳</a></li>
                </ul>
              </div>
              <div class="clear"></div>
            </div>
          </div>
          <div class="clear"></div>
        </div>
      </li>
    </ul>
  </div>
  <script type="text/javascript" src="${ctx}/styles/customer/pshow/js/Slide.jquery.js"></script>