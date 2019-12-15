<%--
  Created by IntelliJ IDEA.
  User: _芸子
  Date: 2019/12/10
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>


<html>
<head>
    <title>登陆页</title>
    <script src="http://localhost:8080/jquery/jquery-3.3.1.min.js"></script>
    <style>
        #loginDiv{
            margin-top: 10%;
        }
    </style>
</head>
<body>
    <div class="col-lg-4 col-sm-offset-4 " id="loginDiv">
        <h3>员工管理系统登陆页</h3>
        <br>
        <br>
        <form class="form-horizontal">
            <div class="form-group">
                <label for="username" class="col-sm-2 control-label">用户名：</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="username" id="username" placeholder="User">
                    <span style="color: red;"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码：</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                    <span style="color: red;"></span>
                </div>
            </div>
        </form>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-5">
                <button class="btn btn-default" id="login">登陆</button>
            </div>
        </div>
    </div>

    <script>
        function checkForm() {
            var input = $("#loginDiv form input");
            var exp1 = /^[a-zA-Z_-]{4,10}$/;
            var exp2 = /^\w{6,16}$/;
            if(!exp1.test(input[0].value)){
                alert("用户名为4到10位大小字母数字_-组合且不能为空！");
                return false;
            }else if(!exp2.test(input[1].value)){
                alert("密码长度为6到16位且不能为空！");
                return false;
            }else {
                return true;
            }
        }
        $("#login").click(function () {
            if(checkForm()){
                $.ajax({
                    url:"http://localhost:8080/boss/login",
                    type:"post",
                    data:$("#loginDiv form").serialize(),
                    success:function (result) {
                        if(result.code == 100){
                            //alert("登陆成功！");
                            window.location.href = "http://localhost:8080/js/jsAPIIndex";
                        }
                        if(result.code == 200){
                            var data = result.extend.errorFields;
                            if(undefined != data.username){
                                $("#username").next("span").text(data.username);
                            }else {
                                $("#username").next("span").text("");
                            }
                            if(undefined != data.password){
                                $("#password").next("span").text(data.password);
                            }else {
                                $("#password").next("span").text("");
                            }
                        }
                    }
                })
            }
        })
    </script>
</body>
<link href="${path}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<script src="${path}/bootstrap/js/bootstrap.js"></script>
</html>
