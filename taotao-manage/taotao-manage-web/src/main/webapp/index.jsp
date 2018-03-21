<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="css/xiaomi.css"/>
    <link rel="stylesheet" href="/css/style.css"/>

    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script src="js/jquery.animate-colors-min.js"></script>
    <script type="text/javascript" src="js/xiaomi.js"></script>
    <script type="text/javascript" src="js/pagenation.js"></script>
</head>

<script>
    $(document).ready(function(){
        data=$.ajax({
            type: "POST",
            url:"${pageContext.request.contextPath}/rest/index/listAllProducts",
            async:false,
            dataType: "json",
            });
        data2 = data.responseText;
        var data2 = JSON.parse(data2);
        data2.forEach(function(e){
            $("#plist").append("<li id=\"p-" + e.id + "\">" +
                                    "<a href=\"${pageContext.request.contextPath}/rest/index/showOneProduct?id=" + e.id + "\" class=\"link\">" +
                                        "<div class=\"img\"><img src=" + e.file + " alt=\"" + e.title + "\"></div>" +
                                        "<h3>" + e.title + "</h3>" +
                                        "<div class=\"price\">" +
                                            "<span class=\"v-unit\">¥</span>" +
                                            "<span class=\"v-value\">" + e.price + "</span>" +
                                        "</div>" +
                                        /*"<span class=\"had\"><b>已售出</b></span>" +*/
                                    "</a>" +
                                "</li>");

        })
    }
    );
</script>

<body>
     <div class="head_box">
         <div id="head_wrap">
             <div id="head_nav">

                 <%--<c:if test="${not empty sessionScope.username}">
                     <div class="user">
                         <c:if test="${sessionScope.roleType eq 0}">
                             买家
                         </c:if>
                         <c:if test="${sessionScope.roleType eq 1}">
                             卖家
                         </c:if>
                         你好，<span class="name">
                             ${sessionScope.username}
                     </span>！<a href="/logout">[退出]</a>
                     </div>
                 </c:if>--%>

             </div>
             <div id="head_right">
                 <div id="head_landing">
                     <!--${pageContext.request.contextPath}-->
                     <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/login/toLoginPage">登陆</a>
                     <span>|</span>
                     <a class="head_nav_a">注册</a>
                     <span>|</span>
                     <a class="head_nav_a">首页</a>
                 </div>
             </div>
         </div>
     </div>
     <div id="main_head_box">
         <div id="menu_wrap">
             <div id="menu_logo">
                 <img src="img/xiaomi_logo.png">
             </div>

             <div id="find_wrap">
                 <div id="find_bar">
                     <input type="text" id="find_input">
                 </div>
                 <div id="find_but">GO</div>
             </div>
         </div>
     </div>

     <div class="g-doc">
         <div class="m-tab m-tab-fw m-tab-simple f-cb">
             <div class="tab">
                 <ul>
                     <li class="z-sel" ><a href="/">所有内容</a></li>

                 </ul>
             </div>
         </div>
         <div class="n-plist">
             <ul class="f-cb" id="plist">
                 <%--<li id="p-1">
                     <a href="/show?id=1" class="link">
                         <div class="img"><img src="ss" alt="Java编程思想（第4版"></div>
                         <h3>Java编程思想（第4版</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">1000</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>
                 <li id="p-2">
                     <a href="/show?id=2" class="link">
                         <div class="img"><img src="132132" alt="百度222"></div>
                         <h3>百度222</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">123</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>
                 <li id="p-3">
                     <a href="/show?id=3" class="link">
                         <div class="img"><img src="https://baike.baidu.com/pic/汉语大字典/1776626/0/3ac79f3df8dcd100bd117545768b4710b8122fc0?fr=lemma&ct=single" alt="修改内容"></div>
                         <h3>修改内容</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">2000</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>
                 <li id="p-4">
                     <a href="/show?id=4" class="link">
                         <div class="img"><img src="fafsge" alt="asdasd"></div>
                         <h3>asdasd</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">100</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>
                 <li id="p-5">
                     <a href="/show?id=5" class="link">
                         <div class="img"><img src="BSB" alt="sdasda"></div>
                         <h3>sdasda</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">100</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>
                 <li id="p-6">
                     <a href="/show?id=1" class="link">
                         <div class="img"><img src="ss" alt="Java编程思想（第4版"></div>
                         <h3>Java编程思想（第4版</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">1000</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>
                 <li id="p-7">
                     <a href="/show?id=2" class="link">
                         <div class="img"><img src="132132" alt="百度222"></div>
                         <h3>百度222</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">123</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>
                 <li id="p-8">
                     <a href="/show?id=3" class="link">
                         <div class="img"><img src="https://baike.baidu.com/pic/汉语大字典/1776626/0/3ac79f3df8dcd100bd117545768b4710b8122fc0?fr=lemma&ct=single" alt="修改内容"></div>
                         <h3>修改内容</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">2000</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>
                 <li id="p-9">
                     <a href="/show?id=4" class="link">
                         <div class="img"><img src="fafsge" alt="asdasd"></div>
                         <h3>asdasd</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">100</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>
                 <li id="p-10">
                     <a href="/show?id=5" class="link">
                         <div class="img"><img src="BSB" alt="sdasda"></div>
                         <h3>sdasda</h3>
                         <div class="price"><span class="v-unit">¥</span><span class="v-value">100</span></div>

                         <span class="had"><b>已售出</b></span>
                     </a>

                 </li>--%>
             </ul>
         </div>
     </div>

     <div class="page">
         <div id="page"></div>
     </div>

     <%--<div class="page" style="margin:0px auto; width:200px; height:20px; border:0px solid #F00;">
         第<span id="a2"></span>/<span id="a1"></span>页<span id="a3"></span>　
         <a href="#" onClick="change(--pageno)">上一页</a>
         <a href="#" onClick="change(++pageno)">下一页</a>
     </div>--%>

     <%--<div id="foot_box">
         <div id="foot_wrap">
             <div class="foot_top">
                 <ul>
                     <li>1小时快修服务</li>
                     <li class="font_top_i">|</li>
                     <li>7天无理由退货</li>
                     <li class="font_top_i">|</li>
                     <li>15天免费换货</li>
                     <li class="font_top_i">|</li>
                     <li>满150元包邮</li>
                     <li class="font_top_i">|</li>
                     <li>520余家售后网点</li>
                 </ul>
             </div>
             <div class="foot_bottom">
                 <div class="foot_bottom_l">
                     <dl>
                         <dt>帮助中心</dt>
                         <dd>购物指南</dd>
                         <dd>支付方式</dd>
                         <dd>配送方式</dd>
                     </dl>
                     <dl>
                         <dt>服务支持</dt>
                         <dd>售后政策</dd>
                         <dd>自助服务</dd>
                         <dd>相关下载</dd>
                     </dl>
                     <dl>
                         <dt>大米之家</dt>
                         <dd>大米之家</dd>
                         <dd>服务网点</dd>
                         <dd>预约亲临到店服务</dd>
                     </dl>
                     <dl>
                         <dt>关注我们</dt>
                         <dd>新浪微博</dd>
                         <dd>大米部落</dd>
                         <dd>官方微信</dd>
                     </dl>
                 </div>
                 <div class="foot_bottom_r">
                     <a>400-100-5678</a>
                     <a>周一至周日 8:00-18:00</a>
                     <a>（仅收市话费）</a>
                     <span> 24小时在线客服</span>
                 </div>
             </div>
         </div>
         <div class="foot_note_box">
             <div class="foot_note_wrap">
                 <div class="foot_note_con">
                     <span class="foot_logo"><img src="img/mi-logo.png" width="38px" height="38px"></span>
						<span class="foot_note_txt">
							<a>大米网</a><h>|</h><a>MIUI</a><h>|</h><a>米聊</a><h>|</h><a>多看书城</a><h>|</h><a>大米路由器</a><h>|</h><a>大米后院</a><h>|</h><a>大米天猫店</a><h>|</h><a>大米淘宝直营店</a><h>|</h><a>大米网盟</a><h>|</h><a>问题反馈</a><h>|</h><a>Select Region</a>
						    <a> 5555555号</a>
						</span>
                 </div>

             </div>
         </div>
     </div>--%>

     <script type="text/javascript" src="js/pageIndex.js"></script>
     <script type="text/javascript" src="js/global.js"></script>

     <%--只要解开此注释,就可以分页!!!--%>
     <%--<script>
         var zz=getzz()
         var pageno=1 ; //当前页
         var pagesize=5; //每页多少条信息
         if(zz.length%pagesize==0){
             var  pageall =zz.length/pagesize ;
         }else{
             var  pageall =parseInt(zz.length/pagesize)+1;
         }   //一共多少页
         change(1);

     </script>--%>

     <%--<script>

         var a = document.getElementById("plist").getElementsByTagName("li");
         var zz =new Array(a.length);
         for(var i=0;i <a.length;i++){
             zz[i]=a[i].innerHTML;
         } //div的字符串数组付给zz
         var pageno=1 ;              //当前页
         var pagesize=5;            //每页多少条信息
         if(zz.length%pagesize==0){
             var  pageall =zz.length/pagesize ;
         }else{
             var  pageall =parseInt(zz.length/pagesize)+1;
         }   //一共多少页

         function change(e){
             pageno=e;
             if(e<1){ //如果输入页<1页
                 e=1;pageno=1;//就等于第1页 ， 当前页为1
             }
             if(e>pageall){  //如果输入页大于最大页
                 e=pageall;pageno=pageall; //输入页和当前页都=最大页
             }
             document.getElementById("plist").innerHTML=""//全部清空
             for(var i=0;i<pagesize;i++){
                 var div =document.createElement("li")//建立div对象
                 div.innerHTML=zz[(e-1)*pagesize+i]//建立显示元素
                 document.getElementById("plist").appendChild(div)//加入all中
                 if(zz[(e-1)*pagesize+i+1]==null) break;//超出范围跳出
             }
             var ye="";
             for(var j=1;j<=pageall;j++){
                 if(e==j){
                     ye=ye+"<span><a href='#' onClick='change("+j+")' style='color:#FF0000'>"+j+"</a></span> "
                 }else{
                     ye=ye+"<a href='#' onClick='change("+j+")'>"+j+"</a> "
                 }
             }
             document.getElementById("a1").innerHTML=pageall;
             document.getElementById("a2").innerHTML=pageno;
             document.getElementById("a3").innerHTML=ye;
         }

         change(1);

     </script>--%>

</body>

</html>