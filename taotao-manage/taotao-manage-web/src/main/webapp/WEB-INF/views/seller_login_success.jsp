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
    <link rel="stylesheet" type="text/css" href="../../css/iconfont.css">
    <!--页面样式-->
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/me.css">
    <link rel="stylesheet" href="../../css/xiaomi.css"/>
    <!--js-->
    <script type="text/javascript" src="/js/global.js"></script>
    <script type="text/javascript" src="/js/public.js"></script>

</head>
<body>
<div class="head_box">
    <div id="head_wrap">
        <div id="head_nav">
            <div class="user">
                卖家你好，<span class="name">
                            ${cookie.username.value}
                        </span>！<a href="/logout">[退出]</a>
            </div>
            <%--<%
                // 获得当前路径以及"直接父路径"的所有Cookie对象,如果没有任何Cookie的话,则返回null
                Cookie[] cookies = request.getCookies();

                // 遍历数组,获得具体的Cookie
                if(cookies == null) {
                    System.out.print("没有Cookie信息");
                } else {
                    cookies
                }
            %>--%>


        </div>
        <%--<div id="head_nav">
            <a class="head_nav_a">大米网</a>
            <span>|</span>
            <a class="head_nav_a">MIUI</a>
            <span>|</span>
            <a class="head_nav_a">米聊</a>
            <span>|</span>
            <a class="head_nav_a">游戏</a>
            <span>|</span>
            <a class="head_nav_a">多看阅读</a>
            <span>|</span>
            <a class="head_nav_a">云服务</a>
            <span>|</span>
            <a class="head_nav_a">大米移动版</a>
            <span>|</span>
            <a class="head_nav_a">问题反馈</a>
            <span>|</span>
            <a class="head_nav_a" id="Select_Region_but">Select Region</a>
        </div>--%>
        <div id="head_right">
            <div id="head_role">


            </div>
            <div id="head_landing">
                <!--${pageContext.request.contextPath}-->
                <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/seller/toReleasePage">发布</a>
                <span>|</span>
                <a class="head_nav_a" href="${pageContext.request.contextPath}/index.jsp">首页</a>
            </div>
            <%--<div id="head_car">
                <a class="head_car_text">购物车（0）</a>
                <div id="car_content" style="height: 0px;width:0px ;background-color: #edffc6;z-index: 999">
                    <a class="car_text"></a>
                </div>
            </div>--%>
        </div>
    </div>
</div>

<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>内容发布</h2>
    </div>
    <div class="n-public">
        <form class="m-form m-form-ht" id="form" method="post" action="/publicSubmit" onsubmit="return false;" autocomplete="off">
            <div class="fmitem">
                <label class="fmlab">标题：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="title" autofocus placeholder="2-80字符"/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="summary" placeholder="2-140字符"/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">图片：</label>
                <div class="fmipt" id="uploadType">
                    <input name="pic" type="radio" value="url" checked /> 图片地址
                    <input name="pic" type="radio" value="file" /> 本地上传
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
                    <input class="u-ipt ipt"  name="image" placeholder="图片地址"/>
                </div>
                <div class="fmipt" id="fileUpload"  style="display:none">
                    <input class="u-ipt ipt" name="file" type="file" id="fileUp"/>
                    <button class="u-btn u-btn-primary" id="upload">上传</button>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">正文：</label>
                <div class="fmipt">
                    <textarea class="u-ipt" name="detail" rows="10" placeholder="2-1000个字符"></textarea>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">价格：</label>
                <div class="fmipt">
                    <input class="u-ipt price" name="price"/>元
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button type="submit" class="u-btn u-btn-primary u-btn-lg">发 布</button>
                </div>
            </div>
        </form>
        <span class="imgpre"><img src="" alt="" id="imgpre"></span>
    </div>
</div>

</body>
</html>
