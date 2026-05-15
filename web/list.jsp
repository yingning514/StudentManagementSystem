<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.student.entity.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生列表</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background:#f5f5f5; }
        .box { max-width: 1000px; margin: 0 auto; background:#fff; padding: 20px; border-radius: 8px; }
        h1 { text-align:center; }
        table { width:100%; border-collapse: collapse; margin-top: 16px; }
        th, td { border:1px solid #ddd; padding: 10px; text-align:center; }
        th { background:#4CAF50; color:#fff; }
        a { color:#4CAF50; text-decoration:none; }
        .topbar { display:flex; justify-content: space-between; align-items:center; }
        .btn { display:inline-block; background:#4CAF50; color:#fff; padding:8px 14px; border-radius:6px; }
        .btn:hover { background:#45a049; }
        .danger { color:#d32f2f; }
    </style>
</head>
<body>
<div class="box">
    <h1>学生列表</h1>

    <div class="topbar">
        <a href="/">返回首页</a>
        <a class="btn" href="/student?action=add">+ 新增学生</a>
    </div>

    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        if (students == null || students.isEmpty()) {
    %>
    <p style="text-align:center;margin-top:20px;">暂无学生数据</p>
    <%
    } else {
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>学号</th>
            <th>姓名</th>
            <th>班级</th>
            <th>成绩</th>
            <th>操作</th>
        </tr>
        <%
            for (Student s : students) {
        %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getStudentId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getClassName() %></td>
            <td><%= s.getScore() %></td>
            <td>
                <a href="/student?action=edit&id=<%= s.getId() %>">编辑</a>
                |
                <a class="danger"
                   href="/student?action=delete&id=<%= s.getId() %>"
                   onclick="return confirm('确认删除该学生吗？');">删除</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
</div>
</body>
</html>