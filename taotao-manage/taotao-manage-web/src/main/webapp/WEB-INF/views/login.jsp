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

<script>

    $(function () {
        $(".clear_input").click(function () {
            $("#username").attr("value", "");
        })
    })


</script>

<body>
    <div class="head_box">
        <div id="head_wrap">
            <div id="head_right">
                <div id="head_landing">
                    <!--${pageContext.request.contextPath}-->
                    <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/login/toLoginPage">登陆</a>
                    <span>|</span>
                    <a class="head_nav_a" href="${pageContext.request.contextPath}/index.jsp">首页</a>
                </div>
            </div>
        </div>
    </div>



<!--头部展示-->
<form class="" id="loginForm" action="/rest/login/toLoginResultPage" method="get" >
    <div class="login_reg " style="color: #00FF00; " >
        <ul>
            <li> <i class="icon iconfont" >&#xe620;</i>
                <input id="username" name="userName" type="text" class="input_text_user ClearInput " placeholder="用户名" required ><a href="javascript:" class="clear_input">x</a>
            </li>
            <li> <i class="icon iconfont " >&#xe606;</i>
                <input id="password" name="passWord" type="password" class="input_text_password mima_dd " placeholder="请输入密码" onchange="mdjia()">
                <input type="text" class="input_text_password mima_wz" style="display:none;" placeholder="请输入密码" onchange="mdjia()">
                <a id="eyes_box" class="eyes_box" data-show="1" href="javascript:void(0);"><i class="icon iconfont" >&#xe624;</i></a> </li>
        </ul>
        <%--<a href="#" class="denglu_but" >登录</a>--%>
        <input type="submit" class="denglu_but" value="登 录" />
        <p align="center"> <a href="#" class="f12 c999" >忘记密码</a></p>
    </div>
</form>
</body>
<script type="text/javascript">
    function mdjia(){

        if($("#eyes_box").attr("data-show")==1){
            var password=$("#password").val();
            var pwd=$.md5(password);
            $("#password").attr("value", pwd);
        }

        if($("#eyes_box").attr("data-show")==2){
            var password=$(".mima_wz").val();
            var pwd=$.md5(password);
            $("#password").attr("value", pwd);
        }


    }
</script>
</html>
