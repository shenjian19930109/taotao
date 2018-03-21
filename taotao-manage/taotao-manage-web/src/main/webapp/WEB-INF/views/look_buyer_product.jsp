<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 18/3/17
  Time: 下午4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>展示新添加产品</title>

    <link rel="stylesheet" href="/css/xiaomi.css"/>
    <link rel="stylesheet" href="/css/style.css"/>

    <%--<script type="text/javascript" src="/js/global.js"></script>--%>
    <script type="text/javascript" src="/js/pageShow.js"></script>
    <script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>


    <style>
        /**{margin:0;padding:0;}*/
        /*body{width:100px;margin:100px auto;}*/

        .gw_num{border: 1px solid #dbdbdb;width: 110px;line-height: 26px;overflow: hidden;}
        .gw_num em{display: block;height: 26px;width: 26px;float: left;color: #7A7979;border-right: 1px solid #dbdbdb;text-align: center;cursor: pointer;font-style:normal;}
        .gw_num .num{display: block;float: left;text-align: center;width: 52px;font-style: normal;font-size: 14px;line-height: 24px;border: 0;}
        .gw_num em.add{float: right;border-right: 0;border-left: 1px solid #dbdbdb;}
    </style>

</head>
<script>
    $(document).ready(function(){
        //加的效果
        $(".add").click(function(){
            var n=$(this).prev().val();
            var num=parseInt(n)+1;
            if(num==0){ return;}
            $(this).prev().val(num);
        });
        //减的效果
        $(".jian").click(function(){
            var n=$(this).next().val();
            var num=parseInt(n)-1;
            if(num==0){ return}
            $(this).next().val(num);
        });
    })

    $(function(){
        $("#addshopcar").click(function(){
            var purchaseNum = $("#purchaseNum").val();
            window.location.href="${pageContext.request.contextPath}/rest/buyer/addShoppingCar?purchaseNum=" + purchaseNum + "&productId=" + ${sessionScope.buyerProductVO.id};
        });
    });


</script>
<body>
<div class="head_box">
    <div id="head_wrap">
        <div id="head_nav">

            <c:if test="${not empty sessionScope.username}">
                <div class="user">
                    买家你好，<span class="name">
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
                <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/buyer/toAccountPage">账务</a>
                <span>|</span>
                <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/buyer/toShoppingCarPage">购物车</a>
                <span>|</span>
                <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/seller/toSellerAllProductPage">首页</a>
            </div>
        </div>
    </div>
</div>

<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${sessionScope.buyerProductVO.file}" alt="" ></div>
        <div class="cnt">
            <h2 style="margin-left: 100px;font-size: 30px;">${sessionScope.buyerProductVO.title}</h2>
            <p class="summary" style="color:#123456;font-size:20px;margin-top:30px; margin-left: 100px">${sessionScope.buyerProductVO.summary}</p>
            <div class="price" style="color:#123456;font-size:25px;margin-top:60px;margin-left: 100px">
                <span class="v-unit">¥</span><span class="v-value">${sessionScope.buyerProductVO.price}</span>
            </div>
            <%--<div class="num" style="color:#123456;font-size:15px;margin-left: 120px">已售出数量：
                <span id="plusNum" class="lessNum"><a>-</a></span>
                <span class="totalNum" id="allNum">${sessionScope.buyerProductVO.soldNum}</span>
                <span id="addNum" class="moreNum"><a>+</a></span>
            </div>--%>
            <div class="num" style="color:#123456;font-size:15px;float : left;">已售出数量：</div>
            <div class="gw_num" style="color:#FFF;font-size:15px;margin-left: 100px">
                <em class="jian">-</em>
                <input type="text" value="1" class="num" id="purchaseNum"/>
                <em class="add">+</em>
            </div>
            <div class="oprt f-cb" style="margin-left:100px;margin-top:30px;">
                <a href="#" class="u-btn u-btn-primary" id="addshopcar">加入购物车</a>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${sessionScope.buyerProductVO.detail}
    </div>
</div>

<%--<script type="text/javascript" src="../js/global.js"></script>--%>
<%--<script type="text/javascript" src="../js/pageShow.js"></script>--%>
</body>
</html>