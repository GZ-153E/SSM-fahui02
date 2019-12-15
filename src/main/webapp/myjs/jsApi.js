
$(function () {
    to_page(1);
});
deptAll();//设置所有部门
function to_page(pn,emp) {
    check_val = [];         //清空选择的员工
    //console.log(pn);
    $("#check_all").prop("checked","");       //清空全选框
    $.ajax({
        url:"http://localhost:8080/js/empAll?pn="+pn,
        data:emp,
        type:"GET",
        dataType: "json",
        contentType:"application/json;charset=UTF-8",
        success:function (result) {
            if(result.code == 100){
                var date = result.extend.pageInfo;
                tableDate(date.list);       //员工信息
                dhmd(date);                 //导航
                fymd(date);                 //分页信息
                pns = date.pageNum;
                pas = date.pages;
                tot = date.total;
            }
        }
    })
}
function tableDate(date) {      //员工信息
    $("tbody").empty();
    var html;
    var a = " ";
    for(var i=0;i<date.length;i++){
        html += "<tr><td><input type='checkbox' class='check_item'/></td>" +
            "<td>"+date[i].id+"</td>\n" +
            "                <td>"+date[i].name+"</td>" +
            "                <td>"+date[i].job+"</td>" +
            "                <td>"+date[i].mgr+"</td>" +
            "                <td>"+date[i].hiredate+"</td>" +
            "                <td>"+date[i].sal+"</td>" +
            "                <td>"+date[i].comm+"</td>" +
            "                <td>"+date[i].deptno+"</td>" +
            "<td><button type='button' class='btn btn-primary bj-btn'>编辑</button>&nbsp;" +
            "<button type='button' class='btn btn-danger sc-btn'>删除</button></td></tr>";
    }
    $("tbody").append(html);

}
function dhmd(result) {   //分页信息方法
    $("#dh").empty();
    $("#dh").html("共"+result.total+"条记录；当前第"+result.pageNum+"页；共"+result.pages+"页;每页显示"+result.pageSize+"条记录");
}
function fymd(result) {         //解析分页信息
    $("#fy").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    //构建元素
    var firstPageLi = $("<li></li>").append(
        $("<a></a>").append("首页"));
    var prePageLi = $("<li></li>").append(
        $("<a></a>").append("&laquo;"));
    if (result.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        //为元素添加点击翻页的事件
        firstPageLi.click(function () {
            to_page(1,emp);
        });
        prePageLi.click(function () {
            to_page(result.pageNum - 1,emp);
        });
    }

    var nextPageLi = $("<li></li>").append(
        $("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append(
        $("<a></a>").append("末页"));
    if (result.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            to_page(result.pageNum + 1,emp);
        });
        lastPageLi.click(function () {
            to_page(result.pages,emp);
        });
    }

    //添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);
    //1,2，3遍历给ul中添加页码提示
    $.each(result.navigatepageNums, function (index, item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page(item,emp);
        });
        ul.append(numLi);
    });
    //添加下一页和末页 的提示
    ul.append(nextPageLi).append(lastPageLi);
    //把ul加入到nav
    $("#fy").append(ul);
}

//添加事件
$(".tj-btn").click(function () {
    $("#myModal form")[0].reset();
    //deptAll();      //查询所有部门
    $("#myModalLabel").text("添加员工");
    $("#myModal form input").next("span").text("");         //清空错误信息
    $("#myModal form #deptno").next("span").text("");            //清空部门下拉列表的错误信息
    $("#myModal #save").attr("btn-nature","tj");           //添加为添加性质按钮
    $("#myModal #id").removeAttr("readonly");               //解除禁用
    $("#myModal").modal({
        backdrop:"static"//弹出模态框
    });
});

//编辑按钮事件
$(document).on("click",".bj-btn",function(){
    $("#myModal form")[0].reset();
    //deptAll();      //查询所有部门
    $("#myModalLabel").text("更新员工");
    $("#myModal form input").next("span").text("");     //清空错误信息
    $("#myModal #save").attr("btn-nature","bj");        //添加为编辑性质按钮
    $("#myModal #id").attr("readonly","readonly");      //禁用员工id  输入框  readonly=”readonly”
    var emp = $(this).closest("tr").children("td");
    $("#myModal #id").val(emp[1].innerText);
    $("#myModal #name").val(emp[2].innerText);
    $("#myModal #job").val(emp[3].innerText);
    $("#myModal #mgr").val(emp[4].innerText!="0"?emp[4].innerText:"");
    $("#myModal #sal").val(emp[6].innerText);
    $("#myModal #comm").val(emp[7].innerText!="0"?emp[7].innerText:"");
    $("#myModal #deptno").val(emp[8].innerText);    //ajax要改成同步
    $("#myModal").modal({
        backdrop:"static"//弹出模态框
    });
});

function deptAll() {
    $("#myModal form")[0].reset();
    $.ajax({
        url:"http://localhost:8080/dept/selectAll",
        type:"get",
        async:false,
        success:function (result) {
            if(result.code == 100){
                $("#deptno").empty();
                var html = "<option selected='selected' value=''>请选择员工部门</option>";
                var data = result.extend.dept;
                for(var i=0;i<data.length;i++){
                    html += "<option value="+data[i].deptno+">"+data[i].dname+"</option>"
                }
                $("#deptno").html(html);
                $("#s-deptno").html(html);
            }
        }
    })
}

///以下是编辑添加操作
var fCheck = [false,false,false,false,false,false,false];       //表单数据是否合法逻辑
$("#save").click(function () {      //保存方法
    formCheck();            //表单数据是否合法
    //console.log(fCheck.indexOf(false));
    var nature = $("#myModal #save").attr("btn-nature");        //确认是添加员工模态框
    if((fCheck.indexOf(false)==-1) && nature=="tj"){
        $.ajax({
            url:"http://localhost:8080/js/saveEmp",
            type:"POST",
            data:$("#myModal form").serialize(),
            success:function (result) {
                saveBtnDataLogic(result);
                if(result.code==100){
                    $("#myModal form input").next("span").text("");
                    $("#myModal form #deptno").next("span").text("");
                    $("#myModal form")[0].reset();
                    alert("添加成功！");
                    var p = tot%5;
                    if(p==0){
                        to_page(pas+1);
                    }else {
                        to_page(pas);
                    }
                    //to_page(pas);
                }
            }
        });
    }
    if((fCheck.indexOf(false)==-1) && nature=="bj"){      //更新按钮
        $.ajax({
            url:"http://localhost:8080/js/updateEmp",
            type:"POST",
            data:$("#myModal form").serialize(),
            success:function (result) {
                saveBtnDataLogic(result);
                if(result.code==100){
                    alert("更新成功成功！");
                    $("#deptno").next("span").text("");
                    to_page(pns);       //去当前页
                }
            }
        });
    }
});
function saveBtnDataLogic(result) {
    if(result.code==200) {
        var data = result.extend.errorFields;
        //console.log(data);
        if(undefined != data.id){
            $("#id").next("span").text(data.id);
        }else {
            $("#id").next("span").text("");
        }
        if(undefined != data.name){
            $("#name").next("span").text(data.name);
        }else {
            $("#name").next("span").text("");
        }
        if(undefined != data.job){
            $("#job").next("span").text(data.job);
        }else {
            $("#job").next("span").text("");
        }
        if(undefined != data.mgr){
            $("#mgr").next("span").text(data.mgr);
        }else {
            $("#mgr").next("span").text("");
        }
        if(undefined != data.sal){
            $("#sal").next("span").text(data.sal);
        }else {
            $("#sal").next("span").text("");
        }
        if(undefined != data.comm){
            $("#comm").next("span").text(data.sal);
        }else {
            $("#comm").next("span").text("");
        }
        if(undefined != data.deptno){
            $("#deptno").next("span").text(data.deptno);
        }else {
            $("#deptno").next("span").text("");
        }
    }
}

function formCheck(){
    var mes = [
        "员工编号必须为4位数字且不能为空！",
        "员工姓名为3-16位字母或2-4为中文且不能为空！",
        "员工工作2-30位英文字母或2-20位中文!",
        "直属领导为已存在的员工!",
        "员工工资为整数或小数！",
        "员工一年年薪为整数或小数！"
    ];
    var input = $("#myModal form input");
    var exp = [/^\d{4}$/,/^[a-zA-Z]{3,16}|[\u4E00-\u9FA5]{2,4}$/,/^[a-zA-Z]{2,30}|[\u4E00-\u9FA5]{2,20}$/,/^(\d{4}|)$/,/^\d+\.?\d{0,2}$/,/^\d+\.?\d{0,2}|$/];
    for(var i=0;i<6;i++){
        if(!exp[i].test(input[i].value)){
            $(input[i]).next("span").text(mes[i]);
            fCheck[i] = false;
        }else {
            $(input[i]).next("span").text("");
            fCheck[i] = true;
        }
    }
    var deptno = $("#myModal form select");
    if(deptno[0].value==""){
        $("#myModal #deptno").next("span").text("请选择部门！");
        fCheck[6] = false;
    }else{
        $("#myModal #deptno").next("span").next("");
        fCheck[6] = true;
    }
}
$("#mgr").blur(function () {
    if(this.value!="" && /^[0-9]{4}$/.test(this.value)){
        $("#mgr").next("span").text("");
        $.ajax({
            url:"http://localhost:8080/js/selectByOne",
            type:"get",
            data:{"empId":this.value},
            success:function (result) {
                if(result.code==200){
                    $("#mgr").next("span").text("直属领导为已存在的员工！");
                    fCheck[3] = false;
                }
                if(result.code==100){
                    $("#mgr").next("span").text("");
                    fCheck[3] = true;
                }
            }
        });
    }else{
        $("#mgr").next("span").text("员工领导为4位数字且不能为空！");
    }
});
$("#id").blur(function () {
    if(this.value!="" && /^[0-9]{4}$/.test(this.value)){
        $("#id").next("span").text("");
        $.ajax({
            url:"http://localhost:8080/js/selectByOne",
            type:"get",
            data:{"empId":this.value},
            success:function (result) {
                if(result.code==100){
                    $("#id").next("span").text("该员工id已存在！");
                    fCheck[3] = false;
                }
                if(result.code==200){
                    $("#id").next("span").text("");
                    fCheck[3] = true;
                }
            }
        });
    }else{
        $("#id").next("span").text("员工编号必须为4为数字且不能为空！");
    }
});


//以下为员工删除js
var check_val = [];         //定义一个员工id数组
//完成全选/全不选功能
$("#check_all").click(function () {
    allCheck();
});
function allCheck() {
    //attr获取checked是undefined;
    //我们这些dom原生的属性；attr获取自定义属性的值；
    //prop修改和读取dom原生属性的值
    var che = $(".check_item");
    $(che).prop("checked",$("#check_all").prop("checked"));
    checkValLogis();    //选中员工的id添加到数组
    //console.log(check_val);
}

//获取选中的员工id
function checkValLogis(){
    var check = $(".check_item:checked").parents("td").next("td");
    if(check.length!=0){
        for(var i=0;i<check.length;i++){
            check_val.push(check.eq(i).text());
        }
    }else{
        check_val = [];
    }
}

//判断选中5个
$(document).on("click",".check_item",function(){
    //判断当前选择中的元素是否5个
    var flag = $(".check_item:checked").length==$(".check_item").length;
    $("#check_all").prop("checked",flag);
    check_val = [];     //每次点击清空id集合
    checkValLogis();    //选中员工的id添加到数组
});

//批量删除点击事件
$("#sc-btn").click(function () {
    if(check_val.length!=0){
        //console.log(check_val);
        if(confirm("确定删除id为【"+check_val+"】的员工!")==true){
            $.ajax({
                url:"http://localhost:8080/js/deleteEmp?idArray="+check_val,
                type:"DELETE",
                success:function (result) {
                    if(result.code == 100){
                        check_val = [];
                        to_page(pas-1);     //上一页
                    }
                }
            });
        }
    }else{
        alert("请选择要删除的员工！")
    }
});

//单个删除
$(document).on("click",".sc-btn",function () {
    $("#check_all").prop("checked",false);
    $(".check_item").prop("checked",false);
    check_val = [];
    var text = $(this).parents("tr").children().eq(1).text();
    check_val.push(text);
    //checkValLogis();    //选中员工的id添加到数组
    if(check_val!==null){
        //console.log(check_val);
        if(confirm("确定删除编号为【"+check_val+"】的员工!")==true){
            $.ajax({
                url:"http://localhost:8080/js/deleteEmp?idArray="+check_val,
                type:"DELETE",
                success:function (result) {
                    if(result.code == 100){
                        check_val = [];
                        to_page(pns);   //去当前页
                    }
                }
            });
        }else{
            check_val = [];
        }
    }
});


//日期，及详细搜索方法
function outLogin() {
    window.location.replace("http://localhost:8080/boss/outLogin");
}

hiredateArray();
function hiredateArray() {
    var select = $("#dateArray").children("select");
    for (var y=8;y<10;y++){
        for(var i=1;i<13;i++){
            $("<option></option>").prop("value","201"+y+"/"+i+"/1").append("201"+y+"/"+i+"/1").appendTo(select);
        }
    }

}

//详细查询
var emp;
$("#search").click(function () {
    searchFormCheck();      //数据验证
    var input = $("#searchDiv form input");
    emp = {
        "id":input[0].value,
        "name":input[1].value,
        "job":input[2].value,
        "mgr":input[3].value,
        "hiredate":$("#dateArray select").eq(0).val(),
        "empss.hiredate":$("#dateArray select").eq(1).val(),
        "sal":input[4].value,
        "empss.sal":input[5].value,
        "comm":input[6].value,
        "empss.comm":input[7].value,
        "deptno":$("#s-deptno").val()
    };
    console.log(emp);
    to_page(1,emp);
});
function searchFormCheck() {
    var mes = [
        "员工编号必须为4位数字！",
        "员工姓名为3-16位字母或2-4为中文！",
        "员工工作2-30位英文字母或2-20位中文!",
        "直属领导为4位数字!",
        "最小工资必须为大于零的数最多两位小数！",
        "最大工资必须为大于零的数最多两位小数！",
        "最小奖金必须为大于零的数最多两位小数！",
        "最大奖金必须为大于零的数最多两位小数！",
    ];
    var input = $("#searchDiv form input");
    var exp = [/^(\d{4}|)$/,/^([a-zA-Z]*|[\u4E00-\u9FA5]*|)$/,/^([a-zA-Z]*|[\u4E00\u9FA5]*|)$/,/^(\d{4}|)$/,/^((^[1-9](\d+)?(\.\d{1,2})?$)|(^\d\.\d{1,2}$)|0|)$/,/^((^[1-9](\d+)?(\.\d{1,2})?$)|(^\d\.\d{1,2}$)|0|)$/,/^((^[1-9](\d+)?(\.\d{1,2})?$)|(^\d\.\d{1,2}$)|0|)$/,/^((^[1-9](\d+)?(\.\d{1,2})?$)|(^\d\.\d{1,2}$)|0|)$/];
    for (var i=0;i<8;i++){
        if(input[i].value!=""){
            if(!exp[i].test(input[i].value)){
                alert(mes[i]);
                input[i].value = "";
                return;
            }
        }
    }
}

$("#qk-btn").click(function () {
    $("#searchDiv form")[0].reset();
})