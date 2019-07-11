<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <!-- 引入CSS -->
    <link href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/h-ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
          type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/easyui/themes/default/easyui.css">
    <!-- 引入JS -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/h-ui/lib/icheck/jquery.icheck.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.js"></script>

    <!-- 页面事件 -->
    <script type="text/javascript">
        $(function () {
            //点击图片切换验证码
            $("#vcodeImg").click(function () {
                this.src = "get_cpacha?vl=4&w=160&h=55&t=" + new Date().getTime();
            });

            //登录按钮事件
            $("#submitBtn").click(function () {
                //提交用户的登录表单信息
                var data = $("#form").serialize();  //序列化
                $.ajax({
                    type: "post",
                    url: "login",
                    data: data,
                    dataType: "json",
                    success: function (data) {
                        if ("success" == data.type) {
                            window.location.href = "main";//进入系统主页面
                        } else {
                            $.messager.alert("提示", data.msg, "warning");
                            $("#vcodeImg").click();//切换验证码
                            $("input[name='vcode']").val("");//清空验证码输入框
                        }
                    }
                });
            });

            $(".skin-minimal input").iCheck({
                radioClass: 'iradio-blue',
                increaseArea: '25%'
            });
        })
    </script>

    <title>学生管理系统 | 登录页面 </title>
    <meta name="keywords" content="学生信息管理系统">
</head>

<body style="font-weight: lighter; ">
<div class="header" style="padding: 0;">
    <h3 style="font-weight: lighter; color: white; width: 550px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">
        Student Management System — SSM
    </h3>
</div>

<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form id="form" class="form form-horizontal" method="post" action="#">
            <!-- 用户身份信息 -->
            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe60a;</i></label>
                <div class="formControls col-8">
                    <input id="username" name="username" type="text" placeholder="账户" class="input-text radius size-L" value="root"/>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe63f;</i></label>
                <div class="formControls col-8">
                    <input id="password" name="password" type="password" placeholder="密码"
                           class="input-text radius size-L" value="1"/>
                </div>
            </div>
            <!-- 登录按钮 -->
            <div class="row">
                <div class="formControls col-8 col-offset-3">
                    <input id="submitBtn" type="button" class="btn btn-primary radius" value="&nbsp;
                    登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>

<!-- 页面底部版权声明 -->
<div class="footer">
    Copyright @ 2019 【it猎人工作室】 All rights reserved | 项目源码 : https://github.com/itHunter/ssm_studentManager
</div>
</body>
</html>