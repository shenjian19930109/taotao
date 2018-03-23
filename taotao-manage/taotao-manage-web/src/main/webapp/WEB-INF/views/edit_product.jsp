<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 18/3/13
  Time: 下午1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>编辑产品</title>
    <!--基础样式-->
    <link rel="stylesheet" type="text/css" href="../../css/iconfont.css">
    <!--页面样式-->
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/me.css">
    <link rel="stylesheet" href="../../css/xiaomi.css"/>
    <!--js-->
    <script type="text/javascript" src="/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="/js/global.js"></script>
    <script type="text/javascript" src="/js/public.js"></script>
    <script type="text/javascript" src="/js/ajaxfileupload.js"></script>

</head>
<script type="text/javascript">
    function radio_show(){
        var pic=document.getElementsByName("pic");
        var div=document.getElementById("c").getElementsByTagName("div");
        for(i=0;i<div.length;i++){
            if(pic[i].checked){
                div[i].style.display="block";
            }
            else{
                div[i].style.display="none";
            }
        }
    }

    //ajax提交信息
    function upload_img() {
        if($("#file").val() != "") {
            $.ajaxFileUpload({
                type: "POST",
                url:"${pageContext.request.contextPath}/rest/upload/uploadFile",
                dataType: "json",
                fileElementId:"file",  // 文件的id
                success: function(d){
                    if(d.code == 0) {
                        /*alert("上传成功");*/
                        //图片显示
                        $("#avatar").attr("value",d.data.url);
                        $("#avatarShow").attr("src",d.data.url);
                        /*alert($("#avatar").attr("value"));*/
                    }
                },
                error: function () {
                    alert("上传失败");
                },
            });
        } else {
            alert("请先选择文件");
        }
    }

    function display_img() {
        $("#avatarShow").attr("src", $("#imgAddr").val());
    }

    $(function () {
        $("#title").blur(function(){
            var $parent = $(this).parent();
            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）
            var titleLen = $("#title").val().trim().length;
            if(titleLen<2 || titleLen>50){
                var errorMsg = " 长度错误！";
                $parent.append("<span class='msg onError' style='color:#F00'>" + errorMsg + "</span>");
            }
        })
        $("#summary").blur(function(){
            var $parent = $(this).parent();
            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）
            var titleLen = $("#summary").val().trim().length;
            if(titleLen<2 || titleLen>140){
                var errorMsg = " 长度错误！";
                $parent.append("<span class='msg onError' style='color:#F00'>" + errorMsg + "</span>");
            }
        })
        $("#detail").blur(function(){
            var $parent = $(this).parent();
            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）
            var titleLen = $("#detail").val().trim().length;
            if(titleLen<2 || titleLen>1000){
                var errorMsg = " 长度错误！";
                $parent.append("<span class='msg onError' style='color:#F00'>" + errorMsg + "</span>");
            }
        })
        $("#price").blur(function(){
            var $parent = $(this).parent();
            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）
            var price = $("#price").val().trim();
            if(!$.isNumeric(price)){
                var errorMsg = " 类型错误！";
                $parent.append("<span class='msg onError' style='color:#F00'>" + errorMsg + "</span>");
            }
        })
    })

    function confirm(){
        /*校验标题\摘要\正文的长度,不正确则不能提交*/
        if($("#title").val().length < 2){
            $("#title").focus();
            return false;
        }else if($("#title").val().length > 80){
            $("#title").focus();
            return false;
        }else if($("#summary").val().length < 2){
            $("#summary").focus();
            return false;
        }else if($("#summary").val().length > 140){
            $("#summary").focus();
            return false;
        }else if($("#detail").val().length < 2){
            $("#detail").focus();
            return false;
        }else if($("#detail").val().length > 1000){
            $("#detail").focus();
            return false;
        }else if(!$.isNumeric($("#price").val().trim())){
            $("#price").focus();
            return false;
        }else {
            return true;
        }
    }

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

<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>内容编辑</h2>
    </div>
    <div class="n-public">
        <form class="m-form m-form-ht" id="form" method="post" action="${pageContext.request.contextPath}/rest/seller/editSubmit?productId=${sessionScope.productId}"  onSubmit="return confirm();" ><%--onsubmit="return false;" autocomplete="off"--%>
            <div class="fmitem">
                <label class="fmlab">标题：</label>
                <div class="fmipt">
                    <input id="title" class="u-ipt ipt" name="title" value="${sessionScope.productVO.title}"/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要：</label>
                <div class="fmipt">
                    <input id="summary" class="u-ipt ipt" name="summary" value="${sessionScope.productVO.summary}"/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">图片：</label>
                <div class="fmipt" id="uploadType">
                    <input name="pic" type="radio" value="url" checked onclick="radio_show();"/> 图片地址
                    <input name="pic" type="radio" value="file" onclick="radio_show();"/> 本地上传
                </div>
            </div>
            <div class="fmitem" id="c">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
                    <input class="u-ipt ipt"  name="image" id="imgAddr" value="${sessionScope.productVO.file}" onchange="display_img();"/>
                </div>
                <div class="fmipt" id="fileUpload"  style="display:none">
                    <input class="u-ipt ipt" name="file" type="file" id="file" onchange="upload_img();"/>
                    <input type="hidden" name="avatar" id="avatar" value="">
                    <%--<button class="u-btn u-btn-primary" id="upload" onclick="upload_img();">上传</button>--%>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">正文：</label>
                <div class="fmipt">
                    <textarea id="detail" class="u-ipt" name="detail" rows="10" >${sessionScope.productVO.detail}</textarea>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">价格：</label>
                <div class="fmipt">
                    <input id="price" class="u-ipt price" name="price"  value="${sessionScope.productVO.price}"/>元
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <input type="submit" class="u-btn u-btn-primary u-btn-lg" value="保存"/>
                </div>
            </div>
        </form>
        <span class="imgpre"><img src="${sessionScope.productVO.file}" alt="" id="avatarShow" width="100px" height="100px"></span>
    </div>
</div>


</body>
</html>
