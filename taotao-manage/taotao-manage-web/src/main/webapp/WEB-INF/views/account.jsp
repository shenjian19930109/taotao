<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 18/3/21
  Time: 下午11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>账务</title>

    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/css/me.css">
    <link rel="stylesheet" href="/css/xiaomi.css"/>
    <!--js-->
    <script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>
</head>

<script>

    $(document).ready(function(){
            data=$.ajax({
                type: "POST",
                url:"${pageContext.request.contextPath}/rest/buyer/listAccount?username=${sessionScope.username}",
                async:false,
                dataType: "json",
            });

            data2 = data.responseText;
            var data2 = JSON.parse(data2);

            /*var str = "<tr>" +
                    "<th>" + '内容图片'  + "</th>"+
                    "<th>" + '内容名称'  + "</th>"+
                    "<th>" + '购买时间' + "</th>" +
                    "<th>" + '购买数量' + "</th>" +
                    "<th>" + '购买价格' + "</th>" +
                    "</tr>";*/
            var str = "";
            data2.forEach(function(e){
                str = str +
                        "<tr>" +
                        "<td>" + "<a href=\"${pageContext.request.contextPath}/rest/buyer/showBuyerOneProduct?id=" + e.id + "\">" + "<img src='" + e.file + "'/>" + "</a>" + "</td>" +
                        "<td>" + "<h4>" + "<a href=\"${pageContext.request.contextPath}/rest/buyer/showBuyerOneProduct?id=" + e.id + "\">" + e.title + "</a>" + "</h4>" + "</td>" +
                        "<td><span class='v-time'>" + e.createTimeStr  + "</span></td>"+
                        "<td><span class='v-num'>" + e.soldNum  + "</span></td>"+
                        "<td><span class='v-unit'>" + e.finalPrice + "</span></td>" +
                            /*"<span id=\"thisId\">" + e.id + "</span>" +*/
                        "</tr>";
            });
            $("#newTable").append(str);
        }
    );

    ${empty sessionScope.totalPrice} ? window.location.reload() : NaN;

    $("#totalPrice").val(${sessionScope.totalPrice});

</script>
<body>
<div class="head_box">
    <div id="head_wrap">
        <div id="head_nav">
            <div class="user">
                买家你好，<span class="name">
                ${sessionScope.username}
            </span>！<a href="${pageContext.request.contextPath}/rest/index/logout">[退出]</a>
            </div>
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
                <a class="head_nav_a" href="${pageContext.request.contextPath}/rest/buyer/toBuyerProductsIndex">首页</a>
            </div>
        </div>
    </div>
</div>

<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <div  style="overflow:auto;">
    <table class="m-table m-table-row n-table g-b3">
        <%--<colgroup><col class="img"/><col/><col class="title"/><col/><col class="time"/><col/><col class="num"/><col/><col class="price"/><col/></colgroup>--%>
        <thead>
        <tr>
            <th width="20%">内容图片</th>
            <th width="10%">内容名称</th>
            <th width="17%">购买时间</th>
            <th width="10%">购买数量</th>
            <th width="10%">购买价格</th>
        </tr>
        </thead>
        <tbody id="newTable">
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4"><div class="total">总计：</div></td>
            <td><span class="v-unit">¥</span><span class="value" id="totalPrice">${sessionScope.totalPrice}</span></td>
        </tr>
        </tfoot>
    </table>
    </div>
</div>

</body>
</html>
