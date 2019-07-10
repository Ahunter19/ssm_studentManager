<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- use EL-Expression-->
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" content="#">
    <title>管理员信息管理页面</title>
    <!-- 引入CSS -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/easyui/css/demo.css">
    <!-- 引入JS -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/easyui/themes/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/easyui/js/validateExtends.js"></script>

    <script type="text/javascript">
        //DOM加载完成后执行的回调函数
        $(function () {
            var table;
            //初始化datagrid
            $('#dataList').datagrid({
                iconCls: 'icon-more',//图标
                border: true,
                collapsible: false,//是否可折叠
                fit: true,//自动大小
                method: "post",
                url: "getUserList?t" + new Date().getTime(),
                idField: 'id',
                singleSelect: false,//是否单选
                rownumbers: true,//行号
                pagination: true,//分页控件
                sortName: 'id',
                sortOrder: 'DESC',
                remoteSort: false,
                columns: [[
                    {field: 'chk', checkbox: true, width: 50},
                    {field: 'id', title: 'ID', width: 100, sortable: true},
                    {field: 'username', title: '用户名', width: 150},
                    {field: 'password', title: '密码', width: 150}
                ]],
                toolbar: "#toolbar"//工具栏
            });

            //设置分页控件
            var p = $('#dataList').datagrid('getPager');
            $(p).pagination({
                pageSize: 10,//设置每页显示的记录条数,默认为10
                pageList: [10, 20, 30, 50, 100],//设置每页的记录条数
                beforePageText: '第',
                afterPageText: '页    共 {pages} 页',
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
            });

            //信息添加按钮事件
            $("#add").click(function () {
                table = $("#addTable");
                $("#addTable").form("clear");//清空表单数据
                $("#addDialog").dialog("open");//打开添加窗口
            });

            //信息修改按钮事件
            $("#edit").click(function () {
                table = $("#editTable");
                var selectRows = $("#dataList").datagrid("getSelections");
                if (selectRows.length !== 1) {
                    $.messager.alert("消息提醒", "请单条选择想要修改的数据哟 !", "warning");
                } else {
                    $("#editDialog").dialog("open");
                }
            });

            //信息删除按钮事件
            $("#delete").click(function () {
                var selectRows = $("#dataList").datagrid("getSelections");//返回所有选中的行,当没有选中的记录时,将返回空数组
                var selectLength = selectRows.length;
                if (selectLength === 0) {
                    $.messager.alert("消息提醒", "请选择想要删除的数据哟!", "warning");
                } else {
                    var ids = [];
                    $(selectRows).each(function (i, row) {
                        ids[i] = row.id;//将预删除行的id封装成一个数组
                    });
                    $.messager.confirm("消息提醒", "删除后将无法恢复该管理员信息! 确定继续?", function (r) {
                        if (r) {
                            $.ajax({
                                type: "post",
                                url: "deleteAdmin?t" + new Date().getTime(),
                                data: {ids: ids},
                                dataType: 'json',
                                success: function (data) {
                                    if (data.success) {
                                        $.messager.alert("消息提醒", "删除成功啦!", "info");
                                        $("#dataList").datagrid("reload");//刷新表格
                                        $("#dataList").datagrid("uncheckAll");//取消勾选当前页所有的行
                                    } else {
                                        $.messager.alert("消息提醒", "服务器端发生异常! 删除失败!", "warning");
                                    }
                                }
                            });
                        }
                    });
                }
            });

            //设置添加管理员信息窗口
            $("#addDialog").dialog({
                title: "添加管理员",
                width: 660,
                height: 380,
                iconCls: "icon-add?t" + new Date().getTime(),
                modal: true,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                draggable: true,
                closed: true,
                buttons: [
                    {
                        text: '添加',
                        plain: true,
                        iconCls: 'icon-user_add',
                        handler: function () {
                            var validate = $("#addForm").form("validate");
                            if (!validate) {
                                $.messager.alert("消息提醒", "请检查你输入的数据哟 !", "warning");
                            } else {
                                var data = $("#addForm").serialize();//序列化表单信息
                                $.ajax({
                                    type: "post",
                                    url: "addUser",
                                    data: data,
                                    dataType: 'json',
                                    success: function (data) {
                                        if ("success" == data.type) {
                                            $("#addDialog").dialog("close"); //关闭窗口
                                            $('#dataList').datagrid("reload");//重新刷新页面数据
                                            $.messager.alert("消息提醒", "添加成功啦!", "info");
                                        } else {
                                            $.messager.alert("消息提醒", data.msg, "warning");
                                        }
                                    }
                                });
                            }
                        }
                    },
                    {
                        text: '重置',
                        plain: true,
                        iconCls: 'icon-reload',
                        handler: function () {
                            $("#add_name").textbox('setValue', "");
                            $("#add_gender").textbox('setValue', "男");
                            $("#add_password").textbox('setValue', "");
                            $("#add_email").textbox('setValue', "");
                            $("#add_telephone").textbox('setValue', "");
                            $("#add_address").textbox('setValue', "");
                        }
                    }
                ]
            });

            //设置编辑管理员信息窗口
            $("#editDialog").dialog({
                title: "修改管理员信息",
                width: 380,
                height: 200,
                iconCls: "icon-edit",
                modal: true,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                draggable: true,
                closed: true,
                buttons: [
                    {
                        text: '提交',
                        plain: true,
                        iconCls: 'icon-edit',
                        handler: function () {
                            var validate = $("#editForm").form("validate");
                            if (!validate) {
                                $.messager.alert("消息提醒", "请检查你输入的数据哟 !", "warning");
                            } else {
                                var data = $("#editForm").serialize();//序列化表单信息
                                $.ajax({
                                    type: "post",
                                    url: "edit?t=" + new Date().getTime(),
                                    data: data,
                                    dataType: 'json',
                                    success: function (data) {
                                        if (data.type == "success") {
                                            $.messager.alert("消息提醒", "修改成功啦 !", "info");
                                            //关闭窗口
                                            $("#editDialog").dialog("close");
                                            //重新刷新页面数据
                                            $('#dataList').datagrid("reload");
                                            $('#dataList').datagrid("uncheckAll");
                                            //用户提示

                                        } else {
                                            $.messager.alert("消息提醒", data.msg, "warning");
                                        }
                                    }
                                });
                            }
                        }
                    },
                    {
                        text: '重置',
                        plain: true,
                        iconCls: 'icon-reload',
                        handler: function () {
                            $("#edit_name").textbox('setValue', "");
                            $("#edit_gender").textbox('setValue', "男");
                            $("#edit_telephone").textbox('setValue', "");
                            $("#edit_email").textbox('setValue', "");
                            $("#edit_telephone").textbox('setValue', "");
                            $("#edit_address").textbox('setValue', "");
                        }
                    }
                ],
                //打开窗口前先初始化表单数据(表单回显)
                onBeforeOpen: function () {
                    var selectRow = $("#dataList").datagrid("getSelected");
                    $("#edit_id").val(selectRow.id);//需根据id更新用户信息
                    $("#edit_username").textbox('setValue', selectRow.username);
                    $("#edit_password").textbox('setValue', selectRow.password);
                    //通过获取头像路径来显示该管理员的头像
                    $("#edit-portrait").attr('src', selectRow.portrait_path);
                }
            });

            //管理员姓名搜索按钮监听事件(将管理员姓名返回给Controller)
            $("#search-btn").click(function () {
                $('#dataList').datagrid('load', {
                    username: $('#search-username').val()//获取管理员姓名
                });
            });
        });
    </script>
</head>
<body>

<!-- 管理员列表信息 -->
<table id="dataList" cellspacing="0" cellpadding="0">
</table>

<!-- 工具栏 -->
<div id="toolbar">
    <%-- 通过JSTL设置用户操作权限: 将添加和删除按钮设置为仅管理员或教师可见	 --%>
    <div style="float: left;"><a id="add" href="javascript:" class="easyui-linkbutton"
                                 data-options="iconCls:'icon-add',plain:true">添加</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <div style="float: left;"><a id="edit" href="javascript:" class="easyui-linkbutton"
                                 data-options="iconCls:'icon-edit',plain:true">修改</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <div style="float: left;"><a id="delete" href="javascript:" class="easyui-linkbutton"
                                 data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
    <!-- 管理员姓名搜索域 -->
    <div style="margin-left: 10px;">
        <div style="float: left;" class="datagrid-btn-separator"></div>
        <a id="edit" href="javascript:" class="easyui-linkbutton"
           data-options="iconCls:'icon-user-teacher',plain:true">管理员姓名</a>
        <input id="search-username" class="easyui-textbox" name="username"/>
        <a id="search-btn" href="javascript:" class="easyui-linkbutton"
           data-options="iconCls:'icon-search',plain:true">搜索</a>
    </div>
</div>


<!-- 添加信息窗口 -->
<div id="addDialog" style="padding: 15px 0 0 45px;">
    <!-- 设置添加头像功能 -->
    <div style="float: right; margin: 10px 25px 0 0; width: 250px; border: 1px solid #EEF4FF" id="add-photo">
    </div>
    <!-- 管理员信息表单 -->
    <form id="addForm" method="post" action="#">
        <table id="addTable" border=0 style="width:200px; table-layout:fixed;" cellpadding="6">
            <!-- 存储所上传的头像路径 -->
            <input id="add_portrait-path" type="hidden" name="portrait_path"/>
            <tr>
                <td>姓名</td>
                <td colspan="4">
                    <input id="add_name" style="width: 200px; height: 30px;" class="easyui-textbox"
                           type="text" name="username" data-options="required:true, missingMessage:'请填写姓名哟~'"/>
                </td>
            </tr>
            <td>密码</td>
            <td colspan="4"><input id="add_password" style="width: 200px; height: 30px;" class="easyui-textbox"
                                   type="password" name="password"
                                   data-options="required:true, missingMessage:'请填写自定义密码哟~'"/>
            </td>
            </tr>
        </table>
    </form>
</div>


<!-- 修改信息窗口 -->
<div id="editDialog" style="padding: 15px 0 0 45px;">
    <!-- 管理员信息表单 -->
    <form id="editForm" method="post" action="#">
        <!-- 获取被修改信息的管理员id -->
        <input type="hidden" id="edit_id" name="id"/>
        <table id="editTable" border=0 style="width:160px; table-layout:fixed;" cellpadding="8">
            <tr>
                <td>姓名</td>
                <td><input id="edit_username" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"
                           name="username" data-options="required:true, missingMessage:'请填写用户名'"/>
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input id="edit_password" style="width: 200px; height: 30px;" class="easyui-textbox" type="text"
                           name="password" data-options="required:true, missingMessage:'请填写密码'"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<!-- 表单处理 -->
<iframe id="photo_target" name="photo_target" onload="uploaded(this)"></iframe>

</body>
</html>
