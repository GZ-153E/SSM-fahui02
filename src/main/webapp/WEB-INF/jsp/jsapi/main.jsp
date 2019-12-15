<%--
  Created by IntelliJ IDEA.
  User: _芸子
  Date: 2019/12/4
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    pageContext.setAttribute("path", request.getContextPath());
%>

<%
    // 权限验证
    if(session.getAttribute("LOGIN_USER")==null){
        response.sendRedirect("http://localhost:8080/boss/loginIndex");
        return;
    }
%>
<html>
<head>
    <title>主页</title>
    <script src="${path}/jquery/jquery-3.3.1.min.js"></script>

    <style>
        #app{
            margin-top: 150px;
        }
        .navbar-form div{
            margin-right: 10px;
        }
        .nav{
            font-family: "Arial Black";
            font-size: 20px;
            margin-top: -10px;
        }
    </style>
</head>
<body>

<div id="app" class="col-sm-8 col-md-offset-2">
    <div class="col-md-offset-5">
        <h2>员工管理系统主页</h2>
    </div>
    <br><br><br>
    <div class="nav">
        <button type="button" class="btn btn-success tj-btn">添加</button>
        <button type="button" class="btn btn-danger" id="sc-btn">删除</button>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:0;">当前登陆：<span>${LOGIN_USER.username}</span></a></li>
                <li class="dropdown">
                    <a href="javascript:0;" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的行为 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">添加行为</a></li>
                        <li><a href="#">修改行为</a></li>
                        <li><a href="#">删除行为</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="javascript:outLogin()">退出</a></li>
                    </ul>
                </li>
            </ul>
    </div>
    <br>
    <div id="searchDiv">
        <nav class="navbar navbar-default">
            <br>
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#"><b>详细搜索 ：</b></a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <label>员工编号： </label>
                            <input type="text" name="id" class="form-control" placeholder="员工编号"><br>
                        </div>
                        <div class="form-group">
                            <label>员工姓名： </label>
                            <input type="text" name="name" class="form-control" placeholder="员工姓名">
                        </div>
                        <div class="form-group">
                            <label>员工工作： </label>
                            <input type="text" name="job" class="form-control" placeholder="员工工作">
                        </div>
                        <div class="form-group">
                            <label>直属领导： </label>
                            <input type="text" name="mgr" class="form-control" placeholder="员工领导">
                        </div>
                        <br><br>
                        <div class="form-group" id="dateArray">
                            <label>入职日期： </label>
                            <select class="form-control" name="hiredate" style="width: 100px;">
                                <option selected="selected" value="">请选择起始时间</option>
                            </select>
                            <label> -- </label>
                            <select class="form-control" name="empss.hiredate" style="width: 100px;">
                                <option selected="selected" value="">请选择截至时间</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>员工工资： </label>
                            <input type="text" name="sal" class="form-control" placeholder="最小工资" style="width: 100px;">
                            <label> -- </label>
                            <input type="text" name="empss.sal" class="form-control" placeholder="最大工资" style="width: 100px;">
                        </div>
                        <div class="form-group">
                            <label>一年奖金： </label>
                            <input type="text" name="comm" class="form-control" placeholder="最小奖金" style="width: 100px;">
                            <label> -- </label>
                            <input type="text" name="empss.comm" class="form-control" placeholder="最大奖金" style="width: 100px;">
                        </div>
                        <div class="form-group">
                            <label>员工部门： </label>
                            <select class="form-control" name="deptno" id="s-deptno">
                                <option selected="selected" value="">请选择员工部门</option>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="col-md-offset-10" style="margin-top: 10px;">
                    <button class="btn btn-default" id="qk-btn">清空</button>
                    <button class="btn btn-success" id="search">搜索</button>
                </div>
                <br>
            </div>
        </nav>
    </div>
    <br>
    <table class="table table-hover">
        <thead>
            <tr>
                <td><input type="checkbox" id="check_all"/></td>
                <td>员工编号</td>
                <td>员工姓名</td>
                <td>员工工作</td>
                <td>直属领导</td>
                <td>入职日期</td>
                <td>员工工资</td>
                <td>一年奖金</td>
                <td>所属部门</td>
                <td>操作</td>
            </tr>
        </thead>
        <tbody>
            <%--<tr>
                <td>11</td>
                <td>11</td>
                <td>11</td>
                <td>11</td>
                <td>11</td>
                <td>11</td>
                <td>11</td>
                <td>11</td>
            </tr>--%>
        </tbody>
    </table>
    <div id="dh"></div>
    <nav aria-label="" class="col-md-offset-4" id="fy">
        <ul class="pagination">
            <%--<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
            <li class="active"><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>--%>
        </ul>
    </nav>
</div>

<div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                </div>
                <div class="modal-body">
                        <form class="form-horizontal" id="modelFrom">
                            <div class="form-group">
                                <label for="id" class="col-sm-2 control-label">员工编号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="id" name="id" placeholder="员工编号">
                                    <span style="color: red;"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">员工姓名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="员工姓名">
                                    <span style="color: red;"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="job" class="col-sm-2 control-label">员工工作</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="job" name="job" placeholder="员工工作">
                                    <span style="color: red;"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="mgr" class="col-sm-2 control-label">直属领导</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="mgr" name="mgr" placeholder="直属领导">
                                    <span style="color: red;"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="sal" class="col-sm-2 control-label">员工工资</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="sal" name="sal" placeholder="员工工资">
                                    <span style="color: red;"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="comm" class="col-sm-2 control-label">一年奖金</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="comm" name="comm" placeholder="一年奖金">
                                    <span style="color: red;"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="deptno" class="col-sm-2 control-label">所属部门</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="deptno" name="deptno">

                                    </select>
                                    <span style="color: red;"></span>
                                </div>
                            </div>
                        </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="save">保存</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script src="${path}/myjs/jsApi.js"></script>
<link href="${path}/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<script src="${path}/bootstrap/js/bootstrap.js"></script>
</html>
