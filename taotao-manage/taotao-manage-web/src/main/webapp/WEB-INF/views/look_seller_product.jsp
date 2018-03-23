<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 18/3/15
  Time: 下午1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>展示新添加产品</title>

    <link rel="stylesheet" href="/css/xiaomi.css"/>
    <link rel="stylesheet" href="/css/style.css"/>

    <script type="text/javascript" src="/js/global.js"></script>
    <script type="text/javascript" src="/js/pageShow.js"></script>
</head>
<script>



</script>
<body>
<div class="head_box">
    <div id="head_wrap">
        <div id="head_nav">

            <c:if test="${not empty sessionScope.username}">
                <div class="user">
                    卖家你好，<span class="name">
                        ${sessionScope.username}
                </span>！<a href="${pageContext.request.contextPath}/rest/index/logout">[退出]</a>
                </div>
            </c:if>

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

<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${sessionScope.sellerProductVO.file}" alt="" ></div>
        <div class="cnt">
            <h2 style="margin-left: 120px;font-size: 30px;">${sessionScope.sellerProductVO.title}</h2>
            <p class="summary" style="color:#123456;font-size:20px;margin-top:30px; margin-left: 120px">${sessionScope.sellerProductVO.summary}</p>
            <div class="price" style="color:#123456;font-size:25px;margin-top:100px;margin-left: 120px">
                <span class="v-unit">¥</span><span class="v-value">${sessionScope.sellerProductVO.price}</span>
            </div>


            <div class="num" style="color:#123456;font-size:15px;margin-left: 120px">已售出数量：<%--<span id="plusNum" class="lessNum"><a>-</a></span>--%>
                <span class="totalNum" id="allNum">${sessionScope.sellerProductVO.soldNum}</span>
                <%--<span id="addNum" class="moreNum"><a>+</a></span>--%>
            </div>


            <div class="oprt f-cb" style="margin-left:120px;margin-top:30px;">
                <a href="${pageContext.request.contextPath}/rest/seller/toEditProduct?productId=${sessionScope.sellerProductVO.id}" class="u-btn u-btn-primary">编 辑</a>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${sessionScope.sellerProductVO.detail}
    </div>
</div>


</body>
</html>
