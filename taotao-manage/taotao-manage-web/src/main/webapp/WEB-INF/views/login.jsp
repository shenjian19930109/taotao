<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 18/3/1
  Time: 下午9:45
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
    <link rel="stylesheet" type="text/css" href="../../css/me.css">
    <link rel="stylesheet" href="../../css/xiaomi.css"/>

    <script type="text/javascript" src="../../js/jquery-2.1.4.min.js"></script>
    <script src="../../js/jquery.animate-colors-min.js"></script>
    <!--js-->
    <script src="../../js/jquery-1.8.3.min.js"></script>
    <script src="../../js/app_mian.js"></script>

    <%--<script type="text/javascript" src="js/jQuery-2.2.0.min.js"></script>--%>
    <script type="text/javascript" src="../../js/jquery.md5.js"></script>
</head>
<body>
    <div class="head_box">
        <div id="head_wrap">
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
                <div id="head_landing">
                    <!--${pageContext.request.contextPath}-->
                    <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/login/toLoginPage">登陆</a>
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



<!--头部展示-->
<form class="" id="loginForm" action="/rest/login/toLoginResultPage" method="get" >
    <div class="login_reg " style="color: #00FF00; " >
        <ul>
            <li> <i class="icon iconfont" >&#xe620;</i>
                <input id="username" name="userName" type="text" class="input_text_user ClearInput " placeholder="用户名" required  ><a href="javascript:" class="clear_input">x</a>
            </li>
            <li> <i class="icon iconfont " >&#xe606;</i>
                <input id="password" name="passWord" type="password" class="input_text_password mima_dd " placeholder="请输入密码" onblur="mdjia()">
                <%--<input name="passWord" type="text" class="input_text_password mima_wz" style="display:none;" placeholder="请输入密码" >--%>
                <a class="eyes_box " data-show="1" href="javascript:void(0);"><i class="icon iconfont" >&#xe624;</i></a> </li>
        </ul>
        <%--<a href="#" class="denglu_but" >登录</a>--%>
        <input type="submit" class="denglu_but" value="登 录" />
        <p align="center"> <a href="#" class="f12 c999" >忘记密码</a></p>
    </div>
</form>
</body>
<script type="text/javascript">
    function mdjia(){
        var password=$("#password").val();
        var pwd=$.md5(password);
        /*alert(pwd);*/
        $("#password").val(pwd);
    }
</script>
</html>
