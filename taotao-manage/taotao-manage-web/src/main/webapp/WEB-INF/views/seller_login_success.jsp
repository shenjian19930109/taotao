<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 18/3/4
  Time: 下午10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <!--基础样式-->
    <link rel="stylesheet" type="text/css" href="/css/iconfont.css">
    <!--页面样式-->
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/css/me.css">
    <link rel="stylesheet" href="/css/xiaomi.css"/>
    <!--js-->
    <script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>
    <script src="/js/jquery.animate-colors-min.js"></script>
    <script type="text/javascript" src="/js/xiaomi.js"></script>
    <script type="text/javascript" src="/js/pagenation.js"></script>

</head>

<script>
    $(document).ready(function(){
        data=$.ajax({
            type: "POST",
            url:"${pageContext.request.contextPath}/rest/seller/listAllSoldProducts?username=${sessionScope.username}",
            async:false,
            dataType: "json",
        });
        data2 = data.responseText;
        var data2 = JSON.parse(data2);
        data2.forEach(function(e){
            $("#plist").append("<li id=\"p-" + e.id + "\">" +
                    "<a href=\"${pageContext.request.contextPath}/rest/seller/showSellerOneProduct?id=" + e.id + "\" class=\"link\">" +
                    "<div class=\"img\"><img src=" + e.file + " alt=\"" + e.title + "\"></div>" +
                    "<h3>" + e.title + "</h3>" +
                    "<div class=\"price\">" +
                    "<span class=\"v-unit\">¥</span>" +
                    "<span class=\"v-value\">" + e.price + "</span>" +
                    "</div>" +
                        <%--<c:if test="${e.sold==false}"> +
                            "<span class=\"had\"><b>已售出</b></span>" +
                        </c:if> +--%>
                        "<span class=\"had\"><b>" + e.sellStatus + "</b></span>" +
                    "</a>" +
                    /*"<span class=\"u-btn u-btn-normal u-btn-xs del\" data-del=" + e.id + ">删除</span>" +*/
                    "</li>");

        })
    }
);
    $(document).ready(function(){
        data=$.ajax({
            type: "POST",
            url:"${pageContext.request.contextPath}/rest/seller/listAllUnSoldProducts?username=${sessionScope.username}",
            async:false,
            dataType: "json",
        });
        data2 = data.responseText;
        var data2 = JSON.parse(data2);
        data2.forEach(function(e){
            $("#plist").append("<li id=\"p-" + e.id + "\">" +
                    "<a href=\"${pageContext.request.contextPath}/rest/seller/showSellerOneProduct?id=" + e.id + "\" class=\"link\">" +
                    "<div class=\"img\"><img src=" + e.file + " alt=\"" + e.title + "\"></div>" +
                    "<h3>" + e.title + "</h3>" +
                    "<div class=\"price\">" +
                    "<span class=\"v-unit\">¥</span>" +
                    "<span class=\"v-value\">" + e.price + "</span>" +
                    "</div>" +
                        <%--<c:if test="${e.sold==false}"> +
                            "<span class=\"had\"><b>已售出</b></span>" +
                        </c:if> +--%>
                        "<span class=\"had\"><b>" + e.sellStatus + "</b></span>" +
                    "</a>" +
                    /*"<span class=\"u-btn u-btn-normal u-btn-xs del\" data-del=" + e.id + " onclick=deleteSellerProduct(" + e.id + ");>删除</span>" +*/
                    "<span id='span' class=\"u-btn u-btn-normal u-btn-xs del\" data-del=" + e.id + ">删除</span>" +
                    /*"<a class=\"u-btn u-btn-normal u-btn-xs del\" data-del=" + e.id + " href=\"${pageContext.request.contextPath}/rest/seller/deleteSellerProduct?id=" + e.id + ">删除</a>" +*/


                    /*"<a class=\"u-btn u-btn-normal u-btn-xs del\" data-del=" + e.id + " href=\"${pageContext.request.contextPath}/rest/seller/deleteSellerProduct?id=" + e.id + ">" +
                    "<span >删除</span>" +*/
                    "</a>" +
                    "</li>");

        })
    }
);

    $ (function ()
        {
            $ ('#span').click (function ()
            {
                $(window.location).prop('href', '${pageContext.request.contextPath}/rest/seller/deleteSellerProduct?id=' + $ ('#span').attr('data-del'));
            })
        })

</script>
<body>
<div class="head_box">
    <div id="head_wrap">
        <div id="head_nav">
            <div class="user">
                卖家你好，<span class="name">
                            ${sessionScope.username}
                        </span>！<a href="${pageContext.request.contextPath}/rest/index/logout">[退出]</a>
            </div>
        </div>
        <div id="head_right">
            <div id="head_role">


            </div>
            <div id="head_landing">
                <!--${pageContext.request.contextPath}-->
                <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/seller/toReleasePage">发布</a>
                <span>|</span>
                <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/seller/toSellerAllProductPage">首页</a>
            </div>
        </div>
    </div>
</div>
<div id="main_head_box">
    <div id="menu_wrap">
        <div id="menu_logo">
            <img src="../../img/xiaomi_logo.png">
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
                <li class="z-sel"><a href="${pageContext.request.contextPath}/rest/seller/toSellerAllProductPage">所有内容</a></li>
                <li><a href="${pageContext.request.contextPath}/rest/seller/toSellerUnSoldProductPage">未售出的内容</a></li>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
            <%--<li id="p-1">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                    <span class="had"><b>已售出</b></span>
                </a>
            </li>
            <li id="p-2">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                    <span class="had"><b>已购买</b></span>
                </a>
            </li>
            <li id="p-3">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                </a>
                <a class="u-btn u-btn-normal u-btn-xs del" data-del="3" href="${pageContext.request.contextPath}/rest/seller/deleteSellerProduct?id=">删除</a>
            </li>
            <li id="p-4">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                </a>
                <span class="u-btn u-btn-normal u-btn-xs del" data-del="4" onclick=deleteSellerProduct();>删除</span>
            </li>
            <li id="p-5">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                </a>
                <span class="u-btn u-btn-normal u-btn-xs del" data-del="5">删除</span>
            </li>
            <li id="p-6">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                </a>
                <span class="u-btn u-btn-normal u-btn-xs del" data-del="6">删除</span>
            </li>
            <li id="p-7">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                </a>
                <span class="u-btn u-btn-normal u-btn-xs del" data-del="7">删除</span>
            </li>
            <li id="p-8">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                </a>
                <span class="u-btn u-btn-normal u-btn-xs del" data-del="8">删除</span>
            </li>
            <li id="p-9">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                </a>
                <span class="u-btn u-btn-normal u-btn-xs del" data-del="9">删除</span>
            </li>--%>
        </ul>
    </div>
</div>

<script type="text/javascript" src="/js/pageIndex.js"></script>
<script type="text/javascript" src="/js/global.js"></script>
</body>
</html>
