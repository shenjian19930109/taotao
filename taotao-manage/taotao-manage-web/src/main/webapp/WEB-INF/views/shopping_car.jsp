<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 18/3/20
  Time: 下午8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车页面</title>
    <!--基础样式-->
    <link rel="stylesheet" type="text/css" href="/css/iconfont.css">
    <!--页面样式-->
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/css/me.css">
    <link rel="stylesheet" href="/css/xiaomi.css"/>

    <script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="/js/global.js"></script>
    <script type="text/javascript" src="/js/settleAccount.js"></script>

    <style>
        .gw_num{border: 1px solid #dbdbdb;width: 110px;line-height: 26px;overflow: hidden;}
        .gw_num em{display: block;height: 26px;width: 26px;float: left;color: #7A7979;border-right: 1px solid #dbdbdb;text-align: center;cursor: pointer;font-style:normal;}
        .gw_num .num{display: block;float: left;text-align: center;width: 52px;font-style: normal;font-size: 14px;line-height: 24px;border: 0;}
        .gw_num em.add{float: right;border-right: 0;border-left: 1px solid #dbdbdb;}
    </style>

</head>

<script>
    $(document).ready(function(){
                data=$.ajax({
                    type: "POST",
                    url:"${pageContext.request.contextPath}/rest/buyer/listAllShoppingCarProducts?username=${sessionScope.username}",
                    async:false,
                    dataType: "json",
                });
                data2 = data.responseText;
                var data2 = JSON.parse(data2);
                var str = "<tr>" +
                        "<th>" + '购物车id'  + "</th>"+
                        "<th>" + '内容名称'  + "</th>"+
                        "<th>" + '数量' + "</th>" +
                        "<th>" + '价格' + "</th>" +
                        "</tr>";
                data2.forEach(function(e){
                    str = str +
                            "<tr>" +
                            "<td>" + e.id  + "</td>"+
                            "<td>" + e.title  + "</td>"+
                            "<td class=\"gw_num\">" +
                            "<em class=\"jian\">"+ "-" + "</em>" +
                            "<input type='text' class=\"num\" id=\"purchaseNum\" value=" + e.purchaseNum + ">" + "</input>" +
                            "<em class=\"add\">"+ "+" + "</em>" +
                            "</td>" +
                            /*"<span id=\"thisId\">" + e.id + "</span>" +*/
                            "<td>" + e.price + "</td>" +
                            "</tr>";
                });
                $("#newTable").append(str);
            }
    );

    $(document).ready(function(){
        //加的效果
        $(".add").click(function(){
            var n=$(this).prev().val();
            var num=parseInt(n)+1;
            if(num==0){ return;}
//            $(this).prev().val(num);
            $(this).prev().attr("value", num);
        });
        //减的效果
        $(".jian").click(function(){
            var n=$(this).next().val();
            var num=parseInt(n)-1;
            if(num==0){ return}
//            $(this).next().val(num);
            $(this).next().attr("value", num);
        });
    });

    function shopping_car () {
        //封装数据的对象
        var ShoppingCarObj =
        {
            id: "",
            title: "",
            purchaseNum: "",
            price: "",
        };
//        var a = JSON.parse("{\"data\":[]}");

        var a = new Array();

        //封装底部表格中的数据
        var rows = document.getElementById("newTable").rows.length; //获得行数(包括thead)
        var colums = document.getElementById("newTable").rows[0].cells.length; //获得列数
        if (rows > 1) {//
            for (var i = 1; i < rows; i++) { //每行 从第二行开始因为第一行是表格的标题
                var ShoppingCarObj = new Object();//这里一定要new新的对象，要不然保存的都是一样的数据；都是最后一行的数据
                ShoppingCarObj.id = document.getElementById("newTable").rows[i].cells[0].innerHTML;
                ShoppingCarObj.title = document.getElementById("newTable").rows[i].cells[1].innerHTML;
                ShoppingCarObj.purchaseNum = document.getElementById("newTable").rows[i].cells[2].childNodes[1].getAttribute('value');
                ShoppingCarObj.price = document.getElementById("newTable").rows[i].cells[3].innerHTML;
//                a.data.push(ShoppingCarObj);//向JSON数组添加JSON对象的方法；很关键
                a.push(ShoppingCarObj);
            }
            ///格式化数据
            var obj = JSON.stringify(a);//这一行很关键
            return obj;
        }
    }

    $(function () {
        $("#account").click(function(){
            var mymessage=confirm("确认购买吗？");
            if(mymessage==true){

                /*遍历获取table中的购物车表id,标题,数量,价格*/
                var sc_json = shopping_car();
                /*确认购买,跳转springmvc*/


                /*$.ajax({
                    url: "${pageContext.request.contextPath}/rest/buyer/clearShoppingCar",
                    type: "POST",
                    dataType:"json",
                    data: sc_json,
                    success: function(data){
                    }
                });*/


                window.location.href="${pageContext.request.contextPath}/rest/buyer/clearShoppingCar?sc=" + sc_json;

            }else if(mymessage==false){
                /*取消,什么都不做*/
            }
        });
    })

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

<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb" >
        <h2>已添加到购物车的内容</h2>
    </div>
    <%--<form action="" method="post">--%>
    <table id="newTable" class="m-table m-table-row n-table g-b3"></table>
    <%--</form>--%>
    <div id="act-btn">
        <button class="u-btn u-btn-primary" id="back">退出</button>
        <button class="u-btn u-btn-primary" id="account" >购买</button>
    </div>
</div>

</body>
</html>
