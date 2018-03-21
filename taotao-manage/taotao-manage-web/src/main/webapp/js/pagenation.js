function getzz() {
    var a = document.getElementById("plist").getElementsByTagName("li");
    var zz =new Array(a.length);
    for(var i=0;i <a.length;i++){
        zz[i]=a[i].innerHTML;
    } //div的字符串数组付给zz
    return zz;
}
function change(e){
    pageno=e;
    if(e<1){ //如果输入页<1页
        e=1;pageno=1;//就等于第1页 ， 当前页为1
    }
    if(e>pageall){  //如果输入页大于最大页
        e=pageall;pageno=pageall; //输入页和当前页都=最大页
    }
    document.getElementById("plist").innerHTML=""//全部清空
    for(var i=0;i<pagesize;i++){
        var div =document.createElement("li")//建立div对象
        div.innerHTML=zz[(e-1)*pagesize+i]//建立显示元素
        document.getElementById("plist").appendChild(div)//加入all中
        if(zz[(e-1)*pagesize+i+1]==null) break;//超出范围跳出
    }
    var ye="";
    for(var j=1;j<=pageall;j++){
        if(e==j){
            ye=ye+"<span><a href='#' onClick='change("+j+")' style='color:#FF0000'>"+j+"</a></span> "
        }else{
            ye=ye+"<a href='#' onClick='change("+j+")'>"+j+"</a> "
        }
    }
    var pageContent="";
    pageContent +='第<span id=\"a2\">'+pageno+'</span>/';
    pageContent +='<span id="a1">'+pageall+'</span>页';
    pageContent +='<span id="a3">'+ye+'</span>';
    pageContent +='<a href="#" onClick="change(--pageno)">上一页</a>';
    pageContent +='<a href="#" onClick="change(++pageno)">下一页</a>';
    $("#page").html(pageContent);
}