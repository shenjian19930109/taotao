<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 18/3/4
  Time: 下午10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录失败</title>
    <link rel="stylesheet" href="/css/xiaomi.css"/>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<div class="head_box">
    <div id="head_wrap">
        <%--<div id="head_nav">
            <div class="user">
                卖家你好，<span class="name">
                ${sessionScope.username}
            </span>！<a href="${pageContext.request.contextPath}/rest/index/logout">[退出]</a>
            </div>
        </div>--%>
        <div id="head_right">
            <div id="head_role">
            </div>
            <div id="head_landing">
                <!--${pageContext.request.contextPath}-->
                <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/login/toLoginPage">登录</a>
                <span>|</span>
                <a class="head_nav_a" href="${pageContext.request.contextPath}/index.jsp">首页</a>
            </div>
        </div>
    </div>
</div>

<div class="g-doc">
    <div class="n-result">
        <h3>登录失败！</h3>
        <p>
            <a href="${pageContext.request.contextPath}/rest/login/toLoginPage">[重新登录]</a>
            <a href="${pageContext.request.contextPath}/index.jsp">[返回首页]</a>
        </p>
    </div>
</div>
</body>
</html>
